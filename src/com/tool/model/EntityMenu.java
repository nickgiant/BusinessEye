// created 17-4-2009
package com.tool.model;


import com.tool.gui.*;
import com.tool.guicomps.*;
import com.tool.utils.DataTree;


import javax.swing.*;

    public class EntityMenu implements Constants
    {
      
      private int entityType;
      
      private EntityInfo entityInfo; // info,report,parameter,statistic, task
      //private EntityMenu[] entityMenuMany;// for many parameters or other many panels
      private EntityParameter entityParameter;
      private EntityManyDataManyRec entityManyDataManyRec;// for many parameters or other many panels
      private EntityReport entityReport;
      private EntityStatistics[] entityStatistics;
      private EntityTask entityTask;
      private EntityTool entityTool;
      private EntityScoreBoard entityScoreBoard;
      
      private String entityCategory;
      private int categoryLevel;
      private DataTree dTree;
      
      private ImageIcon icon;
      private String caption;
      	/*
    public static final int ENTITY_TYPE_DATAENTRY = 1;
    public static final int ENTITY_TYPE_DATAMANY_PARAMETERS = 2;
    public static final int ENTITY_TYPE_REPORT = 3;
    public static final int ENTITY_TYPE_PARAMETER = 4;
    public static final int ENTITY_TYPE_STATISTICS = 5;
    public static final int ENTITY_TYPE_TASK = 6;
    public static final int ENTITY_TYPE_DOCKABLEGRAPH = 7;
    public static final int ENTITY_TYPE_TOOLS = 8;
    //public static final int ENTITY_TYPE_CATEGORY = 10;
    public static final int ENTITY_TYPE_CATEGORY1 = 11;//reports
    public static final int ENTITY_TYPE_CATEGORY2 = 12; //reports
    public static final int ENTITY_TYPE_CATEGORY3 = 13;//reports
      
      public static final int ENTITY_TYPE_SECTION = 14;
      	 **/
     public EntityMenu()
     {
        
     }
    
    public void setEntityType(int entityTypeIn)
    {
       entityType=entityTypeIn;
    }
      
    public void setEntityInfo(EntityInfo entityInfoIn, ImageIcon iconIn)
    {
    	
    	entityInfo=entityInfoIn;
    	caption=entityInfo.getCaption();
    	icon=iconIn;
    }

    // for many parameters or other many panels
    public void setEntityManyDataManyRec(EntityManyDataManyRec entityManyDataManyRecIn, ImageIcon iconIn)
    {
    	entityManyDataManyRec = entityManyDataManyRecIn;
    	
    	caption=entityManyDataManyRecIn.caption;
    	icon=iconIn;
    }    
    
    
    public void setEntityParameter(EntityParameter entityParameterIn, ImageIcon iconIn)
    {
    	
    	entityParameter=entityParameterIn;
    	caption=entityParameter.caption;
    	icon=iconIn;
    }


    public void setEntityReport(EntityReport entityReportIn, ImageIcon iconIn)
    {
    	
    	entityReport=entityReportIn;
    	caption=entityReport.caption;
    	icon=iconIn;
    }

    public void setEntityStatistics(EntityStatistics[] entityStatisticsIn, ImageIcon iconIn)
    {
    	
    	entityStatistics=entityStatisticsIn;
    	caption=entityStatistics[0].caption;
    	icon=iconIn;
    }

    public void setEntityTask(EntityTask entityTaskIn, ImageIcon iconIn)
    {
    	entityTask=entityTaskIn;
    	caption=entityTask.caption;
    	icon=iconIn;
    }

    
    public void setEntityTool(EntityTool entityToolIn, ImageIcon iconIn)
    {
    	entityTool=entityToolIn;
    	caption=entityTool.getCaption();
    	icon=iconIn;
    }    
    
    
    public void setEntityScoreBoard(EntityScoreBoard entityScoreBoardIn, ImageIcon iconIn)
    {
    	entityScoreBoard=entityScoreBoardIn;
    	caption=entityScoreBoard.getTitle();
    	icon=iconIn;
    }
    

    public void setEntityCategory(String captionIn, int categoryLevelIn, ImageIcon icoIn)
    {
    	
    	caption=captionIn;
    	categoryLevel=categoryLevelIn;
    	icon=icoIn;
    }    
      public void setEntitySection(String captionIn, DataTree dTreeIn,/*int categoryLevelIn,*/ ImageIcon icoIn)
    {
    	
    	caption=captionIn;
        dTree = dTreeIn;
    	//categoryLevel=categoryLevelIn;
    	icon=icoIn;
    }    
      
      
    public  int getEntityType()  { return  entityType;  }
    
    public  EntityInfo getEntityInfo()  { return  entityInfo;  }
    
    public  EntityManyDataManyRec getEntityManyDataManyRec() {return entityManyDataManyRec;}// for many parameters or other many panels
   
    
    public  EntityParameter getEntityParameter()  { return  entityParameter;  }
    public  EntityReport getEntityReport()  { return  entityReport;  }
    public  EntityStatistics[] getEntityStatistics()  { return  entityStatistics;  }
    public  EntityTask getEntityTask()  { return  entityTask;  }
    public  EntityScoreBoard getEntityScoreBoard()  { return  entityScoreBoard;  }
    
    public  String getEntityCategory()  { return  entityCategory;  }
    public  String getEntityCaption()  { return  caption;  }
        public  DataTree getEntitySectionDataTree()  { return  dTree;  }
        public  EntityTool getEntityTool()  { return  entityTool;  }
    
    
    public  ImageIcon getEntityIcon()  { return  icon;  }
      public String toString()
      {
      	return caption;
      }            	
    }