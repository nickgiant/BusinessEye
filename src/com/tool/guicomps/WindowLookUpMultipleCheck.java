package com.tool.guicomps;

import com.tool.model.EntityFilterSettings;
import com.tool.jdbc.*;
import com.tool.gui.*;
import com.tool.utils.*;

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

public class WindowLookUpMultipleCheck extends JDialog implements Constants
{
    private JLabel lblMain;
    private JLabel lblBack;
    private JScrollPane listScrollPane;
    private JxPanel panelMain;
    private JPanelDecorated panelOuterDecor;
    private JTabbedPane tabbedPane;
    
    private int location;
    private JTextComponent textField;
    private JTable tableForMultiInsert;
    private int columnToMultiUpdateTable;
    private int typeOfSelectedText; //if type = indices(1), if type text(2)   
    //JTextField txtFilter;
    String tablePrimKeyValue;
    private ArrayList arrList;
    private String[] strArray;
    private JList list;
    //private JListWithCheckBoxManager  listWithCheckBox;
    private String strBetweenIndexes = ",";    
    
    private JTableDec table;
    private JScrollPane tableScrollPane;
    private TableModelResultSetCheckBoxes tableModel;
    private JxPanel panelSearch;
    private PanelDataFilter panelDataFilter;
    private UtilsTable utilsTable;
    private boolean showExtendedSummary;  
    private UtilsDouble uDouble;  
    private JLabel lblBottom;
    private String query;  
    private int intValidationColumn;
    private int intValidationType;      
    	
    private PanelManagement panelManagement;
    
