/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.rest.api;

import javax.ws.rs.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alegi
 */
@Path("/directors")
public class DirectorsRepository {

    private Connection conn;

    public void setConnection(String pos) {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DirectorsRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn
                    = DriverManager.getConnection("jdbc:sqlite:" + pos);
        } catch (SQLException ex) {
            Logger.getLogger(DirectorsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // GET ALL DIRECTOR 
    @GET
    @Path("/")
    @Produces("application/json")
    public List<Director> getDirectors() {
        return getAllDirectors();
    }

    // GET SPECIFIC DIRECTOR 
    @GET
    @Path("{directorId}")
    @Produces("application/json")
    public Director getDirector(@PathParam("directorId") int directorId) {
        return findDirectorById(directorId);
    }

    // GET SPECIFIC MOVIE OF A SPECIFIC DIRECTOR
    @GET
    @Path("{directorId}/movies/{movieId}")
    @Produces("application/json")
    public Movie pathToMovie(@PathParam("directorId") int directorId, @PathParam("movieId") int movieId) {
        return findMovieByDirectorIdAndMovieId(directorId,movieId);
    }

    private Director findDirectorById(int id) {

        PreparedStatement stat = null;
        PreparedStatement stat2 = null;
        Director director = null;
        Movie movie = null;

        try {
            stat = conn.prepareStatement("select * from directors where id = ?");
            stat.setString(1, String.valueOf(id));

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                try {
                    stat2 = conn.prepareStatement("select * from movies where directorID = ?");
                    stat2.setString(1, String.valueOf(rs.getString("id")));

                    ResultSet moviesRes = stat2.executeQuery();

                    director = new Director();
                    director.setId(Integer.parseInt(rs.getString("id")));
                    director.setName(rs.getString("name"));
                    director.setYear(rs.getString("yearOfBirth"));
                    while (moviesRes.next()) {
                        movie = new Movie();
                        movie.setID(moviesRes.getString("id"));
                        movie.setTitle(moviesRes.getString("title"));
                        movie.setYear(moviesRes.getString("year"));
                        movie.setDirectorID(moviesRes.getString("directorID"));

                        director.addMovie(movie);
                    }
                    Logger.getLogger(DirectorsRepository.class.getName()).log(Level.INFO, "Accessed : " + director + director.getMovies());
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(DirectorsRepository.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(DirectorsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return director;
    }

    private Movie findMovieByDirectorIdAndMovieId(int directorId, int movieId) {

        PreparedStatement stat = null;
        PreparedStatement stat2 = null;
        Director director = null;
        Movie movie = null;
        try {
            stat2 = conn.prepareStatement("select * from movies where directorID = ? and movies.id = ?");
            stat2.setString(1, String.valueOf(directorId));
            stat2.setString(2, String.valueOf(movieId));

            ResultSet moviesRes = stat2.executeQuery();

            movie = new Movie();
            movie.setID(moviesRes.getString("id"));
            movie.setTitle(moviesRes.getString("title"));
            movie.setYear(moviesRes.getString("year"));
            movie.setDirectorID(moviesRes.getString("directorID"));

        } catch (SQLException ex) {
            Logger.getLogger(DirectorsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return movie;
    }

    private List<Director> getAllDirectors() {

        PreparedStatement stat = null;
        PreparedStatement stat2 = null;
        List<Director> directors = new ArrayList<>();
        Director director = null;
        Movie movie = null;

        try {
            stat = conn.prepareStatement("select * from directors ");

            ResultSet directorRs = stat.executeQuery();

            while (directorRs.next()) {
                try {
                    stat2 = conn.prepareStatement("select * from movies where directorID = ?");
                    stat2.setString(1, String.valueOf(directorRs.getString("id")));

                    ResultSet moviesRes = stat2.executeQuery();

                    director = new Director();
                    director.setId(Integer.parseInt(directorRs.getString("id")));
                    director.setName(directorRs.getString("name"));
                    director.setYear(directorRs.getString("yearOfBirth"));

                    while (moviesRes.next()) {
                        movie = new Movie();
                        movie.setID(moviesRes.getString("id"));
                        movie.setTitle(moviesRes.getString("title"));
                        movie.setYear(moviesRes.getString("year"));
                        movie.setDirectorID(moviesRes.getString("directorID"));

                        director.addMovie(movie);
                    }

                    directors.add(director);
                    Logger
                            .getLogger(DirectorsRepository.class
                                    .getName()).log(Level.INFO, "Accessed : " + director + director.getMovies());

                } catch (SQLException ex) {
                    Logger.getLogger(DirectorsRepository.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }
            directorRs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DirectorsRepository.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return directors;
    }

}
