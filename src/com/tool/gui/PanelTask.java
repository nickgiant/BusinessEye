
package com.tool.gui;

import com.tool.model.EntityGroupOfComps;
import com.tool.model.EntityFilterSettings;
import com.tool.guicomps.*;
import com.tool.utils.*;
import com.tool.model.*;
import com.tool.jdbc.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.table.*;

 public class PanelTask extends JxPanel implements Constants
{
        private JFrame frame;
        private String name;
        private String caption;
        private String subTitle;
        private JLabel lblSubtitle;
        private JLabel lblStepHeader;
        private int pageCurrent = 0;
        private int pageTotal = 0;
        private String[] calculationOption;
        private EntityTask[] entityTaskArray;
        private int intCalculationOption;
        private String strCalculationOption;
        private EntityFilterSettings[] entityFilterSettings;
        private EntityQuery[] entityQuery;
        
        private JxPanel panelMain;
        private JxPanel panelCalculationOption;
        private PanelDataFilter panelDataFilter;
      //  private PanelUpdateWithCriteria panelUpdateWithCriteria;
        private JxPanel panelDFilters;
        private JxPanel panelExecuteLog;
        private JButtonForPanelDecorated  btnNext;
        private JButtonForPanelDecorated btnPrevious;
        
        private JButtonForPanelDecorated btnExecute;
        
        private Database db;
        private JTable table;
        private TableModelReadOnly tableModel;   
        private boolean isNullify;     
        private String tableForFilters;
        private EntityGroupOfComps[] entityGroupOfComps;
        private PanelManagement panelManagement;
        private String yearEnforce;
        private UtilsGui utilsGui;
        
    public PanelTask(JFrame frame)
    {
        try {
            initialize(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   // fromto -> while is 1 object the 'to' it is layed out in different column because the object is lengthy
	private void initialize(JFrame frameIn)throws Exception
    {
    	
    	db= new Database();
    	utilsGui = new UtilsGui();
    	frame = frameIn;
    	panelMain = new JxPanel();
    	panelMain.setLayout(new BorderLayout());  // new CardLayout());//new BorderLayout());
    	lblSubtitle= new JLabel("subTitle",JLabel.CENTER);
    	lblSubtitle.setBackground(Color.white);
    	lblSubtitle.setOpaque(true);

    	panelMain.add(lblSubtitle, BorderLayout.PAGE_START);
    	panelMain.setBorder(new TitledBorder(""));
    	//panelMain.setPreferredSize(new Dimension(625,365));///////////////////////size width, height
    	
    	panelCalculationOption = new JxPanel();
    	panelCalculationOption.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));
        
    	
    	panelExecuteLog = new JxPanel(new BorderLayout());
    	
    	JScrollPane scrollpaneTable = new JScrollPane();
    	scrollpaneTable.setOpaque(false);
        table = new JTable();
        // not delete it:  cannot format given object as a number
        TableCellRendererDefault tcr = new TableCellRendererDefault();
        
        table.setRowHeight(25);
        table.setDefaultRenderer(Object.class, tcr);
        table.setDefaultRenderer(Integer.class, tcr);
        table.setDefaultRenderer(Number.class, tcr);
        table.setDefaultRenderer(Double.class, tcr); 
        table.setDefaultRenderer(java.util.Date.class, tcr); 
        
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setGridColor(table.getTableHeader().getBackground());

        /* table.setColumnSelectionAllowed(true);*/
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// select a single row only       

        //table.setSurrendersFocusOnKeystroke(true);
        ListSelectionModel lsm;
           lsm = table.getSelectionModel();
            //rowSM.addListSelectionListener(new ListSelectionListener() {
            lsm.addListSelectionListener(new ListSelectionListener()
            {
                public void valueChanged(ListSelectionEvent e)
                {
                    //Ignore extra messages.
                    if (e.getValueIsAdjusting()) return;
                                        
                    
                }
            });

        scrollpaneTable.setViewportView(table);
        scrollpaneTable.setPreferredSize(new Dimension(370, 240));
    	
        tableModel= new TableModelReadOnly();
        table.setModel(tableModel);
            	
        
        JxPanel panelButtons = new JxPanel(new FlowLayout());
        
         btnExecute = new JButtonForPanelDecorated("εκτέλεση");
    	 btnExecute.setIcon(ICO_TASK);
          btnExecute.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	            execute();
	        }
	      });    	
    	panelButtons.add(btnExecute);
        
        
        panelExecuteLog.add(scrollpaneTable, BorderLayout.CENTER);
        
    	panelDFilters = new JxPanel();
    	panelDataFilter = new PanelDataFilter(frame);
    	//panelDataFilter.setOpaque(true);
        panelDFilters.add(panelDataFilter.getPanelFilters());
         
     //   panelUpdateWithCriteria = new PanelUpdateWithCriteria(frame);

    	JxPanel panelCenter = new JxPanel();
    	panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.PAGE_AXIS));//new FlowLayout());
    	
        panelCenter.add(panelCalculationOption);
        panelCenter.add(panelDFilters);
        
    	panelCenter.add(panelButtons);
    	panelCenter.add(panelExecuteLog);
    	
    	//JScrollPane scrol = new JScrollPane();
    	//scrol.setOpaque(true);
