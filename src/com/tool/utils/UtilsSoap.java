/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.utils;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author one
 */
public class UtilsSoap {
    
    
    
    
    
    
    
    
    
    //  https://www.concretepage.com/webservices/java-saaj-web-service-example
	private static SOAPMessage createSoapRequest() throws Exception
        {
		 MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL); //  SOAPConstants.DYNAMIC_SOAP_PROTOCOL  //.newInstance();
		 SOAPMessage soapMessage = messageFactory.createMessage();
		 SOAPPart soapPart = soapMessage.getSOAPPart();
    	         SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
                 
			String prefixUri = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-";
	        String uri = prefixUri + "wssecurity-secext-1.0.xsd";                 
                 soapEnvelope.addNamespaceDeclaration("ns1", uri);
                 
                 
                 soapEnvelope.addNamespaceDeclaration("ns2","http://rgwspublic2/RgWsPublic2Service");
                 
    	         soapEnvelope.addNamespaceDeclaration("ns3", "http://rgwspublic2/RgWsPublic2");//"https://www1.gsis.gr/wsaade/RgWsPublic2/RgWsPublic2");
		 //SOAPBody soapBody = soapEnvelope.getBody();
		 //SOAPElement soapElement = soapBody.addChildElement("rgWsPublic2AfmMethodResponse","rgw");//("rgWsPublic2VersionInfo", "rgw");
		 

                 // //   https://gist.github.com/sivaprasadreddy/2555700

	        String uta = prefixUri + "wssecurity-utility-1.0.xsd";
	        String ta = prefixUri + "username-token-profile-1.0#PasswordText";
			//SOAPEnvelope envelope;
			try {
				//envelope = context.getMessage().getSOAPPart().getEnvelope();
				SOAPFactory factory = SOAPFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
				String prefix = "ns1";
				SOAPElement securityElem = factory.createElement("Security",prefix,uri);
				SOAPElement tokenElem = factory.createElement("UsernameToken",prefix,uri);
				//tokenElem.addAttribute(QName.valueOf("wsu:Id"),"UsernameToken-2");
				//tokenElem.addAttribute(QName.valueOf("xmlns:wsu"), uta);
				SOAPElement userElem = factory.createElement("Username",prefix,uri);
				userElem.addTextNode("120315679C");
				SOAPElement pwdElem = factory.createElement("Password",prefix,uri);
				pwdElem.addTextNode("120315679C");
				//pwdElem.addAttribute(QName.valueOf("Type"), ta);
				tokenElem.addChildElement(userElem);
				tokenElem.addChildElement(pwdElem);
				securityElem.addChildElement(tokenElem);
				SOAPHeader soapHeader = soapEnvelope.getHeader();
				 soapHeader.addChildElement(securityElem);
                                //soapBody = soapEnvelope.getBody();
                                SOAPBody soapBody = soapEnvelope.getBody();
                                //soapBody.addChildElement("rgWsPublic2AfmMethod", "ns2");
                                //SOAPElement soapElement = soapBody.addChildElement("rgWsPublic2AfmMethod", "ns2");
                                SOAPElement soapElement = soapBody.addChildElement("rgWsPublic2AfmMethod", "ns2");//factory.createElement("rgWsPublic2AfmMethod","ns2","INPUT_REC");
                                soapBody.addChildElement(soapElement);

                                SOAPElement soapElementI = soapBody.addChildElement("INPUT_REC", "ns2");//factory.createElement("rgWsPublic2AfmMethod","ns2","INPUT_REC");
                                soapElement.addChildElement(soapElementI);
                                
                                SOAPElement soapElemen = soapBody.addChildElement("afm_called_by", "ns3");
                                soapElementI.addChildElement(soapElemen);
                                //soapElemen.addTextNode("120315679");
                                                               
                                SOAPElement afmElem = soapBody.addChildElement("afm_called_for","ns3");//,"ns3", "ns2");//,"ns2",uri);
                                soapElementI.addChildElement(afmElem);
				afmElem.addTextNode("120315679");
                        
			}
            catch (SOAPException e)
            {
                System.out.println(e.getMessage());
				e.printStackTrace();
	    }


                 //SOAPElement element1 = soapElement.addChildElement("arg0");
		 //element1.addTextNode("EveryOne");
		 soapMessage.saveChanges();
		 System.out.println("----------SOAP Request------------");
		 soapMessage.writeTo(System.out);
                  System.out.println();
		 return soapMessage;    
        }
    
    //   https://www.concretepage.com/webservices/java-saaj-web-service-example
    	 private static void createSoapResponse(SOAPMessage soapResponse) throws Exception
         {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.println("\n----------SOAP Response-----------");
		StreamResult result = new StreamResult(System.out);
                 System.out.println();
		transformer.transform(sourceContent, result);
	 }    
    
    public static void main(String[] args) throws Exception
    {
       // URL url = new URL("http://localhost:8080/employeeservice?wsdl");
 
       
	        try{
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			String url = "https://www1.gsis.gr/wsaade/RgWsPublic2/RgWsPublic2?WSDL";
			SOAPMessage soapRequest = createSoapRequest();
			//hit soapRequest to the server to get response
			SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
			createSoapResponse(soapResponse);
			soapConnection.close();
		}catch (Exception e) {
		     e.printStackTrace();
		} 
    }
    
    
}
