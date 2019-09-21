/*
 */
package com.tool.gui;

import com.tool.guicomps.Constants;
import static com.tool.guicomps.Constants.CLR_PANEL_END_ALTER;
import static com.tool.guicomps.Constants.CLR_PANEL_START_ALTER;
import static com.tool.guicomps.Constants.ICO_CANCEL16;
import static com.tool.guicomps.Constants.ICO_OK16;
import com.tool.guicomps.JButtonForPanelDecorated;
import com.tool.guicomps.JPanelCurvedGradient;
import com.tool.guicomps.JPanelDecorated;
import com.tool.guicomps.JxPanel;
import com.tool.jdbc.Database;
import com.tool.utils.GridLayoutVariable;
import com.tool.utils.UtilsDate;
import com.tool.utils.UtilsGui;
import com.tool.utils.UtilsResource;
import com.tool.utils.UtilsString;
import com.tool.utils.UtilsTable;
import com.tool.utils.VariablesGlobal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 *
 * @author user
 */
public class DialogUserLogin  extends JDialog implements Constants
{
    private UtilsTable utilsTable;
    private UtilsGui utilsGui;
    private UtilsDate utilsDate;
    private UtilsString utilsString;
    private JPanelDecorated panelAllOnIt;
   private JPanelCurvedGradient paneTitle;
   private JButtonForPanelDecorated btnClose;
    private JButtonForPanelDecorated btnOk;    
    private JxPanel panelBottom;
    private JxPanel panelActionButtons;  
    private JxPanel panelMain;
    private JLabel lblPassword;
    private JLabel lblUser;
    private JLabel lblTitle;
    
    private String userId="";

    private JTextField txtPassword;
    private JTextField txtUser;    
    private boolean isLoginAgain = false;
    private Database db= new Database();
     public DialogUserLogin()
    {
       

          initialize();
    }
    
     
    /** Creates new form LoginForm */
    public DialogUserLogin(JFrame parent)
    {
        super(parent,VariablesGlobal.appName+"   "+UtilsResource.getString("Login"),true);//super(parent, modal);
       
        initialize();
       


    }     
     
     
    /**
     * This method is called from within the constructor to
     * initialize the form.
     */
    private void initialize() 
    {    
        
          utilsGui = new UtilsGui();
          utilsString = new UtilsString();
        
        lblTitle = new JLabel();
        panelMain = new JxPanel(); 
        //panelMain = new JxPanel();
        panelActionButtons = new JxPanel();        
        
        lblUser = new JLabel();
        lblPassword = new JLabel();
        txtUser = new JTextField(18);
        txtPassword = new JPasswordField(18);
        btnOk = new JButtonForPanelDecorated();
        btnClose = new JButtonForPanelDecorated();        
  setResizable(true);

        lblTitle.setText("::::::: "+VariablesGlobal.appName+" :: "+VariablesGlobal.appProductCaption+"  :: ver.:"+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+" :::::::");//(UtilsResource.getString("Login"));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));        
        //lblTitle.setForeground(Color.white);
        //lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));//top,left,bottom,right
        
        //lblTitle.setIcon(ICO_LOCKED32);  //ICO_BACK
              Color blue= new Color(129, 169, 226);
              //Color blue= new Color(63, 183, 255);
              Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
            //lightblue =  lightblue.brighter().brighter();
        paneTitle = new JPanelCurvedGradient(CLR_PANEL_START_ALTER,CLR_PANEL_END_ALTER,0,0);//PanelGradient(lblTitle.getBackground().brighter().brighter(),lblTitle.getBackground().darker(),60);//new PanelTitle(new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),Color.white,"title");
        paneTitle.add(lblTitle);
        
        
        
       JPanel panelTextBoxes  = new JPanel();
        panelTextBoxes.setOpaque(false);
        panelTextBoxes.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));
        
        panelMain.add(new JLabel(""));

        
        lblUser.setText(UtilsResource.getString("User"));
        panelTextBoxes.add(lblUser);//, new AbsoluteConstraints(leftOfRightComps+21, topOfLabels, -1, -1));

        //txtUser.setText("nik");
        panelTextBoxes.add(txtUser);//, new AbsoluteConstraints(leftOfRightComps, topOfComponents, 90, -1));

        lblPassword.setText(UtilsResource.getString("Password"));
        panelTextBoxes.add(lblPassword);//, new AbsoluteConstraints(leftOfRightComps+21, 55, -1, -1));
        
        
       // txtPassword.setText("1");
        panelTextBoxes.add(txtPassword);//, new AbsoluteConstraints(leftOfRightComps, 70, 90, -1));
        
        
        
        panelMain.add(panelTextBoxes);
        
        panelBottom = new JxPanel();

