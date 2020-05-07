/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.guicomps;


 
 import com.tool.utils.*;
 
 import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
 
 import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
 
public class JxPanelCollapsable extends JxPanel implements Constants
{
    String text;
    Font font;
    private boolean showPanel;
    //BufferedImage open, closed;
    //Rectangle target;
    JButtonSlideDecorated btnShow;
//    JSlidingPanel panelToContainOtherPanels
        JxPanel panelToContainOtherPanels;
    JLabel lblTitle;
    //JScrollPane scrollContainOther;
    JxPanel panelContained;
     JxPanel panelTitle;  //PanelGradient
    JPanel panelTitleButtons;
     JxPanel panelButtons ;
    //final int
    //    OFFSET = 30,
    //    PAD    =  5;

    public JxPanelCollapsable()
    {
        super();
 
         panelContained = new JxPanel();
        
      //   panelContained.add(new JLabel("label1hfdh"));
         panelTitleButtons = new JPanel();
        String title ="null";
        boolean showPanelIn=true;
        Color colorPanelGradientStart =CLR_PANEL_END;
        Color colorPanelGradientEnd =CLR_PANEL_START;
                                                    
        this.addComponentListener(new CompShowListener());
        
        this.setLayout(new BorderLayout());
        showPanel=showPanelIn;
        lblTitle =new JLabel();
        Font fnt = new Font (lblTitle.getFont().getName(), Font.PLAIN,lblTitle.getFont().getSize()+1);//Font(String name, int style, int size) 

        lblTitle.setFont(fnt);
        lblTitle.setOpaque(false);
  
      btnShow = new JButtonSlideDecorated();
        btnShow.setFocusable(false);
        //btnShow.setIconTextGap(0);
        //btnShow.setMargin(new Insets(1, 2, 1, 2));
        btnShow.setPreferredSize(new Dimension(20,19));// width, height
        //btnShow.setText("v");
        btnShow.setIcon(ICO_SHOWSLIDE);
        //btnShow.setVisible(false);
        btnShow.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {      
	        	 showPanel = !showPanel;
	        	  if(showPanel)
	        	  {
	        	  	 toggleShowPanel(showPanel);
	        	  }
	        	  else
	        	  {
	        	  	 toggleShowPanel(showPanel);
	        	  }
	        	  
                 
	        }
	    });  

    
       
       panelButtons = new JxPanel();
       FlowLayout fl = new FlowLayout();
       fl.setHgap(0);
       fl.setVgap(0);
       panelButtons.setLayout(fl);
       

//       panelTitle = new PanelGradient(colorPanelGradientEnd,colorPanelGradientStart,19);//(this.getBackground().brighter().brighter(), this.getBackground().darker(),23);
        panelTitle = new JxPanel();
        
        panelTitle.setLayout(new BorderLayout());
        //panelTitle.setPreferredSize(new Dimension(panelTitle.getWidth(), 21));
//        panelToContainOtherPanels = new JSlidingPanel();
        panelToContainOtherPanels = new JxPanel();
                
        panelToContainOtherPanels.setBorder(new TitledBorder(new MatteBorder(1,0,0,0,this.getBackground().darker())));//BorderFactory.createLineBorder(CLR_PANEL_CARD_BORDER));  
         
        panelTitle.add(lblTitle,BorderLayout.LINE_START);
        panelTitle.add(panelButtons,BorderLayout.LINE_END);

