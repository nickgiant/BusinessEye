package com.tool.model;

import com.tool.domain.EntityData;
import com.tool.model.EntityFilterSettings;
import com.tool.model.EntityPanel;
import com.tool.model.*;

import java.util.ArrayList;

import javax.swing.*;

public class LookUpMgt
{
   //EntityLookUp entityLookUp;
   ArrayList listEntities;	
	
   public LookUpMgt()
   {
        listEntities = new ArrayList();
        addEntitiesLookup();
   }
   
   public void addEntitiesLookup()
   { 
   
       EntityData entityData = new EntityData();
       entityData.addEntitiesLookup(listEntities);
   }




   public String getFromTheNameTheForeignTable(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     //System.out.println("LookUp.getTable "+elu.getForeignTable()+"--"+name);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	//System.out.println("LookUp.getTable --- "+elu.getForeignTable());
   	     	return elu.getForeignTable();//.getForeignTable();
   	     }
   	  }
      return null;
   }   

   
   public String getTable(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     //System.out.println("LookUp.getTable "+elu.getForeignTable()+"--"+name);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	//System.out.println("LookUp.getTable --- "+elu.getForeignTable());
   	     	return elu.getForeignTable();
   	     }
   	  }
      return null;
   }
   
   public String getQuery(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQuery();
   	     }     
   	  }
      return null;
   }

   public String getQuerySubqueryWhere(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQuerySubqueryWhere();
   	     }     
   	  }
      return null;
   }   
 
   
   public String getQueryWhereForFormVariable(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQueryWhereForFormVariable();
   	     }     
   	  }
      return null;
   }  
   
   
   public String getQuerySubqueryIsActive(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQuerySubqueryIsActive();
   	     }     
   	  }
      return null;
   }   
   
   
   public String getQueryOrderBy(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQueryOrderBy();
   	     }     
   	  }
      return null;
   }

   public String getLookUpKey(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpKey();
   	     }     
   	  }
      return null;
   }

   public String getLookUpKeyTranslation(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpKeyTranslation();
   	     }     
   	  }
      return null;
   }

   public String getLookUpKeyFT(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpKeyFT();
   	     }     
   	  }
      return null;
   }

   public String getLookUpLabel(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpLabel();     //getLookUpLabel();
   	     }     
   	  }
      return null;
   }

   public int getLookUpFieldIndex(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpFieldIndex();
   	     }     
   	  }
      return 0;
   }

   public String[] getLookUpField(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField();
   	     }     
   	  }
      return null;
   }

   public String getLookUpFieldLabel(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpFieldLabel();
   	     }     
   	  }
      return null;
   }

  /* public String getLookUpFormGlobalTableToApply1(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpFormGlobalTableToApply1();
   	     }     
   	  }
      return null;
   }   */
   
   
   
   
   public int getLookUpFieldLength(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpFieldLength();
   	     }     
   	  }
      return 0;
   }   
   
   public String getLookUpFieldType(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpFieldType();
   	     }     
   	  }
      return null;
   }   
   
   public int getLookUpField2Index(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField2Index();
   	     }     
   	  }
      return 0;
   }

   public String getLookUpField2(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField2();
   	     }     
   	  }
      return null;
   }

   public String getLookUpField2Label(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField2Label();
   	     }     
   	  }
      return null;
   }

   public int getLookUpField3Index(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField3Index();
   	     }     
   	  }
      return 0;
   }

   public String getLookUpField3(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField3();
   	     }     
   	  }
      return null;
   }

   public String getLookUpField3Label(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField3Label();
   	     }     
   	  }
      return null;
   }

 /*  public String getLookUpFieldLabel(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getForeignTable().equalsIgnoreCase(name))// non case sensitive search
   	     {
   	     	return elu.getLookUpFieldLabel();
   	     }     
   	  }
      return null;
   }*/

   /*public String getFields(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getForeignTable().equalsIgnoreCase(name))// non case sensitive search
   	     {
   	     	return elu.getFields();
   	     }     
   	  }
      return null;
   }*/

   /*public String[] getFieldsTranslation(String name)
   {
   	//System.out.println("LookUp.getFieldsTranslation "+name);
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getForeignTable().equalsIgnoreCase(name))// non case sensitive search
   	     {
   	     	   	//System.out.println("LookUp.getFieldsTranslation "+name+" "+elu.getFieldsTranslation());

   	     	return elu.getFieldsTranslation();
   	     }     
   	  }
      return null;
   }*/

   public String getQueryEditable(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQueryEditable();
   	     }     
   	  }
      return null;
   }

   public String getStrOfOne(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getStrOfOne();
   	     }     
   	  }
      return null;
   }

   public String getStrOfMany(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getStrOfMany();
   	     }     
   	  }
      return null;
   }

   public String[] getCategoryNodes(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getCategoryNodes();
   	     }     
   	  }
      return null;
   }

   public EntityPanel[] getEntityPanel(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getEntityPanel();
   	     }     
   	  }
      return null;
   }
   
    public String[] getFieldsOnTitle(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getFieldsOnTitle();
   	     }     
   	  }
      return null;
   }  

    public String[] getFieldsOnTitleCaption(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getFieldsOnTitleCaption();
   	     }     
   	  }
      return null;
   }     

    public EntityFilterSettings[] getEntityFilterSettings(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getEntityFilterSettings();
   	     }     
   	  }
      return null;
   }   


   public int getLookUpFieldColDescription(String name)
   {
   	int ret =-1;
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	ret= elu.getIntColFieldDescription();
   	     }     
   	  }
      return ret;
   }
  
   public int getLookUpIntNoOfColsWhenInTable(String name)
   {
   	int ret =-1;
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     //System.out.println("LookUp.getLookUpIntNoOfColsWhenInTable -----"+elu.getName()+" for "+name);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	ret= elu.getIntNoOfColsWhenInTable();
   	     }     
   	  }
      return ret;
   } 
   
   public ImageIcon getIcon(String name)
   {
   	ImageIcon ret=null;
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	ret =  elu.getIcon();
   	     }     
   	  }
      return ret;
   } 
   	
   public boolean getShowToolbar(String name)
   {
   	boolean ret = true;
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	ret =  elu.getShowToolbar();
   	     }     
   	  }
      return ret;
   }  
   	
   public int getIntValidationColumn(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getIntValidationColumn();
   	     }     
   	  }
      return -1;
   }
   
   public int getIntValidationType(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getForeignTable().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getIntValidationType();
   	     }     
   	  }
      return -1;
   }      
   
   public String[] getFieldsForSums(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getFieldsForSums();
   	     }     
   	  }
      return null;
   }   
   
   /*public EntityReport getEntityReport(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getEntityReport();
   	     }     
   	  }
      return null;
   }   */
 /*   public String[] getSearchField(String name)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getForeignTable().equalsIgnoreCase(name))// non case sensitive search
   	     {
   	     	return elu.getSearchField();
   	     }     
   	  }
      return null;
   }   */

  public static void main(String[] args)
  {  //test
     LookUpMgt l = new LookUpMgt();
    // System.out.println("");
    String table = "doy";
    System.out.println(l.getQuery(table));
    System.out.println(l.getLookUpKey(table));
    System.out.println(l.getLookUpKeyFT(table));
    System.out.println(l.getLookUpLabel(table));
    System.out.println(l.getLookUpField(table));
    System.out.println(l.getLookUpField2(table));
//    System.out.println(l.getLookUpFieldLabel(table));
//    System.out.println(l.getFields(table));
    
  }


   
}