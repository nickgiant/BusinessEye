/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//source  http://www.experts-exchange.com/Programming/Languages/Java/Q_23235349.html
//    http://www.screaming-penguin.com/node/4005


// look at http://www.roseindia.net/chartgraphs/stacked-bar-chart1.shtml
//  http://www.java2s.com/Code/Java/Chart/JFreeChartBarChartDemo.htm

//  http://www.codejava.net/java-se/graphics/using-jfreechart-to-draw-xy-line-chart-with-xydataset


package com.tool.guicomps;

import com.tool.jdbc.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

//import java.awt.Paint;

/*import org.jfree.chart.*;
import org.jfree.chart.renderer.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jfree.data.general.*;
import org.jfree.data.xy.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;*/


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PiePlot;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
//import org.jfree.chart.plot.PiePlot3D ;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.jfree.chart.renderer.category.CategoryItemRenderer;

/*import org.jfree.util.PublicCloneable;
import org.jfree.ui.Drawable;*/

//import org.jfree.chart.renderer.*;
//import org.jfree.chart.renderer.category.BarRenderer;

import java.io.File;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.List;

public class PanelGraph extends JxPanel implements Constants
{
    //Desktop desktop;
    private ResultSet rsMaster;
    private ResultSetMetaData rsmdMaster;
    private ResultSet rsDetail;
    private ResultSetMetaData rsmdDetail;    
    JFreeChart chart;
    private Database db;
    private Color backgroundGraphColor = Color.white;
    private Paint colors;
    
    public PanelGraph()
    {
        this.setLayout(new BorderLayout());
        db = new Database();
        //initFrame();
        JLabel lbl =new JLabel();
        backgroundGraphColor = Color.WHITE;//lbl.getBackground();        
    }
    
