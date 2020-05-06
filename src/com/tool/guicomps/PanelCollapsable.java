// http://www.coderanch.com/t/341737/Swing-AWT-SWT-JFace/java/Expand-Collapse-Panels
 
 package com.tool.guicomps;
 
 import com.tool.utils.*;
 
 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
 import javax.swing.border.LineBorder;
 
public class PanelCollapsable extends JxPanel implements Constants
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
    //final int
    //    OFFSET = 30,
    //    PAD    =  5;
 
    public PanelCollapsable(JxPanel panelContained,JPanel panelTitleButtons, String title, boolean showPanelIn,Color colorPanelGradientStart, Color colorPanelGradientEnd)
    {
       // this.text = text;
        //addMouseListener(ml);
        //font = new Font("sans-serif", Font.PLAIN, 12);
        //showPanel = false;
        //setBackground(new Color(200,200,220));
        //setPreferredSize(new Dimension(200,17));
        //setBorder(BorderFactory.createRaisedBevelBorder());
        //setPreferredSize(new Dimension(200,17));
        //createImages();
        //setRequestFocusEnabled(true);
        
        this.addComponentListener(new CompShowListener());
        
        this.setLayout(new BorderLayout());
        showPanel=showPanelIn;
        lblTitle =new JLabel();
        Font fnt = new Font (lblTitle.getFont().getName(), Font.BOLD,lblTitle.getFont().getSize()+1);//Font(String name, int style, int size) 
        

        
        lblTitle.setFont(fnt);
        //lblTitle.setForeground(CLR_PANEL_BORDER);
  
  
       /*JButtonSlideDecorated btnTable = new JButtonSlideDecorated();
       btnTable.setFocusable(false);
       btnTable.setIcon(ICO_TABLE16);
       //btnTable.setMargin(new Insets(1, 2, 1, 2));
       btnTable.setPreferredSize(new Dimension(20,19));// width, height
  
       JButtonSlideDecorated btnHelp = new JButtonSlideDecorated();
       btnHelp.setFocusable(false);
       btnHelp.setIcon(ICO_UPDATE16);
       //btnHelp.setMargin(new Insets(1, 2, 1, 2));
       btnHelp.setPreferredSize(new Dimension(20,19));// width, height*/
  
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


       
       JxPanel panelButtons = new JxPanel();
       FlowLayout fl = new FlowLayout();
       fl.setHgap(0);
       fl.setVgap(0);
       panelButtons.setLayout(fl);
       
       /*panelButtons.add(btnTable);
       panelButtons.add(btnHelp);*/
       if(panelTitleButtons!=null)
       {
          panelButtons.add(panelTitleButtons);	
       }
       else
       {
           
      
           
           
       }
       
       
         
       /* JButtonSlideDecorated btn = new JButtonSlideDecorated();
        btn.setFocusable(false);
        //btnShow.setIconTextGap(0);
        //btnShow.setMargin(new Insets(1, 2, 1, 2));
        btn.setPreferredSize(new Dimension(20,19));// width, height
       
       panelButtons.add(btn);*/
       panelButtons.add(btnShow);
       
        //JPanel panelTitle = new JPanel();
        PanelGradient panelTitle = new PanelGradient(colorPanelGradientEnd,colorPanelGradientStart,19);//(this.getBackground().brighter().brighter(), this.getBackground().darker(),23);
        //panelTitle.setSize(new Dimension(12,12));
        panelTitle.setLayout(new BorderLayout());
        //panelTitle.setPreferredSize(new Dimension(panelTitle.getWidth(), 21));
//        panelToContainOtherPanels = new JSlidingPanel();
        panelToContainOtherPanels = new JxPanel();
        panelToContainOtherPanels.setBorder(BorderFactory.createLineBorder(CLR_PANEL_CARD_BORDER));  
        
        //LineBorder line = new LineBorder(CLR_PANEL_BORDER, 2, true);
        panelTitle.setBorder(BorderFactory.createLineBorder(CLR_PANEL_CARD_BORDER));        
       // panelToContainOtherPanels.setBorder(BorderFactory.createLineBorder(CLR_PANEL_BORDER));
        
        //scrollContainOther= new JScrollPane();
        //scrollContainOther.setSize(new Dimension(10,10));
        //scrollContainOther.setBorder(null);
        //scrollContainOther.setViewportView(panelToContainOtherPanels);


    	lblTitle.setText("    "+title);
        lblTitle.setForeground(CLR_PANEL_BORDER);
    	//showPanel = show;
    	//panelContained= panel;
    	//panelToContainOtherPanels.add(panelContained,BorderLayout.CENTER);

        panelTitle.add(lblTitle,BorderLayout.LINE_START);
        panelTitle.add(panelButtons,BorderLayout.LINE_END);

//        panelToContainOtherPanels.setComponent1(panelTitle);
//	panelToContainOtherPanels.setComponent2(panelContained);


        panelToContainOtherPanels.setLayout(new BorderLayout());
        panelToContainOtherPanels.add(panelTitle, BorderLayout.PAGE_START);
        panelToContainOtherPanels.add(panelContained,BorderLayout.CENTER);

        //this.add(panelTitle, BorderLayout.PAGE_START);
        this.add(panelToContainOtherPanels, BorderLayout.CENTER);

    }
 
    public void toggleShowPanel(boolean show)
    {

    	//showPanel = !showPanel;
         panelToContainOtherPanels.getComponent(1).setVisible(show);
        
    	/*if(show)
    	{
                      panelToContainOtherPanels.showComponent();
    	}
    	else
    	{

               panelToContainOtherPanels.hideComponent();    		
    	}*/
 

           //getParent().validate();
           revalidate();        
           repaint();     
    }
    
    public void showComponentOnPanelWhenLoaded()
    {
    	if(showPanel)
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
    	//    	System.out.println("PanelCollapsable.CompShowListener.componentResized "+e.getComponent().getClass().getName());

      //  displayMessage(e.getComponent().getClass().getName() + " --- Resized ");            
    }

    public void componentShown(ComponentEvent e)
    {
    	showComponentOnPanelWhenLoaded();
    	//System.out.println("PanelCollapsable.CompShowListener.componentShown "+e.getComponent().getClass().getName());
       // displayMessage(e.getComponent().getClass().getName() + " --- Shown");

    }    	
    	
    }    
   /* public void addPanel(String title, JxPanel panel, boolean show)
    {
    	lblTitle.setText("    "+title);
    	showPanel = show;
    	panelContained= panel;
    	//panelToContainOtherPanels.add(panelContained,BorderLayout.CENTER);
    	
        
		panelToContainOtherPanels.setComponent2(panelContained);
    	if(show)
    	{
    	   panelToContainOtherPanels.showComponent();	
    	       	   	
    	}
    	
    	//panelToContainOtherPanels.setVisible(show);
    }*/
    
    
  /*   protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        /*Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        if(showPanel)
            g2.drawImage(open, PAD, 0, this);
        else
            g2.drawImage(closed, PAD, 0, this);
        g2.setFont(font);
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics(text, frc);
        float height = lm.getAscent() + lm.getDescent();
        float x = OFFSET;
        float y = (h + height)/2 - lm.getDescent();
        g2.drawString(text, x, y);*/
