// created 24-09-2006
// www.javaworld.com   http://www.javaworld.com/javaworld/javatips/jw-javatip66.html


package com.tool.utils;

import com.tool.guicomps.*;

import javax.swing.*;
import java.io.IOException;
import java.io.File;
import java.util.*;
import java.util.zip.*;
import java.io.*;
//private static String systemDirectorySymbol=System.getProperty("file.separator");

public class UtilsOS implements Constants
{

    public UtilsOS()
    {
    
    }

    public static String getOS()
    {
        String os = System.getProperty("os.name");
        return os;
    }
    
    public static boolean isOSWindows()
    {
    	boolean os=false;
        if ( getOS().startsWith("Windows"))
    	{      os = true; }
    
    	return os;
    }

    public static boolean isOSLinux()
    {
    	boolean os=false;
        if ( getOS().startsWith("Linux"))
    	{      os = true; }
    
    	return os;
    }

   /* public static void fileToURL(String file)// throws Exception{ 
    {
    	File file = new File("C:\\Documents and Settings\\administrator\\Desktop\\xsl\\test.xsl"); 
        System.out.println("file.toURL() ----> "+file.toURL()); 
        System.out.println("file.toURI().toURL() ----> "+file.toURI().toURL()); 
    } */

    public static void displayPathOrFileFromPath(String path)
    {
       displayPathOrFileFromURL("file://"+path);
    }

    public static void displayPathOrFileFromURL(String url)
    {

       /* try
        {	
			String cmd = "cmd.exe /c start ";
			Runtime.getRuntime().exec(cmd + dir);
        }
        catch(IOException ioe)
        { JOptionPane.showMessageDialog(null, ioe.getMessage());    }
    	
    	System.out.println("dialogSetupDb.openLibPath path: "+dir);
        */

                
        String cmd = null;
        try
        {
            if (isOSWindows())
            {
                cmd = "rundll32" + " " + "url.dll,FileProtocolHandler" + " " + url;
                Process proc = Runtime.getRuntime().exec(cmd);
            }
            else
            {
                // Under Unix, Netscape has to be running for the "-remote"
                // command to work.  So, we try sending the command and
                // check for an exit value.  If the exit command is 0,
                // it worked, otherwise we need to start the browser.
                // cmd = 'netscape -remote openURL(http://www.javaworld.com)'
                cmd = "netscape" + " " + "-remote openURL" + "(" + url + ")";
                Process proc = Runtime.getRuntime().exec(cmd);
                try
                {
                    // wait for exit code -- if it's 0, command worked,
                    // otherwise we need to start the browser up.
                    int exitCode = proc.waitFor();
                    if (exitCode != 0)
                    {
                        // Command failed, start up the browser
                        // cmd = 'netscape http://www.javaworld.com'
                        cmd = "netscape" + " "  + url;
                        proc = Runtime.getRuntime().exec(cmd);
                    }
                }
                catch(InterruptedException ix)
                {
                    System.err.println("UtilsOs error bringing up browser, cmd='" +cmd + "'");
                    System.err.println("UtilsOs error " + ix);
                }
            }
        }
        catch(IOException iox)
        {
            // couldn't exec browser
            System.err.println("UtilsOs Could not invoke browser, command=" + cmd);
            System.err.println("UtilsOs error: " + iox);
        }
    }

