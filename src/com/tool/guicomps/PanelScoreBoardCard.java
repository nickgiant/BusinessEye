 // created 12-09-2010

 
 package com.tool.guicomps;
 
 import com.tool.model.*;
 import com.tool.utils.*;
 import com.tool.gui.*;
 
 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
 import javax.swing.border.LineBorder;
 
public class PanelScoreBoardCard extends JxPanel implements Constants
{
    /*String text;
    Font font;
    private boolean showPanel;
    //BufferedImage open, closed;
    //Rectangle target;
    JButtonSlideDecorated btnShow;
    JSlidingPanel panelToContainOtherPanels;
    JLabel lblTitle;
    //JScrollPane scrollContainOther;
    JxPanel panelContained;
    //final int*/
    //    OFFSET = 30,
    //    PAD    =  5;
    private JButtonSlideDecorated btnTable;
    private TableModelReadOnly tableModel;
    private JxPanel panelComponent;
    private JxPanel panelButtons;
    private PanelCollapsable panelCallapsable;
    private JPanel panelFixed;
    private EntityDockableGraph entityDockableGraph;
    private int no ;
    private boolean showPanel;
    private PanelGraph panelGraph;
    private JScrollPane tableScrollPane;
    private UtilsTable utilsTable;
    private boolean isGraph = true;
    private JTableDec table;
    private UtilsDouble uDouble;
    private boolean isPanelCollapsableOrFixed = false;    // Collapsable= true, Fixed=false
    
