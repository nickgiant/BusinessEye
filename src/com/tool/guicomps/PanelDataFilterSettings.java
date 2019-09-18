// created 20-06-2009

package com.tool.guicomps;

import com.tool.model.EntityFilterSettings;
import com.tool.gui.*;
import com.tool.jdbc.*;
import com.tool.utils.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;


 public class PanelDataFilterSettings extends JxPanel implements Constants
 {
 	
 	private JFrame frame;
 	private JxPanel panelFilterColumns;
 	private JxPanel panelMain;
 	private JComboBox cmbColumns;
 	
    public PanelDataFilterSettings(JFrame frame)
    {
        try
        {
            initialize(frame);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

	private void initialize(JFrame frameIn) throws Exception
    {   panelMain = new JxPanel();
        panelMain.setLayout(new BorderLayout());
    	panelFilterColumns = new JxPanel();
    	panelFilterColumns.setBorder(new TitledBorder("εμφάνιση φίλτρων")); 
    	//panelFilterColumns.setLayout(new BorderLayout());
    	panelFilterColumns.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 5));
    	
    	/*JLabel lblCols = new JLabel("πλήθος στηλών φίλτρων");
    	String[] cols = {"1","2","3","4","5"};
    	cmbColumns = new JComboBox(cols);
    	setCountOfFilterColumnsIndex(1);
    	JxPanel panelMiscSettings = new JxPanel();
    	panelMiscSettings.setBorder(new TitledBorder("ρυθμίσεις")); 
    	panelMiscSettings.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2));
    	panelMiscSettings.add(lblCols);
    	panelMiscSettings.add(cmbColumns);
    	
    	panelMain.add(panelMiscSettings, BorderLayout.PAGE_START);*/
    	panelMain.add(panelFilterColumns, BorderLayout.CENTER);
    	
    } 
    
    public void setEntity(EntityFilterSettings[] entityFilterSettingsIn)
    {  
      if(entityFilterSettingsIn!=null && entityFilterSettingsIn.length>0) 
      {
       for(int e=0;e<entityFilterSettingsIn.length;e++)
       {
       // FlowLayout fl = new FlowLayout();
       // fl.setAlignment(fl.LEFT);
       	//JPanel panelEFS = new JPanel(fl);
       	JCheckBox chkBox = new JCheckBox((e+1)+". "+entityFilterSettingsIn[e].caption);
       	chkBox.setOpaque(false);
       	chkBox.setSelected(true);
       	panelFilterColumns.add(chkBox);
       	
    	//JTextField txtField = new JTextField(12);
    	//panelFilterColumns.add(txtField); 
    	
    	//panelFilterColumns.add(panelEFS);
       }
      }

    }
    
    public void setCountOfFilterColumnsIndex(int index)
    {
    	
    	cmbColumns.setSelectedIndex(index);
    	
    }
    
    public JPanel getPanelFilterSettings()
    {
    /*	if(entityFilterSettings!=null)
    	{
    	  System.out.println("PanelDataFilter.getPanelFilters "+entityFilterSettings.length);
    	}*/
    	return panelMain;
    }    
}