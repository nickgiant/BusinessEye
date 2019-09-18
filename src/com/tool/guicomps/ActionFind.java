//created 14-03-2009
// http://www.jroller.com/santhosh/category/Swing?page=3
package com.tool.guicomps;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.event.*;


// @author Santhosh Kumar T - santhosh@in.fiorano.com 
public abstract class ActionFind extends AbstractAction implements DocumentListener, KeyListener
{ 
       JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); 
       protected JTextField searchField = new JTextField(); 
       JPopupMenu popup = new JPopupMenu(); 
       JComponent component;
       
       public ActionFind()
       { 
           super("Incremental Search"); //NOI18N 
           searchPanel.setBackground(UIManager.getColor("ToolTip.background")); //NOI18N 
           searchField.setOpaque(false); 
           JLabel label = new JLabel("ΞµΟ?Ο?ΞµΟƒΞ·:"); 
           label.setFont(new Font("DialogInput", Font.BOLD, 12)); // for readability 
           searchPanel.add(label); 
           searchPanel.add(searchField); 
           searchField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); 
           searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); 
           popup.setBorder(BorderFactory.createLineBorder(Color.black)); 
           popup.add(searchPanel); 
           searchField.setFont(new Font("DialogInput", Font.PLAIN, 12)); // for readability 
    
           // when the window containing the "comp" has registered Esc key 
           // then on pressing Esc instead of search popup getting closed 
           // the event is sent to the window. to overcome this we 
           // register an action for Esc. 
           searchField.registerKeyboardAction(new ActionListener(){ 
               public void actionPerformed(ActionEvent e){ 
                   popup.setVisible(false); 
               } 
           }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_FOCUSED); 
           
      /*  Action actionCloseSearch = new ActionCloseSearch();
        searchField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "CloseSearch"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        searchField.getActionMap().put("CloseSearch", actionCloseSearch);*/
           
       } 
    
       protected JComponent comp = null; 
       protected boolean ignoreCase; 
    
       /*-------------------------------------------------[ ActionListener ]---------------------------------------------------*/ 
       // not needed because is added later
 //
       public void actionPerformed(ActionEvent ae)
       { 
           if(ae.getSource() == searchField) 
               popup.setVisible(false); 
           else{ 
               comp = (JComponent)ae.getSource();                  // if is != shift should be pressed simultanusly
               ignoreCase = true;//= (ae.getModifiers() & ActionEvent.SHIFT_MASK)==0; 
    
               searchField.removeActionListener(this); 
               searchField.removeKeyListener(this); 
               searchField.getDocument().removeDocumentListener(this); 
               initSearch(ae); 
               searchField.addActionListener(this); 
               searchField.addKeyListener(this); 
               searchField.getDocument().addDocumentListener(this); 
    
               Rectangle rect = comp.getVisibleRect(); 
               popup.show(comp, rect.x, rect.y - popup.getPreferredSize().height - 5); 
               searchField.requestFocus(); 
           } 
       } 
    
       // can be overridden by subclasses to change initial search text etc. 
       protected void initSearch(ActionEvent ae){ 
           searchField.setText(""); //NOI18N 
           searchField.setForeground(Color.black); 
       } 
    
       private void changed(Position.Bias bias){ 
           // note: popup.pack() doesn't work for first character insert 
           popup.setVisible(false); 
           popup.setVisible(true); 
    
           searchField.requestFocus(); 
           searchField.setForeground(changed(comp, searchField.getText(), bias) ? Color.black : Color.red); 
       } 
    
       // should search for given text and select item and 
       // return true if search is successfull 
       protected abstract boolean changed(JComponent comp, String text, Position.Bias bias); 
    
       /*-------------------------------------------------[ DocumentListener ]---------------------------------------------------*/ 
    
       public void insertUpdate(DocumentEvent e){ 
           changed(null); 
       } 
    
       public void removeUpdate(DocumentEvent e){ 
           changed(null); 
       } 
    
       public void changedUpdate(DocumentEvent e){} 


       /*---*/
       
       protected void initSearch(){ 
           searchField.setText(""); //NOI18N 
           searchField.setForeground(Color.black); 
       } 
                  
      /*-------------------------------------------------[ KeyListener ]---------------------------------------------------*/ 
   
      protected boolean shiftDown = false; 
      protected boolean controlDown = false; 
   
      public void keyPressed(KeyEvent ke){ 
         // shiftDown = ke.isShiftDown(); 
          controlDown = ke.isControlDown(); 
   
       // System.out.println("pressed "+ke.getKeyCode());
   
            //   ignoreCase=true;
               
               comp = (JComponent)component; 
          
          if(popup.isVisible())
          {
               searchField.removeActionListener(this); 
               //searchField.removeKeyListener(this); 
               searchField.getDocument().removeDocumentListener(this); 
               //initSearch(); 
               searchField.addActionListener(this); 
               //searchField.addKeyListener(this); 
               searchField.getDocument().addDocumentListener(this); 
               
               // so up or down keys not make error
               Rectangle rect = comp.getVisibleRect(); 
               popup.show(comp, rect.x, rect.y - popup.getPreferredSize().height - 5); 
               searchField.requestFocus();           
          
           
              switch(ke.getKeyCode())
              { 
              case KeyEvent.VK_UP: 
                  changed(Position.Bias.Backward); 
                  break; 
              case KeyEvent.VK_DOWN: 
                  changed(Position.Bias.Forward); 
                  break; 
              }     
                              
          }
          else
          {
               searchField.removeActionListener(this); 
               //searchField.removeKeyListener(this); 
               searchField.getDocument().removeDocumentListener(this); 
               initSearch(); 
               searchField.addActionListener(this); 
               //searchField.addKeyListener(this); 
               searchField.getDocument().addDocumentListener(this);           	
          }

               

          
          
      } 
   
      public void keyTyped(KeyEvent e){} 
      public void keyReleased(KeyEvent e){} 
   
      /*-------------------------------------------------[ Installation ]---------------------------------------------------*/ 
   
      public void install(JComponent comp)
      { 
          comp.registerKeyboardAction(this, KeyStroke.getKeyStroke( KeyEvent.VK_CONTROL,KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED); 
         // comp.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL,KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED); 
          
         // comp.registerKeyboardAction(this, KeyStroke.getKeyStroke( KeyEvent.VK_SHIFT), JComponent.WHEN_FOCUSED); 
         // comp.registerKeyboardAction(this, KeyStroke.getKeyStroke( KeyEvent.CTRL_MASK|KeyEvent.SHIFT_MASK), JComponent.WHEN_FOCUSED); 
          
          // line 54 this file, when ctrl is pressed case incensitive
       /* Action actionCloseSelTab = new ActionCloseSelTab();
        tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4 ,InputEvent.CTRL_DOWN_MASK ), "closeSelTab"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        tabbedPane.getActionMap().put("closeSelTab", actionCloseSelTab);*/
          
          
          component = comp;
          comp.addKeyListener(this);

      } 
   /* private void closeSearch()
    {
    	 popup.setVisible(false); 
    	
    }  

    class  ActionCloseSearch extends AbstractAction                 
    {       
        public ActionCloseSearch()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{           closeSearch();        }    	
    } */       
  }