/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.json.client;

/**
 *
 * @author Alegi
 */
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Passenger")
public class Passenger {

    private int id;
    private String name;
    private int flightID;

    Passenger(int id, String name, int flightID) {
        this.id = id;
        this.name = name;
        this.flightID = flightID;
    }

    Passenger() {
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

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
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
        return "passenger: ID:" + id + " Name: " + name + " FlightID: " + flightID;
    }
}
