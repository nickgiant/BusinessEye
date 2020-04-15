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

   public String getFromForeignTableTheName(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     //System.out.println("LookUp.getTable "+elu.getForeignTable()+"--"+foreignTable);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	//System.out.println("LookUp.getTable --- "+elu.getForeignTable());
   	     	return elu.getName();//.getForeignTable();
   	     }
   	  }
      return null;
   }   
   
   public String getTable(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     //System.out.println("LookUp.getTable "+elu.getForeignTable()+"--"+foreignTable);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	//System.out.println("LookUp.getTable --- "+elu.getForeignTable());
   	     	return elu.getForeignTable();
   	     }
   	  }
      return null;
   }
   
   public String getQuery(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQuery();
   	     }     
   	  }
      return null;
   }

   public String getQuerySubqueryWhere(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQuerySubqueryWhere();
   	     }     
   	  }
      return null;
   }   
 
   
   public String getQueryWhereForFormVariable(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQueryWhereForFormVariable();
   	     }     
   	  }
      return null;
   }  
   
   
   public String getQuerySubqueryIsActive(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQuerySubqueryIsActive();
   	     }     
   	  }
      return null;
   }   
   
   
   public String getQueryOrderBy(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQueryOrderBy();
   	     }     
   	  }
      return null;
   }

   public String getLookUpKey(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpKey();
   	     }     
   	  }
      return null;
   }

   public String getLookUpKeyTranslation(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpKeyTranslation();
   	     }     
   	  }
      return null;
   }

   public String getLookUpKeyFT(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpKeyFT();
   	     }     
   	  }
      return null;
   }

   public String getLookUpLabel(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpLabel();     //getLookUpLabel();
   	     }     
   	  }
      return null;
   }

   public int getLookUpFieldIndex(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpFieldIndex();
   	     }     
   	  }
      return 0;
   }

   public String[] getLookUpField(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField();
   	     }     
   	  }
      return null;
   }

   public String getLookUpFieldLabel(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpFieldLabel();
   	     }     
   	  }
      return null;
   }

  /* public String getLookUpFormGlobalTableToApply1(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpFormGlobalTableToApply1();
   	     }     
   	  }
      return null;
   }   */
   
   
   
   
   public int getLookUpFieldLength(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpFieldLength();
   	     }     
   	  }
      return 0;
   }   
   
   public String getLookUpFieldType(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpFieldType();
   	     }     
   	  }
      return null;
   }   
   
   public int getLookUpField2Index(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField2Index();
   	     }     
   	  }
      return 0;
   }

   public String getLookUpField2(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField2();
   	     }     
   	  }
      return null;
   }

   public String getLookUpField2Label(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField2Label();
   	     }     
   	  }
      return null;
   }

   public int getLookUpField3Index(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField3Index();
   	     }     
   	  }
      return 0;
   }

   public String getLookUpField3(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField3();
   	     }     
   	  }
      return null;
   }

   public String getLookUpField3Label(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getLookUpField3Label();
   	     }     
   	  }
      return null;
   }

 /*  public String getLookUpFieldLabel(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getForeignTable().equalsIgnoreCase(foreignTable))// non case sensitive search
   	     {
   	     	return elu.getLookUpFieldLabel();
   	     }     
   	  }
      return null;
   }*/

   /*public String getFields(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getForeignTable().equalsIgnoreCase(foreignTable))// non case sensitive search
   	     {
   	     	return elu.getFields();
   	     }     
   	  }
      return null;
   }*/

   /*public String[] getFieldsTranslation(String foreignTable)
   {
   	//System.out.println("LookUp.getFieldsTranslation "+foreignTable);
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getForeignTable().equalsIgnoreCase(foreignTable))// non case sensitive search
   	     {
   	     	   	//System.out.println("LookUp.getFieldsTranslation "+foreignTable+" "+elu.getFieldsTranslation());

   	     	return elu.getFieldsTranslation();
   	     }     
   	  }
      return null;
   }*/

   public String getQueryEditable(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getQueryEditable();
   	     }     
   	  }
      return null;
   }

   public String getStrOfOne(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getStrOfOne();
   	     }     
   	  }
      return null;
   }

   public String getStrOfMany(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getStrOfMany();
   	     }     
   	  }
      return null;
   }

   public String[] getCategoryNodes(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getCategoryNodes();
   	     }     
   	  }
      return null;
   }

   public EntityPanel[] getEntityPanel(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getEntityPanel();
   	     }     
   	  }
      return null;
   }
   
    public String[] getFieldsOnTitle(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getFieldsOnTitle();
   	     }     
   	  }
      return null;
   }  

    public String[] getFieldsOnTitleCaption(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getFieldsOnTitleCaption();
   	     }     
   	  }
      return null;
   }     

    public EntityFilterSettings[] getEntityFilterSettings(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getEntityFilterSettings();
   	     }     
   	  }
      return null;
   }   


   public int getLookUpFieldColDescription(String foreignTable)
   {
   	int ret =-1;
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	ret= elu.getIntColFieldDescription();
   	     }     
   	  }
      return ret;
   }
  
   public int getLookUpIntNoOfColsWhenInTable(String foreignTable)
   {
   	int ret =-1;
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     //System.out.println("LookUp.getLookUpIntNoOfColsWhenInTable -----"+elu.getName()+" for "+foreignTable);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	ret= elu.getIntNoOfColsWhenInTable();
   	     }     
   	  }
      return ret;
   } 
   
   public ImageIcon getIcon(String foreignTable)
   {
   	ImageIcon ret=null;
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	ret =  elu.getIcon();
   	     }     
   	  }
      return ret;
   } 
   	
   public boolean getShowToolbar(String foreignTable)
   {
   	boolean ret = true;
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	ret =  elu.getShowToolbar();
   	     }     
   	  }
      return ret;
   }  
   	
   public int getIntValidationColumn(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getIntValidationColumn();
   	     }     
   	  }
      return -1;
   }
   
   public int getIntValidationType(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getForeignTable().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getIntValidationType();
   	     }     
   	  }
      return -1;
   }      
   
   public String[] getFieldsForSums(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getFieldsForSums();
   	     }     
   	  }
      return null;
   }   
   
   /*public EntityReport getEntityReport(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	return elu.getEntityReport();
   	     }     
   	  }
      return null;
   }   */
 /*   public String[] getSearchField(String foreignTable)
   {
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     EntityLookUp elu = (EntityLookUp)listEntities.get(i);
   	     if (elu.getForeignTable().equalsIgnoreCase(foreignTable))// non case sensitive search
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