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

public class EntityDataPayroll extends EntityData implements Constants
{
       DataTree dTree;
       DataTreeNode nodeRoot ;
       
            String globalYearPlusOne="";
       int intYearPlusOne=0;  
       
    public static final String REPORT_CAT_1 = "ανάλυση";
    public static final String REPORT_CAT_2 = "λογαριασμοί";
    public static final String REPORT_CAT_3 = "συναλλασόμενοι";
    public static final String REPORT_CAT_4 = "κατάλογοι";           
    
    public static final String SYSTEM_CAT_1 = "εταιρίας, συστήματος";
    public static final String SYSTEM_CAT_2 = "παροχής υπηρεσίας";
    public static final String SYSTEM_CAT_3 = "εσόδων - εξόδων";
    
 
        
        EntityDBFields[] dbCompanyDBFields = new EntityDBFields[25];        
        EntityGroupOfComps[] dbCompanyEntityGroupOfComps= new EntityGroupOfComps[6];
        EntityGroupOfPanels[] dbCompanyEntityGroupOfPanels = new EntityGroupOfPanels[3];
        
        EntityDBFields[] dbyearLineDBFields = new EntityDBFields[5];
        EntityDBFields[] companyDoyKadDBFields = new EntityDBFields[5];    // be carefull  companyDoyKadDBFields and doyKadDBFields seem the same. are NOT
        
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
                    
        EntityDBFields[] comBranchDBFields = new EntityDBFields[5]; 
            

        
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
        
            
      
        EntityDBFields[] employeeDBFields = new EntityDBFields[15];
        EntityGroupOfComps[] employeeEntityGroupOfComps = new EntityGroupOfComps[5];
        EntityGroupOfPanels[] employeeEntityGroupOfPanels = new EntityGroupOfPanels[1];
        
        // same as second query in etityInfo
        //String traderQueryEditable="SELECT trader.traderId AS \"Νο συναλλασσόμενου\", trader.surname AS \"επίθετο\", trader.name AS\"όνομα\", trader.fathername AS \"πατρόνυμο\", trader.traderAfm AS \"Α.Φ.Μ.\", trader.doyId, trader.idNo AS \"αρ ταυτοτ\", trader.townId, trader.address AS \"διέυθυνση\", trader.phone AS \"τηλέφωνο\" FROM trader, town WHERE trader.townId=town.townId";
        String employeeQueryEditable="SELECT * FROM hr_employee";// LEFT JOIN doy ON trader.doyId=doy.doyId";// LEFT JOIN bank ON trader.bankId=bank.bankId";        
        String[] fieldsOnTitleemployee ={"employeeId","lastname","firstname"};
        String[] fieldsOnTitleCaptionemployee  ={"Νο","επίθετο","όνομα"};
        String[] employeeCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueemployee = {"vatNo"};
        //STATS be careful to have in the query all the fields that are also in the title
        EntityPanel entityPanelemployeeDataentry;// = new EntityPanel("ODOR","trader",traderDBFields,traderEntityGroupOfComps,traderEntityGroupOfPanels,"Νο συναλλασσόμενου","","traderId",traderQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniquetrader,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,entReportEsExDoc);      
        EntityPanel entityPanelemployeeHistory;// = new EntityPanel("stattraderHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT trader.traderId, trader.dbCompanyId, sxesoexoheader.esoexoheaderId, sxactiontype.actionTypeCode, sxesoexoheader.esoexoCodeOfDocument,sxesoexoheader.dbYearId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.isPrinted, sxesoexoheader.countTotal,sxesoexoheader.quantityTotal, sxesoexoheader.pricePreVat, sxesoexoheader.priceVat, sxesoexoheader.priceTotal","FROM trader, sxesoexoheader, sxactiontype","WHERE trader.traderId = sxesoexoheader.traderId AND  = sxesoexoheader.dbCompanyId AND sxactiontype.sxActionTypeId = sxesoexoheader.sxActionTypeId AND trader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND trader.traderId =","","ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoCodeOfDocument",false,"",false,"",entityPanelEsex,fieldsOnTitleEsex,fieldsOnTitleCaptionEsex);     
        //EntityPanel entityPaneltraderProducts = new EntityPanel("stattraderProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.traderId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderBuyers = new EntityPanel("stattraderBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.traderId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderSalesPerDate = new EntityPanel("stattraderSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.traderId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelemployee;// = new EntityPanel[] { entityPaneltraderDataentry,entityPaneltraderHistory};//,entityPaneltraderStatistics,entityPaneltraderProducts,entityPaneltraderBuyers,entityPaneltraderSalesPerDate};
        
        //---------------------------------------------------------------- 
        
  
        
        EntityDBFields[] employmentperiodDBFields = new EntityDBFields[33];
        
