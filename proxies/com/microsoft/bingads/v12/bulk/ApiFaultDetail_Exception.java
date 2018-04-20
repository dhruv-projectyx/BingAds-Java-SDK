
package com.microsoft.bingads.v12.bulk;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "ApiFaultDetail", targetNamespace = "https://bingads.microsoft.com/CampaignManagement/v12")
public class ApiFaultDetail_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ApiFaultDetail faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ApiFaultDetail_Exception(String message, ApiFaultDetail faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ApiFaultDetail_Exception(String message, ApiFaultDetail faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.microsoft.bingads.v12.bulk.ApiFaultDetail
     */
    public ApiFaultDetail getFaultInfo() {
        return faultInfo;
    }

}
