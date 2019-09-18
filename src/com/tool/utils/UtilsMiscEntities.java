// created 18-12-2009
package com.tool.utils;

import  com.tool.model.*;
import com.tool.guicomps.*;

 public class UtilsMiscEntities implements Constants
 {
  	
    public UtilsMiscEntities()
    {

    }

  public EntityExportFileType[] getExportToFileEntities()
  {
  	 
  	    
  	    String[] strColSep={"|","|","|"};
  	    boolean[] hasHeadFootExported={true,true,true};
  	    
  	    EntityExportFileType[] eeft = new EntityExportFileType[6];
        eeft[0] = new EntityExportFileType("text", "text",".txt",strColSep,hasHeadFootExported);
        eeft[1] = new EntityExportFileType("html", "html",".html",null,hasHeadFootExported);
        eeft[2] = new EntityExportFileType("xml", "xml",".xml",null,hasHeadFootExported);
        eeft[3] = new EntityExportFileType("excel", "MS Excel",".xls",null,hasHeadFootExported);
        eeft[4] = new EntityExportFileType("oocalc", "OpenOffice Calc",".ods",null,hasHeadFootExported);
        eeft[5] = new EntityExportFileType("pdf", "pdf",".pdf",null,hasHeadFootExported);

  	return eeft;
  }
 
 }