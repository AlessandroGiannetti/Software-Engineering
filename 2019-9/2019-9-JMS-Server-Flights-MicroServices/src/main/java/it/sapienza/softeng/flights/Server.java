/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.flights;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author Alegi
 */
public class Server {
    public static void main(String args[]) throws Exception {
        
        //check if activeMQ is up and running (micro service)
        boolean serverReady = false;

        while (!serverReady) {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress("broker", 8161), 5000);
                socket.close();
                serverReady = true;
                System.out.println("... broker is finally ready");
            } catch(Exception ex){
                serverReady = false;
                System.out.println("... broker NOT yet ready...");
            }
        }
        
        FlightProductor productor = new FlightProductor();
        productor.start();      
    }
}