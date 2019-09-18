// created 4-7-09
// from http://forums.sun.com/thread.jspa?threadID=337070

package com.tool.guicomps;


import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

import javax.swing.plaf.basic.BasicButtonUI;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
 
import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
 
import javax.swing.event.EventListenerList;
 
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
 

import java.util.EventListener;
 
 
 
/**
 * A JTabbedPane which has a close ('X') icon on each tab.
 *
 * To add a tab, use the method addTab(String, Component)
 *
 * To have an extra icon on each tab (e.g. like in JBuilder, showing the file
 * type) use the method addTab(String, Component, Icon). Only clicking the 'X'
 * closes the tab.
 */
public class JTabbedPaneWithCloseBtn extends JTabbedPane implements MouseListener, MouseMotionListener
{
  private int intTab =0;
  private TabButton closeBtn;
  private int intTabPrepareToClose = -1;
  /**
   * The <code>EventListenerList</code>.
   */
  private EventListenerList listenerList = null;
 
  /**
   * The viewport of the scrolled tabs.
   */
  private JViewport headerViewport = null;
 
  /**
   * The normal closeicon.
   */
  private Icon normalCloseIcon = null;
 
  /**
   * The closeicon when the mouse is over.
   */
  private Icon hooverCloseIcon = null;
 
  /**
   * The closeicon when the mouse is pressed.
   */
  private Icon pressedCloseIcon = null;
 
  /**
   * Creates a new instance of <code>CloseableTabbedPane</code>
   */
  public JTabbedPaneWithCloseBtn() {
    super();
    init(SwingUtilities.LEFT);
  }
 
  /**
   * Creates a new instance of <code>CloseableTabbedPane</code>
   * @param horizontalTextPosition the horizontal position of the text (e.g.
   * SwingUtilities.TRAILING or SwingUtilities.LEFT)
   */
  public JTabbedPaneWithCloseBtn(int horizontalTextPosition) {
    super();
    init(horizontalTextPosition);
  }
 
  /**
   * Initializes the <code>CloseableTabbedPane</code>
   * @param horizontalTextPosition the horizontal position of the text (e.g.
   * SwingUtilities.TRAILING or SwingUtilities.LEFT)
   */
  private void init(int horizontalTextPosition)
  {
    listenerList = new EventListenerList();
    addMouseListener(this);
    addMouseMotionListener(this);
     
    setOpaque(true);
    this.setFocusable(false);
    /*if (getUI() instanceof MetalTabbedPaneUI)
      setUI(new CloseableMetalTabbedPaneUI(horizontalTextPosition));
    else
      setUI(new CloseableTabbedPaneUI(horizontalTextPosition));*/
  }
 
  /**
   * Allows setting own closeicons.
   * @param normal the normal closeicon
   * @param hoover the closeicon when the mouse is over
   * @param pressed the closeicon when the mouse is pressed
   */
/*  public void setCloseIcons(Icon normal, Icon hoover, Icon pressed) {
    normalCloseIcon = normal;
    hooverCloseIcon = hoover;
    pressedCloseIcon = pressed;
  }*/
 
  /**
   * Adds a <code>Component</code> represented by a title and no icon.
   * @param title the title to be displayed in this tab
   * @param component the component to be displayed when this tab is clicked
   */
  public void addTab(String title, Component component) {
    addTab(title, null, component);
  }
 
