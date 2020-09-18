package com.tool.guicomps;

import com.tool.model.LookUpMgt;
import com.tool.jdbc.*;
import com.tool.gui.*;
import com.tool.model.EntityDBFields;
import com.tool.utils.*;

import java.sql.*;
import javax.swing.border.BevelBorder;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class WindowLookUp extends JDialog implements Constants
{
    private JLabel lblMain;
    private JLabel lblBack;
    private JScrollPane listScrollPane;
    private JxPanel panelMain;
    private JPanelDecorated panelOuter;
    private JTabbedPane tabbedPane;
    
    private int location;
    private JTextComponent textField;
    private JTextComponent txtSearch;
    private JLabel lblSearch;
    private int typeOfSelectedText; //if type = indices(1), if type text(2)   
    //JTextField txtFilter;
    private ArrayList arrList;
    private String[] strArray;
    private JList list;
    //private JListWithCheckBoxManager  listWithCheckBox;
    //private String strBetweenIndexes = ",";    
    
    private JTableDec table;
    private JScrollPane tableScrollPane;
    private TableModelReadOnly tableModel;
    //private JPanel panelSearch;
    //private PanelDataFilter panelDataFilter;
    private UtilsTable utilsTable;
    private boolean showExtendedSummary;  
    private UtilsDouble uDouble;  
    private JLabel lblBottom;
    private UtilsPanelReport utilsPanelReport;
    private String query;
    //private Database db;
    private String fieldPK;
    //private boolean isSelected;
    private JButtonForPanelDecorated btnOk; 
    private UtilsString utilsString; 
    private String foreignTable;
    private LookUpMgt lookUp;
    private String strSet= "";  
    private int intValidationColumn;
    private int intValidationType;      
    //private String formGlobalTableToApply1;
    //private String fieldVariableFromPreField;
     private EntityDBFields[]   dbFieldsAll ;
    
      private int  col;
      private int calledWhenIsInTextFieldOrTable;      //        public static int WINDOWLOOKUP_IS_CALLED_IN_TEXTFIELD = 0;
                                                          //public static int WINDOWLOOKUP_IS_CALLED_IN_TABLE = 1;
    private ArrayList    fieldTxts;
    private int intTableOfParentDBFields;
    public WindowLookUp(JFrame frame)
    {
    	super(frame);
	    //System.out.println("WindowLookUp frame "+frame);
	    uDouble=new UtilsDouble();
            uDouble = VariablesGlobal.globalUtilsDouble;
    	utilsTable=new UtilsTable();
    	//panelDataFilter = new PanelDataFilter(frame);
        utilsPanelReport = new UtilsPanelReport();
        utilsString = new UtilsString();
    	//db= new Database();

       //setFocusable(true);
    	
    	panelMain = new JxPanel(new BorderLayout());
    	panelOuter = new JPanelDecorated(new BorderLayout());
    	//panelOuter.setBorder(new BevelBorder(BevelBorder.RAISED));
    	panelOuter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	//panelOuter.setBorder(UIManager.getBorder("TextField.border")); 
  
    	panelMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));       

       JxPanel panelTop = new JxPanel();
       FlowLayout fl = new FlowLayout();
       fl.setHgap(1);
       fl.setVgap(1);
       panelTop.setLayout(fl);
 //      panelSearch = new JPanel();  
