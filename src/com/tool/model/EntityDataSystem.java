/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.model;

/**
 *
 * @author user
 */

import com.tool.guicomps.*;
import com.tool.utils.*;
import java.util.ArrayList;

public class EntityDataSystem extends EntityData implements Constants 
{
       DataTree dTree;
       DataTreeNode nodeRoot ;

    public static final String SYSTEM_CAT_1 = "εταιρίας, συστήματος";
    public static final String SYSTEM_CAT_2 = "παροχής υπηρεσίας";
    public static final String SYSTEM_CAT_3 = "εσόδων - εξόδων";
    //public static final String SYSTEM_CAT_4 = "κατάλογοι";       
    
       String globalYearPlusOne="";
       int intYearPlusOne=0;

       
        //----------------------------------------------------------------
   /*     
       EntityReport entReportServiceSaleDoc;
       
       
        EntityDBFields[] saleDBFields = new EntityDBFields[20];
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] saleEntityGroupOfComps =new EntityGroupOfComps[5];
        EntityGroupOfPanels[] saleEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        
        String saleQueryEditable = "SELECT * FROM saleheader";//product.productId AS \"Νο προϊόντος\", product.productName AS \"ονομασία\", product.currencyId FROM product";
        String[] fieldsOnTitleSale ={"saleheader.saleCodeNo","actiontype.actionTypeCode","saleheader.dateOfSale","customer.name"};
        String[] fieldsOnTitleCaptionSale  ={"αριθ. παρ/κού","τυπος παρ/κού","ημερομηνία","πελάτης"};      
        String[] strSaleCategories = {DATAENTRY,METRICS};

        String[] fieldsUniqueSale = null;
        
        
        EntityUpdateAdditional[] updateAdditionalActionType = new EntityUpdateAdditional[1];
        
        EntityPanel entityPanelSaleDataentry;// = new EntityPanel("ODOR","saleheader",saleDBFields,saleEntityGroupOfComps,saleEntityGroupOfPanels,"Νο πώλησης","","saleHeaderId",saleQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSale,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,updateAdditionalActionType,entReportServiceSaleDoc);      // entReportServiceSaleDoc
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.companyName AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.companyName, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProductCustomers = new EntityPanel("statProductCustomers","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT customer.customerId AS \"νο αγρότη\", customer.surname, customer.name, customer.fatherName,customer.customerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, customer","WHERE invoice.customerId = customer.customerId AND invoice.productId=","GROUP BY customer.customerId","ORDER BY customer.surname, customer.name, customer.fatherName,customer.customerAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelSale;// = new EntityPanel[] { entityPanelSaleDataentry};//,entityPanelProductStatistics,entityPanelProductCustomers,entityPanelProductBuyers,entityPanelProductSalesPerDate};
     */  
        //----------------------------------------------------------------
      /*  EntityDBFields[] customerDBFields = new EntityDBFields[24];
        EntityGroupOfComps[] customerEntityGroupOfComps = new EntityGroupOfComps[7];
        EntityGroupOfPanels[] customerEntityGroupOfPanels = new EntityGroupOfPanels[3];
        
        // same as second query in etityInfo
        //String customerQueryEditable="SELECT customer.customerId AS \"Νο πελάτη\", customer.surname AS \"επίθετο\", customer.name AS\"όνομα\", customer.fathername AS \"πατρόνυμο\", customer.customerAfm AS \"Α.Φ.Μ.\", customer.doyId, customer.idNo AS \"αρ ταυτοτ\", customer.townId, customer.address AS \"διέυθυνση\", customer.phone AS \"τηλέφωνο\" FROM customer, town WHERE customer.townId=town.townId";
        String customerQueryEditable="SELECT * FROM customer";// LEFT JOIN doy ON customer.doyId=doy.doyId";// LEFT JOIN bank ON customer.bankId=bank.bankId";        
        String[] fieldsOnTitleCustomer ={"customerId","name","vatNo"};
        String[] fieldsOnTitleCaptionCustomer  ={"Νο","όνομα","ΑΦΜ"};
        String[] strCustomerCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueCustomer = {"vatNo"};
        //STATS be careful to have in the query all the fields that are also in the title
        EntityPanel entityPanelCustomerDataentry;// = new EntityPanel("ODOR","customer",customerDBFields,customerEntityGroupOfComps,customerEntityGroupOfPanels,"Νο πελάτη","","customerId",customerQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueCustomer,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,entReportServiceSaleDoc);      
        EntityPanel entityPanelCustomerHistory;// = new EntityPanel("statCustomerHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT customer.customerId, customer.dbCompanyId, saleheader.saleHeaderId, actiontype.actionTypeCode, saleheader.saleCodeOfDocument, saleheader.saleCodeNo,saleheader.dbYearId, saleheader.dateOfSale, saleheader.isPrinted, saleheader.countTotal,saleheader.quantityTotal, saleheader.pricePreVat, saleheader.priceVat, saleheader.priceTotal","FROM customer, saleheader, actiontype","WHERE customer.customerId = saleheader.customerId AND customer.dbCompanyId = saleheader.dbCompanyId AND actiontype.actionTypeId = saleheader.actionTypeId AND customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND customer.customerId =","","ORDER BY saleHeader.dateOfSale, saleHeader.saleCodeOfDocument",false,"",false,"",entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);     
        //EntityPanel entityPanelCustomerProducts = new EntityPanel("statCustomerProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.customerId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelCustomerBuyers = new EntityPanel("statCustomerBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.customerId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelCustomerSalesPerDate = new EntityPanel("statCustomerSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.customerId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelCustomer;// = new EntityPanel[] { entityPanelCustomerDataentry,entityPanelCustomerHistory};//,entityPanelCustomerStatistics,entityPanelCustomerProducts,entityPanelCustomerBuyers,entityPanelCustomerSalesPerDate};
*/
        /*String[] deliveryFields={"customerId","dateOfApplication","permanent","dbyear","deliveryId","dbCompanyId"};
        String[] deliveryFieldsTranslation={"customerId","ημ/νία αίτησης","υπολογισμένο","dbyear","deliveryId","dbCompanyId"};
        int[] deliveryGroupOfComps = null;*/
       //---------------------------------------------------------------- 
      /*  EntityDBFields[] deliveryDBFields = new EntityDBFields[6];
        EntityGroupOfComps[] deliveryGroupOfComps = new EntityGroupOfComps[2];
        EntityGroupOfPanels[] deliveryGroupOfPanels = null;
        EntityDBFields[] deliveryDBFieldsMany = new EntityDBFields[14];
        EntityGroupOfComps[] deliveryManyGroupOfComps = null;*/
        
        // declare lookup fields here and at look up the no of fields 'intNoOfColsWhenInTable'
        /*String[] deliveryFieldsMany={"aa","buyerId","buyer","paymentTypeId","invoiceNo","date","productId","value"};
        String[] deliveryFieldsManyTranslation={"αα","Νο αγοραστή","ονομασία αγοραστής","είδος παραστατικού","αρ παρ/κού","ημερομηνία","ονομασία προϊόντος","αξία"};
        int[] deliveryManyGroupOfComps = null;
        EntityDBFields deliveryDBFieldsMany = new EntityDBFields (deliveryFieldsMany,deliveryFieldsManyTranslation,deliveryManyGroupOfComps);
         */
        
        //String[] fieldsForSumsInvoice = {"αξία","επιστροφή"};
        
        //String[] deliveryFieldsManyOnInsert={"customerId","dbyear","deliveryId","dbCompanyId","aa","buyerId","paymentTypeId","invoiceNo","date","productId","value","currencyPercentage","valueReturn"};
        //String[] deliveryFieldsManyTranslationOnInsert={"customerId","dbyear","deliveryId","dbCompanyId","αα","Νο αγοραστή","είδος παραστατικού","αρ παρ/κού","ημερομηνία","ονομασία προϊόντος","αξία","ποσοστό","επιστροφή"};
        
        //String[] deliveryWhereField ={"customerId","dbCompanyId","deliveryId","dbyear"};
        //String[] deliveryWhereValue ={"primKeyValue","globalCompanyId","globalDeliveryId","globalYear"};// global should contain global into their name (needed by panelOneDataOneRecData.checkIfNameIsWhereField)

        //String[] deliveryPrimKeyMany={"buyerId","invoiceNo","paymentTypeId","date"};
        //String[] deliveryPrimKeyManyTran={"buyerId","αρ παρ/κού","paymentTypeId","ημερομηνία"};        
        
        //deliveryQueryEditable;
        //String  deliveryQueryEditable;// = "SELECT d.customerId, d.dateOfApplication, d.dbyear, d.deliveryId, d.dbCompanyId FROM application d WHERE dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+" AND dbCompanyId="+VariablesGlobal.globalCompanyId;
        //String deliveryQueryManyReadOnly;
        //String deliveryQueryManyEditable;
        //EntityPanel entityPanelDeliveryDataentry ;
        
                
        
        //EntityPanel[] entityPanelDelivery; // initialized in loadGenericData
        
        //----------------------------------------------------------------
        /*EntityDBFields[] buyerDBFields = new EntityDBFields[8];
        EntityGroupOfComps[] buyerEntityGroupOfComps = new EntityGroupOfComps[3];
        

        //EntityGroupOfComps buyerEntityGroupOfComps =null;*/
       /* EntityGroupOfPanels[] buyerEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        
        String buyerQueryEditable = "SELECT buyerId AS \"Νο αγοραστή\", buyerTitle AS \"τίτλος αγοραστή\", buyerAfm AS \"Α.Φ.Μ.\", doyId, phone, phone2,productId, notes  FROM buyer";
        int[] fieldsOnTitleBuyer ={1,2,3};
        String[] fieldsOnTitleCaptionBuyer  ={"Νο","τίτλος","ΑΦΜ"};        
        String[] strBuyerCategories = {DATAENTRY,METRICS};
        EntityPanel entityPanelBuyerDataentry = new EntityPanel("ODOR","buyer",buyerDBFields,buyerEntityGroupOfComps,buyerEntityGroupOfPanels,"Νο αγοραστή","","buyerId",buyerQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,false,false,false,null);      
        EntityPanel entityPanelBuyerStatistics = new EntityPanel("statBuyerHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.companyName AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"sum\", AVG(invoice.value) AS \"average\", MIN(invoice.value) AS \"min\", MAX(invoice.value) AS \"max\"","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.buyerId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.companyName, invoice.deliveryId",false,"",false,"");
        EntityPanel entityPanelBuyerProducts = new EntityPanel("statBuyerProducts","STATS",null,"προϊόντα",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.buyerId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel entityPanelBuyerCustomers = new EntityPanel("statBuyerCustomers","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT customer.customerId AS \"νο αγρότη\", customer.surname, customer.name, customer.fatherName,customer.customerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, customer","WHERE invoice.customerId = customer.customerId AND invoice.buyerId=","GROUP BY customer.customerId","ORDER BY customer.surname, customer.name, customer.fatherName,customer.customerAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel entityPanelBuyerBuysPerDate = new EntityPanel("statBuyerBuysPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.buyerId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelBuyer = new EntityPanel[] { entityPanelBuyerDataentry,entityPanelBuyerStatistics,entityPanelBuyerProducts,entityPanelBuyerCustomers,entityPanelBuyerBuysPerDate};
        */
        //----------------------------------------------------------------
       
         //----------------------------------------------------------------        
     EntityDBFields[] paymenttypeLineDBFields = new EntityDBFields[2]; 
       EntityDBFields[] paymenttypeDBFields = new EntityDBFields[1];      

        
        EntityGroupOfComps[] paymenttypeEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] paymenttypeEntityGroupOfPanels = null;

        
        String paymenttypeQueryEditable = "SELECT * FROM paymenttype";
        String[] fieldsOnTitlePaymentType ={"paymentTypeId","description"};
        String[] fieldsOnTitleCaptionPaymentType  ={"Νο","ονομασία"};
        String[] fieldsUniquePaymentType = null;
        EntityCheckFields[] entityCheckFieldsPType = null;
        EntityPanel entityPanelPaymenttypeDataentry = new EntityPanel("ODOR","paymenttype",paymenttypeDBFields,paymenttypeEntityGroupOfComps,paymenttypeEntityGroupOfPanels,"Νο τρόπου πληρωμής","","paymentTypeId",paymenttypeQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniquePaymentType,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsPType,null);  
        EntityPanel[] entityPanelPaymentType = new EntityPanel[] { entityPanelPaymenttypeDataentry};

        //----------------------------------------------------------------
        EntityDBFields[] currencyLineDBFields = new EntityDBFields[3];
        EntityDBFields[] currencyDBFields = new EntityDBFields[1];
        

        EntityGroupOfComps[] currencyEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] currencyEntityGroupOfPanels = null;
        
        
        String currencyQueryEditable ="SELECT * FROM currency";
        String[] fieldsOnTitleCurrency ={"currencyId","name"};
        String[] fieldsOnTitleCaptionCurrency  ={"Νο","ονομασία"}; 
        String[] fieldsUniqueCurrency = null;       
        EntityCheckFields[] entityCheckFieldsCurrency = null;
        EntityPanel entityPanelCurrencyDataentry = new EntityPanel("ODOR","currency",currencyDBFields,currencyEntityGroupOfComps,currencyEntityGroupOfPanels,"Νο νομίσματος","","currencyId",currencyQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueCurrency,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsCurrency,null);  
        EntityPanel[] entityPanelCurrency = new EntityPanel[] { entityPanelCurrencyDataentry};
 
        //----------------------------------------------------------------
        EntityDBFields[] townDBFields = new EntityDBFields[1];
        EntityDBFields[] townLineDBFields = new EntityDBFields[5];
        
        
        EntityGroupOfComps[] townEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] townEntityGroupOfPanels = null;
        
        
        String geoCatQueryEditable="SELECT * FROM geocat";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitleGeoCat ={"geoCatId","geoCatName"};
        String[] fieldsOnTitleCaptionGeoCat  ={"Νο","ονομασία"};     
        
        String[] fieldsUniqueGeoCat = null;  
        EntityCheckFields[] entityCheckFieldsGeoCat = null;
        EntityPanel entityPanelGeoCatDataentry = new EntityPanel("ODOR","geocat",townDBFields,townEntityGroupOfComps,townEntityGroupOfPanels,"Νο πόλης","","geoCatId",geoCatQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueGeoCat,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsGeoCat,null);  
        EntityPanel[] entityPanelGeoCat = new EntityPanel[] {entityPanelGeoCatDataentry};        
        
        
 
        //----------------------------------------------------------------
   /*     //EntityDBFields[] actionTypeLineDBFields = new EntityDBFields[7];
        EntityDBFields[] actionTypeDBFields = new EntityDBFields[8];

        
        EntityGroupOfComps[] actionTypeEntityGroupOfComps = new EntityGroupOfComps[2];
        EntityGroupOfPanels[] actionTypeEntityGroupOfPanels = null;
        
        
        String actionTypeQueryEditable="SELECT * FROM actionType";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitleActionType ={"actionTypeId","actionTypeCode","actionTypeDescription"};
        String[] fieldsOnTitleCaptionActionType  ={"Νο","κωδικός","ονομασία"};     
            String[] fieldsUniqueActionType = {"actionTypeCode"};    
        EntityPanel entityPanelActionTypeDataentry = new EntityPanel("ODOR","actionType",actionTypeDBFields,actionTypeEntityGroupOfComps,actionTypeEntityGroupOfPanels,"Νο τυπου παραστατικού","","actionTypeId",actionTypeQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueActionType,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null);      
        EntityPanel[] entityPanelActionType = new EntityPanel[] {entityPanelActionTypeDataentry};        
     */   
        //----------------------------------------------------------------
        //EntityDBFields[] actionTypeLineDBFields = new EntityDBFields[7];
    /*    EntityDBFields[] printFormDBFields = new EntityDBFields[9];

        
        EntityGroupOfComps[] printFormEntityGroupOfComps = new EntityGroupOfComps[4];
        EntityGroupOfPanels[] printFormEntityGroupOfPanels = new EntityGroupOfPanels[4];
        
        
        String printFormQueryEditable="SELECT * FROM printform";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitlePrintForm ={"printFormId","printFormName"};
        String[] fieldsOnTitleCaptionPrintForm  ={"Νο","ονομασία"};     
            String[] fieldsUniquePrintForm = null;    
        EntityPanel entityPanelPrintFormDataentry = new EntityPanel("ODOR","printForm",printFormDBFields,printFormEntityGroupOfComps,printFormEntityGroupOfPanels,"Νο φόρμας","","printFormId",printFormQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniquePrintForm,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null);      
        EntityPanel[] entityPanelPrintForm = new EntityPanel[] {entityPanelPrintFormDataentry};        
        
      */          
        
        
        
        
        
        //----------------------------------------------------------------
        EntityDBFields[] activityCatLineDBFields = new EntityDBFields[2];
        EntityDBFields[] activityCatDBFields = new EntityDBFields[1];

        
        EntityGroupOfComps[] activityCatEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] activityCatEntityGroupOfPanels = null;
        
        
        String activityCatQueryEditable="SELECT * FROM activitycat";
        String[] fieldsOnTitleActivityCat ={"activityCatId","activityDescr"};
        String[] fieldsOnTitleCaptionActivityCat  ={"Νο","ονομασία"};   
        String[] fieldsUniqueActivityCat = null;     
        EntityCheckFields[] entityCheckFieldsActivityCat = null;
        EntityPanel entityPanelActivityCatDataentry = new EntityPanel("ODOR","activitycat",activityCatDBFields,activityCatEntityGroupOfComps,activityCatEntityGroupOfPanels,"Νο δραστηριότητας","","activityCatId",activityCatQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueActivityCat,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsActivityCat,null);  
        EntityPanel[] entityPanelActivityCat = new EntityPanel[] {entityPanelActivityCatDataentry};        
                
 
        //----------------------------------------------------------------
   /*     EntityDBFields[] serviceCatDBFields = new EntityDBFields[2];

        
        EntityGroupOfComps[] serviceCatEntityGroupOfComps =null;
        EntityGroupOfPanels[] serviceCatEntityGroupOfPanels = null;
        
        
        String serviceCatQueryEditable="SELECT * FROM servicecat";
        String[] fieldsOnTitleServiceCat ={"serviceCatId","catDescr"};
        String[] fieldsOnTitleCaptionServiceCat  ={"Νο","ονομασία"};  
        String[] fieldsUniqueServiceCat = null;      
        EntityPanel entityPanelServiceCatDataentry = new EntityPanel("ODOR","servicecat",serviceCatDBFields,serviceCatEntityGroupOfComps,serviceCatEntityGroupOfPanels,"Νο κατηγορίας","","serviceCatId",serviceCatQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueServiceCat,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null);      
        EntityPanel[] entityPanelServiceCat = new EntityPanel[] {entityPanelServiceCatDataentry};         
     */   
        //----------------------------------------------------------------
        EntityDBFields[] vatCatLineDBFields = new EntityDBFields[5];
        EntityDBFields[] vatCatDBFields = new EntityDBFields[1];

        
        EntityGroupOfComps[] vatCatEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] vatCatEntityGroupOfPanels = null;
        
        
        String vatCatQueryEditable="SELECT * FROM vatcat";
        String[] fieldsOnTitleVatCat ={"vatCatId","vatDescr"};
        String[] fieldsOnTitleCaptionVatCat  ={"Νο κατηγορίας ΦΠΑ","ονομασία"};   
        String[] fieldsUniqueVatCat = null;  
        EntityCheckFields[] entityCheckFieldsVatCat = null;
        EntityPanel entityPanelVatCatDataentry = new EntityPanel("ODOR","vatcat",vatCatDBFields,vatCatEntityGroupOfComps,vatCatEntityGroupOfPanels,"Νο κατηγορίας ΦΠΑ","","vatCatId",vatCatQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueVatCat,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsVatCat,null);  
        EntityPanel[] entityPanelVatCat = new EntityPanel[] {entityPanelVatCatDataentry};        
                
                
        //----------------------------------------------------------------
        EntityDBFields[] doyLineDBFields = new EntityDBFields[8];
        EntityDBFields[] doyDBFields = new EntityDBFields[1];

        EntityGroupOfComps[] doyEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] doyEntityGroupOfPanels = null;

        String doyQueryEditable="SELECT * FROM doy";//doyId AS \"Νο Δ.Ο.Υ.\", doyName AS \"ονομασία\", address AS \"διεύθυνση\", town AS \"πόλη/χωριό\", pc AS \"ΤΚ\", tel1 AS \"τηλ 1\", tel2 AS \"τηλ 2\", fax AS \"φαξ\" FROM doy";
        String[] fieldsOnTitleDoy ={"doyId","doyName"};
        String[] fieldsOnTitleCaptionDoy  ={"Νο","ονομασία"};   
        String[] fieldsUniqueDoy = null;    
        EntityCheckFields[] entityCheckFieldsDoy = null;
        EntityPanel entityPanelDoyDataentry = new EntityPanel("ODOR","doy",doyDBFields,doyEntityGroupOfComps,doyEntityGroupOfPanels,"Νο Δ.Ο.Υ.","","doyId",doyQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDoy,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsDoy,null);  
        EntityPanel[] entityPanelDoy = new EntityPanel[] { entityPanelDoyDataentry};  
        
        // same as DialogLogin
        //----------------------------------------------------------------
        EntityDBFields[] dbCompanyDBFields = new EntityDBFields[22];        
        EntityGroupOfComps[] dbCompanyEntityGroupOfComps= new EntityGroupOfComps[7];
              EntityDBFields[] dbyearLineDBFields = new EntityDBFields[5];

        EntityGroupOfPanels[] dbCompanyEntityGroupOfPanels = new EntityGroupOfPanels[2];
        EntityUpdateAdditional[] updateAdditionalDbCompany = new EntityUpdateAdditional[1];
        
        String dbCompanyQueryEditable="SELECT * FROM dbcompany";//dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.companyName AS\"τίτλος\", dbcompany.companyVatNo AS\"Α.Φ.Μ.\", dbcompany.doyId ,dbcompany.geoCatId,  dbcompany.bankId , dbcompany.bankAccount , dbcompany.bankAccountIBAN , dbcompany.notes FROM dbcompany";
        String[] fieldsOnTitleDbCompany ={"dbCompanyId","companyName","companyVatNo"};
        String[] fieldsOnTitleCaptionDbCompany  ={"Νο","τίτλος","ΑΦΜ"};  
        String[] fieldsUniqueDbCompany = {"companyVatNo"};      
        EntityCheckFields[] entityCheckFieldsDBCompany = null;
        EntityPanel entityPanelDbCompanyDataentry = new EntityPanel("ODOR","dbcompany",dbCompanyDBFields,dbCompanyEntityGroupOfComps,dbCompanyEntityGroupOfPanels,"Νο εταιρίας","","dbCompanyId",dbCompanyQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbCompany,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,updateAdditionalDbCompany,null,null,entityCheckFieldsDBCompany,null);  
        EntityPanel[] entityPanelDbCompany = new EntityPanel[] {entityPanelDbCompanyDataentry};          
        
        //----------------------------------------------------------------
        EntityDBFields[] dbuserDBFields = new EntityDBFields[7];

        EntityGroupOfComps dbuserEntityGroupOfComps[] =  new EntityGroupOfComps[1];
        EntityGroupOfPanels dbuserEntityGroupOfPanels[] = null;
        
        
        String dbUserQueryEditable="SELECT * FROM dbuser";
        String[] fieldsOnTitleDbuser ={"userId","userName","nameOfUser"};
        String[] fieldsOnTitleCaptionDbuser  ={"Νο","user","πλήρες όνομα"};    
        String[] fieldsUniqueDbUser = {"userName"};      
        EntityCheckFields[] entityCheckFieldsDBUser = null;
        EntityPanel entityPanelDbuserDataentry = new EntityPanel("ODOR","dbuser",dbuserDBFields,dbuserEntityGroupOfComps,dbuserEntityGroupOfPanels,"Νο χρήστη","","userId",dbUserQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbUser,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsDBUser,null);  
        EntityPanel[] entityPanelDbuser = new EntityPanel[] { entityPanelDbuserDataentry}; 

        //----------------------------------------------------------------
        
     EntityDBFields[] dbUserRolesDBFields = new EntityDBFields[2]; 
       
        
        EntityGroupOfComps[] dbUserRolesEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] dbUserRolesEntityGroupOfPanels = null;

        
        String dbUserRolesQueryEditable = "SELECT * FROM dbUserRole";
        String[] dbUserRolesFieldsOnTitle ={"userRoleId","roleDescr"};
        String[] dbUserRolesFieldsOnTitleCaption  ={"Νο","ονομασία"};
        String[] dbUserRolesFieldsUnique = null;
        EntityCheckFields[] dbUserRolesEntityCheckFields = null;
        EntityPanel dbUserRolesEntityPanelDataentry = new EntityPanel("ODOR","dbUserRole",dbUserRolesDBFields,dbUserRolesEntityGroupOfComps,dbUserRolesEntityGroupOfPanels,"Νο ρόλου","","userRoleId",dbUserRolesQueryEditable,"βασικά στοιχεία",null, false, true,dbUserRolesFieldsUnique,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,dbUserRolesEntityCheckFields,null);  
        EntityPanel[] dbUserRolesEntityPanel = new EntityPanel[] { dbUserRolesEntityPanelDataentry};        
        
        
        
        //EntityDBFields[] dbyearDBFields = new EntityDBFields[1];
       // EntityDBFields[] dbyearLineDBFields = new EntityDBFields[5];
