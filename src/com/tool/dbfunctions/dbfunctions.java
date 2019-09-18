package com.tool.dbfunctions;

public class dbfunctions
{

	public dbfunctions() 
	{     
	}
	














/* mysql retZeroIfNull

DELIMITER $$

DROP FUNCTION IF EXISTS `farmersvat`.`retZeroIfNull` $$
CREATE DEFINER=`user`@`%` FUNCTION `retZeroIfNull`(val DOUBLE) RETURNS double
BEGIN
    DECLARE d DOUBLE;

    IF val is null THEN SET d = 0;
    ELSE SET d = val;
    END IF;

    RETURN d;
  END $$

DELIMITER ;

*/
/* hsql       
 CREATE FUNCTION retZeroIfNull(val DOUBLE) RETURNS DOUBLE
  LANGUAGE JAVA DETERMINISTIC NO SQL
  EXTERNAL NAME 'CLASSPATH:dbfunctions.DbFunctions.retZeroIfNull'
*/        
        
        
        
// h2   CREATE ALIAS retZeroIfNull FOR "dbfunctions.DbFunctions.retZeroIfNull"
  
        
  public static double retZeroIfNull(double val)
  {
  	double ret = 0;
  	if(val==0)
  	{
  		ret = 0;
  	}
  	else
  	{
  		ret=val;
  	}

    return ret;
  }



