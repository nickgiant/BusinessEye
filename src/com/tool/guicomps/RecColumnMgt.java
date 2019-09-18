// created 22-03-2008
package com.tool.guicomps;

import java.util.ArrayList;

public class RecColumnMgt
{

   //RecColumn recCol;
   ArrayList cols;	
	
   public RecColumnMgt()
   {
        cols = new ArrayList();
       // addEntities();
   }

  /* public void addEntities()
   { 
   
       EntityData entityData = new EntityData();
       entityData.addEntities(entities);
   }*/

   public int getColumnLength(int c)
   {  
       
       RecColumn col = (RecColumn)cols.get(c);
       return col.getColumnLength();
   }
  
   public String getColumnName(int c)
   {  
       
       RecColumn col = (RecColumn)cols.get(c);
       return col.getColumnName();
   }
 
    public String getColumnCaption(int c)// column real name
   {  
       
       RecColumn col = (RecColumn)cols.get(c);
       return col.getColumnCaption();
   }
   
   public String getColumnTable(int c)
   {  
       RecColumn col = (RecColumn)cols.get(c); 
       return col.getColumnTable();
   }

   public String getColumnForeignTable(int c)
   {  
       RecColumn col = (RecColumn)cols.get(c); 
       return col.getColumnForeignTable();
   }

   public int getColumnType(int c)
   {  
       RecColumn col = (RecColumn)cols.get(c);
       return col.getColumnType();
   }
  
   public String getColumnClass(int c)
   {  
       RecColumn col = (RecColumn)cols.get(c);
       return col.getColumnClass();
   }    
   
   public static void main(String[] args)
  {  //test
     RecColumnMgt mgt = new RecColumnMgt();
    // System.out.println("");
     //mgt.getColumnLength()
    
  }
   
}