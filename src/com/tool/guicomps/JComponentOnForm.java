
// created 02-01-2010
//  http://zetcode.com/tutorials/javaswingtutorial/resizablecomponent/

package com.tool.guicomps;


import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

// Resizable.java 

public class JComponentOnForm extends JComponent{

  public final int  BORDERDISTANCE =9;
  public final int NOBORDERDISTANCE = 0;
  Component comp;
  //int borderDistance ; 
  
  public JComponentOnForm(Component compIn) {
    this(compIn, new BorderResizable(9)); //// BORDERDISTANCE 
  }

  public JComponentOnForm(Component compIn, BorderResizable border)
  {
  	comp=compIn;
    setLayout(new BorderLayout());
    add(comp);
    addMouseListener(resizeListener);
    addMouseMotionListener(resizeListener);
    setBorder(border);
  }
  
  public void setBorderDistance (int borderDistanceIn)
  {
  	BorderResizable border = (BorderResizable)getBorder();
  	 border.setDistance(borderDistanceIn);
  }
  
  
  private void resize() {
      if (getParent() != null) {
        ((JComponent)getParent()).revalidate();
      }
  }
  
  public Component getComponent()
  {
  	return comp;
  }
  
  public void repaint()
  {
  	super.repaint();
  	comp.repaint();

      if (getParent() != null) {
        ((JComponent)getParent()).revalidate();
      }  	
  	
  }
  
  MouseInputListener resizeListener = new MouseInputAdapter()
  {
    public void mouseMoved(MouseEvent me) {
      if (hasFocus()) {
          BorderResizable border = (BorderResizable)getBorder();
          setCursor(Cursor.getPredefinedCursor(border.getCursor(me)));
      }
    }
     
      // mine
    public void mouseEntered(MouseEvent mouseEvent) {
    	      BorderResizable border = (BorderResizable)getBorder();
      cursor = border.getCursor(mouseEvent);
      startPos = mouseEvent.getPoint();
      
       border.setDistance(BORDERDISTANCE);
       setBorder(border);
       requestFocus();
       repaint();
    }

    public void mouseExited(MouseEvent mouseEvent) {
    	      BorderResizable border = (BorderResizable)getBorder();
      cursor = border.getCursor(mouseEvent);
      startPos = mouseEvent.getPoint();
      
       setBorder(border);
       repaint();
    }

    private int cursor;
    private Point startPos = null;

    public void mousePressed(MouseEvent me) {
      BorderResizable border = (BorderResizable)getBorder();
      cursor = border.getCursor(me);
      startPos = me.getPoint();
      border.setDistance(BORDERDISTANCE);
      requestFocus();
      repaint();
    }

    public void mouseDragged(MouseEvent me) {

      if (startPos != null) {

        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();

        int dx = me.getX() - startPos.x;
        int dy = me.getY() - startPos.y;
 
        switch (cursor) {
          case Cursor.N_RESIZE_CURSOR:
            if (!(h - dy < 15)) {
              setBounds(x, y + dy, w, h - dy);
              resize();
            }
            break;

          case Cursor.S_RESIZE_CURSOR:
            if (!(h + dy < 15)) {
              setBounds(x, y, w, h + dy);
              startPos = me.getPoint();
              resize();
            }
            break;

          case Cursor.W_RESIZE_CURSOR:
            if (!(w - dx < 15)) {
              setBounds(x + dx, y, w - dx, h);
              resize();
            }
            break;

          case Cursor.E_RESIZE_CURSOR:
            if (!(w + dx < 50)) {
              setBounds(x, y, w + dx, h);
              startPos = me.getPoint();
              resize();
            }
            break;

          case Cursor.NW_RESIZE_CURSOR:
            if (!(w - dx < 15) && !(h - dy < 15)) {
              setBounds(x + dx, y + dy, w - dx, h - dy);
              resize();
            }
            break;

          case Cursor.NE_RESIZE_CURSOR:
            if (!(w + dx < 15) && !(h - dy < 15)) {
              setBounds(x, y + dy, w + dx, h - dy);
              startPos = new Point(me.getX(), startPos.y);
              resize();
            }
            break;

          case Cursor.SW_RESIZE_CURSOR:
            if (!(w - dx < 15) && !(h + dy < 15)) {
              setBounds(x + dx, y, w - dx, h + dy);
              startPos = new Point(startPos.x, me.getY());
              resize();
            }
            break;

          case Cursor.SE_RESIZE_CURSOR:
            if (!(w + dx < 15) && !(h + dy < 15)) {
              setBounds(x, y, w + dx, h + dy);
              startPos = me.getPoint();
              resize();
            }
          break;

          case Cursor.MOVE_CURSOR:
            Rectangle bounds = getBounds();
            bounds.translate(dx, dy);
            setBounds(bounds);
            resize();
          }


          setCursor(Cursor.getPredefinedCursor(cursor));
        }
     }

   public void mouseReleased(MouseEvent mouseEvent) {
     startPos = null;
    }
  };
}
