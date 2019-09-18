// created 17-9-2006

package com.tool.guicomps;


import java.awt.BorderLayout;
import java.awt.Container;
 //import java.awt.event.ActionListener;
//import java.awt.Color;
import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.GradientPaint;
 import java.awt.Dimension;

import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFrame;
//import javax.swing.JButton;
import javax.swing.JWindow;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.lang.Runnable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// called by PanelReportSettingsPreview
public class WindowWait extends JWindow implements Constants, Runnable, ActionListener
{
    private JLabel lblMain;
    private JLabel lblComment;
    private ImageIcon[] icons;
    private boolean animate = false;
   private ImageIcon icon;
   private ImageIcon iconb;
    
    public WindowWait(String s, int location, ImageIcon iconIn, ImageIcon iconbIn)
    {
    	super();//super(true);
    	
    	JPanelCurvedGradient panelMain = new JPanelCurvedGradient();
    	
    	icon=iconIn;
    	iconb=iconbIn;
    	
    	icons = new ImageIcon[2];
    	    icons[0] = icon;
	icons[1] = iconb;
    	
    	lblComment = new JLabel("-");
        lblComment.setHorizontalAlignment(JLabel.CENTER);
        //lblMain = new JLabel(s,JLabel.CENTER);
       lblMain = new JLabel();//(lblBack.getBackground().darker(),lblBack.getBackground().brighter(),60);     
       //System.out.println("WindowWait "+this.getName()+this.getHeight());
       lblMain.setText(s);
       if(icon!=null) 
       {    lblMain.setIcon(icon);  }
       lblMain.setVerticalTextPosition(JLabel.BOTTOM);
       //lblMain.setHorizontalTextPosition(JLabel.CENTER);
      lblMain.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));//top,left,bottom,right
      lblMain.setFont(lblMain.getFont().deriveFont(Font.BOLD));
      //lblMain.setOpaque(true);
      
      //lblMain.setBackground(java.awt.SystemColor.activeCaption);//Color.WHITE);
      //lblMain.setForeground(java.awt.SystemColor.activeCaptionText);
      panelMain.setLayout(new BorderLayout());
      panelMain.add(lblMain, BorderLayout.PAGE_START);
       panelMain.add(lblComment, BorderLayout.PAGE_END);  
        getContentPane().add(panelMain, "Center");

        //getContentPane().setBackground(new Color(223,223,223)); //128  192  223  255black
        
        
        //AnimatedCursor animatedCursor = new AnimatedCursor(this);
        //AnimatedCursor animatedCursor = new AnimatedCursor(SwingUtilities.getAncestorOfClass(JFrame.class, this));
        //animatedCursor.animate();
        
        this.setAlwaysOnTop(true);
        pack();
        setLocationRelativeTo(null);
        setWindowLocation(location);
        
    }

   public void animate()
   {
		animate = true;
		new Thread(this).start();
   }

		public void run()
		{   
   		int count = 0;
		  	//System.out.println("AnimatedCursor:" +frame+" <===> "+window); 

		  while(animate)
		  {

			try
			{
				Thread.currentThread().sleep(200);
			} catch (Exception ex) { }
            
           /* if (frame==null&& window!=null)
            {*/
            	   if(icon!=null && iconb!=null) 
                   {    lblMain.setIcon(icons[count % icons.length]);  } 
            	//window.setCursor(cursors[count % cursors.length]);
          /*  }
            else if (frame!=null && window==null)
            {
            	   if(icon!=null && iconb!=null) 
                   {    lblMain.setIcon(icons[count % icons.length]);  }             	
            	//frame.setCursor(cursors[count % cursors.length]);
            }
            else
            {    System.out.println("AnimatedCursor:if statement 1 out of target"+frame+window);            }*/
			
			count++;
		  }
           /* if (frame==null && window!=null)
            {
                   if(icon!=null) 
                   {    lblMain.setIcon(icon);  } 
                 

            }
            else if (frame!=null && window==null)
            {*/
	                   if(icon!=null) 
                       {    lblMain.setIcon(icon);  } 
	             //frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
           /* }
            else
            {    System.out.println("AnimatedCursor:if statement 2 out of target"+frame+window);          }*/
		
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
    
    
    public void showWindow()
    {
        setVisible(true);
        
    }
    
    // doesn't work
    public void setComment(String message)
    {
       lblComment.setText(message);
    }
    
    public void close()
    {
    	dispose();
    }
    
    public void actionPerformed(ActionEvent evt)
	{
		JButton button = (JButton)evt.getSource();
		if(animate)
		{
			button.setText("Start Animation");
			animate = false;
		}
		else
		{
			animate = true;
			button.setText("Stop Animation");
			new Thread(this).start();
		}
		
	}
    
    //dispose
}