//      panelToContainOtherPanels.setComponent1(panelTitle);
//	panelToContainOtherPanels.setComponent2(panelContained);

        panelTitle.add(panelButtons,BorderLayout.LINE_END);
        
        panelToContainOtherPanels.setLayout(new BorderLayout());
        panelToContainOtherPanels.add(panelTitle, BorderLayout.PAGE_START);
        panelToContainOtherPanels.add(panelContained,BorderLayout.CENTER);

        //this.add(panelTitle, BorderLayout.PAGE_START);
        this.add(panelToContainOtherPanels, BorderLayout.CENTER);                                                
 
    }
   
    
    public void setHeadTitleVisible(boolean isVisible)
    {
        panelTitle.setVisible(isVisible);
        panelToContainOtherPanels.setBorder(null);
    }
    
    private void displayBrowserSearchForString(String butShowStrings)
    {
        //https://www.journaldev.com/7207/google-search-from-java-program-example
        
/*        UtilsString utilsString = new UtilsString();
       butShowStrings = "businesseye.gr+τιμολόγηση+"+butShowStrings;
        butShowStrings = butShowStrings.replaceAll(" ", "+");
        if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    URI uri = new URI("http://www.google.gr/search?q="+butShowStrings);
                    desktop.browse(uri);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
        
        }    
 */
     }    
    
    
    public void setEntityCollapsable(String title,  boolean showPanelIn,Color colorPanelGradientStart, Color colorPanelGradientEnd, int colsOfComps, boolean isBtnCollapseVisible,int buttonsShow , String butShowStrings)
    {
      showPanel=showPanelIn;

      
        JButtonSlideDecorated btnPanelInfo = new JButtonSlideDecorated();
        //btnPanelInfo.setText("i");
        btnPanelInfo.setForeground(CLR_PANEL_BORDER);/// CLR_PANEL_BORDER  //CLR_PANEL_END
        btnPanelInfo.setIcon(ICO_INFO16); //ICO_EXCLAMATION16 // ICO_INFO16
        
        btnPanelInfo.setToolTipText(butShowStrings);
        btnPanelInfo.setFocusable(false);
        btnPanelInfo.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {      
	        	
	        	displayBrowserSearchForString(butShowStrings);
                 
	        }
	    }); 
        
        
        
        //btnShow.setIconTextGap(0);
        //btnShow.setMargin(new Insets(1, 2, 1, 2));
        btnPanelInfo.setPreferredSize(new Dimension(20,19));// width, height
        //JxPanel panelTitleButtons = new JxPanel();
              FlowLayout fl = new FlowLayout();
       fl.setHgap(0);
       fl.setVgap(0);
       panelTitleButtons.setLayout(fl);
       panelTitleButtons.add(btnPanelInfo);

       
    if(buttonsShow==SHOW_BORDER_BTN_ONLY_ONE)
    {
         panelButtons.add(panelTitleButtons);
    }
    else if(buttonsShow==SHOW_BORDER_BTN_NONE)
    {
        
    }
    
       if(isBtnCollapseVisible)
       {
          panelButtons.add(btnShow);
       }


        panelToContainOtherPanels = new JxPanel();

    	lblTitle.setText("     "+title);
        lblTitle.setForeground(CLR_PANEL_BORDER);
        
         panelContained.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, colsOfComps));//new GridLayout(0,2));

    }
    
    @Override
    public Component add(Component comp)
    {
        
        return panelContained.add(comp);
    }
  
    
    public void toggleShowPanel(boolean show)
    {

    	//showPanel = !showPanel;
         panelContained.setVisible(show);

           revalidate();        
           repaint();     
    }
    
    public void showComponentOnPanelWhenLoaded()
    {
    	if(showPanel && panelToContainOtherPanels!= null && panelToContainOtherPanels.getComponentCount()>1)
    	{
             panelToContainOtherPanels.getComponent(1).setVisible(showPanel);
 //   	   panelToContainOtherPanels.showComponentWhenGuiLoaded();
           //getParent().validate();
           revalidate();        
           repaint();     		
    	}
    }
    
    private class CompShowListener implements ComponentListener
    {
    	
    public void componentHidden(ComponentEvent e) {
       // displayMessage(e.getComponent().getClass().getName() + " --- Hidden");
    }

    public void componentMoved(ComponentEvent e) {
       // displayMessage(e.getComponent().getClass().getName() + " --- Moved");
    }

    public void componentResized(ComponentEvent e)
    {
    	showComponentOnPanelWhenLoaded();// should be on componentShown
    	//    	System.out.println("JxPanelCollapsable.CompShowListener.componentResized "+e.getComponent().getClass().getName());

      //  displayMessage(e.getComponent().getClass().getName() + " --- Resized ");            
    }

    public void componentShown(ComponentEvent e)
    {
    	showComponentOnPanelWhenLoaded();
    	//System.out.println("JxPanelCollapsable.CompShowListener.componentShown "+e.getComponent().getClass().getName());
       // displayMessage(e.getComponent().getClass().getName() + " --- Shown");

    }    	
    	
    }    
   

	public static void main(String[] a)
	{

                    JButtonSlideDecorated btnPanelInfo = new JButtonSlideDecorated();
        //btnPanelInfo.setText("i");
        btnPanelInfo.setForeground(CLR_PANEL_BORDER);/// CLR_PANEL_BORDER  //CLR_PANEL_END
        btnPanelInfo.setIcon(ICO_INFO16); //ICO_EXCLAMATION16 // ICO_INFO16
        btnPanelInfo.setFocusable(false);
        //btnShow.setIconTextGap(0);
        //btnShow.setMargin(new Insets(1, 2, 1, 2));
        btnPanelInfo.setPreferredSize(new Dimension(20,19));// width, height
       JxPanel pnl = new JxPanel();
              FlowLayout fl = new FlowLayout();
       fl.setHgap(0);
       fl.setVgap(0);
       pnl.setLayout(fl);
       pnl.add(btnPanelInfo);
            
            
            
          JxPanelCollapsable pc1 = new JxPanelCollapsable();
           pc1.setEntityCollapsable("panel first", true,CLR_PANEL_END,CLR_PANEL_START,2,true,SHOW_BORDER_BTN_ONLY_ONE,"SHOW_BORDER_BTN_ONLY_ONE");//CLR_PANEL_START,CLR_PANEL_END);
           
    //       pc1.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2));//new GridLayout(0,2));
           
	  pc1.add(new JLabel("label1hfdh"));
	  pc1.add(new JTextField(15));
	  pc1.add(new JLabel("label2"));
	  pc1.add(new JTextField(17));
	  pc1.add(new JLabel("labdh"));
	  pc1.add(new JTextField(15));
	  pc1.add(new JLabel("label2fsd"));
	  pc1.add(new JTextField(17));
	  pc1.add(new JLabel("la2fsd"));
	  pc1.add(new JTextField(17));
//		JxPanelCollapsable pc1 = new JxPanelCollapsable(panel1,null, "panel first", true,CLR_PANEL_START,CLR_PANEL_END);
		
          JxPanelCollapsable panel2 = new JxPanelCollapsable();
           panel2.setEntityCollapsable("panel second", true,CLR_PANEL_END,CLR_PANEL_START,4,true,SHOW_BORDER_BTN_ONLY_ONE,"SHOW_BORDER_BTN_ONLY_ONE");//CLR_PANEL_START,CLR_PANEL_END);
           
	  panel2.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 4));//new GridLayout(0,2));
	  panel2.add(new JLabel("label1hfdh"));
	  panel2.add(new JTextField(7));
	  panel2.add(new JLabel("label2"));
	  panel2.add(new JTextField(8));
	  panel2.add(new JLabel("labdh"));
	  panel2.add(new JTextField(7));
	  panel2.add(new JLabel("label2fsd"));
	  panel2.add(new JTextField(8));
	  panel2.add(new JLabel("lab2fsd"));
	  panel2.add(new JTextField(8));	  	  		  	
        
        //JxPanelCollapsable pc2 = new JxPanelCollapsable(panel2,null, "panel second", true,CLR_PANEL_START_ALTER,CLR_PANEL_END_ALTER);

