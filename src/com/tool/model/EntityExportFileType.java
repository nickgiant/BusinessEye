// created 18-12-2009
package com.tool.model;

import com.tool.gui.*;
import com.tool.guicomps.*;

    public class EntityExportFileType
    {
    	
        public String name;
        public String caption;
        public String extension;
        public String [] colSeparationString;
        public boolean[] headFootExported; //is make everything null
        
    
        public EntityExportFileType(String nameIn,String captionIn,String extensionIn, String[] colSeparationStringIn,
        boolean[] headFootExportedIn)
        {
         name=nameIn;
         caption=captionIn;
         extension=extensionIn;  
         colSeparationString=colSeparationStringIn;
         headFootExported=headFootExportedIn;

        }   
         
        public String toString()
        {
        	String ret="";
        /*	if(subTitle!=null && !subTitle.equalsIgnoreCase(""))
        	{
               ret= caption+", "+subTitle;
               //ret="<html><b>"+caption+"</b>, "+subTitle+"</html>";
               // ret="<html><b>"+caption+"</b><br>"+subTitle+"</html>";

            }
            else
            {*/
            	ret = caption;
         /*   	//ret="<html><b>"+caption+"</b></html>";
            	
            }*/
          return ret;
        }

        public String getName()  {   return name;  }
        public String getCaption()  {   return caption;  }
        public String getExtension()  {   return extension;  }        
        public String[] getColSeparationString()  {   return colSeparationString;  }  
        public boolean[] getHeadFootExported()  {   return headFootExported;  }
        
     }      