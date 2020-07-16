/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.json.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author studente
 */
public class Client {

    private static final String BASE_URL = "http://localhost:8080/flights/";
    private static CloseableHttpClient client;

    public static void main(String[] args) throws IOException {
        client = HttpClients.createDefault();
        // Example GET passenger
        System.out.println(getPassenger("1", "1"));

        // Example GET flight
        System.out.println(getFlight("1"));

        // Example POST passenger 
        System.out.println(getFlight("2"));
        System.out.println("Insert francesco on flight 2");
        createValidPassenger();
        System.out.println(getFlight("2"));

        client.close();

    }

    private static Passenger getPassenger(String flightID, String passengerID) throws IOException {
        final URL url = new URL(BASE_URL + flightID + "/passengers/" + passengerID);
        final InputStream input = url.openStream();

        ObjectMapper mapper = new ObjectMapper();
        Passenger pass = (Passenger) mapper.readValue(input, Passenger.class);
        return pass;
    }

    private static Flight getFlight(String flightID) throws IOException {
        final URL url = new URL(BASE_URL + flightID);
        final InputStream input = url.openStream();

        ObjectMapper mapper = new ObjectMapper();
        Flight fl = (Flight) mapper.readValue(input, Flight.class);
        return fl;
    }

    private static void createValidPassenger() throws IOException {
        final HttpPost httpPost = new HttpPost(BASE_URL + "2/passengers/");
        Passenger newPass = new Passenger(3, "Francesco", 2);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newPass);

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(httpPost);
        System.out.println(response);

    }
}
