/*  Table object. Responsible for holding attributes of tables (name, PKs, FKs, indexes)
*/
package com.tool.jdbc;

public class Table
{  

   public Table(String nameIn)
   {
      name=nameIn; //PKs=PKsIn; FKs=FKsIn;
   
   }

   public Table(String nameIn, String[] PKsIn, String[] FKsIn)
   {
      name=nameIn; PKs=PKsIn; FKs=FKsIn;
   
   }
   
   public String getName()  {  return name;  }
   public String[] getPKs()  {  return PKs;  }
   public String[] getFKs()  {  return FKs;  }
   

   private String name;
   private String[] PKs;
   private String[] FKs;
}
   
