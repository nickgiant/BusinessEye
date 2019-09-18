/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tool.model;

import com.tool.guicomps.Constants;

/**
 *

 */
public class EntityDBFieldsCalculation implements Constants
{
   private int whenSetThenCalculateCategoryField = 0; //  the root(parent) is 0 (also current 'table' is 0), the 1 is the first "table"(like sale lines) and goes on
   private int whenSetThenCalculateField; // the value is setted in this field
   private int[] calculationInputCategoryFields;  // these are used for 'calculation'  like whenSetThenCalculateCategoryField
   private int[] calculationInputFields;  // these are used for 'calculation'
   private String calculation;// preffered sql
   // private boolean isReplaceIfHasValue;
    
      public EntityDBFieldsCalculation()
      {
      
      }    
      
      public EntityDBFieldsCalculation(int whenSetThenCalculateCategoryFieldIn, int whenSetThenCalculateFieldIn,int[] calculationInputCategoryFieldsIn,
              int[] calculationInputFieldsIn, String calculationIn)//, boolean isReplaceIfHasValueIn)
      {
           whenSetThenCalculateCategoryField=whenSetThenCalculateCategoryFieldIn;
           whenSetThenCalculateField=whenSetThenCalculateFieldIn;
           calculationInputCategoryFields=calculationInputCategoryFieldsIn;
           calculationInputFields=calculationInputFieldsIn;
           calculation=calculationIn;
           //isReplaceIfHasValue=isReplaceIfHasValueIn;
           
      }  
      
        public int getWhenSetThenCalculateCategoryField()     {     return whenSetThenCalculateCategoryField;        }
        public int getWhenSetThenCalculateField()     {     return whenSetThenCalculateField;        }
        public int[] getCalculationInputCategoryFields()     {     return calculationInputCategoryFields;        }
        public int[] getCalculationInputFields()     {     return calculationInputFields;        }
        public String getCalculation()     {     return calculation;        }      
        //public boolean getIsReplaceIfHasValue()     {     return isReplaceIfHasValue;        }
      
      
      
}
