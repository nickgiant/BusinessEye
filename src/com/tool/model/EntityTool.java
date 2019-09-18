/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.model;

/**
 *
 * @author small
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class EntityTool {
         private String name;
        private String caption;
        private String subTitle;
        private JPanel pnl;
        
        
 public EntityTool(String nameIn,String captionIn,String subTitleIn, JPanel pnlIn)
        {
         name=nameIn;
         caption=captionIn;
         subTitle=subTitleIn;      
         pnl = pnlIn;
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
        public String getSubTitle()  {   return subTitle;  }   
         public JPanel getPanel()  {   return pnl;  }    
}
