
 package com.tool.utils;
 import java.awt.*;
 
public class VariablesGlobal 
{
    public static String globalUserId;

    public static String globalYearDescr;
    public static String globalYearId;
    public static String globalDateFrom;
    public static String globalDateTo;
  
    public static String globalCompanyId;
    public static String globalCompanyName;
    public static String globalDate;
    public static String globalDirConfiguration;
    public static String globalSystemDirectorySymbol=System.getProperty("file.separator");	
    public static String globalFilePassForDb;
    
    public static boolean globalShowSQL=false;
    public static boolean globalShowReadDatabaseSQL =false ; 
    public static boolean globalShowReadSQLRow =false ; // from Database class
    
    public static boolean globalShowStatisticsSQL=false;
    public static boolean globalShowSQLEdit=true;
    public static boolean globalShowInitialisations=false;
    public static boolean globalShowAnalyticErrorMessages=true;
    public static boolean globalShowReleaseOfRS=false;
    public static boolean globalShowReleaseOfRSMD=false;
    public static boolean globalShowMsgOnError=true;
    public static boolean globalShowPrintStackTrace=true;
    
    public static boolean globalShowFrameRedirected=true;

    public static boolean globalShowSelectRecord=false;
   public static boolean globalShowSelectUtilPanelReportRecord=false;//   -t--ooo--t--UtilsPanelReport.getLookupValue  
   public static boolean globalShowSelectDataFromWritable=false;//   OOOO PanelOneDataManyRecData.retrieveDataFromWritable
   public static boolean globalShowReportSQL=false;
    
    public static int jtableEditableClickCountToStart=1;
    
    public static UtilsDouble globalUtilsDouble;
    
    public static boolean globalFieldIsVisibleWhenSetNotVisible=false; // true= show, false= do not show
   
    
    public static String globalformGlobalVariable1="";

    public static String columnNameInc="inc"; //  incremental number   aa   or   date on invoices

    public static  String appName = "BusinessEye";
    public static  String appUseName = "(χρήση μόνο για εκπαιδευτικούς σκοπούς)";
    public static  String appLeadVersion = "1";
    public static  String appSubVersion = "1.2604";
    public static  String appVersionYear = "2020/09";
    public static  String appProduct = "0"; // 0 all
    //public static  String appProductCaption = "";
    //public static  String appShowLogFrame = "0"; // 1 yes, 0 no
    //public static  String appRunParam2 = "0"; // 1 yes, 0 no

    
    public VariablesGlobal() 
    {   
    

    
    }

}
