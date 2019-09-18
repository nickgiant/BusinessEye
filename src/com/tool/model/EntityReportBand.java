/*  created 07-06-2014
*  
 */

package com.tool.model;

import com.tool.guicomps.Constants;

/*
 * @author sun
 */
public class EntityReportBand implements Constants
{
    private String name;
     private String tableName;
     private EntityReportBandField[] entityReportBandFields;
     public int[] fieldsOrderby;
     private String caption;
     //private EntityDBFieldsCalculation[] fieldsCalculation;
     private String sqlGroupByField;
     private int type;//  ENTITYREPORT_QUERY_TYPE_ADDITIONAL , ENTITYREPORT_QUERY_TYPE_GROUP,  ENTITYREPORT_QUERY_TYPE_MAIN
     //private int typeOfSum; // sums, type sum or count  // DBFIELD_TYPE_OF_SUM_SUM, DBFIELD_TYPE_OF_SUM_COUNT   
     private boolean[] boolSettings; // horizontal line, vertical line, show zero,double height of line
     private EntityReportBand[] entityReportBandChild;  // child band
     private EntityPanel[] entityPanelDrill;   // the drill panel
     private String[] panelDrillFieldsOnTitle;
     private String[] panelDrillFieldsOnTitleCaption;
     private String formGlobalTableToGet1;
     private String formGlobalTableToApply1;
     
    
    public EntityReportBand() 
    {
    }    

     public EntityReportBand(String nameIn,  String captionIn, String tableNameIn ,EntityReportBandField[] entityReportBandFieldsIn, int[] fieldsOrderbyIn,String sqlGroupByFieldIn, 
             int typeIn, boolean [] boolSettingsIn, EntityPanel[] entityPanelDrillIn,String[]  panelDrillFieldsOnTitleIn,String[]  panelDrillFieldsOnTitleCaptionIn
             /*String formGlobalTableToGet1In,String formGlobalTableToApply1In*/)
     {
      name=nameIn;
      tableName = tableNameIn;
      entityReportBandFields=entityReportBandFieldsIn;
      fieldsOrderby = fieldsOrderbyIn;
      caption=captionIn;
      sqlGroupByField=sqlGroupByFieldIn;
      type=typeIn;
      boolSettings=boolSettingsIn;
      entityPanelDrill = entityPanelDrillIn;
      panelDrillFieldsOnTitle=panelDrillFieldsOnTitleIn;
      panelDrillFieldsOnTitleCaption = panelDrillFieldsOnTitleCaptionIn;
      //formGlobalTableToGet1 = formGlobalTableToGet1In;
      //formGlobalTableToApply1=formGlobalTableToApply1In;
     } 
      

      
     public void setBoolSettings(boolean[] boolSettingsIn) {boolSettings=boolSettingsIn;}
      
     public String getName() { return name; }       
     public String getCaption() { return caption; }      
     public String getTableName() { return tableName; }       
     public String getSqlGroupByField() {return sqlGroupByField;}
     public int getType()
     { 
         //System.out.println("EntityReportBand type"+type);
         return type;
     }   
     public EntityReportBandField[] getEntityReportBandFields(){return entityReportBandFields;}
     public int[] getFieldsOrderby() {return fieldsOrderby;}
     public boolean[] getBoolSettings() {return boolSettings;}
     public EntityReportBand[]  getEntityReportBandChild() {return entityReportBandChild;}
     public EntityPanel[]  getEntityPanelDrill() {return entityPanelDrill;}
     public String[]  getPanelDrillFieldsOnTitle() {return panelDrillFieldsOnTitle;}
     public String[]  getPanelDrillFieldsOnTitleCaption() {return panelDrillFieldsOnTitleCaption;}
     public String  getFormGlobalTableToGet1() {return formGlobalTableToGet1;}
     public String  getFormGlobalTableToApply1() {return formGlobalTableToApply1;}
}
