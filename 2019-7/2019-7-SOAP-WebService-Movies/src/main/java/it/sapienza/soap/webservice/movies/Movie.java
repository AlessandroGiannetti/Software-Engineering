/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.movies;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author biar
 */
@XmlJavaTypeAdapter(MovieAdapter.class)
public interface Movie {

    public String getTitle();

    public String getYear();

    public String getID();

    public String getDirectorID();
}
