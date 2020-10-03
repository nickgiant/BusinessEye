
package com.tool.utils;

import com.tool.model.LookUpMgt;
import com.tool.gui.*;
import com.tool.jdbc.*;
import com.tool.guicomps.*;
import com.tool.model.EntityDBFields;
import java.sql.*;

import javax.swing.table.*;
import java.lang.reflect.*;

import java.util.*;
import javax.swing.JTable;

public class UtilsPanelReport implements Constants
{
    private ResultSet rs;
//    private ResultSetMetaData rsmd;

    public String primKeyValue;
    //public String primKeyValueFromParentTable;
    public String globalYear;
    public String globalDeliveryId;
    public String globalCompanyId; 
    
    private LookUpMgt lookUp;
     private Database db;
     private DatabaseTableMeta databaseTableMeta;
     
     final String DOTMATRIX_CHAR_EMPTY=" ";
    
    // to be getted
    //private String[] primKeys;
    private String[] primKeysDbField;
    private String[] primKeysDbFieldCaption;
    private String[] primKeysDbFieldValue;
    //private String[] primKeysValue;
    private String colDescriptionValue;
    private UtilsString utilsString;
    private String filePrefs;
    
 public UtilsPanelReport()
 {
		lookUp = new LookUpMgt();
		db = new Database();
		databaseTableMeta = new DatabaseTableMeta();
		utilsString = new UtilsString();
		filePrefs =VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_TABLEPREFERENCES;
		
 }
	

   // called by PanelOneDataOneRecData and PanelODOR.setEntity
   public int getRowForPrimKey(String calledByIn,String queryIn, ResultSet rs, EntityDBFields[] dbFields,String primKey, String primKeyValueIn)
   { 
   	      primKeyValue=primKeyValueIn; 
   	 int row=0;
   	 int line=0;
   	 String columnName="";
     try
     { 
        //System.out.println("--UtilsPanelReport.getRowForPrimKey -->> "+primKey+"="+primKeyValue+"  rs:"+rs);
        
      rs.beforeFirst();
      while(rs.next())
      {
         //System.out.println(" - + - + + + + + +  +UtilsPanelReport.getRowForPrimKey() row="+row+"     dbFields.length:"+dbFields.length+"  calledByIn:"+calledByIn+" queryIn:"+queryIn);
      	 row=row+1; //rs starts from 1
        
         //for (int i = 1; i <= rsmd.getColumnCount(); i++)//  i = fieldTxts  int 0 dbFields.length
        for(int i = 0; i < dbFields.length; i++)//  i = fieldTxts  int 0 
        {
          	columnName = dbFields[i].getDbField();//rsmd.getColumnLabel(i); //get colunm name  dbFieldsIn[i].getCaption();          	
        	//System.out.println(" + +   UtilsPanelReport.getRowForPrimKey ("+i+") columnName:"+columnName+"  primKey:"+primKey+"="+primKeyValue);
        	if (columnName.equalsIgnoreCase(primKey))
        	{
          	   //System.out.println(" - -  UtilsPanelReport.getRowForPrimKey ("+i+") ["+row+"]  "+columnName+"   "+primKey+":"+rs.getString(primKey)+"  "+primKeyValue);
          	   if(rs.getString(primKey)==null)  
                   {
                       
                   }
                   else
                   {
                      //System.out.println(" UtilsPanelReport  ------------- "+i+"   primKey:"+primKey+"  v:"+rs.getString(primKey)+" is ? "+primKeyValue);
                     if ( rs.getString(primKey).equalsIgnoreCase(primKeyValue))
          	     {
          	     	//System.out.println("UtilsPanelReport.getRowForPrimKey()   ["+row+"]    FOUND primKey:"+primKey+"="+rs.getString(primKey)+"="+primKeyValue+" row:"+row+" columnLabel:"+columnLabel);
          	        line=row;
                    
          	     	//System.out.println("--UtilsPanelReport.getRowForPrimKey() row="+row+"  line:"+line);
          	     }
                     else
                     {
                         
                        // System.out.println(" ----error-----------------"+i+"  UtilsPanelReport.getRowForPrimKey else A primKey:"+primKey+"   rs.getString("+primKey+"):"+rs.getString(primKey)+"=primKeyValue:"+primKeyValue+" row:"+row+" columnLabel:"+columnLabel);
                         //String str = rs.getString(primKey)+"";
                         //line = Integer.parseInt(str);
                     }
                   }
          	}
                else if(columnName.equalsIgnoreCase(dbFields[i].getChildTable()))// when a table has a header. ie in ManyDataManyRec (is called in edit for a list lookup)ie paymenttype
                {
                    //System.out.println(" + +  + + UtilsPanelReport.getRowForPrimKey()  "+columnName+"  "+dbFields[i].getChildTable()+"   "+dbFields[i].getTableName()+"    primKey:"+primKey+"   primKeyValue:"+primKeyValue);
          	   if(rs.getString(primKey)==null)  
                   {
                       
                   }
                   else
                   {
                      //System.out.println(" UtilsPanelReport ------------- "+i+" "+"   primKey:"+primKey+"   "+rs.getString(primKey)+" is ? "+primKeyValue);
                     if ( rs.getString(primKey).equalsIgnoreCase(primKeyValue))
          	     {
          	     	//System.out.println("UtilsPanelReport.getRowForPrimKey()   ["+row+"]    FOUND primKey:"+primKey+"="+rs.getString(primKey)+"="+primKeyValue+" row:"+row+" columnLabel:"+columnLabel);
          	        line=row;

          	     }
                     else
                     {

                     }
                   }                    
                }
                else
                {
                    
                }
          	
        } //for
      }//while
            //System.out.println("UtilsPanelReport.getRowForPrimKey "+primKey+" "+line);
         //rs.close(); if enabled makes error
      }
      catch(SQLException e)
      {  System.out.println("Error  UtilsPanelReport.getRowForPrimKey() " + e.getMessage());
         
          e.printStackTrace();
      } 
      	
      if(line==0)
      {
          //line=row;
          System.out.println(" ERROR         calledByIn:"+calledByIn+" queryIn:"+queryIn);
         System.out.println(" ERROR UtilsPanelReport.getRowForPrimKey DOES NOT SELECT A LINE. dbFields.length:"+dbFields.length+" primKey:"+primKey+" primKeyValue:"+primKeyValue+" row:"+row+"  line:"+line+"  perhaps not active 'service' or 'account'");	
         System.out.println(" ERROR");
         
         
      }
   	 return line;
   }
   
   
   // called by PanelOneDataManyRecData.retrieveDataFromWritableTable
   // query without foreign columns
   public String getQueryMany(String sql2, boolean isNewRec, String primKeyDb,String PKForPanelOneRecDataValue,EntityDBFields[] dbFieldsMany/*, String[] sql2WhereField, 
           String[] sql2WhereValue,String yearIn,String companyIdIn,String deliveryIdIn*/)
   {
       // these are not global. taken from panelTwoDataOneRec.retrieveDataFromWritableTable from med
     
                
    
         
     /*globalYear=yearIn;
     globalCompanyId=companyIdIn;
     globalDeliveryId=deliveryIdIn;*/
     
         String query2="";
         String sqlWhere="";
         /*if(sql2WhereField!=null)
         {
             for(int i=0;i<sql2WhereField.length;i++)
             {    
                  if(isNewRec)
                  {
                  	sqlWhere=sqlWhere+" "+sql2WhereField[i]+" = '0'";
                  }
                  else
                  {
                     if (sql2WhereValue[i].equalsIgnoreCase("primKeyValue") && PKForPanelOneRecDataValue!=null )
                     {
                        sqlWhere=sqlWhere+" "+sql2WhereField[i]+" = "+PKForPanelOneRecDataValue;
                     }
                     else if(sql2WhereValue[i].startsWith("global"))
                     {
                         //System.out.println("UtilsPanelReport.getQueryMany       global        "+i+" "+sql2WhereField[i]+"  "+getValueForVariable(sql2WhereValue[i]));
                     	 sqlWhere=sqlWhere+" "+sql2WhereField[i]+" = "+getValueForVariable(sql2WhereValue[i]);
                     }
                     else if(sql2WhereValue[i].equalsIgnoreCase(sql2WhereField[i]))//  database field
                     {
                         String valueFromDb="";
                         String[] pks = getPrimKeys();
                         String[] pksValue = getPrimKeysValue();
                         int pkslength = pks.length;

                         for(int l = 0 ;l<pkslength;l++)
                         {
                             //System.out.println("UtilsPanelReport.getQueryMany   else   "+i+"  pkslength:"+pkslength+" "+sql2WhereField[i]+" =  pks[l]:"+pks[l]);
                             if(pks[l].equalsIgnoreCase(sql2WhereField[i]))
                             {
                                 valueFromDb=pksValue[l];
                                 //System.out.println("      UtilsPanelReport.getQueryMany   if   "+l+"   "+sql2WhereField[i]+"  pks[l]:"+pks[l]+" = "+pksValue[l]+"  valueFromDb:"+valueFromDb);
                             }
                             else
                             {
                                 //System.out.println("        UtilsPanelReport.getQueryMany   else   "+l+"   "+sql2WhereField[i]+"  pks[l]:"+pks[l]+" = "+pksValue[l]);
                             }
                         }
                         
                         sqlWhere=sqlWhere+" "+sql2WhereField[i]+" = "+valueFromDb;
                     }
                     else
                     {
                         System.out.println("   error       UtilsPanelReport.getQueryMany   else   "+i+"   "+sql2WhereField[i]);
                     }
      	          }
      	          if(i<sql2WhereField.length-1)
      	          {
      	          	sqlWhere=sqlWhere+" AND";
      	          }
             } */
             
             
             sqlWhere=sqlWhere+" AND "+primKeyDb+" = "+PKForPanelOneRecDataValue;
             
            /* if(utilsString.hasQueryWhere(sql2))
             {
             	sqlWhere=" AND"+sqlWhere;
             }
             else
             {
             	sqlWhere=" WHERE"+sqlWhere;
             }*/ 
         /*}
         else
         {
         	System.out.println("error UtilsPanelReport.getQueryMany sql2WhereField=null");
         }*/
        if (isNewRec)
        {
        	 query2 = sql2+sqlWhere+  " ORDER BY "+VariablesGlobal.columnNameInc;// aa";//+VariablesGlobal.columnNameInc;;//+  " ORDER BY date";
   	         //panelCenter.setPreferredSize(new Dimension(700, 350));
            
        }
        else
        {
             query2 = sql2+sqlWhere+  " ORDER BY "+VariablesGlobal.columnNameInc;//aa";//+VariablesGlobal.columnNameInc;//  +" ORDER BY date";
             //System.out.println("UtilsPanelReport.getQueryMany query2 "+query2);	
        }
        //System.out.println("UtilsPanelReport.getQueryMany    query2:"+query2);
        return query2;	
   }


   /*
   * 
   * exists in UtilsPanelReport.getValueForVariable,  in PanelOneDataOneRecData.getValueForVariable
   * and TableModelResultSet.getValueForVariable
   */
  /*private String getValueForVariable(String variableName)
  {
      
      
      
      String valueStr="";
      if(variableName.equalsIgnoreCase("globalYear"))
      {
          valueStr=globalYear;
      }
      else if(variableName.equalsIgnoreCase("globalCompanyId"))
      {
          valueStr=globalCompanyId;
      }
      else if(variableName.equalsIgnoreCase("globalDeliveryId"))
      {
          valueStr=globalDeliveryId;
      }    
      else if(variableName.equalsIgnoreCase("primKeyValue"))
      {
          valueStr=primKeyValue;
      }
      else
      {
          valueStr="";
          System.out.println("error UtilsPanelReport.getValueForVariable UNKNOWN "+ variableName+"  "+valueStr);
      }
      
    //System.out.println("UtilsPanelReport.getValueForVariable "+variableName+":"+valueStr);  
  /*	String valueStr="";
  	double valueDoub;
  	int valueInt;
  	Object valueObj;
    try
    {
      //System.out.println("UtilsPanelReport.getValueForVariable  "+ variableName);
        // proguard throws exception
      Field thisField = UtilsPanelReport.class.getField(variableName);
     // Field thisField = this.getField(variableName);

      
      
      
      Class thisClassType = thisField.getType();
      //System.out.println("panelTwoDataOneRec.getValueForVariable "+ thisField+" "+thisClassType);
      //System.out.print("The variable '"+lookingForValue+"' contains ");
      if (thisClassType.toString().equals("double"))
      { 
          valueDoub=thisField.getDouble(this);
           valueStr=Double.toString(valueDoub);   
      }
      else if (thisClassType.toString().equals("int"))
      {
      	   valueInt=thisField.getInt(this);
              valueStr=Integer.toString(valueInt);
      }
      else if (thisClassType.toString().equals("class java.lang.String"))
      {
           valueObj=thisField.get(this);
           
           //System.out.println("UtilsPanelReport.getValueForVariable "+variableName+" valueObj="+valueObj);
           
           
           if (valueObj!=null)
           {      valueStr=valueObj.toString();   }
           else
           { System.out.println("UtilsPanelReport.getValueForVariable "+variableName+" valueObj:"+valueObj);  }
      }

    }
       catch(Exception e)
       {   e.printStackTrace();   }
    
*/
    //  System.out.println("UtilsPanelReport.getValueForVariable -- "+ variableName+"  "+valueStr);
/*    return valueStr;
  }*/
  
  
     public String getStringForPositionInTheLeft(String phrase, int totalChars)
   {
   	  String returnStr="";
   	    if(totalChars >= phrase.length())
   	    {
   	        int intSpaces = totalChars-phrase.length()  ;
   	       	
   	        for(int i=0;i<intSpaces;i++)
   	        {
   	  	        returnStr=returnStr+DOTMATRIX_CHAR_EMPTY;
   	        }
   	  
   	        returnStr=phrase+returnStr;
   	    }
   	    else
   	    {
   	    	returnStr=phrase;
   	    	System.out.println("error ReportPanelODMRPage.getStringForPositionInTheLeft totalChars >= phrase.length()");
   	    }
   	    return returnStr;
   	    
   }
   