    public WindowLookUpMultipleCheck(JFrame frame)
    {
    	super(frame);
	
	uDouble=new UtilsDouble();
        uDouble = VariablesGlobal.globalUtilsDouble;
        
    	utilsTable=new UtilsTable();
    	panelDataFilter = new PanelDataFilter(frame);
    	utilsTable=new UtilsTable();
    	
    	panelMain = new JxPanel();
    	panelMain.setLayout(new BorderLayout());
    	panelOuterDecor = new JPanelDecorated(new BorderLayout());
    	//panelOuterDecor.setBorder(new BevelBorder(BevelBorder.RAISED));
    	panelOuterDecor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	//panelOuterDecor.setBorder(UIManager.getBorder("TextField.border")); 
  
    	panelMain.setBorder(BorderFactory.createEmptyBorder(2, 4, 0, 4));   // (int top, int left, int bottom, int right)     

       JxPanel panelTop = new JxPanel();
       panelTop.setLayout(new BorderLayout());
       panelSearch = new JxPanel();  
//       toolBarData = new ToolBarData();
//       toolBarData.setFocusable(false);
//       toolBarData.setFloatable(false);
       
   
       panelSearch.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
       panelSearch.setAlignmentY(10);
       //panelSearch.setPreferredSize(new Dimension(getWidth(), 22));
       
        JButtonForPanelDecorated btnSearch = new JButtonForPanelDecorated("<html><b>Α</b>ναζήτηση</html>");
        btnSearch.setFocusable(false) ;
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
        //panelSearch.setBackground(Color.cyan);

       
       //listWithCheckBox =new JListWithCheckBoxManager();
       table = new JTableDec();
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
       
       
       panelOuterDecor.add(panelMain ,BorderLayout.CENTER);
       
       list = new JList();

//       listWithCheckBox.setList(list);

       //panelMain.add(txtFilter, BorderLayout.PAGE_START);
//       panelMain.add(list, BorderLayout.CENTER);

         tableScrollPane = new JScrollPane();


        Action actionClose = new ActionClose();
        panelMain.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        panelMain.getActionMap().put("close", actionClose);        
       Action actionSet = new ActionSet();
        panelMain.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5 ,0), "set"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        panelMain.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER ,0), "set"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        panelMain.getActionMap().put("set", actionSet); 


       JButtonForPanelDecorated btnOk = new JButtonForPanelDecorated("<html>ΟΚ <b>F5</b></html>");
       btnOk.setIcon(ICO_OK16);
       btnOk.setFocusable(false) ;
       btnOk.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  //textField.setText(strDate=panelDateChooser.getDate());
           getCheckedToTextOrTable();
       }
      });
       	
       JButtonForPanelDecorated btnCancel = new JButtonForPanelDecorated("<html>άκυρο <b>esc</b></html>");
       btnCancel.setIcon(ICO_CANCEL16);
       btnCancel.setFocusable(false) ;
       btnCancel.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	   //strDate="";
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
      //panelTop.add(toolBarData,BorderLayout.CENTER); 
        panelTop.add(panelSearch,BorderLayout.PAGE_END);  

                            
        getContentPane().add(panelOuterDecor, BorderLayout.CENTER);
        panelMain.add(panelTop, BorderLayout.PAGE_START);
        panelMain.add(panelBottom, BorderLayout.CENTER);
        panelMain.add(panelBtns, BorderLayout.PAGE_END);
       //this.setAlwaysOnTop(true);
       
    }
    
    // for table
    public void setSelectedCheckBox()
    {
    	String text ="";
        if(textField!=null)
        {
    	    text = textField.getText();
        }
        
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
        	    //System.out.println("WindowLookUpMultipleCheck.setSelectedCheckBox selected "+l);
	
        	   	if(l == text.length()-1)// if character = last
        	    {
        	    	//System.out.println("WindowLookUpMultipleCheck.setSelectedIndices selected last "+l+" "+text.indexOf(strBetweenIndexes,l)+" ["+sel+"]");
     		        //System.out.println("WindowLookUpMultipleCheck.setSelectedCheckBox selected if ");
        	    	sel = text.substring(l);
      		        //l = text.indexOf(strBetweenIndexes,l);
        	    }
        	    else if (text.indexOf(strBetweenIndexes,l) == -1)//!text.regionMatches(l,strBetweenIndexes,0,text.length()))   // if is the last value (there is no strBetweenIndexes for this region)
        	    {// if there is no more strBetweenIndexes
        	    	//System.out.println("WindowLookUpMultipleCheck.setSelectedCheckBox selected else if ");

        	    	sel = text.substring(l,text.length());
        	    	l = text.length();
        	    }
        	    else
        	    {
        	    	//System.out.println("WindowLookUpMultipleCheck.setSelectedCheckBox selected "+l+" "+text.indexOf(strBetweenIndexes,l)+" ["+sel+"]");	
        	    	//System.out.println("WindowLookUpMultipleCheck.setSelectedCheckBox selected else ");
        	    	sel = text.substring(l, text.indexOf(strBetweenIndexes,l));
          	        l = text.indexOf(strBetweenIndexes,l);
        	    }
        	    
        	    
        	     //System.out.println("sel "+sel);
            	//if type = indices(1), if type text(2)  
            	//setSelectedIndex( Integer.parseInt(sel)-1);
            	if(typeOfSelectedText==1) // index integer (defined in setEntity)
            	{
            	    //setSelectedIndex(Integer.parseInt(sel)-1);
            	    System.out.println("WindowLookUpMultipleCheck.setSelectedCheckBox selected "+sel+" type"+typeOfSelectedText);
            	}
            	else if (typeOfSelectedText==2)  // index string
            	{
            		//setSelectedIndex( getIndexOfValueFromArray(sel,strArray) );
        	         //System.out.println("WindowLookUpMultipleCheck.setSelectedCheckBox selected "+l+" sel "+sel);
            		
            		  for(int r =0; r<tableModel.getRowCount();r++)
                      {
                      	
                      	//System.out.println("WindowLookUpMultipleCheck.setSelectedCheckBox selected "+l+" sel "+sel);
                      	
         	             if(tableModel.getValueAt(r,1).toString().trim().equalsIgnoreCase(sel.trim()))
         	             {
         	             	
      	        	    //System.out.println("WindowLookUpMultipleCheck.setSelectedCheckBox selected --> "+l+" sel "+sel+" "+tableModel.getValueAt(r,1));

                              tableModel.setValueAt( Boolean.valueOf(true),r,0);
                              //strSet = strSet + tableModel.getValueAt(r,1)+strBetweenIndexes;//1 the no of col id		     			         			
         	             }
                      }
            		
            	}
            	 
        	}	
                //   strArraySel[l] = text.substring(text.indexOf(",",l), text.indexOf(",",l+1))	'
        }// for   	
	
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
    
    private void filter()
    {
    	
   	  this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
   	   //tableModel.filter(strSearchField,strSearch);
    	String queryFilter = panelDataFilter.getSubquery(query);//,-1);// (-1 all) filters apply to all the groups 
    	System.out.println("WindowLookUpMultipleCheck.filter  "+queryFilter);
   	   
        tableModel.setQuery(queryFilter);
 
        int totalWidthOfColumns =20;
        for (int c=0; c<table.getColumnCount(); c++)
        {   // table,column, margin
            totalWidthOfColumns=totalWidthOfColumns+utilsTable.packColumn(table, c, 2,true,false,null);
        }
      
       formatColumns();
       
        table.revalidate();
        panelMain.revalidate();
   	      	
        showExtendedSummary();
    	
    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
    }
    
    // for text or table
    private void getCheckedToTextOrTable()
    {
    	String strSet= "";
         for(int r =0; r<tableModel.getRowCount();r++)
         {
         	if((Boolean)tableModel.getValueAt(r,0))
         	{
                   strSet = strSet + tableModel.getValueAt(r,1)+strBetweenIndexes;//1 the no of col id		     			         			
         	} 	   
         }
          
          if(strSet.endsWith(strBetweenIndexes))
          {
          	strSet = strSet.substring(0,strSet.length()-1);
          }
          if(tableForMultiInsert==null)
          {
             textField.setText(strSet);
          }
          else if(textField==null)
          {
              int intToFreeRow=0;
              int destTableRowCount= tableForMultiInsert.getRowCount();
              TableModelResultSet destinationTableModel=(TableModelResultSet)tableForMultiInsert.getModel();
              if(destTableRowCount>0)
              {
                     intToFreeRow = destTableRowCount;
              }
         int rcount = tableModel.getRowCount();
         for(int r =0; r<rcount;r++)
         {
         	if((Boolean)tableModel.getValueAt(r,0))
         	{
                   String strTextOfCell = tableModel.getValueAt(r,1)+"";//1 the no of col id	
                System.out.println("   getCheckedToTextOrTable     rcount:"+rcount+"       destTableRowCount:"+destTableRowCount+"    "+strTextOfCell+"  intToFreeRow:"+intToFreeRow+"  columnToMultiUpdateTable:"+columnToMultiUpdateTable);
                   destinationTableModel.addEmptyRow(intToFreeRow,tablePrimKeyValue, tableForMultiInsert);
                   destinationTableModel.setValueAt(strTextOfCell, intToFreeRow, columnToMultiUpdateTable);
                   intToFreeRow++;
         	} 	   
         }              
              
              
              //tableForMultiInsert
          }
          close();
    }
     
    public void setEntity(JTextComponent txtfld,JTable tableForMultiIn,int columnToMultiUpdateTableIn,String queryIn,String tablePrimKeyValueIn, EntityFilterSettings[] entityFilterSettingsIn , 
    String dialogTitle, int loc, int typeOfSelectedTextIn, int intValidationColumnIn, int intValidationTypeIn,/*String yearEnforceIn,*/ PanelManagement panelManagement)
    {
    	query=queryIn;
        tablePrimKeyValue=tablePrimKeyValueIn;
    	location =loc;
        textField=txtfld;
        tableForMultiInsert = tableForMultiIn;
        columnToMultiUpdateTable=columnToMultiUpdateTableIn;
        typeOfSelectedText=typeOfSelectedTextIn;
        intValidationColumn =intValidationColumnIn;
        intValidationType = intValidationTypeIn;
       
       //panelDataFilter.setEntityFilterSettingsNull();
       panelDataFilter.setEntity(entityFilterSettingsIn,null,PANEL_FILTER_SEARCH, /*yearEnforceIn,*/panelManagement);
       
       if(entityFilterSettingsIn==null)
       {
       	  panelSearch.setVisible(false);
       	  table.requestFocus();
       }
       else
       {
       	  panelSearch.setVisible(true);
       }


      this.setTitle(dialogTitle);

       
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
        

        
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setGridColor(CLR_TABLE_GRID);

        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//.SINGLE_SELECTION);// select a single row only  
          	
    	tableModel= new TableModelResultSetCheckBoxes();

        table.setModel(tableModel);
        tableModel.setQuery(query);


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
        table.setDefaultRenderer(Number.class, tcrInteger);
        table.setDefaultRenderer(Double.class, tcrDouble); 
        table.setDefaultRenderer(java.util.Date.class, tcrDate);
        table.setDefaultRenderer(Boolean.class,  tcrBoolean);        	
        //table.setDefaultRenderer(Boolean.class, tcrBoolean);
        
        //table.getColumn(0).setCellRenderer(tcrBoolean);
        //table.getColumn(0).setCellEditor(rowEditor); 

 
        //table.setTableHeader(null); //no table header
     
        
        int totalWidthOfColumns =2;
        for (int c=0; c<table.getColumnCount(); c++)
        {   // table,column, margin
            totalWidthOfColumns=totalWidthOfColumns+utilsTable.packColumn(table, c, 2,true,false,null);
        }
        //System.out.println("----"+totalWidthOfColumns);
        tableScrollPane.setPreferredSize(utilsTable.setTableScrollPaneSize(table,totalWidthOfColumns,280));

        formatColumns();
        
        //JPanel panelTable = new JPanel();
        //panelTable.setLayout(new BorderLayout());
        //JLabel lblTableCaption = new JLabel(EntityFilterSettings[s].caption+":");
        
        //panelTable.add(lblTableCaption, BorderLayout.PAGE_START);
        //panelTable.add(tableScrollPane, BorderLayout.CENTER);
        
        //panelFilterSetting.add(panelTable);       
        setSelectedCheckBox();
        panelMain.revalidate();
        showExtendedSummary=false;
        showExtendedSummary();     
        pack();
        setSelectedCheckBox();
        
        this.setModal(true);
        showWindow();
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
   
    public void setWindowLocation(int location, JComponent textField)
    {   
        if (location == WINDOW_LOCATION_CENTER)
        {
             Dimension paneSize   = this.getSize();
    	     Dimension screenSize = this.getToolkit().getScreenSize();
    	     //System.out.println(getSize()+" - "+getToolkit().getScreenSize());
    	     this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
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
    	   //this.setLocation(100,100);
        }
        else if (location ==WINDOW_LOCATION_COMPONENT)
        {
        		
          Dimension paneSize   = this.getSize();
    	  Dimension screenSize = this.getToolkit().getScreenSize();	
        	
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
        }
    }
    
// in PanelODMRData and WinLookupMultipleCheck
  public void showExtendedSummary()
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
    
   class  ActionClose extends AbstractAction                 
   {       
        public ActionClose()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{      close();      }    	
    }     
    
    public void showWindow()
    {
        setWindowLocation(location, textField);	

        setVisible(true);   
    }    
 
    class  ActionSet extends AbstractAction                 
   {       
        public ActionSet()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{      getCheckedToTextOrTable();      }    	
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
    	tableModel.closeDB();
    	dispose();
    	//tabbedPane.requestFocusInWindow();
    }
    
}  