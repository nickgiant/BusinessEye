// created 05-05-2007

package com.tool.guicomps;

import javax.swing.*;
import javax.swing.event.*;
import java.util.Set;
import java.util.HashSet;

import java.awt.event.*;
import java.awt.*;

public class JxTabbedPane extends JTabbedPane implements Constants
{
      private int intTab =0;
	private int remPane=-1;
  public JxTabbedPane() 
  {
      setForwardKeys();
      setBackwardKeys();
    //  this.setSize(this.getWidth(), 80);
   //   this.setUI(new PlasticTabbedPaneUI()); 
      	// PlasticTabbedPaneUI      // AquaBarTabbedPaneUI deprecated
      //this.addContainerListener(new TabContainerListener(this));
  }
 
    /**
   * Adds a <code>Component</code> represented by a title and no icon.
   * @param title the title to be displayed in this tab
   * @param component the component to be displayed when this tab is clicked
   */
  public void addTab(String title, Component component) // to make tabs higher //
  {
      
        super.addTab(title,null, component);
        //super.addTab(title, doPaintCloseIcon ? new CloseTabIcon() : null, component);
  
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel label = new JLabel(title);
        //label.setOpaque(true);
        //label.setIcon(icon);
        //label.setBackground(Color.black);
        panel.add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 3));      
        //tab button
        
        //add more space to the top of the component
        //pnl.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        setTabComponentAt(intTab,panel);
       //System.out.println("TabbedPaneWithClose.addTab tab "+intTab);
        intTab =intTab+1;        
      
      
      
      
    //addTab(title, null, component);
  }
 

    /**
   * Adds a <code>Component</code> represented by a title and no icon.
   * @param title the title to be displayed in this tab
   * @param component the component to be displayed when this tab is clicked
   */
  public void addTab(String title) // to make tabs higher //
  {
      addTab(title,null);
      
  }  
  
  
  
 /* public void addTab(String caption,JPanel pnl)
  {
      /*
               JLabel lab = new JLabel();
        lab.setPreferredSize(new Dimension(200, 30));
        tabbedPane.setTabComponentAt(0, lab);  // tab index, jLabel

      tabbedPane.addTab("report preferences",panelProp);
      
      */
    /*  JLabel label = new JLabel();
      label.setText(caption);
      super.addTab();
      
  }*/
  
  
  
  
  // first setRemovedPane from TabWithClose, then getRemovedPane from PanelManagement
  public void setRemovedPane(int remPaneIn)
  {
  	//this.addContainerListener(ContainerListener cl)
  	remPane=remPaneIn;
  }

  // for closable
  public int getRemovedPane()
  {
  	//this.addContainerListener(ContainerListener cl)
  	return remPane;
  }
  
  /*
   * use of tab and enter to change focused field
   *  JxPanel, JxTabbedPane, JxxTabbedPane 
   * also in PanelManagement
   */ 
  private void setForwardKeys()
  {   
        Set forwardKeys = new HashSet();
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0));
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,forwardKeys);  
  }  
  
    private void setBackwardKeys()
  {   
        Set backwardKeys = new HashSet();
         
        //backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0));
        backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,KeyEvent.SHIFT_DOWN_MASK));
        backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0));
        this.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,backwardKeys);  
  } 
  
  

}