   public String getStringForPositionInTheMiddle(String phrase, String precedingPhrase, int totalChars)
   {                                                                                      // charsPerLine
   	  int intSpaces = (totalChars/2) - (phrase.length()/2) - precedingPhrase.length();
   	  String returnStr="";
   	  for(int i=0;i<intSpaces;i++)
   	  {
   	  	returnStr=returnStr+DOTMATRIX_CHAR_EMPTY;
   	  }
   	  
   	  returnStr=returnStr+phrase;
   	  
   	  return returnStr;
   	
   }
   
   public String getStringForPositionInTheRight(String phrase, String precedingPhrase, int totalChars)
   {
   	  int intSpaces = totalChars-phrase.length()-precedingPhrase.length();
   	  String returnStr="";
   	  for(int i=0;i<intSpaces;i++)
   	  {
   	  	returnStr=returnStr+DOTMATRIX_CHAR_EMPTY;
   	  }
   	  
   	  returnStr=returnStr+phrase;
   	  
   	  return returnStr;
   	
   } 
   
   // int intField no of field in lookup. isTypedOrSaved: true when is typed in keytextxtbox (use of queryLookUpIsActive), is false when is already saved (not use of queryLookUpIsActive)
   //  also appears partially in WindowLookUp.filter 
   public String getLookupValue(String luname, String foreignTable, String lookupValue, int intField, boolean isTypedOrSaved,String formVariableFromField,/*String formGlobalTableToGet1In,String formGlobalTableToApply1In,*/
           String subqueryWhereForAPreviousFieldValue, String entityIn) 
   { 
         String lookupResult="-";
      
              ResultSet rsForeign;
              String foreignQuery="";
              String qWhere = "";
              String queryLookUpWhere = lookUp.getQuerySubqueryWhere(luname);
              
             String queryLookUpIsActive = lookUp.getQuerySubqueryIsActive(luname);

//System.out.println("-t--ooo--t--UtilsPanelReport.getLookupValue     PRE CHECK     formGlobalTableToGet1In:"+formGlobalTableToGet1In+"         VariablesGlobal.globalformGlobalVariable1"+VariablesGlobal.globalformGlobalVariable1+"    formGlobalTableToApply1In:"+formGlobalTableToApply1In+"    foreignTable:"+foreignTable+"      entityIn:"+entityIn+"     subqueryWhereForAPreviousFieldValue:"+subqueryWhereForAPreviousFieldValue);             
             String queryWherePreValue="";
             if(subqueryWhereForAPreviousFieldValue != null && !subqueryWhereForAPreviousFieldValue.equalsIgnoreCase(""))
             {
                 if(utilsString.hasQueryWhere(subqueryWhereForAPreviousFieldValue))
                 {
                     subqueryWhereForAPreviousFieldValue= utilsString.removeStringFromString("WHERE",subqueryWhereForAPreviousFieldValue);
                 }
                 subqueryWhereForAPreviousFieldValue=" AND "+subqueryWhereForAPreviousFieldValue; 
             }
             else
             {
                subqueryWhereForAPreviousFieldValue="";
             }
      
             
            
             
      String queryLookUp = lookUp.getQuery(luname);
      if(VariablesGlobal.globalShowSelectUtilPanelReportRecord)
      {
     System.out.println("-t--ooo--t--UtilsPanelReport.getLookupValue     TO CHECK     luname:"+luname+"    formVariableFromField:"+formVariableFromField+"         VariablesGlobal.globalformGlobalVariable1::"+VariablesGlobal.globalformGlobalVariable1+"        foreignTable:"+foreignTable+"      entityIn:"+entityIn+"     subqueryWhereForAPreviousFieldValue:"+subqueryWhereForAPreviousFieldValue+"  lookupValue:"+lookupValue+"      queryLookUp:"+queryLookUp);             
      }
     String lookupValueQuery= "";
      String subQueryFilterFromRecType = "";
      String queryClosingSubquery = "";
      //if(formGlobalVariable1 !=null && !formGlobalVariable1.equalsIgnoreCase(""))
      String varGlobeVariable1 = VariablesGlobal.globalformGlobalVariable1;
   
      
      
          if(formVariableFromField!= null && !formVariableFromField.equalsIgnoreCase("") && VariablesGlobal.globalformGlobalVariable1!=null && !VariablesGlobal.globalformGlobalVariable1.equalsIgnoreCase(""))             //if(entityIn.equalsIgnoreCase(VariablesGlobal.globalformGlobalTableToGet1))
          {
              if(!subqueryWhereForAPreviousFieldValue.equalsIgnoreCase(""))
              {
                 queryClosingSubquery = VariablesGlobal.globalformGlobalVariable1 +" ) ";
              }
              else
              {
                  queryClosingSubquery="";
              }
          //queryClosingSubquery = " % ) ";
          //String queryLookUpWhereTableOfForm = lookUp.getQuerySubqueryWhere(luname);
          //queryClosingSubquery =  subQueryFilterFromRecType;      //queryLookUpWhereTableOfForm+subQueryFilterFromRecType;
              if(utilsString.hasQueryWhere(queryLookUpWhere))
              {  
                 lookupValueQuery=" AND "+foreignTable+"."+lookUp.getLookUpKey(luname)+" LIKE '"+lookupValue+"' ";
              }
              else
              {
                  lookupValueQuery=" WHERE "+foreignTable+"."+lookUp.getLookUpKey(luname)+" LIKE '"+lookupValue+"' ";
              }
             if(isTypedOrSaved)     //is typed
             {                
                  foreignQuery = queryLookUp+" "+queryLookUpWhere+" "+lookupValueQuery+" "+queryLookUpIsActive+" "+subqueryWhereForAPreviousFieldValue+" "+queryClosingSubquery+" "+lookUp.getQueryOrderBy(luname);
             }
             else
             {
       	          //lookUp.getLookUpField(luname)+" LIKE '"+tb2Text+"%' "
                  foreignQuery = queryLookUp+" "+queryLookUpWhere+" "+lookupValueQuery+" "+subqueryWhereForAPreviousFieldValue+lookUp.getQueryOrderBy(luname);        
             }          
       if(VariablesGlobal.globalShowSelectUtilPanelReportRecord)
      {  
          System.out.println("   IF    -t--OOOOOOOOOOO--t--UtilsPanelReport.getLookupValue  isTypedOrSaved:"+isTypedOrSaved+"       entityIn:"+entityIn+"   name:"+luname+"   foreignTable:"+foreignTable+"        queryClosingSubquery:"+queryClosingSubquery+"     lookupValueQuery:"+lookupValueQuery+"   foreignQuery:"+foreignQuery);
      }
          }
          else
          {
             // queryClosingSubquery = " % ) ";
              if(utilsString.hasQueryWhere(queryLookUpWhere))
              {    
                  lookupValueQuery =  " AND "+foreignTable+"."+lookUp.getLookUpKey(luname)+" LIKE '"+lookupValue+"' ";  //"";//queryClosingSubquery;  //lookupValueQuery=" "+foreignTable+"."+lookUp.getLookUpKey(foreignTable)+" LIKE '"+lookupValue+"' ";
              }
              else
              {
                  lookupValueQuery =  " WHERE "+foreignTable+"."+lookUp.getLookUpKey(luname)+" LIKE '"+lookupValue+"' ";
              }
             if(isTypedOrSaved)     //is typed
             {                
                  foreignQuery = queryLookUp+" "+queryLookUpWhere+" "+lookupValueQuery+" "+queryLookUpIsActive+" "+subqueryWhereForAPreviousFieldValue+" "+lookUp.getQueryOrderBy(luname);
             }
             else
             {
       	          //lookUp.getLookUpField(luname)+" LIKE '"+tb2Text+"%' "
                  foreignQuery = queryLookUp+" " +queryLookUpWhere+" "+lookupValueQuery+" "+subqueryWhereForAPreviousFieldValue+" "+lookUp.getQueryOrderBy(luname);        
             }  
             
             
             
     // if(VariablesGlobal.globalShowSelectUtilPanelReportRecord)
     // {
        System.out.println("   ELSE   UtilsPanelReport.getLookupValue  isTypedOrSaved:"+isTypedOrSaved+"    lookupResult:"+lookupResult+"      globalformGlobalVariable1:"+VariablesGlobal.globalformGlobalVariable1+"   luname:"+luname+"       foreignTable:"+foreignTable+"   lookupValueQuery:"+lookupValueQuery+"    foreignQuery:"+foreignQuery);
     // }
          }
      
      

  System.out.println("UtilsPanelReport.getLookupValue        --oOoOoOoOo--           isTypedOrSaved:"+isTypedOrSaved+"     hasWhere:"+utilsString.hasQueryWhere(queryLookUpWhere)+"   ----    foreignQuery:"+foreignQuery);
         try
         {          
          	    db.retrieveDBDataFromQuery(foreignQuery,"UtilsPanelReport.getLookupValue");
   	            rsForeign=db.getRS();

           if(rsForeign==null)   
           {
               
               
           }
           else
           {
              if (rsForeign.first())
              {
              
                 rsForeign = db.retrieveRow(rsForeign, 1);// go to the only row               
              
                //System.out.println("UtilsPanelReport.getLookupValue field "+foreignQuery+"  "+lookUp.getLookUpFieldIndex(luname));
                 //lookupResult = rsForeign.getString(lookUp.getLookUpFieldIndex(luname));// get field data
                 if (rsForeign.isFirst()) // value looked up might not have been existed
                 {
                     //System.out.println("UtilsPanelReport.getLookupValue   intField:"+intField+" :"+luname+"  :"+lookUp.getLookUpFieldIndex(luname));
                 	if(intField==1)
                 	{
                 	   lookupResult = rsForeign.getString(lookUp.getLookUpFieldIndex(luname));// get field data	
                 	}
                 	else if(intField==2)
                 	{
                 		lookupResult = rsForeign.getString(lookUp.getLookUpField2Index(luname));// get field data
                 	}
                 	else if(intField==3)
                 	{
                 		lookupResult = rsForeign.getString(lookUp.getLookUpField3Index(luname));// get field data
                 	}
                 	else
                 	{
                 		System.out.println("error UtilsPanelReport.getLookupValue intField:"+intField+" (1 or 2 or 3)");
                 	}
                    
                    
                 }
              }
          
           }

         }//try
         catch ( SQLException sqlex)
         {
             System.out.println("error: UtilsPanelReport.getLookupValue(): "+sqlex.getMessage());
             sqlex.printStackTrace();
         }
         
         closeDB();
         return lookupResult;
   }

   
   // not edited  - perhaps not needed
   /*public void retrievePrimKeyValueForManyPKs(String queryIn, int selectedTableRow, String primKeyIn,int intColumnOfDescription,
    boolean isQuery2, String[] sql2WhereField, String[] sql2WhereValue, String entity, TableModel tableModel,
    String[] fieldsMany, String primKeyDb, String primKeyValueSetted)
   // parameters are needed for panelTwoDataManyRec
   {
    /*  private String[] primKeys;
      private String[] primKeysValue;*/
      //System.out.println("UtilsPanelReport.retrievePrimKeyValueForManyPKs "+selectedTableRow);
    
/*       globalYear =VariablesGlobal.globalYear;
       globalDeliveryId =VariablesGlobal.globalDeliveryId;
       globalCompanyId=VariablesGlobal.globalCompanyId;
       
       //System.out.println("PanelODMRData.retrievePrimKeyValue "+queryIn);

     // System.out.println("- PanelODMRData.retrievePrimKeyValue intColumnOfDescription "+intColumnOfDescription);        
     try
     {
         databaseTableMeta.retrievePrimKs(entity);
         int primKeysCount = databaseTableMeta.getCountOfPrimKeys();
         System.out.println(" -- UtilsPanelReport.retrievePrimKeyValueForManyPKs "+primKeysCount+" ("+entity+") "+selectedTableRow);  
         if(primKeysCount == 0)
         {
           System.out.println(" -- UtilsPanelReport.retrievePrimKeyValueForManyPKs primKeysCount ("+entity+") "+primKeysCount);
         }
         primKeys = new String[primKeysCount];
         primKeysValue = new String[primKeysCount];
         for (int pk=0;pk<primKeysCount;pk++)
         {
             	
             	String pkName;
             	//System.out.println("PanelODMRData.retrievePrimKeyValue isEditable "+pk);
             	if (primKeyIn==null)
             	{
             	  pkName=databaseTableMeta.getPrimKeyName(pk);//before
             	  //System.out.println("UtilsPanelReport.retrievePrimKeyValueForManyPKs "+entity+" --- "+pk+" "+pkName+"-"+primKeyIn);
              	}
             	else
             	{
                  pkName=primKeyIn;// so translation be equal with translation
                   //System.out.println("UtilsPanelReport.retrievePrimKeyValueForManyPKs "+entity+" --- ("+pk+") "+pkName);
                }

          	     String whereValueName = getWhereValueNameThatMatchesColumn(pkName,  sql2WhereField,  sql2WhereValue);
          	   //System.out.println("UtilsPanelReport.retrievePrimKeyValueForManyPKs ("+pk+") "+pkName+" whereValueName:"+whereValueName);
          	    // System.out.println("PanelODMRData.retrievePrimKeyValue whereValueName"+whereValueName);
          	     if(!whereValueName.equalsIgnoreCase("-"))
          	     {
                     if (whereValueName.equalsIgnoreCase("primKeyValue"))
                     {
                     	//System.out.println("PanelODMRData.retrievePrimKeyValue whereValueName "+whereValueName);
                     	//System.out.println("PanelODMRData.retrievePrimKeyValue isQuery2"+isQuery2);
                     	if(!isQuery2)
                     	{
                     	  // System.out.println("PanelODMRData.retrievePrimKeyValue ="+pk+" pkName:"+pkName);
                           
   	                      db.retrieveDBDataFromQuery(queryIn,"UtilsPanelReport.retrievePrimKeyValueForManyPKs");
   	                      rs=db.getRS();

                          // System.out.println("PanelODMRData.retrievePrimKeyValue "+queryIn);
                           //System.out.println("PanelODMRData.retrievePrimKeyValue ="+pk+" pkName:"+pkName+" primKeyValue "+primKeyValue);
                           rs = db.retrieveRow(rs, selectedTableRow+1);
                           primKeyValue =rs.getString(pkName); 
                           
                           if(intColumnOfDescription!= -1 && intColumnOfDescription!=0)
                           {
                              colDescriptionValue =rs.getString(intColumnOfDescription); 
                              
                              //System.out.println("PanelODMRData.retrievePrimKeyValue rs "+colDescriptionValue)	;
                           }
                           
                           //System.out.println("PanelODMRData.retrievePrimKeyValue -o-IF  "+pk+" pkName:"+pkName+" primKeysValue[pk]:"+primKeysValue[pk]);
                     	}
                     	else
                     	{
                     	   //System.out.println("PanelODMRData.retrievePrimKeyValue +"+pk+" pkName:"+pkName);
                           primKeys[pk]=pkName;
                           primKeysValue[pk]=primKeyValueSetted;
                          // System.out.println("PanelODMRData.retrievePrimKeyValue -"+pk+" pkName:"+pkName+" primKeyValue "+primKeyValue); 
                           //System.out.println("PanelODMRData.retrievePrimKeyValue -o- ELSE "+pk+" pkName:"+pkName+" primKeysValue[pk]:"+primKeysValue[pk]);
                     	}
                    
                     //query = "SELECT * FROM "+entity;
                     //System.out.println("PanelODMRData.retrievePrimKeyValue "+q);


                     //System.out.println("PanelODMRData.retrievePrimKeyValue -o- "+pk+" pkName:"+pkName+" primKeysValue[pk]:"+primKeysValue[pk]);
                     }
                     else if(whereValueName.equalsIgnoreCase(pkName))// get field value from db
                     {
                         primKeys[pk]=pkName;

                         //System.out.println("PanelODMRData.retrievePrimKeyValueForManyPKs  ELSEIF    [glb]"+pk+" primKeys[pk]:"+primKeys[pk]+" primKeysValue[pk] "+primKeysValue[pk]+" pkName:"+pkName+" ["+(selectedTableRow+1)+"]    queryIn:"+queryIn);
                           
                           String q = queryIn;
                           db.retrieveDBDataFromQuery(q,"UtilsPanelReport.retrievePrimKeyValueForManyPKs");
   	                   ResultSet rsKey= null;
                           rsKey=db.getRS();
                           rsKey = db.retrieveRow(rsKey, selectedTableRow+1);
                           
                           primKeysValue[pk] =rsKey.getString(pkName); 
                           System.out.println("PanelODMRData.retrievePrimKeyValueForManyPKs   ELSEIF     [glb]"+pk+" primKeys[pk]:"+primKeys[pk]+" primKeysValue[pk] "+primKeysValue[pk]+" pkName:"+pkName+" = "+rsKey.getString(pkName)+"    queryIn:"+queryIn);
                         
                         
                         
                         
                     }
                     else  //if is a globalvariable then value should be getted from getValueForVariable(getWhereValueNameThatMatchesColumn(pkName));
                     {
                     	primKeys[pk]=pkName;
                     	//System.out.println("PanelODMRData.retrievePKValue [glb]"+pk+" "+primKeys[pk] );
                     	primKeysValue[pk]=getValueForVariable(getWhereValueNameThatMatchesColumn(pkName, sql2WhereField, sql2WhereValue));
                     	//System.out.println("PanelODMRData.retrievePKValue  ELSE    [glb]"+pk+" primKeys[pk]:"+primKeys[pk]+" primKeysValue[pk] "+primKeysValue[pk]);
                     }
                     //System.out.println("PanelODMRData.retrievePrimKeyValue pkName:"+pkName+" "+getValueForVariable(whereValueName)+" whereValueName "+whereValueName);
                    
                     //System.out.println("UtilsPanelReport.retrievePrimKeyValueForManyPKs -----if----  ("+pk+") "+primKeys[0]+" "+pkName);  
                 }
                 else
                 {
                 	
                 	//System.out.println("PanelODMRData.retrievePKValue else "+pk);
 	                //if is not a globalvariable then value should be get from jtable
                    for (int i = 0; i < tableModel.getColumnCount(); i++)//  i = fields
                    {
                    	//System.out.println("PanelODMRData.retrievePKValue "+i);
                    	String columnLabel="";
                    	//columnLabel = tableModel.getColumnName(i);//fieldsManyTranslation[i]; //get colunm name
                     	 columnLabel = tableModel.getColumnName(i);//fieldsManyTranslation[i]; //get colunm name
        	         
                          if(columnLabel==null)
                          {
                              columnLabel="";
                          }
                         
                         // System.out.println("PanelODMRData.retrievePrimKeyValueForManyPKs "+i+" "+columnLabel+" "+pkName);
                          if (columnLabel.equalsIgnoreCase(pkName))
        	            //if (pkName.equalsIgnoreCase(fieldsMany[i]))
        	            {
        	            	if(tableModel.getRowCount()>0)
        	            	{
        	            	        primKeyValue =  tableModel.getValueAt(selectedTableRow,i).toString();	
        	            	}
        	            	else
        	            	{
        	            		primKeyValue="";
        	            	}
          	                
          	                
                           if(intColumnOfDescription!= -1 && intColumnOfDescription!=0)
                           {          	                
          	                 colDescriptionValue =tableModel.getValueAt(selectedTableRow,intColumnOfDescription-1).toString();
          	                 //System.out.println("PanelODMRData.retrievePrimKeyValue table row"+(intColumnOfDescription-1)+" col"+i+" val"+colDescriptionValue+" col count"+tableModel.getColumnCount())	;
                           }
                            primKeys[pk]=primKeyDb;
        	            	if(tableModel.getRowCount()>0)
        	            	{                            
                                        primKeysValue[pk]=tableModel.getValueAt(selectedTableRow,i).toString();
        	            	}
        	            	else
        	            	{
        	            		primKeysValue[pk]="";
        	            	}        	            	
            
        		            //System.out.println("PanelODMRData.retrievePKValue [jtb]"+pk+" primKeys[pk]:"+primKeys[pk]+" primKeyValue "+primKeyValue);
          	             }
          	             else
          	             {
          	             	//System.out.println("PanelODMRData.retrievePKValue "+pk+" "+i);
          	            if (isQuery2 && pkName.equalsIgnoreCase(fieldsMany[i]))
                     	    {
                     	    	
                     		   //System.out.println("PanelODMRData.retrievePrimKeyValue columnLabel "+columnLabel+" "+fieldsMany[i]+" "+pkName);
                     		   
                            primKeys[pk]=pkName;
        	            	if(tableModel.getRowCount()>0)
        	            	{
                                        primKeysValue[pk]=tableModel.getValueAt(selectedTableRow,i).toString();
        	            	}
        	            	else
        	            	{
        	            		primKeysValue[pk]="";
        	            	}            	            	
                     //	System.out.println("PanelODMRData.retrievePKValue [jtb alt]"+pk+" primKeys[pk]:"+primKeys[pk]+" primKeyValue "+primKeyValue);	   
                     		
                     	    }
                            else
                            {
                                System.out.println("undefined     UtilsPanelReport.retrievePrimKeyValueForManyPKs -----ELSE----- columnLabel "+columnLabel+" "+fieldsMany[i]+" "+pkName);
                                
                            }
          	                 //primKeys[pk]=pkName;
          	                 //System.out.println("PanelODMRData.retrievePrimKeyValue columnLabel"+columnLabel+" not "+pkName);
          	            }
                     }
                    //System.out.println("UtilsPanelReport.retrievePrimKeyValueForManyPKs ----else-----  ("+pk+") "+primKeys[0]+" "+pkName); 
                 }
                  //System.out.println("UtilsPanelReport.retrievePrimKeyValueForManyPKs ---------  ("+pk+") "+primKeys[0]+" "+pkName);   
              //System.out.println("UtilsPanelReport.retrievePrimKeyValueForManyPKs >>> ("+pk+") "+pkName+" whereValueName:"+whereValueName+"  "+primKeysValue[pk]);
         }       
      }
      catch(SQLException sqlE)
      {  System.out.println("Error UtilsPanelReport.retrievePrimKeyValueForManyPKs  " + sqlE);
      }
     
           System.out.println("  UtilsPanelReport.retrievePrimKeyValueForManyPKs  " +primKeys[0]+" "+primKeysValue[0]);
  */         
     
