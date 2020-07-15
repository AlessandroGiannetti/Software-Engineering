/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.professors;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alegi
 */
@XmlRootElement
public class Professor {
    private String name;
    private String surname;
    
    private static Map<String, Professor> professors = new HashMap<String, Professor>();
    private static Integer maxId = -1;
    
    public Professor() { }

    public Professor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public static Professor getProfessor(String id) {
        return professors.get(id);
    }
    
    public static void insertProfessor(Professor professor) {
        maxId += 1;
        professors.put(Integer.toString(maxId), professor);
    }
}