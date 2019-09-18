// created 09-11-2009
package com.tool.guicomps;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.event .*;

public class JButtonForPanelDecorated extends  JButton implements Constants//ButtonGradientOval   /// ButtonCentralGradient  , ButtonGradientOval, ButtonGradient
{
   private JPanelCurvedGradient panelAllOnIt ;
	public JButtonForPanelDecorated()
	{
		super();
         //setBorderPainted(true);
/*          setContentAreaFilled( false );  // removes button border when big size


		 //setMargin(new Insets(2, 2, 2, 2));
		 //setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
        //setSize(80, 80);
        //this.setMargin(new Insets(1, 1, 1, 1)); 
        //setPreferredSize(new Dimension((super.getPreferredSize().width+7),23));// Dimension(int width, int height) // not change in box layout
        setOpaque(true);
        setBackground(CLR_PANEL_END);//  CLR_PANEL_END   CLR_PANEL_START
        setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));//,5,false));
        this.addMouseListener(new MouseAdapter()
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
                     //displayDialogSystemInfo();
                     //System.out.println("DialogMain.initialize clicked ");
                     //clickedOnRow(Integer.parseInt(txtFieldFinal.getToolTipText()));
                 }                 
                });   


		
		 /*Color blue= new Color(129, 169, 226);
         Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
        panelAllOnIt = new JPanelCurvedGradient(blue,lightBlue,0,0);
        this.add(panelAllOnIt);*/
	}

	public JButtonForPanelDecorated(String txt)
	{
		//JButtonForPanelDecorated btn = new JButtonForPanelDecorated();
		//btn.setText(txt);
		super(txt);
		
		 /*Color blue= new Color(129, 169, 226);
         Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
        panelAllOnIt = new JPanelCurvedGradient(blue,lightBlue,0,0);
        this.add(panelAllOnIt);*/
	}
	
}
		
		
	/*	ButtonGradientOval standardButton = new ButtonGradientOval("Standard Button");
		standardButton.setDirection(ButtonGradientOval.VERTICAL);
		standardButton.setGradientType(ButtonGradientOval.NORMAL);
		standardButton.setPreferredSize(new Dimension(130, 28));*/