 //  }

   
   // has been edited
   /*
    * called by PanelODMRData.retrievePrimKeyValueForReadOnlyTable
    *   by PanelODMRData.retrievePrimKeyValueForWritableTable
    *  by this in getNoOfPKAutoIncOfNewRecord
    */
public void retrievePrimKeyValueForOnePK(String queryIn, int selectedTableRow, EntityDBFields[] dbFields,EntityDBFields[] dbFieldsMany, boolean isMany,
        /*String primKeyTranslationIn,*/ /*int intColumnOfDescription,*/ /*String[] sql2WhereField, String[] sql2WhereValue,*/ String entity, /*TableModel tableModel,*/
        String primKeyDb)
   {




     //System.out.println(" +  +  +  +  +  +  UtilsPanelReport.retrievePrimKeyValueForOnePK ("+entity+") dbFields:"+dbFields+" dbFieldsMany:"+dbFieldsMany+" isMany:"+isMany+"    primKeyDb:"+primKeyDb);
      // EntityDBFields[] dbFieldsPKs = null;
       ArrayList listDbFieldsPKs = new ArrayList();
     String lastNo="0";
  	 //String primkeyAutoInc="";
         //String[] primkeyFixedArray;// like companyId
         //String[] primkeyFixedValueArray;
      
     if(isMany)
     {
         //System.out.println(" ->  UtilsPanelReport.retrievePrimKeyValueForOnePK() isMany:"+isMany+" queryIn:"+queryIn);
         dbFields=dbFieldsMany;
         
     }
     else
     {
         //System.out.println(" ->  UtilsPanelReport.retrievePrimKeyValueForOnePK() isMany:"+isMany+"  dbFields.length:"+dbFields+"  dbFieldsMany:"+dbFieldsMany+"  queryIn:"+queryIn);
     }
     
     
         int intPrimkeyFixed=0;
  if(dbFields!=null && dbFields.length>0)       
  {
         for(int c=0;c<dbFields.length;c++)
  	 {
             //System.out.println("UtilsPanelReport.retrievePrimKeyValueForOnePK   c:"+c+"    "+dbFields.length);//+"    "+dbFields[c].getPrimaryKeyIntegerAutoInc());
                if(dbFields[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY)
                {
                    intPrimkeyFixed++;
                }         
         }
              
                    
         intPrimkeyFixed=0;   

  	 for(int c=0;c<dbFields.length;c++)
  	 {
             
             //System.out.println(" + UtilsPanelReport.retrievePrimKeyValueForOnePK ("+entity+") FOR c:"+c+"  dbFields[c].getDbField:"+dbFields[c].getDbField()+",  class:"+dbFields[c].getColClassName());//+"     dbFields[c].getPrimaryKeyIntegerAutoInc():"+dbFields[c].getPrimaryKeyIntegerAutoInc());
             if(!dbFields[c].getColClassName().equalsIgnoreCase("table"))// if component is not one only editable table
             {
                if(dbFields[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC)
                {
                    //primkeyAutoInc = dbFields[c].getDbField();
                    listDbFieldsPKs.add(dbFields[c]);
                    
                }             
                else if(dbFields[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY)
                {
                    //primkeyFixedArray[intPrimkeyFixed]=dbFields[c].getDbField();
                    //primkeyFixedValueArray[intPrimkeyFixed]=dbFields[c].getDefaultValue();
                    //System.out.println("+ UtilsPanelReport.retrievePrimKeyValueForOnePK ("+entity+") FIELD_PRIMARY_KEY primkeyFixedArray:"+primkeyFixedArray[intPrimkeyFixed]+"="+primkeyFixedValueArray[intPrimkeyFixed]+"  "+intPrimkeyFixed);
                    listDbFieldsPKs.add(dbFields[c]);
                    
                    intPrimkeyFixed++;
                }  
                else if(dbFields[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_FROM_PARENTTABLE)
                {
                    //primkeyAutoInc = dbFields[c].getDbField();
                    listDbFieldsPKs.add(dbFields[c]);
                    
                }         
                else if(dbFields[c].getPrimaryKeyIntegerAutoInc()==FIELD_NORMAL_NO_PRIMARY_KEY)// 0
                {
                    //System.out.println("+ UtilsPanelReport.retrievePrimKeyValueForOnePK ("+entity+")    dbFields[c]Caption:"+dbFields[c].getCaption()+"   0    dbFields[c].getPrimaryKeyIntegerAutoInc():"+dbFields[c].getPrimaryKeyIntegerAutoInc());
                }
             }
             else if(dbFields[c].getColClassName().equalsIgnoreCase("table"))// if component is one only editable table
             {
                 EntityDBFields[] tableDBFields = dbFields[c].getDbChildFields();
                 if(tableDBFields!=null)
                 {    
                    for(int cf = 0; cf<tableDBFields.length;cf++)
                    {
                        
                        if(tableDBFields[cf].getDbField().equalsIgnoreCase(primKeyDb)) // when prim key is the prim key of chield
                        {
                            //System.out.println("UtilsPanelReport.retrievePrimKeyValueForOnePK   IF    entity:"+entity+"    primKeyDb:"+primKeyDb+"     "+tableDBFields[cf].getTableName()+"."+tableDBFields[cf].getDbField()+" = "+dbFields[c].getTableName()+"."+dbFields[c].getDbField()); 
                           listDbFieldsPKs.add(tableDBFields[cf]);
                        }
                        else
                        {
                           //System.out.println("UtilsPanelReport.retrievePrimKeyValueForOnePK    ELSE  A   ("+entity+") FOR c:"+c+"    primKeyDb:"+primKeyDb);
                        }
                    }
                 }
             }
             else
             {
                 //System.out.println(" - UtilsPanelReport.retrievePrimKeyValueForOnePK    ELSE     ("+entity+") FOR c:"+c+"    primKeyDb:"+primKeyDb+"     dbFields[c].getDbField:"+dbFields[c].getDbField()+",  class:"+dbFields[c].getColClassName()+"     dbFields[c].getPrimaryKeyIntegerAutoInc():"+dbFields[c].getPrimaryKeyIntegerAutoInc());
             }

  	 }        

         primKeysDbField = new String[listDbFieldsPKs.size()];
         primKeysDbFieldCaption = new String[listDbFieldsPKs.size()];
         primKeysDbFieldValue = new String[listDbFieldsPKs.size()];
         String[] primKeyTable = new String[listDbFieldsPKs.size()];
         
         String q = "";
          q = utilsString.removeCaptionsFromQuerySubStringSelect(queryIn);
         
         
        // System.out.println("  UtilsPanelReport.retrievePrimKeyValueForOnePK entity:"+entity+"      pk size:"+listDbFieldsPKs.size()+"   selectedTableRow:"+selectedTableRow+"     q:"+q);
         
        if(listDbFieldsPKs.size()>0)
        {
           for (int pk=0;pk<listDbFieldsPKs.size();pk++)
           {
 
               EntityDBFields edbf = (EntityDBFields)listDbFieldsPKs.get(pk);
               
               primKeysDbField[pk]=edbf.getDbField();
               primKeyTable[pk]=edbf.getTableName();
               //System.out.println(" o  UtilsPanelReport.retrievePrimKeyValueForOnePK("+pk+") edbf.getTableName():"+edbf.getTableName());
               primKeysDbFieldCaption[pk] = edbf.getCaption();
               //primKeysDbFieldValue[pk]

          //if(primKeyTable[pk].equalsIgnoreCase(entity))  // when the entity is dbcompany dbyear
             //   && utilsString.hasQuerySelectColumnName(q,STRFIELD_DBCOMPANYID)                 && utilsString.hasQuerySelectColumnName(q,STRFIELD_DBYEARID) 
             
           //System.out.println(" UtilsPanelReport.retrievePrimKeyValueForOnePK entity:"+entity+"    ("+pk+")  "+primKeysDbField[pk]+"="+STRFIELD_DBCOMPANYID +"  q:"+q);
          if((primKeysDbField[pk].equalsIgnoreCase(STRFIELD_DBCOMPANYID) && !utilsString.hasQuerySelectColumnName(q,STRFIELD_DBCOMPANYID) ) || (primKeysDbField[pk].equalsIgnoreCase(STRFIELD_DBYEARID) &&  !utilsString.hasQuerySelectColumnName(q,STRFIELD_DBYEARID) ))
          {
              
              if (primKeysDbField[pk].equalsIgnoreCase(STRFIELD_DBCOMPANYID) ) 
              {
                  if(STRTABLE_DBCOMPANY.equalsIgnoreCase(entity))
                  {  
                      try
                      {
                   
                        db.retrieveDBDataFromQuery(q,"UtilsPanelReport.retrievePrimKeyValueForOnePK "+STRTABLE_DBCOMPANY);
   	                if(db.getRecordCount()>0)
                        {
                             rs=db.getRS();
                    // System.out.println("gg ("+entity+") ("+pk+")   primKeysDbField:"+primKeysDbField[pk]+"    primKeysDbFieldCaption"+primKeysDbFieldCaption[pk]+"    selectedTableRow:"+selectedTableRow+"   "+queryIn);
                   //System.out.println("gg ("+pk+") Caption:"+primKeysDbFieldCaption[pk]+", DbField:"+primKeysDbField[pk]+" = "+primKeysDbFieldValue[pk]+" queryIn:"+queryIn);
                             rs = db.retrieveRow(rs, selectedTableRow);
                            if(rs.isAfterLast())
                            {
                       
                            }
                            else
                            { 
                                 primKeysDbFieldValue[pk] = rs.getString(primKeysDbField[pk]); // perhaps  primKeysDbFieldCaption   primKeysDbField
                 //System.out.println("UtilsPanelReport.retrievePrimKeyValueForOnePK ("+pk+") Caption:"+primKeysDbFieldCaption[pk]+", DbField:"+primKeysDbField[pk]+" = "+primKeysDbFieldValue[pk]);//+"   query q:"+q);              
                            }
                        }                        
                     }
                     catch(SQLException sqlE)
                     {
                        System.out.println("Error UtilsPanelReport.retrievePrimKeyValueForOnePK() A  getErrorCode:"+ sqlE.getErrorCode()+"    "+sqlE.getMessage()+"   query q:"+q);
                        sqlE.printStackTrace();
                     }                   
                      finally
                      {
                      closeDB();
                      }
                  }
                  else
                  {
                     primKeysDbFieldValue[pk]=VariablesGlobal.globalCompanyId; 
                  }
                    // System.out.println("pp  UtilsPanelReport.retrievePrimKeyValueForOnePK("+pk+")  entity:"+entity+"    "+primKeysDbField[pk]+"="+primKeysDbFieldValue[pk]);
              }
              else if(primKeysDbField[pk].equalsIgnoreCase(STRFIELD_DBYEARID) )  
              {
                  if(STRTABLE_DBYEAR.equalsIgnoreCase(entity))
                  {
                      
                  try
                  {
                        db.retrieveDBDataFromQuery(q,"UtilsPanelReport.retrievePrimKeyValueForOnePK "+STRTABLE_DBYEAR);
   	               if(db.getRecordCount()>0)
                       {
                            rs=db.getRS();
             
                    // System.out.println("gg.retrievePrimKeyValueForOnePK ("+entity+") ("+pk+")   primKeysDbField:"+primKeysDbField[pk]+"    primKeysDbFieldCaption"+primKeysDbFieldCaption[pk]+"    selectedTableRow:"+selectedTableRow+"   "+queryIn);
                   //System.out.println("gg.retrievePrimKeyValueForOnePK ("+pk+") Caption:"+primKeysDbFieldCaption[pk]+", DbField:"+primKeysDbField[pk]+" = "+primKeysDbFieldValue[pk]+" queryIn:"+queryIn);
                            rs = db.retrieveRow(rs, selectedTableRow);
                            if(rs.isAfterLast())
                            { 
                            }
                            else
                            { 
                                   primKeysDbFieldValue[pk] = rs.getString(primKeysDbField[pk]); // perhaps  primKeysDbFieldCaption   primKeysDbField
                 //System.out.println("UtilsPanelReport.retrievePrimKeyValueForOnePK ("+pk+") Caption:"+primKeysDbFieldCaption[pk]+", DbField:"+primKeysDbField[pk]+" = "+primKeysDbFieldValue[pk]);//+"   query q:"+q);              
                            }
                       
                       }
                 }
                 catch(SQLException sqlE)
                 {
                    System.out.println("Error UtilsPanelReport.retrievePrimKeyValueForOnePK() B  getErrorCode:"+ sqlE.getErrorCode()+"    "+sqlE.getMessage()+"   query q:"+q);
                    sqlE.printStackTrace();
                 }  
                      finally
                      {
                      closeDB();
                      }
                  }
                  else
                  {                  
                    primKeysDbFieldValue[pk]=VariablesGlobal.globalYearId;//  it was globalYear but was not  working with lookup, for farmersvat was not working with entity dbYearDelivery.
                  }                                                       // it appears when dbYearId column is not included in the read-only table 
//                    System.out.println("ccccc-->  UtilsPanelReport.retrievePrimKeyValueForOnePK("+pk+")    entity:"+entity+"="+STRTABLE_DBYEAR+"    "+primKeysDbField[pk]+"="+primKeysDbFieldValue[pk]);
                 closeDB(); 
              }
              else
              {
                  System.out.println(" ****  UtilsPanelReport.retrievePrimKeyValueForOnePK("+pk+") primKeysDbField[pk]:"+primKeysDbField[pk]+" PK not known");
              }
                  
              
             //System.out.println(" UtilsPanelReport.retrievePrimKeyValueForOnePK("+pk+") entity:"+entity+"="+primKeyTable[pk]+" primKeysDbField[pk]:"+primKeysDbField[pk]+" = "+primKeysDbFieldValue[pk]+" -"+utilsString.hasQuerySelectColumnName(q,STRFIELD_DBCOMPANYID)+"- PK not found in SELECT query but is a global variable.     q:"+q);
         
          }
          else
          { 
              //q=utilsString.removeCaptionsFromQuerySubStringSelect(q);
                db.retrieveDBDataFromQuery(q,"UtilsPanelReport.retrievePrimKeyValueForOnePK");
               int dbCount = db.getRecordCount();//dbFields.length;//db.getRecordCount();// listDbFieldsPKs.size();
                //System.out.println("       lb      UtilsPanelReport.retrievePrimKeyValueForOnePK ("+pk+")  entity:"+entity+"   db.getRecordCount():"+db.getRecordCount()+"     q:"+q);
                 rs=db.getRS();
                
                
               if(dbCount>0)
               {
                   //System.out.println("lb2 UtilsPanelReport.retrievePrimKeyValueForOnePK ("+pk+")  entity:"+entity+"   db.getRecordCount():"+db.getRecordCount()+"     q:"+q);
                  
             
                    // System.out.println("g.retrievePrimKeyValueForOnePK ("+entity+") ("+pk+")   primKeysDbField:"+primKeysDbField[pk]+"    primKeysDbFieldCaption"+primKeysDbFieldCaption[pk]+"    selectedTableRow:"+selectedTableRow+"   "+queryIn);
                   //System.out.println("g.retrievePrimKeyValueForOnePK ("+pk+") Caption:"+primKeysDbFieldCaption[pk]+", DbField:"+primKeysDbField[pk]+" = "+primKeysDbFieldValue[pk]+" queryIn:"+queryIn);
                  
                     try
                    {                  
                   if(rs.isAfterLast())
                   {
                       //rs.first();
                     //System.out.println("-llm UtilsPanelReport.retrievePrimKeyValueForOnePK ("+pk+")  entity:"+entity+"  RS.AFTERLAST  rs:"+rs+"   selectedTableRow:"+selectedTableRow+"     primKeysDbField:"+primKeysDbField[pk]+"     q:"+q);
                      // primKeysDbFieldValue[pk] = rs.getString(primKeysDbField[pk]); // perhaps  primKeysDbFieldCaption   primKeysDbField
                       
                   }
                   else
                   {  
                       //System.out.println("- - - - - * UtilsPanelReport.retrievePrimKeyValueForOnePK  count:("+dbCount+")     pk:"+pk+"    selectedTableRow:"+selectedTableRow+"       q:"+q);
                   

                      if(dbCount==1)
                      {
                            
                         rs.first();// for the only one, like in a form in lookup ie paymenttype second button 'edit' displays the selected record(th first one)
                        // System.out.println("- -*UtilsPanelReport.retrievePrimKeyValueForOnePK =1 (count:"+dbCount+") -("+pk+")- Caption:"+primKeysDbFieldCaption[pk]+", DbField:"+primKeysDbField[pk]+" = "+primKeysDbFieldValue[pk]+"  "+rs.getString(primKeysDbField[pk]));//+"   query q:"+q);              
                         primKeysDbFieldValue[pk] = rs.getString(primKeysDbField[pk]); // perhaps  primKeysDbFieldCaption   primKeysDbField                            
                      }
                      else
                      {
                         if(selectedTableRow==0)
                         {
                             selectedTableRow = 1;
                         }
                         else
                         {
                             
                         }
                         rs = db.retrieveRow(rs, selectedTableRow);   // the selected in a generatedareareport the click selects the right panel
                      
                         if(selectedTableRow>dbCount)
                         {
                           System.out.println("--*UtilsPanelReport.retrievePrimKeyValueForOnePK <>1 (count:"+dbCount+") -("+pk+")-    listDbFieldsPKs.size():"+listDbFieldsPKs.size()+"   db.getRecordCount():"+db.getRecordCount()+"   selectedTableRow:"+selectedTableRow+"      Caption:"+primKeysDbFieldCaption[pk]+",[ DbField:"+primKeysDbField[pk]+"] = "+primKeysDbFieldValue[pk]+"   rs:"+rs);//+"  "+rs.getString(primKeysDbField[pk])+"   query q:"+q);              
                           rs.first();  // used in re re calculation of periodiki vat
                         }
                        primKeysDbFieldValue[pk] = rs.getString(primKeysDbField[pk]); // perhaps  primKeysDbFieldCaption   primKeysDbField                                                        
                         
                      }
 
                    
                   }
                 }
                 catch(SQLException sqlE)
                 {
                       System.out.println("Error UtilsPanelReport.retrievePrimKeyValueForOnePK() C  getErrorCode:"+ sqlE.getErrorCode()+"    "+sqlE.getMessage()+"     selectedTableRow:"+selectedTableRow+"    query q:"+q);
                       sqlE.printStackTrace();
                 }                  // primKeysDbFieldValue[pk] = rs.getString(primKeysDbField[pk]);
                  // System.out.println("cbj UtilsPanelReport.retrievePrimKeyValueForOnePK ELSE ("+pk+")  entity:"+entity+"   db.getRecordCount():"+db.getRecordCount()+"   rs.isAfterLast()"+rs.isAfterLast()+"  value:"+primKeysDbFieldValue[pk]+"    q:"+q);
               }
               else
               {
                   
               }
               //closeDB();
    //    System.out.println("cf UtilsPanelReport.retrievePrimKeyValueForOnePK ("+pk+")  entity:"+entity+"  primKeysDbField:"+primKeysDbField[pk]+"="+primKeysDbFieldValue[pk]+"    db.getRecordCount():"+db.getRecordCount()+"    selectedTableRow:"+selectedTableRow+"    q:"+q);        
          }
        
         
                   
         
               //System.out.println(" NEW  UtilsPanelReport.retrievePrimKeyValueForOnePK ("+entity+") ("+pk+") primKeys[pk]:"+primKeysDbField[pk]+"  primKeysDbFieldCaption[pk]:"+primKeysDbFieldCaption[pk]+"="+primKeysDbFieldValue[pk]+"  queryIn:"+queryIn);
         //closeDB();
           }         
        }
        else if (listDbFieldsPKs.size()==0)
        {
           //System.out.println("    =================================  UtilsPanelReport.retrievePrimKeyValueForOnePK   entity:"+entity+"      pk size:"+listDbFieldsPKs.size()+"     primKeyDb:"+primKeyDb);
            if(primKeyDb!=null) // when in PanelMDMRec is called to choose before and after dialoglookup the id 
            {
                //System.out.println("     ==0      UtilsPanelReport.retrievePrimKeyValueForOnePK   entity:"+entity+"      pk size:"+listDbFieldsPKs.size()+"     primKeyDb:"+primKeyDb+"   q:"+q);
                        try
                          {
                              
   	                      db.retrieveDBDataFromQuery(q,/*utilsString.removeCaptionsFromQuerySubStringSelect(queryIn)*/"UtilsPanelReport.retrievePrimKeyValueForOnePK A ");
   	                      rs=db.getRS();
                              rs = db.retrieveRow(rs, selectedTableRow);
                             
                              if(rs.next())  // when there is a record in rs//  do NOT write rs.isFirst, is not correct when there are two records in the table
                              {    
                              // System.out.println("PanelODMRData.UtilsPanelReport A ELSE  entity:"+entity+"  primKeyDb:"+primKeyDb+"   queryIn:"+q);
                              primKeyValue =rs.getString(primKeyDb); //pkName); 
                             // System.out.println("PanelODMRData.UtilsPanelReport A ELSE  entity:"+entity+"      selectedTableRow:"+selectedTableRow+"     primKeyDb:"+primKeyDb+"   primKeyValue:"+primKeyValue);
                              }
                              else // when there is not a record in rs
                              {
                                  System.out.println("Error UtilsPanelReport.retrievePrimKeyValueForOnePK()  D  ELSE       primKeyDb:"+primKeyDb+"     rs:"+rs);
                              }
                          }
                          catch(SQLException sqlE)
                          {
                              System.out.println("Error UtilsPanelReport.retrievePrimKeyValueForOnePK()  E getErrorCode:"+ sqlE.getErrorCode()+"     equals 0     primKeyDb:"+primKeyDb+"   " + sqlE);
                              sqlE.printStackTrace();
                          }               
                        //closeDB();
            }  
            else
            {
                System.out.println(" =============== error UtilsPanelReport.retrievePrimKeyValueForOnePK   entity:"+entity+"      pk size:"+listDbFieldsPKs.size()+"     primKeyDb:"+primKeyDb+" = null");
            }
            
            
            
            
           
        }
        else
        {
            System.out.println("  error  +- UtilsPanelReport.retrievePrimKeyValueForOnePK primKeysCount ("+entity+") listDbFieldsPKs.size:"+listDbFieldsPKs.size());
        }

               //System.out.println("PanelODMRData.retrievePrimKeyValueForOnePK  ("+entity+")  primKeysDbFieldValue[0]"+primKeysDbFieldValue[0]);
              
               // if there is only one key
      // for backward compatibility after this line  
               // this value is forom entity, not db fields
        /*
         * 
         * // for backward compatibility after this line
         * 
         */
                    

        if(listDbFieldsPKs.size()>0)
        {
           for (int pk=0;pk<primKeysDbField.length;pk++)
           {         

                if(primKeyDb!=null && primKeysDbField[pk].equalsIgnoreCase(primKeyDb))  //    primKeyDb  or  primKeyTranslationIn
                {
                    primKeyValue =  primKeysDbFieldValue[pk];// or pk //tableModel.getValueAt(selectedTableRow,i).toString();
 //                   System.out.println(" -o-o-  UtilsPanelReport.retrievePrimKeyValueForOnePK IF ("+pk+")     primKeyValue:"+primKeyValue+"     primKeyDb:"+primKeyDb+"      "+primKeysDbField[pk]+"="+primKeysDbFieldValue[pk]);
                }
                else // when 
                {

                    //if(primKeysDbField[pk].equalsIgnoreCase("dbCompanyId"))
 //                   System.out.println("  -  UtilsPanelReport.retrievePrimKeyValueForOnePK ELSE ("+pk+")    primKeyDb:"+primKeyDb+"   primKeysDbField:"+primKeysDbField[pk]+"="+primKeysDbFieldValue[pk]);
                }
                //primKeys[pk] = primKeysDbField[pk];
                //primKeysValue[pk]=primKeysDbFieldValue[pk];
           }
        }
        else
        {            
           /* if(primKeyValue==null && primKeyDb!=null) // when in PanelMDMRec is called to show dialoglookup
            {
                        try
                          {
                              
   	                      db.retrieveDBDataFromQuery(q,"UtilsPanelReport.retrievePrimKeyValueForOnePK B ");
   	                      rs=db.getRS();
                              rs = db.retrieveRow(rs, selectedTableRow+1);
                             // System.out.println("PanelODMRData.UtilsPanelReport A ELSE  entity:"+entity+"  primKeyDb:"+primKeyDb+"   queryIn:"+q);
                              primKeyValue =rs.getString(primKeyDb); //pkName); 
                              System.out.println("PanelODMRData.UtilsPanelReport B ELSE  entity:"+entity+"    primKeyDb:"+primKeyDb+"   primKeyValue:"+primKeyValue);
                          }
                          catch(SQLException sqlE)
                          {
                              System.out.println("Error UtilsPanelReport.retrievePrimKeyValueForOnePK()  B " + sqlE);
                              sqlE.printStackTrace();
                          }     
                        closeDB();
            } */
            
            //System.out.println("error - UtilsPanelReport.retrievePrimKeyValueForOnePK primKeysCount   A  ("+entity+")    selectedTableRow:"+selectedTableRow+" listDbFieldsPKs.size:"+listDbFieldsPKs.size());
        }
   //     System.out.println("UtilsPanelReport.retrievePrimKeyValueForOnePK IF   listDbFieldsPKs.size()"+listDbFieldsPKs.size()+"    entity:"+entity+"     selectedTableRow:"+selectedTableRow+"     primKeyDb:"+primKeyDb+" primKeyValue:"+primKeyValue);
  }
  else
  {
         /*for (int pk=0;pk<primKeysCount;pk++)
         {
             
    
             	//primKeyDb
            	String pkName;
             	//System.out.println("PanelODMRData.retrievePrimKeyValue isEditable "+isEditable);
             	if (primKeyTranslationIn==null)
             	{
             	  pkName=databaseTableMeta.getPrimKeyName(pk);//before
             	  //System.out.println("UtilsPanelReport.retrievePrimKeyValueForOnePK  "+entity+" --- "+pk+" "+pkName+"-"+primKeyIn);
              	}
             	else
             	{
                  pkName=primKeyTranslationIn;// so translation be equal with translation
                  //System.out.println("UtilsPanelReport.retrievePrimKeyValueForOnePK  "+entity+" "+pk+" "+pkName);
                }      */
                          try
                          {
   	                      db.retrieveDBDataFromQuery(queryIn,"UtilsPanelReport.retrievePrimKeyValueForOnePK C");
   	                      rs=db.getRS();
                              rs = db.retrieveRow(rs, selectedTableRow+1);
                              System.out.println("UtilsPanelReport.retrievePrimKeyValueForOnePK ELSE   C1  entity:"+entity+"  primKeyDb:"+primKeyDb+" selectedTableRow:"+(selectedTableRow+1));
                              primKeyValue =rs.getString(primKeyDb); //pkName); 
                              System.out.println("UtilsPanelReport.retrievePrimKeyValueForOnePK ELSE   C  entity:"+entity+"    selectedTableRow:"+selectedTableRow+"     primKeyDb:"+primKeyDb+"    primKeyValue:"+primKeyValue);
                          closeDB();
                          }
                          catch(SQLException sqlE)
                          {
                              System.out.println("Error UtilsPanelReport.retrievePrimKeyValueForOnePK() F  getErrorCode:"+ sqlE.getErrorCode()+"    queryIn:"+queryIn+"     " + sqlE);
                              sqlE.printStackTrace();
                          }                          
         
  }
      
   
     closeDB();
     //System.out.println("  UtilsPanelReport.retrievePrimKeyValueForOnePK  " +primKeys[0]+" "+primKeysValue[0]);
   }   


  /* calculate the last no of the PK that the db has, add 1 and get it
   * 
   * called by PanelODORData and TableModelResultSet.addEmptyRow
   */
  public String getNoOfPKAutoIncOfNewRecord(boolean isDoNotAddOne,EntityDBFields[] dbFieldsAll,String entity, JTable tableIn,String pkValueFromParentTable, int rowsAddedInJtableBeforeSavedInDB)// isDoNotAddOne when in update of second and more panel ofODORData, 
  {
  	 //databaseTableMeta.retrievePrimKs(entity);
  	 
     //String lastNo="0";
     int intLastNo=0;
     //int intCountOfJTableLines = 0;
     
     int returnNumber=0;
  	 String primkeyAutoInc="";
         String[] primkeyFixedArray;// like companyId
         String[] primkeyFixedValueArray;
         int intPrimkeyFixed=0;
 
         for(int c=0;c<dbFieldsAll.length;c++)
  	 {
                if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY)
                {
                    intPrimkeyFixed++;
                }         
         }
         
                    primkeyFixedArray = new String[intPrimkeyFixed+1];
                    primkeyFixedValueArray = new String[intPrimkeyFixed+1];
         intPrimkeyFixed=0;           
  	 for(int c=0;c<dbFieldsAll.length;c++)
  	 {

                if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC)
                {
                    primkeyAutoInc = dbFieldsAll[c].getDbField();
                    System.out.println("UtilPanelReport.getNoOfPKAutoIncOfNewRecord  FIELD_PRIMARY_KEY_AUTOINC c:"+c+"  entity:"+entity+"         primkeyAutoInc:"+primkeyAutoInc+" ");
                    //primKeyValue=intPrimkeyFixed;
                    //primkeyFixedArray[intPrimkeyFixed]=dbFieldsAll[c].getDbField();
                    //primkeyFixedValueArray[intPrimkeyFixed]=dbFieldsAll[c].getDefaultValue();
                    //System.out.println("primkeyFixedArray:"+primkeyFixedArray[intPrimkeyFixed]+"="+primkeyFixedValueArray[intPrimkeyFixed]+"  "+intPrimkeyFixed);
                    //intPrimkeyFixed++;
                }
                else if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY)
                {
                    primkeyFixedArray[intPrimkeyFixed]=dbFieldsAll[c].getDbField();
                    primkeyFixedValueArray[intPrimkeyFixed]=dbFieldsAll[c].getDefaultValue();
 //                   System.out.println("   -ELSE-   UtilPanelReport.getNoOfPKAutoIncOfNewRecord  c:"+c+"   FIELD_PRIMARY_KEY   primkeyFixedArray:"+primkeyFixedArray[intPrimkeyFixed]+"="+primkeyFixedValueArray[intPrimkeyFixed]+"  "+intPrimkeyFixed);
                    intPrimkeyFixed++;
                }
                else if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_FROM_PARENTTABLE)// 
                {
                    
                    
                     //retrievePrimKeyValueForOnePK( queryIn,  selectedTableRow,  dbFields, dbFieldsMany, isMany, entity, primKeyDb)   ;                 
                    
                    
                    
                    //primkeyAutoInc  = dbFieldsAll[c].getDbField();  
                    primkeyFixedArray[intPrimkeyFixed] = dbFieldsAll[c].getDbField();
                   // int intPkValueFromParentTable = Integer.parseInt(pkValueFromParentTable);
                    primkeyFixedValueArray[intPrimkeyFixed] = pkValueFromParentTable;//  intPkValueFromParentTable;//getPrimKeyValue();//dbFieldsAll[c].getDefaultValue();          // TO DO: CHANGE IT         
                    
                    //primkeyAutoInc = dbFieldsAll[c].getDbField();
                  System.out.println("     -ELSE-   UtilPanelReport.getNoOfPKAutoIncOfNewRecord   intPrimkeyFixed:"+intPrimkeyFixed+" c:"+c+"  FIELD_PRIMARY_KEY_FROM_PARENTTABLE  entity:"+entity+"    primkeyFixedArray:"+primkeyFixedArray[intPrimkeyFixed]+"="+primkeyFixedValueArray[intPrimkeyFixed]+"   dbFieldsAll[c].getPrimaryKeyIntegerAutoInc():"+dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()+"     primkeyAutoInc:"+primkeyAutoInc+"   intPrimkeyFixed:"+intPrimkeyFixed+"    primKeyValue:"+primKeyValue);
                   intPrimkeyFixed++;
                }
                else if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()!=FIELD_NORMAL_NO_PRIMARY_KEY)
                {
                    //System.out.println("error ELSE NOT DEFINED UtilPanelReport.getNoOfPKAutoIncOfNewRecord  c:"+c+"         entity:"+entity+"  NOT-->  FIELD_NORMAL_NO_PRIMARY_KEY   dbFieldsAll[c].getPrimaryKeyIntegerAutoInc():"+dbFieldsAll[c].getPrimaryKeyIntegerAutoInc());
                }
                else
                {
                     //System.out.println("error ELSE NOT DEFINED UtilPanelReport.getNoOfPKAutoIncOfNewRecord  c:"+c+"           entity:"+entity+"   PkType:"+dbFieldsAll[c].getPrimaryKeyIntegerAutoInc());
                }
                
  	 }
         
         
        
