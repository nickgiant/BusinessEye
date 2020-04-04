
// https://www.dreamincode.net/forums/topic/190944-creating-an-updater-in-java/




/*

Ok now before we continue coding we need to create our update site. If you have your own server where you can host the update pages then just skip the part about using bravenet to host our update files but make sure to read about the html files we will be creating to update our app!
The best free site to set up our update site is http://www.bravenet.com/. Go there and sign up for your free account. Once your account is all set up go to the websites tab. In here were going to set up our pages.
Click the Build new Website link. Now click use subdomain. Enter whatever you want for your subdomain. Im going to enter h3r3t1cupdate for my subdomain. Now just click ok. Now click on text/visual editor. In the right panel you should see index.html. Right click on it and select edit file with text editor. You should now be in the text editor. We want to create a new file so click on the new file button and name it version.html. Now refresh the page and you should now see the newly created file in the right panel. Right click and select edit with text editor. Delete all the text in this file and replace with this:
1
	[version]1[/version]

Click save and now were done with this page!
Now lets go back to our UpdateInfo class.
In our UpdateInfo class we need to put our url for the version in the versionURL String. It should look something like this:
1
	private final static String versionURL = "http://<My SubDomain>.bravehost.com/version.html";

replace <My SubDomain> the subdomain you created earlier.

We should now be able to test the getLatestVersion() method!
In our main add this code:
1
	public static void main(String[] args) {
2
	        try {
3
	            System.out.println(UpdateInfo.getLatestVersion());
4
	        } catch (Exception ex) {
5
	            ex.printStackTrace();
6
	        }
7
	    }

The output on the screen should be 1. If it is then everything is working so far but if its not you might of messed something up so go back and make sure you code is correct!
Ok now go back to the text editor on bavehost and create a new file called history.html. Open it up and replace all the text with this:
1
	[history]This is where my history goes![/history]

Note: The history can also use HTML formatting to display update history. Put your HTML formatted history in between the [history] tags. Make sure your formatting starts with <html> or java will not recognize it as HTML formatting!



*/
package com.tool.setup;

import com.tool.guicomps.*;
import com.tool.utils.*;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Thomas Otero H3R3T1C
 */
public class UpdateInfo implements Constants
{
    private final static String versionURL = "http://www.businesseye.gr/upd.txt";//http://h3r3t1cupdate.bravehost.com/version.html";
    private final static String historyURL = "http://www.businesseye.gr/hist.html"; //"https://businesseye737389149.wordpress.com/";////"http://h3r3t1cupdate.bravehost.com/history.html";
   
    private static UtilsGui utilsGui;
    
    public UpdateInfo()
    {
        //utilsGui = new UtilsGui();
    }
    
    public static String getLatestVersion( boolean showMessageWhenNoInternetConnection) throws Exception
    {
        String strRet="";
       try
       {
        String data = getData(versionURL,showMessageWhenNoInternetConnection);
         strRet = data.substring(data.indexOf("[version]")+9,data.indexOf("[/version]"));
       }
       catch(java.lang.StringIndexOutOfBoundsException sie)
       {
            System.out.println("UpdateInfo.getLatestVersion  StringIndexOutOfBoundsException   "+sie.getMessage());
       }
         return strRet;
    }
    
    /*public static String getWhatsNew() throws Exception
    {
        String data = getData(historyURL,false);
        return data;//.substring(data.indexOf("[history]")+9,data.indexOf("[/history]"));
    }*/
    
    public static String getWhatsNewURL() throws Exception
    {
        //String data = getData(historyURL,false);
        return historyURL;//.substring(data.indexOf("[history]")+9,data.indexOf("[/history]"));
    }    
    
    public static long getNewDataSize()throws Exception
    {
        long longRet=-1;
        //URL url = null;
        try
        {
        URL url = new URL(getDownloadLinkFromHost());
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        longRet = conn.getContentLength();        
        }
        catch (MalformedURLException ue)
        {
              System.out.println(" error UpdateInfo.getNewDataSize MalformedURLException:"+ue.getMessage());
              ue.printStackTrace();
        }
        catch(IOException ioe)
        {
              System.out.println(" error UpdateInfo.getNewDataSize IOException:"+ioe.getMessage());
              ioe.printStackTrace();            
        } 
        return longRet;
    }
    
    // exists almost the same in UpdateGui. getDownloadLinkFromHost and UpdateInfo.getDownloadLinkFromHost
    private static String getDownloadLinkFromHost() throws MalformedURLException, IOException
    {
        String strRet="";

        String path = versionURL;
        URL url = new URL(path);

        InputStream html = null;

        html = url.openStream();

        int c = 0;
        StringBuilder buffer = new StringBuilder("");

        while(c != -1)
        {
            c = html.read();
        buffer.append((char)c);
          
        }
       
        return buffer.substring(buffer.indexOf("[url]")+5,buffer.indexOf("[/url]"));
    }    
    
    
    private static String getData(String address, boolean showMessageWhenNoInternetConnection)
    {
        String strRet="";
        utilsGui = new UtilsGui();
       try
       {
        URL url = new URL(address);
        
        InputStream html = null;

        html = url.openStream();
        
        int c = 0;
        StringBuffer buffer = new StringBuffer("");

        while(c != -1)
        {
            c = html.read();
            
        buffer.append((char)c);
        }
        
        strRet=buffer.toString();
      }
      catch(UnknownHostException uhe)
      {
          if(showMessageWhenNoInternetConnection)
          {
              utilsGui.showMessageInfo("No internet connection.");
          }
         
            System.out.println("UpdateInfo.getData  No internet connection. "+uhe.getMessage());
      }
       catch(MalformedURLException mue)
       {
           System.out.println("UpdateInfo.getData  "+mue.getMessage());
       }
       catch (IOException ex)
       {
             System.out.println("UpdateInfo.getData  "+ex.getMessage());
       }
        return strRet;
    }
}
