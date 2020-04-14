//created 06-2017

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.model;

import com.tool.utils.*;
import com.tool.guicomps.*;

import java.util.*;

/**
 *
 * @author user
 */
public class EntityData implements Constants
{
    
       DataTree dTree;
       DataTreeNode nodeRoot ;   
       EntityDataEsoExo entityDataEsoExo;
       EntityDataSerSales entityDataSersal;
       EntityDataFarmersVat entityDataFarmersVat;
       //EntityDataTam entityDataTam;
       EntityDataSystem entityDataSystem;
       EntityDataMetricsSerSales entityDataMetricsSerSales;
       //EntityDataMetricsEsoExo entityDataMetricsEsoExo;
    
    public EntityData()
    {
        
          initializeAllSections();
        
        
       	  dTree = new DataTree();
   	  nodeRoot = new DataTreeNode("root");
   	  dTree.setRootElement(nodeRoot);
    }
    
    private void initializeAllSections()
    {
        
   //     entityDataSersal = new EntityDataSerSales();
   //     entityDataSystem = new EntityDataSystem();
   //     entityDataMetrics = new EntityDataMetricsSerSales();
        
        
    }
    
    
    /*
    * used by DialogHelp
    */
    public DataTree loadAndGetDataTreeNode(boolean[] isNodeVisible)
    {
        
         entityDataSersal = new EntityDataSerSales();

   
        
      
        
       
       /*  might create as loading and using the menus
      for each EntitySection do: 
       EntitySection es = new EntitySection(......)
       EntityMenu empg = new EntityMenu();
       empg.setEntityType(ENTITY_TYPE_SECTION);
       empg.setEntitySection(es,TITLE_...,ICO_...);       
       DataTreeNode nodeempg = new DataTreeNode(empg);
       nodeRoot.getChildFromCaption(SECTION_SERSAL).addChild(nodeempg);     
       */
       
        entityDataSersal.addMainNavigationNodes(isNodeVisible);
        entityDataSersal.loadAllNodes();       
        DataTree dTreeSerSal = entityDataSersal.getDataTree();


       return dTreeSerSal;//dTree;
        
    }
 
    private DataTree loadAndGetEsodaExodaDataTreeNode()
    {
        entityDataEsoExo = new EntityDataEsoExo();
        
       boolean[] isNodeVisible ={true, true, true, true,true};
        entityDataEsoExo.addMainNavigationNodes(isNodeVisible);
        entityDataEsoExo.loadAllNodes();       
        DataTree dTreeMetrics = entityDataEsoExo.getDataTree();
             
       return dTreeMetrics;//dTree;
        
    }               
     
    
    private DataTree loadAndGetFarmersVatDataTreeNode()
    {
        entityDataFarmersVat = new EntityDataFarmersVat();
        
       boolean[] isNodeVisible ={true, true, true, true,true};
        entityDataFarmersVat.addMainNavigationNodes(isNodeVisible);
        entityDataFarmersVat.loadAllNodes();       
        DataTree dTreeMetrics = entityDataFarmersVat.getDataTree();
             
       return dTreeMetrics;//dTree;
        
    }     
   /* private DataTree loadAndGetTamCashDataTreeNode()
    {
          entityDataTam = new EntityDataTam();
        
       boolean[] isNodeVisible ={true, true, true, true,true};
        entityDataTam.addMainNavigationNodes(isNodeVisible);
        entityDataTam.loadAllNodes();       
        DataTree dTreeSsales = entityDataTam.getDataTree();
             
       return dTreeSsales;//dTree;
        
    }   */    
    
   
    
    private DataTree loadAndGetMetricsEsoExoDataTreeNode()
    {
       /* entityDataMetricsEsoExo = new EntityDataMetricsEsoExo();
        
       boolean[] isNodeVisible ={true, true, true, true,true};
        entityDataMetricsEsoExo.addMainNavigationNodes(isNodeVisible);
        entityDataMetricsEsoExo.loadAllNodes();       
        DataTree dTreeMetrics = entityDataMetricsEsoExo.getDataTree();
             
       return dTreeMetrics;//dTree;
        */
        return null;
    }     
    
    
    private DataTree loadAndGetMetricsSerSalesDataTreeNode()
    {
        entityDataMetricsSerSales = new EntityDataMetricsSerSales();
        
       boolean[] isNodeVisible ={true, true, true, true,true};
        entityDataMetricsSerSales.addMainNavigationNodes(isNodeVisible);
        entityDataMetricsSerSales.loadAllNodes();       
        DataTree dTreeMetrics = entityDataMetricsSerSales.getDataTree();
             
       return dTreeMetrics;//dTree;
        
    }    
    
    
    
