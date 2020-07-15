/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.soapws;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author biar
 */
public class StudentAdapter extends XmlAdapter<StudentImpl,Student>{
    public StudentImpl marshal(Student stud) throws Exception {
        if (stud instanceof StudentImpl)
            return (StudentImpl) stud;
        return new StudentImpl(stud.getName());
    }

    @Override
    public Student unmarshal(StudentImpl v) throws Exception {
        return v;
    }
    
}
