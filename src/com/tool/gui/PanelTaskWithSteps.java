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

 public class PanelTaskWithSteps extends JxPanel implements Constants
{
        private JFrame frame;
        private String name;
        private String caption;
        private String subTitle;
        private JLabel lblSubtitle;
        private JLabel lblStepHeader;
        private int pageCurrent = 0;
        private int pageTotal = 0;
        private String[] calculationType;
        private int intCalculationType;
        private String strCalculationType;
        private EntityFilterSettings[] entityFilterSettings;
        private EntityQuery[] entityQuery;
        
        private JxPanel panelMain;
        private JxPanel panelCalculationType;
        private PanelDataFilter panelDataFilter;
      //  private PanelUpdateWithCriteria panelUpdateWithCriteria;
        private JxPanel panelDFilters;
        private JxPanel panelExecute;
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
        
    public PanelTaskWithSteps(JFrame frame)
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
    	panelMain.setPreferredSize(new Dimension(625,365));///////////////////////size width, height
    	
    	panelCalculationType = new JxPanel();
    	panelCalculationType.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));
    	
    	panelExecute = new JxPanel(new BorderLayout());
    	
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
    	
    	JButtonForPanelDecorated btnFirst = new JButtonForPanelDecorated("αρχικό");
    	btnFirst.setIcon(ICO_FIRST16);
          btnFirst.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	           	   pageCurrent=1;
	           	  setStepNumber(pageCurrent);
	        }
	      });    	
    	btnPrevious = new JButtonForPanelDecorated("προηγούμενο");
    	btnPrevious.setIcon(ICO_PREVIOUS16);
          btnPrevious.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	           if(pageCurrent>1)
	           {
	           	if(areFieldsCompleted())
                        {    
                             pageCurrent=pageCurrent-1; 
                             setStepNumber(pageCurrent);
                        }
	           	
                        
                             
                        
                        
	           }
	           else if(pageCurrent==1)
	           {
	           	   pageCurrent=1;
	           	  setStepNumber(pageCurrent);
	           	  
	           }
	            
	        }
	      });    	
        btnNext = new JButtonForPanelDecorated("επόμενο");
    	btnNext.setIcon(ICO_NEXT16);
          btnNext.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	        
	           if(pageCurrent<pageTotal)
	           {
	           	//System.out.println(pageCurrent+1);
	           	
                        if(areFieldsCompleted())
                        {    
                            pageCurrent=pageCurrent+1;    
                            setStepNumber(pageCurrent);                      
                        }
	           	
	           }
	           else if(pageCurrent==pageTotal)
	           {
	           	  setStepNumber(pageCurrent);
	           }
	        }
	      });    	
    	 btnExecute = new JButtonForPanelDecorated("εκτέλεση");
    	 btnExecute.setIcon(ICO_TASK);
          btnExecute.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	            execute();
	        }
	      });    	
    	
    	panelButtons.add(btnFirst);
    	panelButtons.add(btnPrevious);
    	panelButtons.add(btnNext);
    	panelButtons.add(btnExecute);
        
        panelExecute.add(scrollpaneTable, BorderLayout.CENTER);
        
    	panelDFilters = new JxPanel();
    	panelDataFilter = new PanelDataFilter(frame);
    	//panelDataFilter.setOpaque(true);
        panelDFilters.add(panelDataFilter.getPanelFilters());

     //   panelUpdateWithCriteria = new PanelUpdateWithCriteria(frame);

    	JxPanel panelCenter = new JxPanel();
    	panelCenter.setLayout(new FlowLayout()); // new BoxLayout(panelCenter, BoxLayout.PAGE_AXIS));//new FlowLayout());
    	panelCenter.add(panelCalculationType);
    	panelCenter.add(panelDFilters);
    //	panelCenter.add(panelUpdateWithCriteria);
    	panelCenter.add(panelExecute);
    	
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
    	panelMain.add(panelCenter, BorderLayout.CENTER);
    	panelMain.add(panelButtons, BorderLayout.PAGE_END);

    	//this.setLayout(new BorderLayout());
    	this.add(panelMain);
    	
    }
   
        private boolean areFieldsCompleted()
        {
            boolean areFieldsCompleted = panelDataFilter.checkIfFieldsAreCompleted();
            return areFieldsCompleted;
        }
   
        
   private void setStepNumber(int pgCurrent)
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
   	  
   	  if(calculationType.length>1) // if more than one task
   	  {
   	     if(pgCurrent==1)
   	     {
   	     	btnPrevious.setEnabled(false);
   	    	panelCalculationType.setVisible(true);
   	    	panelDFilters.setVisible(false);
   	    	panelExecute.setVisible(false);  
   	    //	panelUpdateWithCriteria.setVisible(false);  
   	    	taskSubTitle="";

   	     }
   	     else if (pgCurrent==2)
   	     {
        
   	      panelDataFilter.setEntity(entityFilterSettings,entityGroupOfComps,PANEL_FILTER_TASK,/*yearEnforce,*/panelManagement);
   	  	    
   	  	btnPrevious.setEnabled(true);
   	    	panelDFilters.setVisible(true);
   	    	panelCalculationType.setVisible(false);
   	    	panelExecute.setVisible(false); 
   	    //	panelUpdateWithCriteria.setVisible(false);  
   	    	taskSubTitle="-> Επιλογές φιλτραρίσματος εγγραφών.";
   	     }
   	    /* else if (pgCurrent==3 && !isNullify)
   	     {   	      
   	     	btnPrevious.setEnabled(true);
   	    	panelDFilters.setVisible(false);
   	    	panelCalculationType.setVisible(false);
   	    	panelExecute.setVisible(false);    	         
   	     /*    panelUpdateWithCriteria.setVisible(true);  
   	         if(panelUpdateWithCriteria.getCountOfCriteriaUpdateLines()==0)
   	         {
   	             panelUpdateWithCriteria.addCriteriaUpdateLine(); 	
   	         }
   	         taskSubTitle="-> Εισάγετε κριτήρια υπολογισμού";  */
   	         
   	     //}
   	     else if(pgCurrent==pageTotal)
   	     {
   	     	btnPrevious.setEnabled(true);
   	    	  panelExecute.setVisible(true); 
   	    	  panelDFilters.setVisible(false);
   	    	  panelCalculationType.setVisible(false);
   	    //	  panelUpdateWithCriteria.setVisible(false);  
   	    	  taskSubTitle="-> Πατήστε 'εκτέλεση' για υπολογισμό.";
   	  	  
   	     }
   	     else
   	     {
   	     	btnPrevious.setEnabled(false);
   	    	panelDFilters.setVisible(false);
   	     	panelCalculationType.setVisible(false);
   	     	panelExecute.setVisible(false);   	  
   	    // 	panelUpdateWithCriteria.setVisible(false); 
   	     	taskSubTitle=""; 	
   	     } 
   	  }
   	  else  // if less than one task
   	  {
   	     if (pgCurrent==1)
   	     {
        
   	        panelDataFilter.setEntity(entityFilterSettings,entityGroupOfComps,PANEL_FILTER_TASK, /*yearEnforce,*/panelManagement);
   	  	    
   	  	    btnPrevious.setEnabled(false);
   	    	panelDFilters.setVisible(true);
   	    	panelCalculationType.setVisible(false);
   	    	panelExecute.setVisible(false); 
   	    //	panelUpdateWithCriteria.setVisible(false);
   	    	taskSubTitle="-> Επιλογές φιλτραρίσματος εγγραφών.";    
   	     }
   	    /* else if (pgCurrent==2 && !isNullify)
   	     {
          	  
          	btnPrevious.setEnabled(true);  	
   	    	panelDFilters.setVisible(false);
   	    	panelCalculationType.setVisible(false);
   	    	panelExecute.setVisible(false); 
   	    	panelUpdateWithCriteria.setVisible(true); 
   	    	 
   	    	 if(panelUpdateWithCriteria.getCountOfCriteriaUpdateLines()==0)
   	         {
   	             panelUpdateWithCriteria.addCriteriaUpdateLine(); 	
   	         }  
   	        taskSubTitle="-> Εισάγετε/αφαιρέστε συνθήκες υπολογισμού.";*/
   	     //}    	        	  	
   	     else if(pgCurrent==pageTotal)
   	     {
   	     	  btnPrevious.setEnabled(true);
   	    	  panelExecute.setVisible(true); 
   	    	  panelDFilters.setVisible(false);
   	    	  panelCalculationType.setVisible(false);
   	   // 	  panelUpdateWithCriteria.setVisible(false); 
   	    	  taskSubTitle="-> Πατήστε 'εκτέλεση' για υπολογισμό.";
   	  	  
   	     }
   	     else
   	     {
   	     	btnPrevious.setEnabled(true);
   	    	panelDFilters.setVisible(false);
   	     	panelCalculationType.setVisible(false);
   	     	panelExecute.setVisible(false); 
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
   	
   }
   
   public void setEntity(EntityTask entityTask, PanelManagement panelManagementIn)
   {
   //public void setEntity(String nameIn,String captionIn,String subTitleIn, String[] calculationTypeIn, 
   //EntityFilterSettings[] entityFilterSettingsIn,EntityQuery[] entityQueryIn, boolean isNullifyIn)
      
   
         /*name=nameIn;
         caption=captionIn;
         subTitle=subTitleIn; 
         entityQuery=entityQueryIn;  
         isNullify=isNullifyIn;*/
         
         name=entityTask.getName();
         caption=entityTask.getCaption();
         subTitle=entityTask.getSubTitle();
         entityQuery=entityTask.getEntityQuery();
         calculationType=entityTask.getCalculationType();
         entityFilterSettings=entityTask.getEntityFilterSettings();
         isNullify=entityTask.getIsNullify();
         tableForFilters=entityTask.getTableForFilters();
         entityGroupOfComps = entityTask.getEntityGroupOfComps();
         panelManagement=panelManagementIn;
         yearEnforce=entityTask.getYearEnforce();
         
         pageTotal=3;
         
         
         
         
         //if(isNullify)
        // {
        // 	pageTotal=pageTotal-1;
        // }
         
         pageCurrent=1;
        

         btnExecute.setEnabled(false);
       

       if(calculationType.length>1)
       {
          ButtonGroup group = new ButtonGroup();
         for(int c=0;c<calculationType.length;c++)  
         {
            JRadioButton radio = new JRadioButton(calculationType[c]);
            radio.setActionCommand(calculationType[c]);
            radio.addActionListener(new ActionSelectCalculationType());
            if(c==0)
            {
            	radio.setSelected(true);
         	    strCalculationType=calculationType[0];
            }
         
         
            group.add(radio);
            panelCalculationType.add(radio);
          } 
            setStepNumber(1);   
       }
       else
       {
       	  intCalculationType=0; // if >0 is selected in actioSelectCalculationType
       	  strCalculationType=calculationType[0];
       	  pageTotal=pageTotal-1;
       	  setStepNumber(1); 
       	  
       }
      
      
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

   	  //System.out.println("PanelTaskWithSteps.execute  "+strCalculationType);
   	 /*   String[] record = new String[colCount];
        for (int i = 0; i < colCount; i++) // for each field
        {
          record[i] = rs.getString(i + 1);
        }*/
       
        
   	  int colCount = 1;
   	  String[] record = new String[colCount];
   	  String[] headers = {"πληροφορίες"};// new String[colCount];
      
      
   	  ArrayList listEntityQuery = new ArrayList();
   	  for(int eq=0;eq<entityQuery.length;eq++)
   	  {
   	  	if(entityQuery[eq].getType()==intCalculationType)
   	  	{
   	  	    listEntityQuery.add(entityQuery[eq]);	
   	  	}
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
        tableModel.addRow("Εκκίνηση εργασίας",headers); 
        
         Database dbTransaction = new Database();
         boolean successfulOutcome=false;
      try
      {
          dbTransaction.transactionLoadConnection();
          dbTransaction.setTransactionAutoCommit(false);
         System.out.println("PanelTaskWithSteps.execute    listEntityQuery.size():"+listEntityQuery.size()+"    dbTransaction:"+dbTransaction); 	  

   	
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

   	      System.out.println("PanelTaskWithSteps.execute    q:"+q+"   size:"+listEntityQuery.size()+"     entQuery.getType:"+entQuery.getType()+"     getQuery():"+entQuery.getQuery());
   	     int ret = 0;
   	     if(entQuery.getType()==QUERY_UPDATE)
   	     {
   	     	String sql = entQuery.getQuery()+" "+sqlWhere;
   	        
   	        //ret = db.updateQuery(sql,"PanelTaskWithSteps.execute", true);
                ret = dbTransaction.transactionUpdateQuery(sql,"PanelTaskWithSteps.execute", true);
                System.out.println("PanelTaskWithSteps.execute   q:"+q+"  IF   getIsUpdate:"+entQuery.getType()+"   ret:"+ret+"    update  sql:"+sql)	;
   	     }
             else if(entQuery.getType()==QUERY_UPDATE_STOREDPROCEDURE)
   	     { 
                System.out.println("PanelTaskWithSteps.execute   q:"+q+"  IF   getIsUpdate:"+entQuery.getType()+" QUERY_UPDATE_STOREDPROCEDURE    NOT IMPLEMENTED  ret:"+ret+"    update  sql:"+entQuery.getQuery())	; 
             }            
             else if (entQuery.getType()==QUERY_READ)
             {
   	     	db.retrieveDBDataFromQuery(entQuery.getQuery(),"PanelTaskWithSteps.execute");
   	     	db.getRS();
   	     	System.out.println("PanelTaskWithSteps.execute rs   q:"+q+"   ELSE    getIsUpdate:"+entQuery.getType()+"      getQuery():"+entQuery.getQuery());
                 
             }
             else
   	     {
                System.out.println("PanelTaskWithSteps.execute   q:"+q+"  IF   getIsUpdate:"+entQuery.getType()+"   NOT DEFINED  ret:"+ret+"    update  sql:"+entQuery.getQuery())	;  
   	     }
   	     
   	    
   	      
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
   	     System.out.println("PanelTaskWithSteps.execute  q:"+q+"     size:"+ listEntityQuery.size()+"     successfulOutcome:"+successfulOutcome+"     "+entQuery.getQuery());
   	}// for q
        
        System.out.println("PanelTaskWithSteps.execute   commit all  dbTransactions:"+dbTransaction);    
        dbTransaction.transactionCommit();
        dbTransaction.updateShowWindowSuccessSave("");
        dbTransaction.setTransactionAutoCommit(true);               
             
       }
       catch(SQLException e)
       {
           dbTransaction.transactionRollback();
           System.out.println(" error  PanelTaskWithSteps.execute   rollBack  dbTransaction:"+dbTransaction);    
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           e.printStackTrace();     
         }           
       }
       finally
	{
            System.out.println("PanelTaskWithSteps.execute  finally    O  "+dbTransaction.isTransactionConnectionNull());
	      if (!dbTransaction.isTransactionConnectionNull())
              {
                  
	           dbTransaction.transactionClose();
              }
              closeDB();
        } 

      
      
   	  if(successfulOutcome)
   	  {
   	  	tableModel.addRow("Η εργασία ("+strCalculationType+") ολοκληρώθηκε με επιτυχία.",headers);
   	  }
   	  else
   	  {
   	  	tableModel.addRow(" Έγιναν λάθη στην εργασία ("+strCalculationType+").",headers);
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
   
   
   class  ActionSelectCalculationType extends AbstractAction                 
   {       
        public ActionSelectCalculationType()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{  
      	    String sel = (String)e.getActionCommand(); 
            
            for(int ct =0; ct<calculationType.length;ct++)
            {
            	
               if (sel.equalsIgnoreCase(calculationType[ct]))
               {
             	  intCalculationType=ct;
             	  strCalculationType=calculationType[ct];
               }
           		            	
            }
  
      	 // System.out.println("PanelTaskWithSteps.ActionSelectCalculationType "+intCalculationType);
          
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
