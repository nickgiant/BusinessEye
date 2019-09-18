/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tool.model;

/**
 *
 * @author sun
 */

        


public class EntityUpdateAdditional
{
        private int updateAdditionalWhen; //NO_UPDATE, UPDATE_ON_INSERT_ONLY,UPDATE_ON_UPDATE_ONLY, UPDATE_ON_BOTH_INSERT_AND_UPDATE,UPDATE_ON_DELETE
        private String updateAdditionalQuery;
        private String[] updateAdditionalQueryFields;
        
        public EntityUpdateAdditional(int updateAdditionalWhenIn,String updateAdditionalQueryIn,String[] updateAdditionalQueryFieldsIn)
        {
            
         updateAdditionalWhen=updateAdditionalWhenIn;
         updateAdditionalQuery=updateAdditionalQueryIn;
         updateAdditionalQueryFields=updateAdditionalQueryFieldsIn;   
         
        }
    
    
    
    
    
    
 public int getUpdateAdditionalWhen()  {return updateAdditionalWhen;}
  public String getUpdateAdditionalQuery()  {   return updateAdditionalQuery;  }
  public String[] getUpdateAdditionalQueryFields()  {   return updateAdditionalQueryFields;  }     
}

