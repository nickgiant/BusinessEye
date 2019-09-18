// created 02-01-2010
//  http://zetcode.com/tutorials/javaswingtutorial/resizablecomponent/

package com.tool.guicomps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

// ResizableBorder.java 

public class BorderResizable implements Border {
  private int dist = 8;

  int locations[] = 
  {
    SwingConstants.NORTH, SwingConstants.SOUTH, SwingConstants.WEST,
    SwingConstants.EAST, SwingConstants.NORTH_WEST,
    SwingConstants.NORTH_EAST, SwingConstants.SOUTH_WEST,
    SwingConstants.SOUTH_EAST
  };

  int cursors[] =
  { 
    Cursor.N_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR, Cursor.W_RESIZE_CURSOR,
    Cursor.E_RESIZE_CURSOR, Cursor.NW_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR,
    Cursor.SW_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR
  };

  public BorderResizable(int dist) {
    this.dist = dist;
  }

  public Insets getBorderInsets(Component component) {
      return new Insets(dist, dist, dist, dist);
  }

  public boolean isBorderOpaque() {
      return false;
  }
 
  public void setDistance(int dist)
  {
     this.dist = dist;  
     //this.revalidate();	
  }
 
  public void paintBorder(Component component, Graphics g, int x, int y,
                          int w, int h) {

      g.setColor(Color.black);  // black
      
      if(dist==0)
      {
      	  //g.drawRect(0, 0, 0, 0); 
      }
      else
      {
      	 g.drawRect(x + dist / 2, y + dist / 2, w - dist, h - dist); 
      }
 
      if (component.hasFocus())
      {
        for (int i = 0; i < locations.length; i++)
        {
        	Rectangle rect = null;
        	/*if(dist==0)
        	{*/
        		 rect = getRectangle(x, y, w, h, locations[i],dist);
        	/*}
        	else
        	{
        		 rect = getRectangle(x, y, w, h, locations[i],dist+1);
        	}*/
          
          g.setColor(Color.WHITE);
          g.fillRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
          g.setColor(Color.BLACK);
          g.drawRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
        }
      }
      else
      {
            /*g.setColor(Color.black);  // black
            g.drawRect(0, 0, 0, 0);	*/

      }
  }

  private Rectangle getRectangle(int x, int y, int w, int h, int location, int distance) {
      switch (location) {
      case SwingConstants.NORTH:
          return new Rectangle(x + w / 2 - distance / 2, y, distance, distance);
      case SwingConstants.SOUTH:
          return new Rectangle(x + w / 2 - distance / 2, y + h - distance, distance, distance);
      case SwingConstants.WEST:
          return new Rectangle(x, y + h / 2 - distance / 2, distance, distance);
      case SwingConstants.EAST:
          return new Rectangle(x + w - distance, y + h / 2 - distance / 2, distance, distance);
      case SwingConstants.NORTH_WEST:
          return new Rectangle(x, y, distance, distance);
      case SwingConstants.NORTH_EAST:
          return new Rectangle(x + w - distance, y, distance, distance);
      case SwingConstants.SOUTH_WEST:
          return new Rectangle(x, y + h - distance, distance, distance);
      case SwingConstants.SOUTH_EAST:
          return new Rectangle(x + w - distance, y + h - distance, distance, distance);
      }
      return null;
  }

  public int getCursor(MouseEvent me) {
      Component c = me.getComponent();
      int w = c.getWidth();
      int h = c.getHeight();

      for (int i = 0; i < locations.length; i++) {
          Rectangle rect = getRectangle(0, 0, w, h, locations[i],dist);
          if (rect.contains(me.getPoint()))
              return cursors[i];
      }

      return Cursor.MOVE_CURSOR;
  }
}
