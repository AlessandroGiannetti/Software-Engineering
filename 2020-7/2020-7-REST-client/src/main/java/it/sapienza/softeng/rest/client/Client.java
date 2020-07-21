/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.rest.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Alegi
 */
public class Client {

    private static final String BASE_URL = "http://localhost:8080/directors/";
    private static CloseableHttpClient client;

    public static void main(String[] args) throws IOException {
        client = HttpClients.createDefault();

        // Example GET directors
        System.out.println("INFO DIRECTORs");
        System.out.println(getDirectors());

        // Example GET director
        System.out.println("INFO DIRECTOR 1");
        System.out.println(getDirector("1"));

        // Example GET Movie
        System.out.println("INFO MOVIES ID: 1 DIRECTOR ID: 1");
        System.out.println(getMovie("1", "1"));

        client.close();

    }

    private static Movie getMovie(String directorID, String movieID) throws IOException {
        final URL url = new URL(BASE_URL + directorID + "/movies/" + movieID);
        final InputStream input = url.openStream();

        ObjectMapper mapper = new ObjectMapper();
        Movie movie = (Movie) mapper.readValue(input, Movie.class);
        return movie;
    }

    private static Director getDirector(String directorID) throws IOException {
        final URL url = new URL(BASE_URL + directorID);
        final InputStream input = url.openStream();

        ObjectMapper mapper = new ObjectMapper();
        Director director = (Director) mapper.readValue(input, Director.class);
        return director;
    }

    private static List<Director> getDirectors() throws IOException {
        final URL url = new URL(BASE_URL);
        final InputStream input = url.openStream();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(input, new TypeReference<List<Director>>() {
        });

    }

}
