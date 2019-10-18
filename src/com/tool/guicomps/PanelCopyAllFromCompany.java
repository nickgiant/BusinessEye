/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.guicomps;

import com.tool.gui.*;
import com.tool.jdbc.*;
import com.tool.model.*;
import com.tool.utils.*;



import java.sql.*;

import com.tool.model.EntityDBFields;
import com.tool.model.EntityDataEsoExo;
import com.tool.model.EntityDataSerSales;
import com.tool.utils.DataTree;
import com.tool.utils.EventQueueTxtRightClick;
import com.tool.utils.GridLayoutVariable;
import com.tool.utils.UtilsGui;
import com.tool.utils.UtilsOS;
import com.tool.utils.VariablesGlobal;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.JFileChooser;


/*import jxl.Cell;
import jxl.CellType;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;*/

/**
 *
 * @author one
 */
public class PanelCopyAllFromCompany extends JxPanel implements Constants
{
    private JxPanel panelMain;    
    private Database db;
    private UtilsString utilsString;
    private PanelDataFilter pnlDataFilter;
    private JCheckBox chk1;
    private JCheckBox chk2;
    private JCheckBox chk3;
    private JCheckBox chk4;


public PanelCopyAllFromCompany()//(Frame parent, boolean modal)
    {
       initialize();
    }    
    


    private void initialize() 
    {
     utilsString = new UtilsString();
    panelMain = new JxPanel();
    panelMain.setLayout(new BorderLayout());
        
        
    
        EntityFilterSettings[] actionTypeCompanyErs = new EntityFilterSettings[1];       
        actionTypeCompanyErs[0]=new EntityFilterSettings("αντιγραφή από εταιρία","onelookup","string","","dbCompanyId","dbcompany",""/*"dbcompany"*/,"",0,-1,-1,FIELD_OBLIGATORY);
       
                pnlDataFilter = new PanelDataFilter(null);//frame);
                pnlDataFilter.setEntity(actionTypeCompanyErs, null,/*entityCalculate.getEntityGroupOfComps()*/ PANEL_FILTER_SEARCH, /*entityCalculate.getYearEnforce(),*/ null/*panelManagementIn*/);
                 
        JxPanel pnlSelections = new JxPanel();
        pnlSelections.setLayout(new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 1));
                

