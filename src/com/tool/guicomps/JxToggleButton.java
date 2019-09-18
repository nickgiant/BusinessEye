/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.guicomps;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JToggleButton;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author sun
 */
public class JxToggleButton extends JToggleButton implements Constants
{
    
   //private CurvedGradientPanel panelAllOnIt ;
	public JxToggleButton()
	{	
          setContentAreaFilled( false );  // removes button border when big size

		 //setMargin(new Insets(2, 2, 2, 2));
		 //setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
        setPreferredSize(new Dimension(22,20));// Dimension(int width, int height) // not change in box layout
        //setSize(80, 80);
        //this.setMargin(new Insets(1, 1, 1, 1)); 
        setOpaque(true);
        setBackground(CLR_PANEL_END);//  CLR_PANEL_END   CLR_PANEL_START
        setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));
/*        this.addMouseListener(new MouseAdapter()
                {       	      
                 public void mouseEntered(MouseEvent e)
                 {   	
                 	setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                 	//setOpaque(true);
                 	setBackground(CLR_LBL_ROLL);
                 	setCursor(new Cursor(Cursor.HAND_CURSOR));
                 }
                 public void mouseExited(MouseEvent e)
                 {
                    setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));                 
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    setBackground(CLR_PANEL_END);//  CLR_PANEL_END
                    //setOpaque(false);
                 }
                 public void mousePressed(MouseEvent e)
                 {
                 	setBorder(BorderFactory.createLineBorder(CLR_LBL_CLICKED_BORDER)); 
                 	setBackground(CLR_LBL_CLICKED);
                 	
                 	//setOpaque(false);                

                 }
                public void mouseReleased(MouseEvent e)
                 {
                 	setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                 	//setOpaque(true);
                 	setBackground(CLR_LBL_ROLL);
                 	setCursor(new Cursor(Cursor.HAND_CURSOR));              

                 }  
                 public void mouseClicked(MouseEvent e)
                 {
                     
                     
                        setBorder(BorderFactory.createLineBorder(CLR_LBL_CLICKED_BORDER)); 
                 	setBackground(CLR_LBL_CLICKED);
                     //displayDialogSystemInfo();
                     //System.out.println("DialogMain.initialize clicked ");
                     //clickedOnRow(Integer.parseInt(txtFieldFinal.getToolTipText()));
                 }                 
                });   	*/	
	
        
      ItemListener itemlistener = new ItemListener()
      {
      public void itemStateChanged(ItemEvent e)
        {
          AbstractButton btn = (AbstractButton) (e.getSource());
          
          if(btn.isSelected())
          {
                    //System.out.println("JxToggleButton: " + btn.getText()+"  "+btn.isSelected());
                        setBorder(BorderFactory.createLineBorder(CLR_LBL_CLICKED_BORDER)); 
                 	setBackground(CLR_LBL_CLICKED);
          }
          else
          {
                      setBackground(CLR_PANEL_END);//  CLR_PANEL_END   CLR_PANEL_START
                      setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));
          }
        }
      };
    this.addItemListener(itemlistener);
        
		
		/*setContentAreaFilled(false);
		setBorderPainted(false);
		//setFont(new Font("Thoma", Font.BOLD, 12));
		setForeground(colorcaption);
		setFocusable(false);*/



		 
		 /*Color blue= new Color(129, 169, 226);
         Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
        panelAllOnIt = new CurvedGradientPanel(blue,lightBlue,0,0);
        this.add(panelAllOnIt);*/
	}
	
	public JxToggleButton(String text) {
		
		 // setBorderPainted(false);
		
		  
		 
		 super.setText(text);

	}	
	
        public void setText(String text)
        {
		
		 // setBorderPainted(false);
		
		  
		 
		 super.setText(text);

	}    
    
    
    
    
}
