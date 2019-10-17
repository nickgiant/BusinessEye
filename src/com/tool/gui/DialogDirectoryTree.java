// created 01-03-2008
// http://www.java2s.com/Code/Java/Swing-JFC/FileTreewithPopupMenu.htm

 package com.tool.gui;
 
 import com.tool.guicomps.*;
 
 import java.io.InputStreamReader ;  
 import java.io.BufferedReader;
 import javax.swing.JOptionPane;
 import java.io.InputStream;
 import java.io.File;


 import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


 import java.io.FilenameFilter;

 import java.io.FileNotFoundException;
 import java.io.UnsupportedEncodingException;
 
 import java.io.FileFilter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 import java.awt.Frame;

 import javax.swing.JTree;
 import javax.swing.tree.TreeCellRenderer;
 import javax.swing.tree.TreePath;
 import javax.swing.tree.TreeSelectionModel;
 import javax.swing.event.TreeExpansionListener;
 import javax.swing.event.TreeExpansionEvent;
 //import javax.swing.JTable;
 import javax.swing.JDialog;
 import javax.swing.JPanel;
// import javax.swing.JTabbedPane;
 import javax.swing.JFrame;
 import javax.swing.JButton;
 //import javax.swing.JTabbedPane;
 import javax.swing.JTextField;
 import javax.swing.JLabel;
 //import javax.swing.JTextArea;
 import javax.swing.JScrollPane;
 //import javax.swing.JList;
 import javax.swing.ListSelectionModel;
 import javax.swing.event.ListSelectionListener;
 import javax.swing.event.ListSelectionEvent;
 import javax.swing.SwingUtilities;
 import javax.swing.UIManager;
 
//import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.Icon;

import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import java.util.HashMap;
import java.util.ArrayList;

import java.util.Vector;
//import guicomps.*;
 
public class DialogDirectoryTree extends JDialog implements Constants
{
	private static DialogDirectoryTree dDirTree;
	private JFrame parentFrame;
   private static JLabel lblCurrentDir;
   private static JTree tree;
   private static DefaultTreeModel treeModel;
   private JButtonForPanelDecorated btnSet;
   private JButtonForPanelDecorated btnClose; 
   private static String selectedDir;
   private static String currentDirInto;
   
   private static DefaultMutableTreeNode rootNode;
   private static DefaultMutableTreeNode node;
   private static DefaultMutableTreeNode base;
   
   private static String strPreLabel="κατάλογος: ";
   private static String rootNodeName="my computer";
    //private String[] filesToZip;
   
   
   	//create map of names to node objects
	private static HashMap namesToNodes = new HashMap();

    
    public DialogDirectoryTree(JFrame parent)//(Frame parent, boolean modal)
    {
       super(parent, "κατάλογος φακέλων", true);
       parentFrame=parent;
      
      
    
    rootNode = new DefaultMutableTreeNode(rootNodeName);
    File[] roots = File.listRoots(); // list drives
    for (int k=0; k<roots.length; k++)
    {
      node = new DefaultMutableTreeNode( new FileNode(roots[k]));
      rootNode.add(node);
      node.add(new DefaultMutableTreeNode( Boolean.valueOf(true) ));
    }
      
      
        setLayout(new BorderLayout());
    	
    	lblCurrentDir = new JLabel(strPreLabel);
    	 tree= new JTree();
    	 
    	 tree.setRootVisible(true);
         tree.setBorder(null);
       
       
        treeModel = new DefaultTreeModel(rootNode); 
        

                    
        tree.setModel(treeModel);
        //tree.setRootVisible(false);
        node = rootNode;
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        tree.addTreeExpansionListener(new DirExpansionListener());

        tree.addTreeSelectionListener(new DirSelectionListener());

       tree.setShowsRootHandles(true); 
       tree.setEditable(false);  
       
        tree.putClientProperty("JTree.lineStyle", "Angled");  
        
        TreeCellRenderer renderer = new IconCellRenderer();
        tree.setCellRenderer(renderer);
     
       /* tree.addTreeSelectionListener(new TreeSelectionListener()
        {
            public void valueChanged(TreeSelectionEvent e)
            {  
         
                
          TreePath path = e.getPath();
          int pathCount = path.getPathCount();
          String strPath = "";
            
          for ( int i = 0 ; i < pathCount; i++)
          {
               strPath =strPath+ path.getPathComponent(i).toString();
               strPath=strPath.replaceFirst("root","");
                //if (i+1 != pathCount) strPath += "#";
          }
          
          System.out.println(tree.getMinSelectionRow()+"+"+strPath);
           
           DefaultMutableTreeNode tn = (DefaultMutableTreeNode) path.getLastPathComponent();
           retrieveDirs(strPath,tn,tree.getMinSelectionRow(),false);
           
           //treeModel.reload();      
               
               lblCurrentDir.setText("κατάλογος: "+strPath);
               selectedDir=strPath;//e.getPath().getLastPathComponent().toString();
               
               
               
               //navTreeSelection((DefaultMutableTreeNode)e.getPath().getLastPathComponent());
            }
        });*/
    	 
    	 JScrollPane scrollTree = new JScrollPane(tree);
    	JxPanel panelBottom = new JxPanel(new FlowLayout());
    	btnSet = new JButtonForPanelDecorated("OK");
    	btnSet.setIcon(ICO_OK16);
    	btnSet.addActionListener(new ActionListener()
       {
	       public void actionPerformed(ActionEvent e) 
	       { 
	          setSelectedAndClose(); 
	          
	       }
	   });	
    	btnClose = new JButtonForPanelDecorated("άκυρο");
    	btnClose.setIcon(ICO_CANCEL16);
    	btnClose.addActionListener(new ActionListener()
       {
	       public void actionPerformed(ActionEvent e) 
	       {           
	          close();
	       }
	   });	
    	panelBottom.add(btnSet);
    	panelBottom.add(btnClose);
    	
    	JPanelDecorated panelMain =new JPanelDecorated(new BorderLayout());
    	
    	panelMain.add(lblCurrentDir, BorderLayout.PAGE_START);
    	panelMain.add(scrollTree, BorderLayout.CENTER);
    	panelMain.add(panelBottom, BorderLayout.PAGE_END);
    	
    	this.add(panelMain);
    	
    	this.pack();
    	this.setSize(320,450);
      
         locateOnCenterOfTheScreen();
         setCloseWindowClick();
         
  }       
         
         
         
         
         
