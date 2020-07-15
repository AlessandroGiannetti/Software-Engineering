package it.sapienza.soap.webservice.bank;

import javax.xml.ws.Endpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alegi
 */
public class Server {

    public static void main(String args[]) throws InterruptedException {
        BankImpl implementor = new BankImpl();
        String address = "http://localhost:8080/bank";
        Endpoint.publish(address, implementor);
        System.out.println("--- SERVER IS UP ---");
        while (true) {

        }
    }
}
