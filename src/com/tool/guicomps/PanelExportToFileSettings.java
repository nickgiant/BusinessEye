// created 18-12-2009


package com.tool.guicomps;

import com.tool.gui.*;

import com.tool.jdbc.*;
import com.tool.model.*;
import com.tool.utils.*;

import javax.swing.border.TitledBorder;
import java.util.Vector;
import java.awt.event.*;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.File;

import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;

import java.sql.*;

  public class PanelExportToFileSettings extends JxPanel implements Constants
  {
  	EntityExportFileType[] entityExportFileType;
  	  private int colCount;
      private String[] colNames;
      private Class[] colClass;
      private Vector dataVector;
      //private PrintWriter printWriter;
      //private ResultSetMetaData rsmd;
      //private Database db= new Database();
  	  //private WindowWait wWaitTimer;
  	
  private	JTextField txtColStartString;
  private	JTextField txtColMiddleString;
  private	JTextField txtColEndString;
  	
  private	JCheckBox chkHead;
  private	JCheckBox chkFoot;
  private	JCheckBox chkFilters;
  	
  private	JxPanel panelChooseFileType;
  private	JxPanel panelExportSettingsColString;
  private	JComboBox cmbFileType;
  	
    public PanelExportToFileSettings(JFrame frame)
    {
    	
    	UtilsMiscEntities utilsMiscEntities = new UtilsMiscEntities();
    	 entityExportFileType = utilsMiscEntities.getExportToFileEntities();    	
    	
    	String[] strExportFileType = new String[entityExportFileType.length];
    	for(int e=0;e<entityExportFileType.length;e++)
    	{
    		strExportFileType[e]=entityExportFileType[e].getCaption() +" ("+entityExportFileType[e].getExtension() +")";
    	}
       
       panelChooseFileType = new JxPanel();
       panelChooseFileType.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2));
       JLabel lblFileType = new JLabel("επιλογή τύπου αρχείου");
       panelChooseFileType.add(lblFileType);
       cmbFileType = new JComboBox(strExportFileType);
       
       cmbFileType.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	setSelectedFileType();
          }
       });       
       panelChooseFileType.add(cmbFileType);
    	
       JxPanel panelMain = new JxPanel();
       //panelMain.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));
       panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));
       //panelMain.setLayout(new GridLayout(0,1));



    	
       panelExportSettingsColString = new JxPanel();
       panelExportSettingsColString.setBorder(new TitledBorder("διαχωριστικά στηλών"));
       panelExportSettingsColString.setLayout(new GridLayout(0,2));//.setLayout(new BoxLayout(panelExportSettingsColString, BoxLayout.PAGE_AXIS));//(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 6));
       
       JLabel lblColStartString = new JLabel("διαχωριστικό στην αρχή");
       panelExportSettingsColString.add(lblColStartString);
       txtColStartString = new JTextField(3);
       panelExportSettingsColString.add(txtColStartString);
       
       JLabel lblColMiddleString = new JLabel("διαχωριστικό στη μέση");
       panelExportSettingsColString.add(lblColMiddleString);
       txtColMiddleString = new JTextField(3);
       panelExportSettingsColString.add(txtColMiddleString);
       
       JLabel lblColEndString = new JLabel("διαχωριστικό στο τέλος");
       panelExportSettingsColString.add(lblColEndString);
       txtColEndString = new JTextField(3);              
       panelExportSettingsColString.add(txtColEndString);
       
       
       JxPanel panelExportSettingsHeadFoot = new JxPanel();
       panelExportSettingsHeadFoot.setBorder(new TitledBorder("εμφάνιση"));
       panelExportSettingsHeadFoot.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 6)); 
       	
       	chkHead = new JCheckBox("επικεφαλίδας");
       	chkHead.setOpaque(false);
       	panelExportSettingsHeadFoot.add(chkHead);
       	chkFoot = new JCheckBox("υποσέλιδου");
       	chkFoot.setOpaque(false);
       	panelExportSettingsHeadFoot.add(chkFoot);       	      
       	chkFilters = new JCheckBox("φίλτρων");
       	chkFilters.setOpaque(false);
       	panelExportSettingsHeadFoot.add(chkFilters);        	
       	
       	JxPanel panelDirSave = new JxPanel();
       	panelDirSave.setBorder(new TitledBorder("αποθήκευση"));
       	panelDirSave.setLayout(new FlowLayout());
        JLabel lblFilePath = new JLabel("διαδρομή");
        panelDirSave.add(lblFilePath);
        JTextField txtFilePath = new JTextField(22);
        //txtBtnDirSave = new JTextBoxWithEditButtons();
        JTextBoxWithEditButtons txtBtnDirSave = new JTextBoxWithEditButtons(txtFilePath, true ,ICON_TREEFOLDER_OPENED,null, false,null,null,2, frame,"" ,"",MONTH_DATE_ONLY);                  	
        panelDirSave.add(txtBtnDirSave);
        
        txtBtnDirSave.setText(System.getProperty("user.dir"));
       	
       	
       	
       panelMain.add(panelChooseFileType);
       panelMain.add(panelExportSettingsHeadFoot);	
       panelMain.add(panelExportSettingsColString);
       panelMain.add(panelDirSave);
       
       this.setLayout(new FlowLayout());
       this.add(panelMain);
       
       cmbFileType.setSelectedIndex(0);
    }
    
    public void setEntity(String typeName)
    {
    	
    	
    	
    	int typeIndex = -0;
    	for(int i=0;i<entityExportFileType.length;i++)
    	{
    		if(entityExportFileType[i].getName().equals(typeName))
    		{
    			typeIndex=i;
    		}
    	}   
    	
    	//setSelectedFileType(typeIndex);
    	cmbFileType.setSelectedIndex(typeIndex);
    	


       panelChooseFileType.setVisible(false);

                 	
    	
    }
    /*public void retrieveSuggestedSettings() // suggested from settings file
    {
    	


    	
    	txtColStartString.setText("|");
    	txtColMiddleString.setText("|");
    	txtColEndString.setText("|");
    	
    	chkHead.setSelected(true);
    	chkFoot.setSelected(true);
    }*/

   private void setSelectedFileType()
   {
             //JComboBox cmbFileType = (JComboBox)e.getSource();
             
             //System.out.println("PanelExportToFileSettings.setSelectedFileType "+typeIndex);
             
             int fileType = (int)cmbFileType.getSelectedIndex();
            

             
             
             String[] colSepStr = entityExportFileType[fileType].getColSeparationString();
             if(colSepStr == null)
             {
       	        txtColStartString.setText("");       	        
    	        txtColMiddleString.setText("");
    	        txtColEndString.setText("");  
    	        
    	        panelExportSettingsColString.setVisible(false);

             }
             else
             {
             	panelExportSettingsColString.setVisible(true); 
             		
       	        txtColStartString.setText(colSepStr[0]);
    	        txtColMiddleString.setText(colSepStr[1]);
    	        txtColEndString.setText(colSepStr[2]);  
    	        	

             }
             

    	boolean[] headFootExported = entityExportFileType[fileType].getHeadFootExported();
    	if(headFootExported == null)
    	{
      	   chkHead.setSelected(true);
    	   chkFoot.setSelected(true); 
    	   chkFilters.setSelected(true); 
    	}
    	else
    	{
      	   chkHead.setSelected(headFootExported[0]);
    	   chkFoot.setSelected(headFootExported[1]);  		
    	   chkFilters.setSelected(headFootExported[2]); 
    	}
    	

   }
   
    public void getSettings() // apply and export
    {
    	txtColStartString.getText();
    	txtColMiddleString.getText();
    	txtColEndString.getText();
    }    
    
    /*public void exportTo(int colCountIn, String[] colNamesIn, Class[] colClassIn, Vector dataVectorIn ,String type,  JFrame frame, String strOfMany)
    {
    	
    }*/
  }