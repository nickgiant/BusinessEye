//created 19-09-2009
package com.tool.model;

import com.tool.guicomps.*;
import com.tool.utils.UtilsString;

public class EntityDBFields implements Constants
{
     private String tableName;
     private String dbField;
     private String caption;
     private int groupOfComps;
     private String colClassName;  // rsmd.getColumnClassName(i) // "java.lang.String", "java.sql.Date" || ("java.lang.Date"), "java.lang.Boolean","java.lang.Integer", "java.lang.Double" || ("java.math.BigDecimal" || "java.lang.Long"), "java.lang.Object"
     private int colWidth; // rsmd.getPrecision(i)
     private int primaryKeyIntegerAutoInc;  // FIELD_PRIMARY_KEY, FIELD_PRIMARY_KEY_AUTOINC, FIELD_NORMAL_NO_PRIMARY_KEY
     private int lookupType; // look in Constants for LOOKUPTYPE_  
     private String lookupEntityName; // the name of lookup entity
    // private boolean isReplaceIfHasValue;// for example the suggested value of product when the buyer is seledted (is declered in dbfield(buyer) for the next cells/fields)
     private int fieldObligatoryOrSuggest;    // FIELD_NOCOMPLETION = 0  FIELD_OBLIGATORY = 1  FIELD_SUGGEST = 2; // for table FIELD_TABLE_NOROWCOMPLETION = 3; FIELD_TABLE_ONEROWATLEAST_OBLIGATORY = 4; FIELD_TABLE_ONEROWATLEAST_SUGGEST = 5;
     private int validation;   //   FIELD_VALIDATION_NO = 0  FIELD_VALIDATION_AFM = 1
     //private int[] includedGroupOfComps;
     /*private int whenSetThenCalculateField = -1; // -1 no calculation, set it so when not loaded alway be -1 
     private int[] calculationInputFields;  // null for not, if 1 then sql , if more than one make calculation
     private String calculation;// preffered sql*/
     private int isVisibleOrEditable;
     private String defaultValue;
     private EntityDBFieldsCalculation[] fieldsCalculationUpdate;// boolean if true then calculation or if false then update // default is true so when not setted(in init of sum fields) calculates
     private EntityDBFieldsCalculation[] fieldsCalculationSelect;
     
     private String childTable = null; // to check if is table field check if is null
     private int childTableHeight=100;
     private int childTableInPosition=CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL;
     private EntityDBFields[] dbChildFields;
     private String sqlTableChildRead;
     private String[] fieldsForCalculationOfTableRead; // only for table field
     private String[] childTableFieldsForSums;
     private int tableFromWhichTheSumWillBeCalculated=-1;// sums, the table, -1 :none
     private int fieldOfTableThatWillBeSummed;// sums, the field
     private int typeOfSum; // sums, type sum or count  // DBFIELD_TYPE_OF_SUM_SUM, DBFIELD_TYPE_OF_SUM_COUNT
    // private String queryForHtmlFileDocRead; // get with  getSqlTableChildRead
     private String formVariableFromField;  // like field 'sxActionTypeId' in module 'sersal' of entity 'actiontype'
     
     private String multipleInsertField;// like stockid or accountid
     private String multipleInsertCaption;
    
      public EntityDBFields()
      {}
      
      // no calculation
      public EntityDBFields(String tableNameIn, String dbFieldIn, String captionIn, int groupOfCompsIn, String colClassNameIn,int colWidthIn,int primaryKeyIntegerAutoIncIn,  
              int lookupTypeIn, String lookupEntityNameIn,boolean isReplaceIfHasValueIn, int fieldObligatoryOrSuggestIn,int validationIn,int isVisibleOrEditableIn)
      {
      
      tableName = tableNameIn;
      dbField=dbFieldIn;
      caption=captionIn;	
      groupOfComps=groupOfCompsIn;
      
      colClassName=colClassNameIn;
      colWidth=colWidthIn;
      primaryKeyIntegerAutoInc=primaryKeyIntegerAutoIncIn;  
      lookupType=lookupTypeIn;
      lookupEntityName=lookupEntityNameIn; // the name of lookup entity
      //isReplaceIfHasValue = isReplaceIfHasValueIn;
      
      fieldObligatoryOrSuggest=fieldObligatoryOrSuggestIn;
      validation=validationIn;
      isVisibleOrEditable = isVisibleOrEditableIn;
      
      }

      // with default value 
      public EntityDBFields(String tableNameIn,String dbFieldIn, String captionIn, int groupOfCompsIn,String colClassNameIn,int colWidthIn,int primaryKeyIntegerAutoIncIn,  
              int lookupTypeIn, String lookupEntityNameIn,/* boolean isReplaceIfHasValueIn,*/ int fieldObligatoryOrSuggestIn,int validationIn,int isVisibleOrEditableIn, 
              String defaultValueIn,String formVariableFromFieldIn)
      {
      tableName = tableNameIn;
      dbField=dbFieldIn;
      caption=captionIn;	
      groupOfComps=groupOfCompsIn;
      colClassName=colClassNameIn;
      colWidth=colWidthIn;
      primaryKeyIntegerAutoInc=primaryKeyIntegerAutoIncIn;           
      lookupType=lookupTypeIn;
      lookupEntityName=lookupEntityNameIn; // the name of lookup entity   
      //isReplaceIfHasValue= isReplaceIfHasValueIn;
      fieldObligatoryOrSuggest=fieldObligatoryOrSuggestIn;
      validation=validationIn;
      isVisibleOrEditable = isVisibleOrEditableIn;
      defaultValue=defaultValueIn;  // if already exists string into field do not complete
      formVariableFromField = formVariableFromFieldIn;
      }      
 
      

      
      
