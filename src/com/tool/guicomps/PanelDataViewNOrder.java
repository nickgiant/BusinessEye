// created 14-2-2009

package com.tool.guicomps;

import com.tool.gui.*;
//import com.tool.jdbc.*;
import com.tool.utils.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.border.*;
import java.awt.event.*;
//import javax.swing.event.*;


 public class PanelDataViewNOrder extends JPanel implements Constants
 {
   private final String ORDER_NO="κανένα";
   
   private JxPanel panelReportView;
   private JxPanel panelReportOrder;
   
    private ArrayList fieldView;
    private ArrayList fieldOrder;
    private ArrayList fieldOrderA;// for order of ascenting
    private ArrayList fieldOrderD ;// for order of decenting 
   
    private int orderComboboxes =4;
   
   private JComboBox cmbOrder;
   
   private UtilsString uString;
   
    private   String[]	strColumnsCaption;
    private   String[]	strColumnsName;
    private   String[]	strColumnsTableName;
   // private   String[]	strColumnsCaptionForView;
   // private   String[]	strColumnsNameForView;    
    //private   String[]	strColumnsCaptionForOrder ;
    //private   String[]	strColumnsNameForOrder  ;
    private int colCount;
   
   String sqlForPrintPreview;
   
   int[] showColumns;
   
   private FlowLayout flowLayout;
   
   private JTableDec table;
   private JScrollPane tableScrollPane;
   private TableModelResultSetCheckBoxes tableModel;
   
      private  JButtonForPanelDecorated btnAll ;
      private  JButtonForPanelDecorated btnNone;
      private  JButtonForPanelDecorated btnInvert;
      //private  JButtonForPanelDecorated btnUp;
      //private  JButtonForPanelDecorated btnDown;
   private       ArrayList listCmbOrder ;
   private ArrayList listPanelOrderComboNRadio;
   private int linePanels;
   private ArrayList listRadioAsc;
   private ArrayList listRadioDesc;
   private PanelOrderComboNRadio panelOrderComboNRadio;
   private UtilsDouble uDouble;
   
    public PanelDataViewNOrder()
    {
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private void initialize() throws Exception
    {
    	uString = new UtilsString();
    	uDouble=new UtilsDouble();

       
       
       flowLayout = new FlowLayout();
       flowLayout.setVgap(2);
       flowLayout.setHgap(2);
    	
       table = new JTableDec();
       tableScrollPane = new JScrollPane();	
       tableScrollPane.setViewportView(table);
    
         JxPanel panelButtons = new JxPanel();//(new GridLayout(0,1));
         panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.PAGE_AXIS));//(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));
        //BoxLayout boxLayout = new BoxLayout(panelButtons, BoxLayout.PAGE_AXIS);
        //panelButtons.setLayout(boxLayout);
        
         btnAll = new JButtonForPanelDecorated();
         btnAll.setText("όλα");
        btnAll.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
	           selectAll();
	        } 
	    });
	             
         btnNone = new JButtonForPanelDecorated();
         btnNone.setText("κανένα");
        btnNone.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
	           selectNone();
	        } 
	    });         
         
         btnInvert = new JButtonForPanelDecorated();
         btnInvert.setText("αντίθετα");
        btnInvert.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
	           selectInvert();
	        } 
	    });         
         
        /* btnUp = new JButtonForPanelDecorated();
         btnUp.setText("/\\ πάνω");
        btnUp.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
	           selectUp();
	        } 
	    });
	             
         btnDown = new JButtonForPanelDecorated();
         btnDown.setText("\\/ κάτω");
        btnDown.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
	           selectDown();
	        } 
	    });  */
	            
        panelButtons.add(btnAll);
        panelButtons.add(btnNone);
        panelButtons.add(btnInvert);
        //panelButtons.add(btnUp);
        //panelButtons.add(btnDown);	
    	
       panelReportView = new JxPanel();
       //panelReportView.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 10));//(new GridLayout(0,5));
       panelReportView.setLayout(new BorderLayout());
       panelReportView.add(tableScrollPane, BorderLayout.LINE_START);
       panelReportView.add(panelButtons, BorderLayout.LINE_END);
       panelReportView.setBorder(new TitledBorder("προβολή στηλών"));
       
       panelReportOrder = new JxPanel();
       panelReportOrder.setLayout(new BoxLayout(panelReportOrder, BoxLayout.PAGE_AXIS));
       panelReportOrder.setBorder(new TitledBorder("ταξινόμηση στηλών"));
       	
       
    }
    
    //called by PanelReportSettings
    public void setEntity( RecColumn[] recColumnIn, int[] isFieldSelected,boolean[] fieldsEditable, int[] fieldsOrderby, boolean[] boolOrderByAsc, 
            boolean[] boolOrderByDesc, int entityReportGroupLength, int intTypeOfViewAndOrder,String primKeyCaptionIn)
    {
        
        // not delete it:  cannot format given object as a number
        TableCellRendererDefault tcr = new TableCellRendererDefault();
        TableCellRendererDouble tcrDouble = new TableCellRendererDouble(uDouble);
        TableCellRendererInteger tcrInteger = new TableCellRendererInteger();
        TableCellRendererDate tcrDate = new TableCellRendererDate();
        TableCellRendererBoolean tcrBoolean = new TableCellRendererBoolean();
        //TableCellRendererCheckBox tcrBoolean = new TableCellRendererCheckBox();

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
        
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setGridColor(CLR_TABLE_GRID);
        //table.setGridColor(table.getTableHeader().getBackground());

        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//.SINGLE_SELECTION);// select a single row only  
          	
    	tableModel= new TableModelResultSetCheckBoxes();

        table.setModel(tableModel);

    	listCmbOrder = new ArrayList();
    	listPanelOrderComboNRadio = new ArrayList();
        listRadioAsc = new ArrayList();
    	listRadioDesc = new ArrayList();
        
    	/*strColumnsCaptionForOrder = new String[recColumnIn.length];
    	strColumnsNameForOrder = new String[recColumnIn.length];
    	for(int r=0;r<recColumnIn.length;r++)
    	{
            //System.out.println("PanelDataViewNOrder.setEntity ("+r+") "+recColumnIn.length+"  "+recColumnIn[r].getColumnName()+"  "+recColumnIn[r].getColumnCaption());
    	  strColumnsCaptionForOrder[r]=recColumnIn[r].getColumnCaption();
    	  strColumnsNameForOrder[r]=recColumnIn[r].getColumnName();
    	} */       
        
        
        // for view do not be able to set visible/invisible the primary key
        /*ArrayList listRecColumn= new ArrayList();
        for(int c=0;c<recColumnIn.length;c++)
        {
            if(recColumnIn[c].getColumnName().equalsIgnoreCase(primKeyDbIn))
            {
            }
            else
            {
                listRecColumn.add(recColumnIn[c]);
            }
        }    
        recColumnIn = new RecColumn[listRecColumn.size()];
        for(int c = 0;c<listRecColumn.size();c++)
        {
            recColumnIn[c] = (RecColumn)listRecColumn.get(c);
        }*/
        
    	
    	// for setting visible/unvisible
        strColumnsCaption = new String[recColumnIn.length];
    	strColumnsName = new String[recColumnIn.length];
        strColumnsTableName = new String[recColumnIn.length];
    	for(int r=0;r<recColumnIn.length;r++)
    	{
            //System.out.println("PanelDataViewNOrder.setEntity ("+r+") "+recColumnIn.length+"  "+recColumnIn[r].getColumnName()+"  "+recColumnIn[r].getColumnCaption());
            strColumnsTableName[r]=recColumnIn[r].getColumnTable();
    	  strColumnsCaption[r]=recColumnIn[r].getColumnCaption();
    	  strColumnsName[r]=recColumnIn[r].getColumnName();
    	}
        
        colCount=recColumnIn.length;
    	
     fieldView = new ArrayList();
     fieldOrder = new ArrayList();
     fieldOrderA = new ArrayList();// for order of ascenting
     fieldOrderD = new ArrayList();// for order of descenting
    
    Vector dataVector = new Vector();
    String[] headers = null;
    if(intTypeOfViewAndOrder == TYPE_OF_VIEWANDORDER_TABLE)
    {
        headers = new String[3];
        headers[0]= "τσεκ";
        headers[1]= "νο";
        headers[2]= "στήλη";
        //= {"τσεκ","νο","στήλη"}; //,"ονομασία στήλης","πλάτος"};	
    }
    else if(intTypeOfViewAndOrder == TYPE_OF_VIEWANDORDER_REPORT)
    {
        headers = new String[5];
        headers[0]= "τσεκ";
        headers[1]= "νο";
        headers[2]= "στήλη"; 
        headers[3]= "ονομασία στήλης";
        headers[4]= "πλάτος";         
        
        //headers = {"τσεκ","νο","στήλη","ονομασία στήλης","πλάτος"};	
    }
    else
    {
        System.out.println("error PanelDataViewNOrder.setEntity  UNKNOWN intTypeOfViewAndOrder:"+intTypeOfViewAndOrder+"  in header.");
    }
    
        if(isFieldSelected != null && isFieldSelected.length!=colCount)
       	{
       	   System.out.println(" error Remove file prefsTable.xml. Preferences are saved without new columns. PanelDataViewNOrder.setEntity: isFieldSelected.length:"+isFieldSelected.length+"!="+colCount);
        }
    
    
            
                  //if(isFieldSelected.length == colCount)
                 // {
            for(int c=0;c<strColumnsCaption.length;c++)
            { 
                Object[] record = new Object[headers.length];// do not place it elsewhere
              //System.out.println("PanelDataViewNOrder "+c);
              //mdlColumnCombo.addElement(strColumnsCaption[c]);
              
              if(isFieldSelected == null)
              {
                 record[0]=new Boolean(true);	
              }
              else
              {

                for (int i = 0; i < recColumnIn.length; i++)// NOT colCount, i = fieldFilterTxts
                {
              	 //System.out.println("---PanelDataViewNOrder.setEntity i"+(i)+" c"+(c)+" length"+isFieldSelected.length+"  col count "+colCount+" "+isFieldSelected+" "+recColumnIn.length+" "+strColumnsCaption.length);
              	 if(isFieldSelected[i]==c+1)
              	 {
              	      record[0]=new Boolean(true);
              	      break;	
              	 }
                 else 
              	 {
              	      record[0]=new Boolean(false);  
              	 }
              	}
              }
              
       if(intTypeOfViewAndOrder == TYPE_OF_VIEWANDORDER_TABLE)
       {            
              record[1]= c+1+"";
              record[2] = strColumnsCaption[c];           
       }
       else if(intTypeOfViewAndOrder == TYPE_OF_VIEWANDORDER_REPORT)
       {              
              record[1]= c+1+"";
              record[2] = strColumnsName[c];  // strColumnsCaption
              record[3] = strColumnsCaption[c];
              record[4] = recColumnIn[c].getColumnLength();
       } 
       else
       {
           System.out.println("error PanelDataViewNOrder.setEntity  UNKNOWN intTypeOfViewAndOrder:"+intTypeOfViewAndOrder+"  in lines.");
       }
              dataVector.addElement(record);
  
            }  // for
                 // }
                 // else
                 // {
                 //     System.out.println("---PanelDataViewNOrder.setEntity  length:"+isFieldSelected.length+"  col count:"+colCount);
                 //     System.out.println("PanelDataViewNOrder.setEntity   Remove file with preferences!");
                 // }       
       tableModel.setData(headers,dataVector);
       tableModel.setRowWithCaptionDisabledAndTrue(primKeyCaptionIn);
       
       //System.out.println("PanelDataViewNOrder.setEntity:"+((Object[]) dataVector.elementAt(2))[0]+" rows"+tableModel.getRowCount()+" "+tableModel.getColumnCount());
       panelReportView.revalidate();
       
        UtilsTable utilsTable=new UtilsTable();
        int totalWidthOfColumns =2;
        for (int c=0; c<table.getColumnCount(); c++)
        {   // table,column, margin
            totalWidthOfColumns=totalWidthOfColumns+utilsTable.packColumn(table, c, 2,true,false,null);
        }
        //System.out.println("----"+totalWidthOfColumns);

         tableScrollPane.setPreferredSize(utilsTable.setTableScrollPaneSize(table,totalWidthOfColumns,280));
        
       

          


                // --------------------------   order  -------------------------------------
    
      JButton btnAdd = new JButton("προσθήκη ταξινόμησης");
    	btnAdd.setIcon(ICO_ADD);
          btnAdd.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	            addOrderByLine();
	        }
	      }); 
	      	      
      JButton btnRemove = new JButton("αφαίρεση τελευταίας ταξινόμησης");
    	btnRemove.setIcon(ICO_DELETE);
          btnRemove.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	            removeOrderByLineLast();
	        }
	      });          
      
      
      JxPanel panelButtons = new JxPanel();
      panelButtons.setLayout(new FlowLayout());
      panelButtons.add(btnAdd);
      panelButtons.add(btnRemove);
      
      panelReportOrder.removeAll();
      panelReportOrder.add(panelButtons);
      

        /* if(colCount<orderComboboxes)// if columns < order fields the no = columnlength
         {
         	orderComboboxes=colCount;
         } 
         else if(colCount>orderComboboxes & colCount>8)
         {
         	orderComboboxes = orderComboboxes +1;
         }*/
      
      //System.out.println("PanelDataViewNOrder.setEntity "+fieldsOrderby.length);
          
      if   (fieldsOrderby!=null && fieldsOrderby.length>0)
      {
      	 linePanels=0;   
      	 
         
         
      	 // it is correct do not change it
         for (int i = 0; i <=fieldsOrderby.length-1; i++)
         {  	
         	  //System.out.println("Paneldataviewnorder "+fieldsOrderby.length+" "+i);
         	  panelOrderComboNRadio = new PanelOrderComboNRadio(i+1);
         	

             System.out.println("setEntity   panelOrderComboNRadio   fieldsOrderby:"+fieldsOrderby.length+"  fieldsOrderby[i]:"+fieldsOrderby[i]+"    i:"+i);
              panelOrderComboNRadio.setComboSelectedIndex(fieldsOrderby[i]);  
              if(boolOrderByAsc!=null &&  boolOrderByDesc!=null)
              {
              	//System.out.println("PanelDataViewNOrder "+i+" "+linePanels+" "+boolOrderByAsc.length+" "+boolOrderByDesc.length);
                panelOrderComboNRadio.setRadioAscSelected(boolOrderByAsc[i]);
                panelOrderComboNRadio.setRadioDescSelected(boolOrderByDesc[i]);              	
              }
              else
              {
              		panelOrderComboNRadio.setRadioAscSelected(true);
              }

              
              
              listPanelOrderComboNRadio.add(panelOrderComboNRadio);
              
              panelReportOrder.add(panelOrderComboNRadio);
         	  linePanels++;
         }
      }       
  
  
    }
    public int getFieldsTableRowCount()// the sum of fields
    {
                
        return table.getRowCount();
    }

    public JPanel getPanelView()
    {
    	return panelReportView;
    }    

    
    public JPanel getPanelOrder()
    {
    	return panelReportOrder;
    }    
    
    public void addOrderByLine()
    {
    	//System.out.println("PanelDataViewNOrder.addOrderByLine "+(linePanels+1));
    	linePanels=linePanels+1;
    	panelOrderComboNRadio = new PanelOrderComboNRadio(linePanels);
    	//System.out.println("PanelDataViewNOrder.addOrderByLine ->"+(linePanels));
    	panelReportOrder.add(panelOrderComboNRadio);
    	listPanelOrderComboNRadio.add(panelOrderComboNRadio);
    	panelReportOrder.revalidate();
    }

    private void removeOrderByLineLast()
    {
    	if((linePanels-1)>=0)
    	{
    		System.out.println("PanelDataViewNOrder.removeOrderByLine "+(linePanels));
    	   
    	   //PanelOrderComboNRadio panelOrderComboNRadio = (PanelOrderComboNRadio)listPanelOrderComboNRadio.get(linePanels-1);
    	   //panelOrderComboNRadio.setComboSelectedIndex(0);
    	   //panelOrderComboNRadio.setVisible(false);
    	   
    	   panelReportOrder.remove(linePanels);//
    	   listPanelOrderComboNRadio.remove(listPanelOrderComboNRadio.get(linePanels-1));
    	   panelReportOrder.revalidate();    
    	   linePanels=linePanels-1;		
    		System.out.println("PanelDataViewNOrder.removeOrderByLine after"+(linePanels-1));
    	}

    }        
    
    
    /*
    * public beacause is called by DialogDataTableConfig.getShowColumns and also used in reports
    *
    * the intToStartFromPreviousBandRowCount used in reportbands,In every other method should b 0
    */        
    public void calculateSubquery(String queryIn)//, int intToStartFromPreviousBandRowCount)
    {
      	String queryReturn="";
       // --------------------------- view ---------------------------------------
       showColumns = new int[colCount];
       //System.out.println("PanelDataViewNOrder.calculateSubquery colCount"+colCount);
       for(int c=0;c<colCount;c++)//strColumnsCaption.length;c++)  // strColumnsCaptionstarts from 0, colCount starts from1 it is rsmd
       {
          //JCheckBox chkView = (JCheckBox)fieldView.get(c);
       //   JComboBox cmbColumn=(JComboBox)fieldView.get(c);
       //   showColumns[c] = cmbColumn.getSelectedIndex();

              //System.out.println("PanelDataViewNOrder.calculateSubquery "+c+" "+showColumns.length+"  "+strColumnsName[c]);//+" "+tableModel.getValueAt(c,1).toString());
             if(tableModel.getValueAt(c,0).toString().equalsIgnoreCase("true"))
             {
            	 showColumns[c] = Integer.valueOf(tableModel.getValueAt(c,1).toString());
          	//System.out.println("PanelDataViewNOrder.calculateSubquery "+c+" "+showColumns.length+"   "+showColumns[c]);//+" "+tableModel.getValueAt(c,1).toString());
            //showColumns[c] = Integer.getInteger(tableModel.getValueAt(c+1,1).toString()) ;	
             }
          
          //System.out.println("PanelDataViewNOrder.calculateSubquery colCount"+showColumns[c]);
          //showColumns[c]=chkView.isSelected();
          //System.out.println("PanelDataViewNOrder.calculateSubquery"+showColumns[c]+" "+c);
       }
       
       //--------------------------- order ---------------------------------------
       
       String sqlOrder="";
       int txtOrder=0;
 //      try
 //      {
       	 String order="";
       for(int c=0;c<listPanelOrderComboNRadio.size();c++)
       {



           
           
       	 JComboBox cmbOrder =(JComboBox)fieldOrder.get(c);
       	 
       	 JRadioButton radioASC = (JRadioButton)fieldOrderA.get(c);
         JRadioButton radioDESC = (JRadioButton)fieldOrderD.get(c);
       	 
       	 
       	int intSelection = cmbOrder.getSelectedIndex();//+intToStartFromPreviousBandRowCount;
        String strSelection = cmbOrder.getSelectedItem().toString();//
        //System.out.println("PanelDataViewNOrder.calculateSubquery      intSelection:"+intSelection+"   c:"+c+"   intToStartFromPreviousBandRowCount:"+intToStartFromPreviousBandRowCount+"   strColumnsCaption.length:"+strColumnsCaption.length);


         
       	 String selection = "";//intSelection+""; // selection column int
       for(int k= 0;k <strColumnsCaption.length;k++)
       {
        String colCaption = strColumnsCaption[k];
    	String colName = strColumnsName[k];
        String colTable = strColumnsTableName[k];           
           
         if(strSelection.equalsIgnoreCase(colCaption))
         {
             selection = colTable+"."+colName;
             break;
         }
       }
         
       	 if(intSelection!=0)  //0 or ORDER_NO = text nothing selected
       	 {
       	 	
       	 	//System.out.println("PanelDataViewNOrder.calculateSubquery intSelection "+intSelection+" "+c);
       	 	//String selection = strColumnsName[intSelection-1];  // selection column name string
       	   if(radioASC.isSelected())
       	   {
       	      order=""+selection+" ASC";	
       	   }
       	   else if(radioDESC.isSelected())
       	   {
       	   	   order=""+selection+" DESC";
       	   }
       	   
       	    txtOrder++; 
       	    
   	        if(txtOrder>1)
            {
     	      	sqlOrder=sqlOrder+" , "+order;
   	        }
   	        else
   	        {
                sqlOrder=sqlOrder+" "+order;
            }
          }// if ! ORDER_NO
       	 }// for   
       	


       
       if (txtOrder>0)
       {  
          if(uString.hasQueryOrderby(queryIn))
          {
          	 queryReturn=","+sqlOrder;
          }
          else
          {
             queryReturn=" ORDER BY "+sqlOrder;
          }
       }
    	
    	sqlForPrintPreview = queryReturn;
    	//System.out.println("sqlForPrintPreview "+sqlForPrintPreview);
    }    
   
    private void selectAll()
    {
    	for(int r =0; r<tableModel.getRowCount();r++)
    	{
    	   tableModel.setValueAt(new Boolean(true),r,0)	;
    	}
    	
    }

    private void selectNone()
    {
    	for(int r =0; r<tableModel.getRowCount();r++)
    	{
    	   tableModel.setValueAt(new Boolean(false),r,0)	;
    	}    	
    }
    
    private void selectInvert()
    {
    	
    	for(int r =0; r<tableModel.getRowCount();r++)
    	{  
    	  //boolean isChecked =Boolean.getBoolean(tableModel.getValueAt(r,0).toString());
    	   //System.out.println(isChecked);
    	   if(tableModel.getValueAt(r,0).toString().equalsIgnoreCase("true"))
    	   {
    	   	 //System.out.println(tableModel.getValueAt(r,0).toString()+" "+r+"->false");
    	     tableModel.setValueAt(new Boolean(false),r,0);
    	   }
    	   else
    	   {
    	   	 //System.out.println(tableModel.getValueAt(r,0).toString()+" "+r+"->true");
    	   	 tableModel.setValueAt(new Boolean(true),r,0);
    	   }
    	   
    	}     	
    	
    }       

    private void selectUp()
    {
    	//tableModel.moveRowUp(table.getSelectedRow());
    }   
    
    private void selectDown()
    {
    	
    }    
    
    private class PanelOrderComboNRadio extends JxPanel
    {
        
        private JRadioButton radioASC;
        private JRadioButton radioDESC;
        
        public PanelOrderComboNRadio(int no)
        {
        	

              JxPanel fieldOrderRow= new JxPanel();
              fieldOrderRow.setLayout(flowLayout);
    
              JLabel lblOrder=new JLabel("πεδίο "+no+":",JLabel.RIGHT);
              DefaultComboBoxModel mdlOrder=new DefaultComboBoxModel();
              cmbOrder=new JComboBox(mdlOrder);
              
              fieldOrderRow.add(lblOrder);
              fieldOrderRow.add(cmbOrder);
              listCmbOrder.add(cmbOrder);
              
              // add optionboxes
              //JLabel lblOrderAD = new JLabel();
              radioASC =new JRadioButton("αύξουσα");
              listRadioAsc.add(radioASC);
              radioASC.setOpaque(false);
              radioDESC =new JRadioButton("φθίνουσα");
              listRadioDesc.add(radioDESC);
              radioDESC.setOpaque(false);
              ButtonGroup group = new ButtonGroup();
              group.add(radioASC);
              group.add(radioDESC);
              radioASC.setSelected(true);
              fieldOrderRow.add(radioASC);
              fieldOrderRow.add(radioDESC);
              
              mdlOrder.addElement(ORDER_NO);
              
              for(int c=0;c<strColumnsCaption.length;c++)
              {
                mdlOrder.addElement(strColumnsCaption[c]);
              }
              
             /* for (int c = 1; c <= rsmd.getColumnCount(); c++)
              {
                  mdlOrder.addElement(rsmd.getColumnLabel(c));//.getColumnName(c));
              } */
             
             cmbOrder.setSelectedIndex(0);
              
              fieldOrder.add(cmbOrder);// add to array list
              fieldOrderA.add(radioASC);
              fieldOrderD.add(radioDESC);

               //fieldOrder.add(cmbOrder);
              this.add(fieldOrderRow);
              
         }        	
        
        public void setComboSelectedIndex(int idx)
        {
        	 cmbOrder.setSelectedIndex(idx);   
        }
        
        public void setRadioAscSelected(boolean sel)
        {
        	radioASC.setSelected(sel);
        }

        public void setRadioDescSelected(boolean sel)
        {
        	radioDESC.setSelected(sel);
        }
         	
    }
    
    
    public int[] getShowColumns()
    {
    	//System.out.println("      +     PanelDataViewNOrder.getShowColumns col "+showColumns);
        //calculateSubquery(query);
    	return showColumns;
    }
     
     //gets only the order by subquery
     // or insert empty query(panel table preferences)
    public String getSubquery(String query)//,int intToStartFromPreviousBandRowCount)
    {
    	calculateSubquery(query);//,intToStartFromPreviousBandRowCount);
    	return sqlForPrintPreview;
    }
   
   
    // before this is called, this: panelDataViewNOrder.getSubquery("") should be called
   public int[] getFieldsOrderby()
   {
   	 
   	 // if there is one selected 0 remove
       for(int c=listPanelOrderComboNRadio.size()-1;c>=0;c--)
       {
       	 JComboBox cmbOrder =(JComboBox)fieldOrder.get(c);
       	 
       	 //System.out.println("PanelDataViewNOrder.getFieldsOrderby remove "+c+" "+listPanelOrderComboNRadio.size());
       	 
   	     if(cmbOrder.getSelectedIndex()==0)
   	     {
   	       //System.out.println("PanelDataViewNOrder.getFieldsOrderby remove --->  "+c+" "+listPanelOrderComboNRadio.size());
    	   panelReportOrder.remove(c);//
    	   listPanelOrderComboNRadio.remove(listPanelOrderComboNRadio.get(c));
    	   panelReportOrder.revalidate();    
    //   linePanels = linePanels-1;	   	     	
   	     	
   	     }
       }   	 	
   	 
   	 // return array selected
   	  ArrayList intRet=new ArrayList();
       for(int c=0;c<listPanelOrderComboNRadio.size();c++)
       {
       	 JComboBox cmbOrder =(JComboBox)fieldOrder.get(c);
   	     intRet.add(cmbOrder.getSelectedIndex());
       }
       
       int [] ret = new int[intRet.size()];
       for(int i =0;i<intRet.size();i++)
       {
       	  ret[i]= Integer.parseInt(intRet.get(i)+"");
       }
   
      return ret;
   
   }
  
    public boolean[] getFieldsOrderbyRadioAsc()
   {
   
   	  ArrayList boolRet=new ArrayList();
       for(int c=0;c<listPanelOrderComboNRadio.size();c++)
       {      
       	      
       	      JRadioButton radioASC =(JRadioButton)listRadioAsc.get(c);
       	      boolRet.add(radioASC.isSelected());

       }
       
       boolean [] ret = new boolean[boolRet.size()];
       for(int i =0;i<boolRet.size();i++)
       {
       	  ret[i]= Boolean.parseBoolean(boolRet.get(i)+"");
       }

      return ret;
   
   }
 
 
     public boolean[] getFieldsOrderbyRadioDesc()
   {
   
   	  ArrayList boolRet=new ArrayList();
       for(int c=0;c<listPanelOrderComboNRadio.size();c++)
       {      
       	      
       	      JRadioButton radioDESC =(JRadioButton)listRadioDesc.get(c);
       	      boolRet.add(radioDESC.isSelected());

       }
       
       boolean [] ret = new boolean[boolRet.size()];
       for(int i =0;i<boolRet.size();i++)
       {
       	  ret[i]= Boolean.parseBoolean(boolRet.get(i)+"");
       }

      return ret;
   
   } 
        
 }
 