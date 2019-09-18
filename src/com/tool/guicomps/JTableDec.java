// created 30-12-2009
//  http://nadeausoftware.com/articles/2008/01/java_tip_how_add_zebra_background_stripes_jtable


package com.tool.guicomps;

import com.tool.utils.UtilsTable;
import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.text.JTextComponent;
/**
 * A JTable that draws a zebra striped background.
 */
//   if  table.setOpaque(false);   not decorated

public class JTableDec extends JTable implements Constants
{
    private java.awt.Color rowColors[] = new java.awt.Color[2];
    private boolean drawStripes = false;
    public boolean isEditable = false;
    
       
    //private PropertyChangeListener AncestorPropertyChangeListener = createAncestorPropertyChangeListener();
    
    /*
    used in changeSelection bellow
    */
 public static enum DIR
 {
      Up, Down, Left, Right
 }    
    
    
    public JTableDec()
    {
    	//this.addPropertyChangeListener("ancestor", fAncestorPropertyChangeListener);
    }
    public JTableDec( int numRows, int numColumns )
    {
        super( numRows, numColumns );
       // this.addPropertyChangeListener("ancestor", fAncestorPropertyChangeListener);
    }
    public JTableDec( Object[][] rowData, Object[] columnNames )
    {
        super( rowData, columnNames );
        //this.addPropertyChangeListener("ancestor", fAncestorPropertyChangeListener);
    }
    public JTableDec( javax.swing.table.TableModel dataModel )
    {
        super( dataModel );
        //this.addPropertyChangeListener("ancestor", fAncestorPropertyChangeListener);
    }
    public JTableDec( javax.swing.table.TableModel dataModel,
        javax.swing.table.TableColumnModel columnModel )
    {
        super( dataModel, columnModel );
        //this.addPropertyChangeListener("ancestor", fAncestorPropertyChangeListener);
    }
    public JTableDec( javax.swing.table.TableModel dataModel,
        javax.swing.table.TableColumnModel columnModel,
        javax.swing.ListSelectionModel selectionModel )
    {
        super( dataModel, columnModel, selectionModel );
        //this.addPropertyChangeListener("ancestor", fAncestorPropertyChangeListener);
    }
    /*public JTableDec( java.util.Vector<?> rowData,
        java.util.Vector<?> columnNames )
    {
        super( rowData, columnNames );
        //this.addPropertyChangeListener("ancestor", fAncestorPropertyChangeListener);
    }*/
    
    
    
