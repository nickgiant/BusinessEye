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
import com.tool.model.EntityQuery;
import com.tool.model.EntityTask;
import com.tool.utils.*;
import java.util.ArrayList;

public class EntityDataPayroll extends EntityData implements Constants
{
       DataTree dTree;
       DataTreeNode nodeRoot ;
       private UtilsDate utilsDate;
            String globalYearPlusOne="";
       int intYearPlusOne=0;  
       
    public static final String REPORT_CAT_1 = "ανάλυση";
    public static final String REPORT_CAT_2 = "λογαριασμοί";
    public static final String REPORT_CAT_3 = "συναλλασόμενοι";
    public static final String REPORT_CAT_4 = "κατάλογοι";           
    
    public static final String SYSTEM_CAT_1 = "εταιρίας, συστήματος";
    public static final String SYSTEM_CAT_2 = "παροχής υπηρεσίας";
    public static final String SYSTEM_CAT_3 = "εσόδων - εξόδων";
    
   public static final String OSYK_DATE_GRATER_THAN = "201901";
 
    
    
        EntityDBFields[] dbCompanyDBFields = new EntityDBFields[26];        
        EntityGroupOfComps[] dbCompanyEntityGroupOfComps= new EntityGroupOfComps[8];
        EntityGroupOfPanels[] dbCompanyEntityGroupOfPanels = new EntityGroupOfPanels[4];
        
        EntityDBFields[] dbyearLineDBFields = new EntityDBFields[5];
        
        
        EntityUpdateAdditional[] updateAdditionalDbCompany = new EntityUpdateAdditional[1];
        
        String dbCompanyQueryEditable="SELECT * FROM dbcompany";//dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.title AS\"τίτλος\", dbcompany.companyVatNo AS\"Α.Φ.Μ.\", dbcompany.doyId ,dbcompany.geoCatId,  dbcompany.bankId , dbcompany.bankAccount , dbcompany.bankAccountIBAN , dbcompany.notes FROM dbcompany";
        String[] fieldsOnTitleDbCompany ={"dbCompanyId","title","companyVatNo"};
        String[] fieldsOnTitleCaptionDbCompany  ={"Νο","τίτλος","ΑΦΜ"};  
        String[] fieldsUniqueDbCompany = {"companyVatNo"};      
        EntityCheckFields[] entityCheckFieldsDBCompany = null;
        EntityPanel entityPanelDbCompanyDataentry = new EntityPanel("ODOR","dbcompany",dbCompanyDBFields,dbCompanyEntityGroupOfComps,dbCompanyEntityGroupOfPanels,"Νο εταιρίας","","dbCompanyId",dbCompanyQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDbCompany,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,updateAdditionalDbCompany,null,null,entityCheckFieldsDBCompany,null);  
        EntityPanel[] entityPanelDbCompany = new EntityPanel[] {entityPanelDbCompanyDataentry};          
   
