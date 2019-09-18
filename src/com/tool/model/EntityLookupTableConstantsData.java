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
public class EntityLookupTableConstantsData
{
    private String pk;
    private String constantstypeId;
    private String constantstypename;
    private int order;  // or name
    private String name;
    private String notes;
    
    
      public EntityLookupTableConstantsData(String pkIn,int orderIn, String nameIn)
      {
      
        pk=pkIn;
        constantstypeId="";
        constantstypename="";        
        order=orderIn;
        name=nameIn;   
        notes="";
      
      }      
    
 
      public EntityLookupTableConstantsData(String pkIn,String constantstypeIdIn, String constantstypenameIn,int orderIn, String nameIn, String notesIn)
      {
      
        pk=pkIn;
        constantstypeId=constantstypeIdIn;
        constantstypename=constantstypenameIn;
        order=orderIn;
        name=nameIn; 
        notes = notesIn;
      
      }       
      
        public String getPk()
        {
           return pk;
        }       
      
    public String getConstantstypeId()
    {
        return constantstypeId;
    }
    
    public String getConstantstypename()   
    {
        return constantstypename;
    }    
        
        public int getOrder()
        {
           return order;
        } 
        
        public String getName() // or name
        {
           return name;
        }          
    
        public String getNotes()
        {
            return notes;
        }
}
