package com.tool.guicomps;

import com.tool.rpt.PanelReportSettingsPreview;
import com.tool.gui.PanelScoreBoard;
import com.tool.gui.PanelOneDataManyRec;
import com.tool.gui.PanelOneDataOneRec;
//import com.tool.gui.PanelTwoDataManyRec;
//import com.tool.gui.PanelTwoDataOneRec;
import com.tool.model.EntityStatistics;
import com.tool.model.EntityParameter;
import com.tool.model.EntityInfoM;
import com.tool.model.EntityPanel;
//import com.tool.model.EntityInfoMany;
import com.tool.model.EntityInfo;
import com.tool.gui.*;
import static com.tool.guicomps.Constants.CLR_BUTTONPANEL_START;
import com.tool.jdbc.*;
import com.tool.model.*;
import com.tool.utils.*;
//import com.tool.lang.*;

import java.sql.Connection;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.KeyboardFocusManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.sql.SQLException;

//import java.util.Hashtable;
import java.text.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.JComponent;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.text.Position;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
 import javax.swing.SwingConstants;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

    //import java.util.Timer;
    //import java.util.TimerTask;
    import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


//import com.l2fprod.common.swing.JTaskPane;
//import com.l2fprod.common.swing.JTaskPaneGroup;

public class PanelManagement extends JxPanel implements Constants 
{
	
    private BevelBorder borderRaised = new BevelBorder(javax.swing.border.BevelBorder.RAISED);
    private JTree treeNavigation;
    //private NavigationTreeModel navigationTreeModel;
    //private JLabel lblCenterTop;
    //private JScrollPane treeScrollPane = new JScrollPane();
    private JScrollPane taskPaneScrollPane= new JScrollPane();
    private JScrollPane menuPanelScrollPane= new JScrollPane();
    
    private JxPanel panelCenterLeft = new JxPanel();
    private JxPanel panelCenterRight = new JxPanel();
    private JxPanel panelCenterLeftBottom = new JxPanel(); 
    private JxPanel panelEmpty = new JxPanel();
    private PanelOneDataManyRec panelOneDataManyRec;
    private PanelOneDataOneRec panelOneDataOneRec;
    //private PanelTwoDataOneRec panelTwoDataOneRec;
    //private PanelTwoDataManyRec panelTwoDataManyRec;
    private PanelReportSettingsPreview panelReportSettingsPreview;
    private PanelStatistics panelStatistics;
    private PanelTask panelTask;
    private PanelDataImportExport panelDataImport;
    private PanelDataImportExport panelDataExport;
    private PanelCopyAllFromCompany panelDataCopyFromCompany;
    private PanelScoreBoard panelScoreBoard;
    private PanelManyDataManyRec panelManyDataManyRec;
    //private PanelDockableDesktop panelDockableDesktop;
    //private JPanel panelNoTabs;
//    private PanelGradientBackground panelNoTabs;  // background panel to add components
    private JPanelDecorated panelNoTabs;  // background panel to add components
    //String url = "http://www.businesseye.gr";
    //private PanelDockableDesktop panelDockableDesktop;
    //private PanelReport panelReport;

    private JSplitPane splitPane = new JSplitPane();
    private PanelMulti panelMulti = new PanelMulti();

   //private DatabaseMeta dbm = new DatabaseMeta();    
   //private Database db= new Database();    
   
   private EntityInfo entityInfo;
   private EntityInfoM entityInfoM;
   
   //private Report report;
   //private ReportM reportM;
   private String userId;
   private String year;
   
   private DefaultMutableTreeNode entityNode;
   private DefaultTreeCellRenderer treeRenderer;
   
  // private  JCalendar calendar;
   private PanelDateChooser panelDateChooser;
//   Timer timer;
   Thread thread;
   
   //private EntityData entityData;
   //private EntityPanel[] entityPanel;
   
   private JTabbedPaneWithCloseBtn tabbedPane;
   private UtilsDate utilsDate;
   private JFrame frame;
   private JPopupMenu tabPopup ;
   private Object selectedTreeObject;
   //private Object menuObject;
   private int intMenu;
//   private Object objEntity;
   //private boolean isTreePanelVisible = true;
   private ArrayList listEntitiesMenu;
   
   private DataTree dataTree;
  //private TabWithClose tabWithClose;
   private ArrayList listOfTabPanes;
   private ArrayList listOfTypeOfTabPanes;
   
   //private JTaskPane taskPane;
   private JxPanelMenu menuPanel;
   //private JPanelCurvedGradient menuPanel;
   private JPanel panelMenuContainer;
   //static ResourceManager RESOURCE = ResourceManager.get(TaskPaneMain.class);
   
   private JxPanel pnlTopBackground ;
   
   private ArrayList listMenuGroup ;
   private boolean isExpanded = false;  
   private WindowWait windowwait;	
   	
   private JLabelGradient lblModule;
   private WindowMenu wmnu;	
   
   
    public PanelManagement(JFrame frame)
    {
        try {
            initialize(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    Action makeAction(String title, String tooltiptext, ImageIcon icon) {
      Action action = new AbstractAction(title) {
        public void actionPerformed(ActionEvent e) {}
      };
      action.putValue(Action.SMALL_ICON, icon);
      action.putValue(Action.SHORT_DESCRIPTION, tooltiptext);
      return action;
    }

    private void initialize(JFrame frameIn) throws Exception
    {
     frame= frameIn;
         
          
         //taskPane = new JTaskPane();   
         menuPanel = new JxPanelMenu();
         JLabel lblColor = new JLabel();
        //Color blue= new Color(114, 166, 252);
          //      Color blue= new Color(120, 204, 255);
         //   Color lightblue = new Color(177, 225, 225); //blue.brighter().brighter();  
              Color blue= new Color(129, 169, 226);
              Color lightBlue= new Color(220,235,253);//148, 215, 254);
        //menuPanel = new JPanelCurvedGradient(blue,lightBlue,0,0);       

       Color col1 = lblColor.getBackground();
       Color col2 = Color.white;//lblColor.getBackground().brighter().brighter().brighter().brighter().brighter();
       	
       //	menuPanel = new JPanelCurvedGradient(col1,col2,0,0);  
         
              utilsDate = new UtilsDate();
       panelDateChooser = new PanelDateChooser();
       listOfTabPanes = new ArrayList();
       listOfTypeOfTabPanes= new ArrayList();
       
 
          panelNoTabs = new JPanelDecorated();
        //panelNoTabs = new JPanelDecorated();//(blue,lightBlue,0,0);           
        //panelNoTabs = new PanelGradientBackground(CLR_PANEL_START_ALTER,CLR_PANEL_END_ALTER,0,0);//PanelGradient(lblTitle.getBackground().brighter().brighter(),lblTitle.getBackground().darker(),60);//new PanelTitle(new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),Color.white,"title");
        

          // globe
        /* JLabel l= new JLabel();
         l.setIcon(APP_LOGO);
         panelNoTabs.setLayout(new GridBagLayout());
         panelNoTabs.add(l, new GridBagConstraints(
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
                0));*/
    
         // clock
         

   
   
         
   
         panelNoTabs.setLayout(new BorderLayout());
         
         
       
         //clockAnalog.setBounds(100,100,100,100);
         JLabel lblLogo= new JLabel();
      //   lblLogo.setIcon(ICO_BACK);  //(ICO_BACK_BIG);//ICO_BACK);//APP_LOGO);
         
         JxPanel panelClockNLogo = new JxPanel();
         panelClockNLogo.setBackground(Color.BLACK);


         
         
         //panelClockNLogo.setLayout(new BoxLayout(panelClockNLogo, BoxLayout.LINE_AXIS));//.PAGE_AXIS));
         
         panelClockNLogo.setLayout(new BorderLayout());//.PAGE_AXIS));
         panelClockNLogo.add(lblLogo, BorderLayout.CENTER);
         
         Note note1 =new Note();
         note1.setPushPinVisible(true);
         note1.setText("notes");
         Note note2 =new Note();
         note2.setPushPinVisible(true);
         note2.setText("notes");
         Note note3 =new Note();
         note3.setPushPinVisible(true);
         note3.setText("notes");
         JxPanel panelNotes = new JxPanel();
         panelNotes.setLayout(new BoxLayout(panelNotes, BoxLayout.LINE_AXIS));

         panelNotes.add(note1);
         panelNotes.add(note2);
         panelNotes.add(note3);


            // url = "http://www.businesseye.gr";
     //   JEditorPane editor = new JEditorPane(url);
     //    editor.setEditable(false);
     //    panelNoTabs.add(editor,BorderLayout.CENTER);
         
         //JxPanel panelDownRight = new JxPanel();
         //panelDownRight.setLayout(new BorderLayout()); //(panelDownRight, BoxLayout.LINE_AXIS));
         //panelDownRight.add(panelClockNLogo, BorderLayout.PAGE_END);
         //panelNoTabs.add(panelDownRight,BorderLayout.LINE_END);    
         //panelNoTabs.add(panelClockNLogo,BorderLayout.CENTER);    
         
         
        pnlTopBackground = new JxPanel();
         GridLayoutVariable layout = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 1);
        pnlTopBackground.setLayout(layout);
        //pnlTopBackground.setOpaque(false);
        //panelNoTabs.setOpaque(true);
        panelNoTabs.add(pnlTopBackground,BorderLayout.PAGE_START);
         
         
         
         //treeNavigation = new JTree();
        // entityData = new EntityData();
         //listEntitiesMenu = new ArrayList();
         tabbedPane = new JTabbedPaneWithCloseBtn();
         //tabbedPane.setUI(new AquaBarTabbedPaneUI()); 
         // PlasticTabbedPaneUI      AquaBarTabbedPaneUI
         	
         tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);//WRAP_TAB_LAYOUT);//.SCROLL_TAB_LAYOUT);//WRAP_TAB_LAYOUT);//SCROLL_TAB_LAYOUT);
         tabbedPane.setFocusable(true);
         tabbedPane.addMouseListener(new TabMouseListener(tabbedPane));
         tabbedPane.addChangeListener(new TabChangeListener(tabbedPane));
         //tabbedPane.addContainerListener(new TabContainerListener(tabbedPane));
         
         
         //tabWithClose = new TabWithClose();         
         //tabWithClose.setYearEnforce(tabbedPane);
         
        Action actionCloseSelTab = new ActionCloseSelTab();
        tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4 ,InputEvent.CTRL_DOWN_MASK ), "closeSelTab"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        tabbedPane.getActionMap().put("closeSelTab", actionCloseSelTab);
         
         
        Action actionShowWindowWithTabs = new ActionShowWindowWithTabs();
        //tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1 ,java.awt.event.InputEvent.CTRL_DOWN_MASK ), "showWindowWithTabs"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3 ,InputEvent.CTRL_DOWN_MASK ) , "showWindowWithTabs");      
        tabbedPane.getActionMap().put("showWindowWithTabs", actionShowWindowWithTabs);

 

        
        menuPanel.setLayout(new BorderLayout());//new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS,1));
        
        JPanel pnlMenu = new JPanel();
        BoxLayout bl = new BoxLayout(pnlMenu,BoxLayout.PAGE_AXIS);
        pnlMenu.setLayout(bl);
        
        menuPanelScrollPane.setViewportView(menuPanel);
        
        //taskPaneScrollPane.setViewportView(taskPane);

//        treeScrollPane.setViewportView(treeNavigation);

        pnlMenu.add(menuPanelScrollPane);
 //       pnlMenu.add(treeScrollPane);
        pnlMenu.add(taskPaneScrollPane);
        
        
        
        //treeScrollPane.setMinimumSize(new Dimension(165, 90));

        //panelCenterLeft.setLayout(new BoxLayout(panelCenterLeft,BoxLayout.Y_AXIS));
        panelCenterLeft.setLayout(new BorderLayout());
     
        //panelCenterLeft.add(lblCenterTop,BorderLayout.PAGE_START);
        
        panelCenterLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelCenterLeft.setMinimumSize(new Dimension(185, 200));//(int width, int height) 
        panelCenterLeft.setMaximumSize(new Dimension(170, 200));
        
       // panelCenterLeft.add(lblToday);
        panelCenterLeft.setBorder(null);//borderRaised
        
        //panelCenterLeftBottom.setMinimumSize(new Dimension(180, 90));
        panelCenterLeftBottom.setLayout(new BorderLayout());
        //panelCenterLeftBottom.add(panelDateChooser, BorderLayout.CENTER);
        

        JPanelDecorated pnlTopModule = new JPanelDecorated();
        pnlTopModule.setLayout(new BorderLayout());
        
        lblModule = new JLabelGradient(CLR_BUTTONPANEL_ROLLOVER_START,CLR_BUTTONPANEL_ROLLOVER_END,27);//(CLR_BUTTONPANEL_ROLLOVER_START,CLR_PANEL_END,13);
        lblModule.setBorder(BorderFactory.createLineBorder(CLR_BUTTONPANEL_ROLLOVER_END));//CLR_PANEL_BORDER));//Color.black));//(BorderFactory.createRaisedBevelBorder());//(BorderFactory.createLineBorder(Color.black));
        
        pnlTopModule.add(lblModule,BorderLayout.PAGE_START);
       // pnlTopModule.add(new ToolBarTree(),BorderLayout.PAGE_END);
        
        
        panelCenterLeft.add(pnlTopModule,BorderLayout.PAGE_START);
        panelCenterLeft.add(pnlMenu,BorderLayout.CENTER);
        panelCenterLeft.add(panelCenterLeftBottom,BorderLayout.PAGE_END);
        
        panelCenterRight.setLayout(new BorderLayout());
       // panelCenterRight.setBorder(borderRaised);
       //panelCenterRight.add(panelMulti, BorderLayout.CENTER);

        //splitPane.setDividerLocation(150);
  
        
        //splitPane.setBorder(borderRaised);
        splitPane.setBorder(null);
        //splitPane.setPreferredSize(new Dimension(600, 350));
        panelCenterLeft.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panelCenterRight.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));        
        
   
        splitPane.setLeftComponent(panelCenterLeft);
        splitPane.setRightComponent(panelCenterRight);

        //splitPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        //getContentPane().add(splitPane, BorderLayout.CENTER);//------------ center
        
        JxPanel panelTabs = new JxPanel();
        panelTabs.setOpaque(true);
        panelTabs.setLayout(new BoxLayout(panelTabs,BoxLayout.LINE_AXIS ));
        panelTabs.add(tabbedPane);
        //tabbedPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelTabs.add(panelNoTabs);
        panelCenterRight.add(panelTabs,BorderLayout.CENTER);
        //addShowTabWithPanel("caption 1", new JPanel());
        //addShowTabWithPanel("caption 2", new JPanel());
        
         setForwardKeys();
         setBackwardKeys();
        
        this.setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);//------------ center

     panelMenuContainer = new JPanel();
     panelMenuContainer.setLayout(new BoxLayout(panelMenuContainer, BoxLayout.PAGE_AXIS)); // new GridLayout(0,1)); //new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS,1)); //new BoxLayout(panelMenuContainer, BoxLayout.PAGE_AXIS));
     //panelMenuContainer.setOpaque(true);
     //panelMenuContainer.setBackground(Color.white);
     menuPanel.add(panelMenuContainer, BorderLayout.CENTER);//.PAGE_START);
         
