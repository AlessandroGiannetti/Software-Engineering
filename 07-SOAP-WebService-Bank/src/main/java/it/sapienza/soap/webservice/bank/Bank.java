/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.bank;

import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author Alegi
 */
@WebService
public interface Bank {
    public String detailsCustomer(int id);

    public String addCustomer(Customer customer);
    
    public List<Customer> getCustomers();
    
}