        // same as second (and the rest) query in etityParameters
        EntityGroupOfComps[] employmentperiodEntityGroupOfComps =new EntityGroupOfComps[3];
        EntityGroupOfPanels[] employmentperiodEntityGroupOfPanels = null;
        
        
        String employmentperiodQueryEditable = "SELECT * FROM pay_employmentperiod";//product.productId AS \"Νο προϊόντος\", product.productName AS \"ονομασία\", product.currencyId FROM product";
        String[] fieldsOnTitleemploymentperiod ={"employmentPeriodId","employeeId"};
        String[] fieldsOnTitleCaptionemploymentperiod  ={"Νο","εργαζόμενος"};      
        String[] employmentperiodCategories = {DATAENTRY,METRICS};
        String[] fieldsUniqueemploymentperiod = null;
        
       EntityCheckFields[] entityCheckFieldsemploymentperiod = null;
      
        
        
        EntityPanel entityPanelemploymentperiodDataentry = new EntityPanel("ODOR","pay_employmentperiod",employmentperiodDBFields,employmentperiodEntityGroupOfComps,employmentperiodEntityGroupOfPanels,"Νo","","employmentPeriodId",employmentperiodQueryEditable,"βασικά στοιχεία",ICO_EDIT16, false, true,fieldsUniqueemploymentperiod,1,UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES,1,UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES,false,false,null,null,null,entityCheckFieldsemploymentperiod,null);  
        //EntityPanel entityPanelProductStatistics = new EntityPanel("statProductHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT dbyear AS \"χρήση\", dbcompany.title AS \"τίτλος συν/σμού\", invoice.deliveryId AS \"αποστολή\", COUNT(*) AS πλήθος, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max","FROM invoice, dbcompany","WHERE invoice.dbCompanyId = dbcompany.dbCompanyId AND invoice.productId=","GROUP BY dbyear, invoice.dbCompanyId, deliveryId","ORDER BY dbyear, dbcompany.title, invoice.deliveryId",false,"",false,"");
        //EntityPanel entityPanelProductTraders = new EntityPanel("statProductTraders","STATS",null,"αγρότες",ICO_STATISTICS16,"SELECT trader.traderId AS \"νο αγρότη\", trader.surname, trader.name, trader.fatherName,trader.traderAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, trader","WHERE invoice.traderId = trader.traderId AND invoice.productId=","GROUP BY trader.traderId","ORDER BY trader.surname, trader.name, trader.fatherName,trader.traderAfm",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductBuyers = new EntityPanel("statProductBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.productId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPanelProductSalesPerDate = new EntityPanel("statProductSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\",returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.ProductId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        EntityPanel[] entityPanelService = new EntityPanel[] { entityPanelemploymentperiodDataentry};//,entityPanelProductStatistics,entityPanelProductTraders,entityPanelProductBuyers,entityPanelProductSalesPerDate};
        
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
     entities.add(entityLookUp = new EntityLookUp("pay_doykad","pay_doykad","SELECT doyKadId AS\"Νο\",kadCode AS\"κωδικός\", description AS\"περιγραφή\" FROM pay_doykad","","","ORDER BY kadCode","","doyKadId","Νο","doyKadId","ΔΟΥ ΚΑΔ",2,lookUpFieldDoyKad,"ΔΟΥ ΚΑΔ",10,"java.lang.String",0,null,null,0,null,null,doyKadQueryEditable,"ΔΟΥ ΚΑΔ","ΔΟΥ ΚΑΔ",null,entityPanelDoyKad,fieldsOnTitleDoyKad, fieldsOnTitleCaptionDoyKad,null,2,1,null,true,-1,-1,null)); 
     //entities.add(entityLookUp = new EntityLookUp("paymenttype","SELECT paymentTypeId AS\"Νο τύπου παρ/κού\",paymentTypeName AS\"ονομασία τύπου παρ/κού\", abbreviation AS\"συντομογραφία\" FROM paymenttype","ORDER BY paymentTypeName","paymentTypeId","Νο τύπου παρ/κού",2,lookUpFieldPaymentType,"είδος παραστατικού",0,null,null,0,null,null,paymenttypeQueryEditable,"τύπου παραστατικού","τύπων παραστατικού",null,entityPanelPaymentType,fieldsOnTitlePaymentType, fieldsOnTitleCaptionPaymentType,null,2,1,null,true,-1,-1)); 

    //---------------------------------------------------------------
    
      EntityFilterSettings[] employeeErs = new EntityFilterSettings[3];       
       //traderErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[0]=new EntityFilterSettings("όνομα","","string","equals","title","hr_employee",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","hr_employee",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[3]=new EntityFilterSettings("ταυτότητα","","string","equals","idNo","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","trader","",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","hr_employee","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[3]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[4]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfEsoexo","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
     
     String[] lookUpFieldemployee={"lastname","firstname","vatNo"};
            
     entities.add(entityLookUp = new EntityLookUp("hr_employee","hr_employee","SELECT hr_employee.employeeId AS\"Νο\", lastname AS\"επίθετο\", firstname AS\"όνομα\",  vatNo AS\"Α.Φ.Μ.\", amkaNo AS\"ΑMKA\" FROM hr_employee","WHERE hr_employee.isActive LIKE 1","AND hr_employee.isActive LIKE 1", "ORDER BY hr_employee.lastname","","employeeId","Νο","employeeId","εργαζόμενος",3,lookUpFieldemployee,"επίθετο ή όνομα ή ΑΦΜ",29,"java.lang.String",4,"vatNo", "Α.Φ.Μ.",0,null,null,employeeQueryEditable, "εργαζόμενου","εργαζόμενων",employeeCategories,entityPanelemployee,fieldsOnTitleemployee,fieldsOnTitleCaptionemployee,employeeErs,2,2,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));

