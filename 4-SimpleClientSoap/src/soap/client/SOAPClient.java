/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.client;

import it.sapienza.softeng.soapws.Student;
import it.sapienza.softeng.soapws.StudentEntry;
import it.sapienza.softeng.soapws.StudentMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author studente
 */
public class SOAPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("Massimo");
        SOAPClient.helloStudent(s1);

        Student s2 = new Student();
        s2.setName("Monica");
        SOAPClient.helloStudent(s2);

        List<StudentEntry> result = SOAPClient.getStudents().getEntry();
        for (int i = 0; i < result.size(); i++) {
            System.out.println(((StudentEntry) result.get(i)).getStudent().getName());
        }
    }

    private static StudentMap getStudents() {
        it.sapienza.softeng.soapws.WSImplService service = new it.sapienza.softeng.soapws.WSImplService();
        it.sapienza.softeng.soapws.WSInterface port = service.getWSImplPort();
        return port.getStudents();
    }

    private static String hello(java.lang.String arg0) {
        it.sapienza.softeng.soapws.WSImplService service = new it.sapienza.softeng.soapws.WSImplService();
        it.sapienza.softeng.soapws.WSInterface port = service.getWSImplPort();
        return port.hello(arg0);
    }

    private static String helloStudent(it.sapienza.softeng.soapws.Student arg0) {
        it.sapienza.softeng.soapws.WSImplService service = new it.sapienza.softeng.soapws.WSImplService();
        it.sapienza.softeng.soapws.WSInterface port = service.getWSImplPort();
        return port.helloStudent(arg0);
    }

  

}
