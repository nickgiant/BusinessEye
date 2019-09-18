 package com.tool.guicomps;
 
 
 import com.tool.gui.*;
 
 import javax.swing.JButton;
 import javax.swing.JToolBar;
 import javax.swing.AbstractButton;
 import javax.swing.JTable;
 import javax.swing.JLabel;
 import javax.swing.ImageIcon;
 import javax.swing.BorderFactory;
 import javax.swing.JOptionPane;
 import java.awt.Font;
 import java.awt.Color;
 import java.awt.event.ActionListener;
 import java.awt.event.ActionEvent;
 
 public class ToolBarData extends JToolBar implements Constants
 {
        private JLabel lblTitle = new JLabel();
        private JButton btnViewMultiR = new JButton();
        private JButton btnViewOneR = new JButton();
        private JButton btnInsert = new JButton();
        private JButton btnDelete = new JButton();
        private JButton btnPrevious = new JButton();
        private JButton btnNext = new JButton();
        private JButton btnFind = new JButton();
        private JButton btnPrintPreview = new JButton();
        private DialogMulti dlg;
        //private Insets insets = new Insets(0, 0, 0, 0);

        public ToolBarData()
        {
            try
           {     initialize();   }
           catch (Exception e)
           {   e.printStackTrace();    }
        }

        private void initialize() throws Exception
        {
        setFloatable(false);
        setRollover(true);

        //lblTitle.setBackground(java.awt.SystemColor.activeCaption);
      lblTitle.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
      lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));
      //lblTitle.setOpaque(true);
      lblTitle.setBackground(Color.white);//panel.getBackground().brighter());
        
        //lblTitle.setFont(new java.awt.Font("Century", 1, 16));
        lblTitle.setForeground(java.awt.SystemColor.activeCaption        );
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("");
        lblTitle.setFocusable(false);
        lblTitle.setMaximumSize(new java.awt.Dimension(200, 15));
        lblTitle.setPreferredSize(new java.awt.Dimension(150, 15));

        /*btnViewMultiR.setText("MultiR");

        //   one
        btnViewOneR.setText("OneR");
        btnViewOneR.addActionListener(new ActionListener()
        {
	       
	        public void actionPerformed(ActionEvent e) 
	        {	//   displayDialog();
	        } 
	    });*/
        
        btnInsert.setText("εισαγωγή");
        btnInsert.setToolTipText("εισαγωγή εγγραφής");
        btnInsert.setIcon(ICO_INSERT16);

        btnDelete.setText("διαγραφή");
        btnDelete.setToolTipText("διαγραφή εγγραφής");
        btnDelete.setIcon(ICO_DELETE16);
        //btnDelete.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnDelete.setHorizontalTextPosition(AbstractButton.CENTER);
        
        //btnPrevious.setText("προηγούμενη");
        btnPrevious.setToolTipText("προηγούμενη εγγραφή");
        btnPrevious.setIcon(ICO_PREVIOUS16);

        //btnNext.setText("επόμενη");
        btnNext.setToolTipText("επόμενη εγγραφή");
        btnNext.setIcon(ICO_NEXT16);

        btnFind.setText("εύρεση");
        btnFind.setToolTipText("εύρεση εγγραφής");
        btnFind.setIcon(ICO_FIND16);
        btnFind.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayDialog();  } 
	    });
	    
        btnPrintPreview.setText("προεπισκόπηση");
        btnPrintPreview.setToolTipText("προεπισκόπηση εκτύπωσης");
        btnPrintPreview.setIcon(ICO_PRINT_PREVIEW16);
        btnPrintPreview.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayMessageDialog("προεπισκόπηση εκτύπωσης","προεπισκόπηση εκτύπωσης");  }
	    });
	    
        //add(lblTitle);          
        //addSeparator();
        //add(btnViewMultiR);
        //add(btnViewOneR);
        addSeparator();
        add(btnInsert);
        add(btnDelete);
        addSeparator();
        add( btnPrevious );
        add( btnNext );
        addSeparator();
        add( btnFind );
        add(btnPrintPreview);
            
        }
    
       public void setTitle(String title, ImageIcon ico)
       {
           lblTitle.setText(title);
           lblTitle.setIcon(ico);
           btnInsert.setToolTipText("εισαγωγή "+title);
           btnDelete.setToolTipText("διαγραφή "+title);
           btnFind.setToolTipText("εύρεση σε "+title);
           
       }
       
  private void displayMessageDialog(String title, String message)
  {
  	JOptionPane.showMessageDialog(this, message, title,JOptionPane.INFORMATION_MESSAGE);
  }

  private void displayDialog()
  {
  	String[] names = {"Arlo", "Cosmo", "Elmo", "Hugo",
                          "Jethro", "Laszlo", "Milo", "Nemo",
                          "Otto", "Ringo", "Rocco", "Rollo"};
                          
  //	dlg.initialize();
     String name = "Cosmo";
//    String selectedName = dlg.showDialog(null, name);
  //  System.out.println(selectedName);
  }


 }