  	//System.out.println("PanelODORData.getNoOfPKOfNewRecord ===> "+entity+"  dbFieldsAll.length("+dbFieldsAll.length+")    primkeyAutoInc:"+primkeyAutoInc+"    primkeyFixed:"+intPrimkeyFixed+"       primKeyValue:"+primKeyValue);

        
    
        if(!primkeyAutoInc.equalsIgnoreCase(""))
  	 {
             String sql="";
             String sqlWhere="";
             if(intPrimkeyFixed>0)
             {
             for(int k=0;k<intPrimkeyFixed;k++)
             {
                 if(primkeyFixedArray[k]!= null && !primkeyFixedArray[k].equalsIgnoreCase(""))
                 {
                     if(k==0)
                     {
                       sqlWhere=sqlWhere+primkeyFixedArray[k]+" LIKE "+primkeyFixedValueArray[k];
                     }
                     else
                     {
                         sqlWhere=sqlWhere+" AND "+primkeyFixedArray[k]+" LIKE "+primkeyFixedValueArray[k];
                     }
                 }
             }
             }
             
             if(intPrimkeyFixed>0)
             {
                 sql = "SELECT "+primkeyAutoInc+" FROM "+entity+" WHERE "+sqlWhere+" ORDER BY "+primkeyAutoInc;
                 
             }
             else
             {
                 sql = "SELECT "+primkeyAutoInc+" FROM "+entity+" ORDER BY "+primkeyAutoInc;
             }
         // System.out.println("UtilsPanelReport.getNoOfPKOfNewRecord    -    primkeyAutoInc:"+primkeyAutoInc+"   intPrimkeyFixed:"+intPrimkeyFixed+"    intPrimkeyFixed:"+intPrimkeyFixed+"         sql:"+sql);
  	 	
  	    db.retrieveDBDataFromQuery(sql,"UtilsPanelReport.getNoOfPKOfNewRecord");
   	    ResultSet rspk=db.getRS();
        //ResultSetMetaData rsmdpk=db.getRSMetaData();
        try
        {
            while (rspk.next())
            {  
        	if(rspk.last())
        	{
        	  //lastNo=rspk.getString(1);
                  intLastNo = rspk.getInt(1);
        	  
                } 	      	 	
  	 	
  	    }
             
        }
  	catch(SQLException e)
  	{
  	 	System.out.println("error  PanelODORData.getNoOfPKOfNewRecord   sql:"+sql+"    "+e.getMessage());
                if(VariablesGlobal.globalShowPrintStackTrace)
                {
                    e.printStackTrace();
                }
  	}
        finally
        {
            closeDB();
        }
            
  	}
        
