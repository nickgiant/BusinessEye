package com.tool.utils;
 
import com.tool.model.EntityMessage;
  import com.tool.guicomps.*;
  import com.tool.gui.*;
import static com.tool.guicomps.Constants.FILE_CONFIG;

  import java.util.*;
 
  import java.awt.*;
  import javax.swing.*;

  import javax.swing.JOptionPane; 
  import javax.swing.JPanel; 
  import javax.swing.JScrollPane; 
  import javax.swing.JTextArea; 
  import javax.swing.JDialog;
  import javax.swing.JFrame;
  import javax.swing.UIManager;
  import javax.swing.UnsupportedLookAndFeelException;
  import javax.swing.SwingUtilities;

  import java.io.File;
  import java.io.IOException;
  import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.Properties;
  
public class UtilsGui implements Constants
{
   private int messageLength = 1;// afte which MessagePanelError takes action
   JTextArea messageTextArea = new JTextArea(); 
    private String systemDirectorySymbol;
    public final int YES = 0;
    public final int NO = 1;
    public final int CANCEL = 3;
    public UtilsGui()
    {
    
    }
    
    public void showMessageInfo(String message)
    {
       String title="Message";
       if (message.length()>messageLength)
       {
          
          JOptionPane.showMessageDialog(null, new MessagePanelError(message,0), title, JOptionPane.INFORMATION_MESSAGE);
       }
       else
       {
       	  JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
       }
    /* public static void showMessageDialog(Component parentComponent,
                                     Object message,
                                     String title,
                                     int messageType)*/
                                              
    }

   
    /*
     * 
     * called by panelDataFilter.checkIfFilesAreCompleted, PanelOneDataOneRec.rowSave, 
     */
    public int showMessageFromList(ArrayList listMessage)
    {
    	boolean continuePositive=true;
    	int ret = -1;
       String title="Message";
       String message ="";
        String outputObligatoryFields="Το πεδίο '";
        String outputObligatoryFieldsEnd=" είναι υποχρεωτικό να συμπληρωθεί.";
        String outputSuggestFields="Το πεδίο '";
        String outputSuggestFieldsEnd=" προτείνεται να συμπληρωθεί.";
        
        String outputTableOneRowObligatory="Στον πίνακα '";
        String outputTableOneRowObligatoryEnd=" είναι υποχρεωτικό να συμπληρωθεί τουλάχιστον μία σειρά.";
        String outputTableOneRowSuggest="Στον πίνακα '";
        String outputTableOneRowSuggestEnd=" προτείνεται να συμπληρωθεί μία σειρά.";
        
       for(int m=0;m<listMessage.size();m++)
       {
       	EntityMessage em = (EntityMessage)listMessage.get(m);
       	 if(em.getType()==FIELD_OBLIGATORY)
       	 {
       	 	if(em.getTableRow()==0)
       	 	{
       	 			message =message+ outputObligatoryFields+em.getMessage()+"'"+outputObligatoryFieldsEnd+"\n";
       	 	}
       	 	else
       	 	{
       	 			message =message+ outputObligatoryFields+em.getMessage()+"' στη γραμμή "+em.getTableRow()+" του πίνακα '"+em.getStrJtable()/*em.getColumn()*/+"' "+outputObligatoryFieldsEnd+"\n";
       	 	}
       	 
       	 	continuePositive=false;
       	 }
       	 else if(em.getType()==FIELD_SUGGEST)
       	 {
       	 	if(em.getTableRow()==0)
       	 	{
       	 			message =message+ outputSuggestFields+em.getMessage()+"'"+outputSuggestFieldsEnd+"\n";
       	 	}
       	 	else
       	 	{
       	 			message =message+ outputSuggestFields+em.getMessage()+"' στη γραμμή "+em.getTableRow()+" του πίνακα '"+em.getStrJtable()/*em.getColumn()*/+"' "+outputSuggestFieldsEnd+"\n";
       	 	}       	 	
       	 	
       	 }
         else if(em.getType()==FIELD_TABLE_ONEROWATLEAST_OBLIGATORY)
         {
             message =message+ outputTableOneRowObligatory+em.getMessage()+"'"+outputTableOneRowObligatoryEnd+"\n";
             continuePositive=false;
         }
         else if(em.getType()==FIELD_TABLE_ONEROWATLEAST_SUGGEST)
         {
             message =message+ outputTableOneRowSuggest+em.getMessage()+"'"+outputTableOneRowSuggestEnd+"\n";
         }
         else
         {
             System.out.println("UtilsGui.showMessageFromList UNKNOWN message type:"+em.getType());
         }
       	  
       }
       
       //message="<html>"+message+"</html>";
       
       if(listMessage.size()==0)
       {
       	  continuePositive=true;
       	  ret= 0;//1 no, 0 yes 
       }
       else
       {
           if(continuePositive)
           {

       Object[] options = {"<html>συνέχεια <b>enter</b></html>", "<html>άκυρο <b>esc</b></html>"};
       //int optionPane=1; //1 no, 0 yes    


             ret = JOptionPane.showOptionDialog(null, new MessagePanelError(message,listMessage.size()),title,
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                       options, options[0]); // 1st parameter null for center of screen but no modal

              //System.out.println("UtilsGui.showMessageFromList "+optionPane);
             //ret= optionPane;
           	
           }
           else
           {
           	  JOptionPane.showMessageDialog(null, new MessagePanelError(message,listMessage.size()), title, JOptionPane.INFORMATION_MESSAGE);
           	  ret= 1;
           }

           
             

       }
    /* public static void showMessageDialog(Component parentComponent,
                                     Object message,
                                     String title,
                                     int messageType)*/
         return ret;                                     
    }


