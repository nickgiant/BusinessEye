/*
 * DialogProgramConfiguration.java
 *
 */

  package com.tool.gui;
   
  import com.tool.guicomps.*;
  import com.tool.utils.*;
  import com.tool.model.*;

  import java.beans.PropertyChangeSupport;
  
  
  import javax.swing.JDialog;
  import javax.swing.JLabel;
  import javax.swing.JPanel;
  import javax.swing.JTextField;
  import javax.swing.JCheckBox;
  import javax.swing.JPasswordField;
  import javax.swing.JScrollPane;
  import javax.swing.JList;
  import javax.swing.JButton;
  import javax.swing.ImageIcon;
  import javax.swing.WindowConstants;
  import javax.swing.DefaultComboBoxModel;
  import javax.swing.JComboBox;
  //import javax.swing.tree.*;
  import javax.swing.JFrame;
  import javax.swing.BoxLayout;
  import javax.swing.BorderFactory;
  import javax.swing.Box;
  import javax.swing.UIManager;
  import javax.swing.UnsupportedLookAndFeelException;
  import javax.swing.SwingUtilities;
  import javax.swing.KeyStroke;
  import javax.swing.Action;
  import javax.swing.AbstractAction;
  import javax.swing.KeyStroke;
  import javax.swing.JComponent;
  import javax.swing.border.TitledBorder;
  import javax.swing.SwingConstants;
  import javax.swing.DefaultListModel;
  import javax.swing.event.ListSelectionListener;
  import javax.swing.event.ListSelectionEvent;
  import javax.swing.ListSelectionModel;  
  import javax.swing.ImageIcon;
  import javax.swing.ListCellRenderer;
  import javax.swing.JTable;
  import javax.swing.table.DefaultTableModel;
  
  import java.awt.BorderLayout;
  import java.awt.event.ActionListener;
  import java.awt.event.ActionEvent;
  import java.awt.Dimension;
  import java.awt.GridLayout;
  import java.awt.FlowLayout;
  import java.awt.event.ActionListener;
  import java.awt.event.ActionEvent;
  import java.awt.event.KeyEvent;
  import java.awt.CardLayout; 
  import java.awt.GridBagLayout; 
  import java.awt.GridBagConstraints; 
   
//  import org.netbeans.lib.awtextra.AbsoluteLayout;
//  import org.netbeans.lib.awtextra.AbsoluteConstraints;

  import java.io.File;
  import java.io.FileOutputStream;  
  import java.io.FileInputStream;
  import java.io.IOException;
  
  import java.util.Properties;  
  import java.util.Calendar;
  import java.util.GregorianCalendar;
  import java.util.ArrayList;

 
public class DialogProgramConfiguration extends JDialog implements Constants
{
	
	private JButton btnClose;
    private JButton btnOk;
    //private JxPanel panelMain;
    //private JxPanel panelLeft;
    private JxPanel panelCenter;
    private JPanel panelBottom;

    //private JListDec listMenu;
    //private DefaultListModel listModel;
    //private JScrollPane scrollpaneMenu;
    
    private JLabel lblLookAndFeel;
    private DefaultComboBoxModel mdlLookAndFeel;
	private JComboBox cmbLookAndFeel;

    //private JLabel lblShowToolbarButtonText;
	//private JCheckBox chkShowToolbarButtonText;
	
    //private JLabel lblShowToolbarButtonFunctionKey;
//	private JCheckBox chkShowToolbarButtonFunctionKey;	
	
	private JLabel lblDateFormat;
    private JComboBox cmbDateFormat;
    private DefaultComboBoxModel mdlDateFormat;
    
    private JLabel lblDateFormatEdit;
    private JComboBox cmbDateFormatEdit;
    private DefaultComboBoxModel mdlDateFormatEdit;

  /*  private JLabel lblProposeDate;
    private JComboBox cmbProposeDate;
    private DefaultComboBoxModel mdlProposeDate;
  */  
  /*  private JLabel lblTextDelimiter;
    //private JTextField txtTextDelimiter;

    private JLabel lblDecimalNumbers;
    private JComboBox cmbDecimalNumbers;
    private DefaultComboBoxModel mdlDecimalNumbers;
 
    private JLabel lblDecimalFormat;
    private JComboBox cmbDecimalFormat;
    private DefaultComboBoxModel mdlDecimalFormat;
    
    private JTableDec table;
    private JScrollPane scrollpaneTable;
    
    private JButton btnPrinterAdd;
    private JButton btnPrinterEdit;
    private JButton btnPrinterDel;
*/
    
