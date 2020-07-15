/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.movies;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author biar
 */
@XmlType(name = "MovieMap")
public class MovieMap {

    private List<Movie> entries = new ArrayList<Movie>();

    @XmlElement(nillable = false, name = "entry")
    public List<Movie> getEntries() {
        return entries;
    }

    public void addMovie(Movie m) {
        entries.add(m);
    }
}
