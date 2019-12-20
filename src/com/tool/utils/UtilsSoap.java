/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.utils;

import com.tool.model.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author one
 */
public class UtilsSoap {
    
    
    
 	/*private static SOAPMessage createObjectSoapRequest() throws Exception
        { 
            String urii = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
            SoapRequestNameSpace srns1 =new SoapRequestNameSpace("ns1","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
            SoapRequestNameSpace srns2 =new SoapRequestNameSpace("ns2","http://rgwspublic2/RgWsPublic2Service");
            SoapRequestNameSpace srns3 =new SoapRequestNameSpace("ns3","http://rgwspublic2/RgWsPublic2");
            SoapRequestNameSpace[] srns = {srns1,srns2,srns3};
            
            SoapRequestFactoryElement srfe1 = new SoapRequestFactoryElement("Security","ns1",urii, null,null);
            SoapRequestFactoryElement srfe2 = new SoapRequestFactoryElement("UsernameToken","ns1",urii, null,"Security");
            SoapRequestFactoryElement srfe3 = new SoapRequestFactoryElement("Username","ns1",urii, "120315679C","UsernameToken");
            SoapRequestFactoryElement srfe4 = new SoapRequestFactoryElement("Password","ns1",urii, "120315679C","UsernameToken");
            
            SoapRequestFactoryElement[] srfe = {srfe1,srfe2,srfe3,srfe4};
            
            SoapRequestBodyChildElement srbce1 = new SoapRequestBodyChildElement("rgWsPublic2AfmMethod","ns2",null,null);       
            SoapRequestBodyChildElement srbce2 = new SoapRequestBodyChildElement("INPUT_REC","ns2",null,"rgWsPublic2AfmMethod"); 
            SoapRequestBodyChildElement srbce3 = new SoapRequestBodyChildElement("afm_called_by","ns3",null,"INPUT_REC"); 
            SoapRequestBodyChildElement srbce4 = new SoapRequestBodyChildElement("afm_called_for","ns3","120315679","INPUT_REC"); 
            
            SoapRequestBodyChildElement[] srbce =  {srbce1,srbce2,srbce3,srbce4}; 
                    
            SoapRequest sr = new SoapRequest("sr1",srns,srfe,srbce);
           
	
            MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL); //  SOAPConstants.DYNAMIC_SOAP_PROTOCOL  //.newInstance();
		 SOAPMessage soapMessage = messageFactory.createMessage();
		 SOAPPart soapPart = soapMessage.getSOAPPart();
    	         SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
                 
                
			//String prefixUri = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-";
	        String uri = sr.namespace[1].uri;                 
               
                for(int a = 0 ; a<sr.namespace.length;a++)
                {
                    soapEnvelope.addNamespaceDeclaration(sr.namespace[a].ns, sr.namespace[a].uri);
                }
                
                
                /*soapEnvelope.addNamespaceDeclaration("ns1", uri);
                 soapEnvelope.addNamespaceDeclaration("ns2","http://rgwspublic2/RgWsPublic2Service");               
    	         soapEnvelope.addNamespaceDeclaration("ns3", "http://rgwspublic2/RgWsPublic2"); */    
	/*   try {
				SOAPFactory factory = SOAPFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
				ArrayList listElements = new ArrayList();
                                SOAPElement  elembase = factory.createElement(sr.factoryElement[0].name,sr.factoryElement[0].ns,sr.factoryElement[0].uri);
                                //listElements.add(elembase);
                                for(int b=0;b< sr.factoryElement.length;b++)
                                {
                                    SOAPElement elem1 = factory.createElement(sr.factoryElement[b].name,sr.factoryElement[b].ns,sr.factoryElement[b].uri);
                                    listElements.add(elem1);
                                    //System.out.println("UtilSoap.createObjectSoapRequest "+b+"  listElements:"+listElements.size()+" added:"+sr.factoryElement[b].name);
                                    if(sr.factoryElement[b].textNode!=null)
                                    {
                                      elem1.addTextNode(sr.factoryElement[b].textNode);
                                    }
                                    SOAPElement  elemc = null;
                                    for(int c=0;c<listElements.size();c++)
                                    {
                                        elemc = (SOAPElement)listElements.get(c);
                                        System.out.println("UtilSoap.createObjectSoapRequest  b:"+b+"  c:"+c+"   "+elemc.getElementName().getLocalName()+"  size:"+listElements.size());
                                        if(elemc.getElementName().getLocalName().equalsIgnoreCase(sr.factoryElement[b].isIncludedInElementName))
                                        {
                                            System.out.println("UtilSoap.createObjectSoapRequest  b:"+b+"  c:"+c+" --  this:"+elemc.getElementName().getLocalName()+"="+sr.factoryElement[b].isIncludedInElementName+" includes:"+sr.factoryElement[b].name);
                                            elemc.addChildElement(elem1);
                                                                                
                                           
                                        }

                                    }
                                      System.out.println("UtilSoap.createObjectSoapRequest elembase add elemc b:"+b);
                                      elembase.addChildElement(elemc);                                      

                                }
                                SOAPHeader soapHeader = soapEnvelope.getHeader();
                                soapHeader.addChildElement(elembase);
				
				*/
                                 
                                 
                                 