    private JFrame parentFrame;
    
	private String plaf_names[];
	private String plaf_vals[];
    
    //private Properties properties;
    //private PropertyChangeSupport changeSupport;
    
    private JPanel panelLook;
   /* private JPanel panelLookLeft;
    private JPanel panelLookRight;*/
    private JPanel panelDate;
    //private JPanel panelDateLeft;
    //private JPanel panelDateRight;
    /*private JPanel panelNumberFormat;
    //private JPanel panelNumberFormatLeft;
    //private JPanel panelNumberFormatRight;
    private JPanel panelEdit;
    private JPanel panelPrinters;
    private JPanel panelPrinterButtons;
    private JPanel panelExport;
    private JPanel panelExportLeft;
    private JPanel panelExportRight;*/
    
   
    private String LookFeel;
    private String LookFeelMemory="";
    private String dateFormat="dd-MM-yyyy";
    private String dateFormatEdit="dd-MM-yyyy";
    //private String decimalNumbers="2";
    //private String decimalFormat=",";
   /* private boolean proposeCurrentDate=false;
    private String delimiter="|";*/
	
	//private String systemDirectorySymbol;
	private UtilsOS utilsOS ;
    public DialogProgramConfiguration(JFrame frame)//(Frame parent, boolean modal)
    {
       super(frame, "Ρυθμίσεις", true);
       parentFrame=frame;
       initialize();
       //this.parent = parent;
    }
 
    public DialogProgramConfiguration()//(Frame parent, boolean modal)
    {
       initialize();
    }

    
    /** This method is called from within the constructor to initialize the form.*/
     
    private void initialize() 
    {
    	//changeSupport = new PropertyChangeSupport(this);
    utilsOS = new UtilsOS();
    //systemDirectorySymbol=System.getProperty("file.separator");	
   // scrollpaneMenu = new JScrollPane();
   // listModel = new DefaultListModel();
   // listMenu = new JListDec(listModel);
//    listMenu.addListSelectionListener(new ListSelectionHandler());
  //  listMenu.setLayoutOrientation(JListDec.VERTICAL);
  //  listMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   // listMenu.setFixedCellHeight(28);
  //  scrollpaneMenu.setViewportView(listMenu);
  //  addListLines();
 /*   panelLeft=new JxPanel();
    panelLeft.setLayout(new BorderLayout());
    panelLeft.add(scrollpaneMenu);
    scrollpaneMenu.setPreferredSize(new Dimension(100, 100));
  */  
        
        //panelMain = new JxPanel();
        panelBottom = new JPanel();
        btnOk = new JButton();
        btnClose = new JButton();

        setResizable(true);
        getConfigFromFile();
        
        //panelMain.setLayout(new AbsoluteLayout()); 
        //panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));
        //panelMain.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        //panelMain.setPreferredSize(new Dimension(277, 210));
        
        addToGuiLookNFeel();
        addToGuiEdit();        
       // addToGuiExport();
        
      //  addToGuiPrinters();
      //  retrievePrinters();

        //panelBottom.setLayout(new BoxLayout(panelBottom, BoxLayout.X_AXIS));
        panelBottom.setLayout(new FlowLayout());
        panelBottom.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
             
        //panelBottom.add(Box.createHorizontalGlue());
 
        btnOk.setIcon(ICO_OK16);
        btnOk.setText("<html>Ok <b>F5</b></html>");
       btnOk.setFocusable(false);
       // getRootPane().setDefaultButton(btnOk);
           btnOk.addActionListener(new ActionListener()
           {
	         public void actionPerformed(ActionEvent e) 
	         {    
	           applyChanges();	      
	         }
	       });
        Action actionOk = new ActionOk();
        btnOk.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), "ok"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnOk.getActionMap().put("ok", actionOk);

        panelBottom.add(btnOk);
                
        //panelBottom.add(Box.createRigidArea(new Dimension(10, 0)));

           btnClose.setIcon(ICO_CANCEL16);
           btnClose.setText("<html>'Aκυρο <b>esc</b></html>");
           btnClose.setFocusable(false);
           btnClose.addActionListener(new ActionListener()
           {
	         public void actionPerformed(ActionEvent e) 
	         {      dispose();     }
	       });
        Action actionClose = new ActionClose();
        btnClose.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnClose.getActionMap().put("close", actionClose);
       
