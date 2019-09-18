// created 09-04-2007  from Swing Hacks  Hack 36
package com.tool.guicomps;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;


  public class PanelSeparator extends JPanel
  { 
                 private Color leftColor; 
                 private Color rightColor;

                 public PanelSeparator(Color left, Color right) {
                     this.leftColor = left;
                     this.rightColor = right;
                     setOpaque(false);
                 }

                 protected void paintComponent(Graphics g) {
                     g.setColor(leftColor);
                     g.drawLine(0,0, 0,getHeight( ));
                     g.setColor(rightColor);
                     g.drawLine(1,0, 1,getHeight( ));

                 }
  }