      //entities.add(entityLookUp = new EntityLookUp("trader1Col","trader","SELECT trader.traderId AS\"Νο συναλλασσόμενου\", traderCode AS\"κωδικός\", title AS\"επωνυμία\",  vatNo AS\"Α.Φ.Μ.\" FROM trader","WHERE trader.active LIKE 1","AND trader.active LIKE 1", "ORDER BY trader.title","","traderId","Νο συναλλασσόμενου","traderId","συναλλασσόμενος",2,lookUpFieldtrader,"επωνυμία ή ΑΦΜ",29,"java.lang.String",0,null,null,0,null,null,traderQueryEditable, "συναλλασσόμενου","συναλλασομένων",strtraderCategories,entityPaneltrader,fieldsOnTitletrader,fieldsOnTitleCaptiontrader,traderErs,2,1,ICO_FARMER16,true,3,FIELD_VALIDATION_AFM,null));
     
      //-------------------------------------------------------------------
     
     
     
           return entities;
   } 
   
    public ArrayList addEntitiesLookupTableConstants(ArrayList  <EntityLookupTableConstants> listEntityLookupTableConstants)
   {
       
       return listEntityLookupTableConstants;
     
   }
    
    
    
     public void addEntitiesParameters()  // do not add ORDER BY to second sql because DialogPrinterSettings will have problem
     {  

        //--------------------------------------------- doyKad
        int[] doyKadFieldsOrderby ={2};
        String[] fieldsForSumsDoyKad=null;//                                in query: because it has the same table 2 times, we use the 1st table as it is, in order to be queried correct in title fields
        EntityParameter pqc = new EntityParameter("pay_doykad", "SELECT doyKadId AS\"Νο\", kadCode AS\"κωδικός\" , description AS\"περιγραφή\" FROM pay_doykad ORDER BY doyKadId","SELECT doyKadId AS\"Νο\", kadCode AS\"κωδικός\" , description AS\"περιγραφή\"","FROM pay_doykad","",fieldsForSumsDoyKad,doyKadDBFields ,"ΚΑΔ εφορίας","DORM","Νο","doyKadId",null,null,"ΚΑΔ εφορίας", "ΚΑΔ εφορίας",entityPanelDoyKad,null,fieldsOnTitleDoyKad,fieldsOnTitleCaptionDoyKad,doyKadFieldsOrderby,-1,-1,globalYearPlusOne);
        EntityMenu empqc = new EntityMenu();
        empqc.setEntityParameter(pqc,ICO_TABLE16);
        empqc.setEntityType(ENTITY_TYPE_PARAMETER);
        DataTreeNode nodeempc = new DataTreeNode(empqc);
        nodeRoot.getChildFromCaption(PARAMETERS).addChild(nodeempc);         

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
       //traderErs[0]=new EntityFilterSettings("επίθετο","","string","equals","surname","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[0]=new EntityFilterSettings("όνομα","","string","equals","title","hr_employee",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[1]=new EntityFilterSettings("ΑΦΜ","","string","equals","vatNo","hr_employee",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[3]=new EntityFilterSettings("ταυτότητα","","string","equals","idNo","trader",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[4]=new EntityFilterSettings("πόλη/χωριό","checkboxTable","string","","townId","town","trader","",-1,-1,-1,FIELD_NOCOMPLETION);
       employeeErs[2]=new EntityFilterSettings("Δ.Ο.Υ.","checkboxTable","string","","doyId","doy","hr_employee","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[3]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
       //traderErs[4]=new EntityFilterSettings("ημερομηνία","","date","fromto","dateOfEsoexo","","sxesoexoheader","",-1,-1,-1,FIELD_NOCOMPLETION);
                             //onelookup,checkboxTable
       EntityGroupOfComps[] employeeFilterEntityGroupOfComps = null;
       
       int[] employeeFieldsOrderBy ={2,3,4,5};
        // if fields change, change them and at lookup entities
       String[] fieldsForSumsemployee = null;//{"ποσότητα","προ φπα", "φπα","σύνολο"};
       // , sum(quantityTotal) AS \"ποσότητα\", sum(pricePreVat) AS \"προ ΦΠΑ\", sum(priceVat) AS \"ΦΠΑ\", sum(priceTotal) AS \"σύνολο\"    LEFT JOIN sxesoexoheader ON sxesoexoheader.traderId = trader.traderId
       EntityInfo ia = new EntityInfo("hr_employee", "SELECT hr_employee.employeeId AS \"Νο\", hr_employee.lastname AS \"επίθετο\",hr_employee.firstname  AS \"όνομα\", hr_employee.fathername  AS \"πατρόνυμο\", hr_employee.mothername  AS \"όνομα μητέρας\",hr_employee.vatNo AS \"Α.Φ.Μ.\",hr_employee.dateOfBirth AS \"ημ. γέννησης\", "+/* hr_employee.doyId AS \"Νο Δ.Ο.Υ.\",*/"  hr_employee.isActive AS \"ενεργός/η\"  FROM hr_employee "+/*LEFT JOIN doy ON trader.doyId=doy.doyId*/ " ORDER BY hr_employee.lastname" ,"SELECT hr_employee.employeeId AS \"Νο\", hr_employee.lastname AS \"επίθετο\",hr_employee.firstname  AS \"όνομα\", hr_employee.fathername  AS \"πατρόνυμο\", hr_employee.mothername  AS \"όνομα μητέρας\",hr_employee.vatNo AS \"Α.Φ.Μ.\",hr_employee.dateOfBirth AS \"ημ. γέννησης\", "+/* hr_employee.doyId AS \"Νο Δ.Ο.Υ.\",*/"  hr_employee.isActive AS \"ενεργός/η\"","FROM hr_employee"/* LEFT JOIN doy ON trader.doyId=doy.doyId "LEFT JOIN town ON trader.townId=town.townId"*/," ",null, fieldsForSumsemployee ,"εργαζόμενοι","DORM","","Νο","employeeId"/*,"",""*/,employeeErs,employeeFilterEntityGroupOfComps ,"εργαζόμενου","εργαζόμενων",employeeCategories,entityPanelemployee,fieldsOnTitleemployee,fieldsOnTitleCaptionemployee,employeeFieldsOrderBy,3,FIELD_VALIDATION_AFM,null,globalYearPlusOne);
        EntityMenu emia = new EntityMenu();
        emia.setEntityInfo(ia,ICO_FARMER16);
        emia.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeemia = new DataTreeNode(emia);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeemia);

     //------------------------------------------------------------   
          
        EntityFilterSettings[] employmentperiodErs = new EntityFilterSettings[1];       
        //employmentperiodErs[0]=new EntityFilterSettings("ονομασία","","string","equals","descr","pay_employmentperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        employmentperiodErs[0]=new EntityFilterSettings("τύπος","checkboxTable","string","","employeeId","hr_employee","pay_employmentperiod","",-1,-1,-1,FIELD_NOCOMPLETION);
        //employmentperiodErs[2]=new EntityFilterSettings("χρήση","checkboxTable","string","","dbYearId","dbyear","saleline","",-1,-1,-1,FIELD_NOCOMPLETION);
       EntityGroupOfComps[] employmentperiodErsEntityGroupOfComps = null;
        
        int[] employmentperiodFieldsOrderby ={2};
        
        String[] fieldsForSumsemploymentperiod ={"ποσότητα","προ φπα", "φπα","σύνολο"};
        
        EntityInfo pf = new EntityInfo("pay_employmentperiod", "SELECT pay_employmentperiod.employmentPeriodId AS \"Νο\", pay_employmentperiod.employeeId, companyBranchId, actualEmploymenStartDate, actualEmploymentEndDate FROM pay_employmentperiod WHERE pay_employmentperiod.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY pay_employmentperiod.employmentPeriodId"  ,"SELECT pay_employmentperiod.employmentPeriodId AS \"Νο\"","FROM pay_employmentperiod","WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId ,null,fieldsForSumsemploymentperiod,"περίοδος εργασίας","DORM","","Νο","pay_employmentPeriodId",/*"","",*/employmentperiodErs,employmentperiodErsEntityGroupOfComps,"περίοδος εργασίας","περιόδων εργασίας",employmentperiodCategories,entityPanelService,fieldsOnTitleemploymentperiod,fieldsOnTitleCaptionemploymentperiod,employmentperiodFieldsOrderby,-1,-1,null,globalYearPlusOne);
        EntityMenu empf = new EntityMenu();
        empf.setEntityInfo(pf,ICO_TABLE16);
        empf.setEntityType(ENTITY_TYPE_DATAENTRY);
        DataTreeNode nodeempf = new DataTreeNode(empf);
        nodeRoot.getChildFromCaption(DATAENTRY).addChild(nodeempf);
        
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
        //entityPanelemployeeHistory = new EntityPanel("stattraderHistory","STATS",null,"ιστορικό",ICO_STATISTICS16,"SELECT sxesoexoheader.traderId, sxesoexoheader.esoexoheaderId, sxactiontype.actionTypeCode, sxesoexoheader.esoexoCodeOfDocument,sxesoexoheader.dbYearId, sxesoexoheader.dateOfEsoexo, sxesoexoheader.isPrinted, sxesoexoheader.countTotal, sxesoexoheader.pricePreVat, sxesoexoheader.priceVat, sxesoexoheader.priceTotal, sxesoexoheader.isTemplate","FROM sxesoexoheader LEFT JOIN sxactiontype ON sxesoexoheader.sxActionTypeId = sxactiontype.sxActionTypeId","WHERE sxesoexoheader.isTemplate ='0'  AND sxesoexoheader.dbCompanyId = sxactiontype.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND sxesoexoheader.traderId =","","ORDER BY sxesoexoheader.dateOfEsoexo, sxesoexoheader.esoexoCodeOfDocument",false,"",false,"",entityPanelSale,fieldsOnTitleSale,fieldsOnTitleCaptionSale);     
        //EntityPanel entityPaneltraderProducts = new EntityPanel("stattraderProducts","STATS",null,"καλλιέργιες",ICO_STATISTICS16,"SELECT product.productId AS \"Νο προϊόντος\", product.productName AS \"προϊόν\",  COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, product","WHERE invoice.productId = product.productId AND invoice.traderId=","GROUP BY product.productId","ORDER BY product.productName",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderBuyers = new EntityPanel("stattraderBuyers","STATS",null,"αγοραστές",ICO_STATISTICS16,"SELECT buyer.buyerId AS \"νο αγοραστή\", buyer.buyerTitle,buyer.buyerAfm, COUNT(*) AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\"","FROM invoice, buyer","WHERE invoice.buyerId = buyer.buyerId AND invoice.traderId=","GROUP BY buyer.buyerId","ORDER BY buyer.buyerTitle",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        //EntityPanel entityPaneltraderSalesPerDate = new EntityPanel("stattraderSalesPerDate","STATS",null,"πωλήσεις ανα μήνα",ICO_STATISTICS16,"SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\"","FROM invoice","WHERE invoice.traderId=","GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear");
        entityPanelemployee = new EntityPanel[] { entityPanelemployeeDataentry};//,entityPanelemployeeHistory};//,entityPaneltraderStatistics,entityPaneltraderProducts,entityPaneltraderBuyers,entityPaneltraderSalesPerDate};
            
         //---------------------------------------------------------   
          
        dbyearLineDBFields[0] = new EntityDBFields("dbyear","dbYearId","Νο χρήσης",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");       
        dbyearLineDBFields[1] = new EntityDBFields("dbyear","dbCompanyId","Νο εταιρίας",0,"java.lang.String",4,FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,""); //uses FIELD_PRIMARY_KEY_FROM_PARENTTABLE, not:VariablesGlobal.globalCompanyId
        dbyearLineDBFields[2] = new EntityDBFields("dbyear","dbYearDescr","περιγραφή χρήσης",0,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        dbyearLineDBFields[3] = new EntityDBFields("dbyear","dbDateFrom","από ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        dbyearLineDBFields[4] = new EntityDBFields("dbyear","dbDateTo","εως ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
//dbyearDBFields[3] = new EntityDBFields("dbyear","yearDelivery","αποστολές του έτους",1,"table",FIELD_VISIBLE_AND_EDITABLE,"dbYearDelivery",200,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,dbYearDeliveryDBFields,FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,"SELECT dbYearDeliveryId, dbYearId,aa,description,dbCompanyId FROM dbYearDelivery WHERE dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+"  ORDER BY aa",null);
        
        
        companyDoyKadDBFields[0] = new EntityDBFields("pay_companydoykad","id","Νο",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");       
        companyDoyKadDBFields[1] = new EntityDBFields("pay_companydoykad","dbCompanyId","Νο εταιρίας",0,"java.lang.String",4,FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,""); //uses FIELD_PRIMARY_KEY_FROM_PARENTTABLE, not:VariablesGlobal.globalCompanyId
        companyDoyKadDBFields[2] = new EntityDBFields("pay_companydoykad","doyKadId","ΚΑΔ εφορίας",0,"java.lang.Integer",11,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_doykad", FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        companyDoyKadDBFields[3] = new EntityDBFields("pay_companydoykad","affectStartDate","από ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        companyDoyKadDBFields[4] = new EntityDBFields("pay_companydoykad","affectEndDate","εως ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
          
        comBranchDBFields[0] = new EntityDBFields("hr_companybranch","branchId","Νο",0,"java.lang.Integer",15, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");       
        comBranchDBFields[1] = new EntityDBFields("hr_companybranch","dbCompanyId","Νο εταιρίας",0,"java.lang.String",4,FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,""); //uses FIELD_PRIMARY_KEY_FROM_PARENTTABLE, not:VariablesGlobal.globalCompanyId
        comBranchDBFields[2] = new EntityDBFields("hr_companybranch","branchTitle","τίτλος",0,"java.lang.String",18,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        comBranchDBFields[3] = new EntityDBFields("hr_companybranch","dateEstablished","από ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        comBranchDBFields[4] = new EntityDBFields("hr_companybranch","datePaused","εως ημερομηνία",0,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
          
           
        String[] updateQueryFieldsCompany ={"dbCompanyId"};

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
       	dbCompanyDBFields[16] = new EntityDBFields("dbcompany","bankAccount","λογαριασμός",2,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	dbCompanyDBFields[17] = new EntityDBFields("dbcompany","bankAccountIBAN","ΙΒΑΝ",2,"java.lang.String",35,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        
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
        dbCompanyDBFields[23] = new EntityDBFields("dbcompany","pay_companydoykad","ΚΑΔ εφορίας",4,"table",FIELD_VISIBLE_AND_EDITABLE,"pay_companydoykad",160,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,companyDoyKadDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM pay_companydoykad WHERE pay_companydoykad.dbCompanyId = # ORDER BY id",selectQueryFieldsCompany,null,null,null);     //String[] childTableFieldsForSumsIn   
        dbCompanyDBFields[24] = new EntityDBFields("dbcompany","dbyear","χρήσεις",5,"table",FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,"dbyear",130,CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE,dbyearLineDBFields,FIELD_TABLE_NOROWCOMPLETION,"SELECT * FROM dbyear WHERE dbCompanyId = # ORDER BY dbyearId",selectQueryFieldsCompany,null,null,null);     //String[] childTableFieldsForSumsIn   
        
        //dbCompanyDBFields[22] = new EntityDBFields("dbcompany","afmTaxisUsername","χρήστης",7,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        ////dbCompanyDBFields[23] = new EntityDBFields("dbcompany","afmTaxisPassword","κωδικός",7,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        dbCompanyEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[1]= new EntityGroupOfComps("διεύθυνση",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[2]= new EntityGroupOfComps("λογαριασμός τράπεζας",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[3]= new EntityGroupOfComps("βασικά μισθοδοσίας",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //dbCompanyEntityGroupOfComps[5]= new EntityGroupOfComps("δεκαδικά (Χρειάζεται να εισέλθετε ξανα στην εφαρμογή για να εφαρμοσθούν οι αλλαγές.)",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[4] = new EntityGroupOfComps("ρυθμίσεις μισθοδοσίας",1,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //dbCompanyEntityGroupOfComps[5] = new EntityGroupOfComps("ΚΑΔ εφορίας",4,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        dbCompanyEntityGroupOfComps[5] = new EntityGroupOfComps("χρήσεις",4,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);

        //dbCompanyEntityGroupOfComps[7] = new EntityGroupOfComps("κωδικοί αναζήτης ΑΦΜ",4,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        
        
        dbCompanyEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        dbCompanyEntityGroupOfPanels[1] = new EntityGroupOfPanels("μισθοδοσία",1);        
        dbCompanyEntityGroupOfPanels[2] = new EntityGroupOfPanels("χρήσεις",1);  
        
        
        
        doyKadDBFields[0] = new EntityDBFields("pay_doykad","doyKadId","Νο τρόπου πληρωμής",0,"java.lang.Integer",1, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
       	doyKadDBFields[1] = new EntityDBFields("pay_doykad","kadCode","κωδικός",0,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        doyKadDBFields[2] = new EntityDBFields("pay_doykad","description","ονομασία",0,"java.lang.String",40,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

        doyKadEntityGroupOfComps[0] = new EntityGroupOfComps("",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        
       doyKadEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1); 
       
    //----------------------------------------------
          
    
        EntityDBFields[] employeeIdNoDBFields = new EntityDBFields[8];
        
        employeeIdNoDBFields[0] = new EntityDBFields("hr_employeeidtype","id","id",0,"java.lang.Integer",5,FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        employeeIdNoDBFields[1] = new EntityDBFields("hr_employeeidtype","employeeId","Νο εργαζόμενου",0,"java.lang.String",6, FIELD_PRIMARY_KEY_FROM_PARENTTABLE,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //employeeIdNoDBFields[2] = new EntityDBFields("hr_employeeidtype","inc","αα",0,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //employeeIdNoDBFields[3] = new EntityDBFields("tradercontact","dbCompanyId","dbCompanyId",0,"java.lang.String",6,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
        
        employeeIdNoDBFields[2] = new EntityDBFields("hr_employeeidtype","idTypeId","τύπος",0,"java.lang.Integer",11,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"idtype",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeIdNoDBFields[3] = new EntityDBFields("hr_employeeidtype","no","αριθμός",0,"java.lang.String",13,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeIdNoDBFields[4] = new EntityDBFields("hr_employeeidtype","issuer","υπηρεσία έκδοσης",0,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeIdNoDBFields[5] = new EntityDBFields("hr_employeeidtype","countryIdIssued","countryIdIssued",0,"java.lang.Integer",11,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeIdNoDBFields[6] = new EntityDBFields("hr_employeeidtype","dateIssued","ημερομηνία έκδοσης",0,"java.sql.Date",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employeeIdNoDBFields[7] = new EntityDBFields("hr_employeeidtype","dateExpire","ημερομηνία λήξης",0,"java.sql.Date",12,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
    
    
    
        employeeDBFields[0] = new EntityDBFields("hr_employee","employeeId","Νο",0,"java.lang.Integer",5, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");
        //employeeDBFields[1] = new EntityDBFields("trader","surname","επίθετο",0,"java.lang.String",20, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,true,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,  false,null);
        //employeeDBFields[1] = new EntityDBFields("trader","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,VariablesGlobal.globalCompanyId);
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
        employeeDBFields[9] = new EntityDBFields("hr_employee","bankId","τράπεζα",0,"java.lang.Integer",0,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"bank",FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        employeeDBFields[10] = new EntityDBFields("hr_employee","telephone1","τηλέφωνο 1",2,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[11] = new EntityDBFields("hr_employee","telephone2","τηλέφωνο 2",2,"java.lang.String",15,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[12] = new EntityDBFields("hr_employee","email","email",2,"java.lang.String",25,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        
        
        
/*        employeeDBFields[12] = new EntityDBFields("hr_employee","addressState","νομός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[10] = new EntityDBFields("hr_employee","addressCity","πόλη/χωριό",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[9] = new EntityDBFields("hr_employee","addressStreet","οδός",1,"java.lang.String",30,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employeeDBFields[11] = new EntityDBFields("hr_employee","addressPC","ΤΚ",1,"java.lang.String",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
*/       
                
        //employeeDBFields[19] = new EntityDBFields("hr_employee","paymenttypeId","τρόπος πληρωμής",2,"java.lang.Integer",3,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"paymenttype",FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");

        employeeDBFields[13] = new EntityDBFields("hr_employee","notes","σημειώσεις",3,"java.lang.String",220,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");


        employeeDBFields[14] = new EntityDBFields("hr_employee","hr_employeeidtype","ταυτότητα",4,"table",FIELD_VISIBLE_AND_EDITABLE,"hr_employeeidtype",95,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,employeeIdNoDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM hr_employeeidtype ORDER BY hr_employeeidtype.id",null,null,null,null);        
        
        //traderDBFields[22] = new EntityDBFields("trader","bank accounts","λογαριασμοί τραπεζών",5,"table",FIELD_VISIBLE_AND_EDITABLE,"traderbankaccount",120,CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL,traderBankaccountDBFields,FIELD_TABLE_ONEROWATLEAST_SUGGEST,"SELECT * FROM traderbankaccount ORDER BY traderbankaccount.inc",null,null,null,null);        
        
        
        
        
        employeeEntityGroupOfComps[0] = new EntityGroupOfComps("βασικά",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employeeEntityGroupOfComps[1] = new EntityGroupOfComps("διεύθυνση",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employeeEntityGroupOfComps[2] = new EntityGroupOfComps("επικοινωνία",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employeeEntityGroupOfComps[3] = new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employeeEntityGroupOfComps[4] = new EntityGroupOfComps("ταυτότητα",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       // employeeEntityGroupOfComps[4] = new EntityGroupOfComps("επαφές",1,1,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE); // if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
       // employeeEntityGroupOfComps[5] = new EntityGroupOfComps("λογαριασμοί τραπεζών",1,2,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);// if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
        
        
        
        
        
        employeeEntityGroupOfPanels[0] = new EntityGroupOfPanels("βασικά",1);
        //traderEntityGroupOfPanels[1] = new EntityGroupOfPanels("επικοινωνία",1);
        //traderEntityGroupOfPanels[2] = new EntityGroupOfPanels("χρηματικά",1);
       
        //-------------------------------------------
       
        
        employmentperiodDBFields[0] = new EntityDBFields("pay_employmentperiod","employmentPeriodId","Νο",0,"java.lang.Integer",3, FIELD_PRIMARY_KEY_AUTOINC,LOOKUPTYPE_NOLOOKUP,null,FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_NOT_EDITABLE_ALWAYS,null,"");        
        employmentperiodDBFields[1] = new EntityDBFields("pay_employmentperiod","dbCompanyId","dbCompanyId",0,"java.lang.String",10,FIELD_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,VariablesGlobal.globalCompanyId,"");
        employmentperiodDBFields[2] = new EntityDBFields("pay_employmentperiod","employeeId","εργαζόμενος",1,"java.lang.Integer",5,FIELD_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"hr_employee", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[3] = new EntityDBFields("pay_employmentperiod","actualEmploymenStartDate","από ημερομηνία",2,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[4] = new EntityDBFields("pay_employmentperiod","actualEmploymentEndDate","εως ημερομηνία",2,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        

       // employmentperiodDBFields[3] = new EntityDBFields("pay_employmentperiod","active","ενεργή",0,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"true","");            

        employmentperiodDBFields[5] = new EntityDBFields("pay_employmentperiod","experienceOnTheSameEmployerYears","χρόνια προθπηρεσίας στον ίδιο εργοδότη",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[6] = new EntityDBFields("pay_employmentperiod","experienceOnTheSameEmployerMonths","μήνες προυπηρεσίας στον ίδιο εργοδότη",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        employmentperiodDBFields[7] = new EntityDBFields("pay_employmentperiod","experienceAllEmployersYears","χρόνια προθπηρεσίας γενικά",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[8] = new EntityDBFields("pay_employmentperiod","experienceAllEmployersMonths","μήνες προυπηρεσίας γενικά",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
              
       
        employmentperiodDBFields[9] = new EntityDBFields("pay_employmentperiod","employmentPeriodIsDefined","ορισμένου/αορίστου",2,"java.lang.Integer",3, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"vatcat",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employmentperiodDBFields[10] = new EntityDBFields("pay_employmentperiod","employmentPeriodEndDate","εως ημερομηνία",2,"java.sql.Date",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[11] = new EntityDBFields("pay_employmentperiod","contractOfEmploymentId","σύμβαση",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[12] = new EntityDBFields("pay_employmentperiod","contractOfEmploymentSpecialId","ειδικότητα σύμβασης",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[13] = new EntityDBFields("pay_employmentperiod","salaryByLaw","νόμιμος μισθός",2,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employmentperiodDBFields[14] = new EntityDBFields("pay_employmentperiod","salaryAgreed","συμφωνηθείς μισθος",2,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,""); 
        
        employmentperiodDBFields[15] = new EntityDBFields("pay_employmentperiod","typeOfHourOrDaywageOrSalaryId","μισθώτός/ημερομίσθιος/ωρομίσθιος",2,"java.lang.Integer",3, FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"vatcat",FIELD_OBLIGATORY,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        
        employmentperiodDBFields[16] = new EntityDBFields("pay_employmentperiod","montlySalary","μισθός",2,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");
        employmentperiodDBFields[17] = new EntityDBFields("pay_employmentperiod","dailyWage","ημερομίσθιο",2,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,""); 
        employmentperiodDBFields[18] = new EntityDBFields("pay_employmentperiod","hourlyWage","ωρομίσθιο",2,"java.lang.Double",8,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,"1",""); 
        
        employmentperiodDBFields[19] = new EntityDBFields("pay_employmentperiod","efkaKadId","ΚΑΔ ΕΦΚΑ",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[20] = new EntityDBFields("pay_employmentperiod","efkaEidikotitaId","ειδικότητα",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        employmentperiodDBFields[21] = new EntityDBFields("pay_employmentperiod","efkaSpecialId","ειδική περίπτωση",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[22] = new EntityDBFields("pay_employmentperiod","efkaKpkId","κωδικός πακέτου κάλυψης",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        
        employmentperiodDBFields[23] = new EntityDBFields("pay_employmentperiod","employmentYekaSpecialtyId","κωδικός ειδικότητας ΥΕΚΑ",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        
        employmentperiodDBFields[24] = new EntityDBFields("pay_employmentperiod","daysPerWeekWorking","ημέρες εργασίας την εβδομάδα",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[25] = new EntityDBFields("pay_employmentperiod","daysPerMonthWorking","ημέρες εργασίας το μήνα",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        employmentperiodDBFields[26] = new EntityDBFields("pay_employmentperiod","hoursPerWeekWorking","ώρες εργασίας την εβδομάδα",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[27] = new EntityDBFields("pay_employmentperiod","hoursPerDayWorking","ώρες εργασίας την ημέρα",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        
        employmentperiodDBFields[28] = new EntityDBFields("pay_employmentperiod","workingTimeId","ωράριο εργασίας",2,"java.lang.Integer",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_ONLYONE_THISFIELD,"pay_contract", FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        


        employmentperiodDBFields[29] = new EntityDBFields("pay_employmentperiod","isCalculateEisforaAllhleguhs","να υπολογίζεται εισφορά αλληλεγγύης",2,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[30] = new EntityDBFields("pay_employmentperiod","efkaPlhresOrario","πλήρες ωράριο",2,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
        employmentperiodDBFields[31] = new EntityDBFields("pay_employmentperiod","efkaOlesErgasimes","όλες οι ημέρες εργάσιμες",2,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");        
        employmentperiodDBFields[32] = new EntityDBFields("pay_employmentperiod","efkaKuriakes","εργασία τις Κυριακές",2,"java.lang.Boolean",5,FIELD_NORMAL_NO_PRIMARY_KEY,LOOKUPTYPE_NOLOOKUP,null, FIELD_SUGGEST,FIELD_VALIDATION_NO,FIELD_VISIBLE_AND_EDITABLE,null,"");                
                 
        
        
        employmentperiodEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employmentperiodEntityGroupOfComps[1]= new EntityGroupOfComps("εργαζόμενος",2,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        employmentperiodEntityGroupOfComps[2]= new EntityGroupOfComps("κύρια",4,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
        //employmentperiodEntityGroupOfComps[3]= new EntityGroupOfComps("σημειώσεις",1,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
   
        

       //---------------------------------------------------------------------       
       
       
       
       
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
//        ta[0] = new EntityStatistics("statInvoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'no') AS \"ΝΟ\", returnMonth(date, 'name') AS \"ΜΗΝΑΣ\" , COUNT(*)AS \"ΠΛΗΘΟΣ\", SUM(invoice.value) AS \"ΣΥΝΟΛΟ\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.valueReturn) AS \"ΕΠΙΣΤΡΟΦΗ\"","FROM invoice",""/*invoice.traderId the same because we need where*/,"GROUP BY returnMonth(date, 'no'),returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
        
        //EntityStatistics se = new EntityStatistics("invoicespermonth","invoice","παραστατικά ανα μήνα","SELECT returnMonth(date, 'name') AS \"μήνας\" , COUNT(*)AS \"πλήθος\", SUM(invoice.value) AS \"σύνολο\", AVG(invoice.value) AS \"Μ.Ο.\",SUM(invoice.returnValue) AS sumret","FROM invoice","WHERE"/*invoice.traderId the same because we need where*/,"GROUP BY returnMonth(date, 'name')","ORDER BY returnMonth(date, 'no')",true,"invoice.dbCompanyId",true,"invoice.dbyear",null,null);
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
