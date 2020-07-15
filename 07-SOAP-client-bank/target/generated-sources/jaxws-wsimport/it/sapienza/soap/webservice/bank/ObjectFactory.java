
package it.sapienza.soap.webservice.bank;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.sapienza.soap.webservice.bank package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DetailsCustomer_QNAME = new QName("http://bank.webservice.soap.sapienza.it/", "detailsCustomer");
    private final static QName _AddCustomerResponse_QNAME = new QName("http://bank.webservice.soap.sapienza.it/", "addCustomerResponse");
    private final static QName _GetCustomers_QNAME = new QName("http://bank.webservice.soap.sapienza.it/", "getCustomers");
    private final static QName _DetailsCustomerResponse_QNAME = new QName("http://bank.webservice.soap.sapienza.it/", "detailsCustomerResponse");
    private final static QName _GetCustomersResponse_QNAME = new QName("http://bank.webservice.soap.sapienza.it/", "getCustomersResponse");
    private final static QName _AddCustomer_QNAME = new QName("http://bank.webservice.soap.sapienza.it/", "addCustomer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.sapienza.soap.webservice.bank
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddCustomer }
     * 
     */
    public AddCustomer createAddCustomer() {
        return new AddCustomer();
    }

    /**
     * Create an instance of {@link DetailsCustomerResponse }
     * 
     */
    public DetailsCustomerResponse createDetailsCustomerResponse() {
        return new DetailsCustomerResponse();
    }

    /**
     * Create an instance of {@link GetCustomersResponse }
     * 
     */
    public GetCustomersResponse createGetCustomersResponse() {
        return new GetCustomersResponse();
    }

    /**
     * Create an instance of {@link GetCustomers }
     * 
     */
    public GetCustomers createGetCustomers() {
        return new GetCustomers();
    }

    /**
     * Create an instance of {@link AddCustomerResponse }
     * 
     */
    public AddCustomerResponse createAddCustomerResponse() {
        return new AddCustomerResponse();
    }

    /**
     * Create an instance of {@link DetailsCustomer }
     * 
     */
    public DetailsCustomer createDetailsCustomer() {
        return new DetailsCustomer();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DetailsCustomer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bank.webservice.soap.sapienza.it/", name = "detailsCustomer")
    public JAXBElement<DetailsCustomer> createDetailsCustomer(DetailsCustomer value) {
        return new JAXBElement<DetailsCustomer>(_DetailsCustomer_QNAME, DetailsCustomer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCustomerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bank.webservice.soap.sapienza.it/", name = "addCustomerResponse")
    public JAXBElement<AddCustomerResponse> createAddCustomerResponse(AddCustomerResponse value) {
        return new JAXBElement<AddCustomerResponse>(_AddCustomerResponse_QNAME, AddCustomerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bank.webservice.soap.sapienza.it/", name = "getCustomers")
    public JAXBElement<GetCustomers> createGetCustomers(GetCustomers value) {
        return new JAXBElement<GetCustomers>(_GetCustomers_QNAME, GetCustomers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DetailsCustomerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bank.webservice.soap.sapienza.it/", name = "detailsCustomerResponse")
    public JAXBElement<DetailsCustomerResponse> createDetailsCustomerResponse(DetailsCustomerResponse value) {
        return new JAXBElement<DetailsCustomerResponse>(_DetailsCustomerResponse_QNAME, DetailsCustomerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bank.webservice.soap.sapienza.it/", name = "getCustomersResponse")
    public JAXBElement<GetCustomersResponse> createGetCustomersResponse(GetCustomersResponse value) {
        return new JAXBElement<GetCustomersResponse>(_GetCustomersResponse_QNAME, GetCustomersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCustomer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bank.webservice.soap.sapienza.it/", name = "addCustomer")
    public JAXBElement<AddCustomer> createAddCustomer(AddCustomer value) {
        return new JAXBElement<AddCustomer>(_AddCustomer_QNAME, AddCustomer.class, null, value);
    }

}
