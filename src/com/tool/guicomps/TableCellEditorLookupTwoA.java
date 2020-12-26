// created 01-10-2006 by  Santhose Kumar  
//   http://www.jroller.com/santhosh/entry/add_button_to_any_tablecelleditor
/** 
 *  http://www.jroller.com/santhosh/category/Swing
 *
 * MySwing: Advanced Swing Utilites 
 * Copyright (C) 2005  Santhosh Kumar T 
 * <p/> 
 * This library is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version. 
 * <p/> 
 * This library is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU 
 * Lesser General Public License for more details. 
 */ 

package com.tool.guicomps;

import com.tool.model.LookUpMgt;
import com.tool.gui.PanelEditOneDataRec;
import com.tool.gui.PanelOneDataManyRecData;
import com.tool.model.EntityPanel;
import com.tool.gui.*;
import com.tool.jdbc.*;
import com.tool.utils.*;
import com.tool.model.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.text.*;
import javax.swing.event.*;

import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;


import javax.swing.table.TableCellEditor;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.ImageIcon;
import javax.swing.DefaultCellEditor;
import javax.swing.SwingConstants;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.AbstractAction;

import java.util.EventObject;
 
public class TableCellEditorLookupTwoA implements TableCellEditor, ActionListener, Constants
{ 
 
    private DefaultCellEditor editor; 
    //private JButton customEditorButton = new JButton("<html><b>F4</b></html>"); 
    private JButton customEditorButton = new JButton(); 
    private JButtonListMenu customEditorButton2;
    
    private EntityDBFields[] dbFields;
    //private LookUp lookUp;
    private JFrame frame;
    protected JTable table; 
    protected int row, column;
    private String foreignTable; 
    private String luname = "";
    private JTextField txtCompKey;
    private JTextField txtDescription;
    private int columnLength;
    PanelOneDataManyRecData panelOneDataManyRecData;
    
   //private Database db= new Database();
   //private Statement stmt;
   //private ResultSet rs;
   //private ResultSetMetaData rsmd;   
   
    private LookUpMgt lookUp;
    private boolean hasDataChanged = false;
    private WindowLookUp winLookUp;
    private UtilsString utilsString;
    private UtilsPanelReport utilsPanelReport;
    private DocumentHandler documentHandlerKey;
//    private String foreignTable;
    //private EntityDBFields dbFieldMany;
    private PanelManagement panelManagement;
    //private EntityReport entityReport;
    private String entity; 
    //private String formGlobalVariable1;
 //private String formGlobalField1;
        private String formGlobalTableToApply1;
      private int noOfCol;  
        private String fieldVariableFromPreField;
       private int intTableOfParentDBFields;
     
     
    public TableCellEditorLookupTwoA(JTextField txt,int columnLengthIn,String lunameIn, String foreignTableIn,ArrayList listMenuCaption, String entityIn,
            JFrame frameIn,EntityDBFields[] dbFieldsIn,int intTableOfParentDBFieldsIn,int noOfColIn, PanelManagement panelManagementIn)//,EntityReport entityReportIn)
    { 
    	dbFields=dbFieldsIn;
        entity=entityIn;
        //formGlobalVariable1=formGlobalVariable1In;
        frame=frameIn;
        columnLength=columnLengthIn;
        //formGlobalTable1=formGlobalTable1In;
        foreignTable=foreignTableIn;
        luname=lunameIn;
        lookUp= new LookUpMgt();
        winLookUp = new WindowLookUp(frame);
        utilsString = new UtilsString();
        utilsPanelReport = new UtilsPanelReport();
        intTableOfParentDBFields =intTableOfParentDBFieldsIn;
        //dbFieldMany=dbFieldManyIn;
        panelManagement=panelManagementIn;
        
            int intNoOfFields =  dbFields.length;
    for(int i = 0;i<intNoOfFields;i++)
    {
        String flf ="";
        flf = dbFields[i].getFormVariableFromField();
        if(flf!=null && !flf.equalsIgnoreCase(""))
        {
                    fieldVariableFromPreField = flf;
                    break;
        }
        else
        {
            fieldVariableFromPreField = "";
        }
    }
        
        
    //    formGlobalTableToApply1=formGlobalTableToApply1In;
        noOfCol = noOfColIn;
        
        //entityReport=entityReportIn;
        System.out.println("TableCellEditorLookupTwoA   entity:"+entity);

              JPopupMenu popupMenuTools = new JPopupMenu("----");
         customEditorButton2 = new JButtonListMenu("",popupMenuTools); 
        
        customEditorButton.setIcon(ICO_LOOKUP);
        
        DefaultCellEditor editor = new DefaultCellEditor(txt);
        editor.setClickCountToStart(JTABLEEDITABLE_CLICKCOUNTTOSTART_1);//edit at 1 click

         txtDescription =new JTextField();
         txtDescription.setBorder(BorderFactory.createEmptyBorder());        
        
        this.editor = editor; 
        this.foreignTable=foreignTable;
        txt.setHorizontalAlignment(SwingConstants.RIGHT);
        //txt.setDocument(new PlainDocumentInsertText(columnWidth,columnType));//limiting the capacity of txt


        	customEditorButton.setToolTipText("εμφάνιση πίνακα για επιλογή");
        	customEditorButton2.setToolTipText("εμφάνιση ιδιοτήτων επιλεγμένης εγγραφής");
       
        customEditorButton.addActionListener(this); 
        
       Action actionShowDialogLookUp = new ActionShowDialogLookUp();
       txt.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "showDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
       txt.getActionMap().put("showDialogLookUp", actionShowDialogLookUp);
       txtDescription.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "showDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
       txtDescription.getActionMap().put("showDialogLookUp", actionShowDialogLookUp); 
      // customEditorButton.setAction(actionShowDialogLookUp);//.addActionListener(this); 
       
        customEditorButton2.setIcon(ICO_EDIT14);
     if(listMenuCaption!=null &&  listMenuCaption.size()>0)   
     {
        for(int l = 0; l<listMenuCaption.size(); l++)          
        {
       JMenuItem menuListItem = (JMenuItem)listMenuCaption.get(l);
       popupMenuTools.add(menuListItem);
   
        }
     }
       
       final JTextField txtFinal = txt;
       final JTextField txtDescriptionFinal = txtDescription;
        txtFinal.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
        {
                  public void focusLost(FocusEvent e)
                  {  // if entered nothing or spaces on textbox
                           
                  }

                  public void focusGained(FocusEvent e)
                  {  // if entered nothing or spaces on textbox
                            
                        if (txtFinal.getText().equals("0"))
                        {
                             txtFinal.setText("");//delete if anytext is in JTextField before it gets the focus with the keybord
                        }                           
                 }
       });        

