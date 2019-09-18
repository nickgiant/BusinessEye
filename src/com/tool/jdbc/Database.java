//http://www.javadb.com/using-database-transaction-with-jdbc
// http://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html


/*       look for
 * 


         Database dbTransaction = new Database();
         try
         {
          dbTransaction.transactionLoadConnection();
          dbTransaction.setTransactionAutoCommit(false);
         System.out.println("PanelODOR.rowDelete     dbTransaction:"+dbTransaction);                 
         
         ------- method throws SQLException  (no try ....catch)  
         --------- transactionUpdateQuery
        
        dbTransaction.transactionCommit();
        dbTransaction.setTransactionAutoCommit(true);               
       
       }
       catch(SQLException e)
       {
           dbTransaction.transactionRollback();
           System.out.println(" error  PanelODOR.rowDelete   rollBack  dbTransaction:"+dbTransaction); 
           dbTransaction.transactionUpdateQuerySQLException(e,true,"PanelODOR.rowDelete");
          
       }
       finally
	{
	      if (!dbTransaction.isTransactionConnectionNull())
              {
	           dbTransaction.transactionClose();
              }
        }  





 *      dbTransaction = new Database();
      try
      {
 *      
 *      dbTransaction.transactionLoadConnection();   // keeps connection in db class, no return
 *      dbTransaction.setTransactionAutoCommit(false);
          
        dbTransaction.transactionUpdateQuery(" sql  ");

*       dbTransaction.transactionCommit();
        dbTransaction.setTransactionAutoCommit(true);
                      
        dbTransaction.setTransactionClose();

        }
        catch (SQLException e) 
        {
 
			System.out.println(e.getMessage());
			dbTransaction.getConnectionTransaction().rollback();
 
	}
        finally
        {
 
			if (isTransactionConnectionNull)     // getConnectionForTransaction  returns
                        {
				dbTransaction.getConnectionTransaction().close();  // getConnectionForTransaction  returns
			}
 
	}

 * 
 */

package com.tool.jdbc;