//         showTreeView();
      
       showMenuPanelView();
         wmnu = new WindowMenu(null);
       
        //treeNavigation.setSelectionRow(0);// 0 is the root row
    }
      



   public void setSelectedMenuButton(EntityMenu entityMenu)
   {
   	//treeNavigation.setSelectionInterval(selectedRow,selectedRow);
   	//                 selectedTreeObject = treeNavigation.getLastSelectedPathComponent();
                navTreeSelection(entityMenu); 
   }

   

    private void setMainBackground(ArrayList listSections, int intSection)  
    {
         //pnlTopBackground.add(lblBackgroundTitle,BorderLayout.PAGE_START);         
        pnlTopBackground.removeAll();
        //pnlTopBackground.setOpaque(false);
         // final  EntityMenu em = null;//(EntityMenu)listSections.get(intSection);
           
        for(int e=0;e<listSections.size();e++)
        {
            EntityMenu  em = (EntityMenu)listSections.get(e);
            Icon icon = em.getEntityIcon();
            String caption = em.getEntityCaption();
            
            //JLabelGradient lblBackgroundTitle = new JLabelGradient(CLR_BUTTONPANEL_ROLLOVER_START,CLR_BUTTONPANEL_ROLLOVER_END,27);//(CLR_BUTTONPANEL_ROLLOVER_START,CLR_PANEL_END,13);
            JLabel lblBackgroundTitle = new JLabel();
            lblBackgroundTitle.setBackground(CLR_BUTTONPANEL_ROLLOVER_END);
            lblBackgroundTitle.setBorder(BorderFactory.createLineBorder(CLR_BUTTONPANEL_ROLLOVER_END));//CLR_PANEL_BORDER));//Color.black));//(BorderFactory.createRaisedBevelBorder());//(BorderFactory.createLineBorder(Color.black));
            lblBackgroundTitle.setOpaque(true);
            lblBackgroundTitle.setText("<html><table><tr><img src=\""+icon+"\"></td><td><b>"+caption+"</b></td></table></html>");
              //lblModule.setBackground(Color.WHITE);
            lblBackgroundTitle.setToolTipText("<html><table><tr><img src=\""+icon+"\"></td><td><b>"+caption+"</b></td></table></html>");
            
        DataTree dataTree = em.getEntitySectionDataTree();
        DataTreeNode dTreeNodeRoot = dataTree.getRootElement();            
                  int level =0;
      int countChildren = dTreeNodeRoot.getNumberOfChildren();//dataTreeIn.getRootElement().getNumberOfChildren();

 
             pnlTopBackground.add(lblBackgroundTitle);      

            JxPanel pnlbtnmenu1 = new JxPanel();
            GridLayoutVariable layout0 = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_ROWS, 1);
            pnlbtnmenu1.setLayout(layout0);//new BoxLayout(pnlbtnmenu, BoxLayout.LINE_AXIS));
            pnlbtnmenu1.setOpaque(false);
                         
      for(int n=0;n<countChildren;n++) // -------- level 0
      {
        DataTreeNode dTreeNodeChild = dTreeNodeRoot.getChildFromIndex(n); 
        EntityMenu entityMenuParent = (EntityMenu)dTreeNodeChild.getData();
            JLabel lblMenu1 = new JLabel();//(CLR_BUTTONPANEL_ROLLOVER_START,CLR_PANEL_END,13);
            lblMenu1.setBackground(CLR_PANEL_END);//CLR_BUTTONPANEL_ROLLOVER_START);
            lblMenu1.setIcon(entityMenuParent.getEntityIcon());
            lblMenu1.setText("<html><b>"+dTreeNodeChild.toString()+"</b></html>");
            lblMenu1.setBorder(BorderFactory.createLineBorder(CLR_BUTTONPANEL_ROLLOVER_END));
            lblMenu1.setOpaque(true);
           
            
            
           // pnlbtnmenu1.add(lblMenu1);
            
            JxPanel pnlChild1 = new JxPanel();
            GridLayoutVariable layout1 = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_ROWS, 7);
            pnlChild1.setLayout(layout1);
           //pnlChild1.setLayout(new FlowLayout());
            
            JxPanel pnlMenu2 = new JxPanel();
            GridLayoutVariable layout2 = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_ROWS, 7);
            pnlMenu2.setLayout(layout2);
            pnlMenu2.add(lblMenu1);
            pnlbtnmenu1.add(pnlMenu2);
            
            
          if(intSection==e)
           {   
               lblBackgroundTitle.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.BLACK));//.createLineBorder(CLR_TOOLTIP, 5));
            pnlbtnmenu1.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.BLACK));//.createLineBorder(CLR_TOOLTIP, 5));
           }
             //pnlbtnmenu.add(pnl2);
             if(dTreeNodeChild.hasNodeChildren())//----------- level 1
    	 {
    	     for(int v=0;v<dTreeNodeChild.getNumberOfChildren();v++)
      	     {
                   DataTreeNode dTreeNodeChild2 = dTreeNodeChild.getChildFromIndex(v);
                    JLabel lbl=null;
    	          final EntityMenu entityMenu = (EntityMenu)dTreeNodeChild2.getData();  
                        if(dTreeNodeChild2.hasNodeChildren()) //------------- level 2
    	                {
    	     for(int k=0;k<dTreeNodeChild2.getNumberOfChildren();k++)
      	     {
                  DataTreeNode dTreeNodeChild3 = dTreeNodeChild2.getChildFromIndex(k);
                    JLabel lbl3;
    	           EntityMenu entityMenu3 = (EntityMenu)dTreeNodeChild3.getData(); 

    	                lbl3 = new JLabel(dTreeNodeChild3.toString());  // subcategories
                        lbl3.setIcon(entityMenu3.getEntityIcon());
                        
                        lbl3.setOpaque(true);
                        lbl3.setBackground(Color.WHITE);
                        lbl3.setBorder(BorderFactory.createLineBorder(Color.WHITE));//CLR_BUTTONPANEL_ROLLOVER_END));

                            final JLabel ftnF3 = lbl3;
                         ftnF3.addMouseListener(new java.awt.event.MouseAdapter()
			{
				boolean isClicked = false;
				public void mouseClicked(MouseEvent e)
				{  
					if(!isClicked)
					{
					  // spFinal.showComponent();
                                            navTreeSelection(entityMenu3);
					   isClicked=true;
					}
					else if(isClicked)
					{					
					 // spFinal.hideComponent();
					  isClicked=false;
					}								
				}

				public void mousePressed(MouseEvent e)
				{
                    ftnF3.setBackground(CLR_BUTTONPANEL_START);//.setColors(CLR_BUTTONPANEL_START,CLR_BUTTONPANEL_END);//(Color.WHITE,CLR_BUTTONPANEL_START);
                    //panelMainMenuFinal.setColors(CLR_BUTTONPANEL_END,CLR_BUTTONPANEL_ROLLOVER);
				}

				public void mouseReleased(MouseEvent e)
				{
                                    ftnF3.setBackground(CLR_BUTTONPANEL_ROLLOVER_START);
                    //panelMainMenuFinal.setColors(CLR_BUTTONPANEL_ROLLOVER_START,CLR_BUTTONPANEL_ROLLOVER_END);    //(CLR_BUTTONPANEL_END,CLR_BUTTONPANEL_ROLLOVER);
                    //panelMainMenuFinal.setColors(Color.WHITE,CLR_BUTTONPANEL_START);
				}
				
				public void mouseEntered(MouseEvent e)
				{
                                    ftnF3.setBackground(CLR_BUTTONPANEL_ROLLOVER_START);
                     //panelMainMenuFinal.setColors(CLR_BUTTONPANEL_ROLLOVER_START,CLR_BUTTONPANEL_ROLLOVER_END);
                     //panelMainMenuFinal.setColors(CLR_BUTTONPANEL_START,CLR_BUTTONPANEL_END);
				}
				
				public void mouseExited(MouseEvent e)
				{
                                    ftnF3.setBackground(Color.WHITE);
                     //panelMainMenuFinal.setColors(Color.WHITE,CLR_BUTTONPANEL_START);//(CLR_BUTTONPANEL_START,CLR_BUTTONPANEL_END);
                     //panelMainMenuFinal.setColors(Color.WHITE,CLR_BUTTONPANEL_START);
				}
				
			});                               
                        
                        pnlChild1.add(lbl3);                   
                 
    	                /*lbl = new JLabel("<html><b>"+dTreeNodeChild2.toString()+"</b></html>");  // subcategories
                        lbl.setIcon(entityMenu.getEntityIcon());
                        pnlChild1.add(lbl);*/
             }
                       }
                        else
                        {
                            
    	              //lbl = new JLabel(dTreeNodeChild2.toString());  // subcategories
                       //lbl.setIcon(entityMenu.getEntityIcon());
                            final EntityMenu entityMenu2 = (EntityMenu)dTreeNodeChild2.getData();  
                            JLabel btn =new JLabel(dTreeNodeChild2.toString());
                            btn.setIcon(entityMenu.getEntityIcon());
                            btn.setOpaque(true);
                            btn.setBackground(Color.WHITE);
                            btn.setBorder(BorderFactory.createLineBorder(Color.WHITE));//CLR_BUTTONPANEL_ROLLOVER_END));
                            final JLabel ftnF = btn;
                         ftnF.addMouseListener(new java.awt.event.MouseAdapter()
			{
				boolean isClicked = false;
				public void mouseClicked(MouseEvent e)
				{  
					if(!isClicked)
					{
                                            navTreeSelection(entityMenu2);
					  // spFinal.showComponent();	
					   isClicked=true;
					}
					else if(isClicked)
					{					
					 // spFinal.hideComponent();
					  isClicked=false;
					}								
				}

				public void mousePressed(MouseEvent e)
				{
                    ftnF.setBackground(CLR_BUTTONPANEL_START);//.setColors(CLR_BUTTONPANEL_START,CLR_BUTTONPANEL_END);//(Color.WHITE,CLR_BUTTONPANEL_START);
                    //panelMainMenuFinal.setColors(CLR_BUTTONPANEL_END,CLR_BUTTONPANEL_ROLLOVER);
				}

				public void mouseReleased(MouseEvent e)
				{
                                    ftnF.setBackground(CLR_BUTTONPANEL_ROLLOVER_START);
                    //panelMainMenuFinal.setColors(CLR_BUTTONPANEL_ROLLOVER_START,CLR_BUTTONPANEL_ROLLOVER_END);    //(CLR_BUTTONPANEL_END,CLR_BUTTONPANEL_ROLLOVER);
                    //panelMainMenuFinal.setColors(Color.WHITE,CLR_BUTTONPANEL_START);
				}
				
				public void mouseEntered(MouseEvent e)
				{
                                    ftnF.setBackground(CLR_BUTTONPANEL_ROLLOVER_START);
                     //panelMainMenuFinal.setColors(CLR_BUTTONPANEL_ROLLOVER_START,CLR_BUTTONPANEL_ROLLOVER_END);
                     //panelMainMenuFinal.setColors(CLR_BUTTONPANEL_START,CLR_BUTTONPANEL_END);
				}
				
				public void mouseExited(MouseEvent e)
				{
                                    ftnF.setBackground(Color.WHITE);
                     //panelMainMenuFinal.setColors(Color.WHITE,CLR_BUTTONPANEL_START);//(CLR_BUTTONPANEL_START,CLR_BUTTONPANEL_END);
                     //panelMainMenuFinal.setColors(Color.WHITE,CLR_BUTTONPANEL_START);
				}
				
			});                               
                            pnlChild1.add(btn);
                        }
             
              
             }
             
        }
             pnlMenu2.add(pnlChild1);
           }
      pnlTopBackground.add(pnlbtnmenu1);
      //pnlTopBackground.add(pnlbtnmenu);
           }
        
   }
