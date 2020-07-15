/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.client.professor;

/**
 *
 * @author Alegi
 */
import java.util.Properties;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ProfessorListener implements MessageListener {

    private TopicConnection connection;

    public ProfessorListener() {
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            Context ctx = new InitialContext(props);

            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
            connection = (TopicConnection) connectionFactory.createConnection();
            TopicSession session = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = (Destination) ctx.lookup("dynamicTopics/professors");
            TopicSubscriber subscriber = session.createSubscriber((Topic) destination);
            subscriber.setMessageListener(this);
        } catch (JMSException err) {
            err.printStackTrace();
        } catch (NamingException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message msg) {
        try {
            String id = msg.getStringProperty("id");
            float rank = msg.getFloatProperty("rank");
            int idInt = Integer.parseInt(id);

            if (idInt == 1 || idInt == 3 || idInt == 4 || idInt == 6 || idInt == 7 || idInt == 8) {
                it.sapienza.soap.webservice.professors.ExamImplService service = new it.sapienza.soap.webservice.professors.ExamImplService();
                it.sapienza.soap.webservice.professors.Exam port = service.getExamImplPort();
                it.sapienza.soap.webservice.professors.Professor result = port.getDetails(id);

                System.out.println("Professor " + id + " is " + result.getName() + " " + result.getSurname() + " and has rank " + rank);
            }
        } catch (JMSException err) {
            err.printStackTrace();
        }
    }

    public void start() {
        try {
            connection.start();
        } catch (JMSException err) {
            err.printStackTrace();
        }
    }

    public void stop() {
        try {
            connection.stop();
        } catch (JMSException err) {
            err.printStackTrace();
        }
    }
}
