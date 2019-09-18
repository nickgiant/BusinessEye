// created 05-09-2010

package com.tool.gui;


import com.tool.model.*;
import com.tool.utils.*;
import com.tool.guicomps.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;



public class DialogBoardSetup extends JDialog implements Constants 
{
	private JButton btnClose;
	private JLabel label;
	private JPanel panelMain;
	private JPanel panelBottom;
	private  JTabbedPane tabbedPane;
    public DialogBoardSetup(JFrame parent)
    {
       // super(parent, "", true);
        super(parent,"ρυθμίσεις", true);//LANGUAGE.getString("AboutDialog.title"));
       // this.parent = parent;
        try
        {            initialize();        }
        catch (Exception e)
        {       e.printStackTrace();        }
        pack();
    }
    private void initialize() throws Exception
    {
    	
  /*    setClosable(true); 
 	setMaximizable(false); 
 	setIconifiable(false); 
 	setResizable(true); */
    panelMain = new JPanel();
    panelMain.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    panelMain.setLayout(new BorderLayout());
    this.setLayout(new BorderLayout());
    panelBottom = new JPanel();
    
    label = new JLabel();
    label.setText("στήλες");  	
    	
    	String[] cols = {"1","2","3"};
    	JComboBox cmbColumns = new JComboBox(cols);
    	cmbColumns.addActionListener(new ComboBoxTabUpdateListener());

    	
    	
    	JPanel panelTop = new JPanel();
    	panelTop.setLayout(new FlowLayout());
    	panelTop.add(label);
    	panelTop.add(cmbColumns);
    	
    	
       tabbedPane = new JTabbedPane();

     PanelSetupBoardColumn panelSetupBoardColumn = new PanelSetupBoardColumn();
     //JComponent panel1 = makeTextPanel("Panel #1");
     tabbedPane.addTab("στήλη 1", panelSetupBoardColumn);
     //tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

    	
    	
    	
    panelMain.add(panelTop,BorderLayout.PAGE_START);
    panelMain.add(tabbedPane,BorderLayout.CENTER);
    
        btnClose = new JButton("<html>κλείσιμο <b>esc</b></html>");
        btnClose.setIcon(ICO_CANCEL16);
        btnClose.requestFocus();
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 

                //dialogMain.setCompanyYearUserDate("nel","2000","user89","date" );
                dispose();
            }
        });
        Action actionClose = new ActionClose();
        btnClose.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnClose.getActionMap().put("close", actionClose);
      
      panelBottom.add(btnClose);
      this.add(panelMain, BorderLayout.CENTER);  
      this.add(panelBottom, BorderLayout.SOUTH);    	
    }
    
    private void updateTabCount(int newNoOfTabs)
    {
    	
    	int currentNoOfTabs = tabbedPane.getTabCount();
    	
    	int result = newNoOfTabs - currentNoOfTabs;
    	
    	
    	// if positive add
    	if(result>0)
    	{

    	   for(int i =0 ; i<result ; i++)
    	   {
    	       tabbedPane.addTab("στήλη "+(tabbedPane.getTabCount()+1), new PanelSetupBoardColumn());	
    	   }
    	   
    		
    	}
    	else if (result<0)
    	{
    		int tabs = tabbedPane.getTabCount()-tabbedPane.getTabCount()+result;
    		System.out.println("DialogBoardSetup.updateTabCount "+tabs);
    		
    		tabs = tabs-tabs-tabs;
    		
    		
    	   for(int i =tabs ; i>0 ; i--)
    	   {
    	   	  System.out.println("DialogBoardSetup.updateTabCount "+tabs+" "+i);
    		 tabbedPane.remove(i);
    	   }
    	}

    }
    
    public void locateOnCenterOfTheScreen()
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
    
  class PanelSetupBoardColumn extends JPanel implements Constants
  {
        
        public PanelSetupBoardColumn()
        {
            try
           {     initialize();   }
           catch (Exception e)
           {   e.printStackTrace();    }
        }


        private void initialize() throws Exception
        { 
        	
        	JTableDec table = new JTableDec();
        
        JScrollPane scrolTable = new JScrollPane();
        scrolTable.setViewportView(table);
        
        		
       this.setLayout(new BorderLayout());
       this.add(scrolTable, BorderLayout.CENTER);        		
        	
        }
        
  }
           
  public class ComboBoxTabUpdateListener implements ActionListener
  {


    public void actionPerformed(ActionEvent e)
    {

        JComboBox cb = (JComboBox)e.getSource();

        String tabCount = (String)cb.getSelectedItem();
        int tabs = Integer.parseInt(tabCount);
        updateTabCount(tabs);

    }

  }


    
    
    
}