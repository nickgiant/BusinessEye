
package com.tool.model;

import javax.swing.ImageIcon;
import com.tool.model.*;
import com.tool.guicomps.*;

public class EntityPanel
{
     private String type;// ODOR, TDOR, STATS
     private String name;
     private String entity;
     private EntityDBFields[] fields;
     private EntityDBFields[] fieldsMany;
     private EntityGroupOfComps[] entityGroupOfComps;
     private EntityGroupOfPanels[] entityGroupOfPanels;
     private String[] fieldsForSumsMany;
     //private String[] fieldsManyOnInsert;
     //private String[] fieldsManyTranslationOnInsert;
     private String entityMany;
     //private String[] sql2WhereField;
     //private String[] sql2WhereValue;
     //private String[] primKeysMany;
     //private String[] primKeysManyTran;
     private String sqlMany;
     private String primKey;// not  make it array, use dbFields
     private String primKeyValue;// not  make it array, use dbFields
     private String primKeyDb;// not  make it array, use dbFields
     private String query;
     private String sqlOne;;
     private String title;
     private ImageIcon ico;
     private boolean isNewRec;
     private String strOfMany2;
     private boolean showTitle;
     private boolean showToolBar;
     private String queryManyReadOnly;
     //private boolean isMasterUnique;
     private boolean isMany;
     private boolean showOnlySaveNPrintButton;
     private String parentTitle;
     private String querySelect;
     private String queryFrom;
     private String queryWhere;
     private String queryGroupBy;
     private String queryOrderBy;
     private boolean isFilterCompany;
     private String fieldCompanyIdName;
     private boolean isFilterYear;
     private String fieldYearName;
     private int extsumcalcsIntTableActionValueField;
     private String extsumcalcsTablePercentage;
     private String extsumcalcsTablePercentageKey;
     private String extsumcalcsTableCategory;
     private String extsumcalcsTableCategoryKey;
     private int extsumcalcsIntTableCategoryField;
     //private String yearEnforceInPanelOne;
     //private String yearEnforceInLines;
       private EntityUpdateAdditional[] updateAdditional;
    private EntityPanel[] drillEntityPanel;
       private String[] fieldsOnTitle; // used here and in EntityInfo. Here because we want to be shown in drill
        private String[] fieldsOnTitleCaption;    // used here and in EntityInfo. Here because we want to be shown in drill
       private String[] fieldsUnique; // like  farmerAFM in farmer, and farmerId + deliveryId in applicationheader,  If null do not show button.
       private int uniqueWhileDataentry;
       private boolean uniqueWhileDataentryEditable;  //UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_NO = 0  public static final int UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES = 1
      private int uniqueBeforeSave;
      private boolean uniqueBeforeSaveEditable;// UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_NO = 0;     public static final int UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES = 1;
       private EntityReport entityReportForm;
       private EntityCalculate entityCalculate;
       private EntityCheckFields[] entityCheckFields; // for example check if VAT ofcurrent company is the same as VAT of customer, then inform
       private EntityTemplate entityTemplate;
       
      public EntityPanel()
      {}
      
