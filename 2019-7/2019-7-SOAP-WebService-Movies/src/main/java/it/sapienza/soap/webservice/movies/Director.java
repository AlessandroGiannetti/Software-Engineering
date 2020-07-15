/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.movies;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Alegi
 */
@XmlType(name = "Director")
public class Director {

    private String name;
    private String yearOfBirth;
    private String ID;

    public Director(String name, String yearOfBirth, String ID) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