      // with calculation  // followingCalculationOrUpdateIn boolean if true then calculation or if false then update
      public EntityDBFields(String tableNameIn ,String dbFieldIn, String captionIn, int groupOfCompsIn, String colClassNameIn,int colWidthIn,int primaryKeyIntegerAutoIncIn,  
              int lookupTypeIn,String lookupEntityNameIn,/*boolean isReplaceIfHasValueIn,*/int fieldObligatoryOrSuggestIn,int validationIn, int isVisibleOrEditableIn,
              String defaultValueIn,EntityDBFieldsCalculation[] fieldsCalculationUpdateIn, EntityDBFieldsCalculation[] fieldsCalculationSelectIn,String formVariableFromFieldIn)
              /*int whenSetThenCalculateFieldIn, int[] calculationInputFieldsIn, String calculationIn)*/
      {
      tableName = tableNameIn;
      dbField=dbFieldIn;
      caption=captionIn;	
      groupOfComps=groupOfCompsIn;
      colClassName=colClassNameIn;
      colWidth=colWidthIn;
      primaryKeyIntegerAutoInc=primaryKeyIntegerAutoIncIn;       
      lookupType=lookupTypeIn;
      lookupEntityName=lookupEntityNameIn; // the name of lookup entity   
      //isReplaceIfHasValue= isReplaceIfHasValueIn;
      fieldObligatoryOrSuggest=fieldObligatoryOrSuggestIn;
      validation=validationIn;
      isVisibleOrEditable = isVisibleOrEditableIn;
      defaultValue=defaultValueIn;  // if already exists string into field do not complete
      fieldsCalculationUpdate=fieldsCalculationUpdateIn;  
       fieldsCalculationSelect=fieldsCalculationSelectIn;
       formVariableFromField = formVariableFromFieldIn;
      }

      // with sums or counts of table 
      public EntityDBFields(String tableNameIn ,String dbFieldIn, String captionIn, int groupOfCompsIn, String colClassNameIn,int colWidthIn,int primaryKeyIntegerAutoIncIn,  
              int lookupTypeIn,String lookupEntityNameIn,/*boolean isReplaceIfHasValueIn,*/int fieldObligatoryOrSuggestIn,int validationIn, int isVisibleOrEditableIn,
              String defaultValueIn, EntityDBFieldsCalculation[] fieldsCalculationUpdateIn, EntityDBFieldsCalculation[] fieldsCalculationSelectIn,
              int tableFromWhichTheSumWillBeCalculatedIn,int fieldOfTableThatWillBeSummedIn, int typeOfSumIn )              /*int whenSetThenCalculateFieldIn, int[] calculationInputFieldsIn, String calculationIn)*/
      {
      tableName = tableNameIn;
      dbField=dbFieldIn;
      caption=captionIn;	
      groupOfComps=groupOfCompsIn;
      colClassName=colClassNameIn;
      colWidth=colWidthIn;
      primaryKeyIntegerAutoInc=primaryKeyIntegerAutoIncIn;       
      lookupType=lookupTypeIn;
      lookupEntityName=lookupEntityNameIn; // the name of lookup entity   
      //isReplaceIfHasValue= isReplaceIfHasValueIn;
      fieldObligatoryOrSuggest=fieldObligatoryOrSuggestIn;
      validation=validationIn;
      isVisibleOrEditable = isVisibleOrEditableIn;
      //fieldsCalculation=fieldsCalculationIn;
      defaultValue=defaultValueIn;  // if already exists string into field do not complete
    fieldsCalculationUpdate=fieldsCalculationUpdateIn;  
       fieldsCalculationSelect=fieldsCalculationSelectIn;
      tableFromWhichTheSumWillBeCalculated=tableFromWhichTheSumWillBeCalculatedIn;
      fieldOfTableThatWillBeSummed=fieldOfTableThatWillBeSummedIn;
      typeOfSum=typeOfSumIn;
     /*whenSetThenCalculateField=whenSetThenCalculateFieldIn; // -1 no calculation
     calculationInputFields=calculationInputFieldsIn;      
      calculation=calculationIn;*/
      }      
      
      
      
      
      // table dbfields or child dbfields
      public EntityDBFields(String tableNameIn, String dbFieldIn, String captionIn, int groupOfCompsIn, String colClassNameIn,int isVisibleOrEditableIn,String childTableIn,
              int childTableHeightIn, int childTableInPositionIn,EntityDBFields[] dbChildFieldsIn,int fieldObligatoryOrSuggestIn,String sqlTableReadIn,String[] fieldsForCalculationOfTableReadIn,//  like dbcompanyid for year in panel of dbcompany
              String[] childTableFieldsForSumsIn,String multipleInsertFieldIn, String multipleInsertCaptionIn)
      {
      
      tableName = tableNameIn;
      dbField=dbFieldIn;
      caption=captionIn;	
      groupOfComps=groupOfCompsIn;
      colClassName=colClassNameIn; // "table" for table dbfields or child dbfields
      isVisibleOrEditable = isVisibleOrEditableIn;
      childTable=childTableIn;
      childTableHeight=childTableHeightIn;
      childTableInPosition=childTableInPositionIn;
      dbChildFields=dbChildFieldsIn;
      fieldObligatoryOrSuggest=fieldObligatoryOrSuggestIn;
      sqlTableChildRead=sqlTableReadIn;
      fieldsForCalculationOfTableRead=fieldsForCalculationOfTableReadIn;
      childTableFieldsForSums = childTableFieldsForSumsIn;
      multipleInsertField=multipleInsertFieldIn;
      multipleInsertCaption=multipleInsertCaptionIn;
      }
 
