package com.tool.domain;

import com.tool.guicomps.*;
import com.tool.model.EntityCalculate;
import com.tool.model.EntityCheckFields;
import com.tool.model.EntityDBFields;
import com.tool.model.EntityDBFieldsCalculation;
import com.tool.model.EntityFilterSettings;
import com.tool.model.EntityGroupOfComps;
import com.tool.model.EntityGroupOfPanels;
import com.tool.model.EntityInfo;
import com.tool.model.EntityLookUp;
import com.tool.model.EntityLookupTableConstants;
import com.tool.model.EntityLookupTableConstantsData;
import com.tool.model.EntityManyDataManyRec;
import com.tool.model.EntityMenu;
import com.tool.model.EntityPanel;
import com.tool.model.EntityParameter;
import com.tool.model.EntityReport;
import com.tool.model.EntityReportBand;
import com.tool.model.EntityReportBandField;
import com.tool.model.EntityTemplate;
import com.tool.model.EntityUpdateAdditional;
import com.tool.utils.*;
import java.util.ArrayList;

public class EntityDataEsoExo extends EntityData implements Constants
{
       DataTree dTree;
       DataTreeNode nodeRoot ;

     
       
    public static final String REPORT_CAT_1 = "ανάλυση";
    public static final String REPORT_CAT_2 = "λογαριασμοί";
    public static final String REPORT_CAT_3 = "συναλλασόμενοι";
    public static final String REPORT_CAT_4 = "κατάλογοι";         
       
       
    
       String globalYearPlusOne="";
       int intYearPlusOne=0;
     
       
        //----------------------------------------------------------------
        
