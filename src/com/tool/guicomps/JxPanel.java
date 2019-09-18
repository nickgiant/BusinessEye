// created 28-09-2006

package com.tool.guicomps;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.util.Set;
import java.util.HashSet;

import java.awt.event.KeyEvent;
import java.awt.KeyboardFocusManager;

public class JxPanel extends JPanel implements Constants
{
  public JxPanel() 
  {
  	super();
  	setOpaque(false);
      setForwardKeys();
      setBackwardKeys();
      textBoxSelectText();
     this.addComponentListener(new ComponentListener()
     {
	        public void componentShown(ComponentEvent e) 
	        {    
    	    textBoxSelectText();
    	    }
            
            public void componentResized(ComponentEvent e) 
	        {    
    	    //textBoxSelectText();
    	    }
            
            public void componentMoved(ComponentEvent e) 
	        {    
    	    //textBoxSelectText();
    	    }
            
            public void componentHidden(ComponentEvent e) 
	        {    
    	    //textBoxSelectText();
    	    }


    	    
      });
  }
  
 
   public JxPanel(LayoutManager lom) 
  {
  	super(lom);
  	setOpaque(false);
      setForwardKeys();
      setBackwardKeys();
      textBoxSelectText();
     this.addComponentListener(new ComponentListener()
     {
	        public void componentShown(ComponentEvent e) 
	        {    
    	    textBoxSelectText();
    	    }
            
            public void componentResized(ComponentEvent e) 
	        {    
    	    //textBoxSelectText();
    	    }
            
            public void componentMoved(ComponentEvent e) 
	        {    
    	    //textBoxSelectText();
    	    }
            
            public void componentHidden(ComponentEvent e) 
	        {    
    	    //textBoxSelectText();
    	    }


    	    
      });
  }
  
  
  private void textBoxSelectText()
  {
  	 for(int c= 0; c<this.getComponentCount(); c++)
  	 {
  	    try
  	    {
//  	    	System.out.println("JxPanel.textBoxSelectText "+this.getComponent(c).getClass());
  	      if (this.getComponent(c).getClass() == Class.forName("javax.swing.JTextField") )
  	      {
//  	      	System.out.println("JxPanel.textBoxSelectText "+c);
  	      	JTextField txt = (JTextField)this.getComponent(c) ;
  	      	txt.setSelectionStart(0);
  	      	txt.setSelectionEnd(txt.getText().length());
                               
  	      }
              else if(this.getComponent(c).getClass() == Class.forName("javax.swing.JTextArea"))
              {
                JTextArea txtArea = (JTextArea)this.getComponent(c) ;
  	      	txtArea.setSelectionStart(0);
  	      	txtArea.setSelectionEnd(txtArea.getText().length());                   
              }
              
  	    }
  	    catch (ClassNotFoundException cex)	 	
  	    {
  	    	System.out.println("error JxPanel.textBoxSelectText "+cex);
  	    }
  	 }
  	
  }

  /*
   *  use of tab and enter to change focused field
   *  JxPanel, JxTabbedPane, JxxTabbedPane 
   *  also in PanelManagement
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