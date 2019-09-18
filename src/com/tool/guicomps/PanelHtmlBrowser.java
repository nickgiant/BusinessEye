//    https://docs.oracle.com/javase/8/javafx/interoperability-tutorial/simpleswingbrowserjava.htm#CBBHEAII


package com.tool.guicomps;
 
//import javafx.application.Platform;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.embed.swing.JFXPanel;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebEvent;
//import javafx.scene.web.WebView;
//import javafx.concurrent.Worker;
//import javafx.concurrent.Worker.State; 
//import static javafx.concurrent.Worker.State.FAILED;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;
 

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

  // import org.jsoup.nodes.*;
 // import org.jsoup.select.*;
 // import org.jsoup.Jsoup;

   import java.io.FileReader;
  import java.io.File;
   import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;



import netscape.javascript.JSObject;
 
public class PanelHtmlBrowser extends JxPanel
 {
 
    //private final JxPanel panelMain = new JxPanel();
    //private WebEngine engine;
         JEditorPane paneEditor = new JEditorPane();
         JScrollPane paneEditorScroll = new JScrollPane(paneEditor);
 
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JLabel lblStatus = new JLabel();
 
    private final JButton btnGo = new JButton("set");
    private final JTextField txtURL = new JTextField();
    private final JProgressBar progressBar = new JProgressBar();
    //private DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    //private String strHtmlFile = "C:\\mydocuments\\!projects\\BusinessEye\\reports\\periodikifpaf2.htm";
 
     private String strValue = "";
     
 
    private String strHtmlFile="";
    private String strFieldReturn ="";   
    
    private ArrayList listFields = new ArrayList();
      
    private ArrayList listFieldValues  = new ArrayList();
    private boolean isLoaded = true;
    
    
   private ArrayList  listHtmlFieldsAndValues = new ArrayList();
    
    
    public  PanelHtmlBrowser() {
        super();
        initComponents();
    }
 
    private void initComponents() {
       // createScene();
        paneEditor.setEditable(false);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadURL(txtURL.getText());
            }
        };
 

        ActionListener alset = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTest();
            }
        };         
        btnGo.addActionListener(alset);
       
      listFields = new ArrayList();
      listFieldValues = new ArrayList();
        
        
        txtURL.addActionListener(al);
 
        progressBar.setPreferredSize(new Dimension(100, 16));
        progressBar.setStringPainted(true);
 
        JPanel topBar = new JPanel(new BorderLayout(5, 0));
        topBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        topBar.add(txtURL, BorderLayout.CENTER);
        topBar.add(btnGo, BorderLayout.EAST);
         btnGo.setVisible(false);
        
        JPanel statusBar = new JPanel(new BorderLayout(5, 0));
        statusBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        statusBar.add(lblStatus, BorderLayout.CENTER);
        statusBar.add(progressBar, BorderLayout.EAST);
 
        
        
        panel.add(topBar, BorderLayout.NORTH);
        panel.add(paneEditorScroll, BorderLayout.CENTER);
        panel.add(statusBar, BorderLayout.SOUTH);
        
        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.CENTER);

    }
 
    
    /*
    *         https://stackoverflow.com/questions/13777377/displaying-html-in-swing
    */
    public void loadURL(String url)
    {
        
        
        
         try {
           paneEditor.setPage(url);
         }
         catch (IOException e)
         {
           paneEditor.setContentType("text/html");
           //paneEditor.setText("<html>Could not load webpage</html>");
             paneEditor.setText("<html>"+e.getLocalizedMessage()+"</html>");
         }         
        
        
    }
    
    
    
    
    /*private void createScene() 
    {
 
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
 
                WebView view = new WebView();
                engine = view.getEngine();
                 
                engine.titleProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                //PanelHtmlBrowser.this.setTitle(newValue);
                            }
                        });
                    }
                });
 
                engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
                    @Override
                    public void handle(final WebEvent<String> event) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                lblStatus.setText(event.getData());
                            }
                        });
                    }
                });
 
                engine.locationProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> ov, String oldValue, final String newValue) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                txtURL.setText(newValue);
                            }
                        });
                    }
                });
 
                engine.getLoadWorker().workDoneProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, final Number newValue) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setValue(newValue.intValue());
                            }
                        });
                    }
                });
 
                engine.getLoadWorker()
                        .exceptionProperty()
                        .addListener(new ChangeListener<Throwable>() {
 
                            @Override
                            public void changed(ObservableValue<? extends Throwable> o, Throwable old, final Throwable value) {
                                if (engine.getLoadWorker().getState() == FAILED)
                                {
                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() 
                                        {
                                            JOptionPane.showMessageDialog(
                                            panel,
                                            (value != null)
                                            ? engine.getLocation() + "\n" + value.getMessage()
                                            : engine.getLocation() + "\nUnexpected error.",
                                            "Loading error...",
                                            JOptionPane.ERROR_MESSAGE);
                                        }
                                    });
                                }
                               
                            }
                        });
                
                
                
                
                
                
                
                
                
          //   http://www.java2s.com/Tutorials/Java/JavaFX/1500__JavaFX_WebEngine.htm
            engine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) ->{
              //System.out.println(newValue);
              if (newValue == State.SUCCEEDED)
              {
                  isLoaded = true;
                System.out.println(".finished loading.... set follows");
                displayFromListTextToField();
                //String html = (String) engine.executeScript("document.documentElement.outerHTML");
                //System.out.println(html);

              }
              else
              {
                  isLoaded = false;
              }
            });                 
 
 //  http://o7planning.org/en/11151/javafx-webview-and-webengine-tutorial    for string               
 /*      engine.setJavaScriptEnabled(true);
 
        // A Worker load the page
        Worker<Void> worker = engine.getLoadWorker();
 
        // Listening to the status of worker
        worker.stateProperty().addListener(new ChangeListener<State>()
        {
 
            @Override
            public void changed(ObservableValue<? extends State> observable, //
                    State oldValue, State newValue) {
 
                // When load successed.
                if (newValue == Worker.State.SUCCEEDED) {
                    // Get window object of page.
                    JSObject jsobj = (JSObject) engine.executeScript("window");
 
                    // Set member for 'window' object.
                    // In Javascript access: window.myJavaMember....
                    jsobj.setMember("myJavaMember", new Bridge());
                }
            }
        });*/
                
                
                
                
              //  jfxPanel.setScene(new Scene(view, 800, 1000));
                        //stage.setWidth(450);
              //stage.setHeight(300);
                //new Scene(webview, 800, 800)
