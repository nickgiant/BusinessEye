package com.tool.guicomps;

import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class WindowSelectTab extends JWindow implements Constants
{
    private JLabel lblMain;
    private JLabel lblBack;
    private JTree tree;
    private JScrollPane treeScrollPane;
    private JPanel panelMain;
    private JPanel panelBevel;
    private JTabbedPane tabbedPane;
    private  ImageIcon[] icons;
    
    public WindowSelectTab(JFrame parent,JTabbedPane tabbedPaneIn, String[] tabs , ImageIcon[] iconsIn ,String title , int location, int selected)
    {
    	super(parent);
    	tabbedPane=tabbedPaneIn;
    	 icons=iconsIn;
    	//getContentPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    	panelMain = new JPanel(new BorderLayout());
    	panelBevel = new JPanel(new BorderLayout());
    	panelBevel.setBorder(new BevelBorder(BevelBorder.RAISED));
    	panelMain.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
    	
    	lblBack = new JLabel();
        //lblMain = new JLabel(s,JLabel.CENTER);
       //lblMain = new LabelGradient(lblBack.getBackground().darker(),lblBack.getBackground().brighter(),40);     
       lblMain = new JLabel();     
       //System.out.println("WindowWait "+this.getName()+this.getHeight());
       lblMain.setText(title);
       lblMain.setVerticalTextPosition(JLabel.CENTER);
       lblMain.setHorizontalTextPosition(JLabel.CENTER);
      lblMain.setBorder(BorderFactory.createEmptyBorder(6, 5, 6, 5));//top,left,bottom,right
      lblMain.setFont(lblMain.getFont().deriveFont(Font.BOLD));
      //lblMain.setOpaque(true);
      
      //lblMain.setBackground(java.awt.SystemColor.activeCaption);//Color.WHITE);
      //lblMain.setForeground(java.awt.SystemColor.activeCaptionText);
       
       //System.out.println("WindowSelectTab tabs.length"+tabs.length);
       
       tree = new JTreeDec(tabs);
       tree.setCellRenderer(new TreeRendererIcons(icons));
       
       tree.setSelectionInterval(selected,selected);
       tree.setRootVisible(false);
       tree.setBorder(null);
       tree.setFocusable(true);
       tree.requestFocus(true);
       tree.setRowHeight(24);//24,34
       tree.setFont(new Font(this.getFont().getName(),Font.PLAIN, 12));// (String name, int style, int size) 

       //tree.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
       //tree.setLayoutOrientation(JList.VERTICAL);
       //tree.setFont(new Font(tree.getFont(),Font.PLAIN, 12));// (String name, int style, int size) 
       treeScrollPane = new JScrollPane();
       
       treeScrollPane.setViewportView(tree);
       tree.addMouseListener(new MouseAdapter()
       {
            public void mouseClicked(MouseEvent e)
            {
            	tree = (JTreeDec)e.getSource();
                if (e.getClickCount() == 1) // make it 2 for doubleclick
                {   
                    //retrievePrimKeyValue( query, selectedTableRow, primKey);
                    //System.out.println("panelManagement.WindowSelectTab table double clicked. selectedTableRow "+selectedTableRow+" PKvalue "+getPrimKeyValue());
                    int sel = tree.getLeadSelectionRow();
                    tabbedPane.setSelectedIndex(sel);
                    close();
                    tabbedPane.requestFocusInWindow();
                }
            } 
       });
        tree.addTreeSelectionListener(new TreeSelectionListener()
        {
        	//Thread thread;
            public void valueChanged(final TreeSelectionEvent e)
            {       
                    tree = (JTreeDec)e.getSource();
                    int selectedIndex =tree.getSelectionRows()[0];
                    
                    tabbedPane.setSelectedIndex(selectedIndex);
                    
                       //double sel =0.00;
                       //double tr = selectedIndex-3.5;  // -8 in order to be in the center of height
                       double rc = tree.getRowCount();
                       double vrc = tree.getVisibleRowCount();
                       double percentageOfRowCount = vrc/rc*100;
                       
                       
                       //sel = tr/rc;
                       double max =treeScrollPane.getVerticalScrollBar().getMaximum();
                       double value =max/percentageOfRowCount;
                       
                       System.out.println("WindowSelectTab value of scroll "+percentageOfRowCount+" "+max+" "+value);
                       treeScrollPane.getVerticalScrollBar().setValue((int)max); 
                    

            }
        });       
       
        
        panelMain.add(lblMain, BorderLayout.PAGE_START);
        panelMain.add(treeScrollPane, BorderLayout.CENTER);
        panelBevel.add(panelMain, BorderLayout.CENTER);
        getContentPane().add(panelBevel);
        
        //getContentPane().setBackground(new Color(223,223,223)); //128  192  223  255black
        
        
        //AnimatedCursor animatedCursor = new AnimatedCursor(this);
        //AnimatedCursor animatedCursor = new AnimatedCursor(SwingUtilities.getAncestorOfClass(JFrame.class, this));
        //animatedCursor.animate();
        
        this.setAlwaysOnTop(true);
        Action actionClose = new ActionClose();
        tree.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        tree.getActionMap().put("close", actionClose);        
        tree.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3 ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        tree.getActionMap().put("close", actionClose);  
        tree.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        tree.getActionMap().put("close", actionClose);         
        
        
        this.setPreferredSize(new Dimension(250,200));//width height
        
        pack();
        setLocationRelativeTo(parent);
        setWindowLocation(location);
        
    }
   
    public void setWindowLocation(int location)
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
    }
    
    private class TreeRendererIcons extends DefaultTreeCellRenderer implements Constants
    {
    	
    	private Color backgroundSelectionColor;
        private Color backgroundNonSelectionColor;
    	private DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
    	private ImageIcon [] icons;
    	
        public TreeRendererIcons(ImageIcon[] iconsIn)
        { 
        icons=iconsIn;
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

          
            //EntityPanel entityPanel =new EntityPanel();
            //  node = (DefaultMutableTreeNode) selectedTreeObject;
            //  nodeInfo = node.getUserObject();
        
           //   nodeText = node.toString();
           //   String nodeParent = node.getParent().toString();
              
              //if (nodeParent.equalsIgnoreCase())
       	

                // Object nodeObj = ((DefaultMutableTreeNode)value).getUserObject();
                // entityPanel = getEntityMenuObjectForNode(getNode(node.toString()), row);
                 
                 //EntityPanelM entityPanelMangement = new EntityPanelM();
                 //entityPanel = epm.getEntityPanel(node.toString());
                 
                 //System.out.println("PanelEditOneDataRec.TreeRendererIcons "+entityPanel.getEntity());
                 
                 //System.out.println("WindowSelectTab.TreeRendererIcons "+icons.length+" "+row);
                 if(icons!=null && row>0)
                 {
                 	
                     setIcon(icons[row]);	
                 }
                 else // = null ico
                 {   
                      System.out.println("WindowSelectTab.TreeRendererIcons icon null row "+row);
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
         TreePath path = tree.getNextMatch(title, startRow, Position.Bias.Forward);
         DefaultMutableTreeNode entityNode = (DefaultMutableTreeNode)path.getLastPathComponent();
         return entityNode;
      }
    
    
    }    
    
   class  ActionClose extends AbstractAction                 
   {       
        public ActionClose()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{      close();      }    	
    }     
    
    public void showWindow()
    {
        setVisible(true);
        
    }
    
    // doesn't working
    public void setText(String message)
    {
       lblMain.setText(message);
    }
    
    public void close()
    {
    	dispose();
    	tabbedPane.requestFocusInWindow();
    }
    
    //dispose
}  
