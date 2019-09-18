package com.tool.model;

//import javax.swing.tree.DefaultMutableTreeNode;
//import com.tool.model.*;
//import com.tool.guicomps.*;

    public class EntityInfo
    {
        private String name;
        private String sqlReadOnly;
        private String sqlSelect;
        private String sqlFrom;
        private String sqlWhere;
        private String sqlOne;
        private String[] fieldsForSums;
        //private String name2;
        //private String sqlMany;
//        private EntityDBFields[] fields;
        
        //private EntityDBFields[] fieldsMany;
          
        //private String[] fieldsManyOnInsert;
        //private String[] fieldsManyTranslationOnInsert;       
        //private String notShowFieldsFromThisInQuery2; // in editable jtable not show fields coming from this table
        //private String queryManyReadOnly;
        //private boolean isMasterUnique; // for example a farmer can only have one row in delivery
        //private String[] sqlManyWhereField;
        //private String[] sqlManyWhereValue;
        private String caption;
        private String view;
        private String entityHeader;// ie farmer in delivery
        private String primKey;
        private String primKeyDb;//primary key as in db
        //private String formGlobalTableToGet1;
       // private String formGlobalField1; // for example when this field of form is used to filter othen fields or fields in a table
       // private String formGlobalTableToApply1;
        //private String[] primKeyMany;
        //private String[] primKeyManyTran;// translation
        private EntityFilterSettings[] entityFilterSettings;
        private EntityGroupOfComps[] entityGroupOfComps;
        //public String[] searchCaption;
        //public String[] searchField;
        private String strOfOne;
        //private String strOfOne2;
        private String strOfMany;
        //private String strOfMany2;
        
        private String[] categoryNodes;
        private EntityPanel[] entityPanel;
        private String[] fieldsOnTitle; // used here and in EntityPanel. EntityPanel because we want to be shown in drill.
        private String[] fieldsOnTitleCaption; // used here and in EntityPanel. EntityPanel because we want to be shown in drill.
        private int[] fieldsOrderby;
        private int intValidationColumn;
        private int intValidationType;
        private String yearEnforce;
        private EntityReport entityReport; // report of document, in farmers vat I have no report focuments
      //  private boolean hasTemplates =false;        // HAS_TEMPLATES =true;   HAS_NOT_TEMPLATES =false; // panel templates, like protupa
        //private EntityUpdateAdditional[] updateAdditional;
        
        //public int main;
        //public char type; // t=task, p=person

        
        /*public  EntityInfo(String nameIn, String sqlReadOnlyIn, String sqlSelectIn,String sqlFromIn,String sqlWhereIn ,String sqlOneIn,String[] fieldsForSumsIn, String name2In,
                String sqlManyIn,EntityDBFields[] fieldsIn, EntityDBFields[] fieldsManyIn, String[] fieldsManyOnInsertIn, String[] fieldsManyTranslationOnInsertIn, 
                String notShowFieldsFromThisInQuery2In,String queryManyReadOnlyIn, boolean isMasterUniqueIn, String[] sqlManyWhereFieldIn,String[] sqlManyWhereValueIn,
                String captionIn, String viewIn,String entityHeaderIn,String primKeyIn,String primKeyDbIn, String[] primKeyManyIn,String[]primKeyManyTranIn, 
                EntityFilterSettings[] entityFilterSettingsIn, EntityGroupOfComps[] entityGroupOfCompsIn,String strOfOneIn, String strOfManyIn, String strOfOneIn2, 
                String strOfManyIn2, String[] categoryNodesIn, EntityPanel[] entityPanelIn, int[]fieldsOnTitleIn, String[]fieldsOnTitleCaptionIn, int[] fieldsOrderbyIn, 
                int intValidationColumnIn,int intValidationTypeIn, String yearEnforceIn)
        */        
        
        public  EntityInfo(String nameIn, String sqlReadOnlyIn, String sqlSelectIn,String sqlFromIn,String sqlWhereIn ,String sqlOneIn,String[] fieldsForSumsIn, 
                /*EntityDBFields[] fieldsIn,*/ String captionIn, String viewIn,String entityHeaderIn,String primKeyIn,String primKeyDbIn,/*String formGlobalTableToGet1In, 
                /*String formGlobalField1In,*//*String formGlobalTableToApply1In,*///String[] primKeyManyIn,String[]primKeyManyTranIn, 
                EntityFilterSettings[] entityFilterSettingsIn, EntityGroupOfComps[] entityGroupOfCompsIn,String strOfOneIn, String strOfManyIn, //String strOfOneIn2, String strOfManyIn2,
                String[] categoryNodesIn, EntityPanel[] entityPanelIn, String[]fieldsOnTitleIn, String[]fieldsOnTitleCaptionIn, int[] fieldsOrderbyIn, 
                int intValidationColumnIn,int intValidationTypeIn,EntityReport entityReportIn, String yearEnforceIn)//, boolean hasTemplatesIn)//,EntityUpdateAdditional[] updateAdditionalIn)
        {
          name = nameIn;
          sqlReadOnly = sqlReadOnlyIn;
          sqlSelect=sqlSelectIn;
          sqlFrom=sqlFromIn;
          sqlWhere=sqlWhereIn;
          sqlOne=sqlOneIn;
          fieldsForSums=fieldsForSumsIn;
         // name2 = name2In;
          caption = captionIn;
          //sqlMany=sqlManyIn;
//          fields=fieldsIn;
          
          //fieldsMany=fieldsManyIn;
          
          /*fieldsManyOnInsert=fieldsManyOnInsertIn;
          fieldsManyTranslationOnInsert=fieldsManyTranslationOnInsertIn;
          notShowFieldsFromThisInQuery2=notShowFieldsFromThisInQuery2In;
          queryManyReadOnly=queryManyReadOnlyIn;
          isMasterUnique=isMasterUniqueIn;
          sqlManyWhereField=sqlManyWhereFieldIn;
          sqlManyWhereValue=sqlManyWhereValueIn;*/
          view = viewIn;
          entityHeader=entityHeaderIn;
          primKey=primKeyIn;
          primKeyDb=primKeyDbIn;
          //formGlobalTableToGet1=formGlobalTableToGet1In;
          //formGlobalField1=formGlobalField1In;
          //formGlobalTableToApply1=formGlobalTableToApply1In;          
          //primKeyMany=primKeyManyIn;
          //primKeyManyTran=primKeyManyTranIn;
          entityFilterSettings= entityFilterSettingsIn;
          entityGroupOfComps = entityGroupOfCompsIn;
          strOfOne=strOfOneIn;
         // strOfOne2=strOfOneIn2;
          strOfMany=strOfManyIn;
         // strOfMany2=strOfManyIn2;
          categoryNodes = categoryNodesIn;
          entityPanel=entityPanelIn;
          fieldsOnTitle=fieldsOnTitleIn;
          fieldsOnTitleCaption=fieldsOnTitleCaptionIn;
          fieldsOrderby=fieldsOrderbyIn;
          intValidationColumn=intValidationColumnIn;
          intValidationType=intValidationTypeIn;
          entityReport=entityReportIn;
          yearEnforce=yearEnforceIn;
         // hasTemplates = hasTemplatesIn;
          //System.out.println("EntityInfo sqlOne"+sqlOne);
         
       
        }

        public String toString()
        {
            return caption;
        }


  public String getName()  {   return name;  }
  public String getSqlReadOnly()  {   return sqlReadOnly;  }
  public String getCaption()  {   return caption;  }
  public String getsqlOne()  {   return sqlOne;  }
  public String[] getFieldsForSums()  {   return fieldsForSums;  }
  //public String getName2()  {   return name2;  }
  //public String getsqlMany()  {   return sqlMany;  }
//  public EntityDBFields[] getFields()  {   return fields;  }
  
  /*public EntityDBFields[] getFieldsMany()  {   return fieldsMany;  }
  
  public String[] getFieldsManyOnInsert()  {   return fieldsManyOnInsert;  }
  public String[] getFieldsManyTranslationOnInsert()  {   return fieldsManyTranslationOnInsert;  }
  public String getNotShowFieldsFromThisInQuery2()  { return  notShowFieldsFromThisInQuery2;  }
  public String getQueryManyReadOnly()  { return  queryManyReadOnly;  }
  public boolean getIsMasterUnique()  {   return isMasterUnique;  }
  public String[] getsqlManyWhereField()  {   return sqlManyWhereField;  }
  public String[] getsqlManyWhereValue()  {   return sqlManyWhereValue;  }*/
  public String getEntityHeader()  { return  entityHeader;  }
  public String getPrimKey()  {   return primKey;  }
  public String getPrimKeyDb()  {   return primKeyDb;  }  
  //public String getFormGlobalTableToGet1()  {   return formGlobalTableToGet1;  } 
  //public String getFormGlobalField1()  {   return formGlobalField1;  } 
  //public String getFormGlobalTableToApply1()  {   return formGlobalTableToApply1;  } 
  //public String[] getPrimKeyMany()  {   return primKeyMany;  }
  //public String[] getPrimKeyManyTran()  {   return primKeyManyTran;  }
  public EntityFilterSettings[]  getEntityFilterSettings()  {   return entityFilterSettings;  }
  public EntityGroupOfComps[] getEntityGroupOfComps() { return entityGroupOfComps; }
  public String getSqlFrom()  {   return sqlFrom;  }
  public String getSqlSelect()  {   return sqlSelect;  }
  public String getSqlWhere()  {   return sqlWhere;  }
  public String getStrOfMany()  {   return strOfMany;  }
  //public String getStrOfMany2()  {   return strOfMany2;  }
  public String getStrOfOne()  {   return strOfOne;  }
  //public String getStrOfOne2()  {   return strOfOne2;  }
  public String getView()  {   return view;  }
  public String[] getCategoryNodes()  {   return categoryNodes;  }
  public EntityPanel[] getEntityPanel()  {   return entityPanel;  }
  public String[] getFieldsOnTitle()  {   return fieldsOnTitle;  }
  public String[] getFieldsOnTitleCaption()  {   return fieldsOnTitleCaption;  }
  public int[] getFieldsOrderby()  {   return fieldsOrderby;  }
  public int getIntValidationColumn()  {   return intValidationColumn;  }
  public int getIntValidationType()  {   return intValidationType;  }
  public EntityReport getEntityReport()  {   return entityReport;  }
  public String getYearEnforce()  {   return yearEnforce;  }
 //public boolean getHasTemplates()  {   return hasTemplates;  }
  
  
  

    }