//       toolBarData = new ToolBarData();
//       toolBarData.setFocusable(false);
//       toolBarData.setFloatable(false);
       
   
//       panelSearch.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
//       panelSearch.setAlignmentY(10);
       //panelSearch.setPreferredSize(new Dimension(getWidth(), 22));
        txtSearch = new JTextField(12);
        txtSearch.getDocument().addDocumentListener(new DocumentHandler());
        lblSearch =new JLabel("εύρεση");
        panelTop.add(lblSearch);
        panelTop.add(txtSearch);
        	
       // JButton btnSearch = new JButton("<html><b>Α</b>ναζήτηση</html>");
        /*btnSearch.setFocusable(false) ;
        btnSearch.setMnemonic(KeyEvent.VK_A);          
        btnSearch.setIcon(ICO_FIND16);       
        btnSearch.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
	           filter();
	        } 
	    });
       
        panelSearch.add(panelDataFilter.getPanelFilters());
        panelSearch.add(btnSearch);
*/        //panelSearch.setBackground(Color.cyan);

       

       table = new JTableDec();
    	tableModel= new TableModelReadOnly();


        // not delete it:  cannot format given object as a number
        TableCellRendererDefault tcr = new TableCellRendererDefault();
        TableCellRendererDouble tcrDouble = new TableCellRendererDouble(uDouble);
        TableCellRendererInteger tcrInteger = new TableCellRendererInteger();
        TableCellRendererDate tcrDate = new TableCellRendererDate();
        TableCellRendererBoolean tcrBoolean = new TableCellRendererBoolean();
        //TableCellRendererCheckBox tcrBoolean = new TableCellRendererCheckBox();
        
        //tcrDouble.getDoubleSettingsFromFile();
        tcrDate.pushUtilsDateToReadFromFile();
        
        // see also PrintTable
        table.setDefaultRenderer(Object.class, tcr);
        table.setDefaultRenderer(Integer.class, tcrInteger);
        table.setDefaultRenderer(Number.class, tcrDouble);
        table.setDefaultRenderer(Double.class, tcrDouble); 
        table.setDefaultRenderer(java.util.Date.class, tcrDate);
        table.setDefaultRenderer(Boolean.class,  tcrBoolean);        	
        //table.setDefaultRenderer(Boolean.class, tcrBoolean);
        
        //table.getColumn(0).setCellRenderer(tcrBoolean);
        //table.getColumn(0).setCellEditor(rowEditor);
        
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTableDec.AUTO_RESIZE_OFF);
        table.setGridColor(CLR_TABLE_GRID);

        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//.SINGLE_SELECTION);// select a single row only  
        table.addFocusListener( new FocusListener()
        {
              public void focusGained(FocusEvent e)
              {
              	if(table.getRowCount()>0)
              	{
              		if(table.getSelectedRow()==-1)
              		{
              		   table.setRowSelectionInterval(0,0);		
              		}   
              	}
                 //displayMessage("Focus gained", e);
              }

              public void focusLost(FocusEvent e)
              {
                   //displayMessage("Focus lost", e);
              }
	
        });

         utilsTable.setTableExitNavigationKeys(table); 
       	 utilsTable.setTableNavigationKeys(table); 

        table.setModel(tableModel);       
       
       panelOuter.add(panelMain ,BorderLayout.CENTER);
       
       list = new JList();

       //panelMain.add(txtFilter, BorderLayout.PAGE_START);
    //   panelMain.add(list, BorderLayout.CENTER);

         tableScrollPane = new JScrollPane();

        Action actionClose = new ActionClose();
        panelMain.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        panelMain.getActionMap().put("close", actionClose);        
       Action actionSet = new ActionSet();
        panelMain.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5 ,0), "set"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        panelMain.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER ,0), "set"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        panelMain.getActionMap().put("set", actionSet); 


       btnOk = new JButtonForPanelDecorated();
       	btnOk.setText("<html>ΟΚ <b>F5</b></html>");
       btnOk.setIcon(ICO_OK16);
       btnOk.setFocusable(false) ;
       btnOk.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
           getSelectedText();
       }
      });
       	
       JButtonForPanelDecorated btnCancel = new JButtonForPanelDecorated();
       	btnCancel.setText("<html>άκυρο <b>esc</b></html>");
       btnCancel.setIcon(ICO_CANCEL16);
       btnCancel.setFocusable(false) ;
       btnCancel.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
           close();
       }
      });
      
       JxPanel panelBtns = new JxPanel(); 
       panelBtns.add(btnOk) ;
       panelBtns.add(btnCancel) ;
       
       lblBottom = new JLabel();
       JxPanel panelBottom =new JxPanel();
       panelBottom.setLayout(new BorderLayout());
       panelBottom.add(tableScrollPane, BorderLayout.CENTER);      
       panelBottom.add(lblBottom, BorderLayout.PAGE_END);
       //        panelTop.add(toolBarData,BorderLayout.CENTER); 
       //        panelTop.add(panelSearch,BorderLayout.PAGE_END);  
                     
        getContentPane().add(panelOuter, BorderLayout.CENTER);
        panelMain.add(panelTop, BorderLayout.PAGE_START);
        panelMain.add(panelBottom, BorderLayout.CENTER);
        panelMain.add(panelBtns, BorderLayout.PAGE_END);
        
       //this.setAlwaysOnTop(true);
    }
    
    // for table,
   /* private void setSelectedRow()
    {
    	String text ="";
    	text = textField.getText();
    	
    	ResultSet rs = db.retrieveResultSet(query);
    	ResultSetMetaData rsmd = db.retrieveRSMetaData(query);
    	
    	int selRow = utilsPanelReport.getRowForPrimKey(rs, rsmd,fieldPK,text);
    	//System.out.println(" setSelectedRow "+selRow);
    	if (table.getRowCount()>0)
    	{
    	   table.setRowSelectionInterval(selRow,selRow);	
    	}
    	
    	
    	db.releaseConnectionRs();
    	db.releaseConnectionRsmd();
    } */
    
    
    private void filter(String lookupText)
    {
    	
   	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
   	  	  
   	  
       fieldPK=lookUp.getLookUpKey(foreignTable);
    	/*int intValidationColumn = lookUp.getIntValidationColumn(foreignTable);
    	int intValidationType = lookUp.getIntValidationType(foreignTable);*/  	  

       lblSearch.setText(lookUp.getLookUpFieldLabel(foreignTable));
        String queryLookUpWhere = lookUp.getQuerySubqueryWhere(foreignTable);
        String queryLookUpWhereForFormVariable = lookUp.getQueryWhereForFormVariable(foreignTable);
        
   	   String strWhere = "";
   	   String strEnd = "";
           
   	   if(utilsString.hasQueryWhere(queryLookUpWhere))
   	   {
   	   	  int len = lookUp.getLookUpField(foreignTable).length;
   	   	  if(len>1 || (lookUp.getLookUpField2(foreignTable)!=null && !lookUp.getLookUpField2(foreignTable).equals("") || lookUp.getLookUpField3(foreignTable)!=null && !lookUp.getLookUpField3(foreignTable).equals("") ))
   	   	  {
   	   	  	strWhere ="AND(";
   	   	  	strEnd=")";
   	   	  }
   	   	  else
   	   	  {
   	   	  	strWhere ="AND";
   	   	  }
   	   }
   	   else
   	   {
   	   	 int len = lookUp.getLookUpField(foreignTable).length;
   	   	  if(len>1 || (lookUp.getLookUpField2(foreignTable)!=null && !lookUp.getLookUpField2(foreignTable).equals("") || lookUp.getLookUpField3(foreignTable)!=null && !lookUp.getLookUpField3(foreignTable).equals("")))
   	   	  {   	   	
   	   	     strWhere = "WHERE(";
   	   	     strEnd=")";
   	   	  }
   	   	  else
   	   	  {
   	   	  	strWhere = "WHERE";
   	   	  }
   	   }
           
    	   String strWhereForFormVariable = "";
   	   String strEndForFormVariable = "";
           
     	   if(utilsString.hasQueryWhere(queryLookUpWhereForFormVariable))
   	   {
   	   	  int len = lookUp.getLookUpField(foreignTable).length;
   	   	  if(len>1 || (lookUp.getLookUpField2(foreignTable)!=null && !lookUp.getLookUpField2(foreignTable).equals("") || lookUp.getLookUpField3(foreignTable)!=null && !lookUp.getLookUpField3(foreignTable).equals("") ))
   	   	  {
   	   	  	strWhereForFormVariable ="AND(";
   	   	  	strEndForFormVariable=")";
   	   	  }
   	   	  else
   	   	  {
   	   	  	strWhereForFormVariable ="AND";
   	   	  }
   	   }
   	   else
   	   {
   	   	 int len = lookUp.getLookUpField(foreignTable).length;
   	   	  if(len>1 || (lookUp.getLookUpField2(foreignTable)!=null && !lookUp.getLookUpField2(foreignTable).equals("") || lookUp.getLookUpField3(foreignTable)!=null && !lookUp.getLookUpField3(foreignTable).equals("")))
   	   	  {   	   	
   	   	     strWhereForFormVariable = "WHERE(";
   	   	     strEndForFormVariable=")";
   	   	  }
   	   	  else
   	   	  {
   	   	  	strWhereForFormVariable = "WHERE";
   	   	  }
   	   }         
           //System.out.println("WindowLookUp.filter   '"+foreignTable+"'    strWhere:"+strWhere+"   "+utilsString.hasQueryWhere(lookUp.getQuerySubqueryWhere(foreignTable))+"    "+lookUp.getQuerySubqueryWhere(foreignTable));
        String q="";  
        String sub = "";
        //has second field to lookup
   	   	if(lookUp.getLookUpField2(foreignTable)!=null && !lookUp.getLookUpField2(foreignTable).equals(""))
   	   	{
   	   		//System.out.println("WindowLookUp.filter IF '"+foreignTable+"'   "+lookUp.getLookUpField2(foreignTable).length()+" "+lookUp.getLookUpField2(foreignTable).equals(""));
   	   		// getLookUpField is array
   	   	 if(lookUp.getLookUpField(foreignTable).length>=1)
   	   	 {   	   	    
   	   		int len = lookUp.getLookUpField(foreignTable).length;
   	   		for(int o= 0; o<len ;o++)
   	   		{
   	   			//System.out.println("WindowLookUp.filter 1");
   	   		   String end ="";	
   	   		   	if(o==0)
   	   		   	{
   	   		   		//System.out.println("WindowLookUp.filter 1 a "+len+" "+o+" "+lookUp.getLookUpField(foreignTable)[o]);
   	   		   	   	sub=sub+lookUp.getLookUpField(foreignTable)[o]+" LIKE '"+txtSearch.getText()+"%'";
   	   		   	}
   	   		   	else
   	   		   	{
   	   		   		//System.out.println("WindowLookUp.filter 1 b "+len+" "+o+" "+lookUp.getLookUpField(foreignTable)[o]);
   	   		   		sub=sub+" OR "+lookUp.getLookUpField(foreignTable)[o]+" LIKE '"+txtSearch.getText()+"%'";
   	   		   	}
   	   		}
   	   	  }
   	   	  else
   	   	  {
   	   	  	 //System.out.println("WindowLookUp.filter 1 c");
            sub = sub+" "+lookUp.getLookUpField(foreignTable)+" LIKE '"+txtSearch.getText()+"%' OR "+lookUp.getLookUpField2(foreignTable)+" LIKE '"+txtSearch.getText()+"%' OR "+lookUp.getLookUpField3(foreignTable)+" LIKE '"+txtSearch.getText()+"%') ";
   	   	  }   	   		
   	   		
   	   	}   	   	
   	  	else if(lookUp.getLookUpField2(foreignTable)!=null && !lookUp.getLookUpField2(foreignTable).equals(""))
   	   	{
   	   		
   	   		//System.out.println("WindowLookUp.filter ELSE IF");
   	   		// getLookUpField is array
   	   	 if(lookUp.getLookUpField(foreignTable).length>=1)
   	   	 {
   	   		int len = lookUp.getLookUpField(foreignTable).length;
   	   		for(int o= 0; o<len ;o++)
   	   		{
   	   		   String end ="";	
   	   		   	if(o==0)
   	   		   	{
   	   		   	   	sub=sub+lookUp.getLookUpField(foreignTable)[o]+" LIKE '"+txtSearch.getText()+"%'";
   	   		   	}
   	   		   	else
   	   		   	{
   	   		   		sub=sub+" OR "+lookUp.getLookUpField(foreignTable)[o]+" LIKE '"+txtSearch.getText()+"%'";
   	   		   	}	
   	   		}
   	   	  }
   	   	  else
   	   	  {   	   		
   	   		sub = lookUp.getLookUpField(foreignTable)+" LIKE '"+txtSearch.getText()+"%' OR "+lookUp.getLookUpField2(foreignTable)+" LIKE '"+txtSearch.getText()+"%') ";
   	   	  }
   	   	}
   	   	else if(lookUp.getLookUpField(foreignTable).length>=1)
   	   	{
   	   		//System.out.println("WindowLookUp.filter 3");
   	   	    // getLookUpField is array
   	   		int len = lookUp.getLookUpField(foreignTable).length;
   	   		for(int o= 0; o<len ;o++)
   	   		{
   	   		   
   	   		   String end ="";	
   	   		   	if(o==0)
   	   		   	{
   	   		   	   	sub=sub+lookUp.getLookUpField(foreignTable)[o]+" LIKE '"+txtSearch.getText()+"%'";
   	   		   	}
   	   		   	else
   	   		   	{
   	   		   		sub=sub+" OR "+lookUp.getLookUpField(foreignTable)[o]+" LIKE '"+txtSearch.getText()+"%'";
   	   		   	}
   	   		   	
   	   		}
   	   		//System.out.println("WindowLookUp.filter "+sub);
   	   		
   	   	}  
   	   	else //does not have second field to lookup
   	   	{
   	   		//System.out.println("WindowLookUp.filter 4");
   	   		sub = lookUp.getLookUpField(foreignTable)+" LIKE '"+txtSearch.getText()+"%'";   	   	
   	   	}  
   	   		
        String queryLookUp = lookUp.getQuery(foreignTable);

        String queryLookUpIsActive = lookUp.getQuerySubqueryIsActive(foreignTable);
        String queryOrderByLookUp = lookUp.getQueryOrderBy(foreignTable);
      //   coppied from UtilsPanelReport.getLookupValue "start"----------------- not double checked
        String subQueryFilterFromRecType="";
        
        
        
        
        
           int intFieldToGetTheValue = -1;
           intFieldToGetTheValue = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsAll);        
    //     System.out.println("WindowLookUp.filter    foreignTable:"+foreignTable+"  col:"+col+"   dbFieldsAll:"+dbFieldsAll.length+"     calledWhenIsInTextFieldOrTable:"+calledWhenIsInTextFieldOrTable +"    intFieldToGetTheValue:"+intFieldToGetTheValue+"     subQueryFilterFromRecType:"+subQueryFilterFromRecType);
        String fieldVariableFromPreFieldInText = "";
         if(intFieldToGetTheValue==-1)
         {
             
         }
         else
         {
         fieldVariableFromPreFieldInText = dbFieldsAll[col].getFormVariableFromField();
         }
      
   
         
     // if(VariablesGlobal.globalformGlobalVariable1!=null && !VariablesGlobal.globalformGlobalVariable1.equalsIgnoreCase("") && intFieldToGetTheValue!=-1)
     // {   
       //System.out.println("WindowLookUp.filter  --------  foreignTable:"+foreignTable+"'     calledWhenIsInTextFieldOrTable:"+calledWhenIsInTextFieldOrTable+"    VariablesGlobal.globalformGlobalVariable1:"+VariablesGlobal.globalformGlobalVariable1+"     intFieldToGetTheValue:"+intFieldToGetTheValue);
          
       if(calledWhenIsInTextFieldOrTable ==  WINDOWLOOKUP_IS_CALLED_IN_TEXTFIELD ) // WINDOWLOOKUP_IS_CALLED_IN_TEXTFIELD = 0;
       {    
      
        String strValueFromField = "";
        
         if(fieldVariableFromPreFieldInText!=null && !fieldVariableFromPreFieldInText.equalsIgnoreCase(""))
        {
           JTextComponent tbf = (JTextComponent) fieldTxts.get(intFieldToGetTheValue);
            strValueFromField= tbf.getText();
           subQueryFilterFromRecType = strValueFromField+" ) ";
           // setVariablesGlobal1(fieldVariableFromPreField,strValueFromField);
        }
         else
         {
           //System.out.println("WindowLookUp.filter  ----ELSE--a--  foreignTable:"+foreignTable+"     calledWhenIsInTextFieldOrTable:"+calledWhenIsInTextFieldOrTable +"    intFieldToGetTheValue:"+intFieldToGetTheValue+"     subQueryFilterFromRecType:"+subQueryFilterFromRecType);
         }         
         //  System.out.println("WindowLookUp.filter  ----IF----  foreignTable:"+foreignTable+"'     calledWhenIsInTextFieldOrTable:"+calledWhenIsInTextFieldOrTable+"  subQueryFilterFromRecType:"+subQueryFilterFromRecType);
       
       
        if( fieldVariableFromPreFieldInText!= null && !fieldVariableFromPreFieldInText.equalsIgnoreCase("")) // intFieldToGetTheValue!=-1 &&
        {
      
   	   q = lookUp.getQuery(foreignTable)+" "+queryLookUpWhereForFormVariable+" "+subQueryFilterFromRecType+" "+strWhereForFormVariable+" "+ sub +" "+strEndForFormVariable+" "+queryLookUpIsActive+" "+queryOrderByLookUp;//+lookUp.getQueryOrderBy(foreignTable);	
         
        }
        else
        {
            q = lookUp.getQuery(foreignTable)+" "+queryLookUpWhere+" "+strWhere+" "+ sub +" "+strEnd+" "+queryLookUpIsActive+" "+queryOrderByLookUp;//+lookUp.getQueryOrderBy(foreignTable);	
        }
       
       
       }
       else if(calledWhenIsInTextFieldOrTable == WINDOWLOOKUP_IS_CALLED_IN_TABLE)  // WINDOWLOOKUP_IS_CALLED_IN_TABLE = 1;
       {
         EntityDBFields[] dbFieldsChild = dbFieldsAll[intTableOfParentDBFields].getDbChildFields();
       // System.out.println("WindowLookUp.filter  WINDOWLOOKUP_IS_CALLED_IN_TABLE  foreignTable:"+foreignTable+"  col:"+col+"   dbFieldsAll:"+dbFieldsAll.length+"   dbFieldsChild:"+dbFieldsChild.length+"   calledWhenIsInTextFieldOrTable:"+calledWhenIsInTextFieldOrTable +"    intFieldToGetTheValue:"+intFieldToGetTheValue+"     subQueryFilterFromRecType:"+subQueryFilterFromRecType);
         String fieldVariableFromPreFieldInTable = dbFieldsChild[col].getFormVariableFromField();       
        String strValueFromField = "";
        //if(intFieldToGetTheValue!=-1)
         if(fieldVariableFromPreFieldInTable!=null && !fieldVariableFromPreFieldInTable.equalsIgnoreCase(""))
        {
            int txtsize = fieldTxts.size();
        //    System.out.println("WindowLookUp.filter  ----ELSE----   txtsize:"+txtsize+"     intFieldToGetTheValue:"+intFieldToGetTheValue);
            
           JTextComponent tbf = (JTextComponent) fieldTxts.get(intFieldToGetTheValue);
            strValueFromField= tbf.getText();
           subQueryFilterFromRecType = strValueFromField+" ) ";
           // setVariablesGlobal1(fieldVariableFromPreField,strValueFromField);
        } 
         else
         {
          // System.out.println("WindowLookUp.filter  ----ELSE---b-  foreignTable:"+foreignTable+"     calledWhenIsInTextFieldOrTable:"+calledWhenIsInTextFieldOrTable +"    intFieldToGetTheValue:"+intFieldToGetTheValue+"     subQueryFilterFromRecType:"+subQueryFilterFromRecType);
         }
         
        if( fieldVariableFromPreFieldInTable!= null && !fieldVariableFromPreFieldInTable.equalsIgnoreCase("")) // intFieldToGetTheValue!=-1 &&
        {
      
   	   q = lookUp.getQuery(foreignTable)+" "+queryLookUpWhereForFormVariable+" "+subQueryFilterFromRecType+" "+strWhereForFormVariable+" "+ sub +" "+strEndForFormVariable+" "+queryLookUpIsActive+" "+queryOrderByLookUp;//+lookUp.getQueryOrderBy(foreignTable);	
          //System.out.println("WindowLookUp.filter if     foreignTable:"+foreignTable+"        q:"+q); 
        }
        else
        {
            q = lookUp.getQuery(foreignTable)+" "+queryLookUpWhere+" "+strWhere+" "+ sub +" "+strEnd+" "+queryLookUpIsActive+" "+queryOrderByLookUp;//+lookUp.getQueryOrderBy(foreignTable);	
            //System.out.println("WindowLookUp.filter else     foreignTable:"+foreignTable+"        q:"+q); 
        }         
         
 
       }




        query=q;
        
        if(utilsString.hasQueryWhere(query))
        {
          query =  utilsString.queryReplaceWildcardWithStarInWhereClause(query);
          //System.out.println("Database.retrieveDBDataFromQuery   query:"+query);
        }
   	   
        tableModel.setQuery(query);
 
        int totalWidthOfColumns =20;
        for (int c=0; c<table.getColumnCount(); c++)
        {   // table,column, margin
            //System.out.println("WindowLookup.filter  col "+c);
            totalWidthOfColumns=totalWidthOfColumns+utilsTable.packColumn(table, c, 2,true,false,null);
        }
        
        formatColumns();

        tableScrollPane.setPreferredSize(utilsTable.setTableScrollPaneSize(table,totalWidthOfColumns,280));
        table.revalidate();
        panelMain.revalidate();
        
        if(tableModel.getRowCount()==1)
        {
        	table.setRowSelectionInterval(0,0);
        	
        }
        
        showExtendedSummary();
        
   	   this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	
   
      
    
    }
    
    
    public String getTextIndex()
    {
    	
        return strSet;    	
    	
    }
    
    /*public void setIsSelected(boolean isSelectedIn)
    {
    	isSelected=isSelectedIn;
    }*/
    
    /*public boolean getIsSelected()
    {
    	return isSelected;
    }*/
    
    /*private void setButton()
    {
     //     DialogLookUp.keyValue = panelOneDataManyRec.getSelectedRowPrimaryKeyValue(lookupQuery,keyField,intColFieldDescription);
    //      DialogLookUp.keyDescription = panelOneDataManyRec.getSelectedRowDescriptionValue();
          
          //System.out.println("DialogLookUp.setButton "+DialogLookUp.keyValue+ " "+DialogLookUp.keyDescription);
          isSelected = true;
          close();
          //DialogLookUp.dialog.setVisible(false);         	
          //System.out.println("DialogLookUp.setButton() "+DialogLookUp.keyValue);

    }*/
    
    
   
    // called by btnOk
    private String getSelectedText()
    {
    	
         
        if(tableModel.getRowCount()>0) 
        {

    	    strSet = tableModel.getValueAt(table.getSelectedRow(),0).toString();//1 the no of col id	    		
    	    //System.out.println("WindowLookUp.getSelectedText strSet:"+strSet);
  //  	    textField.setText(strSet);
        }
          close();    	
      return strSet;
    }
    
    // for table
   /* private void getSelectedPKToText()
    {
    	String strSet= "";

         strSet = tableModel.getValueAt(table.getSelectedRow(),0).toString();//1 the no of col id		     			         			
	   	
         textField.setText(strSet);
         close();
    }*/

    
     
    public void setEntity(JTextComponent txtfld,String lookupText, String foreignTableIn, LookUpMgt lookUpIn,  ArrayList arrList, int loc, int typeOfSelectedTextIn,
            int intValidationColumnIn,int intValidationTypeIn,EntityDBFields[] dbFieldsAllIn,int colIn,ArrayList fieldTxtsIn, int calledWhenIsInTextFieldOrTableIn,
            int intTableOfParentDBFieldsIn)
    {

    	//query=initialQuery;
    	foreignTable= foreignTableIn;
    	lookUp = lookUpIn;
    	typeOfSelectedText=typeOfSelectedTextIn;
    	location =loc;
        textField=txtfld;
        intValidationColumn =intValidationColumnIn;
        intValidationType = intValidationTypeIn;       
        //formGlobalTableToApply1=formGlobalTableToApply1In;
        //fieldVariableFromPreField = fieldVariableFromPreFieldIn;
        dbFieldsAll = dbFieldsAllIn;
        col = colIn;
        fieldTxts = fieldTxtsIn;
        calledWhenIsInTextFieldOrTable=calledWhenIsInTextFieldOrTableIn;
      intTableOfParentDBFields=intTableOfParentDBFieldsIn;
        
        txtSearch.setText(lookupText);
        
        //System.out.println("WindowLookUp.setEntity start");
        
       //txtfld2.setText(""); 
        
       //panelDataFilter.setEntityFilterSettingsNull();
//       panelDataFilter.setEntity(query,entityFilterSettingsIn,null,PANEL_FILTER_SEARCH);
       
/*       if(entityFilterSettingsIn==null)
       {
       	  panelSearch.setVisible(false);
       }
       else
       {
       	  panelSearch.setVisible(true);
       }*/
       
        /*strArray = new String[arrList.size()];
       
       for(int i =0 ; i<arrList.size(); i++)
       {
         strArray[i] = arrList.get(i).toString();
       }
       list.setListData(strArray);*/
       
       /*Action actionClose = new ActionClose();
        list.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3 ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        list.getActionMap().put("close", actionClose);  
        list.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        list.getActionMap().put("close", actionClose); */        

        
        tableScrollPane.setViewportView(table);
        
        
        //filter(lookupText);
        
        /*tableModel.setQuery(query);
 
        //table.setTableHeader(null); //no table header
     
        UtilsTable utilsTable=new UtilsTable();
        int totalWidthOfColumns =20;
        for (int c=0; c<table.getColumnCount(); c++)
        {   // table,column, margin
            totalWidthOfColumns=totalWidthOfColumns+utilsTable.packColumn(table, c, 2);
        }
        //System.out.println("----"+totalWidthOfColumns);
        tableScrollPane.setPreferredSize(utilsTable.setTableScrollPaneSize(table,totalWidthOfColumns,280));
       */
    
        //JPanel panelTable = new JPanel();
        //panelTable.setLayout(new BorderLayout());
        //JLabel lblTableCaption = new JLabel(EntityFilterSettings[s].caption+":");
        
        //panelTable.add(lblTableCaption, BorderLayout.PAGE_START);
        //panelTable.add(tableScrollPane, BorderLayout.CENTER);
        
        //panelFilterSetting.add(panelTable);       
        //setSelectedCheckBox();
        formatColumns();
        
        panelMain.revalidate();
      showExtendedSummary=false;
      showExtendedSummary();     
        pack();
        this.setModal(true);
         //setSelectedRow();
        
    
         
         
        showWindow();
      this.toFront();
        this.requestFocusInWindow();

        //setAlwaysOnTop( true );// if set not editable
        txtSearch.requestFocus();
 
        
        
    }
   
   private void formatColumns()
   {
      for(int i = 0 ; i< table.getColumnCount();i++)
      {

       if(intValidationColumn==i)
       {
       	if(intValidationType==FIELD_VALIDATION_AFM)
       	{

       	
        	   for(int r= 0 ;r<tableModel.getRowCount(); r++)
        	   {       	
        	   	  Object val = tableModel.getValueAt(r, i);
        	   	  //System.out.println("PanelODMRData. check afm "+val);
       	          if(val!=null)
       	          {
       	          	 //table.getCell(r,i).setTableCellRendererComponent(new DefaultTableCellRenderer()
       	          	 TableColumn tcol;
                     tcol = table.getColumnModel().getColumn(i);
                     tcol.setCellRenderer(new TableCellRendererValidation());
       	          }
       	          else if(val==null)
       	          {
       	          	 TableColumn tcol;
                     tcol = table.getColumnModel().getColumn(i);
                     tcol.setCellRenderer(new TableCellRendererValidation());      	          	
       	          }
    	          
        	   }// for
        }//if
       }// if
      }   	
   } 
   /* public void setSelectedIndices(String text)
    {
    	//txtFilter.setText(text);
        // validate text for not including other characters except strBetweenIndexes
    
      //	System.out.println("WindowLookUpMultipleCheck.setSelectedIndices "+text);

        String[] strArraySel;
        String sel="";
        for(int l =0; l<text.length(); l++)
        {
        	// regionmatches    toffset - the starting offset of the subregion in this string.
                              //other - the string argument.
                              //ooffset - the starting offset of the subregion in the string argument.
                              //len - the number of characters to compare. 
                              
                              //if the character is different than strBetweenIndexes
        	if(!text.regionMatches(l,strBetweenIndexes,0,strBetweenIndexes.length()))
        	{
        		
        	   	if(l == text.length()-1)// if character = last
        	    {
        	    	//System.out.println("WindowLookUpMultipleCheck.setSelectedIndices selected last "+l+" "+text.indexOf(strBetweenIndexes,l)+" ["+sel+"]");
        	    	sel = text.substring(l);
      		        //l = text.indexOf(strBetweenIndexes,l);
        	    }
        	    else if (!text.regionMatches(l,strBetweenIndexes,0,strBetweenIndexes.length()))   // if is the last value (there is no strBetweenIndexes for this region)
        	    {// if there is no more strBetweenIndexes
        	    	sel = text.substring(l,text.length());
        	    }
        	    else
        	    {
        	    	System.out.println("WindowLookUpMultipleCheck.setSelectedIndices selected "+l+" "+text.indexOf(strBetweenIndexes,l)+" ["+sel+"]");	
        	    	sel = text.substring(l, text.indexOf(strBetweenIndexes,l));
          	        l = text.indexOf(strBetweenIndexes,l);
        	    }
        	    
        	    
        	    
        	     //System.out.println("sel "+sel);
            	//if type = indices(1), if type text(2)  
            	//setSelectedIndex( Integer.parseInt(sel)-1);
            	if(typeOfSelectedText==1)
            	{
            	    setSelectedIndex(Integer.parseInt(sel)-1);
            	}
            	else if (typeOfSelectedText==2)  
            	{
            		setSelectedIndex( getIndexOfValueFromArray(sel,strArray) );
            	}
            	 
        	}	
                //   strArraySel[l] = text.substring(text.indexOf(",",l), text.indexOf(",",l+1))	'
        }// for
      /*  for(int i =0; i<index.length ; i++)
        {
        	setSelectedIndex(i);// starts from 1 while array starts from 0
        }*/
   // }*/
    
  /*  private int getIndexOfValueFromArray(String value, String[] strArrayIn)
    {
    	int retInt= 0;
    	//System.out.println("length"+arrListIn.size());
    	for(int i =0; i< strArrayIn.length; i++)
    	{
    		if (value.equalsIgnoreCase(strArrayIn[i]))
    		{
    			retInt = i;
    		}
    	}
    	
    	return retInt;
    }*/
    
    /*private void setSelectedIndex(int idx)
    {
    	//System.out.println("WindowLookUpMultipleCheck.setSelectedIndex"+idx);
    	listWithCheckBox.toggleSelection(idx);
    }*/
    
   /* private String getSelectedIndex(int idx)
    {
    	String sel ="";
    	if(listWithCheckBox.getSelectionModel().isSelectedIndex(idx))
    	{
    		sel = Integer.toString(idx+1); // +1 starts fom zero
    	}
    	//System.out.println("WindowLookUpMultipleCheck.getSelectedIndex  "+idx+" '"+sel+"' "+listWithCheckBox.getSelectionModel().isSelectedIndex(idx));
    	
    	return sel;
    }*/

   /* private String getSelectedString(int idx)
    {
    	String sel ="";
    	if(listWithCheckBox.getSelectionModel().isSelectedIndex(idx))
    	{
    		sel = strArray[idx]; // +1 starts fom zero
    	}
    	//System.out.println("WindowLookUpMultipleCheck.getSelectedIndex  "+idx+" '"+sel+"' "+listWithCheckBox.getSelectionModel().isSelectedIndex(idx));
    	
    	return sel;
    }*/
        
   /* public String getSelectedIndices()
    {
    	String strSelected = "";
    	ArrayList arrListSel = new ArrayList();

    	for(int s =0 ; s<strArray.length; s++)
    	{
    	  if(s < strArray.length)
    	  {
    	    if(!getSelectedIndex(s).equalsIgnoreCase("") )
    	    {   // if type = indices(1), if type text(2)
    	    	if (typeOfSelectedText == 1)
    	    	{
    	    	    arrListSel.add(getSelectedIndex(s));
    	    	}
    	    	else if(typeOfSelectedText == 2)
    	    	{
    	    		arrListSel.add(getSelectedString(s));
    	    	}
    	    	//strSelected = strSelected + getSelectedIndex(s)+", ";
    	    }
    	  }
    	}
    	
    	for(int i = 0; i< arrListSel.size() ; i++)
    	{
    		if(i< arrListSel.size()-1) // if not the last rec
    		{
    		    strSelected = strSelected + arrListSel.get(i).toString()+strBetweenIndexes;
    		}
    		else // if the last rec
    		{
    			
    			strSelected = strSelected + arrListSel.get(i).toString();
    		//	System.out.println("WindowLookUpMultipleCheck.getSelectedIndices last rec "+arrListSel.get(i));
    		}
    	}
    	//System.out.println("WindowLookUpMultipleCheck.getSelectedIndices strSelected "+strSelected);
    	
    	return strSelected;
    }*/
   
    private boolean setWindowLocation(int location, JComponent textField)
    { 
    	boolean retShow=false;  
        if (location == WINDOW_LOCATION_CENTER)
        {
             Dimension paneSize   = this.getSize();
    	     Dimension screenSize = this.getToolkit().getScreenSize();
    	     //System.out.println(getSize()+" - "+getToolkit().getScreenSize());
    	     this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
            retShow=true;  
        }
        else if (location == WINDOW_LOCATION_DOWNRIGHT)
        {
    	   
             Dimension paneSize   = this.getSize();
    	     Dimension screenSize = this.getToolkit().getScreenSize();
    	     //System.out.println(getSize()+" - "+getToolkit().getScreenSize());
    	     int taskbarHeight=30;
    	     this.setLocation(
            (screenSize.width  - paneSize.width) ,
            (screenSize.height - paneSize.height-taskbarHeight));
            retShow=true;  
    	   //this.setLocation(100,100);
        }
        else if (location ==2)
        {
        		
          Dimension paneSize   = this.getSize();
    	  Dimension screenSize = this.getToolkit().getScreenSize();	
        
        retShow=true;  
        if(textField.isShowing())	
        {

         Point point = textField.getLocationOnScreen(); 
        
         //this.setLocation(new Point(point.getX(),point.getY()+comp.getHeight()));
         if((this.getWidth()+(int)(point.getX())>screenSize.width) && (this.getHeight()+(int)(point.getY())>screenSize.height))
         {
         	this.setLocation(screenSize.width-this.getWidth() , (int)point.getY()-this.getHeight());
         }         
         else if(this.getWidth()+(int)(point.getX())>screenSize.width)
         {
            this.setLocation(screenSize.width-this.getWidth() , (int)point.getY()+textField.getHeight());
         }
         else if(this.getHeight()+(int)(point.getY())>screenSize.height)
         {
         	this.setLocation((int)(point.getX()) , (int)point.getY()-this.getHeight());
         }
         else
         {
         	this.setLocation((int)(point.getX()) , (int)point.getY()+textField.getHeight());
         }	
         	retShow=true;  
        }
        else
        {   
        	retShow=false;  
        	//this.setVisible(false);
        	//System.out.println("error WindowLookUp.setWindowLocation not visible because txt not visible.");
        }
	
        }
        
        return retShow;
    }
    
