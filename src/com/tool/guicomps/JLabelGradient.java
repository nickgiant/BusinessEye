// created 02-01-2007

package com.tool.guicomps;

import java.awt.GradientPaint;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;



import javax.swing.JLabel;

import javax.swing.ImageIcon;


public class JLabelGradient extends JLabel implements Constants
{
   private Color color1;
   private Color color2;
   private int gradientHeight;
   //private JLabel lblTitle = new JLabel();
   //private static Color TRANSPARENT = new Color(0, 0, 0, 0);

   public JLabelGradient(Color color1In,Color color2In, int gradientHeightIn)//23
   {
       //setLayout(new BorderLayout());
      
      color1 = color1In;
      color2=color2In;
      gradientHeight=gradientHeightIn;
      
      this.setOpaque(false);
      //this.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));//top,left,bottom,right
      
   }
   
      public JLabelGradient()//23
   {
       //setLayout(new BorderLayout());
      
      color1 = CLR_PANEL_END;
      color2=CLR_PANEL_START;
      gradientHeight=23;
      
      this.setOpaque(false);
      //this.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));//top,left,bottom,right
      
   }
   

   protected void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;   //                     15
      GradientPaint paint = new GradientPaint(0, 0, color1, 0, gradientHeight, color2,true);
      g2.setPaint(paint);
      g2.fill(getBounds());
      super.paintComponent(g);
   }

   /*public static void main(String[] args)
   {
      JFrame frm = new JFrame();
      frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frm.getContentPane().add(new PanelGradient(new Color(0, 0, 0, 0),Color.black,16));
      frm.setBounds(100,100,100,100);
      frm.setVisible(true);

   }*/
   
}