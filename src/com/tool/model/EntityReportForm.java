// created 03-01-2010
 package com.tool.model;

 import com.tool.guicomps.*;

 public class EntityReportForm implements Constants
 {

    private String name;
    private String caption;
    private String backgroundFile;
    private int height;
    private int width;
    private int pageOrientation;  // 0 portrait, 1 landscape
    private EntityReportFormField[] entityReportFormField;

    
    public EntityReportForm() 
    {
    }    
    
    
    public EntityReportForm(String nameIn, String captionIn,String backgroundFileIn, int heightIn,int widthIn, int pageOrientationIn)//, EntityReportFormField[] entityReportFormFieldIn) 
    {
      name=nameIn;
      caption=captionIn;
      backgroundFile=backgroundFileIn;
      height=heightIn;
      width=widthIn;    
      pageOrientation=pageOrientationIn;
      //entityReportFormField = entityReportFormFieldIn;
    }
    
  public void setName(String nameIn) {name = nameIn;}
  public void setCaption(String captionIn) {caption = captionIn;}
  public void setBackgroundFile(String backgroundFileIn) {backgroundFile = backgroundFileIn;}
  public void setHeight(int heightIn) {height = heightIn;}
  public void setWidth(int widthIn) {width = widthIn;}
  public void setEntityReportFormField(EntityReportFormField[] entityReportFormFieldIn) {entityReportFormField = entityReportFormFieldIn;}
  
  
  public String getName() { return name;}
  public String getCaption() {return caption ;}
  public String getBackgroundFile() {return backgroundFile ;}
  public int getHeight() {return height;}
  public int getWidth() {return width;}  
  public EntityReportFormField[] getEntityReportFormField() {return entityReportFormField;}  
    
 }