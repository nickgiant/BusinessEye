// created 17-9-2006
package com.tool.model;

import com.tool.gui.*;
import com.tool.guicomps.*;

    public class EntityReport
    {
        public String name;
        public String reportCategory;
        public String query;
        public String queryOrderBy;
        public EntityReportBand[] entityReportBands;
        
        //public String primKeyToConnectHeader;
        public String type;
        public String caption;
        public String subTitle;
        public int main;
        //public char type;
        public EntityFilterSettings[] entityFilterSettings;
        public EntityGroupOfComps[] entityGroupOfComps;
        public int[] isFieldSelected; // db fields
        public boolean[] areFieldsEditable;
        //public int[] fieldsOrderby;
        private String formQueryForPrinting; // the sql to get the html template
        private String formFieldToGetData;//  the field to get the html  template
        private String formMessageNoForm;// message when there is no html template
        public int[] intSettingsReport;
        public boolean[] boolSettingsReport;// show date, company title,companyinfo,print filters, sum in each page
        private String tableAndFieldOfAskingToPrintOrNot; //  ie "actionseries.askForPrint"
       // public String yearEnforce;
        
        public EntityReport(String nameIn,String reportCategoryIn, EntityReportBand[] entityReportBandsIn,String queryIn, String queryOrderByIn,
         String typeIn,String captionIn,String subTitleIn, EntityFilterSettings[] entityFilterSettingsIn, EntityGroupOfComps[] entityGroupOfCompsIn,
         int[] isFieldSelectedIn, boolean[] areFieldsEditableIn, /*int[] fieldsOrderbyIn,*/ String formQueryForPrintingIn,String formFieldToGetDataIn,String formMessageNoFormIn,
         int[] intSettingsReportIn, boolean[] boolSettingsReportIn,  String tableAndFieldOfAskingToPrintOrNotIn)
        {
          name = nameIn;
          reportCategory=reportCategoryIn;
          query=queryIn;
          queryOrderBy=queryOrderByIn;
          //entityHeader=entityHeaderIn;
          entityReportBands = entityReportBandsIn;
          //sqlQueryHeader=sqlQueryHeaderIn;
          //primKeyToConnectHeader=primKeyToConnectHeaderIn;
          type=typeIn;
          caption = captionIn;
          subTitle = subTitleIn;
          entityFilterSettings = entityFilterSettingsIn;
          entityGroupOfComps = entityGroupOfCompsIn;
          isFieldSelected=isFieldSelectedIn;
          areFieldsEditable=areFieldsEditableIn;
          //fieldsOrderby=fieldsOrderbyIn;
          formQueryForPrinting =  formQueryForPrintingIn;
          formFieldToGetData = formFieldToGetDataIn;
          formMessageNoForm =  formMessageNoFormIn;
          boolSettingsReport=boolSettingsReportIn;
          intSettingsReport=intSettingsReportIn;
          tableAndFieldOfAskingToPrintOrNot=tableAndFieldOfAskingToPrintOrNotIn;
          //yearEnforce=yearEnforceIn;
        }

        /*public EntityReport(String nameIn,String reportCategoryIn,String entityHeaderIn,  String sqlQueryIn,String sqlQueryHeaderIn,String primKeyToConnectHeaderIn,String typeIn,String captionIn, int mainIn, EntityFilterSettings[] entityFilterSettingsIn)
        {
          name = nameIn;
          reportCategory=reportCategoryIn;
          entityHeader=entityHeaderIn;
          entityQuery = sqlQueryIn;
          sqlQueryHeader=sqlQueryHeaderIn;
          primKeyToConnectHeader=primKeyToConnectHeaderIn;
          type=typeIn;
          caption = captionIn;
          main = mainIn;
          entityFilterSettings = entityFilterSettingsIn;
        }*/

        public String toString()
        {
        	String ret="";
        /*	if(subTitle!=null && !subTitle.equalsIgnoreCase(""))
        	{
               ret= caption+", "+subTitle;
               //ret="<html><b>"+caption+"</b>, "+subTitle+"</html>";
               // ret="<html><b>"+caption+"</b><br>"+subTitle+"</html>";

            }
            else
            {*/
            	ret = caption;
         /*   	//ret="<html><b>"+caption+"</b></html>";
            	
            }*/
          return ret;
        }
        
        
        //public void setShowColumns(int[] showColumnsIn) {showColumns=showColumnsIn;}
       // public void setBoolSettingsReport(boolean[] boolSettingsReportIn) {boolSettingsReport=boolSettingsReportIn;} 
        //public void setSettedQuery(String queryIn) { settedQuery=queryIn; } 
        
        public  String  getName() { return name; } 
        public  String  getCategory() {return reportCategory;}// like REPORT_CAT_ECONOMIC
        public  String  getType() { return type; } // like ODMR
        public  String  getCaption() { return caption; } 
        public  String  getSubTitle() { return subTitle; } 
        public int[] getIsFieldSelected() {return isFieldSelected;}
        public boolean[] getAreFieldsEditable() {return areFieldsEditable;}
      //  public int[] getFieldsOrderby() {return fieldsOrderby;}
        public int[] getIntSettingsReport() {return intSettingsReport;}
        public boolean[] getBoolSettingsReport() {return boolSettingsReport;}
       // public String getYearEnforce() {return yearEnforce;}
        public EntityGroupOfComps[] getEntityGroupOfComps() { return entityGroupOfComps; } 
        public EntityFilterSettings [] getEntityFilterSettings() { return entityFilterSettings; } 
        public EntityReportBand [] getEntityReportBands() { return entityReportBands ;}
        public String getQuery() {return query;}
        public String getQueryOrderBy() {return queryOrderBy;}
        
        public String getFormQueryForPrinting() {return formQueryForPrinting;}
        public String getFormFieldToGetData() {return formFieldToGetData;}
        public String getFormMessageNoForm() {return formMessageNoForm;}
        public String getTableAndFieldOfAskingToPrintOrNot() {return tableAndFieldOfAskingToPrintOrNot;}
                
                

    }