               GridLayoutVariable layoutGroup = null;
                layoutGroup = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 2);
          //-------------------------------      
          chk1 = new JCheckBox("αντιγραφή σειρών, τύπων παροχής, κινήσεων υπηρεσιών, κινήσεων συναλλασόμενων;");
          chk2 = new JCheckBox("αντιγραφή κατηγοριών υπηρεσιών;");
         //------------------------------
          //chk3 = new JCheckBox("αντιγραφή λογαριασμών;");
          chk3 = new JCheckBox("αντιγραφή τύπων εσοδων - εξόδων;");
          chk4 = new JCheckBox("αντιγραφή πρότυπων;");
          //-----------------------------
          
                //-----------------------------
   if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_TIMOLOGIA) || VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
   { 
                JxPanel panelGroup1 = new JxPanel();
                 panelGroup1.setOpaque(false);
                 panelGroup1.setLayout(layoutGroup);
                  //   CLR_PANEL_BORDER  
                  //TitledBorder lineBorder = javax.swing.BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, this.getBackground().darker()));
                  //TitledBorder titledBorder = new TitledBorder(javax.swing.BorderFactory.createTitledBorder(lineBorder, "δεδομένα προς αντιγραφή",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER));                // java.awt.Color.red 
                  TitledBorder titledBorder1 = new TitledBorder("δεδομένα "+PRODUCT_TIMOLOGIA_CAPTION_OF+" προς αντιγραφή");
                  panelGroup1.setBorder(titledBorder1);    

                 
                 chk1.setOpaque(false);
                 chk1.setSelected(false);
                 panelGroup1.add(chk1); 
                 
                 chk2.setOpaque(false);
                 chk2.setSelected(false);
                 panelGroup1.add(chk2);
                 pnlSelections.add(panelGroup1);
   }
   
                 //-------------------------
   if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_APLOGRAFIKA ) || VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
   {                 
                  JxPanel panelGroup2 = new JxPanel();
                 panelGroup2.setOpaque(false);
                 panelGroup2.setLayout(layoutGroup);
                TitledBorder titledBorder2 = new TitledBorder("δεδομένα "+PRODUCT_APLOGRAFIKA_CAPTION_OF+" προς αντιγραφή");
                 panelGroup2.setBorder(titledBorder2);    

                 
                 chk3.setOpaque(false);
                 chk3.setSelected(false);
                 panelGroup2.add(chk3); 
                
                 chk4.setOpaque(false);
                 chk4.setSelected(false);
                 panelGroup2.add(chk4);  
                                  
                 pnlSelections.add(panelGroup2);
   }
                 //-----------------------
                 
                JLabel lblCopy = new JLabel();
                lblCopy.setText("<html> εκτελείται αν δεν υπάρχουν ήδη καταχωρημένα δεδομένα !</html>");
                //lblCopy.setText("<html> - εκτελείται μόνο μία φορά για κάθε επιλογή ! <p> - εκτελείται αν δεν υπάρχουν καταχωρημένα δεδομένα !</html>");
                JButton btnCopy = new JButton("αντιγραφή");
        //btnCopy.setIcon(ICO_INSERT_COPY);
        //btnCopy.setFocusable(false);
        btnCopy.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	 
	        
                       copyData();

	        } 
	    });                
                   
    JxPanel pnlCopyButton = new JxPanel();
    pnlCopyButton.setLayout(new FlowLayout());
    pnlCopyButton.add(lblCopy);
    pnlCopyButton.add(btnCopy);
     
     panelMain.add(pnlDataFilter, BorderLayout.PAGE_START);
     panelMain.add(pnlSelections, BorderLayout.CENTER);
     panelMain.add(pnlCopyButton, BorderLayout.PAGE_END);
     this.setLayout(new BorderLayout());      
     this.add(panelMain, BorderLayout.PAGE_START); 
 
    }
    
    private String getTableColumnsOf(String table)
    {
        String ret = "";
        DatabaseMeta dbm = new DatabaseMeta();
        dbm.retrieveFields(table);
        String [] cols = dbm.getFieldNames();
        for(int c=1;c<=cols.length;c++)
        {
            ret = ret + cols[c-1];
            if(c==cols.length)
            {
                ret = ret;
            }
            else
            {
                ret= ret+", ";
            }
            
        }
        
        return ret;
    }

    private void copyData()
    {
        Database db =new Database();

                     if(pnlDataFilter.checkIfFieldsAreCompleted())
                    {
                        //if target tables have no data
                        
                        /*
                        INSERT INTO Table (foo, bar, Event_ID) SELECT foo, bar, "155"  FROM Table WHERE Event_ID = "120"
                        */
                        ArrayList<String> listInsertRec = new ArrayList();
                        //String table="";
                        //String compsubq = pnlDataFilter.getSubquery("");
                        //String field = compsubq.substring(1);// dont want to have . (dot)
                        //System.out.println("PanelCopyAllFromCompany.copyData compsubq:"+compsubq);
                        String valueDbCompany =pnlDataFilter.getFilterValue(0);
                        //String[] valueDbCompanySelected = {valueDbCompany};
                        if(chk1.isSelected())
                        {
                            String qInsertCopy1 = getQueryInsertFromCopyForTable("actionstock",valueDbCompany,"","");
                            listInsertRec.add(qInsertCopy1);
                            String qInsertCopy2 = getQueryInsertFromCopyForTable("actiontrader",valueDbCompany,"","");
                            listInsertRec.add(qInsertCopy2);
                            String qInsertCopy3 = getQueryInsertFromCopyForTable("actiontype",valueDbCompany,"","");
                            listInsertRec.add(qInsertCopy3);                             
                            String qInsertCopy4 = getQueryInsertFromCopyForTable("actionseries",valueDbCompany,"","");
                            listInsertRec.add(qInsertCopy4);
                            String qInsertCopy5 = getQueryInsertFromCopyForTable("printform",valueDbCompany,"","");
                            listInsertRec.add(qInsertCopy5);                            
                        }
                        if(chk2.isSelected())
                        {
                            String qInsertCopy1 = getQueryInsertFromCopyForTable("stockcat",valueDbCompany,"","");
                            listInsertRec.add(qInsertCopy1);                           
                        }                        
                        
                        if(chk3.isSelected())
                        {
                            String qInsertCopy1 = getQueryInsertFromCopyForTable("sxactiontype",valueDbCompany,"","");
                            listInsertRec.add(qInsertCopy1);                            
                        }  
                        if(chk4.isSelected())
                        {
                            String qInsertCopy1 = getQueryInsertFromCopyForTable("sxesoexoheader",valueDbCompany,"isTemplate","1");
                            listInsertRec.add(qInsertCopy1);                            
                            String qInsertCopy2 = getQueryInsertFromCopyForTable("sxesoexoline",valueDbCompany, "isTemplate","1");
                            listInsertRec.add(qInsertCopy2); 
                            
                        }          
                        
                        
                       try
                       {
                         db.transactionLoadConnection();
                         db.setTransactionAutoCommit(false);                           
                        for(int i = 0 ;i<listInsertRec.size();i++)
                        {
                            String query = listInsertRec.get(i);
                            //System.out.println("PanelCopyAllFromCompany.copyData "+query);
                            db.transactionUpdateQuery(query,"PanelCopyAllFromCompany.copyData",true);
                        }
                        db.transactionCommit();
                       }
                       catch (SQLException e)
                       {
                           System.out.println("  error PanelCopyAllFromCompany.copyData "+e.getErrorCode()+"  "+e.getMessage());
                           e.printStackTrace();
                       }
                       finally
                       {
                         db.transactionClose();            
                       }
                        
                    }       
        
        
    }
   
    private String getQueryInsertFromCopyForTable(String table,String selectedCompany,String additionalColumn, String additionalColumnValue)
    {
        String qInsertCopy = "";
        if(additionalColumn.equalsIgnoreCase("") && additionalColumnValue.equalsIgnoreCase(""))
        {
             String fields = getTableColumnsOf(table);
             String fieldsWithReplacedCompanyId = fields.replaceFirst("(?i)"+STRFIELD_DBCOMPANYID,VariablesGlobal.globalCompanyId);  // (?i) case insensitive
             qInsertCopy = "INSERT INTO "+table+" ("+fields+" ) SELECT "+fieldsWithReplacedCompanyId+" FROM "+table+" WHERE "+STRFIELD_DBCOMPANYID+" LIKE "+selectedCompany;
        }
        else
        {
             String fields = getTableColumnsOf(table);
             String fieldsWithReplacedCompanyId = fields.replaceFirst("(?i)"+STRFIELD_DBCOMPANYID,VariablesGlobal.globalCompanyId);  // (?i) case insensitive
             qInsertCopy = "INSERT INTO "+table+" ("+fields+" ) SELECT "+fieldsWithReplacedCompanyId+" FROM "+table+" WHERE "+STRFIELD_DBCOMPANYID+" LIKE "+selectedCompany+" AND "+additionalColumn+" LIKE "+additionalColumnValue;
           
        }
        return qInsertCopy;
        
    }
    
    public static void main(String args[])
    {
    	//STRFIELD_DBCOMPANYID
    	VariablesGlobal.globalCompanyId="1";
    	VariablesGlobal.globalCompanyName="no";
    	VariablesGlobal.globalDate="01-01-2009";
    	//VariablesGlobal.globalDeliveryId="1";
    	VariablesGlobal.globalDirConfiguration = System.getProperty("user.dir");
    	VariablesGlobal.globalUserId="user";
    	//VariablesGlobal.globalYear="2009";    	
    	
        // set right click on texts
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueTxtRightClick());     	
    	
    	UtilsGui utilsGui = new UtilsGui();
    	utilsGui.setLookAndFeel();
    	
    	JDialog dialogthis =new JDialog();
        dialogthis.setLayout(new BorderLayout());
          
        // IMPORT = 1;       EXPORT = 2;
        PanelCopyAllFromCompany pnl = new PanelCopyAllFromCompany();
    	//pnl.setEntity(1, null,null);
        dialogthis.add(pnl, BorderLayout.CENTER);

        dialogthis.pack();
        //pnl.locateOnCenterOfTheScreen();
    	//pnl.setCloseClick(dialogthis);
    	dialogthis.setVisible(true);
    }   
    
    
}






