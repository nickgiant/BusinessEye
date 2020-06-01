
package com.tool.model;

import com.tool.guicomps.Constants;


public class EntityGroupOfComps implements Constants
{
     

     private String caption;
     private int columnsOfObjects;
     private int includedInGroupOfPanels;
     private int compsFontSize = FONT_SIZE_NOT_SET;
     private int panelVisible;// 
    
      public EntityGroupOfComps()
      {}
      
      public EntityGroupOfComps(String captionIn, int columnsOfObjectsIn, int includedInGroupOfPanelsIn, int compsFontSizeIn, int panelVisibleIn)
      {
      	
      caption=captionIn;	
      columnsOfObjects=columnsOfObjectsIn;
      includedInGroupOfPanels=includedInGroupOfPanelsIn;
      compsFontSize = compsFontSizeIn;
      panelVisible = panelVisibleIn;
      }

        public String getCaption()     {     return caption;        }
        public int getColumnsOfObjects()     {     return columnsOfObjects;        }
        public int getIncludedInGroupOfPanels()     {     return includedInGroupOfPanels;        }
         public int getCompsFontSize()     {     return compsFontSize;        }
         public int getPanelVisible() {return panelVisible;}
      
}