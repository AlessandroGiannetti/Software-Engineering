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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/fligths")
public class FligthsRepository {

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
                Logger.getLogger(FligthsRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn
                    = DriverManager.getConnection("jdbc:sqlite:" + pos);
        } catch (SQLException ex) {
            Logger.getLogger(FligthsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{fligthId}")
    @Produces("application/json")

    public Flight getFligth(@PathParam("fligthId") int fligthId) {

        return findById(fligthId);
    }

    @PUT
    @Path("{fligthId}")
    @Consumes("application/json")
    public Response updateFligth(@PathParam("fligthId") int fligthId, Flight fligth) {
        Flight existing = findById(fligthId);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (existing.equals(fligth)) {
            return Response.notModified().build();
        }
        // fligths.put(fligthId, fligth);
        update(fligthId, fligth);
        return Response.ok().build();
    }

    @Path("{fligthId}/passengers")
    public Flight pathToPassenger(@PathParam("fligthId") int fligthId) {
        return findById(fligthId);
    }

    private Flight findById(int id) {

        PreparedStatement stat = null;
        PreparedStatement stat2 = null;
        Flight fl = null;

        try {
            stat = conn.prepareStatement("select * from fligth where id = ?");
            stat.setString(1, String.valueOf(id));

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                try {
                    stat2 = conn.prepareStatement("select * from passenger where fligth = ?");
                    stat2.setString(1, String.valueOf(rs.getString("id")));

                    ResultSet passengerRes = stat2.executeQuery();

                    fl = new Flight();
                    fl.setId(Integer.parseInt(rs.getString("id")));
                    fl.setName(rs.getString("name"));
                    while (passengerRes.next()) {
                        fl.addPassenger(new Passenger(Integer.parseInt(passengerRes.getString("id")), passengerRes.getString("name")));
                    }
                    Logger.getLogger(FligthsRepository.class.getName()).log(Level.INFO, "Accessed : " + fl + fl.getPassengers());
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(FligthsRepository.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(FligthsRepository.class.getName()).log(Level.SEVERE, null, ex);
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

    private void update(int fligthId, Flight fligth) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement("update fligth set name = ? where id = ?");
            stat.setString(1, fligth.getName());
            stat.setString(2, String.valueOf(fligthId));

            int affectedRow = stat.executeUpdate();

            if (affectedRow == 1) {
                Logger.getLogger(FligthsRepository.class
                        .getName()).log(Level.INFO, "Updated : " + fligth);

                return;
            } else {
                throw new RuntimeException();

            }
        } catch (Exception ex) {
            Logger.getLogger(FligthsRepository.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