        txtDescriptionFinal.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
        {
                  public void focusLost(FocusEvent e)
                  {  // if entered nothing or spaces on textbox
                           
                  }

                  public void focusGained(FocusEvent e)
                  {  // if entered nothing or spaces on textbox
                        if (txtFinal.getText().equals("0"))
                        {
                             txtFinal.setText("");//delete if anytext is in JTextField before it gets the focus with the keybord
                        }                           
                 }
       });  
       	
       	txtCompKey=txt;      
       
    //     txt.getDocument().addDocumentListener(new DocumentHandler(0,"java.sql.Integer",luname)); 
    //    txtDescription.getDocument().addDocumentListener(new DocumentHandler(1,"java.sql.String",luname)); 

       
        // ui-tweaking 
        customEditorButton.setFocusable(false); 
        customEditorButton.setFocusPainted(false); 
        customEditorButton.setMargin(new Insets(1, 1, 1, 1)); 
        
        customEditorButton2.setFocusable(false); 
        customEditorButton2.setFocusPainted(false); 
        customEditorButton2.setMargin(new Insets(1, 9, 1, 1));     //  Insets(int top, int left, int bottom, int right)
    } 

    // This method is called when a cell value is edited by the user.
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
         txtCompKey = (JTextField)editor.getTableCellEditorComponent(table, value, isSelected, row, column);
          
         
         int width = getColumnWidth(table,column);
         
         System.out.println("TableCellEditorLookupTwoA.getTableCellEditorComponent row"+row+" column"+column);
         
         //winLookUp.setIsSelected(false);
         
         //txtCompKey.setColumns(width/3);
         txtCompKey.setColumns(8);
         //txtDescription.setColumns(width*2/3);
         txtDescription.setColumns(width-8);
         
         txtDescription.setText(utilsPanelReport.getLookupValue(luname,foreignTable,txtCompKey.getText(),1,false,"","",entity,null/*dbFieldsAll*/,null/*fieldTxts*/));
         
        final JTable tableFinal=table;
        JPanel panelTextboxes = new JPanel();
        panelTextboxes.setLayout(new BoxLayout(panelTextboxes, BoxLayout.LINE_AXIS));
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
                InputMap map = txtDescription.getInputMap(condition);
                ActionMap am = txtDescription.getActionMap();

                if(map!=null && am!=null && isEnabled())
                {
                    Object binding = map.get(ks);
                    Action action = (binding==null) ? null : am.get(binding);
                    if(action!=null)
                    {                        
                        /*if (txtDescription.getText().equals("0"))
                        {
                             txtCompKey.setText("");//delete if anytext is in JTextField before it gets the focus with the keybord
                        }*/
                             txtDescription.setSelectionStart(0);
                             txtDescription.setSelectionEnd(txtDescription.getText().length());                        
                        
                        
                        return SwingUtilities.notifyAction(action, ks, e, txtDescription, e.getModifiersEx​());
                    }
                }
                                
                tableFinal.requestFocus();//added, if not added it changes focus to next component when enter pressed
                return false;
            }
        };
        

        documentHandlerKey = new DocumentHandler(0,"java.sql.Integer",luname);
        txtCompKey.getDocument().addDocumentListener(documentHandlerKey); 
        txtDescription.getDocument().addDocumentListener(new DocumentHandler(1,"java.sql.String",luname));    


                  // select all text when focus gained
                  final JTextField txtCompKeyFinal = txtCompKey;
                  txtCompKeyFinal.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                            //tableFinal.requestFocus();
                        }
                        public void focusGained(FocusEvent e)
                        {    //System.out.println("panelODORData.showRow focus gained");
                             //tbFinalDesc.setText(getLookupText(tbFinal.getText(),foreignTable));
                             //txtCompKeyFinal.setSelectionStart(0);
                             //txtCompKeyFinal.setSelectionEnd(txtCompKeyFinal.getText().length());
                             
                        }                    	
                    });                   
                  
                  
                  //final JTextField txtDescriptionFinal = txtDescription;
                  txtDescription.addFocusListener(new FocusListener()
                   {
                   	 String text="";
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                            //tableFinal.requestFocus();
                           // if(txtCompKeyFinal.getText().equalsIgnoreCase(""))
                          //  {
                            //	txtCompKeyFinal.setText(text);
                           // }
                        }
                        public void focusGained(FocusEvent e)
                        {    //System.out.println("panelODORData.showRow focus gained");
                             //tbFinalDesc.setText(getLookupText(tbFinal.getText(),foreignTable));
                       //      text = txtCompKeyFinal.getText();
                       //      txtCompKeyFinal.setText("");
                             txtDescription.setSelectionStart(0);
                             txtDescription.setSelectionEnd(txtDescription.getText().length());
                             
                        }                    	
                    });  
   

   
        
        JLabel lblBetween = new JLabel("");
        lblBetween.setForeground(lblBetween.getBackground());
        
        panelMain.setRequestFocusEnabled(true);
        panelTextboxes.add(txtCompKey);
        if ((dbFields[column].getLookupEntityName()!= null) && (lookUp.getLookUpIntNoOfColsWhenInTable(dbFields[column].getLookupEntityName())==2))
        {
        	
        }
        else if ((dbFields[column].getLookupEntityName()!= null) && (lookUp.getLookUpIntNoOfColsWhenInTable(dbFields[column].getLookupEntityName())==1))//(!rsmd.getTableName(i).equalsIgnoreCase(foreignTable)))
        {
               panelTextboxes.add(lblBetween);
               panelTextboxes.add(txtDescription);
        }
        else
        {
        	
        }

        // added after a comment in  http://www.jroller.com/santhosh/entry/add_button_to_any_tablecelleditor#comments
        panelMain.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                JPanel panel = (JPanel)e.getSource();
                panel.getComponent(0).requestFocus();
                panel.removeFocusListener(this);
            }
        });


        FlowLayout fl = new FlowLayout();
        fl.setVgap(0);
        fl.setHgap(0);
        JPanel panelButtons = new JPanel(fl);
        panelButtons.add(customEditorButton);
        panelButtons.add(customEditorButton2);
        
        panelMain.setBorder(BorderFactory.createLineBorder(CLR_BORDER_INEDIT, 1));
        panelMain.add(panelTextboxes);
        panelMain.add(panelButtons, BorderLayout.LINE_END);
        this.table = table;
        this.row = row;
        this.column = column;
        
        tableFinal.requestFocus();
        
        return panelMain;
              
    } 
 
    public Object getCellEditorValue()
    { 
    	Object ret = null;
    	
    	
            txtCompKey.getDocument().removeDocumentListener(documentHandlerKey);
    		ret = editor.getCellEditorValue();     	
    	//String selVal = winLookUp.getSelectedText();
        
       System.out.println("TableCellEditorLookupTwoA.getCellEditorValue    ret:"+ret);
        
/*    	if(selVal!=null && !selVal.equals(""))
    	{
    		
    		txtCompKey.getDocument().removeDocumentListener(documentHandlerKey);// before the next
    		
    	   table.setValueAt(selVal, row, table.getSelectedColumn());  
           txtCompKey.setText(utilsPanelReport.getLookupValue(foreignTable,selVal,1));
           //txtDescription.setText(utilsPanelReport.getLookupValue(foreignTable,selVal,1));
           ret = editor.getCellEditorValue();    		
   	}
    	else
    	{
    		/*if(lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==1)  
    		{
    		   //txtCompKey.setText(	
    		}
    		else if(lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==2)  
    		{
    			
    		}*/
    		
/*    		txtCompKey.getDocument().removeDocumentListener(documentHandlerKey);
    		ret = editor.getCellEditorValue(); 
    	}*/

        // calculation, in editors
/*        if(dbFieldMany!=null && dbFieldMany.getWhenSetThenCalculateField()!=-1 && ret!=null && !ret.toString().equalsIgnoreCase(""))
        {
        try
        {	
        	int[] cells = dbFieldMany.getCalculationInputFields();
        	String calculation =  dbFieldMany.getCalculation();
        	String val = "";
        	  ResultSet rs;
        	  if(cells.length == 1)
        	  {
        	  	calculation= calculation+txtCompKey.getText();
        	  }
        	  else if (cells.length ==2)
        	  {
        	  	if(cells[0]==this.column)
        	  	{
        	  		calculation= "SELECT "+ret+calculation+table.getValueAt(row,cells[1]);
        	  	}
        	  	else if(cells[1]==this.column)
        	  	{
        	  		calculation= "SELECT "+table.getValueAt(row,cells[0])+calculation+ret;
        	  	}
        	  	else
        	  	{
        	  	   calculation= "SELECT "+table.getValueAt(row,cells[0])+calculation+table.getValueAt(row,cells[1]);	
        	  	}
        	  }
        	  else
        	  {
        	  	System.out.println("TableCellEditorLookupTwoA.getCellEditorValue unsupported no of cells "+cells.length);
        	  }
              rs = db.retrieveResultSet(calculation);
              if (rs.first())
              {
                 rs = db.retrieveRow(rs, 1);// go to the only row  
                 val = rs.getString(1);// get field data	         	
              }              
           table.setValueAt(val, row, dbFieldMany.getWhenSetThenCalculateField()); 
           
             System.out.println("TableCellEditorLookupTwoA.getCellEditorValue val === "+val+" calculation "+calculation);

           
           db.releaseConnectionRs();

         }//try
         catch ( SQLException sqlex)
         {
             System.out.println("error:TableCellEditorLookupTwoA.getCellEditorValue():"+sqlex.getMessage());
         }
                 		
        }
*/



        return ret;      	    	
        //return editor.getCellEditorValue(); 
    } 
 
    public boolean isCellEditable(EventObject anEvent){ 
        return editor.isCellEditable(anEvent); 
    } 
 
    public boolean shouldSelectCell(EventObject anEvent){ 
        return editor.shouldSelectCell(anEvent); 
    } 
 
    public boolean stopCellEditing(){ 
        return editor.stopCellEditing(); 
    } 
 
    public void cancelCellEditing(){ 
        editor.cancelCellEditing(); 
    } 
 
    public void addCellEditorListener(CellEditorListener l){ 
        editor.addCellEditorListener(l); 
    } 
 
    public void removeCellEditorListener(CellEditorListener l){ 
        editor.removeCellEditorListener(l); 
    } 
 
    public final void actionPerformed(ActionEvent e){ 
        editor.cancelCellEditing(); 
        editCell(table, row, column); 
    } 
 

    public void editCell(JTable table, int row, int column)
    { 

            
     /* PanelOneDataManyRecData = new PanelOneDataManyRecData(frame);
      String value = txtCompKey.getText();//(String)table.getValueAt(row, column);
      String selected = PanelOneDataManyRecData.displayDialogLookUp(value,foreignTable);
      //System.out.println("TableCellEditorLookupTwoA editCell ret "+PanelOneDataManyRecData.getColDescriptionValue());
      if (!selected.equalsIgnoreCase(""))// if cancel clicked do not change
      {
      	 //System.out.println("TableCellEditorLookupTwoA.editCell  "+selected+" "+column);
        if ((foreignTable!= null) && (lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==2))//(!rsmd.getTableName(i).equalsIgnoreCase(foreignTable)))
        {
            table.setValueAt(selected, row, column);
        }      	 
        else if ((foreignTable!= null) && (lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==1))//(!rsmd.getTableName(i).equalsIgnoreCase(foreignTable)))
        {
        	 txtDescription.setText(utilsPanelReport.getLookupValue(foreignTable,value,1));
        	 table.setValueAt(selected, row, column);//panelOneDataManyRecData.getColDescriptionValue(), row, column);
        }   
        else
        {
        	table.setValueAt(selected, row, column);
        }       
      
      }*/
      
      displayDialogLookup();
      
      table.requestFocus();
      //System.out.println("TableCellEditorLookupTwoA.editCell "+row);
      //table.setRowSelectionInterval(row,row);
    
    } 
    
    private int getCellEditedRow()
    {
    	return row;
    }
 
     private int getCellEditedCol()
    {
    	return column;
    }

   private int getColumnWidth(JTable table , int column)
   {
   	     TableColumnModel colModel = table.getColumnModel();
         TableColumn col = colModel.getColumn(column);
         TableCellRenderer hRenderer = col.getHeaderRenderer();
         Component comp;
         hRenderer = table.getTableHeader().getDefaultRenderer();
         comp = hRenderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
        	
   	return comp.getPreferredSize().width; 
   }
   
   
   // also in TableModelResultSet as setLookupText
 /* private String getLookupText(int key, String foreignTable)
  {   // called from setValue when a lookup column changed
  	  
  	  String strRet = "";
  	  
  	     try
  	     {
                 ResultSet rsForeign;
                String foreignQuery="";
                String lookupText="";
                
                foreignQuery = "SELECT * FROM "+foreignTable+" WHERE "+lookUp.getLookUpKey(foreignTable)+" = "+key;     

                 if (VariablesGlobal.globalShowSQL)
                 {
                     System.out.println("tableModelRS.setLookupText query"+foreignQuery);                 	
                 }

                rsForeign = db.retrieveResultSet(foreignQuery);
                if (rsForeign.next())// if key there is the record with the typed key
                { 
                   //System.out.println("tableModelRS.setLookupText null rs");
                   rsForeign = db.retrieveRow(rsForeign, 1);// go to the only row               
                   //System.out.println("tableModelRS.setLookupText - r "+row+" foreignTable "+foreignTable);
                   lookupText = rsForeign.getString(lookUp.getLookUpFieldIndex(foreignTable)) ;// get field data

                   //setValueAt(lookupText, row, column+1);
                   strRet = lookupText;
                 }
                 else
                 {
                   //setValueAt("", row, column+1);
                   strRet="";
                 }
         }
         catch (SQLException e)
         {
             System.out.println("TableModelRS.setLookupText "+e);//e.printStackTrace();
         }
         
         return strRet;
  }   */
  
  /*private String getLookupText(String key, String foreignTable)
  {   // called from setValue when a lookup column changed
  	     String lookupText="";
  	     
  	     //int row = 
  	    // int column = 
  	     try
  	     {
                 ResultSet rsForeign;
                String foreignQuery="";
                
                
                foreignQuery = "SELECT * FROM "+foreignTable+" WHERE "+lookUp.getLookUpKey(foreignTable)+" = "+key;     

                 if (VariablesGlobal.globalShowSQL)
                 {
                     System.out.println("tableModelRS.setLookupText query"+foreignQuery);                 	
                 }

                rsForeign = db.retrieveResultSet(foreignQuery);
                if (rsForeign.next())// if key there is the record with the typed key
                { 
                   //System.out.println("tableModelRS.setLookupText null rs");
                   rsForeign = db.retrieveRow(rsForeign, 1);// go to the only row               
                   //System.out.println("tableModelRS.setLookupText - r "+row+" foreignTable "+foreignTable);
                   lookupText = rsForeign.getString(lookUp.getLookUpFieldIndex(foreignTable)) ;// get field data

                   //setValueAt(lookupText, row, column+1);
                 }
                 else
                 {
                   //setValueAt("", row, column+1);
                 }
         }
         catch (SQLException e)
         {
             System.out.println("TableModelRS.setLookupText "+e);//e.printStackTrace();
         }
         
         return lookupText;
  }  */ 


  /* private void displayDialogEdit()
   { 
      // Component comp =this.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
       //System.out.println(comp.getClass());
    	customEditorButton2.setCursor(new Cursor(Cursor.WAIT_CURSOR));

   	   
   	   String selectedKeyValue ="";

   	     selectedKeyValue = txtCompKey.getText();//value = (String)table.getValueAt(getCellEditedRow(), getCellEditedCol());
     
     
     if(!selectedKeyValue.equals(""))
     {
       
       
       
       //PanelOneDataManyRecData PanelOneDataManyRecData;
       PanelEditOneDataRec  panelEditOneDataRec = new PanelEditOneDataRec(frame);
       
       
       
                 
        //System.out.println("panelOneDataManyRec primKeyValue "+selectedKeyValue);
        LookUpMgt lookUp = new LookUpMgt();
        
        String[] fieldsOnTitle=lookUp.getFieldsOnTitle(foreignTable);
         String[] fieldsOnTitleCaption=lookUp.getFieldsOnTitleCaption(foreignTable);
        String editTitle=lookUp.getStrOfOne(foreignTable);
        EntityPanel[] entityPanel = lookUp.getEntityPanel(foreignTable);
        String primKey = lookUp.getLookUpKey(foreignTable);
        ImageIcon iconLU=lookUp.getIcon(foreignTable);
        //String query = lookUp.getQuery(foreignTable);
   //     panelEditOneDataRec.setEntity(entity, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,primKeyValue,primKeyDb,null,null,/*query,*/
   //     editTitle,ico,true,isNewRec,isNewRecFromCopy,true,categoryNodes, false);	
   
 /*       String query = "";
        if(lookUp.getQuerySubqueryWhere(foreignTable) !=null && !lookUp.getQuerySubqueryWhere(foreignTable).trim().equalsIgnoreCase("") )
        {
            query = lookUp.getQuery(foreignTable)+" "+lookUp.getQuerySubqueryWhere(foreignTable)+" AND "+foreignTable+"."+primKey+" LIKE "+selectedKeyValue;
        }
        else
        {
            query = lookUp.getQuery(foreignTable)+" WHERE "+foreignTable+"."+primKey+" LIKE "+selectedKeyValue;
        }        
        
  */      