    public BufferedReader getFileFromZip(String textFile,String zipFile)
    {
    	
    	BufferedReader ret = null;
    	// http://www.java2s.com/Code/Java/File-Input-Output/ReadingtheContentsofaZIPFile.htm
    //txtareaRestoreComments.setText("");
    try {
      ZipFile zf = new ZipFile(zipFile);
      Enumeration entries = zf.entries();

      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      while (entries.hasMoreElements())
      {
        ZipEntry ze = (ZipEntry) entries.nextElement();
        //System.out.println("Read " + ze.getName() + "?");
        //String inputLine = input.readLine();
        if (ze.getName().equalsIgnoreCase(textFile))
        {                //inputLine.equalsIgnoreCase("yes")) {
          long size = ze.getSize();
          if (size > 0) {
            //System.out.println("Length is " + size);
             ret = new BufferedReader( new InputStreamReader(zf.getInputStream(ze),"utf-8"));

          }
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    	
    	return ret;
   }


    public String readFileFromZip(String textFile,String zipFile)
    {
    	String ret="";
    	// http://www.java2s.com/Code/Java/File-Input-Output/ReadingtheContentsofaZIPFile.htm
    //txtareaRestoreComments.setText("");
    try {
      ZipFile zf = new ZipFile(zipFile);
      Enumeration entries = zf.entries();

      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      while (entries.hasMoreElements())
      {
        ZipEntry ze = (ZipEntry) entries.nextElement();
        //System.out.println("Read " + ze.getName() + "?");
        //String inputLine = input.readLine();
        if (ze.getName().equalsIgnoreCase(textFile))
        {                //inputLine.equalsIgnoreCase("yes")) {
          long size = ze.getSize();
          if (size > 0) {
            //System.out.println("Length is " + size);
            BufferedReader br = new BufferedReader( new InputStreamReader(zf.getInputStream(ze),"utf-8"));
            String line;
            
            while ((line = br.readLine()) != null)
            {
            	ret=ret+line+TXT_CHANGELINE;
              //txtareaRestoreComments.append(line);
              //System.out.println(line);
            }
            br.close();
          }
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    	
    	return ret;
   }

   //exists in DialogBackup and in PanelReportFormDesign
   public void deleteFile(String file) 
   {
   	 File f = new File(file);
   	    boolean success = f.delete();
       if (!success) 
       {
       	  //txtareaLog.append("\n error: file '"+file+"' not deleted.");
          System.out.println(" error: file '"+file+"' not deleted.");
       }
       else
       {
       	   //txtareaLog.append("\nfile '"+file+"' deleted.");
    	   //System.out.println("   file '"+file+"' deleted.");
       }             
   } 

 public boolean zipWrite(String[] sourceFilesToZip, String[] sourceFilesToZipInside, String zipDestinationFile)
 {
   boolean ret=false;

     byte[] bufferC = new byte[18024];

     // Specify zip file name
     String zipFileName = zipDestinationFile+TEMPLATE_REPORTFORMFILETYPE;

     try {

       ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));

       // Set the compression ratio
       out.setLevel(Deflater.BEST_COMPRESSION);//.DEFAULT_COMPRESSION);

       // iterate through the array of files, adding each to the zip file
       for (int i = 0; i < sourceFilesToZip.length; i++) 
       {
         
         // Associate a file input stream for the current file
         FileInputStream in = new FileInputStream(sourceFilesToZip[i]);  ///
         
         // Add ZIP entry to output stream.
         out.putNextEntry(new ZipEntry(sourceFilesToZipInside[i]));

         // Transfer bytes from the current file to the ZIP file
         //out.write(buffer, 0, in.read(buffer));
       	 
       	 //txtareaLog.append("\n  zipping file "+(i+1)+" "+filesToZip[i]);
         //System.out.println("zipping file "+(i)+" "+filesToZip[i]);

         int len;
        while ((len = in.read(bufferC)) > 0)
        {
             out.write(bufferC, 0, len);
        }
         // Close the current entry
         out.closeEntry();

         // Close the current file input stream
         in.close();

       }// for
       // Close the ZipOutPutStream
       out.close();
       
       System.out.println("UtilsOS.zipWrite to "+zipFileName);
       ret=true;
     }
     catch (IllegalArgumentException iae)
     {
     	ret=false;
     	JOptionPane.showMessageDialog(null,iae.getMessage());
       iae.printStackTrace();
     }
     catch (FileNotFoundException fnfe)
     {
     	ret=false;
     	JOptionPane.showMessageDialog(null,fnfe.getMessage());
       fnfe.printStackTrace();
     }
     catch (IOException ioe)
     {
     	ret=false;
     	JOptionPane.showMessageDialog(null,ioe.getMessage());
       ioe.printStackTrace();
     }

     return ret;
   
}  


 public ArrayList unzipFile(String strZipFile, String destinationDir)
 {
 	ArrayList ret=new ArrayList();

 	
 	 int bufferE = 2048;
 	 try
     {
       //System.out.println("Example of ZIP file decompression.");

       // Specify file to decompress
       //String inFileName = "c:\example.zip";
       // Specify destination where file will be unzipped
       //String destinationDirectory = "c:\temp\";
       
       //System.out.println("unzip "+strZipFile);
       
       File sourceZipFile = new File(strZipFile);
       File unzipDestinationDirectory = new File(destinationDir);

       // Open Zip file for reading
       ZipFile zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);
       
       //System.out.println("Extracting: " + zipFile.entries());
       // Create an enumeration of the entries in the zip file
       Enumeration zipFileEntries = zipFile.entries();

       // Process each entry
       while (zipFileEntries.hasMoreElements())
       {
         // grab a zip file entry
         ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
         //System.out.println("Extracting: " + entry);
         String currentEntry = entry.getName();
         ret.add(currentEntry);
         //txtareaLog.append("Extracting: " + entry);
         

         File destFile = new File(unzipDestinationDirectory, currentEntry);

         // grab file's parent directory structure
         File destinationParent = destFile.getParentFile();

         // create the parent directory structure if needed
         destinationParent.mkdirs();

         // extract file if not a directory
         if (!entry.isDirectory())
         {
           BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
           int currentByte;
           // establish buffer for writing file
           byte data[] = new byte[bufferE];

           // write the current file to disk
           FileOutputStream fos = new FileOutputStream(destFile);
           BufferedOutputStream dest =
           new BufferedOutputStream(fos, bufferE);

           // read and write until last byte is encountered
           while ((currentByte = is.read(data, 0, bufferE)) != -1)
           {
             dest.write(data, 0, currentByte);
           }
           dest.flush();
           dest.close();
           is.close();
         }
       }
       zipFile.close();
       
       //ret=true;
     }
     catch (IOException ioe)
     {
         System.out.println("error UtilsOS.unzipFile "+ioe.getMessage());//ioe.printStackTrace();
     }
   
 	return ret; 	
 }


    public static void main(String[] args)
    {
        displayPathOrFileFromPath("c:\\mydocs");
    }


}