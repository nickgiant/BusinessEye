// created 12-07-2007
package com.tool.utils;

  import com.tool.guicomps.*;
  import com.tool.gui.*;
import com.tool.jdbc.Database;
  
  import java.text.DecimalFormat;
  import java.text.DecimalFormatSymbols;
 
 import java.math.RoundingMode;
 
  import java.io.FileInputStream;
  import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
  
  import java.util.Properties;  
  import java.util.Locale;
  
 public class UtilsDouble implements Constants
 {

  	 private char doubleDecimalFormatChar;
  	 private int decimalNumbers;
  	 private char thousandsChar;
         private Database db;
         private ResultSet rs;
    public UtilsDouble()
    {
        db= new Database();
    }
   
   

   // return with . instead of , (the way double is recognised)
   public String returnDoubleWithDotInsteadOfAComma(String value)
   {
       String ret ="";
     //  System.out.println("-UtilsDouble.returnDoubleWithDotInsteadOfAComma  value:"+value +"  ret:"+ret );
   	
   if(value != null)
   {
        if(doubleDecimalFormatChar==',')
        {
   	   int idx = value.indexOf(",");
   	   if(idx!=-1)
   	   {
   	 	ret = value.replace(".","");  // replace , with nothing
   	 	ret = ret.replace(",",".");
   	   }
   	   else
   	   {
   	 	ret= value;
   	   }
        }
         else if (doubleDecimalFormatChar=='.')
         {
   	   int idx = value.indexOf(".");
   	   if(idx!=-1)
   	   {
   	 	ret = value.replace(",","");  // replace , with nothing
                //System.out.println("UtilsDouble.returnDoubleWithDotInsteadOfAComma IF ret:"+ret);
   	 	//ret = ret.replace("",".");
   	   }
   	   else
   	   {
   	 	ret= value;
                //System.out.println("UtilsDouble.returnDoubleWithDotInsteadOfAComma ELSE ret:"+ret);
   	   }                 
         }
        else
         {
             System.out.println("UtilsDouble.returnDoubleWithDotInsteadOfAComma UNKNOWN, perhaps not called getSettingsFromDb ret:"+ret+"  doubleDecimalFormatChar:"+doubleDecimalFormatChar+" value:"+value);
         }
   	
   }
   else
   {
       
       value = "";
       ret = "";
   }
       
        
   	return ret;
   }
   
   
  // should be called on setEntity in PanelODORD, in PanelODMRD when renderer is setted, panel statistics
   public void getSettingsFromDb()
   {
   	   //System.out.println("UtilsDouble.getSettingsFromDb");
  	/*try
	  {
     	Properties props = new Properties(); //properties to get from file
     	String dirFile = VariablesGlobal.globalDirConfiguration+System.getProperty("file.separator")+FILE_CONFIG;
        FileInputStream in = new FileInputStream(dirFile);
        props.load(in);
        
        String dfor = props.getProperty("number.decimalFormat");
        doubleDecimalFormatChar = dfor.charAt(0);   	
        
        String decNo =props.getProperty("number.decimalNumbers");
        
        decimalNumbers = Integer.parseInt(decNo);   	
       
       
       
       }
      catch (IOException ex)
      {
          System.out.println("UtilsDouble.getSettingsFromDb IOException: Cannot find text file:"+FILE_CONFIG);
          System.out.println(ex.getMessage());
      }*/
          
          
          System.out.println("UtilsDouble.getSettingsFromDb ------------------------------------------------------");
        
          try
          {
            
            String sqlDec =  "SELECT charOfDecimal, lengthOfDecimalPrice FROM dbcompany WHERE dbcompany.dbCompanyId='"+VariablesGlobal.globalCompanyId+"'";
            db.retrieveDBDataFromQuery(sqlDec,"UtilsDouble.getSettingsFromDb  sqlDec");
   	    rs=db.getRS();
            rs.first();
            String strDec = rs.getString("charOfDecimal");
            doubleDecimalFormatChar = strDec.charAt(0); 

            String strLengthOfDecPrice = rs.getString("lengthOfDecimalPrice");        
        decimalNumbers = Integer.parseInt(strLengthOfDecPrice);             

        if (doubleDecimalFormatChar==',') 
        {
            thousandsChar ='.';
            //pattern = "###.###.###";
        }
        else if(doubleDecimalFormatChar=='.')
        {
        	thousandsChar =',';
        	 //pattern = "###,###,###";
        }            
        
       
          }
          catch(SQLException e)
          {
            System.out.println("error   UtilsDouble.getSettingsFromDb "+e);
            if(VariablesGlobal.globalShowPrintStackTrace)  
            {
                e.printStackTrace();     
            }                
              closeDB();
          }
          
          closeDB();
   
   }
  
   public void closeDB()
   {
   	  db.releaseConnectionRs();
          db.releaseConnectionRsmd();
          
   }
    
    // Object used for tableModelResultSet.hasEmptyRow
   /**
    * 
    * @param value
    * @param showZero if true then return the 0,00 else return nothing. Used in reports and elsewhere.
    * @return 
    */   
   public String getDoubleReading(Object value,boolean showZero)
   {  
      String ret="";
      double valu=Double.valueOf("0.00").doubleValue();
      String v =getDoubleReading(valu);
      //System.out.println("UtilsDouble.getDoubleReading "+value+"-"+v+"-"+getDoubleEditing(valu));
      if (value == null || value.equals(null) || value.toString().trim().equals("") ||  value.toString().trim().equals("0.0")||  value.toString().trim().equals("0"))  // 0.0 in h2
      {
   	      double val=Double.valueOf("0.00").doubleValue();
          //System.out.println("UtilsDouble.getDoubleReading "+value+"-"+val+"-"+getDoubleReading(val));
            if(showZero)
            {
              ret = getDoubleReading(val);          
            }
            else
            {
                ret="";   
            }
      }
      else
      {
   	      double val=Double.valueOf(value.toString()).doubleValue();
              //long val = Long.valueOf(value.toString()).longValue();
          //System.out.println("UtilsDouble.getDoubleReading "+value+"-"+val);//+"-"+getDoubleReading(val));
          ret = getDoubleReading(val);
      }
      
      return ret;
  } 
 
   /**
    * 
    * @param value
    * @param showZero if true then return the 0,00 else return nothing. Used in reports and elsewhere.
    * @return 
    */
   public String getDoubleReading(String value,boolean showZero)
   {  
      String ret="";
      double valu=Double.valueOf("0.00").doubleValue();
      String v =getDoubleReading(valu);
     //System.out.println("UtilsDouble.getDoubleReading "+value+"-"+v+"-"+getDoubleEditing(valu));
      if (value == null || value.equals(null) || value.toString().trim().equals("null") || value.toString().trim().equals("") ||  value.toString().trim().equals("0.0") ||  value.toString().trim().equals("0")) // 0.0 in h2
      {
   	      double val=Double.valueOf("0.00").doubleValue();
          //System.out.println("UtilsDouble.getDoubleReading "+value+"-"+val+"-"+getDoubleReading(val));
            if(showZero)
            {
              ret = getDoubleReading(val);          
            }
            else
            {
                ret="";
            }
      }
      else
      {
   	      double val=Double.valueOf(value.toString()).doubleValue();
          
          ret = getDoubleReading(val);
          //System.out.println("UtilsDouble.getDoubleReading   showZero:"+showZero+"   "+value+"-"+val+"-"+getDoubleReading(val)+"   ret:"+ret);
      }
      
      return ret;
  } 
    
   public String getDoubleReading(double value)
  {
  	  String s="0.00";
      
        String pattern="###,###,###,###,###,##0";
       /* if (doubleDecimalFormatChar==',')
        {
            thousandsChar ='.';
            //pattern = "###.###.###";
        }
        else if(doubleDecimalFormatChar=='.')
        {
        	thousandsChar =',';
        	 //pattern = "###,###,###";
        }*/
        
       /* String decNo =props.getProperty("number.decimalNumbers");
        
        int decimalNumbers = Integer.parseInt(decNo);*/
       
       
       DecimalFormatSymbols dfs = new DecimalFormatSymbols();
       dfs.setDecimalSeparator(doubleDecimalFormatChar);
       dfs.setGroupingSeparator(thousandsChar);
       dfs.setZeroDigit('0');
     
       //System.out.println(pattern+" "+doubleDecimalFormatChar+" "+thousandsChar);
       DecimalFormat df = new DecimalFormat(pattern, dfs);
       df.setGroupingSize(3);
       df.setMaximumFractionDigits(decimalNumbers);
       df.setMinimumFractionDigits(decimalNumbers);
       df.setRoundingMode(RoundingMode.HALF_UP); // if 5.5 then 6
       //System.out.println("UtilsDouble.getDoubleReading "+value);
       s =  df.format(value);
       //System.out.println("UtilsDouble.getDoubleReading "+dfs.getGroupingSeparator() );
        //d = Double.valueOf(s).doubleValue();
        
   //System.out.println("UtilsDouble.getDoubleReading input value:"+value+" ret:"+s);
    return s;
  
  }
  
  public String getDoubleEditing(double value)
  {
  	  String s="0.00";
  	 /* try
	  {
     	Properties props = new Properties(); //properties to get from file
        FileInputStream in = new FileInputStream(FILE_CONFIG);
        props.load(in);
        
        String dfor = props.getProperty("number.decimalFormat");
        char doubleFormat = dfor.charAt(0);
        
        
        String decNo =props.getProperty("number.decimalNumbers");
        int decimalNumbers = Integer.parseInt(decNo);*/
       
       
       DecimalFormatSymbols dfs = new DecimalFormatSymbols();
       dfs.setDecimalSeparator(doubleDecimalFormatChar);
     
       DecimalFormat df = new DecimalFormat("0.00", dfs);
       df.setMaximumFractionDigits(decimalNumbers);
       df.setMinimumFractionDigits(decimalNumbers);
       
       //value=value.trim();
       //System.out.println(value);
       s =  df.format(value)+"";
       //s = value;
       //System.out.println(s);
       //d = Double.valueOf(s).doubleValue();
        
      
      
    return s;  
  } 
  
  public String getDoubleSaving(String value)
  {
  	String str = "0";
  	 

        
       //System.out.println("UtilsDouble.getDoubleSaving  value:"+value+"  returnDoubleWithDotInsteadOfAComma"+returnDoubleWithDotInsteadOfAComma(value));
      /* String strBeforeDecimalChar =   value.substring(0,value.length()-(decimalNumbers+1));
       String strAfterDecimalChar =   value.substring(value.length()-(decimalNumbers),value.length());
       System.out.println("UtilsDouble 1  "+strBeforeDecimalChar);
       value = strBeforeDecimalChar.replaceAll(".", "");
        
        System.out.println("UtilsDouble 2  "+value + " strBeforeDecimalChar:"+strBeforeDecimalChar+"."+strAfterDecimalChar);*/
        
        
  	    /* if(doubleDecimalFormatChar==',')
  	     {
  	           str = value.replace(',','.');
  	     }
  	     else
  	     {
                 
  	     	   str=value;
  	     }*/
  	//System.out.println("UtilsDouble  str:"+str);
             str = returnDoubleWithDotInsteadOfAComma(value);
             String val ="";
             if( str == null  || str.trim().equalsIgnoreCase("") || str.equalsIgnoreCase("null"))
             {
                 val="";
                 str="";
             }
             else
             {
                 //System.out.println("UtilsDouble.getDoubleSaving  val:"+val+"  str:"+str);
                 val= getDoubleEditing(Double.parseDouble(str));
                 str = returnDoubleWithDotInsteadOfAComma(val);
             }
             
             
             
             
    return str;
  
  } 
  
    public static void main(String args[])
    {
    	UtilsDouble ud=new UtilsDouble();
    	Object value=311932.10000000027;
    	ud.getSettingsFromDb();
    	System.out.println(" "+ud.doubleDecimalFormatChar+"  "+ud.decimalNumbers);
    	System.out.println(ud.getDoubleReading(value,true));
    } 
    
 }