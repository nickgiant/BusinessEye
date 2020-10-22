package com.tool.domain;

import com.tool.guicomps.*;
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
import com.tool.model.EntityUpdateAdditional;
import com.tool.utils.*;
import java.util.ArrayList;

public class EntityDataSerSales extends EntityData implements Constants
{
       DataTree dTree;
       DataTreeNode nodeRoot ;

    public static final String REPORT_CAT_1 = "ανάλυση";
    public static final String REPORT_CAT_2 = "συναλλασσόμενοι";
    public static final String REPORT_CAT_3 = "λοιπές";
   // public static final String REPORT_CAT_4 = "κατάλογοι";       
    
       String globalYearPlusOne="";
       int intYearPlusOne=0;

       
        //----------------------------------------------------------------
        
       EntityReport entReportServiceSaleDoc;
       
       
        EntityDBFields[] saleDBFields = new EntityDBFields[21];
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] saleEntityGroupOfComps =new EntityGroupOfComps[6];
        EntityGroupOfPanels[] saleEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        
        String saleQueryEditable = "SELECT * FROM saleheader";//product.productId AS \"Νο προϊόντος\", product.productName AS \"ονομασία\", product.currencyId FROM product";
        String[] fieldsOnTitleSale ={"saleheader.saleCodeNo","actionseries.actionSeriesCode","saleheader.dateOfSale","trader.title"};
        String[] fieldsOnTitleCaptionSale  ={"αριθ. παρ/κού","τυπος παρ/κού","ημερομηνία","συναλλασσόμενος"};      
        String[] strSaleCategories = {DATAENTRY,METRICS};

        String[] fieldsUniqueSale = null;
        
        
        EntityUpdateAdditional[] updateAdditionalActionType = new EntityUpdateAdditional[7];
        
        EntityPanel entityPanelSaleDataentry;// = new EntityPanel("ODOR","saleheader",saleDBFields,saleEntityGroupOfComps,saleEntityGroupOfPanels,"Νο πώλησης","","saleHeaderId",saleQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSale,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,updateAdditionalActionType,entReportServiceSaleDoc);      // entReportServiceSaleDoc
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProductTraders = new EntityPanel("statProductTraders","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT trader.traderId AS \"νο αγρότη\", trader.surname, trader.name, trader.fatherName,trader.traderAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, trader","WHERE invoice.traderId = trader.traderId AND invoice.productId=","GROUP BY trader.traderId","ORDER BY trader.surname, trader.name, trader.fatherName,trader.traderAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelSale;// = new EntityPanel[] { entityPanelSaleDataentry};//,entityPanelProductStatistics,entityPanelProductTraders,entityPanelProductBuyers,entityPanelProductSalesPerDate};
       
        //----------------------------------------------------------------
       /* EntityDBFields[] traderDBFields = new EntityDBFields[24];
        EntityGroupOfComps[] traderEntityGroupOfComps = new EntityGroupOfComps[5];
        EntityGroupOfPanels[] traderEntityGroupOfPanels = new EntityGroupOfPanels[2];
        
        // same as second query in etityInfo
        //String traderQueryEditable="SELECT trader.traderId AS \"Νο συναλλασσόμενου\", trader.surname AS \"επίθετο\", trader.name AS\"όνομα\", trader.fathername AS \"πατρόνυμο\", trader.traderAfm AS \"Α.Φ.Μ.\", trader.doyId, trader.idNo AS \"αρ ταυτοτ\", trader.townId, trader.address AS \"διέυθυνση\", trader.phone AS \"τηλέφωνο\" FROM trader, town WHERE trader.townId=town.townId";
        String traderQueryEditable="SELECT * FROM trader";// LEFT JOIN doy ON trader.doyId=doy.doyId";// LEFT JOIN bank ON trader.bankId=bank.bankId";        
        String[] fieldsOnTitleTrader ={"traderId","title","vatNo"};
        String[] fieldsOnTitleCaptionTrader  ={"Νο","όνομα","ΑΦΜ"};
        String[] strTraderCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueTrader = {"vatNo"};
        //STATS be careful to have in the query all the fields that are also in the title
        EntityPanel entityPanelTraderDataentry;// = new EntityPanel("ODOR","trader",traderDBFields,traderEntityGroupOfComps,traderEntityGroupOfPanels,"Νο συναλλασσόμενου","","traderId",traderQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueTrader,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,entReportServiceSaleDoc);      
        EntityPanel entityPanelTraderHistory;// = new EntityPanel("statTraderHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT trader.traderId, trader.dbCompanyId, saleheader.saleHeaderId, actiontype.actionTypeCode, saleheader.saleCodeOfDocument, saleheader.saleCodeNo,saleheader.dbYearId, saleheader.dateOfSale, saleheader.isPrinted, saleheader.countTotal,saleheader.quantityTotal, saleheader.pricePreVat, saleheader.priceVat, saleheader.priceTotal","FROM trader, saleheader, actiontype","WHERE trader.traderId = saleheader.traderId AND trader.dbCompanyId = saleheader.dbCompanyId AND actionseries.actionseriesId = saleheader.actionseriesId AND trader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND trader.traderId =","","ORDER BY saleheader.dateOfSale, saleheader.saleCodeOfDocument",false,"",false,"",entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);     
        //EntityPanel entityPanelTraderProducts = new EntityPanel("statTraderProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.traderId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelTraderBuyers = new EntityPanel("statTraderBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.traderId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelTraderSalesPerDate = new EntityPanel("statTraderSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.traderId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelTrader;// = new EntityPanel[] { entityPanelTraderDataentry,entityPanelTraderHistory};//,entityPanelTraderStatistics,entityPanelTraderProducts,entityPanelTraderBuyers,entityPanelTraderSalesPerDate};

        /*String[] deliveryFields={"traderId","dateOfApplication","permanent","dbyear","deliveryId","dbCompanyId"};
        String[] deliveryFieldsTranslation={"traderId","ημ/νία αίτησης","υπολογισμένο","dbyear","deliveryId","dbCompanyId"};
        int[] deliveryGroupOfComps = null;*/
       //---------------------------------------------------------------- 
      
        EntityDBFields[] traderDBFields = new EntityDBFields[23];
        EntityGroupOfComps[] traderEntityGroupOfComps = new EntityGroupOfComps[7];
        EntityGroupOfPanels[] traderEntityGroupOfPanels = new EntityGroupOfPanels[3];
        
        // same as second query in etityInfo
        //String traderQueryEditable="SELECT trader.traderId AS \"Νο συναλλασσόμενου\", trader.surname AS \"επίθετο\", trader.name AS\"όνομα\", trader.fathername AS \"πατρόνυμο\", trader.traderAfm AS \"Α.Φ.Μ.\", trader.doyId, trader.idNo AS \"αρ ταυτοτ\", trader.townId, trader.address AS \"διέυθυνση\", trader.phone AS \"τηλέφωνο\" FROM trader, town WHERE trader.townId=town.townId";
        String traderQueryEditable="SELECT * FROM trader";// LEFT JOIN doy ON trader.doyId=doy.doyId";// LEFT JOIN bank ON trader.bankId=bank.bankId";        
        String[] fieldsOnTitletrader ={"traderId","title","vatNo"};
        String[] fieldsOnTitleCaptiontrader  ={"Νο","όνομα","ΑΦΜ"};
        String[] strtraderCategories = {DATAENTRY,METRICS};
        String[] fieldsUniquetrader = {"vatNo"};
        //STATS be careful to have in the query all the fields that are also in the title
        EntityPanel entityPaneltraderDataentry;// = new EntityPanel("ODOR","trader",traderDBFields,traderEntityGroupOfComps,traderEntityGroupOfPanels,"Νο συναλλασσόμενου","","traderId",traderQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniquetrader,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,entReportEsExDoc);      
        EntityPanel entityPaneltraderHistory;// = new EntityPanel("stattraderHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT trader.traderId, trader.dbCompanyId, sxesoexoheader.esoexoheaderId, sxactiontype.actionTypeCode, sxesoexoheader.esoexoCodeOfDocument,sxesoexoheader.dbYearId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.isPrinted, sxesoexoheader.countTotal,sxesoexoheader.quantityTotal, sxesoexoheader.pricePreVat, sxesoexoheader.priceVat, sxesoexoheader.priceTotal","FROM trader, sxesoexoheader, sxactiontype","WHERE trader.traderId = sxesoexoheader.traderId AND  = sxesoexoheader.dbCompanyId AND sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId AND trader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND trader.traderId =","","ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoCodeOfDocument",false,"",false,"",entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);     
        //EntityPanel entityPaneltraderProducts = new EntityPanel("stattraderProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.traderId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderBuyers = new EntityPanel("stattraderBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.traderId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderSalesPerDate = new EntityPanel("stattraderSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.traderId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPaneltrader;// = new EntityPanel[] { entityPaneltraderDataentry,entityPaneltraderHistory};//,entityPaneltraderStatistics,entityPaneltraderProducts,entityPaneltraderBuyers,entityPaneltraderSalesPerDate};
        

        //----------------------------------------------------------------
        
        EntityDBFields[] serviceDBFields = new EntityDBFields[13];
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] serviceEntityGroupOfComps =new EntityGroupOfComps[4];
        EntityGroupOfPanels[] serviceEntityGroupOfPanels = null;
        
        
        String serviceQueryEditable = "SELECT * FROM stock";//product.productId AS \"Νο προϊόντος\", product.productName AS \"ονομασία\", product.currencyId FROM product";
        String[] fieldsOnTitleService ={"stockId","descr"};
        String[] fieldsOnTitleCaptionService  ={"Νο","ονομασία"};      
        String[] strServiceCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueService = null;
        
       EntityCheckFields[] entityCheckFieldsService = null;
      
        
        
        EntityPanel entityPanelServiceDataentry = new EntityPanel("ODOR","stock",serviceDBFields,serviceEntityGroupOfComps,serviceEntityGroupOfPanels,"Νο υπηρεσίας","","stockId",serviceQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueService,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsService,null);  
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProductTraders = new EntityPanel("statProductTraders","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT trader.traderId AS \"νο αγρότη\", trader.surname, trader.name, trader.fatherName,trader.traderAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, trader","WHERE invoice.traderId = trader.traderId AND invoice.productId=","GROUP BY trader.traderId","ORDER BY trader.surname, trader.name, trader.fatherName,trader.traderAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelService = new EntityPanel[] { entityPanelServiceDataentry};//,entityPanelProductStatistics,entityPanelProductTraders,entityPanelProductBuyers,entityPanelProductSalesPerDate};
        
         //----------------------------------------------------------------        
    
 
   
        
        //----------------------------------------------------------------
        //EntityDBFields[] actionTypeLineDBFields = new EntityDBFields[7];
        EntityDBFields[] printFormDBFields = new EntityDBFields[9];

        
        EntityGroupOfComps[] printFormEntityGroupOfComps = new EntityGroupOfComps[4];
        EntityGroupOfPanels[] printFormEntityGroupOfPanels = new EntityGroupOfPanels[2];
        
        
        String printFormQueryEditable="SELECT * FROM printform";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitlePrintForm ={"printFormId","printFormName"};
        String[] fieldsOnTitleCaptionPrintForm  ={"Νο","ονομασία"};     
            String[] fieldsUniquePrintForm = null; 
            EntityCheckFields[] entityCheckFieldsPrintForm =null;
        EntityPanel entityPanelPrintFormDataentry = new EntityPanel("ODOR","printform",printFormDBFields,printFormEntityGroupOfComps,printFormEntityGroupOfPanels,"Νο φόρμας","","printFormId",printFormQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniquePrintForm,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsPrintForm,null);  
        EntityPanel[] entityPanelPrintForm = new EntityPanel[] {entityPanelPrintFormDataentry};        
        
        //----------------------------------------------------------------
        EntityDBFields[] activityCatDBFields = new EntityDBFields[2];

        
        EntityGroupOfComps[] activityCatEntityGroupOfComps =null;
        EntityGroupOfPanels[] activityCatEntityGroupOfPanels = null;
        
        
        String activityCatQueryEditable="SELECT * FROM activitycat";
        String[] fieldsOnTitleActivityCat ={"activityCatId","activityDescr"};
        String[] fieldsOnTitleCaptionActivityCat  ={"Νο","ονομασία"};   
        String[] fieldsUniqueActivityCat = null;  
        EntityCheckFields[] entityCheckFieldsActivityCat =null;
        EntityPanel entityPanelActivityCatDataentry = new EntityPanel("ODOR","activitycat",activityCatDBFields,activityCatEntityGroupOfComps,activityCatEntityGroupOfPanels,"Νο δραστηριότητας","","activityCatId",activityCatQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueActivityCat,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null, entityCheckFieldsActivityCat,null);  
        EntityPanel[] entityPanelActivityCat = new EntityPanel[] {entityPanelActivityCatDataentry};        
                
 
        //----------------------------------------------------------------
        EntityDBFields[] serviceCatLineDBFields = new EntityDBFields[3];
        EntityDBFields[] serviceCatDBFields = new EntityDBFields[1];

        
        EntityGroupOfComps[] serviceCatEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] serviceCatEntityGroupOfPanels = null;
        
        
        String serviceCatQueryEditable="SELECT * FROM stockcat";
        String[] fieldsOnTitleServiceCat ={"stockCatId","catDescr"};
        String[] fieldsOnTitleCaptionServiceCat  ={"Νο","ονομασία"};  
        String[] fieldsUniqueServiceCat = null;   
        EntityCheckFields[] entityCheckFieldsServiceCat =null;
        EntityPanel entityPanelServiceCatDataentry = new EntityPanel("ODOR","stockcat",serviceCatDBFields,serviceCatEntityGroupOfComps,serviceCatEntityGroupOfPanels,"Νο κατηγορίας","","stockCatId",serviceCatQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueServiceCat,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsServiceCat,null);  
        EntityPanel[] entityPanelServiceCat = new EntityPanel[] {entityPanelServiceCatDataentry};         
        
        //----------------------------------------------------------------
        /*EntityDBFields[] vatCatLineDBFields = new EntityDBFields[5];
        EntityDBFields[] vatCatDBFields = new EntityDBFields[1];

        
        EntityGroupOfComps[] vatCatEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] vatCatEntityGroupOfPanels = null;*/
        
        
 
        // same as DialogLogin
 
        
        //----------------------------------------------------------------
    /*    EntityDBFields[] dbuserDBFields = new EntityDBFields[4];

        EntityGroupOfComps dbuserEntityGroupOfComps[] =null;
        EntityGroupOfPanels dbuserEntityGroupOfPanels[] = null;
        
        
        String dbUserQueryEditable="SELECT userId AS\"Νο χρήστη\", username AS\"όνομα χρήστη\", password, nameOfUser AS\"πλήρες όνομα χρήστη\" FROM dbuser";
        String[] fieldsOnTitleDbuser ={"userId","userName","nameOfUser"};
        String[] fieldsOnTitleCaptionDbuser  ={"Νο","user","πλήρες όνομα"};    
        String[] fieldsUniqueDbUser = {"userName"}; 
        EntityCheckFields[] entityCheckFieldsDbuser =null;        
        EntityPanel entityPanelDbuserDataentry = new EntityPanel("ODOR","dbuser",dbuserDBFields,dbuserEntityGroupOfComps,dbuserEntityGroupOfPanels,"Νο χρήστη","","userId",dbUserQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbUser,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsDbuser,null);  
        EntityPanel[] entityPanelDbuser = new EntityPanel[] { entityPanelDbuserDataentry}; */

        //----------------------------------------------------------------
        EntityDBFields[] dbyearDBFields = new EntityDBFields[1];
        EntityDBFields[] dbyearLineDBFields = new EntityDBFields[3];
        //EntityDBFields[] dbYearDeliveryDBFields= new EntityDBFields[5];

        EntityGroupOfComps[] dbyearEntityGroupOfComps =new EntityGroupOfComps[1];
        EntityGroupOfPanels[] dbyearEntityGroupOfPanels = null;
        
        
        String dbyearQueryEditable="SELECT dbYearId AS \"Νο χρήσης\", dbyear AS\"χρήση\", dbCompanyId AS \"Νο εταιρίας\" FROM dbyear";
        String[] fieldsOnTitleDbyear ={"dbYearId","dbyear"};
        String[] fieldsOnTitleCaptionDbyear  ={"Νο χρήσης","χρήση"};     
        String[] fieldsUniqueDbYear = {"dbyear"}; 
        EntityCheckFields[] entityCheckFieldsDbYear =null;  
        EntityPanel entityPanelDbyearDataentry = new EntityPanel("ODOR","dbyear",dbyearDBFields,dbyearEntityGroupOfComps,dbyearEntityGroupOfPanels,"Νο χρήσης","","dbYearId",dbyearQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbYear,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsDbYear,null);  
        EntityPanel[] entityPanelDbyear = new EntityPanel[] { entityPanelDbyearDataentry};         
        
        //----------------------------------------------------------------
        
      /*  EntityGroupOfComps[] bankEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] bankEntityGroupOfPanels = null;
        
       EntityDBFields[] bankDBFields = new EntityDBFields[1];
       EntityDBFields[] bankLineDBFields = new EntityDBFields[3];

        String bankQueryEditable="SELECT * FROM bank";//bankId AS \"Νο τράπεζας\", bankBranch FROM bank";
        String[] fieldsOnTitleBank ={"bankId","bankBranch"};
        String[] fieldsOnTitleCaptionBank  ={"Νο","ονομασία"}; 
        String[] fieldsUniqueBank = null;    
        EntityCheckFields[] entityCheckFieldsBank =null;  
        EntityPanel entityPanelBankDataentry = new EntityPanel("ODOR","bank",bankDBFields,bankEntityGroupOfComps,bankEntityGroupOfPanels,"Νο τράπεζας","","bankId",bankQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueBank,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsBank,null);  
        EntityPanel[] entityPanelBank = new EntityPanel[] { entityPanelBankDataentry};  
        */


        
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
       
        
       
     

       
       boolean[] boolSettingsReportDoc = {true,true,true,true,true}; 
      boolean[] boolSettingsReportTraderfileA = {true,true,true,true,true}; 
      boolean[] boolSettingsReportTraderfileB = {true,true,true,true,true}; 
       int[] intSettingsReportTraderfile={0,0,0,0};
 //       EntityReportBandField[] entityReportBandFieldsSaleLineA =new EntityReportBandField[10];          
 //       EntityReportBandField[] entityReportBandFieldsSaleHeaderB =new EntityReportBandField[7];
       EntityReportBand[] reportBandTraderServiceSaleDoc = new EntityReportBand[2];  
          

   public EntityDataSerSales()
   {
       
          // need them
   	  dTree = new DataTree();
   	  nodeRoot = new DataTreeNode("root");
   	  dTree.setRootElement(nodeRoot);

          // deliveryQueryEditable = "SELECT d.traderId, d.dateOfApplication, d.dbyear, d.deliveryId, d.dbCompanyId FROM application d WHERE dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+" AND dbCompanyId="+VariablesGlobal.globalCompanyId;

       //System.out.println("EntityData"+entityPanelTrader[0].getType());

/*
    FIELD_NOCOMPLETION = 0;
    FIELD_OBLIGATORY = 1;
    FIELD_SUGGEST = 2;
    
    FIELD_VALIDATION_NO = 0  FIELD_VALIDATION_AFM = 1
*/

 
      
      int[] inputFieldsTraderInHeader ={7}; // 7 is traderId
      int[] inputFieldsOfNetValue ={15}; // 15 is pricePreVat
      
      EntityCheckFields[] entityCheckFieldsVATCompanyOfHeader =new EntityCheckFields[2];
             entityCheckFieldsVATCompanyOfHeader[0] = new EntityCheckFields(CHECK_ON_ENTRY,"SELECT IF((SELECT trader.vatNo FROM trader WHERE trader.traderId = #) " +
             "= (SELECT dbcompany.companyVatNo FROM dbcompany, dbcompanyset WHERE dbcompany.dbCompanyId = dbcompanyset.dbCompanyId AND dbcompany.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND dbcompanyset.sersaleCheckAFMOfSaleAndComp = 1 )" +
             ",1,0)",inputFieldsTraderInHeader, 7,"Το ΑΦΜ του συναλλασσόμενου είναι ίδιο με αυτό της επιχείρησης στην οποία εργάζεστε.");
             entityCheckFieldsVATCompanyOfHeader[1] = new EntityCheckFields(CHECK_ON_INSERT_OR_ON_UPDATE,"SELECT IF( # >= (SELECT dbcompanyset.sersaleMaxOfCashNetValue FROM dbcompanyset " +
              "WHERE dbcompanyset.dbCompanyId = "+VariablesGlobal.globalCompanyId+"  AND dbcompanyset.sersaleMaxOfCashCheck = 1),1,0)",inputFieldsOfNetValue,0, "Το παραστατικό θα πρέπει να εξοφλήθεί με τραπεζικό τρόπο.");
                     
           
      
        int[] saleFileHeaderOrderby ={1}; 
        int[] saleFileLineOrderby ={1}; 

        
        
EntityFilterSettings[] salesDocumentErs = new EntityFilterSettings[7] ; 
     salesDocumentErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       //invoiceErs[1]=new EntityFilterSettings("Νο συναλλασσόμενου","lookup","string","fromto","traderId","trader","trader","",1,-1,-1,FIELD_NOCOMPLETION);
       salesDocumentErs[1]=new EntityFilterSettings("επίθετο","","string","equals","title","trader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       salesDocumentErs[2]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","trader","trader","",1,-1,-1,FIELD_NOCOMPLETION);
       salesDocumentErs[3]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","trader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[6]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","f","",1,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[7]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","f","",1,-1,-1,FIELD_NOCOMPLETION);
       salesDocumentErs[4]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfSale","saleheader",null,"",1,0,-1,FIELD_NOCOMPLETION);
      // invoiceErs[6]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","d",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       salesDocumentErs[5]=new EntityFilterSettings("τελικό ποσό","","double","fromto","priceTotal","saleheader",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
       salesDocumentErs[6]=new EntityFilterSettings("υπηρεσία","checkboxTable","string","","stockId","stock","saleline","",2,-1,-1,FIELD_NOCOMPLETION);
       
        
       EntityGroupOfComps[] saleDocumentGroupOfComps = new EntityGroupOfComps[3];
       saleDocumentGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       saleDocumentGroupOfComps[1] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       saleDocumentGroupOfComps[2] = new EntityGroupOfComps("υπηρεσίες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);      
       int[] invoicesaSelected = null;//{1,2,3,4,0,0,0,0,0,0,11,12,0,14,};        
        

       reportBandTraderServiceSaleDoc[0] = new EntityReportBand("saleheader","πώληση","saleheader",null/*entityReportBandFieldsSaleHeaderB*/,saleFileHeaderOrderby,"saleHeaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReportTraderfileA,entityPanelSale,null,null);//,"","");
       reportBandTraderServiceSaleDoc[1] = new EntityReportBand("saleline","υπηρεσίες","saleline",null/*entityReportBandFieldsSaleLineA*/,saleFileLineOrderby,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReportTraderfileB,entityPanelSale,null,null);//,"","");                  
          
          
        entReportServiceSaleDoc = new EntityReport("servicesaledoc", REPORT_CAT_1,reportBandTraderServiceSaleDoc,"SELECT * FROM actionseries LEFT JOIN printform ON actionseries.printFormId = printform.printFormId AND actionseries.dbCompanyId = printform.dbCompanyId  AND printform.isActive = 1 , trader, saleline, stock, saleheader, actiontype LEFT JOIN actiontypecopy ON actiontypecopy.actionTypeId = actiontype.actionTypeId AND  actiontypecopy.dbCompanyId = actiontype.dbCompanyId WHERE actiontype.actionTypeId = actionseries.actionTypeId AND actiontype.dbCompanyId = actionseries.dbCompanyId AND saleheader.traderId = trader.traderId AND saleheader.saleHeaderId = saleline.saleHeaderId AND saleline.stockId = stock.stockId AND actionseries.actionseriesId = saleheader.actionseriesId AND saleheader.dbCompanyId = actionseries.dbCompanyId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleheader.isPrinted = actiontypecopy.copyNo ",""/*ORDER BY saleline.inc"*/,"FORM","εκτύπωση παρ/κών","",salesDocumentErs,saleDocumentGroupOfComps,invoicesaSelected, null,
               // "SELECT saleheader.saleheaderId, saleheader.actionseriesId,actionseries.actionSeriesCode, printform.printFormName, printform.printFormLaser, printform.printFormDotMatrix, actionseries.askForPrint " +
               // " FROM actionseries, printform, saleheader" +
                " ","printform.printFormLaser","Θα πρέπει να επιλέξετε φόρμα στο αντίστοιχο παραστατικό.",intSettingsReportTraderfile,boolSettingsReportDoc,"actionseries.askForPrint");//,globalYearPlusOne) ;

            
            //panels
        entityPanelSaleDataentry = new EntityPanel("ODOR","saleheader",saleDBFields,saleEntityGroupOfComps,saleEntityGroupOfPanels,"Νο πώλησης","","saleHeaderId",saleQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSale,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,updateAdditionalActionType,entReportServiceSaleDoc,null,entityCheckFieldsVATCompanyOfHeader,null);          
        entityPanelSale = new EntityPanel[] { entityPanelSaleDataentry};
            
        
//        EntityCheckFields[] entityCheckFieldstrader = null;
//        entityPanelTraderDataentry = new EntityPanel("ODOR","trader",traderDBFields,traderEntityGroupOfComps,traderEntityGroupOfPanels,"Νο συναλλασσόμενου","","traderId",traderQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueTrader,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null/*hr*/,null,entityCheckFieldstrader,null);  
/*        entityPanelTraderHistory = new EntityPanel("statTraderHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT saleheader.saleHeaderId AS'id', actionseries.actionSeriesCode AS 'κωδ', saleheader.saleCodeOfDocument, saleheader.saleCodeNo, saleheader.dateOfSale AS 'ημερομηνία', saleheader.isPrinted, saleheader.countTotal AS 'πλήθος',saleheader.quantityTotal, saleheader.pricePreVat AS 'προ ΦΠΑ', saleheader.priceVat AS'ΦΠΑ', saleheader.priceTotal AS'σύνολο'","FROM trader, saleheader, actionseries","WHERE trader.traderId = saleheader.traderId AND trader.dbCompanyId = saleheader.dbCompanyId AND actionseries.dbCompanyId = saleheader.dbCompanyId AND actionseries.actionseriesId = saleheader.actionseriesId AND trader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND trader.traderId =","","ORDER BY saleheader.dateOfSale, saleheader.saleCodeOfDocument",false,"",false,"",entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);     
        //EntityPanel entityPanelTraderProducts = new EntityPanel("statTraderProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.traderId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelTraderBuyers = new EntityPanel("statTraderBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.traderId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelTraderSalesPerDate = new EntityPanel("statTraderSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.traderId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        entityPanelTrader = new EntityPanel[] { entityPanelTraderDataentry,entityPanelTraderHistory};//,entityPanelTraderStatistics,entityPanelTraderProducts,entityPanelTraderBuyers,entityPanelTraderSalesPerDate};
            
            
    
        
        
        EntityDBFields[] traderBankaccountDBFields = new EntityDBFields[10];
        
        traderBankaccountDBFields[0] = new EntityDBFields("traderbankaccount","traderBankAccountId","traderBankAccountId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
        traderBankaccountDBFields[1] = new EntityDBFields("traderbankaccount","traderId","Νο συναλλασσόμενου",0,"java.lang.String",6, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
        traderBankaccountDBFields[2] = new EntityDBFields("traderbankaccount","dbCompanyId","dbCompanyId",0,"java.lang.String",6,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
        traderBankaccountDBFields[3] = new EntityDBFields("traderbankaccount","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
        traderBankaccountDBFields[4] = new EntityDBFields("traderbankaccount","bankId","τράπεζα",2,"java.lang.Integer",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
	traderBankaccountDBFields[5] = new EntityDBFields("traderbankaccount","account","λογαριασμός",2,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	traderBankaccountDBFields[6] = new EntityDBFields("traderbankaccount","iban","ΙΒΑΝ",2,"java.lang.String",29,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderBankaccountDBFields[7] = new EntityDBFields("traderbankaccount","firstName","1ο ονοματεπώνυμο",0,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderBankaccountDBFields[8] = new EntityDBFields("traderbankaccount","branch","κατάστημα",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderBankaccountDBFields[9] = new EntityDBFields("traderbankaccount","bic","BIC",0,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        

          
        traderDBFields[0] = new EntityDBFields("trader","traderId","Νο συναλλασσόμενου",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //traderDBFields[1] = new EntityDBFields("trader","surname","επίθετο",0,"java.lang.String",20, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,true,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,  false,null);
        traderDBFields[1] = new EntityDBFields("trader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
        traderDBFields[2] = new EntityDBFields("trader","title","επωνυμία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[3] = new EntityDBFields("trader","traderCode","κωδικός",0,"java.lang.String",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[4] = new EntityDBFields("trader","vatNo","Α.Φ.Μ.",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_AFM,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[5] = new EntityDBFields("trader","activityDescr","δραστηριότητα",0,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[6] = new EntityDBFields("trader","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");               
        traderDBFields[7] = new EntityDBFields("trader","activityCatId","κατηγορία δραστηριότητας",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"activityCat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[8] = new EntityDBFields("trader","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[9] = new EntityDBFields("trader","geoCatId","γεωγραφική κατηγορία",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"geocat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        
        traderDBFields[10] = new EntityDBFields("trader","addressStreet","οδός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[11] = new EntityDBFields("trader","addressCity","πόλη/χωριό",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[12] = new EntityDBFields("trader","addressPC","ΤΚ",1,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[13] = new EntityDBFields("trader","addressState","νομός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[14] = new EntityDBFields("trader","telephone1","τηλέφωνο 1",1,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[15] = new EntityDBFields("trader","telephone2","τηλέφωνο 2",1,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        traderDBFields[16] = new EntityDBFields("trader","fax","fax",1,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[17] = new EntityDBFields("trader","email","email",1,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        traderDBFields[18] = new EntityDBFields("trader","typeofVatExclusionId","καθεστώς ΦΠΑ",2,"java.lang.Integer",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCVatExclusion",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");
        traderDBFields[19] = new EntityDBFields("trader","discountPercentage","έκπτωση %",2,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");     
        traderDBFields[20] = new EntityDBFields("trader","paymenttypeId","τρόπος πληρωμής",2,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"paymenttype",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[21] = new EntityDBFields("trader","currencyId","νόμισμα",2,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"currency",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
        
        traderDBFields[22] = new EntityDBFields("trader","notes","σημειώσεις",3,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");


        //traderDBFields[22] = new EntityDBFields("trader","contacts","επαφές",5,"table",FIELD_VISIBLE_AND_EDITABLE,"tradercontact",160,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,traderContactDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM tradercontact WHERE tradercontact.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY tradercontact.inc",null);        
        
        traderDBFields[23] = new EntityDBFields("trader","bank accounts","λογαριασμοί τραπεζών",4,"table",FIELD_VISIBLE_AND_EDITABLE,"traderbankaccount",120,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,traderBankaccountDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM traderbankaccount WHERE traderbankaccount.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY traderbankaccount.inc",null,null);        
        
        
        
        
        traderEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        traderEntityGroupOfComps[1] = new EntityGroupOfComps("επικοινωνία",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       // traderEntityGroupOfComps[2] = new EntityGroupOfComps("επικοινωνία",4,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        traderEntityGroupOfComps[2] = new EntityGroupOfComps("χρηματικά",6,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        traderEntityGroupOfComps[3] = new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //traderEntityGroupOfComps[5] = new EntityGroupOfComps("επαφές",1,1); // if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
        traderEntityGroupOfComps[4] = new EntityGroupOfComps("λογαριασμοί τραπεζών",1,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
        
        
        
        
        
        traderEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        //traderEntityGroupOfPanels[1] = new EntityGroupOfPanels("επικοινωνία",1);
        traderEntityGroupOfPanels[1] = new EntityGroupOfPanels("χρηματικά",1);
        //traderEntityGroupOfPanels[3] = new EntityGroupOfPanels("σημειώσεις",1);
*/
        
        EntityCheckFields[] entityCheckFieldsTrader = null;
        entityPaneltraderDataentry = new EntityPanel("ODOR","trader",traderDBFields,traderEntityGroupOfComps,traderEntityGroupOfPanels,"Νο συναλλασσόμενου","","traderId",traderQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniquetrader,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null/*hr*/,null,entityCheckFieldsTrader,null);  
        entityPaneltraderHistory = new EntityPanel("stattraderHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT sxesoexoheader.traderId, sxesoexoheader.esoexoheaderId, sxactiontype.actionTypeCode, sxesoexoheader.esoexoCodeOfDocument,sxesoexoheader.dbYearId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.isPrinted, sxesoexoheader.countTotal, sxesoexoheader.pricePreVat, sxesoexoheader.priceVat, sxesoexoheader.priceTotal, sxesoexoheader.isTemplate","FROM sxesoexoheader LEFT JOIN sxactiontype ON sxesoexoheader.sxActionTypeId = sxactiontype.sxActionTypeId","WHERE sxesoexoheader.isTemplate ='0'  AND sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoheader.traderId =","","ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoCodeOfDocument",false,"",false,"",entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);     
        //EntityPanel entityPaneltraderProducts = new EntityPanel("stattraderProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.traderId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderBuyers = new EntityPanel("stattraderBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.traderId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderSalesPerDate = new EntityPanel("stattraderSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.traderId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        entityPaneltrader = new EntityPanel[] { entityPaneltraderDataentry,entityPaneltraderHistory};//,entityPaneltraderStatistics,entityPaneltraderProducts,entityPaneltraderBuyers,entityPaneltraderSalesPerDate};
            
            
    
        
        
        EntityDBFields[] traderBankaccountDBFields = new EntityDBFields[9];
        
        traderBankaccountDBFields[0] = new EntityDBFields("traderbankaccount","traderBankAccountId","traderBankAccountId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        traderBankaccountDBFields[1] = new EntityDBFields("traderbankaccount","traderId","Νο συναλλασσόμενου",0,"java.lang.String",6, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       //traderBankaccountDBFields[2] = new EntityDBFields("traderbankaccount","dbCompanyId","dbCompanyId",0,"java.lang.String",6,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        traderBankaccountDBFields[2] = new EntityDBFields("traderbankaccount","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
        traderBankaccountDBFields[3] = new EntityDBFields("traderbankaccount","bankId","τράπεζα",2,"java.lang.Integer",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
	traderBankaccountDBFields[4] = new EntityDBFields("traderbankaccount","account","λογαριασμός",2,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	traderBankaccountDBFields[5] = new EntityDBFields("traderbankaccount","iban","ΙΒΑΝ",2,"java.lang.String",29,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderBankaccountDBFields[6] = new EntityDBFields("traderbankaccount","firstName","1ο ονοματεπώνυμο",0,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderBankaccountDBFields[7] = new EntityDBFields("traderbankaccount","branch","κατάστημα",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderBankaccountDBFields[8] = new EntityDBFields("traderbankaccount","bic","BIC",0,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
                
        
          
          

        EntityDBFields[] traderContactDBFields = new EntityDBFields[7];
        
        traderContactDBFields[0] = new EntityDBFields("tradercontact","traderContactId","traderContactId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        traderContactDBFields[1] = new EntityDBFields("tradercontact","traderId","Νο συναλλασσόμενου",0,"java.lang.String",6, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        traderContactDBFields[2] = new EntityDBFields("tradercontact","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //traderContactDBFields[3] = new EntityDBFields("tradercontact","dbCompanyId","dbCompanyId",0,"java.lang.String",6,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        
        
        traderContactDBFields[3] = new EntityDBFields("tradercontact","location","τοποθεσία ή τμήμα",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderContactDBFields[4] = new EntityDBFields("tradercontact","person","πρόσωπο",0,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderContactDBFields[5] = new EntityDBFields("tradercontact","phone","τηλέφωνο",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderContactDBFields[6] = new EntityDBFields("tradercontact","email","email",0,"java.lang.String",22,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
          
        traderDBFields[0] = new EntityDBFields("trader","traderId","Νο συναλλασσόμενου",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //traderDBFields[1] = new EntityDBFields("trader","surname","επίθετο",0,"java.lang.String",20, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,true,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,  false,null);
        //traderDBFields[1] = new EntityDBFields("trader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        traderDBFields[1] = new EntityDBFields("trader","title","επωνυμία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[2] = new EntityDBFields("trader","traderCode","κωδικός",0,"java.lang.String",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[3] = new EntityDBFields("trader","vatNo","Α.Φ.Μ.",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_AFM,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[4] = new EntityDBFields("trader","activityDescr","δραστηριότητα",0,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[5] = new EntityDBFields("trader","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                    
        traderDBFields[6] = new EntityDBFields("trader","activityCatId","κατηγορία δραστηριότητας",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"activityCat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[7] = new EntityDBFields("trader","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[8] = new EntityDBFields("trader","geoCatId","γεωγραφική κατηγορία",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"geocat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        
        traderDBFields[9] = new EntityDBFields("trader","addressStreet","οδός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[10] = new EntityDBFields("trader","addressCity","πόλη/χωριό",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[11] = new EntityDBFields("trader","addressPC","ΤΚ",1,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[12] = new EntityDBFields("trader","addressState","νομός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
                
        traderDBFields[13] = new EntityDBFields("trader","telephone","τηλέφωνο",2,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[14] = new EntityDBFields("trader","fax","fax",2,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[15] = new EntityDBFields("trader","email","email",2,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        traderDBFields[16] = new EntityDBFields("trader","currencyId","νόμισμα",3,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"currency",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        traderDBFields[17] = new EntityDBFields("trader","typeofVatExclusionId","καθεστώς ΦΠΑ",3,"java.lang.Integer",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCVatExclusion",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");
        traderDBFields[18] = new EntityDBFields("trader","discountPercentage","έκπτωση %",3,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");     
        traderDBFields[19] = new EntityDBFields("trader","paymenttypeId","τρόπος πληρωμής",3,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"paymenttype",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

        traderDBFields[20] = new EntityDBFields("trader","notes","σημειώσεις",4,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");


        traderDBFields[21] = new EntityDBFields("trader","contacts","επαφές",5,"table",FIELD_VISIBLE_AND_EDITABLE,"tradercontact",160,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,traderContactDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM tradercontact ORDER BY tradercontact.inc",null,null,null,null);        
        
        traderDBFields[22] = new EntityDBFields("trader","bank accounts","λογαριασμοί τραπεζών",6,"table",FIELD_VISIBLE_AND_EDITABLE,"traderbankaccount",120,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,traderBankaccountDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM traderbankaccount ORDER BY traderbankaccount.inc",null,null,null,null);        
        
        
        
        
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
        
        
        EntityDBFields[] saleLineDBFields = new EntityDBFields[13];        
        saleLineDBFields[0] = new EntityDBFields("saleline","saleLineId","saleLineId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
        saleLineDBFields[1] = new EntityDBFields("saleline","saleHeaderId","Νο πώλησης",0,"java.lang.String",5, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
        saleLineDBFields[2] = new EntityDBFields("saleline","dbCompanyId","dbCompanyId",0,"java.lang.String",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
        saleLineDBFields[3] = new EntityDBFields("saleline","inc","αα",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
        
        String calculationVatPercentageSql = "SELECT CASE # "
                + "WHEN 1 THEN (SELECT vatcat.vatPercentage FROM stock, vatcat  WHERE stock.vatCatId=vatcat.vatCatId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId LIKE # )"
                + "WHEN 2 THEN (SELECT vr.vatPercentage FROM stock, vatcat vn LEFT JOIN vatcat vr ON vr.vatCatId = vn.vatReducedCat WHERE stock.vatCatId=vn.vatCatId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId LIKE # )"
                + "WHEN 3 THEN 0 "
                + "ELSE '' END";
        int[] inputServiceCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputService ={4};
        
        int[] inputVatCategory ={FIELDSCALCULATION_CATEGORY_BACKWARD,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVat ={10,4,4};//field        
        //10,inputService,"SELECT vatcat.vatPercentage FROM stock, vatcat  WHERE stock.vatCatId=vatcat.vatCatId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId=");
        EntityDBFieldsCalculation[] fieldsCalculationServiceSelect = new EntityDBFieldsCalculation[3];
        fieldsCalculationServiceSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,6,inputServiceCategory,inputService,"SELECT stock.proposeQuantity FROM stock  WHERE stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId LIKE #");        
        fieldsCalculationServiceSelect[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputServiceCategory,inputService,"SELECT stock.priceWhole FROM stock, vatcat  WHERE stock.vatCatId=vatcat.vatCatId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId LIKE #");
        fieldsCalculationServiceSelect[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,10,inputVatCategory,inputVat,calculationVatPercentageSql);
       
        saleLineDBFields[4] = new EntityDBFields("saleline","stockId","υπηρεσία",2,"java.lang.Integer",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"stock",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationServiceSelect,"");
	saleLineDBFields[5] = new EntityDBFields("saleline","description","περ/φή υπηρεσίας",2,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       
        int[] inputQuantityPriceCategory = {FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputQuantityPrice = {6,7};
       EntityDBFieldsCalculation[] fieldsCalculationQuantitySelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationQuantitySelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,9,inputQuantityPriceCategory,inputQuantityPrice,"SELECT #*#");
       //9,inputPriceBeforeVat,"*");//,"SELECT vatcat.priceWhole FROM stock, vatcat  WHERE stock.vatCatId=vatcat.vatCatId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId=");
        saleLineDBFields[6] = new EntityDBFields("saleline","quantity","ποσότητα",1,"java.lang.Integer",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationQuantitySelect,"0");
        
        
       EntityDBFieldsCalculation[] fieldsCalculationPriceSelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationPriceSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,9,inputQuantityPriceCategory,inputQuantityPrice,"SELECT #*#");        
        saleLineDBFields[7] = new EntityDBFields("saleline","priceForOne","τιμή",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationPriceSelect,"0");
        //saleLineDBFields[8] = new EntityDBFields("saleline","discountPercentage","εκπτωση %",1,"java.lang.Double",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"0");
        saleLineDBFields[8] = new EntityDBFields("saleline","dbYearId","dbYearId",1,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalYearId,"");
        
        //int[] inputPreVatCategorySums ={FIELDSCALCULATION_CATEGORY_SAME};
        //int[] inputPreVatSums ={9};//field
        
        int[] inputPriceBeforeVatCategoryCell ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};// for cells
        int[] inputPriceBeforeVatCell ={9,10};// for cells
       EntityDBFieldsCalculation[] fieldsCalculationPriceBeforeVatSelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationPriceBeforeVatSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,11,inputPriceBeforeVatCategoryCell,inputPriceBeforeVatCell,"SELECT #*#/100"); // for each cell
       //fieldsCalculationPriceBeforeVat[1] = new EntityDBFieldsCalculation(11,11,inputPreVatCategorySums,inputPreVatSums,"SELECT #");// for sums
        saleLineDBFields[9] = new EntityDBFields("saleline","priceBeforeVat","προ ΦΠΑ",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,fieldsCalculationPriceBeforeVatSelect,"");

        
        int[] inputVatPercentCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVatPercentValue ={9,10};
       EntityDBFieldsCalculation[] fieldsCalculationVatPercentValueSelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationVatPercentValueSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,11,inputVatPercentCategory,inputVatPercentValue,"SELECT #*#/100");         
        saleLineDBFields[10] = new EntityDBFields("saleline","vatPercentage","ΦΠΑ %",1,"java.lang.Double",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,fieldsCalculationVatPercentValueSelect,"");
        
        int[] inputVatValueCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputVatValue ={9,11};
       EntityDBFieldsCalculation[] fieldsCalculationVatValueSelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationVatValueSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,12,inputVatValueCategory,inputVatValue,"SELECT #+#");         
        saleLineDBFields[11] = new EntityDBFields("saleline","vatValue","αξία ΦΠΑ",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationVatValueSelect,"");
        
        int[] inputValueWithVatCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputValueWithVat ={12,10,6};
       EntityDBFieldsCalculation[] fieldsCalculationValueWithVatSelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationValueWithVatSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputValueWithVatCategory,inputValueWithVat,"SELECT #/(100+#)*100/#");         
        saleLineDBFields[12] = new EntityDBFields("saleline","valueWithVat","αξία",1,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationValueWithVatSelect,"");                     
        
        
        
        String[] updateQueryFieldsSaleHeader ={"actionseriesId"};
        updateAdditionalActionType[0] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,UPDATEADDITIONAL_ENABLED,null,null,null,"UPDATE actionseries SET seriesNextNumber = (seriesNextNumber + 1 ) WHERE actionseriesId LIKE # AND dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,updateQueryFieldsSaleHeader,null,null,null);                
        String[] updateQueryFieldsEsoexoHeader ={"actionSeriesId","actionSeriesId","dateOfSale","traderId","comments", "pricePreVat", "priceVat", "priceTotal"};
         String[] updateQueryFieldsEsoexoLine ={"saleHeaderId", "saleHeaderId", "saleHeaderId", "saleHeaderId","saleHeaderId"};
         String[] updateQueryFieldsEsoexoHeaderUpd ={"saleHeaderId"};
         String[] updateQueryFieldsEsoexoLineUpd = {"saleHeaderId", "saleHeaderId", "saleHeaderId", "saleHeaderId"};
         
         
        //updateAdditionalParameters[0] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,"INSERT INTO parameter (parameterId,dbCompanyId,lengthOfCodeOfDocuments,charOfDecimal,lengthOfDecimalPrice) VALUES(1,#, 4,',',2)" ,updateQueryFieldsParameters);
        //updateAdditionalDbCompany[0] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,"INSERT INTO dbyear (dbYearId,dbyear,dbCompanyId) VALUES(1,"+VariablesGlobal.globalYear+",#)" ,updateQueryFieldsCompany);
        //String queryIsSxRecordActive = "SELECT actiontype.isSxRecordActive FROM actionseries, actiontype  WHERE actionseries.actionTypeId = actiontype.actionTypeId AND actiontype.dbCompanyId = actionseries.dbCompanyId AND actiontype.dbCompanyId LIKE " +VariablesGlobal.globalCompanyId+" AND actionseries.actionSeriesId LIKE # ";
        //SELECT actiontype.sxActionTypeId FROM actiontype WHERE actiontype.dbCompanyId LIKE " +VariablesGlobal.globalCompanyId+" AND actiontype.actionTypeId LIKE # ";
        String querySxActionTypeId = "SELECT actiontype.sxActionTypeId FROM actionseries, actiontype WHERE actionseries.actionTypeId = actiontype.actionTypeId AND actiontype.dbCompanyId = actionseries.dbCompanyId AND actiontype.dbCompanyId LIKE " +VariablesGlobal.globalCompanyId+" AND actionseries.actionSeriesId LIKE # ";
      
        
        EntityDBFields[] bridgeEsoexoHeaderDBFields = new EntityDBFields[3];
        bridgeEsoexoHeaderDBFields[0] = new EntityDBFields("sxesoexoheader","isTemplate","isTemplate",0,"java.lang.String",3,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"0","");
        bridgeEsoexoHeaderDBFields[1] = new EntityDBFields("sxesoexoheader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        bridgeEsoexoHeaderDBFields[2] = new EntityDBFields("sxesoexoheader","esoexoheaderId","Νο εσόδων εξόδων",1,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //bridgeEsoexoHeaderDBFields[3] = new EntityDBFields("sxesoexoheader","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        
        String queryAccount1 = "SELECT actiontype.sxAccount1 FROM saleline, saleheader, actionseries, actiontype WHERE saleline.saleHeaderId = saleheader.saleHeaderId AND actionseries.actionTypeId = actiontype.actionTypeId AND saleheader.actionseriesId = actionseries.actionseriesId AND actionseries.dbCompanyId = actiontype.dbCompanyId AND saleheader.dbCompanyId = actionseries.dbCompanyId "+
                "AND actionseries.dbCompanyId = saleline.dbCompanyId AND saleline.dbCompanyId = saleheader.dbCompanyId AND saleline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleline.saleHeaderId LIKE # AND saleline.stockId LIKE s AND saleline.salelineid LIKE l";
        String queryAccount2 = "SELECT stock.sxAccount2 FROM saleline, stock WHERE saleline.stockId = stock.stockId AND saleline.dbCompanyId = stock.dbCompanyId AND saleline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  AND saleline.saleHeaderId LIKE # AND saleline.stockId LIKE s AND saleline.salelineid LIKE l";
        String queryAccount3 = "SELECT stock.sxAccount3 FROM saleline, stock WHERE saleline.stockId = stock.stockId AND saleline.dbCompanyId = stock.dbCompanyId AND saleline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  AND saleline.saleHeaderId LIKE # AND saleline.stockId LIKE s AND saleline.salelineid LIKE l";
        
        String[] updateFieldsIfBridge ={"actionSeriesId"};
        String queryIfBridgeIsEnabled = "SELECT actiontype.isSxRecordActive FROM actiontype, actionseries WHERE actiontype.dbCompanyId = actionseries.dbCompanyId AND actiontype.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  AND actiontype.actionTypeId = actionseries.actionTypeId AND actionseries.actionseriesId LIKE #";  // if is enabled return 1, else 0
        
        String queryAccountId = "SELECT sxaccount.accountId FROM sxaccount WHERE sxaccount.accountCode LIKE( SELECT CONCAT( ( "+queryAccount1+" ),'-',( "+queryAccount2+" ),'-',( "+queryAccount3+" )))";
        String queryIsCredit = "SELECT isCredit FROM actionseries, actiontype WHERE actionseries.actionTypeId = actiontype.actionTypeId AND actiontype.dbCompanyId = actionseries.dbCompanyId  "+
        " AND actiontype.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND actionseries.actionSeriesId LIKE #";
                
        //&@ is fot pk of header, &$ is for each row
        updateAdditionalActionType[1] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,queryIfBridgeIsEnabled,updateFieldsIfBridge,null,null,"INSERT INTO sxesoexoheader ( `esoexoHeaderId`, `sxActionTypeId`,oppositeSign,`dbCompanyId`, `saleHeaderId`, dateOfesoexo,  dbYearId, traderId, isTemplate, comments, pricePreVat, priceVat, priceTotal, sourceModuleId) VALUES (&@,("+querySxActionTypeId+"), ("+queryIsCredit+"), '"+VariablesGlobal.globalCompanyId+"',&b,#,"+VariablesGlobal.globalYearId+", # , 0, '#' ,'#', '#', '#',2 ) ",updateQueryFieldsEsoexoHeader,"sxesoexoheader",null,bridgeEsoexoHeaderDBFields);  // &b is for the header of original id like saleheaderid 
        updateAdditionalActionType[2] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,queryIfBridgeIsEnabled,updateFieldsIfBridge,"salelines",queryAccountId,"INSERT INTO sxesoexoline ( `esoexoHeaderId`, `dbCompanyId`,  dbYearId, isTemplate, esoexoLineId, inc,dummy, dummy2, accountId,  priceBeforeVat, vatValue, valueWithVat, saleHeaderId ) SELECT &@, '"+VariablesGlobal.globalCompanyId+"',"+VariablesGlobal.globalYearId+",0, ROW_NUMBER() OVER (ORDER BY accountid), ROW_NUMBER() OVER (ORDER BY accountid), stockId AS s , saleline.salelineid AS l,("+queryAccountId+") AS accountid , SUM(priceBeforeVat), SUM(vatValue), SUM(valueWithVat), # FROM saleline WHERE saleheaderid LIKE # GROUP BY accountid",updateQueryFieldsEsoexoLine,null,null,null);

        updateAdditionalActionType[3] = new EntityUpdateAdditional(UPDATE_ON_UPDATE_ONLY,queryIfBridgeIsEnabled,updateFieldsIfBridge,null,null,
                "INSERT INTO sxesoexoheader ( `esoexoHeaderId`, saleHeaderId, esoexoCodeOfDocument,`sxActionTypeId`, oppositeSign,`dbCompanyId`,  dateOfesoexo,  dbYearId, traderId, isTemplate,comments, pricePreVat, priceVat, priceTotal, sourceModuleId)"+
        " SELECT &@,saleHeaderId, saleCodeOfDocument, actiontype.sxActionTypeId, isCredit, "+VariablesGlobal.globalCompanyId+",dateOfsale, "+VariablesGlobal.globalYearId+", traderId, 0,comments, pricePreVat, priceVat, priceTotal, 2"+
        " FROM actionseries, actiontype, saleheader  WHERE actionseries.actionTypeId = actiontype.actionTypeId AND actionseries.actionSeriesId = saleheader.actionSeriesId "+
        " AND saleheader.dbCompanyId =  actiontype.dbCompanyId AND actiontype.dbCompanyId = actionseries.dbCompanyId "+ 
        " AND actiontype.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleheader.saleheaderId LIKE #", updateQueryFieldsEsoexoHeaderUpd,"sxesoexoheader","esoexoheaderId",bridgeEsoexoHeaderDBFields);  // &b is for the header of original id like saleheaderid 
 
        updateAdditionalActionType[4] = new EntityUpdateAdditional(UPDATE_ON_UPDATE_ONLY,queryIfBridgeIsEnabled,updateFieldsIfBridge,"salelines",queryAccountId,"INSERT INTO sxesoexoline ( `esoexoHeaderId`, `dbCompanyId`,  dbYearId, isTemplate, esoexoLineId, inc,dummy, dummy2, accountId,  priceBeforeVat, vatValue, valueWithVat, saleHeaderId ) SELECT &@, '"+VariablesGlobal.globalCompanyId+"',"+VariablesGlobal.globalYearId+",0, ROW_NUMBER() OVER (ORDER BY accountid), ROW_NUMBER() OVER (ORDER BY accountid), stockId AS s , saleline.salelineid AS l,("+queryAccountId+") AS accountid , SUM(priceBeforeVat), SUM(vatValue), SUM(valueWithVat), saleHeaderId FROM saleline WHERE saleheaderid LIKE # GROUP BY accountid",updateQueryFieldsEsoexoLineUpd,"sxesoexoline",null,null);
        
        updateAdditionalActionType[5] = new EntityUpdateAdditional(UPDATE_ON_DELETE,queryIfBridgeIsEnabled,updateFieldsIfBridge,"salelines",queryAccountId,"WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoline.isTemplate LIKE 0",null,"sxesoexoline",null,null); // on UPDATE_ON_DELETE queries we set just the where clause
        updateAdditionalActionType[6] = new EntityUpdateAdditional(UPDATE_ON_DELETE,queryIfBridgeIsEnabled,updateFieldsIfBridge,null,null,"WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoheader.isTemplate LIKE 0", null,"sxesoexoheader","esoexoheaderId",bridgeEsoexoHeaderDBFields);
        
      
        
        saleDBFields[0] = new EntityDBFields("saleheader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");//FIELD_NOT_VISIBLE  //FIELD_VISIBLE_AND_EDITABLE
        saleDBFields[1] = new EntityDBFields("saleheader","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");  //FIELD_NOT_VISIBLE   // FIELD_VISIBLE_AND_EDITABLE          
        
        saleDBFields[2] = new EntityDBFields("saleheader","saleHeaderId","Νο πώλησης",1,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
        int[] inputActionTypeCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputActionType ={3};
       
        EntityDBFieldsCalculation[] fieldsCalculationActionTypeUpdate = new EntityDBFieldsCalculation[2];
        fieldsCalculationActionTypeUpdate[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,4,inputActionTypeCategory,inputActionType,"UPDATE saleheader SET saleCodeNo = (SELECT actionseries.seriesNextNumber FROM actionseries WHERE  actionseries.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND actionseries.actionseriesId LIKE #) WHERE saleheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleheader.saleHeaderId LIKE ");
        fieldsCalculationActionTypeUpdate[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,5,inputActionTypeCategory,inputActionType,"UPDATE saleheader SET saleCodeOfDocument = (SELECT  LPAD((SELECT actionseries.seriesNextNumber FROM actionseries WHERE  actionseries.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  AND actionseries.actionseriesId LIKE #),(SELECT sersaleCodeOfDocumentsLength FROM dbcompanyset WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ), 0)) WHERE saleheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleheader.saleHeaderId LIKE ");
        
        EntityDBFieldsCalculation[] fieldsCalculationActionTypeSelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationActionTypeSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputActionTypeCategory,inputActionType,"SELECT dbcompanyset.sersaleRetailTrader FROM dbcompanyset,actiontype,actionseries WHERE actionseries.actionTypeId = actiontype.actiontypeId AND actionseries.dbCompanyId = actiontype.dbCompanyId AND dbcompanyset.dbCompanyId = actionseries.dbCompanyId AND dbcompanyset.dbcompanyId = actiontype.dbcompanyId AND dbcompanyset.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND actiontype.actionTypeCatId LIKE 2 AND actionseries.actionseriesId LIKE #");
        saleDBFields[3] = new EntityDBFields("saleheader","actionseriesId","σειρά - τύπος παρ/κού",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"actionseries",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,fieldsCalculationActionTypeUpdate,fieldsCalculationActionTypeSelect,"");// variable before last: 'false' means update
        saleDBFields[4] = new EntityDBFields("saleheader","saleCodeNo","saleCodeNo",1,"java.lang.Integer",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        saleDBFields[5] = new EntityDBFields("saleheader","saleCodeOfDocument","κωδικός παρ/κού",1,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");//,entityFieldUpdateAdditionalCodeOfDocument);
        saleDBFields[6] = new EntityDBFields("saleheader","dateOfSale","ημερομηνία παρ/κού",1, "java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalDate,"");
       // saleDBFields[5] = new EntityDBFields("saleheader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
      //  saleDBFields[6] = new EntityDBFields("saleheader","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalYearId,"");        
        int[] inputTraderCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputTrader ={7};
        //10,inputService,"SELECT vatcat.vatPercentage FROM stock, vatcat  WHERE stock.vatCatId=vatcat.vatCatId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId=");
        EntityDBFieldsCalculation[] fieldsCalculationTraderSelect = new EntityDBFieldsCalculation[3];
        fieldsCalculationTraderSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,9,inputTraderCategory,inputTrader,"SELECT trader.paymentTypeId FROM trader WHERE trader.traderId LIKE #");
        fieldsCalculationTraderSelect[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,10,inputTraderCategory,inputTrader,"SELECT trader.typeofVatExclusionId FROM trader  WHERE trader.traderId LIKE #");
        fieldsCalculationTraderSelect[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,8,inputTraderCategory,inputTrader,"SELECT trader.title FROM trader, dbcompany, dbcompanyset  WHERE dbcompany.dbCompanyId = dbcompanyset.dbCompanyId AND dbcompany.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND dbcompanyset.sersaleCopyTraderNameToSaleComment LIKE 1 AND trader.traderId LIKE #");
        //fieldsCalculationTrader[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputTraderCategory,inputTrader,"SELECT trader.currencyId FROM trader  WHERE trader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND trader.traderId LIKE #");
        
        
        saleDBFields[7] = new EntityDBFields("saleheader","traderId","συναλλασσόμενος",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"trader", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationTraderSelect,"");
        saleDBFields[8] = new EntityDBFields("saleheader","comments","σχόλια",3,"java.lang.String",32,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");//,entityFieldUpdateAdditionalCodeOfDocument);
        saleDBFields[9] = new EntityDBFields("saleheader","paymentTypeId","τρόπος πληρωμής",3,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"paymenttype", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       
        
        
        
        // to calculate the rest(rest of the sums) of the footer below
        int[] inputTotalAfterVatCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputTotalAfterVat ={17};  
    
    int[] inputWithholdingTaxRateCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputWithholdingTaxRate ={15,18};         
     //   EntityDBFieldsCalculation[] fieldsCalculationWithholdingTax = new EntityDBFieldsCalculation[1];
    //    fieldsCalculationWithholdingTax[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,inputWithholdingTaxRateCategory,inputWithholdingTaxRate,"SELECT # * #");   
    int[] inputWithholdingTaxRateTotalCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputWithholdingTaxRateTotal ={17,19,17,19};   // when       
        //EntityDBFieldsCalculation[] fieldsCalculationWithholdingTaxTotal = new EntityDBFieldsCalculation[1];
     //   fieldsCalculationWithholdingTaxTotal[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputWithholdingTaxRateTotalCategory,inputWithholdingTaxRateTotal,"SELECT # + #");                                
    
        EntityDBFieldsCalculation[] fieldGetWithholdingTaxRateSelect = new EntityDBFieldsCalculation[3];
        fieldGetWithholdingTaxRateSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,inputTotalAfterVatCategory,inputTotalAfterVat,              
        "SELECT IF( # > (SELECT dbcompanyset.sersaleWithHoldingTaxAmountGreaterThan FROM dbcompanyset WHERE dbcompanyset.dbcompanyId LIKE "+VariablesGlobal.globalCompanyId+"),"
                + "(SELECT dbcompanyset.sersaleWithHoldingTaxRate FROM dbcompanyset WHERE dbcompanyset.dbcompanyId LIKE "+VariablesGlobal.globalCompanyId+")  ,'')");
        fieldGetWithholdingTaxRateSelect[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputWithholdingTaxRateCategory,inputWithholdingTaxRate,"SELECT # * #/100");
        fieldGetWithholdingTaxRateSelect[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,20,inputWithholdingTaxRateTotalCategory,inputWithholdingTaxRateTotal,"SELECT IF ((# - # = 0), '' , (# - #) )");                                
          // to calculate the rest(rest of the sums) of the footer above 
        
        
        
        
        
        
        int[] inputPreVatCategory ={FIELDSCALCULATION_CATEGORY_BACKWARD,12,12};// 12 is no of table
        int[] inputPreVat ={10,4,4};//field
        EntityDBFieldsCalculation[] fieldsCalculationVatCatSelect = new EntityDBFieldsCalculation[4];
        fieldsCalculationVatCatSelect[0] = new EntityDBFieldsCalculation(12,10,inputPreVatCategory,inputPreVat,calculationVatPercentageSql);//12 is no of table
        fieldsCalculationVatCatSelect[1]= fieldGetWithholdingTaxRateSelect[0];
        fieldsCalculationVatCatSelect[2]= fieldGetWithholdingTaxRateSelect[1];
        fieldsCalculationVatCatSelect[3]= fieldGetWithholdingTaxRateSelect[2];


        
        saleDBFields[10] = new EntityDBFields("saleheader","typeofVatExclusionId","καθεστώς ΦΠΑ",3,"java.lang.Integer",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCVatExclusion",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1",null,fieldsCalculationVatCatSelect,"");
        //int[] inputPreVatCategory ={9};
        //int[] inputPreVat ={9};//field
        //10,inputService,"SELECT vatcat.vatPercentage FROM stock, vatcat  WHERE stock.vatCatId=vatcat.vatCatId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId=");
        //EntityDBFieldsCalculation[] fieldsCalculationPreVat = new EntityDBFieldsCalculation[1];
        //fieldsCalculationPreVat[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,11,inputPreVatCategory,inputPreVat,"SELECT #");
        
        //fieldsCalculationCurrency[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,8,inputTraderCategory,inputTrader,"SELECT trader.currencyId FROM trader  WHERE trader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND trader.traderId LIKE #");
                 
        saleDBFields[11] = new EntityDBFields("saleheader","isPrinted","εκτυπωμένο",3,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,"1","");         
        String[] childTableFieldsForSumsSalelines=null;//{"προ ΦΠΑ","αξία ΦΠΑ","αξία"};
                                                      //  salelines used in additional insert
        saleDBFields[12] = new EntityDBFields("saleheader","salelines","υπηρεσίες",4,"table",FIELD_VISIBLE_AND_EDITABLE,"saleline",120,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,saleLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT * FROM saleline WHERE saleline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY saleline.inc",null,childTableFieldsForSumsSalelines,"stockId","πολλαπλή εισαγωγή υπηρεσιών");        
         
        saleDBFields[13] = new EntityDBFields("saleheader","countTotal","πλήθος",5,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,12,6,DBFIELD_TYPE_OF_SUM_COUNT);        
        saleDBFields[14] = new EntityDBFields("saleheader","quantityTotal","ποσότητα",5,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,12,6,DBFIELD_TYPE_OF_SUM_SUM);        
  
        
        saleDBFields[15] = new EntityDBFields("saleheader","pricePreVat","προ ΦΠΑ",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,12,9,DBFIELD_TYPE_OF_SUM_SUM);        
        saleDBFields[16] = new EntityDBFields("saleheader","priceVat","ΦΠΑ",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,null,12,11,DBFIELD_TYPE_OF_SUM_SUM);        
                
    //    int[] inputCurrencyCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
    //    int[] inputCurrency ={16,20};         
    //    EntityDBFieldsCalculation[] fieldsCalculationCurrency = new EntityDBFieldsCalculation[3];
    //    fieldsCalculationCurrency[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,21,inputCurrencyCategory,inputCurrency,"SELECT # * #");
    //    fieldsCalculationCurrency[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,17,null,null,"SELECT currencyId FROM dbcompany WHERE dbcompany.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId);                 
    //    fieldsCalculationCurrency[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,null,null,"SELECT currency.name FROM currency, dbcompany WHERE currency.currencyId = dbcompany.currencyId AND dbcompany.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId);         
    
    
    
    
    // ---------------------need
    /*     int[] inputTotalAfterVatCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputTotalAfterVat ={17};  
    
    int[] inputWithholdingTaxRateCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputWithholdingTaxRate ={15,18};         
     //   EntityDBFieldsCalculation[] fieldsCalculationWithholdingTax = new EntityDBFieldsCalculation[1];
    //    fieldsCalculationWithholdingTax[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,inputWithholdingTaxRateCategory,inputWithholdingTaxRate,"SELECT # * #");   
    int[] inputWithholdingTaxRateTotalCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputWithholdingTaxRateTotal ={17,19,17,19};   // when       
        //EntityDBFieldsCalculation[] fieldsCalculationWithholdingTaxTotal = new EntityDBFieldsCalculation[1];
     //   fieldsCalculationWithholdingTaxTotal[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputWithholdingTaxRateTotalCategory,inputWithholdingTaxRateTotal,"SELECT # + #");                                
    
        EntityDBFieldsCalculation[] fieldGetWithholdingTaxRateSelect = new EntityDBFieldsCalculation[3];
        fieldGetWithholdingTaxRateSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,inputTotalAfterVatCategory,inputTotalAfterVat,              
        "SELECT IF( # > (SELECT dbcompanyset.sersaleWithHoldingTaxAmountGreaterThan FROM dbcompanyset WHERE dbcompanyset.dbcompanyId LIKE "+VariablesGlobal.globalCompanyId+"),"
                + "(SELECT dbcompanyset.sersaleWithHoldingTaxRate FROM dbcompanyset WHERE dbcompanyset.dbcompanyId LIKE "+VariablesGlobal.globalCompanyId+")  ,'')");
        fieldGetWithholdingTaxRateSelect[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputWithholdingTaxRateCategory,inputWithholdingTaxRate,"SELECT # * #/100");
        fieldGetWithholdingTaxRateSelect[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,20,inputWithholdingTaxRateTotalCategory,inputWithholdingTaxRateTotal,"SELECT IF ((# - # = 0), '' , (# - #) )");                                
       */
       //---------------
    
    
    
    saleDBFields[17] = new EntityDBFields("saleheader","priceTotal","σύνολο με ΦΠΑ",5,"java.lang.Double",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,null,fieldGetWithholdingTaxRateSelect,12,12,DBFIELD_TYPE_OF_SUM_SUM);             
        //EntityDBFieldsCalculation[] fieldsCalculationCurrency = new EntityDBFieldsCalculation[1];
        
    //    saleDBFields[17] = new EntityDBFields("saleheader","companyCurrencyId","companyCurrencyId",4,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);
   //     saleDBFields[18] = new EntityDBFields("saleheader","companyCurrencyDescr","νόμισμα εταιρίας",4,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null);        

        //EntityDBFieldsCalculation[] fieldsCalculationCurrencyId = new EntityDBFieldsCalculation[1];
        //fieldsCalculationCurrencyId[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,16,null,null,"SELECT currencyId FROM dbcompany WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId);                 
    //    saleDBFields[19] = new EntityDBFields("saleheader","currencyId","νόμισμα συναλλαγής",4,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"currency", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);

    //    int[] inputWithholdingTaxRateCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
    //    int[] inputWithholdingTaxRate ={16,17};         
    //    EntityDBFieldsCalculation[] fieldsCalculationWithholdingTax = new EntityDBFieldsCalculation[1];
    ///    fieldsCalculationWithholdingTax[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,inputWithholdingTaxRateCategory,inputWithholdingTaxRate,"SELECT # * #");                                                       String defaultValueIn,EntityDBFieldsCalculation[] fieldsCalculationUpdateIn, EntityDBFieldsCalculation[] fieldsCalculationSelectIn,String formVariableFromFieldIn)             
        saleDBFields[18] = new EntityDBFields("saleheader","withholdingtaxRate","ποσοστό παρακράτησης",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"","");//null,fieldGetWithholdingTaxRateSelect,"");        
        saleDBFields[19] = new EntityDBFields("saleheader","withholdingtaxAmount","παρακράτηση",5,"java.lang.Double",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"","");//,null,fieldGetWithholdingTaxRateSelect,"");//null,true,fieldsCalculationWithholdingTax);        //null);        

    //    int[] inputWithholdingTaxRateTotalCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
    //    int[] inputWithholdingTaxRateTotal ={16,18};         
    //    EntityDBFieldsCalculation[] fieldsCalculationWithholdingTaxTotal = new EntityDBFieldsCalculation[1];
    //    fieldsCalculationWithholdingTaxTotal[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputWithholdingTaxRateTotalCategory,inputWithholdingTaxRateTotal,"SELECT # + #");                                
        saleDBFields[20] = new EntityDBFields("saleheader","priceTotalAfterWithholdingTax","τελικό σύνολο",5,"java.lang.Double",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"","");//null,true,fieldsCalculationWithholdingTaxTotal);//,11,12,DBFIELD_TYPE_OF_SUM_SUM);             
        
        saleEntityGroupOfComps[0] = new EntityGroupOfComps("hidden",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE); //GROUP_OF_PANEL_NOT_VISIBLE
        saleEntityGroupOfComps[1] = new EntityGroupOfComps("βασικά",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        saleEntityGroupOfComps[2] = new EntityGroupOfComps("συναλλασσόμενος",2,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        saleEntityGroupOfComps[3] = new EntityGroupOfComps("λοιπά",8,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //saleEntityGroupOfComps[3] = new EntityGroupOfComps("αιτιολογία",6,0);
        saleEntityGroupOfComps[4] = new EntityGroupOfComps("παρεχόμενες υπηρεσίες",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        saleEntityGroupOfComps[5] = new EntityGroupOfComps("σύνολα",8,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
         
        saleEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);

        //------------------------------------------------------------------------

        
        serviceDBFields[0] = new EntityDBFields("stock","stockId","Νο υπηρεσίας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");        
        serviceDBFields[1] = new EntityDBFields("stock","code","κωδικός",0,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        serviceDBFields[2] = new EntityDBFields("stock","descr","ονομασία",0,"java.lang.String",33,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        serviceDBFields[3] = new EntityDBFields("stock","active","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");            
        serviceDBFields[4] = new EntityDBFields("stock","stockCatId","κατηγορία υπηρεσίας",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"stockcat", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        serviceDBFields[5] = new EntityDBFields("stock","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
        
        serviceDBFields[6] = new EntityDBFields("stock","vatCatId","κατηγορία ΦΠΑ",1,"java.lang.Integer",3, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"vatcat",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        serviceDBFields[7] = new EntityDBFields("stock","priceWhole","τιμή χονδρικής",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        serviceDBFields[8] = new EntityDBFields("stock","discountPercentage","έκπτωση %",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,""); 
        serviceDBFields[9] = new EntityDBFields("stock","proposeQuantity","ποσότητα",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1",""); 
        
        serviceDBFields[10] = new EntityDBFields("stock","sxAccount2","λογαριασμός δευτεροβάθμιος",2,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        serviceDBFields[11] = new EntityDBFields("stock","sxAccount3","λογαριασμός τριτοβάθμιος",2,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        serviceDBFields[12] = new EntityDBFields("stock","notes","σημειώσεις",3,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");     

        serviceEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        serviceEntityGroupOfComps[1]= new EntityGroupOfComps("προτεινόμενες τιμές",8,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        serviceEntityGroupOfComps[2]= new EntityGroupOfComps("σύνδεση με έσοδα-έξοδα",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        serviceEntityGroupOfComps[3]= new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
   
        

       //---------------------------------------------------------------------    
        
   
       dbyearDBFields[0] = new EntityDBFields("dbyear","dbYearId","Νο χρήσης",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbyearEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
     
        
        
        
   	activityCatDBFields[0] = new EntityDBFields("activitycat","activityCatId","Νο δραστηριότητας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	activityCatDBFields[1] = new EntityDBFields("activitycat","activityDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       
       	serviceCatLineDBFields[0] = new EntityDBFields("stockcat","stockCatId","Νο κατηγορίας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	serviceCatLineDBFields[1] = new EntityDBFields("stockcat","catDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        serviceCatLineDBFields[2] = new EntityDBFields("stockcat","dbCompanyId","dbCompanyId",0,"java.lang.String",3, FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        serviceCatDBFields[0] = new EntityDBFields("servicecatheader","stockcat","κατηγορίες υπηρεσίας",0,"table",FIELD_VISIBLE_AND_EDITABLE,"stockcat",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,serviceCatLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT stockCatId AS\"Νο κατηγορίας\", catDescr AS\"κατηγορία\", dbCompanyId FROM stockcat WHERE stockcat.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY stockcat.catDescr",null,null,null,null);     
        serviceCatEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);          
        
 /*       vatCatLineDBFields[0] = new EntityDBFields("vatcat","vatCatId","Νο κατηγορίας ΦΠΑ",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	vatCatLineDBFields[1] = new EntityDBFields("vatcat","vatDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");               
        vatCatLineDBFields[2] = new EntityDBFields("vatcat","vatPercentage","ποσοστό",0,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");           
        vatCatLineDBFields[3] = new EntityDBFields("vatcat","vatReducedCat","μειωμένος συντελεστής",0,"java.lang.Integer",16,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"vatcat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        vatCatLineDBFields[4] = new EntityDBFields("vatcat","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                   
        
        
        vatCatDBFields[0] = new EntityDBFields("vatcatheader","vatcat","κατηγορίες ΦΠΑ",0,"table",FIELD_VISIBLE_AND_EDITABLE,"vatcat",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,vatCatLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT * FROM vatcat ORDER BY vatCatId",null,null);     //String[] childTableFieldsForSumsIn   
        
        vatCatEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);       
*/                
       //----------------------------------------------service sales prefs below------------------
        companySetSerSalesDBFields[0] = new EntityDBFields("dbCompanySet","dbCompanyId","Νο εταιρίας",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,VariablesGlobal.globalCompanyId);
        companySetSerSalesDBFields[1] = new EntityDBFields("dbCompanySet","sersaleCodeOfDocumentsLength","μήκος κωδικού παραστατικών",1,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"7","");
        companySetSerSalesDBFields[2] = new EntityDBFields("dbCompanySet","sersaleWithHoldingTaxAmountGreaterThan","ελαχιστο σύνολο για υπολογισμό % φόρου παρακράτησης",1,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"300","");
        companySetSerSalesDBFields[3] = new EntityDBFields("dbCompanySet","sersaleWithHoldingTaxRate","% φόρου παρακράτησης",1,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"20","");
        companySetSerSalesDBFields[4] = new EntityDBFields("dbCompanySet","sersaleRetailTrader","προτεινόμενος συναλλασσόμενος λιανικής",2,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"trader", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,null,"");
        companySetSerSalesDBFields[5] = new EntityDBFields("dbCompanySet","sersaleCheckAFMOfSaleAndComp","ενημέρωση καταχώρησης παραστατικού με συναλλασσόμενο την εταιρία εργασίας",3,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        companySetSerSalesDBFields[6] = new EntityDBFields("dbCompanySet","sersaleMaxOfCashCheck","ενημέρωση για ποσό εξόφλησης μέσω τράπεζικού τρόπου",4,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        companySetSerSalesDBFields[7] = new EntityDBFields("dbCompanySet","sersaleMaxOfCashNetValue","ελαχιστο ποσό για εξόφληση μέσω τράπεζικού τρόπου",4,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"500","");
        companySetSerSalesDBFields[8] = new EntityDBFields("dbCompanySet","sersaleCopyTraderNameToSaleComment","πρόταση επωνυμίας συναλλασσόμενου στην αιτιολογία πώλησης",5,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        
        
        companySetSerSalesEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE);    
        companySetSerSalesEntityGroupOfComps[1] = new EntityGroupOfComps("παράμετροι",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        companySetSerSalesEntityGroupOfComps[2] = new EntityGroupOfComps("λιανική",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        companySetSerSalesEntityGroupOfComps[3] = new EntityGroupOfComps("έλεγχοι",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        companySetSerSalesEntityGroupOfComps[4] = new EntityGroupOfComps("εξόφληση",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);      
         companySetSerSalesEntityGroupOfComps[5] = new EntityGroupOfComps("προτιμήσεις",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        
         //-------------------------        
         printFormDBFields[0] = new EntityDBFields("printform","printFormId","Νο φόρμας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         printFormDBFields[1] = new EntityDBFields("printform","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
         printFormDBFields[2] = new EntityDBFields("printform","printFormName","φόρμα εκτύπωσης",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"","");
         printFormDBFields[3] = new EntityDBFields("printform","isActive","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");               
         printFormDBFields[4] = new EntityDBFields("printform","printFormLaser","",1,"java.lang.String",45000,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         printFormDBFields[5] = new EntityDBFields("printform","printFormDotMatrix","",2,"java.lang.String",45000,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
         printFormDBFields[6] = new EntityDBFields("printform","imageTop","εικόνα πάνω",3,"java.awt.image.BufferedImage",40/*45000*/,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");     
         printFormDBFields[7] = new EntityDBFields("printform","imageBottom","εικόνα κάτω",3,"java.awt.image.BufferedImage",40/*45000*/,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");               
         printFormDBFields[8] = new EntityDBFields("printform","imageBackground","εικόνα πίσω",3,"java.awt.image.BufferedImage",40/*45000*/,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");             
         
        
        
        printFormEntityGroupOfComps[0] = new EntityGroupOfComps("ιδιότητες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        printFormEntityGroupOfComps[1] = new EntityGroupOfComps("εκτύπωση για laser - γραφικά",2,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        printFormEntityGroupOfComps[2] = new EntityGroupOfComps("εκτύπωση για dot matrix - κρουστικός",2,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE);
        printFormEntityGroupOfComps[3] = new EntityGroupOfComps("εικόνες για laser",2,3,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE);
        
        
        printFormEntityGroupOfPanels[0] = new EntityGroupOfPanels("ιδιότητες",1);
        printFormEntityGroupOfPanels[1] = new EntityGroupOfPanels("φορμα laser",1);
        //printFormEntityGroupOfPanels[2] = new EntityGroupOfPanels("φόρμα dot matrix",1);
        //printFormEntityGroupOfPanels[3] = new EntityGroupOfPanels("εικόνες φόρμας",1);
        //-------------------------        
        
      
     
        
   }
   
 

   
   public void loadAllNodes()
   {
       addReportSettings();
       addEntityInfoNodes();
     //  addStatisticsNodes();
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
        EntityFilterSettings[] saleErs = new EntityFilterSettings[8];       
        saleErs[0]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleheader",/*VariablesGlobal.globalYearId*/"",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[1]=new EntityFilterSettings("αριθ. παρ/κού","","string","equals","saleCodeOfDocument","saleheader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[2]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionseriesId","actionseries","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[3]=new EntityFilterSettings("συναλλασσόμενος","","string","equals","title","trader","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","trader","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[5]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfSale","","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[6]=new EntityFilterSettings("ποσότητα","","double","fromto","quantityTotal","","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[7]=new EntityFilterSettings("τελική τιμή","","double","fromto","priceTotal","","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);

       EntityGroupOfComps[] saleEntityGroupOfCompsFilters = null;
        
        int[] saleFieldsOrderby ={5,2,3,6,1};// also complete sql order by clause
        
        String[] fieldsForSumsSale = {"πλήθος","ποσότητα","προ φπα", "ΦΠΑ","σύνολο μετά ΦΠΑ","παρακράτηση","τελικό σύνολο"};
        //   IF(actiontype.isCredit=1, -saleheader.pricePreVat, saleheader.pricePreVat)
       
        EntityInfo pg = new EntityInfo("saleheader", "SELECT saleheader.saleHeaderId AS \"Νο πώλησης\", actionseries.actionSeriesCode AS \"τύπος\",saleheader.saleCodeNo AS \"αριθ.\", saleheader.saleCodeOfDocument AS \"αριθ. παρ.\", saleheader.dateOfSale AS \"ημερομηνία\",trader.vatNo AS \"Α.Φ.Μ.\",  trader.title AS \"συναλλασσόμενος\", saleheader.comments AS \"σχόλια\", (SELECT saleheader.isPrinted - 1) AS \"εκτ\",saleheader.countTotal AS \"πλήθος\",  saleheader.quantityTotal AS \"ποσότητα\", saleheader.pricePreVat AS \"προ ΦΠΑ\",  saleheader.priceVat AS \"ΦΠΑ\",  saleheader.priceTotal AS \"σύνολο μετά ΦΠΑ\", saleheader.withholdingtaxAmount AS \"παρακράτηση\", saleheader.priceTotalAfterWithholdingTax AS \"τελικό σύνολο\" FROM saleheader , trader, actionseries WHERE saleheader.traderId = trader.traderId AND actionseries.actionseriesId = saleheader.actionseriesId AND saleheader.dbCompanyId = actionseries.dbCompanyId AND actionseries.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY saleheader.dateOfSale, actionseries.actionSeriesCode, saleheader.saleCodeNo, trader.vatNo, saleheader.saleHeaderId" ,"SELECT saleheader.saleHeaderId AS \"Νο πώλησης\", saleheader.actionseriesId , saleheader.saleCodeNo, saleheader.saleCodeOfDocument, saleheader.dateOfSale, saleheader.traderId, saleheader.paymentTypeId, saleheader.isPrinted","FROM saleheader","WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,null,fieldsForSumsSale,"παροχή υπηρεσίας","DORM","","Νο πώλησης","saleHeaderId",/*"","",*/saleErs,saleEntityGroupOfCompsFilters,"πώλησης","πωλήσεων",strSaleCategories,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale,saleFieldsOrderby,5,FIELD_VALIDATION_AFM,entReportServiceSaleDoc,globalYearPlusOne);
        EntityMenu empg = new EntityMenu();
        empg.setEntityInfo(pg,ICO_PAPER);
        empg.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeempg = new DataTreeNode(empg);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeempg);        
        
   //----------------------------------------------------------------------      

     /*  EntityFilterSettings[] traderErs = new EntityFilterSettings[4];       
       //traderErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[0]=new EntityFilterSettings("όνομα","","string","equals","title","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[3]=new EntityFilterSettings("ταυτότητα","","string","equals","idNo","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","trader","",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","trader","",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[3]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[4]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfSale","","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
                             //onelookup,checkboxTable
       EntityGroupOfComps[] traderEntityGroupOfComps = null;
       
       int[] traderFieldsOrderBy ={2,3,4,5,1};
        // if fields change, change them and at lookup entities
       String[] fieldsForSumsTrader = {"ποσότητα","προ φπα", "φπα","σύνολο"};
      */ //EntityInfo ia = new EntityInfo("trader", "SELECT trader.traderId AS \"Νο συναλλασσόμενου\", trader.surname AS \"επίθετο\", trader.title AS \"όνομα\", trader.fathername AS \"πατρόνυμο\", trader.traderAfm AS \"Α.Φ.Μ.\", trader.doyId AS \"Νο Δ.Ο.Υ.\", doy.doyName AS \"ονομασία Δ.Ο.Υ.\", trader.idNo AS \"αρ ταυτοτ\", trader.phone AS \"τηλέφωνο(1)\", trader.phone2 AS \"τηλέφωνο(2)\", trader.phone3 AS \"τηλέφωνο(3)\", town.townName AS \"πόλη/χωριό\",town.postCode AS \"TK\", trader.address AS \"διεύθυνση\", bank.bankBranch AS \"τράπεζα\", trader.bankAccount AS \"λογαριασμός\" FROM trader LEFT JOIN doy ON trader.doyId=doy.doyId LEFT JOIN town ON trader.townId=town.townId LEFT JOIN bank ON trader.bankId=bank.bankId ORDER BY trader.surname, trader.title, trader.fathername, trader.traderAfm" ,"SELECT trader.traderId AS \"Νο συναλλασσόμενου\", trader.surname AS \"επίθετο\", trader.name AS\"όνομα\", trader.fathername AS \"πατρόνυμο\", trader.traderAfm AS \"Α.Φ.Μ.\", trader.doyId, trader.idNo AS \"αρ ταυτοτ\", trader.townId, trader.address AS \"διέυθυνση\", trader.phone AS\"τηλέφωνο\"","FROM trader LEFT JOIN doy ON trader.doyId=doy.doyId LEFT JOIN town ON trader.townId=town.townId","",null, fieldsForSumsTrader,null,null,traderDBFields,null,null,null,null,null,false,null,null ,"αγρότες","DORM","","Νο συναλλασσόμενου","traderId",null,null,traderErs,traderEntityGroupOfComps ,"αγρότη","αγροτών",null,null,strTraderCategories,entityPanelTrader,fieldsOnTitleTrader,fieldsOnTitleCaptionTrader,traderFieldsOrderBy,4,FIELD_VALIDATION_AFM,globalYearPlusOne); 
      // EntityInfo ia = new EntityInfo("trader", "SELECT trader.traderId AS \"Νο συναλλασσόμενου\", trader.title AS \"επωνυμία\",trader.traderCode  AS \"κωδ. συναλλασσόμενου\", trader.vatNo AS \"Α.Φ.Μ.\", activitycat.activityDescr AS \"κατηγορία δραστηριότητας\", trader.telephone1 AS\"τηλέφωνο 1\", trader.telephone2 AS\"τηλέφωνο 2\", trader.email AS\"email\",doy.doyName AS \"ονομασία Δ.Ο.Υ.\", trader.active, sum(quantityTotal) AS \"ποσότητα\", sum(pricePreVat) AS \"προ ΦΠΑ\", sum(priceVat) AS \"ΦΠΑ\", sum(priceTotal) AS \"σύνολο\"  FROM trader LEFT JOIN doy ON trader.doyId=doy.doyId LEFT JOIN saleheader ON saleheader.traderId = trader.traderId AND saleheader.dbCompanyId = trader.dbCompanyId LEFT JOIN activitycat ON activitycat.activityCatId = trader.activityCatId WHERE trader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" GROUP BY trader.traderId ORDER BY trader.title" ,"SELECT trader.traderId AS \"Νο συναλλασσόμενου\", trader.title AS \"επωνυμία\", trader.vatNo AS \"Α.Φ.Μ.\", trader.doyId","FROM trader LEFT JOIN doy ON trader.doyId=doy.doyId "/*LEFT JOIN town ON trader.townId=town.townId"*/,"WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,null, fieldsForSumsTrader ,"συναλλασσόμενοι","DORM","","Νο συναλλασσόμενου","traderId",/*"","",*/traderErs,traderEntityGroupOfComps ,"συναλλασσόμενου","συναλλασσόμενων",strTraderCategories,entityPanelTrader,fieldsOnTitleTrader,fieldsOnTitleCaptionTrader,traderFieldsOrderBy,3,FIELD_VALIDATION_AFM,null,globalYearPlusOne);
      /*  EntityMenu emia = new EntityMenu();
        emia.setEntityInfo(ia,ICO_FARMER16);
        emia.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeemia = new DataTreeNode(emia);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemia);*/

      
       EntityFilterSettings[] traderErs = new EntityFilterSettings[3];       
       //traderErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[0]=new EntityFilterSettings("όνομα","","string","equals","title","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[3]=new EntityFilterSettings("ταυτότητα","","string","equals","idNo","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","trader","",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","trader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[3]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[4]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfEsoexo","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
                             //onelookup,checkboxTable
       EntityGroupOfComps[] traderEntityGroupOfComps = null;
       
       int[] traderFieldsOrderBy ={2,3,4,5};
        // if fields change, change them and at lookup entities
       String[] fieldsForSumstrader = {"ποσότητα","προ φπα", "φπα","σύνολο"};
       // , sum(quantityTotal) AS \"ποσότητα\", sum(pricePreVat) AS \"προ ΦΠΑ\", sum(priceVat) AS \"ΦΠΑ\", sum(priceTotal) AS \"σύνολο\"    LEFT JOIN sxesoexoheader ON sxesoexoheader.traderId = trader.traderId
       EntityInfo ia = new EntityInfo("trader", "SELECT trader.traderId AS \"Νο συναλλασσόμενου\", trader.title AS \"επωνυμία\",trader.traderCode  AS \"κωδ. συναλλασσόμενου\", trader.vatNo AS \"Α.Φ.Μ.\", activitycat.activityDescr AS \"κατηγορία δραστηριότητας\", trader.doyId AS \"Νο Δ.Ο.Υ.\", doy.doyName AS \"ονομασία Δ.Ο.Υ.\", trader.active  FROM trader LEFT JOIN doy ON trader.doyId=doy.doyId  LEFT JOIN activitycat ON activitycat.activityCatId = trader.activityCatId ORDER BY trader.title" ,"SELECT trader.traderId AS \"Νο συναλλασσόμενου\", trader.title AS \"επωνυμία\", trader.vatNo AS \"Α.Φ.Μ.\", trader.doyId","FROM trader LEFT JOIN doy ON trader.doyId=doy.doyId "/*LEFT JOIN town ON trader.townId=town.townId"*/," ",null, fieldsForSumstrader ,"συναλλασόμενοι","DORM","","Νο συναλλασσόμενου","traderId"/*,"",""*/,traderErs,traderEntityGroupOfComps ,"συναλλασσόμενου","συναλλασσόμενων",strtraderCategories,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader,traderFieldsOrderBy,3,FIELD_VALIDATION_AFM,null,globalYearPlusOne);
        EntityMenu emia = new EntityMenu();
        emia.setEntityInfo(ia,ICO_FARMER16);
        emia.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeemia = new DataTreeNode(emia);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemia);
        

    
     //------------------------------------------------------------
        EntityFilterSettings[] serviceErs = new EntityFilterSettings[3];       
        serviceErs[0]=new EntityFilterSettings("ονομασία","","string","equals","descr","stock",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        serviceErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","stockCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
        serviceErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleline","",-1,-1,-1,FIELD_NOCOMPLETION);
       EntityGroupOfComps[] serviceEntityGroupOfComps = null;
        
        int[] serviceFieldsOrderby ={2};
        
        String[] fieldsForSumsService ={"ποσότητα","προ φπα", "φπα","σύνολο"};
        
        EntityInfo pf = new EntityInfo("stock", "SELECT stock.stockId AS \"Νο υπηρεσίας\", stock.descr AS \"περιγραφή\", stock.code AS \"κωδ. υπηρεσίας\", vatcat.vatDescr, stockcat.catDescr  AS \"κατηγορία\", stock.active, priceWhole AS \"τιμή\",  sum(saleline.quantity) AS \"ποσότητα\", sum(saleline.priceBeforeVat) AS \"προ ΦΠΑ\", sum(saleline.vatValue) AS \"ΦΠΑ\", sum(saleline.valueWithVat) AS \"σύνολο\", sxAccount2, sxAccount3 FROM stock LEFT JOIN vatcat ON vatcat.vatCatId = stock.vatCatId LEFT JOIN saleline ON saleline.stockId = stock.stockId AND saleline.dbCompanyId = stock.dbCompanyId LEFT JOIN stockcat ON stockcat.stockCatId = stock.stockCatId AND saleline.dbCompanyId = stock.dbCompanyId WHERE stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" GROUP BY stock.stockId ORDER BY stock.descr"  ,"SELECT stock.stockId AS \"Νο υπηρεσίας\", stock.descr AS \"περιγραφή\"","FROM stock","WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId ,null,fieldsForSumsService,"υπηρεσίες","DORM","","Νο υπηρεσίας","stockId",/*"","",*/serviceErs,serviceEntityGroupOfComps,"υπηρεσίας","υπηρεσιών",strServiceCategories,entityPanelService,fieldsOnTitleService,fieldsOnTitleCaptionService,serviceFieldsOrderby,-1,-1,null,globalYearPlusOne);
        EntityMenu empf = new EntityMenu();
        empf.setEntityInfo(pf,ICO_TABLE16);
        empf.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeempf = new DataTreeNode(empf);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeempf);
  }

  
   public ArrayList addEntitiesLookupTableConstants(ArrayList  <EntityLookupTableConstants> listEntityLookupTableConstants)
   {
       
       EntityLookupTableConstants entityLookupTableConstants;
       
       /*EntityLookupTableConstantsData [] luTCData1 = new EntityLookupTableConstantsData[2];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCData1[0]=new EntityLookupTableConstantsData("1",1,"title1");
       luTCData1[1]=new EntityLookupTableConstantsData("2",2,"title2");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCname",luTCData1)); */

       EntityLookupTableConstantsData [] luTCDataDecimalChar = new EntityLookupTableConstantsData[2];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataDecimalChar[0]=new EntityLookupTableConstantsData(",",1,",");
       luTCDataDecimalChar[1]=new EntityLookupTableConstantsData(".",2,".");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCdecimalchar",luTCDataDecimalChar));       

      /* EntityLookupTableConstantsData [] luTCDataVatExclusion = new EntityLookupTableConstantsData[3];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataVatExclusion[0]=new EntityLookupTableConstantsData("1",1,"κανονικό");
       luTCDataVatExclusion[1]=new EntityLookupTableConstantsData("2",2,"μειωμένο");
       luTCDataVatExclusion[2]=new EntityLookupTableConstantsData("3",3,"απαλλασσόμενο");*/
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCVatExclusion",null));// if null decide by name from db in EntityLookupTableConstants


       /*EntityLookupTableConstantsData [] luTCDataActionTypeCat = new EntityLookupTableConstantsData[2];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataActionTypeCat[0]=new EntityLookupTableConstantsData("1",1,"πωλήσεις χονδρικής");
       luTCDataActionTypeCat[1]=new EntityLookupTableConstantsData("2",2,"πωλήσεις λιανικής");*/
       //luTCDataActionTypeCat[2]=new EntityLookupTableConstantsData("3",3,"πωλήσεις λιανικής");
       
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCActionTypeCat",null)); // if null decide by name in EntityLookupTableConstants   
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCActionStockAffectType",null)); // if null decide by name in EntityLookupTableConstants   
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCActionTraderType",null)); 
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCAskPrintYesNo",null)); 
      
       
       return listEntityLookupTableConstants;
   }
  
   @Override
   public ArrayList addEntitiesLookup(ArrayList entities)
   { 
       // LOOKUPTYPE_ONLYONE_THISFIELD
     EntityLookUp entityLookUp;

     
/*
       EntityFilterSettings[] traderErs = new EntityFilterSettings[3];       
       traderErs[0]=new EntityFilterSettings("επίθετο","","string","equals","title","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[2]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","trader","",-1,-1,-1,FIELD_NOCOMPLETION);
            // for 'intNoOfColsWhenInTable' look also at  deliveryFieldsMany
      
     
     String[] lookUpFieldTrader={"title","vatNo"};
            
     entities.add(entityLookUp = new EntityLookUp("trader","trader","SELECT trader.traderId AS\"Νο συναλλασσόμενου\", traderCode AS\"κωδικός\", title AS\"επωνυμία\",  vatNo AS\"Α.Φ.Μ.\", sum(quantityTotal) AS \"ποσότητα\", sum(pricePreVat) AS \"προ ΦΠΑ\", sum(priceVat) AS \"ΦΠΑ\", sum(priceTotal) AS \"συνολικό ποσό\"  FROM trader LEFT JOIN saleheader ON saleheader.traderId = trader.traderId AND saleheader.dbCompanyId = trader.dbCompanyId","WHERE trader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"AND trader.active LIKE 1", "GROUP BY trader.traderId ORDER BY trader.title","","traderId","Νο συναλλασσόμενου","traderId","συναλλασσόμενος",3,lookUpFieldTrader,"επωνυμία ή ΑΦΜ",29,"java.lang.String",4,"vatNo", "Α.Φ.Μ.",0,null,null,traderQueryEditable, "συναλλασσόμενου","συναλλασσόμενων",strTraderCategories,entityPanelTrader,fieldsOnTitleTrader,fieldsOnTitleCaptionTrader,traderErs,2,2,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));
*/
       EntityFilterSettings[] traderErs = new EntityFilterSettings[3];      
       traderErs[0]=new EntityFilterSettings("κωδικός","","string","equals"," traderCode","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[1]=new EntityFilterSettings("επωνυμία","","string","equals","title","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       traderErs[2]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[2]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","trader","",-1,-1,-1,FIELD_NOCOMPLETION);
            // for 'intNoOfColsWhenInTable' look also at  deliveryFieldsMany
      
     
     String[] lookUpFieldtrader={"title","vatNo"};
            
     entities.add(entityLookUp = new EntityLookUp("trader","trader","SELECT trader.traderId AS\"Νο συναλλασσόμενου\", traderCode AS\"κωδικός\", title AS\"επωνυμία\",  vatNo AS\"Α.Φ.Μ.\" FROM trader","WHERE trader.active LIKE 1",null,"AND trader.active LIKE 1", "ORDER BY trader.title","","traderId","Νο συναλλασσόμενου","traderId","συναλλασσόμενος",3,lookUpFieldtrader,"επωνυμία ή ΑΦΜ",29,"java.lang.String",4,"vatNo", "Α.Φ.Μ.",0,null,null,traderQueryEditable, "συναλλασσόμενου","συναλλασομένων",strtraderCategories,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader,traderErs,2,2,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));
     
      entities.add(entityLookUp = new EntityLookUp("trader1Col","trader","SELECT trader.traderId AS\"Νο συναλλασσόμενου\", traderCode AS\"κωδικός\", title AS\"επωνυμία\",  vatNo AS\"Α.Φ.Μ.\" FROM trader","WHERE trader.active LIKE 1",null,"AND trader.active LIKE 1", "ORDER BY trader.title","","traderId","Νο συναλλασσόμενου","traderId","συναλλασσόμενος",2,lookUpFieldtrader,"επωνυμία ή ΑΦΜ",29,"java.lang.String",0,null,null,0,null,null,traderQueryEditable, "συναλλασσόμενου","συναλλασομένων",strtraderCategories,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader,traderErs,2,1,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));
     
   //-------------------------------------------------------------------------
  

      String[] lookUpFieldServiceCat={"catDescr"};
     
        EntityFilterSettings[] serviceCatErs = new EntityFilterSettings[1];       
        serviceCatErs[0]=new EntityFilterSettings("ονομασία","","string","equals","catDescr","stockcat",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("stockcat","stockcat","SELECT stockCatId AS\"Νο κατηγορίας\", catDescr AS\"κατηγορία\", dbCompanyId FROM stockcat","WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,null,"","ORDER BY catDescr","" ,"stockCatId","Νο κατηγορίας","stockCatId","κατηγορία",2,lookUpFieldServiceCat,"κατηγορία",15,"java.lang.String",0,null,null,0,null,null,serviceCatQueryEditable,"κατηγορία υπηρεσίας","κατηγοριών υπηρεσίας",null,entityPanelServiceCat,fieldsOnTitleServiceCat, fieldsOnTitleCaptionServiceCat,serviceCatErs,2,1,null,true,-1,-1,null));
     
  

     //------------------------------------------------------------------ 
        EntityFilterSettings[] serviceErs = new EntityFilterSettings[3];       
        serviceErs[0]=new EntityFilterSettings("ονομασία","","string","equals","descr","stock",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        serviceErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","stockCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
        serviceErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleline","",-1,-1,-1,FIELD_NOCOMPLETION);
    
     String[] lookUpFieldService={"descr"};                   
    
     entities.add(entityLookUp = new EntityLookUp("stock","stock","SELECT stock.stockId AS\"Νο υπηρεσίας\", stock.descr AS \"ονομασία\", stock.stockCatId AS \"Νο κατηγορίας\",  vatcat.vatDescr, priceWhole AS \"τιμή\"  ,  sum(saleline.quantity) AS \"ποσότητα\", sum(saleline.priceBeforeVat) AS \"προ ΦΠΑ\", sum(saleline.vatValue) AS \"ΦΠΑ\", sum(saleline.valueWithVat) AS \"σύνολο\" FROM stock LEFT JOIN vatcat ON vatcat.vatCatId = stock.vatCatId LEFT JOIN saleline ON saleline.stockId = stock.stockId AND saleline.dbCompanyId = stock.dbCompanyId ","WHERE stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,null,"AND stock.active LIKE 1","GROUP BY stock.stockId ORDER BY stock.descr ","","stockId","Νο υπηρεσίας","stockId","υπηρεσία",2,lookUpFieldService,"ονομασία",22,"java.lang.String",0,null,null,0,null,null,serviceQueryEditable,"υπηρεσίας","υπηρεσιών",null,entityPanelService,fieldsOnTitleService,fieldsOnTitleCaptionService,serviceErs,2,1,null,true,-1,-1,null));    	 	

     
//---------------------------------------------------------------------------    
    
        EntityFilterSettings[] saleErs = new EntityFilterSettings[2];       
        saleErs[0]=new EntityFilterSettings("ονομασία","","string","equals","saleCodeOfDocument","saleheader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionseriesId","actionseries","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
           
     String[] lookUpFieldSale={"saleCodeOfDocument"};                   
    
     entities.add(entityLookUp = new EntityLookUp("saleheader","saleheader","SELECT saleheader.saleCodeOfDocument, saleheader.actionseriesId, saleheader.saleCodeNo, saleheader.traderId  FROM saleheader ",/*, currency WHERE product.currencyId=currency.currencyId"*/"WHERE saleheader.dbCompanyId LIKE "+ VariablesGlobal.globalCompanyId,null,"","ORDER BY saleheader.dateOfSale, saleheader.saleHeaderId ","","saleHeaderId","Νο πώλησης","saleHeaderId","πώληση",3,lookUpFieldSale,"κωδ. παραστατικού",15,"java.lang.String",0,null,null,0,null,null,saleQueryEditable,"πώλησης","πωλήσεων",null,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale,saleErs,2,1,null,true,-1,-1,null));    	 	

  
     //------------------------------------------------------------------ 

         EntityFilterSettings[] printFormErs = new EntityFilterSettings[1];       
        printFormErs[0]=new EntityFilterSettings("ονομασία","","string","equals","printFormName","printform",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        
        String[] lookUpFieldPrintForm={"printFormName"};                   
    
     entities.add(entityLookUp = new EntityLookUp("printform","printform","SELECT printform.printformId AS\"Νο φόρμας\", printform.printformName AS \"φόρμα εκτύπωσης\"  FROM printform","WHERE printform.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,null,"AND printform.isActive LIKE 1","ORDER BY printform.printformName ","","printformId","Νο φόρμας","printform","φόρμα",2,lookUpFieldPrintForm,"ονομασία",33,"java.lang.String",0,null,null,0,null,null,printFormQueryEditable,"φόρμας εκτύπωσης","φορμών εκτύπωσης",null,entityPanelPrintForm,fieldsOnTitlePrintForm,fieldsOnTitleCaptionPrintForm,printFormErs,2,1,null,true,-1,-1,null));    	 	
               
     
   //----------------------------------------------------------------  
     
    
     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
     String[] lookUpFieldDbYear={"dbYearDescr"};
     entities.add(entityLookUp = new EntityLookUp("dbyear","dbyear","SELECT dbyearId AS \"Νο χρήσης\", dbYearDescr AS \"χρήση\" FROM dbyear","WHERE dbCompanyId LIKE '"+VariablesGlobal.globalCompanyId+"'",null,"", "ORDER BY dbYearDescr","" ,"dbyearId","Νο χρήσης","dbyearId","χρήση",2,lookUpFieldDbYear,"χρήση",7,"java.lang.String",0,null,null,0,null,null,"","της χρήσης","των χρήσεων",null,entityPanelDbyear,fieldsOnTitleDbyear,fieldsOnTitleCaptionDbyear,null,-1,1,null,false,-1,-1,null));  

  
          
     return entities;
   }
  
     public void addEntitiesParameters()  // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
    {  


        //------------------------------------------------------------
    
  
         int[] companySetSerSalesFieldsOrderby ={2};
       String[] companySetSerSalesFieldsForSums=null;
       EntityParameter pqs = new EntityParameter("dbcompanyset", "SELECT dbCompanyId AS \"Νο\" FROM dbcompanyset ORDER BY dbCompanyId" ,"SELECT dbCompanyId AS \"Νο\" ","FROM dbcompanyset","",companySetSerSalesFieldsForSums,companySetSerSalesDBFields,"ρυθμ. τιμολόγησης","DORO","Νο","dbcompanyid", null,null,"παρ. υπηρεσίας", "παρ. υπηρεσιών",companySetSerSalesEntityPanel,null,companySetSerSalesFieldsOnTitle,companySetSerSalesFieldsOnTitleCaption,companySetSerSalesFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empqs = new EntityMenu();
        empqs.setEntityParameter(pqs,ICO_SETTINGS);
        empqs.setEntityType(ENTITY_TYPE_PARAMETER);//ENTITY_TYPE_PARAMETER);
          DataTreeNode nodeemq = new DataTreeNode(empqs);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeemq); 
        //-------------------------------------transaction types below-------------
   
        
        
        
       
        // -------------------------transaction types above-----------------  
  

        
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
         
  
        
        //---------------------------------------------
        int[] serviceCatFieldsOrderby ={2};
        String[] fieldsForSumsServiceCat=null;//                                in query: because it has the same table 2 times, we use the 1st table as it is, in order to be queried correct in title fields
        EntityParameter pqc = new EntityParameter("stockcat", "SELECT stockCatId AS\"Νο κατηγορίας\", catDescr AS\"κατηγορία\", dbCompanyId FROM stockcat WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY stockcat.catDescr","SELECT stockCatId AS\"Νο κατηγορίας\", catDescr AS\"κατηγορία\", dbCompanyId","FROM stockcat","WHERE dbCompanyID LIKE "+VariablesGlobal.globalCompanyId,fieldsForSumsServiceCat,serviceCatDBFields ,"κατηγορίες υπηρεσίας","DORM","Νο κατηγορίας υπηρεσίας","stockCatId",null,null,"κατηγορίας της υπηρεσίας", "κατηγοριών των υπηρεσιών",entityPanelServiceCat,null,fieldsOnTitleServiceCat,fieldsOnTitleCaptionServiceCat,serviceCatFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empqc = new EntityMenu();
        empqc.setEntityParameter(pqc,ICO_TABLE16);
        empqc.setEntityType(ENTITY_TYPE_PARAMETER);
       // DataTreeNode nodeempm = new DataTreeNode(empm);
       // nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempm);         

       EntityParameter[] pz = {pqc};
        EntityMenu[] empza = {empqc};
        //empz.setEntityParameter(pz,ICO_TABLE16);
        //empza.setEntityType(ENTITY_TYPE_PARAMETER);

        EntityManyDataManyRec pza = new EntityManyDataManyRec("πίνακες", "κατηγορίες υπηρεσιών",pz,empza);
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
        
        
        //---------------------------------------------------------------------
       
       EntityGroupOfComps[] salelineEntityGroupOfComps = new EntityGroupOfComps[4];
       salelineEntityGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       salelineEntityGroupOfComps[1] = new EntityGroupOfComps("συναλλασσόμενος",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       salelineEntityGroupOfComps[2] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       salelineEntityGroupOfComps[3] = new EntityGroupOfComps("υπηρεσίες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);      
        
       EntityFilterSettings[] invoiceServiceErs = new EntityFilterSettings[5];   
      
     // invoiceServiceErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      invoiceServiceErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //invoiceServiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       //invoiceServiceErs[1]=new EntityFilterSettings("Νο συναλλασσόμενου","lookup","string","fromto","traderId","trader","trader","",1,-1,-1,FIELD_NOCOMPLETION);
       //invoiceServiceErs[2]=new EntityFilterSettings("επίθετο","","string","equals","name","trader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceServiceErs[1]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","trader","trader","",1,-1,-1,FIELD_NOCOMPLETION);
      // invoiceServiceErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","trader",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
       invoiceServiceErs[2]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfSale","saleheader",null,"",2,0,-1,FIELD_NOCOMPLETION);
      // invoiceErs[6]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","d",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       invoiceServiceErs[3]=new EntityFilterSettings("τελικό ποσό","","double","fromto","priceTotal","saleheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);      
       invoiceServiceErs[4]=new EntityFilterSettings("υπηρεσία","checkboxTable","string","","stockId","stock","saleline","",3,-1,-1,FIELD_NOCOMPLETION);
     //  invoiceServiceErs[8]=new EntityFilterSettings("ΦΠΑ υπηρεσίας","checkboxTable","string","","vatCatId","vatcat","stock","",3,-1,-1,FIELD_NOCOMPLETION);       


        boolean[] boolSettingsSaleLine = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsSaleLine =new EntityReportBandField[10];
       
        entityReportBandFieldsSaleLine[0] = new EntityReportBandField("saleline","inc","inc","java.lang.String",8,true,null,null);
        entityReportBandFieldsSaleLine[1] = new EntityReportBandField("saleline","saleHeaderId","saleHeaderId","java.lang.String",8,true,null,null);
        entityReportBandFieldsSaleLine[2] = new EntityReportBandField("saleline","stockId","stockId","java.lang.String",8,true,null,null);        
        entityReportBandFieldsSaleLine[3] = new EntityReportBandField("stock","descr","descr","java.lang.String",35,true,null,null);        
        entityReportBandFieldsSaleLine[4] = new EntityReportBandField("saleline","description","περιγραφή","java.lang.String",25,true,null,null);  
        entityReportBandFieldsSaleLine[5] = new EntityReportBandField("saleline","quantity","quantity","java.lang.String",8,true,null,null);                    
        entityReportBandFieldsSaleLine[6] = new EntityReportBandField("saleline","priceBeforeVat","priceBeforeVat","java.lang.Double",15,true,null,null);
        entityReportBandFieldsSaleLine[7] = new EntityReportBandField("saleline","vatPercentage","vatPercentage","java.lang.String",15,true,null,null);
        entityReportBandFieldsSaleLine[8] = new EntityReportBandField("saleline","vatValue","vatValue","java.lang.Double",15,true,null,null);
        entityReportBandFieldsSaleLine[9] = new EntityReportBandField("saleline","valueWithVat","valueWithVat","java.lang.Double",15,true,null,null);
        //entityReportBandFieldsSaleLine[9] = new EntityReportBandField("saleline","traderId","Νο συναλλασσόμενου","java.lang.String",7,true,null,null);                    
                    
         boolean[] boolSettingstrader = {true,true,true,true};
       // boolean[] boolSettingsTraderΑ = {true,true,true,true};             
        boolean[] boolSettingsSaleHeaderΑ = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsSaleHeaderA =new EntityReportBandField[11];
       
        entityReportBandFieldsSaleHeaderA[0] = new EntityReportBandField("saleheader","saleHeaderId","saleHeaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsSaleHeaderA[1] = new EntityReportBandField("saleheader","saleCodeOfDocument","κωδ παραστατικού","java.lang.String",15,true,null,null);
        entityReportBandFieldsSaleHeaderA[2] = new EntityReportBandField("saleheader","actionseriesId","actionseriesId","java.lang.String",8,true,null,null);   
        entityReportBandFieldsSaleHeaderA[3] = new EntityReportBandField("actionseries","actionSeriesCode","actionSeriesCode","java.lang.String",9,true,null,null);           
        entityReportBandFieldsSaleHeaderA[4] = new EntityReportBandField("saleheader","dateOfSale","ημερομηνία","java.lang.Date",18,true,null,null);        
        entityReportBandFieldsSaleHeaderA[5] = new EntityReportBandField("saleheader","pricePreVat","προ φόρου","java.lang.Double",9,true,null,null);  
        entityReportBandFieldsSaleHeaderA[5] = new EntityReportBandField("saleheader","priceTotal","τελικό ποσό","java.lang.Double",9,true,null,null);  
        entityReportBandFieldsSaleHeaderA[6] = new EntityReportBandField("saleheader","traderId","Νο συναλλασσόμενου","java.lang.String",8,true,null,null);
        entityReportBandFieldsSaleHeaderA[7] = new EntityReportBandField("saleheader","dbYearId","dbYearId","java.lang.String",9,true,null,null);
        entityReportBandFieldsSaleHeaderA[8] = new EntityReportBandField("saleheader","countTotal","πλήθος","java.lang.String",9,true,null,null); 
        entityReportBandFieldsSaleHeaderA[9] = new EntityReportBandField("saleheader","withholdingtaxAmount","withholdingtaxAmount","java.lang.Double",10,true,null,null);  
        entityReportBandFieldsSaleHeaderA[10] = new EntityReportBandField("saleheader","priceTotalAfterWithholdingTax","priceTotalAfterWithholdingTax","java.lang.Double",10,true,null,null);          
        
        //EntityReportBand[] reportBandSaleHeader = new EntityReportBand[1];
        //reportBandSaleHeader[0] = new EntityReportBand("saleheader","πώληση","saleheader",entityReportBandFieldsSaleHeader,ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleHeader);

       // boolean[] boolSettingsTrader = {true,true,true,true};
      /* EntityReportBandField[] entityReportBandFieldsTraderB =new EntityReportBandField[6];
       
        entityReportBandFieldsTraderB[0] = new EntityReportBandField("trader","traderId","Νο συναλλασσόμενου","java.lang.Integer",8,true,null,null);
        entityReportBandFieldsTraderB[1] = new EntityReportBandField("trader","dbCompanyId","dbCompanyId","java.lang.String",8,true,null,null);
        entityReportBandFieldsTraderB[2] = new EntityReportBandField("trader","title","επωνυμία","java.lang.String",40,true,null,null);
        entityReportBandFieldsTraderB[3] = new EntityReportBandField("trader","traderCode","κωδικός","java.lang.String",10,true,null,null);
        entityReportBandFieldsTraderB[4] = new EntityReportBandField("trader","vatNo","Α.Φ.Μ.","java.lang.String",10,true,null,null);
        entityReportBandFieldsTraderB[5] = new EntityReportBandField("trader","activityDescr","δραστηριότητα","java.lang.String",30,true,null,null);  */
      
      //        boolean[] boolSettingstrader = {true,true,true,true};
       EntityReportBandField[] entityReportBandFieldstraderA =new EntityReportBandField[5];
       
        entityReportBandFieldstraderA[0] = new EntityReportBandField("trader","traderId","Νο συναλλασσόμενου","java.lang.Integer",10,true,null,null);
        //entityReportBandFieldstraderA[1] = new EntityReportBandField("trader","dbCompanyId","dbCompanyId","java.lang.String",10,true,null,null);
        entityReportBandFieldstraderA[1] = new EntityReportBandField("trader","title","επωνυμία","java.lang.String",23,true,null,null);
        entityReportBandFieldstraderA[2] = new EntityReportBandField("trader","traderCode","κωδικός","java.lang.String",10,true,null,null);
        entityReportBandFieldstraderA[3] = new EntityReportBandField("trader","vatNo","Α.Φ.Μ.","java.lang.String",11,true,null,null);
        entityReportBandFieldstraderA[4] = new EntityReportBandField("trader","activityDescr","δραστηριότητα","java.lang.String",28,true,null,null);

             
        int[] traderOrderBy={2,4,3}; 
        //int[] invoiceCheckFieldOrderby1 = {3,5};
        int[] invoiceCheckFieldOrderby2 = {5,4};
        int[] invoiceCheckFieldOrderby3 = {1};
        

       EntityReportBand[] reportBandTraderService = new EntityReportBand[3];
       //reportBandTraderService[0] = new EntityReportBand("trader","συναλλασσόμενος","trader",entityReportBandFieldsTraderB,invoiceCheckFieldOrderby1,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsTraderΑ,entityPanelTrader,fieldsOnTitleTrader,fieldsOnTitleCaptionTrader);//,"","");
       reportBandTraderService[0] = new EntityReportBand("trader","συναλλασσόμενος","trader",entityReportBandFieldstraderA,traderOrderBy,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingstrader,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader);//,"","");
       reportBandTraderService[1] = new EntityReportBand("saleheader","παραστατικό","saleheader",entityReportBandFieldsSaleHeaderA,invoiceCheckFieldOrderby2,"saleheaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleHeaderΑ,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);//,"",""); 
       reportBandTraderService[2] = new EntityReportBand("saleline","υπηρεσία","saleline",entityReportBandFieldsSaleLine,invoiceCheckFieldOrderby3,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleLine,null,null,null);//,"","");
       
       boolean[] boolSettingsReportInvoiceA = {true,false,true,true,false};
       
       
       int[] intReportSettingsInvoiceA= {0,0,0,0};

       //EntityFilterSettings[] invoiceErs = null;
       //EntityGroupOfComps[] invoiceEntityGroupOfComps = null;
       int[] invoicesSelectedA =null;
       //String globalYearPlusOne="";
       
       EntityReport rc = new EntityReport("rptTraderSaleheaderSaleline",REPORT_CAT_1,reportBandTraderService,"SELECT trader.traderId, saleheader.dateOfSale, saleheader.saleHeaderId, saleline.inc, trader.*, saleheader.*, saleline.*, stock.*, actionseries.* FROM trader, saleheader, saleline, stock, actionseries "
               + "WHERE saleline.dbCompanyId = saleheader.dbCompanyId AND saleheader.dbCompanyId = stock.dbCompanyId AND stock.dbCompanyId = actionseries.dbCompanyId AND saleheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleheader.traderId = trader.traderId AND saleheader.saleHeaderId = saleline.saleHeaderId AND saleline.stockId = stock.stockId AND actionseries.actionseriesId = saleheader.actionseriesId",""/*"ORDER BY name"*/,"ODMR","συναλλασσόμενοι, παραστατικά, υπηρεσίες","",invoiceServiceErs,salelineEntityGroupOfComps,invoicesSelectedA, null,/*invoiceCheckFieldOrderby3,*/"","","",intReportSettingsInvoiceA,boolSettingsReportInvoiceA,"");//,globalYearPlusOne);
          // EntityReport ra = new EntityReport("invoice",REPORT_CAT_1,null,invoiceEntQuery,null,null,"ODMR","κατάσταση ελέγχου","",invoiceErs,invoiceEntityGroupOfComps,invoicesSelected, null,invoiceFieldOrderby) ;
                            
        EntityMenu emrc = new EntityMenu();
        emrc.setEntityReport(rc,ICO_PRINT_PREVIEW16);
        emrc.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrc = new DataTreeNode(emrc);
        //nodeCat = nodeRoot.getChildFromCaption(REPORTS).getChildFromCaption(REPORT_CAT_1);
        nodeReports.getChildFromCaption(REPORT_CAT_1).addChild(nodeemrc);          
        
        //---------------------------------
 

       
       EntityFilterSettings[] salesErs = new EntityFilterSettings[4];   
     // salesErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      salesErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //salesErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       //salesErs[1]=new EntityFilterSettings("Νο συναλλασσόμενου","lookup","string","fromto","traderId","trader","trader","",1,-1,-1,FIELD_NOCOMPLETION);
       //salesErs[2]=new EntityFilterSettings("επίθετο","","string","equals","name","trader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       salesErs[1]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","trader","trader","",1,-1,-1,FIELD_NOCOMPLETION);
      // salesErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","trader",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
       salesErs[2]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfSale","saleheader",null,"",1,0,-1,FIELD_NOCOMPLETION);
       salesErs[3]=new EntityFilterSettings("υπηρεσία","checkboxTable","string","","stockId","stock","saleline","",2,-1,-1,FIELD_NOCOMPLETION);

       EntityGroupOfComps[] saleDocumentGroupOfComps = new EntityGroupOfComps[3];
       saleDocumentGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       saleDocumentGroupOfComps[1] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       saleDocumentGroupOfComps[2] = new EntityGroupOfComps("υπηρεσίες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       //applicationEntityGroupOfComps[1] = new EntityGroupOfComps("αγρότης",4,0);
       
       int[] invoicesaSelected = null;//{1,2,3,4,0,0,0,0,0,0,11,12,0,14,};
//       int[] fileOrderby ={1};
//       boolean[] boolSettingsReportDoc = {true,true,true,true,true}; 
//      boolean[] boolSettingsReportTraderfileA = {true,true,true,true,true}; 
//      boolean[] boolSettingsReportTraderfileB = {true,true,true,true,true}; 
//       int[] intSettingsReportTraderfile={0,0,0,0};
      	
       //fileEntityQuery[0]= new EntityQuery("SELECT trader.traderafm AS traderafm, SUM(invoice.valueReturn) AS value,SUM(retValueAccordingToType(3, invoice.currencyId, invoice.valueReturn)) AS valueReturn3 FROM application, trader, invoice "+
      // "WHERE trader.traderId=application.traderId AND invoice.traderId=trader.traderId AND application.dbyear=invoice.dbyear AND application.deliveryId =invoice.deliveryId AND application.dbCompanyId=invoice.dbCompanyId GROUP BY trader.traderId",false,0,null,null,null,null,null);


      
                 
        EntityReportBandField[] entityReportBandFieldsSaleLineA =new EntityReportBandField[9];
       
        entityReportBandFieldsSaleLineA[0] = new EntityReportBandField("saleline","inc","inc","java.lang.String",10,true,null,null);
        entityReportBandFieldsSaleLineA[1] = new EntityReportBandField("saleline","saleHeaderId","saleHeaderId","java.lang.String",10,true,null,null);
        entityReportBandFieldsSaleLineA[2] = new EntityReportBandField("saleline","stockId","stockId","java.lang.String",10,true,null,null);        
        entityReportBandFieldsSaleLineA[3] = new EntityReportBandField("stock","descr","descr","java.lang.String",35,true,null,null);        
        entityReportBandFieldsSaleLineA[4] = new EntityReportBandField("saleline","description","περιγραφή","java.lang.String",25,true,null,null);  
        entityReportBandFieldsSaleLineA[5] = new EntityReportBandField("saleline","quantity","quantity","java.lang.String",12,true,null,null);                    
        entityReportBandFieldsSaleLineA[6] = new EntityReportBandField("saleline","priceBeforeVat","priceBeforeVat","java.lang.Double",15,true,null,null);
        //entityReportBandFieldsSaleLineA[6] = new EntityReportBandField("saleline","vatCatId","vatCatId","java.lang.String",15,true,null,null);
        entityReportBandFieldsSaleLineA[7] = new EntityReportBandField("saleline","vatValue","vatValue","java.lang.Double",15,true,null,null);
        entityReportBandFieldsSaleLineA[8] = new EntityReportBandField("saleline","valueWithVat","valueWithVat","java.lang.Double",15,true,null,null);
        //entityReportBandFieldsSaleLineA[8] = new EntityReportBandField("saleline","traderId","Νο συναλλασσόμενου","java.lang.String",7,true,null,null);                    
                    
                    
        boolean[] boolSettingsSaleHeader = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsSaleHeaderB =new EntityReportBandField[8];
       
        entityReportBandFieldsSaleHeaderB[0] = new EntityReportBandField("saleheader","saleCodeOfDocument","κωδ παραστατικού","java.lang.String",15,true,null,null);
        entityReportBandFieldsSaleHeaderB[1] = new EntityReportBandField("saleheader","dateOfSale","ημερομηνία","java.lang.Date",18,true,null,null);        
        entityReportBandFieldsSaleHeaderB[2] = new EntityReportBandField("saleheader","priceTotal","τελικό ποσό","java.lang.Double",20,true,null,null);  
        entityReportBandFieldsSaleHeaderB[3] = new EntityReportBandField("saleheader","traderId","Νο συναλλασσόμενου","java.lang.String",7,true,null,null);
        entityReportBandFieldsSaleHeaderB[4] = new EntityReportBandField("saleheader","saleHeaderId","saleHeaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsSaleHeaderB[5] = new EntityReportBandField("trader","title","όνομα","java.lang.String",30,true,null,null);
        entityReportBandFieldsSaleHeaderB[6] = new EntityReportBandField("saleheader","dbCompanyId","dbCompanyId","java.lang.String",9,true,null,null);
        entityReportBandFieldsSaleHeaderB[7] = new EntityReportBandField("saleheader","dbYearId","dbYearId","java.lang.String",9,true,null,null);      
      
      int[] headOrderby ={2,1,6,5};
      int[] lineOrderby ={1};

//       EntityReportBand[] reportBandTraderServiceSaleDoc = new EntityReportBand[2];                                                                                                         //  EntityPanel[] entityPanelDrillIn,String[]  panelDrillFieldsOnTitleIn,String[]  panelDrillFieldsOnTitleCaptionIn
       reportBandTraderServiceSaleDoc[0] = new EntityReportBand("saleheader","παραστατικά","saleheader",entityReportBandFieldsSaleHeaderB,headOrderby,"saleHeaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleHeader,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);//,"",""); 
       reportBandTraderServiceSaleDoc[1] = new EntityReportBand("saleline","υπηρεσίες","saleline",entityReportBandFieldsSaleLineA,lineOrderby,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleLine,null,null,null);        
      
      
      
       
       

     //  docEntityReportBand[0] = new EntityReportBand("αγρότες","SELECT trader.traderafm AS traderafm, SUM(invoice.valueReturn) AS value,SUM(retValueAccordingToType(3, invoice.currencyId, invoice.valueReturn)) AS valueReturn3 FROM application, trader, invoice, dbDelivery "+
     //  "WHERE trader.traderId=application.traderId AND invoice.traderId=trader.traderId AND application.dbyear=invoice.dbyear AND application.deliveryId =invoice.deliveryId AND application.dbCompanyId=invoice.dbCompanyId GROUP BY trader.traderId","","application",ENTITYREPORT_QUERY_TYPE_MAIN,-1,null,boolSettingsFar); 
        
       
       EntityReport entReportServiceSale = new EntityReport("invoicedosandservices", REPORT_CAT_1,reportBandTraderServiceSaleDoc,"SELECT * FROM saleheader, saleline, trader, stock, actionseries "
               + "WHERE saleheader.traderId = trader.traderId AND saleheader.saleHeaderId = saleline.saleHeaderId AND saleline.stockId = stock.stockId AND actionseries.actionseriesId = saleheader.actionseriesId "
               + "AND stock.dbCompanyId = actionseries.dbCompanyId  AND saleheader.dbCompanyId = stock.dbCompanyId AND saleline.dbCompanyId = saleheader.dbCompanyId AND saleheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,/*"ORDER BY trader.name"*/"","ODMR","παραστατικά και υπηρεσίες","",salesErs,saleDocumentGroupOfComps,invoicesaSelected, null,/*fileOrderby,*/"","","",intSettingsReportTraderfile,boolSettingsReportDoc,"");//,globalYearPlusOne) ;
        EntityMenu emrh = new EntityMenu();                                                                                                                                                                                                                                                                                                                                        //   invoiceServiceErs,salelineEntityGroupOfComps,invoicesSelected, null,invoiceCheckFieldOrderby3,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,globalYearPlusOne);                                                
        emrh.setEntityReport(entReportServiceSale,ICO_PRINT_PREVIEW16); //ICO_REPORTFILE
        emrh.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrh = new DataTreeNode(emrh);
        nodeReports.getChildFromCaption(REPORT_CAT_1).addChild(nodeemrh);

       // ------------------------------------- REPORT_CAT_2 --------------------

        
       EntityGroupOfComps[] saleheaderEntityGroupOfComps = new EntityGroupOfComps[3];
       saleheaderEntityGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       saleheaderEntityGroupOfComps[1] = new EntityGroupOfComps("συναλλασσόμενος",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       saleheaderEntityGroupOfComps[2] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       //invoiceEntityGroupOfComps[3] = new EntityGroupOfComps("υπηρεσίες",4,0);        
        
       EntityFilterSettings[] invoiceErs = new EntityFilterSettings[7];   
      
     // invoiceErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      invoiceErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       invoiceErs[1]=new EntityFilterSettings("Νο συναλλασσόμενου","lookup","string","fromto","traderId","trader","trader","",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[2]=new EntityFilterSettings("επίθετο","","string","equals","title","trader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[3]=new EntityFilterSettings("συναλλασσόμενος","checkboxTable","string","","traderId","trader","trader","",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","trader",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[6]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","f","",1,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[7]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","f","",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[5]=new EntityFilterSettings("ημ/νία παραστατικού","","date","fromto","dateOfSale","saleheader",null,"",2,0,-1,FIELD_NOCOMPLETION);
      // invoiceErs[6]=new EntityFilterSettings("πλήθος παρ/κών","","double","fromto","invcount","d",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[6]=new EntityFilterSettings("τελικό ποσό","","double","fromto","priceTotal","saleheader",null,"",2,-1,-1,FIELD_NOCOMPLETION);
      // invoiceErs[8]=new EntityFilterSettings("ποσό επιστροφής","","double","fromto","valueReturn","a",null,"",2,-1,-1,FIELD_NOCOMPLETION);
      // invoiceErs[9]=new EntityFilterSettings("ποσό κράτησης","","double","fromto","payment","d",null,"",2,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[13]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","buyer","i","",3,-1,1,FIELD_NOCOMPLETION);
     //  invoiceErs[10]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","product","i","",3,-1,1,FIELD_NOCOMPLETION);
      // invoiceErs[15]=new EntityFilterSettings("είδος προϊόντος","checkboxTable","string","","currencyId","currency","i","",3,-1,1,FIELD_NOCOMPLETION);
       //invoiceErs[16]=new EntityFilterSettings("τύπος παραστατικού","checkboxTable","string","","paymentTypeId","paymentType","i","",3,-1,1,FIELD_NOCOMPLETION);        
boolean[] boolSettingsSaleHeaderΒ = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsSaleHeader =new EntityReportBandField[9];
       
        entityReportBandFieldsSaleHeader[0] = new EntityReportBandField("saleheader","saleCodeOfDocument","κωδ παραστατικού","java.lang.String",20,true,null,null);
        entityReportBandFieldsSaleHeader[1] = new EntityReportBandField("saleheader","actionseriesId","actionseriesId","java.lang.String",8,true,null,null);   
        entityReportBandFieldsSaleHeader[2] = new EntityReportBandField("actionseries","actionSeriesCode","actionSeriesCode","java.lang.String",9,true,null,null);   
        entityReportBandFieldsSaleHeader[3] = new EntityReportBandField("saleheader","dateOfSale","ημερομηνία","java.lang.Date",25,true,null,null);   
        entityReportBandFieldsSaleHeader[4] = new EntityReportBandField("saleheader","priceTotal","τελικό ποσό","java.lang.Double",13,true,null,null);  
        entityReportBandFieldsSaleHeader[5] = new EntityReportBandField("saleheader","traderId","Νο συναλλασσόμενου","java.lang.String",7,true,null,null);
        entityReportBandFieldsSaleHeader[6] = new EntityReportBandField("saleheader","dbYearId","dbYearId","java.lang.String",7,true,null,null);
        entityReportBandFieldsSaleHeader[7] = new EntityReportBandField("saleheader","saleHeaderId","saleHeaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsSaleHeader[8] = new EntityReportBandField("saleheader","countTotal","πλήθος","java.lang.String",9,true,null,null);         
         

       /* boolean[] boolSettingsTraderA = {true,true,true,true};
       EntityReportBandField[] entityReportBandFieldsTraderA =new EntityReportBandField[6];
       
        entityReportBandFieldsTraderA[0] = new EntityReportBandField("trader","traderId","Νο συναλλασσόμενου","java.lang.Integer",10,true,null,null);
        entityReportBandFieldsTraderA[1] = new EntityReportBandField("trader","dbCompanyId","dbCompanyId","java.lang.String",10,true,null,null);
        entityReportBandFieldsTraderA[2] = new EntityReportBandField("trader","title","επωνυμία","java.lang.String",23,true,null,null);
        entityReportBandFieldsTraderA[3] = new EntityReportBandField("trader","traderCode","κωδικός","java.lang.String",10,true,null,null);
        entityReportBandFieldsTraderA[4] = new EntityReportBandField("trader","vatNo","Α.Φ.Μ.","java.lang.String",11,true,null,null);
        entityReportBandFieldsTraderA[5] = new EntityReportBandField("trader","activityDescr","δραστηριότητα","java.lang.String",28,true,null,null); */

        EntityReportBandField[] entityReportBandFieldsTraderA =new EntityReportBandField[5];
       
        entityReportBandFieldsTraderA[0] = new EntityReportBandField("trader","traderId","Νο συναλλασσόμενου","java.lang.Integer",10,true,null,null);
        //entityReportBandFieldstraderA[1] = new EntityReportBandField("trader","dbCompanyId","dbCompanyId","java.lang.String",10,true,null,null);
        entityReportBandFieldsTraderA[1] = new EntityReportBandField("trader","title","επωνυμία","java.lang.String",23,true,null,null);
        entityReportBandFieldsTraderA[2] = new EntityReportBandField("trader","traderCode","κωδικός","java.lang.String",10,true,null,null);
        entityReportBandFieldstraderA[3] = new EntityReportBandField("trader","vatNo","Α.Φ.Μ.","java.lang.String",11,true,null,null);
        entityReportBandFieldstraderA[4] = new EntityReportBandField("trader","activityDescr","δραστηριότητα","java.lang.String",28,true,null,null);
        //EntityReportBand[] reportBandSaleHeader = new EntityReportBand[1];
       //reportBandSaleHeader[0] = new EntityReportBand("saleheader","πώληση","saleheader",entityReportBandFieldsSaleHeader,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleHeader); 

       int[] custCheckFieldOrderby = {3,5,4};
       int[] headCheckFieldOrderby = {4,1,9};
       
       
       EntityReportBand[] reportBandTrader = new EntityReportBand[2];
       //reportBandTrader[0] = new EntityReportBand("trader","συναλλασσόμενος","trader",entityReportBandFieldsTraderA,custCheckFieldOrderby,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsTraderA,entityPanelTrader,fieldsOnTitleTrader,fieldsOnTitleCaptionTrader);//,"","");
       reportBandTrader[0] = new EntityReportBand("trader","συναλλασσόμενος","trader",entityReportBandFieldstraderA,traderOrderBy,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingstrader,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader);//,"","");
       reportBandTrader[1] = new EntityReportBand("saleheader","παραστατικά","saleheader",entityReportBandFieldsSaleHeader,headCheckFieldOrderby,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleHeaderΒ,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);//,"",""); 
 
       boolean[] boolSettingsReportInvoiceB = {true,false,true,true,false};
       
      
       int[] intReportSettingsInvoiceB= {0,0,0,0};

//       EntityFilterSettings[] invoiceErs = null;
 //      EntityGroupOfComps[] invoiceEntityGroupOfComps = null;
       int[] invoicesSelectedB =null;
       String globalYearPlusOne="";
       
       EntityReport rb = new EntityReport("rptTraderSaleheader",REPORT_CAT_1,reportBandTrader,"SELECT * FROM trader, saleheader,actionseries WHERE saleheader.traderId = trader.traderId AND actionseries.actionseriesId = saleheader.actionseriesId AND actionseries.dbCompanyId LIKE '"+VariablesGlobal.globalCompanyId+"'","","ODMR","συναλλασσόμενοι και παραστατικά","",invoiceErs,saleheaderEntityGroupOfComps,invoicesSelectedB, null,/*invoiceCheckFieldOrderbyB,*/"","","",intReportSettingsInvoiceB,boolSettingsReportInvoiceB,"");//,globalYearPlusOne);
          // EntityReport ra = new EntityReport("invoice",REPORT_CAT_1,null,invoiceEntQuery,null,null,"ODMR","κατάσταση ελέγχου","",invoiceErs,invoiceEntityGroupOfComps,invoicesSelected, null,invoiceFieldOrderby) ;
                    
        EntityMenu emrb = new EntityMenu();
        emrb.setEntityReport(rb,ICO_PRINT_PREVIEW16);
        emrb.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrb = new DataTreeNode(emrb);
        //nodeCat = nodeRoot.getChildFromCaption(REPORTS).getChildFromCaption(REPORT_CAT_1);
        nodeReports.getChildFromCaption(REPORT_CAT_2).addChild(nodeemrb);                 


       EntityFilterSettings[] traderErs = new EntityFilterSettings[4];   
       //traderErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","trader",VariablesGlobal.globalCompanyId,0,-1,-1,FIELD_OBLIGATORY);
       //invoiceErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbyearId","dbyear","trader", VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
       //invoiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       traderErs[0]=new EntityFilterSettings("Νο συναλλασσόμενου","lookup","string","fromto","traderId","trader","trader","",0,-1,-1,FIELD_NOCOMPLETION);
       traderErs[1]=new EntityFilterSettings("επίθετο","","string","equals","title","trader",null,"",0,-1,-1,FIELD_NOCOMPLETION);
       traderErs[2]=new EntityFilterSettings("αγρότης","checkboxTable","string","","traderId","trader","trader","",0,-1,-1,FIELD_NOCOMPLETION);
       traderErs[3]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","trader",null,"",0,-1,-1,FIELD_NOCOMPLETION);
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
      

       boolean[] boolSettingsTrader = {true,true,true,true};
       
       /*EntityReportBandField[] entityReportBandFieldsTrader =new EntityReportBandField[6];

        entityReportBandFieldsTrader[0] = new EntityReportBandField("trader","traderId","Νο συναλλασσόμενου","java.lang.Integer",11,true,null,null);
        entityReportBandFieldsTrader[1] = new EntityReportBandField("trader","dbCompanyId","dbCompanyId","java.lang.String",11,true,null,null);
        entityReportBandFieldsTrader[2] = new EntityReportBandField("trader","title","επωνυμία","java.lang.String",44,true,null,null);
        entityReportBandFieldsTrader[3] = new EntityReportBandField("trader","traderCode","κωδικός","java.lang.String",18,true,null,null);
        entityReportBandFieldsTrader[4] = new EntityReportBandField("trader","vatNo","Α.Φ.Μ.","java.lang.String",18,true,null,null);
        entityReportBandFieldsTrader[5] = new EntityReportBandField("trader","activityDescr","δραστηριότητα","java.lang.String",45,true,null,null);*/
       
               EntityReportBandField[] entityReportBandFieldsTrader =new EntityReportBandField[5];
       
        entityReportBandFieldsTrader[0] = new EntityReportBandField("trader","traderId","Νο συναλλασσόμενου","java.lang.Integer",10,true,null,null);
        //entityReportBandFieldsTrader[1] = new EntityReportBandField("trader","dbCompanyId","dbCompanyId","java.lang.String",10,true,null,null);
        entityReportBandFieldsTrader[1] = new EntityReportBandField("trader","title","επωνυμία","java.lang.String",23,true,null,null);
        entityReportBandFieldsTrader[2] = new EntityReportBandField("trader","traderCode","κωδικός","java.lang.String",10,true,null,null);
        entityReportBandFieldsTrader[3] = new EntityReportBandField("trader","vatNo","Α.Φ.Μ.","java.lang.String",11,true,null,null);
        entityReportBandFieldsTrader[4] = new EntityReportBandField("trader","activityDescr","δραστηριότητα","java.lang.String",28,true,null,null);
               
        int[] invoiceCheckFieldOrderby = {3,4,5};
       EntityReportBand[] reportBandTraderA = new EntityReportBand[1];
       reportBandTrader[0] = new EntityReportBand("trader","συναλλασσόμενος","trader",entityReportBandFieldstraderA,traderOrderBy,"traderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingstrader,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader);//,"","");
       //reportBandTraderA[0] = new EntityReportBand("trader","συναλλασσόμενος","trader",entityReportBandFieldsTrader,invoiceCheckFieldOrderby,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsTrader,entityPanelTrader,fieldsOnTitleTrader,fieldsOnTitleCaptionTrader);//,"","");
       boolean[] boolSettingsReportInvoice = {true,false,true,true,false};
      
       int[] intReportSettingsInvoice= {0,0,0,0};
       
       
       EntityReport ra = new EntityReport("rptTrader",REPORT_CAT_1,reportBandTraderA,"SELECT * FROM trader ",/*"ORDER BY name"*/"","ODMR","συναλλασσόμενοι","",traderErs,traderEntityGroupOfComps,invoicesSelected, null,/*invoiceCheckFieldOrderby,*/"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne);
          // EntityReport ra = new EntityReport("invoice",REPORT_CAT_1,null,invoiceEntQuery,null,null,"ODMR","κατάσταση ελέγχου","",invoiceErs,invoiceEntityGroupOfComps,invoicesSelected, null,invoiceFieldOrderby) ;

        EntityMenu emra = new EntityMenu();
        emra.setEntityReport(ra,ICO_PRINT_PREVIEW16);
        emra.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemra = new DataTreeNode(emra);
        //nodeCat = nodeRoot.getChildFromCaption(REPORTS).getChildFromCaption(REPORT_CAT_1);
        nodeReports.getChildFromCaption(REPORT_CAT_2).addChild(nodeemra);        
       // --------------------------------- REPORT_CAT_3 ---------------------
       
 
       //boolean[] boolSettingsInvoice = {false,false,true,true};
       //EntityQuery[] invoiceEntQuery = new EntityQuery[1]; 
       //invoiceEntQuery[0]= new EntityQuery("SELECT trader.traderId,trader.traderAfm, trader.surname,trader.name,invoice.* FROM invoice, trader, application WHERE application.traderId = trader.traderId AND invoice.traderId = trader.traderId",false,0,null,null,null,null,null);
  
       //EntityQuery[] deliveryCheckEntQuery = new EntityQuery[1]; 
       //deliveryCheckEntQuery[0]= new EntityQuery("SELECT invoice.traderId,buyer.buyerTitle , invoice.paymentTypeId,invoice.invoiceNo,invoice.date,invoice.dbyear, invoice.productId, invoice.value, invoice.valueReturn FROM invoice, trader, application, buyer WHERE buyer.buyerId=invoice.buyerId AND invoice.traderId = trader.traderId AND application.traderId=trader.traderId AND application.traderId=invoice.traderId ORDER BY trader.surname,trader.name",false,0,null,null,null,null,null);
//-----        
       // same as entityInfoMany the read only of list
       //String deliveryCheckHeaderEntQuery="SELECT f.traderId AS\"Νο συναλλασσόμενου\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.traderAfm AS\"Α.Φ.Μ.\", permanent AS \"υπολ\" , d.dateOfApplication AS \"ημ/νια αίτησης\" , COUNT(i.value) AS \"πλήθος\", SUM(i.value) AS \"αξία\", SUM(retValueAccordingToType(1, i.currencyId, i.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, i.currencyId, i.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, i.currencyId, i.valueReturn)) AS \"κατ 3\", d.valueReturn AS \"συν επιστρ\", d.payment AS \"κράτηση\" FROM application d, trader f, invoice i WHERE i.traderId = f.traderId AND d.traderId = f.traderId AND i.deliveryId = d.deliveryId AND i.dbyear=d.dbyear AND i.dbCompanyId=d.dbCompanyId GROUP BY f.traderId, d.permanent, d.dateOfApplication ORDER BY f.surname, f.name";
  //     EntityReportGroup[] deliveryEntityReportGroup = new EntityReportGroup[2];
 //      deliveryEntityReportGroup[0] = new EntityReportGroup("αγρότες","SELECT f.traderId AS\"Νο συναλλασσόμενου\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.traderAfm AS\"Α.Φ.Μ.\", permanent AS \"υπολ\" ,a.deliveryId, a.dateOfApplication AS \"ημ/νια αίτησης\" , COUNT(i.value) AS \"πλήθος\", SUM(i.value) AS \"αξία\", SUM(retValueAccordingToType(1, i.currencyId, i.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, i.currencyId, i.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, i.currencyId, i.valueReturn)) AS \"κατ 3\", a.valueReturn AS \"συν επιστρ\", a.payment AS \"κράτηση\" FROM application a, trader f, invoice i WHERE i.traderId = f.traderId AND a.traderId = f.traderId AND i.deliveryId = a.deliveryId AND i.dbyear=a.dbyear AND i.dbCompanyId=a.dbCompanyId GROUP BY f.traderId, a.permanent, a.dateOfApplication, a.valuereturn", "ORDER BY f.surname, f.name","application",ENTITYREPORT_QUERY_TYPE_MAIN,0,"traderId",boolSettingsTrader);  // header
 //      deliveryEntityReportGroup[1] = new EntityReportGroup("παραστατικά","SELECT i.aa AS\"α/α\", i.traderId,i.deliveryId,b.buyerTitle AS\"αγοραστής\", it.abbreviation  AS\"παρ/κο\", i.invoiceNo  AS\"αριθμός\",i.date  AS\"ημερομηνία\",i.dbyear, p.productName  AS\"προϊόν\", i.value  AS\"αξία\", i.valueReturn  AS\"επιστροφή\" FROM invoice i, trader f, application a, buyer b, product p, currency pt, paymentType it WHERE i.productId=p.productId AND pt.currencyId=p.currencyId AND b.buyerId=i.buyerId AND i.traderId = f.traderId AND a.traderId=f.traderId AND a.traderId=i.traderId AND a.dbyear=i.dbyear AND a.dbCompanyId=i.dbCompanyId AND a.deliveryId=i.deliveryId AND i.paymentTypeId=it.paymentTypeId","ORDER BY f.surname,f.name","appinvoice",ENTITYREPORT_QUERY_TYPE_MAIN,1,null,boolSettingsInvoice);  // many

        /*public EntityReportBandField(String nameIn,  String captionIn, String tableNameIn ,String dbFieldNameIn,int groupOfCompsIn,String colClassNameIn,int colWidthIn,
              String defaultValueIn, EntityDBFieldsCalculation[] fieldsCalculationIn)*/


         
       
       
       //---------------------------------------------------------------------------
       
      /* EntityFilterSettings[] deliveryErs = new EntityFilterSettings[5];       
       deliveryErs[0]=new EntityFilterSettings("Νο συναλλασσόμενου","lookup","string","fromto","traderId","trader","f","",-1,-1,1,FIELD_NOCOMPLETION);
       deliveryErs[1]=new EntityFilterSettings("επίθετο","","string","equals","surname","trader",null,"",-1,-1,1,FIELD_NOCOMPLETION);
       deliveryErs[2]=new EntityFilterSettings("ΑΦΜ","","string","equals","traderAfm","trader",null,"",-1,-1,1,FIELD_NOCOMPLETION);
       deliveryErs[3]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","town","",-1,-1,-1,FIELD_NOCOMPLETION);
       deliveryErs[4]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","trader","",-1,-1,1,FIELD_NOCOMPLETION);

       
       EntityGroupOfComps[] deliveryEntityGroupOfComps = null;       

       
       int[]deliveryOrderby = {2,1};  
       	boolean[] boolSettingsTown = {true,true,true,true};    
        boolean[] boolSettingsFarme = {true,true,true,true}; 
        boolean[] boolSettingsReportFarm = {true,true,true,true,true}; 	
       int[] intSettingsReportFarm={0,0,0,0};*/
       
         
       EntityFilterSettings[] invoiceeErs = new EntityFilterSettings[10];       
       invoiceeErs[0]=new EntityFilterSettings("Νο συναλλασσόμενου","lookup","string","fromto","traderId","trader","f","",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[1]=new EntityFilterSettings("αγρότης","checkboxTable","string","","traderId","trader","trader","",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[2]=new EntityFilterSettings("επίθετο","","string","equals","surname","trader",null,"",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[3]=new EntityFilterSettings("ΑΦΜ","","string","equals","traderAfm","trader",null,"",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","trader","",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[5]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","trader","",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[6]=new EntityFilterSettings("ημερομηνία παραστ.","","date","fromto","date","invoice",null,"",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[7]=new EntityFilterSettings("αγοραστής","checkboxTable","string","","buyerId","buyer","invoice","",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[8]=new EntityFilterSettings("προϊόν","checkboxTable","string","","productId","product","invoice","",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[9]=new EntityFilterSettings("τύπος παραστατικού","checkboxTable","string","","paymentTypeId","paymentType","invoice","",-1,-1,0,FIELD_NOCOMPLETION);
       
       //invoiceErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","or","townId","town","");       
       
       /*EntityGroupOfComps[] invoiceeEntityGroupOfComps = null;       
       
       int[] invoiceseSelected ={1,2,3,5,6,7,0,};
       boolean[] boolSettingsInvoi = {true,true,true,true}; 
       boolean[] boolSettingsReportInvoi = {true,true,true,true,true}; 
       int [] intSettingsReportInvoi={0,0,0,0};*/
       

       
       

      /* boolean[] boolSettingsInvo = {true,true,true,true}; 
       boolean[] boolSettingsReportReturn = {true,true,true,true,true}; 
       int[] intSettingsReportReturn={0,0,0,0};*/
       
 //----------------------------------------------------------------------------------------------
 //  mass printing of docs
        EntityMenu emrk = new EntityMenu();                                                                                                                                                                                                                                                                                                                                        //   invoiceServiceErs,salelineEntityGroupOfComps,invoicesSelected, null,invoiceCheckFieldOrderby3,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,globalYearPlusOne);                                                
        emrk.setEntityReport(entReportServiceSaleDoc,ICO_PRINT_PREVIEW_FORM16); //ICO_REPORTFILE
        emrk.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrk = new DataTreeNode(emrk);
        nodeReports.getChildFromCaption(REPORT_CAT_3).addChild(nodeemrk); 
        

     }

  public void addStatisticsNodes() 
  {

     }
  
  public void addToolNodes() 
  {

  }

  
public EntityDBFields[] getEntityDbFieldsToImport()
{   
    //EntityDBFields[] eDbFields = new EntityDBFields[traderDBFields.length+serviceDBFields.length];
    
   // EntityDBFields[] edbfc  = traderDBFields;

    //EntityDBFields[] edbfs  = serviceDBFields;
    ArrayList <EntityDBFields> lstSerSales = new ArrayList();
    lstSerSales = addDbFieldsToArrayListToImport(lstSerSales,traderDBFields);
    lstSerSales = addDbFieldsToArrayListToImport(lstSerSales,serviceDBFields);

 
    

    
    
    EntityDBFields[] edbf = new EntityDBFields[lstSerSales.size()];
    for(int d=0;d<lstSerSales.size();d++)
    {
        edbf[d]= lstSerSales.get(d);
    }

     return edbf;
}
  
   private ArrayList <EntityDBFields>  addDbFieldsToArrayListToImport( ArrayList <EntityDBFields> lstSerSales,EntityDBFields[] dbFields)
   {
       //ArrayList <EntityDBFields> = lstSerSales;
         for(int l = 0;l<dbFields.length;l++)
         {
              lstSerSales.add(dbFields[l]);
         }
         
         return lstSerSales;
   }


  
}




// ServiceSales

// todo features---------
// manytomany: able to create new records. When data changed(insert or delete or edit) do not allow to close or exit.
// if view n order preferences are setted (ie farmer) it affects lookups (ie farmer in application. So we need to apply the setted view n order in lookup.
// (servicesales)add constants 'sale xondrikh' and 'sale lianikh' in type of document. Also add textboxs in services prices(hondriki, lainikh).As a consequence change calculations in prices of sales
// 2016-02-09 (program) be able to change lengths of report columns 
// 2016-02-10 (program)Reports: when 3 or more bands shows again band 2(for example in stock sales in 3bands shows again the documentheader before each documentline(stock))
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
