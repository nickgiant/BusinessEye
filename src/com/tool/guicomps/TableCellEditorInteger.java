// created 18-09-2009  // based om javaalmanac
package com.tool.guicomps;


import com.tool.model.*;

//import javax.swing.*;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;

import java.awt.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;

  public class TableCellEditorInteger extends DefaultCellEditor implements TableCellEditor, Constants
  {
    
        // This is the component that will handle the editing of the cell value
        JTextField component = new JTextField();
       private EntityDBFields dbFieldMany;

        
    public TableCellEditorInteger(final JTextField tf, EntityDBFields dbFieldManyIn)//JFormattedTextField tf)//, NumberFormat nf)
    {
       super(tf);
       component=tf;
      dbFieldMany=dbFieldManyIn;
     
      
       component.setHorizontalAlignment(SwingConstants.LEFT);
       component.setBorder(BorderFactory.createLineBorder(CLR_BORDER_INEDIT, 1));
     
     }

        
        // This method is called when a cell value is edited by the user.
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex)
        {
            // 'value' is value contained in the cell located at (rowIndex, vColIndex)
    
            if (isSelected)
            {
                // cell (and perhaps other cells) are selected
            }
    
            // Configure the component with the specified value
            ((JTextField)component).setText((String)value.toString());
            
                              // select all text when focus gained
                  final JTextField tbFinal=component;
                  component.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                        }
                        public void focusGained(FocusEvent e)
                        {    //System.out.println("panelODORData.showRow focus gained");
                             tbFinal.setSelectionStart(0);
                             tbFinal.setSelectionEnd(tbFinal.getText().length());
                        }                    	
                    });
                    
               component.setSelectionStart(0);
               component.setSelectionEnd(tbFinal.getText().length());          
                    
            //component.requestFocus();
            // Return the configured component
            return component;
        }
    
        // This method is called when editing is completed.
        // It must return the new value to be stored in the cell.
        public Object getCellEditorValue()
        {
            return ((JTextField)component).getText();
        }
    }

/*class TableCellEditorString extends DefaultCellEditor
{
  private NumberFormat currencyFormat;

  public TableCellEditorString(final JTextField tf)//JFormattedTextField tf)//, NumberFormat nf)
  {
   super(tf);
   
   //NumberFormat format = NumberFormat.getNumberInstance(); // noumber with , for double
   //currencyFormat = nf;
   
   //tf.setFocusLostBehavior(JFormattedTextField.COMMIT);
   
   tf.setHorizontalAlignment(SwingConstants.LEFT);
   tf.setBorder(null);

   delegate = new EditorDelegate()
   {

    public void setValue(Object param)
    {
     System.out.println("param "+param);
     
     
     if (param.getClass() == String.class)
     {
        //try // from string to double
        //{   
            String str = param.toString();
            //double d = Double.valueOf(str.trim()).doubleValue();
           
            //param=d;
        //}
        //catch (NumberFormatException nfe)
        //{    System.out.println("TableCellEditorString.setValue " + nfe.getMessage());  }
    	
     }
     
     
     /*Double _value = (Double)param;
     if (_value == null) {
      //tf.setValue(currencyFormat.format(0.0));
      tf.setText("0.0");
     } else
     {
      Double _d = _value.doubleValue();
      //String _format = currencyFormat.format(_d);
      //String _format = currencyFormat.format(_d);
      System.out.println("_d "+_d);
      //tf.setValue(_d);
      tf.setText(_d.toString());*/
     //}
 /*   }// setValue



    // This method is called when a cell value is edited by the user.
    public Component getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int rowIndex, int vColIndex)
    {
            // 'value' is value contained in the cell located at (rowIndex, vColIndex)
    
            if (isSelected)
            {
                // cell (and perhaps other cells) are selected
            }
    
            // Configure the component with the specified value
                 
            
            ((JTextField)component).setText((String)value);
    
            // Return the configured component
            return component;
     }



        
        // This method is called when editing is completed.
        // It must return the new value to be stored in the cell.
    public Object getCellEditorValue()
    {

    //    return tf.getCellEditorValue(); 

     //try {
      String field = tf.getText();
      //Number _number = currencyFormat.parse(_field);     
     // double _parsed = Double.valueOf(_field.trim()).doubleValue(); //_number.doubleValue();
      //Double d = new Double(_parsed);
      return field;
    // } catch (ParseException e) {
    //  e.printStackTrace();
    //  return new Double(0.0);
    // }
    }
        
   };
  }
}*/
