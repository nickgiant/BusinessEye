// created 20-09-2006
package com.tool.utils;

  import com.tool.guicomps.*;
  import com.tool.gui.*;
import com.tool.jdbc.Database;
  
  import java.text.DateFormat;
  import java.text.SimpleDateFormat;
  import java.text.ParseException;
 
  import java.io.FileInputStream;
  import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
  
  import java.util.Date;  
  import java.util.Properties;  
  import java.util.Calendar;
  import java.util.GregorianCalendar;

import java.time.*;// new date api
  
 public class UtilsDate implements Constants
 {
  	 String[] allowedPatternsToReadFromDB = {"yyyy-MM-dd","yyyy/MM/dd",};// for mysql
  	 
  	 String dt1 ="dd-MM-yyyy";
  	 String dt2 =dt1.replaceAll("-","/");
  	 String dt3 =dt1.replaceAll("-",".");
  	 String dt4 =dt1.replaceAll("-",",");
  	public String[] allowedPatternsToReadFromGui = {dt1,dt2,dt3,dt4};// for mysql // used also in dialog login
  	 
  	  String dateFormatReading;
  	  String dateFormatEditing;
  	  String dateFormatSavingToDB;
  	  String dirFile;

       // private Database db;
         private ResultSet rs;
          
    public UtilsDate()
    {
    	// db= new Database();
    	

    }


  // called on setEntity in PanelODORD, in PanelODMRD when renderer is setted
  public void readFromFileDateFormats()
  {
  	
  	 dateFormatReading = getDateFormatReading();
  	 dateFormatEditing = getDateFormatEditing();
  	 dateFormatSavingToDB = getDateFormatSavingToDB();
         

  /*        System.out.println("UtilsDate.readFromFileDateFormats ------------------------------------------------------");
        dateFormatReading="dd-MM-yyyy";
        dateFormatEditing="dd-MM-yyyy";  
        dateFormatSavingToDB="yyyy-MM-dd";
          
          try
          {
            
            String sqlDec =  "SELECT dateFormatReading, dateFormatEditing FROM parameter WHERE parameter.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId+" AND parameterId like 1";
            db.retrieveDBDataFromQuery(sqlDec,"UtilsDate.readFromFileDateFormats  sqlDec");
   	    rs=db.getRS();
            rs.first();
            dateFormatReading = rs.getString("dateFormatReading");
            //dateFormatReading = strDec.charAt(0); 

            dateFormatEditing = rs.getString("dateFormatEditing");        
            //dateFormatEditing = Integer.parseInt(strLengthOfDecPrice);             

           
        
       
          }
          catch(SQLException e)
          {
            System.out.println("error   UtilsDate.readFromFileDateFormats "+e);
            if(VariablesGlobal.globalShowPrintStackTrace)  
            {
                e.printStackTrace();     
            }                
              closeDB();
          }
          
          closeDB();         
         
         */
  	
  }

     public void closeDB()
   {
   	  //db.releaseConnectionRs();
          //db.releaseConnectionRsmd();
          
   }
  
  public String getDateFormatReading()
  {
  	String dateFormat="dd-MM-yyyy";
  	  try
	  {
     	Properties props = new Properties(); //properties to get from file
     	dirFile = VariablesGlobal.globalDirConfiguration+System.getProperty("file.separator")+FILE_CONFIG;
        FileInputStream in = new FileInputStream(dirFile);
        props.load(in);
        dateFormat = props.getProperty("date.format");
      }
      catch (IOException ex)
      {
          System.err.println("UtilsDate.getDateFormatReading IOException: Cannot find text file:"+FILE_CONFIG);
          //System.err.println(ex);
      }
    return dateFormat;
   //return dateFormatReading;
  }
  
  public String getDateFormatEditing()
  {
  	String dateFormat="dd-MM-yyyy";
  	  try
	  {
     	Properties props = new Properties(); //properties to get from file
     	dirFile = VariablesGlobal.globalDirConfiguration+System.getProperty("file.separator")+FILE_CONFIG;
        FileInputStream in = new FileInputStream(dirFile);
        props.load(in);
        dateFormat = props.getProperty("date.formatEdit");
      }
      catch (IOException ex)
      {
          System.err.println("UtilsDate.getDateFormatEditing IOException: Cannot find text file:"+FILE_CONFIG);
          //System.err.println(ex);
      }
    return dateFormat;
        //return dateFormatEditing;
  
  } 
 
   public String getDateFormatSavingToDB()// for mysql
  {
  	String dateFormat="yyyy-MM-dd";
    return dateFormat;
  
  }
  
  
  // null or default date for database
  public String getNullDate()
  {
  	return "0000-00-00";
  }
 
  public boolean isDateNull(String dt)
  {
  	boolean retBool = false;
  	
  	if(dt.equalsIgnoreCase(""))
  	{
  		retBool = true;
  	} 
  	else if(dt.equalsIgnoreCase("0000-00-00"))
  	{
  		retBool = true;
  	}
  	else if(dt.equalsIgnoreCase("0000/00/00"))
  	{
  		retBool = true;
  	} 
  	else if(dt.equalsIgnoreCase("00-00-0000"))
  	{
  		retBool = true;
  	}  	
  	else if(dt.equalsIgnoreCase("00/00/0000"))
  	{
  		retBool = true;
  	}  	  	  	 	
  	
  	return retBool;
  }
  
  public String getCurrentDateStringFormattedLocaly(String dateCurrent)
  {
      //String ret = "";
      
          String[] allowedPatternsToRead = {"dd-MM-yyyy","dd/MM/yy","dd-MM-yy", "dd/MM/yyyy"};
          String dat = this.reformatDateString(dateCurrent,allowedPatternsToRead,"dd-MM-yyyy");      
      
          
          
          
      return dat;
      
  }
  
  /*
  * for example Monday
  */
  public String getCurrentDayStringFormattedLocaly(String dateCurrent,String dayPatternToReturn)
  {
      String ret = "";
      
      String dat = getCurrentDateStringFormattedLocaly(dateCurrent);
      ret = getDateNameGreek(dat,dayPatternToReturn,null,null);
       return ret;
  }
 
  /*
  * for example 01
  */
    public String getCurrentDayNoFormattedLocaly(String dateCurrent)
  {
      String ret = "";
      
      String dat = getCurrentDateStringFormattedLocaly(dateCurrent);
      ret = getDateNameGreek(dat,"dd",null,null);      
      
       return ret;
  }
 
    /*
    * for example 12
    */
    public String getCurrentMonthNoFormattedLocaly(String dateCurrent)
  {
      String ret = "";

      String dat = getCurrentDateStringFormattedLocaly(dateCurrent);
      ret = getDateNameGreek(dat,"MM",null,null);        
      
      
       return ret;
  }    

    /*
    * for example December
    */
    public String getCurrentMonthStringFormattedLocaly(String dateCurrent)
  {
      String ret = "";
      
      
      
       return ret;
  } 
    
  
  public String reformatDateStringToReadFromDB(String  dateString)
  {     
     //String[] allowedPatternsToRead = {"yyyy-MM-dd","yyyy/MM/dd",};
     
     return reformatDateString(dateString,allowedPatternsToReadFromDB, dateFormatReading);
  }  

  public String reformatDateStringToEditFromDB(String  dateString)
  {     
     //String[] allowedPatternsToRead = {"yyyy-MM-dd","yyyy/MM/dd",};
     
     return reformatDateString(dateString,allowedPatternsToReadFromDB, dateFormatEditing);
  }
 
  public String reformatDateStringToEdit(String  dateString, String[] allowedPatternsToRead)
  {     
     //String[] allowedPatternsToRead = {"yyyy-MM-dd","yyyy/MM/dd",};
     //System.out.println("UtilsDate.reformatDateStringToEdit"+dateString+"  "+ dateFormatEditing);
     return reformatDateString(dateString,allowedPatternsToRead, dateFormatEditing);
  }  

  public String reformatDateStringToSaveToDB(String  dateString)//, String[] allowedPatternsToRead)
  {
     //String[] allowedPatternsToRead = {"yyyy-MM-dd","yyyy/MM/dd",};
     //System.out.println("UtilsDate.reformatDateStringToSave "+dateString);
      //System.out.println("UtilsDate.reformatDateStringToSave "+reformatDateString(dateString,allowedPatternsToRead, getDateFormatSaving()));
     return reformatDateString(dateString,allowedPatternsToReadFromGui, dateFormatSavingToDB);
  }

 
 public String getDateNameGreek(String date, String returnPattern, String enforceDateFrom, String enforceDateTo)
 { 
 	   String dayName ="";
 	   String [] allowedPatterns = new String[1];
 	   allowedPatterns[0]=getDateFormatEditing();
 	   
 	   
 	   dayName = reformatDateString(date,allowedPatterns,returnPattern);//"EEE");
       
       
       String dayArray[] = {"Κυριακή","Δευτέρα","Τρίτη","Τετάρτη","Πέμπτη","Παρασκευή","Σαββάτο" };
       GregorianCalendar cal = new GregorianCalendar();
       String thisDay="";
       
       //System.out.println("UtilsDate.getDateName dayName is "+dayName);
   int y=0   ;  
   int intYearEnforce = 0;
   

   
 /*  if(dayName != null && !dayName.equalsIgnoreCase("")  )
   {
        cal.set(Integer.parseInt(reformatDateString(date,allowedPatterns,"yyyy")),Integer.parseInt(reformatDateString(date,allowedPatterns,"MM"))-1,Integer.parseInt(reformatDateString(date,allowedPatterns,"dd")));
        int dayInt = cal.get(Calendar.DAY_OF_WEEK);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int m = cal.get(Calendar.MONTH)+1;  // plus 1. dont know why
        y = cal.get(Calendar.YEAR);
        String day = dayArray[dayInt-1];//dayArray starts from 0
         thisDay = d+"-"+m+"-"+y;
        
        String [] pattern = new String[1];
 	    pattern[0]="dd-MM-yyyy";
         
         thisDay = reformatDateString(thisDay,pattern,"dd-MM-yyyy");
         
         
     //System.out.println("UtilsDate.getDateName "+y+" "+intYearEnforce    );
         
       //System.out.println("UtilsDate.getDateName date is "+thisDay+" - "+date+" "+ dayName); 
    }*/
   
   
   if(enforceDateFrom!= null && !enforceDateFrom.equals("") && enforceDateTo!= null && !enforceDateTo.equals(""))
   {
     /*intYearEnforce = Integer.valueOf(yearEnforce);
     
   }   
   
   if(intYearEnforce==0)// so that later will not produce red when there is a date but not anything enforced
   {
   	  intYearEnforce=y;
   }*/
    
   if(dayName == null || dayName.equalsIgnoreCase("")  )
   {

   	  dayName= "___";

   }
   else
   {
       String d = reformatDateStringToSaveToDB(date);
       //String dFrom = reformatDateStringToSaveToDB(enforceDateFrom);
       boolean isAfter = LocalDate.parse(d).isAfter(LocalDate.parse(enforceDateFrom).minusDays(1));
       //System.out.println("getDateNameGreek from  "+thisDay+"   "+date+"   "+d+"    enforceDateFrom:"+enforceDateFrom+"   "+isAfter);
       //String dTo = reformatDateStringToSaveToDB(enforceDateTo);
       boolean isBefore = LocalDate.parse(d).isBefore(LocalDate.parse(enforceDateTo).plusDays(1));
       //System.out.println("getDateNameGreek to  "+thisDay+"   "+date+"   "+d+"    enforceDateTo:"+enforceDateTo+"   "+isBefore);
       //if(!thisDay.equalsIgnoreCase(date) || !isAfter || !isBefore) //y!=intYearEnforce)// if not correct ie 31-02-2007
         if( !isAfter || !isBefore) //y!=intYearEnforce)// if not correct ie 31-02-2007 
   	 {
   	    dayName= "_x_";
   	 }
   	
   }
   }
 	return dayName;
 }


  public String reformatDateString(String dateString, String allowedPatterns[], String appliedPattern) 
  {     
  
    if (appliedPattern == null)
    {
    	System.out.println("--error UtilsDate appliedPattern is null-> perhaps not called readFromFileDateFormats");
    }
  
        int iSize;
        int i;
        boolean b = true;
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat();
        java.util.Date dt = new java.util.Date();
        
        iSize = allowedPatterns.length;
            
         //System.out.println("UtilsDate.reformatDateString dateString"+dateString);
            
        for (i = 0; i < iSize; i++)
        {
            b = true;
            sdf = new java.text.SimpleDateFormat(allowedPatterns[i]);
            if(dateString!=null && !dateString.equalsIgnoreCase(""))
            {    
               try
               {
                   //System.out.println("UtilsDate.reformatDateString       dateString:"+dateString+"    sdf:"+sdf);
                  dt = sdf.parse(dateString);
               }
               catch (ParseException  ex) // unparsable date
               {
                 b = false;
                  //System.out.println("UtilsDate.reformatDateString ParseException date:"+dateString+"         error:"+ex);
               }
            }
            else
            {
                 b = false;
            }
            
            
            if (b==true)
            {
                break;
            }
        }

        if (b==true){
            sdf.applyPattern(appliedPattern);
            return sdf.format(dt);
        } else {
            return null;
        }
                                
    }
    
    public static void main(String[] args)
    {
    	UtilsDate d =new UtilsDate();
    	d.readFromFileDateFormats();
    	System.out.println(d.dateFormatReading+" "+d.dateFormatEditing+" "+d.dateFormatSavingToDB);
        
        String[] allowedPatternsToRead = {"dd-MM-yyyy","dd/MM/yyyy"};
        System.out.println("date 31-02-2007"+ d.getDateNameGreek("31-02-2007","EEEEEEEE","01-01-2007","31-01-2007"));
        
        
        System.out.println("--> "+d.reformatDateStringToSaveToDB("2006-12-31"));
        System.out.println("--> "+d.reformatDateStringToSaveToDB("01-01-2006"));
        
        System.out.println("--> "+d.getCurrentDayStringFormattedLocaly("27-02-2017","EEE"));
        
        System.out.println("--> "+d.getCurrentDayNoFormattedLocaly("27-02-2017"));
        System.out.println("--> "+d.getCurrentMonthNoFormattedLocaly("27-02-2017"));
        System.out.println("--> "+d.getCurrentDateStringFormattedLocaly("27-02-2017"));
        
        
        
        
    }
    
}