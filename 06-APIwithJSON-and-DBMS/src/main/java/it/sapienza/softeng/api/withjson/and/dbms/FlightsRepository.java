/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.api.withjson.and.dbms;

/**
 *
 * @author studente
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/flights")
public class FlightsRepository {

    private Connection conn;

    /* Initial simple version, based on main memory
            
            final private Map<Integer, Fligth> fligths = new HashMap<>();
            {
            
            Fligth fl1 = new Fligth();
            Fligth fl2 = new Fligth();
            fl1.setId(1);
            fl1.setName("AZ140");
            fl2.setId(2);
            fl2.setName("LH9120");
            
            fligths.put(1, fl1);
            fligths.put(2, fl2);
            }
     */
    public void setConnection(String pos) {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FlightsRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn
                    = DriverManager.getConnection("jdbc:sqlite:" + pos);
        } catch (SQLException ex) {
            Logger.getLogger(FlightsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // GET SPECIFIC FLIGHT  
    @GET
    @Path("{flightId}")
    @Produces("application/json")
    public Flight getFlight(@PathParam("flightId") int flightId) {
        return findFlightById(flightId);
    }

    // GET SPECIFIC PASSENGER OF A SPECIFIC FLIGHT
    @GET
    @Path("{flightId}/passengers/{passengerId}")
    @Produces("application/json")
    public Passenger pathToPassenger(@PathParam("flightId") int flightId, @PathParam("passengerId") int passengerId) {
        return findPassengerByFlightId(flightId, passengerId);
    }

    // MODIFICATION/CREATION OF A FLIGHT
    @POST
    @Path("{flightId}")
    @Consumes("application/json")
    public Response updateFlight(@PathParam("flightId") int flightId, Flight flight) {
        Flight existing = findFlightById(flightId);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (existing.equals(flight)) {
            return Response.notModified().build();
        }
        // fligths.put(fligthId, fligth);
        executeUpdateFlight(flightId, flight);
        return Response.ok().build();
    }

    // CREATION OF A PASSENGER
    @POST
    @Path("{flightId}/passengers/")
    @Consumes("application/json")
    public Response updatePassenger(@PathParam("flightId") int flightId, Passenger passenger) {

        // verify if the flight exist and if there is a already a passenger equal
        Passenger Pexisting = findPassengerByFlightId(flightId, passenger.getId());
        Flight Fexisting = findFlightById(flightId);
        if (Fexisting == null) {
            System.out.println("insert an existing flight");
            return Response.status(Response.Status.NOT_FOUND).entity("Flight with code: " + flightId + " Does not exist").build();
        }
        if (Pexisting != null) {
            System.out.println("Passenger already exist");
            return Response.status(Response.Status.CONFLICT).entity("Passenger with code: " + passenger.getId() + " Does not exist").build();
        }
        // fligths.put(fligthId, fligth);
        executeUpdatePassenger(flightId, passenger);
        return Response.ok().build();
    }

    private Flight findFlightById(int id) {

        PreparedStatement stat = null;
        PreparedStatement stat2 = null;
        Flight fl = null;
        Passenger passenger = null;

        try {
            stat = conn.prepareStatement("select * from flight where id = ?");
            stat.setString(1, String.valueOf(id));

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                try {
                    stat2 = conn.prepareStatement("select * from passenger where flightID = ?");
                    stat2.setString(1, String.valueOf(rs.getString("id")));

                    ResultSet passengerRes = stat2.executeQuery();

                    fl = new Flight();
                    fl.setId(Integer.parseInt(rs.getString("id")));
                    fl.setName(rs.getString("name"));
                    while (passengerRes.next()) {
                        passenger = new Passenger();
                        passenger.setId(Integer.parseInt(passengerRes.getString("id")));
                        passenger.setName(passengerRes.getString("name"));
                        passenger.setFlightID(Integer.parseInt(passengerRes.getString("flightID")));

                        fl.addPassenger(passenger);
                    }
                    Logger.getLogger(FlightsRepository.class.getName()).log(Level.INFO, "Accessed : " + fl + fl.getPassengers());
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(FlightsRepository.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlightsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* simple version 
        for (Map.Entry<Integer, Fligth> fligth : fligths.entrySet()) {
            if (fligth.getKey() == id) {
                return fligth.getValue();
            }
        }
         */
        return fl;
    }

    private Passenger findPassengerByFlightId(int flightId, int passengerId) {
        PreparedStatement stat = null;
        Passenger passenger2 = null;

        try {
            stat = conn.prepareStatement("select * from passenger where flightID = ? AND id = ?");
            stat.setString(1, String.valueOf(flightId));
            stat.setString(2, String.valueOf(passengerId));

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                passenger2 = new Passenger();
                passenger2.setId(Integer.parseInt(rs.getString("id")));
                passenger2.setName(rs.getString("name"));
                passenger2.setFlightID(Integer.parseInt(rs.getString("flightID")));

                Logger.getLogger(FlightsRepository.class.getName()).log(Level.INFO, "Accessed : " + passenger2);
                rs.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(FlightsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* simple version 
        for (Map.Entry<Integer, Fligth> fligth : fligths.entrySet()) {
            if (fligth.getKey() == id) {
                return fligth.getValue();
            }
        }
         */
        return passenger2;
    }

    private void executeUpdateFlight(int flightId, Flight flight) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement("INSERT INTO flight (id, name) VALUES ?, ?");
            stat.setString(1, flight.getName());
            stat.setString(2, String.valueOf(flightId));

            int affectedRow = stat.executeUpdate();

            if (affectedRow == 1) {
                Logger.getLogger(FlightsRepository.class
                        .getName()).log(Level.INFO, "Updated : " + flight);

                return;
            } else {
                throw new RuntimeException();

            }
        } catch (Exception ex) {
            Logger.getLogger(FlightsRepository.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void executeUpdatePassenger(int flightId, Passenger passenger) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement("INSERT INTO passenger (id, name, flightID) VALUES (?, ?, ?)");

            stat.setString(1, String.valueOf(passenger.getId()));
            stat.setString(2, passenger.getName());
            stat.setString(3, String.valueOf(flightId));

            int affectedRow = stat.executeUpdate();

            if (affectedRow == 1) {
                Logger.getLogger(FlightsRepository.class
                        .getName()).log(Level.INFO, "Updated : " + passenger);

                return;
            } else {
                throw new RuntimeException();

            }
        } catch (Exception ex) {
            Logger.getLogger(FlightsRepository.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
