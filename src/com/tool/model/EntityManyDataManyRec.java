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
public class EntityManyDataManyRec
{
        public String name;
        public String caption;
        private EntityParameter[] entityManyParameters;
        private EntityMenu[] entityMenuMany; 
        
    
        public EntityManyDataManyRec(String nameIn,String captionIn,EntityParameter[] entityManyParametersIn, EntityMenu[] entityMenuManyIn )
        {
         name=nameIn;
         caption=captionIn;
         entityManyParameters=entityManyParametersIn;
         entityMenuMany=entityMenuManyIn;
        }       
    
    
    
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

        public String getName()  {   return name;  }
        public String getCaption()  {   return caption;  }
       public EntityParameter[] getEntityParameters() {  return entityManyParameters;}
       public EntityMenu[] getEntityMenuMany() {  return entityMenuMany;}
    
}
