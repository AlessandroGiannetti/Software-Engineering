/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.flights;


import javax.naming.*;

import java.util.Properties;
import java.util.Random;
import javax.jms.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alegi
 */
public class FlightProductor {

    private static final Logger LOG = LoggerFactory.getLogger(FlightProductor.class);

    private Random randomGen = new Random();
    private static final String uppercaseCharacters = "QWERTYUIOPASDFGHJKLZXCVBNM";

    private String getFlight() {
        return String.format("%c%c%03d", // 2 characters + 3 digits
                uppercaseCharacters.charAt(randomGen.nextInt(uppercaseCharacters.length())),
                uppercaseCharacters.charAt(randomGen.nextInt(uppercaseCharacters.length())),
                randomGen.nextInt(1000));
    }

    public void start() throws NamingException, JMSException {
        LOG.info("IN");
        Context jndiContext = null;
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;
        String destinationName = "dynamicTopics/Flights";

        /*
         * Create a JNDI API InitialContext object
         */
        try {
            Properties props = new Properties();

            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://broker:61616");
            jndiContext = new InitialContext(props);

        } catch (NamingException e) {
            LOG.info("ERROR in JNDI: " + e.toString());
            System.exit(1);
        }

        /*
         * Look up connection factory and destination.
         */
        try {
            connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            destination = (Destination) jndiContext.lookup(destinationName);
        } catch (NamingException e) {
            LOG.info("JNDI API lookup failed: " + e);
            System.exit(1);
        }

        /*
         * Create connection. Create session from connection; false means
         * session is not transacted. Create sender and text message. Send
         * messages, varying text slightly. Send end-of-messages message.
         * Finally, close connection.
         */
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(destination);

            TextMessage message = null;
            int item = 0;
            String flight;
            String text;

            message = session.createTextMessage();

            while (true) {
                item++;
                flight = getFlight();

                message.setStringProperty("flight", flight);
                text = String.format("Item %d >> flight %s has landed : %b", item, flight, randomGen.nextBoolean());
                message.setText(text);

                LOG.info(text);

                producer.send(message);

                try {
                    Thread.sleep(5000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (JMSException e) {
            LOG.info("Exception occurred: " + e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }

            }
        }
    }
}