    //http://www.coderanch.com/t/346712/GUI/java/Tabbing-editable-cells-JTable
       /*  public void valueChanged(ListSelectionEvent e)  
            {  
                if(!e.getValueIsAdjusting())  
                {  
                    int row = getSelectedRow();  
                    int col = getSelectedColumn();  
                    // Make sure we start with legal values.  
                    while(col < 0) col++;  
                    while(row < 0) row++;  
                    // Find the next editable cell.  
                    while(!isCellEditable(row, col))  
                    {  
                        col++;  
                        if(col > getColumnCount()-1)  
                        {  
                            col = 1;  
                            row = (row == getRowCount()-1) ? 1 : row+1;  
                        }  
                    }  
                    // Select the cell in the table.  
                    final int r = row, c = col;  
                    EventQueue.invokeLater(new Runnable()  
                    {  
                        public void run()  
                        {  
                            changeSelection(r, c, false, false);  
                        }  
                    });  
                    // Edit.  
                    if(isCellEditable(row, col))  
                    {  
                        editCellAt(row, col);  
                        //((JTextField)editorComp).selectAll();  
                        editorComp.requestFocusInWindow();  
                    }  
                }  
            }       */
    
  
   
    
    
    
/*
    *
    *  go to next editable cell
    *
    */    
public void changeSelectionf(int row,int col,boolean toggle,boolean expand) 
{
    // This method is called when the user tries to move to a diffferent cell.
    // If the cell they're trying to move to is not editable, we look for
    // then next cell in the proper direction that is editable.
    if (this.isEditable && !getModel().isCellEditable(row,col))
    {
        // Find the row and column we're coming from.
        int curRow = getEditingRow();
        int curCol = getEditingColumn();
        if (curRow == -1) curRow = getSelectedRow();
        if (curCol == -1) curCol = getSelectedColumn();

        // We may need to wrap-around.
        int nRows = getRowCount();
        int nCols = getColumnCount();

        // If we can't find a cell to move to, we'll stay here.
        int nextRow = row;
        int nextCol = col;

        if (col==curCol) 
        {
            // Up or down motion - go only up or down.
            int direction = row-curRow;
            if (direction>1) direction=1;
            if (direction<-1) direction=-1;
            nextRow = findNextEditableRow(row,col,direction,nRows,nCols);
        }
        else if (row == curRow) 
        {
            // Left-or-right motion - use the "natural" (for Americans) order:
            // left-to-right, top-to-bottom, or vice-versa if we're trying
            // to move to the left. We'll wrap from the bottom row to the top
            // and vice-versa if necessary.
            int direction = col-curCol;
            if (direction>1) direction=1;
            if (direction<-1) direction=-1;
            int[] nextCell = findNextEditableCell(row,col,direction,nRows,nCols);
            nextRow = nextCell[0];
            nextCol = nextCell[1];
        }
        else
        {
            // Both row and column differ. This probably means we've
            // moved off the end of a row, or else the user has clicked
            // on some random cell. The direction is controlled
            // by the row difference (this doesn't always do something
            // intuitive; always setting direction=1 might work better).
            int direction = row-curRow;
            if (direction>1) direction=1;
            if (direction<-1) direction=-1;
            if ((row==0) && (curRow==nRows-1)) direction=1;
            int[] nextCell = findNextEditableCell(row,col,direction,nRows,nCols);
            nextRow = nextCell[0];
            nextCol = nextCell[1];
        }
        // Go to the cell we found.
        //super.changeSelection(nextRow,nextCol,toggle,expand);
        this.changeSelection(nextRow,nextCol,toggle,expand);
           //System.out.println("JTableDec.changeSelection after super  ooo-ooo   isCellEditable:"+isCellEditable(nextRow,nextCol)+" nextRow:"+nextRow+" nextCol:"+nextCol);
         // make edit text in next focusable text
            if(isCellEditable(nextRow,nextCol))
            {
               System.out.println("JTableDec.changeSelection after super                ooo-ooo         isCellEditable:"+isCellEditable(nextRow,nextCol)+" nextRow:"+nextRow+" nextCol:"+nextCol); 
        if (editCellAt(nextRow, nextCol)) // when target cell is editable, set focus
        {
            Component editor = getEditorComponent();
            //editor.setBounds(5, 5, 5, 5);
            //editor.requestFocus();
            editor.requestFocusInWindow();
//          ((JTextComponent)editor).selectAll();
        }
            }
            else
            {
                //System.out.println("JTableDec.changeSelection after super NOT EDITABLE  isCellEditable:"+isCellEditable(targetViewRow,targetViewCol)+" row:"+targetViewRow+" col:"+targetViewCol);
            }        
   
    } 
    else
    {
        // It's an editable cell, so leave the selection here.
        super.changeSelection(row,col,toggle,expand);
    }
}

// Search for an editable cell starting at row,col and using the
// "natural" order.
private int[] findNextEditableCell(int row,int col, int direction,int nRows,int nCols) {
    int origRow=row;
    int origCol=col;
    do {
    col = col+direction;
    if (col>=nCols) {
        col = 0;
        row += direction;
    }
    if (col<0) {
        col = nCols-1;
        row += direction;
    }
    if (row>=nRows) row = 0;
    if (row<0) row = nRows-1;
    //System.out.println("FNEC looking at "+row+','+col);
                if (isCellEditable(row,col)) return new int[]{row,col};
    } while (!((row==origRow)&&(col==origCol)));

    // Nothing editable found; stay here.
    return new int[]{origRow,origCol};
}

// Search directly above/below for an editable cell.
 private int findNextEditableRow(int row,int col,int direction,int nRows,int nCols) {
    int origRow = row;
    do {
        row = row+direction;
        if (row<0) row = nRows-1;
        if (row>=nRows) row=0;
        if (isCellEditable(row,col)) return row;
    } while (row != origRow);
    // Nothing editable found, stay here.
    return origRow;
}    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
  /*   https://gist.github.com/exhuma/976531
    */
//@Override
public void changeSelection(int row, int col, boolean toggle, boolean expand) {
// This method is called when the user tries to move to a different cell.
// If the cell they're trying to move to is not editable, we look for
// the next cell in the proper direction that is editable.
// initialise the new (future) selection to the indices as specified in
// the method call.
// if everything is "normal" we can just use these values. All special
// cases will overwrite the value contained herein. Eventually, these
// variables are returned.

  // is typed in panelODMRData.selection on tab or enter pressed 
int targetViewRow = row;
int targetViewCol = col;
 
int targetModelRow = convertRowIndexToModel(targetViewRow);
int targetModelCol = convertColumnIndexToModel(targetViewCol);
 

 

  
                   
// System.out.println("--JtableDec.changeSelection before if    this.isEditable:"+this.isEditable +"      model is cell editable:"+ getModel().isCellEditable(targetModelRow, targetModelCol)+"  "+targetModelRow+"  "+targetModelCol);
 // isMouseClickedOnTable false: because we would not like to focus on next editable when a cell is clicked
if (this.isEditable && !getModel().isCellEditable(targetModelRow, targetModelCol))
{

 
// Find the row and column we're coming from.
int oldViewRow = getEditingRow();
int oldViewCol = getEditingColumn();
if (oldViewRow == -1)
{
oldViewRow = getSelectedRow();
}
if (oldViewCol == -1)
{
oldViewCol = getSelectedColumn();
}
 


//System.out.println("JtableDec.changeSelection SWITCH A "+this.isEditable+"   model cell editable"+getModel().isCellEditable(targetModelRow, targetModelCol)+"   targetModelRow:"+targetModelRow+"   targetModelCol:"+targetModelCol);

DIR direction;
if (oldViewCol == targetViewCol && oldViewRow < targetViewRow)
{
   direction = DIR.Down;
} 
else if (oldViewCol == targetViewCol && oldViewRow >= targetViewRow)
{
   direction = DIR.Up;
}
else if (oldViewCol == targetViewCol && oldViewRow == targetViewRow)
{
   direction = DIR.Left;
}
else 
{
// defaulting to right
   direction = DIR.Right;
}
 
//LOG.fine(String.format("Moving %s", direction));
 
System.out.println("JtableDec.changeSelection SWITCH B "+this.isEditable+"   model cell editable"+getModel().isCellEditable(targetModelRow, targetModelCol)+"  direction:"+direction+"   targetModelRow:"+targetModelRow+"   targetModelCol:"+targetModelCol);
// determine next cell position
while (this.isEditable && !getModel().isCellEditable(targetModelRow, targetModelCol))
{
//LOG.fine(String.format("Model-Cell %d,%d is still not editable",targetModelRow, targetModelCol));

 //   System.out.println("JtableDec.changeSelection SWITCH "+ direction +"  targetModelRow:"+targetModelRow+"  targetModelCol:"+targetModelCol+"  "+this.isEditable+"   model is cell editable:"+!getModel().isCellEditable(targetModelRow, targetModelCol)); 
  switch (direction)
  {
    case Up:
       targetViewRow -= 1;
       if (targetViewRow < 0)
       {
           targetViewRow = getRowCount() - 1;
       }
//System.out.println("JtableDec.changeSelection SWITCH UP"+ direction); 
       break;
    case Down:
       targetViewRow += 1;
       if (targetViewRow > getRowCount() - 1)
       {
          targetViewRow = 0;
       }
//System.out.println("JtableDec.changeSelection SWITCH DOWN"+ direction); 
       break;
    case Left:
      targetViewCol -= 1;
      if (targetViewCol < 0) 
      {
         targetViewCol = getRowCount() - 1;
         targetViewRow -= 1;
         if (targetViewRow < 0)
         {
            targetViewRow = getRowCount() - 1;
         }
      }
//System.out.println("JtableDec.changeSelection SWITCH LEFT"+ direction); 
       break;
      case Right:
         targetViewCol += 1;
      if (targetViewCol > getColumnCount() - 1)
      {
         targetViewCol = 0;
         targetViewRow += 1;
         if (targetViewRow > getRowCount() - 1) 
         {
             targetViewRow = 0;
         }
      }
//System.out.println("JtableDec.changeSelection SWITCH RIGHT"+ direction); 
      break;
   }
    targetModelRow = convertRowIndexToModel(targetViewRow);
    targetModelCol = convertColumnIndexToModel(targetViewCol);
 } //while
//LOG.fine(String.format("Trying to move selection to %d,%d instead!",targetViewRow, targetViewCol));
//System.out.println("JtableDec.changeSelection LAST");
} // if
else
{
     //System.out.println("         else "+this.isEditable +"     "+ !getModel().isCellEditable(targetModelRow, targetModelCol)+"     targetModelRow:"+targetModelRow+"   targetModelCol:"+targetModelCol);
}


 
super.changeSelection(targetViewRow, targetViewCol, toggle, expand);

        //System.out.println("JTableDec.changeSelection after super "+targetViewRow+" "+targetViewCol+"  toggle:"+toggle+"   expand:"+expand+"  isEditable:"+this.isEditable);

         // make edit text in next focusable text
            if(isCellEditable(targetViewRow,targetViewCol))
            {
                //System.out.println("JTableDec.changeSelection after super  ooo-ooo   isCellEditable:"+isCellEditable(targetViewRow,targetViewCol)+" row:"+targetViewRow+" col:"+targetViewCol);
        if (editCellAt(targetViewRow, targetViewCol)) // when target cell is editable, set focus
        {
            Component editor = getEditorComponent();
            editor.requestFocusInWindow();
//          ((JTextComponent)editor).selectAll();
        }
            }
            else
            {
                //System.out.println("JTableDec.changeSelection after super NOT EDITABLE  isCellEditable:"+isCellEditable(targetViewRow,targetViewCol)+" row:"+targetViewRow+" col:"+targetViewCol);
            }

        
        
} 
    


    
    
