package it.sapienza.softeng.jmsservant;

public class StockMarketServer {

    public static void main(String args[]) throws Exception {

        NotificatoreAcquisto n = new NotificatoreAcquisto();
        n.start();

        ProduttoreQuotazioni q = new ProduttoreQuotazioni();
        q.start();

    }
}
