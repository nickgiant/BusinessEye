/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.guicomps;

import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.BorderFactory;

public class WindowMenu extends JWindow  implements Constants//, ActionListener
{
    //LabelGradient 
    private JLabel lblMain;
    private JLabel lblBack;
    private ArrayList listModules;
    
    public WindowMenu(ArrayList listModulesIn)
    {
    	
    	              Color blue= new Color(129, 169, 226);
              //Color blue= new Color(63, 183, 255);
              Color lightBlue= new Color(220,235,253);//148, 215, 254);    //  196,218,246);//
            //lightblue =  lightblue.brighter().brighter();
        JPanel panel = new JPanelCurvedGradient(blue,lightBlue,0,0);
    	
        //lblMain = new JLabel(s,i,JLabel.CENTER);
    	lblBack = new JLabel();
        lblMain = new JLabel();//LabelGradient(lblBack.getBackground().darker(),lblBack.getBackground().brighter(),60);     
        lblMain.setText("<html><table>"
                + "<tr><td  text-align: center><img src='"+ICO_MENUESODAEXODA+"'></td><td></td><td  text-align: center><img src='"+ICO_MENUSERVICESALES+"'></td><td></td><td  text-align: center><img src='"+ICO_MENUSYSTEM+"'></td></tr>"
                + "<tr><td text-align: center>έσοδα έξοδα</td><td></td><td text-align: center>παροχή υπηρεσίας</td><td></td><td text-align: center>σύστημα</td></tr>"
                + "<tr><td>'btn1'</td><td></td><td>'btn1'</td><td></td><td>'btn1'</td></tr>"
                + "<tr><td>'btn2'</td><td></td><td>'btn2'</td><td></td><td>'btn2'</td></tr>"
                + "</table></html>");
        //lblMain.setIcon(i);
        lblMain.setVerticalTextPosition(JLabel.BOTTOM);
        lblMain.setHorizontalTextPosition(JLabel.CENTER);
        lblMain.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));//top,left,bottom,right
       // lblMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblMain.setFont(lblMain.getFont().deriveFont(Font.BOLD));
        lblMain.setOpaque(false);
    //  lblMain.setBackground(java.awt.SystemColor.activeCaption);//Color.WHITE);
    //  lblMain.setForeground(java.awt.SystemColor.activeCaptionText);
        
        panel.add(lblMain, "Center");
        
        getContentPane().add(panel, "Center");

        //AnimatedCursor animatedCursor = new AnimatedCursor(this);
        //animatedCursor.animate();


        //getContentPane().setBackground(new Color(223,223,223)); //128  192  223  255black
        
        pack();
        setLocationRelativeTo(null);
        
    }    
  
    
    public void setWindowLocation(JComponent component)
    {
    
        		
          Dimension paneSize   = this.getSize();
    	  Dimension screenSize = this.getToolkit().getScreenSize();	
        
       
        if(component.isShowing())	
        {

         Point point = component.getLocationOnScreen(); 
        
         //this.setLocation(new Point(point.getX(),point.getY()+comp.getHeight()));
         if((this.getWidth()+(int)(point.getX())>screenSize.width) && (this.getHeight()+(int)(point.getY())>screenSize.height))
         {
         	this.setLocation(screenSize.width-this.getWidth() , (int)point.getY()-this.getHeight());
         }         
         else if(this.getWidth()+(int)(point.getX())>screenSize.width)
         {
            this.setLocation(screenSize.width-this.getWidth() , (int)point.getY()+component.getHeight());
         }
         else if(this.getHeight()+(int)(point.getY())>screenSize.height)
         {
         	this.setLocation((int)(point.getX()) , (int)point.getY()-this.getHeight());
         }
         else
         {
         	this.setLocation((int)(point.getX()) , (int)point.getY()+component.getHeight());
         }	
         	 
        }
        else
        {   
        	  
        	//this.setVisible(false);
        	//System.out.println("error WindowLookUp.setWindowLocation not visible because txt not visible.");
        }        
        
        
        
        
        
        
    }    
    
    
    
    public void setVisibleNot()
    {
        setVisible(false);
    }    
    
    public void setVisible()
    {
        setVisible(true);
    }
    
    
    
    public void close()
    {
    	dispose();
    }    
    
    
}