	/* retValueAccordingToType
	//mysql

DELIMITER $$

DROP FUNCTION IF EXISTS `farmersvat`.`retValueAccordingToType` $$
CREATE DEFINER=`user`@`%` FUNCTION `retValueAccordingToType`(idType INT, pType INT, val DOUBLE) RETURNS double
BEGIN
    DECLARE d DOUBLE;

    IF idType = pType THEN SET d = val;
    ELSE SET d = null;
    END IF;

    RETURN d;
  END $$

DELIMITER ;


sql to test
SELECT f.farmerId AS"Νο αγρότη", f.surname AS"επίθετο", f.name AS"όνομα", f.fatherName AS"πατρόνυμο",f.farmerAfm AS"Α.Φ.Μ.", permanent AS "υπολ" , d.dateOfApplication AS "ημ/νια αίτησης" , COUNT(i.value) AS "πλήθος", SUM(i.value) AS "αξία", SUM(retValueAccordingToType(1, i.productTypeId, i.valueReturn)) AS "κατ 1", SUM(retValueAccordingToType(2, i.productTypeId, i.valueReturn)) AS "κατ 2", SUM(retValueAccordingToType(3, i.productTypeId, i.valueReturn)) AS "κατ 3", d.valueReturn AS "συν επιστρ", d.payment AS "κράτηση" FROM delivery d, farmer f, invoice i WHERE i.farmerId = f.farmerId AND d.farmerId = f.farmerId AND i.deliveryId = d.deliveryId AND i.dbyear=d.dbyear AND i.companyId=d.companyId AND d.deliveryId = 1 AND d.dbyear=2006 AND d.companyId=1 GROUP BY i.farmerId ORDER BY f.surname, f.name, f.fathername


 h2   CREATE ALIAS retValueAccordingToType FOR "dbfunctions.DbFunctions.retValueAccordingToType"
 
 derby
    create function retValueAccordingToType(idType INT, pType INT, val DOUBLE)  returns Double
    PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA
    EXTERNAL NAME 'jdbc.Database.retValueAccordingToType' 
 
 hsql
  CREATE FUNCTION retValueAccordingToType(idType INT, pType INT, val DOUBLE) RETURNS DOUBLE
  LANGUAGE JAVA DETERMINISTIC NO SQL
  EXTERNAL NAME 'CLASSPATH:dbfunctions.DbFunctions.retValueAccordingToType'
 
 
 */
      public static double retValueAccordingToType(int idType, int pType,double val )
  {

  	double ret = 0;
  	if(idType == pType)
  	{
  		ret=val;
  	}
  	else
  	{
  		ret = 0;
  	}
  
     return ret;
  }	
  // function for h2 ,  return "%m"=month number, return "%M"=month name
  // also a mysql function
  // h2   CREATE ALIAS returnMonth FOR "dbfunctions.DbFunctions.returnMonth"
  //      drop ALIAS returnMonth
  /*
      derby 
      create function date_format(date CHAR, monthFormat CHAR)  returns CHAR
    PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA
    EXTERNAL NAME 'jdbc.Database.date_format' 
 
 hsql
 
  CREATE FUNCTION returnMonth(date VARCHAR(20), monthFormat VARCHAR(10)) RETURNS VARCHAR(20)
  LANGUAGE JAVA DETERMINISTIC NO SQL
  EXTERNAL NAME 'CLASSPATH:dbfunctions.DbFunctions.returnMonth'
  //  CANNOT be put inside db
  
  
  
 
    mysql
    CREATE FUNCTION returnMonth (date CHAR(10), monthFormat CHAR(1)) RETURNS CHAR(12)
    RETURN CONCAT('Hello, ',s,'!');
  
  mysql  date_format(date, \"%M\")
 
 SUBSTRING(str FROM pos FOR len) 

DELIMITER $$
DROP FUNCTION  IF EXISTS `farmersvat`.`returnMonth` $$
CREATE DEFINER=`user`@`%` FUNCTION  `returnMonth`(date DATE, monthFormat VARCHAR(10)) RETURNS VARCHAR(15)

BEGIN
    DECLARE ret VARCHAR(15);

    IF monthFormat = 'no' THEN SET ret = date_format(date, "%m");
    ELSE
       IF date_format(date, "%m")='01' THEN SET ret = 'ΙΑΝΟΥΑΡΙΟΣ';
       END IF;
       IF date_format(date, "%m")='02' THEN SET ret = 'ΦΕΒΡΟΥΑΡΙΟΣ';
       END IF;
       IF date_format(date, "%m")='03' THEN SET ret = 'ΜΑΡΤΙΟΣ';
       END IF;
       IF date_format(date, "%m")='04' THEN SET ret = 'ΑΠΡΙΛΙΟΣ';
       END IF;
       IF date_format(date, "%m")='05' THEN SET ret = 'ΜΑΪΟΣ';
       END IF;
       IF date_format(date, "%m")='06' THEN SET ret = 'ΙΟΥΝΙΟΣ';
       END IF;
       IF date_format(date, "%m")='07' THEN SET ret = 'ΙΟΥΛΙΟΣ';
       END IF;
       IF date_format(date, "%m")='08' THEN SET ret = 'ΑΥΓΟΥΣΤΟΣ';
       END IF;
       IF date_format(date, "%m")='09' THEN SET ret = 'ΣΕΠΤΕΜΒΡΙΟΣ';
       END IF;
       IF date_format(date, "%m")='10' THEN SET ret = 'ΟΚΤΩΒΡΙΟΣ';
       END IF;
       IF date_format(date, "%m")='11' THEN SET ret = 'ΝΟΕΜΒΡΙΟΣ';
       END IF;
       IF date_format(date, "%m")='12' THEN SET ret = 'ΔΕΚΕΜΒΡΙΟΣ';
       END IF;

    END IF;

    RETURN ret;
  END $$

DELIMITER ;
  
  */
  
