/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.soapws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author biar
 */
@XmlType(name = "StudentMap")
public class StudentMap {

    private List<StudentEntry> entries = new ArrayList<StudentEntry>();

    @XmlElement(nillable = false, name = "entry")
    public List<StudentEntry> getEntries() {
        return entries;
    }

    @XmlType(name = "StudentEntry")
    public static class StudentEntry {

        private Integer id;
        private Student student;

        public Integer getId() { return id; }
        public Student getStudent () { return student; }
     
        public void setId(Integer i) { id = i; }
        public void setStudent(Student s) { student = s; }
        
    }
}
