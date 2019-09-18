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

public class TableCellRendererDefault extends DefaultTableCellRenderer implements Constants
{
   
   //coloring a row when is not selected
    public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) 
    {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      
      UtilsOS uOS = new UtilsOS();
      
     /* if( !isSelected )
      {
      	 Color highlight = null;
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
      	 }
      	 else
      	 {
      	 //	highlight = this.getForeground();
      	 }
      }*/
      
      if(hasFocus)
      {
      	  setBackground(CLR_ROW_SELECTED_CELL); // table.getSelectionBackground()
      	  setForeground(table.getForeground());
      }
       
        return cell;
    }
    
}