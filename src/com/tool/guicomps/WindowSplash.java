// from druid dlib library
/*
use
    SplashScreen splashScr = new SplashScreen(Config.dir.images + "/logo.png");
.....
      splashScr.dispose();
*/
package com.tool.guicomps;


import com.tool.utils.*;

import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.Color;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.BorderFactory;

public class WindowSplash extends JWindow// implements Constants
{
    //LabelGradient 
    JLabel lblMain;
    JLabel lblBack;

    public WindowSplash(String s, ImageIcon i)
    {
    	
    	              Color blue= new Color(129, 169, 226);
              //Color blue= new Color(63, 183, 255);
              Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
            //lightblue =  lightblue.brighter().brighter();
        JPanel panel = new JPanelCurvedGradient(blue,lightBlue,0,0);
    	
        //lblMain = new JLabel(s,i,JLabel.CENTER);
    	lblBack = new JLabel();
        lblMain = new JLabel();//LabelGradient(lblBack.getBackground().darker(),lblBack.getBackground().brighter(),60);     
        lblMain.setText("<html><table>"
                //+ "<tr><td></td></tr>"
                + "<tr><td>"+s+"</td><td>"+VariablesGlobal.appUseName+"</td></tr>"
                //+ "<tr><td>"+VariablesGlobal.appUseName+"</td></tr>"
                + "</table></html>");
        lblMain.setIcon(i);
        lblMain.setVerticalTextPosition(JLabel.BOTTOM);
        lblMain.setHorizontalTextPosition(JLabel.CENTER);
        lblMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
    

    
}