    public PanelScoreBoardCard()
    {
    	

       utilsTable=new UtilsTable();
       uDouble=new UtilsDouble();
       
       panelButtons = new JxPanel();
       FlowLayout fl = new FlowLayout();
       fl.setHgap(0);
       fl.setVgap(0);
       panelButtons.setLayout(fl);
    	
        btnTable = new JButtonSlideDecorated();
       btnTable.setFocusable(false);
       btnTable.setIcon(ICO_TABLE16);
       btnTable.setToolTipText("εφμφάνιση πίνακα");
       //btnTable.setMargin(new Insets(1, 2, 1, 2));
       btnTable.setPreferredSize(new Dimension(20,19));// width, height
          btnTable.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        { 
	        	showGraphOrTable();
	        }
	      });   
  
  
       JButtonSlideDecorated btnHelp = new JButtonSlideDecorated();
       btnHelp.setFocusable(false);
       btnHelp.setIcon(ICO_UPDATE16);
       //btnHelp.setMargin(new Insets(1, 2, 1, 2));
       btnHelp.setPreferredSize(new Dimension(20,19));// width, height
       

       panelComponent = new JxPanel();
       panelComponent.setLayout(new CardLayout());   
       
       
       panelButtons.add(btnTable);
       panelButtons.add(btnHelp);    	
    	
    	

    	

    }
    
    public JPanel getPanel()// Collapsable= true, Fixed=false
    {
        //isPanelCollapsableOrFixed = isPanelCollapsableOrFixedIn;
        //System.out.println("PanelScoreBoardCard.getPanel                    isPanelCollapsableOrFixed:"+isPanelCollapsableOrFixed);
        
        JPanel retPanel = panelFixed;
        if(isPanelCollapsableOrFixed)
        {
            retPanel = panelCallapsable;
        }
        else
        {
            retPanel= panelFixed;
        }
        
        //System.out.println("PanelScoreBoardCard.getPanel       isPanelCollapsableOrFixed:"+isPanelCollapsableOrFixed+"    retPanel:"+retPanel);
        
    	return retPanel;
    }
    
    
    public void setEntity(EntityDockableGraph entityDockableGraphIn, int noIn, boolean showPanelIn)
    {
        uDouble = VariablesGlobal.globalUtilsDouble;
       entityDockableGraph = entityDockableGraphIn;
       no=noIn;
       showPanel=showPanelIn;
    	
    	
                int width=200;
                int height=280;
                if(entityDockableGraphIn.getGraphType()==GRAPH_TYPE_PIE)
                {
                	height=height-60;
                }
                else
                {
                	
                }   	
       panelComponent.setPreferredSize(new Dimension(width,height));  // (int width, int height) 
    	
        makeGraph();
        makeTable();
        isPanelCollapsableOrFixed = true;    // Collapsable= true, Fixed=false
    	if(isPanelCollapsableOrFixed)
        {
            makeCardCollapsable();	
        }
        else
        {
            makeCardFixed();
        }
	
    	
    }
    
    public void filter(EntityDockableGraph entityDockableGraphIn, String queryDetailNew)
    {
    	
    	panelGraph.setEntity(entityDockableGraphIn.getTitle(),entityDockableGraphIn.getGraphType(),entityDockableGraphIn.getQueryMaster(),queryDetailNew);
    	
        if(entityDockableGraph.getQueryDetail()!=null )
        {
          System.out.println("PanelScoreBoardCard.filter "+entityDockableGraph.getTitle()+"  DETAIL "+entityDockableGraph.getQueryDetail()+" queryDetailNew:"+queryDetailNew);
          tableModel.setQuery(queryDetailNew);
          table.revalidate();	
        }
        else if(entityDockableGraph.getQueryMaster()!=null && entityDockableGraph.getQueryDetail()==null)
        {
            System.out.println("PanelScoreBoardCard.filter "+entityDockableGraph.getTitle()+" MASTER  "+entityDockableGraph.getQueryMaster()+" queryDetailNew:"+queryDetailNew);
          tableModel.setQuery(queryDetailNew);
          table.revalidate();
        }
    	
        getPanel().revalidate();
        //panelCallapsable.revalidate();
    	
    }
    
    
    private void makeGraph()
    {
    	
    	
//                pd.setEntity(entityDockableGraph[e].getTitle(),entityDockableGraph[e].getGraphType(),entityDockableGraph[e].getQueryMaster(),entityDockableGraph[e].getQueryDetail());             
//                panelDockableDesktop.addDesktopComponent(pd,entityDockableGraph[e].getLeft(),entityDockableGraph[e].getTop(),entityDockableGraph[e].getRight(),entityDockableGraph[e].getBottom());
                panelGraph = new PanelGraph();
                panelGraph.setEntity(entityDockableGraph.getTitle(),entityDockableGraph.getGraphType(),entityDockableGraph.getQueryMaster(),entityDockableGraph.getQueryDetail());
                //panelGraph.setPreferredSize(new Dimension(200,200));
                
              
                
                /*int width=200;
                int height=200;
                if(entityDockableGraph.getGraphType()==GRAPH_TYPE_PIE)
                {
                	height=height-40;
                }
                else
                {
                	
                }*/

   

                panelComponent.add(panelGraph, "graph");          
                //panelComponent.add(panelGraph,BorderLayout.CENTER);    	

    }
    
    

    
    private void showGraphOrTable()
    {
    	
    
    if(isGraph)
    {

       CardLayout cl = (CardLayout)(panelComponent.getLayout());
       cl.show(panelComponent, "table");
    	isGraph=false;
    	btnTable.setIcon(ICO_CHARTBAR);
    	btnTable.setToolTipText("εφμφάνιση γραφήματος");
    }
    else
    {
       CardLayout cl = (CardLayout)(panelComponent.getLayout());
       cl.show(panelComponent, "graph");
    	isGraph=true;   
    	btnTable.setIcon(ICO_TABLE16); 	
    	btnTable.setToolTipText("εφμφάνιση πίνακα");
    }
    


    	
    	
    	
    }
    
    
    private void makeTable()
    {
    	
    	table = new JTableDec();
    	
        tableScrollPane= new JScrollPane();
        tableScrollPane.setViewportView(table);
        
        table.setEntity(false);
        
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setGridColor(CLR_TABLE_GRID);

        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//.SINGLE_SELECTION);// select a single row only  
          	
    	tableModel= new TableModelReadOnly();

        table.setModel(tableModel);
        
        if(entityDockableGraph.getQueryDetail()!=null )
        {
          tableModel.setQuery(entityDockableGraph.getQueryDetail());	
        }
        
        


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
    	
    	
    	panelComponent.add(tableScrollPane, "table");
    	
    }
    
    private void makeCardCollapsable()
    {
    	
    	 //panelCallapsable = new PanelCollapsable(panelComponent,panelButtons,entityDockableGraph.getTitle(),showPanel,CLR_PANEL_START,CLR_PANEL_END);    	
        panelCallapsable = new PanelCollapsable(panelComponent,panelButtons,entityDockableGraph.getTitle(),showPanel,CLR_PANEL_CARD_START,CLR_PANEL_CARD_END);    	
    	 panelCallapsable.revalidate();
    }

    private void makeCardFixed()
    {
    	
    	 //panelCallapsable = new PanelCollapsable(panelComponent,panelButtons,entityDockableGraph.getTitle(),showPanel,CLR_PANEL_START,CLR_PANEL_END);    	
     //   panelFixed = new PanelCollapsable(panelComponent,panelButtons,entityDockableGraph.getTitle(),showPanel,CLR_PANEL_START,CLR_PANEL_END);    	
    	
        panelFixed = new JPanel();
        
    
        
        

        panelFixed.setLayout(new BorderLayout());
       // showPanel=showPanelIn;
       JLabel lblTitle =new JLabel();
        Font fnt = new Font (lblTitle.getFont().getName(), Font.PLAIN,lblTitle.getFont().getSize()+1);//Font(String name, int style, int size) 
        

        
        lblTitle.setFont(fnt);
        //lblTitle.setForeground(CLR_PANEL_BORDER);
  
  
       /*JButtonSlideDecorated btnTable = new JButtonSlideDecorated();
       btnTable.setFocusable(false);
       btnTable.setIcon(ICO_TABLE16);
       //btnTable.setMargin(new Insets(1, 2, 1, 2));
       btnTable.setPreferredSize(new Dimension(20,19));// width, height
  
       JButtonSlideDecorated btnHelp = new JButtonSlideDecorated();
       btnHelp.setFocusable(false);
       btnHelp.setIcon(ICO_UPDATE16);
       //btnHelp.setMargin(new Insets(1, 2, 1, 2));
       btnHelp.setPreferredSize(new Dimension(20,19));// width, height*/
  
        JButtonSlideDecorated btnShow = new JButtonSlideDecorated();
        btnShow.setFocusable(false);
        //btnShow.setIconTextGap(0);
        //btnShow.setMargin(new Insets(1, 2, 1, 2));
        btnShow.setPreferredSize(new Dimension(20,19));// width, height
        //btnShow.setText("v");
        btnShow.setIcon(ICO_SHOWSLIDE);
        //btnShow.setVisible(false);
        btnShow.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {      
	        	/* showPanel = !showPanel;
	        	  if(showPanel)
	        	  {
	        	  	 toggleShowPanel(showPanel);
	        	  }
	        	  else
	        	  {
	        	  	 toggleShowPanel(showPanel);
	        	  }
	        	  */
                 
	        }
	    });  


       
       //JxPanel panelButtons = new JxPanel();
       FlowLayout fl = new FlowLayout();
       fl.setHgap(0);
       fl.setVgap(0);
       panelButtons.setLayout(fl);
       
       /*panelButtons.add(btnTable);
       panelButtons.add(btnHelp);*/

       
       
       
       panelButtons.add(btnShow);
       
        //JPanel panelTitle = new JPanel();
        PanelGradient panelTitle = new PanelGradient(CLR_PANEL_CARD_END,CLR_PANEL_CARD_START,50);//(this.getBackground().brighter().brighter(), this.getBackground().darker(),23);
        //panelTitle.setSize(new Dimension(12,12));
        panelTitle.setLayout(new BorderLayout());
        JPanel panelToContainOtherPanels = new JPanel();
       panelToContainOtherPanels.setBorder(BorderFactory.createLineBorder(CLR_PANEL_CARD_BORDER));  
       BorderLayout bl = new BorderLayout(); 
       panelToContainOtherPanels.setLayout(bl);
        //LineBorder line = new LineBorder(CLR_PANEL_BORDER, 2, true);
        panelTitle.setBorder(BorderFactory.createLineBorder(CLR_PANEL_CARD_BORDER));        
       // panelToContainOtherPanels.setBorder(BorderFactory.createLineBorder(CLR_PANEL_BORDER));
        
        //scrollContainOther= new JScrollPane();
        //scrollContainOther.setSize(new Dimension(10,10));
        //scrollContainOther.setBorder(null);
        //scrollContainOther.setViewportView(panelToContainOtherPanels);


    	lblTitle.setText("    "+entityDockableGraph.getTitle());
    	//showPanel = show;
    	//panelContained= panel;
    	//panelToContainOtherPanels.add(panelContained,BorderLayout.CENTER);

        panelTitle.add(lblTitle,BorderLayout.LINE_START);
        panelTitle.add(panelButtons,BorderLayout.LINE_END);

        panelToContainOtherPanels.add(panelTitle, BorderLayout.PAGE_START);
		panelToContainOtherPanels.add(panelComponent,BorderLayout.CENTER);

        //this.add(panelTitle, BorderLayout.PAGE_START);
        panelFixed.add(panelToContainOtherPanels, BorderLayout.CENTER);
        
        panelFixed.revalidate();
    }    
    
	/*private void filter()
	{
		/*for(int l=0;l<listPanelCollapsable.size();l++)
		{
			PanelCollapsable pCol = (PanelCollapsable)listPanelCollapsable.get(l);
				pCol.toggleShowPanel(false);
		}*/
		
		  
	/*	for(int e = 0; e<listPanelDockable.size();e++)
		{
		
		  final int eFinal = e;
		 Thread runner = new Thread()
		 {
		   public void run()
		   {   
		   	
		   PanelCollapsable panelCallapsable = (PanelCollapsable)listPanelCollapsable.get(eFinal);
		   	
		   	//for
		 
		   				
		    //PanelDockable panelDockable = (PanelDockable)listPanelDockable.get(eFinal);
		    PanelGraph panelDockable = (PanelGraph)listPanelDockable.get(eFinal);
		    

		    //System.out.println("PanelScoreBoard.setEntity master "+entityDockableGraph[e].getQueryMaster());
		    //System.out.println("PanelScoreBoard.setEntity detail "+entityDockableGraph[e].getQueryDetail());
            String queryDetail =entityDockableGraph[eFinal].getQueryDetail();
            //String queryDetailWithoutOrderby="";
            //String queryDetailOrderby="";
            String queryDetailNew="";
		    if(utilsString.hasQueryGroupby(queryDetail))
		    {
		    	String queryDetailWithoutGroupby = utilsString.getQueryWithoutGroupbyAndOrderby(queryDetail);
		    	String queryDetailGroupby = utilsString.getGroupbyAndOrderbySubQuery(queryDetail);
		    	if(utilsString.hasQueryWhere(queryDetailWithoutGroupby))
		    	{
		    		queryDetailNew = queryDetailWithoutGroupby+utilsString.replaseWhereWithAnd(panelDataFilter.getSubquery("",eFinal))+queryDetailGroupby;
		    	}
		    	else
		    	{
		    	    queryDetailNew = queryDetailWithoutGroupby+panelDataFilter.getSubquery("",eFinal)+queryDetailGroupby;	
		    	}

		    }
		    else if(utilsString.hasQueryOrderby(queryDetail))
		    {
		    	String queryDetailWithoutOrderby = utilsString.getQueryWithoutOrderby(queryDetail);
		    	String queryDetailOrderby = utilsString.getOrderbySubQuery(queryDetail);
		    	
		    	if(utilsString.hasQueryWhere(queryDetailWithoutOrderby))
		    	{
		    	   queryDetailNew = queryDetailWithoutOrderby+utilsString.replaseWhereWithAnd(panelDataFilter.getSubquery("",eFinal))+queryDetailOrderby;		    	
		    	}
		    	else
		    	{
		    		queryDetailNew = queryDetailWithoutOrderby+panelDataFilter.getSubquery("",eFinal)+queryDetailOrderby;		    	
		    	}
		    }

		    //System.out.println("PanelScoreBoard.setEntity "+queryDetailNew);
		    panelDockable.setEntity(entityDockableGraph[eFinal].getTitle(),entityDockableGraph[eFinal].getGraphType(),entityDockableGraph[eFinal].getQueryMaster(),queryDetailNew); 
            // panelGraph.setEntity(entityDockableGraph[e].getTitle(),entityDockableGraph[e].getGraphType(),entityDockableGraph[e].getQueryMaster(),entityDockableGraph[e].getQueryDetail());
            panelDockable.revalidate();
            
            panelCallapsable.toggleShowPanel(true);
		   }
		  };
		 
				runner.start();	 
		 }
		    //System.out.println("PanelScoreBoard.setEntity sub "+panelDataFilter.getSubquery());			

	}  */  
    
}