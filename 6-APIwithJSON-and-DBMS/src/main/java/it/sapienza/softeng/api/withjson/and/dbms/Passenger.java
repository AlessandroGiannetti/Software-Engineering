/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.api.withjson.and.dbms;

/**
 *
 * @author Alegi
 */
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Passenger")
public class Passenger {

    private int id;
    private String name;

    Passenger(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public int hashCode() {
        return id + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Passenger) && (id == ((Passenger) obj).getId()) && (name.equals(((Passenger) obj).getName()));
    }

    @Override
    public String toString() {
        return "passenger " + id + " " + name;
    }
}