       EntityReport entReportEsExDoc;
       
       
        EntityDBFields[] esoexoHeaderDBFields = new EntityDBFields[14];
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] esoexoEntityGroupOfComps =new EntityGroupOfComps[6];
        EntityGroupOfPanels[] esoexoEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        String  saleQueryEditableTemplate = "SELECT * FROM sxesoexoheader WHERE sxesoexoheader.isTemplate = 1";
        
        String saleQueryEditable = "SELECT * FROM sxesoexoheader WHERE sxesoexoheader.isTemplate = 0";//product.productId AS \"Νο προϊόντος\", product.productName AS \"ονομασία\", product.currencyId FROM product";
        String[] fieldsOnTitleEsex ={"sxesoexoheader.esoexoCodeOfDocument","sxesoexoheader.dateOfEsoexo"};//,"sxtrader.name"};
        String[] fieldsOnTitleCaptionEsex  ={"κωδ παρ/κού","ημερομηνία"};//,"συναλλασσόμενος"};      
        String[] strSaleCategories = {DATAENTRY,METRICS};

        String[] fieldsUniqueSale = null;
        
        
        EntityUpdateAdditional[] updateAdditionalActionType = null;//new EntityUpdateAdditional[1];
        
        EntityPanel entityPanelEsexDataentry;// = new EntityPanel("ODOR","sxesoexoheader",esoexoHeaderDBFields,esoexoEntityGroupOfComps,esoexoEntityGroupOfPanels,"Νο εσόδων εξόδων","","esoexoheaderId",saleQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSale,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,updateAdditionalActionType,entReportEsExDoc);      // entReportEsExDoc
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProducttraders = new EntityPanel("statProducttraders","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT sxtrader.traderId AS \"νο αγρότη\", sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, sxtrader","WHERE invoice.traderId = sxtrader.traderId AND invoice.productId=","GROUP BY sxtrader.traderId","ORDER BY sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelEsex;// = new EntityPanel[] { entityPanelEsexDataentry};//,entityPanelProductStatistics,entityPanelProducttraders,entityPanelProductBuyers,entityPanelProductSalesPerDate};
       
        //----------------------------------------------------------------
        EntityDBFields[] traderDBFields = new EntityDBFields[23];
        EntityGroupOfComps[] traderEntityGroupOfComps = new EntityGroupOfComps[7];
        EntityGroupOfPanels[] traderEntityGroupOfPanels = new EntityGroupOfPanels[3];
        
        // same as second query in etityInfo
        //String traderQueryEditable="SELECT sxtrader.traderId AS \"Νο πελάτη\", sxtrader.surname AS \"επίθετο\", sxtrader.name AS\"όνομα\", sxtrader.fathername AS \"πατρόνυμο\", sxtrader.traderAfm AS \"Α.Φ.Μ.\", sxtrader.doyId, sxtrader.idNo AS \"αρ ταυτοτ\", sxtrader.townId, sxtrader.address AS \"διέυθυνση\", sxtrader.phone AS \"τηλέφωνο\" FROM sxtrader, town WHERE sxtrader.townId=town.townId";
        String traderQueryEditable="SELECT * FROM sxtrader";// LEFT JOIN doy ON sxtrader.doyId=doy.doyId";// LEFT JOIN bank ON sxtrader.bankId=bank.bankId";        
        String[] fieldsOnTitletrader ={"traderId","title","vatNo"};
        String[] fieldsOnTitleCaptiontrader  ={"Νο","όνομα","ΑΦΜ"};
        String[] strtraderCategories = {DATAENTRY,METRICS};
        String[] fieldsUniquetrader = {"vatNo"};
        //STATS be careful to have in the query all the fields that are also in the title
        EntityPanel entityPaneltraderDataentry;// = new EntityPanel("ODOR","sxtrader",traderDBFields,traderEntityGroupOfComps,traderEntityGroupOfPanels,"Νο πελάτη","","traderId",traderQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniquetrader,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,entReportEsExDoc);      
        EntityPanel entityPaneltraderHistory;// = new EntityPanel("stattraderHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT sxtrader.traderId, sxtrader.dbCompanyId, sxesoexoheader.esoexoheaderId, sxactiontype.actionTypeCode, sxesoexoheader.esoexoCodeOfDocument,sxesoexoheader.dbYearId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.isPrinted, sxesoexoheader.countTotal,sxesoexoheader.quantityTotal, sxesoexoheader.pricePreVat, sxesoexoheader.priceVat, sxesoexoheader.priceTotal","FROM sxtrader, sxesoexoheader, sxactiontype","WHERE sxtrader.traderId = sxesoexoheader.traderId AND  = sxesoexoheader.dbCompanyId AND sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId AND sxtrader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxtrader.traderId =","","ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoCodeOfDocument",false,"",false,"",entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);     
        //EntityPanel entityPaneltraderProducts = new EntityPanel("stattraderProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.traderId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderBuyers = new EntityPanel("stattraderBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.traderId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderSalesPerDate = new EntityPanel("stattraderSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.traderId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPaneltrader;// = new EntityPanel[] { entityPaneltraderDataentry,entityPaneltraderHistory};//,entityPaneltraderStatistics,entityPaneltraderProducts,entityPaneltraderBuyers,entityPaneltraderSalesPerDate};

        /*String[] deliveryFields={"traderId","dateOfApplication","permanent","dbyear","deliveryId","dbCompanyId"};
        String[] deliveryFieldsTranslation={"traderId","ημ/νία αίτησης","υπολογισμένο","dbyear","deliveryId","dbCompanyId"};
        int[] deliveryGroupOfComps = null;*/
       //---------------------------------------------------------------- 
     
        //----------------------------------------------------------------
        
        EntityDBFields[] sxAccountDBFields = new EntityDBFields[14];
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] sxAccountEntityGroupOfComps =new EntityGroupOfComps[3];
        EntityGroupOfPanels[] serviceEntityGroupOfPanels = null;
        
        
        String sxaccountQueryEditable = "SELECT * FROM sxaccount";//product.productId AS \"Νο προϊόντος\", product.productName AS \"ονομασία\", product.currencyId FROM product";
        String[] fieldsOnTitleSXAccount ={"accountId","accountDescr"};
        String[] fieldsOnTitleCaptionSXAccount  ={"Νο","ονομασία"};      
        String[] strSXAccountCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueSXAccount = null;
        EntityCheckFields[] entityCheckFieldsSXAccount = null;
        EntityPanel entityPanelSXAccountDataentry = new EntityPanel("ODOR","sxaccount",sxAccountDBFields,sxAccountEntityGroupOfComps,serviceEntityGroupOfPanels,"Νο λογαριασμού","","accountId",sxaccountQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSXAccount,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsSXAccount,null);      
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProducttraders = new EntityPanel("statProducttraders","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT sxtrader.traderId AS \"νο αγρότη\", sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, sxtrader","WHERE invoice.traderId = sxtrader.traderId AND invoice.productId=","GROUP BY sxtrader.traderId","ORDER BY sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelSXAccount = new EntityPanel[] { entityPanelSXAccountDataentry};//,entityPanelProductStatistics,entityPanelProducttraders,entityPanelProductBuyers,entityPanelProductSalesPerDate};
        //-----------------------------------------------------------------------

        String[] fieldsOnTitleEsexTemp = {"sxesoexoheader.titleOfTemplate","sxesoexoheader.esoexoCodeOfDocument"};//,"sxesoexoheader.dateOfEsoexo"};//,"sxtrader.name"};
        String[] fieldsOnTitleCaptionEsexTemp ={"περιγραφή προτύπου","κωδ παρ/κού"};//,"ημερομηνία"};//,"συναλλασσόμενος"};         
        
        
     
        EntityDBFields[] esoexoHeaderTempDBFields = new EntityDBFields[10];
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] esoexoTempEntityGroupOfComps =new EntityGroupOfComps[5];
        EntityGroupOfPanels[] esoexoTempEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
    
        EntityPanel entityPanelEsexTempDataentry;// = new EntityPanel("ODOR","sxesoexoheader",esoexoHeaderDBFields,esoexoEntityGroupOfComps,esoexoEntityGroupOfPanels,"Νο εσόδων εξόδων","","esoexoheaderId",saleQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSale,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,updateAdditionalActionType,entReportEsExDoc);      // entReportEsExDoc
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProducttraders = new EntityPanel("statProducttraders","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT sxtrader.traderId AS \"νο αγρότη\", sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, sxtrader","WHERE invoice.traderId = sxtrader.traderId AND invoice.productId=","GROUP BY sxtrader.traderId","ORDER BY sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelEsexTemp;// = new EntityPanel[] { entityPanelEsexDataentry};//,entityPanelProductStatistics,entityPanelProducttraders,entityPanelProductBuyers,entityPanelProductSalesPerDate};
               
        
    
        
         //----------------------------------------------------------------        
  /*   EntityDBFields[] paymenttypeLineDBFields = new EntityDBFields[2]; 
       EntityDBFields[] paymenttypeDBFields = new EntityDBFields[1];      

        
        EntityGroupOfComps[] paymenttypeEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] paymenttypeEntityGroupOfPanels = null;

        
        String paymenttypeQueryEditable = "SELECT * FROM paymenttype";
        String[] fieldsOnTitlePaymentType ={"paymentTypeId","description"};
        String[] fieldsOnTitleCaptionPaymentType  ={"Νο","ονομασία"};
        String[] fieldsUniquePaymentType = null;
        EntityCheckFields[] entityCheckFieldsPaymentType = null;
        EntityPanel entityPanelPaymenttypeDataentry = new EntityPanel("ODOR","paymenttype",paymenttypeDBFields,paymenttypeEntityGroupOfComps,paymenttypeEntityGroupOfPanels,"Νο τρόπου πληρωμής","","paymentTypeId",paymenttypeQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniquePaymentType,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsPaymentType,null);  
        EntityPanel[] entityPanelPaymentType = new EntityPanel[] { entityPanelPaymenttypeDataentry};
*/
        //----------------------------------------------------------------
        EntityDBFields[] incomeSettLineDBFields = new EntityDBFields[6];
        EntityDBFields[] incomeSettDBFields = new EntityDBFields[1];
        

        EntityGroupOfComps[] incomeSettEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] incomeSettEntityGroupOfPanels = null;
        
        
        String incomeSettQueryEditable ="SELECT * FROM sxincomedocsettings";
        String[] fieldsOnTitleIncomeSett ={"incomeDocSettingsId"};
        String[] fieldsOnTitleCaptionIncomeSett  ={"Νο"}; 
        String[] fieldsUniqueIncomeSett = null;  
        EntityCheckFields[] entityCheckFieldsIncomeSettings = null;
        EntityPanel entityPanelIncomeSettDataentry = new EntityPanel("ODOR","sxincomedocsettings",incomeSettDBFields,incomeSettEntityGroupOfComps,incomeSettEntityGroupOfPanels,"Νο νομίσματος","","incomeDocSettingsId",incomeSettQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueIncomeSett,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsIncomeSettings,null);      
        EntityPanel[] entityPanelIncomeSett = new EntityPanel[] { entityPanelIncomeSettDataentry};
 
        //----------------------------------------------------------------
        
        EntityDBFields[] sxVatDocDBFields = new EntityDBFields[81];
        
        EntityFilterSettings[] eCalculateFilterVatDoc = new EntityFilterSettings[2];       
        //eFilterSettings[0]=new EntityFilterSettings("ονομασία","","string","equals","vatDocDescr","sxvatdocforperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        EntityGroupOfComps[] entityGroupOfFilterCompsVatDoc = new EntityGroupOfComps[1]; // if not null creates tabs, and nothing is shown
        // entityGroupOfFilterCompsVatDoc[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
         
        String [] sqlQueryTableCalcVatDoc = new String[1];  
             String fieldVatDocPreffix ="f" ;
             String[] arrayFieldVatDocAndValue1 = {"sxaccount.vatDocCode","sumpre"};
             String[] arrayFieldVatDocAndValue2 = {"sxaccount.vatDocCodeVat","sumvat"};        
        EntityCalculate entityCalculateVatDoc = new EntityCalculate("calculatevatdoc","υπολογ. περιοδικής ΦΠΑ","",null,eCalculateFilterVatDoc,entityGroupOfFilterCompsVatDoc,sqlQueryTableCalcVatDoc,false,null,fieldVatDocPreffix,arrayFieldVatDocAndValue1,arrayFieldVatDocAndValue2);
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] sxVatDocEntityGroupOfComps =new EntityGroupOfComps[21];
        EntityGroupOfPanels[] sxVatDocEntityGroupOfPanels = null;
        
        
        
        String sxVatDocQueryEditable = "SELECT * FROM sxvatdocforperiod"; //product.productId AS \"Νο προϊόντος\", product.productName AS \"ονομασία\", product.currencyId FROM product";
        String[] fieldsOnTitleSXVatDoc ={"vatDocForPeriodId","vatForPeriodStartDate","vatForPeriodEndDate"};
        String[] fieldsOnTitleCaptionSXVatDoc  ={"Νο περιοδικής ΦΠΑ","έναρξη περιόδου","λήξη περιόδου"};      
        String[] strSXVatDocCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueSXVatDoc = null;
        EntityCheckFields[] entityCheckFieldsSXVatDoc = null;
        EntityPanel entityPanelSXVatDocDataentry = new EntityPanel("ODOR","sxvatdocforperiod",sxVatDocDBFields,sxVatDocEntityGroupOfComps,sxVatDocEntityGroupOfPanels,"Νο περιοδικής ΦΠΑ","","vatdocforperiodId",sxVatDocQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSXVatDoc,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null, entityCalculateVatDoc,entityCheckFieldsSXVatDoc,null);      
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProducttraders = new EntityPanel("statProducttraders","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT sxtrader.traderId AS \"νο αγρότη\", sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, sxtrader","WHERE invoice.traderId = sxtrader.traderId AND invoice.productId=","GROUP BY sxtrader.traderId","ORDER BY sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelSXVatDoc = new EntityPanel[] { entityPanelSXVatDocDataentry};//,entityPanelProductStatistics,entityPanelProducttraders,entityPanelProductBuyers,entityPanelProductSalesPerDate};

 
        //----------------------------------------------------------------
        
        EntityDBFields[] sxIncomeDocDBFields = new EntityDBFields[8];
        
        
        EntityFilterSettings[] eCalculateFilterIncome = new EntityFilterSettings[2];       
        //eFilterSettings[0]=new EntityFilterSettings("ονομασία","","string","equals","vatDocDescr","sxvatdocforperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        EntityGroupOfComps[] entityGroupOfFilterCompsIncome = null; // if not null creates tabs, and nothing is shown
        String [] sqlQueryTableCalcIncome = new String[1];  
              String fieldIncomePreffix ="" ;
             String[] arrayFieldIncomeAndValue1 = null;
             String[] arrayFieldIncomeAndValue2 = null; 
        EntityCalculate entityCalculateIncomeDoc = new EntityCalculate("calculateincome","υπολογισμός","",null,eCalculateFilterIncome,entityGroupOfFilterCompsIncome,sqlQueryTableCalcIncome,false,null,fieldIncomePreffix,arrayFieldIncomeAndValue1,arrayFieldIncomeAndValue2);
        /*(String nameIn,String captionIn,String subTitleIn, String[] calculationTypeIn,
        EntityFilterSettings[] entityFilterSettingsIn,EntityGroupOfComps[] entityGroupOfCompsIn,
        EntityQuery[] entityQueryIn, boolean isNullifyIn, String yearEnforceIn)*/        
        
        
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] sxIncomeDocEntityGroupOfComps =new EntityGroupOfComps[3];
        EntityGroupOfPanels[] sxIncomeDocEntityGroupOfPanels = null;
        
       
        
        String sxIncomeDocQueryEditable = "SELECT * FROM sxincomedoc"; //product.productId AS \"Νο προϊόντος\", product.productName AS \"ονομασία\", product.currencyId FROM product";
        String[] fieldsOnTitleSXIncomeDoc ={"incomeDocId"};
        String[] fieldsOnTitleCaptionSXIncomeDoc  ={"Νο εισοδήματος"};      
        String[] strSXIncomeDocCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueSXIncomeDoc = null;
        EntityCheckFields[] entityCheckFieldsSXIncomeDoc = null;
        EntityPanel entityPanelSXIncomeDocDataentry = new EntityPanel("ODOR","sxincomedoc",sxIncomeDocDBFields,sxIncomeDocEntityGroupOfComps,sxIncomeDocEntityGroupOfPanels,"Νο εισοδήματος","","incomedocId",sxIncomeDocQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSXIncomeDoc,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,entityCalculateIncomeDoc,entityCheckFieldsSXIncomeDoc,null);
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProducttraders = new EntityPanel("statProducttraders","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT sxtrader.traderId AS \"νο αγρότη\", sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, sxtrader","WHERE invoice.traderId = sxtrader.traderId AND invoice.productId=","GROUP BY sxtrader.traderId","ORDER BY sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelSXIncomeDoc = new EntityPanel[] { entityPanelSXIncomeDocDataentry};//,entityPanelProductStatistics,entityPanelProducttraders,entityPanelProductBuyers,entityPanelProductSalesPerDate};
        
        //----------------------------------------------------------------
        
        EntityDBFields[] myfHeaderDBFields = new EntityDBFields[26];
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] myfHeaderEntityGroupOfComps =new EntityGroupOfComps[10];
        EntityGroupOfPanels[] myfHeaderEntityGroupOfPanels = new EntityGroupOfPanels[5];
        
        
        EntityFilterSettings[] eCalculateFilterMyf = new EntityFilterSettings[2];       
        //eFilterSettings[0]=new EntityFilterSettings("ονομασία","","string","equals","vatDocDescr","sxvatdocforperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        EntityGroupOfComps[] entityGroupOfFilterCompsMyf = new EntityGroupOfComps[1]; // if not null creates tabs, and nothing is shown
        String [] sqlQueryTableCalcMyf = new String[4]; 
              String fieldMyfPreffix ="" ;
             String[] arrayFieldMyfAndValue1 = null;
             String[] arrayFieldMyfAndValue2 = null;        
        EntityCalculate entityCalculateMyf = new EntityCalculate("calculatemyf","υπολογισμός ΜΥΦ","",null,eCalculateFilterMyf,entityGroupOfFilterCompsMyf,sqlQueryTableCalcMyf,false,null, fieldMyfPreffix,arrayFieldMyfAndValue1,arrayFieldMyfAndValue2);
        /*(String nameIn,String captionIn,String subTitleIn, String[] calculationTypeIn,
        EntityFilterSettings[] entityFilterSettingsIn,EntityGroupOfComps[] entityGroupOfCompsIn,
        EntityQuery[] entityQueryIn, boolean isNullifyIn, String yearEnforceIn)*/
    
        String myfHeaderQueryEditable = "SELECT * FROM myfheader"; //product.productId AS \"Νο προϊόντος\", product.productName AS \"ονομασία\", product.currencyId FROM product";
        String[] fieldsOnTitleMyfHeader ={"myfHeaderId","myfTitle"};
        String[] fieldsOnTitleCaptionMyfHeader  ={"Νο myf","περιγραφή"};      
        String[] strMyfHeaderCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueMyfHeader = null;
        EntityCheckFields[] entityCheckFieldsMyfHeader = null;
        EntityPanel entityPanelMyfHeaderDataentry = new EntityPanel("ODOR","myfheader",myfHeaderDBFields,myfHeaderEntityGroupOfComps,myfHeaderEntityGroupOfPanels,"Νο ΜΥΦ","","myfHeaderId",myfHeaderQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueMyfHeader,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null, entityCalculateMyf,entityCheckFieldsMyfHeader,null);      
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProducttraders = new EntityPanel("statProducttraders","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT sxtrader.traderId AS \"νο αγρότη\", sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, sxtrader","WHERE invoice.traderId = sxtrader.traderId AND invoice.productId=","GROUP BY sxtrader.traderId","ORDER BY sxtrader.surname, sxtrader.name, sxtrader.fatherName,sxtrader.traderAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelMyfHeader = new EntityPanel[] { entityPanelMyfHeaderDataentry};//,entityPanelProductStatistics,entityPanelProducttraders,entityPanelProductBuyers,entityPanelProductSalesPerDate};
        
        
        
        //---------------------------------------------------------------------

        //----------------------------------------------------------------
        //EntityDBFields[] actionTypeLineDBFields = new EntityDBFields[7];
        EntityDBFields[] printFormDBFields = new EntityDBFields[5];

        
        EntityGroupOfComps[] printFormEntityGroupOfComps = new EntityGroupOfComps[2];
        EntityGroupOfPanels[] printFormEntityGroupOfPanels = new EntityGroupOfPanels[2];
        
        
        String printFormQueryEditable="SELECT * FROM printform";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitlePrintForm ={"printFormId","printFormName"};
        String[] fieldsOnTitleCaptionPrintForm  ={"Νο","ονομασία"};     
            String[] fieldsUniquePrintForm = null;    
        EntityCheckFields[] entityCheckFieldsPrintForm = null;
        EntityPanel entityPanelPrintFormDataentry = new EntityPanel("ODOR","printform",printFormDBFields,printFormEntityGroupOfComps,printFormEntityGroupOfPanels,"Νο φόρμας","","printFormId",printFormQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniquePrintForm,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsPrintForm,null);    
        EntityPanel[] entityPanelPrintForm = new EntityPanel[] {entityPanelPrintFormDataentry};        
        
                
        
        
        
        
        
        //----------------------------------------------------------------
     /*   EntityDBFields[] activityCatDBFields = new EntityDBFields[2];

        
        EntityGroupOfComps[] activityCatEntityGroupOfComps =null;
        EntityGroupOfPanels[] activityCatEntityGroupOfPanels = null;
        
        
        String activityCatQueryEditable="SELECT * FROM activitycat";
        String[] fieldsOnTitleActivityCat ={"activityCatId","activityDescr"};
        String[] fieldsOnTitleCaptionActivityCat  ={"Νο","ονομασία"};   
        String[] fieldsUniqueActivityCat = null;      
        EntityCheckFields[] entityCheckFieldsActivityCat = null;
        EntityPanel entityPanelActivityCatDataentry = new EntityPanel("ODOR","activitycat",activityCatDBFields,activityCatEntityGroupOfComps,activityCatEntityGroupOfPanels,"Νο δραστηριότητας","","activityCatId",activityCatQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueActivityCat,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsActivityCat,null);    
        EntityPanel[] entityPanelActivityCat = new EntityPanel[] {entityPanelActivityCatDataentry};        
        */        
 

        //----------------------------------------------------------------
      /*  EntityDBFields[] vatCatLineDBFields = new EntityDBFields[5];
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
         */       
                
        //----------------------------------------------------------------

        // same as DialogLogin
        //----------------------------------------------------------------
        EntityDBFields[] dbCompanyDBFields = new EntityDBFields[24];        
        EntityGroupOfComps[] dbCompanyEntityGroupOfComps= new EntityGroupOfComps[7];


        EntityGroupOfPanels[] dbCompanyEntityGroupOfPanels = null;
        EntityUpdateAdditional[] updateAdditionalDbCompany = new EntityUpdateAdditional[1];
        
        String dbCompanyQueryEditable="SELECT * FROM dbcompany";//dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.title AS\"τίτλος\", dbcompany.companyVatNo AS\"Α.Φ.Μ.\", dbcompany.doyId ,dbcompany.geoCatId,  dbcompany.bankId , dbcompany.bankAccount , dbcompany.bankAccountIBAN , dbcompany.notes FROM dbcompany";
        String[] fieldsOnTitleDbCompany ={"dbCompanyId","title","companyVatNo"};
        String[] fieldsOnTitleCaptionDbCompany  ={"Νο","τίτλος","ΑΦΜ"};  
        String[] fieldsUniqueDbCompany = {"companyVatNo"};      
        EntityCheckFields[] entityCheckFieldsDBCompany = null;
        EntityPanel entityPanelDbCompanyDataentry = new EntityPanel("ODOR","dbcompany",dbCompanyDBFields,dbCompanyEntityGroupOfComps,dbCompanyEntityGroupOfPanels,"Νο εταιρίας","","dbCompanyId",dbCompanyQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbCompany,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,updateAdditionalDbCompany,null,null,entityCheckFieldsDBCompany,null);  
        EntityPanel[] entityPanelDbCompany = new EntityPanel[] {entityPanelDbCompanyDataentry};          
        
        //----------------------------------------------------------------
        EntityDBFields[] dbuserDBFields = new EntityDBFields[4];

        EntityGroupOfComps dbuserEntityGroupOfComps[] =null;
        EntityGroupOfPanels dbuserEntityGroupOfPanels[] = null;
        
        
        String dbUserQueryEditable="SELECT userId AS\"Νο χρήστη\", username AS\"όνομα χρήστη\", password, nameOfUser AS\"πλήρες όνομα χρήστη\" FROM dbuser";
        String[] fieldsOnTitleDbuser ={"userId","userName","nameOfUser"};
        String[] fieldsOnTitleCaptionDbuser  ={"Νο","user","πλήρες όνομα"};    
        String[] fieldsUniqueDbUser = {"userName"}; 
        EntityCheckFields[] entityCheckFieldsDBUser = null;        
        EntityPanel entityPanelDbuserDataentry = new EntityPanel("ODOR","dbuser",dbuserDBFields,dbuserEntityGroupOfComps,dbuserEntityGroupOfPanels,"Νο χρήστη","","userId",dbUserQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbUser,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsDBUser,null);  
        EntityPanel[] entityPanelDbuser = new EntityPanel[] { entityPanelDbuserDataentry}; 

        //----------------------------------------------------------------
      
       /* EntityGroupOfComps[] bankEntityGroupOfComps = new EntityGroupOfComps[1];
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
        */

        //----------------------------------prefs-----------------------------------------
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
                
        
        //-----------------------------
       
        //- report initialisations
EntityFilterSettings[] salesDocumentErs = new EntityFilterSettings[6];        
EntityGroupOfComps[] saleDocumentGroupOfComps = new EntityGroupOfComps[3];
       int[] invoicesaSelected = null;//{1,2,3,4,0,0,0,0,0,0,11,12,0,14,};
       int[] fileOrderby ={1};
       boolean[] boolSettingsReportDoc = {true,true,true,true,true}; 
      boolean[] boolSettingsReporttraderfileA = {true,true,true,true,true}; 
      boolean[] boolSettingsReporttraderfileB = {true,true,true,true,true}; 
       int[] intSettingsReporttraderfile={0,0,0,0};
 //       EntityReportBandField[] entityReportBandFieldsesoexolineA =new EntityReportBandField[10];          
 //       EntityReportBandField[] entityReportBandFieldsesoexoheaderB =new EntityReportBandField[7];
       EntityReportBand[] reportBandtraderEsExDoc = new EntityReportBand[2];  //not in use
          

   public EntityDataEsoExo()
   {
       
          // need them
   	  dTree = new DataTree();
   	  nodeRoot = new DataTreeNode("root");
   	  dTree.setRootElement(nodeRoot);

          // deliveryQueryEditable = "SELECT d.traderId, d.dateOfApplication, d.dbyear, d.deliveryId, d.dbCompanyId FROM application d WHERE dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+" AND dbCompanyId="+VariablesGlobal.globalCompanyId;

       //System.out.println("EntityData"+entityPaneltrader[0].getType());

       
    eCalculateFilterMyf[0]=new EntityFilterSettings("ημερομηνία παραστατικών","","date","fromto","dateOfesoexo","","sxesoexoheader","",0,-1,-1,FIELD_OBLIGATORY);
    eCalculateFilterMyf[1]=new EntityFilterSettings( "τύποι ΜΥΦ","checkboxTable","string","","lookupconstantsId","sxmyftype","lookupconstants","",0,-1,-1,FIELD_NOCOMPLETION);
         
    entityGroupOfFilterCompsMyf[0] = new EntityGroupOfComps("φίλτρα εγγραφών εσόδων εξόδων",2,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
    
         sqlQueryTableCalcMyf[0] = "SELECT sxtrader.vatNo AS 'ΑΦΜ',  sxtrader.title AS 'συναλλασσόμενος', sxactiontype.isCredit AS 'isNormalOrCredit', count(priceBeforeVat) AS 'πλήθος', sum(priceBeforeVat) AS 'προ ΦΠΑ', sum(vatValue) AS 'ΦΠΑ' FROM sxesoexoline "+
"INNER JOIN sxesoexoheader ON sxesoexoline.esoexoHeaderId = sxesoexoheader.esoexoHeaderId "+
"INNER JOIN sxactiontype ON sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId "+
"INNER JOIN sxaccount ON sxaccount.accountId = sxesoexoline.accountId "+
"INNER JOIN sxtrader ON sxtrader.traderId = sxesoexoheader.traderId ,"+
"lookupconstants "+
"WHERE sxesoexoline.dbCompanyId = sxesoexoheader.dbCompanyId AND sxactiontype.dbCompanyId = sxesoexoheader.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" "+
"AND lookupconstants.lookupconstantsId = sxactiontype.myfCatId AND lookupconstants.constantstypeId = 3 "+
"AND sxesoexoheader.dbYearId = sxesoexoline.dbYearId AND sxesoexoheader.dbYearId LIKE "+VariablesGlobal.globalYearId+" "+
"AND sxesoexoline.isTemplate = sxesoexoheader.isTemplate AND sxesoexoheader.isTemplate ='0' "+
"AND sxaccount.participatesInMYF LIKE 1 "+
"AND sxactiontype.myfCatId LIKE 1 "+  //tableCount
"GROUP BY sxtrader.traderId, sxactiontype.isCredit";

             sqlQueryTableCalcMyf[1] = "SELECT sxtrader.vatNo AS 'ΑΦΜ',  sxtrader.title AS 'συναλλασσόμενος', count(priceBeforeVat) AS 'πλήθος', sum(priceBeforeVat) AS 'προ ΦΠΑ', sum(vatValue) AS 'ΦΠΑ' FROM sxesoexoline "+
"INNER JOIN sxesoexoheader ON sxesoexoline.esoexoHeaderId = sxesoexoheader.esoexoHeaderId "+
"INNER JOIN sxactiontype ON sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId "+
"INNER JOIN sxaccount ON sxaccount.accountId = sxesoexoline.accountId "+
"INNER JOIN sxtrader ON sxtrader.traderId = sxesoexoheader.traderId ,"+
"lookupconstants "+
"WHERE sxesoexoline.dbCompanyId = sxesoexoheader.dbCompanyId AND sxactiontype.dbCompanyId = sxesoexoheader.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" "+
"AND lookupconstants.lookupconstantsId = sxactiontype.myfCatId AND lookupconstants.constantstypeId = 3 "+
"AND sxesoexoheader.dbYearId = sxesoexoline.dbYearId AND sxesoexoheader.dbYearId LIKE "+VariablesGlobal.globalYearId+" "+
"AND sxesoexoline.isTemplate = sxesoexoheader.isTemplate AND sxesoexoheader.isTemplate ='0' "+
"AND sxaccount.participatesInMYF LIKE 1 "+
"AND sxactiontype.myfCatId LIKE 2 "+  //tableCount
"GROUP BY sxtrader.traderId";
 
               sqlQueryTableCalcMyf[2] = "SELECT sxtrader.vatNo AS 'ΑΦΜ',  sxtrader.title AS 'συναλλασσόμενος', sxactiontype.isCredit AS 'isNormalOrCredit', count(priceBeforeVat) AS 'πλήθος', sum(priceBeforeVat) AS 'προ ΦΠΑ', sum(vatValue) AS 'ΦΠΑ' FROM sxesoexoline "+
"INNER JOIN sxesoexoheader ON sxesoexoline.esoexoHeaderId = sxesoexoheader.esoexoHeaderId "+
"INNER JOIN sxactiontype ON sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId "+
"INNER JOIN sxaccount ON sxaccount.accountId = sxesoexoline.accountId "+
"INNER JOIN sxtrader ON sxtrader.traderId = sxesoexoheader.traderId ,"+
"lookupconstants "+
"WHERE sxesoexoline.dbCompanyId = sxesoexoheader.dbCompanyId AND sxactiontype.dbCompanyId = sxesoexoheader.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" "+
"AND lookupconstants.lookupconstantsId = sxactiontype.myfCatId AND lookupconstants.constantstypeId = 3 "+
"AND sxesoexoheader.dbYearId = sxesoexoline.dbYearId AND sxesoexoheader.dbYearId LIKE "+VariablesGlobal.globalYearId+" "+
"AND sxesoexoline.isTemplate = sxesoexoheader.isTemplate AND sxesoexoheader.isTemplate ='0' "+
"AND sxaccount.participatesInMYF LIKE 1 "+
"AND sxactiontype.myfCatId LIKE 3 "+  //tableCount
"GROUP BY sxtrader.traderId, sxactiontype.isCredit";

             sqlQueryTableCalcMyf[3] = "SELECT sxtrader.vatNo AS 'ΑΦΜ',  sxtrader.title AS 'συναλλασσόμενος', count(priceBeforeVat) AS 'πλήθος', sum(priceBeforeVat) AS 'προ ΦΠΑ', sum(vatValue) AS 'ΦΠΑ' FROM sxesoexoline "+
"INNER JOIN sxesoexoheader ON sxesoexoline.esoexoHeaderId = sxesoexoheader.esoexoHeaderId "+
"INNER JOIN sxactiontype ON sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId "+
"INNER JOIN sxaccount ON sxaccount.accountId = sxesoexoline.accountId "+
"INNER JOIN sxtrader ON sxtrader.traderId = sxesoexoheader.traderId ,"+
"lookupconstants "+
"WHERE sxesoexoline.dbCompanyId = sxesoexoheader.dbCompanyId AND sxactiontype.dbCompanyId = sxesoexoheader.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" "+
"AND lookupconstants.lookupconstantsId = sxactiontype.myfCatId AND lookupconstants.constantstypeId = 3 "+
"AND sxesoexoheader.dbYearId = sxesoexoline.dbYearId AND sxesoexoheader.dbYearId LIKE "+VariablesGlobal.globalYearId+" "+
"AND sxesoexoline.isTemplate = sxesoexoheader.isTemplate AND sxesoexoheader.isTemplate ='0' "+
"AND sxaccount.participatesInMYF LIKE 1 "+
"AND sxactiontype.myfCatId LIKE 4 "+  //tableCount
"GROUP BY sxtrader.traderId";   
             
//---------------------------------------------------------------             
        
eCalculateFilterIncome[0]=new EntityFilterSettings("ημερομηνία παραστατικών","","date","fromto","dateOfesoexo","","sxesoexoheader","",-1,-1,-1,FIELD_OBLIGATORY);
eCalculateFilterIncome[1]=new EntityFilterSettings( "τύποι παραστατικού","checkboxTable","string","","myfCatId","sxactiontype","sxactiontype","",-1,-1,-1,FIELD_NOCOMPLETION);

sqlQueryTableCalcIncome[0] = "SELECT sxtrader.traderId AS 'συναλλασσόμενος', sxincomedocsettings.typeId, sxincomedocsettings.codeId, count(valueWithVat) AS 'πλήθος παρ/κων', sum(valueWithVat) AS 'incomeGross', sum(priceBeforeVat) AS 'incomeNet' " +
"FROM sxesoexoline, sxesoexoheader, sxactiontype, sxaccount, sxtrader, sxincomedocsettings " +
"WHERE sxesoexoline.esoexoHeaderId = sxesoexoheader.esoexoHeaderId " +
"AND sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId " +
"AND sxaccount.accountId = sxesoexoline.accountId " +
"AND sxtrader.traderId = sxesoexoheader.traderId " +
"AND sxactiontype.dbCompanyId = sxesoexoheader.dbCompanyId "+
"AND sxesoexoline.dbCompanyId = sxesoexoheader.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE " +VariablesGlobal.globalCompanyId+" "+
"AND sxesoexoheader.dbYearId = sxesoexoline.dbYearId AND sxesoexoheader.dbYearId LIKE " +VariablesGlobal.globalYearId+" "+
"AND sxesoexoline.isTemplate = sxesoexoheader.isTemplate AND sxesoexoheader.isTemplate ='0' "+
"AND sxincomedocsettings.accountId = sxesoexoline.accountId " +
"AND sxincomedocsettings.accountId = sxaccount.accountId " +
"GROUP BY sxtrader.traderId";  


//-----------------------------------------------------------calc vat doc
        eCalculateFilterVatDoc[0] = new EntityFilterSettings("ημερομηνία παραστατικών","","date","fromto","dateOfesoexo","","sxesoexoheader","",0,-1,-1,FIELD_OBLIGATORY);     
        eCalculateFilterVatDoc[1]=new EntityFilterSettings( "τύποι παραστατικού","checkboxTable","string","","sxactionTypeId","sxactiontype","sxesoexoheader","",0,-1,-1,FIELD_NOCOMPLETION);
        //eFilterSettings[0]=new EntityFilterSettings("ονομασία","","string","equals","vatDocDescr","sxvatdocforperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //EntityGroupOfComps[] entityGroupOfFilterCompsVatDoc = new EntityGroupOfComps[1]; // if not null creates tabs, and nothing is shown
                entityGroupOfFilterCompsVatDoc[0] = new EntityGroupOfComps("φίλτρα εγγραφών εσόδων εξόδων",2,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        sqlQueryTableCalcVatDoc[0] =  "SELECT sxaccount.vatDocCode, sxaccount.vatDocCodeVat, sxesoexoheader.dateOfesoexo, COUNT(sxesoexoLine.priceBeforeVat) AS cnt, SUM(sxesoexoLine.priceBeforeVat) AS sumpre,  SUM(sxesoexoLine.vatValue) AS sumvat, "
                        + "SUM(sxesoexoLine.valueWithVat) AS sumtotal "
                        + "FROM sxaccount,sxesoexoheader, sxesoexoline "
                        + "WHERE sxesoexoLine.accountId = sxaccount.accountId AND sxesoexoheader.esoexoheaderId = sxesoexoLine.esoexoheaderId"
                        + "  AND sxesoexoLine.dbCompanyId = sxesoexoheader.dbCompanyId AND sxesoexoLine.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" "
                        + "GROUP BY sxaccount.vatDocCode";
             

        
        
        
//-----------------------------------------------------------calc vat doc
       /*
    FIELD_NOCOMPLETION = 0;
    FIELD_OBLIGATORY = 1;
    FIELD_SUGGEST = 2;
    
    FIELD_VALIDATION_NO = 0  FIELD_VALIDATION_AFM = 1
*/

          //reports
       
      /*  entityReportBandFieldsesoexolineA[0] = new EntityReportBandField("esoexoline","esoexoheaderId","esoexoheaderId","java.lang.String",10,true,null,null);
        entityReportBandFieldsesoexolineA[1] = new EntityReportBandField("esoexoline","accountId","accountId","java.lang.String",10,true,null,null);        
        entityReportBandFieldsesoexolineA[2] = new EntityReportBandField("sxaccount","accountDescr","accountDescr","java.lang.String",35,true,null,null);        
        entityReportBandFieldsesoexolineA[3] = new EntityReportBandField("esoexoline","description","περιγραφή","java.lang.String",25,true,null,null);  
        entityReportBandFieldsesoexolineA[4] = new EntityReportBandField("esoexoline","quantity","quantity","java.lang.String",12,true,null,null);                    
        entityReportBandFieldsesoexolineA[5] = new EntityReportBandField("esoexoline","priceBeforeVat","priceBeforeVat","java.lang.Double",15,true,null,null);
        entityReportBandFieldsesoexolineA[6] = new EntityReportBandField("esoexoline","vatCatId","vatCatId","java.lang.String",15,true,null,null);
        entityReportBandFieldsesoexolineA[7] = new EntityReportBandField("esoexoline","vatValue","vatValue","java.lang.Double",15,true,null,null);
        entityReportBandFieldsesoexolineA[8] = new EntityReportBandField("esoexoline","valueWithVat","valueWithVat","java.lang.Double",15,true,null,null);
        entityReportBandFieldsesoexolineA[9] = new EntityReportBandField("esoexoline","traderId","Νο πελάτη","java.lang.String",7,true,null,null);                    
                    
                    

       
        entityReportBandFieldsesoexoheaderB[0] = new EntityReportBandField("sxesoexoheader","esoexoCodeOfDocument","κωδ παραστατικού","java.lang.String",15,true,null,null);
        entityReportBandFieldsesoexoheaderB[1] = new EntityReportBandField("sxesoexoheader","dateOfEsoexo","ημερομηνία","java.lang.Date",18,true,null,null);        
        entityReportBandFieldsesoexoheaderB[2] = new EntityReportBandField("sxesoexoheader","priceTotal","τελικό ποσό","java.lang.Double",20,true,null,null);  
        entityReportBandFieldsesoexoheaderB[3] = new EntityReportBandField("sxesoexoheader","traderId","Νο πελάτη","java.lang.String",7,true,null,null);
        entityReportBandFieldsesoexoheaderB[4] = new EntityReportBandField("sxesoexoheader","esoexoheaderId","esoexoheaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheaderB[5] = new EntityReportBandField("sxesoexoheader","dbCompanyId","dbCompanyId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheaderB[6] = new EntityReportBandField("sxesoexoheader","dbYearId","dbYearId","java.lang.String",9,true,null,null);            
       */ 
      
      int[] hOrderby = null;
      int[] lOrderby = null;
      
       reportBandtraderEsExDoc[0] = new EntityReportBand("sxesoexoheader","πώληση","sxesoexoheader",null,hOrderby/*entityReportBandFieldsesoexoheaderB*/,"esoexoheaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReporttraderfileA,entityPanelEsex,null,null);//,"","");
       reportBandtraderEsExDoc[1] = new EntityReportBand("sxesoexoline","υπηρεσίες","sxesoexoline",null,lOrderby/*entityReportBandFieldsesoexolineA*/,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReporttraderfileB,entityPanelEsex,null,null);//,"","");                  
          
          
        entReportEsExDoc = new EntityReport("esexdoc", REPORT_CAT_2,reportBandtraderEsExDoc,"SELECT * FROM sxactiontype, sxtrader, sxesoexoheader, sxesoexoline, sxaccount WHERE sxesoexoheader.traderId = sxtrader.traderId AND sxesoexoheader.esoexoheaderId = sxesoexoline.esoexoheaderId AND sxesoexoline.accountId = sxaccount.accountId AND sxactiontype.sxActiontypeId = sxesoexoheader.sxActionTypeId AND sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ",""/*ORDER BY sxesoexoline.inc*/,"FORM","εντυπα πωλήσεων","",salesDocumentErs,saleDocumentGroupOfComps,invoicesaSelected, null,
                "SELECT esoexoheaderId, sxesoexoheader.sxActionTypeId,sxactiontype.actiontypeCode, printFormName, printFormLaser, printFormDotMatrix " +
                "  FROM sxactiontype, printform, sxesoexoheader" +
                " WHERE  sxactiontype.printFormId = printform.printFormId AND sxactiontype.sxActiontypeId = sxesoexoheader.sxActiontypeId AND sxactiontype.dbCompanyId = printform.dbCompanyId AND sxesoexoheader.dbCompanyId = printform.dbCompanyId AND sxactiontype.dbCompanyId = printform.dbCompanyId AND sxactiontype.active = 1 AND printform.isActive = 1 AND printform.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ","printform.printFormLaser","Θα πρέπει να επιλέξετε φόρμα στο αντίστοιχο παραστατικό.",intSettingsReporttraderfile,boolSettingsReportDoc,"");//,globalYearPlusOne) ;

     
     
      int[] inputFieldsTraderInHeader ={7}; // 7 is traderId
      int[] inputFieldsOfNetValue ={11}; // 15 is esoexo pricePreVat
      EntityCheckFields[] entityCheckFieldsVATCompanyOfEsoexo =new EntityCheckFields[2];
             entityCheckFieldsVATCompanyOfEsoexo[0] = new EntityCheckFields(CHECK_ON_ENTRY,"SELECT IF((SELECT sxtrader.vatNo FROM sxtrader WHERE sxtrader.traderId = #) = "
            + "(SELECT dbcompany.companyVatNo FROM dbcompany, dbcompanyset WHERE dbcompany.dbcompanyId = dbcompanyset.dbcompanyId AND dbcompanyset.esoexoCheckAFMOfEsoExoAndComp = 1 "
            + "AND dbcompany.dbCompanyId = "+VariablesGlobal.globalCompanyId+"),1,0)",inputFieldsTraderInHeader,7,"Το ΑΦΜ του συναλλασσόμενου είναι ίδιο με αυτό της επιχείρησης στην οποία εργάζεστε.");
             entityCheckFieldsVATCompanyOfEsoexo[1] = new EntityCheckFields(CHECK_ON_INSERT_OR_ON_UPDATE,"SELECT IF( # >= (SELECT dbcompanyset.esoexoMaxOfCashNetValue FROM dbcompanyset " +
              "WHERE dbcompanyset.dbCompanyId = "+VariablesGlobal.globalCompanyId+"  AND dbcompanyset.esoexoMaxOfCashCheck = 1),1,0)",inputFieldsOfNetValue,0, "Το παραστατικό θα πρέπει να εξοφλήθεί με τραπεζικό τρόπο.");
                                   
 
    /*        String queryTemplates = "SELECT * FROM sxesoexoheader WHERE  sxesoexoheader.dbCompanyId = "+VariablesGlobal.globalCompanyId+"  AND  sxesoexoheader.isTemplate = 1 ORDER BY sxesoexoheader.titleOfTemplate";//esoexoheaderId";//sxesoexoheader.titleOfTemplate";
      final String subqueryIsNotTemplate = "AND sxesoexoline.isTemplate ='0'";
      final String subqueryIsTemplate = "AND sxesoexoline.isTemplate LIKE '1'";
      String fieldHeaderId = "sxesoexoheader.esoexoheaderId";
      String strNameOfFieldOfTemplateMenu = "sxesoexoheader.titleOfTemplate";
    String setFieldIsTemplate = "isTemplate";  */           
    String queryTemplates = "SELECT * FROM sxesoexoheader WHERE  sxesoexoheader.dbCompanyId = "+VariablesGlobal.globalCompanyId+"  AND  sxesoexoheader.isTemplate = 1 AND isTemplateActive = 1 ORDER BY sxesoexoheader.titleOfTemplate";//esoexoheaderId";//sxesoexoheader.titleOfTemplate";
    String subqueryIsNotTemplate = "AND sxesoexoline.isTemplate LIKE '0'";// to show replase it
    String subqueryIsTemplate = "AND sxesoexoline.isTemplate LIKE '1'";// to save replace it
      String fieldHeaderId = "sxesoexoheader.esoexoheaderId";
      String strNameOfFieldOfTemplateMenu = "sxesoexoheader.titleOfTemplate";
    String setFieldIsTemplate = "\\bisTemplate\\b";     
    EntityTemplate entityTemplateEsoExoHeader = new EntityTemplate(queryTemplates,subqueryIsTemplate,subqueryIsNotTemplate,fieldHeaderId,strNameOfFieldOfTemplateMenu,setFieldIsTemplate);
            
            //panels
        entityPanelEsexDataentry = new EntityPanel("ODOR","sxesoexoheader",esoexoHeaderDBFields,esoexoEntityGroupOfComps,esoexoEntityGroupOfPanels,"Νο εσόδων εξόδων","","esoexoheaderId",saleQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSale,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,/*updateAdditionalActionType*/null,entReportEsExDoc,null,entityCheckFieldsVATCompanyOfEsoexo,entityTemplateEsoExoHeader);             
        entityPanelEsex = new EntityPanel[] { entityPanelEsexDataentry};
            
        
        EntityCheckFields[] entityCheckFieldsTrader = null;
        entityPaneltraderDataentry = new EntityPanel("ODOR","sxtrader",traderDBFields,traderEntityGroupOfComps,traderEntityGroupOfPanels,"Νο πελάτη","","traderId",traderQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniquetrader,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null/*hr*/,null,entityCheckFieldsTrader,null);  
        entityPaneltraderHistory = new EntityPanel("stattraderHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT sxesoexoheader.traderId, sxesoexoheader.esoexoheaderId, sxactiontype.actionTypeCode, sxesoexoheader.esoexoCodeOfDocument,sxesoexoheader.dbYearId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.isPrinted, sxesoexoheader.countTotal, sxesoexoheader.pricePreVat, sxesoexoheader.priceVat, sxesoexoheader.priceTotal, sxesoexoheader.isTemplate","FROM sxesoexoheader LEFT JOIN sxactiontype ON sxesoexoheader.sxActionTypeId = sxactiontype.sxActionTypeId","WHERE sxesoexoheader.isTemplate ='0'  AND sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoheader.traderId =","","ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoCodeOfDocument",false,"",false,"",entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);     
        //EntityPanel entityPaneltraderProducts = new EntityPanel("stattraderProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.traderId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderBuyers = new EntityPanel("stattraderBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.traderId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderSalesPerDate = new EntityPanel("stattraderSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.traderId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        entityPaneltrader = new EntityPanel[] { entityPaneltraderDataentry,entityPaneltraderHistory};//,entityPaneltraderStatistics,entityPaneltraderProducts,entityPaneltraderBuyers,entityPaneltraderSalesPerDate};
            
            
    
        
        
        EntityDBFields[] traderBankaccountDBFields = new EntityDBFields[9];
        
        traderBankaccountDBFields[0] = new EntityDBFields("sxtraderbankaccount","traderBankAccountId","traderBankAccountId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        traderBankaccountDBFields[1] = new EntityDBFields("sxtraderbankaccount","traderId","Νο πελάτη",0,"java.lang.String",6, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       //traderBankaccountDBFields[2] = new EntityDBFields("sxtraderbankaccount","dbCompanyId","dbCompanyId",0,"java.lang.String",6,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        traderBankaccountDBFields[2] = new EntityDBFields("sxtraderbankaccount","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
        traderBankaccountDBFields[3] = new EntityDBFields("sxtraderbankaccount","bankId","τράπεζα",2,"java.lang.Integer",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
	traderBankaccountDBFields[4] = new EntityDBFields("sxtraderbankaccount","account","λογαριασμός",2,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	traderBankaccountDBFields[5] = new EntityDBFields("sxtraderbankaccount","iban","ΙΒΑΝ",2,"java.lang.String",29,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderBankaccountDBFields[6] = new EntityDBFields("sxtraderbankaccount","firstName","1ο ονοματεπώνυμο",0,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderBankaccountDBFields[7] = new EntityDBFields("sxtraderbankaccount","branch","κατάστημα",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderBankaccountDBFields[8] = new EntityDBFields("sxtraderbankaccount","bic","BIC",0,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
                
        
          
          

        EntityDBFields[] traderContactDBFields = new EntityDBFields[7];
        
        traderContactDBFields[0] = new EntityDBFields("sxtradercontact","traderContactId","traderContactId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        traderContactDBFields[1] = new EntityDBFields("sxtradercontact","traderId","Νο πελάτη",0,"java.lang.String",6, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        traderContactDBFields[2] = new EntityDBFields("sxtradercontact","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //traderContactDBFields[3] = new EntityDBFields("sxtradercontact","dbCompanyId","dbCompanyId",0,"java.lang.String",6,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        
        
        traderContactDBFields[3] = new EntityDBFields("sxtradercontact","location","τοποθεσία ή τμήμα",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderContactDBFields[4] = new EntityDBFields("sxtradercontact","person","πρόσωπο",0,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderContactDBFields[5] = new EntityDBFields("sxtradercontact","phone","τηλέφωνο",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderContactDBFields[6] = new EntityDBFields("sxtradercontact","email","email",0,"java.lang.String",22,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
          
        traderDBFields[0] = new EntityDBFields("sxtrader","traderId","Νο πελάτη",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //traderDBFields[1] = new EntityDBFields("sxtrader","surname","επίθετο",0,"java.lang.String",20, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,true,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,  false,null);
        //traderDBFields[1] = new EntityDBFields("sxtrader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        traderDBFields[1] = new EntityDBFields("sxtrader","title","επωνυμία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[2] = new EntityDBFields("sxtrader","traderCode","κωδικός",0,"java.lang.String",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[3] = new EntityDBFields("sxtrader","vatNo","Α.Φ.Μ.",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_AFM,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[4] = new EntityDBFields("sxtrader","activityDescr","δραστηριότητα",0,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[5] = new EntityDBFields("sxtrader","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                    
        traderDBFields[6] = new EntityDBFields("sxtrader","activityCatId","κατηγορία δραστηριότητας",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"activityCat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[7] = new EntityDBFields("sxtrader","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[8] = new EntityDBFields("sxtrader","geoCatId","γεωγραφική κατηγορία",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"geocat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        
        traderDBFields[9] = new EntityDBFields("sxtrader","addressStreet","οδός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[10] = new EntityDBFields("sxtrader","addressCity","πόλη/χωριό",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[11] = new EntityDBFields("sxtrader","addressPC","ΤΚ",1,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[12] = new EntityDBFields("sxtrader","addressState","νομός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
                
        traderDBFields[13] = new EntityDBFields("sxtrader","telephone","τηλέφωνο",2,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[14] = new EntityDBFields("sxtrader","fax","fax",2,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[15] = new EntityDBFields("sxtrader","email","email",2,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        traderDBFields[16] = new EntityDBFields("sxtrader","currencyId","νόμισμα",3,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"currency",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[17] = new EntityDBFields("sxtrader","typeofVatExclusionId","καθεστώς ΦΠΑ",3,"java.lang.Integer",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCVatExclusion",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");
        traderDBFields[18] = new EntityDBFields("sxtrader","discountPercentage","έκπτωση %",3,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");     
        traderDBFields[19] = new EntityDBFields("sxtrader","paymenttypeId","τρόπος πληρωμής",3,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"paymenttype",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

        traderDBFields[20] = new EntityDBFields("sxtrader","notes","σημειώσεις",4,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");


        traderDBFields[21] = new EntityDBFields("sxtrader","contacts","επαφές",5,"table",FIELD_VISIBLE_AND_EDITABLE,"sxtradercontact",160,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,traderContactDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM sxtradercontact ORDER BY sxtradercontact.inc",null,null);        
        
        traderDBFields[22] = new EntityDBFields("sxtrader","bank accounts","λογαριασμοί τραπεζών",6,"table",FIELD_VISIBLE_AND_EDITABLE,"sxtraderbankaccount",120,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,traderBankaccountDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM sxtraderbankaccount ORDER BY sxtraderbankaccount.inc",null,null);        
        
        
        
        
        traderEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        traderEntityGroupOfComps[1] = new EntityGroupOfComps("διεύθυνση",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        traderEntityGroupOfComps[2] = new EntityGroupOfComps("επικοινωνία",4,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        traderEntityGroupOfComps[3] = new EntityGroupOfComps("χρηματικά",4,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        traderEntityGroupOfComps[4] = new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        traderEntityGroupOfComps[5] = new EntityGroupOfComps("επαφές",1,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE); // if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
        traderEntityGroupOfComps[6] = new EntityGroupOfComps("λογαριασμοί τραπεζών",1,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
        
        
        
        
        
        traderEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        traderEntityGroupOfPanels[1] = new EntityGroupOfPanels("επικοινωνία",1);
        traderEntityGroupOfPanels[2] = new EntityGroupOfPanels("χρηματικά",1);
        //traderEntityGroupOfPanels[3] = new EntityGroupOfPanels("σημειώσεις",1);

        EntityDBFields[] esoexolineDBFields = new EntityDBFields[12];        
        esoexolineDBFields[0] = new EntityDBFields("sxesoexoline","esoexolineId","esoexolineId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        esoexolineDBFields[1] = new EntityDBFields("sxesoexoline","esoexoheaderId","Νο εσόδων εξόδων",0,"java.lang.String",5, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        esoexolineDBFields[2] = new EntityDBFields("sxesoexoline","dbCompanyId","dbCompanyId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        esoexolineDBFields[3] = new EntityDBFields("sxesoexoline","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
      
        int[] inputAccDescription ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputDescr ={4};//field        
        int[] inputVatCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVat ={4};//field        
        //10,inputService,"SELECT vatcat.vatPercentage FROM sxaccount, vatcat  WHERE sxaccount.vatCatId=vatcat.vatCatId AND sxaccount.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxaccount.accountId=");
        String calculationVatPercentageSql = "SELECT vatcat.vatPercentage FROM sxaccount, vatcat  WHERE sxaccount.vatCatId=vatcat.vatCatId AND sxaccount.accountId LIKE #";
        String calculationAccDescrSql = "SELECT sxaccount.accountDescr FROM sxaccount  WHERE sxaccount.accountId LIKE #";
        EntityDBFieldsCalculation[] fieldsCalculationServiceSelect = new EntityDBFieldsCalculation[2];
        
        //fieldsCalculationService[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputServiceCategory,inputService,"SELECT sxaccount.priceWhole FROM sxaccount, vatcat  WHERE sxaccount.vatCatId=vatcat.vatCatId AND sxaccount.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxaccount.accountId LIKE #");
        fieldsCalculationServiceSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,5,inputAccDescription,inputDescr,calculationAccDescrSql);
        fieldsCalculationServiceSelect[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,6,inputVatCategory,inputVat,calculationVatPercentageSql);
        
        esoexolineDBFields[4] = new EntityDBFields("sxesoexoline","accountId","λογαριασμός",2,"java.lang.Integer",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"sxaccount",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationServiceSelect,"sxActionTypeId");
	esoexolineDBFields[5] = new EntityDBFields("sxesoexoline","description","περ/φή λογαριασμού",2,"java.lang.String",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");

        
        
        esoexolineDBFields[6] = new EntityDBFields("sxesoexoline","vatPercentage","ΦΠΑ %",1,"java.lang.Double",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,"");//fieldsCalculationService);//fieldsCalculationVatPercentValue);        
        
        

        int[] inputVatValueCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVatValue ={7,6};
       EntityDBFieldsCalculation[] fieldsCalculationVatValueSelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationVatValueSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,8,inputVatValueCategory,inputVatValue,"SELECT #*#/100");//SELECT #+#"); 

       esoexolineDBFields[7] = new EntityDBFields("sxesoexoline","priceBeforeVat","αξία",1,"java.lang.Double",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationVatValueSelect,"");//fieldsCalculationPriceBeforeVat);

        

          int[] inputValueWithVatCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputValueWithVat ={7,8};
       EntityDBFieldsCalculation[] fieldsCalculationValueWithVatSelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationValueWithVatSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,9,inputValueWithVatCategory,inputValueWithVat,"SELECT #+#");        
        
        esoexolineDBFields[8] = new EntityDBFields("sxesoexoline","vatValue","ΦΠΑ",1,"java.lang.Double",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationValueWithVatSelect,"");
        
       
        
        int[] inputVatPercentCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVatPercentValue ={9,6};
       EntityDBFieldsCalculation[] fieldsCalculationNetValueFromTotalSelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationNetValueFromTotalSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputVatPercentCategory,inputVatPercentValue,"SELECT #/(100+#)*100");         
        esoexolineDBFields[9] = new EntityDBFields("sxesoexoline","valueWithVat","σύνολο",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationNetValueFromTotalSelect,"");                     
        esoexolineDBFields[10] = new EntityDBFields("sxesoexoline","dbYearId","dbYearId",1,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        esoexolineDBFields[11] = new EntityDBFields("sxesoexoline","isTemplate","isTemplate",1,"java.lang.String",3,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"0","");
        
        // exist also in sersales domain as a bridge: bridgeEsoexoHeaderDBFields
         esoexoHeaderDBFields[0] = new EntityDBFields("sxesoexoheader","isTemplate","isTemplate",0,"java.lang.String",3,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"0","");
    //    esoexoHeaderDBFields[17] = new EntityDBFields("sxesoexoheader","companyCurrencyId","companyCurrencyId",4,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);
   //     esoexoHeaderDBFields[18] = new EntityDBFields("sxesoexoheader","companyCurrencyDescr","νόμισμα εταιρίας",4,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);        
        esoexoHeaderDBFields[1] = new EntityDBFields("sxesoexoheader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        esoexoHeaderDBFields[2] = new EntityDBFields("sxesoexoheader","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        
        esoexoHeaderDBFields[3] = new EntityDBFields("sxesoexoheader","esoexoheaderId","Νο εσόδων εξόδων",1,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
        esoexoHeaderDBFields[4] = new EntityDBFields("sxesoexoheader","sxActionTypeId","τύπος παρ/κού",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"sxactiontype",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,null,"");// variable before last: 'false' means update
        //esoexoHeaderDBFields[4] = new EntityDBFields("sxesoexoheader","esoexoCodeNo","CodeNo",0,"java.lang.Integer",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);
        esoexoHeaderDBFields[5] = new EntityDBFields("sxesoexoheader","esoexoCodeOfDocument","κωδικός παρ/κού",1,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//,entityFieldUpdateAdditionalCodeOfDocument);
        esoexoHeaderDBFields[6] = new EntityDBFields("sxesoexoheader","dateOfEsoexo","ημερομηνία παρ/κού",1, "java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalDate,"");
        
        int[] inputtraderCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputtrader ={7};
        //10,inputService,"SELECT vatcat.vatPercentage FROM sxaccount, vatcat  WHERE sxaccount.vatCatId=vatcat.vatCatId AND sxaccount.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxaccount.accountId=");
        EntityDBFieldsCalculation[] fieldsCalculationtrader = new EntityDBFieldsCalculation[1];
        fieldsCalculationtrader[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,8,inputtraderCategory,inputtrader,"SELECT sxtrader.title FROM sxtrader, dbcompanyset WHERE dbcompanyset.dbcompanyId = "+VariablesGlobal.globalCompanyId+" AND dbcompanyset.esoexoCopyTraderNameToEsoexoComment = 1  AND sxtrader.traderId LIKE #");
//        fieldsCalculationtrader[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,9,inputtraderCategory,inputtrader,"SELECT sxtrader.typeofVatExclusionId FROM sxtrader  WHERE sxtrader.traderId LIKE #");
 //       fieldsCalculationtrader[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputtraderCategory,inputtrader,"SELECT sxtrader.currencyId FROM sxtrader  WHERE sxtrader.traderId LIKE #");
        
        
        esoexoHeaderDBFields[7] = new EntityDBFields("sxesoexoheader","traderId","συναλλασσόμενος",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"sxtrader", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationtrader,"");
        //esoexoHeaderDBFields[8] = new EntityDBFields("sxesoexoheader","paymentTypeId","τρόπος πληρωμής",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"paymenttype", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        esoexoHeaderDBFields[8] = new EntityDBFields("sxesoexoheader","comments","αιτιολογία",3,"java.lang.String",55,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,null,"");//fieldsCalculationtrader);
 //       int[] inputPreVatCategory ={FIELDSCALCULATION_CATEGORY_BACKWARD,11,11};
 //       int[] inputPreVat ={9,4,4};//field
 //       EntityDBFieldsCalculation[] fieldsCalculationVatCat = new EntityDBFieldsCalculation[1];
 //       fieldsCalculationVatCat[0] = new EntityDBFieldsCalculation(11,10,inputPreVatCategory,inputPreVat,calculationVatPercentageSql);
                
        //esoexoHeaderDBFields[9] = new EntityDBFields("sxesoexoheader","typeofVatExclusionId","καθεστώς ΦΠΑ",2,"java.lang.Integer",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCVatExclusion",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1",true,fieldsCalculationVatCat);
        //int[] inputPreVatCategory ={9};
        //int[] inputPreVat ={9};//field
        //10,inputService,"SELECT vatcat.vatPercentage FROM sxaccount, vatcat  WHERE sxaccount.vatCatId=vatcat.vatCatId AND sxaccount.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxaccount.accountId=");
        //EntityDBFieldsCalculation[] fieldsCalculationPreVat = new EntityDBFieldsCalculation[1];
        //fieldsCalculationPreVat[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,11,inputPreVatCategory,inputPreVat,"SELECT #");
        
        //fieldsCalculationCurrency[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,8,inputtraderCategory,inputtrader,"SELECT sxtrader.currencyId FROM sxtrader  WHERE sxtrader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxtrader.traderId LIKE #");
                 
        //esoexoHeaderDBFields[10] = new EntityDBFields("sxesoexoheader","isPrinted","εκτυπωμένο",2,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);                
        String[] childTableFieldsForSumsesoexolines=null;//{"προ ΦΠΑ","αξία ΦΠΑ","αξία"};
        
        esoexoHeaderDBFields[9] = new EntityDBFields("sxesoexoheader","esoexolines","λογαριασμοί",4,"table",FIELD_VISIBLE_AND_EDITABLE,"sxesoexoline",120,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,esoexolineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT * FROM sxesoexoline WHERE sxesoexoline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoline.isTemplate LIKE '0' ORDER BY sxesoexoline.inc",null,childTableFieldsForSumsesoexolines);        
         
        esoexoHeaderDBFields[10] = new EntityDBFields("sxesoexoheader","countTotal","πλήθος",5,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,9,7,DBFIELD_TYPE_OF_SUM_COUNT);        
        //esoexoHeaderDBFields[10] = new EntityDBFields("sxesoexoheader","quantityTotal","ποσότητα",3,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,11,6,DBFIELD_TYPE_OF_SUM_SUM);        
        esoexoHeaderDBFields[11] = new EntityDBFields("sxesoexoheader","pricePreVat","προ ΦΠΑ",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,9,7,DBFIELD_TYPE_OF_SUM_SUM);        
        esoexoHeaderDBFields[12] = new EntityDBFields("sxesoexoheader","priceVat","ΦΠΑ",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,9,8,DBFIELD_TYPE_OF_SUM_SUM);        
                
    //    int[] inputCurrencyCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
    //    int[] inputCurrency ={16,20};         
    //    EntityDBFieldsCalculation[] fieldsCalculationCurrency = new EntityDBFieldsCalculation[3];
    //    fieldsCalculationCurrency[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,21,inputCurrencyCategory,inputCurrency,"SELECT # * #");
    //    fieldsCalculationCurrency[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,17,null,null,"SELECT currencyId FROM dbcompany WHERE dbcompany.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId);                 
    //    fieldsCalculationCurrency[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,null,null,"SELECT currency.name FROM currency, dbcompany WHERE currency.currencyId = dbcompany.currencyId AND dbcompany.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId);         
/*         int[] inputTotalAfterVatCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputTotalAfterVat ={16};  
    
    int[] inputWithholdingTaxRateCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputWithholdingTaxRate ={16,17};         
     //   EntityDBFieldsCalculation[] fieldsCalculationWithholdingTax = new EntityDBFieldsCalculation[1];
    //    fieldsCalculationWithholdingTax[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,inputWithholdingTaxRateCategory,inputWithholdingTaxRate,"SELECT # * #");   
    int[] inputWithholdingTaxRateTotalCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputWithholdingTaxRateTotal ={16,18,16,18};   // when       
        //EntityDBFieldsCalculation[] fieldsCalculationWithholdingTaxTotal = new EntityDBFieldsCalculation[1];
     //   fieldsCalculationWithholdingTaxTotal[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputWithholdingTaxRateTotalCategory,inputWithholdingTaxRateTotal,"SELECT # + #");                                
    
        EntityDBFieldsCalculation[] fieldGetWithholdingTaxRate = new EntityDBFieldsCalculation[3];
        fieldGetWithholdingTaxRate[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,17,inputTotalAfterVatCategory,inputTotalAfterVat,              
        "SELECT IF( # > (SELECT dbcompany.amountIfGreaterThenCalculateWithHoldingTax FROM dbcompany WHERE dbcompany.dbcompanyId LIKE "+VariablesGlobal.globalCompanyId+"),"
                + "(SELECT dbcompany.rateOfWithHoldingTax FROM dbcompany WHERE dbcompany.dbcompanyId LIKE "+VariablesGlobal.globalCompanyId+")  ,'')");
        fieldGetWithholdingTaxRate[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,inputWithholdingTaxRateCategory,inputWithholdingTaxRate,"SELECT # * #/100");
        fieldGetWithholdingTaxRate[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputWithholdingTaxRateTotalCategory,inputWithholdingTaxRateTotal,"SELECT IF ((# - # = 0), '' , (# - #) )");                                
       */ esoexoHeaderDBFields[13] = new EntityDBFields("sxesoexoheader","priceTotal","σύνολο με ΦΠΑ",5,"java.lang.Double",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,/*fieldGetWithholdingTaxRate,*/9,9,DBFIELD_TYPE_OF_SUM_SUM);             
        //EntityDBFieldsCalculation[] fieldsCalculationCurrency = new EntityDBFieldsCalculation[1];

        //EntityDBFieldsCalculation[] fieldsCalculationCurrencyId = new EntityDBFieldsCalculation[1];
        //fieldsCalculationCurrencyId[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,16,null,null,"SELECT currencyId FROM dbcompany WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId);                 
    //    esoexoHeaderDBFields[19] = new EntityDBFields("sxesoexoheader","currencyId","νόμισμα συναλλαγής",4,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"currency", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);

    //    int[] inputWithholdingTaxRateCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
    //    int[] inputWithholdingTaxRate ={16,17};         
    //    EntityDBFieldsCalculation[] fieldsCalculationWithholdingTax = new EntityDBFieldsCalculation[1];
    ///    fieldsCalculationWithholdingTax[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,inputWithholdingTaxRateCategory,inputWithholdingTaxRate,"SELECT # * #");                        
    //    esoexoHeaderDBFields[13] = new EntityDBFields("sxesoexoheader","withholdingtaxRate","ποσοστό παρακράτησης",4,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"");//,null,true,fieldGetWithholdingTaxRate);        
    //    esoexoHeaderDBFields[14] = new EntityDBFields("sxesoexoheader","withholdingtaxAmount","παρακράτηση",4,"java.lang.Double",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"");//null,true,fieldsCalculationWithholdingTax);        //null);        

    //    int[] inputWithholdingTaxRateTotalCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
    //    int[] inputWithholdingTaxRateTotal ={16,18};         
    //    EntityDBFieldsCalculation[] fieldsCalculationWithholdingTaxTotal = new EntityDBFieldsCalculation[1];
    //    fieldsCalculationWithholdingTaxTotal[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputWithholdingTaxRateTotalCategory,inputWithholdingTaxRateTotal,"SELECT # + #");                                
    //    esoexoHeaderDBFields[15] = new EntityDBFields("sxesoexoheader","priceTotalAfterWithholdingTax","τελικό σύνολο",4,"java.lang.Double",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"");//null,true,fieldsCalculationWithholdingTaxTotal);//,11,12,DBFIELD_TYPE_OF_SUM_SUM);             
        
        esoexoEntityGroupOfComps[0] = new EntityGroupOfComps("hidden",6,0,FONT_SIZE_NOT_SET,GROUP_OF_PANEL_NOT_VISIBLE);
        esoexoEntityGroupOfComps[1] = new EntityGroupOfComps("βασικά",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        esoexoEntityGroupOfComps[2] = new EntityGroupOfComps("συναλλασσόμενος",2,0,FONT_SIZE_NOT_SET,GROUP_OF_PANEL_VISIBLE);
        esoexoEntityGroupOfComps[3] = new EntityGroupOfComps("λοιπά",6,0,FONT_SIZE_NOT_SET,GROUP_OF_PANEL_VISIBLE);
        esoexoEntityGroupOfComps[4] = new EntityGroupOfComps("λογαριασμοι",1,0,FONT_SIZE_NOT_SET,GROUP_OF_PANEL_VISIBLE);
        esoexoEntityGroupOfComps[5] = new EntityGroupOfComps("σύνολα",10,0,FONT_SIZE_NOT_SET,GROUP_OF_PANEL_VISIBLE);
        
         
        esoexoEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);


        
        sxAccountDBFields[0] = new EntityDBFields("sxaccount","accountId","Νο λογαριασμός",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"sxActionTypeId");
        //sxAccountDBFields[1] = new EntityDBFields("sxaccount","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        sxAccountDBFields[1] = new EntityDBFields("sxaccount","accountCode","κωδικός",0,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        sxAccountDBFields[2] = new EntityDBFields("sxaccount","accountDescr","ονομασία",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        sxAccountDBFields[3] = new EntityDBFields("sxaccount","accountCatId","κατηγορία",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCTypeCat", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        sxAccountDBFields[4] = new EntityDBFields("sxaccount","active","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");           
        sxAccountDBFields[5] = new EntityDBFields("sxaccount","vatCatId","κατηγορία ΦΠΑ",0,"java.lang.Integer",3, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"vatcat",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        sxAccountDBFields[6] = new EntityDBFields("sxaccount","participatesInMYF","συμμετοχή σε ΜΥΦ",0,"java.lang.Boolean",1,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");
        sxAccountDBFields[7] = new EntityDBFields("sxaccount","vatExempt","εκπίπτει ο ΦΠΑ",0,"java.lang.Boolean",1,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");
        sxAccountDBFields[8] = new EntityDBFields("sxaccount","vatCalculate","υπολογίζεται ο ΦΠΑ",0,"java.lang.Boolean",1,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");    
        sxAccountDBFields[9] = new EntityDBFields("sxaccount","vatDocCode","κωδικός περιοδικής",1,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");    
        sxAccountDBFields[10] = new EntityDBFields("sxaccount","vatDocCodeVat","κωδικός περιοδικής ΦΠΑ",1,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");     
        sxAccountDBFields[11] = new EntityDBFields("sxaccount","taxDocE3Code","κωδικός E3",1,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");   
        sxAccountDBFields[12] = new EntityDBFields("sxaccount","importexport","εισαγωγές - εξαγωγές",1,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCImportsExports",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");
        sxAccountDBFields[13] = new EntityDBFields("sxaccount","notes","σημειώσεις",2,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,""); 

        sxAccountEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        sxAccountEntityGroupOfComps[1]= new EntityGroupOfComps("έντυπα",8,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        sxAccountEntityGroupOfComps[2]= new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);       
        
   
        

        
   	/*activityCatDBFields[0] = new EntityDBFields("activitycat","activityCatId","Νο δραστηριότητας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	activityCatDBFields[1] = new EntityDBFields("activitycat","activityDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       
       //serviceCatLineDBFields[0] = new EntityDBFields("servicecat","serviceCatId","Νο κατηγορίας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);
       	//serviceCatLineDBFields[1] = new EntityDBFields("servicecat","catDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
       
        //serviceCatDBFields[0] = new EntityDBFields("servicecatheader","servicecat","κατηγορίες λογαριασμόςς",0,"table",FIELD_VISIBLE_AND_EDITABLE,"servicecat",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,serviceCatLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT serviceCatId AS\"Νο κατηγορίας\", catDescr AS\"κατηγορία\" FROM servicecat ORDER BY servicecat.catDescr",null);     
        
        //serviceCatEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0);            
        
        vatCatLineDBFields[0] = new EntityDBFields("vatcat","vatCatId","Νο κατηγορίας ΦΠΑ",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	vatCatLineDBFields[1] = new EntityDBFields("vatcat","vatDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");             
        vatCatLineDBFields[2] = new EntityDBFields("vatcat","vatPercentage","ποσοστό",0,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");            
        vatCatLineDBFields[3] = new EntityDBFields("vatcat","vatReducedCat","μειωμένος συντελεστής",0,"java.lang.Integer",16,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"vatcat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        vatCatLineDBFields[4] = new EntityDBFields("vatcat","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");               
        
        
        vatCatDBFields[0] = new EntityDBFields("vatcatheader","vatcat","κατηγορίες ΦΠΑ",0,"table",FIELD_VISIBLE_AND_EDITABLE,"vatcat",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,vatCatLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT * FROM vatcat ORDER BY vatCatId",null,null);     //String[] childTableFieldsForSumsIn   
        
        vatCatEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);       
        */        
        //------------------------- 

      
        
         //-------------------------        
         printFormDBFields[0] = new EntityDBFields("printform","printFormId","Νο φόρμας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         printFormDBFields[1] = new EntityDBFields("printform","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
         printFormDBFields[2] = new EntityDBFields("printform","printFormName","φόρμα εκτύπωσης",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"","");
         printFormDBFields[3] = new EntityDBFields("printform","isActive","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");             
         printFormDBFields[4] = new EntityDBFields("printform","printFormLaser","",1,"java.lang.String",45000,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        // printFormDBFields[5] = new EntityDBFields("printform","printFormDotMatrix","",2,"java.lang.String",45000,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         //printFormDBFields[6] = new EntityDBFields("printform","imageTop","εικόνα πάνω",3,"java.awt.image.BufferedImage",40/*45000*/,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");    
         //printFormDBFields[7] = new EntityDBFields("printform","imageBottom","εικόνα κάτω",3,"java.awt.image.BufferedImage",40/*45000*/,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");              
         //printFormDBFields[8] = new EntityDBFields("printform","imageBackground","εικόνα πίσω",3,"java.awt.image.BufferedImage",40/*45000*/,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");               
         
        
        
        printFormEntityGroupOfComps[0] = new EntityGroupOfComps("ιδιότητες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        printFormEntityGroupOfComps[1] = new EntityGroupOfComps("εκτύπωση για laser - γραφικά",2,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //printFormEntityGroupOfComps[2] = new EntityGroupOfComps("εκτύπωση για dot matrix - κρουστικός",2,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //printFormEntityGroupOfComps[3] = new EntityGroupOfComps("εικόνες για laser",2,3,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        
        
        printFormEntityGroupOfPanels[0] = new EntityGroupOfPanels("ιδιότητες",1);
        printFormEntityGroupOfPanels[1] = new EntityGroupOfPanels("φορμα laser",1);
        //printFormEntityGroupOfPanels[2] = new EntityGroupOfPanels("φόρμα dot matrix",1);
        //printFormEntityGroupOfPanels[3] = new EntityGroupOfPanels("εικόνες φόρμας",1);
        //-------------------------        
        
        
        
            
            //panels
            EntityCheckFields[] entityCheckFieldsEsexTemp = null;
        entityPanelEsexTempDataentry = new EntityPanel("ODOR","sxesoexoheader",esoexoHeaderTempDBFields,esoexoTempEntityGroupOfComps,esoexoTempEntityGroupOfPanels,"Νο προτύπου εσόδων εξόδων","","esoexoheaderId",saleQueryEditableTemplate,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSale,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,/*updateAdditionalActionType*/null,null/*entReportEsExDoc*/,null,entityCheckFieldsEsexTemp,null);            // entityTemplateEsoExoHeader not in here, only in the entity that can use a template
        entityPanelEsexTemp = new EntityPanel[] { entityPanelEsexTempDataentry};
     
        
        
        
        
        EntityDBFields[] esoexolineTempDBFields = new EntityDBFields[11];        
        esoexolineTempDBFields[0] = new EntityDBFields("sxesoexoline","esoexolineId","esoexolineId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        esoexolineTempDBFields[1] = new EntityDBFields("sxesoexoline","esoexoheaderId","Νο προτύπου εσόδων εξόδων",0,"java.lang.String",5, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        esoexolineTempDBFields[2] = new EntityDBFields("sxesoexoline","dbCompanyId","dbCompanyId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        esoexolineTempDBFields[3] = new EntityDBFields("sxesoexoline","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
      
       // int[] inputAccDescription ={FIELDSCALCULATION_CATEGORY_SAME};
        //int[] inputDescr ={4};//field        
        //int[] inputVatCategory ={FIELDSCALCULATION_CATEGORY_SAME};
       // int[] inputVat ={4};//field        
        //10,inputService,"SELECT vatcat.vatPercentage FROM sxaccount, vatcat  WHERE sxaccount.vatCatId=vatcat.vatCatId AND sxaccount.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxaccount.accountId=");
       // String calculationVatPercentageSql = "SELECT vatcat.vatPercentage FROM sxaccount, vatcat  WHERE sxaccount.vatCatId=vatcat.vatCatId AND sxaccount.accountId LIKE #";
       // String calculationAccDescrSql = "SELECT sxaccount.accountDescr FROM sxaccount  WHERE sxaccount.accountId LIKE #";
       // EntityDBFieldsCalculation[] fieldsCalculationService = new EntityDBFieldsCalculation[2];
        

       fieldsCalculationServiceSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,5,inputAccDescription,inputDescr,calculationAccDescrSql);
        fieldsCalculationServiceSelect[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,6,inputVatCategory,inputVat,calculationVatPercentageSql);
        
        esoexolineTempDBFields[4] = new EntityDBFields("sxesoexoline","accountId","λογαριασμός",2,"java.lang.Integer",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"sxaccount",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationServiceSelect,"sxActionTypeId");
	esoexolineTempDBFields[5] = new EntityDBFields("sxesoexoline","description","περ/φή λογαριασμού",2,"java.lang.String",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");

        
        
        esoexolineTempDBFields[6] = new EntityDBFields("sxesoexoline","vatPercentage","ΦΠΑ %",1,"java.lang.Double",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");//fieldsCalculationService);//fieldsCalculationVatPercentValue);        
        
        

       // int[] inputVatValueCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        //int[] inputVatValue ={7,6};
       //EntityDBFieldsCalculation[] fieldsCalculationVatValue = new EntityDBFieldsCalculation[1];
       //fieldsCalculationVatValue[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,8,inputVatValueCategory,inputVatValue,"SELECT #*#/100");//SELECT #+#"); 

       esoexolineTempDBFields[7] = new EntityDBFields("sxesoexoline","priceBeforeVat","αξία",1,"java.lang.Double",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationVatValueSelect,"");//fieldsCalculationPriceBeforeVat);

        

         // int[] inputValueWithVatCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        //int[] inputValueWithVat ={7,8};
       //EntityDBFieldsCalculation[] fieldsCalculationValueWithVat = new EntityDBFieldsCalculation[1];
       //fieldsCalculationValueWithVat[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,9,inputValueWithVatCategory,inputValueWithVat,"SELECT #+#");        
        
        esoexolineTempDBFields[8] = new EntityDBFields("sxesoexoline","vatValue","ΦΠΑ",1,"java.lang.Double",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationValueWithVatSelect,"");
        
       
        
       // int[] inputVatPercentCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        //int[] inputVatPercentValue ={9,6};
      // EntityDBFieldsCalculation[] fieldsCalculationNetValueFromTotal = new EntityDBFieldsCalculation[1];
      // fieldsCalculationNetValueFromTotal[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputVatPercentCategory,inputVatPercentValue,"SELECT #/(100+#)*100");         
        esoexolineTempDBFields[9] = new EntityDBFields("sxesoexoline","valueWithVat","σύνολο",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationNetValueFromTotalSelect,"");                    
        //esoexolineTempDBFields[10] = new EntityDBFields("sxesoexoline","dbYearId","dbYearId",1,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        esoexolineTempDBFields[10] = new EntityDBFields("sxesoexoline","isTemplate","isTemplate",1,"java.lang.String",3,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"1","");
        
        
        
        esoexoHeaderTempDBFields[0] = new EntityDBFields("sxesoexoheader","esoexoheaderId","Νο προτύπου εσόδων εξόδων",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        esoexoHeaderTempDBFields[1] = new EntityDBFields("sxesoexoheader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
       // esoexoHeaderTempDBFields[2] = new EntityDBFields("sxesoexoheader","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        

      esoexoHeaderTempDBFields[2] = new EntityDBFields("sxesoexoheader","titleOfTemplate","τίτλος",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//fieldsCalculationtrader);
      esoexoHeaderTempDBFields[3] = new EntityDBFields("sxesoexoheader","sxActionTypeId","τύπος παρ/κού",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"sxactiontype",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");// variable before last: 'false' means update
        
        esoexoHeaderTempDBFields[4] = new EntityDBFields("sxesoexoheader","esoexoCodeOfDocument","κωδικός παρ/κού",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//,entityFieldUpdateAdditionalCodeOfDocument);
        //esoexoHeaderTempDBFields[5] = new EntityDBFields("sxesoexoheader","dateOfEsoexo","ημερομηνία παρ/κού",0, "java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
         esoexoHeaderTempDBFields[5] = new EntityDBFields("sxesoexoheader","isTemplateActive","ενεργό",0,"java.lang.Boolean",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");
   
        
        esoexoHeaderTempDBFields[6] = new EntityDBFields("sxesoexoheader","traderId","συναλλασσόμενος",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"sxtrader", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//fieldsCalculationtrader);
        
        esoexoHeaderTempDBFields[7] = new EntityDBFields("sxesoexoheader","comments","αιτιολογία",2,"java.lang.String",55,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//fieldsCalculationtrader);

        String[] childTableFieldsForSumsesoexolinesTemplates=null;//{"προ ΦΠΑ","αξία ΦΠΑ","αξία"};
        
        esoexoHeaderTempDBFields[8] = new EntityDBFields("sxesoexoheader","esoexoline","λογαριασμοί",3,"table",FIELD_VISIBLE_AND_EDITABLE,"sxesoexoline",120,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,esoexolineTempDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT * FROM sxesoexoline WHERE sxesoexoline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoline.isTemplate ='1' ORDER BY sxesoexoline.inc",null,childTableFieldsForSumsesoexolinesTemplates);        
        esoexoHeaderTempDBFields[9] = new EntityDBFields("sxesoexoheader","isTemplate","isTemplate",4,"java.lang.String",3,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"1","");
        
        //esoexoHeaderTempDBFields[9] = new EntityDBFields("sxesoexoheader","countTotal","πλήθος",4,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,8,7,DBFIELD_TYPE_OF_SUM_COUNT);        
        
        //esoexoHeaderTempDBFields[10] = new EntityDBFields("sxesoexoheader","pricePreVat","προ ΦΠΑ",4,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,8,7,DBFIELD_TYPE_OF_SUM_SUM);        
        //esoexoHeaderTempDBFields[11] = new EntityDBFields("sxesoexoheader","priceVat","ΦΠΑ",4,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,8,8,DBFIELD_TYPE_OF_SUM_SUM);        
                
         //esoexoHeaderTempDBFields[12] = new EntityDBFields("sxesoexoheader","priceTotal","σύνολο με ΦΠΑ",4,"java.lang.Double",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,/*fieldGetWithholdingTaxRate,*/8,9,DBFIELD_TYPE_OF_SUM_SUM);             
        
        
   
        
        esoexoTempEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        esoexoTempEntityGroupOfComps[1] = new EntityGroupOfComps("συναλλασσόμενος",2,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        esoexoTempEntityGroupOfComps[2] = new EntityGroupOfComps("λοιπά",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        esoexoTempEntityGroupOfComps[3] = new EntityGroupOfComps("λογαριασμοι",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        esoexoTempEntityGroupOfComps[4] = new EntityGroupOfComps("περιγρφή",2,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE);
         
        esoexoTempEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);

        
        
        
        //-------------------------------
    /*   	paymenttypeLineDBFields[0] = new EntityDBFields("paymentType","paymentTypeId","Νο τρόπου πληρωμής",0,"java.lang.Integer",1, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	paymenttypeLineDBFields[1] = new EntityDBFields("paymentType","description","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	//paymenttypeDBFields[2] = new EntityDBFields("paymentType","abbreviation","συντομογραφία",0,"java.lang.String",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,true,FIELD_SUGGEST,FIELD_VALIDATION_NO,false,null);

        paymenttypeDBFields[0] = new EntityDBFields("paymentTypeheader","paymentType","τύποι πληρωμής",0,"table",FIELD_VISIBLE_AND_EDITABLE,"paymentType",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,paymenttypeLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT paymentTypeId AS \"Νο\", description AS \"τρόπος πληρωμής\" FROM paymentType ORDER BY description",null,null);     //String[] childTableFieldsForSumsIn   
        paymenttypeEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);    */
        //------------------------------------
        incomeSettLineDBFields[0] = new EntityDBFields("sxincomedocsettings","incomeDocSettingsId","Νο νομίσματος",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        incomeSettLineDBFields[1] = new EntityDBFields("sxincomedocsettings","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        incomeSettLineDBFields[2] = new EntityDBFields("sxincomedocsettings","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        incomeSettLineDBFields[3] = new EntityDBFields("sxincomedocsettings","accountId","λογαριασμός",0,"java.lang.Integer",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"sxaccount",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//true,fieldsCalculationService,"sxActionTypeId");
        
        
        incomeSettLineDBFields[4] = new EntityDBFields("sxincomedocsettings","typeId","τύπος",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //currencyLineDBFields[2] = new EntityDBFields("sxincomedocsettings","countOfDecimals","δεκαδικά",0,"java.lang.Integer",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        incomeSettLineDBFields[5] = new EntityDBFields("sxincomedocsettings","codeId","κωδικός",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");      
   	// sample-> vatCatLineDBFields[4] = new EntityDBFields("vatcat","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true");                        
        
        incomeSettDBFields[0] = new EntityDBFields("sxincomedocsettingsheader","sxincomedocsettings","λογαριασμοί",0,"table",FIELD_VISIBLE_AND_EDITABLE,"sxincomedocsettings",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,incomeSettLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT incomeDocSettingsId, dbCompanyId, dbYearId, accountId, typeId, codeId  FROM sxincomedocsettings WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND dbYearId LIKE "+VariablesGlobal.globalYearId+" ORDER BY incomeDocSettingsId",null,null);     //String[] childTableFieldsForSumsIn   
        incomeSettEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);   
       //-------------------------------------- 
        
  
        //--------------------------------------------

        sxVatDocDBFields[0] = new EntityDBFields("sxvatdocforperiod","vatDocForPeriodId","Νο περιοδικής ΦΠΑ",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        sxVatDocDBFields[1] = new EntityDBFields("sxvatdocforperiod","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        sxVatDocDBFields[2] = new EntityDBFields("sxvatdocforperiod","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        sxVatDocDBFields[3] = new EntityDBFields("sxvatdocforperiod","vatDocDescr","ονομασία",0,"java.lang.String",28,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        sxVatDocDBFields[4] = new EntityDBFields("sxvatdocforperiod","vatForPeriodStartDate","ημερομηνία έναρξης περιόδου",0,"java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");  
        sxVatDocDBFields[5] = new EntityDBFields("sxvatdocforperiod","vatForPeriodEndDate","ημερομηνία λήξης περιόδου",0,"java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");  
    

        
        
       int[] inputVatDocCatf307 ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
       int[] inputVatDocf307 ={6,10,14,18,22,26};        
        
        int[] inputVatDocCatf337 ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
       int[] inputVatDocf337 ={7,11,15,19,23,27};  
       
        int[] inputVatDocCatf301 ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVatDocf301 ={6};
       EntityDBFieldsCalculation[] fieldsCalculationVatDoc301 = new EntityDBFieldsCalculation[3];
       fieldsCalculationVatDoc301[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputVatDocCatf301,inputVatDocf301,"SELECT #*13/100");//SELECT #+#"); 
       fieldsCalculationVatDoc301[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,30,inputVatDocCatf307,inputVatDocf307,"SELECT #+#+#+#+#+#");//SELECT #+#"); 
       fieldsCalculationVatDoc301[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,31,inputVatDocCatf337,inputVatDocf337,"SELECT #+#+#+#+#+#");//SELECT #+#");
       
        int[] inputVatDocCatf302 ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVatDocf302 ={10};
        EntityDBFieldsCalculation[] fieldsCalculationVatDoc302 = new EntityDBFieldsCalculation[3];
       fieldsCalculationVatDoc302[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,11,inputVatDocCatf302,inputVatDocf302,"SELECT #*6/100");//SELECT #+#"); 
       fieldsCalculationVatDoc302[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,30,inputVatDocCatf307,inputVatDocf307,"SELECT #+#+#+#+#+#");//SELECT #+#");
       fieldsCalculationVatDoc302[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,31,inputVatDocCatf337,inputVatDocf337,"SELECT #+#+#+#+#+#");//SELECT #+#");
       
       int[] inputVatDocCatf303 ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVatDocf303 ={14}; 
        EntityDBFieldsCalculation[] fieldsCalculationVatDoc303 = new EntityDBFieldsCalculation[3];
       fieldsCalculationVatDoc303[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,15,inputVatDocCatf303,inputVatDocf303,"SELECT #*24/100");//SELECT #+#"); 
      fieldsCalculationVatDoc303[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,30,inputVatDocCatf307,inputVatDocf307,"SELECT #+#+#+#+#+#");//SELECT #+#");        
      fieldsCalculationVatDoc303[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,31,inputVatDocCatf337,inputVatDocf337,"SELECT #+#+#+#+#+#");//SELECT #+#"); 
      
      int[] inputVatDocCatf304 ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVatDocf304 ={18}; 
        EntityDBFieldsCalculation[] fieldsCalculationVatDoc304 = new EntityDBFieldsCalculation[3];
       fieldsCalculationVatDoc304[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputVatDocCatf304,inputVatDocf304,"SELECT #*9/100");//SELECT #+#"); 
      fieldsCalculationVatDoc304[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,30,inputVatDocCatf307,inputVatDocf307,"SELECT #+#+#+#+#+#");//SELECT #+#");        
       fieldsCalculationVatDoc304[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,31,inputVatDocCatf337,inputVatDocf337,"SELECT #+#+#+#+#+#");//SELECT #+#");
      
      
       int[] inputVatDocCatf305 ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVatDocf305 ={22}; 
        EntityDBFieldsCalculation[] fieldsCalculationVatDoc305 = new EntityDBFieldsCalculation[3];
       fieldsCalculationVatDoc305[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,23,inputVatDocCatf305,inputVatDocf305,"SELECT #*4/100");//SELECT #+#"); 
      fieldsCalculationVatDoc305[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,30,inputVatDocCatf307,inputVatDocf307,"SELECT #+#+#+#+#+#");//SELECT #+#");
      fieldsCalculationVatDoc305[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,31,inputVatDocCatf337,inputVatDocf337,"SELECT #+#+#+#+#+#");//SELECT #+#");
      
       
         int[] inputVatDocCatf306 ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVatDocf306 ={26}; 
        EntityDBFieldsCalculation[] fieldsCalculationVatDoc306 = new EntityDBFieldsCalculation[3];
       fieldsCalculationVatDoc306[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,27,inputVatDocCatf306,inputVatDocf306,"SELECT #*17/100");//SELECT #+#"); 
      fieldsCalculationVatDoc306[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,30,inputVatDocCatf307,inputVatDocf307,"SELECT #+#+#+#+#+#");//SELECT #+#");        
      fieldsCalculationVatDoc306[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,31,inputVatDocCatf337,inputVatDocf337,"SELECT #+#+#+#+#+#");//SELECT #+#");

      
      
       int[] inputVatDocCatf367 ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
       int[] inputVatDocf367 ={8,12,16,20,24,28}; 
       EntityDBFieldsCalculation[] fieldsCalculationVatDocSumTo367 = new EntityDBFieldsCalculation[1];
       //fieldsCalculationVatDoc301[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputVatDocCatf301,inputVatDocf301,"SELECT #*13/100");//SELECT #+#"); 
       fieldsCalculationVatDocSumTo367[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,32,inputVatDocCatf367,inputVatDocf367,"SELECT #+#+#+#+#+#");//SELECT #+#"); 
        
       
       int[] inputVatDocCatf430 ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
       int[] inputVatDocf430 ={33,41,57}; 
       
       int[] inputVatDocCatf387 ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
       int[] inputVatDocf387 ={9,13,17,21,25,29}; 
       EntityDBFieldsCalculation[] fieldsCalculationVatDocSumTo387 = new EntityDBFieldsCalculation[2];
       //fieldsCalculationVatDoc301[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputVatDocCatf301,inputVatDocf301,"SELECT #*13/100");//SELECT #+#"); 
       fieldsCalculationVatDocSumTo387[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,33,inputVatDocCatf387,inputVatDocf387,"SELECT #+#+#+#+#+#");//SELECT #+#"); 
       fieldsCalculationVatDocSumTo387[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,65,inputVatDocCatf430,inputVatDocf430,"SELECT #+#+#");//SELECT #+#");            
       
         int[] inputVatDocCatf410 ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
       int[] inputVatDocf410 ={36,40,44}; 
       EntityDBFieldsCalculation[] fieldsCalculationVatDocSumTo410 = new EntityDBFieldsCalculation[2];
       //fieldsCalculationVatDoc301[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputVatDocCatf301,inputVatDocf301,"SELECT #*13/100");//SELECT #+#"); 
       fieldsCalculationVatDocSumTo410[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,41,inputVatDocCatf410,inputVatDocf410,"SELECT #+#+#");//SELECT #+#"); 
       fieldsCalculationVatDocSumTo410[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,65,inputVatDocCatf430,inputVatDocf430,"SELECT #+#+#");//SELECT #+#");
       
         int[] inputVatDocCatf428 ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
       int[] inputVatDocf428 ={52,56,60}; 
       EntityDBFieldsCalculation[] fieldsCalculationVatDocSumTo428 = new EntityDBFieldsCalculation[2];
       //fieldsCalculationVatDoc301[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputVatDocCatf301,inputVatDocf301,"SELECT #*13/100");//SELECT #+#"); 
       fieldsCalculationVatDocSumTo428[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,57,inputVatDocCatf428,inputVatDocf428,"SELECT #+#+#");//SELECT #+#"); 
       fieldsCalculationVatDocSumTo428[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,65,inputVatDocCatf430,inputVatDocf430,"SELECT #+#+#");//SELECT #+#");       
       
       
            int lengthoftxts = 14;    
        
        sxVatDocDBFields[6] = new EntityDBFields("sxvatdocforperiod","f301","301",1,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDoc301,"");
        sxVatDocDBFields[7] = new EntityDBFields("sxvatdocforperiod","f331","331",1,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                    
        sxVatDocDBFields[8] = new EntityDBFields("sxvatdocforperiod","f361","361",1,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo367,"");                    
        sxVatDocDBFields[9] = new EntityDBFields("sxvatdocforperiod","f381","381",1,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo387,"");                    
        
        sxVatDocDBFields[10] = new EntityDBFields("sxvatdocforperiod","f302","302",2,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDoc302,"");
        sxVatDocDBFields[11] = new EntityDBFields("sxvatdocforperiod","f332","332",2,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                      
        sxVatDocDBFields[12] = new EntityDBFields("sxvatdocforperiod","f362","362",2,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo367,"");                    
        sxVatDocDBFields[13] = new EntityDBFields("sxvatdocforperiod","f382","382",2,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo387,"");                         
        
        sxVatDocDBFields[14] = new EntityDBFields("sxvatdocforperiod","f303","303",3,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDoc303,"");
        sxVatDocDBFields[15] = new EntityDBFields("sxvatdocforperiod","f333","333",3,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                      
        sxVatDocDBFields[16] = new EntityDBFields("sxvatdocforperiod","f363","363",3,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo367,"");                    
        sxVatDocDBFields[17] = new EntityDBFields("sxvatdocforperiod","f383","383",3,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo387,"");                                     
        
        sxVatDocDBFields[18] = new EntityDBFields("sxvatdocforperiod","f304","304",4,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDoc304,"");            
        sxVatDocDBFields[19] = new EntityDBFields("sxvatdocforperiod","f334","334",4,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                       
        sxVatDocDBFields[20] = new EntityDBFields("sxvatdocforperiod","f364","364",4,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo367,"");                    
        sxVatDocDBFields[21] = new EntityDBFields("sxvatdocforperiod","f384","384",4,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo387,"");                                             
        
        sxVatDocDBFields[22] = new EntityDBFields("sxvatdocforperiod","f305","305",5,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDoc305,"");                    
        sxVatDocDBFields[23] = new EntityDBFields("sxvatdocforperiod","f335","335",5,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                       
        sxVatDocDBFields[24] = new EntityDBFields("sxvatdocforperiod","f365","365",5,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo367,"");                    
        sxVatDocDBFields[25] = new EntityDBFields("sxvatdocforperiod","f385","385",5,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo387,"");                                      
        
        sxVatDocDBFields[26] = new EntityDBFields("sxvatdocforperiod","f306","306",6,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDoc306,"");                    
        sxVatDocDBFields[27] = new EntityDBFields("sxvatdocforperiod","f336","336",6,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                     
        sxVatDocDBFields[28] = new EntityDBFields("sxvatdocforperiod","f366","366",6,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo367,"");                    
        sxVatDocDBFields[29] = new EntityDBFields("sxvatdocforperiod","f386","386",6,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo387,"");                                           
        
        sxVatDocDBFields[30] = new EntityDBFields("sxvatdocforperiod","f307","307",7,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                    
        sxVatDocDBFields[31] = new EntityDBFields("sxvatdocforperiod","f337","337",7,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                    
        sxVatDocDBFields[32] = new EntityDBFields("sxvatdocforperiod","f367","367",7,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                    
        sxVatDocDBFields[33] = new EntityDBFields("sxvatdocforperiod","f387","387",7,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                                                    
 
        sxVatDocDBFields[34] = new EntityDBFields("sxvatdocforperiod","f342","342",8,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                    
        sxVatDocDBFields[35] = new EntityDBFields("sxvatdocforperiod","hidden","000",8,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[36] = new EntityDBFields("sxvatdocforperiod","f400","400",8,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo410,"");                                                    
        sxVatDocDBFields[37] = new EntityDBFields("sxvatdocforperiod","hidden","000",8,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
 
        sxVatDocDBFields[38] = new EntityDBFields("sxvatdocforperiod","f345","345",9,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                    
        sxVatDocDBFields[39] = new EntityDBFields("sxvatdocforperiod","hidden","000",9,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[40] = new EntityDBFields("sxvatdocforperiod","f402","402",9,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo410,"");                                                    
        sxVatDocDBFields[41] = new EntityDBFields("sxvatdocforperiod","f410","410",9,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
        
        sxVatDocDBFields[42] = new EntityDBFields("sxvatdocforperiod","f348","348",10,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                    
        sxVatDocDBFields[43] = new EntityDBFields("sxvatdocforperiod","hidden","000",10,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[44] = new EntityDBFields("sxvatdocforperiod","f407","407",10,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo410,"");                                                    
        sxVatDocDBFields[45] = new EntityDBFields("sxvatdocforperiod","hidden","000",10,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
 
         sxVatDocDBFields[46] = new EntityDBFields("sxvatdocforperiod","f349","349",11,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                    
        sxVatDocDBFields[47] = new EntityDBFields("sxvatdocforperiod","hidden","000",11,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[48] = new EntityDBFields("sxvatdocforperiod","hidden","000",11,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                                                    
        sxVatDocDBFields[49] = new EntityDBFields("sxvatdocforperiod","hidden","000",11,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
  
        sxVatDocDBFields[50] = new EntityDBFields("sxvatdocforperiod","f310","310",12,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                    
        sxVatDocDBFields[51] = new EntityDBFields("sxvatdocforperiod","hidden","000",12,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[52] = new EntityDBFields("sxvatdocforperiod","f411","411",12,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo428,"");                                                    
        sxVatDocDBFields[53] = new EntityDBFields("sxvatdocforperiod","hidden","000",12,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
        
        sxVatDocDBFields[54] = new EntityDBFields("sxvatdocforperiod","f311","311",13,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                    
        sxVatDocDBFields[55] = new EntityDBFields("sxvatdocforperiod","hidden","000",13,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[56] = new EntityDBFields("sxvatdocforperiod","f422","422",13,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo428,"");                                                    
        sxVatDocDBFields[57] = new EntityDBFields("sxvatdocforperiod","f428","428",13,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
         
        sxVatDocDBFields[58] = new EntityDBFields("sxvatdocforperiod","f312","312",14,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                    
        sxVatDocDBFields[59] = new EntityDBFields("sxvatdocforperiod","hidden","000",14,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[60] = new EntityDBFields("sxvatdocforperiod","f423","423",14,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"0",null,fieldsCalculationVatDocSumTo428,"");                                                    
        sxVatDocDBFields[61] = new EntityDBFields("sxvatdocforperiod","hidden","000",14,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
               
        sxVatDocDBFields[62] = new EntityDBFields("sxvatdocforperiod","hidden","000",15,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                    
        sxVatDocDBFields[63] = new EntityDBFields("sxvatdocforperiod","hidden","000",15,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[64] = new EntityDBFields("sxvatdocforperiod","hidden","000",15,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                                                    
        sxVatDocDBFields[65] = new EntityDBFields("sxvatdocforperiod","f430","430",15,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
 
        
        sxVatDocDBFields[66] = new EntityDBFields("sxvatdocforperiod","f470","470",16,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[67] = new EntityDBFields("sxvatdocforperiod","hidden","000",16,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                                                    
        sxVatDocDBFields[68] = new EntityDBFields("sxvatdocforperiod","f480","480",16,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
    
        sxVatDocDBFields[69] = new EntityDBFields("sxvatdocforperiod","f401","401",17,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[70] = new EntityDBFields("sxvatdocforperiod","hidden","000",17,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                                                    
        sxVatDocDBFields[71] = new EntityDBFields("sxvatdocforperiod","f483","483",17,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
 
        sxVatDocDBFields[72] = new EntityDBFields("sxvatdocforperiod","f403","403",18,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[73] = new EntityDBFields("sxvatdocforperiod","hidden","000",18,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                                                    
        sxVatDocDBFields[74] = new EntityDBFields("sxvatdocforperiod","f505","505",18,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
 
        sxVatDocDBFields[75] = new EntityDBFields("sxvatdocforperiod","f404","404",19,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[76] = new EntityDBFields("sxvatdocforperiod","hidden","000",19,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                                                    
        sxVatDocDBFields[77] = new EntityDBFields("sxvatdocforperiod","hidden","000",19,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
 
        sxVatDocDBFields[78] = new EntityDBFields("sxvatdocforperiod","f502","502",20,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");  //FIELD_NOT_VISIBLE   //FIELD_VISIBLE_NOT_EDITABLE_ALWAYS               
        sxVatDocDBFields[79] = new EntityDBFields("sxvatdocforperiod","hidden","000",20,"java.lang.String",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");                                                    
        sxVatDocDBFields[80] = new EntityDBFields("sxvatdocforperiod","f511","511",20,"java.lang.Double",lengthoftxts,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");    //FIELD_NOT_VISIBLE// FIELD_VISIBLE_NOT_EDITABLE_ALWAYS                                                
 
        int intFontSize= 12;
        sxVatDocEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[1]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[2]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[3]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[4]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[5]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[6]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[7]= new EntityGroupOfComps("σύνολα",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[8]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[9]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[10]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[11]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[12]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[13]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[14]= new EntityGroupOfComps("τιμές",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[15]= new EntityGroupOfComps("σύνολα",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[16]= new EntityGroupOfComps("σύνολα",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[17]= new EntityGroupOfComps("σύνολα",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[18]= new EntityGroupOfComps("σύνολα",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[19]= new EntityGroupOfComps("σύνολα",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
        sxVatDocEntityGroupOfComps[20]= new EntityGroupOfComps("σύνολα",8,0,intFontSize, GROUP_OF_PANEL_VISIBLE);
               
        
        //---------------------------------------------------
        
        
        
         
        EntityDBFields[] sxIncomeDocDataDBFields = new EntityDBFields[11];
        sxIncomeDocDataDBFields[0] = new EntityDBFields("sxincomedocdata","incomeDocDataId","idForData",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        sxIncomeDocDataDBFields[1] = new EntityDBFields("sxincomedocdata","incomeDocId","Νο εισοδήματος",0,"java.lang.String",3, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,FIELD_OBLIGATORY,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        sxIncomeDocDataDBFields[2] = new EntityDBFields("sxincomedocdata","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        sxIncomeDocDataDBFields[3] = new EntityDBFields("sxincomedocdata","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        sxIncomeDocDataDBFields[4] = new EntityDBFields("sxincomedocdata","inc","inc",0,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        sxIncomeDocDataDBFields[5] = new EntityDBFields("sxincomedocdata","traderId","συναλλασσόμενος",0,"java.lang.Integer",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"trader1Col", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//fieldsCalculationtrader);
        //sxIncomeDocDataDBFields[5] = new EntityDBFields("sxincomedocdata","traderId","συναλλασσόμενος",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"sxtrader", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//fieldsCalculationtrader);
        //sxIncomeDocDataDBFields[5] = new EntityDBFields("sxincomedocdata","fieldValue","fieldValue",0,"java.lang.String",500,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        sxIncomeDocDataDBFields[6] = new EntityDBFields("sxincomedocdata","typeId","typeId",0,"java.lang.Integer",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        sxIncomeDocDataDBFields[7] = new EntityDBFields("sxincomedocdata","codeId","codeId",0,"java.lang.Integer",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        sxIncomeDocDataDBFields[8] = new EntityDBFields("sxincomedocdata","incomeCount","πλήθος παρ/κων",0,"java.lang.Integer",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                    
        sxIncomeDocDataDBFields[9] = new EntityDBFields("sxincomedocdata","incomeGross","incomeGross",0,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                    
        sxIncomeDocDataDBFields[10] = new EntityDBFields("sxincomedocdata","incomeNet","incomeNet",0,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                    
        
        
        
        sxIncomeDocDBFields[0] = new EntityDBFields("sxincomedoc","incomeDocId","Νο εισοδήματος",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        sxIncomeDocDBFields[1] = new EntityDBFields("sxincomedoc","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        sxIncomeDocDBFields[2] = new EntityDBFields("sxincomedoc","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        sxIncomeDocDBFields[3] = new EntityDBFields("sxincomedoc","description","ονομασία",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
// sxIncomeDocDBFields[4] = new EntityDBFields("sxincomedoc","dateSave","αποθήκευση",0,"java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,VariablesGlobal.globalDate); 
//sxVatDocDBFields[4] = new EntityDBFields("sxvatdocforperiod","vatDocCatId","κατηγορία",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCTypeCat", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //sxVatDocDBFields[5] = new EntityDBFields("sxvatdocforperiod","vatForPeriodStartDate","έναρξη περιόδου",0, "java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //sxVatDocDBFields[6] = new EntityDBFields("sxvatdocforperiod","vatForPeriodEndDate","λήξη περιόδου",0, "java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);

        String[] childTableFieldsForSumsIncomeDoc=null;//{"προ ΦΠΑ","αξία ΦΠΑ","αξία"};
        sxIncomeDocDBFields[4] = new EntityDBFields("sxincomedoc","sxincomedocdata","εισοδήματα",1,"table",FIELD_VISIBLE_AND_EDITABLE,"sxincomedocdata",120,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,sxIncomeDocDataDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM sxincomedocdata WHERE sxincomedocdata.dbYearId LIKE "+VariablesGlobal.globalYearId+" AND sxincomedocdata.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY sxincomedocdata.inc",null,childTableFieldsForSumsIncomeDoc);        
        sxIncomeDocDBFields[5] = new EntityDBFields("sxincomedoc","totalCount","totalCount",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,4,9,DBFIELD_TYPE_OF_SUM_COUNT); 
        sxIncomeDocDBFields[6] = new EntityDBFields("sxincomedoc","totalGross","totalGross",2,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,4,9,DBFIELD_TYPE_OF_SUM_SUM);                
        sxIncomeDocDBFields[7] = new EntityDBFields("sxincomedoc","totalNet","totalNet",2,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,4,10,DBFIELD_TYPE_OF_SUM_SUM);                     
       
        sxIncomeDocEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        sxIncomeDocEntityGroupOfComps[1]= new EntityGroupOfComps("-",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        sxIncomeDocEntityGroupOfComps[2]= new EntityGroupOfComps("σύνολα",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);   
              
        
         //--------------------------------------------
        
        String[] childTableFieldsForSumsMyfHeader=null;//{"προ ΦΠΑ","αξία ΦΠΑ","αξία"};
       
        EntityDBFields[] myfLineDBFields1 = new EntityDBFields[12];        
        myfLineDBFields1[0] = new EntityDBFields("myfline","myfLineId","myfLineId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        myfLineDBFields1[1] = new EntityDBFields("myfline","myfHeaderId","Νο ΜΥΦ",0,"java.lang.String",5, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        myfLineDBFields1[2] = new EntityDBFields("myfline","dbCompanyId","dbCompanyId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
       myfLineDBFields1[3] = new EntityDBFields("myfline","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        myfLineDBFields1[4] = new EntityDBFields("myfline","listTypeId","listTypeId",0,"java.lang.Integer",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");
        myfLineDBFields1[5] = new EntityDBFields("myfline","afm","ΑΦΜ",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        myfLineDBFields1[6] = new EntityDBFields("myfline","name","συναλλασσόμενος",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        myfLineDBFields1[7] = new EntityDBFields("myfline","isNormalOrCredit","isNormalOrCredit",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields1[8] = new EntityDBFields("myfline","field4","field4",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields1[9] = new EntityDBFields("myfline","field5","field5",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields1[10] = new EntityDBFields("myfline","field6","field6",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields1[11] = new EntityDBFields("myfline","field7","field7",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        myfLineDBFields1[8] = new EntityDBFields("myfline","countOfDocs","πλήθος",0,"java.lang.Integer",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");           
        myfLineDBFields1[9] = new EntityDBFields("myfline","valueNet","προ ΦΠΑ",0,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        myfLineDBFields1[10] = new EntityDBFields("myfline","valueVat","ΦΠΑ",0,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        myfLineDBFields1[11] = new EntityDBFields("myfline","valueGross","μικτό",0,"java.lang.Double",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//fieldsCalculationService);//fieldsCalculationVatPercentValue);        
                 

        
EntityDBFields[] myfLineDBFields2 = new EntityDBFields[11];        
        myfLineDBFields2[0] = new EntityDBFields("myfline","myfLineId","myfLineId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        myfLineDBFields2[1] = new EntityDBFields("myfline","myfHeaderId","Νο ΜΥΦ",0,"java.lang.String",5, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        myfLineDBFields2[2] = new EntityDBFields("myfline","dbCompanyId","dbCompanyId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
       myfLineDBFields2[3] = new EntityDBFields("myfline","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        myfLineDBFields2[4] = new EntityDBFields("myfline","listTypeId","listTypeId",0,"java.lang.Integer",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"2","");
        myfLineDBFields2[5] = new EntityDBFields("myfline","afm","ΑΦΜ",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        myfLineDBFields2[6] = new EntityDBFields("myfline","name","συναλλασσόμενος",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields2[7] = new EntityDBFields("myfline","field3","field3",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields2[8] = new EntityDBFields("myfline","field4","field4",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        myfLineDBFields2[7] = new EntityDBFields("myfline","countOfDocs","πλήθος",0,"java.lang.Integer",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");           
        myfLineDBFields2[8] = new EntityDBFields("myfline","valueNet","προ ΦΠΑ",0,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        myfLineDBFields2[9] = new EntityDBFields("myfline","valueVat","ΦΠΑ",0,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        myfLineDBFields2[10] = new EntityDBFields("myfline","valueGross","μικτό",0,"java.lang.Double",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//fieldsCalculationService);//fieldsCalculationVatPercentValue);               
        //myfLineDBFields2[9] = new EntityDBFields("myfline","field5","field5",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //myfLineDBFields2[10] = new EntityDBFields("myfline","field6","field6",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
       // myfLineDBFields2[11] = new EntityDBFields("myfline","field7","field7",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
      
  EntityDBFields[] myfLineDBFields3 = new EntityDBFields[12];        
        myfLineDBFields3[0] = new EntityDBFields("myfline","myfLineId","myfLineId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        myfLineDBFields3[1] = new EntityDBFields("myfline","myfHeaderId","Νο ΜΥΦ",0,"java.lang.String",5, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        myfLineDBFields3[2] = new EntityDBFields("myfline","dbCompanyId","dbCompanyId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
       myfLineDBFields3[3] = new EntityDBFields("myfline","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        myfLineDBFields3[4] = new EntityDBFields("myfline","listTypeId","listTypeId",0,"java.lang.Integer",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"3","");
        myfLineDBFields3[5] = new EntityDBFields("myfline","afm","ΑΦΜ",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        myfLineDBFields3[6] = new EntityDBFields("myfline","name","συναλλασσόμενος",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        myfLineDBFields3[7] = new EntityDBFields("myfline","isNormalOrCredit","isNormalOrCredit",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields3[8] = new EntityDBFields("myfline","field4","field4",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields3[9] = new EntityDBFields("myfline","field5","field5",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields3[10] = new EntityDBFields("myfline","field6","field6",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields3[11] = new EntityDBFields("myfline","field7","field7",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        myfLineDBFields3[8] = new EntityDBFields("myfline","countOfDocs","πλήθος",0,"java.lang.Integer",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");           
        myfLineDBFields3[9] = new EntityDBFields("myfline","valueNet","προ ΦΠΑ",0,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        myfLineDBFields3[10] = new EntityDBFields("myfline","valueVat","ΦΠΑ",0,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        myfLineDBFields3[11] = new EntityDBFields("myfline","valueGross","μικτό",0,"java.lang.Double",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//fieldsCalculationService);//fieldsCalculationVatPercentValue);        
                
 
          EntityDBFields[] myfLineDBFields4 = new EntityDBFields[11];        
        myfLineDBFields4[0] = new EntityDBFields("myfline","myfLineId","myfLineId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        myfLineDBFields4[1] = new EntityDBFields("myfline","myfHeaderId","Νο ΜΥΦ",0,"java.lang.String",5, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        myfLineDBFields4[2] = new EntityDBFields("myfline","dbCompanyId","dbCompanyId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
       myfLineDBFields4[3] = new EntityDBFields("myfline","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        myfLineDBFields4[4] = new EntityDBFields("myfline","listTypeId","listTypeId",0,"java.lang.Integer",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"4","");
        myfLineDBFields4[5] = new EntityDBFields("myfline","afm","ΑΦΜ",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        myfLineDBFields4[6] = new EntityDBFields("myfline","name","συναλλασσόμενος",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields4[7] = new EntityDBFields("myfline","field3","field3",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields4[8] = new EntityDBFields("myfline","field4","field4",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //myfLineDBFields4[9] = new EntityDBFields("myfline","field5","field5",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        myfLineDBFields4[7] = new EntityDBFields("myfline","countOfDocs","πλήθος",0,"java.lang.Integer",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");           
        myfLineDBFields4[8] = new EntityDBFields("myfline","valueNet","προ ΦΠΑ",0,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        myfLineDBFields4[9] = new EntityDBFields("myfline","valueVat","ΦΠΑ",0,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        myfLineDBFields4[10] = new EntityDBFields("myfline","valueGross","μικτό",0,"java.lang.Double",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//fieldsCalculationService);//fieldsCalculationVatPercentValue);        
                
        
        
        myfHeaderDBFields[0] = new EntityDBFields("myfheader","myfHeaderId","Νο ΜΥΦ",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        myfHeaderDBFields[1] = new EntityDBFields("myfheader","myfTitle","περιγραφή",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");   
        myfHeaderDBFields[2] = new EntityDBFields("myfheader","myfForPeriodStartDate","ημερομηνία έναρξης περιόδου",0,"java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");  
        myfHeaderDBFields[3] = new EntityDBFields("myfheader","myfForPeriodEndDate","ημερομηνία λήξης περιόδου",0,"java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");  
        
        myfHeaderDBFields[4] = new EntityDBFields("myfheader","dbCompanyId","dbCompanyId",1,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
  
        myfHeaderDBFields[5] = new EntityDBFields("myfheader","dbYearId","dbYearId",1,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
 
        myfHeaderDBFields[6] = new EntityDBFields("myfheader","myfline","πωλήσεις",2,"table",FIELD_VISIBLE_AND_EDITABLE,"myfline",120,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,myfLineDBFields1,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM myfline WHERE myfline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND listTypeId LIKE 1 ORDER BY myfline.myflineId",null,childTableFieldsForSumsMyfHeader);        
        myfHeaderDBFields[7] = new EntityDBFields("myfheader","count1","πλήθος 1",3,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,8,DBFIELD_TYPE_OF_SUM_COUNT);        
        myfHeaderDBFields[8] = new EntityDBFields("myfheader","valueNet1","προ ΦΠΑ 1",3,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,9,DBFIELD_TYPE_OF_SUM_SUM);        
        myfHeaderDBFields[9] = new EntityDBFields("myfheader","valueVat1","ΦΠΑ 1",3,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,10,DBFIELD_TYPE_OF_SUM_SUM);        
        myfHeaderDBFields[10] = new EntityDBFields("myfheader","valueGross1","μικτό 1",3,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,11,DBFIELD_TYPE_OF_SUM_SUM);        
        
        myfHeaderDBFields[11] = new EntityDBFields("myfheader","myfline","πωλήσεις λιανικής",4,"table",FIELD_VISIBLE_AND_EDITABLE,"myfline",120,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,myfLineDBFields2,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM myfline WHERE myfline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND listTypeId LIKE 2 ORDER BY myfline.myflineId",null,childTableFieldsForSumsMyfHeader);        
        myfHeaderDBFields[12] = new EntityDBFields("myfheader","count2","πλήθος 2",5,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,7,DBFIELD_TYPE_OF_SUM_COUNT);        
        myfHeaderDBFields[13] = new EntityDBFields("myfheader","valueNet2","προ ΦΠΑ 2",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,8,DBFIELD_TYPE_OF_SUM_SUM);        
        myfHeaderDBFields[14] = new EntityDBFields("myfheader","valueVat2","ΦΠΑ 2",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,9,DBFIELD_TYPE_OF_SUM_SUM);        
        myfHeaderDBFields[15] = new EntityDBFields("myfheader","valueGross2","μικτό 2",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,10,DBFIELD_TYPE_OF_SUM_SUM);        
                
        myfHeaderDBFields[16] = new EntityDBFields("myfheader","myfline","αγορές δαπάνες",6,"table",FIELD_VISIBLE_AND_EDITABLE,"myfline",120,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,myfLineDBFields3,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM myfline WHERE myfline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND listTypeId LIKE 3 ORDER BY myfline.myflineId",null,childTableFieldsForSumsMyfHeader);        
         myfHeaderDBFields[17] = new EntityDBFields("myfheader","count3","πλήθος 3",7,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,8,DBFIELD_TYPE_OF_SUM_COUNT);        
        myfHeaderDBFields[18] = new EntityDBFields("myfheader","valueNet3","προ ΦΠΑ 3",7,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,9,DBFIELD_TYPE_OF_SUM_SUM);        
        myfHeaderDBFields[19] = new EntityDBFields("myfheader","valueVat3","ΦΠΑ 3",7,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,10,DBFIELD_TYPE_OF_SUM_SUM);        
        myfHeaderDBFields[20] = new EntityDBFields("myfheader","valueGross3","μικτό 3",7,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,11,DBFIELD_TYPE_OF_SUM_SUM);        
              
        myfHeaderDBFields[21] = new EntityDBFields("myfheader","myfline","λοιπές δαπάνες",8,"table",FIELD_VISIBLE_AND_EDITABLE,"myfline",120,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,myfLineDBFields4,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM myfline WHERE myfline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND listTypeId LIKE 4 ORDER BY myfline.myflineId",null,childTableFieldsForSumsMyfHeader);        
         myfHeaderDBFields[22] = new EntityDBFields("myfheader","count4","πλήθος 4",9,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,17,DBFIELD_TYPE_OF_SUM_COUNT);        
        myfHeaderDBFields[23] = new EntityDBFields("myfheader","valueNet4","προ ΦΠΑ 4",9,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,8,DBFIELD_TYPE_OF_SUM_SUM);        
        myfHeaderDBFields[24] = new EntityDBFields("myfheader","valueVat4","ΦΠΑ 4",9,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,9,DBFIELD_TYPE_OF_SUM_SUM);        
        myfHeaderDBFields[25] = new EntityDBFields("myfheader","valueGross4","μικτό 4",9,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,0,10,DBFIELD_TYPE_OF_SUM_SUM);        
         
        myfHeaderEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET,GROUP_OF_PANEL_VISIBLE);
        myfHeaderEntityGroupOfComps[1]= new EntityGroupOfComps("hidden",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE);       
        myfHeaderEntityGroupOfComps[2]= new EntityGroupOfComps("-",1,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// table1
        myfHeaderEntityGroupOfComps[3]= new EntityGroupOfComps("-",8,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// 4 count , 1 panel
        myfHeaderEntityGroupOfComps[4]= new EntityGroupOfComps("-",1,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// table 2
        myfHeaderEntityGroupOfComps[5]= new EntityGroupOfComps("-",8,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// 4 count , 1 panel
        myfHeaderEntityGroupOfComps[6]= new EntityGroupOfComps("-",1,3,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE); // table 3
        myfHeaderEntityGroupOfComps[7]= new EntityGroupOfComps("-",8,3,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// 4 count , 1 panel
        myfHeaderEntityGroupOfComps[8]= new EntityGroupOfComps("-",1,4,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// table 4
        myfHeaderEntityGroupOfComps[9]= new EntityGroupOfComps("-",8,4,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// 4 count , 1 panel
        
        myfHeaderEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        myfHeaderEntityGroupOfPanels[1] = new EntityGroupOfPanels("πωλήσεις",1);
        myfHeaderEntityGroupOfPanels[2] = new EntityGroupOfPanels("πωλήσεις λιανικής",1);
        myfHeaderEntityGroupOfPanels[3] = new EntityGroupOfPanels("αγορές δαπάνες",1);
        myfHeaderEntityGroupOfPanels[4] = new EntityGroupOfPanels("λοιπές δαπάνες",1);
        

       companySetEsoExoDBFields[0] = new EntityDBFields("dbCompanySet","dbCompanyId","Νο εταιρίας",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,VariablesGlobal.globalCompanyId);
        companySetEsoExoDBFields[1] = new EntityDBFields("dbCompanySet","esoexoCheckAFMOfEsoExoAndComp","ενημέρωση καταχώρησης εσόδων - εξόδων με συναλλασόμενο την εταιρία εργασίας",1,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        companySetEsoExoDBFields[2] = new EntityDBFields("dbCompanySet","esoexoMaxOfCashCheck","ενημέρωση για ποσό εξόφλησης μέσω τράπεζικού τρόπου",2,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        companySetEsoExoDBFields[3] = new EntityDBFields("dbCompanySet","esoexoMaxOfCashNetValue","ελαχιστο ποσό για εξόφληση μέσω τράπεζικού τρόπου",2,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"500","");        
        companySetEsoExoDBFields[4] = new EntityDBFields("dbCompanySet","esoexoCopyTraderNameToEsoexoComment","πρόταση επωνυμίας συναλλασσόμενου στην αιτιολογία καταχώρησης",3,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        
        companySetEsoExoEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE);   
        companySetEsoExoEntityGroupOfComps[1] = new EntityGroupOfComps("έλεγχοι",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE); 
        companySetEsoExoEntityGroupOfComps[2] = new EntityGroupOfComps("εξόφληση",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        companySetEsoExoEntityGroupOfComps[3] = new EntityGroupOfComps("προτιμήσεις",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);        
        
     //   entReportEsExDoc = new EntityReport("servicesaledoc", REPORT_CAT_2,reportBandtraderEsExDoc,"SELECT * FROM sxtrader, sxesoexoheader, esoexoline, sxaccount WHERE sxesoexoheader.traderId = sxtrader.traderId AND sxesoexoheader.esoexoheaderId = esoexoline.esoexoheaderId AND esoexoline.accountId = sxaccount.accountId",/*"ORDER BY sxtrader.name"*/"","FORM","εντυπα πωλήσεων","",salesDocumentErs,saleDocumentGroupOfComps,invoicesaSelected, null,fileOrderby,intSettingsReporttraderfile,boolSettingsReportDoc,globalYearPlusOne) ;
        
   }
   
   

   
   public void loadAllNodes()
   {
       addReportSettings();
       addEntityInfoNodes();
     //  addStatisticsNodes();
       addDocumentNodes();
       addEntitiesParameters();
       addToolNodes();       
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
        

        EntityMenu emCat3 = new EntityMenu();
        emCat3.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat3.setEntityCategory(DOCUMENTS,1,ICO_MENUCAT_DOCUMENT);//ICO_STATISTICS16);
        //if(isVisible[2])
        //{        
           nodeRoot.addChild(new DataTreeNode(emCat3));
        //}        
        
        
        
        
        EntityMenu emCat4 = new EntityMenu();
        emCat4.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat4.setEntityCategory(PARAMETERS,1,ICO_MENUCAT_EDIT);//ICO_TABLE16);
        if(isVisible[3])
        {
           nodeRoot.addChild(new DataTreeNode(emCat4));
        }


   /*     EntityMenu emCat3 = new EntityMenu();
        emCat3.setEntityType(ENTITY_TYPE_CATEGORY1);
        emCat3.setEntityCategory(METRICS,1,ICO_MENUCAT_EXPLORE);//ICO_STATISTICS16);
        if(isVisible[2])
        {        
           nodeRoot.addChild(new DataTreeNode(emCat3));
        }        
*/
        
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
        EntityFilterSettings[] esoexoErs = new EntityFilterSettings[11];       
        esoexoErs[0]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader",/*VariablesGlobal.globalYearId*/"",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoErs[1]=new EntityFilterSettings("αριθ. παρ/κού","","string","equals","esoexoCodeOfDocument","sxesoexoheader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoErs[2]=new EntityFilterSettings("τύπος","checkboxTable","string","","sxActionTypeId","sxactiontype","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoErs[3]=new EntityFilterSettings("συναλλασσόμενος","","string","equals","title","sxtrader","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoErs[4]=new EntityFilterSettings("ΑΦΜ συναλ.","","string","equals","vatNo","sxtrader","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoErs[5]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfEsoexo","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        //saleErs[6]=new EntityFilterSettings("ποσότητα","","double","fromto","quantityTotal","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoErs[6]=new EntityFilterSettings("τελική τιμή","","double","fromto","priceTotal","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoErs[7]=new EntityFilterSettings( "τύποι ΜΥΦ","checkboxTable","string","","myfCatId","sxmyftype","sxactiontype","",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoErs[8]=new EntityFilterSettings("κωδ. λογαρ.","","string","equals","accountCode","sxaccount",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoErs[9]=new EntityFilterSettings("ονομασία λογ.","","string","equals","accountDescr","sxaccount",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //accountErs[2]=new EntityFilterSettings("τύπος","checkboxTable","string","","accountCatId","accountcat","sxaccount","",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoErs[10]=new EntityFilterSettings("ΦΠΑ λογ.","checkboxTable","string","","vatCatId","vatcat","sxaccount","",-1,-1,-1,FIELD_NOCOMPLETION);        
        
       EntityGroupOfComps[] esoexoEntityGroupOfComps = null;
        
        int[] saleFieldsOrderby ={1,5,6};
        
        String[] fieldsForSumsSale = {"πλήθος","ποσότητα","προ φπα", "ΦΠΑ","σύνολο μετά ΦΠΑ"};
        
        // sxtrader.traderId AS \"Νο πελάτη\", 
                
        //EntityInfo pg = new EntityInfo("sxesoexoheader", "SELECT sxesoexoheader.esoexoheaderId AS \"Νο εσόδων εξόδων\",  sxactiontype.actionTypeCode AS \"τύπος παραστατικού\", sxesoexoheader.esoexoCodeOfDocument, sxesoexoheader.dateOfEsoexo AS \"ημερομηνία\", sxesoexoheader.traderId, sxtrader.title AS \"συναλλασσόμενος\", COUNT(esoexoline.inc) AS \"πλήθος\", SUM(esoexoline.priceBeforeVat)AS \"προ ΦΠΑ\",SUM(esoexoline.vatValue)AS \"ΦΠΑ\", SUM(esoexoline.valueWithVat)AS \"τελική τιμή\" FROM sxesoexoheader LEFT JOIN esoexoline ON sxesoexoheader.esoexoheaderId = esoexoline.esoexoheaderId , sxtrader, sxactiontype WHERE sxesoexoheader.traderId = sxtrader.traderId AND actionType.sxActionTypeId = sxesoexoheader.sxActionTypeId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxtrader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND actionType.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" GROUP BY sxesoexoheader.esoexoheaderId ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoheaderId"  ,"SELECT sxesoexoheader.esoexoheaderId AS \"Νο εσόδων εξόδων\", sxesoexoheader.sxActionTypeId , sxesoexoheader.salCodeNo, sxesoexoheader.esoexoCodeOfDocument, sxesoexoheader.dateOfEsoexo, sxesoexoheader.traderId, sxesoexoheader.wayOfPayment","FROM sxesoexoheader","WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId ,null,fieldsForSumsSale,esoexoHeaderDBFields,"πωλήσεις","DORM","","Νο εσόδων εξόδων","esoexoheaderId",saleErs,esoexoEntityGroupOfComps,"εσόδων εξόδων","πωλήσεων",strSaleCategories,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex,saleFieldsOrderby,-1,-1,globalYearPlusOne);    AND sxesoexoheader.dbYearId in ("+VariablesGlobal.globalYearId+")
        EntityInfo pg = new EntityInfo("sxesoexoheader", "SELECT sxesoexoheader.esoexoheaderId AS \"Νο εσόδων εξόδων\", sxactiontype.actionTypeCode AS \"τύπος\", sxactiontype.myfCatId AS \"ΜΥΦ\", sxesoexoheader.esoexoCodeOfDocument AS \"αριθ. παρ.\", sxesoexoheader.dateOfEsoexo AS \"ημερομηνία\", sxesoexoheader.comments AS \"αιτιολογία\" ,sxtrader.vatNo AS \"Α.Φ.Μ.\",  sxtrader.title AS \"συναλλασσόμενος\", sxesoexoheader.countTotal AS \"πλήθος\", sxesoexoheader.pricePreVat AS \"προ ΦΠΑ\",sxesoexoheader.priceVat AS \"ΦΠΑ\", sxesoexoheader.priceTotal AS \"σύνολο μετά ΦΠΑ\",sxesoexoheader.isTemplate  "
             //   + " FROM sxaccount, sxactiontype RIGHT JOIN sxesoexoheader ON sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId LEFT JOIN sxtrader ON sxesoexoheader.traderId = sxtrader.traderId INNER JOIN sxesoexoline ON sxesoexoline.esoexoHeaderId = sxesoexoheader.esoexoHeaderId "
             //   + " WHERE sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId AND  sxesoexoheader.dbCompanyId = sxesoexoline.dbCompanyId AND sxaccount.accountId = sxesoexoline.accountId AND sxesoexoheader.isTemplate = sxesoexoline.isTemplate AND sxesoexoheader.isTemplate='0' AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoheaderId"  ,"SELECT sxesoexoheader.esoexoheaderId AS \"Νο εσόδων εξόδων\", sxesoexoheader.sxActionTypeId , sxesoexoheader.esoexoCodeOfDocument, sxesoexoheader.dateOfEsoexo, sxesoexoheader.traderId, sxesoexoheader.wayOfPayment","FROM sxesoexoheader","WHERE sxesoexoheader.isTemplate ='0' AND dbCompanyId LIKE "+VariablesGlobal.globalCompanyId ,null,fieldsForSumsSale,"εγγραφές εσόδων εξόδων","DORM","","Νο εσόδων εξόδων","esoexoheaderId",/*"sxactiontype"/*formGlobalTable1*//*,"sxaccount"/*formGlobalTableToApply1*//*"sxActionTypeId"*//*this table, formGlobalField1,*/esoexoErs,esoexoEntityGroupOfComps,"εσόδων εξόδων","εσόδων εξόδων",strSaleCategories,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex,saleFieldsOrderby,5/*AFM column*/,FIELD_VALIDATION_AFM,entReportEsExDoc,globalYearPlusOne);

         + " FROM sxaccount, sxactiontype, sxesoexoheader  LEFT JOIN sxtrader ON sxesoexoheader.traderId = sxtrader.traderId, sxesoexoline" 
         + " WHERE sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId AND sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId AND  sxesoexoheader.esoexoHeaderId = sxesoexoline.esoexoHeaderId AND  sxesoexoheader.dbCompanyId = sxesoexoline.dbCompanyId AND sxaccount.accountId = sxesoexoline.accountId AND sxesoexoheader.isTemplate = sxesoexoline.isTemplate AND sxesoexoheader.isTemplate='0' AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" GROUP BY sxesoexoheader.esoexoHeaderId ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoheaderId"  ,"SELECT sxesoexoheader.esoexoheaderId AS \"Νο εσόδων εξόδων\", sxesoexoheader.sxActionTypeId , sxesoexoheader.esoexoCodeOfDocument, sxesoexoheader.dateOfEsoexo, sxesoexoheader.traderId, sxesoexoheader.wayOfPayment","FROM sxesoexoheader","WHERE sxesoexoheader.isTemplate ='0' AND dbCompanyId LIKE "+VariablesGlobal.globalCompanyId ,null,fieldsForSumsSale,"εγγραφές εσόδων εξόδων","DORM","","Νο εσόδων εξόδων","esoexoheaderId",/*"sxactiontype"/*formGlobalTable1*//*,"sxaccount"/*formGlobalTableToApply1*//*"sxActionTypeId"*//*this table, formGlobalField1,*/esoexoErs,esoexoEntityGroupOfComps,"εσόδων εξόδων","εσόδων εξόδων",strSaleCategories,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex,saleFieldsOrderby,6/*AFM column*/,FIELD_VALIDATION_AFM,entReportEsExDoc,globalYearPlusOne);
                
                
        EntityMenu empg = new EntityMenu();
        empg.setEntityInfo(pg,ICO_ESODAEXODA);
        empg.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeempg = new DataTreeNode(empg);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeempg);        
        
   //----------------------------------------------------------------------      
      
      
      
      
      
      
      
      

       EntityFilterSettings[] traderErs = new EntityFilterSettings[3];       
       //traderErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","sxtrader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[0]=new EntityFilterSettings("όνομα","","string","equals","title","sxtrader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[3]=new EntityFilterSettings("ταυτότητα","","string","equals","idNo","sxtrader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","sxtrader","",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","sxtrader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[3]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[4]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfEsoexo","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
                             //onelookup,checkboxTable
       EntityGroupOfComps[] traderEntityGroupOfComps = null;
       
       int[] traderFieldsOrderBy ={2,3,4,5};
        // if fields change, change them and at lookup entities
       String[] fieldsForSumstrader = {"ποσότητα","προ φπα", "φπα","σύνολο"};
       // , sum(quantityTotal) AS \"ποσότητα\", sum(pricePreVat) AS \"προ ΦΠΑ\", sum(priceVat) AS \"ΦΠΑ\", sum(priceTotal) AS \"σύνολο\"    LEFT JOIN sxesoexoheader ON sxesoexoheader.traderId = sxtrader.traderId
       EntityInfo ia = new EntityInfo("sxtrader", "SELECT sxtrader.traderId AS \"Νο πελάτη\", sxtrader.title AS \"επωνυμία\",sxtrader.traderCode  AS \"κωδ. πελάτη\", sxtrader.vatNo AS \"Α.Φ.Μ.\", activitycat.activityDescr AS \"κατηγορία δραστηριότητας\", sxtrader.doyId AS \"Νο Δ.Ο.Υ.\", doy.doyName AS \"ονομασία Δ.Ο.Υ.\", sxtrader.active  FROM sxtrader LEFT JOIN doy ON sxtrader.doyId=doy.doyId  LEFT JOIN activitycat ON activitycat.activityCatId = sxtrader.activityCatId ORDER BY sxtrader.title" ,"SELECT sxtrader.traderId AS \"Νο πελάτη\", sxtrader.title AS \"επωνυμία\", sxtrader.vatNo AS \"Α.Φ.Μ.\", sxtrader.doyId","FROM sxtrader LEFT JOIN doy ON sxtrader.doyId=doy.doyId "/*LEFT JOIN town ON sxtrader.townId=town.townId"*/," ",null, fieldsForSumstrader ,"συναλλασόμενοι","DORM","","Νο πελάτη","traderId"/*,"",""*/,traderErs,traderEntityGroupOfComps ,"πελάτη","πελατών",strtraderCategories,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader,traderFieldsOrderBy,3,FIELD_VALIDATION_AFM,null,globalYearPlusOne);
        EntityMenu emia = new EntityMenu();
        emia.setEntityInfo(ia,ICO_FARMER16);
        emia.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeemia = new DataTreeNode(emia);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemia);

 


     //------------------------------------------------------------
      
                EntityFilterSettings[] accountErs = new EntityFilterSettings[3];     
        accountErs[0]=new EntityFilterSettings("κωδικός","","string","equals","accountCode","sxaccount",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        accountErs[1]=new EntityFilterSettings("ονομασία","","string","equals","accountDescr","sxaccount",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //accountErs[2]=new EntityFilterSettings("τύπος","checkboxTable","string","","accountCatId","accountcat","sxaccount","",-1,-1,-1,FIELD_NOCOMPLETION);
        accountErs[2]=new EntityFilterSettings("ΦΠΑ","checkboxTable","string","","vatCatId","vatcat","sxaccount","",-1,-1,-1,FIELD_NOCOMPLETION);
        //serviceErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoline","",-1,-1,-1,FIELD_NOCOMPLETION);
        
        
       EntityGroupOfComps[] sxAccountEntityGroupOfComps = null;
        
        int[] serviceFieldsOrderby ={2,3};
        
        String[] fieldsForSumsService = {"ποσότητα","προ φπα", "φπα","σύνολο"};
        
        //     sum(esoexoline.quantity) AS \"ποσότητα\", sum(esoexoline.priceBeforeVat) AS \"προ ΦΠΑ\", sum(esoexoline.vatValue) AS \"ΦΠΑ\", sum(esoexoline.valueWithVat) AS \"σύνολο\"   LEFT JOIN esoexoline ON esoexoline.accountId = sxaccount.accountId 
        EntityInfo pf = new EntityInfo("sxaccount", "SELECT sxaccount.accountId AS \"Νο λογαριασμού\", sxaccount.accountCode AS \"κωδ. λογαρασμού\", sxaccount.accountDescr AS \"περιγραφή\",  vatcat.vatDescr, lookupconstants.name,  sxaccount.active,  sxaccount.vatExempt, sxaccount.vatCalculate, sxaccount.participatesInMYF, sxaccount.vatCatId, sxaccount.vatDocCode, sxaccount.vatDocCodeVat, sxaccount.taxDocE3Code  FROM sxaccount LEFT JOIN vatcat ON vatcat.vatCatId = sxaccount.vatCatId INNER JOIN lookupconstants ON sxaccount.accountCatId = lookupconstants.lookupconstantsId WHERE lookupconstants.constantstypeId = 4 ORDER BY sxaccount.accountCode , sxaccount.accountDescr"  ,"SELECT sxaccount.accountId AS \"Νο λογαριασμού\", sxaccount.accountDescr AS \"περιγραφή\"","FROM sxaccount","",null,fieldsForSumsService,"λογαριασμοί","DORM","","Νο λογαριασμού","accountId",/*""/*formGlobalTable1*//*,""/*formGlobalTableToApply1,*/accountErs,sxAccountEntityGroupOfComps,"λογαριασμός","λογαριασμών",strSXAccountCategories,entityPanelSXAccount,fieldsOnTitleSXAccount,fieldsOnTitleCaptionSXAccount,serviceFieldsOrderby,-1,-1,null,globalYearPlusOne);
        EntityMenu empf = new EntityMenu();
        empf.setEntityInfo(pf,ICO_TABLE16);
        empf.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeempf = new DataTreeNode(empf);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeempf);
        
        
     //------------------------------------------------------------
        EntityFilterSettings[] esoexoTempErs = new EntityFilterSettings[1];
        //esoexoTempErs[0]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader",/*VariablesGlobal.globalYearId*/"",-1,-1,-1,FIELD_NOCOMPLETION);
        //esoexoTempErs[1]=new EntityFilterSettings("αριθ. παρ/κού","","string","equals","esoexoCodeOfDocument","sxesoexoheader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        esoexoTempErs[0]=new EntityFilterSettings("τύπος","checkboxTable","string","","sxActionTypeId","sxactiontype","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        //esoexoTempErs[3]=new EntityFilterSettings("συναλλασσόμενος","","string","equals","name","sxtrader","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        //esoexoTempErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        //esoexoTempErs[5]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfEsoexo","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        //saleErs[6]=new EntityFilterSettings("ποσότητα","","double","fromto","quantityTotal","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        //esoexoTempErs[6]=new EntityFilterSettings("τελική τιμή","","double","fromto","priceTotal","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);



        
        
       EntityGroupOfComps[] esoexoTempEntityGroupOfComps = null;
        
        int[] esoexoTempFieldsOrderby ={1,5,6};
        
        String[] fieldsForSumsEsoexoTemp = null;//{"πλήθος","ποσότητα","προ φπα", "ΦΠΑ","σύνολο μετά ΦΠΑ"};

        // sxtrader.traderId AS \"Νο πελάτη\", 
                
        //EntityInfo pg = new EntityInfo("sxesoexoheader", "SELECT sxesoexoheader.esoexoheaderId AS \"Νο εσόδων εξόδων\",  sxactiontype.actionTypeCode AS \"τύπος παραστατικού\", sxesoexoheader.esoexoCodeOfDocument, sxesoexoheader.dateOfEsoexo AS \"ημερομηνία\", sxesoexoheader.traderId, sxtrader.name AS \"συναλλασσόμενος\", COUNT(esoexoline.inc) AS \"πλήθος\", SUM(esoexoline.priceBeforeVat)AS \"προ ΦΠΑ\",SUM(esoexoline.vatValue)AS \"ΦΠΑ\", SUM(esoexoline.valueWithVat)AS \"τελική τιμή\" FROM sxesoexoheader LEFT JOIN esoexoline ON sxesoexoheader.esoexoheaderId = esoexoline.esoexoheaderId , sxtrader, sxactiontype WHERE sxesoexoheader.traderId = sxtrader.traderId AND actionType.sxActionTypeId = sxesoexoheader.sxActionTypeId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxtrader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND actionType.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" GROUP BY sxesoexoheader.esoexoheaderId ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoheaderId"  ,"SELECT sxesoexoheader.esoexoheaderId AS \"Νο εσόδων εξόδων\", sxesoexoheader.sxActionTypeId , sxesoexoheader.salCodeNo, sxesoexoheader.esoexoCodeOfDocument, sxesoexoheader.dateOfEsoexo, sxesoexoheader.traderId, sxesoexoheader.wayOfPayment","FROM sxesoexoheader","WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId ,null,fieldsForSumsSale,esoexoHeaderDBFields,"πωλήσεις","DORM","","Νο εσόδων εξόδων","esoexoheaderId",saleErs,esoexoEntityGroupOfComps,"εσόδων εξόδων","πωλήσεων",strSaleCategories,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex,saleFieldsOrderby,-1,-1,globalYearPlusOne);    AND sxesoexoheader.dbYearId in ("+VariablesGlobal.globalYearId+")
        EntityInfo pgt = new EntityInfo("sxesoexoheader", "SELECT sxesoexoheader.esoexoheaderId AS \"Νο προτύπου εσόδων εξόδων\", sxesoexoheader.titleOfTemplate AS\"περιγραφή προτύπου εσόδων εξόδων\", sxactiontype.actionTypeCode AS \"τύπος\", sxesoexoheader.esoexoCodeOfDocument AS \"προθ. παρ.\", sxesoexoheader.isTemplateActive AS \"ενεργό\", sxesoexoheader.comments AS \"αιτιολογία\" ,sxtrader.vatNo AS \"Α.Φ.Μ.\",  sxtrader.title AS \"συναλλασσόμενος\", sxesoexoheader.countTotal AS \"πλήθος\", sxesoexoheader.pricePreVat AS \"προ ΦΠΑ\",sxesoexoheader.priceVat AS \"ΦΠΑ\", sxesoexoheader.priceTotal AS \"σύνολο μετά ΦΠΑ\", sxesoexoheader.isTemplate FROM sxactiontype RIGHT JOIN sxesoexoheader ON sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId LEFT JOIN sxtrader ON sxesoexoheader.traderId = sxtrader.traderId "
                + "WHERE sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId AND sxesoexoheader.isTemplate ='1' AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoheaderId"  ,"SELECT sxesoexoheader.esoexoheaderId AS \"Νο προτύπου εσόδων εξόδων\", sxesoexoheader.sxActionTypeId , sxesoexoheader.esoexoCodeOfDocument, sxesoexoheader.dateOfEsoexo, sxesoexoheader.traderId, sxesoexoheader.wayOfPayment","FROM sxesoexoheader","WHERE sxesoexoheader.isTemplate ='1' AND dbCompanyId LIKE "+VariablesGlobal.globalCompanyId ,null,fieldsForSumsEsoexoTemp,"πρότυπα εσόδων εξόδων","DORM","","Νο προτύπου εσόδων εξόδων","esoexoheaderId"/*,"sxactiontype"/*formGlobalTable1*//*,"sxaccount"/*formGlobalTableToApply1*//*"sxActionTypeId"*//*this table, formGlobalField1*/,esoexoTempErs,esoexoTempEntityGroupOfComps,"πρότυπο εσόδων εξόδων","προτύπων εσόδων εξόδων",strSaleCategories,entityPanelEsexTemp,fieldsOnTitleEsexTemp,fieldsOnTitleCaptionEsexTemp,esoexoTempFieldsOrderby,5/*AFM column*/,FIELD_VALIDATION_AFM,entReportEsExDoc,globalYearPlusOne);
        EntityMenu empgt = new EntityMenu();
        empgt.setEntityInfo(pgt,ICO_ESOEXOTEMPLATE);
        empgt.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeempgt = new DataTreeNode(empgt);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeempgt);        
        
   //----------------------------------------------------------------------          
        
        
        
        
        
  }

  
   public ArrayList addEntitiesLookupTableConstants(ArrayList  <EntityLookupTableConstants> listEntityLookupTableConstants)
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

       /*EntityLookupTableConstantsData [] luTCDataVatExclusion = new EntityLookupTableConstantsData[3];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataVatExclusion[0]=new EntityLookupTableConstantsData("1",1,"κανονικό");
       luTCDataVatExclusion[1]=new EntityLookupTableConstantsData("2",2,"μειωμένο");
       luTCDataVatExclusion[2]=new EntityLookupTableConstantsData("3",3,"απαλλασσόμενο");*/
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCVatExclusion",null));    


       
       /*EntityLookupTableConstantsData [] luTCDataMyfTypeCat = new EntityLookupTableConstantsData[5];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataMyfTypeCat[0]=new EntityLookupTableConstantsData("1",1,"χωρίς");
       luTCDataMyfTypeCat[1]=new EntityLookupTableConstantsData("2",2,"πωλήσεις");
       luTCDataMyfTypeCat[2]=new EntityLookupTableConstantsData("3",3,"πωλήσεις λιανικής");
       luTCDataMyfTypeCat[3]=new EntityLookupTableConstantsData("4",4,"αγορές δαπάνες");
       luTCDataMyfTypeCat[4]=new EntityLookupTableConstantsData("5",5,"λοιπές δαπάνες");*/
       
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCMyfTypeCat",null));    // if null decide by name from db in EntityLookupTableConstants   

       /*
       *  dont forget on new entitydata'module' to add in EntityData.addEntitiesLookupTableConstants the EsoExo or a new module
       */       
       /* EntityLookupTableConstantsData [] luTCDataTypeCat = new EntityLookupTableConstantsData[4];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataTypeCat[0]=new EntityLookupTableConstantsData("1",1,"έσοδα");
       luTCDataTypeCat[1]=new EntityLookupTableConstantsData("2",2,"παγια");
       luTCDataTypeCat[2]=new EntityLookupTableConstantsData("3",3,"αγορές");
       luTCDataTypeCat[3]=new EntityLookupTableConstantsData("4",4,"δαπάνες");*/
       
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCTypeCat",null));           
       
       

      /* EntityLookupTableConstantsData [] luTCDataImportsExports = new EntityLookupTableConstantsData[3];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataImportsExports[0]=new EntityLookupTableConstantsData("1",1,"Ελλάδα");
       luTCDataImportsExports[1]=new EntityLookupTableConstantsData("2",2,"Ευρωπαϊκή Ένωση");
       luTCDataImportsExports[2]=new EntityLookupTableConstantsData("3",3,"τρίτες χώρες");
       //luTCDataImportsExports[3]=new EntityLookupTableConstantsData("4",4,"δαπάνες");*/
       
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCImportsExports",null));           
       
       
       
       /*EntityLookupTableConstantsData [] luTCDataMyfPeriod = new EntityLookupTableConstantsData[4];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataMyfPeriod[0]=new EntityLookupTableConstantsData("1",1,"Α τρίμηνο");
       luTCDataMyfPeriod[1]=new EntityLookupTableConstantsData("2",2,"Β τρίμηνο");
       luTCDataMyfPeriod[2]=new EntityLookupTableConstantsData("3",3,"Γ τρίμηνο");
       luTCDataMyfPeriod[3]=new EntityLookupTableConstantsData("4",4,"Δ τρίμηνο");   */    
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCMyfPeriod",null));   
       
       
       
       
       return listEntityLookupTableConstants;
   }
  
  
   public ArrayList addEntitiesLookup(ArrayList entities)
   { 
       // LOOKUPTYPE_ONLYONE_THISFIELD
     EntityLookUp entityLookUp;

     
     // make entities for all tables called with foreign keys
     
       EntityFilterSettings[] traderErs = new EntityFilterSettings[3];      
       traderErs[0]=new EntityFilterSettings("κωδικός","","string","equals"," traderCode","sxtrader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[1]=new EntityFilterSettings("επωνυμία","","string","equals","title","sxtrader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[2]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[2]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","sxtrader","",-1,-1,-1,FIELD_NOCOMPLETION);
            // for 'intNoOfColsWhenInTable' look also at  deliveryFieldsMany
      
     
     String[] lookUpFieldtrader={"title","vatNo"};
            
     entities.add(entityLookUp = new EntityLookUp("sxtrader","sxtrader","SELECT sxtrader.traderId AS\"Νο συναλλασόμενου\", traderCode AS\"κωδικός\", title AS\"επωνυμία\",  vatNo AS\"Α.Φ.Μ.\" FROM sxtrader","WHERE sxtrader.active LIKE 1","AND sxtrader.active LIKE 1", "ORDER BY sxtrader.title","","traderId","Νο συναλλασόμενου","traderId","συναλλασσόμενος",3,lookUpFieldtrader,"επωνυμία ή ΑΦΜ",29,"java.lang.String",4,"vatNo", "Α.Φ.Μ.",0,null,null,traderQueryEditable, "συναλλασόμενου","συναλλασομένων",strtraderCategories,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader,traderErs,2,2,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));

      entities.add(entityLookUp = new EntityLookUp("trader1Col","sxtrader","SELECT sxtrader.traderId AS\"Νο συναλλασόμενου\", traderCode AS\"κωδικός\", title AS\"επωνυμία\",  vatNo AS\"Α.Φ.Μ.\" FROM sxtrader","WHERE sxtrader.active LIKE 1","AND sxtrader.active LIKE 1", "ORDER BY sxtrader.title","","traderId","Νο συναλλασόμενου","traderId","συναλλασσόμενος",2,lookUpFieldtrader,"επωνυμία ή ΑΦΜ",29,"java.lang.String",0,null,null,0,null,null,traderQueryEditable, "συναλλασόμενου","συναλλασομένων",strtraderCategories,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader,traderErs,2,1,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));
     
     
     //------------------------------------------------------------------ 
        
              EntityFilterSettings[] accountErs = new EntityFilterSettings[3];     
        accountErs[0]=new EntityFilterSettings("κωδικός","","string","equals","accountCode","sxaccount",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        accountErs[1]=new EntityFilterSettings("ονομασία","","string","equals","accountDescr","sxaccount",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //accountErs[2]=new EntityFilterSettings("τύπος","checkboxTable","string","","accountCatId","accountcat","sxaccount","",-1,-1,-1,FIELD_NOCOMPLETION);
        accountErs[2]=new EntityFilterSettings("ΦΠΑ","checkboxTable","string","","vatCatId","vatcat","sxaccount","",-1,-1,-1,FIELD_NOCOMPLETION);
        //serviceErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoline","",-1,-1,-1,FIELD_NOCOMPLETION);
        
        
        
     String[] lookUpFieldAccount={"accountCode","accountDescr"};                   
       // , priceWhole AS \"τιμή\"  ,  sum(esoexoline.quantity) AS \"ποσότητα\", sum(esoexoline.priceBeforeVat) AS \"προ ΦΠΑ\", sum(esoexoline.vatValue) AS \"ΦΠΑ\", sum(esoexoline.valueWithVat) AS \"σύνολο\"          LEFT JOIN esoexoline ON esoexoline.accountId = sxaccount.accountId                                                                                                                                                                                                                                                                                                                       
     entities.add(entityLookUp = new EntityLookUp("sxaccount","sxaccount","SELECT sxaccount.accountId AS\"Νο λογαριασμού\", sxaccount.accountCode AS\"κωδ. λογαριασμού\", sxaccount.accountDescr AS \"ονομασία\", lookupconstants.name,  vatcat.vatDescr FROM sxaccount LEFT JOIN vatcat ON vatcat.vatCatId = sxaccount.vatCatId INNER JOIN lookupconstants ON sxaccount.accountCatId = lookupconstants.lookupconstantsId"," WHERE lookupconstants.constantstypeId = 4 ","AND sxaccount.active LIKE 1"," ORDER BY sxaccount.accountCode "," WHERE lookupconstants.constantstypeId = 4 AND sxaccount.accountCatId LIKE (SELECT sxActionTypeCatId FROM sxactiontype WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxactiontype.sxActionTypeId LIKE  "/*the closing parenthesis is added in PanelODMRData.displayDialogLookUp*/,"accountId","Νο λογαριασμού","accountId","λογαριασμός",2,lookUpFieldAccount,"κωδικός ή ονομασία",12,"java.lang.String",0,null,null,0,null,null,sxaccountQueryEditable,"λογαριασμού","λογαριασμών",null,entityPanelSXAccount,fieldsOnTitleSXAccount,fieldsOnTitleCaptionSXAccount,accountErs,2,1,null,true,-1,-1,null));    	 	

     //entities.add(entityLookUp = new EntityLookUp("sxaccountnofilter","sxaccount","SELECT sxaccount.accountId AS\"Νο λογαριασμού\", sxaccount.accountCode AS\"κωδ. λογαριασμού\", sxaccount.accountDescr AS \"ονομασία\", sxaccount.accountCatId AS \"Νο κατηγορίας\",  vatcat.vatDescr FROM sxaccount LEFT JOIN vatcat ON vatcat.vatCatId = sxaccount.vatCatId ","WHERE sxaccount.active LIKE 1","sxaccount.active LIKE 1"," ORDER BY sxaccount.accountDescr ","accountId","Νο λογαριασμού","accountId","λογαριασμός",2,lookUpFieldService,"ονομασία",12,"java.lang.String",0,null,null,0,null,null,sxaccountQueryEditable,"λογαριασμού","λογαριασμών",null,entityPanelSXAccount,fieldsOnTitleSXAccount,fieldsOnTitleCaptionSXAccount,accountErs,2,1,null,true,-1,-1,null));    	 	

          
//---------------------------------------------------------------------------    
    
        EntityFilterSettings[] saleErs = new EntityFilterSettings[2];       
        saleErs[0]=new EntityFilterSettings("ονομασία","","string","equals","esoexoCodeOfDocument","sxesoexoheader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","sxActionTypeId","sxactiontype","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
           
     String[] lookUpFieldSale={"esoexoCodeOfDocument"};                   
    
     entities.add(entityLookUp = new EntityLookUp("sxesoexoheader","sxesoexoheader","SELECT sxesoexoheader.esoexoCodeOfDocument, sxesoexoheader.sxActionTypeId, sxesoexoheader.traderId  FROM sxesoexoheader ","WHERE sxesoexoheader.dbCompanyId LIKE "+ VariablesGlobal.globalCompanyId,"","ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoheaderId ","","esoexoheaderId","Νο εσόδων εξόδων","esoexoheaderId","έσοδα έξοδα",3,lookUpFieldSale,"κωδ. παραστατικού",15,"java.lang.String",0,null,null,0,null,null,saleQueryEditable,"εσόδων εξόδων","πωλήσεων",null,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex,saleErs,2,1,null,true,-1,-1,null));    	 	

 
     //------------------------------------------------------------------ 

         EntityFilterSettings[] printFormErs = new EntityFilterSettings[1];       
        printFormErs[0]=new EntityFilterSettings("ονομασία","","string","equals","printFormName","printform",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","sxActionTypeCatId","servicecat","sxaccount","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        
        String[] lookUpFieldPrintForm={"printFormName"};                   
    
     entities.add(entityLookUp = new EntityLookUp("printform","printform","SELECT printform.printformId AS\"Νο φόρμας\", printform.printformName AS \"φόρμα εκτύπωσης\"  FROM printform","WHERE printform.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"AND printform.isActive LIKE 1","ORDER BY printform.printformName ","","printformId","Νο φόρμας","printform","φόρμα",2,lookUpFieldPrintForm,"ονομασία",33,"java.lang.String",0,null,null,0,null,null,printFormQueryEditable,"φόρμας εκτύπωσης","φορμών εκτύπωσης",null,entityPanelPrintForm,fieldsOnTitlePrintForm,fieldsOnTitleCaptionPrintForm,printFormErs,2,1,null,true,-1,-1,null));    	 	
               
     
   //----------------------------------------------------------------  
     
     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
   /*  String[] lookUpFieldBank={"bankBranch"};
     entities.add(entityLookUp = new EntityLookUp("bank","bank","SELECT bank.bankId AS\"Νο τράπεζας\", bank.bankBranch AS \"τίτλος τράπεζας\", BIC FROM bank","","","ORDER BY bank.bankBranch","","bankId","Νο τράπεζας","bankId","τράπεζα",2,lookUpFieldBank,"τίτλος τράπεζας",18,"java.lang.String",0,null,null,0,null,null,bankQueryEditable,"τράπεζας","τραπεζών",null,entityPanelBank,fieldsOnTitleBank,fieldsOnTitleCaptionBank,null,2,1,null,true,-1,-1,null));    	 		
   */  
      
     String[] lookUpFieldDbCompany={"title"};
     entities.add(entityLookUp = new EntityLookUp("dbcompany","dbcompany","SELECT dbCompanyId AS \"νο\", title AS \"επωνυμία\", companyVatNo AS \"ΑΦΜ\" FROM dbcompany","","", "ORDER BY title","","dbCompanyId","νο","dbCompanyId","εταιρία",2,lookUpFieldDbCompany,"τίτλος εταιρίας",15,"java.lang.String",0,null,null,0,null,null,dbCompanyQueryEditable,"της εταιρίας","εταιριών",null,entityPanelDbCompany,fieldsOnTitleDbCompany,fieldsOnTitleCaptionDbCompany,null,2,1,null,true,2,FIELD_VALIDATION_AFM,null));     

     return entities;
   }
  
     public void addEntitiesParameters()  // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
    {  


        //------------------------------------------------------------
       int[] incomeSettFieldsOrderby ={2};
       String[] fieldsForSumsIncomeSett=null;
       EntityParameter pb = new EntityParameter("sxincomedocsettings", "SELECT incomeDocSettingsId AS \"Νο\" FROM sxincomedocsettings ORDER BY incomeDocSettingsId" ,"SELECT incomeDocSettingsId AS \"Νο\" ","FROM sxincomedocsettings","",fieldsForSumsIncomeSett,incomeSettDBFields,"εισοδήματα","DORM","Νο","incomeDocSettingsId", null,null,"εισόδημα", "εισοδημάτων",entityPanelIncomeSett,null,fieldsOnTitleIncomeSett,fieldsOnTitleCaptionIncomeSett,incomeSettFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empb = new EntityMenu();
        empb.setEntityParameter(pb,ICO_TABLE16);
        empb.setEntityType(ENTITY_TYPE_DATAMANY_PARAMETERS);//ENTITY_TYPE_PARAMETER);
        //DataTreeNode nodeempb = new DataTreeNode(empb);
        //nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempb);
        

        EntityParameter[] arrayPh = {pb};
        EntityMenu[] arrayEmpz = {empb};

        EntityManyDataManyRec empn = new EntityManyDataManyRec("sxincomedocsettings", "εισοδήματα",arrayPh,arrayEmpz);

        
        EntityMenu empo = new EntityMenu();
        empo.setEntityManyDataManyRec(empn,ICO_TABLE16);
        empo.setEntityType(ENTITY_TYPE_DATAMANY_PARAMETERS);
        DataTreeNode nodeempo = new DataTreeNode(empo);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempo);
        
        //------------------------------------------------------------
      
      
         
        

       //------------esoexo prefs---------------------------------------------------------------------------------------
     int[] companySetEsoExoFieldsOrderby ={2};
       String[] companySetEsoExoFieldsForSums=null;
       EntityParameter pr = new EntityParameter("dbcompanyset", "SELECT dbCompanyId AS \"Νο\" FROM dbcompanyset ORDER BY dbCompanyId" ,"SELECT dbCompanyId AS \"Νο\" ","FROM dbcompanyset","",companySetEsoExoFieldsForSums,companySetEsoExoDBFields,"ρυθμ. εσόδων εξόδων","DORO","Νο","dbcompanyid", null,null,"εσόδων εξόδων", "εσόδων εξόδων",companySetEsoExoEntityPanel,null,companySetEsoExoFieldsOnTitle,companySetEsoExoFieldsOnTitleCaption,companySetEsoExoFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empr = new EntityMenu();
        empr.setEntityParameter(pr,ICO_SETTINGS);
        empr.setEntityType(ENTITY_TYPE_PARAMETER);//ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempr = new DataTreeNode(empr);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempr);           
         //------------------------------------------------------------
         
        int[] printFormFieldsOrderby ={2};
        String[] fieldsForSumsPrintForm=null;
        EntityParameter ppf = new EntityParameter("printform", "SELECT printFormId AS \"Νο φόρμας\",  printFormName AS \"ονομασία\", isActive FROM printform WHERE printform.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"SELECT printFormId AS \"Νο φόρμας\",  printFormName AS \"ονομασία\", isActive","FROM printform","WHERE printform.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,fieldsForSumsPrintForm,printFormDBFields ,"φόρμες για εκτύπωση","DORM","Νο φόρμας","printFormId",null,null,"φόρμας", "φορμών",entityPanelPrintForm,null,fieldsOnTitlePrintForm,fieldsOnTitleCaptionPrintForm,printFormFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu emppf = new EntityMenu();
        emppf.setEntityParameter(ppf,ICO_TABLE16);
        emppf.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeemppf = new DataTreeNode(emppf);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeemppf);   
        
         //------------------------------------------------------------         
         
         
 
        
    }


    public void addReportSettings()  // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
    {  
      DataTreeNode nodeCat = null;
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

         
         EntityMenu emCat3 = new EntityMenu();
         emCat3.setEntityCategory(REPORT_CAT_3,ENTITY_TYPE_CATEGORY2,ICO_MENUCAT_REPORT);
        // System.out.println("EntityData.addReportSettings "+REPORTS_CAT_ARRAY[r]+" "+r);
         nodeCat = new DataTreeNode(emCat3);
         nodeRoot.getChildFromCaption(REPORTS).addChild(nodeCat);
         nodeReports = nodeRoot.getChildFromCaption(REPORTS);       
         
         
       //-----------------------------------  REPORT_CAT_1 -----------------	
       EntityFilterSettings[] traderErs = new EntityFilterSettings[4];   
       //traderErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","sxtrader",VariablesGlobal.globalCompanyId,0,-1,-1,FIELD_OBLIGATORY);
       //invoiceErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbyearId","dbyear","sxtrader", VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
       //invoiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       traderErs[0]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","traderId","sxtrader","sxtrader","",0,-1,-1,FIELD_NOCOMPLETION);
       traderErs[1]=new EntityFilterSettings("επίθετο","","string","equals","title","sxtrader",null,"",0,-1,-1,FIELD_NOCOMPLETION);
       traderErs[2]=new EntityFilterSettings("αγρότης","checkboxTable","string","","traderId","sxtrader","sxtrader","",0,-1,-1,FIELD_NOCOMPLETION);
       traderErs[3]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader",null,"",0,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[6]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","f","",1,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[7]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","f","",1,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[8]=new EntityFilterSettings("ημ/νία αίτησης","","date","fromto","dateOfApplication","a",null,"",2,0,-1,FIELD_NOCOMPLETION);
      // invoiceErs[6]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","d",null,"",2,-1,-1,FIELD_NOCOMPLETION);
      // invoiceErs[7]=new EntityFilterSettings("ποσό παρ/κών","","double","fromto","value","d",null,"",2,-1,-1,FIELD_NOCOMPLETION);
      // invoiceErs[8]=new EntityFilterSettings("ποσό επιστροφής","","double","fromto","valueReturn","a",null,"",2,-1,-1,FIELD_NOCOMPLETION);
      // invoiceErs[9]=new EntityFilterSettings("ποσό κράτησης","","double","fromto","payment","d",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[13]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","buyer","i","",3,-1,1,FIELD_NOCOMPLETION);
     //  invoiceErs[10]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","product","i","",3,-1,1,FIELD_NOCOMPLETION);
      // invoiceErs[15]=new EntityFilterSettings("είδος προϊόντος","checkboxTable","string","","currencyId","currency","i","",3,-1,1,FIELD_NOCOMPLETION);
       //invoiceErs[16]=new EntityFilterSettings("τύπος παραστατικού","checkboxTable","string","","paymentTypeId","paymentType","i","",3,-1,1,FIELD_NOCOMPLETION);

       
       
       EntityGroupOfComps[] traderEntityGroupOfComps = new EntityGroupOfComps[1];
       //traderEntityGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0);
       traderEntityGroupOfComps[0] = new EntityGroupOfComps("συναλλασσόμενος",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       //invoiceEntityGroupOfComps[2] = new EntityGroupOfComps("παραστατικό",4,0);
       //invoiceEntityGroupOfComps[3] = new EntityGroupOfComps("υπηρεσίες",4,0);
       /*
       String[] invoiceCaptionOfGroupOfComps = {"αποστολή","αγρότης","άιτηση"};
       int[] invoiceColumnsOfObjects = {6,4,4};
       int[] invoiceIncludedInGroupsOfPanels = {-1,-1,-1};
       
       EntityGroupOfComps invoiceEntityGroupOfComps = new EntityGroupOfComps(invoiceCaptionOfGroupOfComps,invoiceColumnsOfObjects,invoiceIncludedInGroupsOfPanels);       
       */
       int[] invoicesSelected =null;//{1,2,3,4,0,0,0,0,0,0,11,12,0,14,};        
       int[] invoiceFieldOrderby = {3,4,2};
       
       //boolean[] boolSettingsInvoice = {false,false,true,true};
       //EntityQuery[] invoiceEntQuery = new EntityQuery[1]; 
       //invoiceEntQuery[0]= new EntityQuery("SELECT sxtrader.traderId,sxtrader.traderAfm, sxtrader.surname,sxtrader.name,invoice.* FROM invoice, sxtrader, application WHERE application.traderId = sxtrader.traderId AND invoice.traderId = sxtrader.traderId",false,0,null,null,null,null,null);
  
       //EntityQuery[] deliveryCheckEntQuery = new EntityQuery[1]; 
       //deliveryCheckEntQuery[0]= new EntityQuery("SELECT invoice.traderId,buyer.buyerTitle , invoice.paymentTypeId,invoice.invoiceNo,invoice.date,invoice.dbyear, invoice.productId, invoice.value, invoice.valueReturn FROM invoice, sxtrader, application, buyer WHERE buyer.buyerId=invoice.buyerId AND invoice.traderId = sxtrader.traderId AND application.traderId=sxtrader.traderId AND application.traderId=invoice.traderId ORDER BY sxtrader.surname,sxtrader.name",false,0,null,null,null,null,null);
//-----        
       // same as entityInfoMany the read only of list
       //String deliveryCheckHeaderEntQuery="SELECT f.traderId AS\"Νο πελάτη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.traderAfm AS\"Α.Φ.Μ.\", permanent AS \"υπολ\" , d.dateOfApplication AS \"ημ/νια αίτησης\" , COUNT(i.value) AS \"πλήθος\", SUM(i.value) AS \"αξία\", SUM(retValueAccordingToType(1, i.currencyId, i.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, i.currencyId, i.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, i.currencyId, i.valueReturn)) AS \"κατ 3\", d.valueReturn AS \"συν επιστρ\", d.payment AS \"κράτηση\" FROM application d, sxtrader f, invoice i WHERE i.traderId = f.traderId AND d.traderId = f.traderId AND i.deliveryId = d.deliveryId AND i.dbyear=d.dbyear AND i.dbCompanyId=d.dbCompanyId GROUP BY f.traderId, d.permanent, d.dateOfApplication ORDER BY f.surname, f.name";
  //     EntityReportGroup[] deliveryEntityReportGroup = new EntityReportGroup[2];
 //      deliveryEntityReportGroup[0] = new EntityReportGroup("αγρότες","SELECT f.traderId AS\"Νο πελάτη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.traderAfm AS\"Α.Φ.Μ.\", permanent AS \"υπολ\" ,a.deliveryId, a.dateOfApplication AS \"ημ/νια αίτησης\" , COUNT(i.value) AS \"πλήθος\", SUM(i.value) AS \"αξία\", SUM(retValueAccordingToType(1, i.currencyId, i.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, i.currencyId, i.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, i.currencyId, i.valueReturn)) AS \"κατ 3\", a.valueReturn AS \"συν επιστρ\", a.payment AS \"κράτηση\" FROM application a, sxtrader f, invoice i WHERE i.traderId = f.traderId AND a.traderId = f.traderId AND i.deliveryId = a.deliveryId AND i.dbyear=a.dbyear AND i.dbCompanyId=a.dbCompanyId GROUP BY f.traderId, a.permanent, a.dateOfApplication, a.valuereturn", "ORDER BY f.surname, f.name","application",ENTITYREPORT_QUERY_TYPE_MAIN,0,"traderId",boolSettingstrader);  // header
 //      deliveryEntityReportGroup[1] = new EntityReportGroup("παραστατικά","SELECT i.aa AS\"α/α\", i.traderId,i.deliveryId,b.buyerTitle AS\"αγοραστής\", it.abbreviation  AS\"παρ/κο\", i.invoiceNo  AS\"αριθμός\",i.date  AS\"ημερομηνία\",i.dbyear, p.productName  AS\"προϊόν\", i.value  AS\"αξία\", i.valueReturn  AS\"επιστροφή\" FROM invoice i, sxtrader f, application a, buyer b, product p, currency pt, paymentType it WHERE i.productId=p.productId AND pt.currencyId=p.currencyId AND b.buyerId=i.buyerId AND i.traderId = f.traderId AND a.traderId=f.traderId AND a.traderId=i.traderId AND a.dbyear=i.dbyear AND a.dbCompanyId=i.dbCompanyId AND a.deliveryId=i.deliveryId AND i.paymentTypeId=it.paymentTypeId","ORDER BY f.surname,f.name","appinvoice",ENTITYREPORT_QUERY_TYPE_MAIN,1,null,boolSettingsInvoice);  // many

        /*public EntityReportBandField(String nameIn,  String captionIn, String tableNameIn ,String dbFieldNameIn,int groupOfCompsIn,String colClassNameIn,int colWidthIn,
              String defaultValueIn, EntityDBFieldsCalculation[] fieldsCalculationIn)*/
       boolean[] boolSettingstrader = {true,true,true,true};
       
       EntityReportBandField[] entityReportBandFieldstrader =new EntityReportBandField[5];

        entityReportBandFieldstrader[0] = new EntityReportBandField("sxtrader","traderId","Νο συναλλασόμενου","java.lang.Integer",11,true,null,null);
        //entityReportBandFieldstrader[1] = new EntityReportBandField("sxtrader","dbCompanyId","dbCompanyId","java.lang.String",11,true,null,null);
        entityReportBandFieldstrader[1] = new EntityReportBandField("sxtrader","title","επωνυμία","java.lang.String",44,true,null,null);
        entityReportBandFieldstrader[2] = new EntityReportBandField("sxtrader","traderCode","κωδικός","java.lang.String",18,true,null,null);
        entityReportBandFieldstrader[3] = new EntityReportBandField("sxtrader","vatNo","Α.Φ.Μ.","java.lang.String",18,true,null,null);
        entityReportBandFieldstrader[4] = new EntityReportBandField("sxtrader","activityDescr","δραστηριότητα","java.lang.String",45,true,null,null);

        int[] invoiceCheckFieldOrderby = {2,4,3};
        

       EntityReportBand[] reportBandtraderA = new EntityReportBand[1];
       reportBandtraderA[0] = new EntityReportBand("sxtrader","συναλλασσόμενος","sxtrader",entityReportBandFieldstrader,invoiceCheckFieldOrderby,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingstrader,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader);//,"","");
       boolean[] boolSettingsReportInvoice = {true,false,true,true,false};
       
       int[] intReportSettingsInvoice= {0,0,0,0};
       
       EntityReport ra = new EntityReport("rpttrader",REPORT_CAT_1,reportBandtraderA,"SELECT * FROM sxtrader ","","ODMR","συναλλασόμενοι","",traderErs,traderEntityGroupOfComps,invoicesSelected, null,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne);
          // EntityReport ra = new EntityReport("invoice",REPORT_CAT_1,null,invoiceEntQuery,null,null,"ODMR","κατάσταση ελέγχου","",invoiceErs,invoiceEntityGroupOfComps,invoicesSelected, null,invoiceFieldOrderby) ;

//        EntityMenu emra = new EntityMenu();
//        emra.setEntityReport(ra,ICO_PRINT_PREVIEW16);
//        emra.setEntityType(ENTITY_TYPE_REPORT);
//        DataTreeNode nodeemra = new DataTreeNode(emra);
        //nodeCat = nodeRoot.getChildFromCaption(REPORTS).getChildFromCaption(REPORT_CAT_1);
//        nodeReports.getChildFromCaption(REPORT_CAT_1).addChild(nodeemra);

//------------------------------------------------------------------------------
        
       EntityGroupOfComps[] esoexoheaderEntityGroupOfComps = new EntityGroupOfComps[3];
       esoexoheaderEntityGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexoheaderEntityGroupOfComps[1] = new EntityGroupOfComps("συναλλασόμενοι",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexoheaderEntityGroupOfComps[2] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       //invoiceEntityGroupOfComps[3] = new EntityGroupOfComps("υπηρεσίες",4,0);        
      //  
       EntityFilterSettings[] invoiceErs = new EntityFilterSettings[7];   
      
     // invoiceErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      invoiceErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       invoiceErs[1]=new EntityFilterSettings("Νο συναλλασόμενου","lookup","string","fromto","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[2]=new EntityFilterSettings("επίθετο","","string","equals","title","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[3]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[6]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","f","",1,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[7]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","f","",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[5]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfEsoexo","sxesoexoheader",null,"",2,0,-1,FIELD_NOCOMPLETION);
      // invoiceErs[6]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","d",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[6]=new EntityFilterSettings("τελικό ποσό","","double","fromto","priceTotal","sxesoexoheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
      // invoiceErs[8]=new EntityFilterSettings("ποσό επιστροφής","","double","fromto","valueReturn","a",null,"",2,-1,-1,FIELD_NOCOMPLETION);
      // invoiceErs[9]=new EntityFilterSettings("ποσό κράτησης","","double","fromto","payment","d",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[13]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","buyer","i","",3,-1,1,FIELD_NOCOMPLETION);
     //  invoiceErs[10]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","product","i","",3,-1,1,FIELD_NOCOMPLETION);
      // invoiceErs[15]=new EntityFilterSettings("είδος προϊόντος","checkboxTable","string","","currencyId","currency","i","",3,-1,1,FIELD_NOCOMPLETION);
       //invoiceErs[16]=new EntityFilterSettings("τύπος παραστατικού","checkboxTable","string","","paymentTypeId","paymentType","i","",3,-1,1,FIELD_NOCOMPLETION);        
boolean[] boolSettingsesoexoheader = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsesoexoheader =new EntityReportBandField[9];
       
        entityReportBandFieldsesoexoheader[0] = new EntityReportBandField("sxesoexoheader","esoexoCodeOfDocument","κωδ παραστατικού","java.lang.String",20,true,null,null);
        entityReportBandFieldsesoexoheader[1] = new EntityReportBandField("sxesoexoheader","sxActionTypeId","sxActionTypeId","java.lang.String",8,true,null,null);   
        entityReportBandFieldsesoexoheader[2] = new EntityReportBandField("sxactiontype","actionTypeCode","actionTypeCode","java.lang.String",9,true,null,null);   
        entityReportBandFieldsesoexoheader[3] = new EntityReportBandField("sxesoexoheader","dateOfEsoexo","ημερομηνία","java.lang.Date",25,true,null,null);   
        entityReportBandFieldsesoexoheader[4] = new EntityReportBandField("sxesoexoheader","priceTotal","τελικό ποσό","java.lang.Double",13,true,null,null);  
        entityReportBandFieldsesoexoheader[5] = new EntityReportBandField("sxesoexoheader","traderId","Νο συναλλασόμενου","java.lang.String",7,true,null,null);
        entityReportBandFieldsesoexoheader[6] = new EntityReportBandField("sxesoexoheader","dbYearId","dbYearId","java.lang.String",7,true,null,null);
        entityReportBandFieldsesoexoheader[7] = new EntityReportBandField("sxesoexoheader","esoexoheaderId","esoexoheaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheader[8] = new EntityReportBandField("sxesoexoheader","countTotal","πλήθος","java.lang.String",9,true,null,null);         
         

//        boolean[] boolSettingstrader = {true,true,true,true};
       EntityReportBandField[] entityReportBandFieldstraderA =new EntityReportBandField[5];
       
        entityReportBandFieldstraderA[0] = new EntityReportBandField("sxtrader","traderId","Νο συναλλασόμενου","java.lang.Integer",10,true,null,null);
        //entityReportBandFieldstraderA[1] = new EntityReportBandField("sxtrader","dbCompanyId","dbCompanyId","java.lang.String",10,true,null,null);
        entityReportBandFieldstraderA[1] = new EntityReportBandField("sxtrader","title","επωνυμία","java.lang.String",23,true,null,null);
        entityReportBandFieldstraderA[2] = new EntityReportBandField("sxtrader","traderCode","κωδικός","java.lang.String",10,true,null,null);
        entityReportBandFieldstraderA[3] = new EntityReportBandField("sxtrader","vatNo","Α.Φ.Μ.","java.lang.String",11,true,null,null);
        entityReportBandFieldstraderA[4] = new EntityReportBandField("sxtrader","activityDescr","δραστηριότητα","java.lang.String",28,true,null,null);

        //EntityReportBand[] reportBandesoexoheader = new EntityReportBand[1];
       //reportBandesoexoheader[0] = new EntityReportBand("sxesoexoheader","πώληση","sxesoexoheader",entityReportBandFieldsesoexoheader,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoheader); 

       
      int[] traderOrderBy={2,4,3};       
      int[] sxheaderOrderBy={4,1,3};
       
       EntityReportBand[] reportBandtrader = new EntityReportBand[2];
       reportBandtrader[0] = new EntityReportBand("sxtrader","συναλλασσόμενος","sxtrader",entityReportBandFieldstraderA,traderOrderBy,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingstrader,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader);//,"","");
       reportBandtrader[1] = new EntityReportBand("sxesoexoheader","παραστατικό","sxesoexoheader",entityReportBandFieldsesoexoheader,sxheaderOrderBy,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoheader,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);//,"sxactiontype","sxaccount");
 //      boolean[] boolSettingsReportInvoice = {true,false,true,true,false};
       
//       int[] invoiceCheckFieldOrderby = {1,2,3};
//       int[] intReportSettingsInvoice= {0,0,0,0};

//       EntityFilterSettings[] invoiceErs = null;
 //      EntityGroupOfComps[] invoiceEntityGroupOfComps = null;
 //      int[] invoicesSelected =null;
       String globalYearPlusOne="";
       
       EntityReport rb = new EntityReport("rpttraderesoexoheader",REPORT_CAT_1,reportBandtrader,"SELECT * FROM sxtrader, sxesoexoheader,sxactiontype WHERE sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId AND sxesoexoheader.traderId = sxtrader.traderId AND sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"" ,"ODMR","συναλλασόμενοι και παραστατικά","",invoiceErs,esoexoheaderEntityGroupOfComps,invoicesSelected, null,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne);
          // EntityReport ra = new EntityReport("invoice",REPORT_CAT_1,null,invoiceEntQuery,null,null,"ODMR","κατάσταση ελέγχου","",invoiceErs,invoiceEntityGroupOfComps,invoicesSelected, null,invoiceFieldOrderby) ;
                    
        EntityMenu emrb = new EntityMenu();
        emrb.setEntityReport(rb,ICO_PRINT_PREVIEW16);
        emrb.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrb = new DataTreeNode(emrb);
        //nodeCat = nodeRoot.getChildFromCaption(REPORTS).getChildFromCaption(REPORT_CAT_1);
        nodeReports.getChildFromCaption(REPORT_CAT_1).addChild(nodeemrb);                 
        
      //---------------------------------------------------------------------------------  
       
       EntityGroupOfComps[] esoexolineEntityGroupOfComps = new EntityGroupOfComps[4];
       esoexolineEntityGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexolineEntityGroupOfComps[1] = new EntityGroupOfComps("συναλλασσόμενος",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexolineEntityGroupOfComps[2] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexolineEntityGroupOfComps[3] = new EntityGroupOfComps("υπηρεσίες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);       
        
       EntityFilterSettings[] invoiceServiceErs = new EntityFilterSettings[8];   
      
     // invoiceServiceErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      invoiceServiceErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //invoiceServiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       invoiceServiceErs[1]=new EntityFilterSettings("Νο συναλλασόμενου","lookup","string","fromto","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceServiceErs[2]=new EntityFilterSettings("επίθετο","","string","equals","title","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceServiceErs[3]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceServiceErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
       invoiceServiceErs[5]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfEsoexo","sxesoexoheader",null,"",2,0,-1,FIELD_NOCOMPLETION);
      // invoiceErs[6]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","d",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       invoiceServiceErs[6]=new EntityFilterSettings("τελικό ποσό","","double","fromto","priceTotal","sxesoexoheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);      
       invoiceServiceErs[7]=new EntityFilterSettings("λογαριασμός","checkboxTable","string","","accountId","sxaccount","sxesoexoline","",3,-1,-1,FIELD_NOCOMPLETION);
     //  invoiceServiceErs[8]=new EntityFilterSettings("ΦΠΑ λογαριασμόςς","checkboxTable","string","","vatCatId","vatcat","sxaccount","",3,-1,-1,FIELD_NOCOMPLETION);       


        boolean[] boolSettingsesoexoline = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsesoexoline =new EntityReportBandField[9];
       
        entityReportBandFieldsesoexoline[0] = new EntityReportBandField("sxesoexoline","inc","inc","java.lang.String",8,true,null,null);
        entityReportBandFieldsesoexoline[1] = new EntityReportBandField("sxesoexoline","esoexoheaderId","esoexoheaderId","java.lang.String",8,true,null,null);
        entityReportBandFieldsesoexoline[2] = new EntityReportBandField("sxesoexoline","accountId","accountId","java.lang.String",8,true,null,null);        
        entityReportBandFieldsesoexoline[3] = new EntityReportBandField("sxaccount","accountDescr","accountDescr","java.lang.String",35,true,null,null);        
        entityReportBandFieldsesoexoline[4] = new EntityReportBandField("sxesoexoline","description","περιγραφή","java.lang.String",25,true,null,null);  
        //entityReportBandFieldsesoexoline[5] = new EntityReportBandField("sxesoexoline","quantity","quantity","java.lang.String",8,true,null,null);                    
        entityReportBandFieldsesoexoline[5] = new EntityReportBandField("sxesoexoline","priceBeforeVat","priceBeforeVat","java.lang.Double",15,true,null,null);
        entityReportBandFieldsesoexoline[6] = new EntityReportBandField("sxesoexoline","vatPercentage","vatPercentage","java.lang.String",15,true,null,null);
        entityReportBandFieldsesoexoline[7] = new EntityReportBandField("sxesoexoline","vatValue","vatValue","java.lang.Double",15,true,null,null);
        entityReportBandFieldsesoexoline[8] = new EntityReportBandField("sxesoexoline","valueWithVat","valueWithVat","java.lang.Double",15,true,null,null);
        //entityReportBandFieldsesoexoline[9] = new EntityReportBandField("sxesoexoline","traderId","Νο πελάτη","java.lang.String",7,true,null,null);                    
                    
                    
        //boolean[] boolSettingsesoexoheader = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsesoexoheaderA =new EntityReportBandField[9];
       
        entityReportBandFieldsesoexoheaderA[0] = new EntityReportBandField("sxesoexoheader","esoexoheaderId","esoexoheaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheaderA[1] = new EntityReportBandField("sxesoexoheader","esoexoCodeOfDocument","κωδ παραστατικού","java.lang.String",15,true,null,null);
        entityReportBandFieldsesoexoheaderA[2] = new EntityReportBandField("sxesoexoheader","sxActionTypeId","sxActionTypeId","java.lang.String",8,true,null,null);   
        entityReportBandFieldsesoexoheaderA[3] = new EntityReportBandField("sxactiontype","actionTypeCode","actionTypeCode","java.lang.String",9,true,null,null);           
        entityReportBandFieldsesoexoheaderA[4] = new EntityReportBandField("sxesoexoheader","dateOfEsoexo","ημερομηνία","java.lang.Date",18,true,null,null);        
        entityReportBandFieldsesoexoheaderA[5] = new EntityReportBandField("sxesoexoheader","pricePreVat","προ φόρου","java.lang.Double",9,true,null,null);  
        entityReportBandFieldsesoexoheaderA[5] = new EntityReportBandField("sxesoexoheader","priceTotal","τελικό ποσό","java.lang.Double",9,true,null,null);  
        entityReportBandFieldsesoexoheaderA[6] = new EntityReportBandField("sxesoexoheader","traderId","Νο συναλλασόμενου","java.lang.String",8,true,null,null);
        entityReportBandFieldsesoexoheaderA[7] = new EntityReportBandField("sxesoexoheader","dbYearId","dbYearId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheaderA[8] = new EntityReportBandField("sxesoexoheader","countTotal","πλήθος","java.lang.String",9,true,null,null); 
        //entityReportBandFieldsesoexoheaderA[9] = new EntityReportBandField("sxesoexoheader","withholdingtaxAmount","withholdingtaxAmount","java.lang.Double",10,true,null,null);  
        //entityReportBandFieldsesoexoheaderA[10] = new EntityReportBandField("sxesoexoheader","priceTotalAfterWithholdingTax","priceTotalAfterWithholdingTax","java.lang.Double",10,true,null,null);          
        
        //EntityReportBand[] reportBandesoexoheader = new EntityReportBand[1];
        //reportBandesoexoheader[0] = new EntityReportBand("sxesoexoheader","πώληση","sxesoexoheader",entityReportBandFieldsesoexoheader,ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoheader);

       // boolean[] boolSettingstrader = {true,true,true,true};
       EntityReportBandField[] entityReportBandFieldstraderB =new EntityReportBandField[5];
       
        entityReportBandFieldstraderB[0] = new EntityReportBandField("sxtrader","traderId","Νο πελάτη","java.lang.Integer",8,true,null,null);
        //entityReportBandFieldstraderB[1] = new EntityReportBandField("sxtrader","dbCompanyId","dbCompanyId","java.lang.String",8,true,null,null);
        entityReportBandFieldstraderB[1] = new EntityReportBandField("sxtrader","title","επωνυμία","java.lang.String",40,true,null,null);
        entityReportBandFieldstraderB[2] = new EntityReportBandField("sxtrader","traderCode","κωδικός","java.lang.String",10,true,null,null);
        entityReportBandFieldstraderB[3] = new EntityReportBandField("sxtrader","vatNo","Α.Φ.Μ.","java.lang.String",10,true,null,null);
        entityReportBandFieldstraderB[4] = new EntityReportBandField("sxtrader","activityDescr","δραστηριότητα","java.lang.String",30,true,null,null);
           
        
        int[] trOrderby = {2,4,3};
        int[] sxheadOrderby = {5,2};
        int[] sxlinOrderby = {1};
        

       EntityReportBand[] reportBandtraderService = new EntityReportBand[3];
       reportBandtraderService[0] = new EntityReportBand("sxtrader","συναλλασσόμενος","sxtrader",entityReportBandFieldstraderB,trOrderby,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingstrader,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader);//,"","");
       reportBandtraderService[1] = new EntityReportBand("sxesoexoheader","έσοδα έξοδα","sxesoexoheader",entityReportBandFieldsesoexoheaderA,sxheadOrderby,"esoexoheaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoheader,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);//,"sxactiontype","sxaccount");
       reportBandtraderService[2] = new EntityReportBand("sxesoexoline","λογαριασμός","sxesoexoline",entityReportBandFieldsesoexoline,sxlinOrderby,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoline,null,null,null);//,"","");
       //boolean[] boolSettingsReportInvoice = {true,false,true,true,false};
       
       
       //int[] intReportSettingsInvoice= {0,0,0,0};

       //EntityFilterSettings[] invoiceErs = null;
       //EntityGroupOfComps[] invoiceEntityGroupOfComps = null;
       //int[] invoicesSelected =null;
       //String globalYearPlusOne="";
       
       EntityReport rc = new EntityReport("rpttraderesoexoheaderesoexoline",REPORT_CAT_1,reportBandtraderService,"SELECT sxtrader.traderId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoheaderId, sxesoexoline.inc, sxtrader.*, sxesoexoheader.*, sxesoexoline.*, sxaccount.*, sxactiontype.* FROM sxtrader, sxesoexoheader, sxesoexoline, sxaccount, sxactiontype WHERE sxesoexoline.dbCompanyId = sxesoexoheader.dbCompanyId AND sxactiontype.dbCompanyId =  sxesoexoheader.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoheader.traderId = sxtrader.traderId AND sxesoexoheader.esoexoheaderId = sxesoexoline.esoexoheaderId AND sxesoexoline.accountId = sxaccount.accountId AND sxactiontype.sxActiontypeId = sxesoexoheader.sxActiontypeId",""/*"ORDER BY name"*/,"ODMR","συναλλασόμενοι, παραστατικά, λογαριασμοι","",invoiceServiceErs,esoexolineEntityGroupOfComps,invoicesSelected, null,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne);
          // EntityReport ra = new EntityReport("invoice",REPORT_CAT_1,null,invoiceEntQuery,null,null,"ODMR","κατάσταση ελέγχου","",invoiceErs,invoiceEntityGroupOfComps,invoicesSelected, null,invoiceFieldOrderby) ;
                            
        EntityMenu emrc = new EntityMenu();
        emrc.setEntityReport(rc,ICO_PRINT_PREVIEW16);
        emrc.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrc = new DataTreeNode(emrc);
        //nodeCat = nodeRoot.getChildFromCaption(REPORTS).getChildFromCaption(REPORT_CAT_1);
        nodeReports.getChildFromCaption(REPORT_CAT_1).addChild(nodeemrc);          
        
        
        
      /* EntityGroupOfComps[] invoiceReturnEntityGroupOfComps = new EntityGroupOfComps[3];
       invoiceReturnEntityGroupOfComps[0] = new EntityGroupOfComps("εταιρία/χρήση/αποστολή",6,0);
       invoiceReturnEntityGroupOfComps[1] = new EntityGroupOfComps("αγρότης",4,0);
       invoiceReturnEntityGroupOfComps[2] = new EntityGroupOfComps("άιτηση",4,0);*/



 
       /*EntityFilterSettings[] paymentErs = new EntityFilterSettings[14];   
       paymentErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","application",VariablesGlobal.globalCompanyId,0,-1,0,FIELD_OBLIGATORY);
       paymentErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbyear","dbyear","application", VariablesGlobal.globalYear,0,0,0,FIELD_OBLIGATORY);
       paymentErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","application","0",0,-1,-1,FIELD_OBLIGATORY);  //("αποστολή","","string","equals","deliveryId","dbDelivery",null,VariablesGlobal.globalDeliveryId,0,-1,0);       
       paymentErs[3]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","traderId","sxtrader","f","",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[4]=new EntityFilterSettings("επίθετο","","string","equals","surname","sxtrader",null,"",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[5]=new EntityFilterSettings("αγρότης","checkboxTable","string","","traderId","sxtrader","sxtrader","",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[6]=new EntityFilterSettings("ΑΦΜ","","string","equals","traderAfm","sxtrader",null,"",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[7]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","sxtrader","",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[8]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","sxtrader","",1,-1,0,FIELD_NOCOMPLETION);
       paymentErs[9]=new EntityFilterSettings("ημ/νία αίτησης","","date","fromto","dateOfApplication","application",null,"",2,-1,0,FIELD_NOCOMPLETION);
       paymentErs[10]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","application",null,"",2,-1,0,FIELD_NOCOMPLETION);
       paymentErs[11]=new EntityFilterSettings("ποσό παρ/κών","","double","fromto","value","application",null,"",2,-1,0,FIELD_NOCOMPLETION); 
       paymentErs[12]=new EntityFilterSettings("ποσό επιστροφής","","double","fromto","valueReturn","application",null,"",2,-1,0,FIELD_NOCOMPLETION);
       paymentErs[13]=new EntityFilterSettings("ποσό κράτησης","","double","fromto","payment","application",null,"",2,-1,0,FIELD_NOCOMPLETION);
 
 
       boolean[] boolSettingsInvoices = {true,true,true,true,true};
 //      EntityReportGroup[] invEntityReportGroup = new EntityReportGroup[1];
  //     invEntityReportGroup[0] = new EntityReportGroup("παραστατικά","SELECT sxtrader.traderId,sxtrader.traderAfm, sxtrader.surname,sxtrader.name, sxtrader.fathername, application.deliveryId, COUNT(invoice.value), SUM(invoice.value),  SUM(retValueAccordingToType(1, invoice.currencyId, invoice.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, invoice.currencyId, invoice.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, invoice.currencyId, invoice.valueReturn)) AS \"κατ 3\",  application.valueReturn AS \"συν επιστρ\" FROM invoice, sxtrader, application"+
 //      " WHERE application.traderId = sxtrader.traderId AND invoice.traderId = sxtrader.traderId AND invoice.deliveryId = application.deliveryId AND invoice.dbyear=application.dbyear AND invoice.dbCompanyId=application.dbCompanyId GROUP BY invoice.traderId ,application.valueReturn, APPLICATION.DELIVERYID","","invoice",ENTITYREPORT_QUERY_TYPE_MAIN,-1,"", boolSettingsInvoices); 
       
       //EntityQuery[] invEntityQuery = new EntityQuery[1]; 
       //invEntityQuery[0]= new EntityQuery("SELECT sxtrader.traderId,sxtrader.traderAfm, sxtrader.surname,sxtrader.name, sxtrader.fathername, COUNT(invoice.value), SUM(invoice.value),  SUM(retValueAccordingToType(1, invoice.currencyId, invoice.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, invoice.currencyId, invoice.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, invoice.currencyId, invoice.valueReturn)) AS \"κατ 3\",  application.valueReturn AS \"συν επιστρ\" FROM invoice, sxtrader, application"+
       //" WHERE application.traderId = sxtrader.traderId AND invoice.traderId = sxtrader.traderId AND invoice.deliveryId = application.deliveryId AND invoice.dbyear=application.dbyear AND invoice.dbCompanyId=application.dbCompanyId GROUP BY invoice.traderId", false,0,null,null,null,null,null);
       boolean[] boolSettingsReportInvoic = {true,false,true,true,true};
       int[] intReportSettingsInvoic= {0,0,0,0};
/*        EntityReport rb = new EntityReport("rptBook",REPORT_CAT_1,invEntityReportGroup,"ODMR","βιβλίο μεταγραφής","",paymentErs,invoiceReturnEntityGroupOfComps,invoicesSelected, null,invoiceFieldOrderby,intReportSettingsInvoic,boolSettingsReportInvoic,globalYearPlusOne) ;
        EntityMenu emrb = new EntityMenu();
        emrb.setEntityReport(rb,ICO_PRINT_PREVIEW16);
        emrb.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrb = new DataTreeNode(emrb);
        nodeReports.getChildFromCaption(REPORT_CAT_1).addChild(nodeemrb);
*/
   
       
   


//----------------------------------------------------------------------------------------------------------------------------------------------
       
       EntityFilterSettings[] esoexoLedgerErs = new EntityFilterSettings[6];   
     // salesDocumentErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      esoexoLedgerErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //salesDocumentErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       esoexoLedgerErs[1]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       esoexoLedgerErs[2]=new EntityFilterSettings("επίθετο","","string","equals","title","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       esoexoLedgerErs[3]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       esoexoLedgerErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
       esoexoLedgerErs[5]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfEsoexo","sxesoexoheader",null,"",2,0,-1,FIELD_NOCOMPLETION);
       

       EntityGroupOfComps[] esoexoLedgerGroupOfComps = new EntityGroupOfComps[3];
       esoexoLedgerGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexoLedgerGroupOfComps[1] = new EntityGroupOfComps("συναλλασσόμενος",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexoLedgerGroupOfComps[2] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);

      
                 
        EntityReportBandField[] entityReportBandFieldsesoexolineE =new EntityReportBandField[8];
       
        entityReportBandFieldsesoexolineE[0] = new EntityReportBandField("sxesoexoline","esoexoheaderId","esoexoheaderId","java.lang.String",10,true,null,null);
        entityReportBandFieldsesoexolineE[1] = new EntityReportBandField("sxesoexoline","inc","inc","java.lang.String",8,true,null,null);
        entityReportBandFieldsesoexolineE[2] = new EntityReportBandField("sxesoexoline","accountId","accountId","java.lang.String",10,true,null,null);        
        entityReportBandFieldsesoexolineE[3] = new EntityReportBandField("sxaccount","accountDescr","accountDescr","java.lang.String",30,true,null,null);        
        entityReportBandFieldsesoexolineE[4] = new EntityReportBandField("sxesoexoline","description","περιγραφή","java.lang.String",25,true,null,null);  
        //entityReportBandFieldsesoexolineA[4] = new EntityReportBandField("sxesoexoline","quantity","quantity","java.lang.String",12,true,null,null);                    
        entityReportBandFieldsesoexolineE[5] = new EntityReportBandField("sxesoexoline","priceBeforeVat","priceBeforeVat","java.lang.Double",15,true,null,null);
        //entityReportBandFieldsesoexolineA[5] = new EntityReportBandField("sxesoexoline","vatCatId","vatCatId","java.lang.String",15,true,null,null);
        entityReportBandFieldsesoexolineE[6] = new EntityReportBandField("sxesoexoline","vatValue","vatValue","java.lang.Double",15,true,null,null);
        entityReportBandFieldsesoexolineE[7] = new EntityReportBandField("sxesoexoline","valueWithVat","valueWithVat","java.lang.Double",15,true,null,null);
       // entityReportBandFieldsesoexolineA[7] = new EntityReportBandField("sxesoexoline","traderId","Νο πελάτη","java.lang.String",7,true,null,null);                    
                    
                    
        //boolean[] boolSettingsesoexoheader = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsesoexoheaderE =new EntityReportBandField[8];
       
        entityReportBandFieldsesoexoheaderE[0] = new EntityReportBandField("sxesoexoheader","esoexoCodeOfDocument","κωδ παραστατικού","java.lang.String",15,true,null,null);
        entityReportBandFieldsesoexoheaderE[1] = new EntityReportBandField("sxesoexoheader","dateOfEsoexo","ημερομηνία","java.lang.Date",18,true,null,null);        
        entityReportBandFieldsesoexoheaderE[2] = new EntityReportBandField("sxesoexoheader","priceTotal","τελικό ποσό","java.lang.Double",20,true,null,null);  
        entityReportBandFieldsesoexoheaderE[3] = new EntityReportBandField("sxesoexoheader","traderId","Νο συναλλασόμενου","java.lang.String",7,true,null,null);
        entityReportBandFieldsesoexoheaderE[4] = new EntityReportBandField("sxtrader","title","συναλλασσόμενος","java.lang.String",27,true,null,null);
        entityReportBandFieldsesoexoheaderE[5] = new EntityReportBandField("sxesoexoheader","esoexoheaderId","esoexoheaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheaderE[6] = new EntityReportBandField("sxesoexoheader","dbCompanyId","dbCompanyId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheaderE[7] = new EntityReportBandField("sxesoexoheader","dbYearId","dbYearId","java.lang.String",9,true,null,null);      

        
       int[] ledgOrderby1 ={2,5};        
       int[] ledgOrderby2 ={2};
        
       EntityReportBand[] reportBandLedger = new EntityReportBand[2];
       //reportBandtraderEsExDoc[0] = new EntityReportBand("sxesoexoheader","πώληση","sxesoexoheader",entityReportBandFieldsesoexoheaderB,"esoexoheaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReporttraderfileA);
       //reportBandtraderEsExDoc[1] = new EntityReportBand("esoexoline","υπηρεσίες","esoexoline",entityReportBandFieldsesoexolineA,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReporttraderfileB);        
       reportBandLedger[0] = new EntityReportBand("sxesoexoheader","έσοδα έξοδα","sxesoexoheader",entityReportBandFieldsesoexoheaderE,ledgOrderby1,"esoexoheaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoheader,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);//,"sxactiontype","sxaccount");
       reportBandLedger[1] = new EntityReportBand("sxesoexoline","λογαριασμός","sxesoexoline",entityReportBandFieldsesoexolineE,ledgOrderby2,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoline,null,null,null);//,"","");
     

       EntityReport erc = new EntityReport("rpttraderesoexoheaderesoexoline",REPORT_CAT_1,reportBandLedger,"SELECT sxtrader.traderId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoheaderId, sxesoexoline.inc, sxtrader.*, sxesoexoheader.*, sxesoexoline.*, sxaccount.*, sxactiontype.* FROM sxtrader, sxesoexoheader, sxesoexoline, sxaccount, sxactiontype WHERE sxesoexoline.dbCompanyId = sxesoexoheader.dbCompanyId AND sxesoexoline.dbCompanyId = sxactiontype.dbCompanyId AND sxactiontype.dbCompanyId = sxesoexoheader.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoheader.traderId = sxtrader.traderId AND sxesoexoheader.esoexoheaderId = sxesoexoline.esoexoheaderId AND sxesoexoline.accountId = sxaccount.accountId AND sxactiontype.sxActiontypeId = sxesoexoheader.sxActiontypeId",""/*"ORDER BY name"*/,"ODMR","παραστατικά και λογαριασμοί","",esoexoLedgerErs,esoexoLedgerGroupOfComps,invoicesSelected, null,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne);       
      
       EntityMenu emerc = new EntityMenu();
        emerc.setEntityReport(erc,ICO_PRINT_PREVIEW16); //ICO_REPORTFILE
        emerc.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeeemrc = new DataTreeNode(emerc);
        nodeReports.getChildFromCaption(REPORT_CAT_1).addChild(nodeeemrc);
       //listReports.add(entReportEsExDoc);
     //



       // ------------------------------------- REPORT_CAT_2 --------------------
      //-----------------------------------------------------------------------------------------------------------------------------------------------   

       
       EntityFilterSettings[] accountErs = new EntityFilterSettings[2];   
     // salesDocumentErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      accountErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //salesDocumentErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       //accountErs[1]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       //accountErs[2]=new EntityFilterSettings("επίθετο","","string","equals","name","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       //accountErs[3]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       //accountErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
       accountErs[1]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfEsoexo","sxesoexoheader",null,"",1,0,-1,FIELD_NOCOMPLETION);
       

       EntityGroupOfComps[] accountGroupOfComps = new EntityGroupOfComps[2];
       accountGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       //accountGroupOfComps[1] = new EntityGroupOfComps("συναλλασσόμενος",4,0);
       accountGroupOfComps[1] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);

      
                 
        EntityReportBandField[] entityReportBandFieldAccount =new EntityReportBandField[8];
       
        //entityReportBandFieldAccount[0] = new EntityReportBandField("sxaccount","esoexoheaderId","esoexoheaderId","java.lang.String",10,true,null,null);
        entityReportBandFieldAccount[0] = new EntityReportBandField("sxaccount","accountId","accountId","java.lang.String",7,true,null,null); 
        entityReportBandFieldAccount[1] = new EntityReportBandField("sxaccount","accountCode","accountCode","java.lang.String",10,true,null,null); 
        entityReportBandFieldAccount[2] = new EntityReportBandField("sxaccount","accountDescr","accountDescr","java.lang.String",35,true,null,null);        
        entityReportBandFieldAccount[3] = new EntityReportBandField("sxaccount","accountCatId","accountCatId","java.lang.String",12,true,null,null);  
        entityReportBandFieldAccount[4] = new EntityReportBandField("","countofPar","countofPar","java.lang.Double",12,true,null,null);                    
        entityReportBandFieldAccount[5] = new EntityReportBandField("","prevat","prevat","java.lang.Double",15,true,null,null);
        //entityReportBandFieldsesoexolineA[5] = new EntityReportBandField("sxesoexoline","vatCatId","vatCatId","java.lang.String",15,true,null,null);
        entityReportBandFieldAccount[6] = new EntityReportBandField("","vat","vat","java.lang.Double",15,true,null,null);
        entityReportBandFieldAccount[7] = new EntityReportBandField("","withvat","withvat","java.lang.Double",15,true,null,null);
       // entityReportBandFieldsesoexolineA[7] = new EntityReportBandField("sxesoexoline","traderId","Νο πελάτη","java.lang.String",7,true,null,null);                    
                    
                    
        //boolean[] boolSettingsesoexoheader = {true,true,true,true};            
   /*     EntityReportBandField[] entityReportBandFieldsesoexoheaderB =new EntityReportBandField[7];
       
        entityReportBandFieldsesoexoheaderB[0] = new EntityReportBandField("sxesoexoheader","esoexoCodeOfDocument","κωδ παραστατικού","java.lang.String",15,true,null,null);
        entityReportBandFieldsesoexoheaderB[1] = new EntityReportBandField("sxesoexoheader","dateOfEsoexo","ημερομηνία","java.lang.Date",18,true,null,null);        
        entityReportBandFieldsesoexoheaderB[2] = new EntityReportBandField("sxesoexoheader","priceTotal","τελικό ποσό","java.lang.Double",20,true,null,null);  
        entityReportBandFieldsesoexoheaderB[3] = new EntityReportBandField("sxesoexoheader","traderId","Νο πελάτη","java.lang.String",7,true,null,null);
        entityReportBandFieldsesoexoheaderB[4] = new EntityReportBandField("sxesoexoheader","esoexoheaderId","esoexoheaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheaderB[5] = new EntityReportBandField("sxesoexoheader","dbCompanyId","dbCompanyId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheaderB[6] = new EntityReportBandField("sxesoexoheader","dbYearId","dbYearId","java.lang.String",9,true,null,null);      
*/
   int[] accOrderBy = {2,3};
       EntityReportBand[] reportBandtraderAccount = new EntityReportBand[1];
       //reportBandtraderEsExDoc[0] = new EntityReportBand("sxesoexoheader","πώληση","sxesoexoheader",entityReportBandFieldsesoexoheaderB,"esoexoheaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReporttraderfileA);
       //reportBandtraderEsExDoc[1] = new EntityReportBand("esoexoline","υπηρεσίες","esoexoline",entityReportBandFieldsesoexolineA,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReporttraderfileB);        
       //reportBandtraderEsExDocA[0] = new EntityReportBand("sxesoexoheader","έσοδα έξοδα","sxesoexoheader",entityReportBandFieldsesoexoheaderB,"esoexoheaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoheader,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex); 
       reportBandtraderAccount[0] = new EntityReportBand("sxaccount","λογαριασμός","sxesoexoline",entityReportBandFieldAccount,accOrderBy,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoline,entityPanelSXAccount,fieldsOnTitleSXAccount,fieldsOnTitleCaptionSXAccount);//,"","");
     

       EntityReport emrhrep = new EntityReport("rpttraderesoexoheaderesoexoline",REPORT_CAT_2,reportBandtraderAccount,
      
        "SELECT sxaccount.accountId,  sxaccount.accountCode,sxaccount.accountDescr, sxaccount.accountCatId, COUNT(sxesoexoline.accountId) AS countofPar, SUM(sxesoexoline.priceBeforeVat) AS prevat, SUM(sxesoexoline.vatValue) AS vat, SUM(sxesoexoline.valueWithVat) AS withvat "+
        "FROM sxaccount, sxesoexoline,sxesoexoheader "+
       "WHERE sxesoexoline.accountId = sxaccount.accountId AND sxesoexoheader.esoexoHeaderId = sxesoexoline.esoexoHeaderId AND sxesoexoheader.dbCompanyId = sxesoexoline.dbCompanyId AND sxesoexoheader.isTemplate = sxesoexoline.isTemplate AND sxesoexoline.isTemplate ='0' AND sxesoexoline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" "+
       "AND sxaccount.active = 1 "+   // AND sxesoexoheader.dateOfesoexo > '2016-05-01' AND sxesoexoheader.dateOfesoexo < '2016-12-01'"+
       "GROUP BY sxaccount.accountId "
       /*"ORDER BY sxaccount.accountCatId, sxaccount.accountCode"*/,""/*"ORDER BY name"*/,"ODMR","ισοζύγιο λογαριασμών","",accountErs,accountGroupOfComps,invoicesSelected, null,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne);       
       
       
       
       EntityMenu emrh = new EntityMenu();
        emrh.setEntityReport(emrhrep,ICO_PRINT_PREVIEW16); //ICO_REPORTFILE
        emrh.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrh = new DataTreeNode(emrh);
        nodeReports.getChildFromCaption(REPORT_CAT_2).addChild(nodeemrh);
       
     //-----------------------------------------------------------------------------------------------------------------------------------------------   
    
     
       
       EntityFilterSettings[] accountAnalErs = new EntityFilterSettings[2];   
     // salesDocumentErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      accountAnalErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //salesDocumentErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       //accountErs[1]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       //accountErs[2]=new EntityFilterSettings("επίθετο","","string","equals","name","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       //accountErs[3]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       //accountErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
       accountAnalErs[1]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfEsoexo","sxesoexoheader",null,"",1,0,-1,FIELD_NOCOMPLETION);
       

       EntityGroupOfComps[] accountAnalGroupOfComps = new EntityGroupOfComps[2];
       accountAnalGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       //accountGroupOfComps[1] = new EntityGroupOfComps("συναλλασσόμενος",4,0);
       accountAnalGroupOfComps[1] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);

      
                 
        EntityReportBandField[] entityReportBandFieldAccountAnalysis =new EntityReportBandField[4];
       
        //entityReportBandFieldAccount[0] = new EntityReportBandField("sxaccount","esoexoheaderId","esoexoheaderId","java.lang.String",10,true,null,null);
        entityReportBandFieldAccountAnalysis[0] = new EntityReportBandField("sxaccount","accountId","accountId","java.lang.String",7,true,null,null); 
        entityReportBandFieldAccountAnalysis[1] = new EntityReportBandField("sxaccount","accountCode","accountCode","java.lang.String",10,true,null,null); 
        entityReportBandFieldAccountAnalysis[2] = new EntityReportBandField("sxaccount","accountDescr","accountDescr","java.lang.String",35,true,null,null);        
        entityReportBandFieldAccountAnalysis[3] = new EntityReportBandField("sxaccount","accountCatId","accountCatId","java.lang.String",12,true,null,null);  
       // entityReportBandFieldAccountAnalysis[4] = new EntityReportBandField("","countof","countof","java.lang.Double",12,true,null,null);                    
       // entityReportBandFieldAccountAnalysis[5] = new EntityReportBandField("","prevat","prevat","java.lang.Double",15,true,null,null);
        //entityReportBandFieldsesoexolineA[5] = new EntityReportBandField("sxesoexoline","vatCatId","vatCatId","java.lang.String",15,true,null,null);
       // entityReportBandFieldAccountAnalysis[6] = new EntityReportBandField("","vat","vat","java.lang.Double",15,true,null,null);
       // entityReportBandFieldAccountAnalysis[7] = new EntityReportBandField("","withvat","withvat","java.lang.Double",15,true,null,null);
       // entityReportBandFieldsesoexolineA[7] = new EntityReportBandField("sxesoexoline","traderId","Νο πελάτη","java.lang.String",7,true,null,null);                    
                    
                    
        //boolean[] boolSettingsesoexoheader = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsesoexoheaderAnal =new EntityReportBandField[11];
       
        entityReportBandFieldsesoexoheaderAnal[0] = new EntityReportBandField("sxesoexoheader","esoexoCodeOfDocument","κωδ παραστατικού","java.lang.String",15,true,null,null);
        entityReportBandFieldsesoexoheaderAnal[1] = new EntityReportBandField("sxesoexoheader","dateOfEsoexo","ημερομηνία","java.lang.Date",18,true,null,null);        
        entityReportBandFieldsesoexoheaderAnal[2] = new EntityReportBandField("sxesoexoheader","priceTotal","τελικό ποσό","java.lang.Double",20,true,null,null);  
        entityReportBandFieldsesoexoheaderAnal[3] = new EntityReportBandField("sxesoexoheader","traderId","Νο συναλλασόμενου","java.lang.String",5,true,null,null);
        entityReportBandFieldsesoexoheaderAnal[4] = new EntityReportBandField("sxtrader","title","συναλλασσόμενος","java.lang.String",15,true,null,null);
        entityReportBandFieldsesoexoheaderAnal[5] = new EntityReportBandField("sxtrader","vatNo","ΑΦΜ συναλλασόμενου","java.lang.String",10,true,null,null);
        entityReportBandFieldsesoexoheaderAnal[6] = new EntityReportBandField("sxesoexoheader","esoexoheaderId","esoexoheaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheaderAnal[7] = new EntityReportBandField("sxesoexoline","priceBeforeVat","priceBeforeVat","java.lang.Double",20,true,null,null);  
        entityReportBandFieldsesoexoheaderAnal[8] = new EntityReportBandField("sxesoexoline", "valueWithVat","valueWithVat","java.lang.Double",20,true,null,null);  
        entityReportBandFieldsesoexoheaderAnal[9] = new EntityReportBandField("sxesoexoheader","dbCompanyId","dbCompanyId","java.lang.String",9,true,null,null);
        entityReportBandFieldsesoexoheaderAnal[10] = new EntityReportBandField("sxesoexoheader","dbYearId","dbYearId","java.lang.String",9,true,null,null);      

        
        int[] anallogarOrderBy1 = {2,3};
        int[] anallogarOrderBy2 = {2,1,5};
        
       EntityReportBand[] reportBandAccountAnal = new EntityReportBand[2];
       //reportBandtraderEsExDoc[0] = new EntityReportBand("sxesoexoheader","πώληση","sxesoexoheader",entityReportBandFieldsesoexoheaderB,"esoexoheaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReporttraderfileA);
       //reportBandtraderEsExDoc[1] = new EntityReportBand("esoexoline","υπηρεσίες","esoexoline",entityReportBandFieldsesoexolineA,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReporttraderfileB);        
       //reportBandtraderEsExDocA[0] = new EntityReportBand("sxesoexoheader","έσοδα έξοδα","sxesoexoheader",entityReportBandFieldsesoexoheaderB,"esoexoheaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoheader,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex); 
       reportBandAccountAnal[0] = new EntityReportBand("sxaccount","λογαριασμός","sxaccount",entityReportBandFieldAccountAnalysis,anallogarOrderBy1,"accountId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoline,entityPanelSXAccount,fieldsOnTitleSXAccount,fieldsOnTitleCaptionSXAccount);//,"","");
      //reportBandtraderEsExDoc[1] = new EntityReportBand("esoexoline","υπηρεσίες","esoexoline",entityReportBandFieldsesoexolineA,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReporttraderfileB);        
       reportBandAccountAnal[1] = new EntityReportBand("sxesoexoheader","πώληση","sxesoexoheader",entityReportBandFieldsesoexoheaderAnal,anallogarOrderBy2,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReporttraderfileA,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);//,"sxactiontype","sxaccount"); 
      
       EntityReport emrhrepanal = new EntityReport("rptesoexoheaderaccountanalysis",REPORT_CAT_2,reportBandAccountAnal,
      
        "SELECT sxaccount.accountId,  sxaccount.accountCode,sxaccount.accountDescr, sxaccount.accountCatId,  sxesoexoheader.* , sxtrader.*, sxesoexoline.*, sxactiontype.* "+
        "FROM sxaccount, sxesoexoline,sxesoexoheader,sxtrader, sxactiontype "+
       "WHERE sxesoexoline.accountId = sxaccount.accountId AND sxesoexoheader.esoexoHeaderId = sxesoexoline.esoexoHeaderId AND sxesoexoheader.dbCompanyId = sxesoexoline.dbCompanyId AND sxactiontype.sxActiontypeId = sxesoexoheader.sxActiontypeId AND sxesoexoheader.traderId = sxtrader.traderId AND sxactiontype.dbCompanyId=sxesoexoline.dbCompanyId AND sxesoexoheader.isTemplate = sxesoexoline.isTemplate AND sxesoexoline.isTemplate ='0' AND sxesoexoline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" "+
       "AND sxaccount.active = 1 "+       // AND sxesoexoheader.dateOfesoexo > '2016-05-01' AND sxesoexoheader.dateOfesoexo < '2016-12-01'"+
       ""
       /*"ORDER BY sxaccount.accountCatId, sxaccount.accountCode"*/,""/*"ORDER BY name"*/,"ODMR","ανάλυση λογαριασμών","",accountAnalErs,accountAnalGroupOfComps,invoicesSelected, null,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne);       
       
       
       
       EntityMenu emrhanal = new EntityMenu();
        emrhanal.setEntityReport(emrhrepanal,ICO_PRINT_PREVIEW16); //ICO_REPORTFILE
        emrhanal.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrhanal = new DataTreeNode(emrhanal);
        nodeReports.getChildFromCaption(REPORT_CAT_2).addChild(nodeemrhanal);     
     

       // --------------------------------- REPORT_CAT_3 ---------------------
    
         EntityFilterSettings[] esoexoCustomersErs = new EntityFilterSettings[6];   
     // salesDocumentErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      esoexoCustomersErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //salesDocumentErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       esoexoCustomersErs[1]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       esoexoCustomersErs[2]=new EntityFilterSettings("επίθετο","","string","equals","title","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       esoexoCustomersErs[3]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       esoexoCustomersErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
       esoexoCustomersErs[5]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfEsoexo","sxesoexoheader",null,"",2,0,-1,FIELD_NOCOMPLETION);
       

       EntityGroupOfComps[] esoexoCustomersGroupOfComps = new EntityGroupOfComps[3];
       esoexoCustomersGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexoCustomersGroupOfComps[1] = new EntityGroupOfComps("συναλλασσόμενος",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexoCustomersGroupOfComps[2] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);    
       
      int[] custOrderBy1={2,4,3};       
      int[] custOrderBy2={4,1,3};
    
       
      EntityReportBand[] reportBandCustomer = new EntityReportBand[2];
       reportBandCustomer[0] = new EntityReportBand("sxtrader","συναλλασσόμενος","sxtrader",entityReportBandFieldstraderA,custOrderBy1,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingstrader,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader);//,"","");
       reportBandCustomer[1] = new EntityReportBand("sxesoexoheader","παραστατικό","sxesoexoheader",entityReportBandFieldsesoexoheader,custOrderBy2,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoheader,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);//,"sxactiontype","sxaccount");       
       
       
       
  EntityReport emrhri = new EntityReport("rpttraderscustomers",REPORT_CAT_2,reportBandCustomer,
      
        "SELECT * " +
        "FROM sxtrader " +
        "INNER JOIN sxesoexoheader ON sxesoexoheader.traderId = sxtrader.traderId " +
        "INNER JOIN sxactiontype ON sxactiontype.sxActionTypeId =sxesoexoheader.sxActionTypeId " +
        "WHERE sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId " +
        "AND sxesoexoheader.dbCompanyId LIKE " +VariablesGlobal.globalCompanyId+" "+
        "AND sxesoexoheader.isTemplate ='0' " +
        "AND sxactiontype.sxActionTypeCatId IN (1) "
       /*"ORDER BY sxaccount.accountCatId, sxaccount.accountCode"*/,""/*"ORDER BY name"*/,"ODMR","ανάλυση πελατών","",esoexoCustomersErs,esoexoCustomersGroupOfComps,invoicesSelected, null,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne);       
              
       
       EntityMenu emri = new EntityMenu();
        emri.setEntityReport(emrhri,ICO_PRINT_PREVIEW16); //ICO_REPORTFILE
        emri.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemri = new DataTreeNode(emri);
        nodeReports.getChildFromCaption(REPORT_CAT_3).addChild(nodeemri);
       
     //-----------------------------------------------------------------------------------------------------------------------------------------------   
    
     
         EntityFilterSettings[] esoexoSuppliersErs = new EntityFilterSettings[6];   
     // salesDocumentErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      esoexoSuppliersErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //salesDocumentErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       esoexoSuppliersErs[1]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       esoexoSuppliersErs[2]=new EntityFilterSettings("επίθετο","","string","equals","title","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       esoexoSuppliersErs[3]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","sxtrader","sxtrader","",1,-1,-1,FIELD_NOCOMPLETION);
       esoexoSuppliersErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","sxtrader",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
       esoexoSuppliersErs[5]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfEsoexo","sxesoexoheader",null,"",2,0,-1,FIELD_NOCOMPLETION);
       

       EntityGroupOfComps[] esoexoSuppliersGroupOfComps = new EntityGroupOfComps[3];
       esoexoSuppliersGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexoSuppliersGroupOfComps[1] = new EntityGroupOfComps("συναλλασσόμενος",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       esoexoSuppliersGroupOfComps[2] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);  
       
       
      int[] suppOrderBy1={2,4,3};       
      int[] suppOrderBy2={4,1,3};
       
       
      EntityReportBand[] reportBandSupplier = new EntityReportBand[2];
       reportBandSupplier[0] = new EntityReportBand("sxtrader","συναλλασσόμενος","sxtrader",entityReportBandFieldstraderA,suppOrderBy1,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingstrader,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader);//,"","");
       reportBandSupplier[1] = new EntityReportBand("sxesoexoheader","παραστατικό","sxesoexoheader",entityReportBandFieldsesoexoheader,suppOrderBy2,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsesoexoheader,entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);//,"sxactiontype","sxaccount");       
              
       
       
       
       EntityReport emrhrek = new EntityReport("rpttraderssuppliers",REPORT_CAT_2,reportBandSupplier,
      
        "SELECT * " +
        "FROM sxtrader " +
        "INNER JOIN sxesoexoheader ON sxesoexoheader.traderId = sxtrader.traderId " +
        "INNER JOIN sxactiontype ON sxactiontype.sxActionTypeId =sxesoexoheader.sxActionTypeId " +
        "WHERE sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId " +
        "AND sxesoexoheader.dbCompanyId LIKE " +VariablesGlobal.globalCompanyId+" "+
        "AND sxesoexoheader.isTemplate ='0' " +
        "AND sxactiontype.sxActionTypeCatId IN (2,3,4) "
       /*"ORDER BY sxaccount.accountCatId, sxaccount.accountCode"*/,""/*"ORDER BY name"*/,"ODMR","ανάλυση προμηθευτών","",esoexoSuppliersErs,esoexoSuppliersGroupOfComps,invoicesSelected, null,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne);       
       
       
       
       EntityMenu emrhk = new EntityMenu();
        emrhk.setEntityReport(emrhrek,ICO_PRINT_PREVIEW16); //ICO_REPORTFILE
        emrhk.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrhk = new DataTreeNode(emrhk);
        nodeReports.getChildFromCaption(REPORT_CAT_3).addChild(nodeemrhk);     
            
 
     }
    
    
  public void addDocumentNodes()
  {
      
      
      
      //entityPanelActionType ,null,fieldsOnTitleActionType,fieldsOnTitleCaptionActionType,actionTypeFieldsOrderby
      //entityPanelSXVatDoc ,null,fieldsOnTitleSXVatDoc,fieldsOnTitleCaptionSXVatDoc,actionTypeFieldsOrderby

        EntityFilterSettings[] vatDocErs = new EntityFilterSettings[2];
        vatDocErs[0]=new EntityFilterSettings("ονομασία","","string","equals","vatDocDescr","sxvatdocforperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //vatDocErs[1]=new EntityFilterSettings("ημερομηνία","","date","fromto","vatForPeriodStartDate","","sxvatdocforperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
        vatDocErs[1] = new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxvatdocforperiod",VariablesGlobal.globalYearId,-1,-1,-1,FIELD_NOCOMPLETION);
       //  invoiceErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
      
      
        int[] vatDocFieldsOrderby ={1};
        String[] fieldsForSumsVatDoc=null;
        EntityParameter pl = new EntityParameter("sxvatdocforperiod", "SELECT sxvatdocforperiod.vatDocForPeriodId AS \"Νο περιοδικής ΦΠΑ\", vatDocDescr AS\"περιγραφή\",  vatForPeriodStartDate AS \"ημερομηνία έναρξης περιόδου\", vatForPeriodEndDate AS \"ημερομηνία λήξης περιόδου\", sxvatdocforperiod.dbYearId AS \"ετος χρήσης\", dateSave FROM sxvatdocforperiod WHERE sxvatdocforperiod.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" GROUP BY sxvatdocforperiod.vatDocForPeriodId ORDER BY sxvatdocforperiod.vatDocForPeriodId","SELECT sxvatdocforperiod.vatDocForPeriodId AS \"Νο περιοδικής ΦΠΑ\", vatForPeriodStartDate AS \"ημερομηνία έναρξης περιόδου\", vatForPeriodEndDate AS \"ημερομηνία λήξης περιόδου\", dbYearId AS \"ετος χρήσης\" ","FROM sxvatdocforperiod","WHERE sxvatdocforperiod.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,fieldsForSumsVatDoc,sxVatDocDBFields ,"περιοδική ΦΠΑ","DORM","Νο περιοδικής ΦΠΑ","vatDocForPeriodId",vatDocErs,null,"περιοδικής ΦΠΑ", "περιοδικών ΦΠΑ",entityPanelSXVatDoc,null,fieldsOnTitleSXVatDoc,fieldsOnTitleCaptionSXVatDoc,vatDocFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empl = new EntityMenu();
        empl.setEntityParameter(pl,ICO_REPORTDOCUMENT);
        empl.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempl = new DataTreeNode(empl);
        nodeRoot.getChildFromCaption(DOCUMENTS).addChild(nodeempl);   
      

                
      /*  EntityStatistics[] sc = new EntityStatistics[1];
        sc[0] = new EntityStatistics("statInvoicesperyear","invoice","περιοδική ΦΠΑ",
        "SELECT sxaccount.accountId,  sxaccount.accountCode,sxaccount.accountDescr, sxaccount.accountCatId, COUNT(sxesoexoline.accountId) AS countof, SUM(sxesoexoline.priceBeforeVat) AS prevat, SUM(sxesoexoline.vatValue) AS vat, SUM(sxesoexoline.valueWithVat) AS withvat, sxaccount.vatExempt, sxaccount.vatCalculate, sxaccount.participatesInMYF, sxaccount.vatCatId, sxaccount.vatDocCode, sxaccount.taxDocE3Code ",
        "FROM sxaccount, sxesoexoline,sxesoexoheader ",
       "WHERE sxesoexoline.accountId = sxaccount.accountId AND sxesoexoheader.esoexoHeaderId = sxesoexoline.esoexoHeaderId AND sxesoexoheader.dbCompanyId = sxesoexoline.dbCompanyId  AND sxesoexoline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" "+
       "AND sxaccount.active = 1 ",  // AND sxesoexoheader.dateOfesoexo > '2016-05-01' AND sxesoexoheader.dateOfesoexo < '2016-12-01'"+
       "GROUP BY sxaccount.accountId ",
                "ORDER BY sxaccount.accountId",true,"sxesoexoheader.dbCompanyId",false,null,null,null,null,null,null);
        //EntityStatistics sb = new EntityStatistics("invoicesperyear","invoice","παραστατικά ανα χρήση","SELECT dbyear.dbyear, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum,SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM dbyear, invoice","WHERE dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId","GROUP BY dbyear.dbyear","ORDER BY dbyear.dbyear",true,"dbyear.dbCompanyId",false,null,null,null);
        EntityMenu emsc = new EntityMenu();
        emsc.setEntityStatistics(sc,ICO_REPORTDOCUMENT);
        emsc.setEntityType(ENTITY_TYPE_STATISTICS);//  ENTITY_TYPE_DOCUMENT
        DataTreeNode nodeemsc = new DataTreeNode(emsc);
        nodeRoot.getChildFromCaption(DOCUMENTS).addChild(nodeemsc);*/
        

        //EntityStatistics[] sca = new EntityStatistics[1];
        //sca[0] = new EntityStatistics("statMYF","myf","ΜΥΦ","SELECT dbyear.dbyear, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum,SUM(invoice.valueReturn) AS sumret, AVG(invoice.value) AS average","FROM dbyear, invoice","WHERE dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId","GROUP BY dbyear.dbyear","ORDER BY dbyear.dbyear",true,"dbyear.dbCompanyId",false,null,null,null,null,null,null);

        EntityFilterSettings[] myfErs = new EntityFilterSettings[1];       
        myfErs[0]=new EntityFilterSettings("περιγραφή","","string","equals","myfTitle","myfheader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //myfErs[1] = new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","myfheader",VariablesGlobal.globalYearId,-1,-1,-1,FIELD_NOCOMPLETION);       
// myfErs[1]=new EntityFilterSettings("ημερομηνία","","date","fromto","vatForPeriodStartDate","","sxvatdocforperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
       int[] myfHeaderFieldsOrderby ={1};
       String[] fieldsForSumsMyfHeader=null;
        //EntityStatistics sb = new EntityStatistics("invoicesperyear","invoice","παραστατικά ανα χρήση","SELECT dbyear.dbyear, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum,SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM dbyear, invoice","WHERE dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId","GROUP BY dbyear.dbyear","ORDER BY dbyear.dbyear",true,"dbyear.dbCompanyId",false,null,null,null);
        EntityParameter pm = new EntityParameter("myfheader", "SELECT myfHeaderId AS \"Νο ΜΥΦ\", dbCompanyId,  dbYearId, myfTitle AS \"περιγραφή\", myfForPeriodStartDate, myfForPeriodEndDate , dateSave, count1 AS \"πωλήσεις\", count2 AS \"πωλ. λιανικής\", count3 AS \"αγορές-δαπάνες\", count4 AS \"λοιπές δαπάνες\" FROM myfheader WHERE  myfheader.dbYearId LIKE "+VariablesGlobal.globalYearId+" AND myfheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"SELECT myfHeaderId, dbCompanyId,  dbYearId,  myfForPeriodStartDate, myfForPeriodEndDate, myfTitle, dateSave","FROM myfheader","WHERE myfheader.dbYearId LIKE "+VariablesGlobal.globalYearId+" AND myfheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,fieldsForSumsMyfHeader,myfHeaderDBFields ,"καταστάσεις ΜΥΦ","DORM","Νο ΜΥΦ","myfheaderId",myfErs,null,"της ΜΥΦ", "των ΜΥΦ",entityPanelMyfHeader,null,fieldsOnTitleMyfHeader,fieldsOnTitleCaptionMyfHeader,myfHeaderFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empm = new EntityMenu();
        //empm.setEntityStatistics(pm,ICO_REPORTDOCUMENT);
        empm.setEntityParameter(pm,ICO_REPORTDOCUMENT);
        //empm.setEntityType(ENTITY_TYPE_STATISTICS);//  ENTITY_TYPE_DOCUMENT
        empm.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempm = new DataTreeNode(empm);
        nodeRoot.getChildFromCaption(DOCUMENTS).addChild(nodeempm);        
      

       /* EntityStatistics[] scb = new EntityStatistics[1];
        scb[0] = new EntityStatistics("statInvoicesperyear","invoice","Ε3","SELECT dbyear.dbyear, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum,SUM(invoice.valueReturn) AS sumret, AVG(invoice.value) AS average","FROM dbyear, invoice","WHERE dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId","GROUP BY dbyear.dbyear","ORDER BY dbyear.dbyear",true,"dbyear.dbCompanyId",false,null,null,null,null,null,null);
        //EntityStatistics sb = new EntityStatistics("invoicesperyear","invoice","παραστατικά ανα χρήση","SELECT dbyear.dbyear, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum,SUM(invoice.returnValue) AS sumret, AVG(invoice.value) AS average","FROM dbyear, invoice","WHERE dbyear.dbyear=invoice.dbyear AND dbyear.dbCompanyId=invoice.dbCompanyId","GROUP BY dbyear.dbyear","ORDER BY dbyear.dbyear",true,"dbyear.dbCompanyId",false,null,null,null);
        EntityMenu emscb = new EntityMenu();
        emscb.setEntityStatistics(scb,ICO_REPORTDOCUMENT);
        emscb.setEntityType(ENTITY_TYPE_STATISTICS);//  ENTITY_TYPE_DOCUMENT
        DataTreeNode nodeemscb = new DataTreeNode(emscb);
        nodeRoot.getChildFromCaption(DOCUMENTS).addChild(nodeemscb);    */        

       
        EntityFilterSettings[] incomeDocErs = new EntityFilterSettings[1];
        incomeDocErs[0]=new EntityFilterSettings("ονομασία","","string","equals","description","sxincomedoc",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //incomeDocErs[1]=new EntityFilterSettings("ημερομηνία","","date","fromto","vatForPeriodStartDate","","sxincomedoc","",-1,-1,-1,FIELD_NOCOMPLETION);
        //incomeDocErs[0] = new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxincomedoc",VariablesGlobal.globalYearId,-1,-1,-1,FIELD_NOCOMPLETION);
       //  invoiceErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","sxesoexoheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
      
      
        int[] incomeDocFieldsOrderby ={1};
        String[] fieldsForSumsIncomeDoc=null;
        EntityParameter pn = new EntityParameter("sxincomedoc", "SELECT sxincomedoc.incomedocId AS \"Νο εισοδήματος\", sxincomedoc.dbCompanyId, sxincomedoc.dbYearId, sxincomedoc.description, sxincomedoc.dateSave, sxincomedoc.totalCount, sxincomedoc.totalGross, sxincomedoc.totalNet  FROM sxincomedoc LEFT JOIN sxincomedocdata ON sxincomedoc.incomeDocId = sxincomedocdata.incomeDocId WHERE sxincomedocdata.dbYearId LIKE "+VariablesGlobal.globalYearId+" AND sxincomedocdata.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxincomedocdata.dbYearId = sxincomedoc.dbYearId GROUP BY sxincomedoc.incomeDocId ORDER BY sxincomedoc.incomeDocId","SELECT * ","FROM sxincomedoc","WHERE sxincomedocdata.dbYearId LIKE "+VariablesGlobal.globalYearId+" AND sxincomedoc.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,fieldsForSumsIncomeDoc,sxIncomeDocDBFields ,"εισόδημα ελ. επαγγελματία","DORM","Νο εισοδήματος","incomeDocId",incomeDocErs,null,"εισοδήματος", "εισοδημάτων",entityPanelSXIncomeDoc,null,fieldsOnTitleSXIncomeDoc,fieldsOnTitleCaptionSXIncomeDoc,incomeDocFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empn = new EntityMenu();
        empn.setEntityParameter(pn,ICO_REPORTDOCUMENT);
        empn.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempn = new DataTreeNode(empn);
        nodeRoot.getChildFromCaption(DOCUMENTS).addChild(nodeempn);   
      
       
       
  }

  public void addStatisticsNodes() 
  {

      /*
    public static final int GRAPH_TYPE_PIE=1;
    public static final int GRAPH_TYPE_BAR3D=2;
    public static final int GRAPH_TYPE_LINE=3       
      */ 

  }
  
  public void addToolNodes() 
  {
//        EntityStatistics[] ta = new EntityStatistics[1];
//        ta[0] = new EntityStatistics("statInvoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.valueReturn) AS \"ΕΠΙΣΤΡΟΦΗ\"","FROM invoice",""/*invoice.traderId the same because we need where*/,"GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
        
        //EntityStatistics se = new EntityStatistics("invoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'name') AS \"μήνας\" , COUNT(*)AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.returnValue) AS sumret","FROM invoice","WHERE"/*invoice.traderId the same because we need where*/,"GROUP BY returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
/*        EntityMenu emta = new EntityMenu();
        emta.setEntityStatistics(ta,ICO_TOOLS);
        emta.setEntityType(ENTITY_TYPE_TOOLS);
        DataTreeNode nodeemta = new DataTreeNode(emta);
        nodeRoot.getChildFromCaption(TOOLS).addChild(nodeemta);
*/
  }
  

  
public EntityDBFields[] getEntityDbFieldsToImport()
{
   // int intAll = traderDBFields.length + sxAccountDBFields.length;
    //

    ArrayList listAll = new ArrayList();
        for(int a =0;a< traderDBFields.length;a++)
        {
           
             listAll.add(traderDBFields[a]);
        }
        
        for(int b =0;b< sxAccountDBFields.length;b++)
        {
             listAll.add(sxAccountDBFields[b]);
        }            


        EntityDBFields[] edbf = new EntityDBFields[listAll.size()];
        for(int l = 0;l<listAll.size();l++)
        {
            edbf[l] = (EntityDBFields)listAll.get(l);
        }
        
    System.out.println(" ----  getEntityDbFieldsToImport   "+edbf.length+"   "+listAll.size());
    
    

     return edbf;
}
  
  
}
// 


// todo features---------
// manytomany: able to create new records. When data changed(insert or delete or edit) do not allow to close or exit.
// if view n order preferences are setted (ie farmer) it affects lookups (ie farmer in application. So we need to apply the setted view n order in lookup.
// (servicesales)add constants 'sale xondrikh' and 'sale lianikh' in type of document. Also add textboxs in services prices(hondriki, lainikh).As a consequence change calculations in prices of sales
// 2016-02-09 (program) be able to change lengths of report columns 
// 2016-02-10 (program)Reports: when 3 or more bands shows again band 2(for example in sxaccount sales in 3bands shows again the documentheader before each documentline(sxaccount))
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