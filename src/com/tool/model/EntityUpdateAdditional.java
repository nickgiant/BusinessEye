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
        private String ifEnabledReturn1;
         private String[] ifEnabledReturn1FieldsToReplace;
        private String updateAdditionalFieldTable; // if not null is a table
        private String updateAdditionalFieldTableGroupValue;
        private String updateAdditionalQuery;
        private String[] updateAdditionalQueryFields;
        private String updateAdditionalBridgeEntity;
        private String updateAdditionalBridgeDestinationField;// like  esoexoheaderId in table sxesoexoheader (we need it on update to select)
        private EntityDBFields[] updateAdditionalBridgeDbFields;
        
        public EntityUpdateAdditional(int updateAdditionalWhenIn, String ifEnabledReturn1In, String[] ifEnabledReturn1FieldsToReplaceIn, String updateAdditionalFieldTableIn, 
                String updateAdditionalFieldTableGroupValueIn, String updateAdditionalQueryIn,String[] updateAdditionalQueryFieldsIn, String updateAdditionalBridgeEntityIn, 
                String updateAdditionalBridgeDestinationFieldIn, EntityDBFields[] updateAdditionalBridgeDbFieldsIn)
        {
            
         updateAdditionalWhen=updateAdditionalWhenIn;
         ifEnabledReturn1=ifEnabledReturn1In;
         ifEnabledReturn1FieldsToReplace=ifEnabledReturn1FieldsToReplaceIn;
         updateAdditionalFieldTable = updateAdditionalFieldTableIn;
         updateAdditionalFieldTableGroupValue = updateAdditionalFieldTableGroupValueIn;
         updateAdditionalQuery=updateAdditionalQueryIn;
         updateAdditionalQueryFields=updateAdditionalQueryFieldsIn; 
         updateAdditionalBridgeEntity=updateAdditionalBridgeEntityIn;
         updateAdditionalBridgeDestinationField = updateAdditionalBridgeDestinationFieldIn;
         updateAdditionalBridgeDbFields=updateAdditionalBridgeDbFieldsIn;
         
        }
    
    
    
    
    
    
 public int getUpdateAdditionalWhen()  {return updateAdditionalWhen;}
 public String getIfIsEnabledReturn1()  {   return ifEnabledReturn1;}
 public String[] getIfIsEnabledReturn1FieldsToReplace()  { return  ifEnabledReturn1FieldsToReplace;}
 public String getUpdateAdditionalFieldTable()  {   return updateAdditionalFieldTable;  } // if not null is a table
 public String getUpdateAdditionalFieldTableGroupValue()  {   return updateAdditionalFieldTableGroupValue;}
  public String getUpdateAdditionalQuery()  {   return updateAdditionalQuery;  }
  public String[] getUpdateAdditionalQueryFields()  {   return updateAdditionalQueryFields;  }
  public String getUpdateAdditionalBridgeEntity()  {   return updateAdditionalBridgeEntity;  }
  public String getUpdateAdditionalBridgeDestinationField()  {   return updateAdditionalBridgeDestinationField;}
public EntityDBFields[] getUpdateAdditionalBridgeDbFields() { return updateAdditionalBridgeDbFields;}

}