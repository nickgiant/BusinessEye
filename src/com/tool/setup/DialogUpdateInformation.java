// https://www.dreamincode.net/forums/topic/190944-creating-an-updater-in-java/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tool.setup;

import com.tool.guicomps.*;
import com.tool.utils.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;


/**
 *
 * @author Thomas Otero H3R3T1C
 */
public class DialogUpdateInformation extends JFrame implements Constants {

    
        public static final String DIR_FOR_UPDATE = "updates";
        public static final String UPDATE_JAR = "update.jar";
    public static final String FILE_FOR_UPDATE = "update.zip";
    private JEditorPane infoPane;
    private JScrollPane scp;
    private JButton ok;
    private JButton cancel;
    private JPanel panelMain;
    private JLabel lblNewVersion;
    private JLabel lblNewSize;
    private JPanel pan2;
    
    
    

    public DialogUpdateInformation(String newVersion,long newSize,String url)
    {
        initComponents();
        locateOnCenterOfTheScreen();
        lblNewVersion.setText(VariablesGlobal.appName+" "+VariablesGlobal.appProductCaption+" έκδοση:"+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+"        νέα έκδοση:"+newVersion);
        lblNewSize.setText("      μέγεθος:"+newSize/1024+" KBs");
        //infoPane.setText(info);
        
        
        
         try
         {
           //infoPane.setPage(info);
            infoPane.setEditable(false);
            infoPane.setContentType("text/html");
           infoPane.setPage(url);
         }
         catch (IOException e)
         {
           //paneEditor.setText("<html>Could not load webpage</html>");
             infoPane.setText("<html>"+e.getLocalizedMessage()+"</html>");
         }         
        
    }

    private void initComponents() {

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle(VariablesGlobal.appName+" "+VariablesGlobal.appProductCaption+" : υπάρχει νέα έκδοση");
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout());
        lblNewVersion = new JLabel();
        lblNewSize = new JLabel();
        
        pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());
        infoPane = new JEditorPane();
        
        
        
        
        infoPane.setEditable(false);
        //infoPane.setContentType("text/html; charset=utf-8");
        infoPane.setContentType("text/html");
        scp = new JScrollPane();
        scp.setViewportView(infoPane);

        
        JButton btnBackup = new JButton("γρήγορο backup");
        btnBackup.setIcon(ICO_BACKUP);
        btnBackup.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                backup();
            }
        });        
        
        
        
        
        ok = new JButton("αναβάθμιση");
        ok.setIcon(ICO_OK16);
        ok.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                update();
            }
        });

        cancel = new JButton("άκυρο");
        cancel.setIcon(ICO_CANCEL16);
        cancel.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                DialogUpdateInformation.this.dispose();
            }
        });
        panelTop.add(lblNewVersion);
        panelTop.add(lblNewSize);
        pan2.add(btnBackup);
        pan2.add(ok);
        pan2.add(cancel);
        panelMain.add(panelTop, BorderLayout.NORTH);
        panelMain.add(pan2, BorderLayout.SOUTH);
        panelMain.add(scp, BorderLayout.CENTER);
        this.add(panelMain);
        pack();
        setVisible(true);
        this.setSize(600, 330);
    }

    private void backup()
    {
        String dirToBackup = VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_DATABACKUP+VariablesGlobal.globalSystemDirectorySymbol;
        System.out.println("DialogUpdateInformation.update (backup) A");
        DialogBackUp dlgBackUp = new DialogBackUp();
         //dlgBackUp.setVisible(false);
        dlgBackUp.backup(dirToBackup);        
    }    
    
    
    private void update()
    {
      UtilsFileSystem uFileSystem = new UtilsFileSystem();
      //if(uFileSystem.writeFile(VariablesGlobal.appSubVersion+"",FILE_DBVERSION_INFO))
      //{
        
        
        boolean isRunOk=false;
        String[] run = {"java","-jar",VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+UPDATE_JAR,VariablesGlobal.appProduct,VariablesGlobal.appShowLogFrame,VariablesGlobal.appRunParam2};
        try 
        {
            Runtime.getRuntime().exec(run);
            isRunOk=true;
        } catch (Exception ex)
        {
            ex.printStackTrace();
            isRunOk=false;
        }
        if(isRunOk)
        {
            System.exit(0);
        }
     // }
      //else
     // {
          
     // }
      
    }
    private void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    } 
}