/*        //EntityDBFields[] dbYearDeliveryDBFields= new EntityDBFields[5];

        //EntityGroupOfComps[] dbyearEntityGroupOfComps =new EntityGroupOfComps[1];
        EntityGroupOfPanels[] dbyearEntityGroupOfPanels = null;
        
        
        String dbyearQueryEditable="SELECT dbYearId AS \"Νο χρήσης\", dbyear AS\"χρήση\", dbCompanyId AS \"Νο εταιρίας\" FROM dbyear";
        String[] fieldsOnTitleDbyear ={"dbYearId","dbyear"};
        String[] fieldsOnTitleCaptionDbyear  ={"Νο","χρήση"};     
        String[] fieldsUniqueDbYear = {"dbyear"};  
        EntityCheckFields[] entityCheckFieldsDBYear = null;
        EntityPanel entityPanelDbyearDataentry = new EntityPanel("ODOR","dbyear",dbyearDBFields,dbyearEntityGroupOfComps,dbyearEntityGroupOfPanels,"Νο χρήσης","","dbYearId",dbyearQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbYear,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsDBYear,null);  
        EntityPanel[] entityPanelDbyear = new EntityPanel[] { entityPanelDbyearDataentry};         
*/        
        //----------------------------------------------------------------
        
        EntityGroupOfComps[] bankEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] bankEntityGroupOfPanels = null;
        
       EntityDBFields[] bankDBFields = new EntityDBFields[1];
       EntityDBFields[] bankLineDBFields = new EntityDBFields[3];

        String bankQueryEditable="SELECT * FROM bank";//bankId AS \"Νο τράπεζας\", bankBranch FROM bank";
        String[] fieldsOnTitleBank ={"bankId","bankBranch"};
        String[] fieldsOnTitleCaptionBank  ={"Νο","ονομασία"}; 
        String[] fieldsUniqueBank = null;     
        EntityCheckFields[] entityCheckFieldsBank = null;
        EntityPanel entityPanelBankDataentry = new EntityPanel("ODOR","bank",bankDBFields,bankEntityGroupOfComps,bankEntityGroupOfPanels,"Νο τράπεζας","","bankId",bankQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueBank,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsBank,null);  
        EntityPanel[] entityPanelBank = new EntityPanel[] { entityPanelBankDataentry};  
        
        
        
        //-----------------------------
       
        /*EntityGroupOfComps[] dbDeliveryEntityGroupOfComps =null;
        EntityGroupOfPanels[] dbDeliveryEntityGroupOfPanels = null;
        
        EntityDBFields[] dbDeliveryDBFields = new EntityDBFields[2];
        String dbDeliveryQueryEditable="SELECT deliveryId AS \"Νο αποστολής\", description AS \"περιγραφή\" FROM dbDelivery";
        int[] fieldsOnTitleDbDelivery = {1,2};
        String[] fieldsOnTitleCaptionDbDelivery  ={"Νο","περιγραφή"};        
        EntityPanel entityPaneDbDeliveryDataentry = new EntityPanel("ODOR","dbdelivery",dbDeliveryDBFields,dbDeliveryEntityGroupOfComps,dbDeliveryEntityGroupOfPanels,"Νο αποστολής","","deliveryId",dbDeliveryQueryEditable,"βασικά στοιχεία",null, false, true,false,false,false,null);      
        EntityPanel[] entityPanelDbDelivery = new EntityPanel[] { entityPaneDbDeliveryDataentry};  */

        //----------------------------------------------------------------
        
        /*EntityGroupOfComps[] parameterEntityGroupOfComps = new EntityGroupOfComps[3];
        EntityGroupOfPanels[] parameterEntityGroupOfPanels = null;
        
       
       EntityDBFields[] parameterDBFields = new EntityDBFields[5];

        String parameterQueryEditable="SELECT * FROM parameter";
        int[] fieldsOnTitleParameter ={2};
        String[] fieldsOnTitleCaptionParameter  ={"εταιρία"};        
        EntityPanel entityPanelParameterDataentry = new EntityPanel("ODOR","parameter",parameterDBFields,parameterEntityGroupOfComps,parameterEntityGroupOfPanels,"Νο παραμέτρων","","parameterId",parameterQueryEditable,"βασικά στοιχεία",null, false, true,false,false,false,null);      
        EntityPanel[] entityPanelParameter = new EntityPanel[] { entityPanelParameterDataentry};*/  
        
        //- report initialisations
EntityFilterSettings[] salesDocumentErs = new EntityFilterSettings[6];        
EntityGroupOfComps[] saleDocumentGroupOfComps = new EntityGroupOfComps[3];
       int[] invoicesaSelected = null;//{1,2,3,4,0,0,0,0,0,0,11,12,0,14,};
       int[] fileOrderby ={1};
       boolean[] boolSettingsReportDoc = {true,true,true,true,true}; 
      boolean[] boolSettingsReportCustomerfileA = {true,true,true,true,true}; 
      boolean[] boolSettingsReportCustomerfileB = {true,true,true,true,true}; 
       int[] intSettingsReportCustomerfile={0,0,0,0};
 //       EntityReportBandField[] entityReportBandFieldsSaleLineA =new EntityReportBandField[10];          
 //       EntityReportBandField[] entityReportBandFieldsSaleHeaderB =new EntityReportBandField[7];
       EntityReportBand[] reportBandCustomerServiceSaleDoc = new EntityReportBand[2];  //not in use
          
     //-----------------------------------------------------------------------------
       
         EntityDBFields[] companySetSerSalesDBFields = new EntityDBFields[9];

        EntityGroupOfComps[] companySetSerSalesEntityGroupOfComps = new EntityGroupOfComps[6];
        EntityGroupOfPanels[] companySetSerSalesEntityGroupOfPanels = null;
        
        
        String companySetSerSalesQueryEditable ="SELECT * FROM dbcompanyset";
        String[] companySetSerSalesFieldsOnTitle ={"dbCompanyId"};
        String[] companySetSerSalesFieldsOnTitleCaption  ={"Νο"}; 
        String[] companySetSerSalesFieldsUnique = null;  
        EntityCheckFields[] companySetSerSalesEntityCheckFields = null;
        EntityPanel companySetSerSalesEntityPanelDataentry = new EntityPanel("ODOR","dbcompanyset",companySetSerSalesDBFields,companySetSerSalesEntityGroupOfComps,companySetSerSalesEntityGroupOfPanels,"dbcompanyid","","dbcompanyid",companySetSerSalesQueryEditable,"βασικά στοιχεία",null, false, true,companySetSerSalesFieldsUnique,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,companySetSerSalesEntityCheckFields,null);      
        EntityPanel[] companySetSerSalesEntityPanel = new EntityPanel[] { companySetSerSalesEntityPanelDataentry};
       
        
        //----------------------------------------------------------------
        //EntityDBFields[] actionTypeLineDBFields = new EntityDBFields[7];
        EntityDBFields[] actionTypeDBFields = new EntityDBFields[11];

        
        EntityGroupOfComps[] actionTypeEntityGroupOfComps = new EntityGroupOfComps[2];
        EntityGroupOfPanels[] actionTypeEntityGroupOfPanels = null;
        
        
        String actionTypeQueryEditable="SELECT * FROM actiontype";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitleActionType ={"actionTypeId","actionTypeCode","actionTypeDescription"};
        String[] fieldsOnTitleCaptionActionType  ={"Νο","κωδικός","ονομασία"};     
            String[] fieldsUniqueActionType = {"actionTypeCode"};  
            EntityCheckFields[] entityCheckFieldsActionType =null;
        EntityPanel entityPanelActionTypeDataentry = new EntityPanel("ODOR","actiontype",actionTypeDBFields,actionTypeEntityGroupOfComps,actionTypeEntityGroupOfPanels,"Νο τυπου παραστατικού","","actionTypeId",actionTypeQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueActionType,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsActionType,null);  
        EntityPanel[] entityPanelActionType = new EntityPanel[] {entityPanelActionTypeDataentry};     

