/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.soapws;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author biar
 */
@XmlType(name = "Student")
public class StudentImpl implements Student {
    private String name;
    
    public String getName() { return name; }
    public void setName(String n) { name = n; }
    public StudentImpl(String n) { name = n; } 
    public StudentImpl() {}
    
}
