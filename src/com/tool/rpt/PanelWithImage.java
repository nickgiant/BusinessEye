// created 01-01-2010
//    http://www.java2s.com/Code/Java/Swing-JFC/Panelwithbackgroundimage.htm

package com.tool.rpt;

import com.tool.guicomps.JxPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*public class ImageTest {

  public static void main(String[] args) {
    ImagePanel panel = new ImagePanel(new ImageIcon("images/background.png").getImage());

    JFrame frame = new JFrame();
    frame.getContentPane().add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}*/

class PanelWithImage extends JxPanel
{

  private Image img;

  public PanelWithImage()
  {
    
  }

  public PanelWithImage(String strImg)
  {
    this(new ImageIcon(strImg).getImage());
  }

  public PanelWithImage(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }
  
  public void setBackgroundImage(String strImg)
  {
    img = new ImageIcon(strImg).getImage();
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);  
  		
  }

  public void setBackgroundImage(Image img)
  {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);  
  		
  }
  
  public void clearBackgroundImage()
  {
  	 this.img = null; 
    Dimension size = new Dimension(0,0);
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);    	 	
  }
  
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

}