      /*
      *   for htmlfile (docs)
      */
      public EntityDBFields(String tableNameIn, String dbFieldIn, String captionIn, int groupOfCompsIn, String colClassNameIn,int isVisibleOrEditableIn, String childTableIn,
              EntityDBFields[] dbChildFieldsIn,int fieldObligatoryOrSuggestIn,String sqlTableReadIn)
      {
      
      tableName = tableNameIn;
      dbField=dbFieldIn;
      caption=captionIn;	
      groupOfComps=groupOfCompsIn;
      colClassName=colClassNameIn; // "table" for table dbfields or child dbfields
      isVisibleOrEditable = isVisibleOrEditableIn;
      childTable=childTableIn;
      dbChildFields=dbChildFieldsIn;
      fieldObligatoryOrSuggest=fieldObligatoryOrSuggestIn;
      sqlTableChildRead=sqlTableReadIn;
      
      }      
      
      
      
      
       public String getTableName()     {     return tableName;        }
        public String getDbField()     {     return dbField;        }
        public String getCaption()     {     return caption;        }
        public int getGroupOfComps()     {     return groupOfComps;        }
        public String getColClassName()     {     return colClassName;        }
        public int getColWidth()     {     return colWidth;        }
        public int getPrimaryKeyIntegerAutoInc()     {     return primaryKeyIntegerAutoInc;        }
        public int getLookupType()     {     return lookupType;        }
        public String getLookupEntityName()     {     return lookupEntityName;        }
       // public boolean getIsReplaceIfHasValue()     {     return isReplaceIfHasValue;        }
        public int getFieldObligatoryOrSuggest()     {     return fieldObligatoryOrSuggest;        }
        public int getValidation()     {     return validation;        }

        public int getIsVisibleOrEditable()     {     return isVisibleOrEditable;        }
        public String getDefaultValue()     {     return defaultValue;        }
        public EntityDBFieldsCalculation[] getFieldsCalculationUpdate()   {return  fieldsCalculationUpdate; }// followingCalculationOrUpdateIn: boolean if true then calculation or if false then update
        public EntityDBFieldsCalculation[] getFieldsCalculationSelect()     {     return fieldsCalculationSelect;        }
        
        public String getChildTable()    {      return childTable  ;}
        public int getChildTableHeight()    {      return childTableHeight  ;}
        public int getChildTableInPosition()    {      return childTableInPosition  ;}
        public EntityDBFields[] getDbChildFields()     {     return dbChildFields;        }
        public String getSqlTableChildRead() 
        {
            String ret =getFieldsForCalculationOfTableRead();
            
            return ret;//sqlTableChildRead  ;
        }
        private String getFieldsForCalculationOfTableRead() 
        {
                                String calculationEachRow = "";
                                UtilsString utilsString = new UtilsString();
                                int indexOfHashChar = sqlTableChildRead.indexOf("#");
                               
                               if(indexOfHashChar!=-1)
                               {    
                                  calculationEachRow = utilsString.replaceTextOfAStringWithText("#", sqlTableChildRead, fieldsForCalculationOfTableRead, null);
                               }
                               else
                               {
                                   calculationEachRow = sqlTableChildRead;// for the ordenary cases
                               }
            
            
            
            
             return calculationEachRow;
        } // only for 'getSqlTableChildRead' table field, like 'dbcompanyid' for 'dbyear' in panel of dbcompany
        public String[] getChildTableFieldsForSums()    {      return childTableFieldsForSums  ;}
        
        public int getTableFromWhichTheSumWillBeCalculated()     {     return tableFromWhichTheSumWillBeCalculated;        }
        public int getFieldOfTableThatWillBeSummed()     {     return fieldOfTableThatWillBeSummed;        }
        public int getTypeOfSum()     {     return typeOfSum;        }        
       public String getFormVariableFromField() { return formVariableFromField; }
        
         public String getMultipleInsertField() { return multipleInsertField;}
         public String getMultipleInsertCaption() { return multipleInsertCaption;}
}