/*    }
 
   private void createImages()
    {
        int w = 20;
        int h = getPreferredSize().height;
        target = new Rectangle(2, 0, 20, 18);// 3 rd width
        open = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = open.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(getBackground());
        g2.fillRect(0,0,w,h);
        int[] x = { 2, w/2, 18 };
        int[] y = { 4, 15,   4 };
        Polygon p = new Polygon(x, y, 3);
        g2.setPaint(Color.green.brighter());
        g2.fill(p);
        g2.setPaint(Color.blue.brighter());
        g2.draw(p);
        g2.dispose();
        closed = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        g2 = closed.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(getBackground());
        g2.fillRect(0,0,w,h);
        x = new int[] { 3, 13,   3 };
        y = new int[] { 4, h/2, 16 };
        p = new Polygon(x, y, 3);
        g2.setPaint(Color.red);
        g2.fill(p);
        g2.setPaint(Color.blue.brighter());
        g2.draw(p);
        g2.dispose();
    }*/

	public static void main(String[] a)
	{
	 
	  JxPanel panel1 = new JxPanel();
	  panel1.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2));//new GridLayout(0,2));
	  panel1.add(new JLabel("label1hfdh"));
	  panel1.add(new JTextField(15));
	  panel1.add(new JLabel("label2"));
	  panel1.add(new JTextField(17));
	  panel1.add(new JLabel("labdh"));
	  panel1.add(new JTextField(15));
	  panel1.add(new JLabel("label2fsd"));
	  panel1.add(new JTextField(17));
	  panel1.add(new JLabel("la2fsd"));
	  panel1.add(new JTextField(17));	  
	  	  	
		PanelCollapsable pc1 = new PanelCollapsable(panel1,null, "panel first", true,CLR_PANEL_START,CLR_PANEL_END);
		
	  JxPanel panel2 = new JxPanel();
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
        
        PanelCollapsable pc2 = new PanelCollapsable(panel2,null, "panel second", true,CLR_PANEL_START_ALTER,CLR_PANEL_END_ALTER);


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
        
        PanelCollapsable pc3 = new PanelCollapsable(panel3,null, "panel third", true,CLR_PANEL_START,CLR_PANEL_END);


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
        
        PanelCollapsable pc4 = new PanelCollapsable(panel4,null, "panel forth", true,CLR_PANEL_START,CLR_PANEL_END);

				//pc1.setComponent1(lblTitle);
				//pc1.setComponent2(panel1);
			//	s.getComponent2().setSize(new Dimension(100, 300));
				
			JFrame f=new JFrame("");
			JPanelDecorated panelMain = new JPanelDecorated();
			panelMain.setLayout(new BorderLayout());
			JScrollPane scrlPane = new JScrollPane();
			scrlPane.setViewportView(panelMain);
			
			JPanelDecorated panelContainer = new JPanelDecorated();
            panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.PAGE_AXIS));
             
             
             panelContainer.add(pc1);
             panelContainer.add(pc2);
             panelContainer.add(pc3);
             panelContainer.add(pc4);
             
             panelMain.add(panelContainer, BorderLayout.PAGE_START);
             
				f.setContentPane(scrlPane);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(420,390);
		        f.setVisible(true);
	}//*/

}
