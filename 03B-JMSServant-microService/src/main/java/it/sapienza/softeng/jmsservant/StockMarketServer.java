package it.sapienza.softeng.jmsservant;

import java.net.InetSocketAddress;
import java.net.Socket;

public class StockMarketServer {

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

        NotificatoreAcquisto n = new NotificatoreAcquisto();
        n.start();

        ProduttoreQuotazioni q = new ProduttoreQuotazioni();
        q.start();

    }
}
