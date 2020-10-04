// created 01-11-08
// look at http://www.jroller.com/santhosh/entry/browse_idiom_made_easier

package com.tool.guicomps;

import com.tool.utils.*;
import com.tool.gui.*;




import java.text.*;
import java.util.*;

import java.util.Date;
import javax.swing.text.MaskFormatter;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.AbstractDocument;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.awt.*;

import java.awt.event.*;

public class JTextBoxWithEditButtons extends JComponent implements ActionListener, Constants
{
	
    private JButton btnEdit1 = new JButton();//"<html><b>F4</b></html>"); 
    private JButton btnEdit2 = new JButton();//"<html><b>F4</b></html>"); 
    private JButtonListMenu btnEditList ; 
    public JTextComponent txtComp;
    private JxPanel panel = new JxPanel();
    private JxPanel panelBtns = new JxPanel(); 

    private final int LOOKUP_TYPE_DATE =1;
    private final int LOOKUP_TYPE_DIRECTORY =2;
    private WindowSelectDate winDate;
    private boolean isVisibleWinDate = false;
    private JLabel lblDateName;
    private UtilsDate utilsDate;
    private Color colBack;
	private Color colFore;
	//private String yearEnforce;
        private String enforceDateFrom;
        private String enforceDateTo;
	//private String strLabelEmptyDateName = "___";
	String dateFormat="";
	char placeholderChar = '.';
	private String dateMask= "";
	String emptyDate ="";
        String emptyDateDefault = "..-..-....";
	private int type;
	private int dateOnlyOrFromTo;
  public JTextBoxWithEditButtons()
  {
        // if it is date do not forget to call .setYearEnforce(yearEnforce);
        // do not erase
  	txtComp=  new JTextField();
        btnEdit1 = new JButton();//"<html><b>F4</b></html>"); 
        btnEdit2 = new JButton();
  } 
    
