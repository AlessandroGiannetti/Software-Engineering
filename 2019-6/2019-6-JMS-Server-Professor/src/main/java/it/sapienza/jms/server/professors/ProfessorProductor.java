/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.jms.server.professors;

/**
 *
 * @author Alegi
 */
import java.util.Properties;
import java.util.Random;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author biar
 */
public class ProfessorProductor {

    Properties properties = null;
    Context jndiContext = null;
    private ConnectionFactory connectionFactory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

    private Random randomGen = new Random();

    private String getId() {
        return Integer.toString(randomGen.nextInt(6 + 1));  // only 6 professors in the map
    }

    private float getRank() {
        return randomGen.nextFloat() * 100;
    }

    public void start() throws NamingException, JMSException {
        try {
            properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(properties);
        } catch (NamingException e) {
            System.out.println(e.toString());
            System.exit(1);
        }
        
        connectionFactory = (ConnectionFactory)jndiContext.lookup("ConnectionFactory");
        destination = (Destination)jndiContext.lookup("dynamicTopics/professors");

        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(destination);

        String id;
        Float rank;
        String text;
        TextMessage message = session.createTextMessage();
        while (true) {
            id = getId();
            rank = getRank();
            
            message.setStringProperty("id", id);
            message.setFloatProperty("rank", rank);
            text = "Professor " + id + ": " + Float.toString(rank);
            message.setText(text);

            this.producer.send(message);
            System.out.println(text);

            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}