//    	scrol.setViewportView(panelCenter);
    /*	JPanel panelCenterOrientation = new JPanel(new GridBagLayout());
    	panelCenterOrientation.add(
            panelCenter,
            new GridBagConstraints(
                0,
                0,
                1,
                1,
                1.0,
                0.0,
                GridBagConstraints.CENTER,
                GridBagConstraints.CENTER,
                new Insets(18, 18, 17, 17),
                0,
                0));
    	  	
    	panelMain.add(panelCenterOrientation, BorderLayout.CENTER);*/
    	//panelMain.add(panelCenter, BorderLayout.CENTER);
    	panelMain.add(panelCenter, BorderLayout.PAGE_END);

    	//this.setLayout(new BorderLayout());
    	this.add(panelMain);
    	
    }
   
        private boolean areFieldsCompleted()
        {
            boolean areFieldsCompleted = panelDataFilter.checkIfFieldsAreCompleted();
            return areFieldsCompleted;
        }
   
        
/*   private void setStepNumber(int pgCurrent)
   {

        String taskSubTitle="";
        //taskSubTitle="-> Επιλογές φιλτραρίσματος εγγραφών."; 
        
       

  
   	 // System.out.println("PanelTaskWithSteps.setStepNumber "+pgCurrent+" "+pageTotal);
   	  lblSubtitle.revalidate();
   	  if(pgCurrent==pageTotal)
   	  {
   	  	btnExecute.setEnabled(true);
   	  	btnNext.setEnabled(false);
   	  }
   	  else
   	  {
   	    btnExecute.setEnabled(false);
   	    btnNext.setEnabled(true);
   	  }
   	  
   	  if(calculationOption.length>1) // if more than one task
   	  {
   	     if(pgCurrent==1)
   	     {
   	     	btnPrevious.setEnabled(false);
   	    	panelCalculationOption.setVisible(true);
   	    	panelDFilters.setVisible(false);
   	    	panelExecuteLog.setVisible(false);  
   	    //	panelUpdateWithCriteria.setVisible(false);  
   	    	taskSubTitle="";

   	     }
   	     else if (pgCurrent==2)
   	     {
        
   	      panelDataFilter.setEntity(entityFilterSettings,entityGroupOfComps,PANEL_FILTER_TASK,panelManagement);
   	  	    
   	  	btnPrevious.setEnabled(true);
   	    	panelDFilters.setVisible(true);
   	    	panelCalculationOption.setVisible(false);
   	    	panelExecuteLog.setVisible(false); 
   	    //	panelUpdateWithCriteria.setVisible(false);  
   	    	taskSubTitle="-> Επιλογές φιλτραρίσματος εγγραφών.";
   	     }
   	    /* else if (pgCurrent==3 && !isNullify)
   	     {   	      
   	     	btnPrevious.setEnabled(true);
   	    	panelDFilters.setVisible(false);
   	    	panelCalculationOption.setVisible(false);
   	    	panelExecuteLog.setVisible(false);    	         
   	     /*    panelUpdateWithCriteria.setVisible(true);  
   	         if(panelUpdateWithCriteria.getCountOfCriteriaUpdateLines()==0)
   	         {
   	             panelUpdateWithCriteria.addCriteriaUpdateLine(); 	
   	         }
   	         taskSubTitle="-> Εισάγετε κριτήρια υπολογισμού";  */
   	         
   	     //}
   	 /*    else if(pgCurrent==pageTotal)
   	     {
   	     	btnPrevious.setEnabled(true);
   	    	  panelExecuteLog.setVisible(true); 
   	    	  panelDFilters.setVisible(false);
   	    	  panelCalculationOption.setVisible(false);
   	    //	  panelUpdateWithCriteria.setVisible(false);  
   	    	  taskSubTitle="-> Πατήστε 'εκτέλεση' για υπολογισμό.";
   	  	  
   	     }
   	     else
   	     {
   	     	btnPrevious.setEnabled(false);
   	    	panelDFilters.setVisible(false);
   	     	panelCalculationOption.setVisible(false);
   	     	panelExecuteLog.setVisible(false);   	  
   	    // 	panelUpdateWithCriteria.setVisible(false); 
   	     	taskSubTitle=""; 	
   	     } 
   	  }
   	  else  // if less than one task
   	  {
   	     if (pgCurrent==1)
   	     {
        
   	        panelDataFilter.setEntity(entityFilterSettings,entityGroupOfComps,PANEL_FILTER_TASK, panelManagement);
   	  	    
   	  	    btnPrevious.setEnabled(false);
   	    	panelDFilters.setVisible(true);
   	    	panelCalculationOption.setVisible(false);
   	    	panelExecuteLog.setVisible(false); 
   	    //	panelUpdateWithCriteria.setVisible(false);
   	    	taskSubTitle="-> Επιλογές φιλτραρίσματος εγγραφών.";    
   	     }
   	    /* else if (pgCurrent==2 && !isNullify)
   	     {
          	  
          	btnPrevious.setEnabled(true);  	
   	    	panelDFilters.setVisible(false);
   	    	panelCalculationOption.setVisible(false);
   	    	panelExecuteLog.setVisible(false); 
   	    	panelUpdateWithCriteria.setVisible(true); 
   	    	 
   	    	 if(panelUpdateWithCriteria.getCountOfCriteriaUpdateLines()==0)
   	         {
   	             panelUpdateWithCriteria.addCriteriaUpdateLine(); 	
   	         }  
   	        taskSubTitle="-> Εισάγετε/αφαιρέστε συνθήκες υπολογισμού.";*/
   	     //}    	        	  	
   	   /*  else if(pgCurrent==pageTotal)
   	     {
   	     	  btnPrevious.setEnabled(true);
   	    	  panelExecuteLog.setVisible(true); 
   	    	  panelDFilters.setVisible(false);
   	    	  panelCalculationOption.setVisible(false);
   	   // 	  panelUpdateWithCriteria.setVisible(false); 
   	    	  taskSubTitle="-> Πατήστε 'εκτέλεση' για υπολογισμό.";
   	  	  
   	     }
   	     else
   	     {
   	     	btnPrevious.setEnabled(true);
   	    	panelDFilters.setVisible(false);
   	     	panelCalculationOption.setVisible(false);
   	     	panelExecuteLog.setVisible(false); 
   	 //    	panelUpdateWithCriteria.setVisible(false);  
   	     	taskSubTitle=""; 	  	
   	     }    	  	
   	  }
     
   	  // html colors
   	  // http://www.w3schools.com/Html/html_colors.asp
   	  lblSubtitle.setText("<html><table>"  //  bgcolor='F8F8F8'
   	  	+"<tr><td align='center'  bgcolor='F0F0F0'><b>"+caption+"</b></td></tr>"
   	  	+"<tr><td align='center'> <FONT COLOR='#707070'>"+subTitle+"</FONT></td></tr>"
   	  	+"<tr><td align='center'> <FONT COLOR='#585858'>"+taskSubTitle+"</FONT><b> (βήμα "+pgCurrent+" από "+pageTotal+")"+"</b></td></tr>"
   	  	+"</table></html>");   	  
   	
   }*/
   
   public void setEntity(String captionIn, EntityTask[] entityTaskArrayIn, PanelManagement panelManagementIn)
   {
   //public void setEntity(String nameIn,String captionIn,String subTitleIn, String[] calculationOptionIn, 
   //EntityFilterSettings[] entityFilterSettingsIn,EntityQuery[] entityQueryIn, boolean isNullifyIn)
      entityTaskArray=entityTaskArrayIn;
   
         if(entityTaskArray!=null)
         {
             ButtonGroup group = new ButtonGroup();
             for(int t= 0;t<entityTaskArray.length;t++)
             {
            JRadioButton radio = new JRadioButton(entityTaskArray[t].getCaption());
            radio.setToolTipText(entityTaskArray[t].getSubTitle());
            radio.setOpaque(false);
            radio.setActionCommand(entityTaskArray[t].getCaption());
            radio.addActionListener(new ActionSelectCalculationOption());
            if(t==0)
            {
            	radio.setSelected(true);
                strCalculationOption=entityTaskArray[0].getCaption();
            }
         
         
            group.add(radio);
            panelCalculationOption.add(radio); 
            panelCalculationOption.add(new JLabel("<html><table>"  //  bgcolor='F8F8F8'
   	  	+"<tr><td align='center' <FONT COLOR='#707070'>"+entityTaskArray[t].getSubTitle()+"</FONT></td></tr>"     
                    +"</table></html>"));   
                    
                    

             }
             
         }
         
         entityFilterSettings=entityTaskArray[0].getEntityFilterSettings();
         entityGroupOfComps = entityTaskArray[0].getEntityGroupOfComps();
         //name=entityTaskArray.getName();
         caption=captionIn;
         subTitle=entityTaskArray[0].getSubTitle();
         /*entityQuery=entityTask.getEntityQuery();
         calculationOption=entityTask.getCalculationType();
         entityFilterSettings=entityTask.getEntityFilterSettings();
         isNullify=entityTask.getIsNullify();
         tableForFilters=entityTask.getTableForFilters();
         entityGroupOfComps = entityTask.getEntityGroupOfComps();
         panelManagement=panelManagementIn;
         yearEnforce=entityTask.getYearEnforce();
         
         pageTotal=3;*/
         
         //System.out.println("PanelTask      entityGroupOfComps:"+entityGroupOfComps.length);
         
         
         //if(isNullify)
        // {
        // 	pageTotal=pageTotal-1;
        // }
         
         //pageCurrent=1;
        panelDataFilter.setEntity(entityFilterSettings,entityGroupOfComps,PANEL_FILTER_TASK,/*yearEnforce,*/panelManagement);        

        
   	  // html colors
   	  // http://www.w3schools.com/Html/html_colors.asp
   	  lblSubtitle.setText("<html><table>"  //  bgcolor='F8F8F8'
   	  	+"<tr><td align='center'  bgcolor='F0F0F0'><b>"+caption+"</b></td></tr>"
   	  	+"<tr><td align='center'> <FONT COLOR='#707070'>"+subTitle+"</FONT></td></tr>"
   	  	+"</table></html>");     	        
        
        
         //btnExecute.setEnabled(false);
       

      /* if(calculationOption.length>1)
       {
          ButtonGroup group = new ButtonGroup();
         for(int c=0;c<calculationOption.length;c++)  
         {
            JRadioButton radio = new JRadioButton(calculationOption[c]);
            radio.setActionCommand(calculationOption[c]);
            radio.addActionListener(new ActionSelectCalculationType());
            if(c==0)
            {
            	radio.setSelected(true);
         	    strCalculationOption=calculationOption[0];
            }
         
         
            group.add(radio);
            panelCalculationOption.add(radio);
          } 
       }
       else
       {
          intCalculationOption=0; // if >0 is selected in actioSelectCalculationType
       	  strCalculationOption=calculationOption[0];
       	            
       }*/
    /*        setStepNumber(1);   
       }
       else
       {
       	  intCalculationOption=0; // if >0 is selected in actioSelectCalculationType
       	  strCalculationOption=calculationOption[0];
       	  pageTotal=pageTotal-1;
       	  setStepNumber(1); 
       	  
       }*/
      
      
      /*
      String[] arFields = {  "ValueReturn","count" };
      String[] arFieldsCaption = { "αξία επιστροφής","πλήθος παρ/κών"  };
      String sqlWhereInUpdate = "WHERE i.dbyearId=d.dbyearId AND i.companyId = d.companyId AND i.dbYearDeliveryId=d.dbYearDeliveryId";
      String tableForUpdate = "frvapplicationHeader d";
      String fieldToCalculate = "payment";
      panelUpdateWithCriteria.setEntity(arFields,arFieldsCaption,tableForUpdate, sqlWhereInUpdate,fieldToCalculate,isNullify,tableForFilters);
      */
         
         
   }
   
   private void execute()
   {

   	  //System.out.println("PanelTaskWithSteps.execute  "+strCalculationOption);
   	 /*   String[] record = new String[colCount];
        for (int i = 0; i < colCount; i++) // for each field
        {
          record[i] = rs.getString(i + 1);
        }*/
       TableModelReadOnly dm = (TableModelReadOnly) table.getModel();
      int rowCount = dm.getRowCount();
      //Remove rows one by one from the end of the table
      for (int i = rowCount - 1; i >= 0; i--)
      {
       dm.deleteTableRow(i);//;.removeRow(i);
      }
        
   	  int colCount = 1;
   	  String[] record = new String[colCount];
   	  String[] headers = {"πληροφορίες"};// new String[colCount];
      
      
      
   	  ArrayList listEntityQuery = new ArrayList();
   	 entityQuery = entityTaskArray[intCalculationOption].getEntityQuery();
          for(int eq=0;eq<entityQuery.length;eq++)
   	  {
   	  	//if(entityQuery[eq].getType()==intCalculationOption)
   	  	//{
   	  	    listEntityQuery.add(entityQuery[eq]);
                    //System.out.println("PanelTask.execute .eq"+eq+"     intCalculationOption"+intCalculationOption+"     "+entityQuery[eq]);
   	  	//}
   	  }


       // add EntityQuery from UpdateWithCriteria
/*       EntityQuery [] updateEntityQueryFromUpdateWithCriteria = panelUpdateWithCriteria.getUpdateQueries();
   	  for(int e=0;e<updateEntityQueryFromUpdateWithCriteria.length;e++)
   	  {
   	    listEntityQuery.add(updateEntityQueryFromUpdateWithCriteria[e])	;
   	  }
   */	 
          ArrayList lstFieldsUncompleted = panelDataFilter.getListOfFieldsUncompleted();
    if(lstFieldsUncompleted.size()==0)	   
    {
        tableModel.addRow("Εκκίνηση εργασίας ("+strCalculationOption+")",headers); 
        
         Database dbTransaction = new Database();
         boolean successfulOutcome=false;
      try
      {
          dbTransaction.transactionLoadConnection();
          dbTransaction.setTransactionAutoCommit(false);
         //System.out.println("PanelTask.execute    listEntityQuery.size():"+listEntityQuery.size()+"    dbTransaction:"+dbTransaction); 	  

   	
   	for(int q=0;q<listEntityQuery.size();q++ )
   	{
            
   	     EntityQuery entQuery = (EntityQuery)listEntityQuery.get(q);
   	  
   	   String table = entQuery.getTableForFilters();

   	  // EntityFilterSettings[] entFilterSettings = entityFilterSettings;
   	  if(table!=null && !table.equals(""))
   	  {
   	   
         // if not null replace filter table
   	     EntityFilterSettings[] ent = new EntityFilterSettings[entityFilterSettings.length];
   	   
   	     for(int e=0;e<ent.length;e++)
   	     {      
   	          //System.out.println("PanelTaskWithSteps.execute "+e+" "+entityFilterSettings[e].dbTable+" "+ent.length);
   	         ent[e] = new EntityFilterSettings(entityFilterSettings[e].caption,entityFilterSettings[e].type,
   	         entityFilterSettings[e].variableType,entityFilterSettings[e].equivalence, entityFilterSettings[e].dbField,table,table,
                 null,entityFilterSettings[e].groupOfComps,entityFilterSettings[e].filterFromSelectedField,
                 entityFilterSettings[e].forEntityReportGroup,entityFilterSettings[e].getFieldObligatoryOrSuggest()); // (-1 all) filters apply to all the groups of data
   	     }
   	     panelDataFilter.setNewEntityFilterSettings(ent);
   	  }
   	  else //if(table==null && table.equals(""))
   	  {
   	   	  
   	      panelDataFilter.setNewEntityFilterSettings(entityFilterSettings);
   	      //System.out.println("PanelTaskWithSteps.execute '"+table+"'"+entityFilterSettings[1].dbTable);
   	  }
   	   
   	   

   	    
   	   String sqlWhere = panelDataFilter.getSubquery("");//,q);// (-1 all) filters apply to all the groups of data
       //System.out.println("PanelTaskWithSteps.execute >"+sqlWhere);
  	     
       if(entQuery.getQuery().contains("WHERE"))
       {
          sqlWhere=sqlWhere.replaceFirst("WHERE", "AND");
          //System.out.println("PanelTaskWithSteps.execute replace WHERE with AND");
       }
           
   	  

   	      System.out.println("PanelTask.execute  ::  q:"+q+"   size:"+listEntityQuery.size()+"    entQuery.getType:"+entQuery.getType()+"     getQuery():"+entQuery.getQuery());
   	     int ret = 0;
  	     if(entQuery.getType()==QUERY_UPDATE)
   	     {
   	     	String sql = entQuery.getQuery()+" "+sqlWhere;
   	        
   	        //ret = db.updateQuery(sql,"PanelTaskWithSteps.execute", true);
                System.out.println("PanelTask.execute   q:"+q+"  IF1   getIsUpdate:"+entQuery.getType()+"     update  sql:"+sql)	;
                ret = dbTransaction.transactionUpdateQuery(sql,"PanelTask.execute", true);
                
   	     }
             else if(entQuery.getType()==QUERY_UPDATE_STOREDPROCEDURE)
   	     { 

                 String sql = entQuery.getQuery();
                
                 if(entityFilterSettings!=null && entityFilterSettings.length>0)
                {           
                    EntityFilterSettings[] ent = new EntityFilterSettings[entityFilterSettings.length];
                    for(int e=0;e<ent.length;e++)
                    {
                            sql=sql.replaceFirst("#", panelDataFilter.getFilterValue(e));
                                   // System.out.println("PanelTask.execute ===============   e:"+e+"  "+panelDataFilter.getFilterValue(e)+"   sql:"+sql);
                    }
                }                
                System.out.println("PanelTask.execute   q:"+q+"  IF2   getIsUpdate:"+entQuery.getType()+" QUERY_UPDATE_STOREDPROCEDURE      update  sql:"+sql)	; 
   	        //ret = db.updateQuery(sql,"PanelTaskWithSteps.execute", true);
                ret = dbTransaction.transactionUpdateQuery(sql,"PanelTask.execute", true);
                
             }            
             else if (entQuery.getType()==QUERY_READ)
             {
   	     	System.out.println("PanelTask.execute rs   q:"+q+"   IF3    getIsUpdate:"+entQuery.getType()+"      getQuery():"+entQuery.getQuery());
                db.retrieveDBDataFromQuery(entQuery.getQuery(),"PanelTask.execute");
   	     	db.getRS();
   	     	
                 
             }
             else
   	     {
                System.out.println("PanelTask.execute   q:"+q+"  ELSE   getIsUpdate:"+entQuery.getType()+"   NOT DEFINED    update  sql:"+entQuery.getQuery())	;  
   	     }
 
   	     System.out.println("PanelTask.execute   ret:"+ret);
   	    
   	      
   	     if(ret==0)
   	     {
   	     	tableModel.addRow("Βρέθηκαν σφάλματα.    "+entQuery.getMessageFailure(),headers);
   	     	successfulOutcome=false;	
   	     	//break;	
   	     }
   	     else if(ret==1)
   	     {
   	        tableModel.addRow(ret+" "+entQuery.getMessageSuccess(),headers);		
   	        successfulOutcome=true;
   	     }
   	     else
   	     {
   	        tableModel.addRow(ret+" "+entQuery.getMessageSuccess(),headers);		
   	        successfulOutcome=true;
   	     }
   	     System.out.println("PanelTask.execute  q:"+q+"  :   size:"+ listEntityQuery.size()+"     successfulOutcome:"+successfulOutcome);
   	}// for q
        
        System.out.println("PanelTask.execute   commit all  dbTransactions:"+dbTransaction);    
        dbTransaction.transactionCommit();
        dbTransaction.updateShowWindowSuccessSave("");
        dbTransaction.setTransactionAutoCommit(true);               
             
       }
       catch(SQLException e)
       {
           dbTransaction.transactionRollback();
           System.out.println(" error  PanelTask.execute   rollBack  dbTransaction:"+dbTransaction);    
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           e.printStackTrace();     
         }           
       }
       finally
	{
            System.out.println("PanelTask.execute  finally      "+dbTransaction.isTransactionConnectionNull());
	      if (!dbTransaction.isTransactionConnectionNull())
              {
                  
	           dbTransaction.transactionClose();
              }
               closeDB();
        } 

      
      
   	  if(successfulOutcome)
   	  {
   	  	tableModel.addRow("Η εργασία ("+strCalculationOption+") ολοκληρώθηκε με επιτυχία.",headers);
   	  }
   	  else
   	  {
   	  	tableModel.addRow(" Έγιναν λάθη στην εργασία ("+strCalculationOption+").",headers);
   	  }
    }
    else
    {
        utilsGui.showMessageError("Παρακαλώ συμπληρώστε τα πεδία.");
    }
         
   	  
   	 /* record[1] = "success";
   	  tableModel.addRow(record,headers);

   	  record[2] = "finish";
   	  tableModel.addRow(record,headers);   	*/  
   }
   
      public void closeDB()
   {
   	
   	  db.releaseConnectionRs();
          db.releaseConnectionRsmd();
   	
   }
   
   
   class  ActionSelectCalculationOption extends AbstractAction                 
   {       
        public ActionSelectCalculationOption()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{  
      	    String sel = (String)e.getActionCommand(); 
            
            for(int ct =0; ct<entityTaskArray.length;ct++)
            {
            	
               if (sel.equalsIgnoreCase(entityTaskArray[ct].getCaption()))
               {
             	  intCalculationOption=ct;
             	  strCalculationOption=entityTaskArray[ct].getCaption();
               }
           		            	
            }
  
      	 // System.out.println("PanelTaskWithSteps.ActionSelectCalculationType "+intCalculationOption);
          
           /*    CardLayout cl = (CardLayout)(cards.getLayout());
             cl.show(cards, (String)sel);
             selectedPrintingType = (String) sel;
          
            //cmbPrintingTypeDevice.getSelectedItem();
            
            if (sel.equalsIgnoreCase(strPrintingTypeLaser))
            {
            	 isDotmatrix=false;
            }
            else if (sel.equalsIgnoreCase(strPrintingTypeDotMatrix))
            {
               isDotmatrix=true; 	
            }*/
            
      	}
    	
    }	
	
	
}
