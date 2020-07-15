/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.client.bank;

import java.util.Scanner;

/**
 *
 * @author Alegi
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int x = -1;

        while (x != 0) {

            System.out.println("\n-- Choose an operation:");
            System.out.println("---- 1. Add client:");
            System.out.println("---- 2. Client details:");
            System.out.println("---- 3. Show clients:");
            System.out.println("---- 0. Quit:");
            System.out.print("\n---> Insert the code: \n");

            Scanner input = new Scanner(System.in);
            x = input.nextInt();

            switch (x) {
                case 1:   // addCustomer
                
                try { // Call Web Service Operation
                    it.sapienza.soap.webservice.bank.BankImplService service = new it.sapienza.soap.webservice.bank.BankImplService();
                    it.sapienza.soap.webservice.bank.Bank port = service.getBankImplPort();
                    // TODO initialize WS operation arguments here
                    it.sapienza.soap.webservice.bank.Customer arg0 = new it.sapienza.soap.webservice.bank.Customer();
                    arg0.setId(3);
                    arg0.setName("Alessandro");
                    arg0.setSurname("Giannetti");
                    arg0.setBill(0.00);
                    // TODO process result here
                    java.lang.String result = port.addCustomer(arg0);
                    System.out.println("================================");
                    System.out.println("Result: \n" + result);
                    System.out.println("================================");
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                break;
                case 2:   // detailsCustomers()
                try { // Call Web Service Operation
                    it.sapienza.soap.webservice.bank.BankImplService service = new it.sapienza.soap.webservice.bank.BankImplService();
                    it.sapienza.soap.webservice.bank.Bank port = service.getBankImplPort();
                    // TODO initialize WS operation arguments here
                    System.out.print("\n---> Insert Client ID: \n");

                    input = new Scanner(System.in);
                    x = input.nextInt();
                    // TODO process result here
                    java.lang.String result = port.detailsCustomer(x);

                    System.out.println("================================");
                    System.out.println("Result:\n  " + result);
                    System.out.println("================================");
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                break;
                case 3:   // getCustomers()
                try { // Call Web Service Operation
                    it.sapienza.soap.webservice.bank.BankImplService service = new it.sapienza.soap.webservice.bank.BankImplService();
                    it.sapienza.soap.webservice.bank.Bank port = service.getBankImplPort();
                    // TODO process result here
                    java.util.List<it.sapienza.soap.webservice.bank.Customer> result = port.getCustomers();
                    //System.out.println("Result = " + result);
                    System.out.println("================================");
                    for (it.sapienza.soap.webservice.bank.Customer c : result) {
                        System.out.println("ID: " + c.getId() + " Name: " + c.getName() + " Surname: " + c.getSurname());
                    }
                    System.out.println("================================");
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                default:
            }

            System.out.flush();
        }
        System.out.println("\nSEE YA!");
    }
}
