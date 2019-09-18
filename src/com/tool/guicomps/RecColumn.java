  // created 22-03-2008
package com.tool.guicomps;  
  
    public class RecColumn
    {
        private String colDbName;
        private String colDbTable;
        private String colDbForeignTable;
        private int colDbType;// class
        
        private String colDbClassString;
        private String colCaption;  // does not belong to db table
        private int colDbLength;
        private int colDbLengthMaxActual;  // actual max length of string in report
        private boolean isColPK=false;  // call isAutoIncrement
        
        
        // to be deleted, check errors and delete, (does not have colDbClass)// for jtable
        /*public RecColumn(String colDbNameIn, String colDbTableIn, String colDbForeignTableIn, int colDbTypeIn, String colCaptionIn,int colDbLengthIn)
        {
          colDbName = colDbNameIn;
          colDbTable = colDbTableIn;
          colDbForeignTable = colDbForeignTableIn;
          colDbType = colDbTypeIn;
          //colDbClassString = colDbClassStringIn;
          colCaption = colCaptionIn;
          colDbLength=colDbLengthIn;
        } */       
        
        
        // for jtable
        public RecColumn(String colDbNameIn, String colDbTableIn, String colDbForeignTableIn, int colDbTypeIn,String colDbClassStringIn, String colCaptionIn,int colDbLengthIn)
        {
          colDbName = colDbNameIn;
          colDbTable = colDbTableIn;
          colDbForeignTable = colDbForeignTableIn;
          colDbType = colDbTypeIn;
          colDbClassString = colDbClassStringIn;
          colCaption = colCaptionIn;
          colDbLength=colDbLengthIn;
        }
        // for jtable
        public RecColumn(String colDbNameIn, String colDbTableIn, String colDbForeignTableIn,String colCaptionIn,int colDbLengthIn)
        {
          colDbName = colDbNameIn;
          colDbTable = colDbTableIn;
          colDbForeignTable = colDbForeignTableIn;
          colCaption = colCaptionIn;
          colDbLength=colDbLengthIn;
        }
        
        // for reports and table preferences
        public RecColumn(String colDbNameIn,String colCaptionIn,int colDbTypeIn,String colDbTableIn,String colDbClassStringIn,int colDbLengthIn, int colDbLengthMaxActualIn, boolean isColPKIn)
        {
          colDbName = colDbNameIn;
          colDbType = colDbTypeIn;
          colDbClassString = colDbClassStringIn;
          colCaption = colCaptionIn;
          colDbTable=colDbTableIn;
          colDbLength=colDbLengthIn;
          colDbLengthMaxActual=colDbLengthMaxActualIn;
          isColPK=isColPKIn;
        }        
        
        public String toString()
        {    return colDbName;       }
        
        public String getColumnName()
        {  
           return colDbName; 
        }
  
        public String getColumnCaption()
        {  
           return colCaption; 
        }
        
        public String getColumnTable()
        {  
           return colDbTable; 
        }

        public String getColumnForeignTable()
        {  
        //System.out.println("colDbForeignTable "+colDbForeignTable);
           return colDbForeignTable; 
        }
        
        public int getColumnType()
        {  
           return colDbType; 
        }
        
        public String getColumnClass()
        {  
            //System.out.println("RecColumn.getColumnClass =="+colDbClassString);
           return colDbClassString; 
        }        
        
        // classes like // "java.lang.String", "java.sql.Date" || ("java.lang.Date"), "java.lang.Boolean","java.lang.Integer", "java.lang.Double" || ("java.math.BigDecimal" || "java.lang.Long"), 
        /*public String getColClassString()
        {  
           return colDbClassString; 
        }*/
        
        
        
        public int getColumnLength()
        {  
           return colDbLength; 
        }
 
        public int getColumnLengthMaxActual()
        {  
           return colDbLengthMaxActual; 
        }
  
        public boolean getColumnIsPK()
        {  
           return isColPK; 
        }
        
  
        
        public void setColumnLengthMaxActual(int length)
        {
        	colDbLengthMaxActual = length;
        }
        
    }