   /*      
           public DirTree()
  {*/
    //super("Directories Tree [Popup Menus]");
    //setSize(400, 300);

    //DefaultMutableTreeNode top = new DefaultMutableTreeNode( new IconData(ICON_COMPUTER, null, "Computer"));

   /* DefaultMutableTreeNode node;
    File[] roots = File.listRoots(); // list drives
    for (int k=0; k<roots.length; k++)
    {
      node = new DefaultMutableTreeNode(new IconData(ICON_DISK, null, new FileNode(roots[k])));
      root.add(node);
      node.add(new DefaultMutableTreeNode( new Boolean(true) ));
    }*/

   // treeModel = new DefaultTreeModel(top);
   // tree = new JTree(treeModel);

                //tree.putClientProperty("JTree.lineStyle", "Angled");

    /*TreeCellRenderer renderer = new IconCellRenderer();
    tree.setCellRenderer(renderer);*/

   /* tree.addTreeExpansionListener(new 
      DirExpansionListener());

    tree.addTreeSelectionListener(new 
      DirSelectionListener());*/

    /*tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION); 
    tree.setShowsRootHandles(true); 
    tree.setEditable(false);*/

   /* JScrollPane s = new JScrollPane();
    s.getViewport().add(tree);
    getContentPane().add(s, BorderLayout.CENTER);

    lblCurrentDir = new JTextField();
    lblCurrentDir.setEditable(false);
    getContentPane().add(lblCurrentDir, BorderLayout.NORTH);
    

    WindowListener wndCloser = new WindowAdapter()
    {
      public void windowClosing(WindowEvent e) 
      {
        System.exit(0);
      }
    };
    addWindowListener(wndCloser);
    
    setVisible(true);
  }*/

  DefaultMutableTreeNode getTreeNode(TreePath path)
  {
    return (DefaultMutableTreeNode)(path.getLastPathComponent());
  }

  FileNode getFileNode(DefaultMutableTreeNode node)
  {
    if (node == null)
      return null;
    Object obj = node.getUserObject();
    /*if (obj instanceof IconData)
      obj = ((IconData)obj).getObject();*/
    if (obj instanceof FileNode)
      return (FileNode)obj;
    else
      return null;
            
      //return node;
  }
         
         
   
         
         
         
  
 
    public DialogDirectoryTree()//(Frame parent, boolean modal)
    {
       this.setTitle("επιλογή καταλόγου");
       //initialize();
       locateOnCenterOfTheScreen();
    }

    public static void initialize(JFrame parent)
    {
        Frame frame = JOptionPane.getFrameForComponent(null);
        dDirTree = new DialogDirectoryTree(parent);
    }    

