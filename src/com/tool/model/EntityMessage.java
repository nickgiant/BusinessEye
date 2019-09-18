// created 15-10-2009
package com.tool.model;

public class EntityMessage
{
        public String message;
        public int type; 
        public int tableRow;
        public int  column;
        private String strJtable;

      public EntityMessage()
      {}


        public EntityMessage(String messageIn,int typeIn,int tableRowIn,int columnIn, String strJtableIn)//, String strJtableIn)
        {
            message=messageIn;
            type=typeIn;
            tableRow=tableRowIn;
            column=columnIn;
            strJtable= strJtableIn;
        }

        public String toString()
        {
            return message;
        }
        
        public String getMessage()
        {
            return message;
        }

        public int getType()
        {
           return type;
        }

        public int getTableRow()
        {
           return tableRow;
        }
        
        public int getColumn()
        {
        return column;
        }
        
        public String getStrJtable()
        {
        return strJtable;
        }
        
}
