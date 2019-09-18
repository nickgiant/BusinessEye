package com.tool.model;

//import com.tool.model.EntityInfo;
import com.tool.model.*;

//import java.util.Hashtable;
import java.util.ArrayList;


    public class EntityInfoM //the class is public so it can be accessed from outside the package
    {  
    // private Hashtable entityInfos;
     private ArrayList entities;
     private EntityInfo entityInfo;
    // private int EntityInfoCount;
    /* private String[] columnNames = {"name", "index", "type"};
     private Object[][] columnObjects ;//=  new Object[4][4];*/


     public EntityInfoM()
     {
     	entities = new ArrayList();
       // entityInfos = new Hashtable();   // initially empty
        //EntityData ed=new EntityData();
        //ed.addEntitiesLookup(new ArrayList());
        
      // addEntitiesInfo();
     }
    
   
    public String getSqlSelect(String lookupEntity)
   {
   	  for(int i =0; i<entities.size(); i++)
   	  {  
   	     EntityInfo ei = (EntityInfo)entities.get(i);
   	     if (ei.getName().equalsIgnoreCase(lookupEntity))// non case sensitive search
   	     {
   	     	return ei.getSqlSelect();
   	     } 
             
            // System.out.println("EntityInfoM   "+i+ "  "+lookupEntity+" = "+ei.getName());
   	  }
      return null;
   }
   
    public String getSqlFrom(String lookupEntity)
   {
   	  for(int i =0; i<entities.size(); i++)
   	  {  
   	     EntityInfo ei = (EntityInfo)entities.get(i);
   	     if (ei.getName().equalsIgnoreCase(lookupEntity))// non case sensitive search
   	     {
   	     	return ei.getSqlWhere();
   	     }     
   	  }
      return null;
   }
   
   public String getSqlWhere(String lookupEntity)
   {
   	  for(int i =0; i<entities.size(); i++)
   	  {  
   	     EntityInfo ei = (EntityInfo)entities.get(i);
   	     if (ei.getName().equalsIgnoreCase(lookupEntity))// non case sensitive search
   	     {
   	     	return ei.getSqlWhere();
   	     }     
   	  }
      return null;
   }   
   
   // should be the same as entityInfo second query
   public String getQueryEditeable(String entity)
   {
 
    	        String query = null;  // get queries from entities
            if ((getSqlSelect(entity)!=null)&&(getSqlFrom(entity)==null)&&(getSqlWhere(entity)==null))
            {
                query = getSqlSelect(entity) ;
            }
            else if ((getSqlSelect(entity)!= null)&&(getSqlFrom(entity)!=null)&&(getSqlWhere(entity)==null))
            { 
                query=getSqlSelect(entity)+" "+getSqlFrom(entity); 
            }
            else if ((getSqlSelect(entity)!= null)&&(getSqlFrom(entity)!=null)&&(getSqlWhere(entity)!= null))
            {
                query=getSqlSelect(entity)+" "+getSqlFrom(entity)+" "+getSqlWhere(entity); 
            }
            else
            {
                System.out.println("error    EntityInfoM.getQueryEditeable   ELSE   +O+   entity:"+entity+"    '"+getSqlSelect(entity)+"'   "+entities.size()+"     query:"+query);
            }
  
                
              
                
     return query;
  }
  
     
  /* public void add(EntityInfo entityInfo)
   {
      System.out.println("EntityInfoM add "+entityInfo);
      entityInfosA.add(entityInfo);
   }*/
     
  /*   public EntityInfo getEntityInfo(int id)throws EntityInfoException
     {

        return (EntityInfo)entityInfosA.get(id); // get 
     }*/
     
   /* public String getStrOfMany(int i)throws EntityInfoException
    {
       EntityInfo entityInfo = (EntityInfo)entityInfosA.get(i);
       return entityInfo.getStrOfMany();
    }*/

  /* public int count()
   {
     /* int matches = 0;
      for (int i =0; i<entityInfosA.size();i++)
      {
      	System.out.println("EntityInfoM count"+entityInfosA.size());
         EntityInfo c = (EntityInfo)entityInfosA.get(i);
         if (c.equals(entityInfo))
            matches++;
      }
      return matches;*/
    /*  return entityInfosA.size();
   }*/

   public static void main(String[] args)
   {
   	

   	
   	EntityInfoM eim=new EntityInfoM();
     //add(new EntityInfo("farmer","SELECT farmer.farmerId, farmer.surname, farmer.name, farmer.fathername, farmer.farmerAfm, farmer.doyId, farmer.idNo, farmer.address, farmer.townId, farmer.phone","FROM farmer","ORDER BY farmer.surname, farmer.name, farmer.fathername", null ,"αγρότης","DORM","επίθετο","surname","ΑΦΜ","farmerAfm","τον αγρότη","αγροτών",null,null));
     //add(new EntityInfo("delivery",null,null,null, "invoice" ,"παραστατικό","DTRO",null, null, null, null, "την αποστολή", "των αποστολών","το παραστατικό","παραστατικών"));
   System.out.println(eim.getQueryEditeable("farmer"));
   System.out.println(eim.getSqlSelect("fermer"));
 
   }


     }
