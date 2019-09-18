/*   created 30-05-2009
 */

package com.tool.gui;

import com.tool.guicomps.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class DialogMulti extends JDialog implements Constants 
{
	private JButtonForPanelDecorated btnClose;
        private JButtonForPanelDecorated btnSaveAndClose;
	private JPanelDecorated panelMain;
	private JPanel panelToShow;
	private int typeOfPanel;
	private JPanel panelToAdd;
        //private PanelDataFilter pnlDataFilter;
        private boolean isCancelClicked = false;
    public DialogMulti(JFrame parent)
    {
       // super(parent, "", true);
        super(parent, true);//LANGUAGE.getString("AboutDialog.title"));
       // this.parent = parent;
        try
        {            initialize();        }
        catch (Exception e)
        {       e.printStackTrace();        }
    }
    
    private void initialize() throws Exception
    {

        //panelMain = new JPanel();
        //              Color blue= new Color(129, 169, 226);
        //      Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
        panelMain = new JPanelDecorated();//(blue,lightBlue,0,0);
        panelMain.setLayout(new BorderLayout());
       
        
        JPanel panelPageEnd = new JPanel();
        panelPageEnd.setOpaque(false);
        panelPageEnd.setLayout(new FlowLayout());
        
        btnSaveAndClose  = new JButtonForPanelDecorated();
        btnSaveAndClose.setText("<html>αποθήκευση και κλείσιμο <b>F5</b></html>");
        btnSaveAndClose.setIcon(ICO_OK16);
        //btnClose.requestFocus();
        btnSaveAndClose.setFocusable(false);
        btnSaveAndClose.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            { 
                saveAndCloseDialog();
            }
        });
        Action actionSaveAndClose = new ActionSaveAndClose();
        btnSaveAndClose.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5 ,0), "saveAndClose"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnSaveAndClose.getActionMap().put("saveAndClose", actionSaveAndClose);
        
        
        btnClose = new JButtonForPanelDecorated();
        btnClose.setText("<html>κλείσιμο <b>esc</b></html>");
        btnClose.setIcon(ICO_CANCEL16);
        //btnClose.requestFocus();
        btnClose.setFocusable(false);
        btnClose.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            { 
                closeDialog();
            }
        });
        Action actionClose = new ActionClose();
        btnClose.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnClose.getActionMap().put("close", actionClose);
        
        panelPageEnd.add(btnSaveAndClose);
        panelPageEnd.add(btnClose);
        panelMain.add(panelPageEnd, BorderLayout.PAGE_END);
      
        setCloseClick();
      
        this.add(panelMain);
    	
    }
    
    public void setEntity(JPanel pnl, int typeOfPanelIn, String title,boolean showBtnOk)
    {
        
    	panelToShow = pnl;
    	typeOfPanel=typeOfPanelIn;
    	panelToAdd = pnl;
    	panelMain.add(panelToAdd, BorderLayout.CENTER);
    	this.setTitle(title);
        
        if(showBtnOk)
        {
            btnSaveAndClose.setVisible(true);
            btnSaveAndClose.setText("<html>Ok <b>F5</b></html>");
        }
        else
        {
            btnSaveAndClose.setVisible(false);
           // btnClose.setIcon(ICO_OK16);// when alone
        }
    
    }
    
    public void display()
    {
        this.pack();
        locateOnCenterOfTheScreen();
    	this.setVisible(true);//.show();
        
    }

    private void saveAndCloseDialog()
    {
    	if(typeOfPanel==PANEL_TYPE_EDITONEDATAONEREC)
    	{
    	   PanelEditOneDataRec panel = (PanelEditOneDataRec)panelToAdd;  
    	   panel.saveAndClose();
           dispose();
  		
    	}
        else if(typeOfPanel == PANEL_TYPE_CALCULATIONDOCFILTER)
        {
    	  PanelDataFilter pnlDataFilter = (PanelDataFilter)panelToAdd;  
          boolean checkCompleted = pnlDataFilter.checkIfFieldsAreCompleted();
           if(checkCompleted)
           {
               dispose();
           }
        }
    	else if (typeOfPanel== PANEL_TYPE_TASK || typeOfPanel== PANEL_TYPE_ONEDATAMANYREC || typeOfPanel==PANEL_TYPE_REPORT || typeOfPanel==PANEL_TYPE_ANY || 
                typeOfPanel==PANEL_TYPE_COPY_FROM_OTHERCOMPANY )
    	{

    	  // panel..saveAndClose();
           dispose();
    	}
    	else
    	{
    		System.out.println("DialogMulti.saveAndCloseDialog typeOfPanelIn '"+typeOfPanel+"' not found");
    	}           
    }
    
    /*
    *  called only for Documents
    */
    public boolean getIsCancelClicked()
    {
        return isCancelClicked;
    }
    
    private void closeDialog()
    {
    	if(typeOfPanel==PANEL_TYPE_EDITONEDATAONEREC)
    	{
    	   PanelEditOneDataRec panel = (PanelEditOneDataRec)panelToAdd;  
    	   if(panel.toClosePanelOrGoBackAskIfDataChanged())
    	   {
    	      dispose();
    	   }    		
    	}
        else if(typeOfPanel == PANEL_TYPE_CALCULATIONDOCFILTER  || typeOfPanel==PANEL_TYPE_COPY_FROM_OTHERCOMPANY )
        {
    	   //PanelDataFilter pnlDataFilter = (PanelDataFilter)panelToAdd;  
          // boolean checkCompleted = pnlDataFilter.checkIfFieldsAreCompleted();
          // if(checkCompleted)
           //{
            isCancelClicked=true;
               dispose();
           //}
        }        
    	else if (typeOfPanel== PANEL_TYPE_TASK || typeOfPanel== PANEL_TYPE_ONEDATAMANYREC || typeOfPanel==PANEL_TYPE_ANY || typeOfPanel==PANEL_TYPE_REPORT )
    	{
    		dispose();
    	}
    	else
    	{
    		System.out.println("DialogMulti.closeDialog typeOfPanelIn '"+typeOfPanel+"' not found");
    	}
    }
    
    

    public void setPanelMainSize(int sizeHorizontal,int sizeVertical)
    {
          panelMain.setPreferredSize(new Dimension(sizeHorizontal,sizeVertical));        
    }
    
    //create a window listener to respond to the window close click
   private void setCloseClick()
   {
   	setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);   
    addWindowListener(new WindowAdapter()
    {
       public void windowClosing(WindowEvent e)
       {    closeDialog();   }
    });
   }

    
      private void locateOnCenterOfTheScreen()
     {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
      }

    class  ActionClose extends AbstractAction                 
    {       
        public ActionClose()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{           btnClose.doClick();        }    	
    }                


    class  ActionSaveAndClose extends AbstractAction                 
    {       
        public ActionSaveAndClose()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{           btnSaveAndClose.doClick();        }    	
    }          
    
}      
      