         //----------------------------------------------------------------        
     EntityDBFields[] doyKadDBFields = new EntityDBFields[3];       // be carefull  companyDoyKadDBFields and doyKadDBFields seem the same. are NOT

        
        EntityGroupOfComps[] doyKadEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] doyKadEntityGroupOfPanels = new EntityGroupOfPanels[1];

        
        String doyKadQueryEditable = "SELECT * FROM pay_doykad";
        String[] fieldsOnTitleDoyKad ={"doyKadId","kadCode","description"};
        String[] fieldsOnTitleCaptionDoyKad  ={"Νο","κωδικός","ονομασία"};
        String[] fieldsUniqueDoyKad = null;
        EntityCheckFields[] entityCheckFieldsDoyKad = null;
        EntityPanel entityPanelDoyKadDataentry = new EntityPanel("ODOR","pay_doykad",doyKadDBFields,doyKadEntityGroupOfComps,doyKadEntityGroupOfPanels,"ΔΟΥ ΚΑΔ","","doyKadId",doyKadQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueDoyKad,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsDoyKad,null);  
        EntityPanel[] entityPanelDoyKad = new EntityPanel[] { entityPanelDoyKadDataentry};

        //----------------------------------------------------------------        
                    
        EntityDBFields[] comBranchDBFields = new EntityDBFields[10]; 
            

        
        EntityGroupOfComps[] comBranchEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] comBranchEntityGroupOfPanels = new EntityGroupOfPanels[1];

        
        String comBranchQueryEditable = "SELECT * FROM hr_companybranch";
        String[] fieldsOnTitleComBranch ={"branchId","branchTitle"};
        String[] fieldsOnTitleCaptionComBranch  ={"Νο","ονομασία"};
        String[] fieldsUniqueComBranch = null;
        EntityCheckFields[] entityCheckFieldsComBranch = null;
        EntityPanel entityPanelComBranchDataentry = new EntityPanel("ODOR","hr_companybranch",comBranchDBFields,comBranchEntityGroupOfComps,comBranchEntityGroupOfPanels,"υποκατάστημα","","branchId",comBranchQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueComBranch,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsComBranch,null);  
        EntityPanel[] entityPanelComBranch = new EntityPanel[] { entityPanelComBranchDataentry};
         //----------------------------------------------------------------    
        
            
      
        EntityDBFields[] employeeDBFields = new EntityDBFields[26];
        EntityGroupOfComps[] employeeEntityGroupOfComps = new EntityGroupOfComps[8];
        EntityGroupOfPanels[] employeeEntityGroupOfPanels = new EntityGroupOfPanels[2];
        
        // same as second query in etityInfo
        //String payrollPeriodQueryEditable="SELECT pay_payrollperiod.payrollPeriodId AS \"Νο συναλλασσόμενου\", pay_payrollperiod.surname AS \"επίθετο\", pay_payrollperiod.name AS\"όνομα\", pay_payrollperiod.fathername AS \"πατρόνυμο\", pay_payrollperiod.payrollPeriodAfm AS \"Α.Φ.Μ.\", pay_payrollperiod.doyId, pay_payrollperiod.idNo AS \"αρ ταυτοτ\", pay_payrollperiod.townId, pay_payrollperiod.address AS \"διέυθυνση\", pay_payrollperiod.phone AS \"τηλέφωνο\" FROM pay_payrollperiod, town WHERE pay_payrollperiod.townId=town.townId";
        String employeeQueryEditable="SELECT * FROM hr_employee";// LEFT JOIN doy ON pay_payrollperiod.doyId=doy.doyId";// LEFT JOIN bank ON pay_payrollperiod.bankId=bank.bankId";        
        String[] fieldsOnTitleemployee ={"employeeId","lastname","firstname"};
        String[] fieldsOnTitleCaptionemployee  ={"Νο","επίθετο","όνομα"};
        String[] employeeCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueemployee = {"vatNo"};
        //STATS be careful to have in the query all the fields that are also in the title
        EntityPanel entityPanelemployeeDataentry;// = new EntityPanel("ODOR","pay_payrollperiod",payrollPeriodDBFields,payrollPeriodEntityGroupOfComps,payrollPeriodEntityGroupOfPanels,"Νο συναλλασσόμενου","","payrollPeriodId",payrollPeriodQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniquepayrollPeriod,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,entReportEsExDoc);      
        EntityPanel entityPanelemployeeHistory;// = new EntityPanel("statpayrollPeriodHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT pay_payrollperiod.payrollPeriodId, pay_payrollperiod.dbCompanyId, sxesoexoheader.esoexoheaderId, sxactiontype.actionTypeCode, sxesoexoheader.esoexoCodeOfDocument,sxesoexoheader.dbYearId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.isPrinted, sxesoexoheader.countTotal,sxesoexoheader.quantityTotal, sxesoexoheader.pricePreVat, sxesoexoheader.priceVat, sxesoexoheader.priceTotal","FROM pay_payrollperiod, sxesoexoheader, sxactiontype","WHERE pay_payrollperiod.payrollPeriodId = sxesoexoheader.payrollPeriodId AND  = sxesoexoheader.dbCompanyId AND sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId AND pay_payrollperiod.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND pay_payrollperiod.payrollPeriodId =","","ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoCodeOfDocument",false,"",false,"",entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);     
        //EntityPanel entityPanelpayrollPeriodProducts = new EntityPanel("statpayrollPeriodProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.payrollPeriodId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelpayrollPeriodBuyers = new EntityPanel("statpayrollPeriodBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.payrollPeriodId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelpayrollPeriodSalesPerDate = new EntityPanel("statpayrollPeriodSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.payrollPeriodId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelemployee;// = new EntityPanel[] { entityPanelpayrollPeriodDataentry,entityPanelpayrollPeriodHistory};//,entityPanelpayrollPeriodStatistics,entityPanelpayrollPeriodProducts,entityPanelpayrollPeriodBuyers,entityPanelpayrollPeriodSalesPerDate};
        
        //---------------------------------------------------------------- 
        
  
        
        EntityDBFields[] employmentperiodDBFields = new EntityDBFields[35];
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] employmentperiodEntityGroupOfComps =new EntityGroupOfComps[8];
        EntityGroupOfPanels[] employmentperiodEntityGroupOfPanels = new EntityGroupOfPanels[2];
        
        
        String employmentperiodQueryEditable = "SELECT * FROM pay_employmentperiod";//product.productId AS \"Νο προϊόντος\", product.productName AS \"ονομασία\", product.currencyId FROM product";
        String[] fieldsOnTitleemploymentperiod ={"employmentPeriodId","employeeId"};
        String[] fieldsOnTitleCaptionemploymentperiod  ={"Νο","εργαζόμενος"};      
        String[] employmentperiodCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueemploymentperiod = null;
        
       EntityCheckFields[] entityCheckFieldsemploymentperiod = null;
      
        
        
        EntityPanel entityPanelemploymentperiodDataentry = new EntityPanel("ODOR","pay_employmentperiod",employmentperiodDBFields,employmentperiodEntityGroupOfComps,employmentperiodEntityGroupOfPanels,"Νo","","employmentPeriodId",employmentperiodQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueemploymentperiod,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsemploymentperiod,null);  
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProductTraders = new EntityPanel("statProductTraders","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT pay_payrollperiod.payrollPeriodId AS \"νο αγρότη\", pay_payrollperiod.surname, pay_payrollperiod.name, pay_payrollperiod.fatherName,pay_payrollperiod.payrollPeriodAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, pay_payrollperiod","WHERE invoice.payrollPeriodId = pay_payrollperiod.payrollPeriodId AND invoice.productId=","GROUP BY pay_payrollperiod.payrollPeriodId","ORDER BY pay_payrollperiod.surname, pay_payrollperiod.name, pay_payrollperiod.fatherName,pay_payrollperiod.payrollPeriodAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelemploymentperiod = new EntityPanel[] { entityPanelemploymentperiodDataentry};//,entityPanelProductStatistics,entityPanelProductTraders,entityPanelProductBuyers,entityPanelProductSalesPerDate};
        
         //----------------------------------------------------------------        
         
      
        //----------------------------------------------------------------
        //EntityDBFields[] actionTypeLineDBFields = new EntityDBFields[7];
        EntityDBFields[] payContractDBFields = new EntityDBFields[3];
        

        
        EntityGroupOfComps[] payContractEntityGroupOfComps = new EntityGroupOfComps[2];
        EntityGroupOfPanels[] payContractEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        
        String payContractQueryEditable="SELECT * FROM pay_contract";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitlePayContract ={"contractId","description"};
        String[] fieldsOnTitleCaptionPayContract  ={"Νο","ονομασία"};     
            String[] fieldsUniquePayContract = null; 
            EntityCheckFields[] entityCheckFieldsPayContract =null;
        EntityPanel entityPanelPayContractDataentry = new EntityPanel("ODOR","pay_contract",payContractDBFields,payContractEntityGroupOfComps,payContractEntityGroupOfPanels,"Νο","","contractId",payContractQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniquePayContract,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsPayContract,null);  
        EntityPanel[] entityPanelPayContract = new EntityPanel[] {entityPanelPayContractDataentry};        
     //---------------------------------------------------------------------------
        EntityDBFields[]  contractSpecialDBFields = new EntityDBFields[4];
        
        EntityGroupOfComps[] payContractSpecialEntityGroupOfComps = null;//new EntityGroupOfComps[1];
        EntityGroupOfPanels[] payContractSpecialEntityGroupOfPanels = null;//new EntityGroupOfPanels[1];
        
        String payContractSpecialQueryEditable="SELECT * FROM pay_contractspecialty";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitlePayContractSpecial ={"contractSpecialtyId","description"};
        String[] fieldsOnTitleCaptionPayContractSpecial  ={"Νο","περιγραφή"};     
            String[] fieldsUniquePayContractSpecial = null; 
            EntityCheckFields[] entityCheckFieldsPayContractSpecial =null;
        EntityPanel entityPanelPayContractSpecialDataentry = new EntityPanel("ODOR","pay_contractspecialty",contractSpecialDBFields,payContractSpecialEntityGroupOfComps,payContractSpecialEntityGroupOfPanels,"Νο","","contractSpecialtyId",payContractSpecialQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniquePayContractSpecial,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsPayContractSpecial,null);  
        EntityPanel[] entityPanelPayContractSpecial = new EntityPanel[] {entityPanelPayContractSpecialDataentry};  
       //-------------------------------------------------------------------
        
        
        //EntityDBFields[] actionTypeLineDBFields = new EntityDBFields[7];
        EntityDBFields[] efkaΚadDBFields = new EntityDBFields[2];

        
        EntityGroupOfComps[] efkaΚadEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] efkaΚadEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        
        String efkaΚadQueryEditable="SELECT * FROM pay_efkakad";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitleΕfkaΚad ={"efkaKadCode","description"};
        String[] fieldsOnTitleCaptionΕfkaΚad  ={"κωδικός","περιγραφή"};     
            String[] fieldsUniqueΕfkaΚad = null; 
            EntityCheckFields[] entityCheckFieldsΕfkaΚad =null;
        EntityPanel entityPanelΕfkaΚadDataentry = new EntityPanel("ODOR","pay_efkakad",efkaΚadDBFields,efkaΚadEntityGroupOfComps,efkaΚadEntityGroupOfPanels,"Νο","","efkaΚadId",efkaΚadQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueΕfkaΚad,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsΕfkaΚad,null);  
        EntityPanel[] entityPanelΕfkaΚad = new EntityPanel[] {entityPanelΕfkaΚadDataentry};        
        
    //----------------------------------------------------------------
        EntityDBFields[] efkaEidikotitaDBFields = new EntityDBFields[2];

        
        EntityGroupOfComps[] efkaEidikotitaEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] efkaEidikotitaEntityGroupOfPanels = new EntityGroupOfPanels[1];        
        
      String efkaEidikotitaQueryEditable ="SELECT * FROM pay_efkaeidikotita";
     String[] fieldsOnTitleEfkaEidikotita = {"efkaeidikotitaId" };
     String[] fieldsOnTitleCaptionEfkaEidikotita = {"κωδικός"};
            String[] fieldsUniqueΕfkaEidikotita = null; 
            EntityCheckFields[] entityCheckFieldsΕfkaEidikotita =null;     
     EntityPanel entityPanelΕfkaEidikotitaDataentry = new EntityPanel("ODOR","pay_efkaeidikotita",efkaEidikotitaDBFields,efkaEidikotitaEntityGroupOfComps,efkaEidikotitaEntityGroupOfPanels,"Νο","","efkaEidikotitaId",efkaEidikotitaQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueΕfkaEidikotita,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsΕfkaEidikotita,null);  
        EntityPanel[] entityPanelEfkaEidikotita = new EntityPanel[] {entityPanelΕfkaEidikotitaDataentry};        
      
        //----------------------------------------------------------------
        //EntityDBFields[] actionTypeLineDBFields = new EntityDBFields[7];
        EntityDBFields[] efkaKpkDBFields = new EntityDBFields[2];

        
        EntityGroupOfComps[] efkaKpkEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] efkaKpkEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        
        String efkaKpkQueryEditable="SELECT * FROM pay_efkakpk";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitleEfkaKpk ={"codeEfkaKpk","description"};
        String[] fieldsOnTitleCaptionEfkaKpk  ={"Νο","ονομασία"};     
            String[] fieldsUniqueEfkaKpk = null;    
        EntityCheckFields[] entityCheckFieldsEfkaKpk = null;
        EntityPanel entityPanelEfkaKpkDataentry = new EntityPanel("ODOR","pay_efkakpk",efkaKpkDBFields,efkaKpkEntityGroupOfComps,efkaKpkEntityGroupOfPanels,"Νο","","codeEfkaKpk",efkaKpkQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueEfkaKpk,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsEfkaKpk,null);    
        EntityPanel[] entityPanelEfkaKpk = new EntityPanel[] {entityPanelEfkaKpkDataentry};        
                
             //----------------------------------------------------------------
        //EntityDBFields[] actionTypeLineDBFields = new EntityDBFields[7];
        EntityDBFields[] calculationPeriodDBFields = new EntityDBFields[4];

        
        EntityGroupOfComps[] calculationPeriodEntityGroupOfComps = new EntityGroupOfComps[1];
        EntityGroupOfPanels[] calculationPeriodEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        
        String calculationPeriodQueryEditable="SELECT * FROM pay_calculationperiod";//geoCatId AS\"Νο πόλης\", geoCatName AS\"πόλη/χωριό\",state AS \"νομός\", postCode AS\"ΤΚ\", phoneCode AS\"κωδ τηλ\" FROM town";
        String[] fieldsOnTitleCalculationPeriod ={"calculationPeriodId","month","description"};
        String[] fieldsOnTitleCaptionCalculationPeriod  ={"Νο","μήνας","περιγραφή"};     
            String[] fieldsUniqueCalculationPeriod = null; 
            EntityCheckFields[] entityCheckFieldsCalculationPeriod =null;
        EntityPanel entityPanelCalculationPeriodDataentry = new EntityPanel("ODOR","pay_calculationperiod",calculationPeriodDBFields,calculationPeriodEntityGroupOfComps,calculationPeriodEntityGroupOfPanels,"Νο","","calculationPeriodId",calculationPeriodQueryEditable,"βασικά στοιχεία",null, false, true,fieldsUniqueCalculationPeriod,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsCalculationPeriod,null);  
        EntityPanel[] entityPanelCalculationPeriod = new EntityPanel[] {entityPanelCalculationPeriodDataentry};        
        
       //---------------------------------------------------------------- 
      
        EntityDBFields[] payrollPeriodDBFields = new EntityDBFields[14];
        EntityGroupOfComps[] payrollPeriodEntityGroupOfComps = new EntityGroupOfComps[2];
        EntityGroupOfPanels[] payrollPeriodEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        // same as second query in etityInfo
        //String payrollPeriodQueryEditable="SELECT pay_payrollperiod.payrollPeriodId AS \"Νο συναλλασσόμενου\", pay_payrollperiod.surname AS \"επίθετο\", pay_payrollperiod.name AS\"όνομα\", pay_payrollperiod.fathername AS \"πατρόνυμο\", pay_payrollperiod.payrollPeriodAfm AS \"Α.Φ.Μ.\", pay_payrollperiod.doyId, pay_payrollperiod.idNo AS \"αρ ταυτοτ\", pay_payrollperiod.townId, pay_payrollperiod.address AS \"διέυθυνση\", pay_payrollperiod.phone AS \"τηλέφωνο\" FROM pay_payrollperiod, town WHERE pay_payrollperiod.townId=town.townId";
        String payrollPeriodQueryEditable="SELECT * FROM pay_payrollperiod";// LEFT JOIN doy ON pay_payrollperiod.doyId=doy.doyId";// LEFT JOIN bank ON pay_payrollperiod.bankId=bank.bankId";        
        String[] fieldsOnTitlepayrollPeriod ={"payrollPeriodId","employmentPeriodId","calculationPeriodId"};
        String[] fieldsOnTitleCaptionpayrollPeriod  ={"Νο","employmentPeriodId","calculationPeriodId"};
        String[] strpayrollPeriodCategories = {DATAENTRY,METRICS};
        String[] fieldsUniquepayrollPeriod = null;//{"vatNo"};
        //STATS be careful to have in the query all the fields that are also in the title
        EntityCheckFields[] entityCheckFieldsTrader = null;
        EntityPanel entityPanelpayrollPeriodDataentry = new EntityPanel("ODOR","pay_payrollperiod",payrollPeriodDBFields,payrollPeriodEntityGroupOfComps,payrollPeriodEntityGroupOfPanels,"Νο","","payrollPeriodId",payrollPeriodQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniquepayrollPeriod,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null/*hr*/,null,entityCheckFieldsTrader,null);  
        //EntityPanel entityPanelpayrollPeriodHistory = new EntityPanel("statpayrollPeriodHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT sxesoexoheader.payrollPeriodId, sxesoexoheader.esoexoheaderId, sxactiontype.actionTypeCode, sxesoexoheader.esoexoCodeOfDocument,sxesoexoheader.dbYearId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.isPrinted, sxesoexoheader.countTotal, sxesoexoheader.pricePreVat, sxesoexoheader.priceVat, sxesoexoheader.priceTotal, sxesoexoheader.isTemplate","FROM sxesoexoheader LEFT JOIN sxactiontype ON sxesoexoheader.sxActionTypeId = sxactiontype.sxActionTypeId","WHERE sxesoexoheader.isTemplate ='0'  AND sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoheader.payrollPeriodId =","","ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoCodeOfDocument",false,"",false,"",entityPanelSale,fieldsOnTitlepayrollPeriod,fieldsOnTitleCaptionpayrollPeriod);     
        //EntityPanel entityPanelpayrollPeriodProducts = new EntityPanel("statpayrollPeriodProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.payrollPeriodId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelpayrollPeriodBuyers = new EntityPanel("statpayrollPeriodBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.payrollPeriodId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelpayrollPeriodSalesPerDate = new EntityPanel("statpayrollPeriodSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.payrollPeriodId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelpayrollPeriod = new EntityPanel[] { entityPanelpayrollPeriodDataentry};//,entityPanelpayrollPeriodStatistics,entityPanelpayrollPeriodProducts,entityPanelpayrollPeriodBuyers,entityPanelpayrollPeriodSalesPerDate};
        

        //----------------------------------------------------------------
        
  public void addDocumentNodes()
  {
      
  }
 
   public ArrayList addEntitiesLookup(ArrayList entities)
   {
       EntityLookUp entityLookUp;
            //int[] lookUpFieldIndexPaymentType ={2,3,0};     
     String[] lookUpFieldDoyKad={"description"};
     //String[] lookUpFieldLabelPaymentType={"είδος παραστατικού","συντομογραφία",null};
     entities.add(entityLookUp = new EntityLookUp("pay_doykad","pay_doykad","SELECT doyKadId AS\"Νο\",kadCode AS\"κωδικός\", description AS\"περιγραφή\" FROM pay_doykad","",null,"","ORDER BY kadCode","","doyKadId","Νο","doyKadId","ΔΟΥ ΚΑΔ",2,lookUpFieldDoyKad,"ΔΟΥ ΚΑΔ",10,"java.lang.String",0,null,null,0,null,null,doyKadQueryEditable,"ΔΟΥ ΚΑΔ","ΔΟΥ ΚΑΔ",null,entityPanelDoyKad,fieldsOnTitleDoyKad, fieldsOnTitleCaptionDoyKad,null,2,1,null,false,-1,-1,null)); 
     //entities.add(entityLookUp = new EntityLookUp("paymenttype","SELECT paymentTypeId AS\"Νο τύπου παρ/κού\",paymentTypeName AS\"ονομασία τύπου παρ/κού\", abbreviation AS\"συντομογραφία\" FROM paymenttype","ORDER BY paymentTypeName","paymentTypeId","Νο τύπου παρ/κού",2,lookUpFieldPaymentType,"είδος παραστατικού",0,null,null,0,null,null,paymenttypeQueryEditable,"τύπου παραστατικού","τύπων παραστατικού",null,entityPanelPaymentType,fieldsOnTitlePaymentType, fieldsOnTitleCaptionPaymentType,null,2,1,null,true,-1,-1)); 

    //---------------------------------------------------------------
    
      EntityFilterSettings[] employeeErs = new EntityFilterSettings[3];       
       //payrollPeriodErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[0]=new EntityFilterSettings("όνομα","","string","equals","title","hr_employee",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","hr_employee",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[3]=new EntityFilterSettings("ταυτότητα","","string","equals","idNo","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","pay_payrollperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","hr_employee","",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[3]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[4]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfEsoexo","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
     
     String[] lookUpFieldemployee={"lastname","firstname","vatNo"};
            
     entities.add(entityLookUp = new EntityLookUp("hr_employee","hr_employee","SELECT hr_employee.employeeId AS\"Νο\", lastname AS\"επίθετο\", firstname AS\"όνομα\",  vatNo AS\"Α.Φ.Μ.\", amkaNo AS\"ΑMKA\" FROM hr_employee","WHERE hr_employee.isActive LIKE 1",null,"AND hr_employee.isActive LIKE 1", "ORDER BY hr_employee.lastname","","employeeId","Νο","employeeId","εργαζόμενος",2,lookUpFieldemployee,"επίθετο ή όνομα ή ΑΦΜ",29,"java.lang.String",0,null, null,0,null,null,employeeQueryEditable, "εργαζόμενου","εργαζόμενων",employeeCategories,entityPanelemployee,fieldsOnTitleemployee,fieldsOnTitleCaptionemployee,employeeErs,-1,2,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));

      //entities.add(entityLookUp = new EntityLookUp("payrollPeriod1Col","pay_payrollperiod","SELECT pay_payrollperiod.payrollPeriodId AS\"Νο συναλλασσόμενου\", payrollPeriodCode AS\"κωδικός\", title AS\"επωνυμία\",  vatNo AS\"Α.Φ.Μ.\" FROM pay_payrollperiod","WHERE pay_payrollperiod.active LIKE 1","AND pay_payrollperiod.active LIKE 1", "ORDER BY pay_payrollperiod.title","","payrollPeriodId","Νο συναλλασσόμενου","payrollPeriodId","συναλλασσόμενος",2,lookUpFieldpayrollPeriod,"επωνυμία ή ΑΦΜ",29,"java.lang.String",0,null,null,0,null,null,payrollPeriodQueryEditable, "συναλλασσόμενου","συναλλασομένων",strpayrollPeriodCategories,entityPanelpayrollPeriod,fieldsOnTitlepayrollPeriod,fieldsOnTitleCaptionpayrollPeriod,payrollPeriodErs,2,1,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));
     
      //-------------------------------------------------------------------
     
             //int[] lookUpFieldIndexPaymentType ={2,3,0};     
     String[] lookUpFieldDbCompany={"title"};
     entities.add(entityLookUp = new EntityLookUp("dbCompanyForPay","dbcompany","SELECT dbCompanyId AS \"νο\", title AS \"επωνυμία\", companyVatNo AS \"ΑΦΜ\" FROM dbcompany","WHERE dbcompany.dbCompanyId = "+VariablesGlobal.globalCompanyId+" OR dbcompany.participatesInPayroll LIKE 1 ",null,"AND dbcompany.active LIKE 1 AND dbcompany.participatesInPayroll LIKE 1", "ORDER BY title","","dbCompanyId","νο","dbCompanyId","εταιρία",2,lookUpFieldDbCompany,"τίτλος εταιρίας",15,"java.lang.String",0,null,null,0,null,null,dbCompanyQueryEditable,"της εταιρίας","εταιριών",null,entityPanelDbCompany,fieldsOnTitleDbCompany,fieldsOnTitleCaptionDbCompany,null,2,1,null,true,2,FIELD_VALIDATION_AFM,null));     

     //-------------------------------------------------------------------     
     
       EntityFilterSettings[] employmentperiodErs = new EntityFilterSettings[3];      
       employmentperiodErs[0]=new EntityFilterSettings("κωδικός","","string","equals"," payrollPeriodCode","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       employmentperiodErs[1]=new EntityFilterSettings("επωνυμία","","string","equals","title","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       employmentperiodErs[2]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[2]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","pay_payrollperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
            // for 'intNoOfColsWhenInTable' look also at  deliveryFieldsMany
      
     
     String[] lookUpFieldEmployee={"title","vatNo"};
            
     entities.add(entityLookUp = new EntityLookUp("pay_employmentperiod","pay_employmentperiod","SELECT pay_employmentperiod.employmentPeriodId AS \"Νο\", pay_employmentperiod.dbCompanyId, pay_employmentperiod.employeeId, hr_employee.lastname, hr_employee.firstname, hr_employee.fathername, companyBranchId, actualEmploymentStartDate, pay_employmentperiod.actualEmploymentEndDate FROM pay_employmentperiod, hr_employee","WHERE pay_employmentperiod.employeeId = hr_employee.employeeId",/*WHERE pay_employmentperiod.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+*//*WHERE pay_payrollperiod.active LIKE 1"*/null,/*"AND pay_payrollperiod.active LIKE 1"*/"", " ORDER BY pay_employmentperiod.employmentPeriodId","","employmentPeriodId","Νο","employmentPeriodId","εργαζόμενος",4,lookUpFieldEmployee,"επωνυμία ή ΑΦΜ",15,"java.lang.String",0,null, null,0,null,null,employmentperiodQueryEditable, "εργαζόμενου","εργαζόμενων",employmentperiodCategories,entityPanelemploymentperiod,fieldsOnTitleemploymentperiod,fieldsOnTitleCaptionemploymentperiod,employmentperiodErs,-1,2,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));

     //------------------------------------------------------------------ 

         EntityFilterSettings[] payContractErs = new EntityFilterSettings[1];       
        payContractErs[0]=new EntityFilterSettings("ονομασία","","string","equals","description","pay_contract",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        
        String[] lookUpFieldPayContract={"description"};                   
    
     entities.add(entityLookUp = new EntityLookUp("pay_contract","pay_contract","SELECT pay_contract.contractId AS\"Νο\", pay_contract.description AS \"περιγραφή\"  FROM pay_contract",""/*"WHERE pay_contract.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId*/,null,""/*"AND pay_efkakad.isActive LIKE 1"*/,"ORDER BY pay_contract.description ","","contractId","Νο","pay_contract","σύμβαση",2,lookUpFieldPayContract,"περιγραφή",20,"java.lang.String",0,null,null,0,null,null,payContractQueryEditable,"σύμβασης εργασίας","συμβάσεων εργασίας",null,entityPanelPayContract,fieldsOnTitlePayContract,fieldsOnTitleCaptionPayContract,payContractErs,-1,1,null,true,-1,-1,null));    	 	
     //----------------------------------------------------------------------------          
      EntityFilterSettings[] payContractSpecialErs = new EntityFilterSettings[1];       
        payContractSpecialErs[0]=new EntityFilterSettings("ονομασία","","string","equals","description","contractspecialty",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
     String[] lookUpFieldPayContractSpecial={"description"}; 
     //String payContractSpecialQueryEditable ="";
     //EntityPanel[] entityPanelPayContractSpecial=null;
     //String[] fieldsOnTitlePayContractSpecial = null;
     //String[] fieldsOnTitleCaptionPayContractSpecial = null;
     String[] fieldsReplacedInsideQueryPayContractSpecial = {"contractOfEmploymentId"};// get it from dbfields
     entities.add(entityLookUp = new EntityLookUp("contractspecialty","pay_contractspecialty","SELECT pay_contractspecialty.contractSpecialtyId AS\"Νο\", pay_contractspecialty.contractId, pay_contract.description  AS \"σύμβαση\" , pay_contractspecialty.description AS \"περιγραφή ειδικότητας\", amount AS \"μισθός\"   FROM pay_contractspecialty, pay_contract","WHERE pay_contractspecialty.contractId = pay_contract.contractId AND pay_contractspecialty.contractId LIKE # ", fieldsReplacedInsideQueryPayContractSpecial/*"WHERE pay_contract.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId*/,""/*"AND pay_efkakad.isActive LIKE 1"*/,"ORDER BY pay_contractspecialty.contractSpecialtyId ","","contractSpecialtyId","Νο","pay_contractspecialty","ειδικότητα σύμβασης",4,lookUpFieldPayContractSpecial,"περιγραφή",20,"java.lang.String",0,null,null,0,null,null,payContractSpecialQueryEditable,"ειδικότητα σύμβασης εργασίας","ειδικότητες συμβάσεων εργασίας",null,entityPanelPayContractSpecial,fieldsOnTitlePayContractSpecial,fieldsOnTitleCaptionPayContractSpecial,payContractSpecialErs,-1,1,null,false,-1,-1,null));    	 	
      //------------------------------------------------------------------ 

         EntityFilterSettings[] efkaΚadErs = new EntityFilterSettings[2];       
        efkaΚadErs[0]=new EntityFilterSettings("κωδικός","","string","equals","efkaKadCode","pay_efkakad",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        efkaΚadErs[1]=new EntityFilterSettings("περιγραφή","","string","equals","description","pay_efkakad",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        
        String[] lookUpFieldΕfkaΚad={"description"};                   
    
     entities.add(entityLookUp = new EntityLookUp("pay_efkakad","pay_efkakad","SELECT pay_efkakad.efkaKadCode AS \"Νο\", pay_efkakad.description  AS\"περιγραφή\"  FROM pay_efkakad",""/*WHERE pay_efkakad.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId*/,null,""/*"AND pay_efkakad.isActive LIKE 1"*/,"ORDER BY pay_efkakad.efkaKadCode","","efkaKadCode","Νο","pay_efkakad","ΕΦΚΑ ΚΑΔ",2,lookUpFieldΕfkaΚad,"περιγραφή",33,"java.lang.String",0,null,null,0,null,null,efkaΚadQueryEditable,"ΕΦΚΑ ΚΑΔ","ΕΦΚΑ ΚΑΔ",null,entityPanelΕfkaΚad,fieldsOnTitleΕfkaΚad,fieldsOnTitleCaptionΕfkaΚad,efkaΚadErs,-1,1,null,false,-1,-1,null));    	 	
  
         
     entities.add(entityLookUp = new EntityLookUp("pay_efkakadFromCompany","pay_efkakad","SELECT pay_efkakad.efkaKadCode AS \"Νο\", pay_efkakad.description  AS\"περιγραφή\"  FROM pay_efkakad, pay_companyefkakad","WHERE pay_companyefkakad.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND pay_efkakad.efkaKadCode = pay_companyefkakad.efkaKadCode"/*WHERE pay_efkakad.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId*/,null,""/*"AND pay_efkakad.isActive LIKE 1"*/,"ORDER BY pay_efkakad.efkaKadCode","","efkaKadCode","Νο","pay_efkakad","ΕΦΚΑ ΚΑΔ",2,lookUpFieldΕfkaΚad,"περιγραφή",33,"java.lang.String",0,null,null,0,null,null,efkaΚadQueryEditable,"ΕΦΚΑ ΚΑΔ","ΕΦΚΑ ΚΑΔ",null,entityPanelΕfkaΚad,fieldsOnTitleΕfkaΚad,fieldsOnTitleCaptionΕfkaΚad,efkaΚadErs,2,1,null,false,-1,-1,null));    	 	
     
     //---------------------------------------------------------------
     EntityFilterSettings[] efkaEidikotitaSpecialErs = new EntityFilterSettings[2];       
     efkaEidikotitaSpecialErs[0]=new EntityFilterSettings("κωδικός","","string","equals","codeEfkaEidikotita","pay_efkaeidikotita",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
     efkaEidikotitaSpecialErs[1]=new EntityFilterSettings("ονομασία","","string","equals","description","pay_efkaeidikotita",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
     String[] lookUpFieldEfkaEidikotita={"description"}; 
     
     //String efkaEidikotitaQueryEditable ="";
     //EntityPanel[] entityPanelEfkaEidikotita=null;
     //String[] fieldsOnTitleEfkaEidikotita = null;
     //String[] fieldsOnTitleCaptionEfkaEidikotita = null;
     String[] fieldsReplacedInsideQueryEfkaEidikotita = {"efkaKadCode"};// get it from dbfields
     entities.add(entityLookUp = new EntityLookUp("pay_efkaeidikotita","pay_efkaeidikotita","SELECT pay_efkaeidikotita.codeEfkaEidikotita AS \"Νο\",   pay_efkaeidikotita.description  AS \"ειδικότητα\", pay_efkakpk.codeEfkaKpk AS \"ΚΠΚ\" FROM pay_efkakad, pay_efkaeidikotita, pay_efkakadeidkpk, pay_efkakpk","WHERE pay_efkakad.efkaKadCode = pay_efkakadeidkpk.efkaKadCode AND pay_efkaeidikotita.codeEfkaEidikotita = pay_efkakadeidkpk.codeEfkaEid" +
      " AND pay_efkakpk.codeEfkaKpk =  pay_efkakadeidkpk.codeEfkaKpk AND pay_efkakad.efkaKadCode LIKE '#' AND pay_efkakadeidkpk.dateEnd = 999912  AND pay_efkakpk.dateStart > "+OSYK_DATE_GRATER_THAN,
       fieldsReplacedInsideQueryEfkaEidikotita,""/*"AND pay_efkakad.isActive LIKE 1"*/,"ORDER BY pay_efkaeidikotita.description","","codeEfkaEidikotita","Νο","pay_efkaeidikotita","ειδικότητα",2,lookUpFieldEfkaEidikotita,"περιγραφή",20,"java.lang.String",0,null,null,0,null,null,efkaEidikotitaQueryEditable,"ειδικότητα","ειδικότητες",null,entityPanelEfkaEidikotita,fieldsOnTitleEfkaEidikotita,fieldsOnTitleCaptionEfkaEidikotita,efkaEidikotitaSpecialErs,-1,1,null,false,-1,-1,null));    	 	
      //------------------------------------------------------------------ 
      
            

         EntityFilterSettings[] efkaKpkErs = new EntityFilterSettings[2];       
        efkaKpkErs[0]=new EntityFilterSettings("κωδικός","","string","equals","codeEfkaKpk","pay_efkakpk",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
         efkaKpkErs[1]=new EntityFilterSettings("ονομασία","","string","equals","description","pay_efkakpk",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","sxActionTypeCatId","servicecat","sxaccount","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        
        String[] lookUpFieldEfkaKpk={"description"};                   
    
     entities.add(entityLookUp = new EntityLookUp("pay_efkakpk","pay_efkakpk","SELECT pay_efkakpk.codeEfkaKpk AS\"Νο\", pay_efkakpk.description AS \"περιγραφή\", pay_efkakpk.employeePercentage AS \"% εργαζόμενου\", pay_efkakpk.employerPercentage AS \"% εργοδότη\"  FROM pay_efkakpk","WHERE pay_efkakpk.dateStart > "+OSYK_DATE_GRATER_THAN,null,""/*active = 1*/,"ORDER BY pay_efkakpk.codeEfkaKpk ","","codeEfkaKpk","Νο","pay_efkakpk","ΚΠΚ",2,lookUpFieldEfkaKpk,"περιγραφή",20,"java.lang.String",0,null,null,0,null,null,efkaKpkQueryEditable,"ΚΠΚ","ΚΠΚ"/*φορμών εκτύπωσης*/,null,entityPanelEfkaKpk,fieldsOnTitleEfkaKpk,fieldsOnTitleCaptionEfkaKpk,efkaKpkErs,-1,1,null,false,-1,-1,null));    	 	
     
          //------------------------------------------------------------------ 

         EntityFilterSettings[] calculationPeriodErs = new EntityFilterSettings[1];       
        calculationPeriodErs[0]=new EntityFilterSettings("ονομασία","","string","equals","description","pay_calculationperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        //actionTypeErs[1]=new EntityFilterSettings("τύπος","checkboxTable","string","","actionTypeCatId","stockcat","stock","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        
        String[] lookUpFieldCalculationPeriod={"description"};                   
    
     entities.add(entityLookUp = new EntityLookUp("pay_calculationperiod","pay_calculationperiod","SELECT pay_calculationperiod.calculationPeriodId AS\"Νο\", pay_calculationperiod.efkaPeriodTypeId AS \"περιγραφή ΕΦΚΑ\", month, pay_calculationperiod.description  FROM pay_calculationperiod ",""/*"WHERE pay_calculationperiod.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId*/,null,""/*"AND pay_calculationperiod.isActive LIKE 1"*/,"ORDER BY month, pay_calculationperiod.efkaPeriodTypeId","","calculationPeriodId","Νο","pay_calculationperiod","περίοδος",4,lookUpFieldCalculationPeriod,"ονομασία",12,"java.lang.String",0,null,null,0,null,null,calculationPeriodQueryEditable,"περιόδου υπολογισμού","περιόδων υπολογισμού",null,entityPanelCalculationPeriod,fieldsOnTitleCalculationPeriod,fieldsOnTitleCaptionCalculationPeriod,calculationPeriodErs,-1,1,null,true,-1,-1,null));    	 	
               
     
     
     //------------------------------------------------------------------
            EntityFilterSettings[] payrollPeriodErs = new EntityFilterSettings[3];      
       payrollPeriodErs[0]=new EntityFilterSettings("κωδικός","","string","equals"," payrollPeriodCode","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       payrollPeriodErs[1]=new EntityFilterSettings("επωνυμία","","string","equals","title","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       payrollPeriodErs[2]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[2]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","pay_payrollperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
            // for 'intNoOfColsWhenInTable' look also at  deliveryFieldsMany
      
     
     String[] lookUpFieldpayrollPeriod={"title","vatNo"};
            
     entities.add(entityLookUp = new EntityLookUp("pay_payrollperiod","pay_payrollperiod","SELECT pay_payrollperiod.payrollPeriodId AS \"Νο\", pay_payrollperiod.employmentPeriodId,pay_employmentperiod.actualEmploymentStartDate, pay_payrollperiod.calculationPeriodId  AS \"κωδ.\", pay_payrollperiod.startPayrollDate,  pay_payrollperiod.endPayrollDate, pay_payrollperiod.daysWorked AS \"ημέρες\", pay_payrollperiod.salaryGross AS \"salaryGross\", pay_payrollperiod.deductionEmployeePercentage, pay_payrollperiod.deductionCompanyPercentage  FROM pay_payrollperiod LEFT JOIN pay_calculationperiod ON pay_payrollperiod.calculationPeriodId=pay_calculationperiod.calculationPeriodId  LEFT JOIN pay_employmentperiod ON pay_employmentperiod.employmentPeriodId = pay_payrollperiod.employmentPeriodId",""/*WHERE pay_payrollperiod.active LIKE 1"*/,null,/*"AND pay_payrollperiod.active LIKE 1"*/"", "ORDER BY pay_payrollperiod.employmentPeriodId, pay_payrollperiod.payrollPeriodId","","payrollPeriodId","Νο","payrollPeriodId","μισθοδοτική περίοδος",3,lookUpFieldpayrollPeriod,"επωνυμία ή ΑΦΜ",29,"java.lang.String",0,null,null,0,null,null,payrollPeriodQueryEditable, "μισθοδοτικής περίοδου","μισθοδοτικών περίοδων",strpayrollPeriodCategories,entityPanelpayrollPeriod,fieldsOnTitlepayrollPeriod,fieldsOnTitleCaptionpayrollPeriod,payrollPeriodErs,-1,2,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));
     
     //-----------------------------------------------------------------------------
      
           return entities;
   } 
   
    public ArrayList addEntitiesLookupTableConstants(ArrayList  <EntityLookupTableConstants> listEntityLookupTableConstants)
   {
       EntityLookupTableConstants entityLookupTableConstants;
       
        EntityLookupTableConstantsData [] luTCDataTypeOfSalary = new EntityLookupTableConstantsData[4];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       
       luTCDataTypeOfSalary[0]=new EntityLookupTableConstantsData("99",1,"");
       luTCDataTypeOfSalary[1]=new EntityLookupTableConstantsData("1",2,"μισθωτός");
       luTCDataTypeOfSalary[2]=new EntityLookupTableConstantsData("2",3,"ημερομίσθιος");
       luTCDataTypeOfSalary[3]=new EntityLookupTableConstantsData("3",4,"ωρομίσθιος");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCTypeOfSalary",luTCDataTypeOfSalary));              
      
        EntityLookupTableConstantsData [] luTCDataTypeOfContract = new EntityLookupTableConstantsData[2];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataTypeOfContract[0]=new EntityLookupTableConstantsData("1",1,"αορίστου");
       luTCDataTypeOfContract[1]=new EntityLookupTableConstantsData("2",2,"ορισμενου");
       //luTCDataTypeOfSalary[1]=new EntityLookupTableConstantsData("3",3,"ωρομίσθιος");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCTypeOfContract",luTCDataTypeOfContract));              
       
        EntityLookupTableConstantsData [] luTCDataLevelOfEducation = new EntityLookupTableConstantsData[4];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataLevelOfEducation[0]=new EntityLookupTableConstantsData("1",1,"λύκειο");
       luTCDataLevelOfEducation[1]=new EntityLookupTableConstantsData("2",2,"πανεπιστήμειο");
       luTCDataLevelOfEducation[2]=new EntityLookupTableConstantsData("3",3,"μεταπτυχιακό");
       luTCDataLevelOfEducation[3]=new EntityLookupTableConstantsData("4",4,"διδακτορικό");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCLevelOfEducation",luTCDataLevelOfEducation));              
       
           EntityLookupTableConstantsData [] luTCDataMaritalStatus = new EntityLookupTableConstantsData[4];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataMaritalStatus[0]=new EntityLookupTableConstantsData("1",1,"εγγαμος/η");
       luTCDataMaritalStatus[1]=new EntityLookupTableConstantsData("2",2,"άγαμος/η");
       luTCDataMaritalStatus[2]=new EntityLookupTableConstantsData("3",3,"διαζευγμενος/η");
       luTCDataMaritalStatus[3]=new EntityLookupTableConstantsData("4",4,"χήρος/α");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCMaritalStatus",luTCDataMaritalStatus));              
 
       EntityLookupTableConstantsData [] luTCDataTaxCalc = new EntityLookupTableConstantsData[3];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataTaxCalc[0]=new EntityLookupTableConstantsData("1",1,"κανονικός (με αναγωγή στο έτος)");
       luTCDataTaxCalc[1]=new EntityLookupTableConstantsData("2",2,"καθόλου");
       luTCDataTaxCalc[2]=new EntityLookupTableConstantsData("3",3,"ποσοστό");
       //luTCDataMaritalStatus[3]=new EntityLookupTableConstantsData("4",4,"χήρος/α");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCTypeOfTaxCalc",luTCDataTaxCalc));
       
        EntityLookupTableConstantsData [] luTCDataCalculationType = new EntityLookupTableConstantsData[4];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataCalculationType[0]=new EntityLookupTableConstantsData("001",1,"τακτικές");
       luTCDataCalculationType[1]=new EntityLookupTableConstantsData("003",3,"δώρο Χριστουγέννων");
       luTCDataCalculationType[2]=new EntityLookupTableConstantsData("004",4,"δώρο Πάσχα");
       luTCDataCalculationType[3]=new EntityLookupTableConstantsData("005",5,"επίδομα άδειας");
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCCalculatioType",luTCDataCalculationType));        
       
        EntityLookupTableConstantsData [] luTCDataMonths = new EntityLookupTableConstantsData[12];
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCDataMonths[0]=new EntityLookupTableConstantsData("1",1,"Ιανουάριος");
       luTCDataMonths[1]=new EntityLookupTableConstantsData("2",2,"Φεβρουάριος");
       luTCDataMonths[2]=new EntityLookupTableConstantsData("3",3,"Μάρτιος");
       luTCDataMonths[3]=new EntityLookupTableConstantsData("4",4,"Απρίλιος");
       luTCDataMonths[4]=new EntityLookupTableConstantsData("5",5,"Μάϊος");
       luTCDataMonths[5]=new EntityLookupTableConstantsData("6",6,"Ιούνιος");
       luTCDataMonths[6]=new EntityLookupTableConstantsData("7",7,"Ιούλιος");
       luTCDataMonths[7]=new EntityLookupTableConstantsData("8",8,"Αύγουστος");
       luTCDataMonths[8]=new EntityLookupTableConstantsData("9",9,"Σεπτέμβριος");
       luTCDataMonths[9]=new EntityLookupTableConstantsData("10",10,"Οκτώβριος");
       luTCDataMonths[10]=new EntityLookupTableConstantsData("11",11,"Νοέμβριος");
       luTCDataMonths[11]=new EntityLookupTableConstantsData("12",12,"Δεκέμβριος");       
       listEntityLookupTableConstants.add(entityLookupTableConstants = new EntityLookupTableConstants("LTCMonths",luTCDataMonths));        
       
       
       return listEntityLookupTableConstants;
     
   }
    
    
    
     public void addEntitiesParameters()
       // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
     {  
         
         
         //------------------------------------------------------------
         
 EntityFilterSettings[] calculationPeriodErs = new EntityFilterSettings[1];       
        calculationPeriodErs[0]=new EntityFilterSettings("περιγραφή","","string","equals","description","pay_calculationperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);         
         
        int[] calculationPeriodFieldsOrderby ={4,3,2,1};
        String[] fieldsForSumsCalculationPeriod=null;
        EntityParameter ppf = new EntityParameter("pay_calculationperiod", "SELECT pay_calculationperiod.calculationPeriodId AS\"Νο\", pay_calculationperiod.efkaPeriodTypeId AS \"περιγραφή ΕΦΚΑ\", month, pay_calculationperiod.description  FROM pay_calculationperiod","SELECT pay_calculationperiod.calculationPeriodId AS\"Νο\", pay_calculationperiod.efkaPeriodTypeId AS \"περιγραφή ΕΦΚΑ\", month, description","FROM pay_calculationperiod",""/*"WHERE pay_calculationperiod.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId*/,fieldsForSumsCalculationPeriod,calculationPeriodDBFields ,"περίοδοι υπολογισμού","DORM","Νο","calculationPeriodId",calculationPeriodErs,null,"περίοδου υπολογισμού", "περιόδων υπολογισμού",entityPanelCalculationPeriod,null,fieldsOnTitleCalculationPeriod,fieldsOnTitleCaptionCalculationPeriod,calculationPeriodFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu emppf = new EntityMenu();
        emppf.setEntityParameter(ppf,ICO_TABLE16);
        emppf.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeemppf = new DataTreeNode(emppf);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeemppf);   
                 

        //--------------------------------------------- doyKad
        int[] doyKadFieldsOrderby ={2};
        String[] fieldsForSumsDoyKad=null;//                                in query: because it has the same table 2 times, we use the 1st table as it is, in order to be queried correct in title fields
        EntityParameter pqc = new EntityParameter("pay_doykad", "SELECT doyKadId AS\"Νο\", kadCode AS\"κωδικός\" , description AS\"περιγραφή\" FROM pay_doykad ORDER BY doyKadId","SELECT doyKadId AS\"Νο\", kadCode AS\"κωδικός\" , description AS\"περιγραφή\"","FROM pay_doykad","",fieldsForSumsDoyKad,doyKadDBFields ,"ΚΑΔ εφορίας","DORM","Νο","doyKadId",null,null,"ΚΑΔ εφορίας", "ΚΑΔ εφορίας",entityPanelDoyKad,null,fieldsOnTitleDoyKad,fieldsOnTitleCaptionDoyKad,doyKadFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empqc = new EntityMenu();
        empqc.setEntityParameter(pqc,ICO_TABLE16);
        empqc.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempc = new DataTreeNode(empqc);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempc);         

        //-------------------------------------------------------
        
        EntityFilterSettings[] payContractErs = new EntityFilterSettings[1];
        payContractErs[0]=new EntityFilterSettings("περιγραφή","","string","equals","description","pay_contract",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       
                
                
        int[] payContractFieldsOrderby ={2};
        String[] fieldsForSumsPayContract=null;
        EntityParameter ppfc = new EntityParameter("pay_contract", "SELECT contractId AS \"Νο\",  description AS \"ονομασία\" FROM pay_contract","SELECT contractId AS \"Νο\",  description AS \"ονομασία\" ","FROM pay_contract",""/*WHERE*/,fieldsForSumsPayContract,payContractDBFields ,"συμβάσεις εργασίας","DORM","Νο","contractId",payContractErs,null,"σύμβασης", "συμβάσεων",entityPanelPayContract,null,fieldsOnTitlePayContract,fieldsOnTitleCaptionPayContract,payContractFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu emppfc = new EntityMenu();
        emppfc.setEntityParameter(ppfc,ICO_TABLE16);
        emppfc.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeemppfc = new DataTreeNode(emppfc);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeemppfc);          
        
        
        
       /*EntityParameter[] pz = {pqc};
        EntityMenu[] empza = {empqc};
        //empz.setEntityParameter(pz,ICO_TABLE16);
        //empza.setEntityType(ENTITY_TYPE_PARAMETER);

        EntityManyDataManyRec pza = new EntityManyDataManyRec("πίνακες", "κατηγορίες υπηρεσιών",pz,empza);
        EntityMenu empz = new EntityMenu();
        empz.setEntityManyDataManyRec(pza,ICO_TABLE16);
        empz.setEntityType(ENTITY_TYPE_DATAMANY_PARAMETERS);
        DataTreeNode nodeempz = new DataTreeNode(empz);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempz); */  
        //-----------------------------------------------------------
         
     }    

  public void addEntityInfoNodes()
  {
       utilsDate= new UtilsDate();
       utilsDate.readFromFileDateFormats();
        
       EntityFilterSettings[] dbCompanyErs = new EntityFilterSettings[3];       
       dbCompanyErs[0]=new EntityFilterSettings("επωνυμία","","string","equals","title","dbcompany",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       dbCompanyErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","companyVatNo","dbcompany",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       dbCompanyErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","dbcompany","",-1,-1,-1,FIELD_NOCOMPLETION);
       
        int[] companyFieldsOrderby ={2};
        String[] fieldsForSumsDbCompany=null;
        EntityParameter pg = new EntityParameter("dbcompany", "SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.title AS\"τίτλος\", dbcompany.companyVatNo AS\"Α.Φ.Μ.\", activitycat.activityDescr AS \"δραστηριότητα\", geocat.geoCatName AS\"πόλη/χωριό\", dbcompany.doyId AS\"Νο Δ.Ο.Υ.\" ,doy.doyname AS\"ονομασία Δ.Ο.Υ.\", active,  bank.bankBranch AS\"τράπεζα\",dbcompany.bankAccount AS\"λογαριασμός τραπεζας\",dbcompany.bankAccountIBAN AS\"ΙΒΑΝ\" FROM dbcompany LEFT JOIN doy ON dbcompany.doyId=doy.doyId LEFT JOIN geocat ON dbcompany.geoCatId=geocat.geoCatId LEFT JOIN bank ON dbcompany.bankId=bank.bankId  LEFT JOIN activitycat ON activitycat.activityCatId = dbcompany.activityCatId WHERE dbcompany.dbCompanyId = "+VariablesGlobal.globalCompanyId+" OR dbcompany.participatesInPayroll LIKE 1 ORDER BY dbcompany.title"  ,"SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.title AS\"τίτλος\", dbcompany.companyVatNo AS\"Α.Φ.Μ.\", dbcompany.doyId, dbcompany.geoCatId,  dbcompany.bankId , dbcompany.bankAccount , dbcompany.bankAccountIBAN, dbcompany.notes" ," FROM dbcompany" ,"",fieldsForSumsDbCompany,dbCompanyDBFields ,"εταιρίες μισθοδοσίας","DORM","Νο εταιρίας","dbCompanyId",dbCompanyErs,null,"εταιρίας", "εταιριών",entityPanelDbCompany,null,fieldsOnTitleDbCompany,fieldsOnTitleCaptionDbCompany,companyFieldsOrderby,2,FIELD_VALIDATION_AFM,globalYearPlusOne/*,"",""*/);
        EntityMenu empg = new EntityMenu();
        empg.setEntityParameter(pg,ICO_TABLE16);
        empg.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempg = new DataTreeNode(empg);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeempg);
        //nodeSystem.getChildFromCaption(SYSTEM_CAT_1).addChild(nodeempg);      
      
      //--------------------------------------------------------------------------------
        
       EntityFilterSettings[] employeeErs = new EntityFilterSettings[3];       
       //payrollPeriodErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[0]=new EntityFilterSettings("όνομα","","string","equals","title","hr_employee",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","hr_employee",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[3]=new EntityFilterSettings("ταυτότητα","","string","equals","idNo","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","pay_payrollperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","hr_employee","",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[3]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[4]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfEsoexo","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
                             //onelookup,checkboxTable
       EntityGroupOfComps[] employeeFilterEntityGroupOfComps = null;
       
       int[] employeeFieldsOrderBy ={2,3,4,5};
        // if fields change, change them and at lookup entities
       String[] fieldsForSumsemployee = null;//{"ποσότητα","προ φπα", "φπα","σύνολο"};
       // , sum(quantityTotal) AS \"ποσότητα\", sum(pricePreVat) AS \"προ ΦΠΑ\", sum(priceVat) AS \"ΦΠΑ\", sum(priceTotal) AS \"σύνολο\"    LEFT JOIN sxesoexoheader ON sxesoexoheader.payrollPeriodId = pay_payrollperiod.payrollPeriodId
       EntityInfo ia = new EntityInfo("hr_employee", "SELECT hr_employee.employeeId AS \"Νο\", hr_employee.lastname AS \"επίθετο\",hr_employee.firstname  AS \"όνομα\", hr_employee.fathername  AS \"πατρόνυμο\", hr_employee.mothername  AS \"όνομα μητέρας\",hr_employee.vatNo AS \"Α.Φ.Μ.\", hr_employee.amkaNo AS \"AMKA\", "+/* hr_employee.doyId AS \"Νο Δ.Ο.Υ.\",*/"  hr_employee.isActive AS \"ενεργός/η\", hr_employee.dateOfBirth AS \"ημ. γέννησης\", TIMESTAMPDIFF(YEAR, hr_employee.dateOfBirth, '"+utilsDate.reformatDateStringToSaveToDB(VariablesGlobal.globalDate)+"') AS \"ηλικία\"  FROM hr_employee "+/*LEFT JOIN doy ON pay_payrollperiod.doyId=doy.doyId*/ " ORDER BY hr_employee.lastname" ,"SELECT hr_employee.employeeId AS \"Νο\", hr_employee.lastname AS \"επίθετο\",hr_employee.firstname  AS \"όνομα\", hr_employee.fathername  AS \"πατρόνυμο\", hr_employee.mothername  AS \"όνομα μητέρας\",hr_employee.vatNo AS \"Α.Φ.Μ.\",hr_employee.dateOfBirth AS \"ημ. γέννησης\", "+/* hr_employee.doyId AS \"Νο Δ.Ο.Υ.\",*/"  hr_employee.isActive AS \"ενεργός/η\"","FROM hr_employee"/* LEFT JOIN doy ON pay_payrollperiod.doyId=doy.doyId "LEFT JOIN town ON pay_payrollperiod.townId=town.townId"*/," ",null, fieldsForSumsemployee ,"εργαζόμενοι","DORM","","Νο","employeeId"/*,"",""*/,employeeErs,employeeFilterEntityGroupOfComps ,"εργαζόμενου","εργαζόμενων",employeeCategories,entityPanelemployee,fieldsOnTitleemployee,fieldsOnTitleCaptionemployee,employeeFieldsOrderBy,5,FIELD_VALIDATION_AFM,null,globalYearPlusOne);
        EntityMenu emia = new EntityMenu();
        emia.setEntityInfo(ia,ICO_FARMER16);
        emia.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeemia = new DataTreeNode(emia);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemia);

     //------------------------------------------------------------   
          
        EntityFilterSettings[] employmentperiodErs = new EntityFilterSettings[2];       
        employmentperiodErs[0]=new EntityFilterSettings("εταιρία","checkboxTable","string","","dbcompanyId","dbCompanyForPay","pay_employmentperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
        //employmentperiodErs[0]=new EntityFilterSettings("ονομασία","","string","equals","descr","pay_employmentperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        employmentperiodErs[1]=new EntityFilterSettings("εργαζόμενος","checkboxTable","string","","employeeId","hr_employee","pay_employmentperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
        //employmentperiodErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleline","",-1,-1,-1,FIELD_NOCOMPLETION);
       EntityGroupOfComps[] employmentperiodErsEntityGroupOfComps = null;
        
        int[] employmentperiodFieldsOrderby ={5,4,3,1};
        
        String[] fieldsForSumsemploymentperiod = null;//{"ποσότητα","προ φπα", "φπα","σύνολο"};
        
        EntityInfo pf = new EntityInfo("pay_employmentperiod", "SELECT pay_employmentperiod.employmentPeriodId AS \"Νο\", pay_employmentperiod.dbCompanyId, pay_employmentperiod.employeeId, companyBranchId, hr_employee.lastname, hr_employee.firstname, hr_employee.fathername, actualEmploymentStartDate, actualEmploymentEndDate, salaryOrWage AS \"μισθός\", contractOfEmploymentId, efkaKadCode, efkaEidikotitaCode FROM pay_employmentperiod, hr_employee WHERE pay_employmentperiod.employeeId = hr_employee.employeeId "/*WHERE pay_employmentperiod.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+*/+"ORDER BY pay_employmentperiod.employmentPeriodId"  ,"SELECT pay_employmentperiod.employmentPeriodId AS \"Νο\", pay_employmentperiod.dbCompany","FROM pay_employmentperiod",""/*"WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId*/ ,null,fieldsForSumsemploymentperiod,"περίοδοι εργασίας","DORM","","Νο","employmentPeriodId",/*"","",*/employmentperiodErs,employmentperiodErsEntityGroupOfComps,"περιόδου εργασίας","περιόδων εργασίας",employmentperiodCategories,entityPanelemploymentperiod,fieldsOnTitleemploymentperiod,fieldsOnTitleCaptionemploymentperiod,employmentperiodFieldsOrderby,-1,-1,null,globalYearPlusOne);
        EntityMenu empf = new EntityMenu();
        empf.setEntityInfo(pf,ICO_TABLE16);
        empf.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeempf = new DataTreeNode(empf);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeempf);



       //---------------------------------------------------------------------------------
        
        
      String[] taskCalculationTypeCalc ={"υπολογισμός"};
      String[] taskCalculationTypeNull ={"μηδενισμός"};     
       EntityFilterSettings[] taskErs = new EntityFilterSettings[2];   
       //taskErs[0]=new EntityFilterSettings("εταιρία","onelookup","string","","dbCompanyId","dbcompany","a",VariablesGlobal.globalCompanyId,-1,-1,-1,FIELD_OBLIGATORY);  // checkboxTable
       taskErs[0]=new EntityFilterSettings("περίοδος υπολογισμού","onelookup","string","","calculationperiodId","pay_calculationperiod","a","",0,-1,-1,FIELD_OBLIGATORY);
       taskErs[1]=new EntityFilterSettings("εργαζόμενος","checkboxTable","string","equals","employmentPeriodId","pay_employmentperiod","a","",0,-1,-1,FIELD_OBLIGATORY);         //"αποστολή","onelookup","string","equals","deliveryId","i",null,VariablesGlobal.globalDeliveryId,-1,-1,-1);   
       
       EntityGroupOfComps[] taskEntityGroupOfComps = new EntityGroupOfComps[1];
       taskEntityGroupOfComps[0]= new EntityGroupOfComps("",2,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       
       
       
 
       String loginYear = utilsDate.getCurrentYearFormattedLocaly(VariablesGlobal.globalDate);
       
      EntityQuery[] eqCalc = new EntityQuery[1];
      eqCalc[0] = new EntityQuery("CALL calcPayrollForMonthPeriod("+loginYear+",#,'31','#')",QUERY_UPDATE_STOREDPROCEDURE,null,null,null,"εγγραφές ενημερώθηκαν.","Καμία εγγραφή δεν ενημερώθηκε.");
     // eqCalc[1] = new EntityQuery("UPDATE frvapplicationline AS i, frvapplicationheader AS a SET a.permanent =1,  a.valueReturn=(SELECT SUM(i.valueReturn) FROM frvapplicationline AS l WHERE a.applicationHeaderId = l.applicationHeaderId AND a.dbCompanyId=l.dbCompanyId GROUP BY a.applicationHeaderId AND a.dbCompanyId ) WHERE i.dbCompanyId=a.dbCompanyId AND a.applicationHeaderId = i.applicationHeaderId  AND a.dbCompanyId = "+VariablesGlobal.globalCompanyId,true,0,null,null,null,"αιτήσεις ενημερώθηκαν.","Καμία αίτηση δεν ενημερώθηκε.");
     // eq[1] = new EntityQuery("UPDATE frvapplicationheader SET permanent=1",true,0,"success","faillure");
      EntityQuery[] eqNull = new EntityQuery[1];
      eqNull[0] = new EntityQuery("CALL calcPayrollForAllPeriodTypesDelete(#,'#')",QUERY_UPDATE_STOREDPROCEDURE,null,null,null,"εγγραφές ενημερώθηκαν.","Καμία εγγραφή δεν ενημερώθηκε.");
      //eqNull[1] = new EntityQuery("UPDATE frvapplicationline AS i, frvapplicationheader AS a SET a.permanent =0, a.payment = NULL, a.valueReturn= NULL  WHERE a.dbCompanyId = "+VariablesGlobal.globalCompanyId,QUERY_UPDATE_STOREDPROCEDURE,null,null,null,"αιτήσεις ενημερώθηκαν.","Καμία αίτηση δεν ενημερώθηκε.");
     // eq[3] = new EntityQuery("UPDATE frvapplicationheader SET permanent=0",true,1,"success","faillure");    a.invcount=NULL, a.value=NULL,         //  WHERE i.farmerId=a.farmerId AND i.dbyear=a.dbyear AND i.dbCompanyId=a.dbCompanyId AND a.dbYearDeliveryId=i.dbYearDeliveryId
      EntityTask[] entityTaskDelivery = new EntityTask[2];
      entityTaskDelivery[0] = new EntityTask("permanent","υπολογισμός μισθοδοσίας", "υπολογίζει μισθοδοσία", taskCalculationTypeCalc, taskErs,taskEntityGroupOfComps,eqCalc,false,"d",globalYearPlusOne );
      entityTaskDelivery[1] = new EntityTask("permanentnull","διαγραφή  υπολογισμού μισθοδοσίας", "διαγράφει την επιλεγμένη περίοδο μισθοδοσίας", taskCalculationTypeNull, taskErs,taskEntityGroupOfComps,eqNull,true,null,globalYearPlusOne );
      

        //buyerId,applicationlineTypeId,applicationlineNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\",productId,value AS\"αξία\" 
       //EntityGroupOfComps deliveryEntityFilterGroupOfComps = null;
        
        //deliveryQueryEditable="SELECT d.farmerId, d.dateOfApplication, d.deliveryId,  d.dbCompanyId, d.dbyear  FROM frvapplicationheader d WHERE dbyear="+VariablesGlobal.globalYear+" AND deliveryId = "+VariablesGlobal.globalDeliveryId+" AND dbCompanyId="+VariablesGlobal.globalCompanyId;
       
       
       //  "SELECT aa AS\"αα\", buyerId,applicationlineTypeId,applicationlineNo AS\"αρ παρ/κού\",date AS\"ημερομηνία\",productId,value AS\"αξία\" FROM frvapplicationline"
        //base = new DefaultMutableTreeNode(new EntityInfo("frvapplicationheader", null ,null,null,null,deliveryQueryEditable, "frvapplicationline" ,"SELECT buyerId,applicationlineTypeId,applicationlineNo,date,productId,value FROM frvapplicationline",deliveryFields,deliveryFieldsTranslation,deliveryFieldsMany,deliveryFieldsManyTranslation,deliveryFieldsManyOnInsert,deliveryFieldsManyTranslationOnInsert,"frvapplicationheader",true,deliveryWhereField,deliveryWhereValue,"παραστατικά για νέο αγρότη","DTRO", "Νο αγρότη","farmerId",deliveryPrimKeyMany,deliveryPrimKeyManyTran, null, null, "τον παραγωγό με αποστολή", "παραγωγών με αποστολή","το παραστατικό","παραστατικών",null,entityPanelDeliveryDataentry,null,null));
 //       EntityInfoMany ib = new EntityInfoMany("frvapplicationheader", "SELECT f.farmerId AS\"Νο αγρότη\", f.surname AS\"επίθετο\", f.name AS\"όνομα\", f.fatherName AS\"πατρόνυμο\",f.farmerAfm AS\"Α.Φ.Μ.\", count(ri.value) AS\"πλήθος\",sum(ri.value) AS\"σύνολο\",sum(ri.total1) AS \"επισ 1\",sum(ri.total2) AS \"επισ 2\",sum(ri.total3) AS \"επισ 3\", sum(ri.total1)+sum(ri.total2)+sum(ri.total3) AS \"συν.επιστρ\" FROM return_from_applicationlines ri, frvfarmer f WHERE ri.farmerId = f.farmerId "/*AND ri.deliveryId = "+VariablesGlobal.globalDeliveryId+"*/+" AND ri.dbyear="+VariablesGlobal.globalYear+" AND ri.dbCompanyId="+VariablesGlobal.globalCompanyId+" GROUP BY f.farmerId ORDER BY f.surname, f.name",null,null,null,deliveryQueryEditable,null,null, "frvapplicationline",deliveryQueryManyEditable,applicationHeaderDBFields,applicationLineDBFields,/*deliveryFieldsManyOnInsert,deliveryFieldsManyTranslationOnInsert,*/"frvapplicationheader",deliveryQueryManyReadOnly,true, deliveryWhereField, deliveryWhereValue ,"παραστατικά αγρότη","DTRO","f"/*because in sql f is frvfarmer*/,"Νο αγρότη","farmerId",/*deliveryPrimKeyMany,deliveryPrimKeyManyTran ,*/null,null,"αγρότη με παραστατικά", "αγροτών με παραστατικά","το παραστατικό","παραστατικών", null,entityPanelDelivery,null,null,deliveryFieldsOrderBy,-1,null,entityTaskDelivery,4,FIELD_VALIDATION_AFM,globalYearPlusOne);
       
       
       EntityMenu emib = new EntityMenu();
       emib.setEntityTask("υπολογισμός μισθοδοσίας",entityTaskDelivery,ICO_TASK);
       emib.setEntityType(ENTITY_TYPE_TASK);
       //listEntities.add(emib);
       DataTreeNode nodeemib = new DataTreeNode(emib);
       nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemib);
       
        //------------------------------------------------------------
        
        String currentYear = utilsDate.getCurrentYearFormattedLocaly(VariablesGlobal.globalDate);
        
       EntityFilterSettings[] payrollPeriodErs = new EntityFilterSettings[3];  
       payrollPeriodErs[0]=new EntityFilterSettings("εργαζόμενος","checkboxTable","string","equals","employmentPeriodId","pay_employmentperiod","pay_payrollperiod","",0,-1,-1,FIELD_NOCOMPLETION);         //"αποστολή","onelookup","string","equals","deliveryId","i",null,VariablesGlobal.globalDeliveryId,-1,-1,-1);   
       //payrollPeriodErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[0]=new EntityFilterSettings("όνομα","","string","equals","title","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[3]=new EntityFilterSettings("ταυτότητα","","string","equals","idNo","pay_payrollperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","pay_payrollperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
       //payrollPeriodErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","pay_payrollperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
       payrollPeriodErs[1]=new EntityFilterSettings("περίοδος υπ.","checkboxTable","string","","calculationPeriodId","pay_calculationperiod","pay_payrollperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
       payrollPeriodErs[2]=new EntityFilterSettings("έτος","","string","equals","calculationYear","pay_payrollperiod","pay_payrollperiod",currentYear,-1,-1,-1,FIELD_NOCOMPLETION);
//payrollPeriodErs[4]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfEsoexo","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
                             //onelookup,checkboxTable
       //EntityGroupOfComps[] payrollPeriodEntityGroupOfComps = null;
       
       int[] payrollPeriodFieldsOrderBy ={1,0,2,3,4};
        // if fields change, change them and at lookup entities
       String[] fieldsForSumspayrollPeriod = {"μικτά","κρατ. εργαζ.", "καθαρά","κρατ. εργοδ.","σύν. κρατήσεων","πληρωτέο","σύν. εξόδων"};
       // , sum(quantityTotal) AS \"ποσότητα\", sum(pricePreVat) AS \"προ ΦΠΑ\", sum(priceVat) AS \"ΦΠΑ\", sum(priceTotal) AS \"σύνολο\"    LEFT JOIN sxesoexoheader ON sxesoexoheader.payrollPeriodId = pay_payrollperiod.payrollPeriodId
       EntityInfo id = new EntityInfo("pay_payrollperiod", "SELECT pay_payrollperiod.payrollPeriodId AS \"Νο\", pay_payrollperiod.employmentPeriodId,  hr_employee.lastname, hr_employee.firstname, hr_employee.fathername, pay_employmentperiod.actualEmploymentStartDate, pay_employmentperiod.actualEmploymentEndDate, pay_payrollperiod.calculationPeriodId  AS \"περίοδος υπ.\", pay_payrollperiod.calculationYear AS \"έτος\", pay_payrollperiod.startPayrollDate,  pay_payrollperiod.endPayrollDate, pay_payrollperiod.daysWorked AS \"ημέρες\", pay_payrollperiod.daysGiftWorked AS \"ημ. υπολ. δώρων\",pay_payrollperiod.salaryGross AS \"μικτά\", pay_payrollperiod.deductionEmployeeAmount  AS \"κρατ. εργαζ.\" ,pay_payrollperiod.salaryNet AS \"καθαρά\", pay_payrollperiod.deductionCompanyAmount  AS \"κρατ. εργοδ.\", pay_payrollperiod.deductionTotalAmount AS \"σύν. κρατήσεων\", taxValue AS \"ΦΜΥ\", pay_payrollperiod.netPayable AS \"πληρωτέο\", pay_payrollperiod.totalCompanyExpence AS\"σύν. εξόδων\", pay_payrollperiod.timePeriodCalculated  FROM hr_employee, pay_payrollperiod LEFT JOIN pay_calculationperiod ON pay_payrollperiod.calculationPeriodId=pay_calculationperiod.calculationPeriodId  LEFT JOIN pay_employmentperiod ON pay_employmentperiod.employmentPeriodId = pay_payrollperiod.employmentPeriodId WHERE hr_employee.employeeId = pay_employmentperiod.employeeId ORDER BY pay_payrollperiod.calculationPeriodId,  pay_payrollperiod.payrollPeriodId, pay_payrollperiod.employmentPeriodId" ,"SELECT pay_payrollperiod.payrollPeriodId AS \"Νο\", pay_payrollperiod.employmentPeriodId,pay_payrollperiod.calculationPeriodId  AS \"κωδ.\", pay_payrollperiod.startPayrollDate,  pay_payrollperiod.endPayrollDate, pay_payrollperiod.daysWorked AS \"ημέρες\", pay_payrollperiod.salaryGross AS \"salaryGross\", pay_payrollperiod.deductionEmployeePercentage, pay_payrollperiod.deductionCompanyPercentage","FROM pay_payrollperiod LEFT JOIN pay_calculationperiod ON pay_payrollperiod.calculationPeriodId=pay_calculationperiod.calculationPeriodId  LEFT JOIN pay_employmentperiod ON pay_employmentperiod.employmentPeriodId = pay_payrollperiod.employmentPeriodId"," ",null, fieldsForSumspayrollPeriod ,"μισθοδοτικές περίοδοι","DORM","","Νο","payrollPeriodId"/*,"",""*/,payrollPeriodErs,null ,"μισθοδοτικής περίοδου","μισθοδοτικών περίοδων",strpayrollPeriodCategories,entityPanelpayrollPeriod,fieldsOnTitlepayrollPeriod,fieldsOnTitleCaptionpayrollPeriod,payrollPeriodFieldsOrderBy,-1,-1/*FIELD_VALIDATION_AFM*/,null,globalYearPlusOne);
        EntityMenu emid = new EntityMenu();
        emid.setEntityInfo(id,ICO_TABLE16);//,ICO_FARMER16);
        emid.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeemid = new DataTreeNode(emid);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemid);
        
     //------------------------------------------------------------        
      
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
  }

  
   public EntityDataPayroll()
   {
       
          // need them
   	  dTree = new DataTree();
   	  nodeRoot = new DataTreeNode("root");
   	  dTree.setRootElement(nodeRoot);
   
        //------------------------
        EntityCheckFields[] entityCheckFieldsEmployee = null;
        entityPanelemployeeDataentry = new EntityPanel("ODOR","hr_employee",employeeDBFields,employeeEntityGroupOfComps,employeeEntityGroupOfPanels,"Νο","","employeeId",employeeQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueemployee,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null/*hr*/,null,entityCheckFieldsEmployee,null);  
        //entityPanelemployeeHistory = new EntityPanel("statpayrollPeriodHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT sxesoexoheader.payrollPeriodId, sxesoexoheader.esoexoheaderId, sxactiontype.actionTypeCode, sxesoexoheader.esoexoCodeOfDocument,sxesoexoheader.dbYearId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.isPrinted, sxesoexoheader.countTotal, sxesoexoheader.pricePreVat, sxesoexoheader.priceVat, sxesoexoheader.priceTotal, sxesoexoheader.isTemplate","FROM sxesoexoheader LEFT JOIN sxactiontype ON sxesoexoheader.sxActionTypeId = sxactiontype.sxActionTypeId","WHERE sxesoexoheader.isTemplate ='0'  AND sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoheader.payrollPeriodId =","","ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoCodeOfDocument",false,"",false,"",entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);     
        //EntityPanel entityPanelpayrollPeriodProducts = new EntityPanel("statpayrollPeriodProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.payrollPeriodId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelpayrollPeriodBuyers = new EntityPanel("statpayrollPeriodBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.payrollPeriodId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelpayrollPeriodSalesPerDate = new EntityPanel("statpayrollPeriodSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.payrollPeriodId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        entityPanelemployee = new EntityPanel[] { entityPanelemployeeDataentry};//,entityPanelemployeeHistory};//,entityPanelpayrollPeriodStatistics,entityPanelpayrollPeriodProducts,entityPanelpayrollPeriodBuyers,entityPanelpayrollPeriodSalesPerDate};
            
         //---------------------------------------------------------   
        
 
         
         
        dbyearLineDBFields[0] = new EntityDBFields("dbyear","dbYearId","Νο χρήσης",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");       
        dbyearLineDBFields[1] = new EntityDBFields("dbyear","dbCompanyId","Νο εταιρίας",0,"java.lang.String",4,FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,""); //uses FIELD_PRIMARY_KEY_FROM_PARENTTABLE, not:VariablesGlobal.globalCompanyId
        dbyearLineDBFields[2] = new EntityDBFields("dbyear","dbYearDescr","περιγραφή χρήσης",0,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        dbyearLineDBFields[3] = new EntityDBFields("dbyear","dbDateFrom","από ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        dbyearLineDBFields[4] = new EntityDBFields("dbyear","dbDateTo","εως ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
//dbyearDBFields[3] = new EntityDBFields("dbyear","yearDelivery","αποστολές του έτους",1,"table",FIELD_VISIBLE_AND_EDITABLE,"dbYearDelivery",200,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,dbYearDeliveryDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT dbYearDeliveryId, dbYearId,aa,description,dbCompanyId FROM dbYearDelivery WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  ORDER BY aa",null);
        
        EntityDBFields[] companyDoyKadDBFields = new EntityDBFields[5];    // be carefull  companyDoyKadDBFields and doyKadDBFields seem the same. are NOT
        companyDoyKadDBFields[0] = new EntityDBFields("pay_companydoykad","id","Νο",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");       
        companyDoyKadDBFields[1] = new EntityDBFields("pay_companydoykad","dbCompanyId","Νο εταιρίας",0,"java.lang.String",4,FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,""); //uses FIELD_PRIMARY_KEY_FROM_PARENTTABLE, not:VariablesGlobal.globalCompanyId
        companyDoyKadDBFields[2] = new EntityDBFields("pay_companydoykad","doyKadId","ΚΑΔ εφορίας",0,"java.lang.Integer",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_doykad", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        companyDoyKadDBFields[3] = new EntityDBFields("pay_companydoykad","affectStartDate","από ημερομηνία",0,"java.sql.Date",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        companyDoyKadDBFields[4] = new EntityDBFields("pay_companydoykad","affectEndDate","εως ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        
        EntityDBFields[]  companyEfkaKadDBFields = new EntityDBFields[5];
        companyEfkaKadDBFields[0] = new EntityDBFields("pay_companyefkakad","id","Νο",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");       
        companyEfkaKadDBFields[1] = new EntityDBFields("pay_companyefkakad","dbCompanyId","Νο εταιρίας",0,"java.lang.String",4,FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,""); //uses FIELD_PRIMARY_KEY_FROM_PARENTTABLE, not:VariablesGlobal.globalCompanyId
        companyEfkaKadDBFields[2] = new EntityDBFields("pay_companyefkakad","efkaKadCode","ΕΦΚΑ ΚΑΔ",0,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_efkakad", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        companyEfkaKadDBFields[3] = new EntityDBFields("pay_companyefkakad","affectEfkaKadStartDate","από ημερομηνία",0,"java.sql.Date",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        companyEfkaKadDBFields[4] = new EntityDBFields("pay_companyefkakad","affectEfkaKadEndDate","εως ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        
        comBranchDBFields[0] = new EntityDBFields("hr_companybranch","branchId","Νο",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");       
        comBranchDBFields[1] = new EntityDBFields("hr_companybranch","dbCompanyId","Νο εταιρίας",0,"java.lang.String",4,FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,""); //uses FIELD_PRIMARY_KEY_FROM_PARENTTABLE, not:VariablesGlobal.globalCompanyId
        comBranchDBFields[2] = new EntityDBFields("hr_companybranch","branchTitle","τίτλος",0,"java.lang.String",28,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        comBranchDBFields[3] = new EntityDBFields("hr_companybranch","dateEstablished","ίδρυση",0,"java.sql.Date",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        comBranchDBFields[4] = new EntityDBFields("hr_companybranch","datePaused","παύση",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        comBranchDBFields[5] = new EntityDBFields("hr_companybranch","countryIdLocated","χώρα",0,"java.lang.String",28,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        comBranchDBFields[6] = new EntityDBFields("hr_companybranch","state","περιοχή",0,"java.lang.String",28,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        comBranchDBFields[7] = new EntityDBFields("hr_companybranch","city","πόλη",0,"java.lang.String",28,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        comBranchDBFields[8] = new EntityDBFields("hr_companybranch","pc","ΤΚ",0,"java.lang.String",28,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        comBranchDBFields[9] = new EntityDBFields("hr_companybranch","responsibleIdRepresentative","εκπρόσωπος",0,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_efkakad", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
           
        //String[] updateQueryFieldsCompany ={"dbCompanyId"};

       	dbCompanyDBFields[0] = new EntityDBFields("dbcompany","dbCompanyId","Νο εταιρίας",0,"java.lang.Integer",4, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	dbCompanyDBFields[1] = new EntityDBFields("dbcompany","title","τίτλος",0,"java.lang.String",45,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	dbCompanyDBFields[2] = new EntityDBFields("dbcompany","companyVatNo","Α.Φ.Μ.",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_AFM,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	dbCompanyDBFields[3] = new EntityDBFields("dbcompany","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	dbCompanyDBFields[4] = new EntityDBFields("dbcompany","geoCatId","γεωγραφική κατηγορία",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"geocat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbCompanyDBFields[5] = new EntityDBFields("dbcompany","categoryDescription","δραστηριότητα",0,"java.lang.String",45,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbCompanyDBFields[6] = new EntityDBFields("dbcompany","dateEstablished","ημερομηνία έναρξης",0,"java.sql.Date",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbCompanyDBFields[7] = new EntityDBFields("dbcompany","active","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"true","");
        dbCompanyDBFields[8] = new EntityDBFields("dbcompany","activityCatId","κατηγορία δραστηριότητας",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"activityCat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");        
        dbCompanyDBFields[9] = new EntityDBFields("dbcompany","typeOfVatId","κατηγορία ΦΠΑ",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");        
       	dbCompanyDBFields[10] = new EntityDBFields("dbcompany","currencyId","νόμισμα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"currency",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	
        dbCompanyDBFields[11] = new EntityDBFields("dbcompany","addressCity","πόλη/χωριό",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbCompanyDBFields[12] = new EntityDBFields("dbcompany","addressStreet","οδός",1,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbCompanyDBFields[13] = new EntityDBFields("dbcompany","addressPC","ΤΚ",1,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        dbCompanyDBFields[14] = new EntityDBFields("dbcompany","addressState","νομός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
        dbCompanyDBFields[15] = new EntityDBFields("dbcompany","bankId","τράπεζα",2,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	dbCompanyDBFields[16] = new EntityDBFields("dbcompany","bankAccount","λογαριασμός",2,"java.lang.String",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	dbCompanyDBFields[17] = new EntityDBFields("dbcompany","bankAccountIBAN","ΙΒΑΝ",2,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
        dbCompanyDBFields[18] = new EntityDBFields("dbcompany","participatesInPayroll","συμμετέχει στη μισθoδοσία;",3,"java.lang.Boolean",2,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");
        
        dbCompanyDBFields[19] = new EntityDBFields("dbcompany","employerEfkaNo","αριθμός ΕΦΚΑ",3,"java.lang.String",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbCompanyDBFields[20] = new EntityDBFields("dbcompany","employerTpteNo","ΤΠΤΕ",3,"java.lang.String",45,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        dbCompanyDBFields[21] = new EntityDBFields("dbcompany","branchIdBase","έδρα επιχείρησης",3,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        //dbCompanyDBFields[17] = new EntityDBFields("dbcompany","notes","σημειώσεις",3,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        //dbCompanyDBFields[18] = new EntityDBFields("dbcompany","message","μήνυμα",4,"java.lang.String",100,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        
        //dbCompanyDBFields[19] = new EntityDBFields("dbcompany","lengthOfCodeOfDocuments","μήκος κωδικού παραστατικών",5,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"7","");
        //dbCompanyDBFields[20] = new EntityDBFields("dbcompany","amountIfGreaterThenCalculateWithHoldingTax","ελαχιστο σύνολο για υπολογισμό % φόρου παρακράτησης",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"300","");
        //dbCompanyDBFields[21] = new EntityDBFields("dbcompany","rateOfWithHoldingTax","% φόρου παρακράτησης",5,"java.lang.Double",9,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"20","");
        //dbCompanyDBFields[19] = new EntityDBFields("dbcompany","charOfDecimal","χαρακτήρας δεκαδικών",5,"java.lang.String",1,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCdecimalchar",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,",","");
        //dbCompanyDBFields[4] = new EntityDBFields("dbcompany","charOfThousands","χαρακτήρας χιλιάδων",0,"java.lang.String",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null);
        //dbCompanyDBFields[20] = new EntityDBFields("dbcompany","lengthOfDecimalPrice","μήκος δεκαδικών αξίας",5,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"2","");                
        String[] selectQueryFieldsCompany ={"dbCompanyId"};
        dbCompanyDBFields[22] = new EntityDBFields("dbcompany","hr_companybranch","υποκαταστήματα",4,"table",FIELD_VISIBLE_AND_EDITABLE,"hr_companybranch",160,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,comBranchDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM hr_companybranch WHERE hr_companybranch.dbCompanyId = # ORDER BY branchId",selectQueryFieldsCompany,null,null,null);
        dbCompanyDBFields[23] = new EntityDBFields("dbcompany","pay_companydoykad","ΚΑΔ εφορίας",5,"table",FIELD_VISIBLE_AND_EDITABLE,"pay_companydoykad",160,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,companyDoyKadDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM pay_companydoykad WHERE pay_companydoykad.dbCompanyId = # ORDER BY id",selectQueryFieldsCompany,null,null,null);     //String[] childTableFieldsForSumsIn   
        dbCompanyDBFields[24] = new EntityDBFields("dbcompany","pay_companyefkakad","ΕΦΚΑ ΚΑΔ",6,"table",FIELD_VISIBLE_AND_EDITABLE,"pay_companyefkakad",160,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,companyEfkaKadDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM pay_companyefkakad WHERE pay_companyefkakad.dbCompanyId = # ORDER BY id",selectQueryFieldsCompany,null,null,null);     //String[] childTableFieldsForSumsIn   
        dbCompanyDBFields[25] = new EntityDBFields("dbcompany","dbyear","χρήσεις",7,"table",FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"dbyear",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,dbyearLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT * FROM dbyear WHERE dbCompanyId = # ORDER BY dbyearId",selectQueryFieldsCompany,null,null,null);     //String[] childTableFieldsForSumsIn   
        
        //dbCompanyDBFields[22] = new EntityDBFields("dbcompany","afmTaxisUsername","χρήστης",7,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        ////dbCompanyDBFields[23] = new EntityDBFields("dbcompany","afmTaxisPassword","κωδικός",7,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        dbCompanyEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[1]= new EntityGroupOfComps("διεύθυνση",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[2]= new EntityGroupOfComps("λογαριασμός τράπεζας",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[3]= new EntityGroupOfComps("βασικά μισθοδοσίας",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //dbCompanyEntityGroupOfComps[5]= new EntityGroupOfComps("δεκαδικά (Χρειάζεται να εισέλθετε ξανα στην εφαρμογή για να εφαρμοσθούν οι αλλαγές.)",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[4] = new EntityGroupOfComps("υποκαταστήματα",1,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[5] = new EntityGroupOfComps("ΚΑΔ εφορίας",1,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[6] = new EntityGroupOfComps("ΕΦΚΑ ΚΑΔ",1,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //dbCompanyEntityGroupOfComps[5] = new EntityGroupOfComps("ΚΑΔ εφορίας",4,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[7] = new EntityGroupOfComps("χρήσεις",4,3,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);

        //dbCompanyEntityGroupOfComps[7] = new EntityGroupOfComps("κωδικοί αναζήτης ΑΦΜ",4,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        
        
        dbCompanyEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        dbCompanyEntityGroupOfPanels[1] = new EntityGroupOfPanels("υποκαταστήματα",1);  
        dbCompanyEntityGroupOfPanels[2] = new EntityGroupOfPanels("ΚΑΔ",1);
        dbCompanyEntityGroupOfPanels[3] = new EntityGroupOfPanels("χρήσεις",1);  
        
        
        
        doyKadDBFields[0] = new EntityDBFields("pay_doykad","doyKadId","Νο",0,"java.lang.Integer",1, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	doyKadDBFields[1] = new EntityDBFields("pay_doykad","kadCode","κωδικός",0,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyKadDBFields[2] = new EntityDBFields("pay_doykad","description","ονομασία",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

        doyKadEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        
       doyKadEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1); 
       
    //----------------------------------------------
          
    
       /* EntityDBFields[] employeeIdNoDBFields = new EntityDBFields[8];
        
        employeeIdNoDBFields[0] = new EntityDBFields("hr_employeeidtype","id","id",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        employeeIdNoDBFields[1] = new EntityDBFields("hr_employeeidtype","employeeId","Νο εργαζόμενου",0,"java.lang.String",6, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //employeeIdNoDBFields[2] = new EntityDBFields("hr_employeeidtype","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //employeeIdNoDBFields[3] = new EntityDBFields("payrollPeriodcontact","dbCompanyId","dbCompanyId",0,"java.lang.String",6,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        
        employeeIdNoDBFields[2] = new EntityDBFields("hr_employeeidtype","idTypeId","τύπος",0,"java.lang.Integer",11,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"idtype",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeIdNoDBFields[3] = new EntityDBFields("hr_employeeidtype","no","αριθμός",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeIdNoDBFields[4] = new EntityDBFields("hr_employeeidtype","issuer","υπηρεσία έκδοσης",0,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeIdNoDBFields[5] = new EntityDBFields("hr_employeeidtype","countryIdIssued","countryIdIssued",0,"java.lang.Integer",11,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeIdNoDBFields[6] = new EntityDBFields("hr_employeeidtype","dateIssued","ημερομηνία έκδοσης",0,"java.sql.Date",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employeeIdNoDBFields[7] = new EntityDBFields("hr_employeeidtype","dateExpire","ημερομηνία λήξης",0,"java.sql.Date",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
    */

         EntityDBFields[] employeeBankAccountDBFields = new EntityDBFields[5];
        
        employeeBankAccountDBFields[0] = new EntityDBFields("hr_employeebankaccount","employeeBankAcountId","id",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        employeeBankAccountDBFields[1] = new EntityDBFields("hr_employeebankaccount","employeeId","Νο εργαζόμενου",0,"java.lang.String",6, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        employeeBankAccountDBFields[2] = new EntityDBFields("hr_employeebankaccount","bankId","τράπεζα",0,"java.lang.Integer",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeBankAccountDBFields[3] = new EntityDBFields("hr_employeebankaccount","account","λογαριασμός",0,"java.lang.String",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeBankAccountDBFields[4] = new EntityDBFields("hr_employeebankaccount","iban","ΙΒΑΝ",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
    
        employeeDBFields[0] = new EntityDBFields("hr_employee","employeeId","Νο",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //employeeDBFields[1] = new EntityDBFields("pay_payrollperiod","surname","επίθετο",0,"java.lang.String",20, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,true,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,  false,null);
        //employeeDBFields[1] = new EntityDBFields("pay_payrollperiod","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        employeeDBFields[1] = new EntityDBFields("hr_employee","lastname","επίθετο",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[2] = new EntityDBFields("hr_employee","firstname","όνομα",0,"java.lang.String",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[3] = new EntityDBFields("hr_employee","fathername","πατρόνυμο",0,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[4] = new EntityDBFields("hr_employee","mothername","όνομα μητέρας",0,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[5] = new EntityDBFields("hr_employee","vatNo","Α.Φ.Μ.",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_AFM,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[6] = new EntityDBFields("hr_employee","dateOfBirth","ημ/νία γέννησης",0,"java.sql.Date",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[7] = new EntityDBFields("hr_employee","amkaNo","ΑΜΚΑ",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_AMKA,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[8] = new EntityDBFields("hr_employee","isActive","ενεργός/ή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                    
        //employeeDBFields[6] = new EntityDBFields("hr_employee","activityCatId","κατηγορία δραστηριότητας",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"activityCat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //employeeDBFields[7] = new EntityDBFields("hr_employee","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //employeeDBFields[9] = new EntityDBFields("hr_employee","bankId","τράπεζα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        employeeDBFields[9] = new EntityDBFields("hr_employee","telephone1","τηλέφωνο 1",1,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[10] = new EntityDBFields("hr_employee","telephone2","τηλέφωνο 2",1,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[11] = new EntityDBFields("hr_employee","email","email",1,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        employeeDBFields[12] = new EntityDBFields("hr_employee","address1","διεύθυνση 1",2,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[13] = new EntityDBFields("hr_employee","address2","διευθυνση 2",2,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[14] = new EntityDBFields("hr_employee","city","πόλη",2,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[15] = new EntityDBFields("hr_employee","pc","ΤΚ",2,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        employeeDBFields[16] = new EntityDBFields("hr_employee","idNo","αριθμός",3,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[17] = new EntityDBFields("hr_employee","idIssuer","υπηρεσία έκδοσης",3,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[18] = new EntityDBFields("hr_employee","countryIdIssued","countryIdIssued",3,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"country",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[19] = new EntityDBFields("hr_employee","idDateIssued","ημερομηνία έκδοσης",3,"java.sql.Date",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employeeDBFields[20] = new EntityDBFields("hr_employee","idDateExpire","ημερομηνία λήξης",3,"java.sql.Date",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
           
                
        employeeDBFields[21] = new EntityDBFields("hr_employee","eduLevelId","επίπεδο εκπαίδευσης",4,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCLevelOfEducation",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

        employeeDBFields[22] = new EntityDBFields("hr_employee","maritalStatusId","οικογενειακή κατάσταση",5,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCMaritalStatus",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[23] = new EntityDBFields("hr_employee","countOfChildren","πλήθος παιδιών",5,"java.lang.Integer",4,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        employeeDBFields[24] = new EntityDBFields("hr_employee","notes","σημειώσεις",6,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");


        
        employeeDBFields[25] = new EntityDBFields("hr_employee","hr_employeebankaccount","τραπεζικοί λογαριασμοί",7,"table",FIELD_VISIBLE_AND_EDITABLE,"hr_employeebankaccount",95,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,employeeBankAccountDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM hr_employeebankaccount ORDER BY hr_employeebankaccount.employeeBankAcountId",null,null,null,null);        
        
        
        
        
        employeeEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employeeEntityGroupOfComps[1] = new EntityGroupOfComps("επικοινωνία",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employeeEntityGroupOfComps[2] = new EntityGroupOfComps("διεύθυνση",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employeeEntityGroupOfComps[3] = new EntityGroupOfComps("ταυτότητα / διαβατήριο",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
         employeeEntityGroupOfComps[4] = new EntityGroupOfComps("εκπαίδευση",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
         employeeEntityGroupOfComps[5] = new EntityGroupOfComps("οικογενειακή κατάσταση",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employeeEntityGroupOfComps[6] = new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employeeEntityGroupOfComps[7] = new EntityGroupOfComps("τραπεζικοί λογαριασμοί",1,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE); // if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
       // employeeEntityGroupOfComps[5] = new EntityGroupOfComps("λογαριασμοί τραπεζών",1,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
        
        
        
        
        
        employeeEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        employeeEntityGroupOfPanels[1] = new EntityGroupOfPanels("λογαριασμοί",1);
        //payrollPeriodEntityGroupOfPanels[2] = new EntityGroupOfPanels("χρηματικά",1);
       
        //-------------------------------------------
        int[] inputContractSalaryCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputContractSalary ={9,8};
        //10,inputService,"SELECT vatcat.vatPercentage FROM stock, vatcat  WHERE stock.vatCatId=vatcat.vatCatId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId=");
        EntityDBFieldsCalculation[] fieldsCalculationContractSalarySelect = new EntityDBFieldsCalculation[1];
        fieldsCalculationContractSalarySelect[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,10,inputContractSalaryCategory,inputContractSalary,"SELECT pay_contractspecialty.amount FROM pay_contractspecialty  WHERE pay_contractspecialty.contractSpecialtyId LIKE # AND pay_contractspecialty.contractId LIKE #");
        
        int[] inputKpkCategory ={FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputKpk ={20,21};
        //10,inputService,"SELECT vatcat.vatPercentage FROM stock, vatcat  WHERE stock.vatCatId=vatcat.vatCatId AND stock.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND stock.stockId=");
        EntityDBFieldsCalculation[] fieldsCalculationKpk = new EntityDBFieldsCalculation[1];
        fieldsCalculationKpk[0] = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,23,inputKpkCategory,inputKpk,"SELECT pay_efkakadeidkpk.codeEfkaKpk FROM pay_efkakadeidkpk  WHERE pay_efkakadeidkpk.efkaKadCode LIKE '#' AND pay_efkakadeidkpk.codeEfkaEid LIKE '#'");        
        
        int[] inputTypeOfSalCategory ={FIELDSCALCULATION_CATEGORY_SAME, FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputTypeOfSal ={13,13};        
        int[] inputCalcOfWageCategory ={FIELDSCALCULATION_CATEGORY_SAME, FIELDSCALCULATION_CATEGORY_SAME, FIELDSCALCULATION_CATEGORY_SAME, FIELDSCALCULATION_CATEGORY_SAME,FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputCalcOfWage ={13,12,14,13,12};                
        int[] inputCalcOfHourCategory ={FIELDSCALCULATION_CATEGORY_SAME};
        int[] inputCalcOfHour ={15};        
        
        EntityDBFieldsCalculation[] fieldsCalculationTypeOfSalary = new EntityDBFieldsCalculation[3];//
        fieldsCalculationTypeOfSalary[0]  = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,14,inputTypeOfSalCategory,inputTypeOfSal,"SELECT CASE WHEN # = 1 THEN 25 WHEN # = 2 THEN 26 END");
        fieldsCalculationTypeOfSalary[1] =  new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,15,inputCalcOfWageCategory,inputCalcOfWage,"SELECT CASE WHEN # = 1 THEN # / # WHEN # = 2 THEN # END");
        fieldsCalculationTypeOfSalary[2] =  new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,16,inputCalcOfHourCategory,inputCalcOfHour,"SELECT #/6,667");
        //fieldsCalculationTypeOfSalary[3]  = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,17,inputTypeOfSalCategory,inputTypeOfSal,"SELECT 5");
        //fieldsCalculationTypeOfSalary[4]  = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,18,inputTypeOfSalCategory,inputTypeOfSal,"SELECT 8");
        //fieldsCalculationTypeOfSalary[5]  = new EntityDBFieldsCalculation(FIELDSCALCULATION_CATEGORY_SAME,19,inputTypeOfSalCategory,inputTypeOfSal,"SELECT 40");        
        
        
        employmentperiodDBFields[0] = new EntityDBFields("pay_employmentperiod","employmentPeriodId","Νο",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");        
        employmentperiodDBFields[1] = new EntityDBFields("pay_employmentperiod","dbCompanyId","εταιρία",0,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"dbCompanyForPay",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalCompanyId,"");
        employmentperiodDBFields[2] = new EntityDBFields("pay_employmentperiod","employeeId","εργαζόμενος",1,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"hr_employee", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[3] = new EntityDBFields("pay_employmentperiod","workingTimeId","ωράριο εργασίας",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[4] = new EntityDBFields("pay_employmentperiod","employmentYekaSpecialtyId","κωδικός ειδικότητας ΥΕΚΑ",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        employmentperiodDBFields[5] = new EntityDBFields("pay_employmentperiod","efkaPlhresOrario","πλήρες ωράριο",2,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        employmentperiodDBFields[6] = new EntityDBFields("pay_employmentperiod","efkaOlesErgasimes","όλες οι ημέρες εργάσιμες",2,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[7] = new EntityDBFields("pay_employmentperiod","efkaKuriakes","εργασία τις Κυριακές",2,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
                 
        /*0*/
        employmentperiodDBFields[8] = new EntityDBFields("pay_employmentperiod","experienceOnTheSameEmployerYears","χρόνια προυπηρεσίας στον ίδιο εργοδότη",3,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[9] = new EntityDBFields("pay_employmentperiod","experienceOnTheSameEmployerMonths","μήνες προυπηρεσίας στον ίδιο εργοδότη",3,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        employmentperiodDBFields[10] = new EntityDBFields("pay_employmentperiod","experienceAllEmployersYears","χρόνια προυπηρεσίας γενικά",3,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[11] = new EntityDBFields("pay_employmentperiod","experienceAllEmployersMonths","μήνες προυπηρεσίας γενικά",3,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
                      
        
        
        employmentperiodDBFields[12] = new EntityDBFields("pay_employmentperiod","actualEmploymentStartDate","από ημερομηνία",4,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[13] = new EntityDBFields("pay_employmentperiod","actualEmploymentEndDate","εως ημερομηνία",4,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[14] = new EntityDBFields("pay_employmentperiod","employmentPeriodIsDefined","σχέση εργασίας",4,"java.lang.Integer",3, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCTypeOfContract",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");
        employmentperiodDBFields[15] = new EntityDBFields("pay_employmentperiod","employmentPeriodEndDate","εως ημερομηνία",4,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[16] = new EntityDBFields("pay_employmentperiod","contractOfEmploymentId","σύμβαση",4,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[17] = new EntityDBFields("pay_employmentperiod","contractOfEmploymentSpecialId","ειδικότητα σύμβασης",4,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"contractspecialty", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationContractSalarySelect,"");        
        employmentperiodDBFields[18] = new EntityDBFields("pay_employmentperiod","salaryByLaw","νόμιμος μισθός",4,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employmentperiodDBFields[19] = new EntityDBFields("pay_employmentperiod","salaryAgreed","συμφωνηθείς μισθος",4,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");         
        employmentperiodDBFields[20] = new EntityDBFields("pay_employmentperiod","salaryOrWage","μισθός/ημερομίσθιο/ωρομίσθιο",4,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");       
// employmentperiodDBFields[3] = new EntityDBFields("pay_employmentperiod","active","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");            


 /*13*/ employmentperiodDBFields[21] = new EntityDBFields("pay_employmentperiod","typeOfHourOrDaywageOrSalaryId","τύπος εργαζόμενου",5,"java.lang.Integer",5, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCTypeOfSalary",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationTypeOfSalary,"");
        employmentperiodDBFields[22] = new EntityDBFields("pay_employmentperiod","daysPerMonthWorking","ημέρες εργασίας το μήνα",5,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                        
        employmentperiodDBFields[23] = new EntityDBFields("pay_employmentperiod","dailyWage","ημερομίσθιο",5,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,""); 
        employmentperiodDBFields[24] = new EntityDBFields("pay_employmentperiod","hourlyWage","ωρομίσθιο",5,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,""); 
        employmentperiodDBFields[25] = new EntityDBFields("pay_employmentperiod","daysPerWeekWorking","ημέρες εργασίας την εβδομάδα",5,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,""); 
        employmentperiodDBFields[26] = new EntityDBFields("pay_employmentperiod","hoursPerDayWorking","ώρες εργασίας την ημέρα",5,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");      
        employmentperiodDBFields[27] = new EntityDBFields("pay_employmentperiod","hoursPerWeekWorking","ώρες εργασίας την εβδομάδα",5,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        


        
        employmentperiodDBFields[28] = new EntityDBFields("pay_employmentperiod","efkaKadCode","ΕΦΚΑ ΚΑΔ",6,"java.lang.String",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_efkakadFromCompany", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[29] = new EntityDBFields("pay_employmentperiod","efkaEidikotitaCode","ειδικότητα",6,"java.lang.Integer",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_efkaeidikotita", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,null,fieldsCalculationKpk,"");                
        employmentperiodDBFields[30] = new EntityDBFields("pay_employmentperiod","efkaSpecialId","ειδική περίπτωση",6,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[31] = new EntityDBFields("pay_employmentperiod","codeEfkaKpk","κωδικός πακέτου κάλυψης",6,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_efkakpk", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        
        
        


        employmentperiodDBFields[32] = new EntityDBFields("pay_employmentperiod","taxCalcTypeId","τύπος υπολογισμού ΦΜΥ",7,"java.lang.Integer",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCTypeOfTaxCalc", FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1","");        
        employmentperiodDBFields[33] = new EntityDBFields("pay_employmentperiod","taxCalcPercentageIfEnabled","ποσοστό",7,"java.lang.Double",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        

        employmentperiodDBFields[34] = new EntityDBFields("pay_employmentperiod","isCalculateEisforaAllhleguhs","να υπολογίζεται εισφορά αλληλεγγύης",7,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        

        
        
        employmentperiodEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employmentperiodEntityGroupOfComps[1]= new EntityGroupOfComps("εργαζόμενος",2,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employmentperiodEntityGroupOfComps[2]= new EntityGroupOfComps("διάφορα",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employmentperiodEntityGroupOfComps[3]= new EntityGroupOfComps("προυπηρεσία",8,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employmentperiodEntityGroupOfComps[4]= new EntityGroupOfComps("περίοδος εργασίας - σύμβαση",4,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        
        //employmentperiodEntityGroupOfComps[4]= new EntityGroupOfComps("σύμβαση",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employmentperiodEntityGroupOfComps[5]= new EntityGroupOfComps("αμοιβή",8,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //employmentperiodEntityGroupOfComps[6]= new EntityGroupOfComps("κρατήσεις",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employmentperiodEntityGroupOfComps[6]= new EntityGroupOfComps("κρατήσεις",4,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employmentperiodEntityGroupOfComps[7]= new EntityGroupOfComps("Φόρος Μισθωτών Υπηρεσιών",6,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);

        employmentperiodEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        employmentperiodEntityGroupOfPanels[1] = new EntityGroupOfPanels("υπολογιστικά",1);
       //---------------------------------------------------------------------       
       
         //------------------------- 
         
         
             
        contractSpecialDBFields[0] = new EntityDBFields("pay_contractspecialty","contractSpecialtyId","Νο",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");       
        contractSpecialDBFields[1] = new EntityDBFields("pay_contractspecialty","contractId","Νο σύμβασης",0,"java.lang.String",10,FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,""); //uses FIELD_PRIMARY_KEY_FROM_PARENTTABLE, not:VariablesGlobal.globalCompanyId
        //contractSpecialDBFields[2] = new EntityDBFields("pay_contractspecialty","efkaKadId","ΕΦΚΑ ΚΑΔ",0,"java.lang.Integer",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_efkakad", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        contractSpecialDBFields[2] = new EntityDBFields("pay_contractspecialty","description","περιγραφή",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        contractSpecialDBFields[3] = new EntityDBFields("pay_contractspecialty","amount","μισθός",0,"java.lang.Double",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        
         
         String[] selectQueryFieldsContract ={"contractId"};
         payContractDBFields[0] = new EntityDBFields("pay_contract","contractId","Νο",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        // payContractDBFields[1] = new EntityDBFields("pay_contract","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
         payContractDBFields[1] = new EntityDBFields("pay_contract","description","ομομασία",0,"java.lang.String",45,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"","");
        // payContractDBFields[2] = new EntityDBFields("pay_contract","isActive","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");               
         /*payContractDBFields[4] = new EntityDBFields("pay_contract","payContractLaser","",1,"java.lang.String",45000,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         payContractDBFields[5] = new EntityDBFields("pay_contract","payContractDotMatrix","",2,"java.lang.String",45000,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");
         payContractDBFields[6] = new EntityDBFields("pay_contract","imageTop","εικόνα πάνω",3,"java.awt.image.BufferedImage",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");     
         payContractDBFields[7] = new EntityDBFields("pay_contract","imageBottom","εικόνα κάτω",3,"java.awt.image.BufferedImage",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");               
         payContractDBFields[8] = new EntityDBFields("pay_contract","imageBackground","εικόνα πίσω",3,"java.awt.image.BufferedImage",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,null,"");             
        */ 
         payContractDBFields[2] = new EntityDBFields("pay_contract","pay_contractspecialty","ειδικότητα σύμβασης",1,"table",FIELD_VISIBLE_AND_EDITABLE,"pay_contractspecialty",160,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,contractSpecialDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT * FROM pay_contractspecialty "/*WHERE pay_contractspecialty.contractId = #*/+" ORDER BY contractSpecialtyId",null/*selectQueryFieldsContract*/,null,null,null);     //String[] childTableFieldsForSumsIn   
        
        
        payContractEntityGroupOfComps[0] = new EntityGroupOfComps("ιδιότητες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        payContractEntityGroupOfComps[1] = new EntityGroupOfComps("ειδικότητες σύμβασης",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        /*payContractEntityGroupOfComps[2] = new EntityGroupOfComps("εκτύπωση για dot matrix - κρουστικός",2,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE);
        payContractEntityGroupOfComps[3] = new EntityGroupOfComps("εικόνες για laser",2,3,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE);
        */
        
        payContractEntityGroupOfPanels[0] = new EntityGroupOfPanels("ιδιότητες",1);
        //payContractEntityGroupOfPanels[1] = new EntityGroupOfPanels("φορμα laser",1);
        //payContractEntityGroupOfPanels[2] = new EntityGroupOfPanels("φόρμα dot matrix",1);
        //payContractEntityGroupOfPanels[3] = new EntityGroupOfPanels("εικόνες φόρμας",1);
        //-------------------------        
        
           
         //efkaΚadDBFields[0] = new EntityDBFields("pay_efkakad","efkaKadId","Νο",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         efkaΚadDBFields[0] = new EntityDBFields("pay_efkakad","efkaKadCode","κωδικός",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         efkaΚadDBFields[1] = new EntityDBFields("pay_efkakad","description","περιγραφή",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"","");
                   
          efkaΚadEntityGroupOfComps[0] = new EntityGroupOfComps("ιδιότητες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        /*payContractEntityGroupOfComps[1] = new EntityGroupOfComps("εκτύπωση για laser - γραφικά",2,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        payContractEntityGroupOfComps[2] = new EntityGroupOfComps("εκτύπωση για dot matrix - κρουστικός",2,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE);
        payContractEntityGroupOfComps[3] = new EntityGroupOfComps("εικόνες για laser",2,3,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_NOT_VISIBLE);
        */
        
        efkaΚadEntityGroupOfPanels[0] = new EntityGroupOfPanels("ιδιότητες",1);     
        
        //-----------------------------------------------------
         //efkaEidikotitaDBFields[0] = new EntityDBFields("pay_efkaeidikotita","efkaEidikotitaId","Νο",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         efkaEidikotitaDBFields[0] = new EntityDBFields("pay_efkaeidikotita","codeEfkaEidikotita","κωδικός",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         efkaEidikotitaDBFields[1] = new EntityDBFields("pay_efkaeidikotita","description","περιγραφή",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"","");
                   
         efkaEidikotitaEntityGroupOfComps[0] = new EntityGroupOfComps("ιδιότητες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);

         efkaEidikotitaEntityGroupOfPanels[0] = new EntityGroupOfPanels("ιδιότητες",1);         

        
         //-------------------------        
         efkaKpkDBFields[0] = new EntityDBFields("pay_efkakpk","codeEfkaKpk","Νο φόρμας",0,"java.lang.String",10, FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         efkaKpkDBFields[1] = new EntityDBFields("pay_efkakpk","description","περιγραφή",0,"java.lang.String",20,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         //efkaKpkDBFields[2] = new EntityDBFields("pay_efkakpk","efkaKpkName","φόρμα εκτύπωσης",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"","");
         //efkaKpkDBFields[3] = new EntityDBFields("pay_efkakpk","isActive","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");             
         //efkaKpkDBFields[4] = new EntityDBFields("pay_efkakpk","efkaKpkLaser","",1,"java.lang.String",45000,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        
        
        efkaKpkEntityGroupOfComps[0] = new EntityGroupOfComps("ιδιότητες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //efkaKpkEntityGroupOfComps[1] = new EntityGroupOfComps("εκτύπωση για laser - γραφικά",2,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       
        
        efkaKpkEntityGroupOfPanels[0] = new EntityGroupOfPanels("ιδιότητες",1);
        //efkaKpkEntityGroupOfPanels[1] = new EntityGroupOfPanels("φορμα laser",1);

        //-------------------------        
                
         calculationPeriodDBFields[0] = new EntityDBFields("pay_calculationperiod","calculationPeriodId","Νο",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
         //calculationPeriodDBFields[1] = new EntityDBFields("pay_calculationperiod","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_NOT_VISIBLE,VariablesGlobal.globalCompanyId,"");
         calculationPeriodDBFields[1] = new EntityDBFields("pay_calculationperiod","efkaPeriodTypeId","τύπος περιόδου",0,"java.lang.Integer",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCCalculatioType",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"","");
         //calculationPeriodDBFields[3] = new EntityDBFields("pay_calculationperiod","isActive","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");               
         calculationPeriodDBFields[2] = new EntityDBFields("pay_calculationperiod","month","μήνας",0,"java.lang.Integer",6,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_TABLECONSTANTS,"LTCMonths",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         //calculationPeriodDBFields[3] = new EntityDBFields("pay_calculationperiod","year","έτος",0,"java.lang.Integer",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
         calculationPeriodDBFields[3] = new EntityDBFields("pay_calculationperiod","description","περιγραφή",0,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        


        calculationPeriodEntityGroupOfComps[0] = new EntityGroupOfComps("ιδιότητες",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        
        
        calculationPeriodEntityGroupOfPanels[0] = new EntityGroupOfPanels("ιδιότητες",1);
        
        //-------------------------        
       
        payrollPeriodDBFields[0] = new EntityDBFields("pay_payrollperiod","payrollPeriodId","Νo",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //payrollPeriodDBFields[1] = new EntityDBFields("pay_payrollperiod","surname","επίθετο",0,"java.lang.String",20, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,true,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,  false,null);
        payrollPeriodDBFields[1] = new EntityDBFields("pay_payrollperiod","employmentPeriodId","employmentPeriodId",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_employmentperiod",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        payrollPeriodDBFields[2] = new EntityDBFields("pay_payrollperiod","calculationPeriodId","calculationPeriodId",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_calculationperiod", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        payrollPeriodDBFields[3] = new EntityDBFields("pay_payrollperiod","calculationYear","έτος",0,"java.lang.Integer",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        payrollPeriodDBFields[4] = new EntityDBFields("pay_payrollperiod","timePeriodCalculated","ημ/ώρα υπολογισμού",0,"java.lang.String",23,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        payrollPeriodDBFields[5] = new EntityDBFields("pay_payrollperiod","startPayrollDate","από ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        payrollPeriodDBFields[6] = new EntityDBFields("pay_payrollperiod","endPayrollDate","εως ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        //payrollPeriodDBFields[5] = new EntityDBFields("pay_payrollperiod","salaryByLaw","νόμιμος μισθός",0,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //payrollPeriodDBFields[6] = new EntityDBFields("pay_payrollperiod","salaryAgreed","συμφωνηθείς μισθος",0,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");         
       
        payrollPeriodDBFields[7] = new EntityDBFields("pay_payrollperiod","daysWorked","daysWorked",0,"java.lang.Integer",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

       // payrollPeriodDBFields[13] = new EntityDBFields("pay_payrollperiod","endPayrollDate","εως ημερομηνία",4,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        payrollPeriodDBFields[8] = new EntityDBFields("pay_payrollperiod","salaryGross","μικτός μισθός",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //payrollPeriodDBFields[9] = new EntityDBFields("pay_payrollperiod","deductionEmployeePercentage","deductionEmployeePercentage",0,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");         
        //payrollPeriodDBFields[10] = new EntityDBFields("pay_payrollperiod","deductionCompanyPercentage","deductionCompanyPercentage",0,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        payrollPeriodDBFields[9] = new EntityDBFields("pay_payrollperiod","deductionEmployeeAmount","κρατ. εργαζόμενου",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");         
        payrollPeriodDBFields[10] = new EntityDBFields("pay_payrollperiod","deductionCompanyAmount","κρατ. εργοδότη",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        payrollPeriodDBFields[11] = new EntityDBFields("pay_payrollperiod","salaryNet","καθαρά",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");         
        payrollPeriodDBFields[12] = new EntityDBFields("pay_payrollperiod","deductionTotalAmount","σύν. κρατήσεων",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");         
        payrollPeriodDBFields[13] = new EntityDBFields("pay_payrollperiod","taxValue","ΦΜΥ",1,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");         
        /*ayrollPeriodDBFields[3] = new EntityDBFields("pay_payrollperiod","vatNo","Α.Φ.Μ.",0,"java.lang.String",10,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_AFM,FIELD_VISIBLE_AND_EDITABLE,null,"");
        payrollPeriodDBFields[4] = new EntityDBFields("pay_payrollperiod","activityDescr","δραστηριότητα",0,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        //payrollPeriodDBFields[5] = new EntityDBFields("pay_payrollperiod","active","ενεργός",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");                    
        payrollPeriodDBFields[6] = new EntityDBFields("pay_payrollperiod","activityCatId","κατηγορία δραστηριότητας",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"activityCat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        payrollPeriodDBFields[7] = new EntityDBFields("pay_payrollperiod","doyId","Δ.Ο.Υ.",0,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"doy",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        payrollPeriodDBFields[8] = new EntityDBFields("pay_payrollperiod","geoCatId","γεωγραφική κατηγορία",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"geocat",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        */
        
        payrollPeriodEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        payrollPeriodEntityGroupOfComps[1] = new EntityGroupOfComps("αποτελέσματα",12,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        /*payrollPeriodEntityGroupOfComps[2] = new EntityGroupOfComps("επικοινωνία",4,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        payrollPeriodEntityGroupOfComps[3] = new EntityGroupOfComps("χρηματικά",4,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        payrollPeriodEntityGroupOfComps[4] = new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        payrollPeriodEntityGroupOfComps[5] = new EntityGroupOfComps("επαφές",1,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE); // if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
        payrollPeriodEntityGroupOfComps[6] = new EntityGroupOfComps("λογαριασμοί τραπεζών",1,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
        */
        
        
        
        
        payrollPeriodEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        /*payrollPeriodEntityGroupOfPanels[1] = new EntityGroupOfPanels("επικοινωνία",1);
        payrollPeriodEntityGroupOfPanels[2] = new EntityGroupOfPanels("χρηματικά",1);*/        
   }
  
      public void addReportSettings()  // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
    {  
      DataTreeNode nodeCat = null;
      DataTreeNode nodeReports = null;
/*
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
*/      



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
//        ta[0] = new EntityStatistics("statInvoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.valueReturn) AS \"ΕΠΙΣΤΡΟΦΗ\"","FROM invoice",""/*invoice.payrollPeriodId the same because we need where*/,"GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
        
        //EntityStatistics se = new EntityStatistics("invoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'name') AS \"μήνας\" , COUNT(*)AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.returnValue) AS sumret","FROM invoice","WHERE"/*invoice.payrollPeriodId the same because we need where*/,"GROUP BY returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
/*        EntityMenu emta = new EntityMenu();
        emta.setEntityStatistics(ta,ICO_TOOLS);
        emta.setEntityType(ENTITY_TYPE_TOOLS);
        DataTreeNode nodeemta = new DataTreeNode(emta);
        nodeRoot.getChildFromCaption(TOOLS).addChild(nodeemta);
*/
  }
    
   
   public DataTree getDataTree()
   {     
   	return dTree;
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
    
    
    
    
}