				/*
                                SOAPElement securityElem = factory.createElement("Security","ns1",uri);
				SOAPElement tokenElem = factory.createElement("UsernameToken","ns1",uri);
                                
				SOAPElement userElem = factory.createElement("Username","ns1",uri);  userElem.addTextNode("120315679C");
				
				SOAPElement pwdElem = factory.createElement("Password","ns1",uri);  pwdElem.addTextNode("120315679C");
				
				
				tokenElem.addChildElement(userElem);
				tokenElem.addChildElement(pwdElem);
				securityElem.addChildElement(tokenElem);
                                
				//SOAPHeader soapHeader = soapEnvelope.getHeader();
				// soapHeader.addChildElement(securityElem);
                                 
                                 
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
		 System.out.println("----------SOAP object Request------------");
		 soapMessage.writeTo(System.out);
                  System.out.println();
		 return soapMessage;                  
        }   */
    
    
    
    
    
    //  https://www.concretepage.com/webservices/java-saaj-web-service-example
	private static SOAPMessage createSoapRequestAfm(String username, String password,String lookingforAfm) throws Exception
        {
            

            
		 MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL); //  SOAPConstants.DYNAMIC_SOAP_PROTOCOL  //.newInstance();
		 SOAPMessage soapMessage = messageFactory.createMessage();
		 SOAPPart soapPart = soapMessage.getSOAPPart();
    	         SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
                 
			//String prefixUri = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-";
	        String uri = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";                 
                 soapEnvelope.addNamespaceDeclaration("ns1", uri);
                 
                 
                 soapEnvelope.addNamespaceDeclaration("ns2","http://rgwspublic2/RgWsPublic2Service");
                 
    	         soapEnvelope.addNamespaceDeclaration("ns3", "http://rgwspublic2/RgWsPublic2");//"https://www1.gsis.gr/wsaade/RgWsPublic2/RgWsPublic2");
		 //SOAPBody soapBody = soapEnvelope.getBody();
		 //SOAPElement soapElement = soapBody.addChildElement("rgWsPublic2AfmMethodResponse","rgw");//("rgWsPublic2VersionInfo", "rgw");
		 

                 // //   https://gist.github.com/sivaprasadreddy/2555700

	        //String uta = prefixUri + "wssecurity-utility-1.0.xsd";
	        //String ta = prefixUri + "username-token-profile-1.0#PasswordText";
			//SOAPEnvelope envelope;
			try {
				//envelope = context.getMessage().getSOAPPart().getEnvelope();
				SOAPFactory factory = SOAPFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
				//String prefix = "ns1";
				SOAPElement securityElem = factory.createElement("Security","ns1",uri);
				SOAPElement tokenElem = factory.createElement("UsernameToken","ns1",uri);
				//tokenElem.addAttribute(QName.valueOf("wsu:Id"),"UsernameToken-2");
				//tokenElem.addAttribute(QName.valueOf("xmlns:wsu"), uta);
				SOAPElement userElem = factory.createElement("Username","ns1",uri);
				userElem.addTextNode(username);
				SOAPElement pwdElem = factory.createElement("Password","ns1",uri);
				pwdElem.addTextNode(password);
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
				afmElem.addTextNode(lookingforAfm);
                        
			}
            catch (SOAPException e)
            {
                System.out.println(e.getMessage());
				e.printStackTrace();
	    }


