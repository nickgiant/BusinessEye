package com.tool.model;

import com.tool.gui.*;

import javax.swing.*;

public class EntityLookUp
{
	    public String name;
        public String foreignTable;
        public String query; //query read only. the one that apeares in dialogEdit and in showrow of panel (without is active)
        public String querySubqueryWhere;
        private String[] fieldsReplacedInsideQuery; // if query has character # replace
        public String querySubqueryIsActive; //(is added in list only)
        public String queryOrderBy;
        String queryWhereForFormVariable;
        public String lookUpKey;
        public String lookUpKeyTranslation;
        public String lookUpKeyFT; //lookUpKeyForeignTable
        public String lookUpLabel;
        public int lookUpFieldIndex; // the number of column
        public String[] lookUpField;
        //public String[] lookUpFieldsArray;
        public String lookUpFieldLabel;
        public int lookUpFieldLength;
        public String lookUpFieldType;
        public int lookUpField2Index;
        public String lookUpField2;
        public String lookUpField2Label;
        public int lookUpField3Index;
        public String lookUpField3;
        public String lookUpField3Label;        
        public String queryEditable;// the query that is needed to edit the records
        //public EntityDBFields[] fields;
        public String strOfOne;
        public String strOfMany;
        public String[] categoryNodes;
        public EntityPanel[] entityPanel;
        public String[] fieldsOnTitle;
        public String[] fieldsOnTitleCaption;     
        //public String[] searchCaption;
        //public String[] searchField;
        public EntityFilterSettings[]  entityFilterSettings;
        public int intColFieldDescription; // when is -1 use the intField, else uset the no of the description
        public int intNoOfColsWhenInTable; // possible values 1 or two
        public ImageIcon icon;
        public boolean showToolbar;
        public int intValidationColumn;
        public int intValidationType;
        private String[] fieldsForSums;
        //private String formGlobalTableToApply1;// for example in sxaccount lookup set 'sxaccount'
       // private EntityReport entityReport;// report for documents. I have not setted for farmersvat

      public EntityLookUp(String name)
      {}

        public EntityLookUp(String nameIn, String foreignTableIn, String queryIn, String querySubqueryWhereIn,String[] fieldsReplacedInsideQueryIn ,String querySubqueryIsActiveIn,String queryOrderByIn, 
           String queryWhereForFormVariableIn,String lookUpKeyIn, String lookUpKeyTranslationIn, String lookUpKeyFTIn, String lookUpLabelIn, int lookUpFieldIndexIn,
           String[] lookUpFieldIn, String lookUpFieldLabelIn,int  lookUpFieldLengthIn,String lookUpFieldTypeIn,int lookUpField2IndexIn, String lookUpField2In,
           String lookUpField2LabelIn, int lookUpField3IndexIn, String lookUpField3In, String lookUpField3LabelIn, String queryEditableIn, /*EntityDBFields[] fieldsIn,*/ 
           String strOfOneIn,  String strOfManyIn,String [] categoryNodesIn, EntityPanel[] entityPanelIn, String[]fieldsOnTitleIn,
           String[]fieldsOnTitleCaptionIn,EntityFilterSettings[] entityFilterSettingsIn, int intColFieldDescriptionIn,
           int intNoOfColsWhenInTableIn, ImageIcon iconIn , boolean showToolbarIn,int intValidationColumnIn,int intValidationTypeIn, String[] fieldsForSumsIn)//,String formGlobalTableToApply1In)//, EntityReport entityReportIn)
        {
          name=nameIn;
          foreignTable = foreignTableIn;
          query = queryIn;
          querySubqueryWhere=querySubqueryWhereIn;
          fieldsReplacedInsideQuery = fieldsReplacedInsideQueryIn;
          querySubqueryIsActive=querySubqueryIsActiveIn;
          queryOrderBy=queryOrderByIn;
          queryWhereForFormVariable = queryWhereForFormVariableIn;
          lookUpKey = lookUpKeyIn;
          lookUpKeyTranslation=lookUpKeyTranslationIn;
          lookUpKeyFT = lookUpKeyFTIn;
          lookUpLabel = lookUpLabelIn;
          lookUpFieldIndex =lookUpFieldIndexIn;
          lookUpField = lookUpFieldIn;
          //lookUpFieldsArray=lookUpFieldsArrayIn;
          lookUpFieldLabel = lookUpFieldLabelIn;
          lookUpFieldLength = lookUpFieldLengthIn;
          lookUpFieldType = lookUpFieldTypeIn;          
          lookUpField2Index = lookUpField2IndexIn;
          lookUpField2 = lookUpField2In;
          lookUpField2Label = lookUpField2LabelIn;
          lookUpField3Index = lookUpField3IndexIn;
          lookUpField3 = lookUpField3In;
          lookUpField3Label = lookUpField3LabelIn;          
          queryEditable=queryEditableIn;
          //fields=fieldsIn;
          //fieldsTranslation=fieldsTranslationIn;
          strOfOne=strOfOneIn;
          strOfMany=strOfManyIn;
          categoryNodes=categoryNodesIn;
          entityPanel=entityPanelIn;
          fieldsOnTitle=fieldsOnTitleIn;
          fieldsOnTitleCaption=fieldsOnTitleCaptionIn;
          //searchCaption=searchCaptionIn;
          //searchField=searchFieldIn; 
          entityFilterSettings =  entityFilterSettingsIn;
          intColFieldDescription=intColFieldDescriptionIn;
          intNoOfColsWhenInTable=intNoOfColsWhenInTableIn;
          icon=iconIn;
          showToolbar=showToolbarIn;
          intValidationColumn=intValidationColumnIn;
          intValidationType=intValidationTypeIn;
          fieldsForSums=fieldsForSumsIn;
          //formGlobalTableToApply1 = formGlobalTableToApply1In;
          //entityReport=entityReportIn;
        }

