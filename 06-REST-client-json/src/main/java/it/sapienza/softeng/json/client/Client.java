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
import java.io.OutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author studente
 */
public class Client {

    private static final String BASE_URL = "http://localhost:8080/fligths/";
    private static CloseableHttpClient client;

    public static void main(String[] args) throws IOException {
        client = HttpClients.createDefault();

        // Example GET
        
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(BASE_URL + "2");
        
        InputStream input = url.openStream();
        
        Fligth fl = (Fligth)mapper.readValue(input, Fligth.class);        
        System.out.println(fl);
        

        // Example POST/PUT
        
        ObjectMapper objectMapper = new ObjectMapper();
        Fligth newFl = new Fligth();
        
        newFl.setId(4);
        newFl.setName("XX000");
        
        String json = objectMapper.writeValueAsString(newFl); 
        
        HttpPut httpPut = new HttpPut(BASE_URL + "2/");
        
        StringEntity entity = new StringEntity(json);
        httpPut.setEntity(entity);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(httpPut);
        System.out.println(response);
        
        InputStream input2 = url.openStream();
        fl = (Fligth) mapper.readValue(input2, Fligth.class);
        System.out.println(fl);
    }
}