        panelCenter=new JxPanel();
        panelCenter.setLayout(new BorderLayout());
        
        panelCenter.add(panelLook, BorderLayout.PAGE_START);
        panelCenter.add(panelDate, BorderLayout.PAGE_END);
      //  panelCenter.add(panelPrinters,"panelPrinters");
        //panelCenter.add(panelExport,"panelExport");


        panelBottom.add(btnClose);
        
        //getContentPane().add(panelMain, BorderLayout.CENTER);
        
        //getContentPane().add(panelLeft,BorderLayout.LINE_START);
        getContentPane().add(panelCenter,BorderLayout.CENTER);
        getContentPane().add(panelBottom, BorderLayout.SOUTH);
        
        
        pack();
      //  listMenu.setSelectedIndex(0);
      //  listMenu.requestFocus();
        
    }
    
    private void addToGuiLookNFeel()
    {
    	lblLookAndFeel = new JLabel();
        mdlLookAndFeel = new DefaultComboBoxModel();
        cmbLookAndFeel = new JComboBox(mdlLookAndFeel);
        
        //lblShowToolbarButtonText = new JLabel();
    //    chkShowToolbarButtonText = new JCheckBox("κείμενο στις μπάρες");
        
        //lblShowToolbarButtonFunctionKey = new JLabel();
   //     chkShowToolbarButtonFunctionKey = new JCheckBox("πλήκτρο συντόμευσης στις μπάρες");
        
    	panelLook = new JPanel();
         //panelLook.setLayout(new GridLayout(0,2));
         //panelLook.setLayout(new BorderLayout());
        panelLook.setLayout(new FlowLayout());
        //panelLook.setLayout(new GridBagLayout());
        // panelLook.setLayout(new BoxLayout(panelLook, BoxLayout.PAGE_AXIS));
         panelLook.setBorder( new TitledBorder( "εμφάνιση") );
         
         JPanel panelLookInto = new JPanel();
         panelLookInto.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2));
         
        /* panelLookLeft = new JPanel();
         panelLookLeft.setLayout(new GridLayout(0,1));
        //panelLookLeft.setLayout(new BoxLayout(panelLookLeft, BoxLayout.PAGE_AXIS));

         
         panelLookRight = new JPanel();
         panelLookRight.setLayout(new GridLayout(0,1));    */    
         //panelLookRight.setLayout(new BoxLayout(panelLookRight, BoxLayout.PAGE_AXIS));
        
        lblLookAndFeel.setText("εμφάνιση προγράμματος:");
        

		String cLAF = UIManager.getLookAndFeel().getClass().getName();
		UIManager.LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
		plaf_names = new String[lafs.length];
		plaf_vals = new String[lafs.length];
		for (int i = 0; i < lafs.length; i++) {
			plaf_names[i] = lafs[i].getName();
			plaf_vals[i] = lafs[i].getClassName();
			mdlLookAndFeel.addElement(plaf_names[i]);
			if (cLAF.equals(plaf_vals[i])) {
				mdlLookAndFeel.setSelectedItem(plaf_names[i]);
			}
		}
		cLAF = null;
		lafs = null;
        
         //lblShowToolbarButtonText.setText("εμφάνιση κειμένου στις μπάρες:");
 ////       chkShowToolbarButtonText.setSelected(true);

         //lblShowToolbarButtonFunctionKey.setText("εμφάνιση πλήκτρου συντόμευσης στις μπάρες:");
  //      chkShowToolbarButtonFunctionKey.setSelected(true);
        
       /* panelLookLeft.add(lblLookAndFeel);
        panelLookRight.add(cmbLookAndFeel);
        panelLookLeft.add(lblShowToolbarButtonText);
        panelLookRight.add(chkShowToolbarButtonText);
        panelLookLeft.add(lblShowToolbarButtonFunctionKey);
        panelLookRight.add(chkShowToolbarButtonFunctionKey);*/
        
        
          /*GridBagConstraints cLook = new GridBagConstraints();
         cLook.fill = GridBagConstraints.HORIZONTAL;
          cLook.weightx = 0.5;
          cLook.gridx = 0;
          cLook.gridy = 0;
          panelLook.add(lblLookAndFeel, cLook);
          cLook.fill = GridBagConstraints.HORIZONTAL;
          cLook.gridx = 1;
          cLook.gridy = 0;
          panelLook.add(cmbLookAndFeel, cLook);
          cLook.fill = GridBagConstraints.HORIZONTAL;
          cLook.gridx = 0;
          cLook.gridy = 1;
          panelLook.add(lblShowToolbarButtonText, cLook);
          cLook.fill = GridBagConstraints.HORIZONTAL;
          cLook.gridx = 1;
          cLook.gridy = 1;
          panelLook.add(chkShowToolbarButtonText, cLook);
          cLook.fill = GridBagConstraints.HORIZONTAL;
          cLook.gridx = 0;
          cLook.gridy = 2;
          panelLook.add(lblShowToolbarButtonFunctionKey, cLook);
          cLook.fill = GridBagConstraints.HORIZONTAL;
          cLook.gridx = 1;
          cLook.gridy = 2;
          panelLook.add(chkShowToolbarButtonFunctionKey, cLook);*/
          
          panelLookInto.add(lblLookAndFeel);
          panelLookInto.add(cmbLookAndFeel);
          //panelLookInto.add(lblShowToolbarButtonText);
   //       panelLookInto.add(chkShowToolbarButtonText);
          //panelLookInto.add(lblShowToolbarButtonFunctionKey);
   //       panelLookInto.add(chkShowToolbarButtonFunctionKey);
          
          panelLook.add(panelLookInto);
        //panelLook.add(panelLookLeft, BorderLayout.WEST);
        //panelLook.add(panelLookRight, BorderLayout.EAST);

    }

    private void addToGuiEdit()
    {
        lblDateFormat = new JLabel();
        mdlDateFormat = new DefaultComboBoxModel();
        cmbDateFormat = new JComboBox(mdlDateFormat);
        
        lblDateFormatEdit = new JLabel();
        mdlDateFormatEdit = new DefaultComboBoxModel();
        cmbDateFormatEdit = new JComboBox(mdlDateFormatEdit);
        
        /*lblProposeDate = new JLabel();
        mdlProposeDate = new DefaultComboBoxModel();
        cmbProposeDate = new JComboBox(mdlProposeDate);
        
        lblDecimalNumbers = new JLabel();
        mdlDecimalNumbers = new DefaultComboBoxModel();
        cmbDecimalNumbers = new JComboBox(mdlDecimalNumbers);

        lblDecimalFormat = new JLabel();
        mdlDecimalFormat = new DefaultComboBoxModel();
        cmbDecimalFormat = new JComboBox(mdlDecimalFormat);
*/
    	 panelDate = new JPanel();
    	 panelDate.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2));
         //panelDate.setLayout(new BorderLayout());
         panelDate.setBorder( new TitledBorder("ημερομηνία") );
        
         /*panelDateLeft = new JPanel();
         panelDateLeft.setLayout(new GridLayout(0,1));
         
         panelDateRight = new JPanel();
         panelDateRight.setLayout(new GridLayout(0,1));*/
        
        lblDateFormat.setText("μορφή ημερομηνίας:");
        
        mdlDateFormat.addElement("dd-MM-yyyy");
        mdlDateFormat.addElement("dd-MM-yy");
        mdlDateFormat.addElement("EEE dd-MM-yyyy");
        mdlDateFormat.addElement("EEE dd-MM-yy");
        mdlDateFormat.addElement("EEE dd/MM/yyyy");
        mdlDateFormat.addElement("MM-dd-yyyy");
        mdlDateFormat.addElement("yyyy-MM-dd");
        mdlDateFormat.setSelectedItem(dateFormat);
       cmbDateFormat.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
             JComboBox cbdf = (JComboBox)e.getSource();
             dateFormat = (String)cbdf.getSelectedItem();
          }
       });


        lblDateFormatEdit.setText("μορφή ημερομηνίας σε επεξεργασία:");
        
        
        mdlDateFormatEdit.addElement("dd-MM-yyyy");
        mdlDateFormatEdit.addElement("MM-dd-yyyy");
        //mdlDateFormatEdit.addElement("yyyy-MM-dd");
        mdlDateFormatEdit.setSelectedItem(dateFormatEdit);
       cmbDateFormatEdit.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
             JComboBox cbdfe = (JComboBox)e.getSource();
             dateFormatEdit = (String)cbdfe.getSelectedItem();
          }
       });
        

     /*   lblProposeDate.setText("πρόταση σημερινής ημερομηνίας:");

        
        mdlProposeDate.addElement("Ναι");
        mdlProposeDate.addElement("Οχι");
        if (proposeCurrentDate==true)
        {     mdlProposeDate.setSelectedItem("Ναί");        }
        else if (proposeCurrentDate==false)
        {     mdlProposeDate.setSelectedItem("Όχι");        }

       cmbProposeDate.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
             JComboBox cbpd = (JComboBox)e.getSource();
             
             String propdate =  mdlProposeDate.getSelectedItem().toString();
             
             if (propdate.equalsIgnoreCase("ναι"))
             {     proposeCurrentDate=true ;        }
             else if (propdate.equalsIgnoreCase("οχι"))
             {      proposeCurrentDate=false ;        }

          }
       });*/

        panelDate.add(lblDateFormat);
        panelDate.add(cmbDateFormat);
        panelDate.add(lblDateFormatEdit);
        panelDate.add(cmbDateFormatEdit);
       /* panelDate.add(lblProposeDate);
        panelDate.add(cmbProposeDate);

        /*panelDate.add(panelDateLeft, BorderLayout.WEST);
        panelDate.add(panelDateRight, BorderLayout.EAST);*/

