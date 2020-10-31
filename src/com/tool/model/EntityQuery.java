package com.tool.model;

import com.tool.gui.*;


    public class EntityQuery
    {
        private String query;
        //private boolean isUpdate;
        private int type; // QUERY_READ, QUERY_UPDATE, QUERY_UPDATE_STOREDPROCEDURE
        private String primKeyName;
        private String primKeyTable;
        private String tableForFilters; // if different than EntityFilterSettings
        private String messageSuccess;
        private String messageFailure;

    
        public EntityQuery(String queryIn, int typeIn,String primKeyNameIn,String primKeyTableIn,
        String tableForFiltersIn,String messageSuccessIn, String messageFailureIn)
        {
         query=queryIn;
         //isUpdate=isUpdateIn;
         type=typeIn;  
         primKeyName=primKeyNameIn;
         primKeyTable=primKeyTableIn;
         tableForFilters=tableForFiltersIn;
         messageSuccess=messageSuccessIn;
         messageFailure=messageFailureIn;      	
        }   
         
        public String toString()
        {
        	String ret="";
        /*	if(subTitle!=null && !subTitle.equalsIgnoreCase(""))
        	{
               ret= caption+", "+subTitle;
               //ret="<html><b>"+caption+"</b>, "+subTitle+"</html>";
               // ret="<html><b>"+caption+"</b><br>"+subTitle+"</html>";

            }
            else
            {*/
            	ret = query;
         /*   	//ret="<html><b>"+caption+"</b></html>";
            	
            }*/
          return ret;
        }

        public void setQuery(String queryIn)
        {  query=queryIn;  }

        public String getQuery()  {   return query;  }
        //public boolean getIsUpdate()  {   return isUpdate;  }
        public int getType()  {   return type;  }        
        public String getPrimKeyName()  {   return primKeyName;  }          
        public String getPrimKeyTable()  {   return primKeyTable;  }  
        public String getTableForFilters()  {   return tableForFilters   ;  }  
        public String getMessageSuccess()  {   return messageSuccess;  }  
        public String getMessageFailure()  {   return messageFailure;  }  
        
     }    