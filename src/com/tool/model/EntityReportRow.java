// created 31-10-2009
package com.tool.model;

import com.tool.guicomps.*;
import com.tool.jdbc.*;
import com.tool.utils.*;
import com.tool.gui.*;


    public class EntityReportRow
    {
    	private RecColumn[] recColumn;
    	private String[] fieldValues;
    	private EntityReportBand entityReportBand;
        private int intOfEntityReportBand;// in ReportAreaGenerated, this is the no of the band
    	private int recNoOfGroup; // no of group record,     in ReportAreaGenerated, this is the record no 
    	
        public EntityReportRow(RecColumn[] recColumnIn, String[] fieldValuesIn, int intOfEntityReportBandIn, EntityReportBand entityReportBandIn, int recNoOfGroupIn)
        {
        	recColumn=recColumnIn;
        	fieldValues=fieldValuesIn;
        	intOfEntityReportBand=intOfEntityReportBandIn;
        	entityReportBand = entityReportBandIn;
        	recNoOfGroup=recNoOfGroupIn;
        }    	
        	
        	
        public RecColumn[] getRecColumn()  { return recColumn ;}
        public String[] getFieldValues()  { return fieldValues ;}
        public int getIntOfEntityReportBand()  { return intOfEntityReportBand ;}  // in ReportAreaGenerated, this is the no of the band
        public EntityReportBand getEntityReportBand() {return entityReportBand;}
        public int getRecordIntNoOfBand() {return recNoOfGroup;}  // no of group record,     in ReportAreaGenerated, this is the record no 
    	
    }