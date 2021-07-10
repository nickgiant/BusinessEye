/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import org.apache.http.Header;
//import javax.swing.text.Document;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.UtilHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



/**
 *
 * @author one
 */
public class UtilHttpClient
{
    
public static void main(String[] args) 
{
    UtilHttpClient client = new UtilHttpClient();
    //client.sendInvoices();
    client.requestIssuerInvoices();
}


private void sendInvoices()
{
// from    https://mydata-dev.portal.azure-api.net/docs/services/myDATA-dev/operations/post-sendinvoices?

    CloseableHttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://mydata-dev.portal.azure-api.net/SendInvoices");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("aade-user-id", "nickg");
            request.setHeader("Ocp-Apim-Subscription-Key", "ada7a446cd4846e494a3036905524600");

        File xmlFile = new File("./utils/sendInvoicesRQ.xml");
        //File xmlFile = new File("../invoiceDoc.xml");
        // Let's get XML file as String using BufferedReader
        // FileReader uses platform's default character encoding
        // if you need to specify a different encoding, use InputStreamReader
        Reader fileReader = new FileReader(xmlFile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        
        StringBuilder sb = new StringBuilder();
        String line = bufReader.readLine();
        while( line != null){
            sb.append(line).append("\n");
            line = bufReader.readLine();
        }
        String xml2String = sb.toString();
        bufReader.close();
//Read more: https://javarevisited.blogspot.com/2015/07/how-to-read-xml-file-as-string-in-java-example.html#ixzz69PrXkydu
        


            // Request body
            StringEntity reqEntity = new StringEntity(xml2String);
            request.setEntity(reqEntity);

            CloseableHttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            
     
            
            
            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
            else
            {
                 System.out.println("EntityUtils.toString(entity)  is null");
            }
            
            //System.out.println("getStatusCode:  "+response.getStatusLine().getStatusCode());
           //  http://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/fundamentals.html#d5e95 
//System.out.println(response.getProtocolVersion());
//System.out.println(response.getStatusLine().getStatusCode());
//System.out.println(response.getStatusLine().getReasonPhrase());
System.out.println(response.getStatusLine().toString()); 
System.out.println("");
    

               
     httpclient.close();       
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("  error  "+e.getMessage());
        }
   
    }

private void requestIssuerInvoices()
{
       CloseableHttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://mydata-dev.portal.azure-api.net/RequestIssuerInvoices");

           builder.setParameter("mark", "1000016061300");
           // builder.setParameter("nextPartitionKey", "{string}");
           // builder.setParameter("nextRowKey", "{string}");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("aade-user-id", "nickg");
            request.setHeader("Ocp-Apim-Subscription-Key", "ada7a446cd4846e494a3036905524600");

  
            CloseableHttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
//http://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/fundamentals.html#d5e95  
//System.out.println(response.getProtocolVersion());
//System.out.println(response.getStatusLine().getStatusCode());
//System.out.println(response.getStatusLine().getReasonPhrase());
System.out.println(response.getStatusLine().toString());
 

            if (entity != null) 
            {
               // System.out.println(EntityUtils.toString(entity));
                //System.out.println(new StringReader(EntityUtils.toString(entity)));
                System.out.println("responce:");

                String content = EntityUtils.toString(entity);
                System.out.println(content);
   
            }
            else
            {
                System.out.println(" error null "+EntityUtils.toString(entity));
            }

               httpclient.close(); 
        }
        catch (Exception e)
        {
            System.out.println(" Error "+e.getMessage());
        } 
    /*finally
    {
        //Important: Close the connect
        httpclient.getConnectionManager().shutdown();
    }   */     
    
}



}