//       panelEditOneDataRec.setEntity(foreignTable, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,selectedKeyValue,
//               primKey,/*primKeyDb*//*,null,null,*/query,editTitle,iconLU/*,true*/,false,false,true,null, false, panelManagement);//,entityReport);	
/*   
    	

        panelEditOneDataRec.setVisible(true);
        
        DialogMulti dlg = new DialogMulti(frame);
        dlg.setEntity(panelEditOneDataRec,PANEL_TYPE_EDITONEDATAONEREC, "επεξεργασία "+editTitle,true);
        dlg.display();       
       
       
    }   
     
       customEditorButton2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

   }*/
  public String getCellSelectedValue()
  {
      
   	     String value;
   	     value = txtCompKey.getText();//value = (String)table.getValueAt(getCellEditedRow(), getCellEditedCol());
             
             return value;
      
  }
   
   private void displayDialogLookup()
   {        
          // cursor not working
   	     customEditorButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));

   	     String value;
   	     value = txtCompKey.getText();//value = (String)table.getValueAt(getCellEditedRow(), getCellEditedCol());

                //System.out.println("TableCellEditorLookupTwoA.ActionShowDialogLookUp "+value+" "+luname+" "+getCellEditedRow()+" "+getCellEditedCol());
              panelOneDataManyRecData = new PanelOneDataManyRecData(frame);
            System.out.println("TableCellEditorLookupTwoA.displayDialogLookup   value:"+value+"    dbFields:"+dbFields);
              String selected =  panelOneDataManyRecData.displayDialogLookUp(value,luname,foreignTable,dbFields);
         if (!selected.equalsIgnoreCase(""))// if cancel clicked do not change
         {
         	//System.out.println("TableCellEditorLookupTwoA.displayDialogLookup "+selected);
            table.setValueAt(selected, row, column);
            //System.out.println("TableCellEditorLookupTwoA.ActionShowDialogLookUp "+value+" "+getCellEditedRow()+" "+getCellEditedCol());
         }
         //txtDescription.setText(getLookupText(tbFinal.getText(),foreignTable,row,column));
        // table.requestFocus();

        customEditorButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         
   }
  
   //exists modificated in PanelOneDataOneRecData and TableCellEditorLookupTwoA
   public void displayWindowLookUp(String foreignTable, int noOfComp)
   { 
   	   int noOfCharsForOpenningWindow=1;
   	  int noOfCharsChangedToShowWindow=4;
   	  
   	 if(txtDescription.getText().length()>=noOfCharsForOpenningWindow || txtCompKey.getText().length()>=noOfCharsForOpenningWindow )
   	 {
   	   

          
          //winLookUp = new WindowLookUp(frame);
        	 //System.out.println("isVisibleWinLookUpCheck ifisFalse "+winLookUpCheck.isVisible());
  	 /*      ArrayList  arrList = new ArrayList();
           arrList.add("1 first");
           arrList.add("2 second");
           arrList.add("3 third");
           arrList.add("4 forth");
           arrList.add("5 fifth");*/
            
            
            //System.out.println("TableCellEditorLookupTwoAB.displayWindowLookUp "+txtDescription.getText()+" "+txtCompKey.getText().length()+" "+!txtCompKey.getText().trim().equalsIgnoreCase(""));
            
      if(txtDescription.getText().length() <= noOfCharsChangedToShowWindow && !txtCompKey.getText().trim().equalsIgnoreCase(""))     
      {
           System.out.println("TableCellEditorLookupTwoA.displayWindowLookUp "+txtCompKey.getText()+"   "+txtDescription.getText());
           //textFieldReturn.setText(txtCompKey.getText());
           winLookUp.setEntity(txtCompKey,txtCompKey.getText(),luname,foreignTable,lookUp, null,2,2, lookUp.getIntValidationColumn(luname),
                   lookUp.getIntValidationType(luname),dbFields,noOfCol,/*fieldTxts*/null,WINDOWLOOKUP_IS_CALLED_IN_TABLE,intTableOfParentDBFields); 
   	   	
           
           //System.out.println("TableCellEditorLookupTwoA.displayWindowLookUp "+q);
           
        //   winLookUp.setEntity(txtCompKey,q,lookUp.getLookUpKey(foreignTable), null,2,2, lookUp.getIntValidationColumn(foreignTable), lookUp.getIntValidationType(foreignTable)); 
      
/*           final JTextComponent tbFinal = tb;
       	            tbFinal.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                    {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                           winLookUp.close();
                        }

                    	public void focusGained(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                            
                            //textArea.remove(lblIcoAttention);
                        }
                    });          
        
           final JTextComponent tb2Final = tb2;
       	            tb2Final.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                    {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                           winLookUp.close();
                        }

                    	public void focusGained(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                            
                            //textArea.remove(lblIcoAttention);
                        }
                    });

         if(lookUp.getLookUpField2(foreignTable)!=null)
   	     {
           final JTextComponent tb3Final = tb3;
       	            tb3Final.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                    {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                           winLookUp.close();
                        }

                    	public void focusGained(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                            
                            //textArea.remove(lblIcoAttention);
                        }
                    });                          
   	     }*/
   	     
   	    
  
      }    	     
   	 }// if
   	 else
   	 {
   	 	winLookUp.close();
   	 	
   	 }   	 
   } 
  
  //exists modificated in PanelOneDataOneRecData and TableCellEditorLookupTwoA
  private class DocumentHandler implements DocumentListener
  { // if data changed in txt
  
    int no=0;
    String classtype="";
    String foreignTable = null;
    public DocumentHandler( int noIn, String classtypeIn, String foreignTableIn )
    {
        no = noIn;
        classtype=classtypeIn;
        foreignTable=foreignTableIn;
    }
        
    public void insertUpdate(DocumentEvent e)
    {   

          hasDataChanged=true;
           JTextField t = new JTextField();
         
         //System.out.println("PanelODORD.DocumentHandler.insertUpdate hasDataChanged "+hasDataChanged+" "+no+" "+classtype);
         
         if(classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
         {
           //System.out.println("PanelODORD.DocumentHandler.insertUpdate hasDataChanged "+hasDataChanged+" "+no+" "+classtype);

           /*JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(no-1);// -1 because i starts from 1
           textEditFormatedDate.findDateNameInText();// to change day name in label when different date is typed 
           textEditFormatedDate.setBackground(t.getBackground());*/
         } 
         else //if(colWidth>40)
         {
  /*       	if(fieldValidation[no-1]==FIELD_VALIDATION_AFM)
         	{
         		JTextComponent ta =(JTextComponent)fieldTxts.get(no-1);
         		//System.out.println("PanelODORD.DocumentHandler.insertUpdate AFM");
         		ta.setBackground(t.getBackground());  
         		
         		JLabel lb =(JLabel)listLabelValid.get(no-1);	
         		
         		if(utilsString.checkGreekAFM(ta.getText()))	
         		{
         			//lb.setText("correct");
         			lb.setIcon(ICO_OK16);
         			
         		}
         		else
         		{
         			//lb.setText("faulty");
         			lb.setIcon(ICO_CANCEL16);
         		}
         		
         	     
         	            		
         	}
         	else
         	{*/
         	if(no==1)
	        {
         		  //JTextComponent ta =(JTextComponent)fieldTxts.get(no-1);
         	      // ta.setBackground(t.getBackground());	        	
         	      txtDescription.setBackground(t.getBackground());

   	     	     /* String text = txtCompKey.getText();   	     	      
         	      if(!text.trim().equalsIgnoreCase(""))
         	      {
         	         String val =utilsPanelReport.getLookupValue(foreignTable,text,1);	
         	         	System.out.println("PanelODORD.DocumentHandler.insertUpdate no"+no+" "+val);
         	         	txtDescription.setText(val);
         	         	
         	      }*/    
         	      

         	      
	        }
   	        else if(no==0)
   	        {   
   	     	      txtCompKey.setBackground(t.getBackground());


   	     	      String text = txtCompKey.getText();   	     	      
         	      if(!text.trim().equalsIgnoreCase(""))
         	      {         	      	
                          
         	         String val =utilsPanelReport.getLookupValue(luname,foreignTable,text,1,true,fieldVariableFromPreField,"",entity,null/*dbFieldsAll*/,null/*fieldTxts*/);
         	         	//System.out.println("PanelODORD.DocumentHandler.insertUpdate no"+no+" "+val);
         	         	txtDescription.setText(val);
         	         	
         	      }    
   	     	      
   	        }	        

 //        	}
         	
         	if(luname!=null)
         	{
         		displayWindowLookUp(luname,no);
         		

 	     	               		
         		
         	}

       	
         	
            //System.out.println("  change "+luname); 
         	//ta.remove(lblIcoAttention);
         }
        
     
        //System.out.println("PanelODORD.DocumentHandler hasDataChanged "+hasDataChanged);
    }

    public void removeUpdate(DocumentEvent e)
    {

        	 hasDataChanged=true;
        JTextField t = new JTextField();
         if(classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
         {
            //System.out.println("PanelODORD.DocumentHandler.removeUpdate hasDataChanged "+hasDataChanged+" "+no+" "+classtype);
	
          /* JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(no-1);// -1 because i starts from 1
           textEditFormatedDate.findDateNameInText();// to change day name in label when different date is typed 
           textEditFormatedDate.setBackground(t.getBackground());*/
         }
         else
         {
/*         	if(fieldValidation[no-1]==FIELD_VALIDATION_AFM)
         	{
         		JTextComponent ta =(JTextComponent)fieldTxts.get(no-1);
         		//System.out.println("PanelODORD.DocumentHandler.insertUpdate AFM");
         		ta.setBackground(t.getBackground());   
         			
         		JLabel lb =(JLabel)listLabelValid.get(no-1);	
         		
         		if(utilsString.checkGreekAFM(ta.getText()))	
         		{
         			//lb.setText("correct");
         			lb.setIcon(ICO_OK16);
         		}
         		else
         		{
         			//lb.setText("faulty");
         			lb.setIcon(ICO_CANCEL16);
         		}
         	     
         	           		
         	}
         	else
         	{*/
         	if(no==1)
	        {
         		  //JTextComponent ta =(JTextComponent)fieldTxts.get(no-1);
         	      // ta.setBackground(t.getBackground());	        	
         	      txtDescription.setBackground(t.getBackground());
	        }
   	        else if(no==0)
   	        {   
   	     	      txtCompKey.setBackground(t.getBackground());
   	        }
         	
         	if(luname!=null)
         	{
         		displayWindowLookUp(luname,no);
         	}
         	
         	//System.out.println("  change "+luname); 
         }

    }

    public void changedUpdate(DocumentEvent e)
    {
 
           hasDataChanged=true;

    }

   /* private void edited(DocumentEvent e)
    {
        if (guiLoaded)
        { hasDataChanged=true;  }
    }*/
  }  
    
   class  ActionShowDialogLookUp extends AbstractAction                 
   {       
       
        public ActionShowDialogLookUp()
        {
        }
      	
    	public void actionPerformed(ActionEvent e)
      	{        

              displayDialogLookup();
        }    	
    }                



}
