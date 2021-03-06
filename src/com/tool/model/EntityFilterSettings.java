
package com.tool.model;

public class EntityFilterSettings
{
	public String caption;
	public String type; // normal,LookUp, table selection
	public String variableType;// if it is string,date,int,double,boolean
	public String equivalence;// equal, greater, lesser, fromto
	public String dbField; // townId
	//public String dbTable;// town
        private String luname;// lookup name
	public String dbForeignTable;// farmer  // used for checkbox
	public String value;
	public int groupOfComps;
	public int filterFromSelectedField; //  for example we set here 0(companyid) when field is dbyear
	public int forEntityReportGroup;//  -1 for both(I think is for old report system, now is -1)
        private int fieldObligatoryOrSuggest;    // FIELD_NOCOMPLETION = 0  FIELD_OBLIGATORY = 1  FIELD_SUGGEST = 2;
        
      public EntityFilterSettings(String captionIn,String typeIn, String variableTypeIn, String equivalenceIn, 
      	String dbFieldIn, String lunameIn,String dbForeignTableIn, String valueIn, int groupOfCompsIn, 
      	int filterFromSelectedFieldIn,int forEntityReportGroupIn, int fieldObligatoryOrSuggestIn)
      {
      
	    caption=captionIn;
	    type=typeIn;
	    variableType=variableTypeIn;
	    equivalence=equivalenceIn;
	    dbField=dbFieldIn;
	    luname=lunameIn;
	    dbForeignTable=dbForeignTableIn;
        value=valueIn;
        groupOfComps =groupOfCompsIn;
        filterFromSelectedField=filterFromSelectedFieldIn;
        forEntityReportGroup=forEntityReportGroupIn;
        fieldObligatoryOrSuggest=fieldObligatoryOrSuggestIn;
      }

        public String toString()
        {
            return dbField;
        }

      public void setValue(String valueIn)
      {
      	value=valueIn;
      }

      
      public String getValue()
      {
      	if(value==null)
      	{
      		value="";
      	}
      	return value;
      }
  
	 public void setCaption(String captionIn) {caption=captionIn;} 
	 public void setType(String typeIn) {type=typeIn;}   
	 public void setVariableType(String variableTypeIn) {variableType=variableTypeIn;}
	 public void setEquivalence(String equivalenceIn) {equivalence=equivalenceIn;}
	 public void setDbField(String dbFieldIn) {   dbField=dbFieldIn; }
     public void setLookUpName(String name) {   	luname=name;   }
     public void setDbForeignTable(String ftable)   {    	dbForeignTable=ftable;    }
     
     
     public String getTableFromLookUpName(String luname)
     {
         LookUpMgt lookup = new LookUpMgt();
         return lookup.getFromTheNameTheForeignTable(luname);
     }
     
     /*public String getTableFieldFromLookUpName(String luname)
     {
         LookUpMgt lookup = new LookUpMgt();
         return lookup.getFromTheNameTheField(luname);
     }*/     
     
      public String getCaption() { return caption; } 
      public String getLookUpName() {return luname;}
      public int getForEntityReportGroup() { return forEntityReportGroup; } 
      public int getFieldObligatoryOrSuggest() {return fieldObligatoryOrSuggest;}
      public String getVariableType() { return variableType; } 
}