  /**
   * Adds a <code>Component</code> represented by a title and an icon.
   * @param title the title to be displayed in this tab
   * @param component the component to be displayed when this tab is clicked
   * @param extraIcon the icon to be displayed in this tab
   */
  public void addTab(String title, Icon icon , Component component)
  {
   /* boolean doPaintCloseIcon = true;
    try {
      Object prop = null;
      if ((prop = ((JComponent) component).
                    getClientProperty("isClosable")) != null) {
        doPaintCloseIcon = (Boolean) prop;
      }
    } catch (Exception ignored) {/*Could probably be a ClassCastException*///}*/
 
        super.addTab(title,icon, component);
        //super.addTab(title, doPaintCloseIcon ? new CloseTabIcon() : null, component);
  
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel label = new JLabel(title);
        //label.setOpaque(true);
        label.setIcon(icon);
        //label.setBackground(Color.black);
        panel.add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 3));
        //tab button
        
        //JButton button = new JButton("x");//TabButton();
        closeBtn = new TabButton();
         closeBtn.setEntity(panel);
        //closeBtn  

        panel.add(closeBtn);
        //add more space to the top of the component
        //pnl.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        setTabComponentAt(intTab,panel);
       //System.out.println("TabbedPaneWithClose.addTab tab "+intTab);
        intTab =intTab+1;
    
        panel.setFocusable(false);
         label.setFocusable(false);
 
    if (headerViewport == null) {
      for (Component c : getComponents()) {
        if ("TabbedPane.scrollableViewport".equals(c.getName()))
          headerViewport = (JViewport) c;
      }
    }
  }
  
  
   // this allows outside classes to add actionlisteners
    public void addActionListener(ActionListener al)
    {
      closeBtn.addActionListener(al);
    }
  
  private void setTabPrepareToClose(int index)
  {
  	intTabPrepareToClose=index;
  	//System.out.println("TabbedPaneWithClose.setTabPrepareToClose "+intTabPrepareToClose);
  }
  
  public int getTabPrepareToClose()
  {
  	return intTabPrepareToClose;
  }    
  public void removeTabAt(int index)
  {
  	super.removeTabAt(index);
  	intTab=intTab-1;
  }

  public void removeAll()
  {
  	super.removeAll();
  	intTab = 0;
  }
  
  public int indexOfTabComponent(Component tabComponent)
  {
  	return super.indexOfTabComponent(tabComponent);
  }
  
  
  /**
   * Invoked when the mouse button has been clicked (pressed and released) on
   * a component.
   * @param e the <code>MouseEvent</code>
   */
  public void mouseClicked(MouseEvent e) {
//    processMouseEvents(e);
  }
 
  /**
   * Invoked when the mouse enters a component.
   * @param e the <code>MouseEvent</code>
   */
  public void mouseEntered(MouseEvent e) { }
 
  /**
   * Invoked when the mouse exits a component.
   * @param e the <code>MouseEvent</code>
   */
  public void mouseExited(MouseEvent e)
  {
/*    for (int i=0; i<getTabCount(); i++) {
      CloseTabIcon icon = (CloseTabIcon) getIconAt(i);
      if (icon != null)
        icon.mouseover = false;
    }
    repaint();  */
  }
 
  /**
   * Invoked when a mouse button has been pressed on a component.
   * @param e the <code>MouseEvent</code>
   */
  public void mousePressed(MouseEvent e)
  {
//  	processMouseEvents(e);
  }
 
  /**
   * Invoked when a mouse button has been released on a component.
   * @param e the <code>MouseEvent</code>
   */
  public void mouseReleased(MouseEvent e)
  {
 
  }
 
  /**
   * Invoked when a mouse button is pressed on a component and then dragged.
   * <code>MOUSE_DRAGGED</code> events will continue to be delivered to the
   * component where the drag originated until the mouse button is released
   * (regardless of whether the mouse position is within the bounds of the
   * component).<br/>
   * <br/>
   * Due to platform-dependent Drag&Drop implementations,
   * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
   * Drag&amp;Drop operation.
   * @param e the <code>MouseEvent</code>
   */
  public void mouseDragged(MouseEvent e) {
//    processMouseEvents(e);
  }
 
  /**
   * Invoked when the mouse cursor has been moved onto a component but no
   * buttons have been pushed.
   * @param e the <code>MouseEvent</code>
   */
  public void mouseMoved(MouseEvent e) {
//    processMouseEvents(e);
  }
 
  /**
   * Processes all caught <code>MouseEvent</code>s.
   * @param e the <code>MouseEvent</code>
   */
