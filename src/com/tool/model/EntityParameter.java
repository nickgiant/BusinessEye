package com.tool.model;

import com.tool.model.EntityPanel;
import com.tool.model.*;
import com.tool.guicomps.*;

public class EntityParameter
{
        public String name;
        public String sqlReadOnly;  // do NOT write 'order by' because is calculated
        public String sqlSelect;
        public String sqlFrom;
        public String sqlWhere;
        private String[] fieldsForSums;
        public EntityDBFields[] fields;
        //public String[] fieldsTranlsation;        
        public String caption;
        public String view;
        public String primKey;
        public String primKeyDb;
        //private String formGlobalField1; // for example when a typeid is rerquired to filter table records
        public EntityFilterSettings[] entityFilterSettings;
        public EntityGroupOfComps[] entityGroupOfComps;
        //public String[] searchCaption;
        //public String[] searchField;
        public String strOfOne;
        public String strOfMany;
        public EntityPanel[] entityPanel;
        public String[] categoryNodes;
        public String[] fieldsOnTitle;
        public String[] fieldsOnTitleCaption; 
        public int[] fieldOrderby;     
        public int intValidationColumn;
        public int intValidationType;
        public String yearEnforce;
        //private String formGlobalTableToGet1;
       // private String formGlobalField1; // for example when this field of form is used to filter othen fields or fields in a table
        //private String formGlobalTableToApply1;
        
        
        public EntityParameter(String nameIn, String sqlReadOnlyIn, String sqlSelectIn, String sqlFromIn, String sqlWhereIn,String[] fieldsForSumsIn, 
                          EntityDBFields[] fieldsIn, String captionIn, String viewIn ,String primKeyIn,
                          String primKeyDbIn,/* String formGlobalField1In,*/EntityFilterSettings[] entityFilterSettingsIn,EntityGroupOfComps[] entityGroupOfCompsIn,
                           String strOfOneIn,  String strOfManyIn, EntityPanel[] entityPanelIn,  String[] categoryNodesIn,String[]fieldsOnTitleIn,
                           String[]fieldsOnTitleCaptionIn, int[] fieldOrderbyIn,int intValidationColumnIn,int intValidationTypeIn,String yearEnforceIn/*,String formGlobalTableToGet1In,
                          String formGlobalTableToApply1In*/)
        {
        name=nameIn;
        sqlReadOnly=sqlReadOnlyIn;
        sqlSelect=sqlSelectIn;
        sqlFrom=sqlFromIn;
        sqlWhere=sqlWhereIn;
        fieldsForSums=fieldsForSumsIn;
        fields=fieldsIn;
        //fieldsTranlsation=fieldsTranlsationIn;
        caption=captionIn;
        view=viewIn;
        primKey=primKeyIn;
        primKeyDb=primKeyDbIn;
       // formGlobalField1=formGlobalField1In;
        entityFilterSettings=entityFilterSettingsIn;
        entityGroupOfComps = entityGroupOfCompsIn;
        //searchCaption=searchCaptionIn;
        //searchField=searchFieldIn;
        strOfOne=strOfOneIn;
        strOfMany=strOfManyIn;
        entityPanel=entityPanelIn;
        categoryNodes=categoryNodesIn;
          fieldsOnTitle=fieldsOnTitleIn;
          fieldsOnTitleCaption=fieldsOnTitleCaptionIn; 
          fieldOrderby=fieldOrderbyIn;  
          intValidationColumn=intValidationColumnIn;
          intValidationType=intValidationTypeIn;
          yearEnforce=yearEnforceIn;
         // formGlobalTableToGet1=formGlobalTableToGet1In;
          //formGlobalField1=formGlobalField1In;
         // formGlobalTableToApply1=formGlobalTableToApply1In; 
        }

        public String toString()
        {
            return caption;
        }


  public String getName()  {   return name;  }
  public String getSqlReadOnly()  {   return sqlReadOnly;  }
    public String getSqlSelect()  {   return sqlSelect;  }
  public String getSqlFrom()  {   return sqlFrom;  }
  public String getSqlWhere()  {   return sqlWhere;  }
public String[] getFieldsForSums()  {   return fieldsForSums;  }  
  public EntityDBFields[] getFields()  {   return fields;  }
  //public String[] getFieldsTranslation()  {   return fieldsTranlsation;  }
  public String getCaption()  {   return caption;  }
  public String getView()  {   return view;  }
  public String getPrimKey()  {   return primKey;  }  
  public String getPrimKeyDb()  {   return primKeyDb;  }    
 // public String getFormGlobalField1()  {   return formGlobalField1;  }    
  public EntityFilterSettings[] getEntityFilterSettings()  {   return entityFilterSettings;  }
  public EntityGroupOfComps[] getEntityGroupOfComps() { return entityGroupOfComps; }
  //public String[] getSearchField()  {   return searchField;  }
  public String getStrOfOne()  {   return strOfOne;  }
  public String getStrOfMany()  {   return strOfMany;  }
  public EntityPanel[] getEntityPanel()  {   return entityPanel;  }
  public String[] getCategoryNodes()  {   return categoryNodes;  }
  public String[] getFieldsOnTitle()  {   return fieldsOnTitle;  }
  public String[] getFieldsOnTitleCaption()  {   return fieldsOnTitleCaption;  } 
  public int[] getFieldsOrderby()  {   return fieldOrderby;  } 
  public int getIntValidationColumn()  {   return intValidationColumn;  }
  public int getIntValidationType()  {   return intValidationType;  }  
  public String getYearEnforce()  {   return yearEnforce;  }
  	
 //public String getFormGlobalTableToGet1()  {   return formGlobalTableToGet1;  } 
  //public String getFormGlobalField1()  {   return formGlobalField1;  } 
  //public String getFormGlobalTableToApply1()  {   return formGlobalTableToApply1;  } 
   /*     public void getStrOfMany(String nameIn);
        {
            for 
            return strOfMany;
        }*/
}

