/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.jms.server.flights;

/**
 *
 * @author Alegi
 */
public class Server {
    public static void main(String args[]) throws Exception {
        FlightProductor productor = new FlightProductor();
        productor.start();      
    }
}