    public void initialize()
    {
    	Frame frame = JOptionPane.getFrameForComponent(null);
    	dDirTree = new DialogDirectoryTree(null);
    	

    /*	String laf="java";
       try
       {
    	
         laf = UIManager.getSystemLookAndFeelClassName() ;   
         UIManager.setLookAndFeel(laf);   // when there is chosen laf
       }
       catch(ClassNotFoundException cnfe)   
       {
       	System.out.println(cnfe.getMessage());
       }
        catch (Exception exc)
        {System.out.println("Error "+laf+": "+exc);} 
        
        JDialog.setDefaultLookAndFeelDecorated(true);  
        JFrame.setDefaultLookAndFeelDecorated(true);*/
        
    	    //saveFile="backup"+database+".sql";
            //saveFileZip="backup"+database+".zip";
            
            //openFileZip="backup.zip";

    	
    }
    
   private void setCloseWindowClick()
   {
   	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
    addWindowListener(new WindowAdapter()
    {
       public void windowClosing(WindowEvent e)
       {    close();   }
    });
   }

    public void setSelectedAndClose()
    {
    	    //selectedDir=currentDirInto;
    	    //System.out.println("DialogDirectoryTree.setSelectedAndClose "+selectedDir);
         	this.dispose();
    }

    public void close()
    {
    	//setVisible(false);
    	selectedDir=""; //if "" textbtn changes nothing
    	this.dispose();
    	//tabbedPane.requestFocusInWindow();
    }	

    
    public static void showDialog(String currentDirIn)
    {
    	//node.removeAllChildren();   
    	currentDirInto=currentDirIn;

    	if(currentDirInto.equals(""))
    	{
    		currentDirInto="C:\\";
    	}
    	
    	
    	
    	lblCurrentDir.setText(strPreLabel+currentDirInto);
    	
    	System.out.println(currentDirInto);
    	
    	//tree.expandPath(new TreePath(getNode(currentDirInto)));
    	
    	
    	//String[] path = String[];
    	
    	//tree.expandPath(new TreePath(getNode(currentDirInto)));
        //System.out.println(rootNodeName+currentDirInto);
    	  
        dDirTree.setVisible(true);
        

       
    	
    }
    
    private static DefaultMutableTreeNode getNode(String name)
	{
		//make lowercase
		name = name.toLowerCase();
		
		//get node from map
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)(namesToNodes.get(name));
		
		//if not defined
		if(node == null)
		{
			//create new node
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(name);	
			
			//add to map
			namesToNodes.put(name, newNode);
			
			//return new node
			return newNode;
		}
		else
		{
			//return old node
			return node;
		}
	}
    
    
    public static String getSelectedDir()
    {
    	return selectedDir;
    }
    
    private void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }




    // Make sure expansion is threaded and updating the tree model
    // only occurs within the event dispatching thread.
    class DirExpansionListener implements TreeExpansionListener
    {
        public void treeExpanded(TreeExpansionEvent event)
        {
            final DefaultMutableTreeNode node = getTreeNode(event.getPath());
            final FileNode fnode = getFileNode(node);

            Thread runner = new Thread() 
            {
              public void run() 
              {
                if (fnode != null && fnode.expand(node)) 
                {
                  Runnable runnable = new Runnable() 
                  {
                    public void run() 
                    {
                       treeModel.reload(node);
                    }
                  };
                  SwingUtilities.invokeLater(runnable);
                }
              }
            };
            runner.start();
        }

        public void treeCollapsed(TreeExpansionEvent event) {}
    }

  class DirSelectionListener implements TreeSelectionListener 
  {
    public void valueChanged(TreeSelectionEvent event)
    {
      DefaultMutableTreeNode node = getTreeNode(event.getPath());
      FileNode fnode = getFileNode(node);
      if (fnode != null)
      {
    
        String dir=fnode.getFile().getAbsolutePath();
        lblCurrentDir.setText(strPreLabel+dir);
        selectedDir=dir;
      }
      else
      {
      
        lblCurrentDir.setText(strPreLabel);
        selectedDir="";
      }
    }
  }



class FileNode
{
  protected File file;

  public FileNode(File fileIn)
  {
    file = fileIn;
  }

  public File getFile() 
  { 
    return file;
  }

  public String toString() 
  { 
    return file.getName().length() > 0 ? file.getName() : 
      file.getPath();
  }