      // panelOneDataOneRec
      public EntityPanel(String typeIn, String entityIn,EntityDBFields[] fieldsIn,EntityGroupOfComps[] entityGroupOfCompsIn,
      EntityGroupOfPanels[] entityGroupOfPanelsIn,String primKeyIn, String primKeyValueIn,String primKeyDbIn,String queryIn,
      String titleIn, ImageIcon icoIn, boolean isNewRecIn, boolean showToolBarIn,String[] fieldsUniqueIn,int uniqueWhileDataentryIn,boolean uniqueWhileDataentryEditableIn,
      int uniqueBeforeSaveIn,boolean uniqueBeforeSaveEditableIn,boolean isManyIn, boolean showOnlySaveNPrintButtonIn,EntityUpdateAdditional[] updateAdditionalIn,
      EntityReport entityReportFormIn, EntityCalculate entityCalculateIn, //String yearEnforceInPanelOneIn, String yearEnforceInLinesIn,
      EntityCheckFields[] entityCheckFieldsIn, EntityTemplate entityTemplateIn)
      {
      	
      type=typeIn;	
      entity=entityIn;
      fields=fieldsIn;
      entityGroupOfComps = entityGroupOfCompsIn;
      entityGroupOfPanels = entityGroupOfPanelsIn;      
      //fieldsTranslation=fieldsTranslationIn;

      primKey=primKeyIn;
      primKeyValue=primKeyValueIn;
      primKeyDb=primKeyDbIn;
      query=queryIn;
      title=titleIn;
      ico=icoIn;
      isNewRec=isNewRecIn;
      showToolBar=showToolBarIn;
      fieldsUnique=  fieldsUniqueIn;
       uniqueWhileDataentry = uniqueWhileDataentryIn;
       uniqueWhileDataentryEditable=uniqueWhileDataentryEditableIn;
       uniqueBeforeSave=uniqueBeforeSaveIn;
       uniqueBeforeSaveEditable = uniqueBeforeSaveEditableIn;      
      
      
      //isMasterUnique=isMasterUniqueIn;
      isMany=isManyIn;
      showOnlySaveNPrintButton=showOnlySaveNPrintButtonIn;      
      updateAdditional=updateAdditionalIn;
      
      entityReportForm = entityReportFormIn;
      
      entityCalculate = entityCalculateIn;
     //yearEnforceInPanelOne = yearEnforceInPanelOneIn;
     //yearEnforceInLines = yearEnforceInLinesIn;      
      entityCheckFields = entityCheckFieldsIn;
      entityTemplate = entityTemplateIn;
      //System.out.println("EntityPanel.EntityPanel   type:"+type+"    entity:"+entity+"  entityReportForm:"+entityReportForm);
      }
      

      
      // PanelStatistics
      public EntityPanel(String nameIn,String typeIn,String parentTitleIn,String titleIn,ImageIcon icoIn, String querySelectIn,String queryFromIn,
     String queryWhereIn,String queryGroupByIn,String queryOrderByIn, boolean isFilterCompanyIn, String fieldCompanyIdNameIn,
     boolean isFilterYearIn, String fieldYearNameIn,EntityPanel[] drillEntityPanelIn, String[] fieldsOnTitleIn,String[] fieldsOnTitleCaptionIn)//,EntityReport entityReportFormIn)
     {
     	name=nameIn;
     	type=typeIn;
     	parentTitle=parentTitleIn;
     	title=titleIn;
     	ico=icoIn;
     	querySelect=querySelectIn;
     	queryFrom=queryFromIn;
     	queryWhere=queryWhereIn;
     	queryGroupBy=queryGroupByIn;
     	queryOrderBy=queryOrderByIn;
     	isFilterCompany=isFilterCompanyIn;
        fieldCompanyIdName=fieldCompanyIdNameIn;
        isFilterYear=isFilterYearIn;
        fieldYearName=fieldYearNameIn;
        drillEntityPanel = drillEntityPanelIn;
      fieldsOnTitle = fieldsOnTitleIn;
      fieldsOnTitleCaption = fieldsOnTitleCaptionIn;        
     //   entityReportForm = entityReportFormIn;
     
     }
     
     
     
