// created 22-10-2009

package com.tool.gui;


import com.tool.model.EntityGroupOfComps;
import com.tool.model.EntityFilterSettings;
import com.tool.model.*;
import com.tool.utils.*;
import com.tool.gui.*;
import com.tool.guicomps.Constants;
import com.tool.guicomps.JButtonForPanelDecorated;
import com.tool.guicomps.JPanelDecorated;
import com.tool.guicomps.JxPanel;
import com.tool.guicomps.PanelDataFilter;
import com.tool.guicomps.PanelManagement;
import com.tool.guicomps.PanelScoreBoardCard;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import java.lang.Thread;


//import javax.swing.JToolBar;
import java.awt.image.BufferedImage;

public class PanelScoreBoard extends JPanel implements Constants
{
 
//    private PanelDockableDesktop panelDockableDesktop;
    private PanelDataFilter panelDataFilter;
    private JFrame frame;
    private ArrayList listPanelCard;
    private ArrayList listPanelScoreBoardCard;
    private UtilsString utilsString;
    
    private EntityDockableGraph[] entityDockableGraph;
    private JxPanel panelComponentContainerLeft;
    private JxPanel panelComponentContainerRight;
    
    private ToolBarData toolBarData;
        private JButton btnPrintPreview;
        private JButton btnSetup;    
        private JButton btnCustomize;
    
    public PanelScoreBoard(JFrame frameIn)
    {
    	frame=frameIn;
    	listPanelCard = new ArrayList();
    	listPanelScoreBoardCard= new ArrayList();
    	utilsString = new UtilsString();
    	
       toolBarData = new ToolBarData();
       toolBarData.setFocusable(false);    	
    	
//    	panelDockableDesktop = new PanelDockableDesktop();
       panelComponentContainerLeft = new JxPanel();
       panelComponentContainerLeft.setLayout(new BoxLayout(panelComponentContainerLeft, BoxLayout.PAGE_AXIS));
       panelComponentContainerRight = new JxPanel();
       panelComponentContainerRight.setLayout(new BoxLayout(panelComponentContainerRight, BoxLayout.PAGE_AXIS));       
       JxPanel panelFilters = new JxPanel();
       panelFilters.setLayout(new FlowLayout());
       panelDataFilter = new PanelDataFilter(frame);
       JButtonForPanelDecorated btnFilter = new JButtonForPanelDecorated();
       btnFilter.setText("φιλτράρισμα");
       btnFilter.setIcon(ICO_FIND16);
       btnFilter.setFocusable(false);
          btnFilter.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        { 
	             filter();
	        }
	      });       	
       	
       	
       panelFilters.add(panelDataFilter.getPanelFilters());
       panelFilters.add(btnFilter);
       
       
       JPanel topPanel = new JPanel();
       topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
//       topPanel.add(toolBarData);
       topPanel.add(panelFilters);
       
       JPanelDecorated panelDecor = new JPanelDecorated();
       panelDecor.setLayout(new BorderLayout());//new BoxLayout(panelDecor, BoxLayout.PAGE_AXIS));//
       panelDecor.add(topPanel,BorderLayout.PAGE_START);//,BorderLayout.PAGE_START);
        
  
       JPanelDecorated panelIntoLeft = new JPanelDecorated();
       panelIntoLeft.setLayout(new BorderLayout());//new BoxLayout(panelIntoLeft, BoxLayout.PAGE_AXIS));
       panelIntoLeft.add(panelComponentContainerLeft, BorderLayout.PAGE_START);
       
       JPanelDecorated panelIntoRight = new JPanelDecorated();
       panelIntoRight.setLayout(new BorderLayout());//new BoxLayout(panelIntoRight, BoxLayout.PAGE_AXIS));  
       panelIntoRight.add(panelComponentContainerRight, BorderLayout.PAGE_START);
  
       JScrollPane scrollLeft = new JScrollPane();
       scrollLeft.setBorder(null);
//       scrollLeft.setViewportView(panelIntoLeft); 
       //panelIntoLeft.add(scrollLeft, BorderLayout.PAGE_START);
  
       
       JScrollPane scrollRight = new JScrollPane();
       scrollRight.setBorder(null);