  public boolean expand(DefaultMutableTreeNode parent)
  {
    DefaultMutableTreeNode flag = (DefaultMutableTreeNode)parent.getFirstChild();
    if (flag==null)    // No flag
      return false;
    Object obj = flag.getUserObject();
    if (!(obj instanceof Boolean))
      return false;      // Already expanded

    parent.removeAllChildren();  

    File[] files = listFiles();
    if (files == null)
      return true;

    Vector v = new Vector();

    for (int k=0; k<files.length; k++)
    {
      File f = files[k];
      if (!(f.isDirectory()))
        continue;

      FileNode newNode = new FileNode(f);
      
      boolean isAdded = false;
      for (int i=0; i<v.size(); i++)
      {
        FileNode nd = (FileNode)v.elementAt(i);
        if (newNode.compareTo(nd) < 0)
        {
          v.insertElementAt(newNode, i);
          isAdded = true;
          break;
        }
      }
      if (!isAdded)
        v.addElement(newNode);
    }

    for (int i=0; i<v.size(); i++)
    {
      FileNode nd = (FileNode)v.elementAt(i);
      //IconData idata = new IconData(DirTree.ICON_FOLDER, DirTree.ICON_EXPANDEDFOLDER, nd);
      DefaultMutableTreeNode node = new DefaultMutableTreeNode(nd);
      parent.add(node);
        
      if (nd.hasSubDirs())
        node.add(new DefaultMutableTreeNode( Boolean.valueOf(true) ));
    }

    return true;
  }

  public boolean hasSubDirs()
  {
    File[] files = listFiles();
    if (files == null)
      return false;
    for (int k=0; k<files.length; k++)
    {
      if (files[k].isDirectory())
        return true;
    }
    return false;
  }
  
  public int compareTo(FileNode toCompare)
  { 
    return  file.getName().compareToIgnoreCase(toCompare.file.getName() ); 
  }

  protected File[] listFiles()
  {
    if (!file.isDirectory())
      return null;
    try
    {
      return file.listFiles();
    }
    catch (Exception ex)
    {
      JOptionPane.showMessageDialog(null, 
        "Error reading directory "+file.getAbsolutePath(),
        "Warning", JOptionPane.WARNING_MESSAGE);
      return null;
    }
  }
}


class IconCellRenderer extends  JLabel  implements TreeCellRenderer
{
  protected Color m_textSelectionColor;
  protected Color m_textNonSelectionColor;
  protected Color m_bkSelectionColor;
  protected Color m_bkNonSelectionColor;
  protected Color m_borderSelectionColor;

  protected boolean m_selected;

  public IconCellRenderer()
  {
    super();
    m_textSelectionColor = UIManager.getColor("Tree.selectionForeground");
    m_textNonSelectionColor = UIManager.getColor("Tree.textForeground");
    m_bkSelectionColor = UIManager.getColor("Tree.selectionBackground");
    m_bkNonSelectionColor = UIManager.getColor("Tree.textBackground");
    m_borderSelectionColor = UIManager.getColor("Tree.selectionBorderColor");
    setOpaque(false);
  }

  public Component getTreeCellRendererComponent(JTree tree,Object value, boolean sel, boolean expanded, 
  boolean leaf, int row, boolean hasFocus) 
    
  {
    DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;  Object obj = node.getUserObject();
    setText(obj.toString());

                if (obj instanceof Boolean)
                  setText("Retrieving data...");

    /*if (obj instanceof IconData)
    {
      IconData idata = (IconData)obj;
      if (expanded)
        setIcon(idata.getExpandedIcon());
      else
        setIcon(idata.getIcon());
    }
    else*/
    //  setIcon(null);
      
      if (expanded)
        setIcon(ICON_TREEFOLDER_OPENED);
      else
        setIcon(ICON_TREEFOLDER_CLOSED);

    setFont(tree.getFont());
    setForeground(sel ? m_textSelectionColor :  m_textNonSelectionColor);
    setBackground(sel ? m_bkSelectionColor :   m_bkNonSelectionColor);
    m_selected = sel;
    return this;
  }
    
  public void paintComponent(Graphics g) 
  {
    Color bColor = getBackground();
    Icon icon = getIcon();

    g.setColor(bColor);
    int offset = 0;
    if(icon != null && getText() != null) 
      offset = (icon.getIconWidth() + getIconTextGap());
    g.fillRect(offset, 0, getWidth() - 1 - offset,
      getHeight() - 1);
    
    if (m_selected) 
    {
      g.setColor(m_borderSelectionColor);
      g.drawRect(offset, 0, getWidth()-1-offset, getHeight()-1);
    }

    super.paintComponent(g);
    }
}


   public static void main(String[] args)
   {
    	
         DialogDirectoryTree d = new DialogDirectoryTree(null);
         //d.locateOnCenterOfTheScreen();
        // d.retrieveDrives("C:\\");
         //d.showDialog("C:\\");
         d.setVisible(true);
         //bckp.backupDB("farmersvat2"); 
         //bckp.zip();
         //bckp.unzip();
         //bckp.restoreDB("FarmersVat",);

   }


}


