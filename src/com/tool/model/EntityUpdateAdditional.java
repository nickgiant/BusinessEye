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
        private String updateAdditionalFieldTable;
        private String updateAdditionalQuery;
        private String[] updateAdditionalQueryFields;
        private String updateAdditionalBridgeEntity;
        private EntityDBFields[] updateAdditionalBridgeDbFields;
        
        public EntityUpdateAdditional(int updateAdditionalWhenIn, String updateAdditionalFieldTableIn, String updateAdditionalQueryIn,String[] updateAdditionalQueryFieldsIn, String updateAdditionalBridgeEntityIn,
                 EntityDBFields[] updateAdditionalBridgeDbFieldsIn)
        {
            
         updateAdditionalWhen=updateAdditionalWhenIn;
         updateAdditionalFieldTable = updateAdditionalFieldTableIn;
         updateAdditionalQuery=updateAdditionalQueryIn;
         updateAdditionalQueryFields=updateAdditionalQueryFieldsIn; 
         updateAdditionalBridgeEntity=updateAdditionalBridgeEntityIn;
         updateAdditionalBridgeDbFields=updateAdditionalBridgeDbFieldsIn;
         
        }
    
    
    
    
    
    
 public int getUpdateAdditionalWhen()  {return updateAdditionalWhen;}
 public String getUpdateAdditionalFieldTable()  {   return updateAdditionalFieldTable;  }
  public String getUpdateAdditionalQuery()  {   return updateAdditionalQuery;  }
  public String[] getUpdateAdditionalQueryFields()  {   return updateAdditionalQueryFields;  }
  public String getUpdateAdditionalBridgeEntity()  {   return updateAdditionalBridgeEntity;  }
public EntityDBFields[] getUpdateAdditionalBridgeDbFields() { return updateAdditionalBridgeDbFields;}

}