                 //SOAPElement element1 = soapElement.addChildElement("arg0");
		 //element1.addTextNode("EveryOne");
		 soapMessage.saveChanges();
		 //System.out.println("----------SOAP Request------------");
		 soapMessage.writeTo(System.out);
                  System.out.println();
		 return soapMessage;    
        }
    
    //   https://www.concretepage.com/webservices/java-saaj-web-service-example
    	 private static String createSoapResponse(SOAPMessage soapResponse) throws Exception
         {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
                transformerFactory.setAttribute("indent-number", 5);
		Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                //transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                soapResponse.writeTo(bout);                
		//Source sourceContent = soapResponse.getSOAPPart().getContent();
		//System.out.println("\n----------SOAP Response-----------");
		//StreamResult result = new StreamResult(System.out);
                 //System.out.println();
		//transformer.transform(sourceContent, result);
                
String xml =  new String(bout.toByteArray(), "UTF-8");

    //String xml = bout.toString();

 Source xmlInput = new StreamSource(new StringReader(xml));
 StringWriter stringWriter = new StringWriter();
 StreamResult xmlOutput = new StreamResult(stringWriter);
 
 //System.out.println(stringWriter);

 transformer.transform(xmlInput, xmlOutput);
 String xmlString = xmlOutput.getWriter().toString();

 //System.out.println(xmlString);

             return xmlString;
	 }    
    
    public String getXmlAfmFor(String username, String password,String lookingforAfm)  
    {
        String strReturn = "";
        UtilsGui utilsGui = new UtilsGui();
            if(username == null || username.equalsIgnoreCase("") || password ==null || password.equalsIgnoreCase(""))
            {
                
                utilsGui.showMessageError("Δεν έχει οριστεί όνομα χρήστη ή/και κωδικός για να καλέσει την υπηρεσία. "
                        + "\nΠηγαίνετε στις ιδιότητες της επιχείρησης στην επιλογή κωδικοί.");
            }
            else
            {
	        try{
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			//String url = "https://www1.gsis.gr/wsaade/RgWsPublic2/RgWsPublic2?WSDL";
			SOAPMessage soapRequest = createSoapRequestAfm(username,password,lookingforAfm);
			//hit soapRequest to the server to get response
			SOAPMessage soapResponse = soapConnection.call(soapRequest,"https://www1.gsis.gr/wsaade/RgWsPublic2/RgWsPublic2?WSDL");
			strReturn = createSoapResponse(soapResponse);
			soapConnection.close();
		}catch (Exception e)
                {
		     e.printStackTrace();
		} 
            }
                        
        return strReturn;
    }

      public ArrayList getNameNValuesFromXml(ArrayList lstSoan,String afmXml)
      {
         ArrayList lstSoanReturn = new ArrayList();
        try
        {
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
 
          // Load the input XML document, parse it and return an instance of the
          // Document class.
          InputSource is = new InputSource(new StringReader(afmXml));
          is.setEncoding("UTF-8");
          //Document document = builder.parse(new InputSource(new StringReader(afmXml)));
          org.w3c.dom.Document document =  builder.parse(is);
          //System.out.println("--"+document.getFirstChild().getNodeName());
          //List<Employee> employees = new ArrayList<Employee>();
          //NodeList nodeListOnomasia = document.getElementsByTagName("onomasia");
          //System.out.println("-"+afmXml);
         
   for(int l=0;l<lstSoan.size();l++)       
   {
       EntitySoapResponseNamesNValues soapn = (EntitySoapResponseNamesNValues)lstSoan.get(l);
    NodeList nl = document.getElementsByTagName(soapn.nameNode);
    for (int i = 0; i < nl.getLength(); i++)
    {
      //System.out.println("name is : "+nl.item(i).getNodeName()+" "+nl.item(i).getNodeType()+"   "+nl.item(i).getNodeValue());
      int length = nl.item(i).getChildNodes().getLength();
      for (int p = 0; p < length; p ++) 
      {
        Node c = nl.item(i).getChildNodes().item(p);
        if (c.getNodeType() == Node.TEXT_NODE) 
        {
            soapn.value = c.getNodeValue();
            EntitySoapResponseNamesNValues soapnReturn = new EntitySoapResponseNamesNValues(soapn.nameNode,soapn.caption,soapn.classtype,soapn.value,soapn.nameDb);
            lstSoanReturn.add(soapnReturn);
            //System.out.println("name is :"+soapn.nameNode+"   value:"+soapn.value+"  "+c.getNodeValue()+"  ("+soapn.classtype+")");
        }
      }
     }      
   }
        }
        catch(ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }
        catch(SAXException saxe)
        {
            saxe.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }           
   return lstSoanReturn;
      }      
         
         
    public static void main(String[] args) throws Exception
    {
       // URL url = new URL("http://localhost:8080/employeeservice?wsdl");
 
       
	        try{
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			//String url = "https://www1.gsis.gr/wsaade/RgWsPublic2/RgWsPublic2?WSDL";
			SOAPMessage soapRequest = createSoapRequestAfm("120315679C","120315679C","120315679");
			//hit soapRequest to the server to get response
			SOAPMessage soapResponse = soapConnection.call(soapRequest, "https://www1.gsis.gr/wsaade/RgWsPublic2/RgWsPublic2?WSDL");
			System.out.println(createSoapResponse(soapResponse));
			soapConnection.close();
		}catch (Exception e)
                {
		     e.printStackTrace();
		} 
                
                
                
    
	       /* try{
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			
			SOAPMessage soapRequest = createObjectSoapRequest();
			//hit soapRequest to the server to get response
			SOAPMessage soapResponse = soapConnection.call(soapRequest, "https://www1.gsis.gr/wsaade/RgWsPublic2/RgWsPublic2?WSDL");
			System.out.println(createSoapResponse(soapResponse));
			soapConnection.close();
		}catch (Exception e)
                {
		     e.printStackTrace();
		}*/ 
                      
               ArrayList lstSoan = new ArrayList();
               lstSoan.add(new EntitySoapResponseNamesNValues("onomasia","επωνυμία","java.lang.String",null,"name"));
               lstSoan.add(new EntitySoapResponseNamesNValues("deactivation_flag","ενεργός","java.lang.Boolean",null,"active"));
               lstSoan.add(new EntitySoapResponseNamesNValues("deactivation_flag_descr","ενεργός","java.lang.String",null,"active"));
               lstSoan.add(new EntitySoapResponseNamesNValues("regist_date","έναρξη","java.lang.Date",null,"startdate"));
               lstSoan.add(new EntitySoapResponseNamesNValues("doy","κωδ. ΔΟΥ","java.lang.String",null,"doyId"));
               lstSoan.add(new EntitySoapResponseNamesNValues("doy_descr","επωνυμία ΔΟΥ","java.lang.String",null,"dou"));
               lstSoan.add(new EntitySoapResponseNamesNValues("i_ni_flag_descr","νομική μορφή","java.lang.String",null,"nm"));               
               lstSoan.add(new EntitySoapResponseNamesNValues("normal_vat_system_flag","κατηγορία ΦΠΑ","java.lang.String",null,"vatcat"));
               lstSoan.add(new EntitySoapResponseNamesNValues("postal_area_description","περιοχή","java.lang.String",null,"postal"));
               lstSoan.add(new EntitySoapResponseNamesNValues("postal_zip_code","περιοχή","java.lang.String",null,"pc"));
               lstSoan.add(new EntitySoapResponseNamesNValues("firm_act_descr","δραστηριότητα","java.lang.String",null,"kad"));
               lstSoan.add(new EntitySoapResponseNamesNValues("error_code","κωδ σφάλματος","java.lang.String",null,"error_code"));
               lstSoan.add(new EntitySoapResponseNamesNValues("error_descr","σφάλμα","java.lang.String",null,"error_descr"));
               
               UtilsSoap utilsSoap = new UtilsSoap();
               String afmXml = utilsSoap.getXmlAfmFor("120315679C", "120315679C", "120315679");
               
               utilsSoap.getNameNValuesFromXml(lstSoan, afmXml);

          
          
  
               
               
               
               
    }
    
    
}