/*
	  JxPanel panel3 = new JxPanel();
	  panel3.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2));//new GridLayout(0,2));
	  panel3.add(new JLabel("label1hfdh"));
	  panel3.add(new JTextField(15));
	  panel3.add(new JLabel("label2"));
	  panel3.add(new JTextField(17));
	  panel3.add(new JLabel("labdh"));
	  panel3.add(new JTextField(15));
	  panel3.add(new JLabel("label2fsd"));
	  panel3.add(new JTextField(17));
	  panel3.add(new JLabel("lab2fsd"));
	  panel3.add(new JTextField(17));	  	  		  	
        
        JxPanelCollapsable pc3 = new JxPanelCollapsable(panel3,null, "panel third", true,CLR_PANEL_START,CLR_PANEL_END);


	  JxPanel panel4 = new JxPanel();
	  panel4.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 4));//new GridLayout(0,2));
	  panel4.add(new JLabel("label1hfdh"));
	  panel4.add(new JTextField(7));
	  panel4.add(new JLabel("label2"));
	  panel4.add(new JTextField(8));
	  panel4.add(new JLabel("labdh"));
	  panel4.add(new JTextField(7));
	  panel4.add(new JLabel("label2fsd"));
	  panel4.add(new JTextField(8));
	  panel4.add(new JLabel("lab2fsd"));
	  panel4.add(new JTextField(8));	  	  		  	
        
        JxPanelCollapsable pc4 = new JxPanelCollapsable(panel4,null, "panel forth", true,CLR_PANEL_START,CLR_PANEL_END);

				//pc1.setComponent1(lblTitle);
				//pc1.setComponent2(panel1);
			//	s.getComponent2().setSize(new Dimension(100, 300));
	*/			
			JFrame f=new JFrame("");
			JPanelDecorated panelMain = new JPanelDecorated();
			panelMain.setLayout(new BorderLayout());
			JScrollPane scrlPane = new JScrollPane();
			scrlPane.setViewportView(panelMain);
			
			JPanelDecorated panelContainer = new JPanelDecorated();
            panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.PAGE_AXIS));
             
             
            
             panelContainer.add(pc1);
             panelContainer.add(panel2);
            // panelContainer.add(pc3);
            // panelContainer.add(pc4);
             
             panelMain.add(panelContainer, BorderLayout.PAGE_START);
             
				f.setContentPane(scrlPane);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(420,390);
		        f.setVisible(true);
	}//*/

}

