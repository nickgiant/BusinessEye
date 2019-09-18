/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.model;

import com.tool.guicomps.*;
import com.tool.utils.*;


/**
 *
 * @author user
 */
public class EntityDataMetricsSerSales  extends EntityData implements Constants 
{
     DataTree dTree;
       DataTreeNode nodeRoot ;
              String globalYearPlusOne="";
       int intYearPlusOne=0;
       
    public EntityDataMetricsSerSales()
   {      
      
          	  dTree = new DataTree();
   	  nodeRoot = new DataTreeNode("root");
   	  dTree.setRootElement(nodeRoot);
   }   
       
       
   public void loadAllNodes()
   {
       //addReportSettings();
       //addEntityInfoNodes();
      addMetricsNodes();
       //addEntitiesParameters();
       //addToolNodes();       
   }

   
   public DataTree getDataTree()
   {     
   	return dTree;
   }   
   
   
     public  void addMainNavigationNodes(boolean[] isVisible) 
  {
  	
  	
         
         //System.out.println("EntityData.addMainNavigationNodes isVisible.length="+isVisible.length);
         /*for (int n =0;n<ENTITIES_CAT_ARRAY.length;n++)
         {
         	
         	String category =ENTITIES_CAT_ARRAY[n];
         	
           EntityMenu emCat = new EntityMenu();
           emCat.setEntityType(ENTITY_TYPE_CATEGORY1);
           emCat.setEntityCategory(category,1);

         	nodeRoot.addChild(new DataTreeNode(emCat));
         }*/
        
       /* EntityMenu emCat1 = new EntityMenu();
        emCat1.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat1.setEntityCategory(DATAENTRY,1,ICO_MENUCAT_EDIT);//ICO_TABLE16);
        if(isVisible[0])
        {
           nodeRoot.addChild(new DataTreeNode(emCat1));	
        }
        

        EntityMenu emCat2 = new EntityMenu();
        emCat2.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat2.setEntityCategory(REPORTS,1,ICO_MENUCAT_REPORT);//ICO_PRINT_PREVIEW16);
        if(isVisible[1])
        {
           nodeRoot.addChild(new DataTreeNode(emCat2));
        }
        
*/
        
        EntityMenu emCat4 = new EntityMenu();
        emCat4.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat4.setEntityCategory(METRICS_SERSALES_OFCOMPANY,1,ICO_MENUCAT_EDIT);//ICO_TABLE16);
        if(isVisible[3])
        {
           nodeRoot.addChild(new DataTreeNode(emCat4));
        }


        EntityMenu emCat3 = new EntityMenu();
        emCat3.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat3.setEntityCategory(METRICSOF_SALES,1,ICO_MENUCAT_EXPLORE);//ICO_STATISTICS16);
        if(isVisible[2])
        {        
           nodeRoot.addChild(new DataTreeNode(emCat3));
        }        


        
        EntityMenu emCat5 = new EntityMenu();
        emCat5.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat5.setEntityCategory(METRICSOF_ESOEXO,1,ICO_MENUCAT_EXPLORE);//ICO_STATISTICS16);        
        
    if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_APLOGRAFIKA))
   {
        if(isVisible[2])
        {        
           nodeRoot.addChild(new DataTreeNode(emCat5));
        }        

      }
   else if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_TIMOLOGIA) )
   {

        
    }  
   else if( VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
   {
        if(isVisible[2])
        {        
           nodeRoot.addChild(new DataTreeNode(emCat5));
        }        
        
   }             
        
        
 /*       EntityMenu emCat5 = new EntityMenu();
        emCat5.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat5.setEntityCategory(METRICSOF_ESOEXO,1,ICO_MENUCAT_EXPLORE);//ICO_STATISTICS16);
        if(isVisible[2])
        {        
           nodeRoot.addChild(new DataTreeNode(emCat5));
        }          
 */       

  	
  }
   
   
    public void addMetricsNodes() 
  {

      /*
    public static final int GRAPH_TYPE_PIE=1;
    public static final int GRAPH_TYPE_BAR3D=2;
    public static final int GRAPH_TYPE_LINE=3       
      */ 
      
      
      
        EntityDockableGraph[] entityDockableGraph1 = new EntityDockableGraph[3];
        //entityDockableGraph1[0] =new EntityDockableGraph("Toπ 10 προϊόντα",GRAPH_TYPE_PIE,null,null, 0,0,1,1);//"SELECT product.productId AS id, product.productName AS \"προϊόν\", COUNT(product.productId) AS \"πλήθος παρ\", SUM(invoice.value) AS \"τιμή παρ/κών\" FROM product, invoice WHERE product.productId=invoice.productId GROUP BY product.productId ORDER BY SUM(invoice.value) DESC LIMIT 10",0,0,1,1);
        //entityDockableGraph1[0] =new EntityDockableGraph("πορεία ποσών τοπ 6 εταιριών",GRAPH_TYPE_LINE,"SELECT c.dbCompanyId AS id, c.companyName, COUNT(i.dateOfSale) AS count, SUM(i.priceTotal) AS sum, AVG(i.priceTotal) AS average, MIN(i.priceTotal) FROM dbcompany c, saleheader i WHERE c.dbCompanyId=i.dbCompanyId GROUP BY i.dbCompanyId ORDER BY sum LIMIT 6","SELECT saleheader.dbCompanyId AS \"id\", dbyear.dbyearId AS \"χρήση\",COUNT(saleheader.dateOfSale) AS \"πληθ παρ\", SUM(saleheader.priceTotal) AS \"τιμή παρ/κων\", AVG(saleheader.priceTotal) AS averagepricetotal,dbcompany.companyName AS \"εταιρία\" FROM dbyear, saleheader, dbcompany WHERE dbcompany.dbCompanyId=saleheader.dbCompanyId AND dbyear.dbyearId=saleheader.dbyearId AND dbyear.dbCompanyId=saleheader.dbCompanyId GROUP BY saleheader.dbCompanyId, dbyear.dbyearId ORDER BY saleheader.dbCompanyId, dbyear.dbyearId",1,0,1,1);
        entityDockableGraph1[0] =new EntityDockableGraph("πλήθος παραστατικών τοπ 6 εταιριών",GRAPH_TYPE_LINE,"SELECT c.dbCompanyId AS id, c.companyName, COUNT(i.dateOfSale) AS count, SUM(i.priceTotal) AS sum, AVG(i.priceTotal) AS average, MIN(i.priceTotal) FROM dbcompany c, saleheader i WHERE c.dbCompanyId=i.dbCompanyId GROUP BY i.dbCompanyId ORDER BY sum LIMIT 6","SELECT saleheader.dbCompanyId AS \"id\", date_format(saleheader.dateOfSale, '%m') AS \"μήνας\",COUNT(saleheader.dateOfSale) AS \"πληθ παρ\", SUM(saleheader.priceTotal) AS \"τιμή παρ/κων\", AVG(saleheader.priceTotal) AS averagepricetotal,dbcompany.companyName AS \"εταιρία\" FROM dbyear, saleheader, dbcompany WHERE dbcompany.dbCompanyId=saleheader.dbCompanyId AND dbyear.dbyearId=saleheader.dbyearId AND dbyear.dbCompanyId=saleheader.dbCompanyId GROUP BY saleheader.dbCompanyId, date_format(saleheader.dateOfSale, '%m') ORDER BY saleheader.dbCompanyId, date_format(saleheader.dateOfSale, '%m')",1,0,1,1);
        entityDockableGraph1[1] =new EntityDockableGraph("Toπ 10 προϊόντων ",GRAPH_TYPE_PIE,"","SELECT stock.stockId AS id, stock.descr, COUNT(saleline.stockId) AS \"πληθ υπηρ\", SUM(saleline.priceBeforeVat) AS \"τιμή προ φορων\" , SUM(saleline.valueWithVat) AS \"τιμή μετα φορων\" FROM stock, saleline, saleheader WHERE saleline.saleHeaderId =  saleheader.saleHeaderId AND stock.stockId=saleline.stockId  GROUP BY stock.stockId ORDER BY SUM(saleline.valueWithVat) DESC LIMIT 10",0,1,1,1); /// AND saleline.dbyearId="+VariablesGlobal.globalYearId+" AND saleline.dbCompanyId="+VariablesGlobal.globalCompanyId+"
        //entityDockableGraph1[2] =new EntityDockableGraph("πορεία ποσών τοπ 9 προϊόντων",GRAPH_TYPE_LINE,null,null,1,1,1,1);//"SELECT p.productId as id, p.productName, COUNT(i.date) AS count, SUM(i.value) AS sum, AVG(i.value) AS average FROM product p, invoice i WHERE p.productId=i.productId GROUP BY p.productId ORDER BY sum LIMIT 9","SELECT invoice.productId AS id, dbyear.dbyear AS \"χρήση\",product.productName \"προϊόν\", COUNT(invoice.date) AS \"πληθ παρ\", SUM(invoice.value) AS \"τιμή παρ/κων\" FROM dbyear, invoice, product WHERE dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId AND Product.productId=invoice.productId GROUP BY invoice.productId, dbyear.dbyear ORDER BY invoice.productId, dbyear.dbyear",1,1,1,1);
        //entityDockableGraph1[2] =new EntityDockableGraph("πωλήσεις",GRAPH_TYPE_LINE,"SELECT saleheader.dbCompanyId AS \"idcom\", date_format(saleheader.dateOfSale, '%m') AS \"μήνας\",COUNT(saleheader.dateOfSale) AS \"πληθ παρ\", SUM(saleheader.priceTotal) AS \"τιμή παρ/κων\", AVG(saleheader.priceTotal) AS averagepricetotal,dbcompany.companyName AS \"εταιρία\" FROM dbyear, saleheader, dbcompany WHERE dbcompany.dbCompanyId=saleheader.dbCompanyId AND saleheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND dbyear.dbyearId=saleheader.dbyearId AND dbyear.dbCompanyId=saleheader.dbCompanyId GROUP BY date_format(saleheader.dateOfSale, '%m') ORDER BY date_format(saleheader.dateOfSale, '%m')",null,1,2,1,1);
        entityDockableGraph1[2] =new EntityDockableGraph("10 μεγαλύτεροι πελάτες",GRAPH_TYPE_BAR3D,null,"SELECT customer.customerId, customer.name, customer.vatNo, SUM(saleheader.pricePreVat) FROM customer INNER JOIN saleheader ON customer.customerId = saleheader.customerId WHERE customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" GROUP BY customer.customerId ORDER BY  SUM(saleheader.pricePreVat) DESC LIMIT 10",0,2,2,1);
        //entityDockableGraph1[4] =new EntityDockableGraph("υπηρσίες",GRAPH_TYPE_LINE,null,null,1,0,1,1);//"SELECT c.dbCompanyId AS id, c.companyName, COUNT(i.date) AS count, SUM(i.value) AS sum, AVG(i.value) AS average, MIN(i.value) FROM dbcompany c, invoice i WHERE c.dbCompanyId=i.dbCompanyId GROUP BY i.dbCompanyId ORDER BY sum LIMIT 6","SELECT invoice.dbCompanyId AS \"id\", dbyear.dbyear AS \"χρήση\",COUNT(invoice.date) AS \"πληθ παρ\", SUM(invoice.value) AS \"τιμή παρ/κων\", AVG(invoice.value) AS average,dbcompany.companyName AS \"εταιρία\" FROM dbyear, invoice, dbcompany WHERE dbcompany.dbCompanyId=invoice.dbCompanyId AND dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId GROUP BY invoice.dbCompanyId, dbyear.dbyear ORDER BY invoice.dbCompanyId, dbyear.dbyear",1,0,1,1);

       
       EntityFilterSettings[] scoreErs = new EntityFilterSettings[4];
       scoreErs[0]=new EntityFilterSettings("εταιρία","checkboxTable","string","","dbCompanyId","dbcompany","saleheader","",0,-1,-1,FIELD_NOCOMPLETION);
       scoreErs[1]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfSale","","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //scoreErs[2]=new EntityFilterSettings("αποστολή","checkboxTable","string","equals","deliveryId","dbDelivery","invoice","",0,-1,-1,FIELD_NOCOMPLETION); 
       scoreErs[2]=new EntityFilterSettings("πελάτης","checkboxTable","string","","customerId","customer","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //scoreErs[3]=new EntityFilterSettings("υπηρεσία","checkboxTable","string","","stockId","stock","saleline","",-1,-1,-1,FIELD_NOCOMPLETION);
       scoreErs[3]=new EntityFilterSettings("σειρά παραστατικού","checkboxTable","string","","actionseriesId","actionseries","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       
       EntityGroupOfComps[] entityGroupOfComps = null;


        EntityScoreBoard entityScoreBoardA = new EntityScoreBoard("γραφήματα",entityDockableGraph1,scoreErs,entityGroupOfComps,globalYearPlusOne);

        EntityMenu emsga = new EntityMenu();
        emsga.setEntityScoreBoard(entityScoreBoardA,ICO_CHARTBAR);
        emsga.setEntityType(ENTITY_TYPE_DOCKABLEGRAPH);
        DataTreeNode nodeemsga = new DataTreeNode(emsga);
        nodeRoot.getChildFromCaption(METRICS_SERSALES_OFCOMPANY).addChild(nodeemsga);        
     
        

        EntityStatistics[] sa = new EntityStatistics[1];
        sa[0] = new EntityStatistics("statInvoicespercompany","dbcompany","παραστατικά ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, COUNT(saleheader.actionseriesId) AS count, SUM(saleheader.priceTotal) AS sum, SUM(saleheader.priceTotalAfterWithholdingTax) AS sumaftertax, AVG(saleheader.priceTotalAfterWithholdingTax) AS average","FROM dbcompany, saleheader","WHERE dbcompany.dbCompanyId=saleheader.dbCompanyId","GROUP BY dbcompany.dbCompanyId","ORDER BY dbcompany.companyName",false,null,true,"saleheader.dateOfSale","dbCompanyId","dbCompanyId",null,null,null);                                                     																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)
        //sa[1] = new EntityStatistics("statInvoicespercompany","saleheader","παραστατικά ανα εταιρία","SELECT saleheader.customerId, saleheader.dbCompanyId,saleheader.actionseriesId, saleheader.dateOfSale,  saleheader.customerId ,saleheader.paymentTypeId, saleheader.dateOfSale,saleheader.priceTotal, saleheader.priceTotalAfterWithholdingTax","FROM saleheader","","","ORDER BY saleheader.customerId",false,null,true,"saleheader.dateOfSale","dbCompanyId","dbCompanyId",null,null,null);
        //EntityStatistics sa = new EntityStatistics("invoicespercompany","dbcompany","παραστατικά ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum, SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM dbcompany, invoice","WHERE dbcompany.dbCompanyId=invoice.dbCompanyId","GROUP BY dbcompany.dbCompanyId","ORDER BY dbcompany.companyName",false,null,true,"invoice.dbyear","dbCompanyId","dbCompanyId");                                                     																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)																								
        EntityMenu emsa = new EntityMenu();
        emsa.setEntityStatistics(sa,ICO_STATISTICS16);
        emsa.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsa = new DataTreeNode(emsa);
        nodeRoot.getChildFromCaption(METRICS_SERSALES_OFCOMPANY).addChild(nodeemsa);


        EntityStatistics[] sb = new EntityStatistics[1];
        sb[0] = new EntityStatistics("statDeliveriespercompany","dbcompany","αποστολές ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, saleheader.dateOfSale,  COUNT(saleheader.dbYearId) AS count, SUM(saleheader.priceTotal) AS sum, SUM(saleheader.priceTotalAfterWithholdingTax) AS sumwithtax","FROM dbcompany, saleheader","WHERE dbcompany.dbCompanyId=saleheader.dbCompanyId","GROUP BY dbcompany.dbCompanyId, saleheader.dateOfSale","ORDER BY dbcompany.companyName, saleheader.dateOfSale",true,"saleheader.dbCompanyId",true,"saleheader.dateOfSale","dbCompanyId","dbCompanyId",null,null,null);                                                     																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)
        //sb[1] = new EntityStatistics("statDeliveriespercompany","saleheader","παραστατικά ανα εταιρία","SELECT saleheader.customerId, saleheader.dbCompanyId,saleheader.dateOfSale, saleheader.actionseriesId, saleheader.customerId ,saleheader.paymentTypeId, saleheader.dateOfSale, saleheader.priceTotal AS pricetotal, saleheader.priceTotalAfterWithholdingTax AS priceafterwithtax","FROM saleheader","","","ORDER BY saleheader.customerId",true,"saleheader.dbCompanyId",true,"saleheader.dateOfSale","dbCompanyId","dbCompanyId",null,null,null);
        //EntityStatistics sa = new EntityStatistics("invoicespercompany","dbcompany","παραστατικά ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum, SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM dbcompany, invoice","WHERE dbcompany.dbCompanyId=invoice.dbCompanyId","GROUP BY dbcompany.dbCompanyId","ORDER BY dbcompany.companyName",false,null,true,"invoice.dbyear","dbCompanyId","dbCompanyId");                                                     																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)																								
        EntityMenu emsb = new EntityMenu();
        emsb.setEntityStatistics(sb,ICO_STATISTICS16);
        emsb.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsb = new DataTreeNode(emsb);
  //      nodeRoot.getChildFromCaption(METRICSOFCOMPANY).addChild(nodeemsb);


        
        EntityStatistics[] sc = new EntityStatistics[1];
        sc[0] = new EntityStatistics("statInvoicesperyear","saleheader","παραστατικά ανα χρήση","SELECT dbyear.dbyearId, COUNT(saleheader.priceTotal) AS count, SUM(saleheader.priceTotal) AS sum,SUM(saleheader.priceTotalAfterWithholdingTax) AS sumwitwittax, AVG(saleheader.priceTotal) AS averagepricetotal","FROM dbyear, saleheader","WHERE dbyear.dbyearId=saleheader.dbyearId AND dbyear.dbCompanyId=saleheader.dbCompanyId","GROUP BY dbyear.dbyearId","ORDER BY dbyear.dbyearId",true,"dbyear.dbCompanyId",false,null,null,null,null,null,null);
        //EntityStatistics sb = new EntityStatistics("invoicesperyear","invoice","παραστατικά ανα χρήση","SELECT dbyear.dbyear, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum,SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM dbyear, invoice","WHERE dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId","GROUP BY dbyear.dbyear","ORDER BY dbyear.dbyear",true,"dbyear.dbCompanyId",false,null,null,null);
        EntityMenu emsc = new EntityMenu();
        emsc.setEntityStatistics(sc,ICO_STATISTICS16);
        emsc.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsc = new DataTreeNode(emsc);
        nodeRoot.getChildFromCaption(METRICSOF_SALES).addChild(nodeemsc);

        EntityStatistics[] sd = new EntityStatistics[1];
        sd[0] = new EntityStatistics("statSumsofCustomers","customer","ποσά πελατών","SELECT customer.customerId, customer.name, COUNT(saleheader.customerId) AS count, SUM(saleheader.priceTotal) AS sum,SUM(saleheader.priceTotalAfterWithholdingTax) AS sumret, AVG(saleheader.priceTotalAfterWithholdingTax) AS average","FROM customer, saleheader","WHERE customer.customerId=saleheader.customerId","GROUP BY customer.customerId","ORDER BY customer.name",true,"saleheader.dbCompanyId",true,"saleheader.dateOfSale","customerId","customerId",null,null,null);
        //sd[1] = new EntityStatistics("statSumsofCustomers2","saleheader","ποσά πελατών","SELECT customer.customerId,saleheader.dbCompanyId,saleheader.dateOfSale,  saleheader.customerId ,saleheader.paymentTypeId, saleheader.dateOfSale,saleheader.priceTotal, saleheader.priceTotalAfterWithholdingTax","FROM saleheader","","","ORDER BY saleheader.customerIdId",true,"saleheader.dbCompanyId",true,"saleheader.dateOfSale","customerId","customerId",null,null,null);
        //EntityStatistics sc = new EntityStatistics("sumsofbuyers","buyer","ποσά αγοραστών","SELECT buyer.buyerId, buyer.buyerTitle, COUNT(invoice.buyerId) AS count, SUM(invoice.value) AS sum,SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM buyer, invoice","WHERE buyer.buyerId=invoice.buyerId","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear","buyerId","buyerId");
        EntityMenu emsd = new EntityMenu();
        emsd.setEntityStatistics(sd,ICO_STATISTICS16);
        emsd.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsd = new DataTreeNode(emsd);
        nodeRoot.getChildFromCaption(METRICSOF_SALES).addChild(nodeemsd);
      
        
        EntityStatistics[] sg = new EntityStatistics[1];
        sg[0] = new EntityStatistics("statInvoicespermonth","saleheader","παραστατικά ανα μήνα","SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(saleheader.value) AS \"ΣΥΝΟΛΟ\", AVG(saleheader.value) AS \"Μ.Ο.\",SUM(saleheader.valueReturn) AS \"ΕΠΙΣΤΡΟΦΗ\"","FROM saleheader",""/*saleheader.customerId the same because we need where*/,"GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"saleheader.dbCompanyId",true,"saleheader.dbyear",null,null,null,null,null);
        
        //EntityStatistics se = new EntityStatistics("invoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'name') AS \"μήνας\" , COUNT(*)AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.returnValue) AS sumret","FROM invoice","WHERE"/*invoice.customerId the same because we need where*/,"GROUP BY returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
        EntityMenu emsg = new EntityMenu();
        emsg.setEntityStatistics(sg,ICO_STATISTICS16);
        emsg.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsg = new DataTreeNode(emsg);
        nodeRoot.getChildFromCaption(METRICSOF_SALES).addChild(nodeemsg);

   
   
   
  }

    
}
