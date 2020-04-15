/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//     public static final int LOOKUPTYPE_TABLECONSTANTS = 4;   // in Constants
package com.tool.model;


import com.tool.domain.EntityData;
import com.tool.model.*;
import java.util.ArrayList;
/**
 *
 * @author sun
 */
public class LookupTableConstantsMgt 
{
      ArrayList listEntities;	
   public LookupTableConstantsMgt()
   {
        listEntities = new ArrayList();
        addEntitiesLookupTableConstants();
   }
   
   public void addEntitiesLookupTableConstants()
   { 
   
       EntityData entityData = new EntityData();
       entityData.addEntitiesLookupTableConstants(listEntities);
   }    
    
   public String getPk(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookupTableConstants elu = (EntityLookupTableConstants)listEntities.get(i);
   	     //System.out.println("LookUp.getTable "+elu.getForeignTable()+"--"+foreignTable);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	
   	     	return elu.getName();
   	     }
   	  }
      return null;
   }    
   
   public EntityLookupTableConstantsData[] getEntityLookupTableConstantsData(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookupTableConstants elu = (EntityLookupTableConstants)listEntities.get(i);
   	     //System.out.println("LookUp.getTable "+elu.getForeignTable()+"--"+foreignTable);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	
   	     	return elu.getEntitiesLookupTableConstantsData();
   	     }
   	  }
      return null;
   }   
   
}
