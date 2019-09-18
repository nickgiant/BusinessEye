package com.tool.guicomps;

import java.awt.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class JButtonListMenu extends JToggleButton//JButton
{

    private static final long serialVersionUID = 1L;

    private Color arrowColor = Color.BLACK;
JPopupMenu popup;
    
    public JButtonListMenu() {
        super();
    }

    public JButtonListMenu(String label) {
        super(label);
    }

    
    
//public class MenuButton extends JToggleButton {

    

    public JButtonListMenu(String name, JPopupMenu menu) {
        super(name);
        this.popup = menu;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                JToggleButton b = JButtonListMenu.this;
                if (b.isSelected())
                {
                    popup.show(b, 0, b.getBounds().height);
                }
                else 
                {
                    popup.setVisible(false);
                }
            }
        });
        popup.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
            {
                JButtonListMenu.this.setSelected(false);
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });
    }
//} 
        public void setMenuPopup( JPopupMenu menu) {
        //super(name);
        this.popup = menu;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                JToggleButton b = JButtonListMenu.this;
                if (b.isSelected())
                {
                    popup.show(b, 0, b.getBounds().height);
                }
                else 
                {
                    popup.setVisible(false);
                }
            }
        });
        popup.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
            {
                JButtonListMenu.this.setSelected(false);
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension originalSize = super.getPreferredSize();
        int gap = (int) (originalSize.height * 0.2);
        int x = 4;//originalSize.width +5;//+ gap;
        int y = originalSize.height/2;//gap;
        //int diameter = originalSize.height - (gap * 2);
                          
            g.drawLine(x, y, x+4, y);
            g.drawLine(x+1, y+1, x+3, y+1);
            g.drawLine(x+2, y+2, x+2, y+2);        
        
        
        //g.setColor(circleColor);
        //g.fillOval(x, y, diameter, diameter);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        //size.width += size.height;
        return size;
    }

    /*Test the button*/
    public static void main(String[] args)
    {
        JPopupMenu popupMenuTools = new JPopupMenu("----");
        JButtonListMenu button = new JButtonListMenu("Hello, World!",popupMenuTools); // or call setMenuPopup(menu)
       // button.setText("Hello, World!",popupMenuTools);
        
        
        //button.add(popupMenuTools);
        popupMenuTools.add(new JMenuItem("όσοι  κινήθηκαν 1"));
        popupMenuTools.add(new JMenuItem("όσοι  κινήθηκαν 2"));
        popupMenuTools.add(new JMenuItem("όσοι  κινήθηκαν 3"));
        popupMenuTools.add(new JMenuItem("όσοι  κινήθηκαν 4"));
        popupMenuTools.add(new JMenuItem("όσοι  κινήθηκαν 5"));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(button);

        frame.setVisible(true);
    }



    // from   http://www.koders.com/java/fid20361AB8C305DE9B9110DE90F2154FC43AA0E57C.aspx?s=net.sourceforge.processdash.ui.lib#L27
    /** An icon to draw a small downward-pointing arrow.
     */
   /* public static class ArrowDown implements Icon
    {

        Color arrowColor = Color.black;

        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(arrowColor);
            g.drawLine(x, y, x+4, y);
            g.drawLine(x+1, y+1, x+3, y+1);
            g.drawLine(x+2, y+2, x+2, y+2);
        }

        public int getIconWidth() {
            return 6;
        }

        public int getIconHeight() {
            return 4;
        }

    }*/


}
