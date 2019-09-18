// created 10-10-2009

// copy from http://www.developer.com/java/other/article.php/905671/User-Code-Rendering-a-JTable-Header.htm

 package com.tool.guicomps;

/**
     * To support column header icons and tool tips. :-)
     * Usage:
     * >P<
     * >PRE<
     *  MyHeaderRenderer mhr = new MyHeaderRenderer();
     *  mhr.setToolTip(toolTipText);
     *  mhr.setIcon(icon);
     *  TableColumn col = getColumnModel().getColumn(columnIndex);
     *  col.setHeaderRenderer(mhr);
     * >/PRE<
     * This means that you need a new MyHeaderRenderer for each column. The alternative
     * would have been to use only one global renderer and do all the column (index)
     * mappings within this class which would save memory but would be slower. ;-)
     */
     
  import java.awt.*;
  import javax.swing.*;
  import javax.swing.border.*;
  import javax.swing.table.*;
  import java.text.*;
     
    public class TableHeaderRenderer extends DefaultTableCellRenderer {

        /** label to display also an icon */
        JLabel label = null;

        /**
         */
        public TableHeaderRenderer()
        {
            super();
            label = new JLabel();
            label.setIconTextGap(0);
            label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)); // EtchedBorder.RAISED  or EtchedBorder.LOWERED
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
        }//constructor

        /**
         * Overwrites DefaultTableCellRenderer.
         */
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
             super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column);
            if (value != null) {
                label.setText("<html><table><tr><td>"+value+"</td></tr></table></html>"); // text value of header
            } else {
                label.setText("<html><table><tr><td></td></tr></table></html>");// text value of header
            }

            return label;
        }//getTableCellRendererComponent()

        /**
         * Overwrires DefaultTableCellRenderer.
         */
        protected void setValue(Object value)
        {
        	super.setValue("" + value);
            if (value != null)
            {
                label.setText("" + value);
            } else
            {
                label.setText("");
            }
        }//setValue()

        /**
         * @param toolTip text to use for label's tool tip
         */
        public void setToolTip(String toolTip)
        {
            if (toolTip != null) {
                label.setToolTipText(toolTip);
            }
        }//setToolTip()

        /**
         * @param icon icon to set for the label
         */
        public void setIcon(Icon icon)
        {
            if (label != null) {
               label.setIcon(icon);
            }
        }//setIcon()
        

        
    }//MyHeaderRenderer
