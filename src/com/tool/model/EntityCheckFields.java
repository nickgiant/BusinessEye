/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.model;

/**
 *
 * @author user
 */
public class EntityCheckFields
{

    //private final int CHECK_ON_ENTRY = 1;
    //private final int CHECK_ON_INSERT_OR_ON_UPDATE = 2;

     private String queryIfA;
     private int colA; // no of column (or thextfield) on panel where the checks happens(ie customerId)
     private String textMessageWhenTrue;
     private int intWhenToCheck;       //  private final int CHECK_ON_ENTRY = 1;  private final int CHECK_ON_INSERT_OR_ON_UPDATE = 2;
     private int[] inputFields; 
     
      public EntityCheckFields()
      {}


      public EntityCheckFields(int intWhenToCheckIn,String queryIfAIn, int[] inputFieldsIn ,int colAIn, String textMessageWhenTrueIn)
      {
      	intWhenToCheck=intWhenToCheckIn;
      queryIfA=queryIfAIn;	
      inputFields=inputFieldsIn;
      colA = colAIn;
      textMessageWhenTrue = textMessageWhenTrueIn;
          
      }      
      
        public int getWhenToCheck()     {     return intWhenToCheck;}
        public String getQueryIfA()     {     return queryIfA;        }
        public int[] getInputFields()     {     return inputFields;}
        public int getColumnΑ()     {     return colA;}
        public String getTextMessageWhenTrue()     {     return textMessageWhenTrue;        }
          



    
}