//       scrollRight.setViewportView(panelIntoRight); 
       //panelIntoRight.add(scrollRight, BorderLayout.PAGE_START);

       JxPanel panelScrlLeft = new JxPanel();
       panelScrlLeft.setLayout(new BorderLayout());
      

       JPanel panelAll = new JPanel();
       panelAll.setLayout(new GridLayout(0,2));
       panelAll.add(panelIntoLeft);
       panelAll.add(panelIntoRight);       
       
       
       JScrollPane scroll = new JScrollPane();
       //scroll.setBorder(null);
       scroll.setViewportView(panelAll); 

       
   /*    JxPanel panelTwo =new JxPanel();
       panelTwo.setBorder(BorderFactory.createLineBorder(CLR_PANEL_BORDER));  
       panelTwo.setLayout(new GridLayout(0,2));
       panelTwo.add(scrollLeft);
       panelTwo.add(scrollRight);*/

       
       panelDecor.add(scroll, BorderLayout.CENTER);
       
       /*JPanelDecorated panelRoot = new JPanelDecorated();
       panelRoot.setLayout(new BorderLayout());
       panelRoot.add(panelDecor, BorderLayout.CENTER);*/       


       
       this.setLayout(new BorderLayout());
       this.add(panelDecor, BorderLayout.CENTER);
    }

    public void setEntity(EntityScoreBoard entityScoreBoard, PanelManagement panelManagement)
    {
    	 entityDockableGraph = entityScoreBoard.getEntityDockableGraph();
    	EntityFilterSettings[] entityFilterSettings = entityScoreBoard.getEntityFilterSettings();
    	EntityGroupOfComps[] entityGroupOfComps = entityScoreBoard.getEntityGroupOfComps();
       String yearEnforce = entityScoreBoard.getYearEnforce();
        panelDataFilter.setEntity(entityFilterSettings,entityGroupOfComps,PANEL_FILTER_SEARCH,/*yearEnforce,*/ panelManagement);

            //  PanelDockable pd = null;
              for(int e=0;e<entityDockableGraph.length;e++)
              {
              	//pd=new PanelDockable();
              	
//               pd.setEntity(entityDockableGraph[e].getTitle(),entityDockableGraph[e].getGraphType(),entityDockableGraph[e].getQueryMaster(),entityDockableGraph[e].getQueryDetail());             
//                panelDockableDesktop.addDesktopComponent(pd,entityDockableGraph[e].getLeft(),entityDockableGraph[e].getTop(),entityDockableGraph[e].getRight(),entityDockableGraph[e].getBottom());
              /*   PanelGraph panelGraph = new PanelGraph();
                panelGraph.setEntity(entityDockableGraph[e].getTitle(),entityDockableGraph[e].getGraphType(),entityDockableGraph[e].getQueryMaster(),entityDockableGraph[e].getQueryDetail());
                //panelGraph.setPreferredSize(new Dimension(200,200));
                
                JxPanel panelComponent = new JxPanel();
                
                int width=200;
                int height=200;
                if(entityDockableGraph[e].getGraphType()==GRAPH_TYPE_PIE)
                {
                	height=height-40;
                }
                else
                {
                	
                }
                panelComponent.setPreferredSize(new Dimension(width,height));  // (int width, int height) 
                panelComponent.setLayout(new BorderLayout());
                panelComponent.add(panelGraph,BorderLayout.CENTER);*/
                
                PanelScoreBoardCard psbc = new PanelScoreBoardCard();
                psbc.setEntity(entityDockableGraph[e], e, true);
                
                listPanelScoreBoardCard.add(psbc);
                
                if(e%2==0)
                {
                        panelComponentContainerLeft.add(psbc.getPanel());	// Collapsable= true, Fixed=false
                }
                else
                {
                	panelComponentContainerRight.add(psbc.getPanel());	   //  // Collapsable= true, Fixed=false
                }
                
              	
              	//System.out.println("PanelManagement graph "+e);
              }	
    }
	
 private void filter()
 {
        if(areFieldsCompleted())
        {   
		for(int e = 0; e<listPanelScoreBoardCard.size();e++)
		{
		   
		  final int eFinal = e;
		 Thread runner = new Thread()
		 {
		   public void run()
		   {   
		   	
		   PanelScoreBoardCard psbc = (PanelScoreBoardCard)listPanelScoreBoardCard.get(eFinal);
		   
		   
            String queryDetail =entityDockableGraph[eFinal].getQueryDetail();
            //String queryDetailWithoutOrderby="";
            //String queryDetailOrderby="";
            String queryDetailNew="";
		    if(utilsString.hasQueryGroupby(queryDetail))
		    {
		    	String queryDetailWithoutGroupby = utilsString.getQueryWithoutGroupByOrOrderBy(queryDetail);
		    	String queryDetailGroupby = utilsString.getGroupbyAndOrderbySubQuery(queryDetail);
		    	if(utilsString.hasQueryWhere(queryDetailWithoutGroupby))
		    	{
		    		queryDetailNew = queryDetailWithoutGroupby+utilsString.replaceWhereWithAnd(panelDataFilter.getSubquery("")/*,eFinal)*/)+queryDetailGroupby;
                                System.out.println("PanelScoreBoard.filter    hasQueryGroupby    queryDetailNew:"+queryDetailNew+"    '"+utilsString.replaceWhereWithAnd(panelDataFilter.getSubquery(""/*,eFinal*/))+"'");
		    	}
		    	else
		    	{
                            System.out.println("PanelScoreBoard.filter  NOT  hasQueryGroupby ");
		    	    queryDetailNew = queryDetailWithoutGroupby+panelDataFilter.getSubquery(""/*,eFinal*/)+queryDetailGroupby;	
		    	}

		    }
		    else if(utilsString.hasQueryOrderby(queryDetail))
		    {
		    	String queryDetailWithoutOrderby = utilsString.getQueryWithoutOrderby(queryDetail);
		    	String queryDetailOrderby = utilsString.getOrderbySubQuery(queryDetail);
		    	
		    	if(utilsString.hasQueryWhere(queryDetailWithoutOrderby))
		    	{
		    	   queryDetailNew = queryDetailWithoutOrderby+utilsString.replaceWhereWithAnd(panelDataFilter.getSubquery(""/*,eFinal*/))+queryDetailOrderby;
                           System.out.println("PanelScoreBoard.filter     hasQueryOrderby    queryDetailNew:"+queryDetailNew+"    '"+utilsString.replaceWhereWithAnd(panelDataFilter.getSubquery(""/*,eFinal*/))+"'");
		    	}
		    	else
		    	{
		    		queryDetailNew = queryDetailWithoutOrderby+panelDataFilter.getSubquery(""/*,eFinal*/)+queryDetailOrderby;		    	
		    	}
		    }
		   //System.out.println("PanelScoreBoard.filter master "+eFinal+" "+entityDockableGraph[eFinal].getQueryMaster());
		   //System.out.println("PanelScoreBoard.filter detail "+eFinal+" "+entityDockableGraph[eFinal].getQueryDetail());
		   		   
		   psbc.filter(entityDockableGraph[eFinal], queryDetailNew);		   	
           psbc.revalidate();

		   }
		  };
		 
				runner.start();	 
		}
        }// if are files completed 
			
 }				   				


  // called by panelManagement.prepareCloseOfTabPanel
 public void closePanel()
 {
		
 }

        private boolean areFieldsCompleted()
        {
            boolean areFieldsCompleted = panelDataFilter.checkIfFieldsAreCompleted();
            return areFieldsCompleted;
        } 
 
	