        public String getType()     {     return type;        }
        public String getEntity()     {     return entity;        }
        public EntityDBFields[] getDBFields()     {     return fields;        }
        //public String[] getFieldsTranslation()     {     return fieldsTranslation;        }
        public EntityDBFields[] getDBFieldsMany()     {     return fieldsMany;        }
        //public String[] getFieldsManyTranslation()     {     return fieldsManyTranslation;        }
          public EntityGroupOfComps[] getEntityGroupOfComps()     {     return entityGroupOfComps;        }
           public EntityGroupOfPanels[] getEntityGroupOfPanels()     {     return entityGroupOfPanels;        }
        public String[] getFieldsForSumsMany()  {   return fieldsForSumsMany;  }     
         //public String[] getFieldsManyOnInsert()     {     return fieldsManyOnInsert;        }
        //public String[] getFieldsManyTranslationOnInsert()     {     return fieldsManyTranslationOnInsert;        } 
        public String[] getFieldsOnStatisticsTitle() { return fieldsOnTitle;}
        public String[] getFieldsOnStatisticsTitleCaption() { return fieldsOnTitleCaption;}
        public String getEntityMany()     {     return entityMany;        }
        public String getSqlMany()     {     return sqlMany;        }
        public String getQueryManyReadOnly()     {     return queryManyReadOnly;        }
        //public boolean getIsMasterUnique()     {     return isMasterUnique;        }
       // public String[] getSql2WhereField()     {     return sql2WhereField;        }
       // public String[] getSql2WhereValue()     {     return sql2WhereValue;        }
        public String getPrimKey()     {     return primKey;        }
       public String getPrimKeyValue()     {     return primKeyValue;        }
      public String getPrimKeyDb()     {     return primKeyDb;        }
       // public String[] getPrimKeysMany()     {     return primKeysMany;        }
       // public String[] getPrimKeysManyTran()     {     return primKeysManyTran;        }      
      public String getTitle()     {     return title;        }
      public boolean getIsNewRec()     {     return isNewRec;        }
      public ImageIcon getIco()    {     return ico;      }      
      public String getStrOfMany2()     {     return strOfMany2;        }
      public boolean getShowTtitle()     {     return showTitle;        }
      
      public String[] getFieldsUnique() {return  fieldsUnique  ;}
      public int getUniqueWhileDataentry() {return uniqueWhileDataentry;}
      public boolean getUniqueWhileDataentryEditable() {return uniqueWhileDataentryEditable;}
      public int getUniqueBeforeSave() {return uniqueBeforeSave;}
      public boolean getUniqueBeforeSaveEditable() {return uniqueBeforeSaveEditable;}
           
      
      
      
      public String getName()     {     return name;        }

      public String getQuery()     {     return query;        }
      public String getSqlOne()     {     return sqlOne;        }
      public boolean getShowToolBar()     {     return showToolBar;        }
      public boolean getIsMany()     {     return isMany;        }
      public boolean getShowOnlySaveNPrintButton()     {     return showOnlySaveNPrintButton;        }  
      public String getParentTitle()     {     return parentTitle;        }
      public String getQuerySelect()     {     return querySelect;        }
      public String getQueryFrom()     {     return queryFrom;        }
      public String getQueryWhere()     {     return queryWhere;        }
      public String getQueryGroupBy()     {     return queryGroupBy;        }
      public String getQueryOrderBy()     {     return queryOrderBy;        }
      public boolean getIsFilterCompany()     {     return isFilterCompany;        }
      public String getFieldCompanyIdName()     {     return fieldCompanyIdName;        }
      public boolean getIsFilterYear()     {     return isFilterYear;        }
      public String getFieldYearName()     {     return fieldYearName;        }
      
     public int getextsumcalcsIntTableActionValueField()     {     return extsumcalcsIntTableActionValueField;        }
     public String getextsumcalcsTablePercentage()     {     return extsumcalcsTablePercentage;        }
     public String getextsumcalcsTablePercentageKey()     {     return extsumcalcsTablePercentageKey;        }
     public String getextsumcalcsTableCategory()     {     return extsumcalcsTableCategory;        }
     public String getextsumcalcsTableCategoryKey()     {     return extsumcalcsTableCategoryKey;        }
     public int getextsumcalcsIntTableCategoryField()     {     return extsumcalcsIntTableCategoryField;        }
     //public String getyearEnforceInPanelOne()     {     return yearEnforceInPanelOne;        }
     //public String getyearEnforceInLines()     {     return yearEnforceInLines;        }
     
     public EntityUpdateAdditional[] getUpdateAdditional() { return updateAdditional;}
     
     public EntityPanel[] getDrillEntityPanel() {return drillEntityPanel;}
     public EntityReport getEntityReportForm() {return entityReportForm;} 
      
     public EntityCalculate getEntityCalculate() {return entityCalculate;}
     public EntityCheckFields[] getEntityCheckFields() {return entityCheckFields;}
     public EntityTemplate getEntityTemplate() {return entityTemplate;}
}