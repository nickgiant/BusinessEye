

package com.tool.gui;

import com.tool.domain.EntityData;
import com.tool.model.EntityStatistics;
import com.tool.model.EntityParameter;
//import com.tool.model.EntityInfoMany;
import com.tool.model.EntityInfo;
import com.tool.guicomps.*;
import com.tool.model.*;
import com.tool.utils.*;
//import com.tool.lang.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.text.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class DialogHelp extends JDialog implements Constants
{
	private JButtonForPanelDecorated btnClose;
	private JPanelDecorated panelMain;
	private JPanel panelToShow;
	private int typeOfPanel;
	private JPanel panelToAdd;
	private JTreeDec treeNavigation ;
	private Object selectedTreeObject;
	private JSplitPane splitPane;
//	private NavigationTreeModel navigationTreeModel;   // needs TreeModelSorted and TreeModelSortedNode
	private DataTree dataTree;
	private JEditorPane txtPane;
	private int dialogHeight = 450;
	private int dialogWidth = 100;
	private int intLeafsBeforeMenu =1;
	
    public DialogHelp(JFrame frame)
    {
       // super(parent, "", true);
        super(frame, true);//LANGUAGE.getString("AboutDialog.title"));
       // this.parent = parent;
        try
        {            initialize();        }
        catch (Exception e)
        {       e.printStackTrace();        }
    }

    /**
     *  Initialization of class.
     */    
    private void initialize() throws Exception
    {
        this.setTitle("Βοήθεια");
        
        //panelMain = new JPanel();
        //              Color blue= new Color(129, 169, 226);
        //      Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
        panelMain = new JPanelDecorated();//(blue,lightBlue,0,0);
        panelMain.setLayout(new BorderLayout());
        
        splitPane = new JSplitPane();

        
        treeNavigation = new JTreeDec();
        treeNavigation.setBorder(null);
  //      navigationTreeModel = new NavigationTreeModel();             
  //      treeNavigation.setModel(navigationTreeModel);

        treeNavigation.addKeyListener(new KeyListener()
        {
           public void keyPressed(KeyEvent e)
           {
           	 treeNavigation.setFocusTraversalKeysEnabled(false);// when having this enabled enter makes nothing
           	//System.out.println("keyTyped"+e.getKeyCode()+" "+KeyEvent.VK_ENTER);
           	 if(e.getKeyCode() == KeyEvent.VK_ENTER)
           	 {
           	 	           	 	
    
                 selectedTreeObject = treeNavigation.getLastSelectedPathComponent();
                 //objEntity =null;
                 //intMenuCategory=0;
                navTreeSelection(0);  
                treeNavigation.setFocusTraversalKeysEnabled(true); // we enable it after enter is pressed
                
                
             }
           }
           public void keyReleased(KeyEvent e)
           {
           }
           
           public void keyTyped(KeyEvent e)
           {
           }
        });

                 

       treeNavigation.addMouseListener(new MouseAdapter()
       {
            public void mousePressed(MouseEvent e)// on mouse click
            {
            	
            	  selectedTreeObject = treeNavigation.getLastSelectedPathComponent();
            	 
            	treeNavigation = (JTreeDec)e.getSource();
             if(e.getButton()==e.BUTTON1)    //e.BUTTON1 left click  e.BUTTON2 center
             {
                if (e.getClickCount() == 1) // make it 2 for doubleclick
                {   
                   splitPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                   
                   //objEntity=null;
                   //intMenuCategory=0;
                   int selRow = treeNavigation.getRowForLocation(e.getX(), e.getY());
                   navTreeSelection(selRow);  
                
                   splitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
               
                }
              }
              else if (e.getButton()==e.BUTTON3)//e.BUTTON3 right click  // e.BUTTON2 center
              {
                if (e.getClickCount() == 1) // make it 2 for doubleclick
                {               
                   splitPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                   
                   
                    int selRow = treeNavigation.getRowForLocation(e.getX(), e.getY());
                    TreePath selPath = treeNavigation.getPathForLocation(e.getX(), e.getY());

                   
                   
         //           displayNewWindowWithPanel();

                   splitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
              }
            } 
       });     
       	


       	
       	JScrollPane treeScrollPane = new JScrollPane();
       	treeScrollPane.setViewportView(treeNavigation);
        treeScrollPane.setMinimumSize(new Dimension(200, 60));//Dimension(int width, int height) 
        treeScrollPane.setPreferredSize(new Dimension(200, 100));       	
       	

                
        txtPane = new JEditorPane();// height, width
        txtPane.setEditable(false);
        txtPane.setContentType( "text/html" );
        //txtPane.setEditable(false);

       	JScrollPane txtPaneScrollPane = new JScrollPane();
       	txtPaneScrollPane.setViewportView(txtPane);
        
        splitPane.setLeftComponent(treeScrollPane);
        splitPane.setRightComponent(txtPaneScrollPane);
        panelMain.add(splitPane,BorderLayout.CENTER);
        
        /*JPanel panelPageEnd = new JPanel();
        panelPageEnd.setOpaque(false);
        panelPageEnd.setLayout(new FlowLayout());
        btnClose = new JButtonForPanelDecorated();
        btnClose.setText("<html>κλείσιμο <b>esc</b></html>");
        btnClose.setIcon(ICO_CANCEL16);
        //btnClose.requestFocus();
        btnClose.setFocusable(false);
        btnClose.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            { 
                closeDialog();
            }
        });
        Action actionClose = new ActionClose();
        btnClose.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnClose.getActionMap().put("close", actionClose);
        
        panelPageEnd.add(btnClose);
        panelMain.add(panelPageEnd, BorderLayout.PAGE_END);*/
      
        setCloseClick();
      
        this.add(panelMain);
    
    	addNodes();
    	treeNavigation.setSelectionRow(0);// 0 is the root row
    	treeNavigation.setCellRenderer(new TreeRendererIcons());
    }

    /**
     * When a leaf is selected.
     * @param selRow the selected row
     */
     public void navTreeSelection(int selRow)
     {
       
       this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
       
       //EntityMenu entityMenu = new EntityMenu();
       DefaultMutableTreeNode	node = null ;
       
  
       node = (DefaultMutableTreeNode) selectedTreeObject;
       //System.out.println("navTreeSelection "+node.toString());
       //int selRow = treeNavigation.getRowForLocation(e.getX(), e.getY());
     /* DataTreeNode dTreeNodeRoot = dataTree.getRootElement();
      int countChildren = dataTree.getRootElement().getNumberOfChildren();    
      for(int n=0;n<countChildren;n++) // -------- level 0
      {
         //System.out.println("PanelManagement.presentJtreeFromDataTree n "+dTreeNode.getChildFromIndex(n)+" children "+dTreeNode.hasNodeChildren());
         
         DataTreeNode dTreeNodeChild = dTreeNodeRoot.getChildFromIndex(n); 
         EntityMenu entityMenu = (EntityMenu)dTreeNodeChild.getData();
         entityMenu.getEntityCaption();
         entityMenu.getEntityIcon();
    	 //System.out.println("PanelManagement.presentJtreeFromDataTree n "+dTreeNodeChild.getChildFromIndex(n));
      }*/

       String strChilds ="";
       String strFields ="";
       String strPanels ="";
       for(int c=0;c<node.getChildCount();c++)
       {
       	 strChilds=strChilds+"<tr><td></td><td> -> "+node.getChildAt(c)+"</td></tr>";
       }
       
       EntityMenu entityMenu = getEntityMenuObjectForNode(selRow-intLeafsBeforeMenu);
       int intType = entityMenu.getEntityType();
       
       
       if(intType==ENTITY_TYPE_DATAENTRY )
       {
       	  EntityInfo e = entityMenu.getEntityInfo();
       	  int lenPanels = e.getEntityPanel().length;
       	  int intNoOfPanels = 0;
       	  strPanels=strPanels+"<tr><td><b>Λίστα με εγραφές "+e.getStrOfMany()+".</b></td></tr>";
       	  strPanels=strPanels+"<tr><td>ενέργειες: εισαγωγή,επεξεργασία, διαγραφή, πρότυπα, εκτύπωση/εξαγωγή, προτιμήσεις</td></tr>";
       	  strPanels=strPanels+"<tr><td>Ύστερα απο επιλογή μιας εγγραφής (διπλό κλικ) έχετε τις παρακάτω δυνατότητες:</td></tr>";
       	  for(int lp =0; lp<lenPanels;lp++)
       	  {
           if(e.getEntityPanel()[lp].getType().equalsIgnoreCase("ODOR"))
           {

           	//strPanels=strPanels+"<tr><td><b>"+(lp+1)+". "+e.getEntityPanel()[lp].getTitle()+" "+e.getStrOfOne()+"</b> (καταχώρηση-τροποποίηση)</td></tr>";
           	//strPanels=strPanels+"<tr><td>ενέργειες: επιστροφή στη λίστα, εισαγωγή, διαγραφή, αποθήκευση, πρότυπα, προεπισκόπηση εκτύπωσης</td></tr>";
           	//strPanels=strPanels+"<tr><td>πεδία:</td></tr>";

                     
                     EntityGroupOfComps[] entityGroupOfComps = (EntityGroupOfComps[])e.getEntityPanel()[lp].getEntityGroupOfComps();
                     EntityGroupOfPanels[] entityGroupOfPanels = (EntityGroupOfPanels[])e.getEntityPanel()[lp].getEntityGroupOfPanels();
                
                    
                if(entityGroupOfPanels!=null && entityGroupOfPanels.length>0)
                {   
                  for(int gp = 0;gp<entityGroupOfPanels.length;gp++) 
                  {
                       String captionGroupOfPanels =  entityGroupOfPanels[gp].getCaption();                     
                       //System.out.println("DialogHelp.navTreeSelection   "+gp+"  "+captionGroupOfPanels);
                       for(int gc = 0;gc<entityGroupOfComps.length;gc++)
                       {
                           int intGC = entityGroupOfComps[gc].getIncludedInGroupOfPanels();
                           if(gp==gc)
                           {
                               //System.out.println("DialogHelp.navTreeSelection   "+gp+"  "+gc+"   ---  "+captionGroupOfPanels);    
                               
                               strPanels=strPanels+"<tr><td><b>"+(lp+1+gc)+". "+captionGroupOfPanels+" "+e.getStrOfOne()+"</b> (καταχώρηση-τροποποίηση)</td></tr>";
                               strPanels=strPanels+"<tr><td>ενέργειες: αποθήκευση και πίσω, πίσω, εισαγωγή, διαγραφή, αποθήκευση, πρότυπα, προεπισκόπηση εκτύπωσης</td></tr>";
           	               strPanels=strPanels+"<tr><td>πεδία:</td></tr>";
                               strPanels=strPanels+"<tr><td>";
                                 int pnlLen =e.getEntityPanel()[lp].getDBFields().length;
       	                         for(int f=0;f<pnlLen;f++)
       	                         {
                                     int intGrpCmps = e.getEntityPanel()[lp].getDBFields()[f].getGroupOfComps();
                                     int intGrpOfPanels = entityGroupOfComps[intGrpCmps].getIncludedInGroupOfPanels();
                                     if(intGrpCmps==-1)
                                     {
                                         
                                     }
                                     else
                                     {
                                         if(intGrpOfPanels==gc)
                                         {
                                          String field = e.getEntityPanel()[lp].getDBFields()[f].getCaption();
                                          //System.out.println("DialogHelp.navTreeSelection   "+gp+"  "+gc+"  "+f+" "+intGC+"  "+intGrpCmps+"  "+intGrpOfPanels+"  "+field);                                               
                                          strPanels=strPanels+field+", ";
                                         }
                                     }
                                 }
                                 strPanels=strPanels+"</td></tr>";                                      
                                 intNoOfPanels++;
                           }   
                       }
                 }
              }//if
                else // EntityGroupOfPanels is null 
              {
                       System.out.println("error  DialogHelp.navTreeSelection  entityGroupOfPanels:"+entityGroupOfPanels);
              }
           }
           else if(e.getEntityPanel()[lp].getType().equalsIgnoreCase("STATS"))
           {
           	 strPanels=strPanels+"<tr><td><b>"+(lp+1+intNoOfPanels)+". "+e.getEntityPanel()[lp].getTitle()+" "+e.getStrOfOne()+"</b> (προβολή στατιστικών)</td></tr>";
           	 strPanels=strPanels+"<tr><td>ενέργειες: προεπισκόπηση εκτύπωσης, εξαγωγή σε αρχείο, προτιμήσεις</td></tr>";
           }
           else
           {
           	  System.out.println("DialogHelp.navTreeSelection ENTITY_TYPE_DATAENTRY unknown type "+e.getEntityPanel()[lp].getType());
           }
       	  }
       }
 /*      else if(intType== ENTITY_TYPE_DATAENTRYMANY)
       {
       	  EntityInfoMany e = entityMenu.getEntityInfoMany();
       	  int lenPanels = e.getEntityPanel().length;
       	  strPanels=strPanels+"<tr><td><b>Λίστα με εγραφές "+e.getStrOfMany()+".</b></td></tr>";
       	  
       	  int lenEntityTask = e.getEntityTask().length;
       	  EntityTask[] eTask = e.getEntityTask();
       	  String strTasks ="";
       	  for(int et =0;et<lenEntityTask;et++)
       	  {
       	  	  strTasks=strTasks+eTask[et].getCaption()+", ";
       	  }
       	  
       	  
       	  strPanels=strPanels+"<tr><td>ενέργειες: εισαγωγή, επεξεργασία, διαγραφή, εργασίες, πρότυπα, εκτύπωση/εξαγωγή, προτιμήσεις</td></tr>";
       	  strPanels=strPanels+"<tr><td>εργασίες: "+strTasks+"</td></tr>";
          strPanels=strPanels+"<tr><td>Ύστερα απο επιλογή μιας εγγραφής (διπλό κλικ) έχετε τις παρακάτω δυνατότητες:</td></tr>";       	  
       	  /*for(int lp =0; lp<lenPanels;lp++)
       	  {
       	  	int len =e.getEntityPanel()[lp].getDBFields().length;
       	 /* if(e.getEntityPanel()[lp].getType().equalsIgnoreCase("TDOR"))
       	  {
           	strPanels=strPanels+"<tr><td><b>"+(lp+1)+". "+e.getEntityPanel()[lp].getTitle()+" "+e.getStrOfOne()+"</b> (καταχώρηση-τροποποίηση)</td></tr>";
           	strPanels=strPanels+"<tr><td>ενέργειες: επιστροφή στη λίστα, εισαγωγή, διαγραφή, αποθήκευση, πρότυπα, προεπισκόπηση εκτύπωσης</td></tr>";
           	strPanels=strPanels+"<tr><td>πεδία:</td></tr>";
           //int lenPanels = e.getEntityPanel().length;
       	  for(int f=0;f<len;f++)
       	  {
       	  	 if(e.getEntityPanel()[lp].getDBFields()[f].getGroupOfComps()==-1)
       	  	 {
       	  	 	
       	  	 }
       	  	 else
       	  	 {
       	         String field = e.getEntityPanel()[lp].getDBFields()[f].getCaption();	
       	  	     strFields=strFields+" "+field+", ";       	  	 	
       	  	 }

       	  }
       	  	strFields = strFields+"<tr><td>";
       	  int lenM =e.getEntityPanel()[lp].getDBFieldsMany().length;
       	  for(int f=0;f<lenM;f++)
       	  {
       	         String field = e.getEntityPanel()[lp].getDBFieldsMany()[f].getCaption();	
       	  	     strFields=strFields+" "+field+", ";       	  	 	
       	  	 
       	  }   //for
       	  	strFields = strFields+"</td></tr";
       	    strPanels=strPanels+"<tr><td>"+strFields+"</td></tr>";       	  
       	  }// if 
       	  else
       	  {
       	  	  System.out.println("DialogHelp.navTreeSelection ENTITY_TYPE_DATAENTRYMANY unknown type "+e.getEntityPanel()[lp].getType());
       	  }*/
 //      }// for
   	  	     	  

      // }
       else if(intType== ENTITY_TYPE_REPORT)
       {
       	  strPanels=strPanels+"<tr><td><b>εκτύπωση</b></td></tr>";
       	  strPanels=strPanels+"<tr><td>ενέργειες: προεπισκόπηση, εξαγωγή, νέο σχέδιο, αποθήκευση ως, αποθήκευση, διαγραφή</td></tr>";
       	  
       	  EntityReport e = entityMenu.getEntityReport();
       	  int len =e.getEntityFilterSettings().length;
       	  for(int f=0;f<len;f++)
       	  {
       	     String field = e.getEntityFilterSettings()[f].getCaption();	
       	  	 strFields=strFields+" "+field+", ";
       	  }         	  
       	  
       	  strPanels=strPanels+"<tr><td>επιλογές για φιλτράρισμα των δεδομένων της εκτύπωσης: "+strFields+"</td></tr>";
       	  //strPanels=strPanels+"<tr><td>"+strFields+"</td></tr>";
       	  
       	  int lenGroups = e.getEntityReportBands().length;
       	  EntityReportBand[] erg = e.getEntityReportBands();
       	  String strGroups="";
       	  for(int lg = 0 ;lg<lenGroups;lg++)
       	  {
       	  	strGroups=strGroups+" ενότητας "+erg[lg].getCaption()+", ";
       	  }
       	  strPanels=strPanels+"<tr><td>Επίσης επιλογές για εμφάνιση και ταξινόμηση εγγραφών: "+strGroups+"</td></tr>";
       	  
       }
       else if(intType==ENTITY_TYPE_PARAMETER)
       {
       	  EntityParameter e = entityMenu.getEntityParameter();
       	  int lenPanels = e.getEntityPanel().length;
       	  strPanels=strPanels+"<tr><td><b>Λίστα με εγραφές "+e.getStrOfMany()+".</b></td></tr>";
       	  strPanels=strPanels+"<tr><td>ενέργειες: εισαγωγή,επεξεργασία, διαγραφή, πρότυπα, εκτύπωση/εξαγωγή, προτιμήσεις</td></tr>";
       	  strPanels=strPanels+"<tr><td>Ύστερα απο επιλογή μιας εγγραφής (διπλό κλικ) έχετε τις παρακάτω δυνατότητες:</td></tr>";
       	  for(int lp =0; lp<lenPanels;lp++)
       	  {
           if(e.getEntityPanel()[lp].getType().equalsIgnoreCase("ODOR"))
           {
           	strPanels=strPanels+"<tr><td><b>"+(lp+1)+". "+e.getEntityPanel()[lp].getTitle()+" "+e.getStrOfOne()+"</b> (καταχώρηση-τροποποίηση)</td></tr>";
           	strPanels=strPanels+"<tr><td>ενέργειες: επιστροφή στη λίστα, εισαγωγή, διαγραφή, αποθήκευση, πρότυπα, προεπισκόπηση εκτύπωσης</td></tr>";
           	strPanels=strPanels+"<tr><td>πεδία:</td></tr>";
       	   int len =e.getEntityPanel()[lp].getDBFields().length;
       	   for(int f=0;f<len;f++)
       	   {
       	  	 if(e.getEntityPanel()[lp].getDBFields()[f].getGroupOfComps()==-1)
       	  	 {
       	  	 	
       	  	 }
       	  	 else
       	  	 {
       	         String field = e.getEntityPanel()[lp].getDBFields()[f].getCaption();	
       	  	     strFields=strFields+" "+field+", ";       	  	 	
       	  	 }
       	   }
       	   strPanels=strPanels+"<tr><td>"+strFields+"</td></tr>";
           }
           else if(e.getEntityPanel()[lp].getType().equalsIgnoreCase("STATS"))
           {
           	 strPanels=strPanels+"<tr><td><b>"+(lp+1)+". "+e.getEntityPanel()[lp].getTitle()+" "+e.getStrOfOne()+"</b> (προβολή στατιστικών)</td></tr>";
           	 strPanels=strPanels+"<tr><td>ενέργειες: προεπισκόπηση εκτύπωσης, εξαγωγή σε αρχείο, προτιμήσεις</td></tr>";
           }
           else
           {
           	  System.out.println("DialogHelp.navTreeSelection ENTITY_TYPE_DATAENTRY unknown type "+e.getEntityPanel()[lp].getType());
           }
       	  }       	  
       }
       else if(intType==ENTITY_TYPE_STATISTICS)
       {
       	 EntityStatistics[] e = entityMenu.getEntityStatistics();
       	  int lenPanels = e.length;
       	  
       	  for(int lp =0; lp<lenPanels;lp++)
       	  {    
       	  	if(lp==1)   	 
       	  	{
       	  		strPanels=strPanels+"<tr><td>Ύστερα απο επιλογή μιας εγγραφής (διπλό κλικ) μπορείτε να δείτε τα επόμενα επίπεδα:</td></tr>";
       	  	}
       	  	   
       	    strPanels=strPanels+"<tr><td><b>"+e[lp].getCaption()+"</b> (προβολή στατιστικών)</td></tr>";
            strPanels=strPanels+"<tr><td>ενέργειες: προεπισκόπηση εκτύπωσης, εξαγωγή σε αρχείο, προτιμήσεις</td></tr>";
       	  }
       }
       else
       {
       	  System.out.println("DialogHelp.navTreeSelection type unknown "+intType);
       }
       
              
       String strHTML = "<html>"+
       	"<table width = \"100%\">"+
       	"<tr><td bgcolor=\"#cccccc\"><center><b>"+VariablesGlobal.appName+" εκδ "+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+"</b></center></td></tr>"+
       	"<tr><td>"+	
       	"<table>"+
       	"<tr><td><img src=\""+entityMenu.getEntityIcon()+"\"></td><td><b><font size=\"4\">"+entityMenu.getEntityCaption()+"</font></b></td></tr>"+
       	strChilds+
       	"</table>"+
       	"<tr><td><hr width=\"50%\" size=\"1\" noshade /></td></tr>"+
       	"<table>"+
        "<tr><td>τί εμφανίζει:</td></tr>"+
        "<tr><td>bla blue sd ghjd wgeh gewhg ogehoighihgi oireo ,dsmbv,ds oihgohoi jlkr lkj oiew hkewtlehw ewn</td></tr>"+
        "<tr><td>πότε χρεισιμοποιήται:</td></tr>"+
        "<tr><td>sd ghjd wgeh gewhg ogehoighihgi oireo ,dsmbv,ds oihgohoi jlkr lkj oiew hkewtlehw ewn</td></tr>"+
        "<tr><td><hr width=\"50%\" size=\"1\" noshade /></td></tr>"+
        strPanels+
        
        "</table>"+
        "</tr></td>"+	
        "<tr><td bgcolor=\"#cccccc\"><center> (c) "+VariablesGlobal.appName+"  "+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+" - "+VariablesGlobal.appVersionYear+"</center></td></tr>"+  //  colspan=\"2\"
       	"</table></html>";
       setTextPaneText(strHTML);
       
       this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
     }


  private DefaultMutableTreeNode getNode(String title)
  {
  	int startRow = 0;
    //TreePath path = null;
    TreePath path = treeNavigation.getNextMatch(title, startRow, Position.Bias.Forward);
    DefaultMutableTreeNode entityNode = (DefaultMutableTreeNode)path.getLastPathComponent();
    return entityNode;
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
  }   
  
  private void setTextPaneText(String text)
  {
  	txtPane.setText(text);
  	
    /*if (helpURL != null) {
      try
      {
        editorPane.setPage(helpURL);
      }
      catch (IOException e)
      {
        System.err.println("Attempted to read a bad URL: " + helpURL);
      }
    }
    else
    {
    System.err.println("Couldn't find file: TextSamplerDemoHelp.html");
    }
  	*/
  }
  
   private void addNodes()
   {

       	DefaultMutableTreeNode root = new DefaultMutableTreeNode("r");
       	treeNavigation.setRootVisible(false);
//        navigationTreeModel.setRoot(root);
        
        DefaultMutableTreeNode base;
        base = new DefaultMutableTreeNode("μενού");        
//       	navigationTreeModel.insertNodeInto(base, root, 0);	
        
       EntityData entityData = new EntityData();

       // entityData.loadGenericData();
        
        boolean[] isNodeVisible ={true, true, true, true,true};
     	entityData = new EntityData();
       
       dataTree = entityData.loadAndGetDataTreeNode(isNodeVisible);
        
       presentJtreeFromDataTree(base, dataTree); 
        
       /*DefaultMutableTreeNode leafs;
       for(int l=0;l<5;l++)
       {

            leafs = new DefaultMutableTreeNode("title "+l);
       	    navigationTreeModel.insertNodeInto(leafs, base, l);	
       }  */    	
   	
   }

    
    /*public void setEntity(JPanel pnl, int typeOfPanelIn, String title)
    {
    	panelToShow = pnl;
    	typeOfPanel=typeOfPanelIn;
    	panelToAdd = pnl;
    	panelMain.add(panelToAdd, BorderLayout.CENTER);
    	this.setTitle(title);
    
    }*/

    private void presentJtreeFromDataTree(DefaultMutableTreeNode root, DataTree dataTreeIn)
    {
    	 DefaultMutableTreeNode base;
    	 /*int countChildren = dataTreeIn.getRootElement().getNumberOfChildren();
    	 System.out.println("PanelManagement.presentJtreeFromDataTree "+countChildren);
    	 for(int n=0;n<countChildren;n++)
    	 {
          
          //System.out.println("PanelManagement.presentJtreeFromDataTree "+dataTreeIn.getRootElement().getChildFromIndex(n));
          base = new DefaultMutableTreeNode(dataTreeIn.getRootElement().getChildFromIndex(n));                                              
          root.add(base);    	 	
    	 	
    	 }*/
         DataTreeNode dTreeNodeRoot = dataTreeIn.getRootElement();
         DefaultMutableTreeNode mutableTreeNodeRoot = root;

     
      int level =0;
      int countChildren = dataTreeIn.getRootElement().getNumberOfChildren();    
      for(int n=0;n<countChildren;n++) // -------- level 0
      {
         //System.out.println("PanelManagement.presentJtreeFromDataTree n "+dTreeNode.getChildFromIndex(n)+" children "+dTreeNode.hasNodeChildren());
         
         DataTreeNode dTreeNodeChild = dTreeNodeRoot.getChildFromIndex(n); 
         EntityMenu entityMenu = (EntityMenu)dTreeNodeChild.getData();
         
         //entityMenu.getEntityIcon()         	
    	 DefaultMutableTreeNode mutableTreeNodeChild = new DefaultMutableTreeNode(dTreeNodeChild);
    	 mutableTreeNodeRoot.add(mutableTreeNodeChild);
    	 //System.out.println("PanelManagement.presentJtreeFromDataTree n "+dTreeNodeChild.getChildFromIndex(n));

       

    	 
    	 //System.out.println("PanelManagement.presentJtreeFromDataTree ch "+listChildren.size());
    	 if(dTreeNodeChild.hasNodeChildren())//----------- level 1
    	 {
    	
    	     for(int v=0;v<dTreeNodeChild.getNumberOfChildren();v++)
      	     {


                   DataTreeNode dTreeNodeChild2 = dTreeNodeChild.getChildFromIndex(v);
    	           DefaultMutableTreeNode mutableTreeNodeChild2 = new DefaultMutableTreeNode(dTreeNodeChild2);
    	           mutableTreeNodeChild.add(mutableTreeNodeChild2);
    	        
    	               	 		
    	                if(dTreeNodeChild2.hasNodeChildren())//------------- level 2
    	                {
            	
    	                     for(int h=0;h<dTreeNodeChild2.getNumberOfChildren();h++)
      	                     {
                   
                                 //base = new DefaultMutableTreeNode(dTreeNodeChild2.getChildFromIndex(h));                                              
                                 //mutableTreeNodeChild2.add(base);
                                 //mutableTreeNodeChild.add(mutableTreeNodeChild2);    	 	
      	                         //System.out.println("PanelManagement.presentJtreeFromDataTree +"+dTreeNodeChild.getChildFromIndex(v));

                                 DataTreeNode dTreeNodeChild3 = dTreeNodeChild2.getChildFromIndex(h);
    	                         DefaultMutableTreeNode mutableTreeNodeChild3 = new DefaultMutableTreeNode(dTreeNodeChild3);
    	                         mutableTreeNodeChild2.add(mutableTreeNodeChild3);


    	                        if(dTreeNodeChild3.hasNodeChildren())//------------- level 3
    	                        {
            	
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
    	 		                         System.out.println("error PanelManagement.presentJtreeFromDataTree level 4 of tree not supported");
    	 		                       }
    	 		
                          	         }

    	                        } 

    	 		
                          	     }

    	                }    	 		
    	 		
    	 		
    	 		
    	     }

    	 }
      }
    	
    	
    }

  public EntityMenu getEntityMenuObjectForNode( int intLeaf)
  {
     	     
       EntityMenu entityMenu = new EntityMenu();

       // if menu object
       Object nodeInfo = null;
       String nodeText = null;
      
      //System.out.println("PanelManagement.navTreeSelection "+objEntity);
    //   if(objEntity==null)//              is  tree selection
    //   {    
              //node = (DefaultMutableTreeNode) selectedTreeObject;
   //           nodeInfo = node.getUserObject();
        
   //           nodeText = node.toString();
   //           String nodeParent = node.getParent().toString();
                        

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

    //similar in PanelEditOneDataRec
    private class TreeRendererIcons extends DefaultTreeCellRenderer implements Constants
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
                 
                 entityMenu = getEntityMenuObjectForNode(row-intLeafsBeforeMenu);
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
/*                     else if (node.getParent().toString().equalsIgnoreCase(getNodeString(REPORTS_CAT_ARRAY, node.getParent())))
                     {        
                        setIcon(ICO_PRINT_PREVIEW16);       
                     }    */             	
                 	
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
         	System.out.println("error DialogHelp.TreeRendererIcons.getNode "+title+" "+path+" "+startRow);
         }
         
         return entityNode;
      }
    
    
    }    
   
     // for cell renderer
  private String getNodeString(String[] title, TreeNode node) // array for reports)
  {
  	return this.getNode(title,node).toString();
  } 	
    	
    public void display()
    {
        this.pack();
        this.setSize(700,640);  // width/height
        locateOnCenterOfTheScreen();
    	this.setVisible(true);
    }
    
    private void closeDialog()
    {
    	if(typeOfPanel==PANEL_TYPE_EDITONEDATAONEREC)
    	{
    	   PanelEditOneDataRec panel = (PanelEditOneDataRec)panelToAdd;  
    	   if(panel.toClosePanelOrGoBackAskIfDataChanged())
    	   {
    	      dispose();		
    	   }    		
    	}
    	else if (typeOfPanel== PANEL_TYPE_TASK || typeOfPanel== PANEL_TYPE_ONEDATAMANYREC || typeOfPanel==PANEL_TYPE_ANY )
    	{
    		dispose();
    	}
    	else
    	{   
    		dispose();
    		System.out.println("DialogHelp.closeDialog typeOfPanelIn '"+typeOfPanel+"' not found");
    	}
    	

    	
    }

    //create a window listener to respond to the window close click
   private void setCloseClick()
   {
   	setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);   
    addWindowListener(new WindowAdapter()
    {
       public void windowClosing(WindowEvent e)
       {    closeDialog();   }
    });
   }

    
      private void locateOnCenterOfTheScreen()
     {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
      }

    class  ActionClose extends AbstractAction                 
    {       
        public ActionClose()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{           btnClose.doClick();        }    	
    }                


    public static void main(String args[])
    {
    	
    	VariablesGlobal.globalCompanyId="1";
    	VariablesGlobal.globalCompanyName="no";
    	VariablesGlobal.globalDate="01-01-2009";
    	//VariablesGlobal.globalDeliveryId="1";
    	VariablesGlobal.globalDirConfiguration = System.getProperty("user.dir");
    	VariablesGlobal.globalUserId="user";
    	//VariablesGlobal.globalYear="2009";    	
    	
        // set right click on texts
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueTxtRightClick());     	
    	
    	UtilsGui utilsGui = new UtilsGui();
    	utilsGui.setLookAndFeel();
    	
    	DialogHelp dialogHelp =new DialogHelp(null);
    	

    	
    	dialogHelp.display();
    }

}   