class ToolBarData extends JToolBar implements Constants
{
        
        public ToolBarData()
        {
            try
           {     initialize();   }
           catch (Exception e)
           {   e.printStackTrace();    }
        }


        private void initialize() throws Exception
        {
       // btnViewMultiR = new JButton();
       // btnViewOneR = new JButton();

        btnPrintPreview = new JButton();
        btnSetup = new JButton();
        /*mItemHtml = new JMenuItem("σε html");
        mItemPdf = new JMenuItem("σε txt");
        mItemExcel = new JMenuItem("σε excel");*/
        btnCustomize = new JButton();
        	
        setFloatable(false);
        setRollover(true);
        this.setOpaque(false);



                 
        
        btnPrintPreview.setText("<html>προεπισκόπηση <b>F7</b></html>");
        btnPrintPreview.setOpaque(false);
        //btnPrintPreview.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnPrintPreview.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnPrintPreview.setToolTipText("προεπισκόπηση εκτύπωσης");
        btnPrintPreview.setIcon(ICO_PRINT_PREVIEW16);
        btnPrintPreview.setMnemonic(KeyEvent.VK_O);
        btnPrintPreview.setFocusable(false);
        btnPrintPreview.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
	        	//displayDialogReportSettings(); 
	        }
	    });
        /*Action actionPrintPreview = new ActionPrintPreview();
        btnPrintPreview.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrintPreview.getActionMap().put("report", actionPrintPreview);*/
        
        btnSetup.setText("<html>ρυθμίσεις</html>");
        btnSetup.setOpaque(false);
        //btnSetup.setText("<html>εξαγωγή <b>Ξ</b></html>");
        btnSetup.setToolTipText("ρυθμίσεις");
        btnSetup.setIcon(ICO_EDIT16);
        btnSetup.setFocusable(false);
        btnSetup.setMnemonic(KeyEvent.VK_J);
        btnSetup.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	DialogBoardSetup dlgSetup = new DialogBoardSetup(frame);
       	        dlgSetup.locateOnCenterOfTheScreen();
                dlgSetup.setVisible(true);	        	
	        	 //showExportMenu();
	        
	        }
	    });
       /* Action actionExport = new ActionExport();
        btnSetup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "export"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnSetup.getActionMap().put("export", actionExport);*/

    	/*UtilsMiscEntities utilsMiscEntities = new UtilsMiscEntities();
    	 entityExportFileType = utilsMiscEntities.getExportToFileEntities();    	
    	
    	String[] strExportFileType = new String[entityExportFileType.length];
    	for(int i=0;i<entityExportFileType.length;i++)
    	{
    		strExportFileType[i]=entityExportFileType[i].getCaption() +" ("+entityExportFileType[i].getExtension() +")";
    	   JMenuItem mItemExport = new JMenuItem("άμεση εξαγωγή σε αρχείο "+strExportFileType[i]);
    	   final int iFinal = i;
           mItemExport.addActionListener(new ActionListener()
           {
	           public void actionPerformed(ActionEvent e) 
	           {	   exportTo((String)entityExportFileType[iFinal].getName());  }
	       });    	   
    	   popupMenuExport.add(mItemExport);
    	}   */
        
        /*popupMenu.add( mItemHtml);
        popupMenu.add( mItemPdf);
        popupMenu.add( mItemExcel);
        
        mItemHtml.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   exportTo("html");  }
	    });

        mItemPdf.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   exportTo("txt");  }
	    });

        mItemExcel.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   exportTo("xls");  }
	    });*/

        btnCustomize.setText("<html>προσαρμογή</html>");
        btnCustomize.setOpaque(false);
        //btnCustomize.setText("<html>προτιμήσεις <b>Π</b></html>");
        btnCustomize.setToolTipText("προσαρμογή");
        btnCustomize.setIcon(ICO_TABLE_PREFS16);
        btnCustomize.setMnemonic(KeyEvent.VK_T);
        btnCustomize.setFocusable(false);
        btnCustomize.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	  
	        	 //displayDialogTablePreferences(); 
	        }
	    });
       /* Action actionTablePreferences = new ActionTablePreferences();
        btnCustomize.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), "prefs"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnCustomize.getActionMap().put("prefs", actionTablePreferences);*/
//
     //   MouseListener popupListener = new PopupListener();
  //      btnSetup.addMouseListener(popupListener);


        /*btnSetup.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   export();  }
	    });*/
        //IconSeparator icoSeparator = new IconSeparator();
        //JLabel lblIcoSeparator1 =new JLabel();
        


        
        //lblIcoSeparator1.setOpaque(false);
        //lblIcoSeparator.setHorizontalAlignment(SwingConstants.RIGHT);
         
         //lblIcoSeparator1.setIcon(icoSeparator);
         //lblIcoSeparator2.setIcon(icoSeparator);
         //lblIcoSeparator3.setIcon(icoSeparator);
          
        //add(lblIcoSeparator1);
        addSeparator();

        add(btnPrintPreview);
        add(btnSetup);
        add(btnCustomize);
        addSeparator();
        ///addSeparator();
        //addSeparator();
        }
        
        protected void paintComponent(Graphics g)
        {
             Graphics2D g2 = (Graphics2D) g;   //                     15
             GradientPaint paint = new GradientPaint(0, 0, this.getBackground().brighter(), 0, 30, this.getBackground().darker(),true);
             g2.setPaint(paint);
             g2.fill(getBounds());
             super.paintComponent(g);
        }
        
        
  }	
	
}

