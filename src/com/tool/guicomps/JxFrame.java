// from Addison Wesley - Java Design Patterns
package com.tool.guicomps;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ImageIcon;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;


//import java.awt.*;
import java.awt.Dimension;

//import de.muntjak.tinylookandfeel.Theme; //needed for theme
import java.awt.event.*;

public class JxFrame extends JFrame implements Constants
{
  public JxFrame(String title) //Jframe
  {
    super(title);
    
    this.setIconImage(IMG_BOOKMARK);
    
    //setCloseClick();
  }

  
  //create a window listener to respond to the window close click
 /* private void setCloseClick()
  {   
    addWindowListener(new WindowAdapter()
    {
       public void windowClosing(WindowEvent e)
       {    System.exit(0);   }
    });
    
  }*/
/*
  public void changeLookAndFeel(String selection)
  {
  	
  	  try
   	  {
		 UIManager.setLookAndFeel(selection); 
		 SwingUtilities.updateComponentTreeUI(JxFrame.this);
	  }
      catch (UnsupportedLookAndFeelException exc)
        {System.err.println("DialogPreferences:UnsupportedLookAndFeel: "+selection+exc);}
      catch (Exception exc)
        {System.err.println("Error "+selection+": "+exc);} 

  	
  }
*/
  //force system look and feel
//  private void setLookAndFeel()
//  {
   /* String laf ="com.jgoodies.plaf.plastic.PlasticLookAndFeel";//"de.muntjak.tinylookandfeel.TinyLookAndFeel";  

    try
    {    	UIManager.setLookAndFeel(laf);        }
    catch (UnsupportedLookAndFeelException exc)
    {System.err.println("JxFrame:UnsupportedLookAndFeel: "+laf);}
    catch (Exception exc)
    {System.err.println("Error "+laf+": "+exc);}*/



/*        Properties p = new Properties(); // properties to be used for connection
   	    String laf = null;
   	  try
   	  {
     	Properties props = new Properties(); //properties to get from file
        //String fileName = "ViewDB.txt"; //get properties from file
        FileInputStream in = new FileInputStream(CONFIG_FILE);
        props.load(in);

        laf = props.getProperty("LookAndFeel");
       // if (laf.equalsIgnoreCase("de.muntjak.tinylookandfeel.TinyLookAndFeel"))
       // {   	Theme.style = Theme.XP_STYLE;     }
        
        if (laf.equals(""))
        {   laf = UIManager.getSystemLookAndFeelClassName() ;   }
 
        if (laf.equals("java"))
        {      }
        else
        {
             UIManager.setLookAndFeel(laf);   // when there is chosen laf
        }
      
        JDialog.setDefaultLookAndFeelDecorated(true);  // setted in start
        JFrame.setDefaultLookAndFeelDecorated(true);

      }
      catch (IOException ex)
      {
          System.err.println("JxFrame.IOException:Cannot find text file: "+CONFIG_FILE);
          //System.err.println(ex);
      }
      catch (UnsupportedLookAndFeelException exc)
        {System.err.println("JxFrame:UnsupportedLookAndFeel: "+laf+exc);}
      catch (Exception exc)
        {System.err.println("Error "+laf+": "+exc);} 

   }*/
}

