// created 28-09-2006

package com.tool.guicomps;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.util.Set;
import java.util.HashSet;

import java.awt.event.KeyEvent;
import java.awt.KeyboardFocusManager;

public class JxPanelMenu extends JPanel implements Constants
{
  public JxPanelMenu() 
  {
  	setOpaque(false);
      setForwardKeys();

  }


  // use of tab and enter to change focused field
  // also in PanelManagement
  private void setForwardKeys()
  {   
        Set forwardKeys = new HashSet();
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0));
        //forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,forwardKeys);  
        	
        Set backwardKeys = new HashSet();
        backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0));
        //forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0));
        this.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,backwardKeys);         	
  }  
}