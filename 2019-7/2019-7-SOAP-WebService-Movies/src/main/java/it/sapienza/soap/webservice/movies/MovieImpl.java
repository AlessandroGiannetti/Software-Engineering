/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.movies;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author biar
 */
@XmlType(name = "Movie")
public class MovieImpl implements Movie {

    private String id;
    private String title;
    private String year;
    private String directorID;

    public MovieImpl(String id, String title, String year, String directorID) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.directorID = directorID;
    }

    public MovieImpl() {
    }

    @Override
    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public String getDirectorID() {
        return directorID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

}