    public void showMessageError(Component compo,String message)
    {
       String title="Προσοχή !";
       if (message.length()>messageLength)
       {
          
          JOptionPane.showMessageDialog(compo, new MessagePanelError(message,0), title, JOptionPane.ERROR_MESSAGE);
       }
       else
       {
       	  JOptionPane.showMessageDialog(compo, message, title, JOptionPane.ERROR_MESSAGE);
       }
    /* public static void showMessageDialog(Component parentComponent,
                                     Object message,
                                     String title,
                                     int messageType)*/
                    
    }
    
    public void showMessageError(String message)
    {
       String title="Προσοχή !";
       if (message.length()>messageLength)
       {
          
          JOptionPane.showMessageDialog(null, new MessagePanelError(message,0), title, JOptionPane.ERROR_MESSAGE);
       }
       else
       {
       	  JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
       }
  
                        
    }

    
    public int showConfirmYesOrNo(Component comp, String message)
    {
    	if(comp==null)
    	{
    		System.out.println(" error UtilsGui.showConfirmYesOrNo comp = null");
    		//(JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this);
    	}
    	
       String title="Προσοχή !";
       Object[] options = {"<html>Yes <b>enter</b></html>", "<html>No <b>esc</b></html>"};
       int optionPane=1;     

       
       if (message.length()>messageLength)
       {
             optionPane = JOptionPane.showOptionDialog(comp, new MessagePanelError(message,0),title,
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                       options, options[0]); // 1st parameter null for center of screen but no modal

       }
       else
       {
              optionPane = JOptionPane.showOptionDialog(comp, message,title,
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                       options, options[0]); // 1st parameter null for center of screen but no modal
       }
    // showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) 
         
         /* final int YES = 0;
    	 final int NO = 1;*/
    	 
    	 
         return optionPane;
     }