// in PanelODMRData and WinLookupMultipleCheck
  private void showExtendedSummary()
  {
        
        
        int columnsCount=0;
        String columnsTxt="";
        boolean hasColDouble=false;
        boolean hasColInteger=false;
        double sumDouble=0.00;
        double sumDoubleValue=0.0;
        int sumInteger=0;  	
        
        //System.out.println("panelODMRData.showExtendedSummary showExtendedSummary "+showExtendedSummary);
  	    if (showExtendedSummary)
        {
           columnsTxt=columnsTxt+"<table><tr align=center>";	
           columnsTxt=columnsTxt+"<td>|</td>";
          for (int c =0; c<table.getColumnCount(); c++) //Note that this may be different from the number of columns in the table model. 
          {
          	 //System.out.println("PanelODMRData.showExtendedSummary "+c);
             if (tableModel.getColumnClass(c) == Double.class)
             {
             	columnsTxt=columnsTxt+"<td>"+tableModel.getColumnName(c)+"</td><td>|</td>";
             }
             else if (tableModel.getColumnClass(c) == Integer.class)
             {       
               columnsTxt=columnsTxt+"<td>"+tableModel.getColumnName(c)+"</td><td>|</td>";     	
             }
             if (tableModel.getColumnClass(c) == Long.class)
             {
             	columnsTxt=columnsTxt+"<td>"+tableModel.getColumnName(c)+"</td><td>|</td>";
             }              	
             else
             {
             	
             }
          }
          columnsTxt=columnsTxt+"</tr>";
          columnsTxt=columnsTxt+"<tr align=right>";
          columnsTxt=columnsTxt+"<td>|</td>";
          for (int c =0; c<table.getColumnCount(); c++) //Note that this may be different from the number of columns in the table model. 
          {
               columnsCount=columnsCount+1;

                 //           	 System.out.println("PanelODMRData.showExtendedSummary "+c);

              //System.out.println("PanelODMRData.showExtendedSummary 2 "+c);
              if (tableModel.getColumnClass(c) == Double.class )
              {
              	 //System.out.println("panelODMRData.setEntity Double "+c);
                  for (int r =0; r<table.getRowCount(); r++)
                  {
                 	 if (tableModel.getValueAt(r,c)==null)
                	 { 	sumDouble=sumDouble+0.00;       	}
                	 else
                	 {   sumDouble = sumDouble + Double.parseDouble(tableModel.getValueAt(r,c).toString());  	}
                	 hasColDouble=true;
                  }
                  
///////////                  //String strSumDouble= sumDouble.toString();
                  //sumDouble=;
                  //System.out.println("panelODMRData getStringWithTwoDigitsAfterDecimalFromDouble "+ getStringWithTwoDigitsAfterDecimalFromDouble(sumDouble));
                  
                  //columnsTxt=columnsTxt+"  ["+tableModel.getColumnName(c)+": <b>"+uDouble.getDoubleReading(sumDouble)+"</b> ]";
                  columnsTxt=columnsTxt+"<td>"+uDouble.getDoubleReading(sumDouble,true)+"</td><td>|</td>";
                  sumDoubleValue=sumDouble;
                  sumDouble=0.00; //if there is a second or third column it will start from 0
              }
             else if (tableModel.getColumnClass(c) == Integer.class)
             {
             	//System.out.println("panelODMRData.setEntity Integer "+c);
                  for (int r =0; r<table.getRowCount(); r++)
                  {
                  	  //System.out.println(" problem "+tableModel.getColumnName(c)+" "+tableModel.getValueAt(r,c));
                	   if (tableModel.getValueAt(r,c)==null)
                	   {  sumInteger=sumInteger+0;         }
                       else
                       {   sumInteger = sumInteger + Integer.parseInt(tableModel.getValueAt(r,c).toString());      }
                       hasColInteger=true;
                  }

                  //columnsTxt=columnsTxt+"  ["+tableModel.getColumnName(c)+": <b>"+sumInteger+"</b> ]";
                  columnsTxt=columnsTxt+"<td>"+sumInteger+"</td><td>|</td>";
                  sumInteger=0; //if there is a second or third column it will start from 0
              }
              else if (tableModel.getColumnClass(c) == Long.class)
              {
              	 //System.out.println("panelODMRData.setEntity Double "+c);
                  for (int r =0; r<table.getRowCount(); r++)
                  {
                	 //System.out.println(tableModel.getValueAt(r,c));
                 	 if (tableModel.getValueAt(r,c)==null)
                	 { 	sumDouble=sumDouble+0.00;       	}
                	 else
                	 {   sumDouble = sumDouble + Double.parseDouble(tableModel.getValueAt(r,c).toString());  	}
                	 hasColDouble=true;
                  }
                  
///////////                  //String strSumDouble= sumDouble.toString();
                  //sumDouble=;
                  //System.out.println("panelODMRData getStringWithTwoDigitsAfterDecimalFromDouble "+ getStringWithTwoDigitsAfterDecimalFromDouble(sumDouble));
                  
                  //columnsTxt=columnsTxt+"  ["+tableModel.getColumnName(c)+": <b>"+uDouble.getDoubleReading(sumDouble)+"</b> ]";
                  columnsTxt=columnsTxt+"<td>"+uDouble.getDoubleReading(sumDouble,true)+"</td><td>|</td>";
                  sumDoubleValue=sumDouble;
                  sumDouble=0.00; //if there is a second or third column it will start from 0
              }              
              else
              {  //System.out.println("not Double not Number "+c); 
              }
              
          } // for
          columnsTxt=columnsTxt+"</tr>";
          columnsTxt=columnsTxt+"</table>";
         /* if (hasColDouble || hasColInteger )
          {    columnsTxt = "σύνολα:" +  columnsTxt;     }/*
          
          // need them for ExtendedSummaryCalcs
          
         /*  if(isQuery2)
          {
              query = getQueryMany(query, isNewRec ,primKeyValue);
          }
          
          showExtendedSummaryCalcs(entity, query, isNewRec , primKeyValue);*/
        } //if  showExtendedSummary
        
        
        int sumRowCount=0;
        sumRowCount=table.getRowCount();
        
        
         lblBottom.setText("<html><table><tr><td>πλήθος: [<b>"+sumRowCount+"</b>]<td>"+columnsTxt+"</td></table></html>"); 
         lblBottom.revalidate();
         //this.revalidate();

  	
  }    
    
    //exists in panelODMRData , WindowLookUpMult
  /*  private void setTableScrollPaneSize(JTableDec table ,int totalWidthOfColumns)
    {
        int height=200;
        int width=100;
        int columnCount=table.getColumnCount();
        int rowCount=table.getRowCount();
        
  /*      for (int r=0; r<rowCount;r++)
        {
        	colorRow(r);
        }*/
        //height=90;
     
 /*       if (totalWidthOfColumns<795) // maximum width 795
        {   width = totalWidthOfColumns + 5;}
        else
        {   width=795;  }
        
        if (columnCount<=2)
        {      width=250;     }
        else if ((columnCount>2) && (columnCount<=4))
        {      width=400;     }
        else if ((columnCount>4) && (columnCount<=6))
        {      width=580;     }
        else if (columnCount>6)
        {      width=730;     }
        
        //System.out.println("panelODMRData.setScrollPaneSize rowCount "+rowCount);
        
        if (rowCount<=2)
        {      height=70;     }
        else if ((rowCount>2) && (rowCount<=3))
        {      height=90;     }
        else if ((rowCount>3) && (rowCount<=4))
        {      height=100;     }
        else if ((rowCount>4) && (rowCount<=6))
        {      height=160;     }
        else if ((rowCount>6) && (rowCount<=8))
        {      height=190;     }
        else if ((rowCount>8) && (rowCount<=10))
        {      height=210;     }
        else if ((rowCount>10) && (rowCount<=12))
        {      height=240;       }
        else if ((rowCount>12) && (rowCount<=14))
        {      height=250;       }    
        else if ((rowCount>14) && (rowCount<=16))
        {      height=260;       }        
        else if (rowCount>16)
        {
        	int maxWidth=totalWidthOfColumns+25;
        	   height=280; 
                width=maxWidth ;

                if (maxWidth>795) // maximum width 795
                {   width=795;  }
        }
        tableScrollPane.setPreferredSize(new Dimension(totalWidthOfColumns, height));
    }*/
    