    /*   look at http://stackoverflow.com/questions/3013165/jtable-getting-a-cell-into-edit-mode-on-pressing-tab
    *  Place cell in edit mode when it 'gains focus'
    */
   //@Override
    public void changeSelectiona(int row, int column, boolean toggle, boolean extend)
    {
        
        super.changeSelection(row, column, toggle, extend);

        System.out.println("JTableDec.changeSelection          "+row+" "+column+"  "+toggle+" "+extend);

 
            if(isCellEditable(row,column)) 
            {
        if (editCellAt(row, column))
        {
                
            
            Component editor = getEditorComponent();
            editor.requestFocusInWindow();
//          ((JTextComponent)editor).selectAll();
        }
           }

    }  
    
  
        
    
    
    // http://www.coderanch.com/t/603372/GUI/java/Focus-JTable-cell
    /* @Override  
     public boolean editCellAt( int row, int col, EventObject e )  
     {  
        boolean result = super.editCellAt( row, col, e );  
  
        Component editComponent = getEditorComponent();  
        if (  editComponent == null ||   
            !(editComponent instanceof JTextComponent) )  
           return result;  
        if (e instanceof KeyEvent)  
           ((JTextComponent) editComponent).selectAll();  
  
        return result;  
     }*/ 
   
   
   // when is editable set
   public void setEntity(boolean isEditableIn)
   {
   	 isEditable=isEditableIn;
   	 
   	 /*if(!isEditable)
   	 {
   	 	this.addPropertyChangeListener("ancestor", AncestorPropertyChangeListener);
   	 }*/
   }
 
