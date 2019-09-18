package com.tool.gui;

import com.tool.model.LookUpMgt;
import com.tool.model.*;
import com.tool.guicomps.*;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DialogLookUp extends JDialog implements Constants
{
	    private JPanelDecorated panelAllOnIt;
    private static DialogLookUp dialog;
    private static String keyValue = "";
    private static String keyDescription = "";
    private String lookupEntity;
    private static String lookupQuery;
    private PanelOneDataManyRec panelOneDataManyRec;
    private LookUpMgt lookUp;
    private final JButtonForPanelDecorated btnSet;
    private JButtonForPanelDecorated btnClose;
    //private JLabel lblTop;
    private	static String keyField;
    private static int intColFieldDescription;
    private PanelManagement panelManagement;
    private String[] fieldsForSums;
    //private static EntityReport entityReport;
    /**
     * Set up the dialog.  The first argument can be null,
     * but it really should be a component in the dialog's
     * controlling frame.
     */
    public static void initialize(JFrame parent)
    {
        //Frame frame = JOptionPane.getFrameForComponent(null);
        dialog = new DialogLookUp(parent);
    }

    public static void initialize()
    {
        //JFrame frame = (JFrame)JOptionPane.getFrameForComponent(null);
        dialog = new DialogLookUp(null);
    }


    private void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }

    /**
     * Show the initialized dialog.  The first argument should
     * be null if you want the dialog to come up in the center
     * of the screen.  Otherwise, the argument should be the
     * component on top of which the dialog should appear.
     */
    public static String showDialog(Component comp, String lookupEntity, String lookupQueryIn,String keyFieldIn ,String selectedKeyValue, 
            boolean showToolbar,/*String yearEnforceIn,*/ PanelManagement panelManagementIn,String[] fieldsForSumIn, ArrayList fieldTxtsIn )//,EntityReport entityReportIn)
    {  
        //entityReport=entityReportIn;
        //System.out.println("DialogLookUp.showDialog ----------------------------------- lookupQueryIn"+lookupQueryIn);
        if (dialog != null)
        {
        	lookupQuery=lookupQueryIn;
        	keyField=keyFieldIn;
          //System.out.println("DialogLookUp.showDialog  ---**    keyField:"+keyField+"     selectedKeyValue:"+selectedKeyValue+"  lookupQuery:"+lookupQuery);
                dialog.setLookUpEntityAndQuery(lookupEntity, lookupQuery,/*yearEnforceIn,*/ panelManagementIn, fieldsForSumIn, fieldTxtsIn);//,entityReportIn);
          
            dialog.setSelected(keyField,selectedKeyValue,showToolbar);
            dialog.locateOnCenterOfTheScreen();
            //dialog.setLocationRelativeTo(comp);
            dialog.setVisible(true);
        }
        else
        {
            System.out.println("DialogLookUp requires you to call initialize "
                               + "before calling showDialog.");
        }
        //System.out.println("DialogLookUp.showDialog  ---**    keyField:"+keyField+"     return--> keyValue:"+keyValue+"   selectedKeyValue:"+selectedKeyValue+"       lookupQuery:"+lookupQuery);
        return keyValue;
    }
    
    

    
    // setSelected data to panelOneDataManyRecData
    private void setSelected(String keyField, String selectedKeyValue, boolean showToolbar)
    {
    	//System.out.println("DialogLookUp.setSelected keyField"+keyField+" "+selectedKeyValue);
        panelOneDataManyRec.setSelectedTableRow(keyField, selectedKeyValue,showToolbar);
    }
    
    // set data for panelOneDataManyRecData
    private void setLookUpEntityAndQuery(String lookupEntityIn, String lookupQueryIn,/*String yearEnforceIn,*/ PanelManagement panelManagementIn,String[] fieldsForSumsIn,
            ArrayList fieldTxtsIn)//, EntityReport entityReportIn)
    {
        lookupEntity = lookupEntityIn;
        lookupQuery = lookupQueryIn;
        lookUp = new LookUpMgt();
        fieldsForSums=fieldsForSumsIn;
        //System.out.println("DialogLookUp.setLookUpEntityAndQuery "+lookupQuery);
        
        this.setTitle("Επιλογή "+lookUp.getStrOfOne(lookupEntity));
      //  EntityInfoM eim = new EntityInfoM();
       // EntityInfo ent = (EntityInfo)
        //panelOneDataManyRec.setEntity(lookupEntity, lookupQuery,null,null,null,null,null,null,null,null,null,null,null,false,false, false,lookUp.getStrOfMany(lookupEntity),false,false); //entity, query, showExtendedSummary
       //System.out.println("DialogLookUp.setLookUpEntityAndQuery  OOO-00000-OOOOOOOO    lookupEntity:"+lookupEntity+"           entityReportIn:"+entityReportIn+"               lookupQuery:"+lookupQuery+"      queryeditable:"+lookUp.getQueryEditable(lookupEntity));
        
        
        panelOneDataManyRec.setEntity(lookupEntity, lookupQuery,lookUp.getQueryEditable(lookupEntity),fieldsForSums,//null is for fieldsForSums,
         "title", lookUp.getLookUpKeyTranslation(lookupEntity),lookUp.getLookUpKey(lookupEntity)/*,"",""/*formGlobalTableToApply1*/,lookUp.getIcon(lookupEntity),
         lookUp.getEntityFilterSettings(lookupEntity),null,lookUp.getStrOfOne(lookupEntity),lookUp.getStrOfMany(lookupEntity), true,
         lookUp.getIntValidationColumn(lookupEntity), lookUp.getIntValidationType(lookupEntity) , lookUp.getCategoryNodes(lookupEntity), lookUp.getEntityPanel(lookupEntity), 
         lookUp.getFieldsOnTitle(lookupEntity),lookUp.getFieldsOnTitleCaption(lookupEntity),null,/*yearEnforceIn,*/fieldTxtsIn,panelManagementIn);//, entityReportIn);//, updateAdditionalIn); 
        /*  public void setEntity(String entityIn,String queryReadOnlyIn, String queryIn ,
     String titleIn,String primKey,String primKeyDbIn, ImageIcon icoIn, String[] searchCaption, 
     String[] searchFieldIn,String strOfOneIn, String strOfManyIn, Boolean isLookUpDialog,String[] categoryNodesIn,
     EntityPanel[] entityPanelIn, int[]fieldsOnTitleIn, String[] fieldsOnTitleCaptionIn)*/
      
      intColFieldDescription=lookUp.getLookUpFieldColDescription(lookupEntity);
      
      
      
        pack();
    }

    public DialogLookUp(JFrame parent)
    {
        super(parent,"επιλογή...", true);
        
        //buttons
        btnSet = new JButtonForPanelDecorated();
        btnSet.setText("<html>Eπιλογή <b>F5</b></html>");
        btnSet.setIcon(ICO_OK16);
        btnSet.setFocusable(false);
        btnSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {   setButton() ;   }
        });
        Action actionSet = new ActionSet();
        btnSet.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), "set"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnSet.getActionMap().put("set", actionSet);



        btnClose = new JButtonForPanelDecorated();
        btnClose.setText("<html>'Aκυρο <b>esc</b></html>");
        btnClose.setIcon(ICO_CANCEL16);
        btnClose.setFocusable(false);
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 closeWindow();//frame.setVisible(false);//DialogLookUp.dialog.setVisible(false);
            }
        });
        Action actionClose = new ActionClose();
        btnClose.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnClose.getActionMap().put("close", actionClose);

        
        getRootPane().setDefaultButton(btnSet);

        panelOneDataManyRec = new PanelOneDataManyRec(parent);
        //lblTop = new JLabel("Παρακαλώ επιλέξτε ");
        //lblTop.setIcon(ICO_INFO16);
        //label.setText("Παρακαλώ επιλέξτε "+dbEntity);

        //Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        //buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
       // buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(btnSet);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(btnClose);
        
        //to click set on double clicking table
        panelOneDataManyRec.addLookUpTableActionListener(new LookUpTableActionListener());

        
        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = getContentPane();
        //contentPane.add(lblTop, BorderLayout.PAGE_START);
        //contentPane.add(panelOneDataManyRec, BorderLayout.CENTER);
        //contentPane.add(buttonPane, BorderLayout.PAGE_END);

        panelAllOnIt = new JPanelDecorated();
        panelAllOnIt.setLayout(new BorderLayout());
        panelAllOnIt.add(panelOneDataManyRec, BorderLayout.CENTER);
        panelAllOnIt.add(buttonPane, BorderLayout.PAGE_END);

        contentPane.add(panelAllOnIt, BorderLayout.CENTER);
        
        setCloseClick();
        pack();
    }
    
    private void setButton()
    {
   //     DialogLookUp.value = (String)(list.getSelectedValue());
          lookUp = new LookUpMgt();
          //System.out.println("DialogLookUp.setButton() key "+lookUp.getLookUpKeyFT(lookupEntity));
          //DialogLookUp.keyValue = panelOneDataManyRecData.getSelectedDataRowKeyValue(lookUp.getLookUpKeyFT(lookupEntity));
          //int tableRow = panelOneDataManyRec.getSelectedTableRow();
          DialogLookUp.keyValue = panelOneDataManyRec.getSelectedRowPrimaryKeyValue(lookupQuery,keyField,intColFieldDescription);
          DialogLookUp.keyDescription = panelOneDataManyRec.getSelectedRowDescriptionValue();
          
         //System.out.println("DialogLookUp.setButton   keyValue:"+DialogLookUp.keyValue+ " keyDescription:"+DialogLookUp.keyDescription+"  keyField:"+keyField);
          dispose();
          //DialogLookUp.dialog.setVisible(false);         	
          //System.out.println("DialogLookUp.setButton() "+DialogLookUp.keyValue);

    }
    
  private class LookUpTableActionListener implements ActionListener
  { // look at     public void addListSelectionListener(ListSelectionListener al) in panelOneDataManyRecData
    // same as PanelTDMR and PanelODMR and DialogLookup
      public void actionPerformed(ActionEvent e)
      {
          //System.out.println("panelODMR.selection table double clicked.");

           btnSet.doClick();                      
            
      } 
    
  } 
  
  public static String getFieldDescriptionValue()
  {
  	 return DialogLookUp.keyDescription;
  }
  
   private void setCloseClick()
   {
   	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
    addWindowListener(new WindowAdapter()
    {
       public void windowClosing(WindowEvent e)
       {    closeWindow();   }
    });
   }
    
    /**
     * This is here so that you can view DialogLookUp even if you
     * haven't written the code to include it in a program. 
     */
    public static void main(String[] args)
     {
       /* String[] names = {"Arlo", "Cosmo", "Elmo", "Hugo",
                          "Jethro", "ΓΙΑΝΤΣΙΔΗΣ", "Milo", "Nemo",
                          "Otto", "Ringo", "Rocco", "Rollo"};
        JFrame f = new JFrame("Name That Baby");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                 System.exit(0);
            }
        });

        JLabel intro = new JLabel("Επέλεξε όνομα. The chosen name:");

        //final JLabel name = new JLabel("ΓΙΑ");
        final JTextField txtLookUp = new JTextField(12);
        txtLookUp.setText("1");
        intro.setLabelFor(txtLookUp);
        txtLookUp.setForeground(Color.black);
        
        final JTextField txtEntity = new JTextField(9);
        txtEntity.setText("farmer");


        JButton button = new JButton("επιλογή");
        button.setIcon( new ImageIcon("images/Table22.png"));
        DialogLookUp.initialize(f, names, "Επιλογή αγρότη"); ///1
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                String selectedName = DialogLookUp.showDialog(null,txtEntity.getText(), null , txtLookUp.getText());  ///2
//                txtLookUp.setText(selectedName);
            }
        });

        JPanel contentPane = new JPanel();
        f.setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        contentPane.add(intro);
        contentPane.add(txtLookUp);
        contentPane.add(txtEntity);
        contentPane.add(Box.createRigidArea(new Dimension(0,10)));
        contentPane.add(button);
        intro.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        txtLookUp.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        f.pack();
        f.setVisible(true);*/
    }
    
    private void closeWindow()
    {
       keyValue="";
       dispose();
    }
    
    class  ActionSet extends AbstractAction                 
    {       
        public ActionSet()
        {        }    	
    	public void actionPerformed(ActionEvent e)
      	{          btnSet.doClick();       }    	
    }                

    class  ActionClose extends AbstractAction                 
    {       
        public ActionClose()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{           btnClose.doClick();        }    	
    }                

    
}
