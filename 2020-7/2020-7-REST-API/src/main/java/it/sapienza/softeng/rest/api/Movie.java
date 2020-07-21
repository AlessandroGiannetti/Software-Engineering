/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.rest.api;

/**
 *
 * @author Alegi
 */
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Movie")
public class Movie {

    private String id;
    private String title;
    private String year;
    private String directorID;
    
    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

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
    
    @Override
    public int hashCode() {
        return id.hashCode() + title.hashCode() + year.hashCode() + directorID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Movie) && (id == ((Movie) obj).getID()) && (title.equals(((Movie) obj).getTitle()));
    }

    @Override
    public String toString() {
        return "Movie " + id + " " + title + " " + year;
    }

}