import com.tool.utils.*;
import com.tool.gui.*;
import com.tool.guicomps.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Database implements Constants
{

  private Connection con;
  private Connection transactionConnection;
  private Connection conRs;
  private Connection conRsmd;
  public ResultSet rs;
  private ResultSetMetaData rsmd;
  private PreparedStatement preparedStatement;
  private Statement stmnt;
  private PreparedStatement preparedstmnt;
  private DatabaseMetaData md;
 
  private int tableCount;
  //private int fieldCount;
  private UtilsGui utilsGui;
  private UtilsString utilsString;
  private String[] tableNames ;

  private String[] fieldNames;// = {"name", "index", "type","size","isPK","is ImpK"};//   };//
  private Object[][] dataObjects ;//=  new Object[4][4];
 
   private WindowWait wWaitTimer = null;
   private Timer timer;
 
 // private Column column;
 
	public Database() 
	{     
      utilsGui=new UtilsGui();
      utilsString = new UtilsString();
  //      con = DbConnection.getConnectionFromFile();//get from connection factory
 //       stmnt = con.createStatement();

	}

   public void transactionLoadConnection()
   {
       try
       {
            transactionConnection = DbConnection.getConnectionFromFile();
       }
       catch ( SQLException sqlex)
      {
         System.out.println("error: Database.transactionLoadConnection() "+ "error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
      }
       //return con;
   }        
   
   
  public void setTransactionAutoCommit(boolean b)
  {
    try
    {
      transactionConnection.setAutoCommit(b); 
    }
    catch (SQLException ex)
    {
            
            System.out.println("Database.setTransactionAutoCommit "+ex.getMessage());
            
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           ex.printStackTrace();     
         }
            try
            {
                //error occured so we rollback the changes.
                transactionConnection.rollback();
                transactionConnection.close();
                System.out.println("error   Database.setTransactionAutoCommit error occured, rollback changes.");
            }
            catch (SQLException ex1) 
            {
                System.out.println("error   Database.setTransactionAutoCommit "+ex1.getMessage());
                
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           ex1.printStackTrace();     
         }                
         
              
            }
    }   
  }   
   
  public void transactionCommit()
  {
    try
    {
        
       
      transactionConnection.commit();
      //System.out.println("Database.transactionCommit     ");
      // updateShowWindowSuccess(messageInWindow);
      
      
      
    }
    catch (SQLException ex)
    {
            
            System.out.println("error   Database.transactionCommit "+ex.getMessage());
            
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           ex.printStackTrace();     
         }
            try
            {
                //error occured so we rollback the changes.
                transactionConnection.rollback();
                transactionConnection.close();
                System.out.println("error   Database.transactionCommit error occured, rollback changes.");
            }
            catch (SQLException ex1) 
            {
                System.out.println("error   Database.transactionCommit "+ex1.getMessage());
                
                if(VariablesGlobal.globalShowPrintStackTrace)  
                {
                    ex1.printStackTrace();     
                }                
         
              
            }
      }   
  }
      
  public boolean isTransactionConnectionNull()
  {
      boolean isTransCOnnectNull=false;
      
      if(transactionConnection == null)
      {
          isTransCOnnectNull = true;
      }
      else
      {
           isTransCOnnectNull = false;
      }
      
      return isTransCOnnectNull;
  }
  
  
  public void transactionClose()
  {

            try
            {
                //error occured so we rollback the changes.
                //transactionConnection.rollback();
                transactionConnection.close();
                System.out.println("                        Database.setTransactionClose         close.");
            }
            catch (SQLException ex1) 
            {
                System.out.println("error   Database.setTransactionClose "+ex1.getMessage());
                
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           ex1.printStackTrace();     
         }                
         
              
            }
 
  }  
 
  public void transactionRollback()
  {

            try
            {
                //error occured so we rollback the changes.
                transactionConnection.rollback();
           //     updateShowWindowFailure();
             
                System.out.println("error   Database.setTransactionRollback error occured, ROLLBACK changes.");
            }
            catch (SQLException ex1) 
            {
                System.out.println("error   Database.setTransactionRollback "+ex1.getMessage());
                
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           ex1.printStackTrace();     
         }                
         
              
            }
 
  }   

  
   public int transactionUpdateQuery(String query, String strClassThatIsCalled, boolean showDialogOnError) throws SQLException
   {
     int ret=0;
           
            stmnt = transactionConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
       
             // System.out.println("Database.updateQuery "+query);      
        
          ret = stmnt.executeUpdate(query);
              
          
          // http://stackoverflow.com/questions/24588095/sql-exception-generated-keys-not-requested
               //   con = DriverManager.getConnection(...);
  /*      preparedStatement = transactionConnection.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
        preparedStatement.execute();
        rs = preparedStatement.getGeneratedKeys();

        while (rs.next()) {
            System.out.println("Database.transactionUpdateQuery:       --     pkid:"+ String.valueOf(rs.getInt(1)));
        }    
    */
            

              
              
                   if (VariablesGlobal.globalShowSQLEdit)        
                   {        		
                     System.out.println("Database.transactionUpdateQuery  - ret:"+ret+"       Query:"+query);
    	           }     
                 
               
      /*  try (ResultSet generatedKeys = stmnt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                System.out.println("------- PK:"+generatedKeys.getInt(1)+"---------------");
            }
            else {
                throw new SQLException("Creating PK id failed, no ID obtained.");
            }
        } */                  
                   
                   
            /*if(ret==0)
            {
                 updateShowWindowFailure(ret,query);
      	        
            }
            else if (ret ==1 )
            {
                   updateShowWindowSuccess(ret,query);
  
            }
            else if(ret>1)
            {  
    	          updateShowWindowSuccess(ret,query);  
            }*/
        // }
       // System.out.println("database.retrieveResultSet() concurency(1007 read only, 1008 updatable): "+rs.getConcurrency());

     
     //System.out.println("-Database.updateQuery ret:"+ret);
     return ret;
   }  
   
  /*
   * called by this.transactionRollback
   */ 
  private void updateShowWindowFailure()
  {
      
 
    	        if(wWaitTimer==null)
    	        {
    	            wWaitTimer = new WindowWait("<html><table><tr><th>save failure<th></tr></table></html>",WINDOW_LOCATION_DOWNRIGHT,ICO_CANCEL16,null);
    	        }
    	        else
    	        {
    	        	//wWaitTimer.close();
    	        	timer.cancel();
    	        }
    	        
  	            wWaitTimer.showWindow();
      	        timer = new Timer();
      	        timer.schedule(new RemindTask(), 2000);// 3000=3sec      
      
  }
   
  
  /*
  *
  *   called by 
  */
   public void updateShowWindowSuccessSave(String messageInWindow)
   {
       
 
                if(wWaitTimer==null)
    	        {
    	             wWaitTimer = new WindowWait("<html><table><tr><th>επιτυχία αποθήκευσης<th></tr><tr><td>"+messageInWindow+"<td></tr></table></html>",WINDOW_LOCATION_DOWNRIGHT,/*WINDOW_LOCATION_CENTER,*/ICO_OK16,null); // WINDOW_LOCATION_DOWNRIGHT
    	        }
    	        else
    	        {
    	        	//wWaitTimer.close();
    	        	timer.cancel();
    	        }
    	        
  	            wWaitTimer.showWindow();
      	        timer = new Timer();
      	        timer.schedule(new RemindTask(), 1800);// 3000=3sec       
       
   }
   
   public void updateShowWindowSuccessDelete(String messageInWindow)
   {
       
 
                if(wWaitTimer==null)
    	        {
    	             wWaitTimer = new WindowWait("<html><table><tr><th>επιτυχία διαγραφής<th></tr><tr><td>"+messageInWindow+"<td></tr></table></html>",WINDOW_LOCATION_DOWNRIGHT,/*WINDOW_LOCATION_CENTER,*/ICO_OK16,null); // WINDOW_LOCATION_DOWNRIGHT
    	        }
    	        else
    	        {
    	        	//wWaitTimer.close();
    	        	timer.cancel();
    	        }
    	        
  	            wWaitTimer.showWindow();
      	        timer = new Timer();
      	        timer.schedule(new RemindTask(), 1800);// 3000=3sec       
       
   }   
   
   public void transactionUpdateQuerySQLException(SQLException sqlex, boolean showDialogOnError, String strClassThatIsCalled)
   {
       //String query="";


     	String error="";	

          System.out.println(" error:  Database.transactionUpdateQuerySQLException error code:" +sqlex.getErrorCode()+"  Message:" + sqlex.getMessage());
          
          if (sqlex.getErrorCode()==1062)
          { 
             
              error="There is already a record with the same data.\n It is not possible to insert a record two times!";
              if (VariablesGlobal.globalShowAnalyticErrorMessages)  
              {
              	 error=error+"\n error code "+sqlex.getErrorCode()+"\n"+sqlex.getMessage();//+"\n query \n"+query;
              }
              //System.out.println("Database.updateQuery "+query);
            // utilsGui.showMessageError(error); 
          }
          else if (sqlex.getErrorCode()==1451)
          { 
              error="You can not remove a record because there is a reference in other table of the database.";
              if (VariablesGlobal.globalShowAnalyticErrorMessages)  
              {
              	 error=error+"\n error code "+sqlex.getErrorCode()+"\n"+sqlex.getMessage();
              }
             //utilsGui.showMessageError(error); 
          }
          else
          {
          	 error="";
              if (VariablesGlobal.globalShowAnalyticErrorMessages)  
              {
              	 error=error+"error code "+sqlex.getErrorCode()+"\n"+sqlex.getMessage();//+"\n query \n"+query;
              }
             //utilsGui.showMessageError(error);           	
          }
     	
     /*     try
          {
              DbConnection.releaseConnection(con);
          }
          catch(SQLException sqlex1)
          {
              System.out.println("error:  Database.transactionUpdateQuery error code sqlex1: " +sqlex1.getErrorCode()+" " + sqlex1.getMessage());
          }
       */   
          
     	if(showDialogOnError)
     	{
            String strError = "";
            if(VariablesGlobal.globalShowMsgOnError)
            {
                strError=" from class="+strClassThatIsCalled;
            }
     		 utilsGui.showMessageError("Error "+strError+" "+error); 	
     	}// if    showDialogOnError
        
             
       
       
   }
   
   
  
   public String[] retrieveDBObjects(String table, String tableName) //copy from DatabaseMeta
  {
    try
    {
      con = getConnection();//get from connection factory
      DatabaseMetaData md = con.getMetaData();
      //String[] types = {"TABLE"};
      String[] types = {table };
      ResultSet rs = md.getTables( null, null,  "%", types ); // also getProcedures or getFunctions
      tableCount = 0;
      while ( rs.next() )
      {  
      	  tableCount++; 
      	  //System.out.println(tableCount+rs.getString("TABLE_NAME"));
      }
       rs.first();
       tableNames = new String[tableCount];
        
  	for (int i=0; i<tableCount; i++)
        {
            tableNames[i] = rs.getString(tableName);
          // tableNames[i] = rs.getString("TABLE_NAME");//  REMARKS explanation
          //System.out.println("Database.retrieveDBObjects  "+tableNames[i]);
          // tableCaptions[i] = rs.getString("REMARKS");
           rs.next();
        } 
       //rs.close();
       DbConnection.releaseConnection(con);
    }
    catch ( SQLException sqlex)
    {
        System.out.println("error:Database.retrieveTables() "+ "error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
    }
    return tableNames;
  }
 
   
  
 
 // called by ReportCalcs.fetchDataGroup
  public int getIndexFromRSMDforColumnName(ResultSetMetaData rsmd, String colName)
  {
  	int ret=-1;
  	try
  	{
  	 int colCount = rsmd.getColumnCount();
  	 
  	 for(int c=1;c<=colCount;c++)	
  	 {
  	 	if(colName.toUpperCase().equalsIgnoreCase(rsmd.getColumnName(c).toUpperCase()))
  	 	{
  	 		ret=c;
  	 		break;
  	 	}
  	 	else
  	 	{
  	 		
  	 	}
  	 	
  	 }
  	 
  	 if(ret==-1)
  	 {
  	 	System.out.println("error: Database.getIndexFromRSMDforColumnName("+colName+") not found. colCount"+colCount);
  	 }
  	 
    }
    catch ( SQLException sqlex)
    {
        System.out.println("error: Database.getIndexFromRSMDforColumnName("+colName+") "+ "error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
    }  	 
  	 	return ret;
  }
 

  // can also be used    utilsString.escapeSqlInjection(
  //  look also  https://stackoverflow.com/questions/1812891/java-escape-string-to-prevent-sql-injection
  // the following is used in dialoglogin
     public void retrieveDBDataFromQueryPreparedStatement(String query, String classNameForMessage)
   {
	
   	
     try
     {
        conRsmd = getConnection();//get from connection factory

        
        // exists in DialogQueryBrowser.updateDB  and Database.retrieveDBDataFromQuery
        if (DbConnection.getDBEngineOfConnectionFromFile()==DBENGINE_SQLITE)
        {  // SQLite only supports TYPE_FORWARD_ONLY cursors    SQLite only CONCUR_READ_ONLY
            
             stmnt = conRsmd.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); 
        }
        else
        {
            stmnt = conRsmd.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
        }
        
        String queryBeforeWhere = "";
        String queryWhere = "";
        if(utilsString.hasQueryWhere(query))
        {
          query =  utilsString.replaceFromQueryValuesWithQuestionmarks(query);
         
        }
        
        

 
 /*NamedParameterStatement p=new NamedParameterStatement(conRsmd, query);
 p.setString("name", "bob");
 p.setString("address", "123 terrace ct");
  rs=p.executeQuery();   */     
        
			
			preparedStatement = conRsmd.prepareStatement(query);
			ArrayList listFieldValues = utilsString.getListFieldsValue();
                        for(int f=0;f<listFieldValues.size();f++)
                        {
                        preparedStatement.setString(f+1, listFieldValues.get(f)+"");
                        }
			// execute select SQL stetement
			 rs = preparedStatement.executeQuery();        
        
        
        
 //        String strPreparedstmnt = query;
        //System.out.println("Database.retrieveRSMetaData query: "+query);
 //       rs = preparedstmnt.executeQuery(strPreparedstmnt);
        rsmd = rs.getMetaData();
      //  DbConnection.releaseConnection(con);
        //System.out.println("Database.retrieveDBDataFromQuery   getRecordCount="+getRecordCount()+"     query:"+query);
     }
     catch ( SQLException sqlex)
     {
     	
     	if (sqlex.getErrorCode()==1045)
     	{
     		utilsGui.showMessageError("There is no access for this user.\nPlease change username or password.");
     	}
     	else
     	{
           
          	String error="";
              if (VariablesGlobal.globalShowAnalyticErrorMessages)  
              {
              	 error=error+"error Database.retrieveDBDataFromQuery  class="+classNameForMessage +"  "+sqlex.getErrorCode()+" msg:"+sqlex.getMessage()+"\n query: "+query;
              }
             
             System.out.println("-- error --:Database.retrieveDBDataFromQuery() "+ "error code in class:  "+classNameForMessage+"  "+sqlex.getErrorCode()+" " + sqlex.getMessage()+" sql "+query);
             
             utilsGui.showMessageError(error);             
                      
        }     	

          /*String error = "-- error --:Database.retrieveDBDataFromQuery() code: " +sqlex.getErrorCode()+" " + sqlex.getMessage()+" sql "+query;
          System.out.println(error);
          utilsGui.showMessageError(error);  */
     }
     

   }  
  
  
  // first this, then get rs and later rsmd
   public void retrieveDBDataFromQuery(String query, String classNameForMessage)
   {
   	//query = utilsString.queryReplaceWildcardWithStarInWhereClause(query);
   	
   	
     try
     {
        conRsmd = getConnection();//get from connection factory

        
        // exists in DialogQueryBrowser.updateDB  and Database.retrieveDBDataFromQuery
        if (DbConnection.getDBEngineOfConnectionFromFile()==DBENGINE_SQLITE)
        {  // SQLite only supports TYPE_FORWARD_ONLY cursors    SQLite only CONCUR_READ_ONLY
            
             stmnt = conRsmd.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); 
        }
        else
        {
            stmnt = conRsmd.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
        }
        
        if(utilsString.hasQueryWhere(query))
        {
          query =  utilsString.queryReplaceWildcardWithStarInWhereClause(query);
          //System.out.println("Database.retrieveDBDataFromQuery   query:"+query);
        }
         rs = stmnt.executeQuery(query); 
        rsmd = rs.getMetaData();
      //  DbConnection.releaseConnection(con);
        //System.out.println("Database.retrieveDBDataFromQuery   getRecordCount="+getRecordCount()+"     query:"+query);
     }
     catch ( SQLException sqlex)
     {
     	
     	if (sqlex.getErrorCode()==1045)
     	{
     		utilsGui.showMessageError("There is no access for this user.\nPlease change username or password.");
     	}
     	else
     	{
           
          	String error="";
              if (VariablesGlobal.globalShowAnalyticErrorMessages)  
              {
              	 error=error+"error Database.retrieveDBDataFromQuery  class="+classNameForMessage +"  "+sqlex.getErrorCode()+" msg:"+sqlex.getMessage()+"\n query: "+query;
              }
             
             System.out.println("-- error --:Database.retrieveDBDataFromQuery() "+ "error code in class:  "+classNameForMessage+"  "+sqlex.getErrorCode()+" " + sqlex.getMessage()+" sql "+query);
             
             utilsGui.showMessageError(error);             
                      
        }     	

          /*String error = "-- error --:Database.retrieveDBDataFromQuery() code: " +sqlex.getErrorCode()+" " + sqlex.getMessage()+" sql "+query;
          System.out.println(error);
          utilsGui.showMessageError(error);  */
     }
     

   }

   public int getRecordCount()// has to be called  only once
   {  
   	int count =0;
   	
   	try
    {
   	   while (rs.next())
   	   {
   		 count++;
   	   }
           
           rs.beforeFirst();
    }
    catch ( SQLException sqlex)    
    {
          	String error="";
              if (VariablesGlobal.globalShowAnalyticErrorMessages)  
              {
              	 error=error+"error code "+sqlex.getErrorCode()+" msg:"+sqlex.getMessage();
              }
            // utilsGui.showMessageError(error);             
           
           System.out.println("-- error --:Database.getRecordCount() "+ "error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
    	
    }
   	
   return count;
   }   
   
   public ResultSet getRS()
   {  
         return rs;
   }
   
   public ResultSetMetaData getRSMetaData()
   {
   	 return rsmd;
   }   
   

  
   public void releaseConnectionRsmd()
   {
    try
    {	  DbConnection.releaseConnection(conRsmd);   }
    catch ( SQLException sqlex)
    { System.out.println("error:Database.releaseConnectionRsmd() " + sqlex.getMessage());    }
   	  
   	  if(VariablesGlobal.globalShowReleaseOfRSMD)
     {
        System.out.println("Database.releaseConnectionRsmd()");
     }
   }

   

   public void releaseConnectionRs()
   {
    try
    {	  DbConnection.releaseConnection(conRs);   }
    catch ( SQLException sqlex)
    { System.out.println("error:Database.releaseConnectionRs() " + sqlex.getMessage());    }
     
     if(VariablesGlobal.globalShowReleaseOfRS)
     {
        System.out.println("Database.releaseConnectionRs()");
     }
   }

   
   public Connection getConnection()
   {
       try
       {
            con = DbConnection.getConnectionFromFile();
       }
       catch ( SQLException sqlex)
      {
         System.out.println("error: Database.getConnection() "+ "error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
      }
       return con;
   }


 
   
   
   
   //called by   // does NOT work when you update database 'definition' (metadata)
   public int updateQueryNotTransaction(String query, String strClassThatIsCalled, boolean showDialogOnError,boolean showWindowWait)
   {
     int ret=0;
     try
     {
            con = getConnection();
            stmnt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
       
             // System.out.println("Database.updateQuery "+query);      
        
        	 ret = stmnt.executeUpdate(query);
        	if(ret==0)
            {
                if (VariablesGlobal.globalShowSQLEdit)        
                {   	
                    System.out.println("Database.updateQuery failure   ret:"+ret+"      query:"+query);
                    utilsGui.showMessageError("Database.updateQuery failure ret:"+ret+" "+query);
                }
    	        if(wWaitTimer==null)  
    	        {
                    if(showWindowWait)
                    {
    	            wWaitTimer = new WindowWait("<html><table><tr><th>save failure<th></tr></table></html>",WINDOW_LOCATION_DOWNRIGHT,ICO_CANCEL16,null);
                    }
    	        }
    	        else
    	        {
    	        	//wWaitTimer.close();
    	        	timer.cancel();
    	        }
                    if(showWindowWait)
                    {    	        
  	        wWaitTimer.showWindow();
      	        timer = new Timer();
      	        timer.schedule(new RemindTask(), 2000);// 3000=3sec
                    }
      	        
            }
        	else if (ret ==1 )
        	{
                if (VariablesGlobal.globalShowSQLEdit)        
                {        		
                    System.out.println("Database.updateQuery success - ret="+ret+"       query:"+query);
    	        }

    	        if(wWaitTimer==null )
    	        {
                     if(showWindowWait)
                    {
    	             wWaitTimer = new WindowWait("<html><table><tr><th>saved successfuly<th></tr></table></html>",WINDOW_LOCATION_DOWNRIGHT,ICO_OK16,null); // WINDOW_LOCATION_DOWNRIGHT
                    }
    	        }
    	        else
    	        {
    	        	//wWaitTimer.close();
    	        	timer.cancel();
    	        }
    	         if(showWindowWait)
                 {
  	            wWaitTimer.showWindow();
      	        timer = new Timer();
      	        timer.schedule(new RemindTask(), 1800);// 3000=3sec
                 }
    	        
                }
        	else if(ret>1)
        	{
                if (VariablesGlobal.globalShowSQLEdit)        
                {   
                         System.out.println("Database.updateQuery success - ret="+ret+"      sql:"+query);    

    	        }
    	        
    	        if(wWaitTimer==null )
    	        {
                    if(showWindowWait)
                    {
    	                wWaitTimer = new WindowWait("<html><table><tr><th>saved successfuly<th></tr></table></html>",WINDOW_LOCATION_DOWNRIGHT,ICO_OK16,null);	
                    }
    	        }
    	        else
    	        {
    	        	//wWaitTimer.close();
    	        	timer.cancel();
    	        }
                      if(showWindowWait)
                    {  	        
  	            wWaitTimer.showWindow();
      	        timer = new Timer();
      	        timer.schedule(new RemindTask(), 1800);// 3000=3sec
                    }
        	   
            }
        // }
       // System.out.println("database.retrieveResultSet() concurency(1007 read only, 1008 updatable): "+rs.getConcurrency());
        DbConnection.releaseConnection(con);
     }
     catch ( SQLException sqlex)
     {

     	String error="";	

          System.out.println("error:  Database.updateQuery error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
          
          if (sqlex.getErrorCode()==1062)
          { 
             ret=1;
              error="There is already a record with the same data.\n It is not possible to insert a record two times!";
              if (VariablesGlobal.globalShowAnalyticErrorMessages)  
              {
              	 error=error+"\n error code "+sqlex.getErrorCode()+"\n"+sqlex.getMessage()+"\n query \n"+query;
              }
              //System.out.println("Database.updateQuery "+query);
            // utilsGui.showMessageError(error); 
          }
          else if (sqlex.getErrorCode()==1451)
          { 
              error="You can not remove a record because there is a reference in other table of the database.";
              if (VariablesGlobal.globalShowAnalyticErrorMessages)  
              {
              	 error=error+"\n error code "+sqlex.getErrorCode()+"\n"+sqlex.getMessage();
              }
             //utilsGui.showMessageError(error); 
          }
          else
          {
          	 error="";
              if (VariablesGlobal.globalShowAnalyticErrorMessages)  
              {
              	 error=error+"error code "+sqlex.getErrorCode()+"\n"+sqlex.getMessage()+"\n query \n"+query;
              }
             //utilsGui.showMessageError(error);           	
          }
     	
          try
          {
              DbConnection.releaseConnection(con);
          }
          catch(SQLException sqlex1)
          {
              System.out.println("error:  Database.updateQuery error code sqlex1: " +sqlex1.getErrorCode()+" " + sqlex1.getMessage());
          }
          
          
     	if(showDialogOnError)
     	{
            String strError = "";
            if(VariablesGlobal.globalShowMsgOnError)
            {
                strError=" from class="+strClassThatIsCalled;
            }
     		 utilsGui.showMessageError("Error "+strError+" "+error); 	
     	}// if    showDialogOnError
        
      
     }
     
     //System.out.println("-Database.updateQuery ret:"+ret);
     return ret;
   }


  
   public ResultSet retrieveRow(ResultSet rs, int absolute)
   {
      if (rs == null) return rs;
      {
      	 try
         {
            //int currentRow = rs.getRow();
           
             //for sqlite we need a) rs.next() and b) rs.beforeFirst(); because it has to go again to 1st record. But b) does not work with sqlite
            rs.absolute(absolute);
            //currentRow = rs.getRow();
            //System.out.println("Database.RetrieveRow: "+currentRow);
            if(VariablesGlobal.globalShowReadSQLRow)
            {
                System.out.println("Database.retrieveRow() "+absolute);
            }
            
            
         }
         catch(Exception e)
         {  System.out.println("Error Database.retrieveRow() " + e);
         }
         return rs;
      }
    }

   public ResultSet retrievePrevRow(ResultSet rs)
   {  
      if (rs == null) return rs;
      {
      	 try
         {
         	if (rs.previous())
            {
            	 System.out.print("prev <-"+rs.getRow());
            }
            else if (rs.first())
            {  System.out.print("first");  }
            else
            {  System.out.print("else");  }
            
         }
         catch(Exception e)
         {  System.out.println("Error Database.retrievePrevRow() " + e);
         }
         return rs;
      }
 
   }
     
   public ResultSet retrieveNextRow(ResultSet rs)
   {  
      if (rs == null) return rs;
      {
      	 try
         {
         	if (rs.next())
            {
            	 System.out.println("Database.retrieveNextRow -> "+rs.getRow());
            }
            else if (rs.last())
            {  System.out.print("last");  }
            else
            {  System.out.print("else");  }
            
         }
         catch(Exception e)
         {  System.out.println("Error Database.retrieveNextRow() "+ e);
         }
         return rs;
      }
   }
 
   public boolean isFirstRow(ResultSet rs)
   {  
   boolean isFirst=false;
      if (rs == null) return false;
      {
      	 try
         {
         	if (rs.previous())
            {
            	 isFirst=false;
            }
            else if (rs.first())
            {  isFirst=true;  }
            else
            {  isFirst=false;  }
            
         }
         catch(Exception e)
         {  System.out.println("Error Database.isFirstRow() "+ e);
         }
         return isFirst;
      }
   }
   
   public boolean isLastRow(ResultSet rs)
   {  
   boolean isLast=false;
      if (rs == null) return false;
      {
      	 try
         {
         	if (rs.next())
            {
            	 isLast=false;
            }
            else if (rs.last())
            {  isLast=true;  }
            else
            {  isLast=false;  }
            
         }
         catch(Exception e)
         {  System.out.println("Error Database.isLastRow() "+ e);
         }
         return isLast;
      }
   }
   
/*    public ResultSet deleteRow(ResultSet rs)
    {
       try
       {
        System.out.println("Database.deleteRow() concurency(1007 read only, 1008 updatable): "+rs.getConcurrency());
          
          rs.deleteRow();
          System.out.println("Database.deleteRow() deleted");
          if (!rs.isLast()) rs.next();
          else if (!rs.isFirst()) rs.previous();
          else rs = null;
       }
       catch (SQLException e)
       {  System.out.println("Error Database.deleteRow() "+" error code: "+e.getErrorCode()+" "+ e);
       }
       return rs;       
    }*/

    /*public ResultSet updateRow(ResultSet rs)
    {   	
      /* try            deprecated
       {
    	 rs.updateRow();   	 
    	 System.out.println(" updated ");
       }
       catch (SQLException e)
       {  System.out.println("Error Database.updateRow() "+" error code: "+e.getErrorCode()+" "+ e);
       }
       catch(Exception e)
       {  System.out.println("Error " + e);}
       */
    /*   System.out.println("Database.updateRow() update NOT run ");
       return rs;  
	}*/ 

    /*public ResultSet insertRow(ResultSet rs)
    {   	
       try
       {
    	 rs.insertRow();   	 
    	 System.out.println("inserted");
       }
       catch (SQLException e)
       {  System.out.println("Error Database.insertRow() "+" error code: "+e.getErrorCode()+" "+ e);
       }
       return rs;  
	} */

 
/*  public String[] getFieldNames()   {  	return fieldNames;  }

  public Object[][] getDataObjects()   {    return  dataObjects;  }*/

    class RemindTask extends TimerTask
    {
        public void run()
        {
             
              timer.cancel(); //Terminate the timer thread
              
              wWaitTimer.dispose();  
        }
    }


  // function for derby
  /*
    create function retValueAccordingToType(idType INT, pType INT, val DOUBLE)  returns Double
    PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA
    EXTERNAL NAME 'jdbc.Database.retValueAccordingToType'   
   
  
  public static double retValueAccordingToType(int idType , int pType ,double val)
  {
  	double d = 0;
    if (idType == pType)
    {
    	d = val;
    }
    else
    {
    	d = 0;
    }
    
    return d;
  }*/
  
  // function for derby
  public static double ifNull(double expr ,double ret )
  {
  	double d = 0;
    if (expr != 0)//null)
    {
    	d = expr;
    }
    else
    {
    	d = ret;
    }
    
    return d;
  }

  private static void calculateNull2version(Database db,String year)
  {
      
     // documentation h2 UPDATE PERSON P SET NAME=(SELECT A.NAME FROM ADDRESS A WHERE A.ID=P.ID);   
     // sqlite   UPDATE tbl1 SET col2 = (SELECT col2 FROM tbl2 WHERE tbl2.col1 = tbl1.col1)
      
    String sqlIWhere = "i.dbyear="+year+" AND i.dbcompanyid=1 AND i.deliveryId=1";
    String sqlAWhere = "a.dbyear="+year+" AND a.dbcompanyid=1 AND a.deliveryId=1";
    //String fieldInvoicePk = "farmerId";
    //String fieldInvoicePk2 = "invoiceNo";
    //String fieldInvoicePk3 = "invoiceTypeId";
    //String fieldInvoicePk4 = "BuyerId";
    //db.retrieveDBDataFromQuery("SELECT * FROM invoice i WHERE "+sqlIWhere, "Database");  
      
      //db.updateQuery("UPDATE invoice AS i SET i.productTypeId = null,i.productTypePercentage=null, valueReturn = null WHERE "+sqlIWhere,"Database.calculateNull2version",true);
      //db.updateQuery("UPDATE application AS a SET a.permanent =0, a.invcount= null,a.value=null, a.valueReturn=null, a.payment=null WHERE "+sqlAWhere,"Database.calculateNull2version",true);
  }
  
 

  private static void calculateNull(Database db,String year)
  {   
    try
    {
        
     // documentation h2 UPDATE PERSON P SET NAME=(SELECT A.NAME FROM ADDRESS A WHERE A.ID=P.ID);   
     // sqlite   UPDATE tbl1 SET col2 = (SELECT col2 FROM tbl2 WHERE tbl2.col1 = tbl1.col1)
   
        
     //Connection   con = db.getConnection();
     // con.setAutoCommit(false); //false);   
     
    String sqlIWhere = "i.dbyear="+year+" AND i.dbcompanyid=1 AND i.deliveryId=1";
    String fieldInvoicePk = "farmerId";
    String fieldInvoicePk2 = "invoiceNo";
    String fieldInvoicePk3 = "invoiceTypeId";
    String fieldInvoicePk4 = "BuyerId";
    
    db.retrieveDBDataFromQuery("SELECT * FROM invoice i WHERE "+sqlIWhere, "Database");
    
    ResultSet recIRS=db.getRS(); //retrieveResultSet("SELECT * FROM invoice i WHERE "+sqlIWhere);
    
    int recICount=0;
    while(recIRS.next())
    {
    	
    	//System.out.println("recICount "+recICount);
    	//db.retrieveDBDataFromQuery("SELECT pt.productTypeId, (pt.returnVat+pt.returnFuel), (i.value * (pt.returnVat+pt.returnFuel))/100 FROM invoice i, product p, producttype pt WHERE i.productid=p.productid AND pt.producttypeid=p.producttypeid AND "+fieldInvoicePk+"="+recIRS.getString(fieldInvoicePk)+" AND "+fieldInvoicePk2+"='"+recIRS.getString(fieldInvoicePk2)+"' AND "+fieldInvoicePk3+"='"+recIRS.getString(fieldInvoicePk3)+"' AND "+fieldInvoicePk4+"='"+recIRS.getString(fieldInvoicePk4)+"' AND "+sqlIWhere,"Database");
        db.retrieveDBDataFromQuery("SELECT pt.productTypeId, (pt.returnVat+pt.returnFuel), (i.value * (pt.returnVat+pt.returnFuel))/100 FROM invoice i, product p, producttype pt WHERE i.productid=p.productid AND pt.producttypeid=p.producttypeid AND "+fieldInvoicePk+"="+recIRS.getString(fieldInvoicePk)+" AND "+fieldInvoicePk2+"='"+recIRS.getString(fieldInvoicePk2)+"' AND "+fieldInvoicePk3+"='"+recIRS.getString(fieldInvoicePk3)+"' AND "+fieldInvoicePk4+"='"+recIRS.getString(fieldInvoicePk4)+"' AND "+sqlIWhere,"Database.calculateNull");
      ResultSet rs =  db.getRS();//db.retrieveResultSet("SELECT pt.productTypeId, (pt.returnVat+pt.returnFuel), (i.value * (pt.returnVat+pt.returnFuel))/100 FROM invoice i, product p, producttype pt WHERE i.productid=p.productid AND pt.producttypeid=p.producttypeid AND "+fieldInvoicePk+"="+recIRS.getString(fieldInvoicePk)+" AND "+fieldInvoicePk2+"='"+recIRS.getString(fieldInvoicePk2)+"' AND "+fieldInvoicePk3+"='"+recIRS.getString(fieldInvoicePk3)+"' AND "+fieldInvoicePk4+"='"+recIRS.getString(fieldInvoicePk4)+"' AND "+sqlIWhere);
      	rs.first();
      	 
//        db.updateQuery("UPDATE invoice AS i SET i.productTypeId = null,i.productTypePercentage=null, valueReturn = null WHERE "+fieldInvoicePk+"="+recIRS.getString(fieldInvoicePk)+" AND "+fieldInvoicePk2+"='"+recIRS.getString(fieldInvoicePk2)+"' AND "+fieldInvoicePk3+"='"+recIRS.getString(fieldInvoicePk3)+"' AND "+fieldInvoicePk4+"='"+recIRS.getString(fieldInvoicePk4)+"' AND "+sqlIWhere,"Database.calculateNull",true);
      
    	recICount++;
    	
    	
    }
    // System.out.println("Database recICount "+recICount);



    String sqlDWhere = "a.dbyear="+year+" AND a.dbcompanyid=1 AND a.deliveryId=1";
     
     db.retrieveDBDataFromQuery("SELECT * FROM application a WHERE "+sqlDWhere,"Database");
    ResultSet recDRS=db.getRS();//db.retrieveResultSet("SELECT * FROM delivery d WHERE "+sqlDWhere);
    
    int recDCount=0;
    while(recDRS.next())
    {
    	
    	//System.out.println("recDCount "+recDCount);
    	db.retrieveDBDataFromQuery("SELECT COUNT(i.value), SUM(i.value), SUM(i.valueReturn) FROM invoice i WHERE "+fieldInvoicePk+"="+recDRS.getString(fieldInvoicePk)+" AND "+sqlIWhere,"Database");
      ResultSet rsD =db.getRS();//  db.retrieveResultSet("SELECT COUNT(i.value), SUM(i.value), SUM(i.valueReturn) FROM invoice i WHERE "+fieldInvoicePk+"="+recDRS.getString(fieldInvoicePk)+" AND "+sqlIWhere);
      	rsD.first();
      	 
//        db.updateQuery("UPDATE application AS a SET a.permanent =0, a.invcount= null,a.value=null, a.valueReturn=null, a.payment=null WHERE "+fieldInvoicePk+"="+recDRS.getString(fieldInvoicePk)+" AND "+sqlDWhere,"Database.calculateNull",true);
      
    	recDCount++;
    	
    	
    }
     System.out.println("Database.calculateNull recDCount "+recDCount+"  recICount "+recICount);



    }
    catch(SQLException e)
    {
    	System.out.println("error Database.calculateNull "+e);
    }  	

  }

  
  private static void calculate2version(Database db,String year, double percentage)
  {
  
    try
    {
    String sqlIWhere = "i.dbyear="+year+" AND i.dbcompanyid=1 AND i.deliveryId=1";
    String sqlAWhere = "a.dbyear="+year+" AND a.dbcompanyid=1 AND a.deliveryId=1";
    //String fieldInvoicePk = "farmerId";
    //String fieldInvoicePk2 = "invoiceNo";
    //String fieldInvoicePk3 = "invoiceTypeId";
    //String fieldInvoicePk4 = "BuyerId";
    
    
    db.retrieveDBDataFromQuery("SELECT * FROM invoice i WHERE "+sqlIWhere,"Database");
    ResultSet recIRS=db.getRS();// db.retrieveResultSet("SELECT * FROM invoice i WHERE "+sqlIWhere);

    int recICount=0;
    while(recIRS.next())
    {

        // db.retrieveDBDataFromQuery("SELECT * FROM invoice i WHERE "+sqlIWhere,"Database");
        //db.updateQuery("UPDATE invoice AS i SET i.productTypeId = "+rs.getString(1)+",i.productTypePercentage="+rs.getString(2)+", valueReturn = "+rs.getString(3)+" WHERE "+fieldInvoicePk+"="+recIRS.getString(fieldInvoicePk)+" AND "+fieldInvoicePk2+"='"+recIRS.getString(fieldInvoicePk2)+"' AND "+fieldInvoicePk3+"='"+recIRS.getString(fieldInvoicePk3)+"' AND "+fieldInvoicePk4+"='"+recIRS.getString(fieldInvoicePk4)+"' AND "+sqlIWhere,true);
        String qWhereA = "WHERE farmerId="+recIRS.getString("farmerId")+" AND invoiceNo='"+recIRS.getString("invoiceNo")+"' AND invoiceTypeId='"+recIRS.getString("invoiceTypeId")+"' AND BuyerId='"+recIRS.getString("BuyerId")+"' AND "+sqlIWhere;
        String qWhereB = "WHERE i.productid=p.productid AND pt.producttypeid=p.producttypeid AND farmerId="+recIRS.getString("farmerId")+" AND invoiceNo='"+recIRS.getString("invoiceNo")+"' AND invoiceTypeId='"+recIRS.getString("invoiceTypeId")+"' AND BuyerId='"+recIRS.getString("BuyerId")+"' AND "+sqlIWhere+") "+qWhereA;
//          db.updateQuery("UPDATE invoice AS i SET i.productTypeId = (SELECT pt.productTypeId FROM invoice i, product p, producttype pt "+qWhereB,"Database.calculate2version", true);
//          db.updateQuery("UPDATE invoice AS i SET i.productTypePercentage = (SELECT (pt.returnVat+pt.returnFuel) FROM invoice i, product p, producttype pt "+qWhereB,"Database.calculate2version", true);
//          db.updateQuery("UPDATE invoice AS i SET i.valueReturn = (SELECT (i.value * (pt.returnVat+pt.returnFuel))/100 FROM invoice i, product p, producttype pt "+qWhereB,"Database.calculate2version", true);
    
          recICount++;
    } 
         System.out.println("");
         System.out.println("Database.calculate2version finished table invoice, starting application ");
         System.out.println("");
   
    recIRS.close();     
       db.releaseConnectionRs();
       db.releaseConnectionRsmd();
    
    db.retrieveDBDataFromQuery("SELECT * FROM application a WHERE "+sqlAWhere,"Database");
    ResultSet recDRS=db.getRS();// db.retrieveResultSet("SELECT * FROM delivery d WHERE "+sqlDWhere);
    
      int recDCount=0;
      while(recDRS.next())
      {      

//          db.updateQuery("UPDATE application AS a SET a.permanent =1 WHERE farmerId="+recDRS.getString("farmerId")+" AND "+sqlAWhere,"Database.calculate2version",true);
//          db.updateQuery("UPDATE application AS a SET a.invcount= (SELECT COUNT(i.value) FROM invoice i WHERE farmerId="+recDRS.getString("farmerId")+" AND "+sqlAWhere+") WHERE farmerId="+recDRS.getString("farmerId")+" AND "+sqlAWhere,"Database.calculate2version",true);
//          db.updateQuery("UPDATE application AS a SET a.value= (SELECT SUM(i.value) FROM invoice i WHERE farmerId="+recDRS.getString("farmerId")+" AND "+sqlAWhere+") WHERE farmerId="+recDRS.getString("farmerId")+" AND "+sqlAWhere,"Database.calculate2version",true);
//          db.updateQuery("UPDATE application AS a SET a.valueReturn= (SELECT SUM(i.valueReturn) FROM invoice i WHERE farmerId="+recDRS.getString("farmerId")+" AND "+sqlAWhere+") WHERE farmerId="+recDRS.getString("farmerId")+" AND "+sqlAWhere,"Database.calculate2version",true);
//          db.updateQuery("UPDATE application AS a SET a.payment= (SELECT SUM(i.valueReturn)*"+percentage+" FROM invoice i WHERE farmerId="+recDRS.getString("farmerId")+" AND "+sqlAWhere+") WHERE farmerId="+recDRS.getString("farmerId")+" AND "+sqlAWhere,"Database.calculate2version",true);
          
          recDCount++;
      }
      recDRS.close();
       db.releaseConnectionRs();
       db.releaseConnectionRsmd();      
    }
    catch(SQLException e)
    {
       db.releaseConnectionRs();
       db.releaseConnectionRsmd();
    	System.out.println("error Database.calculate2version "+e);
    } 
    
   }

  private static void calculate(Database db,String year, double percentage)
  {
    try
    {
    String sqlIWhere = "i.dbyear="+year+" AND i.dbcompanyid=1 AND i.deliveryId=1";
    String fieldInvoicePk = "farmerId";
    String fieldInvoicePk2 = "invoiceNo";
    String fieldInvoicePk3 = "invoiceTypeId";
    String fieldInvoicePk4 = "BuyerId";
    
    db.retrieveDBDataFromQuery("SELECT * FROM invoice i WHERE "+sqlIWhere,"Database");
    ResultSet recIRS=db.getRS();// db.retrieveResultSet("SELECT * FROM invoice i WHERE "+sqlIWhere);

    int recICount=0;
    while(recIRS.next())
    {
    	
    	//System.out.println("recICount "+recICount);
    	db.retrieveDBDataFromQuery("SELECT pt.productTypeId, (pt.returnVat+pt.returnFuel), (i.value * (pt.returnVat+pt.returnFuel))/100 FROM invoice i, product p, producttype pt WHERE i.productid=p.productid AND pt.producttypeid=p.producttypeid AND "+fieldInvoicePk+"="+recIRS.getString(fieldInvoicePk)+" AND "+fieldInvoicePk2+"='"+recIRS.getString(fieldInvoicePk2)+"' AND "+fieldInvoicePk3+"='"+recIRS.getString(fieldInvoicePk3)+"' AND "+fieldInvoicePk4+"='"+recIRS.getString(fieldInvoicePk4)+"' AND "+sqlIWhere,"Database");
        ResultSet rs =db.getRS();//  db.retrieveResultSet("SELECT pt.productTypeId, (pt.returnVat+pt.returnFuel), (i.value * (pt.returnVat+pt.returnFuel))/100 FROM invoice i, product p, producttype pt WHERE i.productid=p.productid AND pt.producttypeid=p.producttypeid AND "+fieldInvoicePk+"="+recIRS.getString(fieldInvoicePk)+" AND "+fieldInvoicePk2+"='"+recIRS.getString(fieldInvoicePk2)+"' AND "+fieldInvoicePk3+"='"+recIRS.getString(fieldInvoicePk3)+"' AND "+fieldInvoicePk4+"='"+recIRS.getString(fieldInvoicePk4)+"' AND "+sqlIWhere);
      	rs.first();
      	 
//        db.updateQuery("UPDATE invoice AS i SET i.productTypeId = "+rs.getString(1)+",i.productTypePercentage="+rs.getString(2)+", valueReturn = "+rs.getString(3)+" WHERE "+fieldInvoicePk+"="+recIRS.getString(fieldInvoicePk)+" AND "+fieldInvoicePk2+"='"+recIRS.getString(fieldInvoicePk2)+"' AND "+fieldInvoicePk3+"='"+recIRS.getString(fieldInvoicePk3)+"' AND "+fieldInvoicePk4+"='"+recIRS.getString(fieldInvoicePk4)+"' AND "+sqlIWhere,"Database.calculate",true);
      
    	recICount++;
    	
    	
    }
     //System.out.println("Database recICount "+recICount);



    String sqlDWhere = "a.dbyear="+year+" AND a.dbcompanyid=1 AND a.deliveryId=1";
    
    db.retrieveDBDataFromQuery("SELECT * FROM application a WHERE "+sqlDWhere,"Database");
    ResultSet recDRS=db.getRS();// db.retrieveResultSet("SELECT * FROM delivery d WHERE "+sqlDWhere);
    
    int recDCount=0;
    while(recDRS.next())
    {
    	
    	//System.out.println("recDCount "+recDCount);
    	db.retrieveDBDataFromQuery("SELECT COUNT(i.value), SUM(i.value), SUM(i.valueReturn) FROM invoice i WHERE "+fieldInvoicePk+"="+recDRS.getString(fieldInvoicePk)+" AND "+sqlIWhere,"Database");
        ResultSet rsD =db.getRS();//   db.retrieveResultSet("SELECT COUNT(i.value), SUM(i.value), SUM(i.valueReturn) FROM invoice i WHERE "+fieldInvoicePk+"="+recDRS.getString(fieldInvoicePk)+" AND "+sqlIWhere);
      	rsD.first();
      	 
//        db.updateQuery("UPDATE application AS a SET a.permanent =1, a.invcount= "+rsD.getString(1)+",a.value="+rsD.getString(2)+", a.valueReturn="+rsD.getString(3)+", a.payment="+rsD.getDouble(3)*percentage+" WHERE "+fieldInvoicePk+"="+recDRS.getString(fieldInvoicePk)+" AND "+sqlDWhere,"Database.calculate",true);
      
    	recDCount++;
    	
    }
     System.out.println("Database.calculate recDCount "+recDCount+"  recICount "+recICount);


    }
    catch(SQLException e)
    {
    	System.out.println("error Database.calculate "+e);
    }  	
  	
  }

  /*private static void setAutoCommit(Connection connect,boolean autocommit)
  {
      try
      {
          
          connect.setAutoCommit(autocommit);
      }   
      catch (SQLException ex)
        {
            System.out.println("error: Database.setAutoCommit autocommit:"+autocommit+" "+ex.getMessage());
            ex.printStackTrace();
        } 
        
  }*/

  public void setAutoCommit(boolean b)
  {
    try
    {
      con.setAutoCommit(b); 
    }
    catch (SQLException ex)
    {
            
            System.out.println("Database.setAutoCommit "+ex.getMessage());
            
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           ex.printStackTrace();     
         }
            try
            {
                //error occured so we rollback the changes.
                con.rollback();
                System.out.println("Database.setAutoCommit error occured, rollback changes.");
            }
            catch (SQLException ex1) 
            {
                System.out.println("Database.setAutoCommit "+ex1.getMessage());
                
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           ex1.printStackTrace();     
         }                
              
            }
    }   
  }
  
 
 /* public boolean commit(Database dbc)
  {
      boolean ret = false;
    try
    {
        Connection connection = dbc.getConnection();
      connection.commit();
      ret=true;
           
   } catch (SQLException ex)
        {
            
            System.out.println("Database.commit "+ex.getMessage());
            ex.printStackTrace();
            try
            {
                //error occured so we rollback the changes.
                con.rollback();
                System.out.println("Database.commit error occured, rollback changes.");
            } catch (SQLException ex1) 
            {
                System.out.println("Database.commit "+ex1.getMessage());
                ex1.printStackTrace();
            }
         ret=false;   
        }   
    return ret;
  }*/
  private void updateDbWithCommit(Connection cone)
  {
     

       String systemDirectorySymbol=System.getProperty("file.separator");
      String   curDir="";
      /*if(utilsOS.isOSWindows())
      {*/
      	curDir = System.getProperty("user.dir"); // get current dir
        System.out.println(curDir);
      /*}
      else
      {
      	String classPath = System.getProperty("java.class.path");
      	curDir = classPath.substring(0,classPath.lastIndexOf(systemDirectorySymbol));	
      }*/
        
        VariablesGlobal.globalDirConfiguration = curDir;
   try
   {
        cone.setAutoCommit(false);
        
//       this.updateQuery("UPDATE farmer SET surname = 'ΓΙΑΝΤΣ' WHERE Name = 'ΝΙΚΟΛΑΟΣ'","Database.updateDbWithCommit",false);
        
        cone.commit();
        cone.setAutoCommit(true);
        
        
  } catch (SQLException ex)
        {
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           ex.printStackTrace();     
         }
            
            try {
                //An error occured so we rollback the changes.
                cone.rollback();
            } catch (SQLException ex1)
            {
            
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           ex1.printStackTrace();     
         }                
            }
        }    
      
      
      
  }        
  
  
  
  // SELECT * FROM INVOICE where farmerid = 317 ORDER BY farmerId, invoiceNo, value, productId, buyerId, date
  // update invoice set invoiceNo='i5' where farmerId like 17 AND  invoiceNo like 'inv18' AND date like '2006-12-18'
  // to fill 'aa' column with inc numbers
  private void setAutomaticAA(Connection cone, String stryear, String strDbCompanyId)    
  {   
      ArrayList listFarmerId= new ArrayList();
      ArrayList listInvoiceNo= new ArrayList();
      ArrayList listValue= new ArrayList();
      
      
      
      String orderby = " ORDER BY farmerId, invoiceNo, value, productId, buyerId, date";
      
      try
      {
        Statement stmnt1 = cone.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
         //System.out.println("Database.retrieveRSMetaData query: "+query);
       ResultSet rs1 = stmnt1.executeQuery("select farmerId, invoiceNo, value from invoice WHERE dbCompanyId like "+strDbCompanyId+" AND dbYear like "+stryear+orderby);
        //rsmd = rs.getMetaData();
      int rowCount=0; 
      while(rs1.next())
      {
          listFarmerId.add(rs1.getString(1));
          listInvoiceNo.add(rs1.getString(2));
          listValue.add(rs1.getString(3));
         
          rowCount++;
      }
      
      
      System.out.println("Database.setAutomaticAA rowCount "+rowCount);

      
      
      
      //rs1.first();
      int aa=1;
      for(int r = 0;r<rowCount; r++) // // 
      {
          
            
          //   String q0 = "update invoice set invoiceNo = 'inv"+r+"';// WHERE farmerId LIKE 317";
         //    System.out.println(q0);
          //   this.updateQuery(q0, false);
          
          
       
          
          
          
          
         // Statement stmnt2 = cone.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
         // String q2 = "select farmerId from invoice WHERE dbCompanyId like 1 AND dbYear like 2006 AND farmerId like "+rec1.get(r).toString();
         // ResultSet rs2 = stmnt2.executeQuery(q2);
         // rs2.first();
         // if(rec1.get(r).toString().equalsIgnoreCase(rs2.getInt(1)+""))
         // {
              
        if(r!=rowCount-1)
        {
                    
          //System.out.println("Database.setAutomaticAA BEF row ="+r+" rowCount "+rowCount);
          if(listFarmerId.get(r).toString().equalsIgnoreCase(listFarmerId.get(r+1).toString()))
          {

                 
                 
             String q2="";  
              
        if(r==0)      
        {
             //aa=1; 
              q2 = "UPDATE invoice SET aa="+aa+"  WHERE farmerId LIKE "+listFarmerId.get(r).toString()+" AND invoiceNo LIKE '"+listInvoiceNo.get(r).toString()+"' AND value like '"+listValue.get(r).toString()+"' AND dbCompanyId like 1 AND dbYear like "+stryear;
            //System.out.println("Database.setAutomaticAA IFA row ="+r+" farmerid="+listFarmerId.get(r)+" "+listFarmerId.get(r+1)+"  "+q2);  //rs2.getString(1));
           aa++;
            
            
        }
        else 
        {
           
            //aa++;
            
            q2 = "UPDATE invoice SET aa="+aa+" WHERE farmerId LIKE "+listFarmerId.get(r).toString()+" AND invoiceNo LIKE '"+listInvoiceNo.get(r).toString()+"' AND value like '"+listValue.get(r).toString()+"' AND dbCompanyId like 1 AND dbYear like "+stryear;
            //System.out.println("Database.setAutomaticAA IFB row ="+r+"  farmerid="+listFarmerId.get(r)+" "+listFarmerId.get(r+1)+"  "+q2);  //rs2.getString(1));
            
            aa++;
        }
                           
      
//             this.updateQuery(q2,"Database.setAutomaticAA", false);    
                  
              
                 
           
          }
          else
          {
              
              String q3 = "UPDATE invoice SET aa="+aa+" WHERE farmerId LIKE "+listFarmerId.get(r).toString()+" AND invoiceNo LIKE '"+listInvoiceNo.get(r).toString()+"' AND value like '"+listValue.get(r).toString()+"' AND dbCompanyId like 1 AND dbYear like "+stryear;
             // System.out.println("Database.setAutomaticAA ELSE1 row ="+r+"   ( r+1 "+listFarmerId.get(r+1)+")      farmerid="+listFarmerId.get(r)+" aa"+aa+" "+q3);//rs2.getString(1));
                 
//                 this.updateQuery(q3, "Database.setAutomaticAA", false);
              
              aa=1;   
              
              

                 
                 
              
           
          }
         }
        else  //r==rowCount
        {
            
            String q4 = "UPDATE invoice SET aa="+aa+" WHERE farmerId LIKE "+listFarmerId.get(r).toString()+" AND invoiceNo LIKE '"+listInvoiceNo.get(r).toString()+"' AND value like '"+listValue.get(r).toString()+"' AND dbCompanyId like 1 AND dbYear like "+stryear;
            //System.out.println("Database.setAutomaticAA  r ="+r+"= rowCount "+rowCount+" farmerid="+listFarmerId.get(r)+" aa"+aa+"   "+q4);  //rs2.getString(1));
            
            
//            this.updateQuery(q4,"Database.setAutomaticAA", false);
        }
                 
                 
              
       
 
      
      }
      

     
      
      } catch (SQLException ex1) 
      {
          System.out.println("Database.setAutomaticAA "+ex1.getMessage());
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           ex1.printStackTrace();     
         }
               
      }
      
      
  }
  
  
  
  public static void main(String[] args)
  {  //test
 
/*       look for
 *       db.setAutoCommit(false);
 *       Connection connection = db.getConnection();
 *       connection.commit();
 *       db.setAutoCommit(true);
 * 
 */      
      

     Database db = new Database();
     Connection con = db.getConnection();
     db.setAutoCommit(false);
     
     
     
     //db.retrieveTables();
     
    //db.updateDbWithCommit(db.con);
      
    //db.setAutomaticAA(db.con,"2006","1");// year, dbCompanyId 
  
    //---------------------------------
    //db.calculateNull(db,"2005");
     //db.calculate(db,"2005",0.15);
    //db.calculateNull(db,"2006");
     //db.calculate(db,"2005",0.10); 
    //---------------------------------
    //db.calculateNull2version(db,"2005");
    //db.calculate2version(db,"2005",0.10);
    //db.calculateNull2version(db,"2006");
    //db.calculate2version(db,"2006",0.10);
    //---------------------------------
     
    try
    {
     con.commit();
    }
    catch(SQLException e)
    {
        System.out.println("Database.main SQLException"+e.getMessage());
    }
    db.setAutoCommit(true);
  
  

    
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