/*            }
        });
    }

/*public class Bridge {
 
    public void showTime() {
        System.out.println("Show Time");
 
        lblStatus.setText("Now is: " + df.format(new Date()));
    }
}    */
    
    /*public void getAllTextFromFields(String fileHtml)
    {    
     Document doc= null;
     File input =null;

     //engine.getDocument();     
          //   strHtml = engine.getDocument().getDocumentElement().getTextContent();

        try
        {
        //doc = Jsoup.connect(url).get();
        //Document doc = engine.getDocument();
        //doc = Jsoup.parse(strHtml);
        input = new File(fileHtml);
        doc = Jsoup.parse(input, "UTF-8", "");
        }
       catch(IOException ioe)
        {
                  System.out.println("error PanelHtmlBrowser.getAllTextFromFields "+ioe.getMessage()+" strHtmlFile:"+fileHtml);
                  ioe.printStackTrace();
        }
      //log(doc.title());
      
      //Elements links = doc.select("a[href]");

      
      Elements elementsInput = doc.getElementsByTag("input");
      
      
      System.out.println(" title:"+doc.title());
      
        System.out.println("elementsInput: "+ elementsInput.size());
        for (Element element : elementsInput)
        {
            //Elements links = doc.select("a[href]");
              //, trim(link.text()
             System.out.println(element.val​()+"   -    "+ element.className​()+"    -     "+element.html()+"   -   "+element.id()+"   -    "+element.attr("abs:href")+"   -    "+element.text());
        }
*/
    /*  Elements newsHeadlines = doc.select("#mp-itn b a");
     for (Element headline : newsHeadlines)
     {
         System.out.println(headline.attr("title")+headline.absUrl("href"));
     //  log("%s\n\t%s", 
   // headline.attr("title"), headline.absUrl("href"));
    }*/        
    //}    
    
    
   /*
    *  call text from field and get later 
    */
    
    /*public void calculateTextFromField(final String strField)
    {    
     //final String strValue = "";
    
        Platform.runLater(new Runnable() {
        @Override
        public void run() 
        {
          
                //engine.getDocument().getElementById("mytext").value = "My value";
                if (engine != null) 
                {               
   //             engine.executeScript("document.getElementsByName('"+strField+"')[0].focus();");
              String strVal  =  (String)"document.getElementsByName('" + strField + "')[0].value;";
               // System.out.println(strVal);
                 strFieldReturn = (String)engine.executeScript(strVal);
 
                listFields.add(strField);
                 listFieldValues.add(strFieldReturn);                  
               /* }            
        }
        }); 
      
    }*/
    

    private void calculateTest()
    { 
 /*       setTextToField("declaration.document.content.f101", "various customers");
        setTextToField("declaration.document.content.f102", "various");
        setTextToField("declaration.document.content.f103", "var");
        setTextToField("declaration.document.content.f301", "301");
        setTextToField("declaration.document.content.f303", "303");
 */        
    /*   Document doc= null;
       doc = Jsoup.parse(strHtmlFile);

       Elements elementsInput = doc.getElementsByTag("input");

       System.out.println(" title:"+doc.title());
      
       System.out.println("elementsInput: "+ elementsInput.size());      */  
       
   System.out.println(progressBar.getString());

       //getAllTextFromFields(strHtmlFile);

/*       calculateTextFromField("declaration.document.content.f101");
       calculateTextFromField("declaration.document.content.f102"); 
       
       calculateTextFromField("declaration.document.content.f301");
       calculateTextFromField("declaration.document.content.f303");
*/
    try
    {
    TimeUnit.SECONDS.sleep(1);
    }
    catch(InterruptedException ie)
    {
        ie.printStackTrace();
    }   
    
    
    System.out.println(progressBar.getString());
      
       for(int f = 0;f<listFields.size();f++)
       {
       
               System.out.println( " --"+f+"-- "+listFields.get(f)+"-"+listFieldValues.get(f));
                 
       }

    
    /* try
     {*/
       
   
   /*     }
       catch(IOException ioe)
        {
                  System.out.println("error PanelHtmlBrowser.calculate "+ioe.getMessage());
                  ioe.printStackTrace();
        }  */       
        
    }
    
    public ArrayList getFieldsArrayList()
    {
        return listFields;
    }
    
    
    public ArrayList getFieldValuesArrayList()
    {
        return listFieldValues;
    }    
    
    public boolean isPageLoaded()
    {
      
      return isLoaded;
    }
    
    public void setListOfFieldsAndValues(ArrayList listHtmlFieldsAndValuesIn)
    {
        listHtmlFieldsAndValues =listHtmlFieldsAndValuesIn;
    }

    
    /*
    * called by  public   PanelODORecData.calculationFromToolBarButton  htmlfile 
    */
  /*  public void displayFromListTextToField()
    {
       
        Platform.runLater(new Runnable() {
        @Override
        public void run()
        {
          
                //engine.getDocument().getElementById("mytext").value = "My value";
                if (engine != null)
                {                
            
                
          
                    
                    
                
        /*  String strJs = 
          
          "var char =5;"+
          "var pressEvent = document.createEvent('KeyboardEvent');"+
          "pressEvent.initKeyEvent('keypress', true, true, null,false, false, false, false, 0, char.charCodeAt(0));"+
          "var input = document.getElementsByName('" + strField + "');"+ // Get the element where you want to press.
          "input.dispatchEvent(pressEvent);"; // Press the key.                  
               */   
                  
                  /*  "document.getElementsByName('"+strField+"')[0].focus();"+
                    "var e = new Event('keydown');"+
                    "e.key=5;"+    // just enter the char you want to send 
                    "e.keyCode=e.key.charCodeAt(0);"+
                    "e.which=e.keyCode;"+
                    "e.altKey=false;"+
                    "e.ctrlKey=true;"+
                    "e.shiftKey=false;"+
                    "e.metaKey=false;"+
                    "e.bubbles=true;"+
                    "document.dispatchEvent(e);";
                  */
                  
                  
                  
                  /*
                  
                    " var pressEvent = document.createEvent('KeyboardEvent'); "
                  + " var str  = '"+100+"';"
                  + " pressEvent.initKeyEvent('keypress', true, true, null, false, false, false, false, 0, str.valueOf()); "
                  + " var input = document.getElementsByName('" + strField + "')[0];"
                  + " input.dispatchEvent(pressEvent);";// Press the key.  
                  */
                  
 /*   ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine se = sem.getEngineByName("javascript");
    try {
      Double hour = (Double) se.eval("var date = new Date();" + "date.getHours();");
      System.out.println(hour);
     // se.eval("document.URL;");
    */
    /*  se.eval("document.getElementsByName('"+strField+"')[0].focus();");
      se.eval(              "var e = new Event('keydown');");
      se.eval(              "e.key=5;");    // just enter the char you want to send 
      se.eval(              "e.keyCode=e.key.charCodeAt(0);");
      se.eval(              "e.which=e.keyCode;");
      se.eval(              "e.altKey=false;");
      se.eval(              "e.ctrlKey=true;");
      se.eval(              "e.shiftKey=false;");
      se.eval(              "e.metaKey=false;");
      se.eval(              "e.bubbles=true;");
      se.eval(              "document.dispatchEvent(e);");    */
    /*      se.eval("var document = input.getDocument() ;");
          se.eval("var char =5;");
          se.eval("var pressEvent = document.createEvent('KeyboardEvent');");
          se.eval("pressEvent.initKeyEvent('keypress'', true, true, window, false, false, false, false, 0, char.charCodeAt(0));");
 
          se.eval("var input = document.getElementsByName('"+strField+"');"); // Get the element where you want to press.
 
          se.eval("input.dispatchEvent(pressEvent);"); // Press the key.    
    */

 /*   }
    catch (ScriptException e)
    {
      System.err.println(e);
    }*/
 
                    
    
    //engine.executeScript("var str = document.URL;");
   // engine.executeScript("str.valueOf();");
    //engine.executeScript("document.getElementsByName('"+strField+"')[0].focus();");
   // engine.executeScript("document.getElementsByName('" + strField + "')[0].value='" + strValue + "';");
