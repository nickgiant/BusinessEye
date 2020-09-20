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
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


/**
 *
 * @author Thomas Otero H3R3T1C
 */
public class DialogUpdateInformation extends JFrame implements Constants {

    
        public static final String DIR_FOR_UPDATE = "updates";
        public static final String UPDATE_JAR = "Update.jar";
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
        
        initComponents(newVersion, newSize, url);
        locateOnCenterOfTheScreen();
        
    }

    private void initComponents(String newVersion,long newSize,String url) 
    {
        this.setIconImage(IMG_BOOKMARK);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle(VariablesGlobal.appName);
       
        infoPane = new JEditorPane();
        lblNewVersion = new JLabel();
        lblNewSize = new JLabel();
        lblNewVersion.setText(VariablesGlobal.appName+" έκδοση:"+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+"        τελευταία έκδοση:"+newVersion);
        lblNewSize.setText("      μέγεθος:"+newSize/1024+" KBs");
        //infoPane.setText(info);
        
        
        
       //  try
         //{
           //infoPane.setPage(info);
            infoPane.setEditable(false);
            
                    UtilsRssFeed parser = new UtilsRssFeed(url);//"http://businesseye.gr/category/new-versions/feed");//http://businesseye.gr/category/versions.rss");//'https://www.vogella.com/article.rss");
        UrlFeed feed = parser.readFeed();
       // System.out.println(feed);
       UrlFeedMessage message = new UrlFeedMessage();
       String versionTextTitle ="<html><table>";
       infoPane.setContentType("text/html");
       
        for (int m=0; m <feed.getMessages().size(); m++ ) {
           //System.out.println(feed.getMessages().get(m).getLink());
          //System.out.println("      "+feed.getMessages().get(m).getDescription());
              versionTextTitle=versionTextTitle +"<tr><td><img src='http://www.businesseye.gr/bullet2.jpg' alt='-'></td><td><b>"+ feed.getMessages().get(m).getTitle()+"</b></td></tr>";
              versionTextTitle=versionTextTitle +"<tr><td</td><td>"+ feed.getMessages().get(m).getDescription().substring(0, 135)+"...</td></tr>";
        }
            infoPane.setText(versionTextTitle+"</table></html>");
            
            

          // infoPane.setPage(url);
         /*}
         catch (IOException e)
         {
           //paneEditor.setText("<html>Could not load webpage</html>");
             infoPane.setText("<html>"+e.getLocalizedMessage()+"</html>");
         }  */       
        
        
        
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout());
        
        
        
        pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());
        
        
        
        
        
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
        this.setSize(600, 350);
    }

    private void backup()
    {
        String dirToBackup = VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_DATABACKUP+VariablesGlobal.globalSystemDirectorySymbol;
        System.out.println("DialogUpdateInformation.update (backup) A");
        DialogBackUp dlgBackUp = new DialogBackUp();
         //dlgBackUp.setVisible(false);
        dlgBackUp.backup(dirToBackup);        
    }    
    
    // exists in DialogUpdateInformation.update  and DialogMain.updateFromWeb
    private void update()
    {
      UtilsFileSystem uFileSystem = new UtilsFileSystem();
      //if(uFileSystem.writeFile(VariablesGlobal.appSubVersion+"",FILE_DBVERSION_INFO))
      //{
        
        
        boolean isRunOk=false;
        String[] run = {"java","-jar",UPDATE_JAR}; //VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+UPDATE_JAR};//,VariablesGlobal.appProduct,VariablesGlobal.appShowLogFrame,VariablesGlobal.appRunParam2};
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
    
    
    
/*
 * Represents one RSS message
 */
  class FeedMessage {

    String title;
    String description;
    String link;
    String author;
    String guid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", link=" + link + ", author=" + author + ", guid=" + guid
                + "]";
    }

}



/*
 * Stores an RSS feed
 */
 class Feed {

    final String title;
    final String link;
    final String description;
    final String language;
    final String copyright;
    final String pubDate;

    final List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public Feed(String title, String link, String description, String language,
            String copyright, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
    }

    public List<FeedMessage> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getPubDate() {
        return pubDate;
    }

    @Override
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }


 }
    
    
}
