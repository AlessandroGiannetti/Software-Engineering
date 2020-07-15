/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.movies;

import java.util.HashSet;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author biar
 */
public class MovieMapAdapter extends XmlAdapter<MovieMap, HashSet<Movie>> {

    @Override
    public HashSet<Movie> unmarshal(MovieMap vt) throws Exception {
        HashSet<Movie> res = new HashSet<>();
        for (Movie m : vt.getEntries()) {
            res.add(m);
        }
        return res;
    }

    @Override
    public MovieMap marshal(HashSet<Movie> bt) throws Exception {
        MovieMap res = new MovieMap();
        for (Movie m : bt) {
            res.addMovie(m);
        }
        return res;
    }
}
