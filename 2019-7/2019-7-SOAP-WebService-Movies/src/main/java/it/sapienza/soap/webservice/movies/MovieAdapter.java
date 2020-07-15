/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.movies;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author biar
 */
public class MovieAdapter extends XmlAdapter<MovieImpl, Movie> {

    @Override
    public MovieImpl marshal(Movie movie) throws Exception {
        if (movie instanceof MovieImpl) {
            return (MovieImpl) movie;
        }
        return new MovieImpl(movie.getID(), movie.getTitle(), movie.getYear(), movie.getDirectorID());
    }

    @Override
    public Movie unmarshal(MovieImpl movie) throws Exception {
        return (MovieImpl) movie;
    }

}
