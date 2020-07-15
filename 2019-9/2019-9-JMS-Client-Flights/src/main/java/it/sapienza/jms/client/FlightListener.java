/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.jms.client;

import java.sql.*;
import java.sql.Connection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Alegi
 */
public class FlightListener implements MessageListener {
    
    //database path
    private final static String DBPATH = "../database/flightsDB/database";
    
    private final static Pattern STATUSPATTERN = Pattern.compile(".* : (.*)");
    
    private Connection connection;
    private TopicConnection jmsConnection;
    


    public FlightListener() {
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            Context ctx = new InitialContext(props);

            ConnectionFactory connectionFactory = (ConnectionFactory)ctx.lookup("ConnectionFactory");
            jmsConnection = (TopicConnection)connectionFactory.createConnection();
            TopicSession session = (TopicSession) jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = (Destination)ctx.lookup("dynamicTopics/Flights");
            TopicSubscriber subscriber = session.createSubscriber((Topic)destination);
            subscriber.setMessageListener(this);

            Class.forName("org.sqlite.JDBC");
        } catch (JMSException | NamingException | ClassNotFoundException err) {
            err.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public void onMessage(Message msg) {
        try {
            String flight = msg.getStringProperty("flight");
            String text = ((TextMessage)msg).getText();

            Matcher matcher = STATUSPATTERN.matcher(text);
            if (matcher.find()) {
                String status = matcher.group(1);

                PreparedStatement statement = connection.prepareStatement("INSERT INTO flights VALUES(?, ?)");
                statement.setQueryTimeout(30);

                statement.setString(1, flight);
                statement.setString(2, status);
                statement.executeUpdate();

                System.out.println(String.format("%s -> %s", flight, status));
            }
        } catch (JMSException | SQLException err) {
            err.printStackTrace();
        }
    }


    public void start() {
        try {
            jmsConnection.start();
            connection = DriverManager.getConnection("jdbc:sqlite:" +DBPATH);
        } catch (JMSException | SQLException err) {
            err.printStackTrace();
        }
    }


    public void stop() {
        try {
            jmsConnection.stop();
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException | SQLException err) {
            err.printStackTrace();
        }
    }
}