        public String toString()
        {
            return lookUpKey+" "+lookUpField;
        }

        public String getName()
        {
           return name;
        }

        public String getForeignTable()
        {
           return foreignTable;
        }

        public String getQuery()
        {
           return query;
        }

        public String getQuerySubqueryWhere()
        {
           return querySubqueryWhere;
        }        
        
        public String[] getFieldsReplacedInsideQuery()
        {
            return fieldsReplacedInsideQuery;
        }
        
        public String getQuerySubqueryIsActive()
        {
           return querySubqueryIsActive;
        }
        
        public String getQueryOrderBy()
        {
        return queryOrderBy;
        }
        
        public String getQueryWhereForFormVariable()
        {
        return queryWhereForFormVariable;
        }
        
        public String getLookUpKey()
        {
           return lookUpKey;
        }

        public String getLookUpKeyTranslation(){    return lookUpKeyTranslation;  }

        public String getLookUpKeyFT()
        {
           return lookUpKeyFT;
        }
        
        public String getLookUpLabel()
        {
           return lookUpLabel;
        }

        public int getLookUpFieldIndex()
        {
           return lookUpFieldIndex;
        }       

        public String[] getLookUpField()
        {
           return lookUpField;
        }

        
        /*public String[] getLookUpFieldsArray()
        {
           return lookUpFieldsArray;
        }*/        
        
        public String getLookUpFieldLabel()
        {
           return lookUpFieldLabel;
        }

         public int getLookUpFieldLength()
        {
           return lookUpFieldLength;
        }   

        public String getLookUpFieldType()
        {
           return lookUpFieldType;
        }             

        public int getLookUpField2Index()
        {
           return lookUpField2Index;
        }

        public String getLookUpField2()
        {
           return lookUpField2;
        }
        
        public String getLookUpField2Label()
        {
           return lookUpField2Label;
        }

        public int getLookUpField3Index()
        {
           return lookUpField3Index;
        }

        public String getLookUpField3()
        {
           return lookUpField3;
        }
        
        public String getLookUpField3Label()
        {
           return lookUpField3Label;
        }
        
        public String getQueryEditable()
        {
           return queryEditable;
        }

        public String getStrOfOne()
        {
           return strOfOne;
        }
        
        public String getStrOfMany()
        {
           return strOfMany;
        }
        
        public String[] getCategoryNodes()
        {
           return categoryNodes;
        }
  
        public EntityFilterSettings[] getEntityFilterSettings()
        {
        	return entityFilterSettings;
        }
       
        public EntityPanel[] getEntityPanel()  {   return entityPanel;     }      
       public String[] getFieldsOnTitle()  {   return fieldsOnTitle;  }
       public String[] getFieldsOnTitleCaption()  {   return fieldsOnTitleCaption;  }
       public int getIntColFieldDescription()   {     return intColFieldDescription;       }        
       public int getIntNoOfColsWhenInTable()   {   return intNoOfColsWhenInTable;       } 
       public ImageIcon getIcon() { return icon;}  
       	public boolean getShowToolbar() { return showToolbar;}  
  public int getIntValidationColumn()  {   return intValidationColumn;  }
  public int getIntValidationType()  {   return intValidationType;  }   
  public String[] getFieldsForSums()  {   return fieldsForSums;  }
 // public String getLookUpFormGlobalTableToApply1()  { return formGlobalTableToApply1;}
  //public EntityReport getEntityReport() {return entityReport;}
       //public String[] getSearchCaption()  {   return searchCaption;  }
       //public String[] getSearchField()  {   return searchField;  }

}