    private DataTree loadAndGetServiceSalesDataTreeNode()
    {
          entityDataSersal = new EntityDataSerSales();
        
       boolean[] isNodeVisible ={true, true, true, true,true};
        entityDataSersal.addMainNavigationNodes(isNodeVisible);
        entityDataSersal.loadAllNodes();       
        DataTree dTreeSsales = entityDataSersal.getDataTree();
             
       return dTreeSsales;//dTree;
        
    }    
    
    private DataTree loadAndGetSystemDataTreeNode()
    {
        entityDataSystem = new EntityDataSystem();
        
       boolean[] isNodeVisible ={true, true, true, true,true};
        entityDataSystem.addMainNavigationNodes(isNodeVisible);
        entityDataSystem.loadAllNodes();       
        DataTree dTreeSystem = entityDataSystem.getDataTree();
             
       return dTreeSystem;//dTree;
        
    } 
    
    /*
    * called by DialogMain.loadMenu
    */
    public ArrayList loadAndGetAllEntitySections()
    {
        
        
      /* DataTree dTreeSection = new DataTree();
   	DataTreeNode  nodeRootSection = new DataTreeNode("rootSection");
   	  dTreeSection.setRootElement(nodeRootSection);  
*/
        
       /*public static final String PRODUCT_OLA = "0";
       public static final String PRODUCT_TIMOLOGIA = "1";
       public static final String PRODUCT_APLOGRAFIKA = "2";        
        */
        
        ArrayList listSections = new ArrayList();
   if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_APLOGRAFIKA))
   {
        EntityMenu emCat0 = new EntityMenu();
        emCat0.setEntityType(ENTITY_TYPE_SECTION);
        emCat0.setEntitySection(PRODUCT_APLOGRAFIKA_CAPTION,loadAndGetEsodaExodaDataTreeNode(),ICO_MENUESODAEXODA);//(String captionIn, int categoryLevelIn, ImageIcon icoIn)
        listSections.add(emCat0);  
   }
   else if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_TIMOLOGIA))
   {
        
        EntityMenu emCat1 = new EntityMenu();
        emCat1.setEntityType(ENTITY_TYPE_SECTION);
        emCat1.setEntitySection(PRODUCT_TIMOLOGIA_CAPTION,loadAndGetServiceSalesDataTreeNode(),ICO_MENUSERVICESALES);//(String captionIn, int categoryLevelIn, ImageIcon icoIn)
        listSections.add(emCat1);
   }  
   else if( VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
   {
       
       
               EntityMenu emCat1 = new EntityMenu();
        emCat1.setEntityType(ENTITY_TYPE_SECTION);
        emCat1.setEntitySection(PRODUCT_TIMOLOGIA_CAPTION,loadAndGetServiceSalesDataTreeNode(),ICO_MENUSERVICESALES);//(String captionIn, int categoryLevelIn, ImageIcon icoIn)
        listSections.add(emCat1);
       
       
               EntityMenu emCat0 = new EntityMenu();
        emCat0.setEntityType(ENTITY_TYPE_SECTION);
        emCat0.setEntitySection(PRODUCT_APLOGRAFIKA_CAPTION,loadAndGetEsodaExodaDataTreeNode(),ICO_MENUESODAEXODA);//(String captionIn, int categoryLevelIn, ImageIcon icoIn)
        listSections.add(emCat0);
        
     
                EntityMenu emCat2 = new EntityMenu();
        emCat2.setEntityType(ENTITY_TYPE_SECTION);
        emCat2.setEntitySection(PRODUCT_FARMERSVAT_CAPTION,loadAndGetFarmersVatDataTreeNode(),ICO_MENUFARMERSVAT);//(String captionIn, int categoryLevelIn, ImageIcon icoIn)
        listSections.add(emCat2);       
        
   }
        
        
       /*EntityMenu emCat2 = new EntityMenu();
        emCat2.setEntityType(ENTITY_TYPE_SECTION);
        emCat2.setEntitySection("ταμειακά",loadAndGetTamCashDataTreeNode(),ICO_MENUTAMEIO);//(String captionIn, int categoryLevelIn, ImageIcon icoIn)
        listSections.add(emCat2);*/        
        
        //DataTreeNode dtn1 = new DataTreeNode(emCat1);
        //nodeRootSection.addChild(dtn1);     

        EntityMenu emCat10 = new EntityMenu();
        emCat10.setEntityType(ENTITY_TYPE_SECTION);
        emCat10.setEntitySection("σύστημα",loadAndGetSystemDataTreeNode(),ICO_MENUSYSTEM);//(String captionIn, int categoryLevelIn, ImageIcon icoIn)
        listSections.add(emCat10);        
        //DataTreeNode dtn2 = new DataTreeNode(emCat2);
        //nodeRootSection.addChild(dtn2); 
        


        EntityMenu emCat11 = new EntityMenu();
        emCat11.setEntityType(ENTITY_TYPE_SECTION);
        emCat11.setEntitySection("μέτρηση απόδοσης",loadAndGetMetricsSerSalesDataTreeNode(),ICO_MENUMETRICS);//(String captionIn, int categoryLevelIn, ImageIcon icoIn)
        
        
       /*  EntityMenu emCat12 = new EntityMenu();
        emCat12.setEntityType(ENTITY_TYPE_SECTION);
        emCat12.setEntitySection("μέτρηση απόδοσης",loadAndGetMetricsEsoExoDataTreeNode(),ICO_MENUMETRICS2);//(String captionIn, int categoryLevelIn, ImageIcon icoIn)
         */       
   /* if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_APLOGRAFIKA))
   {
       
        //listSections.add(emCat12);
      }
   else if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_TIMOLOGIA) )
   {

        listSections.add(emCat11);        
    }  
   else if( VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
   {
      listSections.add(emCat11);
      // listSections.add(emCat12);  
        
   }   */    
        listSections.add(emCat11);  
        
       
        return listSections;
        
    }
    
    

    
    /*
    * called by LookUpMgt.addEntitiesLookup
    */
     public ArrayList addEntitiesLookup(ArrayList entities)
    {
        ArrayList listEntities = null;
        
        entityDataSystem = new EntityDataSystem();
        listEntities = entityDataSystem.addEntitiesLookup(entities);        
        
        entityDataSersal = new EntityDataSerSales();
        listEntities = entityDataSersal.addEntitiesLookup(entities);
        
        
        entityDataEsoExo = new EntityDataEsoExo();
        listEntities = entityDataEsoExo.addEntitiesLookup(entities);         
        
        entityDataFarmersVat = new EntityDataFarmersVat();
        listEntities = entityDataFarmersVat.addEntitiesLookup(entities);
       /* entityDataTam = new EntityDataTam();
        listEntities = entityDataTam.addEntitiesLookup(entities);  */        
        
        return listEntities;
    }
     
     /*
     *  called by LookupTableConstantsMgt.addEntitiesLookupTableConstants
     */
     public ArrayList addEntitiesLookupTableConstants(ArrayList  <EntityLookupTableConstants> entities)
     {
         
        ArrayList listEntities = null;
        entityDataSersal = new EntityDataSerSales();
        listEntities = entityDataSersal.addEntitiesLookupTableConstants(entities);
                
        
        
        entityDataEsoExo = new EntityDataEsoExo();
        listEntities = entityDataEsoExo.addEntitiesLookupTableConstants(entities);        
        
 
        entityDataFarmersVat = new EntityDataFarmersVat();
        listEntities = entityDataFarmersVat.addEntitiesLookupTableConstants(entities); 
        /*entityDataTam = new EntityDataTam();
        listEntities = entityDataTam.addEntitiesLookupTableConstants(entities);   */
        
        entityDataSystem = new EntityDataSystem();
        listEntities = entityDataSystem.addEntitiesLookupTableConstants(entities);         
        
        return listEntities;
     }
     
     
   
         /*
    * called by DBFieldsMgt.addEntitiesDBFields
    */
  /*   public ArrayList addEntitiesDBFields(ArrayList entities)
    {
        ArrayList listEntities = null;
        entityDataSersal = new EntityDataSerSales();
        listEntities = entityDataSersal.addEntitiesLookup(entities);
        
        
        entityDataEsoExo = new EntityDataEsoExo();
        listEntities = entityDataEsoExo.addEntitiesLookup(entities);         
        
        entityDataTam = new EntityDataTam();
        listEntities = entityDataTam.addEntitiesLookup(entities);          
        
        return listEntities;
    }
     */
     
    
}
