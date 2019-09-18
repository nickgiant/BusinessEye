// created 06-07-2007
// FarmersVat
package com.tool.model;

import com.tool.guicomps.*;
import com.tool.utils.*;


import java.util.ArrayList;

public class EntityDataFarmersVat implements Constants 
{
       DataTree dTree;
       DataTreeNode nodeRoot ;

    public static final String REPORT_CAT_ECONOMIC = "economic";
    public static final String REPORT_CAT_APPLICATIONS = "form";
    public static final String REPORT_CAT_INFORMATIVE = "informative";
    public static final String REPORT_CAT_CATALOGS = "catalogs";
       String globalYearPlusOne="";
       int intYearPlusOne=0;

       //----------------------------------------------------------------
        EntityDBFields[] farmerDBFields = new EntityDBFields[16];
        EntityGroupOfComps[] farmerEntityGroupOfComps = new EntityGroupOfComps[5];
        EntityGroupOfPanels[] farmerEntityGroupOfPanels = new EntityGroupOfPanels[2];
        
        // same as second query in etityInfo
        //String farmerQueryEditable="SELECT frvfarmer.farmerId AS \"Νο αγρότη\", frvfarmer.surname AS \"επίθετο\", frvfarmer.name AS\"όνομα\", frvfarmer.fathername AS \"πατρόνυμο\", frvfarmer.farmerAfm AS \"Α.Φ.Μ.\", frvfarmer.doyId, frvfarmer.idNo AS \"αρ ταυτοτ\", frvfarmer.townId, frvfarmer.address AS \"διέυθυνση\", frvfarmer.phone AS \"τηλέφωνο\" FROM frvfarmer, frvtown WHERE frvfarmer.townId=frvtown.townId";
        //String farmerQueryEditable="SELECT frvfarmer.farmerId AS \"Νο αγρότη\", frvfarmer.surname AS \"επίθετο\", frvfarmer.name AS \"όνομα\", frvfarmer.fathername AS \"πατρό /n νυμο\", frvfarmer.farmerAfm AS \"Α.Φ.Μ.\",frvfarmer.idNo AS \"αρ ταυτοτ\", frvfarmer.townId, frvfarmer.doyId , frvfarmer.notes, frvfarmer.address AS \"διέυθυνση\", frvfarmer.phone AS \"τηλέφωνο(1) οικίας\", frvfarmer.phone2 AS \"τηλέφωνο(2)\",frvfarmer.phone3 AS \"τηλέφωνο(3)\", frvfarmer.bankId, frvfarmer.bankAccount FROM frvfarmer LEFT JOIN doy ON frvfarmer.doyId=doy.doyId LEFT JOIN frvtown ON frvfarmer.townId=frvtown.townId LEFT JOIN bank ON frvfarmer.bankId=bank.bankId";        
        String farmerQueryEditable="SELECT * FROM frvfarmer";
        String[] fieldsOnTitleFarmer ={"farmerId","surname","name","fathername","farmerAfm"};
        String[] fieldsOnTitleCaptionFarmer  ={"Νο","επώνυμο","όνομα","πατρ","ΑΦΜ"};
        String[] fieldsUniqueFarmer = {"farmerAfm"};
        String[] strFarmerCategories = {DATAENTRY,METRICS};
        // EntityPanel[] entityPanelFarmer = new EntityPanel[1];
        EntityPanel entityPanelFarmerDataentry = new EntityPanel("ODOR","frvfarmer",farmerDBFields,farmerEntityGroupOfComps,farmerEntityGroupOfPanels,"Νο αγρότη","","farmerId",farmerQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueFarmer,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,null,null);       //EntityUpdateAdditional
 //       EntityPanel entityPanelFarmerStatistics = new EntityPanel("statFarmerHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.companyName AS \"τίτλος συν/σμού\", frvapplicationline.deliveryId AS \"αποστολή\", COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"συνολο\", AVG(frvapplicationline.value) AS \"Μ.Ο.\", MIN(frvapplicationline.value) AS \"ελαχιστο\", MAX(frvapplicationline.value) AS \"μέγιστο\"","FROM frvapplicationline, dbcompany","WHERE frvapplicationline.dbCompanyId = dbcompany.dbCompanyId AND frvapplicationline.farmerId=","GROUP BY dbyear, frvapplicationline.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.companyName, frvapplicationline.deliveryId",false,"",false,"");     
 //       EntityPanel entityPanelFarmerProducts = new EntityPanel("statFarmerProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT frvproduct.productId AS \"Νο προϊόντος\", frvproduct.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"σύνολο\"","FROM frvapplicationline, frvproduct","WHERE frvapplicationline.productId = frvproduct.productId AND frvapplicationline.farmerId=","GROUP BY frvproduct.productId","ORDER BY frvproduct.productName",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
 //       EntityPanel entityPanelFarmerBuyers = new EntityPanel("statFarmerBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT frvbuyer.buyerId AS \"νο αγοραστή\", frvbuyer.buyerTitle,frvbuyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"σύνολο\"","FROM frvapplicationline, frvbuyer","WHERE frvapplicationline.buyerId = frvbuyer.buyerId AND frvapplicationline.farmerId=","GROUP BY frvbuyer.buyerId","ORDER BY frvbuyer.buyerTitle",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
 //       EntityPanel entityPanelFarmerSalesPerDate = new EntityPanel("statFarmerSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(frvapplicationline.value) AS \"ΣΥΝΟΛΟ\", AVG(frvapplicationline.value) AS \"Μ.Ο.\"","FROM frvapplicationline","WHERE frvapplicationline.farmerId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
        EntityPanel[] entityPanelFarmer = new EntityPanel[] { entityPanelFarmerDataentry};//,entityPanelFarmerStatistics,entityPanelFarmerProducts,entityPanelFarmerBuyers,entityPanelFarmerSalesPerDate};
        
        
        /*String[] deliveryFields={"farmerId","dateOfApplication","permanent","dbyear","deliveryId","dbCompanyId"};
        String[] deliveryFieldsTranslation={"farmerId","ημ/νία αίτησης","υπολογισμένο","dbyear","deliveryId","dbCompanyId"};
        int[] applicationHeaderGroupOfComps = null;*/
       //---------------------------------------------------------------- 
        EntityDBFields[] applicationHeaderDBFields = new EntityDBFields[11];
        EntityGroupOfComps[] applicationHeaderGroupOfComps = new EntityGroupOfComps[4];
        EntityGroupOfPanels[] applicationHeaderGroupOfPanels = null;
        EntityDBFields[] applicationLineDBFields = new EntityDBFields[12];
   //     EntityGroupOfComps[] applicationLineGroupOfPanels = null;
        
        String applicationQueryEditable = "SELECT * FROM frvapplicationheader";
        String[] fieldsOnTitleApplication ={"applicationHeaderId","surname","name","farmerAfm","dateOfApplication"};
        String[] fieldsOnTitleCaptionApplication  ={"Νο αίτησης","επίθετο","όνομα","ΑΦΜ", "ημ/νια αιτησ."};       
        String[] fieldsUniqueApplication = {"dbYearDeliveryId","farmerId","dbCompanyId","dbYearId"}; // field not visible/editable first (not sure if is correct)
        
        String[] strApplicationCategories = {DATAENTRY,METRICS};
        EntityPanel entityPanelApplicationDataentry = new EntityPanel("ODOR","frvapplicationheader",applicationHeaderDBFields,applicationHeaderGroupOfComps,applicationHeaderGroupOfPanels,"Νο αίτησης","","applicationHeaderId",applicationQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueApplication,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,2,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_NO,false,false,null,null,null,null,null);     //EntityUpdateAdditional
 //       EntityPanel entityPanelBuyerStatistics = new EntityPanel("statBuyerHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.companyName AS \"τίτλος συν/σμού\", frvapplicationline.deliveryId AS \"αποστολή\", COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"sum\", AVG(frvapplicationline.value) AS \"average\", MIN(frvapplicationline.value) AS \"min\", MAX(frvapplicationline.value) AS \"max\"","FROM frvapplicationline, dbcompany","WHERE frvapplicationline.dbCompanyId = dbcompany.dbCompanyId AND frvapplicationline.buyerId=","GROUP BY dbyear, frvapplicationline.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.companyName, frvapplicationline.deliveryId",false,"",false,"");
 //       EntityPanel entityPanelBuyerProducts = new EntityPanel("statBuyerProducts","STATS",null,"προϊόντα",ICO_STATISTICS16,"SELECT frvproduct.productId AS \"Νο προϊόντος\", frvproduct.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"σύνολο\"","FROM frvapplicationline, frvproduct","WHERE frvapplicationline.productId = frvproduct.productId AND frvapplicationline.buyerId=","GROUP BY frvproduct.productId","ORDER BY frvproduct.productName",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
 //       EntityPanel entityPanelBuyerFarmers = new EntityPanel("statBuyerFarmers","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT frvfarmer.farmerId AS \"νο αγρότη\", frvfarmer.surname, frvfarmer.name, frvfarmer.fatherName,frvfarmer.farmerAfm, COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"σύνολο\"","FROM frvapplicationline, frvfarmer","WHERE frvapplicationline.farmerId = frvfarmer.farmerId AND frvapplicationline.buyerId=","GROUP BY frvfarmer.farmerId","ORDER BY frvfarmer.surname, frvfarmer.name, frvfarmer.fatherName,frvfarmer.farmerAfm",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
 //       EntityPanel entityPanelBuyerBuysPerDate = new EntityPanel("statBuyerBuysPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(frvapplicationline.value) AS \"ΣΥΝΟΛΟ\", AVG(frvapplicationline.value) AS \"Μ.Ο.\"","FROM frvapplicationline","WHERE frvapplicationline.buyerId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
        EntityPanel[] entityPanelApplication = new EntityPanel[] { entityPanelApplicationDataentry};//,entityPanelBuyerStatistics,entityPanelBuyerProducts,entityPanelBuyerFarmers,entityPanelBuyerBuysPerDate};
        
        
        
        // declare lookup fields here and at look up the no of fields 'intNoOfColsWhenInTable'
        /*String[] deliveryFieldsMany={"aa","buyerId","frvbuyer","applicationlineTypeId","applicationlineNo","date","productId","value"};
        String[] deliveryFieldsManyTranslation={"αα","Νο αγοραστή","ονομασία αγοραστής","είδος παραστατικού","αρ παρ/κού","ημερομηνία","ονομασία προϊόντος","αξία"};
        int[] applicationLineGroupOfPanels = null;
        EntityDBFields applicationLineDBFields = new EntityDBFields (deliveryFieldsMany,deliveryFieldsManyTranslation,applicationLineGroupOfPanels);
         */
        
     //   String[] fieldsForSumsInvoice = {"αξία","επιστροφή"};
        
        //String[] deliveryFieldsManyOnInsert={"farmerId","dbyear","deliveryId","dbCompanyId","aa","buyerId","applicationlineTypeId","applicationlineNo","date","productId","value","productTypePercentage","valueReturn"};
        //String[] deliveryFieldsManyTranslationOnInsert={"farmerId","dbyear","deliveryId","dbCompanyId","αα","Νο αγοραστή","είδος παραστατικού","αρ παρ/κού","ημερομηνία","ονομασία προϊόντος","αξία","ποσοστό","επιστροφή"};
        
   //     String[] deliveryWhereField ={"farmerId","dbCompanyId","deliveryId","dbyear"};
   //     String[] deliveryWhereValue ={"primKeyValue","globalCompanyId","globalDeliveryId","globalYear"};// global should contain global into their name (needed by panelOneDataOneRecData.checkIfNameIsWhereField)

        //String[] deliveryPrimKeyMany={"buyerId","applicationlineNo","applicationlineTypeId","date"};
        //String[] deliveryPrimKeyManyTran={"buyerId","αρ παρ/κού","applicationlineTypeId","ημερομηνία"};        
        
        //deliveryQueryEditable;
        /*String  deliveryQueryEditable;// = "SELECT d.farmerId, d.dateOfApplication, d.dbyear, d.deliveryId, d.dbCompanyId FROM frvapplicationheader d WHERE dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+" AND dbCompanyId="+VariablesGlobal.globalCompanyId;
        String deliveryQueryManyReadOnly;
        String deliveryQueryManyEditable;
        EntityPanel entityPanelDeliveryDataentry ;*/
        
                
        
       // EntityPanel[] entityPanelDelivery; // initialized in loadGenericData
        
        //----------------------------------------------------------------
        EntityDBFields[] buyerDBFields = new EntityDBFields[8];
        EntityGroupOfComps[] buyerEntityGroupOfComps = new EntityGroupOfComps[3];
        

        //EntityGroupOfComps buyerEntityGroupOfComps =null;*/
        EntityGroupOfPanels[] buyerEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        
        String buyerQueryEditable = "SELECT * FROM frvbuyer";
        String[] fieldsOnTitleBuyer ={"buyerId","buyerTitle","buyerAfm"};
        String[] fieldsOnTitleCaptionBuyer  ={"Νο","τίτλος","ΑΦΜ"};  
        String[] fieldsUniqueBuyer = {"buyerAfm"};
      
        String[] strBuyerCategories = {DATAENTRY,METRICS};
        EntityPanel entityPanelBuyerDataentry = new EntityPanel("ODOR","frvbuyer",buyerDBFields,buyerEntityGroupOfComps,buyerEntityGroupOfPanels,"Νο αγοραστή","","buyerId",buyerQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueBuyer,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null, null,null);      //EntityUpdateAdditional
 //       EntityPanel entityPanelBuyerStatistics = new EntityPanel("statBuyerHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.companyName AS \"τίτλος συν/σμού\", frvapplicationline.deliveryId AS \"αποστολή\", COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"sum\", AVG(frvapplicationline.value) AS \"average\", MIN(frvapplicationline.value) AS \"min\", MAX(frvapplicationline.value) AS \"max\"","FROM frvapplicationline, dbcompany","WHERE frvapplicationline.dbCompanyId = dbcompany.dbCompanyId AND frvapplicationline.buyerId=","GROUP BY dbyear, frvapplicationline.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.companyName, frvapplicationline.deliveryId",false,"",false,"");
 //       EntityPanel entityPanelBuyerProducts = new EntityPanel("statBuyerProducts","STATS",null,"προϊόντα",ICO_STATISTICS16,"SELECT frvproduct.productId AS \"Νο προϊόντος\", frvproduct.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"σύνολο\"","FROM frvapplicationline, frvproduct","WHERE frvapplicationline.productId = frvproduct.productId AND frvapplicationline.buyerId=","GROUP BY frvproduct.productId","ORDER BY frvproduct.productName",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
 //       EntityPanel entityPanelBuyerFarmers = new EntityPanel("statBuyerFarmers","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT frvfarmer.farmerId AS \"νο αγρότη\", frvfarmer.surname, frvfarmer.name, frvfarmer.fatherName,frvfarmer.farmerAfm, COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"σύνολο\"","FROM frvapplicationline, frvfarmer","WHERE frvapplicationline.farmerId = frvfarmer.farmerId AND frvapplicationline.buyerId=","GROUP BY frvfarmer.farmerId","ORDER BY frvfarmer.surname, frvfarmer.name, frvfarmer.fatherName,frvfarmer.farmerAfm",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
 //       EntityPanel entityPanelBuyerBuysPerDate = new EntityPanel("statBuyerBuysPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(frvapplicationline.value) AS \"ΣΥΝΟΛΟ\", AVG(frvapplicationline.value) AS \"Μ.Ο.\"","FROM frvapplicationline","WHERE frvapplicationline.buyerId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
        EntityPanel[] entityPanelBuyer = new EntityPanel[] { entityPanelBuyerDataentry};//,entityPanelBuyerStatistics,entityPanelBuyerProducts,entityPanelBuyerFarmers,entityPanelBuyerBuysPerDate};

        //----------------------------------------------------------------
        
