// created 14-10-2006

package com.tool.guicomps;

import java.awt.GradientPaint;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

public class PanelTitle extends JPanel
{
   private Color color1;
   private Color color2;
   private JLabel lblTitle = new JLabel();
   //private static Color TRANSPARENT = new Color(0, 0, 0, 0);

   public PanelTitle(Color color1In,Color color2In, Color colForeground, String text)
   {
       setLayout(new BorderLayout());
      
      color1 = color1In;
      color2=color2In;
      
      this.setOpaque(false);
      setText(text);
      lblTitle.setOpaque(false);
      lblTitle.setForeground(colForeground);
      lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));
      lblTitle.setHorizontalTextPosition(JLabel.LEFT);
      lblTitle.setBorder(BorderFactory.createEmptyBorder(2, 5, 3, 5)); //top,left,bottom,right)
      lblTitle.setHorizontalTextPosition(JLabel.RIGHT);
      add(lblTitle, BorderLayout.CENTER);  
   }

   protected void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      GradientPaint paint = new GradientPaint(0, 0, color1, 0, 15, color2,true);
      // GradientPaint paint = new GradientPaint(0, 0, color1, getWidth(), 0, color2,false);
      g2.setPaint(paint);
      g2.fill(getBounds());
      super.paintComponent(g);
   }

   public void setText(String text)
   { 
      lblTitle.setText(text);
   }
   
   public void setIcon(ImageIcon icon)
   { 
      lblTitle.setIcon(icon);
   } 
   
   public static void main(String[] args)
   {
      JFrame frm = new JFrame();
      frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frm.getContentPane().add(new PanelTitle(new Color(0, 0, 0, 0),Color.black,Color.white, "title"));
      frm.setBounds(100,100,100,100);
      frm.setVisible(true);

   }
   
}