//exists modificated in PanelOneDataOneRecData and TableCellEditorLookUp  and windowlookup
  private class DocumentHandler implements DocumentListener
  { // if data changed in txt
  
    int no=0;
    String classtype="";
    String foreignTable = null;
    public DocumentHandler()
    {

        //foreignTable=foreignTableIn;
    }
        
    public void insertUpdate(DocumentEvent e)
    {   
          /* JTextField t = new JTextField();

           
         	if(foreignTable!=null)
         	{*/
         		
         	     filter(txtSearch.getText());
       		
         /*	}
         	else
         	{
         		 System.out.println("WindowLookUp.DocumentHandler insertUpdate  foreignTable "+foreignTable);
         	}*/
            //System.out.println("  change "+foreignTable); 
         	//ta.remove(lblIcoAttention);
         //System.out.println("WindowLookUp.DocumentHandler  insertUpdate   hasDataChanged");
     }     
         
    

    public void removeUpdate(DocumentEvent e)
    {
        /*JTextField t = new JTextField();
      	
         	if(foreignTable!=null)
         	{*/
         		filter(txtSearch.getText());
              //System.out.println("  change "+isLookUp);         		
         /*	}
         	else
         	{
         		 System.out.println("WindowLookUp.DocumentHandler removeUpdate  foreignTable "+foreignTable);
         	} */        	
         	
     }

    public void changedUpdate(DocumentEvent e)
    {
        filter(txtSearch.getText());
    }

   /* private void edited(DocumentEvent e)
    {
        if (guiLoaded)
        { hasDataChanged=true;  }
    }*/
   } 
    
   class  ActionClose extends AbstractAction                 
   {       
        public ActionClose()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{      close();      }    	
    }     
    
    private void showWindow()
    {
       	

        setVisible(setWindowLocation(location, textField));   
    }    
 
    class  ActionSet extends AbstractAction                 
   {       
        public ActionSet()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{      btnOk.doClick();      }    	
    }    

    
   /* public void clearSelectionsInList()
    {
    	setSelectedIndices(getSelectedIndices());
    }*/
    
    // doesn't working
   /* public void setText(String message)
    {
       lblMain.setText(message);
    }*/
    

    
    public void close()
    {
    	if(tableModel!=null)
    	{
    	   tableModel.closeDB();	
    	}
    	
    	
    	
    	dispose();
    	//tabbedPane.requestFocusInWindow();
    }
    
}  