    public void initFrame()
    {
        JFrame frame=new JFrame("graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        frame.getContentPane().add(this,BorderLayout.CENTER);

      /*
    public static final int GRAPH_TYPE_PIE=1;
    public static final int GRAPH_TYPE_BAR3D=2;
    public static final int GRAPH_TYPE_LINE=3       
      */                
        setEntity("test",3,"","");

        //setLookAndFeel();
        frame.pack();
        //frame.setBounds(150,150,500,500);
        frame.setVisible(true);
    }
    
    public void setEntity(String title, int type, String queryMaster, String queryDetail)
    {
      ChartPanel panelChart = null;// new ChartPanel(createPieChart("----",""));
      
      /*
          public static final int GRAPH_TYPE_PIE=1;
    public static final int GRAPH_TYPE_BAR3D=2;
    public static final int GRAPH_TYPE_LINE=3
      */
      
      
      
     //System.out.println("PanelGraph.setEntity    title:"+title+"     type:"+type+"    queryMaster:"+queryMaster+"    queryDetail:"+queryDetail);
      title="";
      if (type==GRAPH_TYPE_PIE)
      {
        panelChart = new ChartPanel(createPieChart(title,queryDetail));
      }
      else if (type == GRAPH_TYPE_BAR3D)
      {
      	 panelChart = new ChartPanel(createBarChart3D(title,queryMaster));
      }
      else if (type == GRAPH_TYPE_LINE)
      {
      	 panelChart = new ChartPanel(createLineChart(title,queryMaster, queryDetail));//createLineChartNotDb("line chart no db"));//
      }      
      else
      {
          System.out.println("PanelGraph.setEntity UNKNOWN type:"+type);
      }
      
      	panelChart.setBorder(null);
      	panelChart.revalidate();
      //panelChart.setMaximumDrawHeight(200);
      //panelChart.setMaximumDrawWidth(200);
      //panelChart.setSize(100,100);
      this.removeAll();
      this.add(panelChart,BorderLayout.CENTER);
      this.setBorder(null);
    }

    private JFreeChart createPieChart(String titleIn, String queryDetail)
    {
    	//System.out.println("PanelGraph.createPieChart    titleIn"+titleIn);
            DefaultPieDataset pieDataset = new DefaultPieDataset();
	
    	  // Create a simple pie chart
    	//String queryDetail = "SELECT p.productId, p.name, COUNT(p.productId) AS count, SUM(i.value) AS sum FROM product p, invoice i WHERE p.productId=i.productId GROUP BY p.productId ORDER BY sum DESC LIMIT 6";
    if(queryDetail==null || queryDetail.equalsIgnoreCase(""))
    {
    	//System.out.println("PanelGraph.createPieChart queryDetail '"+queryDetail+"'");
    }
    else
    {
   	    db.retrieveDBDataFromQuery(queryDetail,"PanelGraph.createPieChart");
   	    rsDetail=db.getRS();
   	    rsmdDetail=db.getRSMetaData();
   	        	
       //rsmdDetail = db.retrieveRSMetaData(queryDetail);
       //rsDetail = db.retrieveResultSet(queryDetail);
     try
     {   
        while(rsDetail.next())
        {
             pieDataset.setValue(rsDetail.getString(2), rsDetail.getDouble(4)); 	
        	
        }
        
     }// try
     catch ( SQLException sqlex)
     {  // if the error is: Column index out of range then perhaps the LookUp is not registered
        System.out.println("error:PanelGraph.createPieChart: "+sqlex.getMessage());
     }        
     }
       /* 
        pieDataset.setValue("One", new Double(43.2));
        pieDataset.setValue("Two", new Double(10.0));
        pieDataset.setValue("Three", new Double(27.5));
        pieDataset.setValue("Four", new Double(17.5));
        pieDataset.setValue("Five", new Double(11.0));
        pieDataset.setValue("Six", new Double(19.4)); */     


                                            
                                            //title, data, label 
        chart = ChartFactory.createPieChart3D(titleIn,pieDataset,false,true,false);
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(true);
        plot.setLabelGap(0.02);


        plot.setBackgroundPaint(backgroundGraphColor);
        
        /*PieRenderer renderer = (PieRenderer) plot.getRenderer();
        renderer.setWallPaint(Color.WHITE);*/


        PiePlot pplot = (PiePlot)chart.getPlot();
        // Specify the colors here
        Color[] colors = {CLR_PANEL_START,CLR_LBL_ROLL, CLR_PANEL_START_ALTER, CLR_BUTTONPANEL_ROLLOVER_START,
             CLR_PANEL_START.darker(),CLR_LBL_ROLL.darker(), CLR_PANEL_START_ALTER.darker(), 
        	CLR_BUTTONPANEL_ROLLOVER_START.darker(), CLR_PANEL_START.brighter(),CLR_LBL_ROLL.brighter(),
        	CLR_PANEL_START_ALTER.brighter(), CLR_BUTTONPANEL_ROLLOVER_START.brighter()};
        PieRenderer renderer = new PieRenderer(colors);
        renderer.setColor(pplot,pieDataset);

        
        
        
        chart.setBorderVisible(false);
        
        closeDB();
        
    	return chart;
    	
    }
    
  /*  public JFreeChart createXYChart()
    {
        //         Create a simple XY chart
        XYSeries series = new XYSeries("XYGraph");
        series.add(1, 1);
        series.add(1, 2);
        series.add(2, 1);
        series.add(3, 9);
        series.add(4, 10);
        //         Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        //         Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart("XY Chart", // Title
                "x-axis", // x-axis Label
                "y-axis", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
            );
       
       return chart;
    }*/

    private JFreeChart createBarChart3D(String titleIn, String query)
    {
        //System.out.println("PanelGraph.createBarChart3D    titleIn:"+titleIn);
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        /*dataset.setValue(6, "ScdDe", "");
        dataset.setValue(8, "Magsd", "");
        dataset.setValue(5, "SciDnce", "");
        dataset.setValue(3, "Ms", "");
        dataset.setValue(4, "ογgο", "");
        dataset.setValue(10, "ηοπ", "");
        dataset.setValue(7, "ογ", "");
        dataset.setValue(10, "π", "");        
        dataset.setValue(12, "hujf", "");      
        dataset.setValue(8, "ko", "");      
        dataset.setValue(3, "kk", "");   
        dataset.setValue(3, "hh", "");
        dataset.setValue(4, "jj", "");
        dataset.setValue(10, "yr", "");  
        dataset.setValue(3, "to", "");
        dataset.setValue(4, "hte", "");
        dataset.setValue(10, "hter", "");
        dataset.setValue(7, "gds", "");
        dataset.setValue(10, "pko", "");        
        dataset.setValue(12, "wqe", "");   */




   if(query==null || query.equalsIgnoreCase(""))
    {
    	//System.out.println("PanelGraph.createPieChart queryDetail '"+queryDetail+"'");
    }
    else
    {
        try
        {
   	    db.retrieveDBDataFromQuery(query,"PanelGraph.createBarChart3D");
   	    rsDetail=db.getRS();
   	    rsmdDetail=db.getRSMetaData();


            while(rsDetail.next())
            {
               // data.getData().add(new XYChart.Data(rsDetail.getString(2),rsDetail.getDouble(4)));
                //data.add(new PieChart.Data(rsDetail.getString(2),rsDetail.getDouble(4)));
                dataset.setValue(rsDetail.getDouble(4),rsDetail.getString(2),"");
            }
            
            
            
            chart = ChartFactory.createBarChart3D(titleIn,rsmdDetail.getColumnLabel(4), rsmdDetail.getColumnLabel(2), dataset, PlotOrientation.VERTICAL,true, true /*tooltip*/, false);
            
          }catch(Exception e){
              System.out.println("PanelGraph.createBarChart3D  Error on DB connection  "+e.getMessage());
             
          }        
        finally
        {
             closeDB();
        }
                
        
        
        
        
       /* data.getData().add(new XYChart.Data("USA", 46));
        data.getData().add(new XYChart.Data("China", 38));
        data.getData().add(new XYChart.Data("UK", 29));
        data.getData().add(new XYChart.Data("Russia", 22));
        data.getData().add(new XYChart.Data("South Korea", 13));
        data.getData().add(new XYChart.Data("Germany", 11));
        data.getData().add(new XYChart.Data("Ελλάδα", 29));
        data.getData().add(new XYChart.Data("Κύπρος", 22));     */   

       // barChart.getData().add(data);
       // barChart.setLegendVisible(false);        
        







        
    	        //create chart                          //title
        
        //               orientation,label
        
        //change category plot
        CategoryPlot plot = chart.getCategoryPlot();

        // http://www.java2s.com/Code/Java/Chart/JFreeChartBarChartDemo3differentcolorswithinaseries.htm
        final CategoryItemRenderer renderer = new CustomBarRenderer3D
        (
            new Paint[] {CLR_PANEL_START,CLR_LBL_ROLL, CLR_PANEL_START_ALTER, CLR_BUTTONPANEL_ROLLOVER_START,
             CLR_PANEL_START.darker(),CLR_LBL_ROLL.darker(), CLR_PANEL_START_ALTER.darker(), 
        	CLR_BUTTONPANEL_ROLLOVER_START.darker(), CLR_PANEL_START.brighter(),CLR_LBL_ROLL.brighter(),
        	CLR_PANEL_START_ALTER.brighter(), CLR_BUTTONPANEL_ROLLOVER_START.brighter()}
        );

        plot.setRenderer(renderer);

        
        //plot.setDomainAxis(new MyXAxisRenderer());
        //plot.setRangeAxis(new MyYAxisRenderer());
        //plot.setBackgroundPaint(backgroundGraphColor);
        chart.setBorderVisible(false);
        chart.setBackgroundPaint(backgroundGraphColor);

        //customize renderer
        BarRenderer3D drenderer = (BarRenderer3D) plot.getRenderer();
        drenderer.setWallPaint(Color.white);
        //renderer.setBaseToolTipGenerator(new MyToolTipGenerator());
    }
        return chart;
    	
    	
    }


    private JFreeChart createLineChart(String title,String queryMaster, String queryDetail)
    {
        
        
 
    
    
        //stage.setTitle("Line Chart Sample");
        //defining the axes
       // final NumberAxis xAxis = new NumberAxis();
        //   final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        //final NumberAxis yAxis = new NumberAxis();
        //xAxis.setLabel("μήνας");
        //creating the chart
        //final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        XYSeriesCollection xyDataset = new XYSeriesCollection();
        
if(queryDetail==null && queryMaster == null)
{
    
}
else if(queryDetail==null || queryDetail.equalsIgnoreCase(""))
    {
        System.out.println("PanelGraph.createFXLineChart NO DETAIL   title:"+title+"    queryMaster:"+queryMaster);
        
    	
   	    System.out.println("PanelGraph.createFXLineChart   title:"+title+"    queryMaster:"+queryMaster);
        db.retrieveDBDataFromQuery(queryMaster,"PanelGraph.createFXLineChart queryMaster");
   	    rsMaster=db.getRS();
   	    rsmdMaster=db.getRSMetaData();    
            
       try
      {
               XYSeries series1 = new XYSeries(rsmdMaster.getColumnLabel(3));
                 //XYChart.Series series1 = new XYChart.Series();
                 //series1.setName(rsmdMaster.getColumnLabel(3));     
  
                 //XYChart.Series series2 = new XYChart.Series();
                 //series2.setName(rsmdMaster.getColumnLabel(4));
                 XYSeries series2 = new XYSeries(rsmdMaster.getColumnLabel(4));
                 
                 //XYChart.Series series3 = new XYChart.Series();                       
                 //series3.setName(rsmdMaster.getColumnLabel(5));
                 XYSeries series3 = new XYSeries(rsmdMaster.getColumnLabel(5));

          
        while(rsMaster.next())        //for dbcompany
        {
        	
        	//System.out.println("companyId "+rsMaster.getString(1));
        	//XYSeries series1 = new XYSeries(rsMaster.getString(2));
               // XYChart.Series series = new XYChart.Series();
            //   series.getData().add(new XYChart.Data(Integer.parseInt(rsMaster.getObject(2)+""),rsMaster.getObject(4))); 
 
    //series1.getData().add(new XYChart.Data<Number, Number>(Integer.parseInt(rsMaster.getObject(2)+""), Double.parseDouble(rsMaster.getObject(3)+"")));
    series1.add(Integer.parseInt(rsMaster.getObject(2)+""), Double.parseDouble(rsMaster.getObject(3)+""));

    //series2.getData().add(new XYChart.Data<Number, Number>(Integer.parseInt(rsMaster.getObject(2)+""), Double.parseDouble(rsMaster.getObject(4)+"")));
    series2.add(Integer.parseInt(rsMaster.getObject(2)+""), Double.parseDouble(rsMaster.getObject(4)+""));                

    //series3.getData().add(new XYChart.Data<Number, Number>(Integer.parseInt(rsMaster.getObject(2)+""), Double.parseDouble(rsMaster.getObject(5)+"")));
    series3.add(Integer.parseInt(rsMaster.getObject(2)+""), Double.parseDouble(rsMaster.getObject(5)+""));
    
              
               
    	//    lineChart.getData().add(series); 
    	  //xyDataset.addSeries(series); 
    	} 
         //xyDataset.add(series1, series2, series3);
         xyDataset.addSeries(series1);     //---------------------  //,series2,series3); 
         
             chart = ChartFactory.createXYLineChart(
            title,      // chart title
            rsmdMaster.getColumnLabel(2),
            rsmdMaster.getColumnLabel(4),
            //"έτη",                      // x axis label
            //"τιμή",                      // y axis label
            xyDataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );         
         
         
        }
        catch (SQLException ex)
        {    
            System.out.println("PanelGraph.createFXLineChart  Error on DB connection  "+ex.getMessage());
            ex.printStackTrace();
               //Logger.getLogger(LineChartSample.class.getName()).log(Level.SEVERE, null, ex);
        }
      finally
      {
          closeDB();
      }        
        
        
    }
    else
    {   
        
     //String queryMaster = "SELECT c.companyId, c.title, COUNT(i.date) AS count, SUM(i.value) AS sum, AVG(i.value) AS average, MIN(i.value) AS min, MAX(i.value) AS max FROM dbcompany c, invoice i WHERE c.companyId=i.companyId GROUP BY i.companyId ORDER BY sum LIMIT 6";
   	    System.out.println("PanelGraph.createFXLineChart   title:"+title+"    queryMaster:"+queryMaster+"'");
            db.retrieveDBDataFromQuery(queryMaster,"PanelGraph.createFXLineChart queryMaster");
   	    rsMaster=db.getRS();
   	    rsmdMaster=db.getRSMetaData();
   
     //rsmdMaster = db.retrieveRSMetaData(queryMaster);
     //rsMaster = db.retrieveResultSet(queryMaster);
     
     //String queryDetail = "SELECT invoice.companyId, year.year, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max FROM year, invoice WHERE year.year=invoice.year AND year.companyId=invoice.companyId GROUP BY invoice.companyId, year.year ORDER BY invoice.companyId, year.year";
   	   System.out.println("PanelGraph.createFXLineChart    title:"+title+"    queryDetail:"+queryDetail+"'");
            db.retrieveDBDataFromQuery(queryDetail,"PanelGraph.createFXLineChart queryDetail");
   	    rsDetail=db.getRS();
   	    rsmdDetail=db.getRSMetaData();
            
      try
      {
        while(rsMaster.next())        //for dbcompany
        {
        	
        	//System.out.println("companyId "+rsMaster.getString(1));
        	//XYSeries series1 = new XYSeries(rsMaster.getString(2));
                //XYChart.Series series = new XYChart.Series();
                XYSeries series = new XYSeries(rsMaster.getString(2));
         
          rsDetail.beforeFirst();
          while(rsDetail.next())
          {
          	
    	   if(rsDetail.getInt(1)==rsMaster.getInt(1))
    	   {
              System.out.println("PanelGraph.createFXLineChart    title:"+title+"  rsDetail  1,(2),(4),5 ----"+rsDetail.getObject(1)+" - "+rsDetail.getObject(2)+"  -  "+ rsDetail.getObject(4)+"  -  "+ rsDetail.getObject(5));
             //series1.add(rsDetail.getInt(2), rsDetail.getDouble(4));
            // series.getData().add(new XYChart.Data(Integer.parseInt(rsDetail.getObject(2)+""),rsDetail.getObject(4))); //Double.parseDouble(rsDetail.getString(4))));//Add data to Chart. Changed the second input to Integer due to LineChart<Number,Number>. This should work, though I haven't tested it.
             series.add(Double.parseDouble(rsDetail.getObject(2)+""),Double.parseDouble(rsDetail.getObject(4)+""));
    	   }
    	  } 
    	   // xyDataset.getData().add(series); 
    	  xyDataset.addSeries(series); 
    	}
        
        
        
            chart = ChartFactory.createXYLineChart(
            title,      // chart title
            rsmdDetail.getColumnLabel(2),
            rsmdDetail.getColumnLabel(4),
            //"έτη",                      // x axis label
            //"τιμή",                      // y axis label
            xyDataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        
        
        }
        catch (SQLException ex)
        {    
            System.out.println("PanelGraph.createFXLineChart  Error on DB connection  "+ex.getMessage());
            ex.printStackTrace();
               //Logger.getLogger(LineChartSample.class.getName()).log(Level.SEVERE, null, ex);
        }
      finally
      {
          closeDB();
      }
      
    }
 
 
        chart.setBackgroundPaint(backgroundGraphColor);
        
        return chart;
        
        
    }    

    
    
    
    private JFreeChart createLineChartPrevious(String titleIn,String queryMaster, String queryDetail)
    {
        //System.out.println("PanelGraph.createLineChart    titleIn"+titleIn);
    //System.out.println("PanelGraph.createLineChart queryMaster:"+queryMaster+" queryDetail:"+queryDetail);
        XYSeriesCollection xyDataset = new XYSeriesCollection();
    
    if(queryDetail==null || queryDetail.equalsIgnoreCase(""))
    {
    	//System.out.println("PanelGraph.createLineChart queryDetail "+queryDetail+"'");
    }
    else
    {   
        
     //String queryMaster = "SELECT c.companyId, c.title, COUNT(i.date) AS count, SUM(i.value) AS sum, AVG(i.value) AS average, MIN(i.value) AS min, MAX(i.value) AS max FROM dbcompany c, invoice i WHERE c.companyId=i.companyId GROUP BY i.companyId ORDER BY sum LIMIT 6";
   	    System.out.println("PanelGraph.createLineChart   titleIn:"+titleIn+"    queryMaster:"+queryMaster+"'");
            db.retrieveDBDataFromQuery(queryMaster,"PanelGraph.createLineChart queryMaster");
   	    rsMaster=db.getRS();
   	    rsmdMaster=db.getRSMetaData();
   
     //rsmdMaster = db.retrieveRSMetaData(queryMaster);
     //rsMaster = db.retrieveResultSet(queryMaster);
     
     //String queryDetail = "SELECT invoice.companyId, year.year, COUNT(invoice.date) AS count, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max FROM year, invoice WHERE year.year=invoice.year AND year.companyId=invoice.companyId GROUP BY invoice.companyId, year.year ORDER BY invoice.companyId, year.year";
   	   System.out.println("PanelGraph.createLineChart    titleIn:"+titleIn+"    queryDetail:"+queryDetail+"'");
            db.retrieveDBDataFromQuery(queryDetail,"PanelGraph.createLineChart queryDetail");
   	    rsDetail=db.getRS();
   	    rsmdDetail=db.getRSMetaData();
   	         
     //rsmdDetail = db.retrieveRSMetaData(queryDetail);
     //rsDetail = db.retrieveResultSet(queryDetail);
     try
     {  
      
        while(rsMaster.next())        //for dbcompany
        {
        	
        	//System.out.println("companyId "+rsMaster.getString(1));
        	XYSeries series = new XYSeries(rsMaster.getString(2));
         
          rsDetail.beforeFirst();
          while(rsDetail.next())
          {
          	
    	   if(rsDetail.getInt(1)==rsMaster.getInt(1))
    	   {
              System.out.println("PanelGraph.createLineChart    titleIn:"+titleIn+"  rsDetail  1,2,4,5 ----"+rsDetail.getInt(1)+" - "+rsDetail.getInt(2)+"  -  "+ rsDetail.getDouble(4)+"  -  "+ rsDetail.getDouble(5));
             series.add(rsDetail.getInt(2), rsDetail.getDouble(4));
             
    	   }
    	  } 
    	  
    	  xyDataset.addSeries(series); 
    	}
        
    
    
         chart = ChartFactory.createXYLineChart(
            titleIn,      // chart title
            rsmdDetail.getColumnLabel(2),
            rsmdDetail.getColumnLabel(4),
            //"έτη",                      // x axis label
            //"τιμή",                      // y axis label
            xyDataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
    
    
    
    
    
    
     }// try
     catch ( SQLException sqlex)
     {  // if the error is: Column index out of range then perhaps the LookUp is not registered
        System.out.println("error:PanelGraph.createLineChart: "+sqlex.getMessage());
     }        

     }   
        

    	
    /*	XYSeries series1 = new XYSeries("First");
        series1.add(1.0, 1.0);
        series1.add(2.0, 4.0);
        series1.add(3.0, 3.0);
        series1.add(4.0, 5.0);
        series1.add(5.0, 5.0);
        series1.add(6.0, 7.0);
        series1.add(7.0, 7.0);
        series1.add(8.0, 8.0);

        XYSeries series2 = new XYSeries("Second");
        series2.add(1.0, 5.0);
        series2.add(2.0, 7.0);
        series2.add(3.0, 6.0);
        series2.add(4.0, 8.0);
        series2.add(5.0, 4.0);
        series2.add(6.0, 4.0);
        series2.add(7.0, 2.0);
        series2.add(8.0, 1.0);

        XYSeries series3 = new XYSeries("Third");
        series3.add(1.0, 4.0);
        series3.add(2.0, 3.0);
        series3.add(3.0, 2.0);
        series3.add(4.0, 3.0);
        series3.add(5.0, 6.0);
        series3.add(6.0, 3.0);
        series3.add(7.0, 4.0);
        series3.add(8.0, 3.0);

        XYSeries series4 = new XYSeries("fourth");
        series4.add(1.0, 1.0);
        series4.add(2.0, 4.0);
        series4.add(3.0, 3.0);
        series4.add(4.0, 6.0);
        series4.add(7.0, 7.0);
        series4.add(8.0, 2.0);
        series4.add(9.0, 9.0);
        series4.add(10.0, 6.0);     

        XYSeriesCollection xyDataset = new XYSeriesCollection();
        xyDataset.addSeries(series1);
        xyDataset.addSeries(series2);
        xyDataset.addSeries(series3);
        xyDataset.addSeries(series4); */ 
       
    	
    	
    /*	JFreeChart chart = createChart(xyDataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));*/
    	
    	
    	        // create the chart...


        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        //chart.setBackgroundPaint()
        //chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
        
        
        // get a reference to the plot for further customisation...
        // get a reference to the plot for further customisation...
        
        //  Exception in thread "main" java.lang.ClassCastException: org.jfree.chart.plot.PiePlot3D cannot be cast to org.jfree.chart.plot.XYPlot
 /*       final XYPlot xplot = chart.getXYPlot();
        xplot.setBackgroundPaint(Color.white);
       // plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        xplot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        xplot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        xplot.setDomainCrosshairVisible(true);
        xplot.setRangeCrosshairVisible(true);

        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        xplot.setRenderer(renderer);


        // get a reference to the plot for further customisation...
        final XYPlot xyplot = chart.getXYPlot();

        xyplot.getRenderer().setSeriesPaint(0, CLR_PANEL_START_ALTER);
        xyplot.getRenderer().setSeriesPaint(1, CLR_PANEL_START);
        xyplot.getRenderer().setSeriesPaint(2, CLR_LBL_ROLL);
        xyplot.getRenderer().setSeriesPaint(3, CLR_BUTTONPANEL_ROLLOVER_START);
        xyplot.getRenderer().setSeriesPaint(4, CLR_PANEL_START);
        xyplot.getRenderer().setSeriesPaint(5, CLR_BUTTONPANEL_ROLLOVER_START);
        xyplot.getRenderer().setSeriesPaint(6, CLR_PANEL_START_ALTER);
        xyplot.getRenderer().setSeriesPaint(7, CLR_BUTTONPANEL_ROLLOVER_START);
        xyplot.getRenderer().setSeriesPaint(8, CLR_LBL_ROLL);        
        
        
        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) xplot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
      */  
        
        
      closeDB();
         chart.setBackgroundPaint(backgroundGraphColor);
                
        return chart;
    	
    	
    	
    }
 
    
    
    
    
 private JFreeChart createLineChartNotDb(String titleIn)
    {
        //System.out.println("PanelGraph.createLineChartNotDb    titleIn"+titleIn);
    //System.out.println("PanelGraph.createLineChart queryMaster:"+queryMaster+" queryDetail:"+queryDetail);
    //    XYSeriesCollection xyDataset = new XYSeriesCollection();
  
        
   

    
    
    	
    	XYSeries series1 = new XYSeries("First");
        series1.add(1.0, 1.0);
        series1.add(2.0, 4.0);
        series1.add(3.0, 3.0);
        series1.add(4.0, 5.0);
        series1.add(5.0, 5.0);
        series1.add(6.0, 7.0);
        series1.add(7.0, 7.0);
        series1.add(8.0, 8.0);

        XYSeries series2 = new XYSeries("Second");
        series2.add(1.0, 5.0);
        series2.add(2.0, 7.0);
        series2.add(3.0, 6.0);
        series2.add(4.0, 8.0);
        series2.add(5.0, 4.0);
        series2.add(6.0, 4.0);
        series2.add(7.0, 2.0);
        series2.add(8.0, 1.0);

        XYSeries series3 = new XYSeries("Third");
        series3.add(1.0, 4.0);
        series3.add(2.0, 3.0);
        series3.add(3.0, 2.0);
        series3.add(4.0, 3.0);
        series3.add(5.0, 6.0);
        series3.add(6.0, 3.0);
        series3.add(7.0, 4.0);
        series3.add(8.0, 3.0);

        XYSeries series4 = new XYSeries("fourth");
        series4.add(1.0, 1.0);
        series4.add(2.0, 4.0);
        series4.add(3.0, 3.0);
        series4.add(4.0, 6.0);
        series4.add(7.0, 7.0);
        series4.add(8.0, 2.0);
        series4.add(9.0, 9.0);
        series4.add(10.0, 6.0);     

        XYSeriesCollection xyDataset = new XYSeriesCollection();
        xyDataset.addSeries(series1);
        xyDataset.addSeries(series2);
        xyDataset.addSeries(series3);
        xyDataset.addSeries(series4); 
       

    
    //String chartTitle = "Objects Movement Chart";
    String xAxisLabel = "X";
    String yAxisLabel = "Y";
 
    
 
    JFreeChart chart = ChartFactory.createXYLineChart("", xAxisLabel, yAxisLabel, xyDataset);
 
    


        
    	
    /*	JFreeChart chart = createChart(xyDataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));*/
    	
    	
    	        // create the chart...


        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        //chart.setBackgroundPaint()
        //chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
        
        



        // get a reference to the plot for further customisation...
        //final XYPlot xyplot = chart.getXYPlot();

   /*     xyplot.getRenderer().setSeriesPaint(0, CLR_PANEL_START_ALTER);
        xyplot.getRenderer().setSeriesPaint(1, CLR_PANEL_START);
        xyplot.getRenderer().setSeriesPaint(2, CLR_LBL_ROLL);
        xyplot.getRenderer().setSeriesPaint(3, CLR_BUTTONPANEL_ROLLOVER_START);
        xyplot.getRenderer().setSeriesPaint(4, CLR_PANEL_START);
        xyplot.getRenderer().setSeriesPaint(5, CLR_BUTTONPANEL_ROLLOVER_START);
        xyplot.getRenderer().setSeriesPaint(6, CLR_PANEL_START_ALTER);
        xyplot.getRenderer().setSeriesPaint(7, CLR_BUTTONPANEL_ROLLOVER_START);
        xyplot.getRenderer().setSeriesPaint(8, CLR_LBL_ROLL);        
        */
        
        // change the auto tick unit selection to integer units only...
    //    final NumberAxis rangeAxis = (NumberAxis) xyplot.getRangeAxis();
    //    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
        
       chart.setBackgroundPaint(backgroundGraphColor);
                
        return chart;
    	
    	
    	
    }    
    
    
    
/*    private class MyToolTipGenerator extends StandardCategoryToolTipGenerator
    {
        @Override
        public String generateToolTip(CategoryDataset dataset, 
            int row, int column)
        {
            return "My tooltip - "+
                super.generateToolTip(dataset,row,column);
        }
    }*/

    public void saveAsJPEG()
    {
    	
    	
    	
        JFileChooser fc = new JFileChooser(); 
 		
 	//	FileFilter ff = new FileFilter(new File(type));
 	//	fc.addChoosableFileFilter(new FileFilter(new File(type)));
    //   setFileFilter(FileFilter filter) 
    
 		// show the filechooser 
 		String filename = "chart.jpg";
 		fc.setDialogTitle("Εξαγωγή αρχείου jpg");
 		File curDir = new File(System.getProperty("user.dir"));
 		fc.setCurrentDirectory(curDir);
 		fc.setSelectedFile(new File(filename));
 		int result = fc.showSaveDialog(null); 
 		 
 		// if we selected an image, load the image 
 		if(result == JFileChooser.APPROVE_OPTION)
 		{ 
    	
    	   filename=fc.getSelectedFile().getName();
    	
    	try
        {
        	
        	
        	
            ChartUtilities.saveChartAsJPEG(new File(System.getProperty("user.dir")+"\\"+filename), chart, 600, 300);
            System.out.println("saved jpg in:"+System.getProperty("user.dir")+"\\"+filename);
        } catch (Exception e) {
            System.out.println("Problem occurred creating chart.");
        }
    	
    	} // if ok clicked
    }
   
    /**
     * A custom renderer that returns a different color for each item in a single series.
     */
     // http://www.java2s.com/Code/Java/Chart/JFreeChartBarChartDemo3differentcolorswithinaseries.htm
    class CustomBarRenderer3D extends BarRenderer3D {

        /** The colors. */
        private Paint[] colors;

        /**
         * Creates a new renderer.
         *
         * @param colors  the colors.
         */
        public CustomBarRenderer3D(final Paint[] colors) {
            this.colors = colors;
        }

        /**
         * Returns the paint for an item.  Overrides the default behaviour inherited from
         * AbstractSeriesRenderer.
         *
         * @param row  the series.
         * @param column  the category.
         *
         * @return The item color.
         */
        public Paint getItemPaint(final int row, final int column) {
            return this.colors[row % this.colors.length]; // row or column
        }
    }   
    
    
    // http://javabeanz.wordpress.com/2007/08/06/creating-pie-charts-using-custom-colors-jfreechart/
    public static class PieRenderer
    {
        private Color[] color;
       
        public PieRenderer(Color[] color)
        {
            this.color = color;
        }       
       
        public void setColor(PiePlot plot, DefaultPieDataset dataset)
        {
            List <Comparable> keys = dataset.getKeys();
            int aInt;
           
            for (int i = 0; i < keys.size(); i++)
            {
                aInt = i % this.color.length;
                plot.setSectionPaint(keys.get(i), this.color[aInt]);
            }
        }
    }
    
    
   public void closeDB()
   {
   	
   	      db.releaseConnectionRs();
          db.releaseConnectionRsmd();
   	
   }       
    
    public static void main(String[] args)
    {
    	
        PanelGraph tst =new PanelGraph();
        tst.initFrame();
        
    
    }
    

    
}