//        panelBottom.setLayout(new AbsoluteLayout());
        //panelBottom.setLayout(new BoxLayout(panelBottom, BoxLayout.X_AXIS));
        panelBottom.setLayout(new FlowLayout());
        panelBottom.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 3));
        

        panelActionButtons.setLayout(new BoxLayout(panelActionButtons, BoxLayout.X_AXIS));

        btnOk.setIcon(ICO_OK16);
        btnOk.setFocusable(false);
        btnOk.setText("<html>"+UtilsResource.getString("OK")+" <b>F5</b></html>");
       //panelBottom.add(btnOk, new AbsoluteConstraints(150, 0, 80, -1));
        //getRootPane().setDefaultButton(btnOk);
           btnOk.addActionListener(new ActionListener()
           {
	         public void actionPerformed(ActionEvent e) 
	         {      
                     closeAndContinue();	      
                 }
	       });
	    Action actionOk = new DialogUserLogin.ActionOk();
        btnOk.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), "ok"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnOk.getActionMap().put("ok", actionOk);
        
           btnClose.setText("<html>"+UtilsResource.getString("Close")+" <b>esc</b></html>");
           btnClose.setIcon(ICO_CANCEL16);
           btnClose.setFocusable(false);
           btnClose.addActionListener(new ActionListener()
           {
	         public void actionPerformed(ActionEvent e) 
	         {      close();     }
	       });
        Action actionClose = new DialogUserLogin.ActionClose();
        btnClose.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnClose.getActionMap().put("close", actionClose);



        panelActionButtons.add(btnOk);
        panelActionButtons.add(btnClose);
        panelActionButtons.setOpaque(false);
       // panelBottom.add(Box.createHorizontalGlue());
 //       panelBottom.add(panelBottomCompany);
 //       panelBottom.add(panelBottomYear);
        panelBottom.add(panelActionButtons);      

        getContentPane().add(paneTitle, BorderLayout.NORTH);
        
        panelMain.setOpaque(false);
        panelBottom.setOpaque(false);
        
        panelAllOnIt = new JPanelDecorated();//(blue,lightBlue,0,1);
        panelAllOnIt.setLayout(new BorderLayout());
        panelAllOnIt.add(panelMain, BorderLayout.CENTER);  
        //getContentPane().add(panelMain, BorderLayout.CENTER);
        panelAllOnIt.add(panelBottom, BorderLayout.SOUTH);        
        getContentPane().add(panelAllOnIt, BorderLayout.CENTER);
        

        

        
        setCloseClick();
        pack();
        locateOnCenterOfTheScreen();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //new DialogCompanyLogin(new JFrame(), true).show();
        DialogUserLogin loginUser  = new DialogUserLogin();
        loginUser.setVisible(true);
    }



    /**
     * Checks if login data is correct.
     * @return if login data is correct.
     */  
   public boolean checkUserNPass()
   {
      boolean loginOk=false;
      String user = getUser();
      String pass = getPassword();
      
      
      if(user.equalsIgnoreCase("") || pass.equalsIgnoreCase(""))
      {
          utilsGui.showMessageError(this,"Ο χρήστης και ο κωδικός πρέπει να είναι συμπληρωμένα!");
           loginOk=false;
      }
      else
      {
      try
      {	   
      	   ResultSet rs;
           ResultSetMetaData rsmd;
           
           String query = "SELECT userId, username, password FROM dbuser WHERE username='"+utilsString.escapeSqlInjection(user)+"' AND password='"+utilsString.escapeSqlInjection(pass)+"' ";
           //String query = "SELECT userId, username, password FROM dbuser WHERE username='"+user+"' AND password='"+pass+"' ";  //      ' OR '1'='1 
           
         if (VariablesGlobal.globalShowSQL)
         {          
           System.out.println("DialogUserLogin.checkUserNPass: "+query);
         }    
           
           db.retrieveDBDataFromQuery(query,"DialogUserLogin.checkUserNPass");
           //db.retrieveDBDataFromQueryPreparedStatement(query,"DialogCompanyLogin.checkUserNPassFordbCompanyId");     ' OR '1'='1 
   	   rs=db.getRS();
   	   rsmd=db.getRSMetaData();
         
         
       
         if ( rs.next() ) // for mysql, h2 and sqlite   //rs.isFirst()) //hasOneOnlyRow !=0 ) 
         { 
               userId =  rs.getString("userId");  //hasOneOnlyRow; // the first and only
               loginOk=true; 
             
         }
         else
         {
           	loginOk=false;
           	utilsGui.showMessageError(UtilsResource.getString("UsernameAndPasswordNotCorrect"));
         }
          //System.out.println("DialogCompanyLogin.checkUserNPassFordbCompanyId  hasOneOnlyRow="+hasOneOnlyRow);
          
           //db.releaseConnectionRs();
           //setListData(Object[] listData) 
       }
       catch ( SQLException sqlex)
       {
           System.out.println("error:DialogUserLogin.checkUserNPass: "+sqlex.getMessage());
           //sqlex.printStackTrace();
       }
      finally
      {
             closeDB();     
      }
      }// if
   	   return loginOk;
   }


    

    /**
     * Gets the username.
     */     
   public String getUser()
   {
      return txtUser.getText().trim();
   }
 
    /**
     * Gets the userId.
     */     
   public String getUserId()
   { 
      return userId;
   }

    /**
     * Gets the password.
     */    
   public String getPassword()
   {
      return txtPassword.getText().trim();
   }    
    
    
        /**
     * create a window listener to respond to the window close click.
     *
     */
   private void setCloseClick()
   {
   	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
    addWindowListener(new WindowAdapter()
    {
       public void windowClosing(WindowEvent e)
       {    close();   }
    });
   }
   
   
   public boolean closeAndContinue()
   {
       
      boolean loginOk = checkUserNPass();       
      System.out.println("DialogUserLogin.closeAndContinue    loginOk:"+loginOk);
      
      if(loginOk)
      {
          dispose();
      }
      
    return loginOk;
   }
   
   public void setLoginAgain(boolean loginAgain)
   {
       isLoginAgain = loginAgain;
       if(isLoginAgain)
       {
         btnClose.setIcon(ICO_CANCEL16);
         btnClose.setText("<html>"+UtilsResource.getString("Close")+" <b>esc</b></html>");
       }
       else
       {
           btnClose.setIcon(ICO_EXIT16);
           btnClose.setText("<html>"+UtilsResource.getString("Exit")+" <b>esc</b></html>");
       }
       // from inside the program when already logged in
       // log in for the firstTime

   }
   
    private void close()
    {
       
       if (isLoginAgain == true ) // exit
       {
          closeDB();
          this.dispose();       	   
       }
       else
       {      
          closeDB();
           System.exit(0);
       }
    }   
   
    
    private void closeDB()
    {
    	db.releaseConnectionRs();
        db.releaseConnectionRsmd();
    }    
    
    
    /**
     * Locates the frame on the screen center.
     */
    private void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }   

   class  ActionOk extends AbstractAction                 
   {       
        public ActionOk()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{    btnOk.doClick();      }    	
    }                


   class  ActionClose extends AbstractAction                 
   {       
        public ActionClose()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{     btnClose.doClick();     }    	
    } 
    
}
