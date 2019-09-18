
package com.tool.guicomps;

import javax.swing.table.AbstractTableModel;
import com.tool.jdbc.*;

 public class TableModelGeneric extends AbstractTableModel 
 {
        String[] columnNames;// = new String[4];
        Object[][] data;// = new Object[4][4];
        
   TableModelGeneric(String[] columnNamesIn, Object[][] dataIn)
   {    
   	    //System.out.println("TableModelGeneric, get data and tablenames");
        columnNames = columnNamesIn;
        data = dataIn;
   }

 	TableModelGeneric()
 	{
 		System.out.println("TableModelGeneric, default constructor");
 		
 	}
 	    
      /*  public final Object[] longValues = {"Angela", "Andrews", "Teaching high school",
                                            new Integer(20), Boolean.TRUE};
        */                                    
        private boolean DEBUG = true;

        public int getColumnCount() {
            return columnNames.length;
        }
        
        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c)  //error in num comes from here
        {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            /*if (col < 0) { 
                return false;
            } else {*/
                return true;
            
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col)
        {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of " 
                                   + value.getClass() + ")");
            }

            /*if (data[0][col] instanceof Integer)
            {
                //If we don't do something like this, the column
                //switches to contain Strings.
                try
                {
                    data[row][col] = new Integer((String)value);
                    fireTableCellUpdated(row, col);
                }
                catch (NumberFormatException e)
                {
                	System.out.println("The \"" + getColumnName(col)+"\" column accepts only integer values.");
                    JOptionPane.showMessageDialog(TableRenderDemo.this,
                        "The \"" + getColumnName(col)
                        + "\" column accepts only integer values.");
                }
            } else {
                data[row][col] = value;
                fireTableCellUpdated(row, col);
            }*/

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }


        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
 }
