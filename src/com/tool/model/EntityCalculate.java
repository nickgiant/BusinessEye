/*  2018-05-07
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.model;

/**
 *
 * @author small
 */
public class EntityCalculate
{
        public String name;
        public String caption;
        public String subTitle;
        public String [] calculationType;
        public EntityFilterSettings [] entityFilterSettings;
        public String[] query;
        public boolean isNullify; //is make everything null
       // public String tableForFilters; // if different than EntityFilterSettings
        public EntityGroupOfComps[] entityGroupOfComps;
        public String yearEnforce;
        String prefixOfFields;  // like f in periodic of vat
        String[] arrayFieldAndValue1;
        String[] arrayFieldAndValue2;
                
        public EntityCalculate(String nameIn,String captionIn,String subTitleIn, String[] calculationTypeIn,
        EntityFilterSettings[] entityFilterSettingsIn,EntityGroupOfComps[] entityGroupOfCompsIn,
        String[] queryIn, boolean isNullifyIn, String yearEnforceIn, String prefixOfFieldsIn, String[] arrayFieldAndValue1In,String[] arrayFieldAndValue12In)
        {
         name=nameIn;
         caption=captionIn;
         subTitle=subTitleIn;  
         calculationType=calculationTypeIn;
         entityFilterSettings=entityFilterSettingsIn;
         entityGroupOfComps=entityGroupOfCompsIn;
         query= queryIn;
         isNullify=isNullifyIn;
        
         yearEnforce=yearEnforceIn;
         prefixOfFields=prefixOfFieldsIn;
         arrayFieldAndValue1 = arrayFieldAndValue1In;
         arrayFieldAndValue2=arrayFieldAndValue12In;
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
        public String getSubTitle()  {   return subTitle;  }        
        public String[] getCalculationType()  {   return calculationType;  }  
        public EntityFilterSettings[] getEntityFilterSettings()  {   return entityFilterSettings;  }
        public EntityGroupOfComps[]  getEntityGroupOfComps()  {   return entityGroupOfComps;  }
        public String[] getQueryArray()  {   return query;  }  
        public boolean getIsNullify()  {   return isNullify;  }
        public String getYearEnforce()  {   return yearEnforce;  }
        public String getPrefixOfFields()  {   return prefixOfFields;  }
        public String[] getArrayFieldAndValue1()  {   return arrayFieldAndValue1;  }  
        public String[] getArrayFieldAndValue2()  {   return arrayFieldAndValue2;  }  
            
}
