//created 01-06-2008

package com.tool.model;

import com.tool.model.*;

import java.util.ArrayList;

public class EntityPanelM
{
   //EntityLookUp entityLookUp;
   ArrayList entities;	
	
   public EntityPanelM()
   {
        entities = new ArrayList();
        //addEntitiesLookup();
   }
   
   public void addEntityPanel(EntityPanel ep)
   { 
   
      entities.add(ep);
       /*EntityData entityData = new EntityData();
       entityData.addEntitiesLookup(entities);*/
   }
   
   public EntityPanel getEntityPanel(String title)
   {
   	  for(int i =0; i<entities.size(); i++)
   	  {  
   	     EntityPanel ep = (EntityPanel)entities.get(i);
   	     if (ep.getTitle().equalsIgnoreCase(title))// non case sensitive search
   	     {
   	     	return ep;
   	     }     
   	  }
      return null;
   }
  
  public int getCount()
  {
  	return entities.size();
  }
  
  public static void main(String[] args)
  {  //test

  }


   
}