/*   System.out.println("calcFromListTextToField    size"+listHtmlFieldsAndValues.size());
   for(int s = 0;s<listHtmlFieldsAndValues.size();s++)
   {
   engine.executeScript(listHtmlFieldsAndValues.get(s)+"");
   }
    
    
    
           //        engine.executeScript( " var pressEvent = document.createEvent('KeyboardEvent'); "
          //        + " var str  = '0';"
          //        + " pressEvent.initKeyEvent('keypress', true, true, null, false, false, false, false, 0, str.charCodeAt(0));"
          //        + " var input = document.getElementsByName('" + strField + "')[0];"
          //        + " input.dispatchEvent(pressEvent);");// Press the key.     
      

                    
          
 //         engine.executeScript(strJs);
         
          


                }            
            
            
        }
        });
   // listFieldsAndValuesJavaScript.clear();
    }*/
    public void showUrlText(boolean isShow)
    {
        txtURL.setVisible(isShow);
    }
   
    
    /*public void loadURL(final String url) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String tmp = toURL(url);
 
                if (tmp == null) {
                    tmp = toURL("http://" + url);
                }
 
                engine.load(tmp);
                //engine.loadContent(ROOT);
            }
        });
    }*/
 
    
   /* public void setText(final String text) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                engine.loadContent(text);
                
            }
        });
    }*/    
    
    
    
    /*private static String toURL(String str) {
        try {
            return new URL(str).toExternalForm();
        } catch (MalformedURLException exception) {
            return null;
        }
    }*/
 

    // from     http://o7planning.org/en/11151/javafx-webview-and-webengine-tutorial
    // A Bridge class and must a public class
    /*public class Bridge {
 
        public void showTime() {
            System.out.println("Show Time");
 
            lblStatus.setText("Now is: " + df.format(new Date()));
        }
    }*/
  
    
    /*private String loadHtmlFileToString(String strFileHtml)
    {
        
StringBuilder contentBuilder = new StringBuilder();
try
{
    BufferedReader in = new BufferedReader(new FileReader(strFileHtml));
    String str;
    while ((str = in.readLine()) != null) {
        contentBuilder.append(str);
    }
    in.close();
}
catch (IOException e)
{
    System.out.println("    ..loadHtmlFileToString   strFileHtml:"+strFileHtml);
    e.printStackTrace();
}

String str = contentBuilder.toString();        
        
       return str; 
    }*/
    
    
   /*private void loadHtmlFileToSoup(String urlFile)
   {
     Document doc= null;
     File input =null;        
    
        try
        {
        //doc = Jsoup.connect(url).get(); // for url link
        input = new File(urlFile);
        doc = Jsoup.parse(input, "UTF-8", "");
        }
       catch(IOException ioe)
        {
                  System.out.println("error PanelHtmlBrowser.getAllTextFromFields "+ioe.getMessage()+" url:"+urlFile);
                  ioe.printStackTrace();
        }
      //log(doc.title());
      
      //Elements links = doc.select("a[href]");

   
      
   }*/
   
   
  
  
  /*private void loadHtmlStringToEngine(String htlmStr)
  {
 
      final String strHtmlstr = htlmStr;
       Platform.runLater(new Runnable() {
            @Override
            public void run() {
               engine.loadContent(strHtmlstr); 
              
            }
        });           
  }*/
   
   
  /* private void showElementsInputFile()
   {

       
       Document doc= null;
       doc = Jsoup.parse(strHtmlFile);
       
      Elements elementsInput = doc.getElementsByTag("input");
      
      
      System.out.println(" title:"+doc.title());
      
        System.out.println("elementsInput: "+ elementsInput.size());
       for (Element element : elementsInput)
        {
            System.out.println(element.val​()+"   -    "+ element.className​()+"    -     "+element.html()+"   -   "+element.id());
        }        
        //engine.getDocument().getDocumentElement().getTextContent();    
   }*/
  
   
   
  
    
    
    public static void main(String[] args) 
    {
        
       
        
        SwingUtilities.invokeLater(new Runnable() {
 
            @Override
            public void run()
            {
            	        //getContentPane().add(panel);
 
        //setPreferredSize(new Dimension(1024, 600));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // pack();
            
                PanelHtmlBrowser browser = new PanelHtmlBrowser();
                browser.setVisible(true);
                browser.btnGo.setVisible(true);
                browser.loadURL("http://www.businesseye.gr");
               // browser.loadURL("file:///C:/mydocuments/!projects/BusinessEye/reports/periodikifpaf2.htm");
                
              // browser.loadHtmlFile("C:\\mydocuments\\!projects\\BusinessEye\\reports\\periodikifpaf2.htm");
              
               //browser.loadURL("file:///C:/mydocuments/!projects/BusinessEye/reports/periodikifpaf2.htm");
                
            JFrame frm = new JFrame();
            //frm.setLayout(BorderLayout);
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.getContentPane().add(browser);//(browser, BorderLayout.CENTER);
            frm.setPreferredSize(new Dimension(1024, 768));
            frm.pack();
            frm.setVisible(true);
                
             

             
          // browser.strHtmlFile = "C:\\mydocuments\\!projects\\BusinessEye\\reports\\periodikifpaf2.htm";
          //  browser.loadHtmlFileToSoup(browser.strHtmlFile);
            
            }
        });
        

    }
}