        EntityDBFields[] productDBFields = new EntityDBFields[1];
        EntityDBFields[] productLineDBFields = new EntityDBFields[3];
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] productEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] productEntityGroupOfPanels = null;
        
        
        String productQueryEditable = "SELECT * FROM frvproduct";
        String[] fieldsOnTitleProduct ={"productId","productName"};
        String[] fieldsOnTitleCaptionProduct  ={"Νο","ονομασία"}; 
        String[] fieldsUniqueProduct = null;
     
        String[] strProductCategories = {DATAENTRY,METRICS};

        EntityPanel entityPanelProductDataentry = new EntityPanel("ODOR","frvproduct",productDBFields,productEntityGroupOfComps,productEntityGroupOfPanels,"Νο προϊόντος","","productId",productQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueProduct,0,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_NO,0,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_NO,false,false,null,null,null,null,null);     //EntityUpdateAdditional   
  //      EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.companyName AS \"τίτλος συν/σμού\", frvapplicationline.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(frvapplicationline.value) AS sum, AVG(frvapplicationline.value) AS average, MIN(frvapplicationline.value) AS min, MAX(frvapplicationline.value) AS max","FROM frvapplicationline, dbcompany","WHERE frvapplicationline.dbCompanyId = dbcompany.dbCompanyId AND frvapplicationline.productId=","GROUP BY dbyear, frvapplicationline.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.companyName, frvapplicationline.deliveryId",false,"",false,"");
  //      EntityPanel entityPanelProductFarmers = new EntityPanel("statProductFarmers","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT frvfarmer.farmerId AS \"νο αγρότη\", frvfarmer.surname, frvfarmer.name, frvfarmer.fatherName,frvfarmer.farmerAfm, COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"σύνολο\"","FROM frvapplicationline, frvfarmer","WHERE frvapplicationline.farmerId = frvfarmer.farmerId AND frvapplicationline.productId=","GROUP BY frvfarmer.farmerId","ORDER BY frvfarmer.surname, frvfarmer.name, frvfarmer.fatherName,frvfarmer.farmerAfm",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
  //      EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT frvbuyer.buyerId AS \"νο αγοραστή\", frvbuyer.buyerTitle,frvbuyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(frvapplicationline.value) AS \"σύνολο\"","FROM frvapplicationline, frvbuyer","WHERE frvapplicationline.buyerId = frvbuyer.buyerId AND frvapplicationline.productId=","GROUP BY frvbuyer.buyerId","ORDER BY frvbuyer.buyerTitle",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
  //      EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(frvapplicationline.value) AS \"ΣΥΝΟΛΟ\", AVG(frvapplicationline.value) AS \"Μ.Ο.\"","FROM frvapplicationline","WHERE frvapplicationline.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear");
        EntityPanel[] entityPanelProduct = new EntityPanel[] { entityPanelProductDataentry};//,entityPanelProductStatistics,entityPanelProductFarmers,entityPanelProductBuyers,entityPanelProductSalesPerDate};
        
         //----------------------------------------------------------------
       EntityDBFields[] invoicetypeDBFields = new EntityDBFields[1];
        EntityDBFields[] invoicetypeLineDBFields = new EntityDBFields[3];          
        
        EntityGroupOfComps[] invoicetypeEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] invoicetypeEntityGroupOfPanels = null;

        String invoicetypeQueryEditable = "SELECT * FROM frvinvoicetype";
        String[] fieldsOnTitleInvoiceType ={"invoiceTypeId","invoiceTypeName"};
        String[] fieldsOnTitleCaptionInvoiceType  ={"Νο","ονομασία"};
        String[] fieldsUniqueInvoiceType = null;

        EntityPanel entityPanelInvoicetypeDataentry = new EntityPanel("ODOR","frvinvoicetype",invoicetypeDBFields,invoicetypeEntityGroupOfComps,invoicetypeEntityGroupOfPanels,"Νο τύπου παρ/κού","","invoiceTypeId",invoicetypeQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueInvoiceType,0,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,0,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,null,null);   //EntityUpdateAdditional     
        EntityPanel[] entityPanelInvoiceType = new EntityPanel[] { entityPanelInvoicetypeDataentry};

        //----------------------------------------------------------------
        EntityDBFields[] producttypeDBFields = new EntityDBFields[1];
        EntityDBFields[] producttypeLineDBFields = new EntityDBFields[4];
        
        EntityGroupOfComps[] producttypeEntityGroupOfComps =new EntityGroupOfComps[1];
        EntityGroupOfPanels[] producttypeEntityGroupOfPanels = null;
        
        String producttypeQueryEditable ="SELECT * FROM frvproducttype";
        String[] fieldsOnTitleProductType ={"productTypeId","productTypeName"};
        String[] fieldsOnTitleCaptionProductType  ={"Νο","ονομασία"};      
        String[] fieldsUniqueProductType = null;
        
        EntityPanel entityPanelProducttypeDataentry = new EntityPanel("ODOR","frvproducttype",producttypeDBFields,producttypeEntityGroupOfComps,producttypeEntityGroupOfPanels,"Νο τύπου προϊόντος","","productTypeId",producttypeQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueProductType,0,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,0,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,null,null);       //EntityUpdateAdditional
        EntityPanel[] entityPanelProductType = new EntityPanel[] { entityPanelProducttypeDataentry};
 
        //----------------------------------------------------------------
        EntityDBFields[] townDBFields = new EntityDBFields[1];
        EntityDBFields[] townLineDBFields = new EntityDBFields[5];
        
        
        EntityGroupOfComps[] townEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] townEntityGroupOfPanels = null;
        
        
        String townQueryEditable="SELECT * FROM frvtown";
        String[] fieldsOnTitleTown ={"townId","townName"};
        String[] fieldsOnTitleCaptionTown  ={"Νο","ονομασία"}; 
        String[] fieldsUniqueTown = null;
        
        EntityPanel entityPanelTownDataentry = new EntityPanel("ODOR","frvtown",townDBFields,townEntityGroupOfComps,townEntityGroupOfPanels,"Νο πόλης","","townId",townQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueTown,0,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,0,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,null,null);        //EntityUpdateAdditional
        EntityPanel[] entityPanelTown = new EntityPanel[] {entityPanelTownDataentry};        
        
        //----------------------------------------------------------------
        /*EntityDBFields[] doyDBFields = new EntityDBFields[1];
        EntityDBFields[] doyLineDBFields = new EntityDBFields[8];
        
        EntityGroupOfComps[] doyEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] doyEntityGroupOfPanels = null;

        String doyQueryEditable="SELECT * FROM doy";
        String[] fieldsOnTitleDoy ={"doyId","doyName"};
        String[] fieldsOnTitleCaptionDoy  ={"Νο","ονομασία"};   
        String[] fieldsUniqueDoy = null;
     
        EntityPanel entityPanelDoyDataentry = new EntityPanel("ODOR","doy",doyDBFields,doyEntityGroupOfComps,doyEntityGroupOfPanels,"Νο Δ.Ο.Υ.","","doyId",doyQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDoy,0,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,0,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,null);      //EntityUpdateAdditional
        EntityPanel[] entityPanelDoy = new EntityPanel[] { entityPanelDoyDataentry};  
        */
        
        //----------------------------------------------------------------
       
      /*  EntityDBFields[] bankDBFields = new EntityDBFields[1];
        EntityDBFields[] bankLineDBFields = new EntityDBFields[2];
        
        EntityGroupOfComps[] bankEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] bankEntityGroupOfPanels = null;
        
       
       

        String bankQueryEditable="SELECT * FROM bank";
        String[] fieldsOnTitleBank ={"bankId","bankBranch"};
        String[] fieldsOnTitleCaptionBank  ={"Νο","ονομασία"};       
        String[] fieldsUniqueBank = null;
        
        EntityPanel entityPanelBankDataentry = new EntityPanel("ODOR","bank",bankDBFields,bankEntityGroupOfComps,bankEntityGroupOfPanels,"Νο τράπεζας","","bankId",bankQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueBank,0,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,0,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,null);     
        EntityPanel[] entityPanelBank = new EntityPanel[] { entityPanelBankDataentry};  
        */        
        
        
        
        
        // same as DialogLogin
        //----------------------------------------------------------------
       /* EntityDBFields[] dbCompanyDBFields = new EntityDBFields[13];        
        EntityGroupOfComps[] dbCompanyEntityGroupOfComps= new EntityGroupOfComps[5];


        EntityGroupOfPanels[] dbCompanyEntityGroupOfPanels = null;
        
        String dbCompanyQueryEditable="SELECT * FROM dbcompany";//SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.companyName AS\"τίτλος\", dbcompany.companyVatNo AS\"Α.Φ.Μ.\", dbcompany.doyId, dbcompany.president ,dbcompany.townId,  dbcompany.bankId , dbcompany.bankAccount , dbcompany.bankAccountIBAN , dbcompany.notes FROM dbcompany";
        String[] fieldsOnTitleDbCompany ={"dbCompanyId","companyName","companyVatNo"};
        String[] fieldsOnTitleCaptionDbCompany  ={"Νο","τίτλος","ΑΦΜ"}; 
        String[] fieldsUniqueDbCompany = {"companyVatNo"};
        
        EntityPanel entityPanelDbCompanyDataentry = new EntityPanel("ODOR","dbcompany",dbCompanyDBFields,dbCompanyEntityGroupOfComps,dbCompanyEntityGroupOfPanels,"Νο εταιρίας","","dbCompanyId",dbCompanyQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbCompany,0,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,0,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,null); //EntityUpdateAdditional     
        EntityPanel[] entityPanelDbCompany = new EntityPanel[] {entityPanelDbCompanyDataentry};          
        */
        //----------------------------------------------------------------
       /* EntityDBFields[] dbuserDBFields = new EntityDBFields[4];

        EntityGroupOfComps dbuserEntityGroupOfComps[] =null;
        EntityGroupOfPanels dbuserEntityGroupOfPanels[] = null;
        
        
        String dbUserQueryEditable="SELECT * FROM dbuser";
        String[] fieldsOnTitleDbuser ={"userId","username","nameOfUser"};
        String[] fieldsOnTitleCaptionDbuser  ={"Νο","user","πλήρες όνομα"};  
        String[] fieldsUniqueDbUser = null;
        

        EntityPanel entityPanelDbuserDataentry = new EntityPanel("ODOR","dbuser",dbuserDBFields,dbuserEntityGroupOfComps,dbuserEntityGroupOfPanels,"Νο χρήστη","","userId",dbUserQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbUser,0,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,0,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,null,null);  
        EntityPanel[] entityPanelDbuser = new EntityPanel[] { entityPanelDbuserDataentry}; 
        */
        //----------------------------------------------------------------
        EntityDBFields[] dbYearDBFields = new EntityDBFields[4];
        //EntityDBFields[] dbYearDBFields= new EntityDBFields[5];

        EntityGroupOfComps[] dbyearEntityGroupOfComps =new EntityGroupOfComps[2];
        EntityGroupOfPanels[] dbyearEntityGroupOfPanels = null;
        
        
        String dbyearQueryEditable="SELECT * FROM dbyear";
        String[] fieldsOnTitleDbyear ={"dbyear"};
        String[] fieldsOnTitleCaptionDbyear  ={"χρήση"}; 
        String[] fieldsUniqueDbYear = {"dbyear"};

        EntityPanel entityPanelDbyearDataentry = new EntityPanel("ODOR","dbyear",dbYearDBFields,dbyearEntityGroupOfComps,dbyearEntityGroupOfPanels,"Νο χρήσης","","dbYearId",dbyearQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbYear,0,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,0,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,null,null);  
        EntityPanel[] entityPanelDbyear = new EntityPanel[] { entityPanelDbyearDataentry};         
        

        //-----------------------------
       
 /*       EntityGroupOfComps[] dbDeliveryEntityGroupOfComps =null;
        EntityGroupOfPanels[] dbDeliveryEntityGroupOfPanels = null;
        
        EntityDBFields[] dbDeliveryDBFields = new EntityDBFields[2];
        String dbDeliveryQueryEditable="SELECT * FROM dbDelivery";
        String[] fieldsOnTitleDbDelivery = {"deliveryId","description"};
        String[] fieldsOnTitleCaptionDbDelivery  ={"Νο","περιγραφή"};        
        EntityPanel entityPaneDbDeliveryDataentry = new EntityPanel("ODOR","dbdelivery",dbDeliveryDBFields,dbDeliveryEntityGroupOfComps,dbDeliveryEntityGroupOfPanels,"Νο αποστολής","","deliveryId",dbDeliveryQueryEditable,"βασικά στοιχεία",null, false, true,false,false,false,null);      
        EntityPanel[] entityPanelDbDelivery = new EntityPanel[] { entityPaneDbDeliveryDataentry};  */

        EntityGroupOfComps[] dbYearDeliveryEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] dbYearDeliveryEntityGroupOfPanels = null;
        
        EntityDBFields[] dbYearDeliveryDBFields = new EntityDBFields[6];
        String dbYearDeliveryQueryEditable="SELECT * FROM frvyeardelivery";
        String[] fieldsOnTitleDbYearDelivery = {"dbYearDeliveryId","description"};
        String[] fieldsOnTitleCaptionDbYearDelivery  ={"Νο","περιγραφή"};      
        String[] fieldsUniqueDbYearDelivery = null; 
        
        EntityPanel entityPaneDbYearDeliveryDataentry = new EntityPanel("ODOR","frvyeardelivery",dbYearDeliveryDBFields,dbYearDeliveryEntityGroupOfComps,dbYearDeliveryEntityGroupOfPanels,"Νο αποστολής","","dbYearDeliveryId",dbYearDeliveryQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbYearDelivery,0,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,0,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,null,null);  
        EntityPanel[] entityPanelDbYearDelivery = new EntityPanel[] { entityPaneDbYearDeliveryDataentry};         
        

   public EntityDataFarmersVat()
   {
          // need them
   	  dTree = new DataTree();
   	  nodeRoot = new DataTreeNode("root");
   	  dTree.setRootElement(nodeRoot);



/*
    FIELD_NOCOMPLETION = 0;
    FIELD_OBLIGATORY = 1;
    FIELD_SUGGEST = 2;
    
    FIELD_VALIDATION_NO = 0  FIELD_VALIDATION_AFM = 1
*/

        EntityDBFields[] farmerTelephonesDBFields = new EntityDBFields[6];
        
        farmerTelephonesDBFields[0] = new EntityDBFields("frvfarmertelephone","farmerTelephoneId","farmerTelephoneId",0,"java.lang.Integer",10,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerTelephonesDBFields[1] = new EntityDBFields("frvfarmertelephone","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerTelephonesDBFields[2] = new EntityDBFields("frvfarmertelephone","farmerId","Νο αγρότη",0,"java.lang.String",8, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        
        farmerTelephonesDBFields[3] = new EntityDBFields("frvfarmertelephone","department","τοποθεσία ή τμήμα",0,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerTelephonesDBFields[4] = new EntityDBFields("frvfarmertelephone","telephone","τηλέφωνο",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerTelephonesDBFields[5] = new EntityDBFields("frvfarmertelephone","person","πρόσωπο",0,"java.lang.String",23,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
          

        farmerDBFields[0] = new EntityDBFields("frvfarmer","farmerId","Νο αγρότη",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[1] = new EntityDBFields("frvfarmer","surname","επίθετο",0,"java.lang.String",20, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,  FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[2] = new EntityDBFields("frvfarmer","name","όνομα",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[3] = new EntityDBFields("frvfarmer","fathername","πατρόνυμο",0,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[4] = new EntityDBFields("frvfarmer","farmerAfm","Α.Φ.Μ.",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_AFM,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[5] = new EntityDBFields("frvfarmer","idNo","αρ ταυτοτ",0,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[6] = new EntityDBFields("frvfarmer","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        farmerDBFields[7] = new EntityDBFields("frvfarmer","townId","πόλη/χωριό",1,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"frvtown",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[8] = new EntityDBFields("frvfarmer","address","διεύθυνση",1,"java.lang.String",38,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[9] = new EntityDBFields("frvfarmer","phone","τηλέφωνο(1) οικίας",1,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[10] = new EntityDBFields("frvfarmer","phone2","τηλέφωνο(2)",1,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[11] = new EntityDBFields("frvfarmer","phone3","τηλέφωνο(3)",1,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        farmerDBFields[12] = new EntityDBFields("frvfarmer","notes","σημειώσεις",2,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

        farmerDBFields[13] = new EntityDBFields("frvfarmer","bankId","τράπεζα",3,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        farmerDBFields[14] = new EntityDBFields("frvfarmer","bankAccount","λογαριασμός",3,"java.lang.String",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,""); 
        
        farmerDBFields[15] = new EntityDBFields("frvfarmer","frvfarmertelephone","τηλέφωνα του αγρότη",4,"table",FIELD_VISIBLE_AND_EDITABLE,"frvfarmertelephone",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,farmerTelephonesDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT farmerId, inc, farmerTelephoneId,department,telephone,person FROM frvfarmertelephone ORDER BY frvfarmertelephone.inc",null,null);    //String[] childTableFieldsForSumsIn   
        

        
        
        
        
        farmerEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET);
        farmerEntityGroupOfComps[1] = new EntityGroupOfComps("κατοικία - τηλέφωνα",4,0,FONT_SIZE_NOT_SET);
        farmerEntityGroupOfComps[2] = new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET);
        farmerEntityGroupOfComps[3] = new EntityGroupOfComps("τράπεζα - λογαριασμός",4,1,FONT_SIZE_NOT_SET);
        farmerEntityGroupOfComps[4] = new EntityGroupOfComps("τηλέφωνα",1,1,FONT_SIZE_NOT_SET); // if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form

        

        
        farmerEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        farmerEntityGroupOfPanels[1] = new EntityGroupOfPanels("τράπεζα/τηλέφωνα",1);
        //farmerEntityGroupOfPanels[2] = new EntityGroupOfPanels("σημειώσεις",1);

   //    
        applicationLineDBFields[0] = new EntityDBFields("frvapplicationline","applicationLineId","applicationLineId",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        applicationLineDBFields[1] = new EntityDBFields("frvapplicationline","inc","αα",0,"java.lang.Integer",2,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        applicationLineDBFields[2] = new EntityDBFields("frvapplicationline","applicationHeaderId","applicationHeaderId",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       // int[] inputBuyer ={1};
        //applicationLineDBFields[3] = new EntityDBFields("frvapplicationline","buyerId","Νο αγοραστή",0, "java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TWO_FIRSTFIELD/*LOOKUPTYPE_ONLYONE_THISFIELD*/,"frvbuyer",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,""); //false,6,inputBuyer,"SELECT frvproduct.productId FROM frvproduct, frvbuyer  WHERE frvproduct.productId=frvbuyer.productId AND frvbuyer.buyerId=");
        applicationLineDBFields[3] = new EntityDBFields("frvapplicationline","buyerId","ονομασία αγοραστή ή Α.Φ.Μ.",0,"java.lang.Integer",18,FIELD_NORMAL_NO_PRIMARY_KEY,/*LOOKUPTYPE_TWO_SECONDFIELD,*/LOOKUPTYPE_ONLYONE_THISFIELD,"frvbuyer", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");// in case it only should be one column look at lookup (a parameter should be 1 instead of 2)
        applicationLineDBFields[4] = new EntityDBFields("frvapplicationline","invoiceTypeId","είδος παραστατικού",0,"java.lang.Integer",14,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"frvinvoicetype", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        applicationLineDBFields[5] = new EntityDBFields("frvapplicationline","invoiceNo","αρ παρ/κού",0,"java.lang.String",9,FIELD_NORMAL_NO_PRIMARY_KEY, LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        applicationLineDBFields[6] = new EntityDBFields("frvapplicationline","dateinv","ημερομηνία",0, "java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       // int[] inputPer ={6};
        applicationLineDBFields[7] = new EntityDBFields("frvapplicationline","productId","ονομασία προϊόντος",0,"java.lang.Integer",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"frvproduct",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//false,8,inputPer ,"SELECT returnVat+returnFuel FROM frvproducttype, frvproduct WHERE frvproducttype.productTypeId = frvproduct.productTypeId AND frvproduct.productId=");
     //   int[] inputPerValue ={8,7};
        applicationLineDBFields[8] = new EntityDBFields("frvapplicationline","value","αξία",0,"java.lang.Double",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//false,9, inputPerValue, "/100*");
        applicationLineDBFields[9] = new EntityDBFields("frvapplicationline","productTypePercentage","ποσοστό",0,"java.lang.Double",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");//,false,9, inputPerValue, "/100*");
        applicationLineDBFields[10] = new EntityDBFields("frvapplicationline","valueReturn","επιστροφή",0,"java.lang.Double",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //applicationLineDBFields[11] = new EntityDBFields("frvapplicationline","farmerId","farmerId",0,"java.lang.String",6, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        applicationLineDBFields[11] = new EntityDBFields("frvapplicationline","dbcompanyid","dbcompanyid",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        //applicationLineDBFields[12] = new EntityDBFields("frvapplicationline","dbyear","dbyear",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalYear);  
        //applicationLineDBFields[12] = new EntityDBFields("frvapplicationline","deliveryId","deliveryId",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalDeliveryId);        
        
   //    FIELD_PRIMARY_KEY
        applicationHeaderDBFields[0] = new EntityDBFields("frvapplicationheader","applicationHeaderId","νο",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");// -1 not show
        applicationHeaderDBFields[1] = new EntityDBFields("frvapplicationheader","dbCompanyId","Νο εταιρείας",0,"java.lang.String",4,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO, FIELD_VISIBLE_AND_EDITABLE, VariablesGlobal.globalCompanyId,"");
        applicationHeaderDBFields[2] = new EntityDBFields("frvapplicationheader","dbYearDeliveryId","Νο αποστολής",0,"java.lang.String",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"frvyeardelivery", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"","");  // -1 not show
        applicationHeaderDBFields[3] = new EntityDBFields("frvapplicationheader","farmerId","Νο αγρότη",1,"java.lang.Integer",5, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"frvfarmer",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        applicationHeaderDBFields[4] = new EntityDBFields("frvapplicationheader","dbYearId","dbYearId",1,"java.lang.String",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalYearId,"");// -1 not show        
        applicationHeaderDBFields[5] = new EntityDBFields("frvapplicationheader","dateOfApplication","ημ/νία αίτησης",1,"java.sql.Date",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalDate,"");
        applicationHeaderDBFields[6] = new EntityDBFields("frvapplicationheader","permanent","υπολογισμένο",1,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        //applicationHeaderDBFields[4] = new EntityDBFields("frvapplicationheader","deliveryId","deliveryId",1,"java.lang.String",4,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalDeliveryId);
        
        applicationHeaderDBFields[7] = new EntityDBFields("frvapplicationheader","frvapplicationline","παραστατικά",2,"table",FIELD_VISIBLE_AND_EDITABLE,"frvapplicationline",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,applicationLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT * FROM frvapplicationline WHERE frvapplicationline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY frvapplicationline.inc",null,null);     //String[] childTableFieldsForSumsIn   

         applicationHeaderDBFields[8] = new EntityDBFields("frvapplicationheader","invcount","πλήθος",3,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,7,8,DBFIELD_TYPE_OF_SUM_COUNT);        
        applicationHeaderDBFields[9] = new EntityDBFields("frvapplicationheader","value","αξία",3,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,7,8,DBFIELD_TYPE_OF_SUM_SUM);        
        applicationHeaderDBFields[10] = new EntityDBFields("frvapplicationheader","valueReturn","επιστροφή",3,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,7,10,DBFIELD_TYPE_OF_SUM_SUM);        
        //applicationHeaderDBFields[11] = new EntityDBFields("frvapplicationheader","priceVat","ΦΠΑ",2,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,11,11,DBFIELD_TYPE_OF_SUM_SUM);        
              
        
        
        applicationHeaderGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET);
        applicationHeaderGroupOfComps[1] = new EntityGroupOfComps("αίτηση",4,0,FONT_SIZE_NOT_SET);
        applicationHeaderGroupOfComps[2] = new EntityGroupOfComps("παραστατικά",1,0,FONT_SIZE_NOT_SET);
        applicationHeaderGroupOfComps[3] = new EntityGroupOfComps("σύνολα",6,0,FONT_SIZE_NOT_SET);

        
        buyerDBFields[0] = new EntityDBFields("frvbuyer","buyerId","Νο αγοραστή",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        buyerDBFields[1] = new EntityDBFields("frvbuyer","buyerTitle","τίτλος αγοραστή",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        buyerDBFields[2] = new EntityDBFields("frvbuyer","buyerAfm","Α.Φ.Μ. αγοραστή",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_AFM,FIELD_VISIBLE_AND_EDITABLE,null,"");
        buyerDBFields[3] = new EntityDBFields("frvbuyer","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        buyerDBFields[4] = new EntityDBFields("frvbuyer","phone","τηλέφωνο(1)",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        buyerDBFields[5] = new EntityDBFields("frvbuyer","phone2","τηλέφωνο(2)",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        buyerDBFields[6] = new EntityDBFields("frvbuyer","productId","προϊόν",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"frvproduct", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        buyerDBFields[7] = new EntityDBFields("frvbuyer","notes","σημειώσεις",2,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        

        buyerEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET);
        buyerEntityGroupOfComps[1] = new EntityGroupOfComps("προτεινόμενα στοιχεία κατα την καταχώρηση παραστατικών",4,0,FONT_SIZE_NOT_SET);
        buyerEntityGroupOfComps[2] = new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET);

        buyerEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        //buyerEntityGroupOfPanels[1] = new EntityGroupOfPanels("σημειώσεις",1);        
        
        
        //--------

        
        productLineDBFields[0] = new EntityDBFields("frvproduct","productId","Νο προϊόντος",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        productLineDBFields[1] = new EntityDBFields("frvproduct","productName","ονομασία",0,"java.lang.String",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        productLineDBFields[2] = new EntityDBFields("frvproduct","productTypeId","τύπος προϊόντος",0,"java.lang.Integer",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"frvproducttype", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        // 'line' should be upper// , frvproducttype.producttypeName AS \"τύπος προϊόντος\", frvproducttype.returnVat AS \"επιστρ φόρου\", frvproducttype.returnFuel AS \"επιστρ πετρελαίου\" 
        productDBFields[0] = new EntityDBFields("productheader","frvproduct","προϊόντα",0,"table",FIELD_VISIBLE_AND_EDITABLE,"frvproduct",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,productLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT frvproduct.productId AS \"Νο προϊόντος\", frvproduct.productName AS \"ονομασία\", frvproduct.productTypeId AS \"Νο τύπου\" FROM frvproduct LEFT JOIN frvproducttype ON frvproduct.productTypeId=frvproducttype.productTypeId ORDER BY frvproduct.productName",null,null);     //String[] childTableFieldsForSumsIn   
        
        productEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);

        //--------------
        
       	invoicetypeLineDBFields[0] = new EntityDBFields("frvinvoicetype","invoiceTypeId","Νο τύπου παρ/κού",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	invoicetypeLineDBFields[1] = new EntityDBFields("frvinvoicetype","invoiceTypeName","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	invoicetypeLineDBFields[2] = new EntityDBFields("frvinvoicetype","abbreviation","συντομογραφία",0,"java.lang.String",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        invoicetypeDBFields[0] = new EntityDBFields("invoiceTypeheader","frvinvoicetype","τύποι παραστατικών",0,"table",FIELD_VISIBLE_AND_EDITABLE,"frvinvoicetype",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,invoicetypeLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT invoiceTypeId AS \"Νο τύπου παραστατικού\", invoicetypeName AS \"ονομασία\",abbreviation AS \"συντομογραφία\" FROM frvinvoicetype ORDER BY invoiceTypeId",null,null);     //String[] childTableFieldsForSumsIn   
        
        invoicetypeEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);

        //--------------   
        producttypeLineDBFields[0] = new EntityDBFields("frvproducttype","productTypeId","Νο τύπου προϊόντος",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        producttypeLineDBFields[1] = new EntityDBFields("frvproducttype","productTypeName","ονομασία",0,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        producttypeLineDBFields[2] = new EntityDBFields("frvproducttype","returnVat","ποσοστό επιστροφής φόρου",0,"java.lang.Double",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        producttypeLineDBFields[3] = new EntityDBFields("frvproducttype","returnFuel","ποσοστό επιστροφής πετρελαίου",0,"java.lang.Double",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
   	
        producttypeDBFields[0] = new EntityDBFields("producttypeheader","frvproducttype","τύποι προϊόντων",0,"table",FIELD_VISIBLE_AND_EDITABLE,"frvproducttype",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,producttypeLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT productTypeId AS \"Νο τύπου προϊόντος\",producttypeName AS \"ονομασία\",returnVat AS \"ποσοστό επιστροφής φόρου\",returnFuel AS \"ποσοστό επιστροφής πετρελαίου\" FROM frvproducttype ORDER BY productTypeId",null,null);     //String[] childTableFieldsForSumsIn   
        
        producttypeEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);
        //-----------------
        townLineDBFields[0] = new EntityDBFields("frvtown","townId","Νο πόλης",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        townLineDBFields[1] = new EntityDBFields("frvtown","townName","πόλη/χωριό",0,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        townLineDBFields[2] = new EntityDBFields("frvtown","state","νομός",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        townLineDBFields[3] = new EntityDBFields("frvtown","postCode","T.K.",0,"java.lang.String",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        townLineDBFields[4] = new EntityDBFields("frvtown","phoneCode","κωδ τηλ",0,"java.lang.String",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
   	
        townDBFields[0] = new EntityDBFields("townheader","frvtown","πόλεις/χωριά",0,"table",FIELD_VISIBLE_AND_EDITABLE,"frvtown",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,townLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT townId AS\"Νο πόλης\", townName AS\"πόλη/χωριό\", state AS\"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM frvtown ORDER BY townName",null,null);     //String[] childTableFieldsForSumsIn   
        
        townEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);

        //---------------------
        /*doyLineDBFields[0] = new EntityDBFields("doy","doyId","Νο Δ.Ο.Υ.",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        doyLineDBFields[1] = new EntityDBFields("doy","doyName","ονομασία",0,"java.lang.String",32,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[2] = new EntityDBFields("doy","address","διεύθυνση",0,"java.lang.String",33,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[3] = new EntityDBFields("doy","frvtown","πόλη/χωριό",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[4] = new EntityDBFields("doy","pc","T.K.",0,"java.lang.String",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[5] = new EntityDBFields("doy","tel1","τηλέφωνο(1)",0,"java.lang.String",16,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[6] = new EntityDBFields("doy","tel2","τηλέφωνο(2)",0,"java.lang.String",16,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[7] = new EntityDBFields("doy","fax","φαξ",0,"java.lang.String",16,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        doyDBFields[0] = new EntityDBFields("doyheader","doy","ΔΟΥ",0,"table",FIELD_VISIBLE_AND_EDITABLE,"doy",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,doyLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT doyId AS \"Νο Δ.Ο.Υ.\", doyName AS \"ονομασία\", address AS \"διεύθυνση\", town AS \"πόλη/χωριό\", pc AS \"ΤΚ\", tel1 AS \"τηλ 1\", tel2 AS \"τηλ 2\", fax AS \"φαξ\" FROM doy ORDER BY doyId",null);     //String[] childTableFieldsForSumsIn   
        
        doyEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0);  */      
        
        //-------
        
       /*	bankLineDBFields[0] = new EntityDBFields("bank","bankId","Νο τράπεζας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	bankLineDBFields[1] = new EntityDBFields("bank","bankBranch","τίτλος τράπεζας",0,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        bankDBFields[0] = new EntityDBFields("bankheader","bank","τράπεζες",0,"table",FIELD_VISIBLE_AND_EDITABLE,"bank",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,bankLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT bankId AS \"Νο τράπεζας\", bankBranch AS \"ονομασία τράπεζας\" FROM bank ORDER BY bankId",null);     //String[] childTableFieldsForSumsIn   
        
        bankEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0);        
        */
        
        
       //---------------------
       /*	dbCompanyDBFields[0] = new EntityDBFields("dbcompany","dbCompanyId","Νο εταιρίας",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[1] = new EntityDBFields("dbcompany","companyName","τίτλος",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[2] = new EntityDBFields("dbcompany","companyVatNo","Α.Φ.Μ εταιρίας.",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_AFM,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[3] = new EntityDBFields("dbcompany","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[4] = new EntityDBFields("dbcompany","active","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");
        //dbCompanyDBFields[5] = new EntityDBFields("dbcompany","president","επών-όνομα προέδρου",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	//dbCompanyDBFields[5] = new EntityDBFields("dbcompany","townId","πόλη/χωριό",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"frvtown",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[5] = new EntityDBFields("dbcompany","bankId","υποκατάστημα",1,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[6] = new EntityDBFields("dbcompany","bankAccount","λογαριασμός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[7] = new EntityDBFields("dbcompany","bankAccountIBAN","ΙΒΑΝ",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[8] = new EntityDBFields("dbcompany","notes","σημειώσεις",2,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbCompanyDBFields[9] = new EntityDBFields("dbcompany","message","μήνυμα",3,"java.lang.String",100,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbCompanyDBFields[10] = new EntityDBFields("dbcompany","lengthOfCodeOfDocuments","μήκος κωδικού παραστατικών",4,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"7","");
        dbCompanyDBFields[11] = new EntityDBFields("dbcompany","charOfDecimal","χαρακτήρας δεκαδικών",4,"java.lang.String",1,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCdecimalchar",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,",","");
        //dbCompanyDBFields[4] = new EntityDBFields("dbcompany","charOfThousands","χαρακτήρας χιλιάδων",0,"java.lang.String",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        dbCompanyDBFields[12] = new EntityDBFields("dbcompany","lengthOfDecimalPrice","μήκος δεκαδικών αξίας",4,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"2","");   
         */       
                
        
        
        
       /* dbCompanyEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",4,0);
        dbCompanyEntityGroupOfComps[1]= new EntityGroupOfComps("λογαριασμός τράπεζας",6,0);
        dbCompanyEntityGroupOfComps[2]= new EntityGroupOfComps("σημειώσεις",1,0);
        dbCompanyEntityGroupOfComps[3]= new EntityGroupOfComps("μήνυμα",2,0);
        dbCompanyEntityGroupOfComps[4]= new EntityGroupOfComps("ρυθμίσεις (Πρέπει να εισέλθετε ξανα στην εφαρμογή για να εφαρμοσθούν οι αλλαγές.)",4,0);
       */
       /* dbuserDBFields[0] = new EntityDBFields("dbUser","userId","Νο χρήστη",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbuserDBFields[1] = new EntityDBFields("dbUser","username","όνομα χρήστη",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbuserDBFields[2] = new EntityDBFields("dbUser","password","password",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbuserDBFields[3] = new EntityDBFields("dbUser","nameOfUser","πλήρες όνομα χρήστη",0,"java.lang.String",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //dbuserDBFields[4] = new EntityDBFields("dbCompanyId","Νο εταιρίας",0, FIELD_SUGGEST,FIELD_VALIDATION_NO);
        */
        
        
        
        
        dbYearDeliveryDBFields[0] = new EntityDBFields("frvyeardelivery","dbYearDeliveryId","dbYearDeliveryId",0,"java.lang.Integer",8,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbYearDeliveryDBFields[1] = new EntityDBFields("frvyeardelivery","inc","αα",0,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbYearDeliveryDBFields[2] = new EntityDBFields("frvyeardelivery","description","περιγραφή",0,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbYearDeliveryDBFields[3] = new EntityDBFields("frvyeardelivery","printFormId","φόρμα",0,"java.lang.Integer",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"printform",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbYearDeliveryDBFields[4] = new EntityDBFields("frvyeardelivery","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalCompanyId,"");
        dbYearDeliveryDBFields[5] = new EntityDBFields("frvyeardelivery","dbYearId","Νο χρήσης",0,"java.lang.Integer",10, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        dbYearDeliveryEntityGroupOfComps[0]= new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);
        
        dbYearDBFields[0] = new EntityDBFields("dbyear","dbYearId","Νο χρήσης",0,"java.lang.Integer",7, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbYearDBFields[1] = new EntityDBFields("dbyear","dbyear","χρήση",0,"java.lang.String",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbYearDBFields[2] = new EntityDBFields("dbyear","dbCompanyId","Νο εταιρίας",0,"java.lang.Integer",4,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalCompanyId,"");
        dbYearDBFields[3] = new EntityDBFields("dbyear","frvyeardelivery","αποστολές του έτους",1,"table",FIELD_VISIBLE_AND_EDITABLE,"frvyeardelivery",350,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,dbYearDeliveryDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT dbYearDeliveryId, dbYearId,inc,description,printFormId,dbCompanyId FROM frvyeardelivery WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  ORDER BY inc",null,null);// this last null is childTableFieldsForSumsIn
        
        
        dbyearEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET);
        dbyearEntityGroupOfComps[1] = new EntityGroupOfComps("αποστολές",1,0,FONT_SIZE_NOT_SET);
       

        //dbDeliveryDBFields[0] = new EntityDBFields("dbDelivery","deliveryId","Νο αποστολής",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //dbDeliveryDBFields[1] = new EntityDBFields("dbDelivery","description","περιγραφή",0,"java.lang.String",28,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        
        

   }
   
   // called in PanelManagement addNodes()
   public void loadGenericData()
   {
   	
      
      //System.out.println("EntityData "+applicationHeaderGroupOfComps);  
   	
   	   
 /*  	  intYearPlusOne = Integer.valueOf(VariablesGlobal.globalYear)+1;
   	   globalYearPlusOne = intYearPlusOne+"";
   	 // vars global are not loaded before so we load it here
   	deliveryQueryEditable = "SELECT a.farmerId, a.dateOfApplication, a.permanent, a.dbyear, a.deliveryId, a.dbCompanyId "
                + "FROM frvapplicationheader a "
                + "WHERE dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+" AND dbCompanyId="+VariablesGlobal.globalCompanyId;
        
        deliveryQueryManyReadOnly = "SELECT i.aa AS \"αα\", i.farmerId, b.buyerTitle AS\"αγοραστής\" ,it.abbreviation AS\"παρ\" ,applicationlineNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\", p.productName AS\"προϊόν\" ,i.value AS\"αξία\", i.productTypePercentage AS\"ποσοστό\" , i.valueReturn AS\"επιστροφή\" "
                + "FROM frvapplicationline i, frvproduct p, frvfarmer f, frvapplicationheader a, frvbuyer b,applicationlineType it "
                + "WHERE b.buyerId=i.buyerId AND f.farmerId=i.farmerId AND f.farmerId=a.farmerId AND i.deliveryId=a.deliveryId AND i.productId = p.productId AND a.dbyear=i.dbyear AND a.dbCompanyId=i.dbCompanyId AND i.applicationlineTypeId=it.applicationlineTypeId AND i.dbyear="+VariablesGlobal.globalYear+" AND i.deliveryId = "+VariablesGlobal.globalDeliveryId+" AND i.dbCompanyId="+VariablesGlobal.globalCompanyId+" ORDER BY surname, name, fathername, f.farmerAFM, f.farmerId, i.aa";// order by not taken into account
        
        deliveryQueryManyEditable= "SELECT aa AS \"αα\", buyerId,applicationlineTypeId,applicationlineNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\", productId, value AS\"αξία\" , productTypePercentage AS\"ποσοστό\" , valueReturn AS\"επιστροφή\", DBCOMPANYID, DBYEAR, deliveryId, FARMERID "
                + "FROM frvapplicationline i WHERE i.dbyear="+VariablesGlobal.globalYear+" AND i.deliveryId = "+VariablesGlobal.globalDeliveryId+" AND i.dbCompanyId="+VariablesGlobal.globalCompanyId;

  */      
       
  //      entityPanelDeliveryDataentry = null;// new EntityPanel("TDOR","frvapplicationheader",deliveryQueryEditable,applicationHeaderDBFields,applicationLineDBFields,applicationHeaderGroupOfComps,applicationHeaderGroupOfPanels,fieldsForSumsInvoice,
           ///*deliveryFieldsManyOnInsert, deliveryFieldsManyTranslationOnInsert,*/"frvapplicationline", deliveryQueryManyEditable, deliveryQueryManyReadOnly,true/*isMasterUnique*/,deliveryWhereField,
           //deliveryWhereValue, "Νο αγρότη","primkeyvalue","farmerId",/*deliveryPrimKeyMany,deliveryPrimKeyManyTran,*/false,"βασικά στοιχεία", ICO_TABLE16,"παραστατικών αγρότη",
           //true,7,"frvproducttype","productTypeId","frvproduct","productId",3,globalYearPlusOne,VariablesGlobal.globalYear);// String yearEnforceInActionIn, String yearEnforceInLinesIn)
        
        //EntityPanel entityPanelDeliveryStatistics = new EntityPanel("statDeliveyHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.companyName AS \"τίτλος συν/σμού\", frvapplicationline.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(frvapplicationline.value) AS sum, AVG(frvapplicationline.value) AS average, MIN(frvapplicationline.value) AS min, MAX(frvapplicationline.value) AS max","FROM frvapplicationline, dbcompany","WHERE frvapplicationline.dbCompanyId = dbcompany.dbCompanyId AND frvapplicationline.farmerId=","GROUP BY dbyear, frvapplicationline.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.companyName, frvapplicationline.deliveryId",false,"",false,"");     
                
        //EntityPanel entityPanelFarmerStatistics = new EntityPanel("STATS",null,"ιστορικό",null,"SELECT dbyear AS \"χρήση\", dbcompany.companyName AS \"τίτλος συν/σμού\", frvapplicationline.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(frvapplicationline.value) AS sum, AVG(frvapplicationline.value) AS average, MIN(frvapplicationline.value) AS min, MAX(frvapplicationline.value) AS max","FROM frvapplicationline, dbcompany","WHERE frvapplicationline.dbCompanyId = dbcompany.dbCompanyId AND frvapplicationline.farmerId=","GROUP BY dbyear, frvapplicationline.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.companyName, frvapplicationline.deliveryId",false,"",false,"");     
 //        entityPanelDelivery = new EntityPanel[] {entityPanelDeliveryDataentry};//,entityPanelDeliveryStatistics};
       
       
   }

   
   public DataTree getDataTree()
   {
   	return dTree;
   }
     
 /* public DefaultMutableTreeNode addDialogEditRecNavigationNodes(boolean[] visibleCats) 
  {      
         DefaultMutableTreeNode entityNode = new DefaultMutableTreeNode();
  	     DefaultMutableTreeNode base;
         
         if(visibleCats[0]==true)
         {
         
            //EntityTreeNode etnDataentry = new EntityTreeNode(DATAENTRY);
            base = new DefaultMutableTreeNode(DATAENTRY);
            entityNode.add(base);
         }
         
         if(visibleCats[1]==true)
         {
            //EntityTreeNode etnReports = new EntityTreeNode(REPORTS);
            base = new DefaultMutableTreeNode(REPORTS);
            entityNode.add(base); 
         }
         
         if(visibleCats[2]==true)
         {
           //EntityTreeNode etnStatistics = new EntityTreeNode(METRICS);
            base = new DefaultMutableTreeNode(METRICS);       
            entityNode.add(base); 
         }
         
         return entityNode;
  } */
   
  /*public  DefaultMutableTreeNode addMainNavigationNodes(DefaultMutableTreeNode entityNode) 
  {
  	
  	
  	   //DataTreeNode dNodeDataEntry = new DataTreeNode(DATAENTRY);

  	   
  	
  	     DefaultMutableTreeNode cat;
  	     DefaultMutableTreeNode base;
         
         
         for (int n =0;n<ENTITIES_CAT_ARRAY_FOR_TREE.length;n++)
         {
         	
         	String category =ENTITIES_CAT_ARRAY_FOR_TREE[n];
         	
         	
         	
           
//           cat = new DefaultMutableTreeNode(category);
//           entityNode.add(cat); 
           EntityMenu emCat = new EntityMenu();
           emCat.setEntityType(ENTITY_TYPE_CATEGORY1);
           emCat.setEntityCategory(category,1);
           //listEntities.add(emCat);       

         	nodeRoot.addChild(new DataTreeNode(emCat));
         }
        
        //System.out.println("EntityData.addMainNavigationNodes add nodes");
        
         return entityNode;
  	
  }*/



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
        
        EntityMenu emCat1 = new EntityMenu();
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
        
  /*      EntityMenu emCat3 = new EntityMenu();
        emCat3.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat3.setEntityCategory(METRICS,1,ICO_MENUCAT_EXPLORE);//ICO_STATISTICS16);
        if(isVisible[2])
        {        
           nodeRoot.addChild(new DataTreeNode(emCat3));
        }
  */      
        EntityMenu emCat4 = new EntityMenu();
        emCat4.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat4.setEntityCategory(PARAMETERS,1,ICO_MENUCAT_EDIT);//ICO_TABLE16);
        if(isVisible[3])
        {
           nodeRoot.addChild(new DataTreeNode(emCat4));
        }
        
 /*       EntityMenu emCat5 = new EntityMenu();
        emCat5.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat5.setEntityCategory(TOOLS,1,ICO_MENUCAT_TOOLS);//ICO_TABLE16);
        if(isVisible[4])
        {
           nodeRoot.addChild(new DataTreeNode(emCat5));
        }
*/        
        //System.out.println("EntityData.addMainNavigationNodes add nodes");
        
  	
  }


  public void addEntityInfoNodes()
  {

       EntityFilterSettings[] farmerErs = new EntityFilterSettings[6];       
       farmerErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","frvfarmer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       farmerErs[1]=new EntityFilterSettings("όνομα","","string","equals","name","frvfarmer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       farmerErs[2]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","frvfarmer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       farmerErs[3]=new EntityFilterSettings("ταυτότητα","","string","equals","idNo","frvfarmer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       farmerErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","frvtown","frvfarmer","",-1,-1,-1,FIELD_NOCOMPLETION);
       farmerErs[5]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","frvfarmer","",-1,-1,-1,FIELD_NOCOMPLETION);
       
       EntityGroupOfComps[] farmerEntityGroupOfComps = null;
       
       int[] farmerFieldsOrderBy ={2,3,4,5};
        // if fields change, change them and at lookup entities
       String[] fieldsForSumsFarmer = null;
       //EntityInfo ia = new EntityInfo("frvfarmer", "SELECT frvfarmer.farmerId AS \"Νο αγρότη\", frvfarmer.surname AS \"επίθετο\", frvfarmer.name AS \"όνομα\", frvfarmer.fathername AS \"πατρόνυμο\", frvfarmer.farmerAfm AS \"Α.Φ.Μ.\", frvfarmer.doyId AS \"Νο Δ.Ο.Υ.\", doy.doyName AS \"ονομασία Δ.Ο.Υ.\", frvfarmer.idNo AS \"αρ ταυτοτ\", frvfarmer.phone AS \"τηλέφωνο(1)\", frvfarmer.phone2 AS \"τηλέφωνο(2)\", frvfarmer.phone3 AS \"τηλέφωνο(3)\", frvtown.townName AS \"πόλη/χωριό\",frvtown.postCode AS \"TK\", frvfarmer.address AS \"διεύθυνση\", bank.bankBranch AS \"τράπεζα\", frvfarmer.bankAccount AS \"λογαριασμός\" FROM frvfarmer LEFT JOIN doy ON frvfarmer.doyId=doy.doyId LEFT JOIN frvtown ON frvfarmer.townId=frvtown.townId LEFT JOIN bank ON frvfarmer.bankId=bank.bankId ORDER BY frvfarmer.surname, frvfarmer.name, frvfarmer.fathername, frvfarmer.farmerAfm" ,"SELECT frvfarmer.farmerId AS \"Νο αγρότη\", frvfarmer.surname AS \"επίθετο\", frvfarmer.name AS\"όνομα\", frvfarmer.fathername AS \"πατρόνυμο\", frvfarmer.farmerAfm AS \"Α.Φ.Μ.\", frvfarmer.doyId, frvfarmer.idNo AS \"αρ ταυτοτ\", frvfarmer.townId, frvfarmer.address AS \"διέυθυνση\", frvfarmer.phone AS\"τηλέφωνο\"","FROM frvfarmer LEFT JOIN doy ON frvfarmer.doyId=doy.doyId LEFT JOIN frvtown ON frvfarmer.townId=frvtown.townId","",null, fieldsForSumsFarmer,null,null,farmerDBFields,null,null,null,null,null,false,null,null ,"αγρότες","DORM","","Νο αγρότη","farmerId",null,null,farmerErs,farmerEntityGroupOfComps ,"αγρότη","αγροτών",null,null,strFarmerCategories,entityPanelFarmer,fieldsOnTitleFarmer,fieldsOnTitleCaptionFarmer,farmerFieldsOrderBy,4,FIELD_VALIDATION_AFM,globalYearPlusOne); 
       EntityInfo ia = new EntityInfo("frvfarmer", "SELECT frvfarmer.farmerId AS \"Νο αγρότη\", frvfarmer.surname AS \"επίθετο\", frvfarmer.name AS \"όνομα\", frvfarmer.fathername AS \"πατρόνυμο\", frvfarmer.farmerAfm AS \"Α.Φ.Μ.\", frvfarmer.doyId AS \"Νο Δ.Ο.Υ.\", doy.doyName AS \"ονομασία Δ.Ο.Υ.\", frvfarmer.idNo AS \"αρ ταυτοτ\", frvfarmer.phone AS \"τηλέφωνο(1)\", frvfarmer.phone2 AS \"τηλέφωνο(2)\", frvfarmer.phone3 AS \"τηλέφωνο(3)\", frvtown.townName AS \"πόλη/χωριό\",frvtown.postCode AS \"TK\", frvfarmer.address AS \"διεύθυνση\", bank.bankBranch AS \"τράπεζα\", frvfarmer.bankAccount AS \"λογαριασμός\" FROM frvfarmer LEFT JOIN doy ON frvfarmer.doyId=doy.doyId LEFT JOIN frvtown ON frvfarmer.townId=frvtown.townId LEFT JOIN bank ON frvfarmer.bankId=bank.bankId ORDER BY frvfarmer.surname, frvfarmer.name, frvfarmer.fathername, frvfarmer.farmerAfm" ,"SELECT frvfarmer.farmerId AS \"Νο αγρότη\", frvfarmer.surname AS \"επίθετο\", frvfarmer.name AS\"όνομα\", frvfarmer.fathername AS \"πατρόνυμο\", frvfarmer.farmerAfm AS \"Α.Φ.Μ.\", frvfarmer.doyId, frvfarmer.idNo AS \"αρ ταυτοτ\", frvfarmer.townId, frvfarmer.address AS \"διέυθυνση\", frvfarmer.phone AS\"τηλέφωνο\"","FROM frvfarmer LEFT JOIN doy ON frvfarmer.doyId=doy.doyId LEFT JOIN frvtown ON frvfarmer.townId=frvtown.townId","",null, fieldsForSumsFarmer/*,farmerDBFields*/ ,"αγρότες","DORM","","Νο αγρότη","farmerId",farmerErs,farmerEntityGroupOfComps ,"αγρότη","αγροτών",strFarmerCategories,entityPanelFarmer,fieldsOnTitleFarmer,fieldsOnTitleCaptionFarmer,farmerFieldsOrderBy,4,FIELD_VALIDATION_AFM,null,globalYearPlusOne);
        EntityMenu emia = new EntityMenu();
        emia.setEntityInfo(ia,ICO_FARMER16);
        emia.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeemia = new DataTreeNode(emia);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemia);
        
       //-----------------------------------------------------------------------
     
                 
       
    /*EntityFilterSettings[] deliveryErs = new EntityFilterSettings[8];       
       deliveryErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","f",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       deliveryErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","f",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       deliveryErs[2]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","frvtown","f","",-1,-1,-1,FIELD_NOCOMPLETION);
       deliveryErs[3]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","f","",-1,-1,-1,FIELD_NOCOMPLETION);
       deliveryErs[4]=new EntityFilterSettings("ημ/νία αιτ.","","date","fromto","dateOfApplication","d",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       deliveryErs[5]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","frvbuyer","i","",-1,-1,-1,FIELD_NOCOMPLETION);
       deliveryErs[6]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","frvproduct","i","",-1,-1,-1,FIELD_NOCOMPLETION);
       deliveryErs[7]=new EntityFilterSettings("αξία","","double","fromto","sum(i.value)","",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
      */ 
       //frvapplicationheader
       
       EntityGroupOfComps[] appicationEntityFilterGroupOfComps = null;

       
      // frvapplicationheader more for mysql
      //EntityInfoMany ic = new EntityInfoMany("frvapplicationheader", "SELECT f.farmerId AS\"Νο αγρότη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.farmerAfm AS\"Α.Φ.Μ.\", d.dateOfApplication AS \"ημ/νια αίτησης\", count(ri.value) AS\"πλήθος\",sum(ri.value) AS\"αξία\",sum(ri.total1) AS \"επισ 1\",sum(ri.total2) AS \"επισ 2\",sum(ri.total3) AS \"επισ 3\", sum(ri.total1)+sum(ri.total2)+sum(ri.total3) AS \"συν επιστρ\" FROM return_from_applicationlines ri, frvfarmer f, frvapplicationheader d WHERE ri.farmerId = f.farmerId AND d.farmerId = f.farmerId AND d.deliveryId = "+VariablesGlobal.globalDeliveryId+" AND d.dbyear="+VariablesGlobal.globalYear+" AND d.dbCompanyId="+VariablesGlobal.globalCompanyId+" AND ri.deliveryId = d.deliveryId AND ri.dbyear=d.dbyear AND ri.dbCompanyId=d.dbCompanyId GROUP BY f.farmerId ORDER BY f.surname, f.name",null,null,null,deliveryQueryEditable, "frvapplicationline","SELECT buyerId,applicationlineTypeId,applicationlineNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\",productId,value AS\"αξία\" FROM frvapplicationline",applicationHeaderDBFields,applicationLineDBFields,deliveryFieldsManyOnInsert,deliveryFieldsManyTranslationOnInsert,"frvapplicationheader",deliveryQueryManyReadOnly,true, deliveryWhereField, deliveryWhereValue ,"παραστατικά αγροτών","DTRM","f"/*because in sql f is frvfarmer*/,"Νο αγρότη","farmerId",deliveryPrimKeyMany,deliveryPrimKeyManyTran ,deliveryErs,deliveryEntityFilterGroupOfComps, "αγρότη με παραστατικά", "αγροτών με παραστατικά","το παραστατικό","παραστατικών", null,entityPanelDelivery,null,null,deliveryFieldsOrderBy,-1,null,entityTaskDelivery,4,FIELD_VALIDATION_AFM);
      /*  EntityMenu emic = new EntityMenu();
        emic.setEntityInfoMany(ic,ICO_PAPER);
        emic.setEntityType(ENTITY_TYPE_DATAENTRYMANY);
        //listEntities.add(emic);
        DataTreeNode nodeemic = new DataTreeNode(emic);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemic);   */    

       EntityFilterSettings[] deliveryBErs = new EntityFilterSettings[12];  
       // not added because when try to edit shows for VariablesGlobal settings
       /*EntityFilterSettings(String captionIn,String typeIn, String variableTypeIn, String equivalenceIn, 
      	String dbFieldIn, String dbTableIn,String dbForeignTableIn, String valueIn,
        int groupOfCompsIn, int filterFromSelectedFieldIn,int forEntityReportGroupIn)*/
       
       //deliveryBErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","i",VariablesGlobal.globalCompanyId,0,-1,-1);
       //deliveryBErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbyear","dbyear","i", VariablesGlobal.globalYear,0,0,-1);
       //deliveryBErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","","deliveryId","dbdelivery","i",VariablesGlobal.globalDeliveryId,0,-1,-1);               	     
       // if change this, also change EntityInfoMany query
       deliveryBErs[0]=new EntityFilterSettings("αποστολή","checkboxTable","string","equals","dbYearDeliveryId","frvyeardelivery","frvapplicationheader",""/*VariablesGlobal.globalDeliveryId*/,-1,-1,-1,FIELD_NOCOMPLETION);   
       deliveryBErs[1]=new EntityFilterSettings("επίθετο","","string","equals","surname","f",null,"",0,-1,-1,FIELD_NOCOMPLETION);
       deliveryBErs[2]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","f",null,"",0,-1,-1,FIELD_NOCOMPLETION);
       deliveryBErs[3]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","frvtown","f","",0,-1,-1,FIELD_NOCOMPLETION);
       deliveryBErs[4]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","f","",0,-1,-1,FIELD_NOCOMPLETION);
       deliveryBErs[5]=new EntityFilterSettings("ημ/νία αιτ.","","date","fromto","dateOfApplication","frvapplicationheader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       deliveryBErs[6]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","frvbuyer","i","",1,-1,-1,FIELD_NOCOMPLETION);
       deliveryBErs[7]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","frvproduct","i","",1,-1,-1,FIELD_NOCOMPLETION);
       deliveryBErs[8]=new EntityFilterSettings("πλήθος","","double","fromto","invcount","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       deliveryBErs[9]=new EntityFilterSettings("αξία","","double","fromto","value","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       deliveryBErs[10]=new EntityFilterSettings("επιστροφή","","double","fromto","valueReturn","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       deliveryBErs[11]=new EntityFilterSettings("κράτηση","","double","fromto","payment","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       
       
       EntityGroupOfComps[] dbdeliveryEntityGroupOfComps  = null; // = new EntityGroupOfComps(deliveryBCaptionOfGroupOfComps,deliveryBColumnsOfObjects,deliveryBIncludedInGroupsOfPanels);       
       String[] fieldsForSumsApplication = {"πλήθος","αξία","επιστροφή","κράτηση","σύνολο"};
       //EntityGroupOfComps[] deliveryEntityFilterGroupOfComps = null;
      
      String[] checkBoxInfo ={"Δεν έχουν υπολογιστεί οι αιτήσεις.","<html><b>Προσοχή!!!</b> Υπάρχουν και αιτήσεις που <b>δεν</b> είναι υπολογίσμένες.</html>","Έχουν υπολογιστεί όλες οι αιτήσεις για τα κριτήρια που θέσατε."};
      
//      EntityInfoMany id =  new EntityInfoMany("frvapplicationheader", "SELECT f.farmerId AS\"Νο αγρότη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.farmerAfm AS\"Α.Φ.Μ.\", permanent AS \"υπολ\" , a.dateOfApplication AS \"ημ/νια αίτησης\",a.deliveryId AS \"αποστολή\", COUNT(i.value) AS \"πλήθος\", SUM(i.value) AS \"αξία\", SUM(retValueAccordingToType(1, i.productTypeId, i.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, i.productTypeId, i.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, i.productTypeId, i.valueReturn)) AS \"κατ 3\", a.valueReturn AS \"επιστροφή\", a.payment AS \"κράτηση\", (a.valueReturn - a.payment) AS \"σύνολο\""+
//              " FROM frvapplicationheader a, frvfarmer f, frvapplicationline i, dbDelivery d "+
              //" WHERE i.farmerId = f.farmerId AND a.farmerId = f.farmerId AND i.deliveryId = a.deliveryId AND a.deliveryId = d.deliveryId AND i.dbyear=a.dbyear AND i.dbCompanyId=a.dbCompanyId "/*AND i.dbCompanyId="+VariablesGlobal.globalCompanyId+"*/ +/*"AND i.dbyear="+VariablesGlobal.globalYear+-//*" AND i.deliveryId="+VariablesGlobal.globalDeliveryId+*/" GROUP BY i.farmerId ORDER BY f.surname, f.name, f.fathername",null,null,null,deliveryQueryEditable, "frvapplicationline","SELECT buyerId,applicationlineTypeId,applicationlineNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\",productId,value AS\"αξία\" FROM frvapplicationline",applicationHeaderDBFields,applicationLineDBFields,deliveryFieldsManyOnInsert,deliveryFieldsManyTranslationOnInsert,"frvapplicationheader",deliveryQueryManyReadOnly,true, deliveryWhereField, deliveryWhereValue ,"παραστατικά αγροτών","DTRM","f"/*because in sql f is frvfarmer*/,"Νο αγρότη","farmerId",deliveryPrimKeyMany,deliveryPrimKeyManyTran ,deliveryBErs, deliveryEntityFilterGroupOfComps,"αγρότη με παραστατικά", "αγροτών με παραστατικά","το παραστατικό","παραστατικών", null,entityPanelDelivery,null,null,deliveryFieldsOrderBy,5,checkBoxInfo,entityTaskDelivery,4,FIELD_VALIDATION_AFM);
//              " WHERE i.farmerId = f.farmerId AND a.farmerId = f.farmerId AND i.deliveryId = a.deliveryId AND a.deliveryId = d.deliveryId AND i.dbyear=a.dbyear AND i.dbCompanyId=a.dbCompanyId AND i.dbCompanyId="+VariablesGlobal.globalCompanyId+" AND i.dbyear="+VariablesGlobal.globalYear+/*" AND i.deliveryId="+VariablesGlobal.globalDeliveryId+*/
//              " GROUP BY i.farmerId, i.deliveryId ORDER BY f.surname, f.name, f.fathername, f.farmerAfm,  i.deliveryId",null,null,null,deliveryQueryEditable,fieldsForSumsApplication,fieldsForSumsInvoice, "frvapplicationline","SELECT buyerId,applicationlineTypeId,applicationlineNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\",productId,value AS\"αξία\" FROM frvapplicationline",applicationHeaderDBFields,applicationLineDBFields,/*deliveryFieldsManyOnInsert,deliveryFieldsManyTranslationOnInsert,*/"frvapplicationheader",deliveryQueryManyReadOnly,true, deliveryWhereField, deliveryWhereValue ,"παραστατικά αγροτών","DTRM","f"/*because in sql f is frvfarmer*/,"Νο αγρότη","farmerId",/*deliveryPrimKeyMany,deliveryPrimKeyManyTran ,*/deliveryBErs, deliveryEntityFilterGroupOfComps,"αγρότη με παραστατικά", "αγροτών με παραστατικά","το παραστατικό","παραστατικών", null,entityPanelDelivery,null,null,deliveryFieldsOrderBy,5,checkBoxInfo,entityTaskDelivery,4,FIELD_VALIDATION_AFM,globalYearPlusOne);
            int[] applicationFieldsOrderBy ={2,3,4,5};
      
      /*EntityInfo(String nameIn, String sqlReadOnlyIn, String sqlSelectIn,String sqlFromIn,String sqlWhereIn ,String sqlOneIn,String[] fieldsForSumsIn, 
                /*EntityDBFields[] fieldsIn,*/ // String captionIn, String viewIn,String entityHeaderIn,String primKeyIn,String primKeyDbIn, //String[] primKeyManyIn,String[]primKeyManyTranIn, 
     /*          EntityFilterSettings[] entityFilterSettingsIn, EntityGroupOfComps[] entityGroupOfCompsIn,String strOfOneIn, String strOfManyIn, //String strOfOneIn2, String strOfManyIn2,
                String[] categoryNodesIn, EntityPanel[] entityPanelIn, String[]fieldsOnTitleIn, String[]fieldsOnTitleCaptionIn, int[] fieldsOrderbyIn, 
                int intValidationColumnIn,int intValidationTypeIn, String yearEnforceIn)*/
                                                                                                           //SUM(retValueAccordingToType(1, i.productTypeId, i.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, i.productTypeId, i.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, i.productTypeId, i.valueReturn)) AS \"κατ 3\",
      EntityInfo id =  new EntityInfo("frvapplicationheader","SELECT frvapplicationheader.applicationHeaderId AS\"Νο αίτησης\" , f.farmerId AS\"Νο αγρότη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.farmerAfm AS\"Α.Φ.Μ.\", permanent AS \"υπολ\" , frvapplicationheader.dateOfApplication AS \"ημ/νια αίτησης\",frvapplicationheader.dbYearDeliveryId AS \"αποστολή\", frvapplicationheader.invCount AS \"πλήθος\", frvapplicationheader.value AS \"αξία\",  frvapplicationheader.valueReturn AS \"επιστροφή\", frvapplicationheader.payment AS \"κράτηση\", (frvapplicationheader.valueReturn - frvapplicationheader.payment) AS \"σύνολο\""+
              " FROM frvapplicationheader, frvfarmer f, frvapplicationline i, frvyeardelivery d "+
      //        " WHERE i.farmerId = f.farmerId AND a.farmerId = f.farmerId AND i.dbYearDeliveryId = a.dbYearDeliveryId AND a.dbYearDeliveryId = d.dbYearDeliveryId AND i.dbyear=a.dbyear AND i.dbCompanyId=a.dbCompanyId  " GROUP BY i.farmerId ORDER BY f.surname, f.name, f.fathername",null,null,null,deliveryQueryEditable, "frvapplicationline","SELECT buyerId,applicationlineTypeId,applicationlineNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\",productId,value AS\"αξία\" FROM frvapplicationline",applicationHeaderDBFields,applicationLineDBFields,deliveryFieldsManyOnInsert,deliveryFieldsManyTranslationOnInsert,"frvapplicationheader",deliveryQueryManyReadOnly,true, deliveryWhereField, deliveryWhereValue ,"παραστατικά αγροτών","DTRM","f","Νο αγρότη","farmerId",deliveryPrimKeyMany,deliveryPrimKeyManyTran ,deliveryBErs, deliveryEntityFilterGroupOfComps,"αγρότη με παραστατικά", "αγροτών με παραστατικά","το παραστατικό","παραστατικών", null,entityPanelDelivery,null,null,deliveryFieldsOrderBy,5,checkBoxInfo,entityTaskDelivery,4,FIELD_VALIDATION_AFM);
              " WHERE frvapplicationheader.applicationHeaderId = i.applicationHeaderId AND frvapplicationheader.farmerId = f.farmerId AND frvapplicationheader.dbYearDeliveryId = d.dbYearDeliveryId AND i.dbCompanyId=frvapplicationheader.dbCompanyId AND frvapplicationheader.dbCompanyId="+VariablesGlobal.globalCompanyId+/*" AND frvapplicationheader.dbYearId="+VariablesGlobal.globalYearId+""+*//*" AND i.dbYearDeliveryId="+VariablesGlobal.globalDeliveryId+*/
              " GROUP BY frvapplicationheader.applicationHeaderId ORDER BY f.surname, f.name, f.fathername, f.farmerAfm,  frvapplicationheader.dbYearDeliveryId","SELECT frvapplicationheader.applicationHeaderId , f.farmerId AS\"Νο αγρότη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.farmerAfm AS\"Α.Φ.Μ.\", permanent AS \"υπολ\" , frvapplicationheader.dateOfApplication AS \"ημ/νια αίτησης\",frvapplicationheader.dbYearDeliveryId AS \"αποστολή\", COUNT(i.value) AS \"πλήθος\", SUM(i.value) AS \"αξία\", SUM(retValueAccordingToType(1, i.productTypeId, i.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, i.productTypeId, i.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, i.productTypeId, i.valueReturn)) AS \"κατ 3\", frvapplicationheader.valueReturn AS \"επιστροφή\", frvapplicationheader.payment AS \"κράτηση\", (a.valueReturn - a.payment) AS \"σύνολο\""," FROM frvapplicationheader, frvfarmer f, frvapplicationline i, dbDelivery d ","",null,fieldsForSumsApplication,"παραστατικά αγροτών","DORM","","Νο αίτησης","applicationHeaderId",deliveryBErs,appicationEntityFilterGroupOfComps,"αγρότη με παραστατικά", "αγροτών με παραστατικά", strApplicationCategories,entityPanelApplication,fieldsOnTitleApplication, fieldsOnTitleCaptionApplication,applicationFieldsOrderBy,5,FIELD_VALIDATION_AFM,null,globalYearPlusOne);
      
//        listEntityInfo.add(id);
        EntityMenu emid = new EntityMenu();
        emid.setEntityInfo(id,ICO_PAPER);
        emid.setEntityType(ENTITY_TYPE_DATAENTRY);
        //listEntities.add(emid);
        DataTreeNode nodeemid = new DataTreeNode(emid);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemid);        

       //---------------------------------------------------------------------------------
        
        
 String[] taskCalculationTypeCalc ={"υπολογισμός"};
      String[] taskCalculationTypeNull ={"μηδενισμός"};     
       EntityFilterSettings[] taskErs = new EntityFilterSettings[2];   
       //taskErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","a",VariablesGlobal.globalCompanyId,-1,-1,-1,FIELD_OBLIGATORY);  // checkboxTable
       taskErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbyearId","dbyear","a", VariablesGlobal.globalYearId,-1,-1,-1,FIELD_OBLIGATORY);
       taskErs[1]=new EntityFilterSettings("αποστολή","checkboxTable","string","equals","dbYearDeliveryId","frvyeardelivery","a","",-1,-1,-1,FIELD_NOCOMPLETION);         //"αποστολή","onelookup","string","equals","deliveryId","i",null,VariablesGlobal.globalDeliveryId,-1,-1,-1);   
       
       EntityGroupOfComps[] taskEntityGroupOfComps = null;
       
      EntityQuery[] eqCalc = new EntityQuery[2];
      eqCalc[0] = new EntityQuery("UPDATE frvapplicationline AS i, frvapplicationheader AS a, frvproducttype AS pt ,frvproduct AS p SET i.productTypeId = pt.productTypeId,i.productTypePercentage=(pt.returnVat+pt.returnFuel), i.valueReturn = (i.value * (pt.returnVat+pt.returnFuel))/100 WHERE i.productId=p.productId AND a.applicationHeaderId = i.applicationHeaderId AND p.productTypeId=pt.productTypeId AND a.dbCompanyId = "+VariablesGlobal.globalCompanyId,true,0,null,null,null,"παραστατικά ενημερώθηκαν.","Κανένα παραστατικό δεν ενημερώθηκε.");
      eqCalc[1] = new EntityQuery("UPDATE frvapplicationline AS i, frvapplicationheader AS a SET a.permanent =1,  a.valueReturn=(SELECT SUM(i.valueReturn) FROM frvapplicationline AS l WHERE a.applicationHeaderId = l.applicationHeaderId AND a.dbCompanyId=l.dbCompanyId GROUP BY a.applicationHeaderId AND a.dbCompanyId ) WHERE i.dbCompanyId=a.dbCompanyId AND a.applicationHeaderId = i.applicationHeaderId  AND a.dbCompanyId = "+VariablesGlobal.globalCompanyId,true,0,null,null,null,"αιτήσεις ενημερώθηκαν.","Καμία αίτηση δεν ενημερώθηκε.");
     // eq[1] = new EntityQuery("UPDATE frvapplicationheader SET permanent=1",true,0,"success","faillure");
      EntityQuery[] eqNull = new EntityQuery[2];
      eqNull[0] = new EntityQuery("UPDATE frvapplicationline AS i,frvapplicationheader AS a, frvproducttype AS pt,frvproduct AS p SET i.productTypeId = NULL ,i.productTypePercentage=NULL, i.valueReturn = NULL WHERE i.productId=p.productId AND a.applicationHeaderId = i.applicationHeaderId AND p.productTypeId=pt.productTypeId AND a.dbCompanyId = "+VariablesGlobal.globalCompanyId,true,0,null,null,null,"παραστατικά ενημερώθηκαν.","Κανένα παραστατικό δεν ενημερώθηκε.");
      eqNull[1] = new EntityQuery("UPDATE frvapplicationline AS i, frvapplicationheader AS a SET a.permanent =0, a.payment = NULL, a.valueReturn= NULL  WHERE a.dbCompanyId = "+VariablesGlobal.globalCompanyId,true,0,null,null,null,"αιτήσεις ενημερώθηκαν.","Καμία αίτηση δεν ενημερώθηκε.");
     // eq[3] = new EntityQuery("UPDATE frvapplicationheader SET permanent=0",true,1,"success","faillure");    a.invcount=NULL, a.value=NULL,         //  WHERE i.farmerId=a.farmerId AND i.dbyear=a.dbyear AND i.dbCompanyId=a.dbCompanyId AND a.dbYearDeliveryId=i.dbYearDeliveryId
      EntityTask[] entityTaskDelivery = new EntityTask[2];
      entityTaskDelivery[0] = new EntityTask("permanent","υπολογισμός επιστροφής και κρατήσεων", "υπολογίζει τα ποσά επιστροφής των αγροτών και τις κρατήσεις του συνεταιρισμού", taskCalculationTypeCalc, taskErs,taskEntityGroupOfComps,eqCalc,false,"d",globalYearPlusOne );
      entityTaskDelivery[1] = new EntityTask("permanentnull","μηδενισμός επιστροφής και κρατήσεων", "μηδενίζει τα ποσά επιστροφής των αγροτών και τις κρατήσεις του συνεταιρισμού", taskCalculationTypeNull, taskErs,taskEntityGroupOfComps,eqNull,true,null,globalYearPlusOne );
      

        //buyerId,applicationlineTypeId,applicationlineNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\",productId,value AS\"αξία\" 
       //EntityGroupOfComps deliveryEntityFilterGroupOfComps = null;
        
        //deliveryQueryEditable="SELECT d.farmerId, d.dateOfApplication, d.deliveryId,  d.dbCompanyId, d.dbyear  FROM frvapplicationheader d WHERE dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+" AND dbCompanyId="+VariablesGlobal.globalCompanyId;
       
       
       //  "SELECT aa AS\"αα\", buyerId,applicationlineTypeId,applicationlineNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\",productId,value AS\"αξία\" FROM frvapplicationline"
        //base = new DefaultMutableTreeNode(new EntityInfo("frvapplicationheader", null ,null,null,null,deliveryQueryEditable, "frvapplicationline" ,"SELECT buyerId,applicationlineTypeId,applicationlineNo,date,productId,value FROM frvapplicationline",deliveryFields,deliveryFieldsTranslation,deliveryFieldsMany,deliveryFieldsManyTranslation,deliveryFieldsManyOnInsert,deliveryFieldsManyTranslationOnInsert,"frvapplicationheader",true,deliveryWhereField,deliveryWhereValue,"παραστατικά για νέο αγρότη","DTRO", "Νο αγρότη","farmerId",deliveryPrimKeyMany,deliveryPrimKeyManyTran, null, null, "τον παραγωγό με αποστολή", "παραγωγών με αποστολή","το παραστατικό","παραστατικών",null,entityPanelDeliveryDataentry,null,null));
 //       EntityInfoMany ib = new EntityInfoMany("frvapplicationheader", "SELECT f.farmerId AS\"Νο αγρότη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.farmerAfm AS\"Α.Φ.Μ.\", count(ri.value) AS\"πλήθος\",sum(ri.value) AS\"σύνολο\",sum(ri.total1) AS \"επισ 1\",sum(ri.total2) AS \"επισ 2\",sum(ri.total3) AS \"επισ 3\", sum(ri.total1)+sum(ri.total2)+sum(ri.total3) AS \"συν.επιστρ\" FROM return_from_applicationlines ri, frvfarmer f WHERE ri.farmerId = f.farmerId "/*AND ri.deliveryId = "+VariablesGlobal.globalDeliveryId+"*/+" AND ri.dbyear="+VariablesGlobal.globalYear+" AND ri.dbCompanyId="+VariablesGlobal.globalCompanyId+" GROUP BY f.farmerId ORDER BY f.surname, f.name",null,null,null,deliveryQueryEditable,null,null, "frvapplicationline",deliveryQueryManyEditable,applicationHeaderDBFields,applicationLineDBFields,/*deliveryFieldsManyOnInsert,deliveryFieldsManyTranslationOnInsert,*/"frvapplicationheader",deliveryQueryManyReadOnly,true, deliveryWhereField, deliveryWhereValue ,"παραστατικά αγρότη","DTRO","f"/*because in sql f is frvfarmer*/,"Νο αγρότη","farmerId",/*deliveryPrimKeyMany,deliveryPrimKeyManyTran ,*/null,null,"αγρότη με παραστατικά", "αγροτών με παραστατικά","το παραστατικό","παραστατικών", null,entityPanelDelivery,null,null,deliveryFieldsOrderBy,-1,null,entityTaskDelivery,4,FIELD_VALIDATION_AFM,globalYearPlusOne);
       
       
       EntityMenu emib = new EntityMenu();
       emib.setEntityTask(entityTaskDelivery[0],ICO_TASK);
       emib.setEntityType(ENTITY_TYPE_TASK);
       //listEntities.add(emib);
       DataTreeNode nodeemib = new DataTreeNode(emib);
       nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemib);   
       
       
       EntityMenu emic = new EntityMenu();
       emic.setEntityTask(entityTaskDelivery[1],ICO_TASK);
       emic.setEntityType(ENTITY_TYPE_TASK);
       //listEntities.add(emib);
       DataTreeNode nodeemic = new DataTreeNode(emic);
       nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemic);        
       
        
        //---------------------------------------------------------
        
        
       EntityFilterSettings[] buyerErs = new EntityFilterSettings[3];       
       buyerErs[0]=new EntityFilterSettings("επωνυμία","","string","equals","buyerTitle","frvbuyer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       buyerErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","buyerAfm","frvbuyer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       buyerErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","frvbuyer","",-1,-1,-1,FIELD_NOCOMPLETION);
       
       EntityGroupOfComps[] buyerEntityGroupOfComps = null;

       int[] buyerFieldsOrderBy ={2,3};
       
       String[] fieldsForSumsBuyer = null;
       
       //EntityInfo ie=new EntityInfo("frvbuyer", "SELECT frvbuyer.buyerId AS\"Νο αγοραστή\", frvbuyer.buyerTitle AS \"τίτλος αγοραστή\", frvbuyer.buyerAfm AS \"Α.Φ.Μ.\", frvbuyer.doyId AS \"Νο Δ.Ο.Υ.\", doy.doyName AS \"ονομασία Δ.Ο.Υ.\", frvbuyer.phone AS \"τηλέφωνο(1)\",frvbuyer.phone2 AS \"τηλέφωνο(2)\", frvproduct.productName AS \"προτ. προϊόν\" FROM frvbuyer LEFT JOIN doy ON frvbuyer.doyId=doy.doyId LEFT JOIN frvproduct ON frvbuyer.productId=frvproduct.productId ORDER BY buyerTitle "  ,"SELECT buyerId AS \"Νο αγοραστή\", buyerTitle AS \"τίτλος αγοραστή\", buyerAfm AS \"Α.Φ.Μ.\", doyId"," FROM frvbuyer","",null,fieldsForSumsBuyer,null,null,buyerDBFields,null,null,null,null,null,false,null,null ,"αγοραστές","DORM","","Νο αγοραστή","buyerId",null,null,buyerErs,buyerEntityGroupOfComps,"αγοραστή","αγοραστών",null, null, strBuyerCategories,entityPanelBuyer,fieldsOnTitleBuyer, fieldsOnTitleCaptionBuyer,buyerFieldsOrderBy,2,FIELD_VALIDATION_AFM,globalYearPlusOne);
        EntityInfo ie=new EntityInfo("frvbuyer", "SELECT frvbuyer.buyerId AS\"Νο αγοραστή\", frvbuyer.buyerTitle AS \"τίτλος αγοραστή\", frvbuyer.buyerAfm AS \"Α.Φ.Μ.\", frvbuyer.doyId AS \"Νο Δ.Ο.Υ.\", doy.doyName AS \"ονομασία Δ.Ο.Υ.\", frvbuyer.phone AS \"τηλέφωνο(1)\",frvbuyer.phone2 AS \"τηλέφωνο(2)\", frvproduct.productName AS \"προτ. προϊόν\" FROM frvbuyer LEFT JOIN doy ON frvbuyer.doyId=doy.doyId LEFT JOIN frvproduct ON frvbuyer.productId=frvproduct.productId ORDER BY buyerTitle "  ,"SELECT buyerId AS \"Νο αγοραστή\", buyerTitle AS \"τίτλος αγοραστή\", buyerAfm AS \"Α.Φ.Μ.\", doyId"," FROM frvbuyer","",null,fieldsForSumsBuyer,"αγοραστές","DORM","","Νο αγοραστή","buyerId",buyerErs,buyerEntityGroupOfComps,"αγοραστή","αγοραστών", strBuyerCategories,entityPanelBuyer,fieldsOnTitleBuyer, fieldsOnTitleCaptionBuyer,buyerFieldsOrderBy,2,FIELD_VALIDATION_AFM,null,globalYearPlusOne);
        EntityMenu emie = new EntityMenu();
        emie.setEntityInfo(ie,ICO_BUYER16);
        emie.setEntityType(ENTITY_TYPE_DATAENTRY);
        //listEntities.add(emie);
        DataTreeNode nodeemie = new DataTreeNode(emie);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemie);        

 //     EntityQuery[] eq = new EntityQuery[2];
//      eq[0] = new EntityQuery("UPDATE frvapplicationline, frvproducttype ,frvproduct SET frvapplicationline.productTypeId = frvproducttype.productTypeId,frvapplicationline.productTypePercentage=(frvproducttype.returnVat+frvproducttype.returnFuel), valueReturn = (value * (frvproducttype.returnVat+frvproducttype.returnFuel))/100 WHERE frvapplicationline.productId=frvproduct.productId AND frvproduct.productTypeId=frvproducttype.productTypeId",true,0,null,null,"παραστατικά ενημερώθηκαν.","Κανένα παραστατικό δεν ενημερώθηκε.");
     // eq[1] = new EntityQuery("UPDATE frvapplicationheader SET permanent=1",true,0,"success","faillure");
//      eq[1] = new EntityQuery("UPDATE frvapplicationline, frvproducttype ,frvproduct SET frvapplicationline.productTypeId = NULL ,frvapplicationline.productTypePercentage=NULL, valueReturn = NULL, frvapplicationline.payment = NULL WHERE frvapplicationline.productId=frvproduct.productId AND frvproduct.productTypeId=frvproducttype.productTypeId",true,1,null,null,"παραστατικά ενημερώθηκαν.","Κανένα παραστατικό δεν ενημερώθηκε.");
     // eq[3] = new EntityQuery("UPDATE frvapplicationheader SET permanent=0",true,1,"success","faillure");      
//      EntityTask ta = new EntityTask("permanent","οριστικοποίηση", "οριστικοποίηση / αποοριστικοποίηση", taskCalculationType, taskErs,eq,false );
/*        EntityTask ta = new EntityTask("permanent","υπολογισμός επιστροφής και κρατήσεων", "υπολογισμός επιστροφής και κρατήσεων", taskCalculationTypeCalc, taskErs,taskEntityGroupOfComps,eqCalc,false,"d" );
        EntityMenu emta = new EntityMenu();
        emta.setEntityTask(ta,ICO_TASK);
        emta.setEntityType(ENTITY_TYPE_TASK);
        //listEntities.add(emie);
        DataTreeNode nodeemta = new DataTreeNode(emta);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemta); */
     //------------------------------------------------------------
     
     /*   EntityFilterSettings[] productErs = new EntityFilterSettings[2];       
        productErs[0]=new EntityFilterSettings("ονομασία","","string","equals","productName","frvproduct",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        productErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","productTypeId","frvproducttype","frvproduct","",-1,-1,-1,FIELD_NOCOMPLETION);
        
       EntityGroupOfComps[] productEntityGroupOfComps = null;
        
        int[] productFieldsOrderby ={2};
        
        String[] fieldsForSumsProduct = null;
        
        //EntityParameter pf = new EntityParameter("frvproduct", "SELECT frvproduct.productId AS \"Νο προϊόντος\", frvproduct.productName AS \"ονομασία\", frvproduct.productTypeId AS \"Νο τύπου\", frvproducttype.producttypeName AS \"τύπος προϊόντος\", frvproducttype.returnVat AS \"επιστρ φόρου\", frvproducttype.returnFuel AS \"επιστρ πετρελαίου\" FROM frvproduct LEFT JOIN frvproducttype ON frvproduct.productTypeId=frvproducttype.productTypeId ORDER BY frvproduct.productName"  ,"SELECT frvproduct.productId AS \"Νο προϊόντος\", frvproduct.productName AS \"ονομασία\", frvproduct.productTypeId","FROM frvproduct","",fieldsForSumsProduct,productDBFields,"προϊόντα","DORM","Νο προϊόντος","productId",productErs,productEntityGroupOfComps,"προϊόντος","προϊόντων",entityPanelProduct,null,fieldsOnTitleProduct,fieldsOnTitleCaptionProduct,productFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityInfo pf = new EntityInfo("frvproduct", "SELECT frvproduct.productId AS \"Νο προϊόντος\", frvproduct.productName AS \"ονομασία\", frvproduct.productTypeId AS \"Νο τύπου\", frvproducttype.producttypeName AS \"τύπος προϊόντος\", frvproducttype.returnVat AS \"επιστρ φόρου\", frvproducttype.returnFuel AS \"επιστρ πετρελαίου\" FROM frvproduct LEFT JOIN frvproducttype ON frvproduct.productTypeId=frvproducttype.productTypeId ORDER BY frvproduct.productName"  ,"SELECT frvproduct.productId AS \"Νο προϊόντος\", frvproduct.productName AS \"ονομασία\", frvproduct.productTypeId","FROM frvproduct","",null,fieldsForSumsProduct,"προϊόντα","DORM","","Νο προϊόντος","productId",productErs,productEntityGroupOfComps,"προϊόντος","προϊόντων",strProductCategories,entityPanelProduct,fieldsOnTitleProduct,fieldsOnTitleCaptionProduct,productFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empf = new EntityMenu();
        empf.setEntityInfo(pf,ICO_TABLE16);
        empf.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeempf = new DataTreeNode(empf);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeempf);
        */
  }

   public ArrayList addEntitiesLookupTableConstants(ArrayList <EntityLookupTableConstants> listEntityLookupTableConstants)
   {
       
       EntityLookupTableConstants entityLookupTableConstants;
       

        /*

       EntityLookupTableConstantsData [] luTCDataDecimalChar = new EntityLookupTableConstantsData[2];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataDecimalChar[0]=new EntityLookupTableConstantsData(",",1,",");
       luTCDataDecimalChar[1]=new EntityLookupTableConstantsData(".",2,".");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCdecimalchar",luTCDataDecimalChar));       
       */
           
       
       return listEntityLookupTableConstants;
   }
  
   public ArrayList addEntitiesLookup(ArrayList entities)
   { 
     EntityLookUp entityLookUp;

     
     // make entities for all tables called with foreign keys
     
       EntityFilterSettings[] farmerErs = new EntityFilterSettings[3];       
       farmerErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","frvfarmer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       farmerErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","frvfarmer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       farmerErs[2]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","frvtown","frvfarmer","",-1,-1,-1,FIELD_NOCOMPLETION);
            // for 'intNoOfColsWhenInTable' look also at  deliveryFieldsMany
      
     
     String[] lookUpFieldFarmer={"surname","farmerAfm"};
     
     /*        public EntityLookUp(String nameIn, String foreignTableIn, String queryIn, String querySubqueryWhereIn, String querySubqueryIsActiveIn,String queryOrderByIn, 
           String lookUpKeyIn, String lookUpKeyTranslationIn, String lookUpKeyFTIn, String lookUpLabelIn, int lookUpFieldIndexIn,
           String[] lookUpFieldIn,String lookUpFieldLabelIn,int  lookUpFieldLengthIn,String lookUpFieldTypeIn,int lookUpField2IndexIn, String lookUpField2In,
           String lookUpField2LabelIn, int lookUpField3IndexIn, String lookUpField3In, String lookUpField3LabelIn,
           String queryEditableIn,   String strOfOneIn,
           String strOfManyIn,String [] categoryNodesIn, EntityPanel[] entityPanelIn, String[]fieldsOnTitleIn,
           String[]fieldsOnTitleCaptionIn,EntityFilterSettings[] entityFilterSettingsIn, int intColFieldDescriptionIn,
           int intNoOfColsWhenInTableIn, ImageIcon iconIn , boolean showToolbarIn,int intValidationColumnIn,int intValidationTypeIn, String[] fieldsForSumsIn)*/
            
     entities.add(entityLookUp = new EntityLookUp("frvfarmer","frvfarmer","SELECT farmerId AS\"Νο αγρότη\", surname AS\"επίθετο\", frvfarmer.name AS\"όνομα\", fatherName AS\"πατρόνυμο\", farmerAfm AS\"Α.Φ.Μ.\", idNo AS\"αρ ταυτοτ\", frvtown.townName AS \"πόλη/χωριό\" FROM frvfarmer LEFT JOIN frvtown ON frvfarmer.townId=frvtown.townId","","","ORDER BY surname, frvfarmer.name, fatherName",""/*queryWhereForFormVariable*/,"farmerId","Νο αγρότη","farmerId","αγρότης",2,lookUpFieldFarmer,"επίθετο ή ΑΦΜ",10,"java.lang.String",3,"name","όνομα",5,"farmerAfm", "Α.Φ.Μ.",farmerQueryEditable, "αγρότη","αγροτών",strFarmerCategories,entityPanelFarmer,fieldsOnTitleFarmer,fieldsOnTitleCaptionFarmer,farmerErs,2,2,ICO_FARMER16,true,4,FIELD_VALIDATION_AFM,null));

     
     //int[] lookUpFieldIndexInvoiceType ={2,3,0};     
     String[] lookUpFieldInvoiceType={"invoiceTypeName","abbreviation"};
     //String[] lookUpFieldLabelInvoiceType={"είδος παραστατικού","συντομογραφία",null};
     entities.add(entityLookUp = new EntityLookUp("frvinvoicetype","frvinvoicetype","SELECT invoiceTypeId AS\"Νο τύπου παρ/κού\",invoiceTypeName AS\"ονομασία τύπου παρ/κού\", abbreviation AS\"συντομογραφία\" FROM frvinvoicetype","","","ORDER BY invoiceTypeName",""/*queryWhereForFormVariable*/,"invoiceTypeId","Νο τύπου παρ/κού","invoiceTypeId","είδος παραστατικού",2,lookUpFieldInvoiceType,"είδος παραστατικού",15,"java.lang.String",0,null,null,0,null,null,invoicetypeQueryEditable,"τύπου παραστατικού","τύπων παραστατικού",null,entityPanelInvoiceType,fieldsOnTitleInvoiceType, fieldsOnTitleCaptionInvoiceType,null,2,1,null,true,-1,-1,null)); 
     //entities.add(entityLookUp = new EntityLookUp("applicationtype","SELECT applicationlineTypeId AS\"Νο τύπου παρ/κού\",applicationlineTypeName AS\"ονομασία τύπου παρ/κού\", abbreviation AS\"συντομογραφία\" FROM applicationtype","ORDER BY applicationlineTypeName","applicationlineTypeId","Νο τύπου παρ/κού",2,lookUpFieldInvoiceType,"είδος παραστατικού",0,null,null,0,null,null,applicationtypeQueryEditable,"τύπου παραστατικού","τύπων παραστατικού",null,entityPanelInvoiceType,fieldsOnTitleInvoiceType, fieldsOnTitleCaptionInvoiceType,null,2,1,null,true,-1,-1)); 


     //int[] lookUpFieldIndexInvoiceType ={2,3,0};     
     String[] lookUpFieldTown={"townName"};
     
        EntityFilterSettings[] townErs = new EntityFilterSettings[1];       
        townErs[0]=new EntityFilterSettings("ονομασία","","string","equals","townName","frvtown",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("frvtown","frvtown","SELECT townId AS\"Νο πόλης\", townName AS\"πόλη/χωριό\", state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM frvtown","","","ORDER BY townName" ,""/*queryWhereForFormVariable*/,"townId","Νο πόλης","townId","πόλη/χωριό",2,lookUpFieldTown,"πόλη/χωριό",10,"java.lang.String",0,null,null,0,null,null,townQueryEditable,"πόλης","πόλεων",null,entityPanelTown,fieldsOnTitleTown, fieldsOnTitleCaptionTown,townErs,2,1,null,true,-1,-1,null));
     
  

     //int[] lookUpFieldIndexInvoiceType ={2,3,0};     
     /*String[] lookUpFieldDoy={"doyName"};
     
        EntityFilterSettings[] doyErs = new EntityFilterSettings[2];       
        doyErs[0]=new EntityFilterSettings("κωδικός","","string","equals","doyId","doy",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        doyErs[1]=new EntityFilterSettings("ονομασία","","string","equals","doyName","doy",null,"",-1,-1,-1,FIELD_NOCOMPLETION); */
     //entities.add(entityLookUp = new EntityLookUp("doy","doy","SELECT doyId AS\"Νο Δ.Ο.Υ.\", doyName AS\"ονομασία Δ.Ο.Υ.\" FROM doy","","","ORDER BY doyId",""/*queryWhereForFormVariable*/,"doyId","Νο Δ.Ο.Υ.","doyId","Δ.Ο.Υ.",2,lookUpFieldDoy,"ονομασία Δ.Ο.Υ.",15,"java.lang.String",0,null,null,0,null,null,doyQueryEditable,"της Δ.Ο.Υ.","Δ.Ο.Υ.",null,entityPanelDoy,fieldsOnTitleDoy, fieldsOnTitleCaptionDoy,doyErs,2,1,null,true,-1,-1,null));     	 	
     
     //int[] lookUpFieldIndexInvoiceType ={2,3,0};     
     String[] lookUpFieldBuyer={"buyerId","buyerTitle","buyerAfm"};     
       EntityFilterSettings[] buyerErs = new EntityFilterSettings[2];       
       buyerErs[0]=new EntityFilterSettings("επωνυμία","","string","equals","buyerTitle","frvbuyer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       buyerErs[1]=new EntityFilterSettings("Α.Φ.Μ.","","string","equals","buyerAfm","frvbuyer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("frvbuyer","frvbuyer","SELECT buyerId AS\"Νο αγοραστή\",buyerTitle AS\"τίτλος αγοραστή\",buyerAfm AS\"Α.Φ.Μ.\", phone AS\"τηλέφωνο(1)\", frvproduct.productName AS \"προτ. προϊόν\"  FROM frvbuyer LEFT JOIN frvproduct ON frvbuyer.productId=frvproduct.productId","","","ORDER BY buyerTitle",""/*queryWhereForFormVariable*/,"buyerId","Νο αγοραστή","buyerId","ονομασία αγοραστή ή Α.Φ.Μ.",2,lookUpFieldBuyer," id ή τίτλος αγοραστή ή ΑΦΜ",15,"java.lang.String",3,"buyerAfm","Α.Φ.Μ. αγοραστή",0,null,null,buyerQueryEditable,"αγοραστή","αγοραστών",strBuyerCategories,entityPanelBuyer,fieldsOnTitleBuyer, fieldsOnTitleCaptionBuyer,buyerErs,2,1,ICO_BUYER16,true,2,FIELD_VALIDATION_AFM,null)); // after buyerErs and 2 and before ico should be 1(is the count of cols)
     
     
        EntityFilterSettings[] productErs = new EntityFilterSettings[2];       
        productErs[0]=new EntityFilterSettings("ονομασία","","string","equals","productName","frvproduct",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        productErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","productTypeId","frvproducttype","frvproduct","",-1,-1,-1,FIELD_NOCOMPLETION);
     //int[] lookUpFieldIndexInvoiceType ={2,3,0};     
     String[] lookUpFieldProduct={"productName"};                   
     //entities.add(entityLookUp = new EntityLookUp("frvproduct","SELECT frvproduct.productId AS\"Νο προϊόντος\", frvproduct.productName AS \"ονομασία\", frvproduct.productTypeId AS \"Νο τύπου\", frvproducttype.producttypeName AS \"τύπος προϊόντος\", frvproducttype.returnVat AS \"επιστρ φόρου\", frvproducttype.returnFuel AS \"επιστρ πετρελαίου\"  FROM frvproduct, frvproducttype WHERE frvproduct.productTypeId=frvproducttype.productTypeId","ORDER BY frvproduct.productName ","productId","Νο προϊόντος","productId","ονομασία προϊόντος",2,lookUpFieldProduct,"ονομασία",0,null,null,0,null,null,productQueryEditable,"προϊόντος","προϊόντων",null,entityPanelProduct,fieldsOnTitleProduct,fieldsOnTitleCaptionProduct,productErs,2,1,null,true,-1,-1));    	 	
     entities.add(entityLookUp = new EntityLookUp("frvproduct","frvproduct","SELECT frvproduct.productId AS\"Νο προϊόντος\", frvproduct.productName AS \"ονομασία\", frvproduct.productTypeId AS \"Νο τύπου\", frvproducttype.producttypeName AS \"τύπος προϊόντος\", frvproducttype.returnVat AS \"επιστρ φόρου\", frvproducttype.returnFuel AS \"επιστρ πετρελαίου\"  FROM frvproduct, frvproducttype"," WHERE frvproduct.productTypeId = frvproducttype.productTypeId","","ORDER BY frvproduct.productName ",""/*queryWhereForFormVariable*/,"productId","Νο προϊόντος","productId","ονομασία προϊόντος",2,lookUpFieldProduct,"ονομασία",15,"java.lang.String",0,null,null,0,null,null,productQueryEditable,"προϊόντος","προϊόντων",null,entityPanelProduct,fieldsOnTitleProduct,fieldsOnTitleCaptionProduct,productErs,2,1,null,true,-1,-1,null));    	 	
     
     //int[] lookUpFieldIndexInvoiceType ={2,3,0};     
     //String[] lookUpFieldBank={"bankBranch"};
     //entities.add(entityLookUp = new EntityLookUp("bank","bank","SELECT bank.bankId AS\"Νο τράπεζας\", bank.bankBranch AS \"τίτλος τράπεζας\" FROM bank","","","ORDER BY bank.bankBranch",""/*queryWhereForFormVariable*/,"bankId","Νο τράπεζας","bankId","τράπεζα",2,lookUpFieldBank,"τίτλος τράπεζας",10,"java.lang.String",0,null,null,0,null,null,bankQueryEditable,"τράπεζας","τραπεζών",null,entityPanelBank,fieldsOnTitleBank,fieldsOnTitleCaptionBank,null,2,1,null,true,-1,-1,null));    	 		
     
     //int[] lookUpFieldIndexInvoiceType ={2,3,0};     
     String[] lookUpFieldProductType={"productTypeName"};
     entities.add(entityLookUp = new EntityLookUp("frvproducttype","frvproducttype","SELECT productTypeId AS \"Νο τύπου προϊόντος\",producttypeName AS \"ονομασία\",returnVat AS\"ποσοστό επιστροφής φόρου\",returnFuel AS \"ποσοστό επιστροφής πετρελαίου\" FROM frvproducttype","","","ORDER BY productTypeId",""/*queryWhereForFormVariable*/,"productTypeId","Νο τύπου προϊόντος","productTypeId","τύπος προϊόντος",2,lookUpFieldProductType,"τύπος προϊόντος",15,"java.lang.String",0,null,null,0,null,null,producttypeQueryEditable,"του τύπου προϊόντος","τύπων προϊόντος",null,entityPanelProductType,fieldsOnTitleProductType, fieldsOnTitleCaptionProductType,null,2,1,null,true,-1,-1,null));	

     
     String[] lookUpFieldDbYearDelivery={"description"};    // deliveryId
     entities.add(entityLookUp = new EntityLookUp("frvyeardelivery","frvyeardelivery","SELECT dbYearDeliveryId AS \"Νο αποστολής\",inc AS\"αα\", description  AS \"περιγραφή\" FROM frvyeardelivery","WHERE dbYearId = "+VariablesGlobal.globalYearId +" AND dbCompanyId = "+VariablesGlobal.globalCompanyId+"","","ORDER BY inc",""/*queryWhereForFormVariable*/,"dbYearDeliveryId","Νο αποστολής","dbYearDeliveryId","αποστολής",3,lookUpFieldDbYearDelivery,"Νο αποστολής",15,"java.lang.String",0,null,null,0,null,null,dbYearDeliveryQueryEditable,"αποστολής","αποστολών",null,entityPanelDbYearDelivery,fieldsOnTitleDbYearDelivery,fieldsOnTitleCaptionDbYearDelivery,null,-1,1,ICO_PAPER,true,-1,-1,null));      	 	
 
     //int[] lookUpFieldIndexInvoiceType ={2,3,0};     
     String[] lookUpFieldDbYear={"dbyear"};
     entities.add(entityLookUp = new EntityLookUp("dbyear","dbyear","SELECT DISTINCT dbyearId AS \"χρήση Νο\", dbyear AS \"χρήση\", dbyear AS \"χρήση επιστροφής\" FROM dbyear","","", "ORDER BY dbyear" ,""/*queryWhereForFormVariable*/,"dbyear","χρήση","dbyear","χρήση",2,lookUpFieldDbYear,"χρήση",10,"java.lang.String",0,null,null,0,null,null,"","της χρήσης","των χρήσεων",null,entityPanelDbyear,fieldsOnTitleDbyear,fieldsOnTitleCaptionDbyear,null,-1,1,null,false,-1,-1,null));  

     //int[] lookUpFieldIndexInvoiceType ={2,3,0};     
    // String[] lookUpFieldDbCompany={"companyName"};
    // entities.add(entityLookUp = new EntityLookUp("dbcompany","dbcompany","SELECT dbCompanyId AS \"νο\", companyName AS \"επωνυμία\", companyVatNo AS \"ΑΦΜ\" FROM dbcompany","","", "ORDER BY companyName",""/*queryWhereForFormVariable*/,"dbCompanyId","νο","dbCompanyId","εταιρία",2,lookUpFieldDbCompany,"τίτλος εταιρίας",10,"java.lang.String",0,null,null,0,null,null,dbCompanyQueryEditable,"της εταιρίας","εταιριών",null,entityPanelDbCompany,fieldsOnTitleDbCompany,fieldsOnTitleCaptionDbCompany,null,2,1,null,true,2,FIELD_VALIDATION_AFM,null));     

     
     //System.out.println("--------  EntityData.addEntitiesLookup size "+entities.size());

     return entities;
   }
  
    public void addEntitiesParameters()  // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
    {
    
     //------------------------------------------------------------
  
        EntityFilterSettings[] productErs = new EntityFilterSettings[2];       
        productErs[0]=new EntityFilterSettings("ονομασία","","string","equals","productName","frvproduct",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        productErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","productTypeId","frvproducttype","frvproduct","",-1,-1,-1,FIELD_NOCOMPLETION);
        
      // EntityGroupOfComps[] productEntityGroupOfComps = null;
        
        int[] productFieldsOrderby ={2};
        
        String[] fieldsForSumsProduct = null;
        
       
        EntityParameter pa = new EntityParameter("frvproduct", "SELECT frvproduct.productId AS \"Νο προϊόντος\", frvproduct.productName AS \"ονομασία\", frvproduct.productTypeId AS \"Νο τύπου\", frvproducttype.producttypeName AS \"τύπος προϊόντος\", frvproducttype.returnVat AS \"επιστρ φόρου\", frvproducttype.returnFuel AS \"επιστρ πετρελαίου\" FROM frvproduct LEFT JOIN frvproducttype ON frvproduct.productTypeId=frvproducttype.productTypeId ORDER BY frvproduct.productName"  ,"SELECT frvproduct.productId AS \"Νο προϊόντος\", frvproduct.productName AS \"ονομασία\", frvproduct.productTypeId","FROM frvproduct","",fieldsForSumsProduct,productDBFields,"προϊόντα","DORM","Νο προϊόντος","productId",productErs,productEntityGroupOfComps,"προϊόντος","προϊόντων",entityPanelProduct,null,fieldsOnTitleProduct,fieldsOnTitleCaptionProduct,productFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empa = new EntityMenu();
        empa.setEntityParameter(pa,ICO_TABLE16);
        empa.setEntityType(ENTITY_TYPE_PARAMETER);
        /*DataTreeNode nodeempa = new DataTreeNode(empa);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempa);*/

        //------------------------------------------------------------
       int[] productTypeFieldsOrderby ={2};
       String[] fieldsForSumsProducttype=null;
       EntityParameter pb = new EntityParameter("frvproducttype", "SELECT productTypeId AS \"Νο τύπου προϊόντος\",producttypeName AS \"ονομασία\",returnVat AS \"ποσοστό επιστροφής φόρου\",returnFuel AS \"ποσοστό επιστροφής πετρελαίου\" FROM frvproducttype ORDER BY productTypeId" ,"SELECT productTypeId AS \"Νο τύπου προϊόντος\",producttypeName AS \"ονομασία\", returnVat AS \"ποσοστό επιστροφής φόρου\",returnFuel AS \"ποσοστό επιστροφής πετρελαίου\"","FROM frvproducttype","",fieldsForSumsProducttype,producttypeDBFields,"είδη προϊόντων","DORM","Νο τύπου προϊόντος","productTypeId", null,null,"τύπου προϊόντος", "τύπων προϊόντων",entityPanelProductType,null,fieldsOnTitleProductType,fieldsOnTitleCaptionProductType,productTypeFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empb = new EntityMenu();
        empb.setEntityParameter(pb,ICO_TABLE16);
        empb.setEntityType(ENTITY_TYPE_PARAMETER);
        /*DataTreeNode nodeempb = new DataTreeNode(empb);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempb);*/

        
        //------------------------------------------------------------
        EntityFilterSettings[] townErs = new EntityFilterSettings[1];       
        townErs[0]=new EntityFilterSettings("ονομασία","","string","equals","townName","frvtown",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       EntityGroupOfComps[] townEntityGroupOfComps = null;
        
        int[] townFieldsOrderby ={2};
        String[] fieldsForSumsTown=null;
        EntityParameter pc = new EntityParameter("frvtown",  "SELECT townId AS\"Νο πόλης\", townName AS\"πόλη/χωριό\", state AS\"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM frvtown ORDER BY townName"  ,"SELECT townId AS\"Νο πόλης\", townName AS\"πόλη/χωριό\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\"" ,"FROM frvtown" ,"",fieldsForSumsTown ,townDBFields,"πόλεις/χωριά","DORM","Νο πόλης","townId",townErs,townEntityGroupOfComps, "πόλης","πόλεων",entityPanelTown,null,fieldsOnTitleTown,fieldsOnTitleCaptionTown,townFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empc = new EntityMenu();
        empc.setEntityParameter(pc,ICO_TABLE16);
        empc.setEntityType(ENTITY_TYPE_PARAMETER);
        /*DataTreeNode nodeempc = new DataTreeNode(empc);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempc);*/

        
        //------------------------------------------------------------
      /*  EntityFilterSettings[] doyErs = new EntityFilterSettings[2];       
        doyErs[0]=new EntityFilterSettings("κωδικός","","string","equals","doyId","doy",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        doyErs[1]=new EntityFilterSettings("ονομασία","","string","equals","doyName","doy",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       EntityGroupOfComps[] doyEntityGroupOfComps = null;
                      
        int[] doyFieldsOrderby ={2};
        String[] fieldsForSumsDoy=null;
        EntityParameter pd = new EntityParameter("doy", "SELECT doyId AS \"Νο Δ.Ο.Υ.\", doyName AS \"ονομασία\", address AS \"διεύθυνση\", town AS \"πόλη/χωριό\", pc AS \"ΤΚ\", tel1 AS \"τηλ 1\", tel2 AS \"τηλ 2\", fax AS \"φαξ\" FROM doy ORDER BY doyId"  ,"SELECT doyId AS \"Νο Δ.Ο.Υ.\", doyName AS \"ονομασία\", address AS \"διεύθυνση\", town AS \"πόλη/χωριό\", pc AS \"ΤΚ\", tel1 AS \"τηλ 1\", tel2 AS \"τηλ 2\", fax AS \"φαξ\" ","FROM doy" ,"",fieldsForSumsDoy,doyDBFields ,"Δ.Ο.Υ.","DORM","Νο Δ.Ο.Υ.","doyId",doyErs,doyEntityGroupOfComps,"της Δ.Ο.Υ.","Δ.Ο.Υ.",entityPanelDoy,null,fieldsOnTitleDoy,fieldsOnTitleCaptionDoy,doyFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empd = new EntityMenu();
        empd.setEntityParameter(pd,ICO_TABLE16);
        empd.setEntityType(ENTITY_TYPE_PARAMETER);
        /*DataTreeNode nodeempd = new DataTreeNode(empd);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempd);*/

         
         //------------------------------------------------------------
        int[] invoicetypeFieldsOrderby ={2};
        String[] fieldsForSumsInvoicetype=null;
        EntityParameter pe = new EntityParameter("frvinvoicetype", "SELECT invoiceTypeId AS \"Νο τύπου παραστατικού\", invoicetypeName AS \"ονομασία\",abbreviation AS \"συντομογραφία\" FROM frvinvoicetype ORDER BY invoiceTypeId","SELECT invoiceTypeId AS \"Νο τύπου παραστατικού\", invoicetypeName AS \"ονομασία\" abbreviation AS \"συντομογραφία\"","FROM frvinvoicetype","",fieldsForSumsInvoicetype,invoicetypeDBFields ,"τύποι παραστατικών","DORM","Νο τύπου παραστατικού","invoiceTypeId",null,null,"τύπου παραστατικών", "τύπων παραστατικών",entityPanelInvoiceType,null,fieldsOnTitleInvoiceType,fieldsOnTitleCaptionInvoiceType,invoicetypeFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empe = new EntityMenu();
        empe.setEntityParameter(pe,ICO_TABLE16);
        empe.setEntityType(ENTITY_TYPE_PARAMETER);
        /*DataTreeNode nodeempe = new DataTreeNode(empe);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempe);*/
        
        //------------------------------------------------------------
       /* int[] bankFieldsOrderby ={2};
        String[] fieldsForSumsBank=null;
        EntityParameter pf = new EntityParameter("bank", "SELECT bankId AS \"Νο τράπεζας\", bankBranch AS \"τίτλος τράπεζας\" FROM bank","SELECT bankId AS \"Νο τράπεζας\", bankBranch AS \"τίτλος τράπεζας\"","FROM bank","",fieldsForSumsBank,bankDBFields ,"τράπεζες","DORM","Νο τράπεζας","bankId",null,null,"τράπεζας", "τραπεζών",entityPanelBank,null,fieldsOnTitleBank,fieldsOnTitleCaptionBank,bankFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empf = new EntityMenu();
        empf.setEntityParameter(pf,ICO_TABLE16);
        empf.setEntityType(ENTITY_TYPE_PARAMETER);
       /* DataTreeNode nodeempf = new DataTreeNode(empf);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempf);    */    

        //--------------------------------------------------------------
        
         
        
        
        //------------------------------------------------------------
        
       /*EntityFilterSettings[] dbCompanyErs = new EntityFilterSettings[3];       
       dbCompanyErs[0]=new EntityFilterSettings("επωνυμία","","string","equals","companyName","dbcompany",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       dbCompanyErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","companyVatNo","dbcompany",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       dbCompanyErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","dbcompany","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        int[] companyFieldsOrderby ={2};
        String[] fieldsForSumsDbCompany=null;
        EntityParameter pg = new EntityParameter("dbcompany", "SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.companyName AS\"τίτλος\", dbcompany.companyVatNo AS\"Α.Φ.Μ.\", dbcompany.doyId AS\"Νο Δ.Ο.Υ.\" ,doy.doyname AS\"ονομασία Δ.Ο.Υ.\",  active, bank.bankBranch AS\"τράπεζα\",dbcompany.bankAccount AS\"λογαριασμός τραπεζας\",dbcompany.bankAccountIBAN AS\"ΙΒΑΝ\" FROM dbcompany LEFT JOIN doy ON dbcompany.doyId=doy.doyId LEFT JOIN bank ON dbcompany.bankId=bank.bankId ORDER BY dbcompany.companyName"  ,"SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.companyName AS\"τίτλος\", dbcompany.companyVatNo AS\"Α.Φ.Μ.\", dbcompany.doyId,  dbcompany.bank , dbcompany.bankAccount , dbcompany.bankAccountIBAN, dbcompany.notes" ," FROM dbcompany" ,"",fieldsForSumsDbCompany,dbCompanyDBFields ,"εταιρίες","DORM","Νο εταιρίας","dbCompanyId",dbCompanyErs,null,"εταιρίας", "εταιριών",entityPanelDbCompany,null,fieldsOnTitleDbCompany,fieldsOnTitleCaptionDbCompany,companyFieldsOrderby,2,FIELD_VALIDATION_AFM,globalYearPlusOne);
        EntityMenu empg = new EntityMenu();
        empg.setEntityParameter(pg,ICO_TABLE16);
        empg.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempg = new DataTreeNode(empg);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempg);
       */
        
        //---------------------------------------------------------
        // dbyear
        EntityFilterSettings[] dbYearErs = new EntityFilterSettings[1]; 
        dbYearErs[0]=new EntityFilterSettings("έτος","","string","equals","dbyear","dbyear",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        int[] yearFieldsOrderby ={2,1};
        String[] fieldsForSumsDbYear=null;                                     //  , dbyear.dbcompanyid AS\"Νο εταιρίας\"  ,dbcompany.companyName AS\"εταιρία\"      , dbcompany        dbyear.dbCompanyId=dbcompany.dbCompanyId AND 
        EntityParameter ph = new EntityParameter("dbyear", "SELECT dbyear.dbYearId AS \"Νο χρήσης\", dbyear.dbyear AS\"χρήση\" FROM dbyear  WHERE dbyear.dbcompanyid='"+VariablesGlobal.globalCompanyId+"' ORDER BY  dbyear.dbYearId" ,"SELECT dbYearId AS\"Νο χρήσης\", dbyear AS\"χρήση\", dbcompanyid AS\"Νο εταιρίας\"","FROM dbyear","",fieldsForSumsDbYear,dbYearDBFields ,"έτη/αποστολές", "DORM","Νο χρήσης","dbYearId", dbYearErs,null,"χρήσης","χρήσεων",entityPanelDbyear,null,fieldsOnTitleDbyear,fieldsOnTitleCaptionDbyear,yearFieldsOrderby,-1,-1,globalYearPlusOne);  //SELECT dbyear.dbYearId AS "Νο χρήσης", dbyear.dbyear AS"χρήση", Count(aa) AS "πλήθος περίοδων" FROM dbyear, frvyeardelivery  WHERE dbyear.dbYearId = frvyeardelivery.dbYearId AND dbyear.dbcompanyid='1' GROUP BY dbyear.dbYearId ORDER BY dbyear.dbYearId
        EntityMenu emph = new EntityMenu();
        emph.setEntityParameter(ph,ICO_TABLE16);
        emph.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeemph = new DataTreeNode(emph);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeemph);
         
        //------------------------------------------------------------
        
     /*   int[] dbYearDeliveryFieldsOrderby ={1};
        String[] fieldsForSumsDbYearDelivery=null;
        EntityParameter pj = new EntityParameter("frvyeardelivery", "SELECT dbYearDeliveryId AS \"Νο αποστολής\", description  AS \"περιγραφή\" FROM frvyeardelivery WHERE dbcompanyid = "+VariablesGlobal.globalCompanyId+" AND dbYearId = "+VariablesGlobal.globalYearId+"","SELECT deliveryId AS \"νο αποστολής\", description  AS \"περιγραφή\" ","FROM frvyeardelivery","",fieldsForSumsDbYearDelivery,dbYearDeliveryDBFields ,"αποστολές","DORM","Νο αποστολής","dbYearDeliveryId",null,null,"αποστολής", "αποστολών",entityPanelDbYearDelivery,null,fieldsOnTitleDbYearDelivery,fieldsOnTitleCaptionDbYearDelivery,dbYearDeliveryFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empj = new EntityMenu();
        empj.setEntityParameter(pj,ICO_TABLE16);
        empj.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempj = new DataTreeNode(empj);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempj);        
     */
        

        
        //---------------------------------------------------------
        
       /* int[] userFieldsOrderby ={2};
        String[] fieldsForSumsDbUser=null;
        EntityParameter pk = new EntityParameter("dbuser", "SELECT userId AS\"Νο χρήστη\", username AS\"όνομα χρήστη\", password, nameOfUser AS\"πλήρες όνομα χρήστη\" FROM dbuser"  ,"SELECT userId AS\"Νο χρήστη\", username AS\"ονομασία χρήστη\", password, nameOfUser AS\"πλήρες όνομα χρήστη\"","FROM dbuser","",fieldsForSumsDbUser,dbuserDBFields ,"χρήστες", "DORM","Νο χρήστη","userId", null,null,"χρήστη","χρηστών",entityPanelDbuser,null,fieldsOnTitleDbuser,fieldsOnTitleCaptionDbuser,userFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empk = new EntityMenu();
        empk.setEntityParameter(pk,ICO_TABLE16);
        empk.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempk = new DataTreeNode(empk);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempk);
      */  
        //--------------------------------------------------------

        EntityParameter[] pz = {pa,pb,pc,pe};
        EntityMenu[] empza = {empa,empb,empc,empe};
        //empz.setEntityParameter(pz,ICO_TABLE16);
        //empza.setEntityType(ENTITY_TYPE_PARAMETER);        
        
        
        EntityManyDataManyRec pza = new EntityManyDataManyRec("tables", "διάφοροι πίνακες",pz,empza);
        EntityMenu empz = new EntityMenu();
        empz.setEntityManyDataManyRec(pza,ICO_TABLE16);
        empz.setEntityType(ENTITY_TYPE_DATAMANY_PARAMETERS);
        DataTreeNode nodeempz = new DataTreeNode(empz);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempz); 
        
    }


    public void addReportSettings()  // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
    {

      DataTreeNode nodeCat = null;
      DataTreeNode nodeReports = null;

         //sub categories
         EntityMenu emCat1 = new EntityMenu();
         emCat1.setEntityCategory(REPORT_CAT_ECONOMIC,ENTITY_TYPE_CATEGORY2,ICO_MENUCAT_REPORT);
        // System.out.println("EntityData.addReportSettings "+REPORTS_CAT_ARRAY[r]+" "+r);
         nodeCat = new DataTreeNode(emCat1);
         nodeRoot.getChildFromCaption(REPORTS).addChild(nodeCat);
         nodeReports = nodeRoot.getChildFromCaption(REPORTS);
         //System.out.println("EntityData.addReportSettings "+nodeCat+" . "+nodeRoot.getChildFromCaption(REPORTS).getChildFromCaption(REPORTS_CAT_ARRAY[r]));
       
         
         EntityMenu emCat2 = new EntityMenu();
         emCat2.setEntityCategory(REPORT_CAT_APPLICATIONS,ENTITY_TYPE_CATEGORY2,ICO_MENUCAT_REPORT);
        // System.out.println("EntityData.addReportSettings "+REPORTS_CAT_ARRAY[r]+" "+r);
         nodeCat = new DataTreeNode(emCat2);
         nodeRoot.getChildFromCaption(REPORTS).addChild(nodeCat);
         nodeReports = nodeRoot.getChildFromCaption(REPORTS);         

         
   /*      EntityMenu emCat3 = new EntityMenu();
         emCat3.setEntityCategory(REPORT_CAT_INFORMATIVE,ENTITY_TYPE_CATEGORY2,ICO_MENUCAT_REPORT);
        // System.out.println("EntityData.addReportSettings "+REPORTS_CAT_ARRAY[r]+" "+r);
         nodeCat = new DataTreeNode(emCat3);
         nodeRoot.getChildFromCaption(REPORTS).addChild(nodeCat);
         nodeReports = nodeRoot.getChildFromCaption(REPORTS);       
   */      
         
       //-----------------------------------  REPORT_CAT_ECONOMIC -----------------	
       EntityFilterSettings[] applicationlineErs = new EntityFilterSettings[14];   
       applicationlineErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","frvapplicationheader",VariablesGlobal.globalCompanyId,0,-1,-1,FIELD_OBLIGATORY);
       applicationlineErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbyearId","dbyear","frvapplicationheader", VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
       applicationlineErs[2]=new EntityFilterSettings("αποστολή","checkboxTable","string","equals","dbyeardeliveryId","frvyeardelivery","frvapplicationheader","",0,-1,-1,FIELD_NOCOMPLETION);        
       applicationlineErs[3]=new EntityFilterSettings("Νο αγρότη","lookup","string","fromto","farmerId","frvfarmer","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       applicationlineErs[4]=new EntityFilterSettings("επίθετο","","string","equals","surname","frvfarmer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       applicationlineErs[5]=new EntityFilterSettings("αγρότης","checkboxTable","string","","farmerId","frvfarmer","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       applicationlineErs[6]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","frvfarmer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       applicationlineErs[7]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","frvtown","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       applicationlineErs[8]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       applicationlineErs[9]=new EntityFilterSettings("ημ/νία αίτησης","","date","fromto","dateOfApplication","frvapplicationheader",null,"",2,0,-1,FIELD_NOCOMPLETION);
       applicationlineErs[10]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       applicationlineErs[11]=new EntityFilterSettings("ποσό παρ/κών","","double","fromto","value","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       applicationlineErs[12]=new EntityFilterSettings("ποσό επιστροφής","","double","fromto","valueReturn","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       applicationlineErs[13]=new EntityFilterSettings("ποσό κράτησης","","double","fromto","payment","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
      // applicationlineErs[14]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","frvbuyer","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);
      // applicationlineErs[15]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","frvproduct","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);
      // applicationlineErs[16]=new EntityFilterSettings("είδος προϊόντος","checkboxTable","string","","productTypeId","frvproducttype","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);
      // applicationlineErs[17]=new EntityFilterSettings("τύπος παραστατικού","checkboxTable","string","","invoiceTypeId","frvinvoicetype","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);

       
       
       EntityGroupOfComps[] applicationlineEntityGroupOfComps = new EntityGroupOfComps[3];
       applicationlineEntityGroupOfComps[0] = new EntityGroupOfComps("εταιρία/χρήση/αποστολή",6,0,FONT_SIZE_NOT_SET);
       applicationlineEntityGroupOfComps[1] = new EntityGroupOfComps("αγρότης",4,0,FONT_SIZE_NOT_SET);
       applicationlineEntityGroupOfComps[2] = new EntityGroupOfComps("άιτηση",4,0,FONT_SIZE_NOT_SET);
      // applicationlineEntityGroupOfComps[3] = new EntityGroupOfComps("παραστατικά",4,0);
       /*
       String[] applicationlineCaptionOfGroupOfComps = {"αποστολή","αγρότης","άιτηση"};
       int[] applicationlineColumnsOfObjects = {6,4,4};
       int[] applicationlineIncludedInGroupsOfPanels = {-1,-1,-1};
       
       EntityGroupOfComps applicationlineEntityGroupOfComps = new EntityGroupOfComps(applicationlineCaptionOfGroupOfComps,applicationlineColumnsOfObjects,applicationlineIncludedInGroupsOfPanels);       
       */
       int[] applicationlinesSelected =null;//{1,2,3,4,0,0,0,0,0,0,11,12,0,14,};        
       int[] applicationlineFieldOrderby = {3,4,2};
       boolean[] boolSettingsFarmer = {true,true,true,true};
       boolean[] boolSettingsInvoice = {false,false,true,true};
       //EntityQuery[] applicationlineEntQuery = new EntityQuery[1]; 
       //applicationlineEntQuery[0]= new EntityQuery("SELECT frvfarmer.farmerId,frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name,frvapplicationline.* FROM frvapplicationline, frvfarmer, frvapplicationheader WHERE frvapplicationheader.farmerId = frvfarmer.farmerId AND frvapplicationline.farmerId = frvfarmer.farmerId",false,0,null,null,null,null,null);
  
       //EntityQuery[] deliveryCheckEntQuery = new EntityQuery[1]; 
       //deliveryCheckEntQuery[0]= new EntityQuery("SELECT frvapplicationline.farmerId,frvbuyer.buyerTitle , frvapplicationline.applicationlineTypeId,frvapplicationline.applicationlineNo,frvapplicationline.date,frvapplicationline.dbyear, frvapplicationline.productId, frvapplicationline.value, frvapplicationline.valueReturn FROM frvapplicationline, frvfarmer, frvapplicationheader, frvbuyer WHERE frvbuyer.buyerId=frvapplicationline.buyerId AND frvapplicationline.farmerId = frvfarmer.farmerId AND frvapplicationheader.farmerId=frvfarmer.farmerId AND frvapplicationheader.farmerId=frvapplicationline.farmerId ORDER BY frvfarmer.surname,frvfarmer.name",false,0,null,null,null,null,null);
        
       // same as entityInfoMany the read only of list
       //String deliveryCheckHeaderEntQuery="SELECT f.farmerId AS\"Νο αγρότη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.farmerAfm AS\"Α.Φ.Μ.\", permanent AS \"υπολ\" , d.dateOfApplication AS \"ημ/νια αίτησης\" , COUNT(i.value) AS \"πλήθος\", SUM(i.value) AS \"αξία\", SUM(retValueAccordingToType(1, i.productTypeId, i.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, i.productTypeId, i.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, i.productTypeId, i.valueReturn)) AS \"κατ 3\", d.valueReturn AS \"συν επιστρ\", d.payment AS \"κράτηση\" FROM frvapplicationheader d, frvfarmer f, frvapplicationline i WHERE i.farmerId = f.farmerId AND d.farmerId = f.farmerId AND i.deliveryId = d.deliveryId AND i.dbyear=d.dbyear AND i.dbCompanyId=d.dbCompanyId GROUP BY f.farmerId, d.permanent, d.dateOfApplication ORDER BY f.surname, f.name";
       
       EntityReportBandField[] erbfApplicationHeader = new EntityReportBandField[9];
       erbfApplicationHeader[0]=new EntityReportBandField("frvapplicationheader","applicationHeaderId","applicationHeaderId","java.lang.Integer",4,true,"",null);
       erbfApplicationHeader[1]=new EntityReportBandField("frvapplicationheader","dbYearDeliveryId","αποστολή","java.lang.Integer",4,true,"",null);
       erbfApplicationHeader[2]=new EntityReportBandField("frvapplicationheader","dbCompanyId","dbCompanyId","java.lang.String",4,true,"",null);
       erbfApplicationHeader[3]=new EntityReportBandField("frvapplicationheader","farmerId","farmerId","java.lang.Integer",4,true,"",null);
       erbfApplicationHeader[4]=new EntityReportBandField("frvapplicationheader","dbYearId","dbYearId","java.lang.Integer",4,true,"",null);
       erbfApplicationHeader[5]=new EntityReportBandField("frvapplicationheader","dateOfApplication","ημ/νία αίτησης","java.sql.Date",12,true,"",null);
       erbfApplicationHeader[6]=new EntityReportBandField("frvapplicationheader","invcount","invcount","java.lang.Double",8,true,"",null);
       erbfApplicationHeader[7]=new EntityReportBandField("frvapplicationheader","value","value","java.lang.Double",10,true,"",null);
       erbfApplicationHeader[8]=new EntityReportBandField("frvapplicationheader","valueReturn","επιστροφή","java.lang.Double",10,true,"",null);       
       
       EntityReportBandField[] erbfFarmer = new EntityReportBandField[5];
       erbfFarmer[0]=new EntityReportBandField("frvfarmer","farmerId","farmerId","java.lang.Integer",4,true,"",null);
       erbfFarmer[1]=new EntityReportBandField("frvfarmer","farmerAfm","farmerAfm","java.lang.String",15,true,"",null);
       erbfFarmer[2]=new EntityReportBandField("frvfarmer","surname","surname","java.lang.String",35,true,"",null);
       erbfFarmer[3]=new EntityReportBandField("frvfarmer","name","name","java.lang.String",35,true,"",null);
       erbfFarmer[4]=new EntityReportBandField("frvfarmer","fathername","fathername","java.lang.String",25,true,"",null);

       //EntityReportBand[] documentsEntityReportBand = new EntityReportBand[1];
       
           /*  public EntityReportBand(String nameIn,  String captionIn, String tableNameIn ,EntityReportBandField[] entityReportBandFieldsIn,String sqlGroupByFieldIn, int typeIn,
              boolean [] boolSettingsIn)*/
           int[] fieldsOrderbyFarmer = {3,4,5,2,1};
           int[] fieldsOrderbyApplication = {2,6,1};
 
       EntityReportBand[] farmersEntityReportBand = new EntityReportBand[2];
       farmersEntityReportBand[0] = new EntityReportBand("frvfarmer","αγρότες","frvfarmer",erbfFarmer,fieldsOrderbyFarmer,"farmerId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsFarmer,entityPanelFarmer,fieldsOnTitleFarmer,fieldsOnTitleCaptionFarmer);//,documentsEntityReportBand);  // header
       farmersEntityReportBand[1] = new EntityReportBand("frvapplicationheader","αίτηση","frvapplicationheader",erbfApplicationHeader,fieldsOrderbyApplication,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsInvoice,entityPanelApplication,fieldsOnTitleApplication, fieldsOnTitleCaptionApplication);  // many
       


       boolean[] boolSettingsReportInvoice = {true,false,true,true,false};
 //      int[] applicationlineCheckFieldOrderby = {1,2,3};
       int[] intReportSettingsInvoice= {0,0,0,0};
       EntityReport ra = new EntityReport("rptControlReport",REPORT_CAT_ECONOMIC,farmersEntityReportBand,"SELECT * FROM  frvfarmer, frvapplicationheader WHERE frvapplicationheader.farmerId = frvfarmer.farmerId",""/*ORDER BY*/,"ODMR","κατάσταση ελέγχου","έτους χρήσης "+VariablesGlobal.globalYearDescr+",  αποστολή",applicationlineErs,applicationlineEntityGroupOfComps,applicationlinesSelected, null,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne) ;//    AND a.farmerId=i.farmerId   AND a.dbyear=i.dbyear  AND a.deliveryId=i.deliveryId
       
       // EntityReport ra = new EntityReport("frvapplicationline",REPORT_CAT_ECONOMIC,null,applicationlineEntQuery,null,null,"ODMR","κατάσταση ελέγχου","",applicationlineErs,applicationlineEntityGroupOfComps,applicationlinesSelected, null,applicationlineFieldOrderby) ;
    //EntityReport rb = new EntityReport("rptBook",REPORT_CAT_ECONOMIC,invEntityReportBand,"SELECT *  FROM frvapplicationline, frvfarmer, frvapplicationheader WHERE frvapplicationheader.farmerId = frvfarmer.farmerId AND frvapplicationheader.applicationHeaderId = frvapplicationline.applicationHeaderId","GROUP BY frvapplicationheader.applicationHeaderId"/* ORDER BY frvapplicationheader.applicationheaderId"*/,"ODMR","βιβλίο μεταγραφής",                                                                                                                                                                                                                                                                                                                                                                                                                           "subtitle",paymentErs,applicationlineReturnEntityGroupOfComps,applicationlinesSelected, null,applicationlineFieldOrderby,intReportSettingsInvoic,boolSettingsReportInvoic,globalYearPlusOne);

       EntityMenu emra = new EntityMenu();
        emra.setEntityReport(ra,ICO_PRINT_PREVIEW16);
        emra.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemra = new DataTreeNode(emra);
        nodeReports.getChildFromCaption(REPORT_CAT_ECONOMIC).addChild(nodeemra);
//---------------------------------------------------------
       EntityFilterSettings[] controlErs = new EntityFilterSettings[18];   
       controlErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","frvapplicationheader",VariablesGlobal.globalCompanyId,0,-1,-1,FIELD_OBLIGATORY);
       controlErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbyearId","dbyear","frvapplicationheader", VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
       controlErs[2]=new EntityFilterSettings("αποστολή","checkboxTable","string","equals","dbyeardeliveryId","frvyeardelivery","frvapplicationheader","",0,-1,-1,FIELD_NOCOMPLETION);        
       controlErs[3]=new EntityFilterSettings("Νο αγρότη","lookup","string","fromto","farmerId","frvfarmer","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       controlErs[4]=new EntityFilterSettings("επίθετο","","string","equals","surname","frvfarmer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       controlErs[5]=new EntityFilterSettings("αγρότης","checkboxTable","string","","farmerId","frvfarmer","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       controlErs[6]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","frvfarmer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       controlErs[7]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","frvtown","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       controlErs[8]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       controlErs[9]=new EntityFilterSettings("ημ/νία αίτησης","","date","fromto","dateOfApplication","frvapplicationheader",null,"",2,0,-1,FIELD_NOCOMPLETION);
       controlErs[10]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       controlErs[11]=new EntityFilterSettings("ποσό παρ/κών","","double","fromto","value","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       controlErs[12]=new EntityFilterSettings("ποσό επιστροφής","","double","fromto","valueReturn","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       controlErs[13]=new EntityFilterSettings("ποσό κράτησης","","double","fromto","payment","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       controlErs[14]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","frvbuyer","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);
       controlErs[15]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","frvproduct","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);
       controlErs[16]=new EntityFilterSettings("είδος προϊόντος","checkboxTable","string","","productTypeId","frvproducttype","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);
       controlErs[17]=new EntityFilterSettings("τύπος παραστατικού","checkboxTable","string","","invoiceTypeId","frvinvoicetype","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);

       
       
       EntityGroupOfComps[] controlEntityGroupOfComps = new EntityGroupOfComps[4];
       controlEntityGroupOfComps[0] = new EntityGroupOfComps("εταιρία/χρήση/αποστολή",6,0,FONT_SIZE_NOT_SET);
       controlEntityGroupOfComps[1] = new EntityGroupOfComps("αγρότης",4,0,FONT_SIZE_NOT_SET);
       controlEntityGroupOfComps[2] = new EntityGroupOfComps("άιτηση",4,0,FONT_SIZE_NOT_SET);
       controlEntityGroupOfComps[3] = new EntityGroupOfComps("παραστατικά",4,0,FONT_SIZE_NOT_SET);
       /*
       String[] applicationlineCaptionOfGroupOfComps = {"αποστολή","αγρότης","άιτηση"};
       int[] applicationlineColumnsOfObjects = {6,4,4};
       int[] applicationlineIncludedInGroupsOfPanels = {-1,-1,-1};
       
       EntityGroupOfComps applicationlineEntityGroupOfComps = new EntityGroupOfComps(applicationlineCaptionOfGroupOfComps,applicationlineColumnsOfObjects,applicationlineIncludedInGroupsOfPanels);       
       */
       //int[] controlSelected =null;//{1,2,3,4,0,0,0,0,0,0,11,12,0,14,};        
       //int[] controlFieldOrderby = {3,4,2};
       boolean[] boolSettingsFarmerControl = {true,true,true,true};
       boolean[] boolSettingsInvoiceControl = {false,false,true,true};
       boolean[] boolSettingsAppLineControl = {true,true,true,true};
       
       
       EntityReportBandField[] erbfApplicationLineCtrl = new EntityReportBandField[8];
       erbfApplicationLineCtrl[0]=new EntityReportBandField("frvapplicationline","applicationHeaderId","applicationHeaderId","java.lang.Integer",8,true,"",null);
       erbfApplicationLineCtrl[1]=new EntityReportBandField("frvapplicationline","applicationLineId","applicationLineId","java.lang.Integer",8,true,"",null);
       erbfApplicationLineCtrl[2]=new EntityReportBandField("frvapplicationline","inc","α/α","java.lang.Integer",8,true,"",null);
       erbfApplicationLineCtrl[3]=new EntityReportBandField("frvapplicationline","buyerId","buyerId","java.lang.Integer",8,true,"",null);
       erbfApplicationLineCtrl[4]=new EntityReportBandField("frvapplicationline","invoiceNo","invoiceNo","java.lang.String",15,true,"",null);
       erbfApplicationLineCtrl[5]=new EntityReportBandField("frvapplicationline","dateInv","ημερομηνία","java.sql.Date",12,true,"",null);
       erbfApplicationLineCtrl[6]=new EntityReportBandField("frvapplicationline","productId","productId","java.lang.Integer",4,true,"",null);
       erbfApplicationLineCtrl[7]=new EntityReportBandField("frvapplicationline","value","αξία παρ/κού","java.lang.Double",12,true,"",null);  // 'value' has the same name in both tables header and line
       
        
       EntityReportBandField[] erbfApplicationHeaderCtrl = new EntityReportBandField[13];
       erbfApplicationHeaderCtrl[0]=new EntityReportBandField("frvapplicationheader","applicationHeaderId","applicationHeaderId","java.lang.Integer",8,true,"",null);
       erbfApplicationHeaderCtrl[1]=new EntityReportBandField("frvapplicationheader","dbYearDeliveryId","αποστολή","java.lang.Integer",8,true,"",null);
       erbfApplicationHeaderCtrl[2]=new EntityReportBandField("frvapplicationheader","dbCompanyId","dbCompanyId","java.lang.String",8,true,"",null);
       erbfApplicationHeaderCtrl[3]=new EntityReportBandField("frvapplicationheader","farmerId","farmerId","java.lang.Integer",8,true,"",null);
       //erbfApplicationHeaderCtrl[4]=new EntityReportBandField("frvfarmer","farmerId","farmerId","java.lang.Integer",8,true,"",null);
       erbfApplicationHeaderCtrl[4]=new EntityReportBandField("frvfarmer","farmerAfm","farmerAfm","java.lang.String",10,true,"",null);
       erbfApplicationHeaderCtrl[5]=new EntityReportBandField("frvfarmer","surname","επίθετο","java.lang.String",30,true,"",null);
       erbfApplicationHeaderCtrl[6]=new EntityReportBandField("frvfarmer","name","όνομα","java.lang.String",24,true,"",null);
       erbfApplicationHeaderCtrl[7]=new EntityReportBandField("frvfarmer","fathername","πατρώνυμο","java.lang.String",19,true,"",null);       
       erbfApplicationHeaderCtrl[8]=new EntityReportBandField("frvapplicationheader","dbYearId","dbYearId","java.lang.Integer",4,true,"",null);
       erbfApplicationHeaderCtrl[9]=new EntityReportBandField("frvapplicationheader","dateOfApplication","ημ/νία αίτησης","java.sql.Date",12,true,"",null);
       erbfApplicationHeaderCtrl[10]=new EntityReportBandField("frvapplicationheader","invcount","invcount","java.lang.Double",8,true,"",null);
       erbfApplicationHeaderCtrl[11]=new EntityReportBandField("frvapplicationheader","value","σύνολο αξίας","java.lang.Double",12,true,"",null); // 'value' has the same name in both tables header and line
       erbfApplicationHeaderCtrl[12]=new EntityReportBandField("frvapplicationheader","valueReturn","επιστροφή","java.lang.Double",12,true,"",null);       
       
       /*EntityReportBandField[] erbfFarmerCtrl = new EntityReportBandField[5];
       erbfFarmerCtrl[0]=new EntityReportBandField("frvfarmer","farmerId","farmerId","java.lang.Integer",8,true,"",null);
       erbfFarmerCtrl[1]=new EntityReportBandField("frvfarmer","farmerAfm","farmerAfm","java.lang.String",12,true,"",null);
       erbfFarmerCtrl[2]=new EntityReportBandField("frvfarmer","surname","surname","java.lang.String",35,true,"",null);
       erbfFarmerCtrl[3]=new EntityReportBandField("frvfarmer","name","name","java.lang.String",35,true,"",null);
       erbfFarmerCtrl[4]=new EntityReportBandField("frvfarmer","fathername","fathername","java.lang.String",25,true,"",null);*/

       
       //EntityReportBand[] appLineControlEntityReportBand = new EntityReportBand[1];
       //appLineControlEntityReportBand[0] = new EntityReportBand("frvapplicationline","παραστατικά","frvapplicationline",erbfApplicationLineCtrl,/*"SELECT i.inc AS\"α/α\", i.farmerId,i.deliveryId,b.buyerTitle AS\"αγοραστής\", it.abbreviation  AS\"παρ/κο\", i.applicationlineNo  AS\"αριθμός\",i.date  AS\"ημερομηνία\",i.dbyear, p.productName  AS\"προϊόν\", i.value  AS\"αξία\", i.valueReturn  AS\"επιστροφή\" FROM frvapplicationline i, frvfarmer f, frvapplicationheader a, frvbuyer b, frvproduct p, frvproducttype pt, frvinvoicetype it WHERE i.productId=p.productId AND pt.productTypeId=p.productTypeId AND b.buyerId=i.buyerId AND i.farmerId = f.farmerId AND a.farmerId=f.farmerId AND a.farmerId=i.farmerId AND a.dbyear=i.dbyear AND a.dbCompanyId=i.dbCompanyId AND a.deliveryId=i.deliveryId AND i.invoiceTypeId=it.invoiceTypeId"*/"applicationLineId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsAppLineControl);  // many
       
       
       //EntityReportBand[] documentsControlEntityReportBand = new EntityReportBand[1];
       //documentsControlEntityReportBand[0] = new EntityReportBand("frvapplicationheader","αίτηση","frvapplicationheader",erbfApplicationHeaderCtrl,/*"SELECT i.inc AS\"α/α\", i.farmerId,i.deliveryId,b.buyerTitle AS\"αγοραστής\", it.abbreviation  AS\"παρ/κο\", i.applicationlineNo  AS\"αριθμός\",i.date  AS\"ημερομηνία\",i.dbyear, p.productName  AS\"προϊόν\", i.value  AS\"αξία\", i.valueReturn  AS\"επιστροφή\" FROM frvapplicationline i, frvfarmer f, frvapplicationheader a, frvbuyer b, frvproduct p, frvproducttype pt, frvinvoicetype it WHERE i.productId=p.productId AND pt.productTypeId=p.productTypeId AND b.buyerId=i.buyerId AND i.farmerId = f.farmerId AND a.farmerId=f.farmerId AND a.farmerId=i.farmerId AND a.dbyear=i.dbyear AND a.dbCompanyId=i.dbCompanyId AND a.deliveryId=i.deliveryId AND i.invoiceTypeId=it.invoiceTypeId"*/"farmerId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsInvoiceControl,appLineControlEntityReportBand);  // many
           /*  public EntityReportBand(String nameIn,  String captionIn, String tableNameIn ,EntityReportBandField[] entityReportBandFieldsIn,String sqlGroupByFieldIn, int typeIn,
              boolean [] boolSettingsIn)*/
 
           
           int[] fieldsOrderbyApplicationHeaderCtrl = {6,7,8,5,4};
           int[] fieldsOrderbyApplicationLineCtrl = {3};
           
       EntityReportBand[] controlEntityReportBand = new EntityReportBand[2];
      // controlEntityReportBand[0] = new EntityReportBand("frvfarmer","αγρότες","frvfarmer",erbfFarmerCtrl/*farmerDBFields*/,null/*checkFieldOrderby*/,"farmerId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsFarmerControl,entityPanelFarmer,fieldsOnTitleFarmer,fieldsOnTitleCaptionFarmer);//,documentsControlEntityReportBand);  // header
        controlEntityReportBand[0] = new EntityReportBand("frvapplicationheader","αίτηση","frvapplicationheader",erbfApplicationHeaderCtrl,fieldsOrderbyApplicationHeaderCtrl,"applicationHeaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsInvoiceControl,entityPanelApplication,fieldsOnTitleApplication, fieldsOnTitleCaptionApplication);//,appLineControlEntityReportBand);  // many
       controlEntityReportBand[1] = new EntityReportBand("frvapplicationline","παραστατικά","frvapplicationline",erbfApplicationLineCtrl,fieldsOrderbyApplicationLineCtrl,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsAppLineControl,null,null,null);  // many
        
    /*   EntityQuery[] retPerTownEntityQuery = new EntityQuery[1];
       retPerTownEntityQuery[0]= new EntityQuery("SELECT k.farmerId, k.surname, k.name, k.farmerAfm, k.townId, k.doyId, COUNT(farmerId),SUM(value) FROM (SELECT frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name, frvfarmer.townId, frvtown.townName, frvfarmer.doyId, frvapplicationline.* FROM frvapplicationline,"+
       "frvfarmer, frvtown WHERE frvapplicationline.farmerId = frvfarmer.farmerId AND frvtown.townId = frvfarmer.townId AND dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+
       " AND dbCompanyId="+VariablesGlobal.globalCompanyId+") k GROUP BY k.farmerId ORDER BY townName",false,0,null,null,null,null,null);
       	
       EntityReport rd = new EntityReport("return",REPORT_CAT_INFORMATIVE,"frvfarmer",retPerTownEntityQuery,"SELECT townId, townName FROM frvtown","townId","TDMR","επιστροφές ανα πόλη/χωριό","έτους χρήσης "+VariablesGlobal.globalYear+", "+VariablesGlobal.globalDeliveryId+"η αποστολή",deliveryErs,deliveryEntityGroupOfComps,null, null,deliveryOrderby);
*/

       boolean[] boolSettingsReportControl = {true,false,true,true,false};
//       int[] applicationlineCheckFieldOrderby = {1,2,3};
       int[] intReportSettingsInvoiceControl= {0,0,0,0};                                                          
       EntityReport rc = new EntityReport("rptControlAnalyticalReport",REPORT_CAT_ECONOMIC,controlEntityReportBand,"SELECT * FROM frvfarmer, frvapplicationheader, frvapplicationline, frvbuyer, frvproduct, frvproducttype, frvinvoicetype WHERE frvapplicationline.productId=frvproduct.productId AND frvproducttype.productTypeId=frvproduct.productTypeId AND frvbuyer.buyerId=frvapplicationline.buyerId AND frvapplicationheader.farmerId = frvfarmer.farmerId AND frvapplicationheader.farmerId=frvfarmer.farmerId  AND frvapplicationheader.dbCompanyId=frvapplicationline.dbCompanyId AND frvapplicationline.invoiceTypeId=frvinvoicetype.invoiceTypeId AND frvapplicationline.applicationHeaderId = frvapplicationheader.applicationHeaderId AND frvapplicationheader.DBCOMPANYID = frvapplicationline.DBCOMPANYID AND frvapplicationheader.DBCOMPANYID = "+VariablesGlobal.globalCompanyId+"",//+"ORDER BY frvapplicationheader.FARMERID"
       /*ORDER BY f.farmerId, i.applicationHeaderId"*/"","ODMR","αναλυτική κατάσταση ελέγχου","έτους χρήσης "+VariablesGlobal.globalYearDescr+",  αποστολή",controlErs,controlEntityGroupOfComps,null, null,"","","",intReportSettingsInvoiceControl,boolSettingsReportControl,"");//,globalYearPlusOne) ;
          // EntityReport ra = new EntityReport("frvapplicationline",REPORT_CAT_ECONOMIC,null,applicationlineEntQuery,null,null,"ODMR","κατάσταση ελέγχου","",applicationlineErs,applicationlineEntityGroupOfComps,applicationlinesSelected, null,applicationlineFieldOrderby) ;
    //EntityReport rb = new EntityReport("rptBook",REPORT_CAT_ECONOMIC,invEntityReportBand,"SELECT *  FROM frvapplicationline, frvfarmer, frvapplicationheader WHERE frvapplicationheader.farmerId = frvfarmer.farmerId AND frvapplicationheader.applicationHeaderId = frvapplicationline.applicationHeaderId","GROUP BY frvapplicationheader.applicationHeaderId"/* ORDER BY frvapplicationheader.applicationheaderId"*/,"ODMR","βιβλίο μεταγραφής",                                                                                                                                                                                                                                                                                                                                                                                                                           "subtitle",paymentErs,applicationlineReturnEntityGroupOfComps,applicationlinesSelected, null,applicationlineFieldOrderby,intReportSettingsInvoic,boolSettingsReportInvoic,globalYearPlusOne);

       EntityMenu emrc = new EntityMenu();
        emrc.setEntityReport(rc,ICO_PRINT_PREVIEW16);
        emrc.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrc = new DataTreeNode(emrc);
        nodeReports.getChildFromCaption(REPORT_CAT_ECONOMIC).addChild(nodeemrc);        
        

        
        
        
//------------------------------------------------------------        
       EntityGroupOfComps[] applicationlineReturnEntityGroupOfComps = new EntityGroupOfComps[3];
       applicationlineReturnEntityGroupOfComps[0] = new EntityGroupOfComps("εταιρία/χρήση/αποστολή",6,0,FONT_SIZE_NOT_SET);
       applicationlineReturnEntityGroupOfComps[1] = new EntityGroupOfComps("αγρότης",4,0,FONT_SIZE_NOT_SET);
       applicationlineReturnEntityGroupOfComps[2] = new EntityGroupOfComps("άιτηση",4,0,FONT_SIZE_NOT_SET);



 
       EntityFilterSettings[] paymentErs = new EntityFilterSettings[14];   
       paymentErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","frvapplicationheader",VariablesGlobal.globalCompanyId,0,-1,0,FIELD_OBLIGATORY);
       paymentErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbyearId","dbyear","frvapplicationheader", VariablesGlobal.globalYearId,0,0,0,FIELD_OBLIGATORY);
       paymentErs[2]=new EntityFilterSettings("αποστολή","checkboxTable","string","equals","dbYearDeliveryId","frvyeardelivery","frvapplicationheader","",0,-1,-1,FIELD_NOCOMPLETION);  //("αποστολή","","string","equals","deliveryId","dbDelivery",null,VariablesGlobal.globalDeliveryId,0,-1,0);       
       paymentErs[3]=new EntityFilterSettings("Νο αγρότη","lookup","string","fromto","farmerId","frvfarmer","f","",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[4]=new EntityFilterSettings("επίθετο","","string","equals","surname","frvfarmer",null,"",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[5]=new EntityFilterSettings("αγρότης","checkboxTable","string","","farmerId","frvfarmer","frvfarmer","",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[6]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","frvfarmer",null,"",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[7]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","frvtown","frvfarmer","",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[8]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","frvfarmer","",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[9]=new EntityFilterSettings("ημ/νία αίτησης","","date","fromto","dateOfApplication","frvapplicationheader",null,"",2,-1,0,FIELD_NOCOMPLETION);
       paymentErs[10]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","frvapplicationheader",null,"",2,-1,0,FIELD_NOCOMPLETION);
       paymentErs[11]=new EntityFilterSettings("ποσό παρ/κών","","double","fromto","value","frvapplicationheader",null,"",2,-1,0,FIELD_NOCOMPLETION); 
       paymentErs[12]=new EntityFilterSettings("ποσό επιστροφής","","double","fromto","valueReturn","frvapplicationheader",null,"",2,-1,0,FIELD_NOCOMPLETION);
       paymentErs[13]=new EntityFilterSettings("ποσό κράτησης","","double","fromto","payment","frvapplicationheader",null,"",2,-1,0,FIELD_NOCOMPLETION);
 
       
       
      
       
       EntityReportBandField[] erbFields = new EntityReportBandField[13];
       erbFields[0]=new EntityReportBandField("frvfarmer","farmerAfm","farmerAfm","java.lang.String",8,true,"",null);
       erbFields[1]=new EntityReportBandField("frvfarmer","surname","surname","java.lang.String",25,true,"",null);
       erbFields[2]=new EntityReportBandField("frvfarmer","name","name","java.lang.String",25,true,"",null);
       erbFields[3]=new EntityReportBandField("frvfarmer","fathername","fathername","java.lang.String",15,true,"",null);
       erbFields[4]=new EntityReportBandField("frvapplicationheader","applicationHeaderId","applicationHeaderId","java.lang.Integer",8,true,"",null);
       erbFields[5]=new EntityReportBandField("frvapplicationheader","dbYearDeliveryId","αποστολή","java.lang.Integer",8,true,"",null);
       erbFields[6]=new EntityReportBandField("frvapplicationheader","dbCompanyId","dbCompanyId","java.lang.String",8,true,"",null);
       erbFields[7]=new EntityReportBandField("frvapplicationheader","farmerId","farmerId","java.lang.Integer",8,true,"",null);
       erbFields[8]=new EntityReportBandField("frvapplicationheader","dbYearId","dbYearId","java.lang.Integer",8,true,"",null);
       erbFields[9]=new EntityReportBandField("frvapplicationheader","dateOfApplication","ημ/νία αίτησης","java.sql.Date",12,true,"",null);
       erbFields[10]=new EntityReportBandField("frvapplicationheader","invcount","invcount","java.lang.Double",8,true,"",null);
       erbFields[11]=new EntityReportBandField("frvapplicationheader","value","value","java.lang.Double",12,true,"",null); // produces wrong values seems that 'value' is in table header and table line, so we write the table
       erbFields[12]=new EntityReportBandField("frvapplicationheader","valueReturn","επιστροφή","java.lang.Double",12,true,"",null);
               
       /*String tableNameIn, String dbFieldNameIn,String captionIn, String colClassNameIn,int colWidthIn,boolean isVisibleIn,
              String defaultValueIn, EntityDBFieldsCalculation[] fieldsCalculationIn)*/
 
       boolean[] boolSettingsInvoices = {true,true,true,true,true};

           int[] fieldsOrderbyApplicationBook = {6,2,3,4,1,8,10};
            
       //EntityReportGroup[] invEntityReportGroup = new EntityReportGroup[1];
       //invEntityReportGroup[0] = new EntityReportGroup("παραστατικά","SELECT frvfarmer.farmerId,frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name, frvfarmer.fathername, frvapplicationheader.deliveryId, COUNT(frvapplicationline.value), SUM(frvapplicationline.value),  SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"κατ 3\",  frvapplicationheader.valueReturn AS \"συν επιστρ\" FROM frvapplicationline, frvfarmer, frvapplicationheader"+
       //" WHERE frvapplicationheader.farmerId = frvfarmer.farmerId AND frvapplicationline.farmerId = frvfarmer.farmerId AND frvapplicationline.deliveryId = frvapplicationheader.deliveryId AND frvapplicationline.dbyear=frvapplicationheader.dbyear AND frvapplicationline.dbCompanyId=frvapplicationheader.dbCompanyId GROUP BY frvapplicationline.farmerId ,frvapplicationheader.valueReturn, APPLICATION.DELIVERYID","","frvapplicationline",ENTITYREPORT_QUERY_TYPE_MAIN,-1,"", boolSettingsInvoices); 
       EntityReportBand[] invEntityReportBand = new EntityReportBand[1];
       invEntityReportBand[0] = new EntityReportBand("rptBook","βιβλίο μεταγραφής","frvapplicationheader",erbFields,fieldsOrderbyApplicationBook/*checkFieldOrderby*/,null,ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsInvoices,entityPanelApplication,fieldsOnTitleApplication, fieldsOnTitleCaptionApplication);;
       //EntityQuery[] invEntityQuery = new EntityQuery[1]; 
       //invEntityQuery[0]= new EntityQuery("SELECT frvfarmer.farmerId,frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name, frvfarmer.fathername, COUNT(frvapplicationline.value), SUM(frvapplicationline.value),  SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"κατ 3\",  frvapplicationheader.valueReturn AS \"συν επιστρ\" FROM frvapplicationline, frvfarmer, frvapplicationheader"+
       //" WHERE frvapplicationheader.farmerId = frvfarmer.farmerId AND frvapplicationline.farmerId = frvfarmer.farmerId AND frvapplicationline.deliveryId = frvapplicationheader.deliveryId AND frvapplicationline.dbyear=frvapplicationheader.dbyear AND frvapplicationline.dbCompanyId=frvapplicationheader.dbCompanyId GROUP BY frvapplicationline.farmerId", false,0,null,null,null,null,null);
       boolean[] boolSettingsReportInvoic = {true,false,true,true,true};
       int[] intReportSettingsInvoic= {0,0,0,0};
        EntityReport rb = new EntityReport("rptBook",REPORT_CAT_ECONOMIC,invEntityReportBand,"SELECT *  FROM frvfarmer, frvapplicationheader, frvapplicationline WHERE frvapplicationheader.farmerId = frvfarmer.farmerId AND frvapplicationheader.applicationHeaderId = frvapplicationline.applicationHeaderId","GROUP BY frvapplicationheader.applicationHeaderId"/* ORDER BY frvapplicationheader.applicationheaderId"*/,"ODMR","βιβλίο μεταγραφής","subtitle",paymentErs,applicationlineReturnEntityGroupOfComps,applicationlinesSelected, null,"","","",intReportSettingsInvoic,boolSettingsReportInvoic,"");//,globalYearPlusOne);
        EntityMenu emrb = new EntityMenu();
        emrb.setEntityReport(rb,ICO_PRINT_PREVIEW16);
        emrb.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrb = new DataTreeNode(emrb);
        nodeReports.getChildFromCaption(REPORT_CAT_ECONOMIC).addChild(nodeemrb);

   //--------------------------------------------------------------------------------------------
/*
       EntityGroupOfComps[] paymentEntityGroupOfComps = new EntityGroupOfComps[3];
       paymentEntityGroupOfComps[0] = new EntityGroupOfComps("εταιρία/χρήση/αποστολή",6,0);
       paymentEntityGroupOfComps[1] = new EntityGroupOfComps("αγρότης",4,0);
       paymentEntityGroupOfComps[2] = new EntityGroupOfComps("άιτηση",4,0);       

       int[] paymentSelected = null;//{1,2,3,4,0,0,0,0,0,0,11,12,0,14,};        
       int[] paymentFieldOrderby = {3,4,2};
       boolean[] boolSettingsFarmers = {true,true,true,true,true};
       boolean[] boolSettingsReportPayment = {true,true,true,true,true};
       int[] intReportSettingsPayment={0,0,0,0};
       //EntityQuery[] paymentEntityQuery = new EntityQuery[1];/*SUM(frvapplicationline.valueReturn) AS \"επιστροφή\", SUM(frvapplicationline.payment) AS \"κράτηση\",*/
       //paymentEntityQuery[0]= new EntityQuery("SELECT frvfarmer.farmerId,frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name, frvapplicationheader.invcount AS \"πληθ παρ\", frvapplicationheader.value AS \"αξία\",  frvapplicationheader.valueReturn AS \"επιστροφή\", frvapplicationheader.payment AS \"κράτηση\", frvapplicationheader.signature AS \"υπογραφή\"  FROM frvfarmer, frvapplicationheader"+
       //" WHERE frvapplicationheader.farmerId = frvfarmer.farmerId GROUP BY frvapplicationheader.farmerId, frvapplicationheader.invcount", false,0,null,null,null,null,null);
    
  /*     EntityReportGroup[] paymentEntityReportGroup = new EntityReportGroup[1];
       paymentEntityReportGroup[0] = new EntityReportGroup("αγρότες","SELECT frvfarmer.farmerId, frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name, bank.bankBranch AS \"τράπεζα\", frvfarmer.bankAccount AS \"λογαριασμός\", frvapplicationheader.invcount AS \"πληθ παρ\", frvapplicationheader.value AS \"αξία\",  frvapplicationheader.valueReturn AS \"επιστροφή\", frvapplicationheader.payment AS \"κράτηση\",frvapplicationheader.valueReturn - frvapplicationheader.payment AS \"σύνολο\", frvapplicationheader.signature AS \"υπογραφή\"     FROM frvfarmer LEFT JOIN bank ON bank.bankId=frvfarmer.bankId LEFT JOIN frvapplicationheader ON frvfarmer.farmerId=frvapplicationheader.farmerId"+
       " GROUP BY frvfarmer.farmerId, frvapplicationheader.invcount, frvapplicationheader.valueReturn","","frvapplicationheader",ENTITYREPORT_QUERY_TYPE_MAIN,-1,null,boolSettingsFarmers); 

//       EntityReport rc = new EntityReport("rptPayment",REPORT_CAT_ECONOMIC,paymentEntityReportGroup,"ODMR","κατάσταση πληρωμής","",paymentErs,paymentEntityGroupOfComps,paymentSelected, null,paymentFieldOrderby,intReportSettingsPayment,boolSettingsReportPayment,globalYearPlusOne) ;
        EntityMenu emrc = new EntityMenu();
//        emrc.setEntityReport(rc,ICO_PRINT_PREVIEW16);
        emrc.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrc = new DataTreeNode(emrc);
        nodeReports.getChildFromCaption(REPORT_CAT_ECONOMIC).addChild(nodeemrc);*/
       
       // --------------------------------- REPORT_CAT_INFORMATIVE ---------------------
       
       EntityFilterSettings[] deliveryErs = new EntityFilterSettings[5];       
       deliveryErs[0]=new EntityFilterSettings("Νο αγρότη","lookup","string","fromto","farmerId","frvfarmer","f","",-1,-1,1,FIELD_NOCOMPLETION);
       deliveryErs[1]=new EntityFilterSettings("επίθετο","","string","equals","surname","frvfarmer",null,"",-1,-1,1,FIELD_NOCOMPLETION);
       deliveryErs[2]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","frvfarmer",null,"",-1,-1,1,FIELD_NOCOMPLETION);
       deliveryErs[3]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","frvtown","frvtown","",-1,-1,-1,FIELD_NOCOMPLETION);
       deliveryErs[4]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","frvfarmer","",-1,-1,1,FIELD_NOCOMPLETION);

       
       EntityGroupOfComps[] deliveryEntityGroupOfComps = null;       

       
       int[]deliveryOrderby = {2,1};  
       	boolean[] boolSettingsTown = {true,true,true,true};    
        boolean[] boolSettingsFarme = {true,true,true,true}; 
        boolean[] boolSettingsReportFarm = {true,true,true,true,true}; 	
       int[] intSettingsReportFarm={0,0,0,0};
       //applicationlineErs[2]=new EntityFilterSettings("πόλη/χωριό","string","equals","name","frvtown");
      /* listModel.addElement(new EntityReport("frvapplicationheader","SELECT frvfarmer.* FROM frvfarmer, frvapplicationheader "+
       "WHERE frvfarmer.farmerId=frvapplicationheader.farmerId AND dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+
       " AND dbCompanyId="+VariablesGlobal.globalCompanyId,"ODMR","αγρότες που έχουν επιστροφές",deliveryErs,null, true) );
       */

       //EntityQuery[] retPerTownEntityQuery = new EntityQuery[1];
       //retPerTownEntityQuery[0]= new EntityQuery("SELECT k.farmerId, k.surname, k.name, k.farmerAfm, k.townId, k.doyId, COUNT(farmerId),SUM(value) FROM (SELECT frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name, frvfarmer.townId, frvtown.townName, frvfarmer.doyId, frvapplicationline.* FROM frvapplicationline,"+
       //"frvfarmer, frvtown WHERE frvapplicationline.farmerId = frvfarmer.farmerId AND frvtown.townId = frvfarmer.townId AND dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+
       //" AND dbCompanyId="+VariablesGlobal.globalCompanyId+") k GROUP BY k.farmerId ORDER BY townName",false,0,null,null,null,null,null);
       	
    //   EntityReportGroup[] retPerTownEntityReportGroup = new EntityReportGroup[2];
  //     retPerTownEntityReportGroup[0] = new EntityReportGroup("πόλεις/χωριά","SELECT townId, townName FROM frvtown","","frvtown",ENTITYREPORT_QUERY_TYPE_MAIN,0,"townId",boolSettingsTown); 
   //    retPerTownEntityReportGroup[1] = new EntityReportGroup("αγρότες","SELECT k.farmerId, k.surname, k.name, k.farmerAfm, k.townId, k.doyId, COUNT(farmerId),SUM(value) FROM (SELECT frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name, frvfarmer.townId, frvtown.townName, frvfarmer.doyId, frvapplicationline.* FROM frvapplicationline,"+
   //    "frvfarmer, frvtown WHERE frvapplicationline.farmerId = frvfarmer.farmerId AND frvtown.townId = frvfarmer.townId AND dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+
   //    " AND dbCompanyId="+VariablesGlobal.globalCompanyId+") k GROUP BY k.farmerId","ORDER BY townName","townfarmer",ENTITYREPORT_QUERY_TYPE_MAIN,1,null,boolSettingsFarme); 	
       	
//       EntityReport rd = new EntityReport("rptMoneyReturn",REPORT_CAT_INFORMATIVE,retPerTownEntityReportGroup,"TDMR","επιστροφές ανα πόλη","έτους χρήσης "+VariablesGlobal.globalYear+", "+VariablesGlobal.globalDeliveryId+"η αποστολή",deliveryErs,deliveryEntityGroupOfComps,null, null,deliveryOrderby,intSettingsReportFarm,boolSettingsReportFarm,globalYearPlusOne);
/*        EntityMenu emrd = new EntityMenu();
 //       emrd.setEntityReport(rd,ICO_PRINT_PREVIEW16);
        emrd.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrd = new DataTreeNode(emrd);
        nodeReports.getChildFromCaption(REPORT_CAT_INFORMATIVE).addChild(nodeemrd);
*/

         
       EntityFilterSettings[] applicationlineeErs = new EntityFilterSettings[10];       
       applicationlineeErs[0]=new EntityFilterSettings("Νο αγρότη","lookup","string","fromto","farmerId","frvfarmer","f","",-1,-1,0,FIELD_NOCOMPLETION);
       applicationlineeErs[1]=new EntityFilterSettings("αγρότης","checkboxTable","string","","farmerId","frvfarmer","frvfarmer","",-1,-1,0,FIELD_NOCOMPLETION);
       applicationlineeErs[2]=new EntityFilterSettings("επίθετο","","string","equals","surname","frvfarmer",null,"",-1,-1,0,FIELD_NOCOMPLETION);
       applicationlineeErs[3]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","frvfarmer",null,"",-1,-1,0,FIELD_NOCOMPLETION);
       applicationlineeErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","frvtown","frvfarmer","",-1,-1,0,FIELD_NOCOMPLETION);
       applicationlineeErs[5]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","frvfarmer","",-1,-1,0,FIELD_NOCOMPLETION);
       applicationlineeErs[6]=new EntityFilterSettings("ημερομηνία παραστ.","","date","fromto","date","frvapplicationline",null,"",-1,-1,0,FIELD_NOCOMPLETION);
       applicationlineeErs[7]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","frvbuyer","frvapplicationline","",-1,-1,0,FIELD_NOCOMPLETION);
       applicationlineeErs[8]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","frvproduct","frvapplicationline","",-1,-1,0,FIELD_NOCOMPLETION);
       applicationlineeErs[9]=new EntityFilterSettings("τύπος παραστατικού","checkboxTable","string","","invoiceTypeId","frvinvoicetype","frvapplicationline","",-1,-1,0,FIELD_NOCOMPLETION);
       
       //applicationlineErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","or","townId","frvtown","");       
       
       EntityGroupOfComps[] applicationlineeEntityGroupOfComps = null;       
       
       int[] applicationlineseSelected ={1,2,3,5,6,7,0,};
       boolean[] boolSettingsInvoi = {true,true,true,true}; 
       boolean[] boolSettingsReportInvoi = {true,true,true,true,true}; 
       int [] intSettingsReportInvoi={0,0,0,0};
       //EntityQuery[] retFarmerEntityQuery = new EntityQuery[1];
       //retFarmerEntityQuery[0]= new EntityQuery("SELECT frvfarmer.farmerId,frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name,frvapplicationline.dbyear,frvapplicationline.value,frvapplicationline.valueReturn FROM frvapplicationline, "+
       //"frvfarmer WHERE frvapplicationline.farmerId = frvfarmer.farmerId",false,0,null,null,null,null,null);

   //    EntityReportGroup[] retFarmerEntityReportGroup = new EntityReportGroup[1];
   //    retFarmerEntityReportGroup[0] = new EntityReportGroup("παραστατικά","SELECT frvfarmer.farmerId,frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name,frvapplicationline.dbyear,frvapplicationline.value,frvapplicationline.valueReturn FROM frvapplicationline, "+
   //    "frvfarmer WHERE frvapplicationline.farmerId = frvfarmer.farmerId","","frvapplicationline",ENTITYREPORT_QUERY_TYPE_MAIN,-1,null,boolSettingsInvoi); 


//       EntityReport re = new EntityReport("rptReturnInvoice",REPORT_CAT_INFORMATIVE,retFarmerEntityReportGroup,"ODMR","επιστροφές αγροτών","",applicationlineeErs,applicationlineeEntityGroupOfComps,applicationlineseSelected, null,null,intSettingsReportInvoi,boolSettingsReportInvoi,globalYearPlusOne) ;
/*        EntityMenu emre = new EntityMenu();
//        emre.setEntityReport(re,ICO_PRINT_PREVIEW16);
        emre.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemre = new DataTreeNode(emre);
        nodeReports.getChildFromCaption(REPORT_CAT_INFORMATIVE).addChild(nodeemre);
 */      
      /* boolean[] applicationlinesSelectedTDMR ={true,true,true,true,true,false,false,false,false,false,false,false,true,false,true,};
       base = new DefaultMutableTreeNode(new EntityReport("frvapplicationline","frvtown","SELECT frvfarmer.farmerId,frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name,frvtown.townId, frvapplicationline.* FROM frvapplicationline, "+
       "frvfarmer, frvtown WHERE frvapplicationline.farmerId = frvfarmer.farmerId AND frvtown.townId=frvfarmer.townId","SELECT townId, name,name,name name ,name,name,name, name, name, name ,name,name ,name,name FROM frvtown","townId","TDMR","επιστροφές αγροτών TDMR","",applicationlineErs,applicationlinesSelectedTDMR, null) );
        entityNode.add(base);*/
       
       

       //applicationlineErs[2]=new EntityFilterSettings("πόλη/χωριό","string","equals","name","frvtown");
      /* listModel.addElement(new EntityReport("frvapplicationheader","SELECT frvfarmer.* FROM frvfarmer, frvapplicationheader "+
       "WHERE frvfarmer.farmerId=frvapplicationheader.farmerId AND dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+
       " AND dbCompanyId="+VariablesGlobal.globalCompanyId,"ODMR","αγρότες που έχουν επιστροφές",deliveryErs,null, true) );
       */
       boolean[] boolSettingsInvo = {true,true,true,true}; 
       boolean[] boolSettingsReportReturn = {true,true,true,true,true}; 
       int[] intSettingsReportReturn={0,0,0,0};
       //EntityQuery[] retInvoiceSumEntityQuery = new EntityQuery[1];
       //retInvoiceSumEntityQuery[0]= new EntityQuery("SELECT k.farmerId,k.surname, k.name, k.farmerAfm, COUNT(farmerId) AS count ,SUM(value) AS sum FROM (SELECT frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name, frvapplicationline.* FROM frvapplicationline,"+
       //"frvfarmer WHERE frvapplicationline.farmerId = frvfarmer.farmerId AND dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+
       //" AND dbCompanyId="+VariablesGlobal.globalCompanyId+") k GROUP BY farmerId",false,0,null,null,null,null,null);       

   //    EntityReportGroup[] retInvoiceSumEntityReportGroup = new EntityReportGroup[1];
   //    retInvoiceSumEntityReportGroup[0] = new EntityReportGroup("παραστατικά","SELECT k.farmerId,k.surname, k.name, k.farmerAfm, COUNT(farmerId) AS count ,SUM(value) AS sum FROM (SELECT frvfarmer.farmerAfm, frvfarmer.surname,frvfarmer.name, frvapplicationline.* FROM frvapplicationline,"+
   //    "frvfarmer WHERE frvapplicationline.farmerId = frvfarmer.farmerId AND dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+
  //     " AND dbCompanyId="+VariablesGlobal.globalCompanyId+") k GROUP BY farmerId","","frvapplicationline",ENTITYREPORT_QUERY_TYPE_MAIN,-1,null,boolSettingsInvo); 

 //      EntityReport rf = new EntityReport("rptMoneyReturn",REPORT_CAT_INFORMATIVE,retInvoiceSumEntityReportGroup,"ODMR","ποσά παραστατικών","έτους χρήσης "+VariablesGlobal.globalYear+", "+VariablesGlobal.globalDeliveryId+"η αποστολή",applicationlineeErs,deliveryEntityGroupOfComps,null, null,null,intSettingsReportReturn,boolSettingsReportReturn,globalYearPlusOne) ;
/*        EntityMenu emrf = new EntityMenu();
 //       emrf.setEntityReport(rf,ICO_PRINT_PREVIEW16);
        emrf.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrf = new DataTreeNode(emrf);
        nodeReports.getChildFromCaption(REPORT_CAT_INFORMATIVE).addChild(nodeemrf);
 */       
/*       EntityReport rg = new EntityReport("frvfarmer",REPORT_CAT_INFORMATIVE,null,"SELECT frvfarmer.* FROM frvfarmer, frvapplicationheader "+
       "WHERE frvfarmer.farmerId=frvapplicationheader.farmerId AND dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+
       " AND dbCompanyId="+VariablesGlobal.globalCompanyId,null,null,"ODMR","αγρότες με επιστροφές","έτους χρήσης "+VariablesGlobal.globalYear+", "+VariablesGlobal.globalDeliveryId+"η αποστολή",deliveryErs,null, null);
        EntityMenu emrg = new EntityMenu();
        emrg.setEntityReport(rg);
        emrg.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrg = new DataTreeNode(emrg);
       nodeReports.getChildFromCaption(REPORT_CAT_INFORMATIVE).addChild(nodeemrg);*/

       // ------------------------------------- REPORT_CAT_APPLICATIONS --------------------
       EntityFilterSettings[] applicationheaderFileErs = new EntityFilterSettings[3];       
       applicationheaderFileErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","frvapplicationheader",VariablesGlobal.globalCompanyId,0,-1,0,FIELD_OBLIGATORY);
       applicationheaderFileErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbyear","dbyear","frvapplicationheader", VariablesGlobal.globalYearDescr,0,-1,0,FIELD_OBLIGATORY);
       applicationheaderFileErs[2]=new EntityFilterSettings("αποστολή","checkboxTable","string","","deliveryId","dbDelivery","frvapplicationheader","",0,-1,-1,FIELD_NOCOMPLETION);  //"αποστολή","","string","equals","deliveryId","dbDelivery",null,VariablesGlobal.globalDeliveryId,0,-1,0);                
       
             
       EntityGroupOfComps[] applicationheaderEntityGroupOfComps = new EntityGroupOfComps[1];
       applicationheaderEntityGroupOfComps[0] = new EntityGroupOfComps("εταιρία/χρήση/αποστολή",6,0,FONT_SIZE_NOT_SET);
       //applicationheaderEntityGroupOfComps[1] = new EntityGroupOfComps("αγρότης",4,0);
       
       int[] applicationlinesaSelected = null;//{1,2,3,4,0,0,0,0,0,0,11,12,0,14,};
       int[] fileOrderby ={1};
       boolean[] boolSettingsFar = {true,true,true,true,true}; 
      boolean[] boolSettingsReportFarmerfile = {true,true,true,true,true}; 
       int[] intSettingsReportFarmerfile={0,0,0,0};
      	// EntityQuery[] fileEntityQuery = new EntityQuery[1];
       //fileEntityQuery[0]= new EntityQuery("SELECT frvfarmer.farmerafm AS farmerafm, SUM(frvapplicationline.valueReturn) AS value,SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS valueReturn3 FROM frvapplicationheader, frvfarmer, frvapplicationline "+
      // "WHERE frvfarmer.farmerId=frvapplicationheader.farmerId AND frvapplicationline.farmerId=frvfarmer.farmerId AND frvapplicationheader.dbyear=frvapplicationline.dbyear AND frvapplicationheader.deliveryId =frvapplicationline.deliveryId AND frvapplicationheader.dbCompanyId=frvapplicationline.dbCompanyId GROUP BY frvfarmer.farmerId",false,0,null,null,null,null,null);


  //     EntityReportGroup[] fileEntityReportGroup = new EntityReportGroup[1];
  //     fileEntityReportGroup[0] = new EntityReportGroup("αγρότες","SELECT frvfarmer.farmerafm AS farmerafm, SUM(frvapplicationline.valueReturn) AS value,SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS valueReturn3 FROM frvapplicationheader, frvfarmer, frvapplicationline, dbDelivery "+
  //     "WHERE frvfarmer.farmerId=frvapplicationheader.farmerId AND frvapplicationline.farmerId=frvfarmer.farmerId AND frvapplicationheader.dbyear=frvapplicationline.dbyear AND frvapplicationheader.deliveryId =frvapplicationline.deliveryId AND frvapplicationheader.dbCompanyId=frvapplicationline.dbCompanyId GROUP BY frvfarmer.farmerId","","frvapplicationheader",ENTITYREPORT_QUERY_TYPE_MAIN,-1,null,boolSettingsFar); 

       
//       EntityReport rh = new EntityReport("farmerfile", REPORT_CAT_APPLICATIONS,fileEntityReportGroup,"ODMR","αρχείο δισκετας","",applicationheaderFileErs,applicationheaderEntityGroupOfComps,applicationlinesaSelected, null,fileOrderby,intSettingsReportFarmerfile,boolSettingsReportFarmerfile,globalYearPlusOne) ;
/*        EntityMenu emrh = new EntityMenu();
//        emrh.setEntityReport(rh,ICO_REPORTFILE);
        emrh.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrh = new DataTreeNode(emrh);
        nodeReports.getChildFromCaption(REPORT_CAT_APPLICATIONS).addChild(nodeemrh);
*/
        
   
       
       
       EntityFilterSettings[] applicationheaderFormErs = new EntityFilterSettings[14];   
       applicationheaderFormErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","frvapplicationheader",VariablesGlobal.globalCompanyId,0,-1,-1,FIELD_OBLIGATORY);
       applicationheaderFormErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbyearId","dbyear","frvapplicationheader", VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
       applicationheaderFormErs[2]=new EntityFilterSettings("αποστολή","checkboxTable","string","equals","dbyeardeliveryId","frvyeardelivery","frvapplicationheader","",0,-1,-1,FIELD_NOCOMPLETION);        
       applicationheaderFormErs[3]=new EntityFilterSettings("Νο αγρότη","lookup","string","fromto","farmerId","frvfarmer","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       applicationheaderFormErs[4]=new EntityFilterSettings("επίθετο","","string","equals","surname","frvfarmer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       applicationheaderFormErs[5]=new EntityFilterSettings("αγρότης","checkboxTable","string","","farmerId","frvfarmer","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       applicationheaderFormErs[6]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","frvfarmer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       applicationheaderFormErs[7]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","frvtown","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       applicationheaderFormErs[8]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","frvfarmer","",1,-1,-1,FIELD_NOCOMPLETION);
       applicationheaderFormErs[9]=new EntityFilterSettings("ημ/νία αίτησης","","date","fromto","dateOfApplication","frvapplicationheader",null,"",2,0,-1,FIELD_NOCOMPLETION);
       applicationheaderFormErs[10]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       applicationheaderFormErs[11]=new EntityFilterSettings("ποσό παρ/κών","","double","fromto","value","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       applicationheaderFormErs[12]=new EntityFilterSettings("ποσό επιστροφής","","double","fromto","valueReturn","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       applicationheaderFormErs[13]=new EntityFilterSettings("ποσό κράτησης","","double","fromto","payment","frvapplicationheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
      // applicationlineErs[14]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","frvbuyer","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);
      // applicationlineErs[15]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","frvproduct","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);
      // applicationlineErs[16]=new EntityFilterSettings("είδος προϊόντος","checkboxTable","string","","productTypeId","frvproducttype","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);
      // applicationlineErs[17]=new EntityFilterSettings("τύπος παραστατικού","checkboxTable","string","","invoiceTypeId","frvinvoicetype","frvinvoicetype","",3,-1,1,FIELD_NOCOMPLETION);

       
       
       EntityGroupOfComps[] applicationheaderFormEntityGroupOfComps = new EntityGroupOfComps[3];
       applicationheaderFormEntityGroupOfComps[0] = new EntityGroupOfComps("εταιρία/χρήση/αποστολή",6,0,FONT_SIZE_NOT_SET);
       applicationheaderFormEntityGroupOfComps[1] = new EntityGroupOfComps("αγρότης",4,0,FONT_SIZE_NOT_SET);
       applicationheaderFormEntityGroupOfComps[2] = new EntityGroupOfComps("άιτηση",4,0,FONT_SIZE_NOT_SET);     
       
       
       
       
       int[] applicationheaderOrderBy = {2,3,4,5,6};
       
     
       
      /* EntityQuery[] applicationheaderEntityQuery = new EntityQuery[2];
       applicationheaderEntityQuery[0]= new EntityQuery("SELECT frvfarmer.farmerId, dbcompany.companyName AS companyname, dbcompany.companyVatNo AS companyafm, dbcompany.doyId AS cdoyid, dbcompany.townId AS ctowid,  frvfarmer.surname AS farmersurname, frvfarmer.name AS farmername, frvfarmer.fathername AS ffathername,frvfarmer.farmerafm AS farmerafm, doy.doyId AS fdoyid , doy.doyName AS farmerdoyname, frvtown.townname AS farmertownname, COUNT(frvapplicationline.value) AS cinv, SUM(frvapplicationline.value) AS valinvoic,  SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.value)) AS \"valinvo1\", SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"retinvoi1\" ,  SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.value)) AS \"valinvo2\", SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"retinvoi2\" ,  SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.value)) AS \"valinvo3\", SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"retinvoi3\", retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.productTypePercentage) AS \"per1\" , retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.productTypePercentage) AS \"per2\" , retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.productTypePercentage) AS \"per3\", (ifnull(SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.value)),0) + ifnull(SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.value)),0)+ ifnull(SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.value)),0)) AS valinvoir, (ifnull(SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.value)),0) + ifnull(SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.value)),0)+ ifnull(SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.value)),0)) AS valinvois, (retZeroIfNull(SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))+ retZeroIfNull(SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))+ retZeroIfNull(SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))) AS retinvoir, (retZeroIfNull(SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))+ retZeroIfNull(SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))+ retZeroIfNull(SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))) AS retinvoil  FROM dbcompany, frvapplicationline, frvapplicationheader, frvfarmer LEFT JOIN doy ON frvfarmer.doyId=doy.doyId LEFT JOIN frvtown ON frvfarmer.townId=frvtown.townId  "+
       "WHERE dbcompany.dbCompanyId=frvapplicationheader.dbCompanyId AND frvfarmer.farmerId=frvapplicationheader.farmerId AND frvapplicationline.farmerId=frvfarmer.farmerId AND frvapplicationline.dbyear=frvapplicationheader.dbyear GROUP BY frvfarmer.farmerId, dbcompany.companyName",false,0,null,null,null,null,null);
       applicationheaderEntityQuery[1]= new EntityQuery("SELECT i.farmerId, applicationlineTypeId AS tem,applicationlineNo AS tin,date AS tdate,b.buyerTitle AS tbuyertit ,b.buyerAFM AS tbuyeraf ,p.productName AS tproduct, retValueAccordingToType(1,i.productTypeId,i.value) AS tvalinv1, retValueAccordingToType(2,i.productTypeId,i.value) AS tvalinv2, retValueAccordingToType(3,i.productTypeId,i.value) AS tvalinv3 FROM frvapplicationline i, frvproduct p, frvfarmer f, frvapplicationheader d, frvbuyer b WHERE b.buyerId=i.buyerId AND f.farmerId=i.farmerId AND f.farmerId=d.farmerId AND i.deliveryId=d.deliveryId AND i.productId = p.productId AND i.dbyear="+VariablesGlobal.globalYear+" AND i.deliveryId = "+VariablesGlobal.globalDeliveryId+" AND i.dbCompanyId="+VariablesGlobal.globalCompanyId+" ORDER BY surname, name, fathername, f.farmerAFM, f.farmerId",false,1,"farmerId","f",null,null,null);
       */
       

      
      boolean[] boolSettingsAppLine = {true,true,true,true};
      int [] fieldsOrderbyApplicationLine ={3};
    EntityReportBandField[] erbfApplicationLine = new EntityReportBandField[8];
       erbfApplicationLine[0]=new EntityReportBandField("frvapplicationline","applicationHeaderId","applicationHeaderId","java.lang.Integer",8,true,"",null);
       erbfApplicationLine[1]=new EntityReportBandField("frvapplicationline","applicationLineId","applicationLineId","java.lang.Integer",8,true,"",null);
       erbfApplicationLine[2]=new EntityReportBandField("frvapplicationline","inc","α/α","java.lang.Integer",8,true,"",null);
       erbfApplicationLine[3]=new EntityReportBandField("frvapplicationline","buyerId","buyerId","java.lang.Integer",8,true,"",null);
       erbfApplicationLine[4]=new EntityReportBandField("frvapplicationline","invoiceNo","invoiceNo","java.lang.String",15,true,"",null);
       erbfApplicationLine[5]=new EntityReportBandField("frvapplicationline","dateInv","ημερομηνία","java.sql.Date",12,true,"",null);
       erbfApplicationLine[6]=new EntityReportBandField("frvapplicationline","productId","productId","java.lang.Integer",4,true,"",null);
       erbfApplicationLine[7]=new EntityReportBandField("frvapplicationline","value","αξία παρ/κού","java.lang.Double",12,true,"",null);  // 'value' has the same name in both tables header and line
             
      
      
       
        boolean[] boolSettingsFa = {true,true,true,true}; 
        boolean[] boolSettingsInv = {true,true,true,true}; 
        boolean[] boolSettingsReportFarmerapp = {true,true,true,true,true}; 
        int[] intSettingsReportFarmerapp={0,0,0,0};	
        int[] fieldsOrderbyApplicationHeader = {2,3,4,1};
      EntityReportBandField[] erbFieldsAppForm = new EntityReportBandField[13];
       erbFieldsAppForm[0]=new EntityReportBandField("frvfarmer","farmerAfm","farmerAfm","java.lang.String",8,true,"",null);
       erbFieldsAppForm[1]=new EntityReportBandField("frvfarmer","surname","surname","java.lang.String",35,true,"",null);
       erbFieldsAppForm[2]=new EntityReportBandField("frvfarmer","name","name","java.lang.String",35,true,"",null);
       erbFieldsAppForm[3]=new EntityReportBandField("frvfarmer","fathername","fathername","java.lang.String",25,true,"",null);
       erbFieldsAppForm[4]=new EntityReportBandField("frvapplicationheader","applicationHeaderId","applicationHeaderId","java.lang.Integer",8,true,"",null);
       erbFieldsAppForm[5]=new EntityReportBandField("frvapplicationheader","dbYearDeliveryId","αποστολή","java.lang.Integer",8,true,"",null);
       erbFieldsAppForm[6]=new EntityReportBandField("frvapplicationheader","dbCompanyId","dbCompanyId","java.lang.String",8,true,"",null);
       erbFieldsAppForm[7]=new EntityReportBandField("frvapplicationheader","farmerId","farmerId","java.lang.Integer",8,true,"",null);
       erbFieldsAppForm[8]=new EntityReportBandField("frvapplicationheader","dbYearId","dbYearId","java.lang.Integer",8,true,"",null);
       erbFieldsAppForm[9]=new EntityReportBandField("frvapplicationheader","dateOfApplication","ημ/νία αίτησης","java.sql.Date",12,true,"",null);
       erbFieldsAppForm[10]=new EntityReportBandField("frvapplicationheader","invcount","count","java.lang.Double",12,true,"",null);
       erbFieldsAppForm[11]=new EntityReportBandField("frvapplicationheader","value","value","java.lang.Double",12,true,"",null);
       erbFieldsAppForm[12]=new EntityReportBandField("frvapplicationheader","valueReturn","επιστροφή","java.lang.Double",12,true,"",null); 
     
     
  
        
        
        
        EntityReportBand[] applicationheaderEntityReportBand = new EntityReportBand[2];
       applicationheaderEntityReportBand[0] = new EntityReportBand("rptApplicationFormHeader","αιτήσεις","frvapplicationheader",erbFieldsAppForm,fieldsOrderbyApplicationHeader,"applicationHeaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReportFarmerapp,entityPanelApplication,fieldsOnTitleApplication, fieldsOnTitleCaptionApplication);
       applicationheaderEntityReportBand[1] = new EntityReportBand("rptApplicationFormLine","παραστατικά","frvapplicationline",erbfApplicationLine,fieldsOrderbyApplicationLine,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsAppLine,null,null,null);  // many        
       /*EntityReportGroup[] applicationheaderEntityReportGroup = new EntityReportGroup[2];
       applicationheaderEntityReportGroup[0] = new EntityReportGroup("αγρότες","SELECT frvfarmer.farmerId, dbcompany.companyName AS companyname, dbcompany.companyVatNo AS companyafm, dbcompany.doyId AS cdoyid, dbcompany.townId AS ctowid,  frvfarmer.surname AS farmersurname, frvfarmer.name AS farmername, frvfarmer.fathername AS ffathername,frvfarmer.farmerafm AS farmerafm, doy.doyId AS fdoyid , doy.doyName AS farmerdoyname, frvtown.townname AS farmertownname, frvtown.postCode AS farpc, frvapplicationheader.dbyear AS year, COUNT(frvapplicationline.value) AS cinv, SUM(frvapplicationline.value) AS valinvoic,  SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.value)) AS \"valinvo1\", SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"retinvoi1\" ,  SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.value)) AS \"valinvo2\", SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"retinvoi2\" ,  SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.value)) AS \"valinvo3\", SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.valueReturn)) AS \"retinvoi3\", retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.productTypePercentage) AS \"per1\" , retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.productTypePercentage) AS \"per2\" , retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.productTypePercentage) AS \"per3\", (ifnull(SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.value)),0) + ifnull(SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.value)),0)+ ifnull(SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.value)),0)) AS valinvoir, (ifnull(SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.value)),0) + ifnull(SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.value)),0)+ ifnull(SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.value)),0)) AS valinvois, (retZeroIfNull(SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))+ retZeroIfNull(SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))+ retZeroIfNull(SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))) AS retinvoir, (retZeroIfNull(SUM(retValueAccordingToType(1, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))+ retZeroIfNull(SUM(retValueAccordingToType(2, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))+ retZeroIfNull(SUM(retValueAccordingToType(3, frvapplicationline.productTypeId, frvapplicationline.valueReturn)))) AS retinvoil  "+
       "FROM dbcompany, frvapplicationline, frvproducttype, frvapplicationheader, frvfarmer LEFT JOIN doy ON frvfarmer.doyId=doy.doyId LEFT JOIN frvtown ON frvfarmer.townId=frvtown.townId  "+
       "WHERE dbcompany.dbCompanyId=frvapplicationheader.dbCompanyId AND frvapplicationline.dbCompanyId = frvapplicationheader.dbCompanyId AND frvfarmer.farmerId=frvapplicationheader.farmerId AND frvapplicationline.farmerId=frvfarmer.farmerId AND frvapplicationheader.farmerId=frvapplicationline.farmerId AND frvapplicationline.dbyear=frvapplicationheader.dbyear GROUP BY frvfarmer.farmerId, dbcompany.companyName","","frvfarmer",ENTITYREPORT_QUERY_TYPE_MAIN,-1,null,boolSettingsFa); 
       applicationheaderEntityReportGroup[1] = new EntityReportGroup("παραστατικα","SELECT i.farmerId, i.inc AS tinc,  applicationlineType.abbreviation AS tem,applicationlineNo AS tin,date AS tdate,b.buyerTitle AS tbuyertit ,b.buyerAFM AS tbuyeraf ,p.productName AS tproduct, retValueAccordingToType(1,i.productTypeId,i.value) AS tvalinv1, retValueAccordingToType(2,i.productTypeId,i.value) AS tvalinv2, retValueAccordingToType(3,i.productTypeId,i.value) AS tvalinv3 "+
       "FROM frvapplicationline i, frvproduct p, frvfarmer f, frvapplicationheader a, frvbuyer b, applicationlineType "+
       "WHERE b.buyerId=i.buyerId AND f.farmerId=i.farmerId AND f.farmerId=a.farmerId AND i.deliveryId=a.deliveryId AND i.productId = p.productId AND i.dbyear=a.dbyear AND applicationlineType.applicationlineTypeId=i.applicationlineTypeId AND i.dbyear="+VariablesGlobal.globalYear+" AND i.dbCompanyId="+VariablesGlobal.globalCompanyId+" ORDER BY surname, name, fathername, f.farmerAFM, f.farmerId",
       "","f",ENTITYREPORT_QUERY_TYPE_ADDITIONAL,-1,"farmerId",boolSettingsInv);
       */	
       
//, APPLICATION.DBYEAR, INVOICE.PRODUCTTYPEID			
       //  1st report from vivlio metagrafhs  EntityReport rb = new EntityReport("rptBook",REPORT_CAT_ECONOMIC,invEntityReportBand,"SELECT *  FROM frvapplicationline, frvfarmer, frvapplicationheader WHERE frvapplicationheader.farmerId = frvfarmer.farmerId AND frvapplicationheader.applicationHeaderId = frvapplicationline.applicationHeaderId","ORDER BY frvapplicationheader.applicationheaderId","ODMR","βιβλίο μεταγραφής","subtitle",paymentErs,applicationlineReturnEntityGroupOfComps,applicationlinesSelected, null,applicationlineFieldOrderby,intReportSettingsInvoic,boolSettingsReportInvoic,globalYearPlusOne);
//    old version    EntityReport ri = new EntityReport("farmerapp",REPORT_CAT_APPLICATIONS,applicationheaderEntityReportGroup,"FORM","αιτήσεις παραγωγών-έντυπα","",applicationheaderFormErs,applicationheaderFormEntityGroupOfComps,applicationlinesSelected, null, applicationheaderOrderBy,intSettingsReportFarmerapp,boolSettingsReportFarmerapp,globalYearPlusOne) ;
        EntityReport ri = new EntityReport("farmerapp",REPORT_CAT_APPLICATIONS,applicationheaderEntityReportBand,"SELECT *  FROM frvfarmer , frvapplicationheader, frvapplicationline WHERE frvapplicationheader.farmerId = frvfarmer.farmerId AND frvapplicationheader.applicationHeaderId = frvapplicationline.applicationHeaderId",""/*ORDER BY frvapplicationheader.applicationheaderId"*/,"FORM","αιτήσεις παραγωγών-έντυπα","subtitle",applicationheaderFormErs,applicationheaderFormEntityGroupOfComps,applicationlinesSelected, null,
                  "SELECT frvyeardelivery.printFormId, printFormName, printFormLaser, printFormDotMatrix , frvapplicationheader.FARMERID, frvyeardelivery.dbYearDeliveryId, frvapplicationheader.applicationHeaderId"+
                  " FROM frvyeardelivery, dbyear, frvapplicationheader, printform " +
                  " WHERE  frvyeardelivery.printFormId = printform.printFormId AND frvyeardelivery.dbCompanyId = printform.dbCompanyId AND frvapplicationheader.DBYEARDELIVERYID = frvyeardelivery.dbYearDeliveryId AND frvyeardelivery.dbCompanyId = frvapplicationheader.DBCOMPANYID AND dbyear.dbYearId = frvyeardelivery.dbYearId AND dbyear.dbCompanyId = frvyeardelivery.dbCompanyId"
                  +" AND printform.isActive = 1 AND printform.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" "
                  ,"printform.printFormLaser","Θα πρέπει να επιλέξετε τη φόρμα της αίτησης.",intSettingsReportFarmerapp,boolSettingsReportFarmerapp,"");//,globalYearPlusOne) ;
        EntityMenu emri = new EntityMenu();
        emri.setEntityReport(ri,ICO_PRINT_PREVIEW_FORM16);//ICO_REPORTDOCUMENT);
        emri.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemri = new DataTreeNode(emri);
        nodeReports.getChildFromCaption(REPORT_CAT_APPLICATIONS).addChild(nodeemri);

     }

  public void addStatisticsNodes() 
  {

       
        EntityDockableGraph[] entityDockableGraph1 = new EntityDockableGraph[5];
        entityDockableGraph1[0] =new EntityDockableGraph("Toπ 10 προϊόντα",GRAPH_TYPE_PIE,null,"SELECT frvproduct.productId AS id, frvproduct.productName AS \"προϊόν\", COUNT(frvproduct.productId) AS \"πλήθος παρ\", SUM(frvapplicationline.value) AS \"τιμή παρ/κών\" FROM frvproduct, frvapplicationline WHERE frvproduct.productId=frvapplicationline.productId GROUP BY frvproduct.productId ORDER BY SUM(frvapplicationline.value) DESC LIMIT 10",0,0,1,1);
        entityDockableGraph1[1] =new EntityDockableGraph("πορεία ποσών τοπ 6 εταιριών",GRAPH_TYPE_LINE,"SELECT c.dbCompanyId AS id, c.companyName, COUNT(i.dateinv) AS count, SUM(i.value) AS sum, AVG(i.value) AS average, MIN(i.value) FROM dbcompany c, frvapplicationline i WHERE c.dbCompanyId=i.dbCompanyId GROUP BY i.dbCompanyId ORDER BY sum LIMIT 6","SELECT frvapplicationline.dbCompanyId AS \"id\", dbyear.dbyear AS \"χρήση\",COUNT(frvapplicationline.dateinv) AS \"πληθ παρ\", SUM(frvapplicationline.value) AS \"τιμή παρ/κων\", AVG(frvapplicationline.value) AS average,dbcompany.companyName AS \"εταιρία\" FROM dbyear, frvapplicationline, dbcompany WHERE dbcompany.dbCompanyId=frvapplicationline.dbCompanyId GROUP BY frvapplicationline.dbCompanyId, dbyear.dbyearId ORDER BY frvapplicationline.dbCompanyId, dbyear.dbyearId",1,0,1,1);
        entityDockableGraph1[2] =new EntityDockableGraph("Toπ 10 προϊόντων "+VariablesGlobal.globalCompanyName+" έτους "+VariablesGlobal.globalYearDescr,GRAPH_TYPE_PIE,null,"SELECT frvproduct.productId AS id, frvproduct.productName, COUNT(frvproduct.productId) AS \"πληθ παρ\", SUM(frvapplicationline.value) AS \"τιμή παρ/κών\" FROM frvproduct, frvapplicationline WHERE frvproduct.productId=frvapplicationline.productId AND frvapplicationline.dbCompanyId="+VariablesGlobal.globalCompanyId+" GROUP BY frvproduct.productId ORDER BY SUM(frvapplicationline.value) DESC LIMIT 10",0,1,1,1);
        entityDockableGraph1[3] =new EntityDockableGraph("πορεία ποσών τοπ 9 προϊόντων",GRAPH_TYPE_LINE,"SELECT p.productId as id, p.productName, COUNT(i.dateinv) AS count, SUM(i.value) AS sum, AVG(i.value) AS average FROM frvproduct p, frvapplicationline i WHERE p.productId=i.productId GROUP BY p.productId ORDER BY sum LIMIT 9","SELECT frvapplicationline.productId AS id, dbyear.dbyear AS \"χρήση\",frvproduct.productName \"προϊόν\", COUNT(frvapplicationline.dateinv) AS \"πληθ παρ\", SUM(frvapplicationline.value) AS \"τιμή παρ/κων\" FROM dbyear, frvapplicationline, frvproduct WHERE dbyear.dbCompanyId=frvapplicationline.dbCompanyId AND Product.productId=frvapplicationline.productId GROUP BY frvapplicationline.productId, dbyear.dbyearId ORDER BY frvapplicationline.productId, dbyear.dbyear",1,1,1,1);
        entityDockableGraph1[4] =new EntityDockableGraph("μεγαλύτεροι προμηθευτές",2,null,null,0,2,2,1);
        //entityDockableGraph1[5] =new EntityDockableGraph("πωλήσεις ανα νομό",2,null,null,1,2,1,1);

       
       EntityFilterSettings[] scoreErs = new EntityFilterSettings[4];
       scoreErs[0]=new EntityFilterSettings("εταιρία","checkboxTable","string","","dbCompanyId","dbcompany","frvapplicationline","",0,-1,-1,FIELD_NOCOMPLETION);
       //scoreErs[1]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbyearId","dbyear","frvapplicationline", "",0,0,-1,FIELD_NOCOMPLETION);
      // scoreErs[2]=new EntityFilterSettings("αποστολή","checkboxTable","string","equals","dbYearDeliveryId","frvyeardelivery","frvapplicationline","",0,-1,-1,FIELD_NOCOMPLETION); 
       scoreErs[1]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","frvbuyer","frvapplicationline","",-1,-1,-1,FIELD_NOCOMPLETION);
       scoreErs[2]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","frvproduct","frvapplicationline","",-1,-1,-1,FIELD_NOCOMPLETION);
       scoreErs[3]=new EntityFilterSettings("τύπος παραστατικού","checkboxTable","string","","invoiceTypeId","frvinvoicetype","frvapplicationline","",-1,-1,-1,FIELD_NOCOMPLETION);
       
       EntityGroupOfComps[] entityGroupOfComps = null;


        EntityScoreBoard entityScoreBoardA = new EntityScoreBoard("γραφήματα",entityDockableGraph1,scoreErs,entityGroupOfComps,globalYearPlusOne);

        EntityMenu emsga = new EntityMenu();
        emsga.setEntityScoreBoard(entityScoreBoardA,ICO_CHARTBAR);
        emsga.setEntityType(ENTITY_TYPE_DOCKABLEGRAPH);
        DataTreeNode nodeemsga = new DataTreeNode(emsga);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsga);        
     
        

        EntityStatistics[] sa = new EntityStatistics[2];
        sa[0] = new EntityStatistics("statInvoicespercompany","dbcompany","παραστατικά ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, COUNT(frvapplicationline.dateinv) AS count, SUM(frvapplicationline.value) AS sum, SUM(frvapplicationline.valueReturn) AS sumret, AVG(frvapplicationline.value) AS average","FROM dbcompany, frvapplicationline","WHERE dbcompany.dbCompanyId=frvapplicationline.dbCompanyId","GROUP BY dbcompany.dbCompanyId","ORDER BY dbcompany.companyName",false,null,true,"frvapplicationline.dbyear","dbCompanyId","dbCompanyId",null,null,null);                                                   																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)
        sa[1] = new EntityStatistics("statInvoicespercompany","frvapplicationline","παραστατικά ανα εταιρία","SELECT frvapplicationline.farmerId, frvapplicationline.dbCompanyId,frvapplicationline.dbyear,  frvapplicationline.buyerId ,frvapplicationline.invoiceTypeId,frvapplicationline.productId, frvapplicationline.dateinv,frvapplicationline.value, frvapplicationline.valueReturn","FROM frvapplicationline","","","ORDER BY frvapplicationline.buyerId",false,null,true,"frvapplicationline.dbyear","dbCompanyId","dbCompanyId",null,null,null);
        //EntityStatistics sa = new EntityStatistics("applicationlinespercompany","dbcompany","παραστατικά ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, COUNT(frvapplicationline.dateinv) AS count, SUM(frvapplicationline.value) AS sum, SUM(frvapplicationline.returnValue) AS sumret, AVG(frvapplicationline.value) AS average","FROM dbcompany, frvapplicationline","WHERE dbcompany.dbCompanyId=frvapplicationline.dbCompanyId","GROUP BY dbcompany.dbCompanyId","ORDER BY dbcompany.companyName",false,null,true,"frvapplicationline.dbyear","dbCompanyId","dbCompanyId");                                                     																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)																								
        EntityMenu emsa = new EntityMenu();
        emsa.setEntityStatistics(sa,ICO_STATISTICS16);
        emsa.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsa = new DataTreeNode(emsa);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsa);


        EntityStatistics[] sb = new EntityStatistics[2];
        sb[0] = new EntityStatistics("statDeliveriespercompany","dbcompany","αποστολές ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, frvapplicationheader.dbyear, frvapplicationheader.deliveryId, COUNT(frvapplicationheader.deliveryId) AS count, SUM(frvapplicationheader.value) AS sum, SUM(frvapplicationheader.valueReturn) AS sumret, SUM(frvapplicationheader.payment) AS payment","FROM dbcompany, frvapplicationheader","WHERE dbcompany.dbCompanyId=frvapplicationheader.dbCompanyId","GROUP BY dbcompany.dbCompanyId, frvapplicationheader.dbyear,frvapplicationheader.deliveryId","ORDER BY dbcompany.companyName, frvapplicationheader.dbyear, frvapplicationheader.deliveryId",true,"frvapplicationheader.dbCompanyId",true,"frvapplicationheader.dbyear","dbCompanyId","dbCompanyId",null,null,null);                                              																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)
        sb[1] = new EntityStatistics("statDeliveriespercompany","frvapplicationline","παραστατικά ανα εταιρία","SELECT frvapplicationline.farmerId, frvapplicationline.dbCompanyId,frvapplicationline.dbyear,  frvapplicationline.buyerId ,frvapplicationline.invoiceTypeId,frvapplicationline.productId, frvapplicationline.dateinv,frvapplicationline.value, frvapplicationline.valueReturn","FROM frvapplicationline","","","ORDER BY frvapplicationline.buyerId",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear","dbCompanyId","dbCompanyId",null,null,null);
        //EntityStatistics sa = new EntityStatistics("applicationlinespercompany","dbcompany","παραστατικά ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, COUNT(frvapplicationline.dateinv) AS count, SUM(frvapplicationline.value) AS sum, SUM(frvapplicationline.returnValue) AS sumret, AVG(frvapplicationline.value) AS average","FROM dbcompany, frvapplicationline","WHERE dbcompany.dbCompanyId=frvapplicationline.dbCompanyId","GROUP BY dbcompany.dbCompanyId","ORDER BY dbcompany.companyName",false,null,true,"frvapplicationline.dbyear","dbCompanyId","dbCompanyId");                                                     																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)																								
        EntityMenu emsb = new EntityMenu();
        emsb.setEntityStatistics(sb,ICO_STATISTICS16);
        emsb.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsb = new DataTreeNode(emsb);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsb);


        
        EntityStatistics[] sc = new EntityStatistics[1];
        sc[0] = new EntityStatistics("statInvoicesperyear","frvapplicationline","παραστατικά ανα χρήση","SELECT dbyear.dbyear, COUNT(frvapplicationline.dateinv) AS count, SUM(frvapplicationline.value) AS sum,SUM(frvapplicationline.valueReturn) AS sumret, AVG(frvapplicationline.value) AS average","FROM dbyear, frvapplicationline","WHERE dbyear.dbyear=frvapplicationline.dbyear AND dbyear.dbCompanyId=frvapplicationline.dbCompanyId","GROUP BY dbyear.dbyear","ORDER BY dbyear.dbyear",true,"dbyear.dbCompanyId",false,null,null,null,null,null,null);
        //EntityStatistics sb = new EntityStatistics("applicationlinesperyear","frvapplicationline","παραστατικά ανα χρήση","SELECT dbyear.dbyear, COUNT(frvapplicationline.dateinv) AS count, SUM(frvapplicationline.value) AS sum,SUM(frvapplicationline.returnValue) AS sumret, AVG(frvapplicationline.value) AS average","FROM dbyear, frvapplicationline","WHERE dbyear.dbyear=frvapplicationline.dbyear AND dbyear.dbCompanyId=frvapplicationline.dbCompanyId","GROUP BY dbyear.dbyear","ORDER BY dbyear.dbyear",true,"dbyear.dbCompanyId",false,null,null,null);
        EntityMenu emsc = new EntityMenu();
        emsc.setEntityStatistics(sc,ICO_STATISTICS16);
        emsc.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsc = new DataTreeNode(emsc);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsc);

        EntityStatistics[] sd = new EntityStatistics[2];
        sd[0] = new EntityStatistics("statSumsofbuyers","frvbuyer","ποσά αγοραστών","SELECT frvbuyer.buyerId, frvbuyer.buyerTitle, COUNT(frvapplicationline.buyerId) AS count, SUM(frvapplicationline.value) AS sum,SUM(frvapplicationline.valueReturn) AS sumret, AVG(frvapplicationline.value) AS average","FROM frvbuyer, frvapplicationline","WHERE frvbuyer.buyerId=frvapplicationline.buyerId","GROUP BY frvbuyer.buyerId","ORDER BY frvbuyer.buyerTitle",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear","buyerId","buyerId",null,null,null);
        sd[1] = new EntityStatistics("statSumsofbuyers2","frvapplicationline","ποσά αγοραστών","SELECT frvapplicationline.farmerId,frvapplicationline.dbCompanyId,frvapplicationline.dbyear,  frvapplicationline.buyerId ,frvapplicationline.invoiceTypeId,frvapplicationline.productId, frvapplicationline.dateinv,frvapplicationline.value, frvapplicationline.valueReturn","FROM frvapplicationline","","","ORDER BY frvapplicationline.buyerId",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear","buyerId","buyerId",null,null,null);
        //EntityStatistics sc = new EntityStatistics("sumsofbuyers","frvbuyer","ποσά αγοραστών","SELECT frvbuyer.buyerId, frvbuyer.buyerTitle, COUNT(frvapplicationline.buyerId) AS count, SUM(frvapplicationline.value) AS sum,SUM(frvapplicationline.returnValue) AS sumret, AVG(frvapplicationline.value) AS average","FROM frvbuyer, frvapplicationline","WHERE frvbuyer.buyerId=frvapplicationline.buyerId","GROUP BY frvbuyer.buyerId","ORDER BY frvbuyer.buyerTitle",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear","buyerId","buyerId");
        EntityMenu emsd = new EntityMenu();
        emsd.setEntityStatistics(sd,ICO_STATISTICS16);
        emsd.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsd = new DataTreeNode(emsd);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsd);

        
        
        EntityStatistics[] se = new EntityStatistics[2];
        se[0] = new EntityStatistics("statSumsofproducts","frvproduct","ποσά προϊόντων","SELECT frvproduct.productId, frvproduct.productName, COUNT(frvproduct.productId) AS count, SUM(frvapplicationline.value) AS sum,SUM(frvapplicationline.valueReturn) AS sumret, AVG(frvapplicationline.value) AS average","FROM frvproduct, frvapplicationline","WHERE frvproduct.productId=frvapplicationline.productId","GROUP BY frvproduct.productId","ORDER BY frvproduct.productName",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear","productId","productId",null,null,null);
        se[1] = new EntityStatistics("statSumsofproducts2","frvapplicationline","ποσά προϊόντων","SELECT frvapplicationline.farmerId,frvapplicationline.dbCompanyId,frvapplicationline.dbyear,  frvapplicationline.buyerId ,frvapplicationline.invoiceTypeId,frvapplicationline.productId, frvapplicationline.dateinv,frvapplicationline.value, frvapplicationline.valueReturn","FROM frvapplicationline","","","ORDER BY frvapplicationline.buyerId",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear","productId","productId",null,null,null); 
        //EntityStatistics sd = new EntityStatistics("sumsofproducts","frvproduct","ποσά προϊόντων","SELECT frvproduct.productId, frvproduct.productName, COUNT(frvproduct.productId) AS count, SUM(frvapplicationline.value) AS sum,SUM(frvapplicationline.returnValue) AS sumret, AVG(frvapplicationline.value) AS average","FROM frvproduct, frvapplicationline","WHERE frvproduct.productId=frvapplicationline.productId","GROUP BY frvproduct.productId","ORDER BY frvproduct.productName",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear","productId","productId");
        EntityMenu emse = new EntityMenu();
        emse.setEntityStatistics(se,ICO_STATISTICS16);
        emse.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemse = new DataTreeNode(emse);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemse);

        
        EntityStatistics[] sf = new EntityStatistics[2];
        sf[0] = new EntityStatistics("statSumsoffarmers","frvfarmer","ποσά αγροτών","SELECT frvfarmer.farmerId, frvfarmer.surname,frvfarmer.name, frvfarmer.fatherName, COUNT(frvapplicationline.farmerId) AS count, SUM(frvapplicationline.value) AS sum,SUM(frvapplicationline.valueReturn) AS sumret, AVG(frvapplicationline.value) AS average","FROM frvfarmer, frvapplicationline","WHERE frvfarmer.farmerId=frvapplicationline.farmerId","GROUP BY frvfarmer.farmerId","ORDER BY frvfarmer.surname, frvfarmer.name, frvfarmer.fatherName",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear","farmerId","farmerId",null,null,null);
        sf[1] = new EntityStatistics("statSumsoffarmers2","frvapplicationline","ποσά αγροτών","SELECT frvapplicationline.farmerId,frvapplicationline.dbCompanyId,frvapplicationline.dbyear, frvapplicationline.buyerId ,frvapplicationline.invoiceTypeId,frvapplicationline.productId, frvapplicationline.dateinv,frvapplicationline.value, frvapplicationline.valueReturn","FROM frvapplicationline","","","ORDER BY frvapplicationline.buyerId",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear","farmerId","farmerId",null,null,null);
        //EntityStatistics sc = new EntityStatistics("sumsofbuyers","frvbuyer","ποσά αγοραστών","SELECT frvbuyer.buyerId, frvbuyer.buyerTitle, COUNT(frvapplicationline.buyerId) AS count, SUM(frvapplicationline.value) AS sum,SUM(frvapplicationline.returnValue) AS sumret, AVG(frvapplicationline.value) AS average","FROM frvbuyer, frvapplicationline","WHERE frvbuyer.buyerId=frvapplicationline.buyerId","GROUP BY frvbuyer.buyerId","ORDER BY frvbuyer.buyerTitle",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear","buyerId","buyerId");
        EntityMenu emsf = new EntityMenu();
        emsf.setEntityStatistics(sf,ICO_STATISTICS16);
        emsf.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsf = new DataTreeNode(emsf);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsf);

                
        
        EntityStatistics[] sg = new EntityStatistics[1];
        sg[0] = new EntityStatistics("statInvoicespermonth","frvapplicationline","παραστατικά ανα μήνα","SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(frvapplicationline.value) AS \"ΣΥΝΟΛΟ\", AVG(frvapplicationline.value) AS \"Μ.Ο.\",SUM(frvapplicationline.valueReturn) AS \"ΕΠΙΣΤΡΟΦΗ\"","FROM frvapplicationline",""/*frvapplicationline.farmerId the same because we need where*/,"GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear",null,null,null,null,null);
        
        //EntityStatistics se = new EntityStatistics("applicationlinespermonth","frvapplicationline","παραστατικά ανα μήνα","SELECT returnMonth(date, 'name') AS \"μήνας\" , COUNT(*)AS \"πλήθος\", SUM(frvapplicationline.value) AS \"σύνολο\", AVG(frvapplicationline.value) AS \"Μ.Ο.\",SUM(frvapplicationline.returnValue) AS sumret","FROM frvapplicationline","WHERE"/*frvapplicationline.farmerId the same because we need where*/,"GROUP BY returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear",null,null);
        EntityMenu emsg = new EntityMenu();
        emsg.setEntityStatistics(sg,ICO_STATISTICS16);
        emsg.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsg = new DataTreeNode(emsg);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsg);

  }
  
  
     public void loadAllNodes()
   {
       addReportSettings();
       addEntityInfoNodes();
     //  addStatisticsNodes();
      // addDocumentNodes();
       addEntitiesParameters();
       addToolNodes();       
   }
  
  
  public void addToolNodes() 
  {
//        EntityStatistics[] ta = new EntityStatistics[1];
//        ta[0] = new EntityStatistics("statInvoicespermonth","frvapplicationline","παραστατικά ανα μήνα","SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(frvapplicationline.value) AS \"ΣΥΝΟΛΟ\", AVG(frvapplicationline.value) AS \"Μ.Ο.\",SUM(frvapplicationline.valueReturn) AS \"ΕΠΙΣΤΡΟΦΗ\"","FROM frvapplicationline",""/*frvapplicationline.farmerId the same because we need where*/,"GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear",null,null);
        
        //EntityStatistics se = new EntityStatistics("applicationlinespermonth","frvapplicationline","παραστατικά ανα μήνα","SELECT returnMonth(date, 'name') AS \"μήνας\" , COUNT(*)AS \"πλήθος\", SUM(frvapplicationline.value) AS \"σύνολο\", AVG(frvapplicationline.value) AS \"Μ.Ο.\",SUM(frvapplicationline.returnValue) AS sumret","FROM frvapplicationline","WHERE"/*frvapplicationline.farmerId the same because we need where*/,"GROUP BY returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"frvapplicationline.dbCompanyId",true,"frvapplicationline.dbyear",null,null);
/*        EntityMenu emta = new EntityMenu();
        emta.setEntityStatistics(ta,ICO_TOOLS);
        emta.setEntityType(ENTITY_TYPE_TOOLS);
        DataTreeNode nodeemta = new DataTreeNode(emta);
        nodeRoot.getChildFromCaption(TOOLS).addChild(nodeemta);
*/
  }
  
}
// FarmersVat

// todo features---------
// manytomany: able to create new records. When data changed(insert or delete or edit) do not allow to close or exit.
// if view n order preferences are setted (ie frvfarmer) it affects lookups (ie frvfarmer in application. So we need to apply the setted view n order in lookup.
// 2016-02-09 (program) be able to change lengths of report columns 
// 2016-02-10 (program)Reports: when 3 or more bands shows again band 2(for example in service sales in 3bands shows again the documentheader before each documentline(service))
// 2016-02-10 (program)reports: fix ordering on 2nd or 3rd band
// 2016-02-13 (program)PanelManyDataManyRec: when close panel and save all panels to ask and then save or not



//changes ------
// 2015-11-22 remove of EntityReportGroup (entitydata files)
// 2015-12-24 allow more than 1 tab with the same name (program)
// 2016-02-09 added field 'message' in dbcompany. Shows this message in status bar after login (both)
// 2016-05-15 changed the call of utilsDouble getSettingsFromDB to DialogMain (program)
// 2017-06-04 changed second button which is editlookup to listlookup with new and edit, also changed icon to yellow star (program)