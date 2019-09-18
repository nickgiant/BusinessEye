// created 15-10-2009

package com.tool.guicomps;

 import com.tool.utils.*;
import com.tool.gui.*;

import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.SwingConstants;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TableCellRendererValidation extends DefaultTableCellRenderer implements Constants
{
   
   //coloring a row when is not selected
    public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) 
    {
        UtilsOS uOS = new UtilsOS();
        UtilsString utilsString =new UtilsString();
                      	Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                      	  
                      	  //System.out.println("PanelODMRData. check afm "+value+" "+row);
                      	  
      	               /*   Color highlight = null;
      	                 if(uOS.isOSWindows())
      	                 {
      	                        highlight = VariablesGlobal.globalColorWindowsHighlight;//.activeCaption;	
      	                         //ac.brighter().brighter();
                                Color c = table.getBackground();
                                if( (row%2)==0)
                                {	  
                                   setBackground(highlight); 
                                }
                                else
                                {     setBackground(c);    }  
                                	
                                if(isSelected)	    	     
                                {
                                	setBackground(table.getSelectionBackground());
                                }
      	                  }
      	                  else
      	                  {
      	                         //	highlight = this.getForeground();
      	                  }*/




                      	  
                      	  if(value!=null && !value.equals("") && !utilsString.checkGreekAFM(value.toString()))
                      	  {
                      	  	//JLabel lbl = new JLabel();
                      	  	//lbl.setIcon(ICO_CANCEL16);
                      	  	//System.out.println("PanelODMRData. correct afm "+value.toString());
                            if(hasFocus)
                            {
      	                         setBackground(CLR_ROW_SELECTED_CELL); // table.getSelectionBackground()
      	                         setForeground(table.getForeground());
                            }  
                            else
                            {
                            	cell.setForeground(Color.red);
                            }
                      	  	cell.setForeground(Color.red);
                    	  	
                      	  }
                      	  else if(value==null || value.equals(""))
                      	  {
                      	  	cell.setBackground(Color.red);
                      	  }
                      	  else
                      	  {
                      	  	if(isSelected)
                      	  	{
                      	  		cell.setForeground(table.getSelectionForeground());
                      	  	}
                      	  	else
                      	  	{
                      	  	   cell.setForeground(table.getForeground());	
                      	  	}
                      	  	
                            if(hasFocus)
                            {
      	                         setBackground(CLR_ROW_SELECTED_CELL); // table.getSelectionBackground()
      	                         setForeground(table.getForeground());
                            }                        	  	
                      	  	
                      	  }
                      	  
                      	  


                      	  
                      	  return cell;
                      	  
   }     
}            	
                      	
                      		          	 	
