// created 30-05-2009

package com.tool.gui;

  import com.tool.guicomps.*;
  import com.tool.utils.*;
  import com.tool.model.*;
  import com.tool.jdbc.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.event.*;

 public class PanelUpdateWithCriteria extends JxPanel implements Constants
{
    private JxPanel panelMain; 
    private JxPanel panelLines;
    private String[]fieldName;
    private  String[] fieldCaption;
    private   String table;
    private   String fieldToCalculate;
    private int linePanels =0;
    private JComboBox cmbScenario;
    private ArrayList listPanelLines;
    private boolean isNullify;
    private String tableForFilters;
    private String sqlUpdateWhere;
    
    public PanelUpdateWithCriteria(JFrame frame)
    {
        try {
            initialize(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private void initialize(JFrame frameIn)throws Exception
    {
    	listPanelLines = new ArrayList();
    	panelMain = new JxPanel(); 
    	panelMain.setLayout(new BorderLayout());
    	panelLines = new JxPanel(new GridLayout(0,1));//(new BoxLayout(panelLines, BoxLayout.PAGE_AXIS));
     
     FlowLayout fl = new FlowLayout();
    	       fl.setVgap(2);
    
        JLabel lblScenario = new JLabel("σενάριο");
    	cmbScenario = new JComboBox();
    	JButton  btnNewScenario = new JButton("νέο");
    	JButton  btnSaveScenario = new JButton("αποθήκευση");
    	btnSaveScenario.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {  
    	      getConfigForCriteriaUpdateLine(0);
    	    }
	    });    	
    	JButton  btnSaveAsScenario = new JButton("αποθήκευση ως");
    	JButton  btnRemScenario = new JButton("διαγραφή");
    	
    	JxPanel panelScenario = new JxPanel(fl);
    	panelScenario.add(lblScenario);
    	panelScenario.add(cmbScenario);
    	panelScenario.add(btnNewScenario);
    	panelScenario.add(btnSaveScenario);
    	panelScenario.add(btnSaveAsScenario);
    	panelScenario.add(btnRemScenario);
    	
    	JButtonForPanelDecorated  btnAdd = new JButtonForPanelDecorated("προσθήκη συνθήκης");
    	btnAdd.setIcon(ICO_ADD);
          btnAdd.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	            addCriteriaUpdateLine();
	        }
	      }); 
    	
    	JButtonForPanelDecorated  btnRemove = new JButtonForPanelDecorated("αφαίρεση τελευταίας συνθήκης");
    	btnRemove.setIcon(ICO_DELETE);
          btnRemove.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	            removeLastCriteriaUpdateLine();
	        }
	      });     	
    	
    	JxPanel panelButtonLines = new JxPanel(fl );
    	panelButtonLines.add(btnAdd);
    	panelButtonLines.add(btnRemove);
    	
    	
    	JxPanel panelButtons = new JxPanel(new GridLayout(0,1) );
    	//panelButtons.add(panelScenario);
    	panelButtons.add(panelButtonLines);
    	
    	panelMain.add(panelButtons, BorderLayout.PAGE_START);
    	panelMain.add(panelLines, BorderLayout.CENTER);
    	
    	
    	this.add(panelMain,BorderLayout.CENTER);
    	
    	loadScenarios();
    	
    }
    
    public int getCountOfCriteriaUpdateLines()
    {
    	return linePanels;
    }
    
    public void addCriteriaUpdateLine()
    {
    	linePanels=linePanels+1;
    	PanelCriteriaUpdateLine panelCriteriaUpdateLine = new PanelCriteriaUpdateLine();
    	panelLines.add(panelCriteriaUpdateLine);
    	listPanelLines.add(panelCriteriaUpdateLine);
    	panelLines.revalidate();
    }

    private void removeLastCriteriaUpdateLine()
    {
    	if((linePanels-1)>=0)
    	{
    	   panelLines.remove(linePanels-1);
    	   listPanelLines.remove(listPanelLines.get(linePanels-1));
    	   panelLines.revalidate();    
    	   linePanels=linePanels-1;		
    		
    	}

    }    
 
    public EntityQuery[] getUpdateQueries()
    {
       
    	EntityQuery [] ret = null;

    	if(isNullify)
    	{
    		ret = new  EntityQuery[listPanelLines.size()];
              // ret[0] = new EntityQuery("UPDATE "+table+" SET "+fieldToCalculate+"= NULL",true,0,null,null,tableForFilters,"ενημερώσεις.","Η ενημέρωση δεν έγινε.");  		
    		
    	}
    	else
    	{    // executed before calculation in order to be 0 before calculation (if null cannot add)
    	   ret = new  EntityQuery[listPanelLines.size()+1];
    	    
    	   ret[0] = new EntityQuery("UPDATE "+table+" SET "+fieldToCalculate+" = 0 ",QUERY_UPDATE,null,null,tableForFilters,"ενημερώσεις με 0.","Η ενημέρωση δεν έγινε.");	
    		
    	  for(int l =0; l<listPanelLines.size();l++)
    	  {
    		ret[l+1] = new EntityQuery(getConfigForCriteriaUpdateLine(l),QUERY_UPDATE,null,null,tableForFilters,"ενημερώσεις.","Η ενημέρωση δεν έγινε.");
    	  }
    	
    	}
    	

    	
    	return ret;
    }
 
 
    private String getConfigForCriteriaUpdateLine(int line)
    {
    	String ret = "";
        PanelCriteriaUpdateLine panelCriteriaUpdateLine = (PanelCriteriaUpdateLine)listPanelLines.get(line);
        
        ret= "UPDATE "+table+" SET "+panelCriteriaUpdateLine.getConfigSqlSet()+" WHERE "+panelCriteriaUpdateLine.getConfigSqlWhere();
        
        System.out.println("PanelUpdateWithCriteria.getConfigForCriteriaUpdateLine ("+line+") "+ret);
                
        return ret;
    }   
   
   private void loadScenarios()
   {
   	
   	 cmbScenario.addItem("1 scenario");
   	
   }
    
   public void setEntity(String[]fieldNameIn, String[] fieldCaptionIn, String tableIn, String sqlUpdateWhereIn, String fieldToCalculateIn,
   boolean isNullifyIn, String tableForFiltersIn)
   {
   	 fieldName = fieldNameIn;
   	 fieldCaption = fieldCaptionIn;
   	 table = tableIn;
   	 sqlUpdateWhere=sqlUpdateWhereIn;
   	 fieldToCalculate=fieldToCalculateIn;
   	isNullify=isNullifyIn;
   	tableForFilters=tableForFiltersIn;
   	  //addCriteriaUpdateLine();
   	
   }
   
   private class PanelCriteriaUpdateLine extends JxPanel
   {
   	        JComboBox cmbFields1 ;
            JComboBox cmbWhere ;
            JTextField txtField1A ;
            JTextField txtField1B; 
            JComboBox cmbCalc;
            JTextField txtField2A;     
            
            String[] arWhere = {  "μεγαλύτερο", "μικρότερο", "μεταξύ" };
            
            String[] arCalc = { "επί ποσοστό","σταθερο ποσό", "επί ποσό" };
            
                                          
   	    public PanelCriteriaUpdateLine()
        { 
           
            JLabel lblLine =new JLabel(linePanels+".");
            JLabel lblField1 =new JLabel("εαν πεδίο");
             cmbFields1 = new JComboBox(fieldCaption);
             cmbWhere = new JComboBox(arWhere);
             txtField1A = new JTextField(5);
              
             txtField1A.setText("0");
             txtField1B = new JTextField(5);
             txtField1B.setText("0");
            
            final JComboBox cmbWhereFinal=cmbWhere;
            final JTextField txtField1BFinal =txtField1B;
             cmbWhere.addActionListener(new ActionListener()
             {
	            public void actionPerformed(ActionEvent e) 
	            {  
	              if(cmbWhereFinal.getSelectedIndex()==2)
	              {
	              	  txtField1BFinal.setEditable(true);
	              	  txtField1BFinal.setText("0");
	              }
	              else
	              {
	              	  txtField1BFinal.setEditable(false);
	              	  txtField1BFinal.setText("");
	              }
	            }
	         });            
            cmbWhere.setSelectedIndex(0);
            
            
            //JLabel lblField2 =new JLabel("πεδίο");
            //JComboBox cmbFields2 = new JComboBox(fieldCaption);
            JLabel lblCalc =new JLabel("τότε");
             cmbCalc = new JComboBox(arCalc);
             txtField2A = new JTextField(5);
             txtField2A.setText("0");

    	       FlowLayout fl = new FlowLayout();
    	       fl.setVgap(2);
    	       setLayout(fl);
    	       
    	       add(lblLine);
    	       add(lblField1);
    	       add(cmbFields1);
    	       add(cmbWhere);
    	       add(txtField1A);
    	       add(txtField1B);
    	       //add(lblField2);
    	       //add(cmbFields2);
    	       add(lblCalc);
    	       add(cmbCalc);
    	       add(txtField2A);

        }
        
        public String getConfigSqlWhere()
        {
        	String ret = "";
        	// String[] arWhere = {  "greater", "less", "between" };
        	String selectedField = fieldName[cmbFields1.getSelectedIndex()];
            String fieldWhere ="";
            if(cmbWhere.getSelectedIndex()==0)
            {
            	fieldWhere=selectedField+" >= "+txtField1A.getText();
            }
            else if(cmbWhere.getSelectedIndex()==1)
            {
            	fieldWhere=selectedField+" <= "+txtField1A.getText();
            }
            else if(cmbWhere.getSelectedIndex()==2)
            {
            	fieldWhere=selectedField+" >= "+txtField1A.getText()+" AND "+selectedField+" <= "+txtField2A.getText();
            }         	
        	
        	
        	ret = fieldWhere;
        	
        	
        	return ret;
        }

        public String getConfigSqlSet()
        {
        	String ret = "";
        	String selectedField = fieldName[cmbFields1.getSelectedIndex()];
            
            // String[] arCalc = { "percentage","fixed amount", "multiplied amount" };
            String fieldCalc ="";
            if(cmbCalc.getSelectedIndex()==0)
            {
            	fieldCalc=selectedField+" * "+txtField2A.getText()+" /100";
            }
            else if(cmbCalc.getSelectedIndex()==1)
            {
            	fieldCalc=txtField2A.getText();
            }
            else if(cmbCalc.getSelectedIndex()==2)
            {
            	fieldCalc=selectedField+" * "+txtField2A.getText();
            }            
        	
        	ret = ""+fieldToCalculate+" = "+fieldToCalculate+" + "+fieldCalc;
        	
        	
        	return ret;
        }
      	
      	public void setConfig()
      	{
      		
      	}
    
   	
   }
}       