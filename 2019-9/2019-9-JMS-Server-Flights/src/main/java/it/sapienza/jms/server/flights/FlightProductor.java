/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.jms.server.flights;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author Alegi
 */
public class FlightProductor {
    private Random randomGen = new Random();
    private static final String uppercaseCharacters = "QWERTYUIOPASDFGHJKLZXCVBNM";


    private String getFlight() {
        return String.format("%c%c%03d", // 2 characters + 3 digits
                uppercaseCharacters.charAt(randomGen.nextInt(uppercaseCharacters.length())),
                uppercaseCharacters.charAt(randomGen.nextInt(uppercaseCharacters.length())),
                randomGen.nextInt(1000));
    }


    public void start() throws NamingException, JMSException {
        Properties properties = null;
        Context jndiContext = null;

        try {
            properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(properties);
        } catch (NamingException e) {
            System.out.println(e.toString());
            System.exit(1);
        }
        
        ConnectionFactory connectionFactory = (ConnectionFactory)jndiContext.lookup("ConnectionFactory");
        Destination destination = (Destination)jndiContext.lookup("dynamicTopics/Flights");

        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);

        int item = 0;
        String flight;
        String text;
        TextMessage message = session.createTextMessage();
        while (true) {
            flight = getFlight();
            
            message.setStringProperty("flight", flight);
            text = String.format("Item %d >> flight %s has landed : %b", item, flight, randomGen.nextBoolean());
            message.setText(text);

            producer.send(message);
            System.out.println(text);
            item += 1;

            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}