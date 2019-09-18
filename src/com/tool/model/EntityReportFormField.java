// created 03-01-2010
 package com.tool.model;
 
 import com.tool.guicomps.*;
 
 public class EntityReportFormField implements Constants
 {
 	private int id; // not be counted when xml file is read and presented
        private String tableName;
    private String name;
    private String caption;
    private int page;
    private int x;
    private int y;
    private int height;
    private int width;
    private int alignment;
    private boolean showBorder; // not boolean because XMLReader.returnObjectTypeClassFromStrng does not return correct boolean
    private boolean showBackground;
    private boolean showBold;
    private boolean showItalic;
    private int fontSize;
    
    
    
    public EntityReportFormField() 
    {
    }
   
    /*
     * has pageIn
     */
    public EntityReportFormField(String tableNameIn,  String nameIn, String captionIn,int pageIn, int xIn, int yIn, int heightIn,int widthIn,int alignmentIn,
    boolean showBorderIn,boolean showBackgroundIn, boolean showBoldIn, boolean showItalicIn, int fontSizeIn)
    {
      //id=idIn;
      tableName=tableNameIn;
      name=nameIn;
      caption=captionIn;
      page=pageIn;
      x=xIn;
      y=yIn;
      height=heightIn;
      width=widthIn;
      alignment= alignmentIn;
      showBorder= showBorderIn;
      showBackground=showBackgroundIn;
      showBold= showBoldIn;
      showItalic= showItalicIn;
      fontSize=fontSizeIn;
        	
    }
 
    /*
     *
     *  has not pageIn
     */
    public EntityReportFormField(String tableNameIn, String nameIn, String captionIn, int xIn, int yIn, int heightIn,int widthIn,int alignmentIn, 
    boolean showBorderIn,boolean showBackgroundIn, boolean showBoldIn, boolean showItalicIn, int fontSizeIn)
    {
      //id=idIn;
       tableName=tableNameIn;
      page = 0;  // not in initialization
      
      name=nameIn;
      caption=captionIn;
      //page=pageIn;
      x=xIn;
      y=yIn;
      height=heightIn;
      width=widthIn;
      alignment= alignmentIn;
      showBorder= showBorderIn;
      showBackground=showBackgroundIn;
      showBold= showBoldIn;
      showItalic= showItalicIn;
      fontSize=fontSizeIn;
        	
    } 
     
     
     // available fields to add in form
    public EntityReportFormField(String tableNameIn, String nameIn ,int alignmentIn,int widthIn)
    {
      //id=idIn;
       tableName=tableNameIn;
      name=nameIn;
      /*caption=captionIn;
      page=pageIn;
      x=xIn;
      y=yIn;
      height=heightIn;*/
      width=widthIn;
      alignment= alignmentIn;
      /*showBorder= showBorderIn;
      showBackground=showBackgroundIn;
      showBold= showBoldIn;
      showItalic= showItalicIn;
      fontSize=fontSizeIn;*/
        	
    }
 
 
  public void setId(int idIn) {id = idIn;}
  public void setTableName(String tableNameIn) {tableName = tableNameIn;}
  public void setName(String nameIn) {name = nameIn;}
  public void setCaption(String captionIn) {caption = captionIn;}
  public void setX(int xIn) {x = xIn;}
  public void setY(int yIn) {y = yIn;}
  public void setHeight(int heightIn) {height = heightIn;}
  public void setWidth(int widthIn) {width = widthIn;}
  public void setAlignment(int alignmentIn) {alignment = alignmentIn;}
  public void setShowBorder(boolean showBorderIn) {showBorder = showBorderIn;}
  public void setShowBackground(boolean showBackgroundIn) {showBackground = showBackgroundIn;}
  public void setShowBold(boolean showBoldIn) {showBold = showBoldIn;}
  public void setShowItalic(boolean showItalicIn) {showItalic = showItalicIn;}
  public void setFontSize(int fontSizeIn) {fontSize = fontSizeIn;}

  public void setPage(int pageIn) {page = pageIn;}


  public int getId() {return id;}
  public String getTableName() { return tableName;}
  public String getName() { return name;}
  public String getCaption() {return caption ;}
  public int getX() {return x;}
  public int getY() {return y;}
  public int getHeight() {return height;}
  public int getWidth() {return width;}
  public int getAlignment() {return alignment;}
  public boolean getShowBorder() {return showBorder;}
  public boolean getShowBackground() {return showBackground;}
  public boolean getShowBold() {return showBold;}
  public boolean getShowItalic() {return showItalic;}
  public int getFontSize() {return fontSize;}

 public int getPage() {return page;}
    
 }