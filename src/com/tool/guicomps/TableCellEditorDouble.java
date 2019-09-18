// created 01-10-2006  // based om javaalmanac
// tutorial Professional Swing JTable
package com.tool.guicomps;


import com.tool.utils.*;
import com.tool.model.*;
import com.tool.jdbc.*;

import java.sql.*;

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
import javax.swing.border.LineBorder;

import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


  public class TableCellEditorDouble extends DefaultCellEditor implements TableCellEditor, Constants
  {
    
        // This is the component that will handle the editing of the cell value
    protected JTable table; 
    protected int row, column;    
    private UtilsDouble utilsd; 
    private UtilsGui utilsGui;
    private JTextField component;    
    //private EntityDBFields dbFieldMany;
  // private Database db= new Database();    
        
    public TableCellEditorDouble(final JTextField tf,UtilsDouble uDouble)//JFormattedTextField tf)//, NumberFormat nf)
    {
       super(tf);
       component=tf;
       //dbFieldMany=dbFieldManyIn;
       
        	utilsd = uDouble;//new UtilsDouble();
                //System.out.println("TableCellEditorDouble.TableCellEditorDouble");
            //utilsd.getSettingsFromDb();
        //NumberFormat format = NumberFormat.getNumberInstance(); // noumber with , for double
       //currencyFormat = nf;
   
       //tf.setFocusLostBehavior(JFormattedTextField.COMMIT);
   
       tf.setHorizontalAlignment(SwingConstants.RIGHT);
       tf.setBorder(BorderFactory.createLineBorder(CLR_BORDER_INEDIT, 1));
     
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
            
         String s = "0";
        if(value!=null && !value.toString().trim().equalsIgnoreCase(""))
        {
        	  s = value.toString();
                  //System.out.println("TableCellEditorDouble.getTableCellEditorComponent   s:"+s+"     value:"+value);
        }
        else
        {
            s="0";
        }
        //System.out.println("TableCellEditorDouble.getTableCellEditorComponent   s:"+s);
         double valueDbl = Double.valueOf(s).doubleValue(); 
         String valueStr="";
         if(valueDbl == 0)
         {
         	valueStr="";
         }
         else
         {
           valueStr = utilsd.getDoubleEditing((Double)valueDbl);
         }
         //System.out.println("TableCellEditorDouble.getTableCellEditorComponent"+valueStr+" "+value);
            ((JTextField)component).setText((String)valueStr);
            
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
                  
                                    
                  
                  
          this.table = table;
          this.row = rowIndex;
          this.column = vColIndex;    

          component.setSelectionStart(0);
          component.setSelectionEnd(component.getText().length());        	    	
            //System.out.println("TableCellEditorDouble.getTableCellEditorComponent row --> "+this.row);
            // Return the configured component
            return component;
      }
        
        // when stopped cell editing
      /*  public boolean stopCellEditing()
		{
			try
			{
			String editingValue =	((JTextField)component).getText();
				//String editingValue = (String)getCellEditorValue();
 
				if(editingValue.length() <= 7)
				{
					
					component.setBorder(new LineBorder(Color.red));
					component.selectAll();
					component.requestFocusInWindow();
 
             	     utilsGui.showMessageInfo("Λάθος στη μορφή του αριθμού.");
					return false;
				}
				
			}
			catch(ClassCastException exception)
			{
				return false;
			}
 
			return super.stopCellEditing();
		}*/
    
    
        // This method is called when editing is completed.
        // It must return the new value to be stored in the cell.
     public Object getCellEditorValue()
     {
         String valueStr = ((JTextField)component).getText();
         
         String value=utilsd.getDoubleSaving(valueStr); 
         
         //double valueDbl = Double.valueOf(valueStr).doubleValue(); 
        //System.out.println("TableCellEditorDouble.getCellEditorValue -----"+row);
        // calculation, in editors
 /*       if(dbFieldMany!=null &&dbFieldMany.getWhenSetThenCalculateField()!=-1 && valueStr!=null && !valueStr.equalsIgnoreCase(""))
        {
        try
        {	
        	int[] cells = dbFieldMany.getCalculationInputFields();
        	String calculation =  dbFieldMany.getCalculation();
        	String val = "";
        	  ResultSet rs;
        	  if(cells.length == 1)
        	  {
        	  	calculation= calculation+valueStr;
        	  }
        	  else if (cells.length ==2)
        	  {
        	  	if(cells[0]==this.column)
        	  	{
        	  		calculation= "SELECT "+value+calculation+table.getValueAt(row,cells[1]);
        	  	}
        	  	else if(cells[1]==this.column)
        	  	{
        	  		calculation= "SELECT "+table.getValueAt(row,cells[0])+calculation+value;
        	  	}
        	  	else
        	  	{
        	  	   calculation= "SELECT "+table.getValueAt(row,cells[0])+calculation+table.getValueAt(row,cells[1]);	
        	  	}
        	  	
        	  }
        	  else
        	  {
        	  	System.out.println("TableCellEditorDouble.getCellEditorValue unsupported no of cells "+cells.length);
        	  }
        	 
        	 
        	 
              rs = db.retrieveResultSet(calculation);
              if (rs.first())
              {
                 rs = db.retrieveRow(rs, 1);// go to the only row  
                 //System.out.println("TableCellEditorDouble.getCellEditorValue  calculation "+calculation);
                 val = rs.getString(1);// get field data	         	
              }
             table.setValueAt(val, row, dbFieldMany.getWhenSetThenCalculateField()); 
           
             System.out.println("TableCellEditorLookupB.TableCellEditorDouble val === "+val+" row"+row+" col"+dbFieldMany.getWhenSetThenCalculateField()+" calculation "+calculation);

           
           db.releaseConnectionRs();

         }//try
         catch ( SQLException sqlex)
         {
             System.out.println("error:TableCellEditorDouble.getCellEditorValue(): "+sqlex.getMessage());
         }
                 		
        }
*/
         	
         return value;
     }
}




/*class TableCellEditorDouble extends DefaultCellEditor
{
  private NumberFormat currencyFormat;

  public TableCellEditorDouble(final JTextField tf)//JFormattedTextField tf)//, NumberFormat nf)
  {
   super(tf);
   
   //NumberFormat format = NumberFormat.getNumberInstance(); // noumber with , for double
   //currencyFormat = nf;
   
//   tf.setFocusLostBehavior(JFormattedTextField.COMMIT);
   
   tf.setHorizontalAlignment(SwingConstants.RIGHT);
   tf.setBorder(null);

   delegate = new EditorDelegate()
   {

    public void setValue(Object param)
    {
     System.out.println("param "+param);
     
     
     if (param.getClass() == String.class)
     {
        try // from string to double
        {   
            String str = param.toString();
            double d = Double.valueOf(str.trim()).doubleValue();
           
            param=d;
        }
        catch (NumberFormatException nfe)
        {    System.out.println("TableCellEditorDouble.parseStringToDouble " + nfe.getMessage());  }
    	
     }
     
     
     Double _value = (Double)param;
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
      tf.setText(_d.toString());
     }
    }// setValue

    public Object getCellEditorValue()
    {
     //try {
      String _field = tf.getText();
      //Number _number = currencyFormat.parse(_field);     
      double _parsed = Double.valueOf(_field.trim()).doubleValue(); //_number.doubleValue();
      Double d = new Double(_parsed);
      return d;
    // } catch (ParseException e) {
    //  e.printStackTrace();
    //  return new Double(0.0);
    // }
    }
        
   };
  }
}*/
