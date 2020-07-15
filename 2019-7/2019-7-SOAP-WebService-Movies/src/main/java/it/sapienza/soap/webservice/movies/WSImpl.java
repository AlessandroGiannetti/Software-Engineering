/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.movies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

@WebService(endpointInterface = "it.sapienza.soap.webservice.movies.WSInterface")
public class WSImpl implements WSInterface {

    private Connection conn;

    public void setConnection() {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(WSImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn = DriverManager.getConnection("jdbc:sqlite:../database/moviesDB/database");

            // manage session results
            if (conn != null) {
                System.out.println("Connected!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(WSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public HashSet<Movie> getMovies() {
        PreparedStatement stat = null;
        HashSet<Movie> res = new HashSet<>();
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:../database/moviesDB/database");
            stat = conn.prepareStatement("select * from movies");
            ResultSet rs = stat.executeQuery();
            System.out.println("prima");
            while (rs.next()) {
                System.out.println(rs.getString("id") + " " + rs.getString("title") + " " + rs.getString("year") + " " + rs.getString("directorID"));
                Movie m = new MovieImpl(rs.getString("id"), rs.getString("title"), rs.getString("year"), rs.getString("directorID"));
                res.add(m);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(WSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(res);
        return res;
    }

    @Override
    public Director getDirector(String ID) {
        return findById(ID);
    }

    private Director findById(String id) {
        PreparedStatement stat = null;
        Director dir = null;
        try {
            stat = conn.prepareStatement("select * from directors where id = ?");
            stat.setString(1, id);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                dir = new Director(rs.getString("name"), rs.getString("yearOfBirth"), rs.getString("id"));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(WSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dir;
    }
}
