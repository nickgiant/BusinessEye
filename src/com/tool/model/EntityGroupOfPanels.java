//created 19-09-2009

package com.tool.model;


public class EntityGroupOfPanels
{
     
     private String caption;
     private int columnsOfObjects;// no of panels inside a row 
     //private int includedGroupOfComps;

    
      public EntityGroupOfPanels()
      {}
      
      public EntityGroupOfPanels(String captionIn, int columnsOfObjectsIn)
      {
      	
      caption=captionIn;	
      columnsOfObjects=columnsOfObjectsIn;
      //includedGroupOfComps = includedGroupOfCompsIn;
          
      }

        public String getCaption()     {     return caption;        }
        public int getColumnsOfObjects()     {     return columnsOfObjects;        }
        //public int[] getIncludedGroupOfComps()     {     return includedGroupOfComps;        }
      
}