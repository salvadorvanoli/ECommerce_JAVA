
package services;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0.2
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "CategoriaNoPuedeTenerProductosException", targetNamespace = "http://services/")
public class CategoriaNoPuedeTenerProductosException_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private CategoriaNoPuedeTenerProductosException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public CategoriaNoPuedeTenerProductosException_Exception(String message, CategoriaNoPuedeTenerProductosException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public CategoriaNoPuedeTenerProductosException_Exception(String message, CategoriaNoPuedeTenerProductosException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: services.CategoriaNoPuedeTenerProductosException
     */
    public CategoriaNoPuedeTenerProductosException getFaultInfo() {
        return faultInfo;
    }

}
