  //created 14-09-2006
  // javaalmanac
  package com.tool.guicomps;
 
  import com.tool.utils.*;
  
  import java.awt.Component;
  import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import java.awt.event.*;
import java.awt.*;

import javax.swing.text.*;
import java.text.*;

  import javax.swing.*;
  import javax.swing.SwingConstants;
  import javax.swing.JTable;
  import javax.swing.table.DefaultTableCellRenderer;
  import javax.swing.table.TableCellRenderer; 
  //import javax.swing.event.*;
  import javax.swing.table.TableCellEditor;
  import javax.swing.DefaultCellEditor;
  import javax.swing.AbstractCellEditor;
  import javax.swing.JComponent;
  import javax.swing.JTextField;
  import javax.swing.JTextField;
  import javax.swing.border.LineBorder;
  import javax.swing.SwingUtilities;
  import javax.swing.JFrame;
  
  //import java.text.SimpleDateFormat;
  //import java.text.ParseException;
  
  import java.util.Date;
   
  //import java.util.EventObject;


    public class TableCellEditorDate extends DefaultCellEditor implements TableCellEditor, Constants
    {
    private UtilsDate utilsd; 
    private UtilsGui utilsGui;
    private JTextComponent component;
    //private JxFormattedTextFieldDate.FormatSpec formatSpec;
    private JTextBoxWithEditButtons txtdate;
//    private String yearEnforceInLines;
    //private JxFormattedTextFieldDate textFormatedDate;
    private JFormattedTextField textFormatedDate;
    
           
    public TableCellEditorDate(JTextField txt, String yearEnforceInLinesIn, UtilsDate utilsDate)
    { 
        // This is the component that will handle the editing of the cell value
        super(txt);
 //       yearEnforceInLines=yearEnforceInLinesIn;
        component = txt;
        
        utilsd = utilsDate;//new UtilsDate();
        utilsd.readFromFileDateFormats();
        utilsGui=new UtilsGui();
        //formatSpec = new JxFormattedTextFieldDate.FormatSpec();
        txtdate = new JTextBoxWithEditButtons();
//        txtdate.setYearEnforce(yearEnforceInLines);

    

        
        
    }   
    // This method is called when a cell value is edited by the user.
    public Component getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int rowIndex, int vColIndex)
    {
            // 'value' is value contained in the cell located at (rowIndex, vColIndex)
    
    textFormatedDate = new JFormattedTextField();
    //textFormatedDate.setPreferredSize(new Dimension(170,25));   

            if (isSelected)
            {
                // cell (and perhaps other cells) are selected
            }
    
            // Configure the component with the specified value
                 
       String[] allowedPatternsToRead = {"yyyy-MM-dd","yyyy/MM/dd"};
       String dateString = value.toString();
       //System.out.println("tableCellEditor.getTableCellEditorComponent "+dateString);
       value = utilsd.reformatDateStringToEdit(dateString,allowedPatternsToRead);
       
       //JTextBoxWithEditButtons txtdate = new JTextBoxWithEditButtons();
       int intColumnWidthDate = 8;//utilsDate.getDateFormatEditing().length();		   
       //textFormatedDate = new JxFormattedTextFieldDate(intColumnWidthDate,formatSpec);
       txtdate = new JTextBoxWithEditButtons(textFormatedDate, true,ICO_CALENDAR,null , false,null,null,LOOKUP_TYPE_DATE, null,/*yearEnforceInLines*/"","",MONTH_DATE_ONLY);                  	
       
       textFormatedDate.setBorder(BorderFactory.createEmptyBorder()); 
       final JTable tableFinal=table;
        JPanel panelMain = new JPanel(new BorderLayout())
        {
            public void addNotify()
            {
                super.addNotify();
                //txtCompKey.requestFocus();// deleted
                tableFinal.requestFocus();// added
            }

            protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed)
            {
            	//super.processKeyBinding( ks,  e, condition, pressed);
            	
               //InputMap inputMap = textFormatedDate.getInputMap(condition);
               //ActionMap actionMap = textFormatedDate.getActionMap();	
               
               InputMap inputMap = txtdate.getTextComp().getInputMap(condition);
               ActionMap actionMap = txtdate.getTextComp().getActionMap();	            	
            	
            	if (inputMap != null && actionMap != null)
            	{
                  Object binding = inputMap.get(ks);
                 if (binding != null)
                 {
                    Action action = actionMap.get(binding);
                    if (action != null) 
                    {
                        //System.out.println("TableCellEditor.processKeyBinding "+txtdate.getText()+"    "+txtdate.isDateEmpty());
                    	if(txtdate.isDateEmpty())
                        {
                            
                        }    
                        else
                        {
                           textFormatedDate.setSelectionStart(0);
                           textFormatedDate.setSelectionEnd(textFormatedDate.getText().length());
                        } 
                         //System.out.println("TableCellEditorDate.getTableCellEditorComponent if inputMap"+inputMap+" actionMap"+actionMap+" binding"+binding+" action"+action);                   	
                                            	
                         //return SwingUtilities.notifyAction(action, ks, e, textFormatedDate, e.getModifiers());
                         return SwingUtilities.notifyAction(action, ks, e, txtdate.getTextComp(), e.getModifiersEx​());
                          
                    }
                    else
                    {
                    	//System.out.println("TableCellEditorDate.getTableCellEditorComponent else inputMap"+inputMap+" actionMap"+actionMap+" binding"+binding+" action"+action);
                    }
                 }
               }
               
               
               
               tableFinal.requestFocus();//added, if not added it changes focus to next component when enter pressed
            //}
            return super.processKeyBinding(ks, e, condition, pressed);
            	
            	
           } 
        }; 

       component = txtdate.getTextComp();
            
       ((JTextComponent)component).setText((String)value);
            
            
                  // select all text when focus gained
                  final JTextComponent tbFinal=component;
                  final JTextBoxWithEditButtons txtDateEdit=txtdate;
                  component.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                        }
                        public void focusGained(FocusEvent e)
                        {    //System.out.println("panelODORData.showRow focus gained");
                             
                            //System.out.println("JTextBoxWithEditButtons.JTextBoxWithEditButtons "+txtDateEdit.getText()+"    "+txtDateEdit.isDateEmpty());
                    	    if(txtDateEdit.isDateEmpty())
                            {
                                tbFinal.setCaretPosition(0);
                                
                                //txt needs one more keystroke, the following does not work
                                //tbFinal.dispatchEvent(new KeyEvent(tbFinal, KeyEvent.KEY_TYPED, 0, 0, KeyEvent.VK_UNDEFINED, '0'));                                 
                            }    
                            else
                            {
                             tbFinal.setSelectionStart(0);
                             tbFinal.setSelectionEnd(tbFinal.getText().length());
                            }
                        }                    	
                    });

            // Return the configured component
            //return component;
           // return txtdate.getComponent();
           panelMain.setBorder(BorderFactory.createLineBorder(CLR_BORDER_INEDIT, 1));
           panelMain.add(txtdate.getComponent(), BorderLayout.PAGE_START);
            
          return panelMain;
     }

        // when stopped cell editing
        public boolean stopCellEditing()
		{
			try
			{
			String editingValue =	((JTextField)component).getText();
				//String editingValue = (String)getCellEditorValue();
 
				if(!txtdate.getIsDateValid())
				{
					
					//component.setBorder(new LineBorder(Color.red));
					component.selectAll();
					component.requestFocusInWindow();
 
             	    // utilsGui.showMessageInfo("Λάθος στη μορφή της ημερομηνίας. \n Η σωστή μορφή είναι dd/mm/yyyy");
					return false;
				}
			}
			catch(ClassCastException exception)
			{
				return false;
			}
 
			return super.stopCellEditing();
		}





    
        // This method is called when editing is completed.
        // It must return the new value to be stored in the cell.
        public Object getCellEditorValue()
        {
         String value;
         value=((JTextField)component).getText();
         
         String[] allowedPatternsToRead = new String[1];     
         allowedPatternsToRead[0] = utilsd.getDateFormatEditing();//{"dd-MM-yyyy","dd/MM/yyyy","yyyy-MM-dd","yyyy/MM/dd"};
         //System.out.println("tableCellEditorDate.getTableCellEditorComponent"+allowedPatternsToRead);
         String dateString = value.toString();
         value = utilsd.reformatDateStringToSaveToDB(dateString);
         
         
            return value;
        }
    
 }
  
