// created 24-04-2007
package com.tool.guicomps;

import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

  // limiting the capacity of jtextfield and limiting the format only in numbers
  public class PlainDocumentInsertText extends PlainDocument 
  {
  	int maxSize;
  	String type;
  	
    public PlainDocumentInsertText(int limit,String typeIn)
    {
            maxSize = limit;
            type=typeIn;
            /*if(maxSize==-1)
            {
            	maxSize=50;// for example for TableCellEditorLookupB in PanelODMRData 
            }*/
    }  
    
  	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
  	{
			String acceptedChars ="";
			
			//System.out.println("PlainDocumentInsertText.insertString "+type);		
			if (type.equalsIgnoreCase("java.lang.Integer"))
			{
				acceptedChars = "0123456789";
			}
			if (type.equalsIgnoreCase("java.lang.Date") || type.equalsIgnoreCase("java.sql.Date")  )
			{
				acceptedChars = "0123456789";
			}
   		    if (type.equalsIgnoreCase("java.lang.Double"))
			{
				acceptedChars = "0123456789.,";
			}
			
			
			if (str == null)
				return;
			if (offset >= maxSize)
			{
                            //System.out.println("PlainDocumentInsertText.insertString offset >= maxSize");
				return;
			}
			// does the insertion exceed the max length
			if (str.length() > maxSize)
			{
                           // System.out.println("PlainDocumentInsertText.insertString str.length() > maxSize");
				str = str.substring(0, maxSize);
			}
			if (acceptedChars.equalsIgnoreCase(""))
			{
                            //System.out.println("PlainDocumentInsertText.insertString acceptedChars.equalsIgnoreCase");
				//System.out.println("String.");
				super.insertString(offset, str, attr);
				return;
			}	
			for (int i = 0; i < str.length(); i++)
			{
				if (acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1)
                                {
                                        //System.out.println("PlainDocumentInsertText.insertString acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1");
					return;
                                }
			}
                        
                        //System.out.println("PlainDocumentInsertText.insertString super.insertString("+offset+", "+str+", "+attr);
                        
			super.insertString(offset, str, attr);
	}
   
  }