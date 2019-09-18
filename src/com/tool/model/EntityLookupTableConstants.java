/*  created 20013-11-02

 */

package com.tool.model;

import com.tool.jdbc.*;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author sun
 */
public class EntityLookupTableConstants 
{
    
        private String name;
        //private String pkName;
        private EntityLookupTableConstantsData[] entitiesLookupTableConstantsData;
        
      public EntityLookupTableConstants(String nameIn, EntityLookupTableConstantsData[] entitiesLookupTableConstantsDataIn)
      {
        name=nameIn;
       // pkName=pkNameIn;
       
       if(entitiesLookupTableConstantsDataIn==null)
       {
        
           String sql = "SELECT lookupconstantsId, constantstypeId, constantstypename, constantsorder, name, notes FROM lookupconstants WHERE constantstypename LIKE '"+name+"'";
           Database db = new Database();
           db.retrieveDBDataFromQuery(sql, "EntityLookupTableConstants");
           ResultSet rs = db.getRS();
         try
       {            int rcCount = db.getRecordCount();
           
       EntityLookupTableConstantsData [] luTCData = new EntityLookupTableConstantsData[rcCount];
       
       rs.beforeFirst();

       //for (int r=1;r<=rcCount; r++)
       int r =0;
       while(rs.next())
       {
       //public EntityLookupTableConstantsData(String pkIn,int orderIn, String titleIn)
       luTCData[r]=new EntityLookupTableConstantsData(rs.getString("lookupconstantsId"),rs.getString("constantstypeId"),rs.getString("constantstypename"),rs.getInt("constantsorder"),
               rs.getString("name"),rs.getString("notes"));
       //System.out.println("EntityLookupTableConstants "+name+"  "+ rs.getString("name") +"  "+r+"   rcCount:"+rcCount);
       r++;
       //luTCDataVatExclusion[1]=new EntityLookupTableConstantsData("2",2,"μειωμένο");
       //luTCDataVatExclusion[2]=new EntityLookupTableConstantsData("3",3,"απαλλασσόμενο"); 
       }
       
       
       
       entitiesLookupTableConstantsData=luTCData;       
       }
       catch(SQLException e)
       {
           System.out.println("EntityLookupTableConstants "+e.getMessage());
           e.printStackTrace();
       }
       finally
       {
           
           db.releaseConnectionRs();
       }
       
       }
       else
       {
       
        entitiesLookupTableConstantsData=entitiesLookupTableConstantsDataIn;
       }
      
      }        

      
      
        /*public String getPkName()
        {
           return pkName;
        } */      
      
      
        public String getName()
        {
           return name;
        }    
        
        public EntityLookupTableConstantsData[] getEntitiesLookupTableConstantsData()
        {
           return entitiesLookupTableConstantsData;
        }          
}