/*       panelNumberFormat = new JPanel();
       panelNumberFormat.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 4));
       //panelNumberFormat.setLayout(new BorderLayout());
       panelNumberFormat.setBorder( new TitledBorder( "αριθμοί") );
*/
         /*panelNumberFormatLeft = new JPanel();
         panelNumberFormatLeft.setLayout(new GridLayout(0,1));

         panelNumberFormatRight = new JPanel();
         panelNumberFormatRight.setLayout(new GridLayout(0,1));*/


       /* lblDecimalNumbers.setText("αριθμοί μετά την υποδιαστολή:");
        
        mdlDecimalNumbers.addElement("0");
        mdlDecimalNumbers.addElement("1");
        mdlDecimalNumbers.addElement("2");
        mdlDecimalNumbers.addElement("3");
        mdlDecimalNumbers.addElement("4");
        mdlDecimalNumbers.setSelectedItem(decimalNumbers);
       cmbDecimalNumbers.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
             JComboBox cbdecn = (JComboBox)e.getSource();
             decimalNumbers = (String)cbdecn.getSelectedItem();
          }
       });*/



      /*  lblDecimalFormat.setText("μορφή υποδιαστολής:");
        
        mdlDecimalFormat.addElement(",");
        mdlDecimalFormat.addElement(".");
        mdlDecimalFormat.setSelectedItem(decimalFormat);
        cmbDecimalFormat.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
             JComboBox cbdecf = (JComboBox)e.getSource();
             decimalFormat = (String)cbdecf.getSelectedItem();
          }
       });*/
       
      /* panelEdit = new JPanel();
       //panelEdit.setLayout(new BorderLayout());
       //panelEdit.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));
       panelEdit.setLayout(new FlowLayout());
       panelEdit.setBorder( new TitledBorder( "επεξεργασία") );
    */   //panelEdit.add(panelDate, BorderLayout.PAGE_START);
       //panelEdit.add(panelNumberFormat, BorderLayout.PAGE_END);
    //   JPanel panelIntoEdit = new JPanel();
    //   panelIntoEdit.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));

        /*panelNumberFormat.add(lblDecimalFormat);
        panelNumberFormat.add(cmbDecimalFormat);
        panelNumberFormat.add(lblDecimalNumbers);
        panelNumberFormat.add(cmbDecimalNumbers);*/
        
        //panelNumberFormat.add(panelNumberFormatLeft, BorderLayout.WEST);
        //panelNumberFormat.add(panelNumberFormatRight, BorderLayout.EAST);

        /*  GridBagConstraints cEdit = new GridBagConstraints();
          cEdit.fill = GridBagConstraints.HORIZONTAL;
          cEdit.weightx = 0.5;
          cEdit.gridx = 0;
          cEdit.gridy = 0;
          panelEdit.add(panelDate, cEdit);
          cEdit.fill = GridBagConstraints.HORIZONTAL;
          cEdit.gridx = 0;
          cEdit.gridy = 1;
          panelEdit.add(panelNumberFormat, cEdit);*/
       
      // panelIntoEdit.add(panelDate);
       //panelIntoEdit.add(panelNumberFormat);
      // panelEdit.add(panelDate);
    	
    }
    
    /*private void addToGuiExport()
    {
    	  panelExport = new JPanel();
         panelExport.setLayout(new BorderLayout());
         //panelExport.setLayout(new GridBagLayout());
         panelExport.setBorder( new TitledBorder("εξαγωγή αρχείου") );


        PanelExportToFileSettings panelExportToFileSettings = new PanelExportToFileSettings(parentFrame);

        //panelExportToFileSettings.retrieveSuggestedSettings();
        panelExport.add(panelExportToFileSettings, BorderLayout.PAGE_START);
        // panelExportLeft = new JPanel();
         //panelExportLeft.setLayout(new GridLayout(0,1));
         
        // panelExportRight = new JPanel();
         //panelExportRight.setLayout(new GridLayout(0,1));

/*
        lblTextDelimiter=new JLabel("διαχωρiστικό στηλών σε αρχείο '.txt':");
        
        txtTextDelimiter=new JTextField(10);
        txtTextDelimiter.setText(delimiter);

        //panelExportLeft.add(lblTextDelimiter);
        //panelExportRight.add(txtTextDelimiter);

         GridBagConstraints cExport = new GridBagConstraints();
         cExport.fill = GridBagConstraints.HORIZONTAL;
          cExport.weightx = 0.5;
          cExport.gridx = 0;
          cExport.gridy = 0;
          panelExport.add(lblTextDelimiter, cExport);
          cExport.fill = GridBagConstraints.HORIZONTAL;
          cExport.gridx = 1;
          cExport.gridy = 0;
          panelExport.add(txtTextDelimiter, cExport);*/
        
        //panelExport.add(panelExportLeft,BorderLayout.WEST);
        //panelExport.add(panelExportRight,BorderLayout.EAST);

  //  }
    
  /*  private void addToGuiPrinters()
    {
    	panelPrinters = new JPanel();
        panelPrinters.setLayout(new BorderLayout());
        panelPrinters.setBorder( new TitledBorder("εκτυπωτές") );
        
        panelPrinterButtons=new JPanel();
        panelPrinterButtons.setLayout(new FlowLayout());
        
        btnPrinterAdd=new JButton("προσθήκη");
        //btnPrinterAdd.setIcon(ICO_CANCEL16);
        //btnPrinterAdd.setText("<html>'Aκυρο <b>esc</b></html>");
        btnPrinterAdd.setFocusable(false);
        btnPrinterAdd.addActionListener(new ActionListener()
           {
	         public void actionPerformed(ActionEvent e) 
	         {      printerAdd();     }
	       });
        //Action actionClose = new ActionClose();
        //btnPrinterAdd.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        //btnPrinterAdd.getActionMap().put("close", actionClose);

        btnPrinterEdit=new JButton("επεξεργασία");
        //btnPrinterEdit.setIcon(ICO_CANCEL16);
        //btnPrinterEdit.setText("<html>'Aκυρο <b>esc</b></html>");
        btnPrinterEdit.setFocusable(false);
        btnPrinterEdit.addActionListener(new ActionListener()
           {
	         public void actionPerformed(ActionEvent e) 
	         {      printerEdit();     }
	       });
        //Action actionClose = new ActionClose();
        //btnPrinterEdit.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        //btnPrinterEdit.getActionMap().put("close", actionClose);
        
        btnPrinterDel=new JButton("διαγραφή");
        //btnPrinterDel.setIcon(ICO_CANCEL16);
        //btnPrinterDel.setText("<html>'Aκυρο <b>esc</b></html>");
        btnPrinterDel.setFocusable(false);
        btnPrinterDel.addActionListener(new ActionListener()
           {
	         public void actionPerformed(ActionEvent e) 
	         {      printerDel();     }
	       });
        //Action actionClose = new ActionClose();
        //btnPrinterDel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        //btnPrinterDel.getActionMap().put("close", actionClose);
        
        panelPrinterButtons.add(btnPrinterAdd);
        panelPrinterButtons.add(btnPrinterEdit);
        panelPrinterButtons.add(btnPrinterDel);
        
        table=new JTableDec();
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowSelectionAllowed(true);
    
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// select a single row only 
        scrollpaneTable = new JScrollPane();
        scrollpaneTable.setViewportView(table);
        scrollpaneTable.setPreferredSize(new Dimension(350, 150));
        
        
        
        panelPrinters.add(panelPrinterButtons,BorderLayout.PAGE_START);
        panelPrinters.add(scrollpaneTable,BorderLayout.CENTER);

    	
    }*/

    private void printerAdd()
    {
    	
    }
    
    /*private void printerEdit()
    {
    	DialogPrinterEdit dlgPrinterEdit = new DialogPrinterEdit(new javax.swing.JFrame(), true);
    	dlgPrinterEdit.setVisible(true);
    	
    }*/
    
    private void printerDel()
    {
    	
    }
    
    /*private void addListLines()
    {
    	//listModel.addElement(new Line("<html>εκτυπωτές",ICO_PRINT16));

    	listModel.addElement(new Line("εμφάνιση",STR_ICO_LOOK, "panelLook"));
    	listModel.addElement(new Line("επεξεργασία",STR_ICO_EDIT,"panelEdit"));
    	listModel.addElement(new Line("εκτυπωτές",STR_ICO_PRINTER, "panelPrinters"));    	
    	//listModel.addElement(new Line("εξαγωγή",STR_ICO_EXPORT,"panelExport"));
    }*/
    
    /**
     * Locates the frame on the screen center.
     */
    public void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }

	private void setConfigToFile()
	{
		
		
		Properties fileProperties = new Properties();
        
        fileProperties.setProperty("LookAndFeel", LookFeel);
        
        //String dateFormatSelection = cmbDateFormat.getSelectedItem().toString();
        fileProperties.setProperty("date.format", dateFormat);
        fileProperties.setProperty("date.formatEdit", dateFormatEdit);
        //String proposeDateSelection = cmbProposeDate.getSelectedItem().toString();
     /*   if (proposeCurrentDate == true )
        {   fileProperties.setProperty("date.proposeCurrentProgramDate", "y");  }
        else if (proposeCurrentDate == false )
        {   fileProperties.setProperty("date.proposeCurrentProgramDate", "n");  }
     */   //fileProperties.setProperty("number.decimalNumbers", decimalNumbers);
        //fileProperties.setProperty("number.decimalFormat", decimalFormat);
        //fileProperties.setProperty("textDelimiter", txtTextDelimiter.getText());
 
 //		System.out.println(VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_CONFIG) ;

        
      try
      {
        File file = new File(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_CONFIG);
        // Create file if it does not exist
        //boolean exists= file.exists();
        boolean success = false;
        success = file.createNewFile();
        if (success)
        {
            // File did not exist and was created
           System.out.println(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_CONFIG+ " did not found and created.") ;
            fileProperties.store(new FileOutputStream(file), null);
        }
        else
        {
            // File already exists
            fileProperties.store(new FileOutputStream(file), null);
        }
      }catch (IOException e)
      { System.err.println("DialogConfiguration.setConfigToFile (cannot find file) "+e+" "+VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_CONFIG);    }


    }

  /*public void packColumns()
  {
  	
  	     UtilsTable utilsTable=new UtilsTable();
         //for each column pack
         //int totalWidthOfColumns =0; 
         for (int c=0; c<table.getColumnCount(); c++)
         {   // table,column, margin
             utilsTable.packColumn(table, c, 4,true,false,null);
         }       
  	
  }*/

	private void getConfigFromFile()
	{
	  try
	  {
     	Properties props = new Properties(); //properties to get from file
     String curDir ="";

      
        FileInputStream in = new FileInputStream(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_CONFIG);
        props.load(in);
        LookFeel = props.getProperty("LookAndFeel");
        LookFeelMemory=LookFeel;
        dateFormat = props.getProperty("date.format");
        dateFormatEdit = props.getProperty("date.formatEdit");
     /*/   String propose = props.getProperty("date.proposeCurrentProgramDate");
        if (propose.equalsIgnoreCase("n"))
        {    proposeCurrentDate=false;    }
        else if (propose.equalsIgnoreCase("y"))
        {    proposeCurrentDate=true;   }
        else
        {  
           System.out.println("DialogConfiguration.getConfigFromFile proposeCurrentProgramDate not n not y")  ;
           proposeCurrentDate=false;
        }*/
        //decimalNumbers=props.getProperty("number.decimalNumbers");
        //decimalFormat=props.getProperty("number.decimalFormat");
        //delimiter=props.getProperty("textDelimiter");
        
      }
      catch (IOException ex)
      {
          System.err.println("DialogConfiguration.IOException:Cannot find text file:"+FILE_CONFIG);
          //System.err.println(ex);
      }
      
   }

   /* private void listSelection(int index)
    {   
        Line ln=(Line)listModel.get(index);
        
    	CardLayout cardLayout = (CardLayout)(panelCenter.getLayout());
        cardLayout.show(panelCenter, (String)ln.layoutName);
         
    }*/
    
    /*public void retrievePrinters()
    {
    	DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        tableModel.addColumn("id");
        tableModel.addColumn("όνομα");
        tableModel.addColumn("τύπος");
        tableModel.addColumn("θύρα");
        
        try
        {
        
   	    	XMLPrinterListParser parser=new XMLPrinterListParser();
	     	ArrayList printers=parser.parse(VariablesGlobal.globalDirConfiguration+"\\"+FILE_PRINTERS);
		    for(int i=0;i<printers.size();i++)
		   {
	     		Printer p =(Printer)printers.get(i);
	    		//System.out.println("retrievePrinters"+p.getName());
	    		String id=Integer.toString(p.getId());
	    		String[] data={id.toString(),p.getName(),p.getType(),p.getPort()};
                tableModel.addRow(data);	
	       }
        }
        catch (Exception e)
        {
        	System.out.println("DialogConfiguration.retrievePrinters "+e.getMessage());
        }
       
       packColumns(); 
    }*/
    
	private void applyChanges()
	{
			
	LookFeel = plaf_vals[cmbLookAndFeel.getSelectedIndex()];
		
		
		setConfigToFile();
	
	
	if(!LookFeelMemory.equalsIgnoreCase(LookFeel))
	{
        String lnf = plaf_vals[cmbLookAndFeel.getSelectedIndex()];
        //setLookAndFeel(lnf);
        //DialogMain.changeLookAndFeel(selection);
        //JxFrame.changeLookAndFeel(selection);
   	   try
   	   {
		 UIManager.setLookAndFeel(LookFeel); 
		 SwingUtilities.updateComponentTreeUI(this);
		 SwingUtilities.updateComponentTreeUI(parentFrame);//DialogConfiguration.getRootPane());//
	   //dialogMain.updateUIs();
	   }
       catch (UnsupportedLookAndFeelException exc)
        {System.err.println("DialogConfiguration:UnsupportedLookAndFeel: "+LookFeel+exc);}
       catch (Exception exc)
        {System.err.println("Error "+LookFeel+": "+exc);} 
	 }// if
		dispose();
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        DialogProgramConfiguration dialogProgConfiguration  = new DialogProgramConfiguration();
        //dialogConfiguration.setSize(360,300);
        dialogProgConfiguration.setVisible(true);
    }   
    
   class  ActionOk extends AbstractAction                 
   {       
        public ActionOk()
        {
        }
      	
    	public void actionPerformed(ActionEvent e)
      	{
           btnOk.doClick();
        }    	
    }                


   class  ActionClose extends AbstractAction                 
   {       
        public ActionClose()
        {
        }
      	
    	public void actionPerformed(ActionEvent e)
      	{
           btnClose.doClick();
        }    	
    }                
   
   /*class ListSelectionHandler implements ListSelectionListener 
   {

   public void valueChanged(ListSelectionEvent e)
   {

    if (e.getValueIsAdjusting() == false)
    {
        if (listMenu.getSelectedIndex() == -1)
        {
            //System.out.println("No selection, disable fire button.");
        }
        else
        {
            listSelection(listMenu.getSelectedIndex());
            //System.out.println("Selection, enable the fire button."+listMenu.getSelectedIndex());
        }
      }
   }  
   }*/
   
   /* private class Line
    {
        String caption;
        String image;
        String layoutName;
            	
        Line(String captionIn, String imageIn,String layoutNameIn)
        {
            caption = captionIn;
            image = imageIn;
            layoutName=layoutNameIn;
        }
        
        public String toString()
        {
        	
        	java.net.URL url = getClass().getResource(image);
        	return "<html><table><tr><td><img src='"+url+"'></td><td>"+caption+"</td><tr></html>";
        }

    }*/
    
   /* class ListImageCellRenderer extends JLabel implements ListCellRenderer
    {
        public ListImageCellRenderer()
        {        setOpaque(true);      }
        
        public JComponent getListCellRendererComponent(JListDec list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            Line line = (Line)value;
            setText(line.value);
            setIcon(line.image);
            //setBackground(isSelected ? Color.red : (index & 1) == 0 ? Color.cyan : Color.green);
            //setForeground(isSelected ? Color.white : Color.black);
            return this;
        }
    }   */ 
    
}
