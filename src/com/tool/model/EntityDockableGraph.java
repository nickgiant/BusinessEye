// created 9-10-2009
package com.tool.model;

import com.tool.gui.*;
import com.tool.guicomps.*;

    public class EntityDockableGraph
    {
          private String title;
          private int graphType;
          private String queryMaster;
          private String queryDetail;
          //panelDockablepanelDockableIn
          private int left;
          private int top;
          private int right;
          private int bottom;

        
    
        public EntityDockableGraph(String titleIn, int graphTypeIn, String queryMasterIn, String queryDetailIn, int leftIn,int topIn, int rightIn, int bottomIn)
        {
          title=titleIn;
          graphType=graphTypeIn;
          queryMaster=queryMasterIn;
          queryDetail=queryDetailIn;
          //panelDockablepanelDockableIn
          left=leftIn;
          top=topIn;
          right=rightIn;
          bottom=bottomIn;

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
        public int getGraphType()  {   return graphType;  }
        public String getQueryMaster()  {   return queryMaster;  }        
        public String getQueryDetail()  {   return queryDetail;  }   
        public int getLeft()  {   return left;  }
        public int getTop()  {   return top;  }
        public int getRight()  {   return right;  }
        public int getBottom()  {   return bottom;  }

        
     }      