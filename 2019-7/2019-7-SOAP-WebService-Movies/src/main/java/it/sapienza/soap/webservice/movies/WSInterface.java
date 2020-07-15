/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.movies;

import java.util.HashSet;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@WebService
public interface WSInterface {

    @XmlJavaTypeAdapter(MovieMapAdapter.class)
    public HashSet<Movie> getMovies();

    public Director getDirector(String ID);
}
