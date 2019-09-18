/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tool.model;

import com.tool.guicomps.Constants;

/**
 *
 * @author sun
 */
public class EntityReportBandField implements Constants
{
    
//private String name;
    private String tableName;
     private String dbFieldName;
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
     private boolean isVisible;
     private String defaultValue;
     private EntityDBFieldsCalculation[] fieldsCalculation;
     private int type;//  ENTITYREPORT_QUERY_TYPE_ADDITIONAL , ENTITYREPORT_QUERY_TYPE_GROUP,  ENTITYREPORT_QUERY_TYPE_MAIN
     private String childTable = null; // to check if is table field check if is null
     private int childTableHeight=100;
     private int childTableInPosition=CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL;
     private EntityReportBand[] dbChildReportBand;
     private String sqlTableChildRead;
     private String[] childTableFieldsForSums;
     private int tableFromWhichTheSumWillBeCalculated=-1;// sums, the table, -1 :none
     private int fieldOfTableThatWillBeSummed;// sums, the field
     private int typeOfSum; // sums, type sum or count  // DBFIELD_TYPE_OF_SUM_SUM, DBFIELD_TYPE_OF_SUM_COUNT    
    
    public EntityReportBandField() 
    {
    }    

      
      // with calculation
    // entityReportBandFieldsFarmer[0] = new EntityReportBandField("customer","customerId","Νο πελάτη",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);
      public EntityReportBandField(String tableNameIn, String dbFieldNameIn,String captionIn, String colClassNameIn,int colWidthIn,boolean isVisibleIn,/*int primaryKeyIntegerAutoIncIn,  */
              //int lookupTypeIn,String lookupEntityNameIn,/*boolean isReplaceIfHasValueIn,*/int fieldObligatoryOrSuggestIn,int validationIn, int isVisibleOrEditableIn,
              String defaultValueIn, EntityDBFieldsCalculation[] fieldsCalculationIn)
              /*int whenSetThenCalculateFieldIn, int[] calculationInputFieldsIn, String calculationIn)*/
      {
      //name=nameIn;
      tableName = tableNameIn;
      dbFieldName=dbFieldNameIn;
      caption=captionIn;	
      //type=typeIn;
      //groupOfComps=groupOfCompsIn;
      colClassName=colClassNameIn;
      colWidth=colWidthIn;
      //primaryKeyIntegerAutoInc=primaryKeyIntegerAutoIncIn;       
      //lookupType=lookupTypeIn;
      //lookupEntityName=lookupEntityNameIn; // the name of lookup entity   
      //isReplaceIfHasValue= isReplaceIfHasValueIn;
      //fieldObligatoryOrSuggest=fieldObligatoryOrSuggestIn;
      //validation=validationIn;
      isVisible = isVisibleIn;
      fieldsCalculation=fieldsCalculationIn;
      defaultValue=defaultValueIn;  // if already exists string into field do not complete
     /*whenSetThenCalculateField=whenSetThenCalculateFieldIn; // -1 no calculation
     calculationInputFields=calculationInputFieldsIn;      
      calculation=calculationIn;*/
      }    
    
      // table dbfields or child report fields
      public EntityReportBandField(String tableNameIn, String dbFieldNameIn,String captionIn,  int typeIn, int groupOfCompsIn, String colClassNameIn,boolean isVisibleIn, String childTableIn,
              int childTableHeightIn, int childTableInPositionIn,EntityReportBand[] dbChildReportBandIn,int fieldObligatoryOrSuggestIn,String sqlTableReadIn,
              String[] childTableFieldsForSumsIn)
      {
      //name=nameIn;
      tableName = tableNameIn;
      dbFieldName=dbFieldNameIn;
      caption=captionIn;
      type=typeIn;
      groupOfComps=groupOfCompsIn;
      colClassName=colClassNameIn; // "table" for table dbfields or child dbfields
      isVisible = isVisibleIn;
      childTable=childTableIn;
      childTableHeight=childTableHeightIn;
      childTableInPosition=childTableInPositionIn;
      dbChildReportBand=dbChildReportBandIn;
      fieldObligatoryOrSuggest=fieldObligatoryOrSuggestIn;
      sqlTableChildRead=sqlTableReadIn;
      childTableFieldsForSums = childTableFieldsForSumsIn;
      }

      public String getTableName() { return tableName; }         
     public String getDbFieldName() { return dbFieldName; }           
     public String getCaption() { return caption; }      
     public String getColClassName() { return colClassName; }   
     //public int getType() { return type; }     
     public int getColWidth() {return colWidth;}
     public boolean getIsVisible() {return isVisible;}
    
      
}
