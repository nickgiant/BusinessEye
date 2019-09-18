// created 23-09-2006
package com.tool.utils;

import com.tool.guicomps.*;
import com.tool.model.EntityDBFields;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table .TableCellRenderer;

import java.awt.Component;

import java.util.*;

 public class UtilsTable implements Constants
 {
 	

  	
    public UtilsTable()
    {



    }
     
     //based on javaalmanac
    /* public void sortColumn(TableModelResultSet model, int colIndex, boolean ascending)
     {
        Vector data = model.getTableDataVector();//getDataVector();
        
        Object[] colData = new Object[model.getRowCount()];
    
        // Copy the column data in an array
        for (int i=0; i<colData.length; i++) {
            colData[i] = ((Vector)data.get(i)).get(colIndex);
        }
    
        // Sort the array of column data
        Arrays.sort(colData, new ColumnSorter(ascending));
    
        // Copy the sorted values back into the table model
        for (int i=0; i<colData.length; i++) {
            ((Vector)data.get(i)).set(colIndex, colData[i]);
        }
        model.fireTableStructureChanged();
    }
    //based on javaalmanac
    public class ColumnSorter implements Comparator
    {
        boolean ascending;
        ColumnSorter(boolean ascending) {
            this.ascending = ascending;
        }
        public int compare(Object a, Object b) {
            // Treat empty strains like nulls
            if (a instanceof String && ((String)a).length() == 0) {
                a = null;
            }
            if (b instanceof String && ((String)b).length() == 0) {
                b = null;
            }
    
            // Sort nulls so they appear last, regardless
            // of sort order
            if (a == null && b == null) {
                return 0;
            } else if (a == null) {
                return 1;
            } else if (b == null) {
                return -1;
            } else if (a instanceof Comparable) {
                if (ascending) {
                    return ((Comparable)a).compareTo(b);
                } else {
                    return ((Comparable)b).compareTo(a);
                }
            } else {
                if (ascending) {
                    return a.toString().compareTo(b.toString());
                } else {
                    return b.toString().compareTo(a.toString());
                }
            }
        }
    }*/


    
    // Sets the preferred width of the column. The column will be wide enough to
    // show the column head and the widest cell in the column. margin pixels are added to the left and right.
    // public because DialogLogin also uses it
    public int packColumn(JTable table, int vColIndex, int margin, boolean preferredIsVisible, boolean isSetColWidthFromEntityData,EntityDBFields dbFieldMany)
    {
        //TableModel model = table.getModel();
        DefaultTableColumnModel colModel = (DefaultTableColumnModel)table.getColumnModel();
        
        TableColumn col = colModel.getColumn(vColIndex);
        //System.out.println("UtilsTable.packColumn col: "+col+" vColIndex "+vColIndex);
        int width = 0;
    
        // Get width of column header
        TableCellRenderer hRenderer = col.getHeaderRenderer();
        Component comp;
   if(!isSetColWidthFromEntityData) // isSetColWidthFromEntityData = false and dbFieldMany=null
   {
        if (hRenderer == null)
        {
            if (table.getTableHeader()!=null)// in case header is null (DialogLogin)
            {
            	//String colCap = col.getHeaderValue().toString();
            	//System.out.println("UtilsTable.packColumn "+vColIndex+" "+colCap);
            	
            	// TableColumn col = table.getColumnModel().getColumn(i);
             //col.setHeaderRenderer(thr);     
             //col.getHeaderRenderer()
             
             //TableColumn col = colModel.getColumn(vColIndex);    	
    	     String columnCaption = col.getHeaderValue().toString();

                hRenderer = table.getTableHeader().getDefaultRenderer();
             comp = hRenderer.getTableCellRendererComponent(table, columnCaption, false, false, 0, 0);
               width = comp.getPreferredSize().width;
               
               //System.out.println("UtilsTable.packColumn "+vColIndex+" "+width);
               
               //width = colCaption.length()*7;
           }
        }
        else
        {
    	     String columnCaption = col.getHeaderValue().toString();
            	
                //hRenderer = table.getTableHeader().getDefaultRenderer();
             comp = hRenderer.getTableCellRendererComponent(table, columnCaption, false, false, 0, 0);
               width = comp.getPreferredSize().width;
               
               //System.out.println("UtilsTable.packColumn else  "+vColIndex+" "+width);        	
        }
        
        //System.out.println("UtilsTable.packColumn "+table.getRowCount());
        // Get maximum width of column data
        for (int r=0; r<table.getRowCount(); r++)
        {
            hRenderer = table.getCellRenderer(r, vColIndex);
            
            //System.out.println("UtilsTable.packColumn "+vColIndex+" "+col.getHeaderValue().toString()+" "+width+" "+table.getValueAt(r, vColIndex)+" "+hRenderer.getClass());
            
            comp = hRenderer.getTableCellRendererComponent(table, table.getValueAt(r, vColIndex), false, false, r, vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
    
        // Add margin
        width += 2*margin;
        
        // string for string , object for lookup
        if( table.getModel().getColumnClass(vColIndex) == Object.class && width>150)
        {
            	width = 150;
        }
    }
    else
    {
       width =  dbFieldMany.getColWidth();
       width = width * 9;
       width += 2*margin;
    }
     
       
        // Set the width
        if(preferredIsVisible)
        {
             col.setPreferredWidth(width);    
        }
        else
        {// PnelOneDataManyRecData.packColumnsWritable    else if(dbFieldsMany[c].getIsHidden())
              // col.setPreferredWidth(width); 
             col.setPreferredWidth(0);  
            
             //table.getModel().isCellEditable(vColIndex, vColIndex)
             col.setMinWidth(0);
             col.setMaxWidth(0);
              //colModel.getColumn(vColIndex).
        }
        
        return width;
    }
    
    //exists in panelODMRData (this not use here) , WindowLookUpMultipleCheck , PanelDataViewNOrder
    public Dimension setTableScrollPaneSize(JTable table ,int totalWidthOfColumns, int maxHeight)
    {
        int height=200;
        int width=100;
        int columnCount=table.getColumnCount();
        int rowCount=table.getRowCount();
        
        //System.out.println("UtilsTable.setTableScrollPaneSize"+table.getRowHeight()+"*"+rowCount);
  /*      for (int r=0; r<rowCount;r++)
        {
        	colorRow(r);
        }*/
        //height=90;
     
        if (totalWidthOfColumns<795) // maximum width 795
        {   width = totalWidthOfColumns + 4;}
        else
        {   width=795;  }
        
        if (columnCount<=2)
        {      width=250;     }
        else if ((columnCount>2) && (columnCount<=4))
        {      width=400;     }
        else if ((columnCount>4) && (columnCount<=6))
        {      width=570;     }
        else if (columnCount>6)
        {      width=730;     }
        
        
        
        /*if (rowCount<=2)
        {      height=70;     }
        else if ((rowCount>2) && (rowCount<=3))
        {      height=90;     }
        else if ((rowCount>3) && (rowCount<=4))
        {      height=100;     }
        else if ((rowCount>4) && (rowCount<=6))
        {      height=130;     }
        else if ((rowCount>6) && (rowCount<=8))
        {      height=170;     }
        else if ((rowCount>8) && (rowCount<=10))
        {      height=210;     }
        else if ((rowCount>10) && (rowCount<=12))
        {      height=240;       }
        else if ((rowCount>12) && (rowCount<=14))
        {      height=250;       }    
        else if ((rowCount>14) && (rowCount<=16))
        {      height=260;       }  */
       
        if(rowCount<=5)
        {
        	height = 160;
        }
        /*else if(rowCount>3 && rowCount<=5)
        {
        	height=(table.getRowHeight()+10)*rowCount;
        } */  
        else if(rowCount>5 && rowCount<=9)
        {
        	height=(table.getRowHeight()+5)*rowCount;
        	if(height>280)
        	{
        		height=280;
        	}
        }
        else if(rowCount>9 && rowCount<=16)
        {
        	height=(table.getRowHeight()+3)*rowCount;
        	if(height>280)
        	{
        		height=280;
        	}
        }              
        else if (rowCount>16)
        {
        	int maxWidth=totalWidthOfColumns+25;
        	   
        	   height=maxHeight; //280
               
                width=maxWidth ;

                if (maxWidth>795) // maximum width 795
                {   width=795;  }
        }
        //System.out.println("UtilsTable.setScrollPaneSize rowCount:"+rowCount+" height:"+height);
        //tableScrollPane.setPreferredSize(new Dimension(totalWidthOfColumns, height));
    
     return new Dimension(totalWidthOfColumns, height);
    }   
    	
   public void copyTableToReadOnlyTable(JTable tableFrom, JTable tableTo)
   {

/*   http://groups.google.com/group/jhug/browse_thread/thread/85cd2d93dd042f74

TableModel model = table.getTableModel();

if(model instanceof DefaultTableModel) {
 DefaultTableModel tableModel = (DefaultTableModel)model;
  //remove code specific to  DefaultTableModel

} else if(model instanceof MyDefaultTableModel) {

   MyDefaultTableModeltableModel = (MyDefaultTableModel)model;
   //remove code specific to  MyDefaultTableModel

} else if(model instanceof CustomTableModel){

   CustomTableModeltableModel = (CustomTableModel)model;
   //remove code specific to  CustomTableModel

} 
*/


        int rowCountTableFrom = tableFrom.getRowCount();
    	int colCountTableFrom = tableFrom.getColumnCount();

        int rowCountTableTo = tableTo.getRowCount();
    	int colCountTableTo = tableTo.getColumnCount();
        
        
     /*   TableModel modelTo = tableTo.getModel();
        TableModel tableModelTo =(DefaultTableModel)modelTo;
       if(modelTo instanceof DefaultTableModel)
       {
            tableModelTo = (DefaultTableModel)modelTo;
           //remove code specific to  DefaultTableModel
       }
       else if(modelTo instanceof TableModelReadOnly)
       {
            tableModelTo = (TableModelReadOnly)modelTo;
           //remove code specific to  MyDefaultTableModel
       }
        
        
        TableModel modelFrom = tableFrom.getModel();
        TableModel tableModelFrom =(DefaultTableModel)modelFrom;
       if(modelFrom instanceof DefaultTableModel)
       {
            tableModelFrom = (DefaultTableModel)modelFrom;
           //remove code specific to  DefaultTableModel
       }
       else if(modelFrom instanceof TableModelReadOnly)
       {
            tableModelFrom = (TableModelReadOnly)modelFrom;
           //remove code specific to  MyDefaultTableModel
       }   */     
        
     	TableModelReadOnly tableModelTo = (TableModelReadOnly)tableTo.getModel();
     //	DefaultTableModel tableModelFrom = (DefaultTableModel)tableFrom.getModel();

    	// add columns
    	//System.out.println("UtilsTable.copyTableToTable FROM c:"+colCountTableFrom+" r:"+rowCountTableFrom);
    	ArrayList columns = new ArrayList();
    	for(int c=0;c<colCountTableFrom;c++)
    	{
           //System.out.println("UtilsTable.copyTableToTable c:"+c);
           columns.add(c, tableFrom.getColumnName(c));
    	}
        

      /* // Get all the table data 
       Vector data = model.getDataVector();
       // Copy the second row 
       Vector row = (Vector)data.elementAt(1);
       row = (Vector)row.clone(); 
        
        //int mColIndex = 0;*/
        //ArrayList colData = new ArrayList(rowCountTableFrom);
        Vector vectorTo= new Vector();
        
        for (int r=0; r<rowCountTableFrom; r++)
        { 
        	//row = (Vector)data.elementAt(r);
            //colData.add(row.get(mColIndex));
           Object[] row = new Object[colCountTableFrom];//(Vector)data.elementAt(r);
           for(int c=0;c<colCountTableFrom;c++)      
           {
           	  row[c]=tableFrom.getValueAt(r,c);
           	  //tableTo.setValueAt(tableFrom.getValueAt(r,c),r,c); // (Object aValue, int row, int column) 
           	  //System.out.println("UtilsTable.copyTableToReadOnlyTable r "+r+" c "+c);
           	  
           }
           vectorTo.add(r,row);
            
        } // Copy it to the second column 
        

        
        tableModelTo.setDataVector(vectorTo,columns);
        
       /* for (int i=0; i<colData.size(); i++)
        { 
        	row = (Vector)data.elementAt(i);
        	row.set(1, colData.get(i));
        }*/         
        
        
        
   	    //tableModelTo.setDataVector(tableModelFrom.getDataVector(), columns);
       	
       	//tmTo.setDataVector(Object[][] dataVector, Object[] columnIdentifiers);
    	//setDataVector(Vector dataVector, Vector columnIdentifiers)    	
   	    //tableModelTo.fireTableStructureChanged(); 
        //tableModelTo.fireTableDataChanged();
   }

    	
    /*public void copyTableToTable(JTable tableFrom, JTable tableTo)	
    {
    	// if there is any dta remove
        int rowCountTableFrom = tableFrom.getRowCount();
    	int colCountTableFrom = tableFrom.getColumnCount();

        int rowCountTableTo = tableTo.getRowCount();
    	int colCountTableTo = tableTo.getColumnCount();
 
     	DefaultTableModel tmTo = (DefaultTableModel)tableTo.getModel();
    	

    	// add columns
    	for(int c=0;c<colCountTableFrom;c++)
    	{
    	  
    	  tmTo.addColumn(tableFrom.getColumnName(c));
    	}

       // add data
      System.out.println("UtilsTable.copyTableToTable FROM c:"+colCountTableFrom+" r:"+rowCountTableFrom);
      
      //DefaultTableModel tmFrom = (DefaultTableModel)tableTo.getModel();
      for (int r = 0; r < rowCountTableFrom; r++)
      {
        Object[] record = new Object[colCountTableFrom];
     
    	for(int c=0;c<colCountTableFrom;c++)
    	{
    	  //System.out.println("UtilsTable.copyTableToTable c:"+c+" r:"+r+" "+tableFrom.getValueAt(r,c));
    	  record[c]=tableFrom.getValueAt(r,c)+"";
    	}         

                 tmTo.addRow(record);  
        // dataVector.addElement(record);
      }

        tmTo.fireTableStructureChanged(); 
        tmTo.fireTableDataChanged();
        
        
        int colcount = tableTo.getColumnCount();
        for(int c=0;c<colcount;c++)
        {
           packColumn(tableTo,c,2);	
        	
        }    	
    	
    	
    }*/
    
    
    // read only
    public void setTableExitNavigationKeys(JTable tableIn)
    {
    	Set forwardKeys = new HashSet();
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0));
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
        tableIn.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,forwardKeys);  	
        
        Set backwardKeys = new HashSet();
        backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,java.awt.event.InputEvent.SHIFT_DOWN_MASK));// 1 for alt key
        tableIn.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS ,backwardKeys);  	
        
        tableIn.setFocusTraversalKeys(KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS,null);
        tableIn.setFocusTraversalKeys(KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS,null);
       
        
    }

    // read only
    public void setTableNavigationKeys(JTable tableIn)
    {  
//https://community.oracle.com/thread/1484485?start=0&tstart=0        
// the enter key to work as the tab key
        KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        InputMap inputMap = tableIn.getInputMap(JTableDec.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(enter, inputMap.get(tab));
 

        /*
InputMap im = tableIn.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
ActionMap am = tableIn.getActionMap();

KeyStroke tabKey = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);

Action moveNextCellAction = am.get(im.get(tabKey));

ContinousEditAction continousEditAction = new ContinousEditAction(tableIn, moveNextCellAction);

im.put(tabKey, "Action.tab");

am.put("Action.tab", (Action) continousEditAction);
        */
        
        
        
       // no need for shift tab. It works without it. 
       /* Set backwardKeys = new HashSet();
    KeyStroke shiftTab =  KeyStroke.getKeyStroke(KeyEvent.VK_TAB, java.awt.event.InputEvent.SHIFT_DOWN_MASK);         
        backwardKeys.add(shiftTab);// 1 for alt key
        tableIn.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS ,backwardKeys);  	
       */
        
        
        
        //  http://stackoverflow.com/questions/3013165/jtable-getting-a-cell-into-edit-mode-on-pressing-tab
     
        
                    
        
        
        
    }

  	
    	
/*DefaultTableModel model = new MyDefaultTableModel();
JTable table = new JTable(model);
table.setModel(model);

// Create 3 columns
model.addColumn("Col1");
model.addColumn("Col2");
model.addColumn("Col3");
model.addRow(new Object[]{"v1"});

// Remove the first visible column without removing the underlying data
table.removeColumn(table.getColumnModel().getColumn(0));

// Disable autoCreateColumnsFromModel to prevent
// the reappearance of columns that have been removed but
// whose data is still in the table model
table.setAutoCreateColumnsFromModel(false);

// Remove the first visible column and its data
removeColumnAndData(table, 0);

// Remove the last visible column and its data
removeColumnAndData(table, table.getColumnCount()-1);*/

// Removes the specified column from the table and the associated
// call data from the table model.
public void removeColumnAndData(JTable table, int vColIndex) {
    MyDefaultTableModel model = (MyDefaultTableModel)table.getModel();
    TableColumn col = table.getColumnModel().getColumn(vColIndex);
    int columnModelIndex = col.getModelIndex();
    Vector data = model.getDataVector();
    Vector colIds = model.getColumnIdentifiers();

    // Remove the column from the table
    table.removeColumn(col);

    // Remove the column header from the table model
    colIds.removeElementAt(columnModelIndex);

    // Remove the column data
    for (int r=0; r<data.size(); r++) {
        Vector row = (Vector)data.get(r);
        row.removeElementAt(columnModelIndex);
    }
    model.setDataVector(data, colIds);

    // Correct the model indices in the TableColumn objects
    // by decrementing those indices that follow the deleted column
    Enumeration enumer = table.getColumnModel().getColumns();
    for (; enumer.hasMoreElements(); ) {
        TableColumn c = (TableColumn)enumer.nextElement();
        if (c.getModelIndex() >= columnModelIndex) {
            c.setModelIndex(c.getModelIndex()-1);
        }
    }
    model.fireTableStructureChanged();
}

// This subclass adds a method to retrieve the columnIdentifiers
// which is needed to implement the removal of
// column data from the table model
class MyDefaultTableModel extends DefaultTableModel {
    public Vector getColumnIdentifiers() {
        return columnIdentifiers;
    }
}




/*

called by this

The ContinousEditAction is responsible for finding the next editable cell. 
Basically when the action is fired, you take stock of the current cell via 
JTable.getEditingRow & JTable.getEditingColumn methods 
(you also want to check that the table is edit mode via JTable.isEditing, 
otherwise you need to use JTable.getSelectedRow & JTable.getSelectedColumn - in fact you 
might get away with doing just this, but this is how I approached the problem).

From there, you want to walk the cells until you find a cell that is editable.

Basically, you want to check to the end of the current row, then move to the next 
until no more rows exist, depending on what you want to do, you may choose to loop back 
around to the start of the table (cell 0x0) and walk it until you reach your current position.

Be careful, you can end up in a continuous loop if you're not careful :P.

If you don't find any editable cells, you may simply wish to select the next available cell
using JTable.setRowSelectionInterval & JTable.setRowSelectionInterval, other wise you
can call JTable.editCellAt(nextRow, nextCol)

But this all comes down to what it is you want to achieve.
*/
/*class ContinousEditAction 
{

    private  ContinousEditAction(JTable tableIn,Action moveNextCellAction)
    {
        
    }
 }*/
	
    	 
}    