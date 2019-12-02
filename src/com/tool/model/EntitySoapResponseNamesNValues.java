/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.model;

/**
 *
 * @author one
 */
public class EntitySoapResponseNamesNValues {
    

    public String nameNode;
    public String caption;
    public String classtype;
    public String value;
    public String nameDb;
    
    public  EntitySoapResponseNamesNValues(String nameNodeIn,String captionIn,String classtypeIn, String valueIn, String nameDbIn)
    {
        nameNode=nameNodeIn;
        caption=captionIn;
        classtype=classtypeIn;
        value=valueIn;
        nameDb=nameDbIn;
    }
}