/*  private void processMouseEvents(MouseEvent e) {
    int tabNumber = getUI().tabForCoordinate(this, e.getX(), e.getY());
    if (tabNumber < 0) return;
    CloseTabIcon icon = (CloseTabIcon) getIconAt(tabNumber);
    if (icon != null) {
      Rectangle rect= icon.getBounds();
      Point pos = headerViewport == null ?
                  new Point() : headerViewport.getViewPosition();
      Rectangle drawRect = new Rectangle(
        rect.x - pos.x, rect.y - pos.y, rect.width, rect.height);
 
      if (e.getID() == e.MOUSE_PRESSED) {
        icon.mousepressed = e.getModifiers() == e.BUTTON1_MASK;
        repaint(drawRect);
      } else if (e.getID() == e.MOUSE_MOVED || e.getID() == e.MOUSE_DRAGGED ||
                 e.getID() == e.MOUSE_CLICKED) {
        pos.x += e.getX();
        pos.y += e.getY();
        if (rect.contains(pos)) {
          if (e.getID() == e.MOUSE_CLICKED) {
            int selIndex = getSelectedIndex();
            if (fireCloseTab(selIndex)) {
              if (selIndex > 0) {
                // to prevent uncatchable null-pointers
                Rectangle rec = getUI().getTabBounds(this, selIndex - 1);
 
                MouseEvent event = new MouseEvent((Component) e.getSource(),
                                                  e.getID() + 1,
                                                  System.currentTimeMillis(),
                                                  e.getModifiers(),
                                                  rec.x,
                                                  rec.y,
                                                  e.getClickCount(),
                                                  e.isPopupTrigger(),
                                                  e.getButton());
                dispatchEvent(event);
              }
              //the tab is being closed
              //removeTabAt(tabNumber);
              remove(selIndex);
            } else {
              icon.mouseover = false;
              icon.mousepressed = false;
              repaint(drawRect);
            }
          } else {
            icon.mouseover = true;
            icon.mousepressed = e.getModifiers() == e.BUTTON1_MASK;
          }
        } else {
          icon.mouseover = false;
        }
        repaint(drawRect);
      }
    }
  }
*/ 
  /**
   * Adds an <code>CloseableTabbedPaneListener</code> to the tabbedpane.
   * @param l the <code>CloseableTabbedPaneListener</code> to be added
   */
//  public void addCloseableTabbedPaneListener(CloseableTabbedPaneListener l) {
//    listenerList.add(CloseableTabbedPaneListener.class, l);
//  }
 
  /**
   * Removes an <code>CloseableTabbedPaneListener</code> from the tabbedpane.
   * @param l the listener to be removed
   */
/*  public void removeCloseableTabbedPaneListener(CloseableTabbedPaneListener l) {
    listenerList.remove(CloseableTabbedPaneListener.class, l);
  }
*/ 
  /**
   * Returns an array of all the <code>SearchListener</code>s added to this
   * <code>SearchPane</code> with addSearchListener().
   * @return all of the <code>SearchListener</code>s added or an empty array if
   * no listeners have been added
   */
/*  public CloseableTabbedPaneListener[] getCloseableTabbedPaneListener() {
    return listenerList.getListeners(CloseableTabbedPaneListener.class);
  }
*/ 
  /**
   * Notifies all listeners that have registered interest for notification on
   * this event type.
   * @param tabIndexToClose the index of the tab which should be closed
   * @return true if the tab can be closed, false otherwise
   */
/*  protected boolean fireCloseTab(int tabIndexToClose) {
    boolean closeit = true;
    // Guaranteed to return a non-null array
    Object[] listeners = listenerList.getListenerList();
    for (Object i : listeners) {
      if (i instanceof CloseableTabbedPaneListener) {
        if (!((CloseableTabbedPaneListener) i).closeTab(tabIndexToClose)) {
          closeit = false;
          break;
        }
      }
    }
    return closeit;
  }
*/ 
  /**
   * The class which generates the 'X' icon for the tabs. The constructor
   * accepts an icon which is extra to the 'X' icon, so you can have tabs
   * like in JBuilder. This value is null if no extra icon is required.
   */
//  class CloseTabIcon implements Icon {
    /**
     * the x position of the icon
     */
//    private int x_pos;
 
    /**
     * the y position of the icon
     */
//    private int y_pos;
 
    /**
     * the width the icon
     */
//    private int width;
 
    /**
     * the height the icon
     */
//    private int height;
 
    /**
     * the additional fileicon
     */
//    private Icon fileIcon;
 
    /**
     * true whether the mouse is over this icon, false otherwise
     */
//    private boolean mouseover = false;
 
    /**
     * true whether the mouse is pressed on this icon, false otherwise
     */
//    private boolean mousepressed = false;

    /**
     * Creates a new instance of <code>CloseTabIcon</code>
     * @param fileIcon the additional fileicon, if there is one set
     */
/*    public CloseTabIcon() {
      //this.fileIcon = fileIcon;
      width  = 16;
      height = 16;
    }
*/
 
    /**
     * Creates a new instance of <code>CloseTabIcon</code>
     * @param fileIcon the additional fileicon, if there is one set
     */