  public JTextBoxWithEditButtons( JTextComponent txt, boolean showFirstButton,ImageIcon img1,Action a1,
   boolean showSecondButton, ImageIcon img2,/*ArrayList listMenuCaption, */ /*ArrayList listMenuActionShowDialog,*/Action a2, int typeIn,
   JFrame frame, String enforceDateFromIn,String enforceDateToIn, int dateOnlyOrFromToIn)
  {
    // needs to getComponent in order to add it to a panel
    
      
      lblDateName = new JLabel("___"); // also in UtilsDate
        
     
              JPopupMenu popupMenuTools = new JPopupMenu("----");
         btnEditList = new JButtonListMenu("",popupMenuTools);
      
      txtComp=  new JTextField();
      btnEdit1 = new JButton();

       type=typeIn;
       txtComp= txt;
       //yearEnforce= yearEnforceIn;
       enforceDateFrom=enforceDateFromIn;
       enforceDateTo = enforceDateToIn;
       dateOnlyOrFromTo=dateOnlyOrFromToIn;
       utilsDate = new UtilsDate();
       utilsDate.readFromFileDateFormats();
       
       panel.setOpaque(false);
  	   panel.setLayout(new BorderLayout());
  	   FlowLayout fl = new FlowLayout();
  	   fl.setVgap(0);
  	   fl.setHgap(0);
  	   panelBtns.setLayout(fl);//new BoxLayout(panelBtns,BoxLayout.LINE_AXIS ));
  	   //panelBtns.setBackground(txt.getBackground());
       //txt.setHorizontalAlignment(SwingConstants.LEFT);
       btnEdit1.addActionListener(this); 
       
       Action actionShowDate = new ActionShowDate(enforceDateFromIn , enforceDateToIn );
       Action actionShowWindowDir = new ActionShowWindowDir();

      if (type == LOOKUP_TYPE_DATE)
      {

       //Action actionShowDate = new ActionShowDate();
       txtComp.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "showWindowDate"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
       txtComp.getActionMap().put("showWindowDate", actionShowDate);
       //txtComp.setPreferredSize(new Dimension(80,txtComp.getHeight()));

      }
      else if(type==LOOKUP_TYPE_DIRECTORY)
      {       
         //Action actionShowWindowDir = new ActionShowWindowDir();
         txtComp.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "showWindowDir"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
         txtComp.getActionMap().put("showWindowDir", actionShowWindowDir);
      }
      
      final int typeFinal=type;
      final JTextComponent txtCompFinal = txtComp;
      txtComp.addFocusListener(new FocusListener()
      {
          public void focusLost(FocusEvent e)
          {
           
           if(isDateEmpty() || txtCompFinal.getText() == null)
           {
               	setEmptyDate(); // if nothing in text then nothing in label
                lblDateName.setBackground(colBack);
        	    lblDateName.setForeground(colFore);
           }

           if (typeFinal == LOOKUP_TYPE_DATE && !getIsDateValid())
           {
           	  txtCompFinal.requestFocus();
           }           
          	 /*  if (typeFinal == LOOKUP_TYPE_DATE && winDate.isVisible())
               {
                   //winDate.setVisible(false);
                   winDate.dispose();
                   //isVisibleWinDate = false;
                
                   //txtComp.setText(winDate.getDate());
                   //System.out.println("focusLost "+txtComp.getText());       
               }
               else
               {
               }*/
          }
          
          public void focusGained(FocusEvent e)  
          { 
                        /*System.out.println("JTextBoxWithEditButtons.JTextBoxWithEditButtons "+txtCompFinal.getText()+"    "+isDateEmpty());
                    	if(isDateEmpty())
                        {            
                        }    
                        else
                        {*/
                   txtCompFinal.setSelectionStart(0);
                   txtCompFinal.setSelectionEnd(txtCompFinal.getText().length());
                     //   }
          }             
      });
      
          
          lblDateName.setVisible(false);
          lblDateName.setBorder(UIManager.getBorder("TextField.border"));
          lblDateName.setOpaque(true);
              
      
      	   	 colBack =lblDateName.getBackground();
	   	     colFore =lblDateName.getForeground();
      
         if (type == LOOKUP_TYPE_DATE)
         {  
         	
     dateFormat = utilsDate.getDateFormatEditing();    	
         	
    //SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
    dateMask = dateFormat.replace('d','#'); // "##-##-####";
    dateMask = dateMask.replace('D','#'); 
    dateMask = dateMask.replace('m','#');
    dateMask = dateMask.replace('M','#'); 
    dateMask= dateMask.replace('Y','#');
    dateMask= dateMask.replace('y','#'); 
    
    emptyDate=dateMask.replace('#','.'); //"..-..-....";
    
    MaskFormatter mask = null;
    try
    {
      mask = new MaskFormatter(dateMask);
      mask.setPlaceholderCharacter(placeholderChar);
    }
    catch(ParseException pe)
    {
      System.out.println("JTextBoxWithEditButtons PE: " + pe.getMessage());
    }
     txtComp = new JFormattedTextField(mask);
    //JFormattedTextField textFormattedDate = new JFormattedTextField();
    txtComp.setPreferredSize(new Dimension(67,txtComp.getHeight()));
   

    //txtComp.setValue(formatter.format(new Date()));
    //textFormattedDate.setValue("01-01-2010");         	
 
   

        	
         	
         	
                lblDateName.setVisible(true);
           
                this.getDocument().addDocumentListener(new DocumentListenerDateText());                 
         }        
      
       //textComp.registerKeyboardAction(a, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED); 


        if (!txt.isEnabled())
        {
        	btnEdit1.setEnabled(false);
        }
        
        btnEdit1.setVisible(showFirstButton);
        //btnEdit2.setVisible(showSecondButton);
        //if(isSecondButtonList)
        //{
            
       // }
       // else
       // {
       btnEdit2.setVisible(showSecondButton);
       //btnEditList.setVisible(showSecondButton);
            //btnEdit2.setVisible(false);
       // }
        
        if(showFirstButton)
        {
              if (type == LOOKUP_TYPE_DATE)
              {
                  btnEdit1.setToolTipText("επιλογή ημερομηνίας");
              }
              else if(type==LOOKUP_TYPE_DIRECTORY)
              { 
              	  btnEdit1.setToolTipText("επιλογή καταλόγου");
              }
              else
              {
              	 btnEdit1.setToolTipText("εμφάνιση πίνακα για επιλογή");
              }
        	
        }
        
        if(showSecondButton)
        {
        	//btnEdit2.setToolTipText("εμφάνιση ιδιοτήτων επιλεγμένης εγγραφής");
                btnEditList.setToolTipText("μενού ενεργειών επιλεγμένης εγγραφής");
        }
        
        
        // ui-tweaking 
        btnEdit1.setFocusable(false); 
        if (img1 == null)
        {
           btnEdit1.setText("<html><b>F4</b></html>");	
        }
        else
        {
          btnEdit1.setIcon(img1); 	
        }
        btnEdit1.setFocusPainted(false); 
        btnEdit1.setMargin(new Insets(1, 1, 1, 1));  // Insets(int top, int left, int bottom, int right)       
        btnEdit1.setDefaultCapable(false); 
        //btnEdit1.setAction(a1);
        if (type == LOOKUP_TYPE_DATE)
        {
             btnEdit1.addActionListener(actionShowDate);
        }
        else if(type == LOOKUP_TYPE_DIRECTORY)
        {
        		btnEdit1.addActionListener(actionShowWindowDir);
        }
        else
        {
            btnEdit1.addActionListener(a1);
        }
        


        if (img2 == null)
        {
           btnEditList.setText("<html><b>....</b></html>");	
        }
        else
        {
          btnEdit2.setIcon(img2); 
          //btnEditList.setIcon(img2); 
        }
        btnEdit2.setFocusable(false); 
        btnEdit2.setFocusPainted(false); 
        btnEdit2.setMargin(new Insets(1, 2, 1, 2));        // Insets(int top, int left, int bottom, int right)
        btnEdit2.setDefaultCapable(false); 
        btnEdit2.addActionListener(a2);
         

     
    /* if(listMenuCaption!=null &&  listMenuCaption.size()>0)   
     {
        for(int l = 0; l<listMenuCaption.size(); l++)          
        {
       JMenuItem menuListItem = (JMenuItem)listMenuCaption.get(l);
       popupMenuTools.add(menuListItem);
   
        }
     }*/

       
        
        
        
       // btnEditList.setFocusable(false); 
       // btnEditList.setFocusPainted(false); 
       // btnEditList.setMargin(new Insets(1, 8, 1, 0));//(new Insets(1, 2, 1, 2));        //  Insets(int top, int left, int bottom, int right)
        //btnEditList.setDefaultCapable(false); 
 //       btnEditList.addActionListener(a2);
        
        
        winDate = new WindowSelectDate(frame);
       
       
        //panel.setBorder(UIManager.getBorder("ComboBox.border")); 
       // panel.setBorder(UIManager.getBorder("TextField.border")); 
       // Insets insets = txt.getMargin(); 
     //   txt.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right)); 
        //panel.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right)); 
        
        panelBtns.add(btnEdit1);
        panelBtns.add(btnEdit2);
        //panelBtns.add(btnEditList);
        
        panel.setRequestFocusEnabled(true);
        panel.add(lblDateName , BorderLayout.LINE_START);
        panel.add(txtComp, BorderLayout.CENTER);
        panel.add(panelBtns, BorderLayout.LINE_END);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);

        
  }
  
  /*public void setYearEnforce(String yearEnforceIn)
  {
     System.out.println("JTextBoxWithEditButtons.setYearEnforce="+yearEnforceIn);
      yearEnforce=yearEnforceIn;
      //this.revalidate();
      if(lblDateName==null)
      {
          
      }
      else
      {
         lblDateName.revalidate();
      }
  }*/

  public void setDateEnforceFromAndTo(String enforceDateFromIn,String enforceDateToIn)
  {
     //System.out.println("JTextBoxWithEditButtons.setYearEnforce="+yearEnforceIn);
      //yearEnforce=yearEnforceIn;
      enforceDateFrom = enforceDateFromIn;
      enforceDateTo = enforceDateToIn;
      //this.revalidate();
      if(lblDateName==null)
      {
          
      }
      else
      {
         lblDateName.revalidate();
      }
  }  
  
  public boolean isDateEmpty()
  {
  	boolean ret = true;
  	//System.out.println("JTextBoxWithEditButtons.isDateEmpty "+txtComp.getText());
  	
  	   if(txtComp.getText().equalsIgnoreCase(emptyDateDefault))
  	   {
  	   	 ret = true;
  	   }
  	   else
  	   {
  	   	  ret = false;
  	   }
  	   //System.out.println("JTextBoxWithEditButtons.isDateEmpty "+ret);
  	 return ret;
  }
  
  
  public void setEmptyDate()
  {
  	txtComp.setText("");
  }
  
  public JComponent getComponent()
  {
  	 return this;
  }
  
  // for setting focus in PanelDataFilter
  public JTextComponent getTextComp()
  {
  	  return txtComp;
  }
  
  public void setText(String text)
  {
   
  	if(type==LOOKUP_TYPE_DATE && text.equalsIgnoreCase(""))
  	{
  		 txtComp.setText(text);
  		 setEmptyDate();
  		 
  	}       
  	else
  	{
             //System.out.println("JTextBoxWithEditButtons.setText type="+type+" ="+text);
  	     txtComp.setText(text); 	
             
  	}

     // System.out.println("JTextBoxWithEditButtons.setText  type="+type+"  text="+text);
      
  	
  	//System.out.println("JTextBoxWithEditButtons.setText "+text);
  }

 private void showDirCatalog()
 {  
    DialogDirectoryTree.initialize((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
    //DialogDirectoryTree dialogDirectoryTree = new DialogDirectoryTree();
    DialogDirectoryTree.showDialog(txtComp.getText());
    String selected = DialogDirectoryTree.getSelectedDir();
    if(!selected.equalsIgnoreCase(""))
    {
       txtComp.setText(selected);	
    }
    
    
 }
 
  public boolean getIsDateValid()
  {
  	 boolean ret=false;
  	if(enforceDateFrom.equalsIgnoreCase("") && enforceDateTo.equalsIgnoreCase(""))
        {
            ret=true;
        	//lblDateName.setBackground(colBack);
        	//lblDateName.setForeground(colFore);
        	//lblDateName.revalidate();            
        }
        else
        {
  	    if(lblDateName.getText().equalsIgnoreCase("_x_"))// from utilsDate, if is not valid paint red
           {
            ret=false;
           }
           else
           {
             ret=true;
           }
        }
  	  return ret;
  }
 
  public void setBackground(Color col)
  {
  	txtComp.setBackground(col);
  }
  
  public void findDateNameInText()
  {  	
  	//System.out.println("JTextBoxWithEditButtons.findDateNameInText "+getText());
  	lblDateName.setText(utilsDate.getDateNameGreek(getText(),"EEE", enforceDateFrom,enforceDateTo));
  }

  public String getText()
  {  // if date doesnt work when date is not complete because of JxFormattedTextFieldDate
         String ret="";
         
         if (type == LOOKUP_TYPE_DATE && !isDateEmpty() )
         {
         	  ret = txtComp.getText();
         }
         else if(type == LOOKUP_TYPE_DATE && isDateEmpty())
         {
         	
         }
         else
         {
         	ret = txtComp.getText();
         }
         //System.out.println("JTextBoxWithEditButtons getText isDateEmpty:"+isDateEmpty()+"  txtComp.getText"+txtComp.getText());
  
  	return ret;  	
  }   
  
  public Document getDocument()
  {
  	//System.out.println("JTextBoxWithEditButtons getDocument()");
  	return txtComp.getDocument();
  }
  
  public void setDocument(Document doc)
  {
  	 txtComp.setDocument(doc);
  }
  
  public void setSelectionStart(int start)   
  {
  	txtComp.setSelectionStart(start);
  	//System.out.println("JTextBoxWithEditButtons start"+start);
  }
  
  public void setSelectionEnd(int end)
  {
  	txtComp.setSelectionEnd(end);
  }

  public void setBtn1Enabled(boolean enabled)
  {
  	btnEdit1.setEnabled(enabled);
  	btnEdit1.revalidate();
  }
 
   public void setBtn2Enabled(boolean enabled)
  {
  	btnEdit2.setEnabled(enabled);
  	btnEdit2.revalidate();
  } 
  
  public void setBtn1Visible(boolean visible)
  {
  	btnEdit1.setVisible(visible);
  }
 
   public void setBtn2Visible(boolean visible)
  {
  	btnEdit2.setVisible(visible);
  } 
  
   public void addFocusListener(FocusListener fl)
   {
   	  txtComp.addFocusListener(fl);
   }
  
   // neede in order to get compiled
   public final void actionPerformed(ActionEvent e)
    { 
        //editor.cancelCellEditing(); 
        //editCell(table, row, column); 
    } 

    class  ActionShowDate extends AbstractAction                 
    {    
        public ActionShowDate(String enforceDateFromIn, String enforceDateToIn)
        {     
            enforceDateFrom=enforceDateFromIn;
            enforceDateTo = enforceDateToIn;
           //yearEnforce = yearEnforceIn;
        }
    	public void actionPerformed(ActionEvent e)
      	{
      	 System.out.println("JTextBoxWithEditButtons ActionShowDate enforceDateFrom:"+enforceDateFrom+"   enforceDateTo:"+enforceDateTo);         
            winDate.setEntity(txtComp,txtComp.getText(),2,  dateOnlyOrFromTo);
          
      	  if(!winDate.isVisible())
      	  {
      	       
      	    winDate.setVisible(true);
      	    //winDate.dispose();
      	    //isVisibleWinDate = true;
      	  }
      	  else
      	  {
      	   	 winDate.setVisible(false);
      	   	 winDate.dispose();
      	    // isVisibleWinDate = false;
      	     //txtComp.setText(winDate.getDate());
      	  }

//    	  txtComp.requestFocus(true);

      	  //JOptionPane.showMessageDialog(null,comp.getX(), "edit", JOptionPane.INFORMATION_MESSAGE);  
      	}    	
    }


    class  ActionShowWindowDir extends AbstractAction                 
    {    
        public ActionShowWindowDir()
        {     
           
        }
    	public void actionPerformed(ActionEvent e)
      	{
      			
      		showDirCatalog();

      	}    	
    }	

    class DocumentListenerDateText implements  DocumentListener
    {
        public void changedUpdate(DocumentEvent e) {check();}
        public void insertUpdate(DocumentEvent e) {check();}
        public void removeUpdate(DocumentEvent e) {check();}
        void check()
         {
         	
         	
			
			
			findDateNameInText();
	   

        if(!getIsDateValid())// if is not valid paint red
        {
        	lblDateName.setBackground(Color.red);
        	lblDateName.setForeground(Color.red);
        	lblDateName.revalidate();
        }
        else
        {
        	lblDateName.setBackground(colBack);
        	lblDateName.setForeground(colFore);
        	lblDateName.revalidate();
        }
    }           	
         	
            //boolean even = field.getText().length() % 2 == 0;
            //field.setBackground(even? Color.WHITE : Color.YELLOW);
        
    };



  /*public class DocumentInsertDateText extends PlainDocument 
  {
  	//int maxSize = 10;
  	
    public DocumentInsertDateText()
    {
            super();
            
    }  
    
  	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
  	{
		/*	String acceptedChars ="";

				acceptedChars = "0123456789-/";
			
			
			if (str == null)
				return;
			if (offset >= maxSize)
			{
				return;
			}
			// does the insertion exceed the max length
			if (str.length() > maxSize)
			{
				str = str.substring(0, maxSize);
			}
			if (acceptedChars.equalsIgnoreCase(""))
			{
				//System.out.println("String.");
				super.insertString(offset, str, attr);
				return;
			}	
			for (int i = 0; i < str.length(); i++)
			{
				if (acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1)
					return;
			}
			
			
			
			
			
			findDateNameInText();
	   

        if(!getIsDateValid())// if is not valid paint red
        {
        	lblDateName.setBackground(Color.red);
        	lblDateName.setForeground(Color.red);
        	lblDateName.revalidate();
        }
        else
        {
        	lblDateName.setBackground(colBack);
        	lblDateName.setForeground(colFore);
        	lblDateName.revalidate();
        }
       
       //txtComp.setBackground(Color.white);
       super.insertString(offset, str, attr);
        
	}
  }	*/
}