  public static String returnMonth(String date, String monthFormat)
  {
  	String ret ="";
  	
  	String strMonthNo = date.substring(5,7);
  	  
  	if(monthFormat.equals("no"))  //return "%m"=month number
  	{
  		ret = strMonthNo;
  	}
  	else if(monthFormat.equals("name"))//return "%M"=month name
  	{
  		int monthNo = Integer.parseInt(strMonthNo);
  		
  		if(monthNo==1)
  		{
  			ret = "ΙΑΝΟΥΑΡΙΟΣ";
  		}
  		else if (monthNo==2)
  		{
  			ret = "ΦΕΒΡΟΥΑΡΙΟΣ";
  		}
  		else if (monthNo==3)
  		{
  			ret = "ΜΑΡΤΙΟΣ";
  		}
  		else if (monthNo==4)
  		{
  			ret = "ΑΠΡΙΛΙΟΣ";
  		}
  		else if (monthNo==5)
  		{
  			ret = "ΜΑΪΟΣ";
  		}
  		else if (monthNo==6)
  		{
  			ret = "ΙΟΥΝΙΟΣ";
  		}
  		else if (monthNo==7)
  		{
  			ret = "ΙΟΥΛΙΟΣ";
  		}
  		else if (monthNo==8)
  		{
  			ret = "ΑΥΓΟΥΣΤΟΣ";
  		}
  		else if (monthNo==9)
  		{
  			ret = "ΣΕΠΤΕΜΒΡΙΟΣ";
  		}
  		else if (monthNo==10)
  		{
  			ret = "ΟΚΤΩΒΡΙΟΣ";
  		}
  		else if (monthNo==11)
  		{
  			ret = "ΝΟΕΜΒΡΙΟΣ";
  		}
 		else if (monthNo==12)
  		{
  			ret = "ΔΕΚΕΜΒΡΙΟΣ";
  		}
  		else
  		{
  			System.out.println("DbFunctions.returnMonth not supported month "+monthNo);
  		}  		
  	}
  	else
  	{
  		System.out.println("DbFunctions.returnMonth not supported key "+monthFormat);
  	}
  	
  	
  	
  	
  	 return ret;
  }

  
  
  
  
 
  
  
  
/*
   --------------------------  mysql ----------------------------------------------------
   
   CREATE PROCEDURE procedure1  (IN parameter1 INTEGER)   parameters 
BEGIN                                       start of block 
  DECLARE variable1 CHAR(10);                variables 
  IF parameter1 = 17 THEN                     start of IF 
    SET variable1 = 'birds';                    assignment 
  ELSE
    SET variable1 = 'beasts';                   assignment 
  END IF;                                    end of IF 
  INSERT INTO table1 VALUES (variable1);     statement 
END                                         end of block 




      h2
 ------------------------- User-Defined Functions and Stored Procedures  ------------------------------

In addition to the built-in functions, this database supports user-defined Java functions. In this database, Java 

functions can be used as stored procedures as well. A function must be declared (registered) before it can be 

used. Only static Java methods are supported; both the class and the method must be public. Example Java method:

package acme;
import java.math.*;
public class Function {
    public static boolean isPrime(int value) {
        return new BigInteger(String.valueOf(value)).isProbablePrime(100);
    }
}

The Java function must be registered in the database by calling CREATE ALIAS:

CREATE ALIAS IS_PRIME FOR "acme.Function.isPrime"

For a complete sample application, see src/test/org/h2/samples/Function.java. 

------------------------------------------------------------------------------------
 hsql        
   http://hsqldb.org/doc/2.0/guide/sqlroutines-chapt.html#src_jrt_routines
 
 
 CREATE FUNCTION sinh(v DOUBLE) RETURNS DOUBLE
  LANGUAGE JAVA DETERMINISTIC NO SQL
  EXTERNAL NAME 'CLASSPATH:java.lang.Math.sinh'
 
 
 
 */
 
   public static void main(String[] args)
  {  //test
    dbfunctions dbf = new dbfunctions();
    
    
    System.out.println("DbFunctions returnMonth "+dbf.returnMonth("2009-05-08","name"));
    //String tbl = "invoice";
    //db.retrieveTables();
    //db.retrieveResultSet("select * from user");
    
    //System.out.println("Database date_format "+db.date_format("2009-05-08","\"%M\""));
/*    
  //  db.retrieveRSMetaData("SELECT *, COUNT(farmerId),SUM(value) FROM (SELECT farmer.farmerAfm, farmer.surname,farmer.name, invoice.* FROM invoice,"+
   //    "farmer WHERE invoice.farmerId = farmer.farmerId) k GROUP BY farmerId");
    
    db.retrieveRSMetaData("SELECT sri.farmerId,f.farmerAfm, f.surname, f.name, f.fatherName, count(sri.value) AS count, sum(sri.value) AS value, sum(sri.total) AS returnsum"+
"FROM farmer f,(SELECT i.farmerId, i.deliveryId,f.surname, f.name, f.fathername, i.dbyear, pt.productTypeId, (pt.returnVat + pt.returnFuel) AS percentage, value, value*(pt.returnVat + pt.returnFuel)/100 AS total"+
"FROM invoice i, productType pt, farmer f"+
"WHERE i.invoiceTypeId= pt.productTypeId AND f.farmerId=i.farmerId AND i.dbyear=2006) sri"+
"WHERE sri.farmerId = f.farmerId"+
"GROUP BY f.farmerId, f.surname, f.name,f.fathername, f.farmerAfm"+
"ORDER BY f.surname, f.name,f.fathername");
*/
   // db.retrieveFields(tbl);
  }
}