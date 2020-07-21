/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.rest.api;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alegi
 */

@JacksonXmlRootElement(localName = "Director")
public class Director {

    private int id;
    private String name;
    private String year;
    private List<Movie> movies = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public void setMovies (List<Movie> movies) {
        this.movies = movies;
    }

    @GET
    @Path("{movieId}")
    public Movie getMovie(@PathParam("movieId") int movieId) {
        return findById(movieId);
    }

    @POST
    public Response createMovie(Movie movie) {
        for (Movie element : movies) {
            if (element.getID() == movie.getID()) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
        movies.add(movie);
        return Response.ok(movie).build();
    }

    @DELETE
    @Path("{movieId}")
    public Response deleteMovie(@PathParam("movieId") int movieId) {
        Movie movie = findById(movieId);
        if (movie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        movies.remove(movie);
        return Response.ok().build();
    }

    private Movie findById(int id) {
        for (Movie movie : movies) {
            if (movie.getID().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        return id + name.hashCode() + year.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Director) && (id == ((Director) obj).getId()) && (name.equals(((Director) obj).getName())) && (year.equals(((Director) obj).getYear()));
    }

    @Override
    public String toString() {
        return "Director: " + id + " " + name + " " + year;
    }

}