    public int showConfirmYesOrNoOrCancel(Component comp, String message)
    {
    	if(comp==null)
    	{
    		System.out.println(" error UtilsGui.showConfirmYesOrNoOrCancel comp = null");
    		//(JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this);
    	}    	
    	
       String title="Προσοχή !";
       Object[] options = {"<html>Yes <b>enter</b></html>", "<html>No</html>", "<html>Cancel <b>esc</b></html>"};
       int optionPane=1;     

       
       if (message.length()>messageLength)
       {
             optionPane = JOptionPane.showOptionDialog(comp, new MessagePanelError(message,0),title,
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                       options, options[0]); // 1st parameter null for center of screen but no modal

       }
       else
       {
              optionPane = JOptionPane.showOptionDialog(comp, message,title,
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                       options, options[0]); // 1st parameter null for center of screen but no modal
       }
       
       
     /* int YES = 0;
    int NO = 1;
     int CANCEL = 3;*/
       
       
    // showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) 
          
         return optionPane;
     }
   
  public boolean getIsLogVisibleAndOtherParameters()
  {
      
         systemDirectorySymbol=System.getProperty("file.separator");
   
        Properties p = new Properties(); // properties to be used for connection
   	    boolean isVisible = false;
   	try
   	{
     	Properties props = new Properties(); //properties to get from file
        //String fileName = "ViewDB.txt"; //get properties from file
        FileInputStream in = new FileInputStream(VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_CONFIG);
        props.load(in);
        if(props.getProperty("runlogview")!= null && props.getProperty("runlogview").equalsIgnoreCase("11a"))
        {
        isVisible = true;
        }
        
        if(props.getProperty("showfunctionsondevelopment")!= null && props.getProperty("showfunctionsondevelopment").equalsIgnoreCase("12a"))
        {
            VariablesGlobal.globalEnableModulesInDevelopment=true;
        }
        else
        {
            VariablesGlobal.globalEnableModulesInDevelopment=false;
        }
       }
      catch (IOException ex)
      {
          System.out.println("UtilsGui.setLookAndFeel IOException:Cannot find text file: "+VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_CONFIG);
          //System.err.println(ex);
      }
   return isVisible;
  }
    
    
  public void setLookAndFeel()
  {
   systemDirectorySymbol=System.getProperty("file.separator");
   
        Properties p = new Properties(); // properties to be used for connection
   	    String laf = null;
   	  try
   	  {
     	Properties props = new Properties(); //properties to get from file
        //String fileName = "ViewDB.txt"; //get properties from file
        FileInputStream in = new FileInputStream(VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_CONFIG);
        props.load(in);

        laf = props.getProperty("LookAndFeel");

        
        if (laf.equals(""))
        {   laf = UIManager.getSystemLookAndFeelClassName() ;   }  //  com.sun.java.swing.plaf.windows.WindowsLookAndFeel
 
        if (laf.equals("java"))
        {      }
        else
        {
             UIManager.setLookAndFeel(laf);   // when there is chosen laf
        }
        
      
        JDialog.setDefaultLookAndFeelDecorated(true);  
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        
      }
      catch (IOException ex)
      {
          System.out.println("UtilsGui.setLookAndFeel IOException:Cannot find text file: "+VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_CONFIG);
          //System.err.println(ex);
      }
      catch (UnsupportedLookAndFeelException exc)
        {System.out.println("UtilsGui.setLookAndFeel UnsupportedLookAndFeel: "+laf+exc);}
      catch (Exception exc)
        {System.out.println("UtilsGui.setLookAndFeel Error "+laf+": "+exc);} 

   }

   /*
  *  called from dialog main
  */
  public void createUiConfigToFile()
	{
	
         String dateFormat="dd-MM-yyyy";
         String dateFormatEdit="dd-MM-yyyy";
		
		Properties fileProperties = new Properties();
        
        fileProperties.setProperty("LookAndFeel", "org.fife.plaf.Office2003.Office2003LookAndFeel");//    com.sun.java.swing.plaf.windows.WindowsLookAndFeel
        
        //String dateFormatSelection = cmbDateFormat.getSelectedItem().toString();
        fileProperties.setProperty("date.format", dateFormat);
        fileProperties.setProperty("date.formatEdit", dateFormatEdit);
        fileProperties.setProperty("runlogview", "0");// is read in DialogMain.main
        fileProperties.setProperty("showfunctionsondevelopment", "0");// is read in DialogMain.main
        
 
 //		System.out.println(VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_CONFIG) ;

        
 
        File file = new File(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_CONFIG);
        // Create file if it does not exist
        boolean exists= file.exists();
        if(!exists)
        {
                 try
             {
        boolean success = false;
        success = file.createNewFile();
        if (success)
        {
            // File did not exist and was created
           System.out.println(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_CONFIG+ " did not found and created.") ;
            fileProperties.store(new FileOutputStream(file), null);
        }
        else
        {
            // File already exists
            fileProperties.store(new FileOutputStream(file), null);
        }
            }catch (IOException e)
            { System.err.println("DialogMain.setConfigToFile (cannot find file) "+e+" "+VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_CONFIG);    }
        }
        else // if it exists from an older version
        {
            //boolean isLogVisible = getIsLogVisible();
        }

    } 

public class MessagePanelError extends JPanel
{
  JScrollPane messageScrollPane = new JScrollPane();
  
