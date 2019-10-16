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
import javax.swing.event.AncestorListener;
import net.atlanticbb.tantlinger.shef.HTMLEditorPane;
import novaworx.textpane.SyntaxTextPane;
/**
 *
 * @author user
 */
public class PanelHtmlEditor extends JxPanel
{
      private  JPanel panel;
      //private JTextArea textArea;
      private HTMLEditorPane editor ;
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
    
       editor = new HTMLEditorPane();
       



       // textArea = new JTextArea();
        JScrollPane scrollPaneTextArea = new JScrollPane();//textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    scrollPaneTextArea.setOpaque(false);        
        scrollPaneTextArea.setViewportView(editor);
        panel = new JPanel(new BorderLayout());        
        panel.add(scrollPaneTextArea,BorderLayout.CENTER);
        
        //panel.add(editor,BorderLayout.CENTER);

        //editor.setEditTabNotVisible();
       editor.setFocusable(true);
       setFocusable(true);
 


        
   //     panel.add(jfxPanel, BorderLayout.CENTER);   
        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.CENTER);
        
}

    public void setTextAreaString(String strHtml)
    {
        
        editor.setText(strHtml);
    }
    
    
    public String getTextAreaString()
    {
        return editor.getText();
    }
    
   /* @Override
    public void focusGained(FocusEvent fe) {
    System.out.println("-------------------Focus gained in JPanel");
    }
   @Override
   public void focusLost(FocusEvent fe)
   {
       
             if(fe.getComponent() instanceof HTMLEditorPane)
            {
                HTMLEditorPane ed = (HTMLEditorPane)fe.getComponent();
                editor.setText(ed.getText());
                System.out.println("---------*----------Focus lost in JPanel");
            }
       
    System.out.println("-----------------Focus lost in JPanel");
   }
@Override
public void mousePressed(MouseEvent me) {
    requestFocus();
    System.out.println("------------Mouse Pressed in JPanel");
}
@Override
public void mouseReleased(MouseEvent me) {}
@Override
public void mouseClicked(MouseEvent me) {}
@Override
public void mouseEntered(MouseEvent me) {}
@Override
public void mouseExited(MouseEvent me) {}   */
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
    
    
    public static void main(String args[])
    {
        
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        PanelHtmlEditor p = new PanelHtmlEditor();
        
        String html ="             ";
        
        
        p.setTextAreaString(html);
        f.add(p,BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }    
    
}