/*    public CloseTabIcon(Icon fileIcon) {
      this.fileIcon = fileIcon;
      width  = 16;
      height = 16;
    }
*/ 
    /**
     * Draw the icon at the specified location. Icon implementations may use the
     * Component argument to get properties useful for painting, e.g. the
     * foreground or background color.
     * @param c the component which the icon belongs to
     * @param g the graphic object to draw on
     * @param x the upper left point of the icon in the x direction
     * @param y the upper left point of the icon in the y direction
     */
/*    public void paintIcon(Component c, Graphics g, int x, int y) {
      boolean doPaintCloseIcon = true;
      try {
        // JComponent.putClientProperty("isClosable", new Boolean(false));
        JTabbedPane tabbedpane = (JTabbedPane) c;
        int tabNumber = tabbedpane.getUI().tabForCoordinate(tabbedpane, x, y);
        JComponent curPanel = (JComponent) tabbedpane.getComponentAt(tabNumber);
        Object prop = null;
        if ((prop = curPanel.getClientProperty("isClosable")) != null) {
          doPaintCloseIcon = (Boolean) prop;
        }
      } catch (Exception ignored) {/*Could probably be a ClassCastException*//*}*/
/*      if (doPaintCloseIcon) {
        x_pos = x;
        y_pos = y;
        int y_p = y + 1;
 
        if (normalCloseIcon != null && !mouseover) {
          normalCloseIcon.paintIcon(c, g, x, y_p);
        } else if (hooverCloseIcon != null && mouseover && !mousepressed) {
          hooverCloseIcon.paintIcon(c, g, x, y_p);
        } else if (pressedCloseIcon != null && mousepressed) {
          pressedCloseIcon.paintIcon(c, g, x, y_p);
        } else {
          y_p++;
 
          Color col = g.getColor();
 
          if (mousepressed && mouseover) {
            g.setColor(Color.WHITE);
            g.fillRect(x+1, y_p, 12, 13);
          }
 
          g.setColor(Color.black);
          g.drawLine(x+1, y_p, x+12, y_p);
          g.drawLine(x+1, y_p+13, x+12, y_p+13);
          g.drawLine(x, y_p+1, x, y_p+12);
          g.drawLine(x+13, y_p+1, x+13, y_p+12);
          g.drawLine(x+3, y_p+3, x+10, y_p+10);
          if (mouseover)
            g.setColor(Color.GRAY);
          g.drawLine(x+3, y_p+4, x+9, y_p+10);
          g.drawLine(x+4, y_p+3, x+10, y_p+9);
          g.drawLine(x+10, y_p+3, x+3, y_p+10);
          g.drawLine(x+10, y_p+4, x+4, y_p+10);
          g.drawLine(x+9, y_p+3, x+3, y_p+9);
          g.setColor(col);
          if (fileIcon != null) {
            fileIcon.paintIcon(c, g, x+width, y_p);
          }
        }
      }
    }
*/ 
    /**
     * Returns the icon's width.
     * @return an int specifying the fixed width of the icon.
     */
/*    public int getIconWidth() {
      return width + (fileIcon != null ? fileIcon.getIconWidth() : 0);
    }
*/ 
    /**
     * Returns the icon's height.
     * @return an int specifying the fixed height of the icon.
     */
/*    public int getIconHeight() {
      return height;
    }
*/ 
    /**
     * Gets the bounds of this icon in the form of a <code>Rectangle<code>
     * object. The bounds specify this icon's width, height, and location
     * relative to its parent.
     * @return a rectangle indicating this icon's bounds
     */
/*    public Rectangle getBounds() {
      return new Rectangle(x_pos, y_pos, width, height);
    }
  }
*/ 
  /**
   * A specific <code>BasicTabbedPaneUI</code>.
   */
//  class CloseableTabbedPaneUI extends BasicTabbedPaneUI {
 
   /**
    * the horizontal position of the text
    */
//    private int horizontalTextPosition = SwingUtilities.LEFT;
 
    /**
     * Creates a new instance of <code>CloseableTabbedPaneUI</code>
     */
//    public CloseableTabbedPaneUI() {
//    }
 
    /**
     * Creates a new instance of <code>CloseableTabbedPaneUI</code>
     * @param horizontalTextPosition the horizontal position of the text (e.g.
     * SwingUtilities.TRAILING or SwingUtilities.LEFT)
     */