//-------------------------------------transction types below---------------------------
        EntityDBFields[] actionseriesDBFields = new EntityDBFields[11];
        
        EntityGroupOfComps[] actionseriesEntityGroupOfComps = new EntityGroupOfComps[2];
        EntityGroupOfPanels[] actionseriesEntityGroupOfPanels = null;
        
        
        String actionseriesQueryEditable ="SELECT * FROM actionseries";
        String[] actionseriesFieldsOnTitle ={"actionseriesId","descr"};
        String[] actionseriesFieldsOnTitleCaption  ={"Νο","ονομασία"}; 
        String[] actionseriesFieldsUnique = null;    
        EntityCheckFields[] actionseriesEntityCheckFields =null;
        EntityPanel actionseriesEntityPanelDataentry = new EntityPanel("ODOR","actionseries",actionseriesDBFields,actionseriesEntityGroupOfComps,actionseriesEntityGroupOfPanels,"Νο σειράς","","actionseriesId",actionseriesQueryEditable,"βασικά στοιχεία",null, false, true,actionseriesFieldsUnique,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,actionseriesEntityCheckFields,null);  
        EntityPanel[] actionseriesEntityPanel = new EntityPanel[] { actionseriesEntityPanelDataentry};
 
        //----------------------------------------------------------------
        EntityDBFields[] actiontraderDBFields = new EntityDBFields[4];
        
        EntityGroupOfComps[] actiontraderEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] actiontraderEntityGroupOfPanels = null;
        
        
        String actiontraderQueryEditable ="SELECT * FROM actiontrader";
        String[] actiontraderFieldsOnTitle ={"actiontraderId","descr"};
        String[] actiontraderFieldsOnTitleCaption  ={"Νο","ονομασία"}; 
        String[] actiontraderFieldsUnique = null;    
        EntityCheckFields[] actiontraderEntityCheckFields =null;
        EntityPanel actiontraderEntityPanelDataentry = new EntityPanel("ODOR","actiontrader",actiontraderDBFields,actiontraderEntityGroupOfComps,actiontraderEntityGroupOfPanels,"Νο κίνησης συναλλασσόμενου","","actiontraderId",actiontraderQueryEditable,"βασικά στοιχεία",null, false, true,actiontraderFieldsUnique,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,actiontraderEntityCheckFields,null);  
        EntityPanel[] actiontraderEntityPanel = new EntityPanel[] { actiontraderEntityPanelDataentry};
        
 
        //----------------------------------------------------------------        
        EntityDBFields[] actionstockDBFields = new EntityDBFields[6];
        
        EntityGroupOfComps[] actionstockEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] actionstockEntityGroupOfPanels = null;
        
        
        String actionstockQueryEditable ="SELECT * FROM actionstock";
        String[] actionstockFieldsOnTitle ={"actionstockId","descr"};
        String[] actionstockFieldsOnTitleCaption  ={"Νο","ονομασία"}; 
        String[] actionstockFieldsUnique = null;    
        EntityCheckFields[] actionstockEntityCheckFields =null;
        EntityPanel actionstockEntityPanelDataentry = new EntityPanel("ODOR","actionstock",actionstockDBFields,actionstockEntityGroupOfComps,actionstockEntityGroupOfPanels,"Νο τύπου προϊόντος","","actionstockId",actionstockQueryEditable,"βασικά στοιχεία",null, false, true,actionstockFieldsUnique,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,actionstockEntityCheckFields,null);  
        EntityPanel[] actionstockEntityPanel = new EntityPanel[] { actionstockEntityPanelDataentry};        
        //-----------------------------        
        
        
        
        
        
        
        
        
        
        
        //---------------------------------------------------------------------------
         EntityDBFields[] companySetEsoExoDBFields = new EntityDBFields[5];

        EntityGroupOfComps[] companySetEsoExoEntityGroupOfComps = new EntityGroupOfComps[4];
        EntityGroupOfPanels[] companySetEsoExoEntityGroupOfPanels = null;
        
        
        String companySetEsoExoQueryEditable ="SELECT * FROM dbcompanyset";
        String[] companySetEsoExoFieldsOnTitle ={"dbCompanyId"};
        String[] companySetEsoExoFieldsOnTitleCaption  ={"Νο"}; 
        String[] companySetEsoExoFieldsUnique = null;  
        EntityCheckFields[] companySetEsoExoEntityCheckFields = null;
        EntityPanel companySetEsoExoEntityPanelDataentry = new EntityPanel("ODOR","dbcompanyset",companySetEsoExoDBFields,companySetEsoExoEntityGroupOfComps,companySetEsoExoEntityGroupOfPanels,"dbcompanyid","","dbcompanyid",companySetEsoExoQueryEditable,"βασικά στοιχεία",null, false, true,companySetEsoExoFieldsUnique,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,companySetEsoExoEntityCheckFields,null);      
        EntityPanel[] companySetEsoExoEntityPanel = new EntityPanel[] { companySetEsoExoEntityPanelDataentry};        
        
       
   public EntityDataSystem()
   {
     
          // need them
   	  dTree = new DataTree();
   	  nodeRoot = new DataTreeNode("root");
   	  dTree.setRootElement(nodeRoot);

          // deliveryQueryEditable = "SELECT d.customerId, d.dateOfApplication, d.dbyear, d.deliveryId, d.dbCompanyId FROM application d WHERE dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+" AND dbCompanyId="+VariablesGlobal.globalCompanyId;

       //System.out.println("EntityData"+entityPanelCustomer[0].getType());

        dbyearLineDBFields[0] = new EntityDBFields("dbyear","dbYearId","Νο χρήσης",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");       
        dbyearLineDBFields[1] = new EntityDBFields("dbyear","dbCompanyId","Νο εταιρίας",0,"java.lang.String",4,FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,""); //uses FIELD_PRIMARY_KEY_FROM_PARENTTABLE, not:VariablesGlobal.globalCompanyId
        dbyearLineDBFields[2] = new EntityDBFields("dbyear","dbYearDescr","περιγραφή χρήσης",0,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        dbyearLineDBFields[3] = new EntityDBFields("dbyear","dbDateFrom","από ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        dbyearLineDBFields[4] = new EntityDBFields("dbyear","dbDateTo","εως ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
//dbyearDBFields[3] = new EntityDBFields("dbyear","yearDelivery","αποστολές του έτους",1,"table",FIELD_VISIBLE_AND_EDITABLE,"dbYearDelivery",200,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,dbYearDeliveryDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT dbYearDeliveryId, dbYearId,aa,description,dbCompanyId FROM dbYearDelivery WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  ORDER BY aa",null);
        
        
        
        //dbyearEntityGroupOfComps[1] = new EntityGroupOfComps("αποστολές έτους",1,0);

        
        
        String[] updateQueryFieldsCompany ={"dbCompanyId"};
        //updateAdditionalParameters[0] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,"INSERT INTO parameter (parameterId,dbCompanyId,lengthOfCodeOfDocuments,charOfDecimal,lengthOfDecimalPrice) VALUES(1,#, 4,',',2)" ,updateQueryFieldsParameters);
        //updateAdditionalDbCompany[0] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,"INSERT INTO dbyear (dbYearId,dbyear,dbCompanyId) VALUES(1,"+VariablesGlobal.globalYear+",#)" ,updateQueryFieldsCompany);
        updateAdditionalDbCompany[0] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,"INSERT INTO dbcompanyset (`dbCompanyId`, `sersaleCodeOfDocumentsLength`, `sersaleWithHoldingTaxAmountGreaterThan`, `sersaleWithHoldingTaxRate`, `sersaleCheckAFMOfSaleAndComp`, `sersaleCopyCustomerNameToSaleComment`, `esoexoCheckAFMOfEsoExoAndComp`,`esoexoCopyTraderNameToEsoexoComment`) VALUES ('#', '7', '300', '20','1','1','1','1') " ,updateQueryFieldsCompany);
        
       	dbCompanyDBFields[0] = new EntityDBFields("dbcompany","dbCompanyId","Νο εταιρίας",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	dbCompanyDBFields[1] = new EntityDBFields("dbcompany","companyName","τίτλος",0,"java.lang.String",45,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[2] = new EntityDBFields("dbcompany","companyVatNo","Α.Φ.Μ.",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_AFM,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[3] = new EntityDBFields("dbcompany","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[4] = new EntityDBFields("dbcompany","geoCatId","γεωγραφική κατηγορία",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"geocat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbCompanyDBFields[5] = new EntityDBFields("dbcompany","categoryDescription","δραστηριότητα",0,"java.lang.String",45,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbCompanyDBFields[6] = new EntityDBFields("dbcompany","active","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");
        dbCompanyDBFields[7] = new EntityDBFields("dbcompany","activityCatId","δραστηριότητα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"activityCat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        dbCompanyDBFields[8] = new EntityDBFields("dbcompany","typeOfVatId","κατηγορία ΦΠΑ",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
       	dbCompanyDBFields[9] = new EntityDBFields("dbcompany","currencyId","νόμισμα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"currency",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	
        dbCompanyDBFields[10] = new EntityDBFields("dbcompany","addressCity","πόλη/χωριό",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbCompanyDBFields[11] = new EntityDBFields("dbcompany","addressStreet","οδός",1,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbCompanyDBFields[12] = new EntityDBFields("dbcompany","addressPC","ΤΚ",1,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbCompanyDBFields[13] = new EntityDBFields("dbcompany","addressState","νομός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        dbCompanyDBFields[14] = new EntityDBFields("dbcompany","bankId","τράπεζα",2,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[15] = new EntityDBFields("dbcompany","bankAccount","λογαριασμός",2,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	dbCompanyDBFields[16] = new EntityDBFields("dbcompany","bankAccountIBAN","ΙΒΑΝ",2,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        
        dbCompanyDBFields[17] = new EntityDBFields("dbcompany","notes","σημειώσεις",3,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        dbCompanyDBFields[18] = new EntityDBFields("dbcompany","message","μήνυμα",4,"java.lang.String",100,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        
        //dbCompanyDBFields[19] = new EntityDBFields("dbcompany","lengthOfCodeOfDocuments","μήκος κωδικού παραστατικών",5,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"7","");
        //dbCompanyDBFields[20] = new EntityDBFields("dbcompany","amountIfGreaterThenCalculateWithHoldingTax","ελαχιστο σύνολο για υπολογισμό % φόρου παρακράτησης",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"300","");
        //dbCompanyDBFields[21] = new EntityDBFields("dbcompany","rateOfWithHoldingTax","% φόρου παρακράτησης",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"20","");
        dbCompanyDBFields[19] = new EntityDBFields("dbcompany","charOfDecimal","χαρακτήρας δεκαδικών",5,"java.lang.String",1,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCdecimalchar",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,",","");
        //dbCompanyDBFields[4] = new EntityDBFields("dbcompany","charOfThousands","χαρακτήρας χιλιάδων",0,"java.lang.String",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        dbCompanyDBFields[20] = new EntityDBFields("dbcompany","lengthOfDecimalPrice","μήκος δεκαδικών αξίας",5,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"2","");                
        String[] selectQueryFieldsCompany ={"dbCompanyId"};
        dbCompanyDBFields[21] = new EntityDBFields("dbcompany","dbyear","χρήσεις",6,"table",FIELD_VISIBLE_AND_EDITABLE,"dbyear",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,dbyearLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT * FROM dbyear WHERE dbCompanyId = # ORDER BY dbyear",selectQueryFieldsCompany,null);     //String[] childTableFieldsForSumsIn   
        
        dbCompanyEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET);
        dbCompanyEntityGroupOfComps[1]= new EntityGroupOfComps("διεύθυνση",4,0,FONT_SIZE_NOT_SET);
        dbCompanyEntityGroupOfComps[2]= new EntityGroupOfComps("λογαριασμός τράπεζας",6,0,FONT_SIZE_NOT_SET);
        dbCompanyEntityGroupOfComps[3]= new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET);
        dbCompanyEntityGroupOfComps[4]= new EntityGroupOfComps("μήνυμα",2,0,FONT_SIZE_NOT_SET);
        //dbCompanyEntityGroupOfComps[5]= new EntityGroupOfComps("παραστατικά",6,0);
        dbCompanyEntityGroupOfComps[5]= new EntityGroupOfComps("δεκαδικά (Χρειάζεται να εισέλθετε ξανα στην εφαρμογή για να εφαρμοσθούν οι αλλαγές.)",4,0,FONT_SIZE_NOT_SET);
        dbCompanyEntityGroupOfComps[6] = new EntityGroupOfComps("χρήσεις (Χρειάζεται να εισέλθετε ξανα στην εφαρμογή για να εφαρμοσθούν οι αλλαγές.)",4,1,FONT_SIZE_NOT_SET);

        
        dbCompanyEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        dbCompanyEntityGroupOfPanels[1] = new EntityGroupOfPanels("χρήσεις",1);        
        
        
        
        
        
        dbuserDBFields[0] = new EntityDBFields("dbUser","userId","Νο χρήστη",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbuserDBFields[1] = new EntityDBFields("dbUser","username","όνομα χρήστη",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbuserDBFields[2] = new EntityDBFields("dbUser","password","password",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbuserDBFields[3] = new EntityDBFields("dbUser","nameOfUser","πλήρες όνομα χρήστη",0,"java.lang.String",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbuserDBFields[4] = new EntityDBFields("dbUser","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");        
        dbuserDBFields[5] = new EntityDBFields("dbUser","email","email",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbuserDBFields[6] = new EntityDBFields("dbUser","userRoleId","ρόλος",0,"java.lang.Integer",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"dbuserrole",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //dbuserDBFields[4] = new EntityDBFields("dbCompanyId","Νο εταιρίας",0, FIELD_SUGGEST,FIELD_VALIDATION_NO);
        dbuserEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET);
        
        
        //dbYearDeliveryDBFields[0] = new EntityDBFields("dbYearDelivery","dbYearId","Νο χρήσης",0,"java.lang.String",10, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //dbYearDeliveryDBFields[1] = new EntityDBFields("dbYearDelivery","aa","αα",0,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //dbYearDeliveryDBFields[2] = new EntityDBFields("dbYearDelivery","dbYearDeliveryId","dbYearDeliveryId",0,"java.lang.String",8,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //dbYearDeliveryDBFields[3] = new EntityDBFields("dbYearDelivery","description","περιγραφή",0,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //dbYearDeliveryDBFields[4] = new EntityDBFields("dbYearDelivery","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalCompanyId);
        
        dbUserRolesDBFields[0] = new EntityDBFields("dbUserRole","userRoleId","Νο ρόλου",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbUserRolesDBFields[1] = new EntityDBFields("dbUserRole","roleDescr","περιγραφή",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        
        dbUserRolesEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET);

       

        
        
        
   	activityCatLineDBFields[0] = new EntityDBFields("activitycat","activityCatId","Νο δραστηριότητας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	activityCatLineDBFields[1] = new EntityDBFields("activitycat","activityDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        activityCatDBFields[0] = new EntityDBFields("activitycatheader","activitycat","δραστηριότητες",0,"table",FIELD_VISIBLE_AND_EDITABLE,"activitycat",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,activityCatLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT activityCatId AS\"Νο δραστηριότητας\", activityDescr AS\"δραστηριότητα\" FROM activitycat ORDER BY activitycat.activityDescr",null,null);     
        activityCatEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);
        
       	/*serviceCatDBFields[0] = new EntityDBFields("servicecat","serviceCatId","Νο κατηγορίας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);
       	serviceCatDBFields[1] = new EntityDBFields("servicecat","catDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
          */ 
        
        vatCatLineDBFields[0] = new EntityDBFields("vatcat","vatCatId","Νο κατηγορίας ΦΠΑ",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	vatCatLineDBFields[1] = new EntityDBFields("vatcat","vatDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        vatCatLineDBFields[2] = new EntityDBFields("vatcat","vatPercentage","ποσοστό",0,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        vatCatLineDBFields[3] = new EntityDBFields("vatcat","vatReducedCat","μειωμένος συντελεστής",0,"java.lang.Integer",16,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"vatcat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        vatCatLineDBFields[4] = new EntityDBFields("vatcat","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                        
        vatCatDBFields[0] = new EntityDBFields("vatcatheader","vatcat","κατηγορίες ΦΠΑ",0,"table",FIELD_VISIBLE_AND_EDITABLE,"vatcat",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,vatCatLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT * FROM vatcat ORDER BY vatCatId",null,null);     //String[] childTableFieldsForSumsIn   
        
        vatCatEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);    
                
        //------------------------- 

       /*  actionTypeDBFields[0] = new EntityDBFields("actiontype","actionTypeId","Νο τύπου παραστατικού",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);
         actionTypeDBFields[1] = new EntityDBFields("actiontype","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
         actionTypeDBFields[2] = new EntityDBFields("actiontype","actionTypeDescription","ονομασία",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
         actionTypeDBFields[3] = new EntityDBFields("actiontype","actionTypeCode","κωδικός παραστατικού",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
         actionTypeDBFields[4] = new EntityDBFields("actiontype","typeNextNumber","επόμενος αριθμός παραστατικού",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
         actionTypeDBFields[5] = new EntityDBFields("actiontype","actionTypeCatId","κατηγορία παραστατικού",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCActionTypeCat", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
         actionTypeDBFields[6] = new EntityDBFields("actiontype","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true");                
         actionTypeDBFields[7] = new EntityDBFields("actiontype","printformId","φόρμα εκτύπωσης",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"printform",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
       // actionTypeDBFields[0] = new EntityDBFields("actiontypeheader","actiontype","τύποι παραστατικών",0,"table",FIELD_VISIBLE_AND_EDITABLE,"actiontype",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,actionTypeLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT * FROM actiontype ORDER BY actionTypeId",null);     //String[] childTableFieldsForSumsIn   
        
        actionTypeEntityGroupOfComps[0] = new EntityGroupOfComps("ιδιότητες",4,0);         
        actionTypeEntityGroupOfComps[1] = new EntityGroupOfComps("εκτύπωση",4,0);    
        */
        
         //-------------------------        
        /* printFormDBFields[0] = new EntityDBFields("printForm","printFormId","Νο φόρμας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);
         printFormDBFields[1] = new EntityDBFields("actiontype","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
         printFormDBFields[2] = new EntityDBFields("printForm","printFormName","φόρμα εκτύπωσης",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"");
         printFormDBFields[3] = new EntityDBFields("printForm","isActive","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true");                
        */ // printFormDBFields[4] = new EntityDBFields("printForm","printFormLaser","",1,"java.lang.String",45000,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
         //printFormDBFields[5] = new EntityDBFields("printForm","printFormDotMatrix","",2,"java.lang.String",45000,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        // printFormDBFields[6] = new EntityDBFields("printForm","imageTop","εικόνα πάνω",3,"java.awt.image.BufferedImage",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);        
        // printFormDBFields[7] = new EntityDBFields("printForm","imageBottom","εικόνα κάτω",3,"java.awt.image.BufferedImage",40//,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);                 
         //printFormDBFields[8] = new EntityDBFields("printForm","imageBackground","εικόνα πίσω",3,"java.awt.image.BufferedImage",40/,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);                 
         
        
        
       /* printFormEntityGroupOfComps[0] = new EntityGroupOfComps("ιδιότητες",4,0);  
        printFormEntityGroupOfComps[1] = new EntityGroupOfComps("εκτύπωση για laser - γραφικά",2,1);  
        printFormEntityGroupOfComps[2] = new EntityGroupOfComps("εκτύπωση για dot matrix - κρουστικός",2,2);  
        printFormEntityGroupOfComps[3] = new EntityGroupOfComps("εικόνες για laser",2,3); 
        
        
        printFormEntityGroupOfPanels[0] = new EntityGroupOfPanels("ιδιότητες",1);
        printFormEntityGroupOfPanels[1] = new EntityGroupOfPanels("φορμα laser",1);
        printFormEntityGroupOfPanels[2] = new EntityGroupOfPanels("φόρμα dot matrix",1);
        printFormEntityGroupOfPanels[3] = new EntityGroupOfPanels("εικόνες φόρμας",1);
        */
         //-------------------------        
        
        
        
        
        
        
       	paymenttypeLineDBFields[0] = new EntityDBFields("paymentType","paymentTypeId","Νο τρόπου πληρωμής",0,"java.lang.Integer",1, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	paymenttypeLineDBFields[1] = new EntityDBFields("paymentType","description","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	//paymenttypeDBFields[2] = new EntityDBFields("paymentType","abbreviation","συντομογραφία",0,"java.lang.String",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,true,FIELD_SUGGEST,FIELD_VALIDATION_NO,false,null);

        paymenttypeDBFields[0] = new EntityDBFields("paymentTypeheader","paymentType","τύποι πληρωμής",0,"table",FIELD_VISIBLE_AND_EDITABLE,"paymentType",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,paymenttypeLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT paymentTypeId AS \"Νο\", description AS \"τρόπος πληρωμής\" FROM paymentType ORDER BY description",null,null);     //String[] childTableFieldsForSumsIn   
        paymenttypeEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);
        
        currencyLineDBFields[0] = new EntityDBFields("currency","currencyId","Νο νομίσματος",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        currencyLineDBFields[1] = new EntityDBFields("currency","name","ονομασία",0,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //currencyLineDBFields[2] = new EntityDBFields("currency","countOfDecimals","δεκαδικά",0,"java.lang.Integer",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        currencyLineDBFields[2] = new EntityDBFields("currency","active","ενεργό",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
   	// sample-> vatCatLineDBFields[4] = new EntityDBFields("vatcat","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true");                        
        
        currencyDBFields[0] = new EntityDBFields("currencyheader","currency","νομίσματα",0,"table",FIELD_VISIBLE_AND_EDITABLE,"currency",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,currencyLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT currencyId , name , active FROM currency ORDER BY currencyId",null,null);     //String[] childTableFieldsForSumsIn   
        currencyEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);  
        
        
        townLineDBFields[0] = new EntityDBFields("geocat","geoCatId","Νο πόλης",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        townLineDBFields[1] = new EntityDBFields("geocat","geoCatName","πόλη/χωριό",0,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        townLineDBFields[2] = new EntityDBFields("geocat","state","νομός",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        townLineDBFields[3] = new EntityDBFields("geocat","postCode","T.K.",0,"java.lang.String",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        townLineDBFields[4] = new EntityDBFields("geocat","phoneCode","κωδ τηλ",0,"java.lang.String",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

        townDBFields[0] = new EntityDBFields("townheader","geocat","πόλεις/χωριά",0,"table",FIELD_VISIBLE_AND_EDITABLE,"geocat",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,townLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\", state AS\"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM geocat ORDER BY geoCatName",null,null);     //String[] childTableFieldsForSumsIn   
        
        townEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);     
        
       /* parameterDBFields[0] = new EntityDBFields("parameter","parameterId","Νο παραμέτρου",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);
        parameterDBFields[1] = new EntityDBFields("parameter","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        parameterDBFields[2] = new EntityDBFields("parameter","lengthOfCodeOfDocuments","μήκος κωδικού παραστατικών",1,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        parameterDBFields[3] = new EntityDBFields("parameter","charOfDecimal","χαρακτήρας δεκαδικών",2,"java.lang.String",1,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //parameterDBFields[4] = new EntityDBFields("parameter","charOfThousands","χαρακτήρας χιλιάδων",0,"java.lang.String",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        parameterDBFields[4] = new EntityDBFields("parameter","lengthOfDecimalPrice","μήκος δεκαδικών αξίας",2,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);                
        //parameterDBFields[5] = new EntityDBFields("parameter","dateFormatReading","μορφή ημερομηνίας σε ανάγνωση",3,"java.lang.String",28,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //parameterDBFields[6] = new EntityDBFields("parameter","dateFormatEditing","μορφή ημερομηνίας σε επεξεγασία",3,"java.lang.String",28,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        
        parameterEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",4,0);
        parameterEntityGroupOfComps[1]= new EntityGroupOfComps("παραστατικά",4,0);
        parameterEntityGroupOfComps[2]= new EntityGroupOfComps("δεκαδικά",4,0); */        
       // parameterEntityGroupOfComps[3]= new EntityGroupOfComps("μορφή ημερομηνίας",4,0); 
        
        
        
        doyLineDBFields[0] = new EntityDBFields("doy","doyId","Νο Δ.Ο.Υ.",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        doyLineDBFields[1] = new EntityDBFields("doy","doyName","ονομασία",0,"java.lang.String",32,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[2] = new EntityDBFields("doy","address","διεύθυνση",0,"java.lang.String",33,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[3] = new EntityDBFields("doy","town","πόλη/χωριό",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[4] = new EntityDBFields("doy","pc","T.K.",0,"java.lang.String",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[5] = new EntityDBFields("doy","tel1","τηλέφωνο(1)",0,"java.lang.String",16,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[6] = new EntityDBFields("doy","tel2","τηλέφωνο(2)",0,"java.lang.String",16,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyLineDBFields[7] = new EntityDBFields("doy","fax","φαξ",0,"java.lang.String",16,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

        
        doyDBFields[0] = new EntityDBFields("doyheader","doy","ΔΟΥ",0,"table",FIELD_VISIBLE_AND_EDITABLE,"doy",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,doyLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT doyId AS \"Νο Δ.Ο.Υ.\", doyName AS \"ονομασία\", address AS \"διεύθυνση\", town AS \"πόλη/χωριό\", pc AS \"ΤΚ\", tel1 AS \"τηλ 1\", tel2 AS \"τηλ 2\", fax AS \"φαξ\" FROM doy ORDER BY doyId",null,null);     //String[] childTableFieldsForSumsIn   
        
        doyEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);        
        
            
        
        
        
        
        
       	bankLineDBFields[0] = new EntityDBFields("bank","bankId","Νο τράπεζας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	bankLineDBFields[1] = new EntityDBFields("bank","bankBranch","υποκατάστημα τράπεζας",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        bankLineDBFields[2] = new EntityDBFields("bank","bic","BIC",0,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

        bankDBFields[0] = new EntityDBFields("bankheader","bank","τράπεζες",0,"table",FIELD_VISIBLE_AND_EDITABLE,"bank",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,bankLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT bankId AS \"Νο τράπεζας\", bankBranch AS \"ονομασία τράπεζας\", bic AS \"BIC\" FROM bank ORDER BY bankId",null,null);     //String[] childTableFieldsForSumsIn   
        bankEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);
        //dbDeliveryDBFields[0] = new EntityDBFields("dbDelivery","deliveryId","Νο αποστολής",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //dbDeliveryDBFields[1] = new EntityDBFields("dbDelivery","description","περιγραφή",0,"java.lang.String",28,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);

        //----------------------------------------------------------------------        
       //----------------------------------------------service sales prefs below------------------
        companySetSerSalesDBFields[0] = new EntityDBFields("dbCompanySet","dbCompanyId","Νο εταιρίας",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,VariablesGlobal.globalCompanyId);
        companySetSerSalesDBFields[1] = new EntityDBFields("dbCompanySet","sersaleCodeOfDocumentsLength","μήκος κωδικού παραστατικών",1,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"7","");
        companySetSerSalesDBFields[2] = new EntityDBFields("dbCompanySet","sersaleWithHoldingTaxAmountGreaterThan","ελαχιστο σύνολο για υπολογισμό % φόρου παρακράτησης",1,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"300","");
        companySetSerSalesDBFields[3] = new EntityDBFields("dbCompanySet","sersaleWithHoldingTaxRate","% φόρου παρακράτησης",1,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"20","");
        companySetSerSalesDBFields[4] = new EntityDBFields("dbCompanySet","sersaleRetailCustomer","προτεινόμενος πελάτης λιανικής",2,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"customer", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,null,"");
        companySetSerSalesDBFields[5] = new EntityDBFields("dbCompanySet","sersaleCheckAFMOfSaleAndComp","ενημέρωση καταχώρησης παραστατικού με πελάτη την εταιρία εργασίας",3,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        companySetSerSalesDBFields[6] = new EntityDBFields("dbCompanySet","sersaleMaxOfCashCheck","ενημέρωση για ποσό εξόφλησης μέσω τράπεζικού τρόπου",4,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        companySetSerSalesDBFields[7] = new EntityDBFields("dbCompanySet","sersaleMaxOfCashNetValue","ελαχιστο ποσό για εξόφληση μέσω τράπεζικού τρόπου",4,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"500","");
        companySetSerSalesDBFields[8] = new EntityDBFields("dbCompanySet","sersaleCopyCustomerNameToSaleComment","πρόταση επωνυμίας πελάτη στην αιτιολογία πώλησης",5,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        
        
        companySetSerSalesEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);    
        companySetSerSalesEntityGroupOfComps[1] = new EntityGroupOfComps("παράμετροι",6,0,FONT_SIZE_NOT_SET);
        companySetSerSalesEntityGroupOfComps[2] = new EntityGroupOfComps("λιανική",4,0,FONT_SIZE_NOT_SET);
        companySetSerSalesEntityGroupOfComps[3] = new EntityGroupOfComps("έλεγχοι",4,0,FONT_SIZE_NOT_SET);
        companySetSerSalesEntityGroupOfComps[4] = new EntityGroupOfComps("εξόφληση",4,0,FONT_SIZE_NOT_SET);      
         companySetSerSalesEntityGroupOfComps[5] = new EntityGroupOfComps("προτιμήσεις",4,0,FONT_SIZE_NOT_SET);
      
      
      
        //------------------------- 

         actionTypeDBFields[0] = new EntityDBFields("actiontype","actionTypeId","Νο τύπου παραστατικού",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         actionTypeDBFields[1] = new EntityDBFields("actiontype","actionTypeDescription","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         actionTypeDBFields[2] = new EntityDBFields("actiontype","actionTypeCode","κωδικός παραστατικού",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         actionTypeDBFields[3] = new EntityDBFields("actiontype","isDebit","χρέωση",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"","");                       
         actionTypeDBFields[4] = new EntityDBFields("actiontype","actionTraderId","κίνηση συναλλασσόμενου",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"actiontrader",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         actionTypeDBFields[5] = new EntityDBFields("actiontype","actionStockId","κίνηση αποθήκης",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"actionstock",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         
         //actionTypeDBFields[6] = new EntityDBFields("actiontype","typeNextNumber","επόμενος αριθμός παραστατικού",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         actionTypeDBFields[6] = new EntityDBFields("actiontype","actionTypeCatId","κατηγορία παραστατικού",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCActionTypeCat", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         //actionTypeDBFields[8] = new EntityDBFields("actiontype","isCredit","πιστωτικό",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"false","");       
         //actionTypeDBFields[9] = new EntityDBFields("actiontype","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");        
         //actionTypeDBFields[10] = new EntityDBFields("actiontype","printformId","φόρμα εκτύπωσης",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"printform",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         actionTypeDBFields[7] = new EntityDBFields("actiontype","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
         actionTypeDBFields[8] = new EntityDBFields("actiontype","isSxRecordActive","ενημέρωση",1,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,"false","");                       
// actionTypeDBFields[0] = new EntityDBFields("actiontypeheader","actiontype","τύποι παραστατικών",0,"table",FIELD_VISIBLE_AND_EDITABLE,"actiontype",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,actionTypeLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT * FROM actiontype ORDER BY actionTypeId",null);     //String[] childTableFieldsForSumsIn   
         actionTypeDBFields[9] = new EntityDBFields("actiontype","sxActionTypeId","τύπος",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"sxactiontype",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
         actionTypeDBFields[10] = new EntityDBFields("actiontype","sxAccountId","λογαριασμός",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"sxaccount",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"sxActionTypeId");
         
         
         
         actionTypeEntityGroupOfComps[0] = new EntityGroupOfComps("ιδιότητες",4,0,FONT_SIZE_NOT_SET);     
        //actionTypeEntityGroupOfComps[1] = new EntityGroupOfComps("εκτύπωση",4,0);
        actionTypeEntityGroupOfComps[1] = new EntityGroupOfComps("ενημέρωση εσόδων εξόδων",6,0,FONT_SIZE_NOT_SET);
        
      
      

         actionseriesDBFields[0] = new EntityDBFields("actionseries","actionseriesId","Νο σειράς",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         actionseriesDBFields[1] = new EntityDBFields("actionseries","descr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
         actionseriesDBFields[2] = new EntityDBFields("actionseries","actionSeriesCode","σειρά παραστατικού",0,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         actionseriesDBFields[3] = new EntityDBFields("actionseries","actionTypeId","τύπος παραστατικού",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"actiontype", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         actionseriesDBFields[4] = new EntityDBFields("actionseries","seriesNextNumber","επόμενος αριθμός παραστατικού",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");
         actionseriesDBFields[5] = new EntityDBFields("actionseries","isActive","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");        
         actionseriesDBFields[6] = new EntityDBFields("actionseries","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
         actionseriesDBFields[7] = new EntityDBFields("actionseries","askForPrint","εκτύπωση μετά την αποθήκευση",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCAskPrintYesNo",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1",""); ///LOOKUPTYPE_TABLECONSTANTS
         actionseriesDBFields[8] = new EntityDBFields("actionseries","printformId","φόρμα εκτύπωσης",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"printform",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         actionseriesDBFields[9] = new EntityDBFields("actionseries","printCopies","αντίτυπα",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");                       
         actionseriesDBFields[10] = new EntityDBFields("actionseries","printprinterId","εκτυπωτής",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"printer",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         
         
         actionseriesEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET);      
         actionseriesEntityGroupOfComps[1] = new EntityGroupOfComps("εκτύπωση",6,0,FONT_SIZE_NOT_SET);

          //---------------------------------------------------------------------------------------------------- 

         actiontraderDBFields[0] = new EntityDBFields("actiontrader","actiontraderId","Νο",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         actiontraderDBFields[1] = new EntityDBFields("actiontrader","descr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
         actiontraderDBFields[2] = new EntityDBFields("actiontrader","actionType","επηρεάζει",0,"java.lang.Integer",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCActionTraderType",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");
         actiontraderDBFields[3] = new EntityDBFields("actiontrader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");         
         
         actiontraderEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",6,0,FONT_SIZE_NOT_SET);
         
          //---------------------------------------------------------------------------------------------------- 

         actionstockDBFields[0] = new EntityDBFields("actionstock","actionstockId","Νο",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         actionstockDBFields[1] = new EntityDBFields("actionstock","descr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
         actionstockDBFields[2] = new EntityDBFields("actionstock","affectType","επηρεάζει",0,"java.lang.Integer",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCActionStockAffectType",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");
         actionstockDBFields[3] = new EntityDBFields("actionstock","affectsQuantity","επηρεάζει ποσότητα",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"","");        
         actionstockDBFields[4] = new EntityDBFields("actionstock","affectsValue","επηρεάζει αξία",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"","");        
         actionstockDBFields[5] = new EntityDBFields("actionstock","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
         
         actionstockEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",6,0,FONT_SIZE_NOT_SET);
                 
               
      
      
      
      
      
        
     //------------------------------------------------------------service sales prefs above-----------------
     
     
        companySetEsoExoDBFields[0] = new EntityDBFields("dbCompanySet","dbCompanyId","Νο εταιρίας",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,VariablesGlobal.globalCompanyId);
        companySetEsoExoDBFields[1] = new EntityDBFields("dbCompanySet","esoexoCheckAFMOfEsoExoAndComp","ενημέρωση καταχώρησης εσόδων - εξόδων με συναλλασόμενο την εταιρία εργασίας",1,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        companySetEsoExoDBFields[2] = new EntityDBFields("dbCompanySet","esoexoMaxOfCashCheck","ενημέρωση για ποσό εξόφλησης μέσω τράπεζικού τρόπου",2,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        companySetEsoExoDBFields[3] = new EntityDBFields("dbCompanySet","esoexoMaxOfCashNetValue","ελαχιστο ποσό για εξόφληση μέσω τράπεζικού τρόπου",2,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"500","");        
        companySetEsoExoDBFields[4] = new EntityDBFields("dbCompanySet","esoexoCopyTraderNameToEsoexoComment","πρόταση επωνυμίας συναλλασσόμενου στην αιτιολογία καταχώρησης",3,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        
        companySetEsoExoEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET);   
        companySetEsoExoEntityGroupOfComps[1] = new EntityGroupOfComps("έλεγχοι",4,0,FONT_SIZE_NOT_SET); 
        companySetEsoExoEntityGroupOfComps[2] = new EntityGroupOfComps("εξόφληση",4,0,FONT_SIZE_NOT_SET);
        companySetEsoExoEntityGroupOfComps[3] = new EntityGroupOfComps("προτιμήσεις",4,0,FONT_SIZE_NOT_SET);
        
   //---------------------------------------------------------------------------
        
    
   }
   
   // called in PanelManagement addNodes()
  /* public void loadGenericData()
   {
   	
      
      //System.out.println("EntityData "+deliveryGroupOfComps);  
   	
   	   
   	  intYearPlusOne = Integer.valueOf(VariablesGlobal.globalYear)+1;
   	   globalYearPlusOne = intYearPlusOne+"";
   	 // vars global are not loaded before so we load it here
   	deliveryQueryEditable = "SELECT a.customerId, a.dateOfApplication, a.permanent, a.dbyear, a.deliveryId, a.dbCompanyId "
                + "FROM application a "
                + "WHERE dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+" AND dbCompanyId="+VariablesGlobal.globalCompanyId;
        
        deliveryQueryManyReadOnly = "SELECT i.aa AS \"αα\", i.customerId, b.buyerTitle AS\"αγοραστής\" ,it.abbreviation AS\"παρ\" ,invoiceNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\", p.productName AS\"προϊόν\" ,i.value AS\"αξία\", i.currencyPercentage AS\"ποσοστό\" , i.valueReturn AS\"επιστροφή\" "
                + "FROM invoice i, product p, customer f, application a, buyer b,paymentType it "
                + "WHERE b.buyerId=i.buyerId AND f.customerId=i.customerId AND f.customerId=a.customerId AND i.deliveryId=a.deliveryId AND i.productId = p.productId AND a.dbyear=i.dbyear AND a.dbCompanyId=i.dbCompanyId AND i.paymentTypeId=it.paymentTypeId AND i.dbyear="+VariablesGlobal.globalYear+" AND i.deliveryId = "+VariablesGlobal.globalDeliveryId+" AND i.dbCompanyId="+VariablesGlobal.globalCompanyId+" ORDER BY surname, name, fathername, f.vatNo, f.customerId, i.aa";// order by not taken into account
        
        deliveryQueryManyEditable= "SELECT aa AS \"αα\", buyerId,paymentTypeId,invoiceNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\", productId, value AS\"αξία\" , currencyPercentage AS\"ποσοστό\" , valueReturn AS\"επιστροφή\", DBCOMPANYID, DBYEAR, deliveryId, FARMERID "
                + "FROM invoice i WHERE i.dbyear="+VariablesGlobal.globalYear+" AND i.deliveryId = "+VariablesGlobal.globalDeliveryId+" AND i.dbCompanyId="+VariablesGlobal.globalCompanyId;

        
       
        entityPanelDeliveryDataentry = new EntityPanel("TDOR","application",deliveryQueryEditable,deliveryDBFields,deliveryDBFieldsMany,deliveryGroupOfComps,deliveryGroupOfPanels,fieldsForSumsInvoice,
           "invoice", deliveryQueryManyEditable, deliveryQueryManyReadOnly,true,deliveryWhereField,
           deliveryWhereValue, "Νο πελάτη","primkeyvalue","customerId",false,"βασικά στοιχεία", ICO_TABLE16,"παραστατικών αγρότη",
           true,7,"currency","currencyId","product","productId",3,globalYearPlusOne,VariablesGlobal.globalYear);// String yearEnforceInActionIn, String yearEnforceInLinesIn)
        
        //EntityPanel entityPanelDeliveryStatistics = new EntityPanel("statDeliveyHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.companyName AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.customerId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.companyName, invoice.deliveryId",false,"",false,"");     
                
        //EntityPanel entityPanelCustomerStatistics = new EntityPanel("STATS",null,"ιστορικό",null,"SELECT dbyear AS \"χρήση\", dbcompany.companyName AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.customerId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.companyName, invoice.deliveryId",false,"",false,"");     
         entityPanelDelivery = new EntityPanel[] {entityPanelDeliveryDataentry};//,entityPanelDeliveryStatistics};
       
       
   }*/

   
   public void loadAllNodes()
   {
       //addReportSettings();
       //addEntityInfoNodes();
      // addStatisticsNodes();
       addEntitiesParameters();
       addToolNodes();       
   }
   
   
   public DataTree getDataTree()
   {     
   	return dTree;
   }
    
   // may be called from ReportAreaGenerated.clickedOnRow, 
   /*public DataTreeNode getDataTreeNodeDataEntry(String caption)
   {
       boolean[] bool = {true,true,true,true};
       this.addMainNavigationNodes(bool);
       this.addEntityInfoNodes();
       nodeRoot.getChildFromCaption(DATAENTRY);//.getChildFromCaption(caption);
       
       return nodeRoot;
       
   }*/
           

   
   
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
        emCat4.setEntityCategory(PARAMETERSOFSYSTEM,1,ICO_MENUCAT_EDIT);//ICO_TABLE16);
        if(isVisible[3])
        {
           nodeRoot.addChild(new DataTreeNode(emCat4));
        }


        EntityMenu emCat3 = new EntityMenu();
        emCat3.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat3.setEntityCategory(MAINTENANCEOFSYSTEM,1,ICO_MENUCAT_EXPLORE);//ICO_STATISTICS16);
        if(isVisible[2])
        {        
           nodeRoot.addChild(new DataTreeNode(emCat3));
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
      




     //------------------------------------------------------------
       
  }

  
   public ArrayList addEntitiesLookupTableConstants(ArrayList <EntityLookupTableConstants> listEntityLookupTableConstants)
   {
       
       EntityLookupTableConstants entityLookupTableConstants;
       
       EntityLookupTableConstantsData [] luTCData1 = new EntityLookupTableConstantsData[2];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCData1[0]=new EntityLookupTableConstantsData("1",1,"title1");
       luTCData1[1]=new EntityLookupTableConstantsData("2",2,"title2");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCname",luTCData1));


       EntityLookupTableConstantsData [] luTCDataDecimalChar = new EntityLookupTableConstantsData[2];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataDecimalChar[0]=new EntityLookupTableConstantsData(",",1,",");
       luTCDataDecimalChar[1]=new EntityLookupTableConstantsData(".",2,".");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCdecimalchar",luTCDataDecimalChar));       

       EntityLookupTableConstantsData [] luTCDataVatExclusion = new EntityLookupTableConstantsData[3];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataVatExclusion[0]=new EntityLookupTableConstantsData("1",1,"κανονικό");
       luTCDataVatExclusion[1]=new EntityLookupTableConstantsData("2",2,"μειωμένο");
       luTCDataVatExclusion[2]=new EntityLookupTableConstantsData("3",3,"απαλλασσόμενο");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCVatExclusion",luTCDataVatExclusion));    


       
       EntityLookupTableConstantsData [] luTCDataActionTypeCat = new EntityLookupTableConstantsData[3];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataActionTypeCat[0]=new EntityLookupTableConstantsData("1",1,"πωλήσεις χονδρικής");
       luTCDataActionTypeCat[1]=new EntityLookupTableConstantsData("2",2,"πωλήσεις χονδρικής πιστωτικό");
       luTCDataActionTypeCat[2]=new EntityLookupTableConstantsData("3",3,"πωλήσεις λιανικής");
       
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCActionTypeCat",luTCDataActionTypeCat));        
       
       return listEntityLookupTableConstants;
   }
  
  
   public ArrayList addEntitiesLookup(ArrayList entities)
   { 
       // LOOKUPTYPE_ONLYONE_THISFIELD
     EntityLookUp entityLookUp;

     
     // make entities for all tables called with foreign keys
     
   /*    EntityFilterSettings[] customerErs = new EntityFilterSettings[3];       
       customerErs[0]=new EntityFilterSettings("επίθετο","","string","equals","name","customer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       customerErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","customer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       customerErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //customerErs[2]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","customer","",-1,-1,-1,FIELD_NOCOMPLETION);
            // for 'intNoOfColsWhenInTable' look also at  deliveryFieldsMany
      
     
     String[] lookUpFieldCustomer={"name","vatNo"};
            
     entities.add(entityLookUp = new EntityLookUp("customer","customer","SELECT customer.customerId AS\"Νο πελάτη\", customerCode AS\"κωδικός\", name AS\"επωνυμία\",  vatNo AS\"Α.Φ.Μ.\", sum(quantityTotal) AS \"ποσότητα\", sum(pricePreVat) AS \"προ ΦΠΑ\", sum(priceVat) AS \"ΦΠΑ\", sum(priceTotal) AS \"συνολικό ποσό\"  FROM customer LEFT JOIN saleheader ON saleheader.customerId = customer.customerId AND saleheader.dbCompanyId = customer.dbCompanyId","WHERE customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"AND customer.active LIKE 1", "GROUP BY customer.customerId ORDER BY customer.name","customerId","Νο πελάτη","customerId","πελάτης",3,lookUpFieldCustomer,"επωνυμία ή ΑΦΜ",29,"java.lang.String",4,"vatNo", "Α.Φ.Μ.",0,null,null,customerQueryEditable, "πελάτη","πελατών",strCustomerCategories,entityPanelCustomer,fieldsOnTitleCustomer,fieldsOnTitleCaptionCustomer,customerErs,2,2,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));
*/
     
     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
     String[] lookUpFieldPaymentType={"description"};
     //String[] lookUpFieldLabelPaymentType={"είδος παραστατικού","συντομογραφία",null};
     entities.add(entityLookUp = new EntityLookUp("paymenttype","paymenttype","SELECT paymentTypeId AS\"Νο τρόπου πληρωμής\",description AS\"ονομασία\" FROM paymenttype","","","ORDER BY description","","paymentTypeId","Νο τρόπου πληρωμής","paymentTypeId","τρόπος πληρωμής",2,lookUpFieldPaymentType,"τρόπος πληρωμής",10,"java.lang.String",0,null,null,0,null,null,paymenttypeQueryEditable,"τρόπου πληρωμής","τρόπων πληρωμής",null,entityPanelPaymentType,fieldsOnTitlePaymentType, fieldsOnTitleCaptionPaymentType,null,2,1,null,true,-1,-1,null)); 
     //entities.add(entityLookUp = new EntityLookUp("paymenttype","SELECT paymentTypeId AS\"Νο τύπου παρ/κού\",paymentTypeName AS\"ονομασία τύπου παρ/κού\", abbreviation AS\"συντομογραφία\" FROM paymenttype","ORDER BY paymentTypeName","paymentTypeId","Νο τύπου παρ/κού",2,lookUpFieldPaymentType,"είδος παραστατικού",0,null,null,0,null,null,paymenttypeQueryEditable,"τύπου παραστατικού","τύπων παραστατικού",null,entityPanelPaymentType,fieldsOnTitlePaymentType, fieldsOnTitleCaptionPaymentType,null,2,1,null,true,-1,-1)); 


     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
     String[] lookUpFieldGeoCat={"geoCatName"};
     
        EntityFilterSettings[] geoCatErs = new EntityFilterSettings[1];       
        geoCatErs[0]=new EntityFilterSettings("ονομασία","","string","equals","geoCatName","geocat",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("geocat","geocat","SELECT geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\", state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM geocat","","","ORDER BY geoCatName" ,"","geoCatId","Νο πόλης","geoCatId","πόλη/χωριό",2,lookUpFieldGeoCat,"πόλη/χωριό",16,"java.lang.String",0,null,null,0,null,null,geoCatQueryEditable,"πόλης","πόλεων",null,entityPanelGeoCat,fieldsOnTitleGeoCat, fieldsOnTitleCaptionGeoCat,geoCatErs,2,1,null,true,-1,-1,null));
     
  
     String[] lookUpFieldActivity={"activityDescr"};
     
        EntityFilterSettings[] activityCatErs = new EntityFilterSettings[1];       
        activityCatErs[0]=new EntityFilterSettings("ονομασία","","string","equals","activityDescr","activitycat",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("activitycat","activitycat","SELECT activityCatId AS\"Νο δραστηριότητας\", activityDescr AS\"δραστηριότητα\" FROM activitycat","","","ORDER BY activityDescr","" ,"activityCatId","Νο δραστηριότητας","activityCatId","δραστηριότητα",2,lookUpFieldActivity,"δραστηριότητα",16,"java.lang.String",0,null,null,0,null,null,activityCatQueryEditable,"δραστηριότητα","δραστηριοτήτων",null,entityPanelActivityCat,fieldsOnTitleActivityCat, fieldsOnTitleCaptionActivityCat,activityCatErs,2,1,null,true,-1,-1,null));
     
  

   /*   String[] lookUpFieldServiceCat={"catDescr"};
     
        EntityFilterSettings[] serviceCatErs = new EntityFilterSettings[1];       
        serviceCatErs[0]=new EntityFilterSettings("ονομασία","","string","equals","catDescr","servicecat",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("servicecat","servicecat","SELECT serviceCatId AS\"Νο κατηγορίας\", catDescr AS\"κατηγορία\" FROM servicecat","","","ORDER BY catDescr" ,"serviceCatId","Νο κατηγορίας","serviceCatId","κατηγορία",2,lookUpFieldServiceCat,"κατηγορία",15,"java.lang.String",0,null,null,0,null,null,serviceCatQueryEditable,"κατηγορία υπηρεσίας","κατηγοριών υπηρεσίας",null,entityPanelServiceCat,fieldsOnTitleServiceCat, fieldsOnTitleCaptionServiceCat,serviceCatErs,2,1,null,true,-1,-1,null));
     
  */

      String[] lookUpFieldVatCat={"vatDescr"};
     
        EntityFilterSettings[] vatCatErs = new EntityFilterSettings[1];       
        vatCatErs[0]=new EntityFilterSettings("ονομασία","","string","equals","vatDescr","vatcat",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("vatcat","vatcat","SELECT vatCatId AS\"Νο κατηγορίας ΦΠΑ\", vatDescr AS\"κατηγορία ΦΠΑ\", vatPercentage AS \"ποσοστό\" FROM vatcat","","AND vatcat.active LIKE 1","ORDER BY vatDescr","","vatCatId","Νο κατηγορίας ΦΠΑ","vatCatId","κατηγορία",2,lookUpFieldVatCat,"κατηγορία",7,"java.lang.String",0,null,null,0,null,null,vatCatQueryEditable,"κατηγορία ΦΠΑ","κατηγοριών ΦΠΑ",null,entityPanelVatCat,fieldsOnTitleVatCat, fieldsOnTitleCaptionVatCat,vatCatErs,2,1,null,true,-1,-1,null));
     
  
     
     
     
     
     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
     String[] lookUpFieldDoy={"doyName"};
     
        EntityFilterSettings[] doyErs = new EntityFilterSettings[2];       
        doyErs[0]=new EntityFilterSettings("κωδικός","","string","equals","doyId","doy",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        doyErs[1]=new EntityFilterSettings("ονομασία","","string","equals","doyName","doy",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("doy","doy","SELECT doyId AS\"Νο Δ.Ο.Υ.\", doyName AS\"ονομασία Δ.Ο.Υ.\" FROM doy","","","ORDER BY doyId","","doyId","Νο Δ.Ο.Υ.","doyId","Δ.Ο.Υ.",2,lookUpFieldDoy,"ονομασία Δ.Ο.Υ.",12,"java.lang.String",0,null,null,0,null,null,doyQueryEditable,"της Δ.Ο.Υ.","Δ.Ο.Υ.",null,entityPanelDoy,fieldsOnTitleDoy, fieldsOnTitleCaptionDoy,doyErs,2,1,null,true,-1,-1,null));     	 	
     
     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
   /*  String[] lookUpFieldBuyer={"buyerId","buyerTitle","buyerAfm"};     
       EntityFilterSettings[] buyerErs = new EntityFilterSettings[2];       
       buyerErs[0]=new EntityFilterSettings("επωνυμία","","string","equals","buyerTitle","buyer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       buyerErs[1]=new EntityFilterSettings("Α.Φ.Μ.","","string","equals","buyerAfm","buyer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("buyer","buyer","SELECT buyerId AS\"Νο αγοραστή\",buyerTitle AS\"τίτλος αγοραστή\",buyerAfm AS\"Α.Φ.Μ.\", phone AS\"τηλέφωνο(1)\", product.productName AS \"προτ. προϊόν\"  FROM buyer LEFT JOIN product ON buyer.productId=product.productId","","","ORDER BY buyerTitle","buyerId","Νο αγοραστή","buyerId","ονομασία αγοραστή ή Α.Φ.Μ.",2,lookUpFieldBuyer," id ή τίτλος αγοραστή ή ΑΦΜ",15,"java.lang.String",3,"buyerAfm","Α.Φ.Μ. αγοραστή",0,null,null,buyerQueryEditable,"αγοραστή","αγοραστών",strBuyerCategories,entityPanelBuyer,fieldsOnTitleBuyer, fieldsOnTitleCaptionBuyer,buyerErs,2,2,ICO_BUYER16,true,2,FIELD_VALIDATION_AFM,null)); 
    */
     //------------------------------------------------------------------ 
 
//---------------------------------------------------------------------------    
    
 /*       EntityFilterSettings[] saleErs = new EntityFilterSettings[2];       
        saleErs[0]=new EntityFilterSettings("ονομασία","","string","equals","saleCodeOfDocument","saleheader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeId","actionType","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
           
     String[] lookUpFieldSale={"saleCodeOfDocument"};                   
   */ 
   //  entities.add(entityLookUp = new EntityLookUp("saleheader","saleheader","SELECT saleHeader.saleCodeOfDocument, saleheader.actionTypeId, saleheader.saleCodeNo, saleheader.customerId  FROM saleheader ",/*, currency WHERE product.currencyId=currency.currencyId"*/"WHERE saleheader.dbCompanyId LIKE "+ VariablesGlobal.globalCompanyId,"","ORDER BY saleheader.dateOfSale, saleheader.saleHeaderId ","saleHeaderId","Νο πώλησης","saleHeaderId","πώληση",3,lookUpFieldSale,"κωδ. παραστατικού",15,"java.lang.String",0,null,null,0,null,null,saleQueryEditable,"πώλησης","πωλήσεων",null,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale,saleErs,2,1,null,true,-1,-1,null));    	 	

     //------------------------------------------------------------------ 

 /*        EntityFilterSettings[] actionTypeErs = new EntityFilterSettings[1];       
        actionTypeErs[0]=new EntityFilterSettings("ονομασία","","string","equals","actionTypeDescription","service",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeCatId","servicecat","service","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        
        String[] lookUpFieldActionType={"actionTypeDescription"};                   
    
     entities.add(entityLookUp = new EntityLookUp("actiontype","actiontype","SELECT actiontype.actionTypeId AS\"Νο τύπου παραστατικού\", actiontype.actionTypeCode AS\"κωδικός\", actiontype.actionTypeDescription AS \"ονομασία τύπου παραστατικού\"  FROM actiontype","WHERE actiontype.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"AND actiontype.active LIKE 1","ORDER BY actiontype.actionTypeDescription ","actionTypeId","Νο τύπου παραστατικού","actionTypeId","τύπος παραστατικού",3,lookUpFieldActionType,"ονομασία",33,"java.lang.String",0,null,null,0,null,null,actionTypeQueryEditable,"τυπου παραστατικού","τύπων παραστατικών",null,entityPanelActionType,fieldsOnTitleActionType,fieldsOnTitleCaptionActionType,actionTypeErs,2,1,null,true,-1,-1,null));    	 	
*/
     //------------------------------------------------------------------ 

  /*       EntityFilterSettings[] printFormErs = new EntityFilterSettings[1];       
        printFormErs[0]=new EntityFilterSettings("ονομασία","","string","equals","printFormName","printForm",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeCatId","servicecat","service","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        
        String[] lookUpFieldPrintForm={"printFormName"};                   
    
     entities.add(entityLookUp = new EntityLookUp("printform","printform","SELECT printform.printformId AS\"Νο φόρμας\", printform.printformName AS \"φόρμα εκτύπωσης\"  FROM printform","WHERE printform.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"AND printform.isActive LIKE 1","ORDER BY printform.printformName ","printformId","Νο φόρμας","printform","φόρμα",2,lookUpFieldPrintForm,"ονομασία",33,"java.lang.String",0,null,null,0,null,null,printFormQueryEditable,"φόρμας εκτύπωσης","φορμών εκτύπωσης",null,entityPanelPrintForm,fieldsOnTitlePrintForm,fieldsOnTitleCaptionPrintForm,printFormErs,2,1,null,true,-1,-1,null));    	 	
    */           
 
  
  
  
   //----------------------------------------------------------------  
  
     String[] lookUpFieldRoleDescr={"roleDescr"};
     //String[] lookUpFieldLabelPaymentType={"είδος παραστατικού","συντομογραφία",null};
     entities.add(entityLookUp = new EntityLookUp("dbuserrole","dbuserrole","SELECT userRoleId ,roleDescr AS\"περιγραφή\" FROM dbuserrole","","","ORDER BY roleDescr","","userRoleId","Νο","userRoleId","ρόλος",2,lookUpFieldRoleDescr,"ρόλος",10,"java.lang.String",0,null,null,0,null,null,dbUserRolesQueryEditable,"ρόλου","ρόλων",null,dbUserRolesEntityPanel,dbUserRolesFieldsOnTitle, dbUserRolesFieldsOnTitleCaption,null,2,1,null,true,-1,-1,null)); 
     //entities.add(entityLookUp = new EntityLookUp("paymenttype","SELECT paymentTypeId AS\"Νο τύπου παρ/κού\",paymentTypeName AS\"ονομασία τύπου παρ/κού\", abbreviation AS\"συντομογραφία\" FROM paymenttype","ORDER BY paymentTypeName","paymentTypeId","Νο τύπου παρ/κού",2,lookUpFieldPaymentType,"είδος παραστατικού",0,null,null,0,null,null,paymenttypeQueryEditable,"τύπου παραστατικού","τύπων παραστατικού",null,entityPanelPaymentType,fieldsOnTitlePaymentType, fieldsOnTitleCaptionPaymentType,null,2,1,null,true,-1,-1)); 





   
     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
     String[] lookUpFieldBank={"bankBranch"};
     entities.add(entityLookUp = new EntityLookUp("bank","bank","SELECT bank.bankId AS\"Νο τράπεζας\", bank.bankBranch AS \"τίτλος τράπεζας\", BIC FROM bank","","","ORDER BY bank.bankBranch","","bankId","Νο τράπεζας","bankId","τράπεζα",2,lookUpFieldBank,"τίτλος τράπεζας",18,"java.lang.String",0,null,null,0,null,null,bankQueryEditable,"τράπεζας","τραπεζών",null,entityPanelBank,fieldsOnTitleBank,fieldsOnTitleCaptionBank,null,2,1,null,true,-1,-1,null));    	 		
     
     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
//     String[] lookUpFieldCurrency={"name"};
//     entities.add(entityLookUp = new EntityLookUp("currency","currency","SELECT currencyId AS \"Νο νομίσματος\",name AS \"ονομασία\" FROM currency","WHERE currency.active LIKE 1","AND currency.active LIKE 1","ORDER BY currencyId","currencyId","Νο νομίσματος","currencyId","ονομασία",2,lookUpFieldCurrency,"νομίσματος",8,"java.lang.String",0,null,null,0,null,null,currencyQueryEditable,"του νομίσματος","νομισμάτων",null,entityPanelCurrency,fieldsOnTitleCurrency, fieldsOnTitleCaptionCurrency,null,2,1,null,true,-1,-1,null));	
     
      //int[] lookUpFieldIndexPaymentType ={2,3,0};     
//     String[] lookUpFieldDelivery={"description"};    // deliveryId
//     entities.add(entityLookUp = new EntityLookUp("dbdelivery","dbdelivery","SELECT deliveryId AS \"Νο αποστολής\", description  AS \"περιγραφή\" FROM dbdelivery","","","ORDER BY deliveryId","deliveryId","Νο αποστολής","deliveryId","αποστολής",2,lookUpFieldDelivery,"Νο αποστολής",8,"java.lang.String",0,null,null,0,null,null,dbDeliveryQueryEditable,"αποστολής","αποστολών",null,entityPanelDbDelivery,fieldsOnTitleDbDelivery,fieldsOnTitleCaptionDbDelivery,null,-1,1,ICO_PAPER,true,-1,-1,null));      	 	
     
     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
//     String[] lookUpFieldDbYear={"dbyear"};
//     entities.add(entityLookUp = new EntityLookUp("dbyear","dbyear","SELECT dbyearId AS \"χρήση Νο\", dbyear AS \"χρήση\" FROM dbyear","WHERE dbCompanyId LIKE '"+VariablesGlobal.globalCompanyId+"'","", "ORDER BY dbyear" ,"","dbyearId","χρήση","dbyearId","χρήση",2,lookUpFieldDbYear,"χρήση",7,"java.lang.String",0,null,null,0,null,null,"","της χρήσης","των χρήσεων",null,entityPanelDbyear,fieldsOnTitleDbyear,fieldsOnTitleCaptionDbyear,null,-1,1,null,false,-1,-1,null));  

     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
     String[] lookUpFieldDbCompany={"companyName"};
     entities.add(entityLookUp = new EntityLookUp("dbcompany","dbcompany","SELECT dbCompanyId AS \"νο\", companyName AS \"επωνυμία\", companyVatNo AS \"ΑΦΜ\" FROM dbcompany","","", "ORDER BY companyName","","dbCompanyId","νο","dbCompanyId","εταιρία",2,lookUpFieldDbCompany,"τίτλος εταιρίας",15,"java.lang.String",0,null,null,0,null,null,dbCompanyQueryEditable,"της εταιρίας","εταιριών",null,entityPanelDbCompany,fieldsOnTitleDbCompany,fieldsOnTitleCaptionDbCompany,null,2,1,null,true,2,FIELD_VALIDATION_AFM,null));     

     
     
     // ------------------------ lookup servicesales transactions below---------
         EntityFilterSettings[] actionTypeErs = new EntityFilterSettings[1];       
        actionTypeErs[0]=new EntityFilterSettings("ονομασία","","string","equals","actionTypeDescription","actiontype",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
    
        String[] lookUpFieldActionType={"actionTypeDescription"};                      
     entities.add(entityLookUp = new EntityLookUp("actiontype","actiontype","SELECT actiontype.actionTypeId AS\"Νο τύπου παραστατικού\", actiontype.actionTypeCode AS\"κωδικός\", actiontype.actionTypeDescription AS \"ονομασία τύπου παραστατικού\" ,actiontype.isDebit, lookupconstants.name  FROM actiontype INNER JOIN lookupconstants ON actiontype.actionTypeCatId = lookupconstants.lookupconstantsId","WHERE lookupconstants.constantstypeId = 2 AND  actiontype.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"","ORDER BY actiontype.actionTypeCatId, actiontype.actionTypeId","","actionTypeId","Νο τύπου παραστατικού","actionTypeId","τύπος παραστατικού",3,lookUpFieldActionType,"ονομασία",33,"java.lang.String",0,null,null,0,null,null,actionTypeQueryEditable,"τυπου παραστατικού","τύπων παραστατικών",null,entityPanelActionType,fieldsOnTitleActionType,fieldsOnTitleCaptionActionType,actionTypeErs,2,1,null,true,-1,-1,null));    	 	
     
  //--------------------------------------- -----------------------------
     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
     String[] actionseriesLookUpField={"descr"};
     
        EntityFilterSettings[] actionseriesErs = new EntityFilterSettings[2];       
        actionseriesErs[0]=new EntityFilterSettings("κωδικός","","string","equals","actionseriesId","actionseries",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        actionseriesErs[1]=new EntityFilterSettings("ονομασία","","string","equals","descr","actionseries",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("actionseries","actionseries","SELECT actionseriesId AS\"Νο\", descr AS\"ονομασία\", actionSeriesCode, seriesNextNumber FROM actionseries","WHERE actionseries.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"","ORDER BY actionseriesId","","actionseriesId","Νο","actionseriesId","σειρές",2,actionseriesLookUpField,"ονομασία",32,"java.lang.String",0,null,null,0,null,null,actionseriesQueryEditable,"της σειράς","σειρές",null,actionseriesEntityPanel,actionseriesFieldsOnTitle, actionseriesFieldsOnTitleCaption,actionseriesErs,2,1,null,true,-1,-1,null));     	 	
          
    //-------------------------------------------------------------------------------
     String[] actionstockLookUpField={"descr"};
     
        EntityFilterSettings[] actionstockErs = new EntityFilterSettings[2];       
        actionstockErs[0]=new EntityFilterSettings("κωδικός","","string","equals","actionstockId","actionstock",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        actionstockErs[1]=new EntityFilterSettings("ονομασία","","string","equals","descr","actionstock",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("actionstock","actionstock","SELECT actionstockId AS\"Νο\", descr AS\"ονομασία\", affectType, affectsQuantity, affectsValue FROM actionstock","WHERE actionstock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"","ORDER BY actionstockId","","actionstockId","Νο","actionstockId","τύποι αποθήκης",2,actionstockLookUpField,"ονομασία",18,"java.lang.String",0,null,null,0,null,null,actionstockQueryEditable,"της κίνησης αποθήκης","κινήσεων αποθήκης",null,actionstockEntityPanel,actionstockFieldsOnTitle, actionstockFieldsOnTitleCaption,actionstockErs,2,1,null,true,-1,-1,null));     	 	
              
   //-------------------------------------------------------------------------------
     String[] actiontraderLookUpField={"descr"};
     
        EntityFilterSettings[] actiontraderErs = new EntityFilterSettings[2];       
        actiontraderErs[0]=new EntityFilterSettings("κωδικός","","string","equals","actiontraderId","actiontrader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        actiontraderErs[1]=new EntityFilterSettings("ονομασία","","string","equals","descr","actiontrader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("actiontrader","actiontrader","SELECT actiontraderId AS\"Νο\", descr AS\"ονομασία\" FROM actiontrader","WHERE actiontrader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"","ORDER BY actiontraderId","","actiontraderId","Νο","actiontraderId","κινήσεις συναλλασσόμενου",2,actiontraderLookUpField,"ονομασία",18,"java.lang.String",0,null,null,0,null,null,actiontraderQueryEditable,"του κίνησης συναλλασσόμενου","τύπων συναλ.",null,actiontraderEntityPanel,actiontraderFieldsOnTitle, actiontraderFieldsOnTitleCaption,actiontraderErs,2,1,null,true,-1,-1,null));     	 	
           //--------------------------------------- transaction types above----------        
     
     
     return entities;
   }
  
     public void addEntitiesParameters()  // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
    {  


         
         
      DataTreeNode nodeCat = null;
       DataTreeNode nodeSystem = null;
       
        //sub categories
         EntityMenu emCat1 = new EntityMenu();
         emCat1.setEntityCategory(SYSTEM_CAT_1,ENTITY_TYPE_CATEGORY2,ICO_MENUCAT_SYSTEM);
        // System.out.println("EntityData.addReportSettings "+REPORTS_CAT_ARRAY[r]+" "+r);
         nodeCat = new DataTreeNode(emCat1);
         nodeRoot.getChildFromCaption(PARAMETERSOFSYSTEM).addChild(nodeCat);
         nodeSystem = nodeRoot.getChildFromCaption(PARAMETERSOFSYSTEM);
         //System.out.println("EntityData.addReportSettings "+nodeCat+" . "+nodeRoot.getChildFromCaption(REPORTS).getChildFromCaption(REPORTS_CAT_ARRAY[r]));
       
  
         EntityMenu emCat2 = new EntityMenu();
         emCat2.setEntityCategory(SYSTEM_CAT_2,ENTITY_TYPE_CATEGORY2,ICO_MENUCAT_SYSTEM);
        // System.out.println("EntityData.addReportSettings "+REPORTS_CAT_ARRAY[r]+" "+r);
         nodeCat = new DataTreeNode(emCat2);
   if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_TIMOLOGIA) || VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
   {         
         nodeRoot.getChildFromCaption(PARAMETERSOFSYSTEM).addChild(nodeCat);
         nodeSystem = nodeRoot.getChildFromCaption(PARAMETERSOFSYSTEM);         

   }
   
      
         EntityMenu emCat3 = new EntityMenu();
         emCat3.setEntityCategory(SYSTEM_CAT_3,ENTITY_TYPE_CATEGORY2,ICO_MENUCAT_SYSTEM);
        // System.out.println("EntityData.addReportSettings "+REPORTS_CAT_ARRAY[r]+" "+r);
         nodeCat = new DataTreeNode(emCat3);
   if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_APLOGRAFIKA ) || VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
   {         
         nodeRoot.getChildFromCaption(PARAMETERSOFSYSTEM).addChild(nodeCat);
         nodeSystem = nodeRoot.getChildFromCaption(PARAMETERSOFSYSTEM);          
         
    }  
   
   if( VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_FARMERSVAT) || VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
   {
       
       
   }     
         
         
         
         
         

        
       EntityFilterSettings[] dbCompanyErs = new EntityFilterSettings[3];       
       dbCompanyErs[0]=new EntityFilterSettings("επωνυμία","","string","equals","companyName","dbcompany",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       dbCompanyErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","companyVatNo","dbcompany",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       dbCompanyErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","dbcompany","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        int[] companyFieldsOrderby ={2};
        String[] fieldsForSumsDbCompany=null;
        EntityParameter pg = new EntityParameter("dbcompany", "SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.companyName AS\"τίτλος\", dbcompany.companyVatNo AS\"Α.Φ.Μ.\", activitycat.activityDescr AS \"δραστηριότητα\", geocat.geoCatName AS\"πόλη/χωριό\", dbcompany.doyId AS\"Νο Δ.Ο.Υ.\" ,doy.doyname AS\"ονομασία Δ.Ο.Υ.\", active,  bank.bankBranch AS\"τράπεζα\",dbcompany.bankAccount AS\"λογαριασμός τραπεζας\",dbcompany.bankAccountIBAN AS\"ΙΒΑΝ\" FROM dbcompany LEFT JOIN doy ON dbcompany.doyId=doy.doyId LEFT JOIN geocat ON dbcompany.geoCatId=geocat.geoCatId LEFT JOIN bank ON dbcompany.bankId=bank.bankId  LEFT JOIN activitycat ON activitycat.activityCatId = dbcompany.activityCatId ORDER BY dbcompany.companyName"  ,"SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.companyName AS\"τίτλος\", dbcompany.companyVatNo AS\"Α.Φ.Μ.\", dbcompany.doyId, dbcompany.geoCatId,  dbcompany.bankId , dbcompany.bankAccount , dbcompany.bankAccountIBAN, dbcompany.notes" ," FROM dbcompany" ,"",fieldsForSumsDbCompany,dbCompanyDBFields ,"εταιρίες","DORM","Νο εταιρίας","dbCompanyId",dbCompanyErs,null,"εταιρίας", "εταιριών",entityPanelDbCompany,null,fieldsOnTitleDbCompany,fieldsOnTitleCaptionDbCompany,companyFieldsOrderby,2,FIELD_VALIDATION_AFM,globalYearPlusOne/*,"",""*/);
        EntityMenu empg = new EntityMenu();
        empg.setEntityParameter(pg,ICO_TABLE16);
        empg.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempg = new DataTreeNode(empg);
        //nodeRoot.getChildFromCaption(SYSTEM_CAT_1).addChild(nodeempg);
        nodeSystem.getChildFromCaption(SYSTEM_CAT_1).addChild(nodeempg);
        
        
        //---------------------------------------------------------
        // dbyear
        //EntityParameter[] pz = {pb,pc,pd,pe,pf,pl,pm};
        //EntityMenu[] empza = {empb,empc,empd,empe,empf,empl,empf};        
 /*       EntityFilterSettings[] dbYearErs = new EntityFilterSettings[1]; 
        dbYearErs[0]=new EntityFilterSettings("έτος","","string","equals","dbyear","dbyear",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        int[] yearFieldsOrderby ={2,1};
        String[] fieldsForSumsDbYear=null;   
*/        
 //       EntityParameter ph = new EntityParameter("dbyear", "SELECT dbyear.dbYearId AS \"Νο χρήσης\", dbyear.dbyear AS\"χρήση\" FROM dbyear  WHERE dbyear.dbcompanyid='"+VariablesGlobal.globalCompanyId+"' ORDER BY  dbyear.dbYearId" ,"SELECT dbYearId AS\"Νο χρήσης\", dbyear AS\"χρήση\", dbcompanyid AS\"Νο εταιρίας\"","FROM dbyear","",fieldsForSumsDbYear,dbyearDBFields ,"έτη/χρήσεις", "DORM","Νο χρήσης","dbYearId", dbYearErs,null,"χρήσης","χρήσεων",entityPanelDbyear,null,fieldsOnTitleDbyear,fieldsOnTitleCaptionDbyear,yearFieldsOrderby,-1,-1,globalYearPlusOne/*,"",""*/);//SELECT dbyear.dbYearId AS "Νο χρήσης", dbyear.dbyear AS"χρήση", Count(aa) AS "πλήθος περίοδων" FROM dbyear, dbYearDelivery  WHERE dbyear.dbYearId = dbYearDelivery.dbYearId AND dbyear.dbcompanyid='1' GROUP BY dbyear.dbYearId ORDER BY dbyear.dbYearId        
        


        int[] dbUserRolesFieldsOrderby ={2};
        String[] dbUserRolesFieldsForSums=null;
        EntityParameter pj = new EntityParameter("dbuserrole", "SELECT userRoleId AS\"Νο\", roleDescr AS\"όνομα ρόλου\" FROM dbuserrole"  ,"SELECT userRoleId AS\"Νο\", roleDescr AS\"όνομα ρόλου\" ","FROM dbuserrole","",dbUserRolesFieldsForSums,dbUserRolesDBFields ,"ρόλοι", "DORM","Νο ρόλου","userRoleId", null,null,"ρόλου","ρόλων",dbUserRolesEntityPanel,null,dbUserRolesFieldsOnTitle,dbUserRolesFieldsOnTitleCaption,dbUserRolesFieldsOrderby,-1,-1,globalYearPlusOne/*,"",""*/);
        EntityMenu empj = new EntityMenu();
        empj.setEntityParameter(pj,ICO_TABLE16);
        empj.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempj = new DataTreeNode(empj);
        //nodeRoot.getChildFromCaption(SYSTEM_CAT_1).addChild(nodeempj);        
        nodeSystem.getChildFromCaption(SYSTEM_CAT_1).addChild(nodeempj);
        
        //---------------------------------------------------------
        
        int[] userFieldsOrderby ={2};
        String[] fieldsForSumsDbUser=null;
        EntityParameter pk = new EntityParameter("dbuser", "SELECT userId AS\"Νο χρήστη\", username AS\"όνομα χρήστη\", password, nameOfUser AS\"πλήρες όνομα χρήστη\", active, email, dbuserrole.roleDescr AS\" ρόλος \" FROM dbuser LEFT JOIN dbuserrole ON dbuser.userRoleId = dbUserRole.userRoleId"  ,"SELECT userId AS\"Νο χρήστη\", username AS\"ονομασία χρήστη\", password, nameOfUser AS\"πλήρες όνομα χρήστη\", active, email ","FROM dbuser","",fieldsForSumsDbUser,dbuserDBFields ,"χρήστες", "DORM","Νο χρήστη","userId", null,null,"χρήστη","χρηστών",entityPanelDbuser,null,fieldsOnTitleDbuser,fieldsOnTitleCaptionDbuser,userFieldsOrderby,-1,-1,globalYearPlusOne/*,"",""*/);
        EntityMenu empk = new EntityMenu();
        empk.setEntityParameter(pk,ICO_TABLE16);
        empk.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempk = new DataTreeNode(empk);
        //nodeRoot.getChildFromCaption(SYSTEM_CAT_1).addChild(nodeempk);
         nodeSystem.getChildFromCaption(SYSTEM_CAT_1).addChild(nodeempk);   
      
        
        
        //------------------------------------------------------------
       int[] currencyFieldsOrderby ={2};
       String[] fieldsForSumsCurrency=null;
       EntityParameter pb = new EntityParameter("currency", "SELECT currencyId AS \"Νο νομίσματος\",name AS \"ονομασία\", active FROM currency ORDER BY currencyId" ,"SELECT currencyId AS \"Νο νομίσματος\",name AS \"ονομασία\",countOfDecimals ,active","FROM currency","",fieldsForSumsCurrency,currencyDBFields,"νομίσματα","DORM","Νο νομίσματος","currencyId", null,null,"νομίσματος", "νομισμάτων",entityPanelCurrency,null,fieldsOnTitleCurrency,fieldsOnTitleCaptionCurrency,currencyFieldsOrderby,-1,-1,globalYearPlusOne/*,"",""*/);
        EntityMenu empb = new EntityMenu();
        empb.setEntityParameter(pb,ICO_TABLE16);
        empb.setEntityType(ENTITY_TYPE_PARAMETER);
      //  DataTreeNode nodeempb = new DataTreeNode(empb);
      //  nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempb);

        
        //------------------------------------------------------------
        EntityFilterSettings[] geoCatErs = new EntityFilterSettings[1];       
        geoCatErs[0]=new EntityFilterSettings("ονομασία","","string","equals","geoCatName","geocat",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
      // EntityGroupOfComps[] geoCatEntityGroupOfComps = null;
        
        int[] geoCatFieldsOrderby ={2};
        String[] fieldsForSumsGeoCat=null;
        EntityParameter pc = new EntityParameter("geocat",  "SELECT geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\", state AS\"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM geocat ORDER BY geoCatName"  ,"SELECT geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\"" ,"FROM geocat" ,"",fieldsForSumsGeoCat ,townDBFields,"πόλεις/χωριά","DORM","Νο πόλης","geoCatId",geoCatErs,townEntityGroupOfComps, "πόλης","πόλεων",entityPanelGeoCat,null,fieldsOnTitleGeoCat,fieldsOnTitleCaptionGeoCat,geoCatFieldsOrderby,-1,-1,globalYearPlusOne/*,"",""*/);
        EntityMenu empc = new EntityMenu();
        empc.setEntityParameter(pc,ICO_TABLE16);
        empc.setEntityType(ENTITY_TYPE_PARAMETER);
       // DataTreeNode nodeempc = new DataTreeNode(empc);
       // nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempc);

        
        //------------------------------------------------------------
        EntityFilterSettings[] doyErs = new EntityFilterSettings[2];       
        doyErs[0]=new EntityFilterSettings("κωδικός","","string","equals","doyId","doy",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        doyErs[1]=new EntityFilterSettings("ονομασία","","string","equals","doyName","doy",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //EntityGroupOfComps[] doyEntityGroupOfComps = null;
                      
        int[] doyFieldsOrderby ={2};
        String[] fieldsForSumsDoy=null;
        EntityParameter pd = new EntityParameter("doy", "SELECT doyId AS \"Νο Δ.Ο.Υ.\", doyName AS \"ονομασία\", address AS \"διεύθυνση\", town AS \"πόλη/χωριό\", pc AS \"ΤΚ\", tel1 AS \"τηλ 1\", tel2 AS \"τηλ 2\", fax AS \"φαξ\" FROM doy ORDER BY doyId"  ,"SELECT doyId AS \"Νο Δ.Ο.Υ.\", doyName AS \"ονομασία\", address AS \"διεύθυνση\", town AS \"πόλη/χωριό\", pc AS \"ΤΚ\", tel1 AS \"τηλ 1\", tel2 AS \"τηλ 2\", fax AS \"φαξ\" ","FROM doy" ,"",fieldsForSumsDoy,doyDBFields ,"Δ.Ο.Υ.","DORM","Νο Δ.Ο.Υ.","doyId",doyErs,doyEntityGroupOfComps,"της Δ.Ο.Υ.","Δ.Ο.Υ.",entityPanelDoy,null,fieldsOnTitleDoy,fieldsOnTitleCaptionDoy,doyFieldsOrderby,-1,-1,globalYearPlusOne/*,"",""*/);
        EntityMenu empd = new EntityMenu();
        empd.setEntityParameter(pd,ICO_TABLE16);
        empd.setEntityType(ENTITY_TYPE_PARAMETER);
       // DataTreeNode nodeempd = new DataTreeNode(empd);
       // nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempd);

      
         
         //------------------------------------------------------------
        int[] paymenttypeFieldsOrderby ={2};
        String[] fieldsForSumsPaymenttype=null;
        EntityParameter pe = new EntityParameter("paymenttype", "SELECT paymentTypeId AS \"Νο τρόπου πληρωμής\", description AS \"ονομασία\" FROM paymenttype","SELECT paymentTypeId AS \"Νο τρόπου πληρωμής\", description AS \"ονομασία\"","FROM paymenttype","",fieldsForSumsPaymenttype,paymenttypeDBFields ,"τύποι πληρωμών","DORM","Νο τρόπου πληρωμής","paymentTypeId",null,null,"τρόπου πληρωμών", "τρόπων πληρωμών",entityPanelPaymentType,null,fieldsOnTitlePaymentType,fieldsOnTitleCaptionPaymentType,paymenttypeFieldsOrderby,-1,-1,globalYearPlusOne/*,"",""*/);
        EntityMenu empe = new EntityMenu();
        empe.setEntityParameter(pe,ICO_TABLE16);
        empe.setEntityType(ENTITY_TYPE_PARAMETER);
       // DataTreeNode nodeempe = new DataTreeNode(empe);
       // nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempe);
        
        //------------------------------------------------------------
        int[] bankFieldsOrderby ={2};
        String[] fieldsForSumsBank=null;
        EntityParameter pf = new EntityParameter("bank", "SELECT bankId AS \"Νο τράπεζας\", bankBranch AS \"τίτλος τράπεζας\", BIC FROM bank","SELECT bankId AS \"Νο τράπεζας\", bankBranch AS \"τίτλος τράπεζας\"","FROM bank","",fieldsForSumsBank,bankDBFields ,"τράπεζες","DORM","Νο τράπεζας","bankId",null,null,"τράπεζας", "τραπεζών",entityPanelBank,null,fieldsOnTitleBank,fieldsOnTitleCaptionBank,bankFieldsOrderby,-1,-1,globalYearPlusOne/*,"",""*/);
        EntityMenu empf = new EntityMenu();
        empf.setEntityParameter(pf,ICO_TABLE16);
        empf.setEntityType(ENTITY_TYPE_PARAMETER);
        //DataTreeNode nodeempf = new DataTreeNode(empf);
        //nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempf);        

        //------------------------------------------------------------        
        
        
        
        int[] vatCatFieldsOrderby ={3};
        String[] fieldsForSumsVatCat=null;//                                in query: because it has the same table 2 times, we use the 1st table as it is, in order to be queried correct in title fields
        EntityParameter pm = new EntityParameter("vatcat", "SELECT vatcat.vatCatId AS \"Νο κατηγορίας ΦΠΑ\", vatcat.vatDescr AS \"ονομασία\", vatcat.vatPercentage AS \"ποσοστό\", vatcat.vatReducedCat AS \"μειωμένος συντελεστής\" , vcr.vatDescr AS \"ονομασία μειωμ. συντ.\", vatcat.active  FROM vatcat LEFT JOIN vatcat vcr ON vatcat.vatReducedCat=vcr.vatCatId ORDER BY vatcat.vatCatId","SELECT vatCatId AS \"Νο κατηγορίας ΦΠΑ\", vatDescr AS \"ονομασία\", vatPercentage AS \"ποσοστό\"","FROM vatcat","",fieldsForSumsVatCat,vatCatDBFields ,"κατηγορίες ΦΠΑ","DORM","Νο κατηγορίας ΦΠΑ","vatCatId",null,null,"κατηγορίας ΦΠΑ", "κατηγοριών ΦΠΑ",entityPanelVatCat,null,fieldsOnTitleVatCat,fieldsOnTitleCaptionVatCat,vatCatFieldsOrderby,-1,-1,globalYearPlusOne/*,"",""*/);
        EntityMenu empm = new EntityMenu();
        empm.setEntityParameter(pm,ICO_TABLE16);
        empm.setEntityType(ENTITY_TYPE_PARAMETER);
       // DataTreeNode nodeempm = new DataTreeNode(empm);
       // nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempm);     
        
       //------------------------------------------------
  
     /*String[] lookUpFieldActivity={"activityDescr"};
     
        EntityFilterSettings[] activityCatErs = new EntityFilterSettings[1];       
        activityCatErs[0]=new EntityFilterSettings("ονομασία","","string","equals","activityDescr","activitycat",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("activitycat","activitycat","SELECT activityCatId AS\"Νο δραστηριότητας\", activityDescr AS\"δραστηριότητα\" FROM activitycat","","","ORDER BY activityDescr" ,"activityCatId","Νο δραστηριότητας","activityCatId","δραστηριότητα",2,lookUpFieldActivity,"δραστηριότητα",16,"java.lang.String",0,null,null,0,null,null,activityCatQueryEditable,"δραστηριότητα","δραστηριοτήτων",null,entityPanelActivityCat,fieldsOnTitleActivityCat, fieldsOnTitleCaptionActivityCat,activityCatErs,2,1,null,true,-1,-1,null));
       */     
        int[] activityCatFieldsOrderby ={2};
        String[] fieldsForSumsActivityCat=null;//                                in query: because it has the same table 2 times, we use the 1st table as it is, in order to be queried correct in title fields
        EntityParameter pp = new EntityParameter("activitycat", "SELECT activityCatId AS\"Νο δραστηριότητας\", activityDescr AS\"δραστηριότητα\" FROM activitycat ORDER BY activitycat.activityDescr","SELECT activityCatId AS\"Νο δραστηριότητας\", activityDescr AS\"δραστηριότητα\"","FROM activitycat","",fieldsForSumsActivityCat,activityCatDBFields ,"δραστηριότητες","DORM","Νο δραστηριότητας","activityCatId",null,null,"δραστηριότητας", "δραστηριοτήτων",entityPanelActivityCat,null,fieldsOnTitleActivityCat,fieldsOnTitleCaptionActivityCat,activityCatFieldsOrderby,-1,-1,globalYearPlusOne/*,"",""*/);
        EntityMenu empp = new EntityMenu();
        empp.setEntityParameter(pp,ICO_TABLE16);
        empp.setEntityType(ENTITY_TYPE_PARAMETER);         
        
        
        //--------------------------------------------------------

        EntityParameter[] pz = {pb,pc,pd,pe,pf,pm,pp};
        EntityMenu[] empza = {empb,empc,empd,empe,empf,empm,empp};
        //empz.setEntityParameter(pz,ICO_TABLE16);
        //empza.setEntityType(ENTITY_TYPE_PARAMETER);        
        
        
        EntityManyDataManyRec pza = new EntityManyDataManyRec("tables", "διάφοροι πίνακες",pz,empza);
        EntityMenu empz = new EntityMenu();
        empz.setEntityManyDataManyRec(pza,ICO_TABLE16);
        empz.setEntityType(ENTITY_TYPE_DATAMANY_PARAMETERS);
        DataTreeNode nodeempz = new DataTreeNode(empz);
       // nodeRoot.getChildFromCaption(SYSTEM_CAT_1).addChild(nodeempz);         //
        if(nodeSystem.getChildFromCaption(SYSTEM_CAT_2)!=null)
        {         
       nodeSystem.getChildFromCaption(SYSTEM_CAT_1).addChild(nodeempz);
        }
       //---------------------------------------------------------------------------------------------------
        
      int[] companySetSerSalesFieldsOrderby ={2};
       String[] companySetSerSalesFieldsForSums=null;
       EntityParameter pq = new EntityParameter("dbcompanyset", "SELECT dbCompanyId AS \"Νο\" FROM dbcompanyset ORDER BY dbCompanyId" ,"SELECT dbCompanyId AS \"Νο\" ","FROM dbcompanyset","",companySetSerSalesFieldsForSums,companySetSerSalesDBFields,"ρυθμ. παροχής υπηρεσιών","DORO","Νο","dbcompanyid", null,null,"παρ. υπηρεσίας", "παρ. υπηρεσιών",companySetSerSalesEntityPanel,null,companySetSerSalesFieldsOnTitle,companySetSerSalesFieldsOnTitleCaption,companySetSerSalesFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empq = new EntityMenu();
        empq.setEntityParameter(pq,ICO_SETTINGS);
        empq.setEntityType(ENTITY_TYPE_PARAMETER);//ENTITY_TYPE_PARAMETER);

        DataTreeNode nodeempq = new DataTreeNode(empq);
        //nodeRoot.getChildFromCaption(SYSTEM_CAT_2).addChild(nodeempq);        
        if(nodeSystem.getChildFromCaption(SYSTEM_CAT_2)!=null)
        {         
        nodeSystem.getChildFromCaption(SYSTEM_CAT_2).addChild(nodeempq);
        }
        
        //-------------------------------------service sales transaction types below-------------
        int[] actionseriesFieldsOrderby ={1};
        String[] actionseriesFieldsForSums=null;
        EntityParameter pta = new EntityParameter("actionseries", "SELECT actionseries.actionseriesId AS \"Νο\",  actionseries.descr AS \"ονομασία\", actionseries.actionSeriesCode, actionseries.isActive,  actionseries.seriesNextNumber, actiontype.actionTypeDescription FROM actionseries, actiontype WHERE actionseries.actionTypeId = actiontype.actionTypeId AND actionseries.dbCompanyId = actiontype.dbCompanyId AND actionseries.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"SELECT actionseriesId AS \"Νο \",  descr AS \"ονομασία\", isActive","FROM actionseries","WHERE actionseries.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,actionseriesFieldsForSums,actionseriesDBFields ,"σειρές","DORM","Νο","actionseriesId",null,null,"σειράς", "σειρών",actionseriesEntityPanel,null,actionseriesFieldsOnTitle,actionseriesFieldsOnTitleCaption,actionseriesFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empta = new EntityMenu();
        empta.setEntityParameter(pta,ICO_TABLE16);
        empta.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempta = new DataTreeNode(empta);
        //nodeRoot.getChildFromCaption(SYSTEM_CAT_2).addChild(nodeempta);          
        if(nodeSystem.getChildFromCaption(SYSTEM_CAT_2)!=null)
        {         
        nodeSystem.getChildFromCaption(SYSTEM_CAT_2).addChild(nodeempta);
        }
        //------------------------------------------------------------
        
        int[] actionTypeFieldsOrderby ={2};
        String[] fieldsForSumsActionType=null;
        EntityParameter pl = new EntityParameter("actiontype", "SELECT actionTypeId AS \"Νο τύπου παραστατικού\", actionTypeCode, actionTypeDescription AS \"ονομασία\",actiontype.isDebit,lookupconstants.name FROM actiontype  INNER JOIN lookupconstants ON actiontype.actionTypeCatId = lookupconstants.lookupconstantsId WHERE lookupconstants.constantstypeId = 2 AND actiontype.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"SELECT actionTypeId AS \"Νο τύπου παραστατικού\", actionTypeCode, actionTypeDescription AS \"ονομασία\"","FROM actiontype","WHERE actiontype.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,fieldsForSumsActionType,actionTypeDBFields ,"τύποι παραστατικών","DORM","Νο τύπου παραστατικού","actionTypeId",null,null,"τύπου παραστατικών", "τύπων παραστατικών",entityPanelActionType,null,fieldsOnTitleActionType,fieldsOnTitleCaptionActionType,actionTypeFieldsOrderby,-1,-1,globalYearPlusOne/*,"sxactiontype"/*formGlobalTable1*//*,"sxaccount"/*formGlobalTableToApply1*/);
        EntityMenu empl = new EntityMenu();
        empl.setEntityParameter(pl,ICO_TABLE16);
        empl.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempl = new DataTreeNode(empl);
        //nodeRoot.getChildFromCaption(SYSTEM_CAT_2).addChild(nodeempl); 
        if(nodeSystem.getChildFromCaption(SYSTEM_CAT_2)!=null)
        { 
        nodeSystem.getChildFromCaption(SYSTEM_CAT_2).addChild(nodeempl);
        }
         //------------------------------------------------------------
        
        int[] actionstockFieldsOrderby ={1};
        String[] actionstockFieldsForSums=null;
        EntityParameter ptb = new EntityParameter("actionstock", "SELECT actionstockId AS \"Νο\",  descr AS \"ονομασία\",affectType, affectsQuantity, affectsValue FROM actionstock WHERE actionstock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"SELECT actionstockId AS \"Νο \",  descr AS \"ονομασία\"","FROM actionstock","WHERE actionstock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,actionstockFieldsForSums,actionstockDBFields ,"κινήσεις αποθήκης","DORM","Νο","actionstockId",null,null,"κίνησης αποθήκης", "κινήσεων αποθήκης",actionstockEntityPanel,null,actionstockFieldsOnTitle,actionstockFieldsOnTitleCaption,actionstockFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu emptb = new EntityMenu();
        emptb.setEntityParameter(ptb,ICO_TABLE16);
        emptb.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeemptb = new DataTreeNode(emptb);
        //nodeRoot.getChildFromCaption(SYSTEM_CAT_2).addChild(nodeemptb); 
        if(nodeSystem.getChildFromCaption(SYSTEM_CAT_2)!=null)
        {         
        nodeSystem.getChildFromCaption(SYSTEM_CAT_2).addChild(nodeemptb);
        } 
        //------------------------------------------------------------  
         
        int[] actiontraderFieldsOrderby ={1};
        String[] actiontraderFieldsForSums=null;
        EntityParameter ptc = new EntityParameter("actiontrader", "SELECT actiontraderId AS \"Νο\",  descr AS \"ονομασία\" FROM actiontrader WHERE actiontrader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"SELECT actiontraderId AS \"Νο \",  descr AS \"ονομασία\"","FROM actiontrader","WHERE actiontrader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,actiontraderFieldsForSums,actiontraderDBFields ,"κινήσεις συναλλασσόμενου","DORM","Νο","actiontraderId",null,null,"κίνησης συναλλασσόμενου", "τύπων συναλ.",actiontraderEntityPanel,null,actiontraderFieldsOnTitle,actiontraderFieldsOnTitleCaption,actiontraderFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu emptc = new EntityMenu();
        emptc.setEntityParameter(ptc,ICO_TABLE16);
        emptc.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeemptc = new DataTreeNode(emptc);
        //nodeRoot.getChildFromCaption(SYSTEM_CAT_2).addChild(nodeemptc);
        if(nodeSystem.getChildFromCaption(SYSTEM_CAT_2)!=null)
        {        
        nodeSystem.getChildFromCaption(SYSTEM_CAT_2).addChild(nodeemptc);
        }
        // -------------------------service sales transaction types above-----------------          
        
        
        
        
       
        
        
       //---------------------------------------------------------------------------------------------------
     int[] companySetEsoExoFieldsOrderby ={2};
       String[] companySetEsoExoFieldsForSums=null;
       EntityParameter pr = new EntityParameter("dbcompanyset", "SELECT dbCompanyId AS \"Νο\" FROM dbcompanyset ORDER BY dbCompanyId" ,"SELECT dbCompanyId AS \"Νο\" ","FROM dbcompanyset","",companySetSerSalesFieldsForSums,companySetSerSalesDBFields,"ρυθμ. εσόδων εξόδων","DORO","Νο","dbcompanyid", null,null,"εσόδων εξόδων", "εσόδων εξόδων",companySetEsoExoEntityPanel,null,companySetEsoExoFieldsOnTitle,companySetEsoExoFieldsOnTitleCaption,companySetEsoExoFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empr = new EntityMenu();
        empr.setEntityParameter(pr,ICO_SETTINGS);
        empr.setEntityType(ENTITY_TYPE_PARAMETER);//ENTITY_TYPE_PARAMETER);

        DataTreeNode nodeempr = new DataTreeNode(empr);
        //nodeRoot.getChildFromCaption(SYSTEM_CAT_3).addChild(nodeempr); 
        if(nodeSystem.getChildFromCaption(SYSTEM_CAT_3)!=null)
        {
        nodeSystem.getChildFromCaption(SYSTEM_CAT_3).addChild(nodeempr);
        }
     
    }


    public void addReportSettings()  // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
    {     

     /* DataTreeNode nodeCat = null;
      DataTreeNode nodeReports = null;

         //sub categories
         EntityMenu emCat1 = new EntityMenu();
         emCat1.setEntityCategory(REPORT_CAT_1,ENTITY_TYPE_CATEGORY2,ICO_MENUCAT_REPORT);
        // System.out.println("EntityData.addReportSettings "+REPORTS_CAT_ARRAY[r]+" "+r);
         nodeCat = new DataTreeNode(emCat1);
         nodeRoot.getChildFromCaption(REPORTS).addChild(nodeCat);
         nodeReports = nodeRoot.getChildFromCaption(REPORTS);
         //System.out.println("EntityData.addReportSettings "+nodeCat+" . "+nodeRoot.getChildFromCaption(REPORTS).getChildFromCaption(REPORTS_CAT_ARRAY[r]));
       
         
         EntityMenu emCat2 = new EntityMenu();
         emCat2.setEntityCategory(REPORT_CAT_2,ENTITY_TYPE_CATEGORY2,ICO_MENUCAT_REPORT);
        // System.out.println("EntityData.addReportSettings "+REPORTS_CAT_ARRAY[r]+" "+r);
         nodeCat = new DataTreeNode(emCat2);
         nodeRoot.getChildFromCaption(REPORTS).addChild(nodeCat);
         nodeReports = nodeRoot.getChildFromCaption(REPORTS);         
      
       */  
 
     }

  public void addStatisticsNodes() 
  {

      /*
    public static final int GRAPH_TYPE_PIE=1;
    public static final int GRAPH_TYPE_BAR3D=2;
    public static final int GRAPH_TYPE_LINE=3       
      */ 
      
      
      
 /*       EntityDockableGraph[] entityDockableGraph1 = new EntityDockableGraph[5];
        //entityDockableGraph1[0] =new EntityDockableGraph("Toπ 10 προϊόντα",GRAPH_TYPE_PIE,null,null, 0,0,1,1);//"SELECT product.productId AS id, product.productName AS \"προϊόν\", COUNT(product.productId) AS \"πλήθος παρ\", SUM(invoice.value) AS \"τιμή παρ/κών\" FROM product, invoice WHERE product.productId=invoice.productId GROUP BY product.productId ORDER BY SUM(invoice.value) DESC LIMIT 10",0,0,1,1);
        entityDockableGraph1[0] =new EntityDockableGraph("πορεία ποσών τοπ 6 εταιριών",GRAPH_TYPE_LINE,null,null,1,0,1,1);//"SELECT c.dbCompanyId AS id, c.companyName, COUNT(i.date) AS count, SUM(i.value) AS sum, AVG(i.value) AS average, MIN(i.value) FROM dbcompany c, invoice i WHERE c.dbCompanyId=i.dbCompanyId GROUP BY i.dbCompanyId ORDER BY sum LIMIT 6","SELECT invoice.dbCompanyId AS \"id\", dbyear.dbyear AS \"χρήση\",COUNT(invoice.date) AS \"πληθ παρ\", SUM(invoice.value) AS \"τιμή παρ/κων\", AVG(invoice.value) AS average,dbcompany.companyName AS \"εταιρία\" FROM dbyear, invoice, dbcompany WHERE dbcompany.dbCompanyId=invoice.dbCompanyId AND dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId GROUP BY invoice.dbCompanyId, dbyear.dbyear ORDER BY invoice.dbCompanyId, dbyear.dbyear",1,0,1,1);
        entityDockableGraph1[1] =new EntityDockableGraph("Toπ 10 προϊόντων "+VariablesGlobal.globalCompanyName+" έτους "+VariablesGlobal.globalYear,GRAPH_TYPE_PIE,null,null,0,1,1,1);//"SELECT product.productId AS id, product.productName, COUNT(product.productId) AS \"πληθ παρ\", SUM(invoice.value) AS \"τιμή παρ/κών\" FROM product, invoice WHERE product.productId=invoice.productId AND dbyear="+VariablesGlobal.globalYear+" AND dbCompanyId="+VariablesGlobal.globalCompanyId+" GROUP BY product.productId ORDER BY SUM(invoice.value) DESC LIMIT 10",0,1,1,1);
        //entityDockableGraph1[2] =new EntityDockableGraph("πορεία ποσών τοπ 9 προϊόντων",GRAPH_TYPE_LINE,null,null,1,1,1,1);//"SELECT p.productId as id, p.productName, COUNT(i.date) AS count, SUM(i.value) AS sum, AVG(i.value) AS average FROM product p, invoice i WHERE p.productId=i.productId GROUP BY p.productId ORDER BY sum LIMIT 9","SELECT invoice.productId AS id, dbyear.dbyear AS \"χρήση\",product.productName \"προϊόν\", COUNT(invoice.date) AS \"πληθ παρ\", SUM(invoice.value) AS \"τιμή παρ/κων\" FROM dbyear, invoice, product WHERE dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId AND Product.productId=invoice.productId GROUP BY invoice.productId, dbyear.dbyear ORDER BY invoice.productId, dbyear.dbyear",1,1,1,1);
        entityDockableGraph1[2] =new EntityDockableGraph("πωλήσεις ανα νομό",2,null,null,1,2,1,1);
        entityDockableGraph1[3] =new EntityDockableGraph("μεγαλύτεροι προμηθευτές",2,null,null,0,2,2,1);
        entityDockableGraph1[4] =new EntityDockableGraph("πορεία ποσών εταιριών",GRAPH_TYPE_LINE,null,null,1,0,1,1);//"SELECT c.dbCompanyId AS id, c.companyName, COUNT(i.date) AS count, SUM(i.value) AS sum, AVG(i.value) AS average, MIN(i.value) FROM dbcompany c, invoice i WHERE c.dbCompanyId=i.dbCompanyId GROUP BY i.dbCompanyId ORDER BY sum LIMIT 6","SELECT invoice.dbCompanyId AS \"id\", dbyear.dbyear AS \"χρήση\",COUNT(invoice.date) AS \"πληθ παρ\", SUM(invoice.value) AS \"τιμή παρ/κων\", AVG(invoice.value) AS average,dbcompany.companyName AS \"εταιρία\" FROM dbyear, invoice, dbcompany WHERE dbcompany.dbCompanyId=invoice.dbCompanyId AND dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId GROUP BY invoice.dbCompanyId, dbyear.dbyear ORDER BY invoice.dbCompanyId, dbyear.dbyear",1,0,1,1);

       
       EntityFilterSettings[] scoreErs = new EntityFilterSettings[6];
       scoreErs[0]=new EntityFilterSettings("εταιρία","checkboxTable","string","","dbCompanyId","dbcompany","invoice","",0,-1,-1,FIELD_NOCOMPLETION);
       scoreErs[1]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbyear","dbyear","invoice", "",0,0,-1,FIELD_NOCOMPLETION);
       scoreErs[2]=new EntityFilterSettings("αποστολή","checkboxTable","string","equals","deliveryId","dbDelivery","invoice","",0,-1,-1,FIELD_NOCOMPLETION); 
       scoreErs[3]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","buyer","invoice","",-1,-1,-1,FIELD_NOCOMPLETION);
       scoreErs[4]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","product","invoice","",-1,-1,-1,FIELD_NOCOMPLETION);
       scoreErs[5]=new EntityFilterSettings("τύπος παραστατικού","checkboxTable","string","","paymentTypeId","paymentType","invoice","",-1,-1,-1,FIELD_NOCOMPLETION);
       
       EntityGroupOfComps[] entityGroupOfComps = null;


        EntityScoreBoard entityScoreBoardA = new EntityScoreBoard("γραφήματα",entityDockableGraph1,scoreErs,entityGroupOfComps,globalYearPlusOne);

        EntityMenu emsga = new EntityMenu();
        emsga.setEntityScoreBoard(entityScoreBoardA,ICO_CHARTBAR);
        emsga.setEntityType(ENTITY_TYPE_DOCKABLEGRAPH);
        DataTreeNode nodeemsga = new DataTreeNode(emsga);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsga);        
     
        

        EntityStatistics[] sa = new EntityStatistics[2];
        sa[0] = new EntityStatistics("statInvoicespercompany","dbcompany","παραστατικά ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum, SUM(invoice.valueReturn) AS sumret, AVG(invoice.value) AS average","FROM dbcompany, invoice","WHERE dbcompany.dbCompanyId=invoice.dbCompanyId","GROUP BY dbcompany.dbCompanyId","ORDER BY dbcompany.companyName",false,null,true,"invoice.dbyear","dbCompanyId","dbCompanyId",null,null,null);                                                     																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)
        sa[1] = new EntityStatistics("statInvoicespercompany","invoice","παραστατικά ανα εταιρία","SELECT invoice.customerId, invoice.dbCompanyId,invoice.dbyear,  invoice.buyerId ,invoice.paymentTypeId,invoice.productId, invoice.date,invoice.value, invoice.valueReturn","FROM invoice","","","ORDER BY invoice.buyerId",false,null,true,"invoice.dbyear","dbCompanyId","dbCompanyId",null,null,null);
        //EntityStatistics sa = new EntityStatistics("invoicespercompany","dbcompany","παραστατικά ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum, SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM dbcompany, invoice","WHERE dbcompany.dbCompanyId=invoice.dbCompanyId","GROUP BY dbcompany.dbCompanyId","ORDER BY dbcompany.companyName",false,null,true,"invoice.dbyear","dbCompanyId","dbCompanyId");                                                     																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)																								
        EntityMenu emsa = new EntityMenu();
        emsa.setEntityStatistics(sa,ICO_STATISTICS16);
        emsa.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsa = new DataTreeNode(emsa);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsa);


        EntityStatistics[] sb = new EntityStatistics[2];
        sb[0] = new EntityStatistics("statDeliveriespercompany","dbcompany","αποστολές ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, application.dbyear, application.deliveryId, COUNT(application.deliveryId) AS count, SUM(application.value) AS sum, SUM(application.valueReturn) AS sumret, SUM(application.payment) AS payment","FROM dbcompany, application","WHERE dbcompany.dbCompanyId=application.dbCompanyId","GROUP BY dbcompany.dbCompanyId, application.dbyear,application.deliveryId","ORDER BY dbcompany.companyName, application.dbyear, application.deliveryId",true,"application.dbCompanyId",true,"application.dbyear","dbCompanyId","dbCompanyId",null,null,null);                                                     																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)
        sb[1] = new EntityStatistics("statDeliveriespercompany","invoice","παραστατικά ανα εταιρία","SELECT invoice.customerId, invoice.dbCompanyId,invoice.dbyear,  invoice.buyerId ,invoice.paymentTypeId,invoice.productId, invoice.date,invoice.value, invoice.valueReturn","FROM invoice","","","ORDER BY invoice.buyerId",true,"invoice.dbCompanyId",true,"invoice.dbyear","dbCompanyId","dbCompanyId",null,null,null);
        //EntityStatistics sa = new EntityStatistics("invoicespercompany","dbcompany","παραστατικά ανα εταιρία","SELECT dbcompany.dbCompanyId, dbcompany.companyName, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum, SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM dbcompany, invoice","WHERE dbcompany.dbCompanyId=invoice.dbCompanyId","GROUP BY dbcompany.dbCompanyId","ORDER BY dbcompany.companyName",false,null,true,"invoice.dbyear","dbCompanyId","dbCompanyId");                                                     																																																																																//boolean isFilterCompanyIn, String fielddbCcompanyIdNameIn, boolean isFilterYearIn,String fieldYearNameIn)																								
        EntityMenu emsb = new EntityMenu();
        emsb.setEntityStatistics(sb,ICO_STATISTICS16);
        emsb.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsb = new DataTreeNode(emsb);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsb);


        
        EntityStatistics[] sc = new EntityStatistics[1];
        sc[0] = new EntityStatistics("statInvoicesperyear","invoice","παραστατικά ανα χρήση","SELECT dbyear.dbyear, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum,SUM(invoice.valueReturn) AS sumret, AVG(invoice.value) AS average","FROM dbyear, invoice","WHERE dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId","GROUP BY dbyear.dbyear","ORDER BY dbyear.dbyear",true,"dbyear.dbCompanyId",false,null,null,null,null,null,null);
        //EntityStatistics sb = new EntityStatistics("invoicesperyear","invoice","παραστατικά ανα χρήση","SELECT dbyear.dbyear, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum,SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM dbyear, invoice","WHERE dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId","GROUP BY dbyear.dbyear","ORDER BY dbyear.dbyear",true,"dbyear.dbCompanyId",false,null,null,null);
        EntityMenu emsc = new EntityMenu();
        emsc.setEntityStatistics(sc,ICO_STATISTICS16);
        emsc.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsc = new DataTreeNode(emsc);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsc);

        EntityStatistics[] sd = new EntityStatistics[2];
        sd[0] = new EntityStatistics("statSumsofbuyers","buyer","ποσά αγοραστών","SELECT buyer.buyerId, buyer.buyerTitle, COUNT(invoice.buyerId) AS count, SUM(invoice.value) AS sum,SUM(invoice.valueReturn) AS sumret, AVG(invoice.value) AS average","FROM buyer, invoice","WHERE buyer.buyerId=invoice.buyerId","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear","buyerId","buyerId",null,null,null);
        sd[1] = new EntityStatistics("statSumsofbuyers2","invoice","ποσά αγοραστών","SELECT invoice.customerId,invoice.dbCompanyId,invoice.dbyear,  invoice.buyerId ,invoice.paymentTypeId,invoice.productId, invoice.date,invoice.value, invoice.valueReturn","FROM invoice","","","ORDER BY invoice.buyerId",true,"invoice.dbCompanyId",true,"invoice.dbyear","buyerId","buyerId",null,null,null);
        //EntityStatistics sc = new EntityStatistics("sumsofbuyers","buyer","ποσά αγοραστών","SELECT buyer.buyerId, buyer.buyerTitle, COUNT(invoice.buyerId) AS count, SUM(invoice.value) AS sum,SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM buyer, invoice","WHERE buyer.buyerId=invoice.buyerId","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear","buyerId","buyerId");
        EntityMenu emsd = new EntityMenu();
        emsd.setEntityStatistics(sd,ICO_STATISTICS16);
        emsd.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsd = new DataTreeNode(emsd);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsd);

        
        
        EntityStatistics[] se = new EntityStatistics[2];
        se[0] = new EntityStatistics("statSumsofproducts","product","ποσά προϊόντων","SELECT product.productId, product.productName, COUNT(product.productId) AS count, SUM(invoice.value) AS sum,SUM(invoice.valueReturn) AS sumret, AVG(invoice.value) AS average","FROM product, invoice","WHERE product.productId=invoice.productId","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear","productId","productId",null,null,null);
        se[1] = new EntityStatistics("statSumsofproducts2","invoice","ποσά προϊόντων","SELECT invoice.customerId,invoice.dbCompanyId,invoice.dbyear,  invoice.buyerId ,invoice.paymentTypeId,invoice.productId, invoice.date,invoice.value, invoice.valueReturn","FROM invoice","","","ORDER BY invoice.buyerId",true,"invoice.dbCompanyId",true,"invoice.dbyear","productId","productId",null,null,null);        
        //EntityStatistics sd = new EntityStatistics("sumsofproducts","product","ποσά προϊόντων","SELECT product.productId, product.productName, COUNT(product.productId) AS count, SUM(invoice.value) AS sum,SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM product, invoice","WHERE product.productId=invoice.productId","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear","productId","productId");
        EntityMenu emse = new EntityMenu();
        emse.setEntityStatistics(se,ICO_STATISTICS16);
        emse.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemse = new DataTreeNode(emse);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemse);

        
        EntityStatistics[] sf = new EntityStatistics[2];
        sf[0] = new EntityStatistics("statSumsofcustomers","customer","ποσά αγροτών","SELECT customer.customerId, customer.surname,customer.name, customer.fatherName, COUNT(invoice.customerId) AS count, SUM(invoice.value) AS sum,SUM(invoice.valueReturn) AS sumret, AVG(invoice.value) AS average","FROM customer, invoice","WHERE customer.customerId=invoice.customerId","GROUP BY customer.customerId","ORDER BY customer.surname, customer.name, customer.fatherName",true,"invoice.dbCompanyId",true,"invoice.dbyear","customerId","customerId",null,null,null);
        sf[1] = new EntityStatistics("statSumsofcustomers2","invoice","ποσά αγροτών","SELECT invoice.customerId,invoice.dbCompanyId,invoice.dbyear, invoice.buyerId ,invoice.paymentTypeId,invoice.productId, invoice.date,invoice.value, invoice.valueReturn","FROM invoice","","","ORDER BY invoice.buyerId",true,"invoice.dbCompanyId",true,"invoice.dbyear","customerId","customerId",null,null,null);
        //EntityStatistics sc = new EntityStatistics("sumsofbuyers","buyer","ποσά αγοραστών","SELECT buyer.buyerId, buyer.buyerTitle, COUNT(invoice.buyerId) AS count, SUM(invoice.value) AS sum,SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM buyer, invoice","WHERE buyer.buyerId=invoice.buyerId","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear","buyerId","buyerId");
        EntityMenu emsf = new EntityMenu();
        emsf.setEntityStatistics(sf,ICO_STATISTICS16);
        emsf.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsf = new DataTreeNode(emsf);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsf);

                
        
     *///   EntityStatistics[] sg = new EntityStatistics[1];
      //  sg[0] = new EntityStatistics("statInvoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.valueReturn) AS \"ΕΠΙΣΤΡΟΦΗ\"","FROM invoice",""/*invoice.customerId the same because we need where*/,"GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null,null,null,null);
        
        //EntityStatistics se = new EntityStatistics("invoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'name') AS \"μήνας\" , COUNT(*)AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.returnValue) AS sumret","FROM invoice","WHERE"/*invoice.customerId the same because we need where*/,"GROUP BY returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
     /*   EntityMenu emsg = new EntityMenu();
        emsg.setEntityStatistics(sg,ICO_STATISTICS16);
        emsg.setEntityType(ENTITY_TYPE_STATISTICS);
        DataTreeNode nodeemsg = new DataTreeNode(emsg);
        nodeRoot.getChildFromCaption(METRICS).addChild(nodeemsg);
*/
  }
  
  public void addToolNodes() 
  {
      // IMPORT = 1;       EXPORT = 2;
      PanelDataImportExport panelImport = new PanelDataImportExport(1);
      //panelImport.setEntityImport();
        EntityTool ta = new EntityTool("import",CAPTION_IMPORT_DATA,"",panelImport);
        //ta[0] = new EntityStatistics("statInvoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.valueReturn) AS \"ΕΠΙΣΤΡΟΦΗ\"","FROM invoice",""/*invoice.customerId the same because we need where*/,"GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
        
        //EntityStatistics se = new EntityStatistics("invoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'name') AS \"μήνας\" , COUNT(*)AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.returnValue) AS sumret","FROM invoice","WHERE"/*invoice.customerId the same because we need where*/,"GROUP BY returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
        EntityMenu emta = new EntityMenu();
        emta.setEntityTool(ta,ICO_TOOLS);
        emta.setEntityType(ENTITY_TYPE_TOOL);
        DataTreeNode nodeemta = new DataTreeNode(emta);
        nodeRoot.getChildFromCaption(MAINTENANCEOFSYSTEM).addChild(nodeemta);

        
       // IMPORT = 1;       EXPORT = 2; 
      PanelDataImportExport panelExport = new PanelDataImportExport(2);
      //panelExport.setEntityExport();
        EntityTool tb = new EntityTool("export",CAPTION_EXPORT_DATA,"",panelExport);
        //ta[0] = new EntityStatistics("statInvoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.valueReturn) AS \"ΕΠΙΣΤΡΟΦΗ\"","FROM invoice",""/*invoice.customerId the same because we need where*/,"GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
        
        //EntityStatistics se = new EntityStatistics("invoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'name') AS \"μήνας\" , COUNT(*)AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.returnValue) AS sumret","FROM invoice","WHERE"/*invoice.customerId the same because we need where*/,"GROUP BY returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
        EntityMenu emtb = new EntityMenu();
        emtb.setEntityTool(tb,ICO_TOOLS);
        emtb.setEntityType(ENTITY_TYPE_TOOL);
        DataTreeNode nodeemtb = new DataTreeNode(emtb);
        nodeRoot.getChildFromCaption(MAINTENANCEOFSYSTEM).addChild(nodeemtb);        
        
  }
  
  /*      help
  
  
               DataTreeNode dataTreeNodeDataEntry =  entityData.getDataTreeNodeDataEntry(caption);
             int countOfChildren = dataTreeNodeDataEntry.getNumberOfChildren();
             DataTreeNode dtndtCaption =  dataTreeNodeDataEntry.getChildFromCaption(caption);
             
             System.out.println("ReportAreaGenerated.displayDrillDialog     selectedTableRowIn:"+selectedTableRowIn+"     name:"+name+"  caption:"+caption);
             for(int d = 0;d<countOfChildren;d++)
             {
                DataTreeNode dtndtIndex = dataTreeNodeDataEntry.getChildFromIndex(d);
  
  
  
  */
  

  
  
}
// ServiceSales

// todo features---------
// manytomany: able to create new records. When data changed(insert or delete or edit) do not allow to close or exit.
// if view n order preferences are setted (ie farmer) it affects lookups (ie farmer in application. So we need to apply the setted view n order in lookup.
// (servicesales)add constants 'sale xondrikh' and 'sale lianikh' in type of document. Also add textboxs in services prices(hondriki, lainikh).As a consequence change calculations in prices of sales
// 2016-02-09 (program) be able to change lengths of report columns 
// 2016-02-10 (program)Reports: when 3 or more bands shows again band 2(for example in service sales in 3bands shows again the documentheader before each documentline(service))
// 2016-02-10 (program)reports: fix ordering on 2nd or 3rd band
// 2016-02-13 (program)PanelManyDataManyRec: when close panel and save all panels to ask and then save or not 
// 2016-05-17 program 'save and new' button

//changes ------
// 2015-11-22 remove of EntityReportGroup (entitydata files)
// 2015-12-24 allow more than 1 tab with the same name (program)
// 2016-02-02 constant length of report columns (program)
// 2016-02-06 changed lengths of report columns (entitydata files)(servicesales only)
// 2016-02-09 added field 'message' in dbcompany. Shows this message in status bar after login (both)
// 2016-05-15 changed the call of utilsDouble getSettingsFromDB to DialogMain (program)
//2016-08-27 added actiontypecat constants (servicesales)
// 2017-06-04 changed second button which is editlookup to listlookup with new and edit, also changed icon to yellow star (program)