        //intCountOfJTableLines = tableIn.getRowCount();

        
        //intLastNo=Integer.parseInt(lastNo.toString());  
        if(tableIn == null)// when called by PanelODORData.rowSaveAll
        {
            intLastNo++;
            returnNumber = intLastNo;
        }
        else // when called by TableModelResultSet.addEmptyRow
        {
            int intColumnAutoInc = -1;
                for(int c=0;c<dbFieldsAll.length;c++)
  	        {            
                   if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC)
                   {
                       intColumnAutoInc=c;
                   }            
                }
            
            for (int r= 0 ; r<tableIn.getRowCount();r++)
            {
                
                int jTablePkAutoInc = Integer.parseInt(tableIn.getValueAt(r, intColumnAutoInc).toString());
                returnNumber = intLastNo + rowsAddedInJtableBeforeSavedInDB;
                if(returnNumber == jTablePkAutoInc)
                {
                    returnNumber++;
                }    
            }
            
            if(tableIn.getRowCount()==0)// when in jtable is added the first row, (the previous 'for' calculates 'returnNumber' if there is a row, else remains '0' )
            {
                returnNumber=1;
            }
                    //System.out.println(" ELSE  PanelODORData.getNoOfPKOfNewRecord          primkeyAutoInc:"+primkeyAutoInc+"       intLastNo:"+intLastNo+"           returnNumber:"+returnNumber+"       rowsAddedInJtableBeforeSavedInDB:"+rowsAddedInJtableBeforeSavedInDB);
        }
        
        System.out.println("  +++  PanelODORData.getNoOfPKOfNewRecord      returnNumber:"+returnNumber +"    before->    isDoNotAddOne:"+isDoNotAddOne);
         if(isDoNotAddOne)
         {
               if(returnNumber>0)             
               {
                   returnNumber--;
               }
                  
         }
         else
         {
             
         }        
        
        /*else if(tableIn!=null && intLastNo == intCountOfJTableLines)   
        {        
            
           //lastNo =  tableIn.getRowCount()+"";
           returnNumber=intCountOfJTableLines+1; // for table with 'inc' panelODMRec
            System.out.println("  - -- - - - ELSE = PanelODORData.getNoOfPKOfNewRecord            primkeyAutoInc:"+primkeyAutoInc+"       intLastNo:"+intLastNo+"  =  intCountOfJTableLines:"+intCountOfJTableLines+"        returnNumber:"+returnNumber+"       rowsAddedInJtableBeforeSavedInDB:"+rowsAddedInJtableBeforeSavedInDB);
        }        
        else if(tableIn!=null && intLastNo < intCountOfJTableLines)   
        {        
            
           //lastNo =  tableIn.getRowCount()+"";
           returnNumber=intCountOfJTableLines; // for table with 'inc' panelODMRec
            System.out.println("  - -- - - - ELSE < PanelODORData.getNoOfPKOfNewRecord            primkeyAutoInc:"+primkeyAutoInc+"       intLastNo:"+intLastNo+"  <  intCountOfJTableLines:"+intCountOfJTableLines+"        returnNumber:"+returnNumber+"       rowsAddedInJtableBeforeSavedInDB:"+rowsAddedInJtableBeforeSavedInDB);
        }
        else if(tableIn!=null && intLastNo > intCountOfJTableLines)   
        {
           // lastNo =  (tableIn.getRowCount()+1)+"";
            returnNumber = intLastNo;
             
            
            
            System.out.println("  - - - - - - ELSE > PanelODORData.getNoOfPKOfNewRecord          primkeyAutoInc:"+primkeyAutoInc+"       intLastNo:"+intLastNo+" >  intCountOfJTableLines:"+intCountOfJTableLines+"        returnNumber:"+returnNumber+"       rowsAddedInJtableBeforeSavedInDB:"+rowsAddedInJtableBeforeSavedInDB);
        }
        else
        {
            System.out.println("  - - - - - - ELSE LAST PanelODORData.getNoOfPKOfNewRecord              primkeyAutoInc:"+primkeyAutoInc+"       intLastNo:"+intLastNo+" o   intCountOfJTableLines:"+intCountOfJTableLines+"        returnNumber:"+returnNumber+"       rowsAddedInJtableBeforeSavedInDB:"+rowsAddedInJtableBeforeSavedInDB);
        }*/
       
        
  	 //returnNumber = intLastNo;//Integer.parseInt(lastNo);
  	 //System.out.println("OOOOO UtilsPanelReport.getNoOfPKOfNewRecord                                         returnNumber"+returnNumber);

        
         
  	 return returnNumber+"";	  
  }



  /* calculate the last no of the PK that the db has, add 1 and get it
   * 
   * called by PanelODORData and TableModelResultSet.addEmptyRow
   */
 /* public String getNoOfPKAutoIncOfNewRecord_todelete_(boolean isDoNotAddOne,EntityDBFields[] dbFieldsAll,String entity)// isDoNotAddOne when in update of second and more panel ofODORData, 
  {
  	 //databaseTableMeta.retrievePrimKs(entity);
  	 
     String lastNo="0";
  	 String primkeyAutoInc="";
         String[] primkeyFixedArray;// like companyId
         String[] primkeyFixedValueArray;
         int intPrimkeyFixed=0;
 
         for(int c=0;c<dbFieldsAll.length;c++)
  	 {
                if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY)
                {
                    intPrimkeyFixed++;
                }         
         }
         
                    primkeyFixedArray = new String[intPrimkeyFixed+1];
                    primkeyFixedValueArray = new String[intPrimkeyFixed+1];
         intPrimkeyFixed=0;           
  	 for(int c=0;c<dbFieldsAll.length;c++)
  	 {

                if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC)
                {
                    primkeyAutoInc = dbFieldsAll[c].getDbField();
                    System.out.println("UtilPanelReport.getNoOfPKAutoIncOfNewRecord  FIELD_PRIMARY_KEY_AUTOINC c:"+c+"  entity:"+entity+"         primkeyAutoInc:"+primkeyAutoInc+" ");
                
                    //primkeyFixedArray[intPrimkeyFixed]=dbFieldsAll[c].getDbField();
                    //primkeyFixedValueArray[intPrimkeyFixed]=dbFieldsAll[c].getDefaultValue();
                    //System.out.println("primkeyFixedArray:"+primkeyFixedArray[intPrimkeyFixed]+"="+primkeyFixedValueArray[intPrimkeyFixed]+"  "+intPrimkeyFixed);
                    //intPrimkeyFixed++;
                }
                else if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY)
                {
                    primkeyFixedArray[intPrimkeyFixed]=dbFieldsAll[c].getDbField();
                    primkeyFixedValueArray[intPrimkeyFixed]=dbFieldsAll[c].getDefaultValue();
                    System.out.println("   -ELSE-   UtilPanelReport.getNoOfPKAutoIncOfNewRecord  c:"+c+"   FIELD_PRIMARY_KEY   primkeyFixedArray:"+primkeyFixedArray[intPrimkeyFixed]+"="+primkeyFixedValueArray[intPrimkeyFixed]+"  "+intPrimkeyFixed);
                    intPrimkeyFixed++;
                }
                else if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_FROM_PARENTTABLE)// 
                {
                    //primkeyAutoInc  = dbFieldsAll[c].getDbField();  
                    primkeyFixedArray[intPrimkeyFixed] = dbFieldsAll[c].getDbField();
                    primkeyFixedValueArray[intPrimkeyFixed] =  dbFieldsAll[c].getDefaultValue();          // TO DO: CHANGE IT         
                    
                    //primkeyAutoInc = dbFieldsAll[c].getDbField();
                  System.out.println("     -ELSE-   UtilPanelReport.getNoOfPKAutoIncOfNewRecord   intPrimkeyFixed:"+intPrimkeyFixed+" c:"+c+"  FIELD_PRIMARY_KEY_FROM_PARENTTABLE  entity:"+entity+"    primkeyFixedArray:"+primkeyFixedArray[intPrimkeyFixed]+"="+primkeyFixedValueArray[intPrimkeyFixed]+"   dbFieldsAll[c].getPrimaryKeyIntegerAutoInc():"+dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()+"     primkeyAutoInc:"+primkeyAutoInc+"   intPrimkeyFixed:"+intPrimkeyFixed+"    primKeyValue:"+primKeyValue);
                   intPrimkeyFixed++;
                }
                else if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()!=FIELD_NORMAL_NO_PRIMARY_KEY)
                {
                    //System.out.println("error ELSE NOT DEFINED UtilPanelReport.getNoOfPKAutoIncOfNewRecord  c:"+c+"         entity:"+entity+"  NOT-->  FIELD_NORMAL_NO_PRIMARY_KEY   dbFieldsAll[c].getPrimaryKeyIntegerAutoInc():"+dbFieldsAll[c].getPrimaryKeyIntegerAutoInc());
                }
                else
                {
                     //System.out.println("error ELSE NOT DEFINED UtilPanelReport.getNoOfPKAutoIncOfNewRecord  c:"+c+"           entity:"+entity+"   PkType:"+dbFieldsAll[c].getPrimaryKeyIntegerAutoInc());
                }
                
  	 }
         
         
         
  	System.out.println("========= PanelODORData.getNoOfPKOfNewRecord ===> "+entity+"  dbFieldsAll.length("+dbFieldsAll.length+")    primkeyAutoInc:"+primkeyAutoInc+"    primkeyFixed:"+intPrimkeyFixed+"       primKeyValue:"+primKeyValue);
  	 if(!primkeyAutoInc.equalsIgnoreCase(""))
  	 {
             String sql="";
             String sqlWhere="";
             if(intPrimkeyFixed>0)
             {
             for(int k=0;k<intPrimkeyFixed;k++)
             {
                 if(primkeyFixedArray[k]!= null && !primkeyFixedArray[k].equalsIgnoreCase(""))
                 {
                     if(k==0)
                     {
                       sqlWhere=sqlWhere+primkeyFixedArray[k]+" LIKE "+primkeyFixedValueArray[k];
                     }
                     else
                     {
                         sqlWhere=sqlWhere+" AND "+primkeyFixedArray[k]+" LIKE "+primkeyFixedValueArray[k];
                     }
                 }
             }
             }
             
             if(intPrimkeyFixed>0)
             {
                 sql = "SELECT "+primkeyAutoInc+" FROM "+entity+" WHERE "+sqlWhere+" ORDER BY "+primkeyAutoInc;
                 
             }
             else
             {
                 sql = "SELECT "+primkeyAutoInc+" FROM "+entity+" ORDER BY "+primkeyAutoInc;
             }
          System.out.println(" ---- * * * ----- UtilsPanelReport.getNoOfPKOfNewRecord    -    primkeyAutoInc:"+primkeyAutoInc+"   intPrimkeyFixed:"+intPrimkeyFixed+"    intPrimkeyFixed:"+intPrimkeyFixed+"      sql:"+sql);
  	 	
  	    db.retrieveDBDataFromQuery(sql,"UtilsPanelReport.getNoOfPKOfNewRecord");
   	    ResultSet rspk=db.getRS();
        //ResultSetMetaData rsmdpk=db.getRSMetaData();
        try
        {
        while (rspk.next())
        {  
        	if(rspk.last())
        	{
        	  lastNo=rspk.getString(1);
        	} 	      	 	
  	 	
  	 	}
        }
  	 	catch(SQLException e)
  	 	{
  	 		System.out.println("PanelODORData.getNoOfPKOfNewRecord "+e.getMessage());
  	 	}
  	 }
  	 int no = Integer.parseInt(lastNo);
  	 //System.out.println("PanelODORData.getNoOfPKOfNewRecord   no"+no);
         if(isDoNotAddOne)
         {
             //no;
         }
         else
         {
             no++;
         }
         closeDB();
         
  	 return no+"";	  
  }*/

  private void closeDB()
  {
    db.releaseConnectionRs();
    db.releaseConnectionRsmd();
  }

   
   //from PanelOneDataManyRecData
  public String[] getPrimKeys()
  {
      //System.out.println("UtilsPanelReport.getPrimKeys ------ "+primKeys[0]+"="+primKeysValue[0]+" "+primKeys[1]+"="+primKeysValue[1]);
  	 return primKeysDbField;
  }

  public String[] getPrimKeysCaption()
  {  
    return primKeysDbFieldCaption;
  }

   //from PanelOneDataManyRecData
  public String[] getPrimKeysValue()
  {
  	return primKeysDbFieldValue;
  }
 
   //from PanelOneDataManyRecData
  // gets data from retrievePrimKeyValueForOnePK
  public String getPrimKeyValue()
  {   
      
      if(primKeyValue == null || primKeyValue.equalsIgnoreCase(""))
      {
          System.out.println("    ERROR       UtilsPanelReport.getPrimKeyValue    primKeyValue:"+primKeyValue);
      }
      else
      {
          
      }
      
  	 return primKeyValue;
  }
    
   public String getWhereValueNameThatMatchesColumn(String columnLabel, String[] sql2WhereField, String[] sql2WhereValue)
   { 
     String whereValueName="-"; 
     if(sql2WhereField!=null)
     {
        for(int i=0;i<sql2WhereField.length;i++)
        {
           //System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereField[i]);
           /*if(sql2WhereValue[i].regionMatches(true,0,word,0,word.length()))   	          
           {
           	  */ //System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereValue[i]+" matches global");
      	       if(sql2WhereField[i].equalsIgnoreCase(columnLabel))
      	       {
      	       	//System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereValue[i]+" matches global");
      	          whereValueName=sql2WhereValue[i]	;
      	       }
      	  /* }*/
        }
      }
   	   return whereValueName;
   }

   //  is getted from panelODMRData
    public String getColDescriptionValue()
    {    
      return colDescriptionValue;
    }

  
    public String getSubQueryOrderByForPreferences(String name )
  {
 	     
  	   String ret ="";
  	   XMLReader reader = new XMLReader();
   String[] tagElements ={"name"};
   String[] tagElementsType ={"String"};    	
    	
     if(reader.checkIfElementExists(filePrefs, "Table",tagElements,tagElementsType,name))
     {
     	
     	// load file
   	       XMLReader reader2 = new XMLReader();
          String[] tagElements2 ={"name","sqlOrderBy"};
          String[] tagElementsType2 ={"String", "String"};  
  	           // 1 is the second -> sqlOrderBy
  	      ret =  reader2.getValueFromXmlElement(filePrefs, "Table",tagElements2,tagElementsType2,1,name);     	
     	//System.out.println("PanelOneDataManyRecData.loadQueryAndMetaDataForTablePreferences load file for "+name + ret);
     	
     }
     else
     {
     	//System.out.println("PanelOneDataManyRecData.loadQueryAndMetaDataForTablePreferences get prefs from app for "+name);
     	//get prefs from app
     }   
 
     return ret;
  	
  }  

    
    public int loadDataFromXmlFileRetInt(String filePrefs,String elementCategory,String[] tagElements,String[] tagElementsType, int lookForElement,String elementName)
  {
 	     
  	   //String ret ="";
  	   XMLReader reader = new XMLReader();
           int intAction=-1;
           String strAction="";
  // String[] tagElements ={"name"};
  // String[] tagElementsType ={"String"};    	
    	
     if(reader.checkIfElementExists(filePrefs, elementCategory,tagElements,tagElementsType,elementName))
     {
     	
     	// load file
   	       XMLReader reader2 = new XMLReader();
          //String[] tagElements2 ={"name","intsOrderBy"};
          //String[] tagElementsType2 ={"String", "String"};  
  	           // 1 is the second -> sqlOrderBy
  	    //System.out.println("1UtilsPanelReport.loadDataFromXmlFileRetIntArray :"+elementCategory+" :"+tagElements[0]+" :"+tagElements[1]+" :"+elementCategory+" :" + lookForElement+" :"+elementName);
  	    strAction = reader2.getValueFromXmlElement(filePrefs, elementCategory,tagElements,tagElementsType,lookForElement,elementName);
     	    intAction = Integer.parseInt(strAction.toString());
            //System.out.println("--2UtilsPanelReport.loadDataFromXmlFileRetIntArray "+elementCategory+" "+tagElements[0]+" "+tagElements[1]+" "+elementCategory+" " + lookForElement+" "+elementName+"  allIntsOrderBy"+allIntsOrderBy);
	
     	//System.out.println("PanelOneDataManyRecData.loadQueryAndMetaDataForTablePreferences load file for "+name + ret);
     	
     }
     else
     {
     	//System.out.println("PanelOneDataManyRecData.loadQueryAndMetaDataForTablePreferences get prefs from app for "+name);
     	//get prefs from app
     }   
 
     return intAction;
  	
  }      
    
 
  public String[] loadDataFromXmlFileRetStringArray(String filePrefs,String elementCategory,String[] tagElements,String[] tagElementsType, int lookForElement,String elementName)
  {
  	
     	String allIntsOrderBy="";
   	       XMLReader reader2 = new XMLReader();
          //String[] tagElements2 ={"name","intsOrderBy"};
          //String[] tagElementsType2 ={"String", "String"};  
  	           // 1 is the second -> sqlOrderBy
  	    //System.out.println("1UtilsPanelReport.loadDataFromXmlFileRetIntArray :"+elementCategory+" :"+tagElements[0]+" :"+tagElements[1]+" :"+elementCategory+" :" + lookForElement+" :"+elementName);
  	    allIntsOrderBy = reader2.getValueFromXmlElement(filePrefs, elementCategory,tagElements,tagElementsType,lookForElement,elementName);
     	    //System.out.println("--2UtilsPanelReport.loadDataFromXmlFileRetIntArray "+elementCategory+" "+tagElements[0]+" "+tagElements[1]+" "+elementCategory+" " + lookForElement+" "+elementName+"  allIntsOrderBy"+allIntsOrderBy);

            ArrayList lstCommas = utilsString.getIndicesOf(allIntsOrderBy,",");
            String[] ret = new String[lstCommas.size()];
           
            int previous=0;
            for(int l = 0; l<lstCommas.size();l++)
            {
            	//System.out.println("DialogDataConfig.setEntity "+previous+"-"+lstCommas.get(l)+" "+allIntsOrderBy.substring(previous,Integer.parseInt(lstCommas.get(l).toString())));
                ret[l] = allIntsOrderBy.substring(previous,Integer.parseInt(lstCommas.get(l).toString())).toString();
              	previous=Integer.parseInt(lstCommas.get(l).toString())+1;	
            }  	
            	
          //  System.out.println("UtilsPanelReport.loadDataFromXmlFileRetIntArray ---"+ret);
  	return ret;
  }    
    
    
    
  public int[] loadDataFromXmlFileRetIntArray(String filePrefs,String elementCategory,String[] tagElements,String[] tagElementsType, int lookForElement,String elementName)
  {
  	
     	String allIntsOrderBy="";
   	       XMLReader reader2 = new XMLReader();
          //String[] tagElements2 ={"name","intsOrderBy"};
          //String[] tagElementsType2 ={"String", "String"};  
  	           // 1 is the second -> sqlOrderBy
  	    //System.out.println("1UtilsPanelReport.loadDataFromXmlFileRetIntArray :"+elementCategory+" :"+tagElements[0]+" :"+tagElements[1]+" :"+elementCategory+" :" + lookForElement+" :"+elementName);
  	    allIntsOrderBy = reader2.getValueFromXmlElement(filePrefs, elementCategory,tagElements,tagElementsType,lookForElement,elementName);
     	    //System.out.println("--2UtilsPanelReport.loadDataFromXmlFileRetIntArray "+elementCategory+" "+tagElements[0]+" "+tagElements[1]+" "+elementCategory+" " + lookForElement+" "+elementName+"  allIntsOrderBy"+allIntsOrderBy);

            ArrayList lstCommas = utilsString.getIndicesOf(allIntsOrderBy,",");
            int[] ret = new int[lstCommas.size()];
           
            int previous=0;
            for(int l = 0; l<lstCommas.size();l++)
            {
            	//System.out.println("DialogDataConfig.setEntity "+previous+"-"+lstCommas.get(l)+" "+allIntsOrderBy.substring(previous,Integer.parseInt(lstCommas.get(l).toString())));
                ret[l] = Integer.parseInt(allIntsOrderBy.substring(previous,Integer.parseInt(lstCommas.get(l).toString())).toString());
              	previous=Integer.parseInt(lstCommas.get(l).toString())+1;	
            }  	
            	
          //  System.out.println("UtilsPanelReport.loadDataFromXmlFileRetIntArray ---"+ret);
  	return ret;
  }


  public boolean[] loadDataFromXmlFileRetBool(String filePrefs,String elementCategory,String[] tagElements,String[] tagElementsType, int lookForElement,String elementName)
  {
  	
     	String allIntsOrderBy="";
   	       XMLReader reader2 = new XMLReader();

  	           // 1 is the second -> sqlOrderBy
  	    allIntsOrderBy =  reader2.getValueFromXmlElement(filePrefs, elementCategory,tagElements,tagElementsType,lookForElement,elementName);     	
      //System.out.println("UtilsPanelReport.loadDataFromXmlFileRetBool load file for elementName:"+elementName + "  allIntsOrderBy:"+allIntsOrderBy);

            ArrayList lstCommas = utilsString.getIndicesOf(allIntsOrderBy,",");
            boolean[] ret = new boolean[lstCommas.size()];
           
            int previous=0;
            for(int l = 0; l<lstCommas.size();l++)
            {
            	//System.out.println("DialogDataConfig.setEntity "+previous+"-"+lstCommas.get(l)+" "+allIntsOrderBy.substring(previous,Integer.parseInt(lstCommas.get(l).toString())));
                ret[l] = Boolean.parseBoolean(allIntsOrderBy.substring(previous,Integer.parseInt(lstCommas.get(l).toString())).toString());
              	previous=Integer.parseInt(lstCommas.get(l).toString())+1;	
            }  	
  	return ret;
  }

 
  

  
   /* from ParentDBFields
    *   exists differentiated with differences both in PanelODoRData and panelODMRData
    */
    public int calculateAllFieldsFromParentDBFieldsForFormVariable1(EntityDBFields[] dbFieldsInGroupOfPanels)
    {
        
      ArrayList listDbFieldsAll = new ArrayList();
      int intFieldToGetTheValue = -1;
     
      if(dbFieldsInGroupOfPanels!=null)
      {
      for(int f= 0 ;f<dbFieldsInGroupOfPanels.length;f++)
      {
        // System.out.println("UtilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1     f:"+f+"       "+dbFieldsInGroupOfPanels[f].getCaption()+"   class:"+dbFieldsInGroupOfPanels[f].getColClassName());   
          listDbFieldsAll.add(dbFieldsInGroupOfPanels[f]);
          if(dbFieldsInGroupOfPanels[f].getColClassName().equalsIgnoreCase("table"))
          {
    
              //int lenOfTableFields = dbFieldsInGroupOfPanels[f].getDbChildFields().length;
             EntityDBFields[] childDBFields = dbFieldsInGroupOfPanels[f].getDbChildFields();
             int lenOfTableFields = childDBFields.length;
            for(int t = 0;t<lenOfTableFields;t++)
            {
                 //  System.out.println("panelODMRData.calculateAllFieldsForFormVariable1 t:"+t+"  "+childDBFields[t].getDbField());
                   listDbFieldsAll.add(childDBFields[t]);
            }
          }
      }
      }
      else
      {
         System.out.println(" error UtilsPanelReport  you need to define an EntityGroupOfPanels");
      }
      
      
      for(int l =0 ;l<listDbFieldsAll.size();l++)
      {
          EntityDBFields cfields = (EntityDBFields)listDbFieldsAll.get(l);
          //System.out.println("panelODMRData.calculateAllFieldsForFormVariable1        listDbFieldsAll     l:"+l+"    getTableName:"+cfields.getTableName()+" getDbField:"+cfields.getDbField()+"   -   "+cfields.getFormVariableFromField());
      }
      
      EntityDBFields[] dbFieldsAll = new EntityDBFields[listDbFieldsAll.size()];
      for(int l =0 ;l<listDbFieldsAll.size();l++)
      {
          dbFieldsAll[l] = (EntityDBFields)listDbFieldsAll.get(l);
          //System.out.println("panelODMRData.calculateAllFieldsForFormVariable1        listDbFieldsAll     l:"+l+"    getTableName:"+cfields.getTableName()+" getDbField:"+cfields.getDbField()+"   -   "+cfields.getFormVariableFromField());
      }      
      
      
      intFieldToGetTheValue = calculateAllFieldsFromDBFieldsAllForFormVariable1(dbFieldsAll);
       return intFieldToGetTheValue;     
    }
    
    
    /*
    * from DBFieldsAll
    */
    public int calculateAllFieldsFromDBFieldsAllForFormVariable1(EntityDBFields[] dbFieldsAll)
    {
        
      int intFieldToGetTheValue = -1;
      ArrayList listDbFieldsAll = new ArrayList();
      for(int a = 0; a<dbFieldsAll.length;a++)
      {
          listDbFieldsAll.add(dbFieldsAll[a]);
      }
      
      
       String fieldVariableFromPreField = "";
       ArrayList lstFieldVariable = new ArrayList();
       //int intFieldVar = -1;
          //String fieldNameThatHasFormVariable="";
          for(int g=0;g<listDbFieldsAll.size();g++)
          {
              EntityDBFields listChildDBField = (EntityDBFields)listDbFieldsAll.get(g);
              fieldVariableFromPreField  = listChildDBField.getFormVariableFromField();
              if(fieldVariableFromPreField!=null && !fieldVariableFromPreField.equalsIgnoreCase(""))
              {
                   fieldVariableFromPreField = listChildDBField.getFormVariableFromField();
                   //System.out.println("panelODMRData.calculateAllFieldsForFormVariable1     equals    fieldVariableFromPreField:"+fieldVariableFromPreField);
                   break;
              }
              else
              {
                  fieldVariableFromPreField="";
              }
          }
       for(int k= 0 ;k<listDbFieldsAll.size();k++)
      {
            EntityDBFields listChildDBField = (EntityDBFields)listDbFieldsAll.get(k);
          //EntityDBFields gfv  = listDbFieldsAll.get(l);
            //fieldVariableFromPreField  = listChildDBField.getFormVariableFromField();
           String fieldName = listChildDBField.getDbField();
            //System.out.println("PanelODoRData.showRow    equals ?      i:"+i+"   k:"+k+"        fieldName"+fieldName);  
            if(fieldName.equalsIgnoreCase(fieldVariableFromPreField)) // fieldVariableFromPreField!=null && !fieldVariableFromPreField.equalsIgnoreCase("") 
            {
                    intFieldToGetTheValue = k;
                    //System.out.println("PanelODoRData.calculateAllFieldsForFormVariable1  -  equals ?     k:"+k+"   intFieldToGetTheValue:"+intFieldToGetTheValue+"    fieldVariableFromPreField:"+fieldVariableFromPreField+"  =   fieldName"+fieldName+"    lstFieldVariable:"+lstFieldVariable);  
                break;
                 //System.out.println("panelODMRData.calculateAllFieldsForFormVariable1    equals ?      i:"+i+"   k:"+k+"   fieldVariableFromPreField:"+fieldVariableFromPreField+"    lstFieldVariable:"+lstFieldVariable);  

            }
      }

     // System.out.println("panelODMRData.calculateAllFieldsForFormVariable1    equals ?  i:"+i+" fieldVariableFromPreField:"+fieldVariableFromPreField+"   intFieldToGetTheValue:"+intFieldToGetTheValue); 
        return intFieldToGetTheValue;      
    }  
  
  
  
  
  
  

}