/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.api.withjson.and.dbms;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author studente
 */
@JacksonXmlRootElement(localName = "Fligth")
public class Flight {

    private int id;
    private String name;
    private List<Passenger> passengers = new ArrayList<>();

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

    public List<Passenger> getPassengers() {
        return passengers;
    }
    
    public void addPassenger(Passenger passenger){
        this.passengers.add(passenger);
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
    
    @GET
    @Path("{passengerId}")
    public Passenger getPassenger(@PathParam("passengerId") int passengerId) {
        return findById(passengerId);
    }

    @POST
    public Response createPassenger(Passenger passenger) {
        for (Passenger element : passengers) {
            if (element.getId() == passenger.getId()) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
        passengers.add(passenger);
        return Response.ok(passenger).build();
    }

    @DELETE
    @Path("{passengerId}")
    public Response deleteStudent(@PathParam("passengerId") int passengerId) {
        Passenger passenger = findById(passengerId);
        if (passenger == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        passengers.remove(passenger);
        return Response.ok().build();
    }

    private Passenger findById(int id) {
        for (Passenger passenger : passengers) {
            if (passenger.getId() == id) {
                return passenger;
            }
        }
        return null;
    }
    

    @Override
    public int hashCode() {
        return id + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Flight) && (id == ((Flight) obj).getId()) && (name.equals(((Flight) obj).getName()));
    }

    @Override
    public String toString() {
        return "fligth " + id + " " + name;
    }

}
