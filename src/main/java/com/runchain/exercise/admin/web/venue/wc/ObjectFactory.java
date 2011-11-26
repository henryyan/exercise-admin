
package com.runchain.exercise.admin.web.venue.wc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.runchain.exercise.admin.web.venue.wc package. 
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

    private final static QName _CheckVenueNameExist_QNAME = new QName("http://service.venue.yanhl.net/", "checkVenueNameExist");
    private final static QName _CheckVenueNameExistResponse_QNAME = new QName("http://service.venue.yanhl.net/", "checkVenueNameExistResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.runchain.exercise.admin.web.venue.wc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckVenueNameExistResponse }
     * 
     */
    public CheckVenueNameExistResponse createCheckVenueNameExistResponse() {
        return new CheckVenueNameExistResponse();
    }

    /**
     * Create an instance of {@link CheckVenueNameExist }
     * 
     */
    public CheckVenueNameExist createCheckVenueNameExist() {
        return new CheckVenueNameExist();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckVenueNameExist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.venue.yanhl.net/", name = "checkVenueNameExist")
    public JAXBElement<CheckVenueNameExist> createCheckVenueNameExist(CheckVenueNameExist value) {
        return new JAXBElement<CheckVenueNameExist>(_CheckVenueNameExist_QNAME, CheckVenueNameExist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckVenueNameExistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.venue.yanhl.net/", name = "checkVenueNameExistResponse")
    public JAXBElement<CheckVenueNameExistResponse> createCheckVenueNameExistResponse(CheckVenueNameExistResponse value) {
        return new JAXBElement<CheckVenueNameExistResponse>(_CheckVenueNameExistResponse_QNAME, CheckVenueNameExistResponse.class, null, value);
    }

}