    /** Add stripes between cells and behind non-opaque cells. */
    public void paintComponent( java.awt.Graphics g )
    {
        if ( !(drawStripes = isOpaque( )) )
        {
            super.paintComponent( g );
            return;
        }
 
        // Paint zebra background stripes
        updateZebraColors( );
        final java.awt.Insets insets = getInsets( );
        final int w   = getWidth( )  - insets.left - insets.right;
        final int h   = getHeight( ) - insets.top  - insets.bottom;
        final int x   = insets.left;
        int y         = insets.top;
        int rowHeight = 16; // A default for empty tables
        final int nItems = getRowCount( );
        for ( int i = 0; i < nItems; i++, y+=rowHeight )
        {
            rowHeight = getRowHeight( i );
            g.setColor( rowColors[i&1] );
            g.fillRect( x, y, w, rowHeight );
        }
        // Use last row height for remainder of table area
        final int nRows = nItems + (insets.top + h - y) / rowHeight;
        for ( int i = nItems; i < nRows; i++, y+=rowHeight )
        {
            g.setColor( rowColors[i&1] );
            g.fillRect( x, y, w, rowHeight );
        }
        final int remainder = insets.top + h - y;
        if ( remainder > 0 )
        {
            g.setColor( rowColors[nRows&1] );
            g.fillRect( x, y, w, remainder );
        }
 
        // Paint component
        setOpaque( false );
        super.paintComponent( g );
        setOpaque( true );
    }
 
