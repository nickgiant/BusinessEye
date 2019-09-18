// created 22-10-2009

package com.tool.model;

import com.tool.gui.*;
import com.tool.guicomps.*;

    public class EntityScoreBoard
    {
    	private String title;
          private EntityDockableGraph[] entityDockableGraph;
        private EntityFilterSettings[] entityFilterSettings;
        private EntityGroupOfComps[] entityGroupOfComps;
        private String yearEnforce;
    
        public EntityScoreBoard(String titleIn, EntityDockableGraph[] entityDockableGraphIn, EntityFilterSettings[] entityFilterSettingsIn, 
                EntityGroupOfComps[] entityGroupOfCompsIn, String yearEnforceIn)
        {
          title=titleIn;
          entityDockableGraph=entityDockableGraphIn;
          entityFilterSettings =  entityFilterSettingsIn;
          entityGroupOfComps =entityGroupOfCompsIn;
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
            	ret = title;
         /*   	//ret="<html><b>"+caption+"</b></html>";
            	
            }*/
          return ret;
        }

        public String getTitle()  {   return title;  }
        public EntityDockableGraph[] getEntityDockableGraph()  {   return entityDockableGraph;  }
        public EntityFilterSettings[] getEntityFilterSettings()  {   return entityFilterSettings;  }
        public EntityGroupOfComps[] getEntityGroupOfComps()  {   return entityGroupOfComps;}
        public String getYearEnforce()  {   return yearEnforce;  }
        
     }      