/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.model;

import java.util.ArrayList;

/**
 *
 * @author small
 */
public class DBFieldMgt
{
   EntityDBFields[] listEntities;	
	
   public DBFieldMgt()
   {
        //listEntities = new ArrayList();
        //addEntitiesDBField();
   }
   
   public void addEntitiesDBField(EntityDBFields[] edbf)
   { 
       listEntities = edbf;
   }

   public int getDBFieldInt(String strDBField)
   {
   	  for(int i =0; i<listEntities.length; i++)
   	  {  
   	    // EntityDBFields edf = (EntityDBFields)listEntities.[i];
   	     //System.out.println("LookUp.getTable "+elu.getForeignTable()+"--"+foreignTable);
   	     if (listEntities[i].getDbField().toUpperCase().equalsIgnoreCase(strDBField.toUpperCase()))// non case sensitive search
   	     {
   	     	//System.out.println("LookUp.getTable --- "+elu.getForeignTable());
   	     	return i;//edf.getDbField();
   	     }
   	  }
      return -1;
   }
   
   public String getFormVariableFromField(String strDBField)
   {
   	  for(int i =0; i<listEntities.length; i++)
   	  {  
   	     EntityDBFields edf = (EntityDBFields)listEntities[i];
   	     //System.out.println("LookUp.getTable "+elu.getForeignTable()+"--"+foreignTable);
   	     if (listEntities[i].getDbField().toUpperCase().equalsIgnoreCase(strDBField.toUpperCase()))// non case sensitive search
   	     {
   	     	//System.out.println("LookUp.getTable --- "+elu.getForeignTable());
   	     	return edf.getFormVariableFromField();
   	     }
   	  }
      return null;
   }   
   

   public String getFieldFromFormVariableFromField(String strDBFieldFormVariable)
   {
   	  for(int i =0; i<listEntities.length; i++)
   	  {  
   	     EntityDBFields edf = (EntityDBFields)listEntities[i];
   	     //System.out.println("LookUp.getTable "+elu.getForeignTable()+"--"+foreignTable);
   	     if (listEntities[i].getDbField().toUpperCase().equalsIgnoreCase(strDBFieldFormVariable.toUpperCase()))// non case sensitive search
   	     {
   	     	//System.out.println("LookUp.getTable --- "+elu.getForeignTable());
   	     	return edf.getDbField();
   	     }
   	  }
      return null;
   }   

   
}