    /** Add background stripes behind rendered cells. */
    public java.awt.Component prepareRenderer(
        javax.swing.table.TableCellRenderer renderer, int row, int col )
    {
        final java.awt.Component c = super.prepareRenderer( renderer, row, col );
        if ( drawStripes && !isCellSelected( row, col ) )
            c.setBackground( rowColors[row&1] );
        return c;
    }
 
    /** Add background stripes behind edited cells. */
    public java.awt.Component prepareEditor(
        javax.swing.table.TableCellEditor editor, int row, int col )
    {
        final java.awt.Component c = super.prepareEditor( editor, row, col );
        if ( drawStripes && !isCellSelected( row, col ) )
            c.setBackground( rowColors[row&1] );
        return c;
    }
 
    /** Force the table to fill the viewport's height. */
    public boolean getScrollableTracksViewportHeight( )
    {
        final java.awt.Component p = getParent( );
        if ( !(p instanceof javax.swing.JViewport) )
            return false;
        return ((javax.swing.JViewport)p).getHeight() > getPreferredSize().height;
    }
 
    /** Compute zebra background stripe colors. */
    private void updateZebraColors( )
    {
        if ( (rowColors[0] = getBackground( )) == null )
        {
            rowColors[0] = rowColors[1] = java.awt.Color.white;
            return;
        }
        final java.awt.Color sel = getSelectionBackground( );
        if ( sel == null )
        {
            rowColors[1] = rowColors[0];
            return;
        }
        final float[] bgHSB = java.awt.Color.RGBtoHSB(
            rowColors[0].getRed( ), rowColors[0].getGreen( ),
            rowColors[0].getBlue( ), null );
        final float[] selHSB  = java.awt.Color.RGBtoHSB(
            sel.getRed( ), sel.getGreen( ), sel.getBlue( ), null );
        rowColors[0] = CLR_ROW_ODD;
        rowColors[1] = CLR_ROW_EVEN;/*java.awt.Color.getHSBColor(
            (selHSB[1]==0.0||selHSB[2]==0.0) ? bgHSB[0] : selHSB[0],
            0.1f * selHSB[1] + 0.9f * bgHSB[1],
            bgHSB[2] + ((bgHSB[2]<0.5f) ? 0.05f : -0.05f) );*/
    }
    
   /* private PropertyChangeListener createAncestorPropertyChangeListener() {
        return new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                // indicate that the parent of the JTable has changed.
                parentDidChange();
            }
        };
    }*/

   /* private void parentDidChange() {
        // if the parent of the table is an instance of JViewport, and that JViewport's parent is
        // a JScrollpane, then install the custom BugFixedViewportLayout.
        if (this.getParent() instanceof JViewport
                && this.getParent().getParent() instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) this.getParent().getParent();
            scrollPane.getViewport().setLayout(new BugFixedViewportLayout());
        }
    }*/    
    	
    /**
     * A modified ViewportLayout to fix the JFC bug where components that implement Scrollable do
     * not resize correctly, if their size is less than the viewport size.
     * This is a JDK1.2.2 bug (id 4310721). This used to work in Swing 1.0.3 and the fix is putting
     * the old logic back.
     * Copied from: http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4310721
     */
    /*private class BugFixedViewportLayout extends ViewportLayout {
        public void layoutContainer(Container parent) {
            // note that the original code (at the link supplied in the comment above) contained the
            // following call to super.layoutContainer(parent). this call caused the table to
            // continuously paint itself when the table did not fill the view. thus, i've commented
            // it out for now, as doing so seems to have no ill effects.
            // super.layoutContainer(parent);
          if(!isEditable)
          {
          	  super.layoutContainer(parent);
          }
            

            JViewport vp = (JViewport)parent;
            Component view = vp.getView();

            if(view == null) {
                return;
            }

            Point viewPosition = vp.getViewPosition();
            Dimension viewPrefSize = view.getPreferredSize();
            Dimension vpSize = vp.getSize();
            Dimension viewSize = new Dimension(viewPrefSize);

            if ((viewPosition.x == 0) && (vpSize.width > viewPrefSize.width)) {
                viewSize.width = vpSize.width;
            }

            if ((viewPosition.y == 0) && (vpSize.height > viewPrefSize.height)) {
                viewSize.height = vpSize.height;
            }

            if (!viewSize.equals(viewPrefSize)) {
                vp.setViewSize(viewSize);
            }
        }
    }    */	
    
}