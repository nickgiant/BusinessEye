/*
 */

package com.tool.gui;

import com.tool.guicomps.*;

//import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;


public class DialogKeys extends JDialog implements Constants 
{
	private JButton btnClose;
	private JLabel label;
	private JPanel panelMain;
	private JPanel panelBottom;
	
    public DialogKeys(JFrame parent)
    {
       // super(parent, "", true);
        super(parent,"Πλήκτρα συντόμευσης", true);//LANGUAGE.getString("AboutDialog.title"));
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
    
    this.setLayout(new BorderLayout());
    panelBottom = new JPanel();
    
    label = new JLabel();
    label.setText("<html>"+
    "<table>"+
    "<tr><th>πλήκτρο</th><th>λειτουργία</th></tr>"+
    "<tr><td>escape</td><td>κλείσιμο παραθύρου χωρίς αποθήκευση</td></tr>"+
    "<tr><td>F3</td><td>προβολή επιλογής καρτελών</td></tr>"+
    "<tr><td>ctrl</td><td>ευρεση σε πίνακα</td></tr>"+    
    "<tr><td></td><td>----------------------------</td></tr>"+
    "<tr><td>alt+Λ</td><td>εμφάνιση πλήκτρων συντόμευσης</td></tr>"+
    "<tr><td></td><td>----------------------------</td></tr>"+
    "<tr><td>F9</td><td>νέα εγγραφή</td></tr>"+
    "<tr><td>F10</td><td>επεξεργασία εγγραφής</td></tr>"+
    "<tr><td>F11</td><td>αποθήκευση αλλαγών εγγραφής</td></tr>"+
    "<tr><td>F12</td><td>διαγραφή εγγραφής</td></tr>"+
    "<tr><td>F7</td><td>προεπισκόπηση εκτύπωσης</td></tr>"+
    "<tr><td>PageUp</td><td>εμφανίζει την προηγούμενη φόρμα</td></tr>"+        
    "<tr><td>PageDOwn</td><td>εμφανίζει την επόμενη φόρμα</td></tr>"+                
    "<tr><td></td><td>----------------------------</td></tr>"+
    "<tr><td>F4</td><td>μέσα σε πεδίο εμφανίζει λιστα με επιλογές</td></tr>"+
    "<tr><td>F5</td><td>επιλέγει σε μια λίστα από τιμές</td></tr>"+
    "<tr><td></td><td>----------------------------</td></tr>"+
    "<tr><td>alt+Ξ</td><td>εξαγωγή αρχείου</td></tr>"+
    "<tr><td>alt+Π</td><td>προτιμήσεις πίνακα</td></tr>"+

    "</table>"+
    "</html>");

    panelMain.add(label);
    
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
      //getContentPane().add(panel);
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


}