  //created 10-01-2009

 package com.tool.guicomps;
 
 import com.tool.utils.*;
import com.tool.gui.*;
import static com.tool.guicomps.Constants.CLR_BORDER_INEDIT;


  import java.awt.Component;
  import java.awt.Color;
  import java.awt.SystemColor;
  import java.awt.FlowLayout;
  
  
  import javax.swing.BoxLayout;
  import javax.swing.SwingConstants;
  import javax.swing.JTable;
  import javax.swing.table.DefaultTableCellRenderer;
  import javax.swing.table.TableCellRenderer; 
  import javax.swing.JCheckBox;
  import javax.swing.JPanel;
  import java.text.SimpleDateFormat;
  import java.text.ParseException;
 
  import java.io.FileInputStream;
  import java.io.IOException;
  
  import java.util.Properties;   
  import java.util.Date;
import javax.swing.BorderFactory;
   
  public class TableCellRendererBoolean extends DefaultTableCellRenderer implements Constants
  {
  	private JCheckBox checkBox;
  	private JPanel panelMain;
  	
    public TableCellRendererBoolean()
    {
      super();
      setHorizontalAlignment(SwingConstants.CENTER);
    }


     public void pushUtilsDateToReadFromFile()
     {
     }
  /*  public void setValue(Object value)
    {
      if ((value != null) && (value instanceof Number))
      {
        Number numberValue = (Number) value;
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        value = formatter.format(numberValue.doubleValue());
      } 
      super.setValue(value);
    } */

    public Component getTableCellRendererComponent(JTable table, Object value,
     boolean isSelected, boolean hasFocus, int row, int column) 
    {
      	
      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
  
      panelMain = new JPanel();
      FlowLayout layoutFlow = new FlowLayout();
      layoutFlow.setVgap(1);
      panelMain.setLayout(layoutFlow);
      panelMain.setRequestFocusEnabled(true);
      checkBox = new JCheckBox();
      checkBox.setBorder(null);
      
      UtilsOS uOS = new UtilsOS();
      
      if( !isSelected )
      {
      	/* Color highlight = null;
      	 Color c = table.getBackground();
      	 if(uOS.isOSWindows())
      	 {      	
      	     
      	     highlight = VariablesGlobal.globalColorWindowsHighlight;//.activeCaption;	
             if( (row%2)==0)
             {	  
                   	 panelMain.setBackground(highlight);
                     checkBox.setBackground(highlight);
             }
             else
             {    
                   panelMain.setBackground(c);
         	       checkBox.setBackground(c);
             }        
      	  }
      	  else
      	  {
      	  	
      	  	       panelMain.setBackground(c);
         	       checkBox.setBackground(c);
      	  }*/

         //checkBox.setEnabled(false);
      }
      else
      {
      	panelMain.setBackground(table.getSelectionBackground());
      	checkBox.setBackground(table.getSelectionBackground());
      	//checkBox.setEnabled(false);

      }

      if(hasFocus )
      {
      	setBackground(CLR_ROW_SELECTED_CELL);
      	panelMain.setBackground(CLR_ROW_SELECTED_CELL);
        checkBox.setBackground(CLR_ROW_SELECTED_CELL);
      	setForeground(table.getForeground());
      }

   if(value!=null)
   {    
      if(Boolean.parseBoolean(value.toString()))
      {
      	checkBox.setSelected(true);
      }
      else
      {
      	checkBox.setSelected(false);
      }
      
      if( value.toString().equals("1") || value.toString().trim().equalsIgnoreCase("true") )
      {
      	checkBox.setSelected(true);
      }
      else if(value.toString().equals("0") || value.toString().trim().equalsIgnoreCase("false") )
      {
      	checkBox.setSelected(false);
      }
   }
   else
   {
       System.out.println("error TableCellRendererBoolean.getTableCellRendererComponent row"+row+" col"+column+" is null val="+value);
   }
      panelMain.add(checkBox);
      
       //return cell;
     return panelMain;
    }

  public void setValue(Object value)
  {
   //   if ((value != null) && (value instanceof Date))
   //   {
     
    // String[] allowedPatternsToRead = {"yyyy-MM-dd","yyyy/MM/dd"};
     
   if(value!=null)
   {
     
      super.setValue(value);
    
   }
   else
   {
   	  super.setValue("");
   }
  }
 } 