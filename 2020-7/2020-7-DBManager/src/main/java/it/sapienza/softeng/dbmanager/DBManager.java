/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Alegi
 */
public class DBManager {

    public static void main(String[] args) throws Exception {

        if (args.length < 2) {
            System.out.println("ARG0: Pass the path of the database");
            System.out.println("ARG1: Pass 'create' to initialize the database, 'run' to print the content of the database");
            System.exit(1);
        }

        Class.forName("org.sqlite.JDBC");
        Connection conn
                = DriverManager.getConnection("jdbc:sqlite:" + args[0]);

        // manage session results
        if (conn != null) {
            System.out.println("Connected!");
        } else {
            System.out.println("Failed to make connection!");
        }

        Statement s = conn.createStatement();

        if (args[1].equals("create")) {

            //drop table if exists
            s.executeUpdate("drop table if exists movies;");
            s.executeUpdate("drop table if exists directors;");

            //Create tables
            String tableDirectors = "CREATE TABLE directors("
                    + "id INTEGER PRIMARY KEY,"
                    + "name TEXT,"
                    + "yearOfBirth TEXT"
                    + ");";

            String tableMovies = "CREATE TABLE movies("
                    + "id INT PRIMARY KEY,"
                    + "title TEXT,"
                    + "year TEXT,"
                    + "directorID INT,"
                    + "FOREIGN KEY (directorID) REFERENCES directors(id)"
                    + ");";

            s.executeUpdate(tableDirectors);
            s.executeUpdate(tableMovies);

            //Populate Tables
            s.executeUpdate("insert into directors values(1, 'Nolan', '1976');");
            s.executeUpdate("insert into directors values(2, 'Quentin Tarantulina', '1976');");
            s.executeUpdate("insert into directors values(3, 'Marvel directors', '1976');");
            s.executeUpdate("insert into directors values(4, 'DC directors', '1976');");

            s.executeUpdate("insert into movies values(1, 'Batman Begins', '2007','1');");
            s.executeUpdate("insert into movies values(2, 'Inception', '2006','1');");
            s.executeUpdate("insert into movies values(3, 'Bojack', '2018','2');");
            s.executeUpdate("insert into movies values(4, 'IronMan', '2010','3');");
            s.executeUpdate("insert into movies values(5, 'Thor', '2005','3');");
            s.executeUpdate("insert into movies values(6, 'Hulk', '2008','3');");
            s.executeUpdate("insert into movies values(7, 'Spiderman', '2019','3');");
            s.executeUpdate("insert into movies values(8, 'Batman e Robin', '1999','4');");
            s.executeUpdate("insert into movies values(9, 'Catwoman', '2002','4');");
            s.executeUpdate("insert into movies values(10, 'Wonder Woman', '2018','4');");
            s.executeUpdate("insert into movies values(11, 'AcquaMan', '2017','4');");
            s.executeUpdate("insert into movies values(12, 'Green Arrow', '2011','4');");
            s.executeUpdate("insert into movies values(13, 'Flash', '2013','4');");
            s.executeUpdate("insert into movies values(14, 'Atom', '2000','4');");
            s.executeUpdate("insert into movies values(15, 'Batman il cavalirere oscuro', '2009','1');");
        } else {
            ResultSet rs = s.executeQuery("select * from directors join movies where directors.id = movies.directorID ;");
            while (rs.next()) {
                System.out.print("Directors = " + rs.getString("id") + " is : ");
                System.out.println(rs.getString("name"));
            }
            rs.close();
        }
        conn.close();
    }
}
