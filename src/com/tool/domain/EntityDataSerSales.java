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
    public static final String REPORT_CAT_2 = "πελάτες";
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
        String[] fieldsOnTitleSale ={"saleheader.saleCodeNo","actionseries.actionSeriesCode","saleheader.dateOfSale","customer.title"};
        String[] fieldsOnTitleCaptionSale  ={"αριθ. παρ/κού","τυπος παρ/κού","ημερομηνία","πελάτης"};      
        String[] strSaleCategories = {DATAENTRY,METRICS};

        String[] fieldsUniqueSale = null;
        
        
        EntityUpdateAdditional[] updateAdditionalActionType = new EntityUpdateAdditional[3];
        
        EntityPanel entityPanelSaleDataentry;// = new EntityPanel("ODOR","saleheader",saleDBFields,saleEntityGroupOfComps,saleEntityGroupOfPanels,"Νο πώλησης","","saleHeaderId",saleQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSale,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,updateAdditionalActionType,entReportServiceSaleDoc);      // entReportServiceSaleDoc
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProductCustomers = new EntityPanel("statProductCustomers","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT customer.customerId AS \"νο αγρότη\", customer.surname, customer.name, customer.fatherName,customer.customerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, customer","WHERE invoice.customerId = customer.customerId AND invoice.productId=","GROUP BY customer.customerId","ORDER BY customer.surname, customer.name, customer.fatherName,customer.customerAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelSale;// = new EntityPanel[] { entityPanelSaleDataentry};//,entityPanelProductStatistics,entityPanelProductCustomers,entityPanelProductBuyers,entityPanelProductSalesPerDate};
       
        //----------------------------------------------------------------
        EntityDBFields[] customerDBFields = new EntityDBFields[24];
        EntityGroupOfComps[] customerEntityGroupOfComps = new EntityGroupOfComps[5];
        EntityGroupOfPanels[] customerEntityGroupOfPanels = new EntityGroupOfPanels[2];
        
        // same as second query in etityInfo
        //String customerQueryEditable="SELECT customer.customerId AS \"Νο πελάτη\", customer.surname AS \"επίθετο\", customer.name AS\"όνομα\", customer.fathername AS \"πατρόνυμο\", customer.customerAfm AS \"Α.Φ.Μ.\", customer.doyId, customer.idNo AS \"αρ ταυτοτ\", customer.townId, customer.address AS \"διέυθυνση\", customer.phone AS \"τηλέφωνο\" FROM customer, town WHERE customer.townId=town.townId";
        String customerQueryEditable="SELECT * FROM customer";// LEFT JOIN doy ON customer.doyId=doy.doyId";// LEFT JOIN bank ON customer.bankId=bank.bankId";        
        String[] fieldsOnTitleCustomer ={"customerId","title","vatNo"};
        String[] fieldsOnTitleCaptionCustomer  ={"Νο","όνομα","ΑΦΜ"};
        String[] strCustomerCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueCustomer = {"vatNo"};
        //STATS be careful to have in the query all the fields that are also in the title
        EntityPanel entityPanelCustomerDataentry;// = new EntityPanel("ODOR","customer",customerDBFields,customerEntityGroupOfComps,customerEntityGroupOfPanels,"Νο πελάτη","","customerId",customerQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueCustomer,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,entReportServiceSaleDoc);      
        EntityPanel entityPanelCustomerHistory;// = new EntityPanel("statCustomerHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT customer.customerId, customer.dbCompanyId, saleheader.saleHeaderId, actiontype.actionTypeCode, saleheader.saleCodeOfDocument, saleheader.saleCodeNo,saleheader.dbYearId, saleheader.dateOfSale, saleheader.isPrinted, saleheader.countTotal,saleheader.quantityTotal, saleheader.pricePreVat, saleheader.priceVat, saleheader.priceTotal","FROM customer, saleheader, actiontype","WHERE customer.customerId = saleheader.customerId AND customer.dbCompanyId = saleheader.dbCompanyId AND actionseries.actionseriesId = saleheader.actionseriesId AND customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND customer.customerId =","","ORDER BY saleheader.dateOfSale, saleheader.saleCodeOfDocument",false,"",false,"",entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);     
        //EntityPanel entityPanelCustomerProducts = new EntityPanel("statCustomerProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.customerId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelCustomerBuyers = new EntityPanel("statCustomerBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.customerId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelCustomerSalesPerDate = new EntityPanel("statCustomerSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.customerId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelCustomer;// = new EntityPanel[] { entityPanelCustomerDataentry,entityPanelCustomerHistory};//,entityPanelCustomerStatistics,entityPanelCustomerProducts,entityPanelCustomerBuyers,entityPanelCustomerSalesPerDate};

        /*String[] deliveryFields={"customerId","dateOfApplication","permanent","dbyear","deliveryId","dbCompanyId"};
        String[] deliveryFieldsTranslation={"customerId","ημ/νία αίτησης","υπολογισμένο","dbyear","deliveryId","dbCompanyId"};
        int[] deliveryGroupOfComps = null;*/
       //---------------------------------------------------------------- 
      
        

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
        //EntityPanel entityPanelProductCustomers = new EntityPanel("statProductCustomers","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT customer.customerId AS \"νο αγρότη\", customer.surname, customer.name, customer.fatherName,customer.customerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, customer","WHERE invoice.customerId = customer.customerId AND invoice.productId=","GROUP BY customer.customerId","ORDER BY customer.surname, customer.name, customer.fatherName,customer.customerAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelService = new EntityPanel[] { entityPanelServiceDataentry};//,entityPanelProductStatistics,entityPanelProductCustomers,entityPanelProductBuyers,entityPanelProductSalesPerDate};
        
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
      boolean[] boolSettingsReportCustomerfileA = {true,true,true,true,true}; 
      boolean[] boolSettingsReportCustomerfileB = {true,true,true,true,true}; 
       int[] intSettingsReportCustomerfile={0,0,0,0};
 //       EntityReportBandField[] entityReportBandFieldsSaleLineA =new EntityReportBandField[10];          
 //       EntityReportBandField[] entityReportBandFieldsSaleHeaderB =new EntityReportBandField[7];
       EntityReportBand[] reportBandCustomerServiceSaleDoc = new EntityReportBand[2];  
          

   public EntityDataSerSales()
   {
       
          // need them
   	  dTree = new DataTree();
   	  nodeRoot = new DataTreeNode("root");
   	  dTree.setRootElement(nodeRoot);

          // deliveryQueryEditable = "SELECT d.customerId, d.dateOfApplication, d.dbyear, d.deliveryId, d.dbCompanyId FROM application d WHERE dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+" AND dbCompanyId="+VariablesGlobal.globalCompanyId;

       //System.out.println("EntityData"+entityPanelCustomer[0].getType());

/*
    FIELD_NOCOMPLETION = 0;
    FIELD_OBLIGATORY = 1;
    FIELD_SUGGEST = 2;
    
    FIELD_VALIDATION_NO = 0  FIELD_VALIDATION_AFM = 1
*/

 
      
      int[] inputFieldsCustomerInHeader ={7}; // 7 is customerId
      int[] inputFieldsOfNetValue ={15}; // 15 is pricePreVat
      
      EntityCheckFields[] entityCheckFieldsVATCompanyOfHeader =new EntityCheckFields[2];
             entityCheckFieldsVATCompanyOfHeader[0] = new EntityCheckFields(CHECK_ON_ENTRY,"SELECT IF((SELECT customer.vatNo FROM customer WHERE customer.dbCompanyId = "+VariablesGlobal.globalCompanyId+" AND customer.customerId = #) " +
             "= (SELECT dbcompany.companyVatNo FROM dbcompany, dbcompanyset WHERE dbcompany.dbCompanyId = dbcompanyset.dbCompanyId AND dbcompany.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND dbcompanyset.sersaleCheckAFMOfSaleAndComp = 1 )" +
             ",1,0)",inputFieldsCustomerInHeader, 7,"Το ΑΦΜ του πελάτη είναι ίδιο με αυτό της επιχείρησης στην οποία εργάζεστε.");
             entityCheckFieldsVATCompanyOfHeader[1] = new EntityCheckFields(CHECK_ON_INSERT_OR_ON_UPDATE,"SELECT IF( # >= (SELECT dbcompanyset.sersaleMaxOfCashNetValue FROM dbcompanyset " +
              "WHERE dbcompanyset.dbCompanyId = "+VariablesGlobal.globalCompanyId+"  AND dbcompanyset.sersaleMaxOfCashCheck = 1),1,0)",inputFieldsOfNetValue,0, "Το παραστατικό θα πρέπει να εξοφλήθεί με τραπεζικό τρόπο.");
                     
           
      
        int[] saleFileHeaderOrderby ={1}; 
        int[] saleFileLineOrderby ={1}; 

        
        
EntityFilterSettings[] salesDocumentErs = new EntityFilterSettings[7] ; 
     salesDocumentErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       //invoiceErs[1]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","customerId","customer","customer","",1,-1,-1,FIELD_NOCOMPLETION);
       salesDocumentErs[1]=new EntityFilterSettings("επίθετο","","string","equals","title","customer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       salesDocumentErs[2]=new EntityFilterSettings("πελάτης","checkboxTable","string","","customerId","customer","customer","",1,-1,-1,FIELD_NOCOMPLETION);
       salesDocumentErs[3]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","customer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
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
        
        
        
      
       reportBandCustomerServiceSaleDoc[0] = new EntityReportBand("saleheader","πώληση","saleheader",null/*entityReportBandFieldsSaleHeaderB*/,saleFileHeaderOrderby,"saleHeaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReportCustomerfileA,entityPanelSale,null,null);//,"","");
       reportBandCustomerServiceSaleDoc[1] = new EntityReportBand("saleline","υπηρεσίες","saleline",null/*entityReportBandFieldsSaleLineA*/,saleFileLineOrderby,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsReportCustomerfileB,entityPanelSale,null,null);//,"","");                  
          
          
        entReportServiceSaleDoc = new EntityReport("servicesaledoc", REPORT_CAT_1,reportBandCustomerServiceSaleDoc,"SELECT * FROM actionseries LEFT JOIN printform ON actionseries.printFormId = printform.printFormId AND actionseries.dbCompanyId = printform.dbCompanyId  AND printform.isActive = 1 , customer, saleline, stock, saleheader, actiontype LEFT JOIN actiontypecopy ON actiontypecopy.actionTypeId = actiontype.actionTypeId AND  actiontypecopy.dbCompanyId = actiontype.dbCompanyId WHERE actiontype.actionTypeId = actionseries.actionTypeId AND actiontype.dbCompanyId = actionseries.dbCompanyId AND saleheader.customerId = customer.customerId AND saleheader.saleHeaderId = saleline.saleHeaderId AND saleline.stockId = stock.stockId AND actionseries.actionseriesId = saleheader.actionseriesId AND customer.dbCompanyId = saleline.dbCompanyId AND saleheader.dbCompanyId = customer.dbCompanyId AND saleheader.dbCompanyId = actionseries.dbCompanyId AND stock.dbCompanyId = customer.dbCompanyId AND customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleheader.isPrinted = actiontypecopy.copyNo ",""/*ORDER BY saleline.inc"*/,"FORM","εκτύπωση παρ/κών","",salesDocumentErs,saleDocumentGroupOfComps,invoicesaSelected, null,
               // "SELECT saleheader.saleheaderId, saleheader.actionseriesId,actionseries.actionSeriesCode, printform.printFormName, printform.printFormLaser, printform.printFormDotMatrix, actionseries.askForPrint " +
               // " FROM actionseries, printform, saleheader" +
                " ","printform.printFormLaser","Θα πρέπει να επιλέξετε φόρμα στο αντίστοιχο παραστατικό.",intSettingsReportCustomerfile,boolSettingsReportDoc,"actionseries.askForPrint");//,globalYearPlusOne) ;

            
            //panels
        entityPanelSaleDataentry = new EntityPanel("ODOR","saleheader",saleDBFields,saleEntityGroupOfComps,saleEntityGroupOfPanels,"Νο πώλησης","","saleHeaderId",saleQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueSale,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,updateAdditionalActionType,entReportServiceSaleDoc,null,entityCheckFieldsVATCompanyOfHeader,null);          
        entityPanelSale = new EntityPanel[] { entityPanelSaleDataentry};
            
        
        EntityCheckFields[] entityCheckFieldscustomer = null;
        entityPanelCustomerDataentry = new EntityPanel("ODOR","customer",customerDBFields,customerEntityGroupOfComps,customerEntityGroupOfPanels,"Νο πελάτη","","customerId",customerQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueCustomer,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null/*hr*/,null,entityCheckFieldscustomer,null);  
        entityPanelCustomerHistory = new EntityPanel("statCustomerHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT saleheader.saleHeaderId AS'id', actionseries.actionSeriesCode AS 'κωδ', saleheader.saleCodeOfDocument, saleheader.saleCodeNo, saleheader.dateOfSale AS 'ημερομηνία', saleheader.isPrinted, saleheader.countTotal AS 'πλήθος',saleheader.quantityTotal, saleheader.pricePreVat AS 'προ ΦΠΑ', saleheader.priceVat AS'ΦΠΑ', saleheader.priceTotal AS'σύνολο'","FROM customer, saleheader, actionseries","WHERE customer.customerId = saleheader.customerId AND customer.dbCompanyId = saleheader.dbCompanyId AND actionseries.dbCompanyId = saleheader.dbCompanyId AND actionseries.actionseriesId = saleheader.actionseriesId AND customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND customer.customerId =","","ORDER BY saleheader.dateOfSale, saleheader.saleCodeOfDocument",false,"",false,"",entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);     
        //EntityPanel entityPanelCustomerProducts = new EntityPanel("statCustomerProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.customerId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelCustomerBuyers = new EntityPanel("statCustomerBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.customerId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelCustomerSalesPerDate = new EntityPanel("statCustomerSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.customerId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        entityPanelCustomer = new EntityPanel[] { entityPanelCustomerDataentry,entityPanelCustomerHistory};//,entityPanelCustomerStatistics,entityPanelCustomerProducts,entityPanelCustomerBuyers,entityPanelCustomerSalesPerDate};
            
            
    
        
        
        EntityDBFields[] customerBankaccountDBFields = new EntityDBFields[10];
        
        customerBankaccountDBFields[0] = new EntityDBFields("customerbankaccount","customerBankAccountId","customerBankAccountId",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
        customerBankaccountDBFields[1] = new EntityDBFields("customerbankaccount","customerId","Νο πελάτη",0,"java.lang.String",6, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
        customerBankaccountDBFields[2] = new EntityDBFields("customerbankaccount","dbCompanyId","dbCompanyId",0,"java.lang.String",6,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
        customerBankaccountDBFields[3] = new EntityDBFields("customerbankaccount","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
        customerBankaccountDBFields[4] = new EntityDBFields("customerbankaccount","bankId","τράπεζα",2,"java.lang.Integer",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
	customerBankaccountDBFields[5] = new EntityDBFields("customerbankaccount","account","λογαριασμός",2,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       	customerBankaccountDBFields[6] = new EntityDBFields("customerbankaccount","iban","ΙΒΑΝ",2,"java.lang.String",29,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerBankaccountDBFields[7] = new EntityDBFields("customerbankaccount","firstName","1ο ονοματεπώνυμο",0,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerBankaccountDBFields[8] = new EntityDBFields("customerbankaccount","branch","κατάστημα",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerBankaccountDBFields[9] = new EntityDBFields("customerbankaccount","bic","BIC",0,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        

          
        customerDBFields[0] = new EntityDBFields("customer","customerId","Νο πελάτη",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //customerDBFields[1] = new EntityDBFields("customer","surname","επίθετο",0,"java.lang.String",20, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,true,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,  false,null);
        customerDBFields[1] = new EntityDBFields("customer","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
        customerDBFields[2] = new EntityDBFields("customer","title","επωνυμία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[3] = new EntityDBFields("customer","customerCode","κωδικός",0,"java.lang.String",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[4] = new EntityDBFields("customer","vatNo","Α.Φ.Μ.",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_AFM,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[5] = new EntityDBFields("customer","activityDescr","δραστηριότητα",0,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[6] = new EntityDBFields("customer","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");               
        customerDBFields[7] = new EntityDBFields("customer","activityCatId","κατηγορία δραστηριότητας",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"activityCat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[8] = new EntityDBFields("customer","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[9] = new EntityDBFields("customer","geoCatId","γεωγραφική κατηγορία",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"geocat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        
        customerDBFields[10] = new EntityDBFields("customer","addressStreet","οδός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[11] = new EntityDBFields("customer","addressCity","πόλη/χωριό",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[12] = new EntityDBFields("customer","addressPC","ΤΚ",1,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[13] = new EntityDBFields("customer","addressState","νομός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[14] = new EntityDBFields("customer","telephone1","τηλέφωνο 1",1,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[15] = new EntityDBFields("customer","telephone2","τηλέφωνο 2",1,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        customerDBFields[16] = new EntityDBFields("customer","fax","fax",1,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[17] = new EntityDBFields("customer","email","email",1,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        customerDBFields[18] = new EntityDBFields("customer","typeofVatExclusionId","καθεστώς ΦΠΑ",2,"java.lang.Integer",7,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCVatExclusion",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");
        customerDBFields[19] = new EntityDBFields("customer","discountPercentage","έκπτωση %",2,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");     
        customerDBFields[20] = new EntityDBFields("customer","paymenttypeId","τρόπος πληρωμής",2,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"paymenttype",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        customerDBFields[21] = new EntityDBFields("customer","currencyId","νόμισμα",2,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"currency",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
        
        customerDBFields[22] = new EntityDBFields("customer","notes","σημειώσεις",3,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");


        //customerDBFields[22] = new EntityDBFields("customer","contacts","επαφές",5,"table",FIELD_VISIBLE_AND_EDITABLE,"customercontact",160,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,customerContactDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM customercontact WHERE customercontact.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY customercontact.inc",null);        
        
        customerDBFields[23] = new EntityDBFields("customer","bank accounts","λογαριασμοί τραπεζών",4,"table",FIELD_VISIBLE_AND_EDITABLE,"customerbankaccount",120,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,customerBankaccountDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM customerbankaccount WHERE customerbankaccount.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY customerbankaccount.inc",null,null);        
        
        
        
        
        customerEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        customerEntityGroupOfComps[1] = new EntityGroupOfComps("επικοινωνία",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       // customerEntityGroupOfComps[2] = new EntityGroupOfComps("επικοινωνία",4,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        customerEntityGroupOfComps[2] = new EntityGroupOfComps("χρηματικά",6,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        customerEntityGroupOfComps[3] = new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //customerEntityGroupOfComps[5] = new EntityGroupOfComps("επαφές",1,1); // if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
        customerEntityGroupOfComps[4] = new EntityGroupOfComps("λογαριασμοί τραπεζών",1,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
        
        
        
        
        
        customerEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        //customerEntityGroupOfPanels[1] = new EntityGroupOfPanels("επικοινωνία",1);
        customerEntityGroupOfPanels[1] = new EntityGroupOfPanels("χρηματικά",1);
        //customerEntityGroupOfPanels[3] = new EntityGroupOfPanels("σημειώσεις",1);

        

        
        
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
        updateAdditionalActionType[0] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,null,"UPDATE actionseries SET seriesNextNumber = (seriesNextNumber + 1 ) WHERE actionseriesId LIKE # AND dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,updateQueryFieldsSaleHeader,null,null);                
        String[] updateQueryFieldsEsoexoHeader ={"actionSeriesId","saleHeaderId","dateOfSale", "pricePreVat", "priceVat", "priceTotal"};
         String[] updateQueryFieldsEsoexoLine ={"saleHeaderId", "saleLineId","saleHeaderId", "saleLineId","saleHeaderId", "saleLineId","priceBeforeVat","vatValue","valueWithVat"};
        //updateAdditionalParameters[0] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,"INSERT INTO parameter (parameterId,dbCompanyId,lengthOfCodeOfDocuments,charOfDecimal,lengthOfDecimalPrice) VALUES(1,#, 4,',',2)" ,updateQueryFieldsParameters);
        //updateAdditionalDbCompany[0] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,"INSERT INTO dbyear (dbYearId,dbyear,dbCompanyId) VALUES(1,"+VariablesGlobal.globalYear+",#)" ,updateQueryFieldsCompany);
        String queryIsSxRecordActive = "SELECT actiontype.isSxRecordActive FROM actionseries, actiontype  WHERE actionseries.actionTypeId = actiontype.actionTypeId AND actiontype.dbCompanyId = actionseries.dbCompanyId AND actiontype.dbCompanyId LIKE " +VariablesGlobal.globalCompanyId+" AND actionseries.actionSeriesId LIKE # ";
        //SELECT actiontype.sxActionTypeId FROM actiontype WHERE actiontype.dbCompanyId LIKE " +VariablesGlobal.globalCompanyId+" AND actiontype.actionTypeId LIKE # ";
        String querySxActionTypeId = "SELECT actiontype.sxActionTypeId FROM actionseries, actiontype WHERE actionseries.actionTypeId = actiontype.actionTypeId AND actiontype.dbCompanyId = actionseries.dbCompanyId AND actiontype.dbCompanyId LIKE " +VariablesGlobal.globalCompanyId+" AND actionseries.actionSeriesId LIKE # ";
        String querySxAccountId = "";
        
        EntityDBFields[] bridgeEsoexoHeaderDBFields = new EntityDBFields[3];
        bridgeEsoexoHeaderDBFields[0] = new EntityDBFields("sxesoexoheader","isTemplate","isTemplate",0,"java.lang.String",3,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"0","");
        bridgeEsoexoHeaderDBFields[1] = new EntityDBFields("sxesoexoheader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        bridgeEsoexoHeaderDBFields[2] = new EntityDBFields("sxesoexoheader","esoexoheaderId","Νο εσόδων εξόδων",1,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //bridgeEsoexoHeaderDBFields[3] = new EntityDBFields("sxesoexoheader","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");
        
        String queryAccount1 = "SELECT actiontype.sxAccount1 FROM saleline, saleheader, actionseries, actiontype WHERE saleline.saleHeaderId = saleheader.saleHeaderId AND actionseries.actionTypeId = actiontype.actionTypeId AND saleheader.actionseriesId = actionseries.actionseriesId AND actionseries.dbCompanyId = actiontype.dbCompanyId AND saleheader.dbCompanyId = actionseries.dbCompanyId "+
                "AND actionseries.dbCompanyId = saleline.dbCompanyId AND saleline.dbCompanyId = saleheader.dbCompanyId AND saleline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleline.saleHeaderId LIKE # AND saleline.saleLineId LIKE # ";
        String queryAccount2 = "SELECT stock.sxAccount2 FROM saleline, stock WHERE saleline.stockId = stock.stockId AND saleline.dbCompanyId = stock.dbCompanyId AND saleline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  AND saleline.saleHeaderId LIKE # AND saleline.saleLineId LIKE # ";
        String queryAccount3 = "SELECT stock.sxAccount3 FROM saleline, stock WHERE saleline.stockId = stock.stockId AND saleline.dbCompanyId = stock.dbCompanyId AND saleline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  AND saleline.saleHeaderId LIKE # AND saleline.saleLineId LIKE # ";
        
        
        String queryAccountId = "SELECT sxaccount.accountId FROM sxaccount WHERE sxaccount.accountCode LIKE( SELECT CONCAT( ( "+queryAccount1+" ),'-',( "+queryAccount2+" ),'-',( "+queryAccount3+" )))";
        //&@ is fot pk of header, &$ is for each row
        updateAdditionalActionType[1] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,null,"INSERT INTO sxesoexoheader ( `esoexoHeaderId`, `sxActionTypeId`,`dbCompanyId`, `saleHeaderId`, dateOfesoexo, dbYearId, isTemplate, pricePreVat, priceVat, priceTotal) VALUES (&@,("+querySxActionTypeId+"), '"+VariablesGlobal.globalCompanyId+"',#,#,"+VariablesGlobal.globalYearId+", 0, '#', '#', '#') ",updateQueryFieldsEsoexoHeader,"sxesoexoheader",bridgeEsoexoHeaderDBFields);
        updateAdditionalActionType[2] = new EntityUpdateAdditional(UPDATE_ON_INSERT_ONLY,"salelines","INSERT INTO sxesoexoline ( `esoexoHeaderId`, `dbCompanyId`,  dbYearId, isTemplate, esoexoLineId, inc, accountId, priceBeforeVat, vatValue, valueWithVat ) VALUES (&@, '"+VariablesGlobal.globalCompanyId+"',"+VariablesGlobal.globalYearId+",0, &a, &a, ("+queryAccountId+")  , '#', '#', '#') ",updateQueryFieldsEsoexoLine,null,null);

        saleDBFields[0] = new EntityDBFields("saleheader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");//FIELD_NOT_VISIBLE  //FIELD_VISIBLE_AND_EDITABLE
        saleDBFields[1] = new EntityDBFields("saleheader","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalYearId,"");  //FIELD_NOT_VISIBLE   // FIELD_VISIBLE_AND_EDITABLE          
        
        saleDBFields[2] = new EntityDBFields("saleheader","saleHeaderId","Νο πώλησης",1,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
        int[] inputActionTypeCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputActionType ={3};
       
        EntityDBFieldsCalculation[] fieldsCalculationActionTypeUpdate = new EntityDBFieldsCalculation[2];
        fieldsCalculationActionTypeUpdate[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,4,inputActionTypeCategory,inputActionType,"UPDATE saleheader SET saleCodeNo = (SELECT actionseries.seriesNextNumber FROM actionseries WHERE  actionseries.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND actionseries.actionseriesId LIKE #) WHERE saleheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleheader.saleHeaderId LIKE ");
        fieldsCalculationActionTypeUpdate[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,5,inputActionTypeCategory,inputActionType,"UPDATE saleheader SET saleCodeOfDocument = (SELECT  LPAD((SELECT actionseries.seriesNextNumber FROM actionseries WHERE  actionseries.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  AND actionseries.actionseriesId LIKE #),(SELECT sersaleCodeOfDocumentsLength FROM dbcompanyset WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ), 0)) WHERE saleheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleheader.saleHeaderId LIKE ");
        
        EntityDBFieldsCalculation[] fieldsCalculationActionTypeSelect = new EntityDBFieldsCalculation[1];
       fieldsCalculationActionTypeSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,7,inputActionTypeCategory,inputActionType,"SELECT dbcompanyset.sersaleRetailCustomer FROM dbcompanyset,actiontype,actionseries WHERE actionseries.actionTypeId = actiontype.actiontypeId AND actionseries.dbCompanyId = actiontype.dbCompanyId AND dbcompanyset.dbCompanyId = actionseries.dbCompanyId AND dbcompanyset.dbcompanyId = actiontype.dbcompanyId AND dbcompanyset.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND actiontype.actionTypeCatId LIKE 2 AND actionseries.actionseriesId LIKE #");
        saleDBFields[3] = new EntityDBFields("saleheader","actionseriesId","σειρά - τύπος παρ/κού",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"actionseries",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,fieldsCalculationActionTypeUpdate,fieldsCalculationActionTypeSelect,"");// variable before last: 'false' means update
        saleDBFields[4] = new EntityDBFields("saleheader","saleCodeNo","saleCodeNo",1,"java.lang.Integer",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        saleDBFields[5] = new EntityDBFields("saleheader","saleCodeOfDocument","κωδικός παρ/κού",1,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");//,entityFieldUpdateAdditionalCodeOfDocument);
        saleDBFields[6] = new EntityDBFields("saleheader","dateOfSale","ημερομηνία παρ/κού",1, "java.sql.Date" ,8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalDate,"");
       // saleDBFields[5] = new EntityDBFields("saleheader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
      //  saleDBFields[6] = new EntityDBFields("saleheader","dbYearId","dbYearId",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalYearId,"");        
        int[] inputCustomerCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputCustomer ={7};
        //10,inputService,"SELECT vatcat.vatPercentage FROM stock, vatcat  WHERE stock.vatCatId=vatcat.vatCatId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId=");
        EntityDBFieldsCalculation[] fieldsCalculationCustomerSelect = new EntityDBFieldsCalculation[3];
        fieldsCalculationCustomerSelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,9,inputCustomerCategory,inputCustomer,"SELECT customer.paymentTypeId FROM customer WHERE  customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND customer.customerId LIKE #");
        fieldsCalculationCustomerSelect[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,10,inputCustomerCategory,inputCustomer,"SELECT customer.typeofVatExclusionId FROM customer  WHERE customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND customer.customerId LIKE #");
        fieldsCalculationCustomerSelect[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,8,inputCustomerCategory,inputCustomer,"SELECT customer.title FROM customer, dbcompany, dbcompanyset  WHERE dbcompany.dbCompanyId = dbcompanyset.dbCompanyId AND customer.dbCompanyId = dbcompanyset.dbCompanyId AND dbcompany.dbCompanyId = customer.dbCompanyId AND customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND dbcompanyset.sersaleCopyCustomerNameToSaleComment LIKE 1 AND customer.customerId LIKE #");
        //fieldsCalculationCustomer[2] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputCustomerCategory,inputCustomer,"SELECT customer.currencyId FROM customer  WHERE customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND customer.customerId LIKE #");
        
        
        saleDBFields[7] = new EntityDBFields("saleheader","customerId","πελάτης",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"customer", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationCustomerSelect,"");
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
        
        //fieldsCalculationCurrency[1] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,8,inputCustomerCategory,inputCustomer,"SELECT customer.currencyId FROM customer  WHERE customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND customer.customerId LIKE #");
                 
        saleDBFields[11] = new EntityDBFields("saleheader","isPrinted","εκτυπωμένο",3,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,"1","");         
        String[] childTableFieldsForSumsSalelines=null;//{"προ ΦΠΑ","αξία ΦΠΑ","αξία"};
                                                      //  salelines used in additional insert
        saleDBFields[12] = new EntityDBFields("saleheader","salelines","υπηρεσίες",4,"table",FIELD_VISIBLE_AND_EDITABLE,"saleline",120,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,saleLineDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT * FROM saleline WHERE saleline.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY saleline.inc",null,childTableFieldsForSumsSalelines);        
         
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
        saleEntityGroupOfComps[2] = new EntityGroupOfComps("πελάτης",2,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        saleEntityGroupOfComps[3] = new EntityGroupOfComps("λοιπά",8,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //saleEntityGroupOfComps[3] = new EntityGroupOfComps("αιτιολογία",6,0);
        saleEntityGroupOfComps[4] = new EntityGroupOfComps("παρεχόμενες υπηρεσίες",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        saleEntityGroupOfComps[5] = new EntityGroupOfComps("σύνολα",8,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
         
        saleEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);


        
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
   
        

           
        
   
       dbyearDBFields[0] = new EntityDBFields("dbyear","dbYearId","Νο χρήσης",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbyearEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
     
        
        
        
   	activityCatDBFields[0] = new EntityDBFields("activitycat","activityCatId","Νο δραστηριότητας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	activityCatDBFields[1] = new EntityDBFields("activitycat","activityDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
       
       	serviceCatLineDBFields[0] = new EntityDBFields("stockcat","stockCatId","Νο κατηγορίας",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	serviceCatLineDBFields[1] = new EntityDBFields("stockcat","catDescr","ονομασία",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        serviceCatLineDBFields[2] = new EntityDBFields("stockcat","dbCompanyId","dbCompanyId",0,"java.lang.String",3, FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId,"");
        serviceCatDBFields[0] = new EntityDBFields("servicecatheader","stockcat","κατηγορίες υπηρεσίας",0,"table",FIELD_VISIBLE_AND_EDITABLE,"stockcat",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,serviceCatLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT stockCatId AS\"Νο κατηγορίας\", catDescr AS\"κατηγορία\", dbCompanyId FROM stockcat WHERE stockcat.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY stockcat.catDescr",null,null);     
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
        companySetSerSalesDBFields[4] = new EntityDBFields("dbCompanySet","sersaleRetailCustomer","προτεινόμενος πελάτης λιανικής",2,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"customer", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,null,"");
        companySetSerSalesDBFields[5] = new EntityDBFields("dbCompanySet","sersaleCheckAFMOfSaleAndComp","ενημέρωση καταχώρησης παραστατικού με πελάτη την εταιρία εργασίας",3,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        companySetSerSalesDBFields[6] = new EntityDBFields("dbCompanySet","sersaleMaxOfCashCheck","ενημέρωση για ποσό εξόφλησης μέσω τράπεζικού τρόπου",4,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        companySetSerSalesDBFields[7] = new EntityDBFields("dbCompanySet","sersaleMaxOfCashNetValue","ελαχιστο ποσό για εξόφληση μέσω τράπεζικού τρόπου",4,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"500","");
        companySetSerSalesDBFields[8] = new EntityDBFields("dbCompanySet","sersaleCopyCustomerNameToSaleComment","πρόταση επωνυμίας πελάτη στην αιτιολογία πώλησης",5,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                
        
        
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
        saleErs[3]=new EntityFilterSettings("πελάτης","","string","equals","title","customer","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","customer","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[5]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfSale","","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[6]=new EntityFilterSettings("ποσότητα","","double","fromto","quantityTotal","","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[7]=new EntityFilterSettings("τελική τιμή","","double","fromto","priceTotal","","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);

       EntityGroupOfComps[] saleEntityGroupOfCompsFilters = null;
        
        int[] saleFieldsOrderby ={5,2,3,6,1};// also complete sql order by clause
        
        String[] fieldsForSumsSale = {"πλήθος","ποσότητα","προ φπα", "ΦΠΑ","σύνολο μετά ΦΠΑ","παρακράτηση","τελικό σύνολο"};
        //   IF(actiontype.isCredit=1, -saleheader.pricePreVat, saleheader.pricePreVat)
       
        EntityInfo pg = new EntityInfo("saleheader", "SELECT saleheader.saleHeaderId AS \"Νο πώλησης\", actionseries.actionSeriesCode AS \"τύπος\",saleheader.saleCodeNo AS \"αριθ.\", saleheader.saleCodeOfDocument AS \"αριθ. παρ.\", saleheader.dateOfSale AS \"ημερομηνία\",customer.vatNo AS \"Α.Φ.Μ.\",  customer.title AS \"πελάτης\", saleheader.comments AS \"σχόλια\", (SELECT saleheader.isPrinted - 1) AS \"εκτ\",saleheader.countTotal AS \"πλήθος\",  saleheader.quantityTotal AS \"ποσότητα\", saleheader.pricePreVat AS \"προ ΦΠΑ\",  saleheader.priceVat AS \"ΦΠΑ\",  saleheader.priceTotal AS \"σύνολο μετά ΦΠΑ\", saleheader.withholdingtaxAmount AS \"παρακράτηση\", saleheader.priceTotalAfterWithholdingTax AS \"τελικό σύνολο\" FROM saleheader , customer, actionseries WHERE saleheader.customerId = customer.customerId AND actionseries.actionseriesId = saleheader.actionseriesId AND saleheader.dbCompanyId = actionseries.dbCompanyId AND customer.dbCompanyId = actionseries.dbCompanyId AND actionseries.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY saleheader.dateOfSale, actionseries.actionSeriesCode, saleheader.saleCodeNo, customer.vatNo, saleheader.saleHeaderId" ,"SELECT saleheader.saleHeaderId AS \"Νο πώλησης\", saleheader.actionseriesId , saleheader.saleCodeNo, saleheader.saleCodeOfDocument, saleheader.dateOfSale, saleheader.customerId, saleheader.paymentTypeId, saleheader.isPrinted","FROM saleheader","WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,null,fieldsForSumsSale,"παροχή υπηρεσίας","DORM","","Νο πώλησης","saleHeaderId",/*"","",*/saleErs,saleEntityGroupOfCompsFilters,"πώλησης","πωλήσεων",strSaleCategories,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale,saleFieldsOrderby,5,FIELD_VALIDATION_AFM,entReportServiceSaleDoc,globalYearPlusOne);
        EntityMenu empg = new EntityMenu();
        empg.setEntityInfo(pg,ICO_PAPER);
        empg.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeempg = new DataTreeNode(empg);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeempg);        
        
   //----------------------------------------------------------------------      

       EntityFilterSettings[] customerErs = new EntityFilterSettings[4];       
       //customerErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","customer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       customerErs[0]=new EntityFilterSettings("όνομα","","string","equals","title","customer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       customerErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","customer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //customerErs[3]=new EntityFilterSettings("ταυτότητα","","string","equals","idNo","customer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //customerErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","customer","",-1,-1,-1,FIELD_NOCOMPLETION);
       customerErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","customer","",-1,-1,-1,FIELD_NOCOMPLETION);
       customerErs[3]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //customerErs[4]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfSale","","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
                             //onelookup,checkboxTable
       EntityGroupOfComps[] customerEntityGroupOfComps = null;
       
       int[] customerFieldsOrderBy ={2,3,4,5,1};
        // if fields change, change them and at lookup entities
       String[] fieldsForSumsCustomer = {"ποσότητα","προ φπα", "φπα","σύνολο"};
       //EntityInfo ia = new EntityInfo("customer", "SELECT customer.customerId AS \"Νο πελάτη\", customer.surname AS \"επίθετο\", customer.title AS \"όνομα\", customer.fathername AS \"πατρόνυμο\", customer.customerAfm AS \"Α.Φ.Μ.\", customer.doyId AS \"Νο Δ.Ο.Υ.\", doy.doyName AS \"ονομασία Δ.Ο.Υ.\", customer.idNo AS \"αρ ταυτοτ\", customer.phone AS \"τηλέφωνο(1)\", customer.phone2 AS \"τηλέφωνο(2)\", customer.phone3 AS \"τηλέφωνο(3)\", town.townName AS \"πόλη/χωριό\",town.postCode AS \"TK\", customer.address AS \"διεύθυνση\", bank.bankBranch AS \"τράπεζα\", customer.bankAccount AS \"λογαριασμός\" FROM customer LEFT JOIN doy ON customer.doyId=doy.doyId LEFT JOIN town ON customer.townId=town.townId LEFT JOIN bank ON customer.bankId=bank.bankId ORDER BY customer.surname, customer.title, customer.fathername, customer.customerAfm" ,"SELECT customer.customerId AS \"Νο πελάτη\", customer.surname AS \"επίθετο\", customer.name AS\"όνομα\", customer.fathername AS \"πατρόνυμο\", customer.customerAfm AS \"Α.Φ.Μ.\", customer.doyId, customer.idNo AS \"αρ ταυτοτ\", customer.townId, customer.address AS \"διέυθυνση\", customer.phone AS\"τηλέφωνο\"","FROM customer LEFT JOIN doy ON customer.doyId=doy.doyId LEFT JOIN town ON customer.townId=town.townId","",null, fieldsForSumsCustomer,null,null,customerDBFields,null,null,null,null,null,false,null,null ,"αγρότες","DORM","","Νο πελάτη","customerId",null,null,customerErs,customerEntityGroupOfComps ,"αγρότη","αγροτών",null,null,strCustomerCategories,entityPanelCustomer,fieldsOnTitleCustomer,fieldsOnTitleCaptionCustomer,customerFieldsOrderBy,4,FIELD_VALIDATION_AFM,globalYearPlusOne); 
       EntityInfo ia = new EntityInfo("customer", "SELECT customer.customerId AS \"Νο πελάτη\", customer.title AS \"επωνυμία\",customer.customerCode  AS \"κωδ. πελάτη\", customer.vatNo AS \"Α.Φ.Μ.\", activitycat.activityDescr AS \"κατηγορία δραστηριότητας\", customer.telephone1 AS\"τηλέφωνο 1\", customer.telephone2 AS\"τηλέφωνο 2\", customer.email AS\"email\",doy.doyName AS \"ονομασία Δ.Ο.Υ.\", customer.active, sum(quantityTotal) AS \"ποσότητα\", sum(pricePreVat) AS \"προ ΦΠΑ\", sum(priceVat) AS \"ΦΠΑ\", sum(priceTotal) AS \"σύνολο\"  FROM customer LEFT JOIN doy ON customer.doyId=doy.doyId LEFT JOIN saleheader ON saleheader.customerId = customer.customerId AND saleheader.dbCompanyId = customer.dbCompanyId LEFT JOIN activitycat ON activitycat.activityCatId = customer.activityCatId WHERE customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" GROUP BY customer.customerId ORDER BY customer.title" ,"SELECT customer.customerId AS \"Νο πελάτη\", customer.title AS \"επωνυμία\", customer.vatNo AS \"Α.Φ.Μ.\", customer.doyId","FROM customer LEFT JOIN doy ON customer.doyId=doy.doyId "/*LEFT JOIN town ON customer.townId=town.townId"*/,"WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,null, fieldsForSumsCustomer ,"πελάτες","DORM","","Νο πελάτη","customerId",/*"","",*/customerErs,customerEntityGroupOfComps ,"πελάτη","πελατών",strCustomerCategories,entityPanelCustomer,fieldsOnTitleCustomer,fieldsOnTitleCaptionCustomer,customerFieldsOrderBy,3,FIELD_VALIDATION_AFM,null,globalYearPlusOne);
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
        
        EntityInfo pf = new EntityInfo("stock", "SELECT stock.stockId AS \"Νο υπηρεσίας\", stock.descr AS \"περιγραφή\", stock.code AS \"κωδ. υπηρεσίας\", vatcat.vatDescr, stockcat.catDescr  AS \"κατηγορία\", stock.active, priceWhole AS \"τιμή\",  sum(saleline.quantity) AS \"ποσότητα\", sum(saleline.priceBeforeVat) AS \"προ ΦΠΑ\", sum(saleline.vatValue) AS \"ΦΠΑ\", sum(saleline.valueWithVat) AS \"σύνολο\" FROM stock LEFT JOIN vatcat ON vatcat.vatCatId = stock.vatCatId LEFT JOIN saleline ON saleline.stockId = stock.stockId AND saleline.dbCompanyId = stock.dbCompanyId LEFT JOIN stockcat ON stockcat.stockCatId = stock.stockCatId AND saleline.dbCompanyId = stock.dbCompanyId WHERE stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" GROUP BY stock.stockId ORDER BY stock.descr"  ,"SELECT stock.stockId AS \"Νο υπηρεσίας\", stock.descr AS \"περιγραφή\"","FROM stock","WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId ,null,fieldsForSumsService,"υπηρεσίες","DORM","","Νο υπηρεσίας","stockId",/*"","",*/serviceErs,serviceEntityGroupOfComps,"υπηρεσίας","υπηρεσιών",strServiceCategories,entityPanelService,fieldsOnTitleService,fieldsOnTitleCaptionService,serviceFieldsOrderby,-1,-1,null,globalYearPlusOne);
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
  
  
   public ArrayList addEntitiesLookup(ArrayList entities)
   { 
       // LOOKUPTYPE_ONLYONE_THISFIELD
     EntityLookUp entityLookUp;

     
     // make entities for all tables called with foreign keys
     
       EntityFilterSettings[] customerErs = new EntityFilterSettings[3];       
       customerErs[0]=new EntityFilterSettings("επίθετο","","string","equals","title","customer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       customerErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","customer",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       customerErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //customerErs[2]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","customer","",-1,-1,-1,FIELD_NOCOMPLETION);
            // for 'intNoOfColsWhenInTable' look also at  deliveryFieldsMany
      
     
     String[] lookUpFieldCustomer={"title","vatNo"};
            
     entities.add(entityLookUp = new EntityLookUp("customer","customer","SELECT customer.customerId AS\"Νο πελάτη\", customerCode AS\"κωδικός\", title AS\"επωνυμία\",  vatNo AS\"Α.Φ.Μ.\", sum(quantityTotal) AS \"ποσότητα\", sum(pricePreVat) AS \"προ ΦΠΑ\", sum(priceVat) AS \"ΦΠΑ\", sum(priceTotal) AS \"συνολικό ποσό\"  FROM customer LEFT JOIN saleheader ON saleheader.customerId = customer.customerId AND saleheader.dbCompanyId = customer.dbCompanyId","WHERE customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"AND customer.active LIKE 1", "GROUP BY customer.customerId ORDER BY customer.title","","customerId","Νο πελάτη","customerId","πελάτης",3,lookUpFieldCustomer,"επωνυμία ή ΑΦΜ",29,"java.lang.String",4,"vatNo", "Α.Φ.Μ.",0,null,null,customerQueryEditable, "πελάτη","πελατών",strCustomerCategories,entityPanelCustomer,fieldsOnTitleCustomer,fieldsOnTitleCaptionCustomer,customerErs,2,2,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));

   
  

      String[] lookUpFieldServiceCat={"catDescr"};
     
        EntityFilterSettings[] serviceCatErs = new EntityFilterSettings[1];       
        serviceCatErs[0]=new EntityFilterSettings("ονομασία","","string","equals","catDescr","stockcat",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     entities.add(entityLookUp = new EntityLookUp("stockcat","stockcat","SELECT stockCatId AS\"Νο κατηγορίας\", catDescr AS\"κατηγορία\", dbCompanyId FROM stockcat","WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"","ORDER BY catDescr","" ,"stockCatId","Νο κατηγορίας","stockCatId","κατηγορία",2,lookUpFieldServiceCat,"κατηγορία",15,"java.lang.String",0,null,null,0,null,null,serviceCatQueryEditable,"κατηγορία υπηρεσίας","κατηγοριών υπηρεσίας",null,entityPanelServiceCat,fieldsOnTitleServiceCat, fieldsOnTitleCaptionServiceCat,serviceCatErs,2,1,null,true,-1,-1,null));
     
  

     //------------------------------------------------------------------ 
        EntityFilterSettings[] serviceErs = new EntityFilterSettings[3];       
        serviceErs[0]=new EntityFilterSettings("ονομασία","","string","equals","descr","stock",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        serviceErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","stockCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
        serviceErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleline","",-1,-1,-1,FIELD_NOCOMPLETION);
    
     String[] lookUpFieldService={"descr"};                   
    
     entities.add(entityLookUp = new EntityLookUp("stock","stock","SELECT stock.stockId AS\"Νο υπηρεσίας\", stock.descr AS \"ονομασία\", stock.stockCatId AS \"Νο κατηγορίας\",  vatcat.vatDescr, priceWhole AS \"τιμή\"  ,  sum(saleline.quantity) AS \"ποσότητα\", sum(saleline.priceBeforeVat) AS \"προ ΦΠΑ\", sum(saleline.vatValue) AS \"ΦΠΑ\", sum(saleline.valueWithVat) AS \"σύνολο\" FROM stock LEFT JOIN vatcat ON vatcat.vatCatId = stock.vatCatId LEFT JOIN saleline ON saleline.stockId = stock.stockId AND saleline.dbCompanyId = stock.dbCompanyId ","WHERE stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"AND stock.active LIKE 1","GROUP BY stock.stockId ORDER BY stock.descr ","","stockId","Νο υπηρεσίας","stockId","υπηρεσία",2,lookUpFieldService,"ονομασία",22,"java.lang.String",0,null,null,0,null,null,serviceQueryEditable,"υπηρεσίας","υπηρεσιών",null,entityPanelService,fieldsOnTitleService,fieldsOnTitleCaptionService,serviceErs,2,1,null,true,-1,-1,null));    	 	

     
//---------------------------------------------------------------------------    
    
        EntityFilterSettings[] saleErs = new EntityFilterSettings[2];       
        saleErs[0]=new EntityFilterSettings("ονομασία","","string","equals","saleCodeOfDocument","saleheader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        saleErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionseriesId","actionseries","saleheader","",-1,-1,-1,FIELD_NOCOMPLETION);
           
     String[] lookUpFieldSale={"saleCodeOfDocument"};                   
    
     entities.add(entityLookUp = new EntityLookUp("saleheader","saleheader","SELECT saleheader.saleCodeOfDocument, saleheader.actionseriesId, saleheader.saleCodeNo, saleheader.customerId  FROM saleheader ",/*, currency WHERE product.currencyId=currency.currencyId"*/"WHERE saleheader.dbCompanyId LIKE "+ VariablesGlobal.globalCompanyId,"","ORDER BY saleheader.dateOfSale, saleheader.saleHeaderId ","","saleHeaderId","Νο πώλησης","saleHeaderId","πώληση",3,lookUpFieldSale,"κωδ. παραστατικού",15,"java.lang.String",0,null,null,0,null,null,saleQueryEditable,"πώλησης","πωλήσεων",null,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale,saleErs,2,1,null,true,-1,-1,null));    	 	

  
     //------------------------------------------------------------------ 

         EntityFilterSettings[] printFormErs = new EntityFilterSettings[1];       
        printFormErs[0]=new EntityFilterSettings("ονομασία","","string","equals","printFormName","printform",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        
        String[] lookUpFieldPrintForm={"printFormName"};                   
    
     entities.add(entityLookUp = new EntityLookUp("printform","printform","SELECT printform.printformId AS\"Νο φόρμας\", printform.printformName AS \"φόρμα εκτύπωσης\"  FROM printform","WHERE printform.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,"AND printform.isActive LIKE 1","ORDER BY printform.printformName ","","printformId","Νο φόρμας","printform","φόρμα",2,lookUpFieldPrintForm,"ονομασία",33,"java.lang.String",0,null,null,0,null,null,printFormQueryEditable,"φόρμας εκτύπωσης","φορμών εκτύπωσης",null,entityPanelPrintForm,fieldsOnTitlePrintForm,fieldsOnTitleCaptionPrintForm,printFormErs,2,1,null,true,-1,-1,null));    	 	
               
     
   //----------------------------------------------------------------  
     
    
     //int[] lookUpFieldIndexPaymentType ={2,3,0};     
     String[] lookUpFieldDbYear={"dbYearDescr"};
     entities.add(entityLookUp = new EntityLookUp("dbyear","dbyear","SELECT dbyearId AS \"Νο χρήσης\", dbYearDescr AS \"χρήση\" FROM dbyear","WHERE dbCompanyId LIKE '"+VariablesGlobal.globalCompanyId+"'","", "ORDER BY dbYearDescr","" ,"dbyearId","Νο χρήσης","dbyearId","χρήση",2,lookUpFieldDbYear,"χρήση",7,"java.lang.String",0,null,null,0,null,null,"","της χρήσης","των χρήσεων",null,entityPanelDbyear,fieldsOnTitleDbyear,fieldsOnTitleCaptionDbyear,null,-1,1,null,false,-1,-1,null));  

  
          
     return entities;
   }
  
     public void addEntitiesParameters()  // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
    {  


        //------------------------------------------------------------
    
  
         int[] companySetSerSalesFieldsOrderby ={2};
       String[] companySetSerSalesFieldsForSums=null;
       EntityParameter pqs = new EntityParameter("dbcompanyset", "SELECT dbCompanyId AS \"Νο\" FROM dbcompanyset ORDER BY dbCompanyId" ,"SELECT dbCompanyId AS \"Νο\" ","FROM dbcompanyset","",companySetSerSalesFieldsForSums,companySetSerSalesDBFields,"ρυθμ. παροχής υπηρεσιών","DORO","Νο","dbcompanyid", null,null,"παρ. υπηρεσίας", "παρ. υπηρεσιών",companySetSerSalesEntityPanel,null,companySetSerSalesFieldsOnTitle,companySetSerSalesFieldsOnTitleCaption,companySetSerSalesFieldsOrderby,-1,-1,globalYearPlusOne);
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
       salelineEntityGroupOfComps[1] = new EntityGroupOfComps("πελάτης",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       salelineEntityGroupOfComps[2] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       salelineEntityGroupOfComps[3] = new EntityGroupOfComps("υπηρεσίες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);      
        
       EntityFilterSettings[] invoiceServiceErs = new EntityFilterSettings[5];   
      
     // invoiceServiceErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      invoiceServiceErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //invoiceServiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       //invoiceServiceErs[1]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","customerId","customer","customer","",1,-1,-1,FIELD_NOCOMPLETION);
       //invoiceServiceErs[2]=new EntityFilterSettings("επίθετο","","string","equals","name","customer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceServiceErs[1]=new EntityFilterSettings("πελάτης","checkboxTable","string","","customerId","customer","customer","",1,-1,-1,FIELD_NOCOMPLETION);
      // invoiceServiceErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","customer",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
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
        //entityReportBandFieldsSaleLine[9] = new EntityReportBandField("saleline","customerId","Νο πελάτη","java.lang.String",7,true,null,null);                    
                    
        boolean[] boolSettingsCustomerΑ = {true,true,true,true};             
        boolean[] boolSettingsSaleHeaderΑ = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsSaleHeaderA =new EntityReportBandField[11];
       
        entityReportBandFieldsSaleHeaderA[0] = new EntityReportBandField("saleheader","saleHeaderId","saleHeaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsSaleHeaderA[1] = new EntityReportBandField("saleheader","saleCodeOfDocument","κωδ παραστατικού","java.lang.String",15,true,null,null);
        entityReportBandFieldsSaleHeaderA[2] = new EntityReportBandField("saleheader","actionseriesId","actionseriesId","java.lang.String",8,true,null,null);   
        entityReportBandFieldsSaleHeaderA[3] = new EntityReportBandField("actionseries","actionSeriesCode","actionSeriesCode","java.lang.String",9,true,null,null);           
        entityReportBandFieldsSaleHeaderA[4] = new EntityReportBandField("saleheader","dateOfSale","ημερομηνία","java.lang.Date",18,true,null,null);        
        entityReportBandFieldsSaleHeaderA[5] = new EntityReportBandField("saleheader","pricePreVat","προ φόρου","java.lang.Double",9,true,null,null);  
        entityReportBandFieldsSaleHeaderA[5] = new EntityReportBandField("saleheader","priceTotal","τελικό ποσό","java.lang.Double",9,true,null,null);  
        entityReportBandFieldsSaleHeaderA[6] = new EntityReportBandField("saleheader","customerId","Νο πελάτη","java.lang.String",8,true,null,null);
        entityReportBandFieldsSaleHeaderA[7] = new EntityReportBandField("saleheader","dbYearId","dbYearId","java.lang.String",9,true,null,null);
        entityReportBandFieldsSaleHeaderA[8] = new EntityReportBandField("saleheader","countTotal","πλήθος","java.lang.String",9,true,null,null); 
        entityReportBandFieldsSaleHeaderA[9] = new EntityReportBandField("saleheader","withholdingtaxAmount","withholdingtaxAmount","java.lang.Double",10,true,null,null);  
        entityReportBandFieldsSaleHeaderA[10] = new EntityReportBandField("saleheader","priceTotalAfterWithholdingTax","priceTotalAfterWithholdingTax","java.lang.Double",10,true,null,null);          
        
        //EntityReportBand[] reportBandSaleHeader = new EntityReportBand[1];
        //reportBandSaleHeader[0] = new EntityReportBand("saleheader","πώληση","saleheader",entityReportBandFieldsSaleHeader,ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleHeader);

       // boolean[] boolSettingsCustomer = {true,true,true,true};
       EntityReportBandField[] entityReportBandFieldsCustomerB =new EntityReportBandField[6];
       
        entityReportBandFieldsCustomerB[0] = new EntityReportBandField("customer","customerId","Νο πελάτη","java.lang.Integer",8,true,null,null);
        entityReportBandFieldsCustomerB[1] = new EntityReportBandField("customer","dbCompanyId","dbCompanyId","java.lang.String",8,true,null,null);
        entityReportBandFieldsCustomerB[2] = new EntityReportBandField("customer","title","επωνυμία","java.lang.String",40,true,null,null);
        entityReportBandFieldsCustomerB[3] = new EntityReportBandField("customer","customerCode","κωδικός","java.lang.String",10,true,null,null);
        entityReportBandFieldsCustomerB[4] = new EntityReportBandField("customer","vatNo","Α.Φ.Μ.","java.lang.String",10,true,null,null);
        entityReportBandFieldsCustomerB[5] = new EntityReportBandField("customer","activityDescr","δραστηριότητα","java.lang.String",30,true,null,null);
             
        
        int[] invoiceCheckFieldOrderby1 = {3,5};
        int[] invoiceCheckFieldOrderby2 = {5,4};
        int[] invoiceCheckFieldOrderby3 = {1};
        

       EntityReportBand[] reportBandCustomerService = new EntityReportBand[3];
       reportBandCustomerService[0] = new EntityReportBand("customer","πελάτης","customer",entityReportBandFieldsCustomerB,invoiceCheckFieldOrderby1,"customerId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsCustomerΑ,entityPanelCustomer,fieldsOnTitleCustomer,fieldsOnTitleCaptionCustomer);//,"","");
       reportBandCustomerService[1] = new EntityReportBand("saleheader","παραστατικό","saleheader",entityReportBandFieldsSaleHeaderA,invoiceCheckFieldOrderby2,"saleheaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleHeaderΑ,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);//,"",""); 
       reportBandCustomerService[2] = new EntityReportBand("saleline","υπηρεσία","saleline",entityReportBandFieldsSaleLine,invoiceCheckFieldOrderby3,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleLine,null,null,null);//,"","");
       
       boolean[] boolSettingsReportInvoiceA = {true,false,true,true,false};
       
       
       int[] intReportSettingsInvoiceA= {0,0,0,0};

       //EntityFilterSettings[] invoiceErs = null;
       //EntityGroupOfComps[] invoiceEntityGroupOfComps = null;
       int[] invoicesSelectedA =null;
       //String globalYearPlusOne="";
       
       EntityReport rc = new EntityReport("rptCustomerSaleheaderSaleline",REPORT_CAT_1,reportBandCustomerService,"SELECT customer.customerId, saleheader.dateOfSale, saleheader.saleHeaderId, saleline.inc, customer.*, saleheader.*, saleline.*, stock.*, actionseries.* FROM customer, saleheader, saleline, stock, actionseries WHERE customer.dbCompanyId = saleheader.dbCompanyId AND actionseries.dbCompanyId = customer.dbCompanyId AND stock.dbCompanyId = customer.dbCompanyId AND saleline.dbCompanyId = saleheader.dbCompanyId AND saleheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND saleheader.customerId = customer.customerId AND saleheader.saleHeaderId = saleline.saleHeaderId AND saleline.stockId = stock.stockId AND actionseries.actionseriesId = saleheader.actionseriesId",""/*"ORDER BY name"*/,"ODMR","πελάτες, παραστατικά, υπηρεσίες","",invoiceServiceErs,salelineEntityGroupOfComps,invoicesSelectedA, null,/*invoiceCheckFieldOrderby3,*/"","","",intReportSettingsInvoiceA,boolSettingsReportInvoiceA,"");//,globalYearPlusOne);
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
       //salesErs[1]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","customerId","customer","customer","",1,-1,-1,FIELD_NOCOMPLETION);
       //salesErs[2]=new EntityFilterSettings("επίθετο","","string","equals","name","customer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       salesErs[1]=new EntityFilterSettings("πελάτης","checkboxTable","string","","customerId","customer","customer","",1,-1,-1,FIELD_NOCOMPLETION);
      // salesErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","customer",null,"",1,-1,-1,FIELD_NOCOMPLETION);        
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
//      boolean[] boolSettingsReportCustomerfileA = {true,true,true,true,true}; 
//      boolean[] boolSettingsReportCustomerfileB = {true,true,true,true,true}; 
//       int[] intSettingsReportCustomerfile={0,0,0,0};
      	
       //fileEntityQuery[0]= new EntityQuery("SELECT customer.customerafm AS customerafm, SUM(invoice.valueReturn) AS value,SUM(retValueAccordingToType(3, invoice.currencyId, invoice.valueReturn)) AS valueReturn3 FROM application, customer, invoice "+
      // "WHERE customer.customerId=application.customerId AND invoice.customerId=customer.customerId AND application.dbyear=invoice.dbyear AND application.deliveryId =invoice.deliveryId AND application.dbCompanyId=invoice.dbCompanyId GROUP BY customer.customerId",false,0,null,null,null,null,null);


      
                 
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
        //entityReportBandFieldsSaleLineA[8] = new EntityReportBandField("saleline","customerId","Νο πελάτη","java.lang.String",7,true,null,null);                    
                    
                    
        boolean[] boolSettingsSaleHeader = {true,true,true,true};            
        EntityReportBandField[] entityReportBandFieldsSaleHeaderB =new EntityReportBandField[8];
       
        entityReportBandFieldsSaleHeaderB[0] = new EntityReportBandField("saleheader","saleCodeOfDocument","κωδ παραστατικού","java.lang.String",15,true,null,null);
        entityReportBandFieldsSaleHeaderB[1] = new EntityReportBandField("saleheader","dateOfSale","ημερομηνία","java.lang.Date",18,true,null,null);        
        entityReportBandFieldsSaleHeaderB[2] = new EntityReportBandField("saleheader","priceTotal","τελικό ποσό","java.lang.Double",20,true,null,null);  
        entityReportBandFieldsSaleHeaderB[3] = new EntityReportBandField("saleheader","customerId","Νο πελάτη","java.lang.String",7,true,null,null);
        entityReportBandFieldsSaleHeaderB[4] = new EntityReportBandField("saleheader","saleHeaderId","saleHeaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsSaleHeaderB[5] = new EntityReportBandField("customer","title","όνομα","java.lang.String",30,true,null,null);
        entityReportBandFieldsSaleHeaderB[6] = new EntityReportBandField("saleheader","dbCompanyId","dbCompanyId","java.lang.String",9,true,null,null);
        entityReportBandFieldsSaleHeaderB[7] = new EntityReportBandField("saleheader","dbYearId","dbYearId","java.lang.String",9,true,null,null);      
      
      int[] headOrderby ={2,1,6,5};
      int[] lineOrderby ={1};

//       EntityReportBand[] reportBandCustomerServiceSaleDoc = new EntityReportBand[2];                                                                                                         //  EntityPanel[] entityPanelDrillIn,String[]  panelDrillFieldsOnTitleIn,String[]  panelDrillFieldsOnTitleCaptionIn
       reportBandCustomerServiceSaleDoc[0] = new EntityReportBand("saleheader","παραστατικά","saleheader",entityReportBandFieldsSaleHeaderB,headOrderby,"saleHeaderId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleHeader,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);//,"",""); 
       reportBandCustomerServiceSaleDoc[1] = new EntityReportBand("saleline","υπηρεσίες","saleline",entityReportBandFieldsSaleLineA,lineOrderby,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleLine,null,null,null);        
      
      
      
       
       

     //  docEntityReportBand[0] = new EntityReportBand("αγρότες","SELECT customer.customerafm AS customerafm, SUM(invoice.valueReturn) AS value,SUM(retValueAccordingToType(3, invoice.currencyId, invoice.valueReturn)) AS valueReturn3 FROM application, customer, invoice, dbDelivery "+
     //  "WHERE customer.customerId=application.customerId AND invoice.customerId=customer.customerId AND application.dbyear=invoice.dbyear AND application.deliveryId =invoice.deliveryId AND application.dbCompanyId=invoice.dbCompanyId GROUP BY customer.customerId","","application",ENTITYREPORT_QUERY_TYPE_MAIN,-1,null,boolSettingsFar); 
        
       
       EntityReport entReportServiceSale = new EntityReport("invoicedosandservices", REPORT_CAT_1,reportBandCustomerServiceSaleDoc,"SELECT * FROM saleheader, saleline, customer, stock, actionseries "
               + "WHERE saleheader.customerId = customer.customerId AND saleheader.saleHeaderId = saleline.saleHeaderId AND saleline.stockId = stock.stockId AND actionseries.actionseriesId = saleheader.actionseriesId "
               + "AND actionseries.dbCompanyId = customer.dbCompanyId  AND customer.dbCompanyId = saleheader.dbCompanyId AND stock.dbCompanyId = customer.dbCompanyId AND saleline.dbCompanyId = saleheader.dbCompanyId AND saleheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,/*"ORDER BY customer.name"*/"","ODMR","παραστατικά και υπηρεσίες","",salesErs,saleDocumentGroupOfComps,invoicesaSelected, null,/*fileOrderby,*/"","","",intSettingsReportCustomerfile,boolSettingsReportDoc,"");//,globalYearPlusOne) ;
        EntityMenu emrh = new EntityMenu();                                                                                                                                                                                                                                                                                                                                        //   invoiceServiceErs,salelineEntityGroupOfComps,invoicesSelected, null,invoiceCheckFieldOrderby3,"","","",intReportSettingsInvoice,boolSettingsReportInvoice,globalYearPlusOne);                                                
        emrh.setEntityReport(entReportServiceSale,ICO_PRINT_PREVIEW16); //ICO_REPORTFILE
        emrh.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrh = new DataTreeNode(emrh);
        nodeReports.getChildFromCaption(REPORT_CAT_1).addChild(nodeemrh);

       // ------------------------------------- REPORT_CAT_2 --------------------

        
       EntityGroupOfComps[] saleheaderEntityGroupOfComps = new EntityGroupOfComps[3];
       saleheaderEntityGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       saleheaderEntityGroupOfComps[1] = new EntityGroupOfComps("πελάτης",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       saleheaderEntityGroupOfComps[2] = new EntityGroupOfComps("παραστατικό",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       //invoiceEntityGroupOfComps[3] = new EntityGroupOfComps("υπηρεσίες",4,0);        
        
       EntityFilterSettings[] invoiceErs = new EntityFilterSettings[7];   
      
     // invoiceErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader",VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
      invoiceErs[0]=new EntityFilterSettings("χρήση","onelookup","string","","dbYearId","dbyear","saleheader", VariablesGlobal.globalYearId,0,-1,-1,FIELD_NOCOMPLETION);
       //invoiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       invoiceErs[1]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","customerId","customer","customer","",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[2]=new EntityFilterSettings("επίθετο","","string","equals","title","customer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[3]=new EntityFilterSettings("πελάτης","checkboxTable","string","","customerId","customer","customer","",1,-1,-1,FIELD_NOCOMPLETION);
       invoiceErs[4]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","customer",null,"",1,-1,-1,FIELD_NOCOMPLETION);
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
        entityReportBandFieldsSaleHeader[5] = new EntityReportBandField("saleheader","customerId","Νο πελάτη","java.lang.String",7,true,null,null);
        entityReportBandFieldsSaleHeader[6] = new EntityReportBandField("saleheader","dbYearId","dbYearId","java.lang.String",7,true,null,null);
        entityReportBandFieldsSaleHeader[7] = new EntityReportBandField("saleheader","saleHeaderId","saleHeaderId","java.lang.String",9,true,null,null);
        entityReportBandFieldsSaleHeader[8] = new EntityReportBandField("saleheader","countTotal","πλήθος","java.lang.String",9,true,null,null);         
         

        boolean[] boolSettingsCustomerA = {true,true,true,true};
       EntityReportBandField[] entityReportBandFieldsCustomerA =new EntityReportBandField[6];
       
        entityReportBandFieldsCustomerA[0] = new EntityReportBandField("customer","customerId","Νο πελάτη","java.lang.Integer",10,true,null,null);
        entityReportBandFieldsCustomerA[1] = new EntityReportBandField("customer","dbCompanyId","dbCompanyId","java.lang.String",10,true,null,null);
        entityReportBandFieldsCustomerA[2] = new EntityReportBandField("customer","title","επωνυμία","java.lang.String",23,true,null,null);
        entityReportBandFieldsCustomerA[3] = new EntityReportBandField("customer","customerCode","κωδικός","java.lang.String",10,true,null,null);
        entityReportBandFieldsCustomerA[4] = new EntityReportBandField("customer","vatNo","Α.Φ.Μ.","java.lang.String",11,true,null,null);
        entityReportBandFieldsCustomerA[5] = new EntityReportBandField("customer","activityDescr","δραστηριότητα","java.lang.String",28,true,null,null);

        //EntityReportBand[] reportBandSaleHeader = new EntityReportBand[1];
       //reportBandSaleHeader[0] = new EntityReportBand("saleheader","πώληση","saleheader",entityReportBandFieldsSaleHeader,"customerId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleHeader); 

       int[] custCheckFieldOrderby = {3,5,4};
       int[] headCheckFieldOrderby = {4,1,9};
       
       
       EntityReportBand[] reportBandCustomer = new EntityReportBand[2];
       reportBandCustomer[0] = new EntityReportBand("customer","πελάτης","customer",entityReportBandFieldsCustomerA,custCheckFieldOrderby,"customerId",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsCustomerA,entityPanelCustomer,fieldsOnTitleCustomer,fieldsOnTitleCaptionCustomer);//,"","");
       reportBandCustomer[1] = new EntityReportBand("saleheader","παραστατικά","saleheader",entityReportBandFieldsSaleHeader,headCheckFieldOrderby,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsSaleHeaderΒ,entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);//,"",""); 
 
       boolean[] boolSettingsReportInvoiceB = {true,false,true,true,false};
       
      
       int[] intReportSettingsInvoiceB= {0,0,0,0};

//       EntityFilterSettings[] invoiceErs = null;
 //      EntityGroupOfComps[] invoiceEntityGroupOfComps = null;
       int[] invoicesSelectedB =null;
       String globalYearPlusOne="";
       
       EntityReport rb = new EntityReport("rptCustomerSaleheader",REPORT_CAT_1,reportBandCustomer,"SELECT * FROM customer, saleheader,actionseries WHERE saleheader.customerId = customer.customerId AND actionseries.actionseriesId = saleheader.actionseriesId AND customer.dbCompanyId =  saleheader.dbCompanyId AND actionseries.dbCompanyId = customer.dbCompanyId AND customer.dbCompanyId LIKE '"+VariablesGlobal.globalCompanyId+"'","","ODMR","πελάτες και παραστατικά","",invoiceErs,saleheaderEntityGroupOfComps,invoicesSelectedB, null,/*invoiceCheckFieldOrderbyB,*/"","","",intReportSettingsInvoiceB,boolSettingsReportInvoiceB,"");//,globalYearPlusOne);
          // EntityReport ra = new EntityReport("invoice",REPORT_CAT_1,null,invoiceEntQuery,null,null,"ODMR","κατάσταση ελέγχου","",invoiceErs,invoiceEntityGroupOfComps,invoicesSelected, null,invoiceFieldOrderby) ;
                    
        EntityMenu emrb = new EntityMenu();
        emrb.setEntityReport(rb,ICO_PRINT_PREVIEW16);
        emrb.setEntityType(ENTITY_TYPE_REPORT);
        DataTreeNode nodeemrb = new DataTreeNode(emrb);
        //nodeCat = nodeRoot.getChildFromCaption(REPORTS).getChildFromCaption(REPORT_CAT_1);
        nodeReports.getChildFromCaption(REPORT_CAT_2).addChild(nodeemrb);                 


       EntityFilterSettings[] customerErs = new EntityFilterSettings[4];   
       //customerErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","customer",VariablesGlobal.globalCompanyId,0,-1,-1,FIELD_OBLIGATORY);
       //invoiceErs[1]=new EntityFilterSettings("χρήση","onelookup","string","","dbyearId","dbyear","customer", VariablesGlobal.globalYearId,0,0,-1,FIELD_OBLIGATORY);
       //invoiceErs[2]=new EntityFilterSettings("αποστολή","onelookup","string","equals","deliveryId","dbDelivery","a",VariablesGlobal.globalDeliveryId,0,-1,-1,FIELD_OBLIGATORY);        
       customerErs[0]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","customerId","customer","customer","",0,-1,-1,FIELD_NOCOMPLETION);
       customerErs[1]=new EntityFilterSettings("επίθετο","","string","equals","title","customer",null,"",0,-1,-1,FIELD_NOCOMPLETION);
       customerErs[2]=new EntityFilterSettings("αγρότης","checkboxTable","string","","customerId","customer","customer","",0,-1,-1,FIELD_NOCOMPLETION);
       customerErs[3]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","customer",null,"",0,-1,-1,FIELD_NOCOMPLETION);
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

       
       
       EntityGroupOfComps[] customerEntityGroupOfComps = new EntityGroupOfComps[1];
       //customerEntityGroupOfComps[0] = new EntityGroupOfComps("χρήση",6,0);
       customerEntityGroupOfComps[0] = new EntityGroupOfComps("πελάτης",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
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
      

       boolean[] boolSettingsCustomer = {true,true,true,true};
       
       EntityReportBandField[] entityReportBandFieldsCustomer =new EntityReportBandField[6];

        entityReportBandFieldsCustomer[0] = new EntityReportBandField("customer","customerId","Νο πελάτη","java.lang.Integer",11,true,null,null);
        entityReportBandFieldsCustomer[1] = new EntityReportBandField("customer","dbCompanyId","dbCompanyId","java.lang.String",11,true,null,null);
        entityReportBandFieldsCustomer[2] = new EntityReportBandField("customer","title","επωνυμία","java.lang.String",44,true,null,null);
        entityReportBandFieldsCustomer[3] = new EntityReportBandField("customer","customerCode","κωδικός","java.lang.String",18,true,null,null);
        entityReportBandFieldsCustomer[4] = new EntityReportBandField("customer","vatNo","Α.Φ.Μ.","java.lang.String",18,true,null,null);
        entityReportBandFieldsCustomer[5] = new EntityReportBandField("customer","activityDescr","δραστηριότητα","java.lang.String",45,true,null,null);
               
        int[] invoiceCheckFieldOrderby = {3,4,5};
       EntityReportBand[] reportBandCustomerA = new EntityReportBand[1];
       reportBandCustomerA[0] = new EntityReportBand("customer","πελάτης","customer",entityReportBandFieldsCustomer,invoiceCheckFieldOrderby,"",ENTITYREPORT_QUERY_TYPE_MAIN,boolSettingsCustomer,entityPanelCustomer,fieldsOnTitleCustomer,fieldsOnTitleCaptionCustomer);//,"","");
       boolean[] boolSettingsReportInvoice = {true,false,true,true,false};
      
       int[] intReportSettingsInvoice= {0,0,0,0};
       
       EntityReport ra = new EntityReport("rptCustomer",REPORT_CAT_1,reportBandCustomerA,"SELECT * FROM customer WHERE customer.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId,/*"ORDER BY name"*/"","ODMR","πελάτες","",customerErs,customerEntityGroupOfComps,invoicesSelected, null,/*invoiceCheckFieldOrderby,*/"","","",intReportSettingsInvoice,boolSettingsReportInvoice,"");//,globalYearPlusOne);
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
       //invoiceEntQuery[0]= new EntityQuery("SELECT customer.customerId,customer.customerAfm, customer.surname,customer.name,invoice.* FROM invoice, customer, application WHERE application.customerId = customer.customerId AND invoice.customerId = customer.customerId",false,0,null,null,null,null,null);
  
       //EntityQuery[] deliveryCheckEntQuery = new EntityQuery[1]; 
       //deliveryCheckEntQuery[0]= new EntityQuery("SELECT invoice.customerId,buyer.buyerTitle , invoice.paymentTypeId,invoice.invoiceNo,invoice.date,invoice.dbyear, invoice.productId, invoice.value, invoice.valueReturn FROM invoice, customer, application, buyer WHERE buyer.buyerId=invoice.buyerId AND invoice.customerId = customer.customerId AND application.customerId=customer.customerId AND application.customerId=invoice.customerId ORDER BY customer.surname,customer.name",false,0,null,null,null,null,null);
//-----        
       // same as entityInfoMany the read only of list
       //String deliveryCheckHeaderEntQuery="SELECT f.customerId AS\"Νο πελάτη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.customerAfm AS\"Α.Φ.Μ.\", permanent AS \"υπολ\" , d.dateOfApplication AS \"ημ/νια αίτησης\" , COUNT(i.value) AS \"πλήθος\", SUM(i.value) AS \"αξία\", SUM(retValueAccordingToType(1, i.currencyId, i.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, i.currencyId, i.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, i.currencyId, i.valueReturn)) AS \"κατ 3\", d.valueReturn AS \"συν επιστρ\", d.payment AS \"κράτηση\" FROM application d, customer f, invoice i WHERE i.customerId = f.customerId AND d.customerId = f.customerId AND i.deliveryId = d.deliveryId AND i.dbyear=d.dbyear AND i.dbCompanyId=d.dbCompanyId GROUP BY f.customerId, d.permanent, d.dateOfApplication ORDER BY f.surname, f.name";
  //     EntityReportGroup[] deliveryEntityReportGroup = new EntityReportGroup[2];
 //      deliveryEntityReportGroup[0] = new EntityReportGroup("αγρότες","SELECT f.customerId AS\"Νο πελάτη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.customerAfm AS\"Α.Φ.Μ.\", permanent AS \"υπολ\" ,a.deliveryId, a.dateOfApplication AS \"ημ/νια αίτησης\" , COUNT(i.value) AS \"πλήθος\", SUM(i.value) AS \"αξία\", SUM(retValueAccordingToType(1, i.currencyId, i.valueReturn)) AS \"κατ 1\", SUM(retValueAccordingToType(2, i.currencyId, i.valueReturn)) AS \"κατ 2\", SUM(retValueAccordingToType(3, i.currencyId, i.valueReturn)) AS \"κατ 3\", a.valueReturn AS \"συν επιστρ\", a.payment AS \"κράτηση\" FROM application a, customer f, invoice i WHERE i.customerId = f.customerId AND a.customerId = f.customerId AND i.deliveryId = a.deliveryId AND i.dbyear=a.dbyear AND i.dbCompanyId=a.dbCompanyId GROUP BY f.customerId, a.permanent, a.dateOfApplication, a.valuereturn", "ORDER BY f.surname, f.name","application",ENTITYREPORT_QUERY_TYPE_MAIN,0,"customerId",boolSettingsCustomer);  // header
 //      deliveryEntityReportGroup[1] = new EntityReportGroup("παραστατικά","SELECT i.aa AS\"α/α\", i.customerId,i.deliveryId,b.buyerTitle AS\"αγοραστής\", it.abbreviation  AS\"παρ/κο\", i.invoiceNo  AS\"αριθμός\",i.date  AS\"ημερομηνία\",i.dbyear, p.productName  AS\"προϊόν\", i.value  AS\"αξία\", i.valueReturn  AS\"επιστροφή\" FROM invoice i, customer f, application a, buyer b, product p, currency pt, paymentType it WHERE i.productId=p.productId AND pt.currencyId=p.currencyId AND b.buyerId=i.buyerId AND i.customerId = f.customerId AND a.customerId=f.customerId AND a.customerId=i.customerId AND a.dbyear=i.dbyear AND a.dbCompanyId=i.dbCompanyId AND a.deliveryId=i.deliveryId AND i.paymentTypeId=it.paymentTypeId","ORDER BY f.surname,f.name","appinvoice",ENTITYREPORT_QUERY_TYPE_MAIN,1,null,boolSettingsInvoice);  // many

        /*public EntityReportBandField(String nameIn,  String captionIn, String tableNameIn ,String dbFieldNameIn,int groupOfCompsIn,String colClassNameIn,int colWidthIn,
              String defaultValueIn, EntityDBFieldsCalculation[] fieldsCalculationIn)*/


         
       
       
       //---------------------------------------------------------------------------
       
      /* EntityFilterSettings[] deliveryErs = new EntityFilterSettings[5];       
       deliveryErs[0]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","customerId","customer","f","",-1,-1,1,FIELD_NOCOMPLETION);
       deliveryErs[1]=new EntityFilterSettings("επίθετο","","string","equals","surname","customer",null,"",-1,-1,1,FIELD_NOCOMPLETION);
       deliveryErs[2]=new EntityFilterSettings("ΑΦΜ","","string","equals","customerAfm","customer",null,"",-1,-1,1,FIELD_NOCOMPLETION);
       deliveryErs[3]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","town","",-1,-1,-1,FIELD_NOCOMPLETION);
       deliveryErs[4]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","customer","",-1,-1,1,FIELD_NOCOMPLETION);

       
       EntityGroupOfComps[] deliveryEntityGroupOfComps = null;       

       
       int[]deliveryOrderby = {2,1};  
       	boolean[] boolSettingsTown = {true,true,true,true};    
        boolean[] boolSettingsFarme = {true,true,true,true}; 
        boolean[] boolSettingsReportFarm = {true,true,true,true,true}; 	
       int[] intSettingsReportFarm={0,0,0,0};*/
       
         
       EntityFilterSettings[] invoiceeErs = new EntityFilterSettings[10];       
       invoiceeErs[0]=new EntityFilterSettings("Νο πελάτη","lookup","string","fromto","customerId","customer","f","",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[1]=new EntityFilterSettings("αγρότης","checkboxTable","string","","customerId","customer","customer","",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[2]=new EntityFilterSettings("επίθετο","","string","equals","surname","customer",null,"",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[3]=new EntityFilterSettings("ΑΦΜ","","string","equals","customerAfm","customer",null,"",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","customer","",-1,-1,0,FIELD_NOCOMPLETION);
       invoiceeErs[5]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","customer","",-1,-1,0,FIELD_NOCOMPLETION);
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
    //EntityDBFields[] eDbFields = new EntityDBFields[customerDBFields.length+serviceDBFields.length];
    
   // EntityDBFields[] edbfc  = customerDBFields;

    //EntityDBFields[] edbfs  = serviceDBFields;
    ArrayList <EntityDBFields> lstSerSales = new ArrayList();
    lstSerSales = addDbFieldsToArrayListToImport(lstSerSales,customerDBFields);
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