/*   public void setSelectedTreeRow(int selectedRow)
   {
   	treeNavigation.setSelectionInterval(selectedRow,selectedRow);
   	                 selectedTreeObject = treeNavigation.getLastSelectedPathComponent();
                //navTreeSelection(null); 
              
   }
*/
   
 /*    private void displayNewWindowWithPanel(EntityMenu entityMenu)
     {
     	//int selectedRow  = treeNavigation.getSelectionModel().getLeadSelectionRow();
     	
     	FrameNew f = new FrameNew();
     	f.setEntity( VariablesGlobal.appName+" "+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+" ετ:"+ VariablesGlobal.globalCompanyName+" χρ:"+VariablesGlobal.globalYear, entityMenu);
     	f.setVisible(true);
        
     	
     }  */
  
  public boolean isTreePanelVisible()
  {
  	return panelCenterLeft.isVisible();
  }



  /*private void showTreeView()
  {
    //addNodes(false);
    //treeScrollPane.setVisible(true);  	
    taskPaneScrollPane.setVisible(false);
    menuPanelScrollPane.setVisible(false); 
        
  }*/

  private void showTaskPaneView()
  {
  	//addNodes(false);
    //treeScrollPane.setVisible(false);  	
    taskPaneScrollPane.setVisible(true);
    menuPanelScrollPane.setVisible(false);  	
  }
  
  public void showMenuPanelView()
  {
    //addNodes(false);
    //treeScrollPane.setVisible(false);  	
    taskPaneScrollPane.setVisible(false);
    menuPanelScrollPane.setVisible(true);
  	
  }



  public void setTreePanelVisible()
  {
  	if(panelCenterLeft.isVisible())
  	{
  	  panelCenterLeft.setVisible(false);	
  	}
  	else
  	{
  	  panelCenterLeft.setVisible(true);	
  	}

  	    splitPane.setLeftComponent(panelCenterLeft);
        splitPane.setRightComponent(panelCenterRight);
  	 
  	 //System.out.println("PanelManagement.setTreePanelVisible "+visible);
  }
  
  public void initializePanel(int typeOfPanel)
  {
  	
  
  	
  	if(typeOfPanel ==PANEL_TYPE_ONEDATAMANYREC)// panel.equalsIgnoreCase("panelOneDataManyRec"))
  	{
  		//System.out.println("PanelManagement frame  "+frame);
  		panelOneDataManyRec = new PanelOneDataManyRec(frame);
  	}

  	if(typeOfPanel ==PANEL_TYPE_ONEDATAONEREC)//if(panel.equalsIgnoreCase("panelOneDataOneRec"))
  	{
  		
  	    	
         panelOneDataOneRec = new PanelOneDataOneRec(frame);
  	}
  	
  	/*if(typeOfPanel ==PANEL_TYPE_TWODATAONEREC)//if(panel.equalsIgnoreCase("panelTwoDataOneRec"))
  	{
         panelTwoDataOneRec = new PanelTwoDataOneRec(frame);
  	}*/
  	
  	/*if(typeOfPanel ==PANEL_TYPE_TWODATAMANYREC)//if(panel.equalsIgnoreCase("panelTwoDataManyRec"))
  	{
        panelTwoDataManyRec = new PanelTwoDataManyRec(frame);
  	} */ 	 
     //panelReport = new PanelReport();
    if(typeOfPanel ==PANEL_TYPE_REPORT)//if(panel.equalsIgnoreCase("panelReportSettingsPreview"))
  	{
        panelReportSettingsPreview =  new PanelReportSettingsPreview(frame);
  	}  
    
    if(typeOfPanel ==PANEL_TYPE_STATISTICS)//if(panel.equalsIgnoreCase("panelStatistics"))
  	{      	     
      panelStatistics =  new PanelStatistics(frame);
 	}
 	
    if(typeOfPanel ==PANEL_TYPE_TASK)//if(panel.equalsIgnoreCase("panelTask"))
  	{      	     
      panelTask =  new PanelTask(frame);
 	} 
    
        if(typeOfPanel ==PANEL_TYPE_TOOL)//if(panel.equalsIgnoreCase("panelTask"))
  	{      	  // IMPORT = 1;       EXPORT = 2;    
      panelDataImport =  new PanelDataImportExport(1);
      panelDataExport =  new PanelDataImportExport(2);
      panelDataCopyFromCompany = new PanelCopyAllFromCompany();
 	} 
    
 	if(	typeOfPanel == PANEL_TYPE_SCOREBOARD)
 	{
 		panelScoreBoard = new PanelScoreBoard(frame);
 		//panelDockableDesktop = new PanelDockableDesktop();
 	}
        
    if(typeOfPanel ==PANEL_TYPE_MANYDATAMANYREC_PARAMETERS)
  	{      	     
           panelManyDataManyRec =  new PanelManyDataManyRec(frame);
 	}        
        
        
 		
  }

  
  
  /*
  *
  * when relogin to hide the menu
  */
  public void panelMenuButtonRemove()
  {
   if(listMenuGroup!=null)   
   {
    for(int l=0;l<listMenuGroup.size();l++)
        {

        	JSlidingPanel jsp = (JSlidingPanel)listMenuGroup.get(l);
        	jsp.setVisible(false);//.showComponentWhenGuiLoaded();        		 	
            //    panelCenterLeft.setVisible(false);
        }  	       
   } 
      //panelMenuContainer.removeAll();
      
  }  
  
  
  
  
    public void setSectionActive(ArrayList listSections, int intSection)  
    {
        setMainBackground(listSections,intSection);

            EntityMenu em = (EntityMenu)listSections.get(intSection);
           
            
            Icon icon = em.getEntityIcon();
            String caption = em.getEntityCaption();
            
             lblModule.setText("<html><table><tr><img src=\""+icon+"\"></td><td><b>"+caption+"</b></td></table></html>");
              //lblModule.setBackground(Color.WHITE);
             lblModule.setToolTipText("<html><table><tr><img src=\""+icon+"\"></td><td><b>"+caption+"</b></td></table></html>");

    
      //  loadSectionMenu
        
        DataTree dataTree = em.getEntitySectionDataTree();
        DataTreeNode dtnr = dataTree.getRootElement();
        presentMenuPanelFromDataTree(dtnr);

      panelMenuExpandAll();
       
    }
  

  
  public void setDate(String date)
  {
       //System.out.println(VariablesGlobal.globalDate);
       DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String[] allowedPatternsToRead = {"dd-MM-yyyy","dd/MM/yyyy","dd-MM-yy","dd/MM/yy"};
       String d = utilsDate.reformatDateString(date,allowedPatternsToRead, "dd-MM-yyyy");
      //System.out.println("d "+d);
       //VariablesGlobal.globalDate = d;
       panelDateChooser.select(d, dateFormat);       
  }
  
  /*private void treeExpandAll()
  {
        Object[] path;

       path = navigationTreeModel.getPathToRoot(getNode(DATAENTRY));
       treeNavigation.expandPath(new TreePath(path));

       path = navigationTreeModel.getPathToRoot(getNode(REPORTS));
       treeNavigation.expandPath(new TreePath(path));

       // expand ech node that is reports
       for(int i =0; i<REPORTS_CAT_ARRAY.length;i++) 
       {
         path = navigationTreeModel.getPathToRoot(getNode(REPORTS_CAT_ARRAY[i]));
         treeNavigation.expandPath(new TreePath(path));
       }

        path = navigationTreeModel.getPathToRoot(getNode(METRICS));
        treeNavigation.expandPath(new TreePath(path));

        path = navigationTreeModel.getPathToRoot(getNode(PARAMETERS));
        treeNavigation.expandPath(new TreePath(path));


  	
  }*/
  
  // called by DialogMain.setCompanyYearUserDate
 public void panelMenuExpandAll()
  {

        
         //treeNavigation.setSelectionRow(0);// 0 is the root row 
     /*  entityData = new EntityData();
        

        
       boolean[] isNodeVisible ={true, true, true, true,false};
     	entityData = new EntityData();
       
       dataTree = entityData.loadAndGetDataTreeNode(isNodeVisible);
       */ 
       //presentJtreeFromDataTree(navigationTreeModel.getRootNode(), dataTree );        
       //presentTaskPaneFromDataTree(dataTree ); 
 //      listMenuGroup = new ArrayList();
  //     presentMenuPanelFromDataTree(dataTree);
      	
          	
         SwingUtilities.invokeLater(new Runnable()
         {
              public void run()
              {       	
                    panelButtonExpandOrCollapseAll(true); // expand on start
              }
         });	    
  }
  
  
  
  private void panelButtonExpandGroupNo(int noOfGroup)
  {
      
      final int  finalNoOfGroup = noOfGroup;
        	class ThreadShow extends Thread
        		{ // This method is called when the thread runs
        		 public void run()
        		 {
        		 	
                             for(int m=0;m<listMenuGroup.size();m++)
                             {
        	JSlidingPanel jsp = (JSlidingPanel)listMenuGroup.get(m);
        	jsp.showComponent();//.showComponentWhenGuiLoaded();        		 	
                             }
        		 }
        		 } 
            
            Thread thread = new ThreadShow();
            thread.start(); 
                
      
      
      
      
  }
  

  
  
  
  public void panelButtonExpandOrCollapseAll(boolean expand)
  {
  	 if(expand)   
  	 {
  	    for(int l=0;l<listMenuGroup.size();l++)
        {
        	final int lFin=l;
        	class ThreadShow extends Thread
        		{ // This method is called when the thread runs
        		 public void run()
        		 {
        		 	
        	JSlidingPanel jsp = (JSlidingPanel)listMenuGroup.get(lFin);
        	jsp.showComponent();//.showComponentWhenGuiLoaded();        		 	
        		 		
        		 }
        		 } 
            
            Thread thread = new ThreadShow();
            thread.start(); 
        }  	 	
  	 }
  	 else
  	 {
  	    for(int l=0;l<listMenuGroup.size();l++)
        {
        	final int lFin=l;
        	class ThreadHide extends Thread
        		{ // This method is called when the thread runs
        		 public void run()
        		 {
        		 	        	
        	JSlidingPanel jsp = (JSlidingPanel)listMenuGroup.get(lFin);
        	jsp.hideComponent();
        		 		
        		 }
        		 } 
            
            Thread thread = new ThreadHide();
            thread.start();         	
        }  	   	 	
  	 }

  }
  
  
  private void showWindowMenu(boolean show, JComponent comp)
  {
      
       if(show)
       {
          wmnu.setWindowLocation(comp);
         wmnu.setVisible();
         show = false;
       }
       else
       {
          wmnu.setVisibleNot();
          show = true;
       }
  
  }
  
  
  
 /* private DefaultMutableTreeNode getNode(String title)
  {
  	int startRow = 0;
    //TreePath path = null;
    TreePath path = treeNavigation.getNextMatch(title, startRow, Position.Bias.Forward);
    DefaultMutableTreeNode entityNode = (DefaultMutableTreeNode)path.getLastPathComponent();
    return entityNode;
  }
  
  // for cell renderer
  private String getNodeString(String[] title, TreeNode node) // array for reports)
  {
  	return this.getNode(title,node).toString();
  }
  
  private DefaultMutableTreeNode getNode(String[] title, TreeNode node) // array for reports
  {
  	String nodeTxt="" ;
  	DefaultMutableTreeNode entityNode = new DefaultMutableTreeNode();
  	
  	//nodeTxt=node.toString();
  	
  	if(node!=null)
  	{
  		//System.out.println("node null"+nodeTxt);
  		nodeTxt=node.toString();
  	}
      	   
    
  	   
  	   for(int rc =0; rc<title.length;rc++)
  	   {
          if(title[rc].equalsIgnoreCase(nodeTxt))
          {
  	           int startRow = 0;
               TreePath path = treeNavigation.getNextMatch(title[rc], startRow, Position.Bias.Forward);
    
               entityNode = (DefaultMutableTreeNode)path.getLastPathComponent();
           }
       //System.out.println("getNode "+title[rc]+" no "+rc);
       }
        //System.out.println("getNode ->"+entityNode);

    
    return entityNode;
  } */
  
  /*private void addMainNavigationNodes(boolean isNotLoginAgain)
  {
  	//if (isNotLoginAgain)
  	//{
  	  entityData.addMainNavigationNodes(); 	
  	  
  	//}
  }*/
   
 /* private void addTableNodes()
  {
     dbm.retrieveTables();
     DefaultMutableTreeNode tableNode = navigationTreeModel.getTableNode();
     tableNode.removeAllChildren();   
     DefaultMutableTreeNode base;
     
    for (int i=0; i<dbm.getTableCount(); i++ )
    {
       base = new DefaultMutableTreeNode(dbm.getTableName(i));
       tableNode.add(base);
    }
  }*/
 
  /*private void addEntityNodes(boolean isNotLoginAgain) 
  {

    /*    if (!isNotLoginAgain)
        { // have to select it because if we had one selected from before will cause an error

            treeNavigation.setSelectionRow(0);// 0 is the root row 

     	    entityNode.removeAllChildren();            

//            navigationTreeModel.reload();// refresh

        }
        else
        {
        //	initializePanels(); // initialize them only once        
        }*/
    
 /*     
       entityData.addEntityInfoNodes();
  }*/
  
 /*   private void addReportNodes(boolean isNotLoginAgain) 
  {

     

     /*   if (!isNotLoginAgain)
        { // have to select it because if we had one selected from before will cause an error
            treeNavigation.setSelectionRow(0);// 0 is the root row 
     	    entityNode.removeAllChildren();            
           // navigationTreeModel.reload();// refresh
        }
        else
        {
        //	initializePanels(); // initialize them only once        
        }*/
      
    //   entityData.addReportSettings();
       
   // }
 /* }*/

  


  

    private void presentMenuPanelFromDataTree(DataTreeNode dTreeNodeRoot)
    {
     listMenuGroup = new ArrayList();
     panelMenuContainer.removeAll(); // if change login data to change the menu
     int intHeightButton =23;
     int intWidthButton =160;
  //   DataTreeNode dTreeNodeRoot = dataTreeIn.getRootElement();
      JLabel lblDefault = new JLabel();
     //JTaskPaneGroup systemGroup = null ;
    	
      int level =0;
      int countChildren = dTreeNodeRoot.getNumberOfChildren();//dataTreeIn.getRootElement().getNumberOfChildren();
      

      for(int n=0;n<countChildren;n++) // -------- level 0
      {
         //System.out.println("PanelManagement.presentJtreeFromDataTree n "+dTreeNode.getChildFromIndex(n)+" children "+dTreeNode.hasNodeChildren());
         
        DataTreeNode dTreeNodeChild = dTreeNodeRoot.getChildFromIndex(n); 

        JSlidingPanel sp =new JSlidingPanel();
        
        EntityMenu entityMenuParent = (EntityMenu)dTreeNodeChild.getData();
 //       JLabel lblMainMenu = new JLabel("<html><b>"+dTreeNodeChild.toString()+"</b></html>");
        
//        JxToggleButton btnMainMenu = new JxToggleButton("<html><b>"+dTreeNodeChild.toString()+"</b></html>");
        JButton btnMainMenu = new JButton("<html><b>"+dTreeNodeChild.toString()+"</b></html>");
        btnMainMenu.setContentAreaFilled(false);  // http://stackoverflow.com/questions/22427057/how-to-set-a-jbutton-to-be-undecorated-and-remove-the-mouselistener
        btnMainMenu.setFocusable(false);
        // JLabel lblMainMenu = new JLabel(dTreeNodeChild.toString());
    	//lblMainMenu.setBackground(lblDefault.getBackground().darker());
    	//lblMainMenu.setOpaque(true);
        //lblMainMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));//LIGHT_GRAY));
        //lblMainMenu.setBackground(Color.LIGHT_GRAY);
 //       lblMainMenu.setOpaque(false);
        btnMainMenu.setOpaque(false);
        menuPanel.setOpaque(true);
        menuPanel.setBackground(Color.white);
        final JSlidingPanel spFinal = sp;
//        lblMainMenu.setIcon(entityMenuParent.getEntityIcon());
        btnMainMenu.setIcon(entityMenuParent.getEntityIcon());
       
        btnMainMenu.setVerticalTextPosition(JLabel.BOTTOM);
        //btnMainMenu.setHorizontalTextPosition(JLabel.CENTER);
        btnMainMenu.setHorizontalTextPosition(JLabel.RIGHT);
        btnMainMenu.setHorizontalAlignment(SwingConstants.LEFT);//.CENTER);	
/*lblMainMenu.setVerticalTextPosition(JLabel.BOTTOM);
lblMainMenu.setHorizontalTextPosition(JLabel.CENTER);
lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);		     
*/ 
        
/*lblMainMenu.setVerticalTextPosition(JLabel.TOP);
lblMainMenu.setHorizontalTextPosition(JLabel.RIGHT);
lblMainMenu.setHorizontalAlignment(SwingConstants.LEFT);*/
 
        JPanelCurvedGradient panelMainMenu = new JPanelCurvedGradient(Color.WHITE,CLR_BUTTONPANEL_START);//(CLR_BUTTONPANEL_START,CLR_BUTTONPANEL_END);
        
        final JPanelCurvedGradient panelMainMenuFinal = panelMainMenu;
//			lblMainMenu.addMouseListener(new java.awt.event.MouseAdapter()
                         btnMainMenu.addMouseListener(new java.awt.event.MouseAdapter()
			{
				boolean isClicked = false;
				public void mouseClicked(MouseEvent e)
				{  
					if(!isClicked)
					{
					   spFinal.showComponent();	
					   isClicked=true;
					}
					else if(isClicked)
					{					
					  spFinal.hideComponent();
					  isClicked=false;
					}								
				}

				public void mousePressed(MouseEvent e)
				{
                    panelMainMenuFinal.setColors(CLR_BUTTONPANEL_START,CLR_BUTTONPANEL_END);//(Color.WHITE,CLR_BUTTONPANEL_START);
                    //panelMainMenuFinal.setColors(CLR_BUTTONPANEL_END,CLR_BUTTONPANEL_ROLLOVER);
				}

				public void mouseReleased(MouseEvent e)
				{
                    panelMainMenuFinal.setColors(CLR_BUTTONPANEL_ROLLOVER_START,CLR_BUTTONPANEL_ROLLOVER_END);    //(CLR_BUTTONPANEL_END,CLR_BUTTONPANEL_ROLLOVER);
                    //panelMainMenuFinal.setColors(Color.WHITE,CLR_BUTTONPANEL_START);
				}
				
				public void mouseEntered(MouseEvent e)
				{
                     panelMainMenuFinal.setColors(CLR_BUTTONPANEL_ROLLOVER_START,CLR_BUTTONPANEL_ROLLOVER_END);
                     //panelMainMenuFinal.setColors(CLR_BUTTONPANEL_START,CLR_BUTTONPANEL_END);
				}
				
				public void mouseExited(MouseEvent e)
				{
                     panelMainMenuFinal.setColors(Color.WHITE,CLR_BUTTONPANEL_START);//(CLR_BUTTONPANEL_START,CLR_BUTTONPANEL_END);
                     //panelMainMenuFinal.setColors(Color.WHITE,CLR_BUTTONPANEL_START);
				}
				
			});        
        
        
        //PanelGradient panelMainMenu = new PanelGradient(CLR_PANEL_START_ALTER, CLR_PANEL_END_ALTER,26);
        
        //panelMainMenu.setPreferredSize(new Dimension(130, 20));
        panelMainMenu.setLayout(new BorderLayout());
//        panelMainMenu.add(lblMainMenu, BorderLayout.CENTER);
          panelMainMenu.add(btnMainMenu, BorderLayout.CENTER);      
          
          
//        lblMainMenu.setPreferredSize(new Dimension(22,22));
       //menuPanel.add(panelMainMenu);

    	JPanel panelMainMenuButtonContainer = new JPanel();
    	panelMainMenuButtonContainer.setBackground(Color.white);
    	panelMainMenuButtonContainer.setOpaque(true);
    	panelMainMenuButtonContainer.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS,1)); // new BoxLayout(panelMainMenuButtonContainer,BoxLayout.PAGE_AXIS )); ///new GridLayout(0,1));//
    	//menuPanel.add(panelMainMenuButtonContainer);

    	sp.setComponent1(panelMainMenu);
    	sp.setComponent2(panelMainMenuButtonContainer);
    	listMenuGroup.add(sp);
    	//sp.showComponentWhenGuiLoaded();

    	panelMenuContainer.add(sp);
    	    	    	
    	 //System.out.println("PanelManagement.presentJtreeFromDataTree ch "+listChildren.size());
    	 if(dTreeNodeChild.hasNodeChildren())//----------- level 1
    	 {
    	
            // System.out.println("     child caption:"+dTreeNodeChild.toString());
    	     for(int v=0;v<dTreeNodeChild.getNumberOfChildren();v++)
      	     {

                   DataTreeNode dTreeNodeChild2 = dTreeNodeChild.getChildFromIndex(v);
    	           // DefaultMutableTreeNode mutableTreeNodeChild2 = new DefaultMutableTreeNode(dTreeNodeChild2);
    	           //mutableTreeNodeChild.add(mutableTreeNodeChild2);

    	           EntityMenu entityMenu = (EntityMenu)dTreeNodeChild2.getData();  

    	                if(dTreeNodeChild2.hasNodeChildren()) //------------- level 2
    	                {

    	              JLabel lbl = new JLabel("<html><b>"+dTreeNodeChild2.toString()+"</b></html>");  // subcategories
          //            JButton btnSub2 = new JButton("<html><b>"+dTreeNodeChild2.toString()+"</b></html>");  // subcategories
                      //System.out.println("     child2 caption:"+dTreeNodeChild2.toString());
                      //JLabel lbl = new JLabel(dTreeNodeChild2.toString());
    	              //lbl.setPreferredSize(new Dimension(22,22));
    	 //             btnSub2.setIcon(entityMenu.getEntityIcon());
                       lbl.setIcon(entityMenu.getEntityIcon());
    	              //menuPanel.add(lbl);            	
    	              //System.out.println("PanelManagement.presentMenuPanelFromDataTree "+dTreeNodeChild2.toString());
    	              panelMainMenuButtonContainer.add(lbl);//btnSub2);  // add subcategory          	
    	                     for(int h=0;h<dTreeNodeChild2.getNumberOfChildren();h++)
      	                     {
                   
      	                         //System.out.println("PanelManagement.presentMenuPanelFromDataTree +"+dTreeNodeChild.getChildFromIndex(v));

    	             

            	                   DataTreeNode dTreeNodeChild3 = dTreeNodeChild2.getChildFromIndex(h);
                        	       EntityMenu entityMenuSub = (EntityMenu)dTreeNodeChild3.getData();    	             

    	                        if(dTreeNodeChild3.hasNodeChildren()) //------------- level 3
    	                        {

    	                          JLabel lblSub = new JLabel( "--- "+dTreeNodeChild3.toString());
    	                          //lblSub.setPreferredSize(new Dimension(22,22));
    	                          lblSub.setIcon(entityMenuSub.getEntityIcon());
    	                          lblSub.setOpaque(false);
    	                          
    	                          panelMainMenuButtonContainer.add(lblSub);   
    	                          //menuPanel.add(lblSub);            	

    	                             for(int k=0;k<dTreeNodeChild3.getNumberOfChildren();k++)
      	                             {
                                     // base = new DefaultMutableTreeNode(dTreeNodeChild3.getChildFromIndex(k));                                              
                                     // mutableTreeNodeChild3.add(base);
                                     // mutableTreeNodeChild2.add(mutableTreeNodeChild3);    	 	
      	                              //System.out.println("PanelManagement.presentJtreeFromDataTree +"+dTreeNodeChild.getChildFromIndex(v));

                                      DataTreeNode dTreeNodeChild4 = dTreeNodeChild3.getChildFromIndex(k);
    	                              //DefaultMutableTreeNode mutableTreeNodeChild3 = new DefaultMutableTreeNode(dTreeNodeChild3);
    	                              //mutableTreeNodeChild2.add(mutableTreeNodeChild3);
    	 		
    	 		    	               if(dTreeNodeChild4.hasNodeChildren())//------------- level 4
    	                               {
    	 		                         System.out.println("error PanelManagement.presentTaskPaneFromDataTree level 4 of tree not supported");
    	 		                       }    	 		
                          	         }
    	                        } 
    	                        else
    	                        {
    	                        	JButton btn3 = new JButton(dTreeNodeChild3.toString(),entityMenuSub.getEntityIcon());
    	                        	btn3.setToolTipText("<html><table><tr><td><img src=\""+entityMenuSub.getEntityIcon()+"\"></td><td>"+dTreeNodeChild3.toString()+"<td></tr></table><html>");
    	                        	btn3.setFocusable(false);
              btn3.setPreferredSize(new Dimension(intWidthButton,intHeightButton));
              btn3.setVerticalTextPosition(AbstractButton.TOP);
              btn3.setHorizontalTextPosition(AbstractButton.RIGHT);	                        	
    	      btn3.setHorizontalAlignment(SwingConstants.LEFT);                  	
    	                        	//btn3.setHorizontalAlignment(SwingConstants.LEFT);
    	                        	//btn3.set
    	                        	//btn3.setMargin(new Insets(1, 1, 1, 1));
    	                        	//btn3.setBorder(null) ;

    	                           //menuPanel.add(btn3);
    	                           //systemGroup.add(makeAction(dTreeNodeChild3.toString(), "",entityMenuSub.getEntityIcon()));
    	                        JLabel lbl3 = new JLabel(dTreeNodeChild3.toString());
    	                        lbl3.setFocusable(false);
    	                        //lbl3.setFocusPainted(true);
    	                        lbl3.setIcon(entityMenuSub.getEntityIcon());
    	                        lbl3.setHorizontalAlignment(SwingConstants.LEFT);
    	                        
    	                        
        lbl3.setBorder(BorderFactory.createLineBorder(lblDefault.getBackground()));
        

        
        final JLabel lbl3Final =lbl3;
        final JLabel lblDefaultFinal =lblDefault;
        lbl3Final.addMouseListener(new MouseAdapter()
                {       	      
                 public void mouseEntered(MouseEvent e)
                 {
                 	lbl3Final.setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                 	lbl3Final.setOpaque(true);
                 	lbl3Final.setBackground(CLR_LBL_ROLL);
                 	lbl3Final.setCursor(new Cursor(Cursor.HAND_CURSOR));
                 }
                 public void mouseExited(MouseEvent e)
                 {
                    lbl3Final.setBorder(BorderFactory.createLineBorder(lblDefaultFinal.getBackground()));                 
                    lbl3Final.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    lbl3Final.setOpaque(false);
                 }
                 public void mousePressed(MouseEvent e)
                 {
                 	lbl3Final.setBorder(BorderFactory.createLineBorder(Color.white)); 
                 	lbl3Final.setOpaque(false);                

                 }
                 public void mouseReleased(MouseEvent e)
                 {
                 	lbl3Final.setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                 	lbl3Final.setOpaque(true);
                 	lbl3Final.setBackground(CLR_LBL_ROLL);
                 	lbl3Final.setCursor(new Cursor(Cursor.HAND_CURSOR));              

                 }                   
                 public void mouseClicked(MouseEvent e)
                 {
                     //displayDialogSystemInfo();
                     //System.out.println("DialogMain.initialize clicked ");
                     //clickedOnRow(Integer.parseInt(txtFieldFinal.getToolTipText()));
                 }                 
                });      	                        
    	                        
    	                        

    	                       final EntityMenu entityMenuSubFinal =  entityMenuSub;
        	JButtonMainMenu standardButton = new JButtonMainMenu(); // 2//1
                //JButton standardButton = new JButton();               
 //               JButtonFlat standardButton = new JButtonFlat();
                //standardButton.setFocusable(false);
		standardButton.setPreferredSize(new Dimension(intWidthButton, intHeightButton));
		//standardButton.setToolTipText(dTreeNodeChild3.toString());
		standardButton.setToolTipText("<html><table><tr><td><img src=\""+entityMenuSubFinal.getEntityIcon()+"\"></td><td>"+dTreeNodeChild3.toString()+"<td></tr></table><html>");
		standardButton.setText(dTreeNodeChild3.toString());
		standardButton.setIcon(entityMenuSubFinal.getEntityIcon());
                standardButton.setVerticalTextPosition(AbstractButton.TOP);
                standardButton.setHorizontalTextPosition(AbstractButton.RIGHT);
                standardButton.setHorizontalAlignment(SwingConstants.LEFT);  
		standardButton.setFocusable(false); 
                
                
                
       standardButton.addMouseListener(new MouseAdapter()
       {
            public void mousePressed(MouseEvent e)// on mouse click
            {
            	
            	  //selectedTreeObject = treeNavigation.getLastSelectedPathComponent();
            	 
            	//treeNavigation = (JTree)e.getSource();
             if(e.getButton()==e.BUTTON1)    //e.BUTTON1 left click  e.BUTTON2 center
             {
                if (e.getClickCount() == 1) // make it 2 for doubleclick
                {   
                   splitPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));

                   navTreeSelection(entityMenuSubFinal);  
                
                   splitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
                }
              }
              else if (e.getButton()==e.BUTTON3)//e.BUTTON3 right click  // e.BUTTON2 center
              {
                if (e.getClickCount() == 1) // make it 2 for doubleclick
                {               
                   splitPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                   
                   
                    //int selRow = treeNavigation.getRowForLocation(e.getX(), e.getY());
                    //TreePath selPath = treeNavigation.getPathForLocation(e.getX(), e.getY());

                   
                   
                  //  displayNewWindowWithPanel(entityMenuSubFinal);

                   splitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
              }
            } 
       });   
       	
        standardButton.addKeyListener(new KeyListener()
        {
           public void keyPressed(KeyEvent e)
           {
           	 //treeNavigation.setFocusTraversalKeysEnabled(false);// when having this enabled enter makes nothing
           	//System.out.println("keyTyped"+e.getKeyCode()+" "+KeyEvent.VK_ENTER);
           	 if(e.getKeyCode() == KeyEvent.VK_ENTER)
           	 {           	 	           	 	
                   splitPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));

                   navTreeSelection(entityMenuSubFinal);  
                
                   splitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
                 }
           }
           public void keyReleased(KeyEvent e)
           {
           }
           
           public void keyTyped(KeyEvent e)
           {
           }
        });       	
       				   
			//menuPanel.add(standardButton);    	                        
			panelMainMenuButtonContainer.add(standardButton);   
    	                        
    	                        
    	                        }

    	 		
                          	     }

    	                }    	 		
    	                else
    	                {
  	                	      JButton btn2 = new JButton(dTreeNodeChild2.toString(),entityMenu.getEntityIcon());
                                      btn2.setFocusable(false);
  	              btn2.setPreferredSize(new Dimension(intWidthButton,intHeightButton));
                btn2.setVerticalTextPosition(AbstractButton.BOTTOM);
                btn2.setHorizontalTextPosition(AbstractButton.CENTER);  
                btn2.setHorizontalAlignment(SwingConstants.CENTER);
                              btn2.setMargin(new Insets(1, 1, 1, 1)); 
                              	//btn2.setBorderPainted(false);
                              	
    	                     // menuPanel.add(btn2);
    	                     //systemGroup.add(makeAction(dTreeNodeChild2.toString(), "",entityMenu.getEntityIcon()));    	                
    	              final EntityMenu entityMenuFinal =  entityMenu;       
        	JButtonMainMenu standardButton = new JButtonMainMenu(); // 2
//                JButton standardButton = new JButton();
//                JButtonFlat standardButton = new JButtonFlat();
		standardButton.setPreferredSize(new Dimension(intWidthButton, intHeightButton));
		//standardButton.setToolTipText(dTreeNodeChild2.toString());
		standardButton.setToolTipText("<html><table><tr><td><img src=\""+entityMenu.getEntityIcon()+"\"></td><td>"+dTreeNodeChild2.toString()+"<td></tr></table><html>");
		standardButton.setText(dTreeNodeChild2.toString());
		standardButton.setIcon(entityMenu.getEntityIcon());
          standardButton.setVerticalTextPosition(AbstractButton.TOP);
          standardButton.setHorizontalTextPosition(AbstractButton.RIGHT);                                       
  	  standardButton.setHorizontalAlignment(SwingConstants.LEFT);		
                standardButton.setFocusable(false);  
			
       standardButton.addMouseListener(new MouseAdapter()
       {
            public void mousePressed(MouseEvent e)// on mouse click
            {
            	
            	  //selectedTreeObject = treeNavigation.getLastSelectedPathComponent();
            	 
            	//treeNavigation = (JTree)e.getSource();
             if(e.getButton()==e.BUTTON1)    //e.BUTTON1 left click  e.BUTTON2 center
             {
                if (e.getClickCount() == 1) // make it 2 for doubleclick
                {   
                   splitPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));

                   navTreeSelection(entityMenuFinal);  
                
                   splitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
                }
              }
              else if (e.getButton()==e.BUTTON3)//e.BUTTON3 right click  // e.BUTTON2 center
              {
                if (e.getClickCount() == 1) // make it 2 for doubleclick
                {               
                   splitPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                   
                   
                    //int selRow = treeNavigation.getRowForLocation(e.getX(), e.getY());
                    //TreePath selPath = treeNavigation.getPathForLocation(e.getX(), e.getY());

                   
                   
                 //   displayNewWindowWithPanel(entityMenuFinal);

                   splitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
              }
            } 
       });   			

        standardButton.addKeyListener(new KeyListener()
        {
           public void keyPressed(KeyEvent e)
           {
           	 //treeNavigation.setFocusTraversalKeysEnabled(false);// when having this enabled enter makes nothing
           	//System.out.println("keyTyped"+e.getKeyCode()+" "+KeyEvent.VK_ENTER);
           	 if(e.getKeyCode() == KeyEvent.VK_ENTER)
           	 {           	 	           	 	
                   splitPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));

                   navTreeSelection(entityMenuFinal);  
                
                   splitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
             }
           }
           public void keyReleased(KeyEvent e)
           {
           }
           
           public void keyTyped(KeyEvent e)
           {
           }
        }); 
			  
			panelMainMenuButtonContainer.add(standardButton);	
			//menuPanel.add(standardButton);	                     
    	                     
    	 		        }
    	 		
    	 		
    	     }

    	 }
   	 
      }
    	
    }

       
  public EntityMenu getEntityMenuObjectForNode(DefaultMutableTreeNode node, int intLeaf)
  {
     	     
       EntityMenu entityMenu = new EntityMenu();

       // if menu object
       Object nodeInfo = null;
       String nodeText = null;
      
      //System.out.println("PanelManagement.navTreeSelection "+objEntity);
    //   if(objEntity==null)//              is  tree selection
    //   {    
              //node = (DefaultMutableTreeNode) selectedTreeObject;
              nodeInfo = node.getUserObject();
        
              nodeText = node.toString();
              String nodeParent = node.getParent().toString();
                        

        DataTreeNode dTreeNode = null ;
        
        //DataTreeNode dTreeNodeRoot = dataTree.getRootElement();
        ArrayList listNodes = (ArrayList)dataTree.toList();
        for(int j = 0 ;j<listNodes.size();j++)
        {
        	//System.out.println(" navTreeSelection size"+listNodes.size());
        	if(j==intLeaf)
        	{
        	 //System.out.println("PanelManagement.navTreeSelection selrow "+treeNavigation.getLeadSelectionRow());
        	 dTreeNode  =(DataTreeNode)listNodes.get(j+1); ///// attention +1 
        	 //System.out.println(" navTreeSelection  -"+j+"-"+dTreeNode);
          	 entityMenu = (EntityMenu)dTreeNode.getData();        
          	 //System.out.println(" navTreeSelection  -"+j+"-"+entityMenu.getEntityType()+entityMenu.getEntityCaption());		
        	}
	
        }
         
          //System.out.println("PanelManagement.navTreeSelection en type "+entityMenu.getEntityType());
  
 
     /* }
      else //                       is menu selection
      {
      	// node = new DefaultMutableTreeNode();
      	//System.out.println("panelManagement.navTreeSelection menu selection");

       //intMenuCategory=intMenuCategoryIn;
       //objEntity=objEntityIn;      	
      	
        DataTreeNode dTreeNode = null ;
        
        //DataTreeNode dTreeNodeRoot = dataTree.getRootElement();
        ArrayList listNodes = (ArrayList)dataTree.toList();
        
        //System.out.println("PanelManagement.navTreeSelection - "+(intMenuCategory)); 
        	 //System.out.println("PanelManagement.navTreeSelection selrow "+treeNavigation.getLeadSelectionRow());
        	 dTreeNode  =(DataTreeNode)listNodes.get(intMenu); ///// attention +1 
        	 //System.out.println(" navTreeSelection  -"+j+"-"+dTreeNode);
          	 //entityMenu = (EntityMenu)dTreeNode.getData();       	
          	 entityMenu = (EntityMenu)dTreeNode.getData();
      	
      }*/
     
     
     return entityMenu;
     
     }
     
  
     public void navTreeSelection(EntityMenu entityMenu)
     {  
         final EntityMenu entityMenuFin = entityMenu;
         this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
         
         
         // not PanelStatistics, causes an error
         if(entityMenu.getEntityType()!=ENTITY_TYPE_STATISTICS)
         {
         
          windowwait = new WindowWait("please wait",WINDOW_LOCATION_CENTER,ICO_RELOAD16, ICO_RELOADB16);
         windowwait.animate();
   		          // thread for show window wait
	         thread = new Thread(new Runnable() {
	          public void run()
	          {
	          
           	       windowwait.showWindow();
	          
	               thread = null;
	          }
	          });
              thread.start();   	  	
   	  	

            thread = new Thread(new Runnable() {
	          public void run()
	          {


               //System.out.println("-->---- PanelManagement.navTreeSelection - "); 
         navTreeMenuSelection(entityMenuFin);
         //System.out.println("PanelManagement.navTreeSelection "+entityMenu.getEntityCaption());         

         //this.revalidate();
         
         
              windowwait.close();
         


	          }
	          });
              thread.start();
         }
         else
         {
             navTreeMenuSelection(entityMenu);
         }
         
         //navTreeMenuSelection(entityMenuFin);
         this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         
     }
  
     
     // called also by DialogMain.menuSelection
     public void navTreeMenuSelection(EntityMenu entityMenu)
     {     	
       
       //this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
       
       //EntityMenu entityMenu = new EntityMenu();
       DefaultMutableTreeNode	node = null ;
       
       if(entityMenu==null)//              is  tree selection
       {    
              node = (DefaultMutableTreeNode) selectedTreeObject;
              //int selRow = treeNavigation.getRowForLocation(e.getX(), e.getY());
              entityMenu = getEntityMenuObjectForNode(node,treeNavigation.getLeadSelectionRow());
       }
       else  //   is menu selection (entity menu is setted from DialogMain)
       {
       	     //System.out.println("PanelManagement.navTreeMenuSelection "+entityMenu.getEntityCaption()); 
       }
       
       
       
     	     
       // System.out.println("PanelManagement.navTreeMenuSelection getEntityType "+entityMenu.getEntityType()+" intMenuCategory "+(intMenu));

        if(entityMenu.getEntityType()==ENTITY_TYPE_CATEGORY1 || entityMenu.getEntityType()==ENTITY_TYPE_CATEGORY2 )  
        {
        	
        }   
        //else if(// node.getParent() == getNode(DATAENTRY) || ENTITIES_CAT_ARRAY[intMenuCategory].equalsIgnoreCase(DATAENTRY) || entMenu.getEntityType()==ENTITY_TYPE_DATAENTRY)// when entity childs selected
        else if(  entityMenu.getEntityType()==ENTITY_TYPE_DATAENTRY)// when entity childs selected
        {
        //System.out.println("PanelManagement.navTreeMenuSelection info "+node.getParent()+" "+ENTITIES_CAT_ARRAY_FOR_MENU[intMenuCategory]);
           
         //entMenu = (EntityMenu)listEntitiesMenu.get(treeNavigation.getLeadSelectionRow());  
           
           EntityInfo entInfo;
        	//if is menu or is node
//          if(objEntity==null)
//          {
          	//System.out.println("PanelManagement.navTreeMenuSelection "+treeNavigation.getLeadSelectionRow());
          	//EntityMenu entM = (EntityMenu)listEntitiesMenu.get(treeNavigation.getLeadSelectionRow());
          	
          	entInfo = (EntityInfo)entityMenu.getEntityInfo();
          		//System.out.println("PanelManagement.navTreeMenuSelection *"+entityMenu.getEntityType()+" "+entityMenu.getEntityCaption());
//          }
//          else
//          {
//          	ent = (EntityInfo)objEntity;
//          }
          
          //ent= entMenu.getEntityInfo();
/////////////               
          System.out.println("PanelManagement.navTreeMenuSelection       view:"+entInfo.getView()+"     entInfo.getCaption():"+entInfo.getCaption());
          if  (entInfo.getView().equalsIgnoreCase("DORM"))
          {
          	initializePanel(PANEL_TYPE_ONEDATAMANYREC);	
          	
          	String query = null;  // get queries from entities
          	if ((entInfo.getSqlSelect()!=null)&&(entInfo.getSqlFrom()==null)&&(entInfo.getSqlWhere()==null))
            { query = entInfo.getSqlSelect() ; }
            else if ((entInfo.getSqlSelect()!= null)&&(entInfo.getSqlFrom()!=null)&&(entInfo.getSqlWhere()==null))
            { query=entInfo.getSqlSelect()+" "+entInfo.getSqlFrom(); }
            else if ((entInfo.getSqlSelect()!= null)&&(entInfo.getSqlFrom()!=null)&&(entInfo.getSqlWhere()!= null))
            { query=entInfo.getSqlSelect()+" "+entInfo.getSqlFrom()+" "+entInfo.getSqlWhere(); }
                                          //ent.name
                
            addShowTabWithPanel(entInfo.getCaption(),entityMenu.getEntityIcon(), panelOneDataManyRec,PANEL_TYPE_ONEDATAMANYREC);
            //if(!)
            //{
                    //System.out.println("PanelManagement.navTreeMenuSelection ---------- query="+query+"   ent.getSqlReadOnly()"+ent.getSqlReadOnly());
            panelOneDataManyRec.setEntityForEntityInfo(entInfo, query,entityMenu,false, this);//,entInfo.getEntityReport());
            
            
            /*panelOneDataManyRec.setEntity(ent.getName(), ent.getSqlReadOnly(), query,ent.getFieldsForSums(), ent.getCaption(),//nodeText,//nodeParent+"  :  "+nodeText,
            ent.getPrimKey(),ent.getPrimKeyDb(),entityMenu.getEntityIcon(), ent.getEntityFilterSettings(),ent.getEntityGroupOfComps(),ent.getStrOfOne(), 
            ent.getStrOfMany(),false, ent.getIntValidationColumn(), ent.getIntValidationType(), ent.getCategoryNodes(),ent.getEntityPanel(),ent.getFieldsOnTitle(),
            ent.getFieldsOnTitleCaption(),ent.getFieldsOrderby(), ent.getYearEnforce(),this);            */
            
           // }
              /*  public void setYearEnforce(String entityIn,String queryReadOnlyIn, String queryIn ,
             String titleIn,String primKey,String primKeyDbIn, ImageIcon icoIn, String[] searchCaption, 
             String[] searchFieldIn,String strOfOneIn, String strOfManyIn, Boolean isLookUpDialog,String[] categoryNodesIn,
             EntityPanel[] entityPanelIn, int[]fieldsOnTitleIn, String[] fieldsOnTitleCaptionIn)*/
            
            //System.out.println(" many "+ent.name+ent.view+ent.fields+ent.fieldsTranslation); 
            
           
            
           // panelCenterRight.add(panelOneDataManyRec, BorderLayout.CENTER);          
          }
          else if (entInfo.getView().equalsIgnoreCase("DORO"))
          {
          	initializePanel(PANEL_TYPE_ONEDATAONEREC);	

          	
          	String query = null;  // get queries from entities
          	if ((entInfo.getSqlSelect()!=null)&&(entInfo.getSqlFrom()==null)&&(entInfo.getSqlWhere()==null))
            { query = entInfo.getSqlSelect() ; }
            else if ((entInfo.getSqlSelect()!= null)&&(entInfo.getSqlFrom()!=null)&&(entInfo.getSqlWhere()==null))
            { query=entInfo.getSqlSelect()+" "+entInfo.getSqlFrom(); }
            else if ((entInfo.getSqlSelect()!= null)&&(entInfo.getSqlFrom()!=null)&&(entInfo.getSqlWhere()!= null))
            { query=entInfo.getSqlSelect()+" "+entInfo.getSqlFrom()+" "+entInfo.getSqlWhere(); }
          	
                addShowTabWithPanel(entInfo.getCaption(),entityMenu.getEntityIcon(), panelOneDataOneRec,PANEL_TYPE_ONEDATAONEREC);
               //System.out.println("PanelManagement  entInfo  "+entInfo.getCaption());         
          	//if(!)
          	//{
    /*------ panelOneDataOneRec.setYearEnforce(ent.name,ent.fields,null,null, ent.primKey, 
            null,ent.primKeyDb, query , ent.caption,entityMenu.getEntityIcon(), false,false,
            true,ent.isMasterUnique,false,ent.getFieldsOnTitle(),ent.getFieldsOnTitleCaption(),
            false,"",true,1,this,null); //1 is not known why*/
            //}
           /*public void setYearEnforce(String entity,String[]fields,String[]fieldsTranslation,String primKey, 
      String primKeyValueIn,String primKeyDbIn,String queryIn,  String titleIn, ImageIcon ico, boolean isNewRecIn, isNewRecFromCopy 
      boolean showToolBar,boolean isMasterUnique,boolean isMany,int[]fieldsOnTitleIn, String[] fieldsOnTitleCaptionIn,
      boolean showOnlySaveNPrintButton)*/
            
            //System.out.println(" one "+ent.name+ent.view); 
           // panelCenterRight.add(panelOneDataOneRec, BorderLayout.CENTER);

          } 
          else if (entInfo.getView().equalsIgnoreCase("dbUser"))
          {
          	initializePanel(PANEL_TYPE_ONEDATAONEREC);
          	String query = null;  // get queries from entities
          	if ((entInfo.getSqlSelect()!=null)&&(entInfo.getSqlFrom()==null)&&(entInfo.getSqlWhere()==null))
            { query = entInfo.getSqlSelect() ; }
            else if ((entInfo.getSqlSelect()!= null)&&(entInfo.getSqlFrom()!=null)&&(entInfo.getSqlWhere()==null))
            { query=entInfo.getSqlSelect()+" "+entInfo.getSqlFrom(); }
            else if ((entInfo.getSqlSelect()!= null)&&(entInfo.getSqlFrom()!=null)&&(entInfo.getSqlWhere()!= null))
            { query=entInfo.getSqlSelect()+" "+entInfo.getSqlFrom()+" "+entInfo.getSqlWhere(); }
          	
                addShowTabWithPanel(entInfo.getCaption(),entityMenu.getEntityIcon(), panelOneDataOneRec,PANEL_TYPE_ONEDATAONEREC);
         	//if(!)
          	//{
/*---------     panelOneDataOneRec.setYearEnforce(ent.name,ent.fields,null,null, ent.primKey,VariablesGlobal.globalUserId,ent.primKeyDb, query , 
                    ent.caption,entityMenu.getEntityIcon(), false, false,true,ent.isMasterUnique,false,ent.getFieldsOnTitle(),
                    ent.getFieldsOnTitleCaption(),true,"",true,1,this,null);*/
            //}
            // String entity,String primKey, String primKeyValue, String query,  String title, ImageIcon ico, boolean isNewRecIn,isNewRecFromCopy, boolean showToolBar
            //System.out.println(" one "+ent.name+ent.view); 
            //panelCenterRight.add(panelOneDataOneRec, BorderLayout.CENTER);
          }  
          else if (entInfo.getView().equalsIgnoreCase("dbcompany"))
          {
          	initializePanel(PANEL_TYPE_ONEDATAONEREC);
          	String query = null;  // get queries from entities
          	if ((entInfo.getSqlSelect()!=null)&&(entInfo.getSqlFrom()==null)&&(entInfo.getSqlWhere()==null))
            { query = entInfo.getSqlSelect() ; }
            else if ((entInfo.getSqlSelect()!= null)&&(entInfo.getSqlFrom()!=null)&&(entInfo.getSqlWhere()==null))
            { query=entInfo.getSqlSelect()+" "+entInfo.getSqlFrom(); }
            else if ((entInfo.getSqlSelect()!= null)&&(entInfo.getSqlFrom()!=null)&&(entInfo.getSqlWhere()!= null))
            { query=entInfo.getSqlSelect()+" "+entInfo.getSqlFrom()+" "+entInfo.getSqlWhere(); }
          	
                addShowTabWithPanel(entInfo.getCaption(),entityMenu.getEntityIcon(), panelOneDataOneRec,PANEL_TYPE_ONEDATAONEREC);
          	//if(!)
          	//{          	
 /*--------  panelOneDataOneRec.setYearEnforce(ent.name,ent.fields,null,null, ent.primKey,VariablesGlobal.globalCompanyId,ent.primKeyDb, 
                    query , ent.caption,entityMenu.getEntityIcon(), false,false,true,ent.isMasterUnique,false,ent.getFieldsOnTitle(),
                    ent.getFieldsOnTitleCaption(),true,"",true,1,this,null); //1 is not known why*/
            //}
            // String entity,String primKey, String primKeyValue, String query,  String title, ImageIcon ico, boolean isNewRecIn, isNewRecFromCopy boolean showToolBar
            //System.out.println(" one "+ent.name+ent.view); 
            //panelCenterRight.add(panelOneDataOneRec, BorderLayout.CENTER);

          }  
          else
          {
             System.out.println("panelManagement.navTreeMenuSelection neither DORO neither DORM  "+entInfo.getName()); 
          }

       }
 /*       else if(  entityMenu.getEntityType()==ENTITY_TYPE_DATAENTRYMANY)// when entity childs selected
        {
        //System.out.println("PanelManagement.navTreeMenuSelection info "+node.getParent()+" "+ENTITIES_CAT_ARRAY_FOR_MENU[intMenuCategory]);
           
         //entMenu = (EntityMenu)listEntitiesMenu.get(treeNavigation.getLeadSelectionRow());  
           
           EntityInfoMany ent;
        	//if is menu or is node
//          if(objEntity==null)
//          {
          	//System.out.println("PanelManagement.navTreeMenuSelection "+treeNavigation.getLeadSelectionRow());
          	//EntityMenu entM = (EntityMenu)listEntitiesMenu.get(treeNavigation.getLeadSelectionRow());
          	
          	ent = (EntityInfoMany)entityMenu.getEntityInfoMany();
          		//System.out.println("PanelManagement.navTreeMenuSelection *"+entityMenu.getEntityType()+" "+entityMenu.getEntityCaption());
//          }
//          else
//          {
//          	ent = (EntityInfoMany)objEntity;
//          }
          
          //ent= entMenu.getEntityInfo();
/////////////               
          if  (ent.getView().equalsIgnoreCase("DORM"))
          {
          	System.out.println("error PanelManagement.navTreeMenuSelection view "+ent.getView()+" not in ENTITY_TYPE_DATAENTRYMANY");
          }
          else if (ent.view.equalsIgnoreCase("DORO"))
          {
              System.out.println("error PanelManagement.navTreeMenuSelection view "+ent.getView()+" not in ENTITY_TYPE_DATAENTRYMANY");
          } 
          else if (ent.view.equalsIgnoreCase("DTRO"))
          {
          	initializePanel(PANEL_TYPE_TWODATAONEREC);
          	
          	
          	System.out.println("PanelManagement "+ent.getEntityPanel()[0].getEntityGroupOfComps());
          	
          	/*if(!addShowTabWithPanel(ent.caption,entityMenu.getEntityIcon(), panelTwoDataOneRec,PANEL_TYPE_TWODATAONEREC))
          	{
          		
            panelTwoDataOneRec.setEntity(ent.getEntityPanel()[0], //ent.name,ent.sqlOne,ent.fields,ent.fieldsMany,ent.getEntityPanel()[0].getEntityGroupOfComps(),
            null,// ent.getEntityPanel()[0].getEntityGroupOfPanels(),ent.fieldsManyOnInsert,ent.fieldsManyTranslationOnInsert, ent.name2, ent.sqlMany,ent.isMasterUnique,
            //ent.sqlManyWhereField,ent.sqlManyWhereValue, ent.primKey,
            null,// ent.primKeyDb,ent.primKeyMany,ent.primKeyManyTran,
            true, //ent.caption, ICO_TABLE16, ent.strOfMany2,
            true,//6,"producttype","productTypeId","product","productId",3,"","",
            this,null);
            }*/
       //String entityIn,String sqlOneIn,String[]fields,String[]fieldsTranslation,String[]fieldsMany,String[]fieldsManyTranslation,
        //String[]fieldsManyOnInsert,String[]fieldsManyTranslationOnInsert, String entityManyIn, String sqlManyIn,boolean isMasterUnique,String[] sql2WhereFieldIn,
        //String[] sql2WhereValueIn, String primKeyIn, String primKeyValueIn,String primKeyDbIn,String[] primKeysManyIn,String[] primKeysManyTranIn,boolean isNewRecIn, String title,
        //ImageIcon ico, String strOfMany2,boolean showTitle)
                  //System.out.println(" one "+ent.name+ent.view); 
            //panelCenterRight.add(panelTwoDataOneRec, BorderLayout.CENTER);
//          } 
          /*else if (ent.view.equalsIgnoreCase("DTRM"))
          {
          	initializePanel(PANEL_TYPE_TWODATAMANYREC);
          	if(!addShowTabWithPanel(ent.caption,entityMenu.getEntityIcon(), panelTwoDataManyRec,PANEL_TYPE_TWODATAMANYREC))
          	{
          	//System.out.println("panelManagement.navTreeMenuSelection ent.sqlOne"+ent.sqlOne);
            panelTwoDataManyRec.setEntity(ent.getName(),ent.getName2(),ent.getEntityPanel(), ent.getSqlReadOnly(),ent.getFieldsForSums(),ent.getFieldsForSumsMany(),ent.getEntityHeader(),
                    ent.getPrimKey(),ent.getPrimKeyDb(),ent.getsqlOne(), ent.getIsMasterUnique(), ent.getCaption(),entityMenu.getEntityIcon(), 
                    ent.getsqlMany(),ent.getQueryManyReadOnly(),ent.getsqlManyWhereField(), ent.getsqlManyWhereValue(), ent.getEntityFilterSettings(), 
                    ent.getEntityGroupOfCompsForPrinting(), ent.getStrOfOne(), ent.getStrOfMany(),ent.getStrOfMany2(),ent.getCategoryNodes(),ent.getFieldsOrderby(),
                    ent.getCheckBoxesColumn(), ent.getCheckBoxInfo(),ent.getIntValidationColumn(), ent.getIntValidationType(), ent.getEntityTask(),ent.getYearEnforce(),this);
            }
            /*setYearEnforce(String entityIn, EntityPanel[] entityPanelIn, String queryReadOnlyIn,  String primKeyIn,String primKeyDbIn,String[] primKeysManyIn,
             String[]primKeysManyTranIn,String sqlOneIn, boolean isMasterUniqueIn, String titleIn, ImageIcon icoIn,String sql2In,String [] sql2WhereFieldIn,
             String [] sql2WhereValueIn,String[] searchCaption, String[] searchFieldIn, String strOfOneIn, String strOfManyIn,String strOfMany2In,String[] categoryNodesIn)*/
            
            //System.out.println(" one "+ent.name+ent.view); 
            //panelCenterRight.add(panelTwoDataManyRec, BorderLayout.CENTER);
            
         // }
/*          else if (ent.view.equalsIgnoreCase("dbUser"))
          {
                   System.out.println("error PanelManagement.navTreeMenuSelection view "+ent.getView()+" not in ENTITY_TYPE_DATAENTRYMANY");         
          }
          else if (ent.view.equalsIgnoreCase("dbcompany"))
          {
                   System.out.println("error PanelManagement.navTreeMenuSelection view "+ent.getView()+" not in ENTITY_TYPE_DATAENTRYMANY");     
          	
          }  
          else
          {
             System.out.println("panelManagement.navTreeMenuSelection neither DORO neither DORM neither DTRO "+ent.name); 
          }

       }     */  
       else if(entityMenu.getEntityType()==ENTITY_TYPE_REPORT)
       // else if ( node.getParent() == getNode(REPORTS_CAT_ARRAY, node.getParent()) ||  ENTITIES_CAT_ARRAY[intMenuCategory].equalsIgnoreCase(REPORTS) )// when report childs selected
        {
           //System.out.println("PanelManagement.navTreeMenuSelection type  ENTITY_TYPE_REPORT    "+entityMenu.getEntityType());
        	initializePanel(PANEL_TYPE_REPORT);
        	//PanelReportsList panelReportsList =  new PanelReportsList();
        	//panelCenterRight.add(panelReportsList,BorderLayout.CENTER);

     	    EntityReport erpt;

        	//if is menu or is node
//          if(objEntity==null)
//          {
          	erpt   = (EntityReport)entityMenu.getEntityReport();
//          }
//          else
//          {
//          	erpt = (EntityReport)objEntity;
//          }  
                addShowTabWithPanel(REPORT_STRING_ON_TITLE_OF_TAB+erpt.caption,entityMenu.getEntityIcon(), panelReportSettingsPreview,PANEL_TYPE_REPORT);
        	//if(!)
         	//{      
          	     //System.out.println("select "+nodeText);  	
     	    
     	         //panelReportSettingsAndPreview panelReportSettingsAndPreview = new panelReportSettingsAndPreview((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
                 panelReportSettingsPreview.setEntity(erpt,true, this);
    
        	    //panelReportSettingsPreview.chooseReports(nodeText);
       	       //}
        	//splitPane.setRightComponent(panelCenterRight);
        /*	Report rpt = (Report)nodeInfo;
        	
        	panelCenterRight.add(panelMulti,BorderLayout.CENTER);
            panelMulti.setText(nodeText);
        	splitPane.setRightComponent(panelCenterRight);
            
            //panelCenterRight.add(panelReport, BorderLayout.CENTER);
            //panelReport.setYearEnforce(rpt.name,"ODMR",null,null,null,rpt.sqlQuery,null,rpt.title,true);
            
            DialogReportSettings dialogReportSettings = new DialogReportSettings((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
            dialogReportSettings.setYearEnforce(rpt.name,"ODMR",null,null,null,rpt.sqlQuery,null,rpt.title,true,rpt.entityFilterSettings);
            // (String entityIn,String viewType,Vector dataVectorIn,String primKey,String primKeyValue, String queryIn, String titleIn,boolean summaryPageIn)
            dialogReportSettings.showDialog();*/
        }   
        else if(entityMenu.getEntityType()==ENTITY_TYPE_STATISTICS)
        //else if ( node.getParent() == getNode(METRICS) || ENTITIES_CAT_ARRAY[intMenuCategory].equalsIgnoreCase(METRICS) )// when report childs selected
        {
        	
        	initializePanel(PANEL_TYPE_STATISTICS);
        	
        	EntityStatistics[] stat;
        	
        	//if is menu or is node
//          if(objEntity==null)
//          {
          	stat  = (EntityStatistics[])entityMenu.getEntityStatistics();
//          }
//          else
//          {
//          	stat = (EntityStatistics)objEntity;
//          }        	
        	
        	addShowTabWithPanel(stat[0].getCaption(),entityMenu.getEntityIcon(), panelStatistics,PANEL_TYPE_STATISTICS);
          	/*if(!)
          	{ */
          	
          	 panelStatistics.setEntity(METRICS,stat, entityMenu.getEntityIcon(),0,null,""/*formGlobalTableToGet1*/,""/*formGlobalTableToApply1*/,null,null,this);  // last null is for entityReportForm
          	       	
        	/*panelStatistics.setYearEnforce(METRICS,stat.getEntity(),stat.getCaption(),false,"",entityMenu.getEntityIcon(),stat.getQuerySelect(),stat.getQueryFrom(),stat.getQueryWhere(),stat.getQueryBroupBy(),
        	 stat.getQueryOrderBy(),stat.getIsFilterCompany(),stat.getFieldCompanyName(),stat.getIsFilterYear(),stat.getFieldYearName(), stat.getPrimKey(),stat.getPrimKeyDb());
        	*/
        	//}
        }
        else if(entityMenu.getEntityType()==ENTITY_TYPE_PARAMETER)
        //else if ( node.getParent() == getNode(PARAMETERS) || ENTITIES_CAT_ARRAY[intMenuCategory].equalsIgnoreCase(PARAMETERS) )// when report childs selected
        {        
    
           EntityParameter entParameter;
    	 //System.out.println("DialogParameters.listSelection "+ent.name);
    	 //panelCenterRight.removeAll();  
        
        	//if is menu or is node
//          if(objEntity==null)
//          {
          	entParameter   = (EntityParameter)entityMenu.getEntityParameter();
//          }
//          else
//          {
//          	ent = (EntityParameter)objEntity;
//          }        	 
        
          if  (entParameter.view.equalsIgnoreCase("DORM"))
          {
          	initializePanel(PANEL_TYPE_ONEDATAMANYREC);	
          	
          	String query = null;  // get queries from entities
          	if ((entParameter.sqlSelect!=null)&&(entParameter.sqlFrom==null)&&(entParameter.sqlWhere==null))
            { query = entParameter.sqlSelect ; }
            else if ((entParameter.sqlSelect!= null)&&(entParameter.sqlFrom!=null)&&(entParameter.sqlWhere==null))
            { query=entParameter.sqlSelect+" "+entParameter.sqlFrom; }
            else if ((entParameter.sqlSelect!= null)&&(entParameter.sqlFrom!=null)&&(entParameter.sqlWhere!= null))
            { query=entParameter.sqlSelect+" "+entParameter.sqlFrom+" "+entParameter.sqlWhere; }
             
                addShowTabWithPanel(entParameter.caption,entityMenu.getEntityIcon(), panelOneDataManyRec, PANEL_TYPE_ONEDATAMANYREC);
          	/*if(!)
          	{*/	
          //System.out.println("PanelManagement.navTreeMenuSelection ---------- query="+query+"   ent.getSqlReadOnly()"+ent.getSqlReadOnly());
           
             panelOneDataManyRec.setEntityForEntityParameters(entParameter, query,entityMenu,false, this);//,null);       
                    
             /*panelOneDataManyRec.setEntity(ent.name, ent.sqlReadOnly, query,ent.getFieldsForSums(),
             ent.caption, ent.primKey,ent.primKeyDb,entityMenu.getEntityIcon(), ent.entityFilterSettings,ent.getEntityGroupOfComps(),
             ent.strOfOne, ent.strOfMany,false,ent.getIntValidationColumn(), ent.getIntValidationType(),
             ent.getCategoryNodes(), ent.getEntityPanel(),ent.getFieldsOnTitle(),ent.getFieldsOnTitleCaption(),ent.getFieldsOrderby(),ent.getYearEnforce(),this);*/
            //}
           /*    public void setEntity(String entityIn,String queryReadOnlyIn, String queryIn ,
     String titleIn,String primKey,String primKeyDbIn, ImageIcon icoIn, String[] searchCaption, 
     String[] searchFieldIn,String strOfOneIn, String strOfManyIn, Boolean isLookUpDialog,String[] categoryNodesIn,
     EntityPanel[] entityPanelIn, int[]fieldsOnTitleIn, String[] fieldsOnTitleCaptionIn)*/
           
           
           // System.out.println(" many "+ent.name+ent.view); 
        //    panelCenterRight.add(panelOneDataManyRec, BorderLayout.CENTER);  
          }
          else if (entParameter.view.equalsIgnoreCase("DORO"))
          {
          	initializePanel(PANEL_TYPE_ONEDATAONEREC);	
          	String query = null;  // get queries from entities
          	if ((entParameter.sqlSelect!=null)&&(entParameter.sqlFrom==null)&&(entParameter.sqlWhere==null))
            { query = entParameter.sqlSelect ; }
            else if ((entParameter.sqlSelect!= null)&&(entParameter.sqlFrom!=null)&&(entParameter.sqlWhere==null))
            { query=entParameter.sqlSelect+" "+entParameter.sqlFrom; }
            else if ((entParameter.sqlSelect!= null)&&(entParameter.sqlFrom!=null)&&(entParameter.sqlWhere!= null))
            { query=entParameter.sqlSelect+" "+entParameter.sqlFrom+" "+entParameter.sqlWhere; }
                
          	addShowTabWithPanel(entParameter.caption,entityMenu.getEntityIcon(), panelOneDataOneRec,PANEL_TYPE_ONEDATAONEREC);
               // System.out.println("PanelManagement  entParameter  "+entParameter.caption+"   "+VariablesGlobal.globalCompanyId);
          	//if(!)
          	//{          	
            panelOneDataOneRec.setEntity(entParameter.name,/*ent.getEntityPanel(),*/entParameter.getEntityPanel()[0], /*ent.primKey*//*"1"*/ VariablesGlobal.globalCompanyId , query , entParameter.caption,false,false,
              /* entParameter.getFormGlobalTableToGet1(),entParameter.getFormGlobalTableToApply1(),*/ entParameter.getFieldsOnTitle(), entParameter.getFieldsOnTitleCaption(),true, entParameter.getYearEnforce(),false,true,   
               entityMenu.getEntityIcon(),this,null,IS_CALLED_BY_ONE_TABLE_ODOR);//false,false,true,false,false,
            //true,"",true,1, this,null);//1 is not known why*/
            
    /*panelOneDataOneRec setEntity(String entityIn,EntityPanel[] entityPanelArrayIn,EntityPanel entityPanelIn, String primKeyValueIn,String queryIn,String titleIn, boolean isNewRecIn,  boolean isNewRecFromCopyIn,
      int[]fieldsOnTitleIn,  String[] fieldsOnTitleCaptionIn, boolean showOnlySaveNPrintButton, String yearEnforceIn, boolean isShowBackToListButtons,boolean showToolBar, 
      ImageIcon icon,int intSelectedPanel, PanelManagement panelManagementIn, PanelEditOneDataRec  panelEditOneDataRecIn)            */

     /*PanelEditOneDataRec setEntity(String entityIn, EntityPanel[] entityPanelIn,int[]fieldsOnTitleIn, String[] fieldsOnTitleCaptionIn, 
    boolean isMasterUnique, String primKey,  String primKeyValueIn,String primKeyDbIn, String queryIn,
    String titleIn,ImageIcon ico, boolean dataOneIn, boolean isNewRecIn,boolean isNewRecFromCopyIn , boolean showBtnOk,
     String[] categoryNodes, boolean showShowListButtonsIn ,PanelManagement panelManagementIn)*/            
            
            //}


          }

        
        }// node parameters
        else if(  entityMenu.getEntityType()==ENTITY_TYPE_TASK)// when entity childs selected
        {
        	
        	initializePanel(PANEL_TYPE_TASK);
        	
        	EntityTask[] task;
        	
        	//if is menu or is node
//          if(objEntity==null)
//          {
          	task  = (EntityTask[])entityMenu.getEntityTaskArray();
//          }
//          else
//          {
//          	task = (EntityTask)objEntity;
//          }        	
        	
        	addShowTabWithPanel(entityMenu.getEntityCaption(),entityMenu.getEntityIcon(), panelTask,PANEL_TYPE_TASK);
          	/*if(!)
          	{ */       	
        	//panelCenterRight.add(panelStatistics,BorderLayout.CENTER);
        	//navigationTreeModel.getStatisticsNode().toString()+"/"+
        	panelTask.setEntity(entityMenu.getEntityCaption(),task,this);
        	//}
        	//panelReportsList.setText(nodeText);
        	//panelReportsList.chooseReports(nodeText);
        	//splitPane.setRightComponent(panelStatistics);        	
        }
        else if (entityMenu.getEntityType()==ENTITY_TYPE_DOCKABLEGRAPH)
        {

        	initializePanel(PANEL_TYPE_SCOREBOARD);
        	
        	EntityScoreBoard entityScoreBoard;
        	
        	//if is menu or is node

          	entityScoreBoard  = (EntityScoreBoard)entityMenu.getEntityScoreBoard();
       	
        	 panelScoreBoard.setEntity(entityScoreBoard,this);
        		
             /* PanelDockable pd = null;
              for(int e=0;e<entityDockableGraph.length;e++)
              {
              	pd=new PanelDockable();
                pd.setYearEnforce(entityDockableGraph[e].getTitle(),entityDockableGraph[e].getGraphType(),entityDockableGraph[e].getQueryMaster(),entityDockableGraph[e].getQueryDetail());             
                panelDockableDesktop.addDesktopComponent(pd,entityDockableGraph[e].getLeft(),entityDockableGraph[e].getTop(),entityDockableGraph[e].getRight(),entityDockableGraph[e].getBottom());
              	//System.out.println("PanelManagement graph "+e);
              }*/
      	
        	addShowTabWithPanel(entityScoreBoard.getTitle(),entityMenu.getEntityIcon(), panelScoreBoard,PANEL_TYPE_SCOREBOARD);
          	/*if(!)
          	{        	
        	//panelCenterRight.add(panelStatistics,BorderLayout.CENTER);
        	//navigationTreeModel.getStatisticsNode().toString()+"/"+

        	
        	}*/
        }
        else if(entityMenu.getEntityType()==ENTITY_TYPE_DATAMANY_PARAMETERS)
        {
            initializePanel(PANEL_TYPE_MANYDATAMANYREC_PARAMETERS);
            EntityManyDataManyRec   entityManyDataManyRec; //PanelManyDataManyRec panelManyDataManyRec;
            
            entityManyDataManyRec  = (EntityManyDataManyRec)entityMenu.getEntityManyDataManyRec();
            
            panelManyDataManyRec.setEntity(entityManyDataManyRec,this);
            
            //System.out.println("PanelManagement.entityManyDataManyRec    length"+entityManyDataManyRec.getEntityParameters().length);
            
            addShowTabWithPanel(entityManyDataManyRec.getCaption(),entityMenu.getEntityIcon(), panelManyDataManyRec,PANEL_TYPE_MANYDATAMANYREC_PARAMETERS);
                /*if(!)
          	{        	
        	//panelCenterRight.add(panelStatistics,BorderLayout.CENTER);
        	//navigationTreeModel.getStatisticsNode().toString()+"/"+

        	
        	}*/
            
            
        }
        else if(  entityMenu.getEntityType()==ENTITY_TYPE_TOOL)// when entity childs selected
        {	
        	initializePanel(PANEL_TYPE_TOOL);
        	
        	EntityTool tool;

          	tool  = (EntityTool)entityMenu.getEntityTool();
    	
        	
                
                
                if(tool.getCaption().equalsIgnoreCase(CAPTION_IMPORT_DATA))
                {
        	   addShowTabWithPanel(tool.getCaption(),entityMenu.getEntityIcon(), panelDataImport,ENTITY_TYPE_TOOL);
                }
                else if(tool.getCaption().equalsIgnoreCase(CAPTION_EXPORT_DATA))
                {
                    addShowTabWithPanel(tool.getCaption(),entityMenu.getEntityIcon(), panelDataExport,ENTITY_TYPE_TOOL);
                }
                else
                {
                    System.out.println("panelManagement.navTreeMenuSelection   NOT DEFINED  caption:"+tool.getCaption()+" panelDataCopyFromCompany");
                    addShowTabWithPanel(tool.getCaption(),entityMenu.getEntityIcon(), panelDataCopyFromCompany,ENTITY_TYPE_TOOL);
                }
        	//panelTask.setEntity(task,this);
        }
        else
        {
        	System.out.println("error  PanelManagement.navTreeMenuSelection NOT DEFINED type = "+entityMenu.getEntityType());
        }
                    //this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
         this.revalidate();
        
    }
     
    // adds tabs, public because is also called by DialogMain
    public void addShowTabWithPanel(String caption,ImageIcon ico, JPanel panel, int typeOfPanel)
    {
   
   
   /*    public static final int PANEL_TYPE_ONEDATAMANYREC = 1;
    PANEL_TYPE_ONEDATAONEREC = 2;
    PANEL_TYPE_MANYDATAMANYREC_PARAMETERS = 3;
    PANEL_TYPE_EDITONEDATAONEREC=5;
    PANEL_TYPE_STATISTICS = 6;
    PANEL_TYPE_REPORT = 7;
    PANEL_TYPE_TASK = 8;*/ 	
    	
   if(ico==null) 	
   {
   	
   	  if( typeOfPanel==PANEL_TYPE_ONEDATAMANYREC || typeOfPanel==PANEL_TYPE_ONEDATAONEREC || typeOfPanel==PANEL_TYPE_EDITONEDATAONEREC || typeOfPanel==PANEL_TYPE_MANYDATAMANYREC_PARAMETERS)
   	  {
   	  	ico =  ICO_TABLE16;
   	  }
   	  else if (typeOfPanel==PANEL_TYPE_STATISTICS)
   	  {
   	  	ico =  ICO_STATISTICS16;
   	  }
   	  else if (typeOfPanel==PANEL_TYPE_REPORT)
   	  {
   	  	ico =  ICO_PRINT_PREVIEW16;
   	  }   	  
   	  else if (typeOfPanel==PANEL_TYPE_TASK)
   	  {
   	  	ico =  ICO_TASK;
   	  }      	
   	
   }
    	
    	
    
         	listOfTabPanes.add(panel); //  add to arraylist
         	listOfTypeOfTabPanes.add(typeOfPanel);
         	//System.out.println("PanelMangement.addShowTabWithPanel typeOfPanel "+typeOfPanel);
    	    tabbedPane.addTab(caption, ico, panel); 
    	    tabbedPane.addActionListener(new ActionListenerTabCLoseButtonClicked());
            
    	    tabbedPane.setToolTipTextAt(tabbedPane.getTabCount()-1,"<html><table><tr><td><img src=\""+ico+"\"></td><td>"+caption+"<td></tr></table><html>");
    	    //tabWithClose = new TabWithClose(tabbedPane);
    //	    tabWithClose = new TabWithClose();
    //	    tabWithClose.setYearEnforce(tabbedPane);
    //        tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, tabWithClose);
            tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
   //      }
          //tabbedPane.requestFocusInWindow();   
     
        // return exists;
    }
  
  private void showTabbedPanelKeysWindow(int selected)
  {   
        //Set forwardKeys = new HashSet();
        //forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0));
        //forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
        //this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,forwardKeys);  
        

        
        ArrayList tabsArrayList = new ArrayList();
        ArrayList tabsIcoArrayList = new ArrayList();
        for (int t= 0; t<tabbedPane.getTabCount();t++)
        {
        	tabsArrayList.add(t,tabbedPane.getTitleAt(t));
        	tabsIcoArrayList.add(t,tabbedPane.getIconAt(t));
        	//System.out.println("tabs "+tabbedPane.getTitleAt(t));
        }
        
        String[] tabsList = new String[tabsArrayList.size()];
        ImageIcon[] tabsIcoList = new ImageIcon[tabsIcoArrayList.size()];
        for(int i = 0; i<tabsArrayList.size(); i++)
        {
        	tabsList[i] = (i+1)+". "+tabsArrayList.get(i).toString();
        	tabsIcoList[i] =(ImageIcon)tabsIcoArrayList.get(i);
        }
        
        if(tabsList.length>0)
        {
          WindowSelectTab winSelectTab = new WindowSelectTab((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this),tabbedPane,tabsList,tabsIcoList,"επίλέξτε καρτέλα",WINDOW_LOCATION_CENTER,selected);
          winSelectTab.showWindow();
        }
        
  }
    
    private boolean prepareCloseOfTabPanel(int index)
    {
     boolean ret = false; // true close panel, false not close panel
     
     //System.out.println("PanelManagement.prepareCloseOfTabPanel index "+index);
     
     int typeOfTabPanels = Integer.parseInt(listOfTypeOfTabPanes.get(index).toString());
     if(typeOfTabPanels==PANEL_TYPE_ONEDATAMANYREC)
     {
        PanelOneDataManyRec panel = (PanelOneDataManyRec)listOfTabPanes.get(index);  
        ret = panel.closePanelAsk();
     }
     else if(typeOfTabPanels==PANEL_TYPE_ONEDATAONEREC)
     {
     	PanelOneDataOneRec panel = (PanelOneDataOneRec)listOfTabPanes.get(index);  
        ret = panel.closePanelAsk();
     }
     else if(typeOfTabPanels==PANEL_TYPE_MANYDATAMANYREC_PARAMETERS)
     {
     	PanelManyDataManyRec panel = (PanelManyDataManyRec)listOfTabPanes.get(index);  
        ret = panel.closePanelAsk();
     }     
     /*else if(typeOfTabPanels==PANEL_TYPE_TWODATAMANYREC)
     {
     	PanelTwoDataManyRec panel = (PanelTwoDataManyRec)listOfTabPanes.get(index);  
        ret = panel.closePanel();
     } */
     /*else if(typeOfTabPanels==PANEL_TYPE_TWODATAONEREC)
     {
     	PanelTwoDataOneRec panel = (PanelTwoDataOneRec)listOfTabPanes.get(index);  
        ret = panel.closePanelAsk();
     } */             
     else if(typeOfTabPanels==PANEL_TYPE_STATISTICS)
     {
     	PanelStatistics panel = (PanelStatistics)listOfTabPanes.get(index);  
        panel.closePanel();
        ret = true;
     }   
     else if(typeOfTabPanels==PANEL_TYPE_REPORT)
     {
     	ret = true;
     }
     else if(typeOfTabPanels==PANEL_TYPE_SCOREBOARD)
     {
     	PanelScoreBoard panel = (PanelScoreBoard)listOfTabPanes.get(index);  
        panel.closePanel();
     	ret = true;
     }     
     else
     {
     	ret = true;
     	System.out.println("PanelManagement.prepareCloseOfTabPanel typeOfTabPanels "+typeOfTabPanels+" not available");
     }        
         
     
     
     /*if(listOfTabPanes.size()==1)
     {
         closeAllOpenDBConnections();
     }*/
     
     
     //System.out.println("PanelManagement.prepareCloseOfTabPanel typeOfTabPanels:"+typeOfTabPanels+" ret:"+ret);
    	return ret;
    }
    
  /*private void closeAllOpenDBConnections()
  {
      Database db= new Database();
      db.releaseConnectionRs();
      db.releaseConnectionRsmd();      
      
      
    try
    {      
      Connection con = db.getConnection();
       DbConnection.releaseConnection(con);
    }
    catch ( SQLException sqlex)
    {
        System.out.println(" error:  PanelManagement.closeAllOpenDBConnections() "+ "error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
        sqlex.printStackTrace();
    }       

    System.out.println(" TODO  PanelManagement.closeAllOpenDBConnections ");
    
  }*/
    
   public boolean closeTab(int index)
   {
      boolean ret = false;
      
     // true close panel, false not close panel 
     if(prepareCloseOfTabPanel(index))
     {
     	
       tabbedPane.removeTabAt(index);
       listOfTabPanes.remove(index); // remove from arraylist
       listOfTypeOfTabPanes.remove(index); // remove from arraylist     	     	
     	ret = true;
     	
     }
     else
     {
     	 ret = false;
     }
     
     return ret;
   }
   
   // handle all calls to close all tabs
   public boolean closeAllTabs()
   {         
        boolean ret = true;

        boolean isCloseAll=true;
            for(int t =0 ; t<tabbedPane.getTabCount() ; t++)
            {
            	//System.out.println("PanelManagement.closeAllTabs tab "+t);
               if (prepareCloseOfTabPanel(t))
               {
                     tabbedPane.remove(t);
                      
               }
               else
               {
                    isCloseAll=false;
               }
            }
           

          /* listOfTabPanes.clear();
           listOfTypeOfTabPanes.clear();
           
              tabbedPane.removeAll();
             // also code in TabChangeListener
   	        tabbedPane.setVisible(false);
    		panelNoTabs.setVisible(true);*/
    	//System.out.println("PanelManagement.closeAllTabs isCloseAll:"+isCloseAll);
            
          if(isCloseAll)  
          {
                tabbedPane.removeAll() ;
                panelNoTabs.setVisible(true);
                tabbedPane.setVisible(false);
              ret=true;
          }
          else
          {
              ret=false;
          }
            
          //System.out.println("PanelManagement.closeAllTabs ret:"+ret);
    		return ret;
   }
  
    // use of tab and enter to change focused field
  // also in JxPanel
    private void setForwardKeys()
  {   
        Set forwardKeys = new HashSet();
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0));
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,forwardKeys);  // tabbedPane
  }  
  
    private void setBackwardKeys()
  {   
        Set backwardKeys = new HashSet();
         
        //backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0));
        backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,KeyEvent.SHIFT_DOWN_MASK));
        backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0));
        this.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,backwardKeys);   // tabbedPane
  }     
    
    
   class  ActionShowWindowWithTabs extends AbstractAction                 
   {       
        public ActionShowWindowWithTabs()
        {        }    	
    	public void actionPerformed(ActionEvent e)
      	{         
      	
      	      showTabbedPanelKeysWindow(tabbedPane.getSelectedIndex());
      	
      	}    	
    }   
    
    
    
   class  ActionCloseSelTab extends AbstractAction                 
   {       
        public ActionCloseSelTab()
        {        }    	
    	public void actionPerformed(ActionEvent e)
      	{         
      	  closeTab(tabbedPane.getSelectedIndex());

      	}    	
    }     
   /* class RemindTask extends TimerTask
    {
    	TreeSelectionEvent e;
    	public RemindTask(TreeSelectionEvent eIn)
    	{
    	   e=eIn;
    	}
        public void run()
        {
            // navTreeSelection((DefaultMutableTreeNode)e.getPath().getLastPathComponent());        	
             System.out.println("panelManagement.RemindTask stoptimer");
              timer.cancel(); //Terminate the timer thread

        }
    }*/
    
    
    /*public void keyTyped(KeyEvent e)
    {
        if (e.getKeyChar() == '') { // ctrl-w - close tab
            tabbedPane.remove(tabbedPane.getSelectedIndex());
        }
    }   */ 
    
  /*  private void setPanelGraphSelected()
    {
    	
    	PanelDockable pd=new PanelDockable();
        pd.setYearEnforce("Toπ 6 προϊόντα όλων των ετών",GRAPH_TYPE_PIE,null,"SELECT p.productId, p.productName, COUNT(p.productId) AS count, SUM(i.value) AS sum FROM product p, invoice i WHERE p.productId=i.productId GROUP BY p.productId ORDER BY sum DESC LIMIT 6");  
        panelDockableDesktop.addDesktopComponent(pd,0,0,1,1);
      
        pd=new PanelDockable();
        pd.setYearEnforce("πορεία τοπ 6 εταιριών",GRAPH_TYPE_LINE,"SELECT c.companyId, c.title, COUNT(i.date) AS count, SUM(i.value) AS sum, AVG(i.value) AS average, MIN(i.value) AS min, MAX(i.value) AS max FROM dbcompany c, invoice i WHERE c.companyId=i.companyId GROUP BY i.companyId ORDER BY sum LIMIT 6","SELECT invoice.companyId, dbyear.dbyear AS \"χρήση\", COUNT(invoice.date) AS count, SUM(invoice.value) AS \"τιμή\", AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max FROM dbyear, invoice WHERE dbyear.dbyear=invoice.dbyear AND dbyear.companyId=invoice.companyId GROUP BY invoice.companyId, dbyear.dbyear ORDER BY invoice.companyId, dbyear.dbyear");
        panelDockableDesktop.addDesktopComponent(pd,1,0,1,1);
        
        pd=new PanelDockable();
        pd.setYearEnforce("Toπ 6 προϊόντων "+VariablesGlobal.globalCompanyName+" έτους "+VariablesGlobal.globalYear,GRAPH_TYPE_PIE,null,"SELECT p.productId, p.productName, COUNT(p.productId) AS count, SUM(i.value) AS sum FROM product p, invoice i WHERE p.productId=i.productId AND year="+VariablesGlobal.globalYear+" AND companyId="+VariablesGlobal.globalCompanyId+" GROUP BY p.productId ORDER BY sum DESC LIMIT 6");
        panelDockableDesktop.addDesktopComponent(pd,0,1,1,1);
        
        pd=new PanelDockable();
        pd.setYearEnforce("πορεία τοπ 9  προϊόντων",GRAPH_TYPE_LINE,"SELECT p.productId, p.productName, COUNT(i.date) AS count, SUM(i.value) AS sum, AVG(i.value) AS average, MIN(i.value) AS min, MAX(i.value) AS max FROM product p, invoice i WHERE p.productId=i.productId GROUP BY p.productId ORDER BY sum LIMIT 9","SELECT invoice.productId, dbyear.dbyear AS \"χρήση\", COUNT(invoice.date) AS count, SUM(invoice.value) AS \"τιμή\", AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max FROM dbyear, invoice WHERE dbyear.dbyear=invoice.dbyear AND dbyear.companyId=invoice.companyId GROUP BY invoice.productId, dbyear.dbyear ORDER BY invoice.productId, dbyear.dbyear");
        panelDockableDesktop.addDesktopComponent(pd,1,1,1,1);
        
        pd=new PanelDockable();
        pd.setYearEnforce("μεγαλύτεροι προμηθευτές",2,null,null);
        panelDockableDesktop.addDesktopComponent(pd,0,2,1,1);

        pd=new PanelDockable();
        pd.setYearEnforce("πωλήσεις ανα νομό",2,null,null);
        panelDockableDesktop.addDesktopComponent(pd,1,2,1,1);
    	
    }*/
 
 /*    private class TabContainerListener implements ContainerListener//, ActionListener
    {
    	TabbedPaneWithClose tabbedPane;
    	public TabContainerListener(TabbedPaneWithClose tabbedPaneIn)
        {
        	tabbedPane = tabbedPaneIn;
    	}
    	
    public void componentAdded(ContainerEvent e)
    {    }


    // first setRemovedPane from TabWithClose, then getRemovedPane from PanelManagement
    public void componentRemoved(ContainerEvent e)
    {
        //System.out.println("PanelManagement.TabContainerListener removedPane "+tabbedPane.getRemovedPane());
        int index = tabbedPane.getRemovedPane();
        prepareCloseOfTabPanel(index);
        listOfTabPanes.remove(index); // remove from arraylist
        listOfTypeOfTabPanes.remove(index); // remove from arraylist
        //tabbedPane.getTabComponentAt(tabbedPane.getRemovedPane())
    }

    }*/
 
 
    public class TabChangeListener implements ChangeListener//, ActionListener
    {
    JTabbedPaneWithCloseBtn tabbedPane;
    public TabChangeListener(JTabbedPaneWithCloseBtn tabbedPaneIn)
    {
    	tabbedPane=tabbedPaneIn;
    	
    	if (tabbedPane.getTabCount()==0)
    	{
    	 
    	 
    	    tabbedPane.setVisible(false);
    	    panelNoTabs.setVisible(true);
    	}
    	else
    	{
    		//System.out.println("PanelManagement.TabChangeListener "+tabbedPane.getTabCount()+tabbedPane.getTabRunCount() +tabbedPane.getTitleAt(0) );
    		tabbedPane.setVisible(true);
    		panelNoTabs.setVisible(false);
    	}
 
    }    
    
    public void stateChanged(ChangeEvent e)
    {
    	// also code in closeAllTabs
    
    	if (tabbedPane.getTabCount()==0)
    	{
    	 
    	 
    	    tabbedPane.setVisible(false);
    	    panelNoTabs.setVisible(true);
    	}
    	else
    	{
    		//System.out.println("PanelManagement.TabChangeListener stateChanged"+tabbedPane.getTabCount()+tabbedPane.getTabRunCount() +tabbedPane.getTitleAt(0) );
    		tabbedPane.setVisible(true);
    		panelNoTabs.setVisible(false);
    	}

   //System.out.println("PanelManagement.TabChangeListener stateChanged  "+tabbedPane.getRemovedPane() );

    	
    }
    
    } 
    
    
    
   private class ActionListenerTabCLoseButtonClicked implements ActionListener
  {// look at     public void addActionListener(ActionListener al) in panelPrintPreview and PanelReportSettings
    // here:
    //      	panelReportSettings.addActionListener(new PanelActionListener());
    //    	panelPrintPreview.addActionListener(new PanelActionListener());
    public void actionPerformed(ActionEvent e)
    {
      //System.out.println("Action \"" + e.getActionCommand() + "\" performed from within TabbedPaneWithClose class");
       
       //System.out.println("PanelManagement. ActionListenerTabCLoseButtonClicked "+tabbedPane.getTabPrepareToClose());
       //close action
       closeTab(tabbedPane.getTabPrepareToClose());



         
    }
  }    
     
     // from http://java-swing.blogspot.com/2006/12/closing-tabs-with-swing.html
     public class TabMouseListener implements MouseListener//, ActionListener
     {
    JTabbedPane tabbedPane;
    public TabMouseListener(JTabbedPane tabbedPaneIn)
    {
    	tabbedPane=tabbedPaneIn;
    	 tabPopup = new JPopupMenu();
         JMenuItem menuCloseThisTab = new JMenuItem("κλείσιμο καρτέλας  ctrl-F4",ICO_CANCEL16);
         //menuCloseThisTab.setMnemonic(KeyEvent.VK_F4);
         JMenuItem menuCloseAllTabs = new JMenuItem("κλείσιμο όλων");
         JMenuItem menuSelectFromAvailTabs = new JMenuItem("επιλογή καρτέλας  F3",ICO_TABLE16);
         menuCloseThisTab.addActionListener( new ActionListener() 
         { 
          	public void actionPerformed(ActionEvent e)
          	{
          		closeTab(tabbedPane.getSelectedIndex());
          	}
         }	
         );
         menuCloseAllTabs.addActionListener( new ActionListener() 
         { 
          	public void actionPerformed(ActionEvent e)
          	{
          		closeAllTabs();
          	}
         }	
         );      
         
         menuSelectFromAvailTabs.addActionListener( new ActionListener() 
         { 
          	public void actionPerformed(ActionEvent e)
          	{
          		showTabbedPanelKeysWindow(tabbedPane.getSelectedIndex());
          	}
         }	
         );
            
         tabPopup.add(menuCloseThisTab);
    	tabPopup.add(menuCloseAllTabs);
  	    tabPopup.add(menuSelectFromAvailTabs);

    }

    public void mousePressed(MouseEvent e)
    {
       showPopup(e);
    }
    private void showPopup(MouseEvent e)
    {
      if (e.isPopupTrigger())
      {
       //source = e.getSource();
       //clickPoint = e.getPoint();
       tabPopup.show(e.getComponent(), e.getX(), e.getY());
      }
   }
   
    public void mouseExited(MouseEvent e)
    {
      // showPopup(e);
    }

    public void mouseEntered(MouseEvent e)
    {
      // showPopup(e);
    }

    public void mouseClicked(MouseEvent e)
    {
      // showPopup(e);
    }
           
   public void mouseReleased(MouseEvent e)
   {
     showPopup(e); // here because different platforms handle popups differently
   }

   	  
   }
   
   class ToolBarTree extends JToolBar
   {
      private JButton  btnShow = new JButton();
      private JButton btnHelp = new JButton();
      private JButton btnKeys = new JButton();
      private JButton btnHide = new JButton();
      private JButton btnAbout = new JButton();
     // private IconSeparator icoSeparator;
      //private JLabel lblIcoSeparator1= new JLabel();
      //private JLabel lblIcoSeparator2= new JLabel();
      //private JLabel lblIcoSeparator3= new JLabel();
      
        public ToolBarTree()
        {
            try
           {     initialize();   }
           catch (Exception e)
           {   e.printStackTrace();    }
        }

        private void initialize() throws Exception
        {
          setFloatable(false);
          setRollover(true);
          
          //this.setOpaque(false);

          //this.setBackground(Color.blue);
          //this.setOrientation(this.VERTICAL);
         
 
           btnShow.setText("<html></html>");
   //       btnShow.setText("<html><b>Β</b>οήθεια</html>");
          btnShow.setOpaque(false);
          //btnShow.setToolTipText("βοήθεια");
          btnShow.setIcon(ICO_MENUCATEGORY_EXP_COL);
          //btnShow.setMnemonic(KeyEvent.VK_B);          
          btnShow.setFocusable(false);
          btnShow.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	              
                      /*//showTreeView();
	              if(isExpanded)
	              {
	              	panelButtonExpandOrCollapseAll(false);
	              	isExpanded= false;
	              }
	              else
	              {
	              	panelButtonExpandOrCollapseAll(true);
	              	isExpanded= true;
	              }
	              */
                  //panelButtonExpandOrCollapseAll(true);
                    
                    showWindowMenu(true,btnShow);
	        }
	      });
          
          
          btnHelp.setText("<html></html>");
   //       btnHelp.setText("<html><b>Β</b>οήθεια</html>");
          btnHelp.setOpaque(false);
          btnHelp.setToolTipText("βοήθεια");
          btnHelp.setIcon(ICO_HELP16);
          //btnHelp.setMnemonic(KeyEvent.VK_B);          
          btnHelp.setFocusable(false);
          btnHelp.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	            showTaskPaneView();
	        }
	      });

          btnKeys.setText("<html></html>");
          btnKeys.setOpaque(false);
          btnKeys.setToolTipText("πλήκτρα συντομεύσεων");
          btnKeys.setIcon(ICO_KEYS16);
          //btnKeys.setMnemonic(KeyEvent.VK_L);          
          btnKeys.setFocusable(false);
          btnKeys.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	            showMenuPanelView();
	        }
	      });

          
          btnHide.setText("<html></html>");
 //         btnHide.setText("<html>πλ<b>Η</b>ροφορίες</html>");
          btnHide.setOpaque(false);
          //btnHide.setToolTipText("");
          btnHide.setMnemonic(KeyEvent.VK_H);          
          btnHide.setIcon(ICO_KEYS16);
          btnHide.setFocusable(false);
          //btnHide.setVerticalTextPosition(AbstractButton.BOTTOM);
          //btnHide.setHorizontalTextPosition(AbstractButton.CENTER);
          btnHide.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {

                 //setTreePanelVisible();	
              
	        }
	      });
	      
	      


          //addSeparator();
        //IconResize icoResize = new IconResize();
        //icoSeparator = new IconSeparator();
       // lblIcoSeparator1.setIcon(icoSeparator);
        //lblIcoSeparator1.setText("kj");
        
        //lblIcoSeparator1.setOpaque(false);
        //lblIcoSeparator1.setHorizontalAlignment(SwingConstants.LEFT);
        //lblIcoSeparator1.setVerticalAlignment(SwingConstants.TOP);
        
        //lblIcoSeparator2.setIcon(icoSeparator);  
        //lblIcoSeparator3.setIcon(icoSeparator);
                  
          //add(lblIcoSeparator1);
          addSeparator();
          add(btnShow);
          add(btnKeys);
          add(btnHelp);
          addSeparator();          
          //add(lblIcoSeparator2);          
          add(btnHide);
          addSeparator();          

        }
        
        @Override
        protected void paintComponent(Graphics g)
        {
             /*Graphics2D g2 = (Graphics2D) g;   //                     15
             GradientPaint paint = new GradientPaint(0, 0, this.getBackground().brighter(), 0, 37, this.getBackground().darker(),true);
             g2.setPaint(paint);
             g2.fill(getBounds());*/
             super.paintComponent(g);
        }
   
   }  
    
    
    //similar in PanelEditOneDataRec
    /*private class TreeRendererIcons extends DefaultTreeCellRenderer implements Constants
    {
    	
    	private Color backgroundSelectionColor;
        private Color backgroundNonSelectionColor;
    	private DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
    	
        public TreeRendererIcons()
        { 
        
        backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
        backgroundNonSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();        
        
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
        {
            setLeafIcon(ICO_TABLE16);
            setOpenIcon(ICON_TREEFOLDER_OPENED);
            setClosedIcon(ICON_TREEFOLDER_CLOSED);

           // 	System.out.println("PanelManagement.TreeRendererIcons "+value);

            super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
            
            DefaultMutableTreeNode node =(DefaultMutableTreeNode)value;
            
//----------------------
         
            Object nodeInfo = null;
            String nodeText = null;
          
            EntityMenu entityMenu =new EntityMenu();
            //  node = (DefaultMutableTreeNode) selectedTreeObject;
            //  nodeInfo = node.getUserObject();
        
           //   nodeText = node.toString();
           //   String nodeParent = node.getParent().toString();
              
              //if (nodeParent.equalsIgnoreCase())
          


            if(node.getChildCount()==0)
            {
            	//System.out.println("error PanelMgt.TreeRendererIcons "+node.toString());

                 //Object nodeObj = ((DefaultMutableTreeNode)value).getUserObject();
                 DefaultMutableTreeNode nodeSel = getNode(node.toString());
                 if (nodeSel!=null)
                 {
                 
                 entityMenu = getEntityMenuObjectForNode(nodeSel, row);
                 if(entityMenu.getEntityIcon()!=null)
                 {
                     setIcon( entityMenu.getEntityIcon());	
                 }
                 else
                 {

                      if(node.getParent() != null)
                     {
            	//System.out.println("PanelManagement.TreeRendererIcons "+node.getParent()+"."+node);
            
              //   if ( node.getParent() == getNode(METRICS))  // wrong
                       if ( node.getParent().toString().equalsIgnoreCase(METRICS))
                     {                setIcon(ICO_STATISTICS16);     } 
                     else if (node.getParent().toString().equalsIgnoreCase(getNodeString(REPORTS_CAT_ARRAY, node.getParent())))
                     {        
                        setIcon(ICO_PRINT_PREVIEW16);       
                     }                 	
                 	
                    }
                 

                 } 
                 
                }      // if (nodeSel!=null)      
                
               }
               
            
        if (sel) {
          this.setBackground(backgroundSelectionColor);
        } else {
          this.setBackground(backgroundNonSelectionColor);
        }


            return this;
        }
    
      private DefaultMutableTreeNode getNode(String title)
      {
  	     int startRow = 0;
         //TreePath path = null;
         //System.out.println("PanelMgt.TreeRendererIcons.getNode treeNavigation"+treeNavigation);
         TreePath path = treeNavigation.getNextMatch(title, startRow, Position.Bias.Forward);
         DefaultMutableTreeNode entityNode=null;
         if(path !=null)
         {
         	      //System.out.println("PanelMgt.TreeRendererIcons.getNode "+title+" "+path);
                  entityNode = (DefaultMutableTreeNode)path.getLastPathComponent();

         }
         else
         {
         	//treeNavigation.setVisible(false);
         	System.out.println("error PanelMgt.TreeRendererIcons.getNode "+title+" "+path+" "+startRow);
         }
         
         return entityNode;
      }
    
    
    }*/
    
    


}