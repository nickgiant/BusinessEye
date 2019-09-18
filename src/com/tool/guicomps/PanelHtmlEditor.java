/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.guicomps;


/*import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import javafx.scene.web.*;
/*import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;*/
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author user
 */
public class PanelHtmlEditor extends JxPanel
{
      private  JPanel panel;
      //private final JFXPanel jfxPanel = new JFXPanel();
     // private  HTMLEditor htmlEditor;
    
    public  PanelHtmlEditor() 
    {    
            super();
            initComponents();
    }
 
    private void initComponents()
{
       /*     SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createfxScene();
            }
        });*/
        
        panel = new JPanel(new BorderLayout());        

   //     panel.add(jfxPanel, BorderLayout.CENTER);   

        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.CENTER);
        
}

    /*private void createfxScene() 
    {
        htmlEditor = new HTMLEditor();
       Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //final HTMLEditor htmlEditor = new HTMLEditor();
                Scene scene = new Scene(htmlEditor);
                jfxPanel.setScene(scene);
            }
        });
   }*/
    
    /*public void setHtmlText(String text)
    {
            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                htmlEditor.setHtmlText(text);
            }
        });        

    }*/
    
    /*public String getHtmlText()
    {
       String txt ="";
       /*     SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {    */    
            
      //              txt = htmlEditor.getHtmlText();
          /*  }
        });*/       
     /*   return txt;
    }*/
}
