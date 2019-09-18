// created 6-5-2009
package com.tool.model;

import com.tool.gui.*;
import com.tool.guicomps.*;

    public class EntityTask
    {
        public String name;
        public String caption;
        public String subTitle;
        public String [] calculationType;
        public EntityFilterSettings [] entityFilterSettings;
        public EntityQuery[] entityQuery;
        public boolean isNullify; //is make everything null
        public String tableForFilters; // if different than EntityFilterSettings
        public EntityGroupOfComps[] entityGroupOfComps;
        public String yearEnforce;
        
    
        public EntityTask(String nameIn,String captionIn,String subTitleIn, String[] calculationTypeIn,
        EntityFilterSettings[] entityFilterSettingsIn,EntityGroupOfComps[] entityGroupOfCompsIn,
        EntityQuery[] entityQueryIn, boolean isNullifyIn, String tableForFiltersIn,String yearEnforceIn)
        {
         name=nameIn;
         caption=captionIn;
         subTitle=subTitleIn;  
         calculationType=calculationTypeIn;
         entityFilterSettings=entityFilterSettingsIn;
         entityGroupOfComps=entityGroupOfCompsIn;
         entityQuery= entityQueryIn;
         isNullify=isNullifyIn;
         tableForFilters=tableForFiltersIn;
         yearEnforce=yearEnforceIn;
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
            	ret = caption;
         /*   	//ret="<html><b>"+caption+"</b></html>";
            	
            }*/
          return ret;
        }

        public String getName()  {   return name;  }
        public String getCaption()  {   return caption;  }
        public String getSubTitle()  {   return subTitle;  }        
        public String[] getCalculationType()  {   return calculationType;  }  
        public EntityFilterSettings[] getEntityFilterSettings()  {   return entityFilterSettings;  }
        public EntityGroupOfComps[]  getEntityGroupOfComps()  {   return entityGroupOfComps;  }
        public EntityQuery[] getEntityQuery()  {   return entityQuery;  }  
        public boolean getIsNullify()  {   return isNullify;  }
        public String getTableForFilters()  {   return tableForFilters;  }
        public String getYearEnforce()  {   return yearEnforce;  }
        
     }      