/*    public CloseableTabbedPaneUI(int horizontalTextPosition) {
      this.horizontalTextPosition = horizontalTextPosition;
    }
*/ 
    /**
     * Layouts the label
     * @param tabPlacement the placement of the tabs
     * @param metrics the font metrics
     * @param tabIndex the index of the tab
     * @param title the title of the tab
     * @param icon the icon of the tab
     * @param tabRect the tab boundaries
     * @param iconRect the icon boundaries
     * @param textRect the text boundaries
     * @param isSelected true whether the tab is selected, false otherwise
     */
/*    protected void layoutLabel(int tabPlacement, FontMetrics metrics,
                               int tabIndex, String title, Icon icon,
                               Rectangle tabRect, Rectangle iconRect,
                               Rectangle textRect, boolean isSelected) {
 
      textRect.x = textRect.y = iconRect.x = iconRect.y = 0;
 
      javax.swing.text.View v = getTextViewForTab(tabIndex);
      if (v != null) {
        tabPane.putClientProperty("html", v);
      }
 
      SwingUtilities.layoutCompoundLabel((JComponent) tabPane,
                                         metrics, title, icon,
                                         SwingUtilities.CENTER,
                                         SwingUtilities.CENTER,
                                         SwingUtilities.CENTER,
                                         //SwingUtilities.TRAILING,
                                         horizontalTextPosition,
                                         tabRect,
                                         iconRect,
                                         textRect,
                                         textIconGap + 2);
 
      tabPane.putClientProperty("html", null);
      
      int xNudge = getTabLabelShiftX(tabPlacement, tabIndex, isSelected);
      int yNudge = getTabLabelShiftY(tabPlacement, tabIndex, isSelected);
      iconRect.x += xNudge;
      iconRect.y += yNudge;
      textRect.x += xNudge;
      textRect.y += yNudge;
    }
*/    
    
   /*protected void layoutLabel(int tabPlacement, FontMetrics metrics,
                               int tabIndex, String title, Icon icon,
                               Rectangle tabRect, Rectangle iconRect,
                               Rectangle textRect, boolean isSelected)
   { 
        JPanel pnl = new JPanel();
        pnl.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel label = new JLabel (title);
        //label.setBackground(Color.black);
        pnl.add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3));
        //tab button
        
        JButton button = new JButton("x");//TabButton();
        pnl.add(button);
        //add more space to the top of the component
        //pnl.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        tabPane.setTabComponentAt(tabIndex,pnl);
    
    }
    
    
  }*/
 
  /**
   * A specific <code>MetalTabbedPaneUI</code>.
   */
//  class CloseableMetalTabbedPaneUI extends MetalTabbedPaneUI {
 
   /**
    * the horizontal position of the text
    */
//    private int horizontalTextPosition = SwingUtilities.LEFT;
 
    /**
     * Creates a new instance of <code>CloseableMetalTabbedPaneUI</code>
     */
//    public CloseableMetalTabbedPaneUI() {
//    }
 
    /**
     * Creates a new instance of <code>CloseableMetalTabbedPaneUI</code>
     * @param horizontalTextPosition the horizontal position of the text (e.g.
     * SwingUtilities.TRAILING or SwingUtilities.LEFT)
     */
//    public CloseableMetalTabbedPaneUI(int horizontalTextPosition) {
//      this.horizontalTextPosition = horizontalTextPosition;
//    }
 
    /**
     * Layouts the label
     * @param tabPlacement the placement of the tabs
     * @param metrics the font metrics
     * @param tabIndex the index of the tab
     * @param title the title of the tab
     * @param icon the icon of the tab
     * @param tabRect the tab boundaries
     * @param iconRect the icon boundaries
     * @param textRect the text boundaries
     * @param isSelected true whether the tab is selected, false otherwise
     */
