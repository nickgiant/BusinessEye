package com.tool.guicomps;

import com.tool.utils.*;
import com.tool.gui.*;

import javax.swing.border.BevelBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;
import java.text.*;

import java.util.GregorianCalendar;
import java.util.Date;


public class WindowSelectDate extends JDialog implements Constants
{
    private JLabel lblMain;
   // private JList list;
   // private JScrollPane listScrollPane;
    private JPanel panelMain;
    private JPanel panelOuter;
   // private JTabbedPane tabbedPane;
    //private JCalendar calendar;
    private String strDate="";
    private JButton btnOk;
    private JButton btnCancel;
    private PanelDateChooser panelDateChooser;
    private JTextComponent textField;
    private UtilsDate utilsDate;
    private JxPanel panelSelections;
    //private JComboBox cmbYear;
    //private JComboBox cmbMonth;
    private int dateOnlyOrFromTo;
    private String yearEnforce;
    
    public WindowSelectDate(JFrame parent)
    {
    	super(parent);
        utilsDate = new UtilsDate();
    	panelMain = new JPanel(new BorderLayout());
    	panelOuter = new JPanel(new BorderLayout());
    	//panelOuter.setBorder(new BevelBorder(BevelBorder.RAISED));
    	panelMain.setBorder(BorderFactory.createEmptyBorder(8, 12, 2, 12));
    	panelOuter.setBorder(BorderFactory.createLineBorder(Color.BLACK));  
        //this.setAlwaysOnTop(true);

       panelDateChooser = new PanelDateChooser();//(parent);

       panelSelections = new JxPanel();
      panelSelections.setLayout(new GridLayout(0,1));
      
/*	  cmbYear = new JComboBox();
	  for ( int i=FIRST_YEAR; i<=LAST_YEAR; i++ )
	  {
	  	cmbYear.addItem( Integer.toString(i) );
	  }
        cmbYear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt)
       {
           int idx = cmbMonth.getSelectedIndex();
           String strIdx = "";
           idx=idx+1;  // idx starts from 0
           if(idx<10)
           {
              strIdx="0"+idx    ;
           }
           else
           {
              strIdx=""+idx;
           }
           
           
       	   setDateOfMonth(strIdx);
          
       }
      });        */
          
/*          cmbMonth =  new JComboBox();
          cmbMonth.removeAllItems();
	 // for ( int i=FIRST_YEAR; i<=LAST_YEAR; i++ )
	  //{
	  	cmbMonth.addItem("Ιανουάριος");
                cmbMonth.addItem("Φεβρουάριος");
                cmbMonth.addItem("Μάρτιος");
                cmbMonth.addItem("Απρίλιος");
                cmbMonth.addItem("Μάϊος");
                cmbMonth.addItem("Ιούνιος");
                cmbMonth.addItem("Ιούλιος");
                cmbMonth.addItem("Αύγουστος");
                cmbMonth.addItem("Σεπτέμβριος");
                cmbMonth.addItem("Οκτώβριος");
                cmbMonth.addItem("Νοέμβριος");
                cmbMonth.addItem("Δεκέμβριος");
                
	  //}          
       cmbMonth.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
           int idx = cmbMonth.getSelectedIndex();
           String strIdx = "";
           idx=idx+1;  // idx starts from 0
           if(idx<10)
           {
              strIdx="0"+idx    ;
           }
           else
           {
              strIdx=""+idx;
           }
           
           
       	   setDateOfMonth(strIdx);
          
       }
      });
	  //cmbMonth.addItemListener(     );
	 */    
      
      /*JButtonFlat btnMonthJan = new JButtonFlat();
      btnMonthJan.setText("Ιανουάριος");
       btnMonthJan.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("01",dateOnlyOrFromTo);
       }
      });
            
      JButtonFlat btnMonthFeb = new JButtonFlat();
      btnMonthFeb.setText("Φεβρουάριος");
       btnMonthFeb.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("02",dateOnlyOrFromTo);
       }
      });
            
      JButtonFlat btnMonthMar = new JButtonFlat();
      btnMonthMar.setText("Μάρτιος");
       btnMonthMar.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("03",dateOnlyOrFromTo);
       }
      });      
      
      JButtonFlat btnMonthApr = new JButtonFlat();
      btnMonthApr.setText("Απρίλιος");
       btnMonthApr.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("04",dateOnlyOrFromTo);
       }
      });
            
      JButtonFlat btnMonthMay = new JButtonFlat();   
      	btnMonthMay.setText("Μάϊος");
       btnMonthMay.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("05",dateOnlyOrFromTo);
       }
      });      	
      JButtonFlat btnMonthJun = new JButtonFlat();   
      btnMonthJun.setText("Ιούνιος");      
       btnMonthJun.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("06",dateOnlyOrFromTo);
       }
      });
            	
      JButtonFlat btnMonthJul = new JButtonFlat();
      btnMonthJul.setText("Ιούλιος");      
       btnMonthJul.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("07",dateOnlyOrFromTo);
       }
      });      
      JButtonFlat btnMonthAug = new JButtonFlat();
      btnMonthAug.setText("Αύγουστος");      
       btnMonthAug.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("08",dateOnlyOrFromTo);
       }
      });
            
      JButtonFlat btnMonthSep = new JButtonFlat();  
      	btnMonthSep.setText("Σεπτέμβριος");    
       btnMonthSep.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("09",dateOnlyOrFromTo);
       }
      });
            	
      JButtonFlat btnMonthOkt = new JButtonFlat();
      btnMonthOkt.setText("Οκτώβριος"); 
       btnMonthOkt.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("10",dateOnlyOrFromTo);
       }
      });
            
      JButtonFlat btnMonthNov = new JButtonFlat();
      btnMonthNov.setText("Νοέμβριος"); 
       btnMonthNov.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("11",dateOnlyOrFromTo);
       }
      });
            	
      JButtonFlat btnMonthDec = new JButtonFlat(); 
      	btnMonthDec.setText("Δεκέμβριος");         
       btnMonthDec.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setDateOfMonth("12",dateOnlyOrFromTo);
       }
      });*/
      

      
      /*panelSelections.add(btnMonthJan);     
      panelSelections.add(btnMonthFeb); 
      panelSelections.add(btnMonthApr);
      panelSelections.add(btnMonthApr);
      panelSelections.add(btnMonthMay); 
      panelSelections.add(btnMonthJun);  
      panelSelections.add(btnMonthJul);   
      panelSelections.add(btnMonthAug);
      panelSelections.add(btnMonthSep);   
      panelSelections.add(btnMonthOkt); 
      panelSelections.add(btnMonthNov);
      panelSelections.add(btnMonthDec); */        
 
       
      //panelSelections.add(cmbYear); 
      //panelSelections.add(cmbMonth);        
       
       btnOk = new JButton("<html>ΟΚ <b>F5</b></html>");
       btnOk.setIcon(ICO_OK16);       
       btnOk.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	  setSelectedDate();
          
       }
      });
       	
       btnCancel = new JButton("<html>άκυρο <b>esc</b></html>");
       btnCancel.setIcon(ICO_CANCEL16);
       btnCancel.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent evt)
       {
       	   strDate="";
           close();
       }
      });
      
       JPanel panelBtns = new JPanel(); 
       panelBtns.add(btnOk) ;
       panelBtns.add(btnCancel) ;

        Action actionClose = new ActionClose();
        panelOuter.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        panelOuter.getActionMap().put("close", actionClose);        

       Action actionSet = new ActionSet();
        panelOuter.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5 ,0), "set"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        panelOuter.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER ,0), "set"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        panelOuter.getActionMap().put("set", actionSet); 

        btnOk.requestFocus(true);
        
        panelMain.add(panelSelections, BorderLayout.PAGE_START);
        panelMain.add(panelDateChooser, BorderLayout.CENTER);
        panelMain.add(panelBtns, BorderLayout.PAGE_END);             

        
        panelOuter.add(panelMain, BorderLayout.CENTER);
        getContentPane().add(panelOuter);  
       this.setModal(true);
    	
    }	
    
   public void setEntity(JTextComponent txtFld, String date, int location, /*String yearEnforceIn,*/ int dateOnlyOrFromToIn)
   {
 //	yearEnforce=yearEnforceIn;   
    	textField=txtFld;
    	dateOnlyOrFromTo=dateOnlyOrFromToIn;
        
        if(dateOnlyOrFromTo==MONTH_DATE_ONLY)
        {
        	panelSelections.setVisible(false);
        }
        else
        {
        	panelSelections.setVisible(true);
        }
    	
        
        //System.out.println("WindowSelectDate.setEntity yearEnforce= "+yearEnforce);
    	//cmbYear.setSelectedItem(yearEnforce);//VariablesGlobal.globalYear);
   
       lblMain = new JLabel();     
       //System.out.println("WindowWait "+this.getName()+this.getHeight());
       lblMain.setText(date);
       lblMain.setVerticalTextPosition(JLabel.CENTER);
       lblMain.setHorizontalTextPosition(JLabel.CENTER);
       lblMain.setBorder(BorderFactory.createEmptyBorder(6, 5, 6, 5));//top,left,bottom,right
       //lblMain.setFont(lblMain.getFont().deriveFont(Font.BOLD));
      

       setDateOnPanel(date);
//       panelDateChooser.setYearEnforce(yearEnforce);// after select
 

//lblMain.setText(calendar.getCalendarModel().setDate(new Date())));
      
      //lblMain.setOpaque(true);
      
      //lblMain.setBackground(java.awt.SystemColor.activeCaption);//Color.WHITE);
      //lblMain.setForeground(java.awt.SystemColor.activeCaptionText);
       
       
      /* list = new JList(tabs);

       list.setSelectedIndex(selected);

       list.setFocusable(true);
       list.requestFocus(true);
       list.setFixedCellHeight(24);//24,34
       list.setFont(new Font(this.getFont().getName(),Font.PLAIN, 12));// (String name, int style, int size) 

       list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
       list.setLayoutOrientation(JList.VERTICAL);
       //list.setFont(new Font(list.getFont(),Font.PLAIN, 12));// (String name, int style, int size) 
       listScrollPane = new JScrollPane();
       listScrollPane.setViewportView(list);
       list.addMouseListener(new MouseAdapter()
       {
            public void mouseClicked(MouseEvent e)
            {
            	list = (JList)e.getSource();
                if (e.getClickCount() == 1) // make it 2 for doubleclick
                {   
                    //retrievePrimKeyValue( query, selectedTableRow, primKey);
                    //System.out.println("panelManagement.WindowSelectTab table double clicked. selectedTableRow "+selectedTableRow+" PKvalue "+getPrimKeyValue());
                    tabbedPane.setSelectedIndex(list.getSelectedIndex());
                    close();
                    tabbedPane.requestFocusInWindow();
                }
            } 
       });
        list.addListSelectionListener(new ListSelectionListener()
        {
        	//Thread thread;
            public void valueChanged(final ListSelectionEvent e)
            {       
                    list = (JList)e.getSource();
                    int selectedIndex =list.getSelectedIndex();
                    
                    tabbedPane.setSelectedIndex(selectedIndex);
                    
                       double sel =0.00;
                       double tr = selectedIndex-3.5;  // -8 in order to be in the center of height
                       double rc = list.getModel().getSize();
                       sel = tr/rc;
                       double num =listScrollPane.getVerticalScrollBar().getMaximum();
                       num=num*sel;
                       listScrollPane.getVerticalScrollBar().setValue((int)num); 
                    

            }
        });       
       */
       

        
        //getContentPane().setBackground(new Color(223,223,223)); //128  192  223  255black
        
        
        //AnimatedCursor animatedCursor = new AnimatedCursor(this);
        //AnimatedCursor animatedCursor = new AnimatedCursor(SwingUtilities.getAncestorOfClass(JFrame.class, this));
        //animatedCursor.animate();

       // list.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3 ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
       // list.getActionMap().put("close", actionClose);  
       // list.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
       // list.getActionMap().put("close", actionClose);         
        
        pack();
        //setLocationRelativeTo(txtField);
        setWindowLocation(location, textField);
        //setAlwaysOnTop( true );//  if set not always editable
        
    }
    
    private void setDateOfMonth(String month)
    {
        int monthStartOrEnd=dateOnlyOrFromTo;
    	String date ="";
    	if(monthStartOrEnd==MONTH_START)
    	{
    		date="01-"+month+"-"+yearEnforce;
    		
    	}
    	else if(monthStartOrEnd==MONTH_END)
    	{   String d="";
    		if(month.equalsIgnoreCase("01") || month.equalsIgnoreCase("03") || month.equalsIgnoreCase("05") || month.equalsIgnoreCase("07") || month.equalsIgnoreCase("08") || month.equalsIgnoreCase("10") || month.equalsIgnoreCase("12"))
    	    {
    	    	d="31";
    	    }
    	    else if(month.equalsIgnoreCase("04") || month.equalsIgnoreCase("06") || month.equalsIgnoreCase("09") || month.equalsIgnoreCase("11"))
    	    {
    	    	d="30";
    	    }
    	    else
    	    {   
    	    	int year = Integer.parseInt(yearEnforce);
    	    	int y =year%4 ;

    	    	if(y==0)
    	    	{
    	    		d="29";
    	    	}
    	    	else
    	    	{
    	    		d="28";
    	    	}
    	    	
    	    }
    	    
    		date=d+"-"+month+"-"+yearEnforce;
    	}
    	
    	setDateOnPanel(date);
    	
    	//textField.setText(date);
    	//close();    	
    }
    
    private void setDateOnPanel(String dateIn)
    {
    	
       DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
       String[] allowedPatternsToRead = {"dd-MM-yyyy","dd/MM/yyyy","dd-MM-yy","dd/MM/yy"};
       String d = utilsDate.reformatDateString(dateIn,allowedPatternsToRead, "dd-MM-yyyy");
       
       panelDateChooser.select(d, dateFormat);    	
    	
    }
    
    private void setSelectedDate()
    {
    	textField.setText(strDate=panelDateChooser.getDate());
    	close();
    }
   
    public void setWindowLocation(int location, JComponent textField)
    {   
        if (location == WINDOW_LOCATION_CENTER)
        {
             Dimension paneSize   = this.getSize();
    	     Dimension screenSize = this.getToolkit().getScreenSize();
    	     //System.out.println(getSize()+" - "+getToolkit().getScreenSize());
    	     this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
        }
        else if (location == WINDOW_LOCATION_DOWNRIGHT)
        {
    	   
             Dimension paneSize   = this.getSize();
    	     Dimension screenSize = this.getToolkit().getScreenSize();
    	     //System.out.println(getSize()+" - "+getToolkit().getScreenSize());
    	     int taskbarHeight=30;
    	     this.setLocation(
            (screenSize.width  - paneSize.width) ,
            (screenSize.height - paneSize.height-taskbarHeight));
    	   //this.setLocation(100,100);
        }
        else if (location ==2)
        {
          Dimension paneSize   = this.getSize();
    	  Dimension screenSize = this.getToolkit().getScreenSize();	
        	
         Point point = textField.getLocationOnScreen(); 
        
         //this.setLocation(new Point(point.getX(),point.getY()+comp.getHeight()));
         if((this.getWidth()+(int)(point.getX())>screenSize.width) && (this.getHeight()+(int)(point.getY())>screenSize.height))
         {
         	this.setLocation(screenSize.width-this.getWidth() , (int)point.getY()-this.getHeight());
         }         
         else if(this.getWidth()+(int)(point.getX())>screenSize.width)
         {
            this.setLocation(screenSize.width-this.getWidth() , (int)point.getY()+textField.getHeight());
         }
         else if(this.getHeight()+(int)(point.getY())>screenSize.height)
         {
         	this.setLocation((int)(point.getX()) , (int)point.getY()-this.getHeight());
         }
         else
         {
         	this.setLocation((int)(point.getX()) , (int)point.getY()+textField.getHeight());
         }
        	
        }
    }
    
  /* private void set()
   {
   	
   	this.setVisible(false);
   } */
    
  /* public String getDate()
   {
   	 strDate=panelDateChooser.getDate();
   	return strDate;
   } */
    
   class  ActionClose extends AbstractAction                 
   {       
        public ActionClose()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{      close();      }    	
    }     
    
   class  ActionSet extends AbstractAction                 
   {       
        public ActionSet()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{      setSelectedDate();      }    	
    }     
    
    public void showWindow()
    {
        setVisible(true);
        
    }
    
    // doesn't working
   /* public void setText(String message)
    {
       lblMain.setText(message);
    }*/
    
    public void close()
    {
    	//setVisible(false);
    	this.dispose();
    	//tabbedPane.requestFocusInWindow();
    }
    
    //dispose
}  