  String message;
  int rows ;


  public MessagePanelError(String message, int rows)
  {
    this.message = message;
    this.rows=rows;
    try
    {     initialize();   }
    catch(Exception ex)
    {    ex.printStackTrace();   }
  }

  private MessagePanelError()
  {  }

  void initialize() throws Exception
  {
  	
  	     //Font font = new Font(reportArea.getFont().getName(), Font.BOLD /*reportArea.getFont().getStyle()*/, 8);
         //reportArea.setFont(font);*/
  	
    this.setLayout(new BorderLayout());
    messageTextArea.setEnabled(true);
    Font f = this.getFont();
    messageTextArea.setFont(f);
    messageTextArea.setBackground(this.getBackground());
    messageTextArea.setEditable(false);
    messageTextArea.setLineWrap(true);
    messageTextArea.setText(message);
    this.add(messageScrollPane, BorderLayout.CENTER);
    messageScrollPane.getViewport().add(messageTextArea, null);
    messageTextArea.setBorder(null);
    messageScrollPane.setBorder(null);
   
   setPanelSize();

  }
  public void setText(String text)
  {
    this.message = text;
  }
  
  private void setPanelSize()
  {
  	Dimension dime =this.getPreferredSize();
  	//this.getPreferredSize(dime);
  	
  	int height=0;
  	int width=0;
  	
  	 double dblwidth =dime.getWidth();
     double dblheight=dime.getHeight();  	
  	

  	
  	if(rows!=0)
  	{
  		messageTextArea.setRows(rows+1);
  		messageTextArea.setColumns(67);
  		//height=rows;
  	}
  	else
  	{
  		
     if(dblwidth>Integer.MIN_VALUE && dblwidth<Integer.MAX_VALUE)
     {       width = (int)dblwidth;            }

     
     if(dblheight>Integer.MIN_VALUE && dblheight<Integer.MAX_VALUE)
     {       height = (int)dblheight;            }
     
       	
  	if(message.length()>messageLength)
  	{
  		height=message.length()/3;
  		//System.out.println("UtilsGui.setPanelSize height "+height);
  	}  	
  		
  		
          
     if(width>750)
     { width=750;} 
     else if (width<460)
     { width=460; }
     
     if(height>480)
     { height=480;} 
          
     this.setPreferredSize(new Dimension(width, height));  			
  		
  	}
     
  }


}
   public String showInputMessage(Object message, Object entityName)
  {
  	 return JOptionPane.showInputDialog(message,entityName);
  } 
    // from   http://www.koders.com/java/fid20361AB8C305DE9B9110DE90F2154FC43AA0E57C.aspx?s=net.sourceforge.processdash.ui.lib#L27
    /** An icon to draw a small downward-pointing arrow.
    
    public static class ArrowDown implements Icon
    {

        Color arrowColor = Color.black;

        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(arrowColor);
            g.drawLine(x, y, x+4, y);
            g.drawLine(x+1, y+1, x+3, y+1);
            g.drawLine(x+2, y+2, x+2, y+2);
        }

        public int getIconWidth() {
            return 6;
        }

        public int getIconHeight() {
            return 4;
        }

    } */



}