/*    protected void layoutLabel(int tabPlacement, FontMetrics metrics,
                               int tabIndex, String title, Icon icon,
                               Rectangle tabRect, Rectangle iconRect,
                               Rectangle textRect, boolean isSelected) {
 
      textRect.x = textRect.y = iconRect.x = iconRect.y = 0;
 
      javax.swing.text.View v = getTextViewForTab(tabIndex);
      if (v != null) {
        tabPane.putClientProperty("html", v);
      }
 
      SwingUtilities.layoutCompoundLabel((JComponent) tabPane,
                                         metrics, title, icon,
                                         SwingUtilities.CENTER,
                                         SwingUtilities.CENTER,
                                         SwingUtilities.CENTER,
                                         //SwingUtilities.TRAILING,
                                         horizontalTextPosition,
                                         tabRect,
                                         iconRect,
                                         textRect,
                                         textIconGap + 2);
 
      tabPane.putClientProperty("html", null);
      
      int xNudge = getTabLabelShiftX(tabPlacement, tabIndex, isSelected);
      int yNudge = getTabLabelShiftY(tabPlacement, tabIndex, isSelected);
      iconRect.x += xNudge;
      iconRect.y += yNudge;
      textRect.x += xNudge;
      textRect.y += yNudge;
    }
  }
 */ 
  
  
  /**
 * The listener that's notified when an tab should be closed in the
 * <code>CloseableTabbedPane</code>.
 */
//public interface CloseableTabbedPaneListener extends EventListener {
  /**
   * Informs all <code>CloseableTabbedPaneListener</code>s when a tab should be
   * closed
   * @param tabIndexToClose the index of the tab which should be closed
   * @return true if the tab can be closed, false otherwise
   */
//  boolean closeTab(int tabIndexToClose);
//}
 

  
  
    public class TabButton extends JButton implements Constants//implements ActionListener {
   {
        JPanel panel = null;
        public TabButton()
        {
            int size = 15;
            
            //indexTab=indexTabIn;
            setPreferredSize(new Dimension(size, size));
            //setIcon(ICO_CANCEL16);
            setToolTipText("κλείσιμο ctrl+F4");
            //Make the button looks the same for all Laf's
            setUI(new BasicButtonUI());
            //Make it transparent
            setContentAreaFilled(false);
            //No need to be focusable
            setFocusable(false);
            setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));
            //setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));//BorderFactory.createRaisedBevelBorder());//.createEtchedBorder());
            setBorderPainted(true);
            //Making nice rollover effect
            //we use the same listener for all buttons
            setOpaque(true);
            setBackground(CLR_PANEL_END);
            setRolloverEnabled(true);
            //Close the proper tab by clicking the button
            
        }
      
      public void setEntity(JPanel panelIn)
      {
      	panel=panelIn;
      	//addActionListener(new ButtonActionListener(panel));
      	addMouseListener(new ButtonMouseListener(panel));
      }
         
    //private final static MouseListener buttonMouseListener = new MouseAdapter()
    //private final static MouseListener buttonMouseListener(MouseAdapter me)
    public class ButtonMouseListener implements MouseListener
    {
    	Component panel = null;
        public ButtonMouseListener(Component panelIn)
        {
        	panel=panelIn; 
        }
        
        public void mouseEntered(MouseEvent e)
        {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                //button.setBorderPainted(true);
                button.setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                //button.setOpaque(true);
                button.setBackground(CLR_LBL_ROLL);
            }
            
        }

        public void mouseExited(MouseEvent e)
        {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                //button.setBorderPainted(false);
                button.setBackground(CLR_PANEL_END);
                button.setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));
                //button.setOpaque(false);
                //setBackground(CLR_LBL_NORMAL);
            }
            
        }
        
      public void mousePressed(MouseEvent e)
      {
            int i = indexOfTabComponent(panel);
            setTabPrepareToClose(i); 
            	//setOpaque(true);
            	setBackground(CLR_LBL_CLICKED);
            	setBorder(BorderFactory.createLineBorder(CLR_LBL_CLICKED_BORDER));
      }        


      public void mouseClicked(MouseEvent e)
      {
      }
           
      public void mouseReleased(MouseEvent e)
      {
      }        
    }        
       
  /*     public class ButtonActionListener implements ActionListener
       {
        Component panel = null;
        public ButtonActionListener(Component panelIn)
        {
        	panel=panelIn; 
        }
        
        //REMOVE TAB
        public void actionPerformed(ActionEvent e)
        {
        	int i = indexOfTabComponent(panel);
            setTabPrepareToClose(i);    
                //System.out.println("TabWithClose.actionPerformed removedPane"+removedPane );
        }
       }*/
       
       
        //we don't want to update UI for this button
        public void updateUI()
        {
        }

        //paint the cross
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            //shift the image for pressed buttons
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(new Color(163,1,1));// Red Green Blue
           /* if (getModel().isRollover())
            {
                g2.setColor(Color.red);
            }*/
            int delta = 11;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }
    }
  
  
  
}
