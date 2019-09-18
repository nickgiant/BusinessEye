package com.tool.guicomps;

import com.tool.jdbc.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.FlowLayout;

//import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class PanelMulti extends JPanel implements Constants 
{
	//private BorderLayout borderLayout = new BorderLayout();
	//private FlowLayout flowLayout = new FlowLayout();
	private PanelGradient panelCenter;
	private JPanel panelTop;
    private JLabel lblTitle = new JLabel();
    private JLabel lblLogo = new JLabel();

	public PanelMulti()
	{
            try
           {     initialize();   }
           catch (Exception e)
           {   e.printStackTrace();    }
		
	}
	
	private void initialize() throws Exception
    {
    	setLayout(new BorderLayout());

    	panelTop = new JPanel();
    	panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.PAGE_AXIS));
    	panelTop.setOpaque(false);
    	panelCenter = new PanelGradient(this.getBackground().brighter(), this.getBackground().darker(),500);//530);
        panelCenter.setLayout(new BorderLayout());
      //lblTitle.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
      //lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));
      lblTitle.setOpaque(false);
      //lblTitle.setBackground(SystemColor.activeCaption);//panel.getBackground().brighter());        
      //lblTitle.setForeground(this.getBackground().brighter().brighter());
    	//lblTitle.setForeground(java.awt.SystemColor.activeCaption);
    	//lblTitle.setText("(PanelMulti) Επιλέξτε από το μενού αριστερά.");
        
        //lblLogo.setIcon(APP_LOGO);
        lblLogo.setOpaque(false);
        
        
        panelTop.add(lblTitle);
        panelTop.add(lblLogo);
        panelCenter.add(panelTop, BorderLayout.PAGE_START);
        //panelCenter.add(lblLogo,BorderLayout.CENTER);
        
        
        add(panelCenter, BorderLayout.CENTER);
        
    }

	public void setText(String text)
    {
        lblTitle.setText(text+" : Επιλέξτε υποκατηγορία από το μενού.");
    }	


 }