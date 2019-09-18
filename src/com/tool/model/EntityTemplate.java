/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.model;

/**
 *
 * @author user
 */
public class EntityTemplate
{
    
    /*
    *
    *        String queryTemplates = "SELECT * FROM sxesoexoheader WHERE  sxesoexoheader.dbCompanyId = "+VariablesGlobal.globalCompanyId+"  AND  sxesoexoheader.isTemplate = 1 ORDER BY sxesoexoheader.titleOfTemplate";//esoexoheaderId";//sxesoexoheader.titleOfTemplate";
      final String subqueryIsNotTemplate = "AND sxesoexoline.isTemplate ='0'";
      final String subqueryIsTemplate = "AND sxesoexoline.isTemplate LIKE '1'";
      String fieldHeaderId = "sxesoexoheader.esoexoheaderId";
      String strNameOfFieldOfTemplateMenu = "sxesoexoheader.titleOfTemplate";
    String setFieldIsTemplate = "isTemplate";
    */
    
    
    
    private String queryShowTemplates;
    private String subQueryIsTemplate;
    private String subQueryIsNotTemplate;
    private String fieldOfTemplateMenu;
    private String fieldPrimkeyId ;
    private String fieldIsTemplate ;
    
    
      public EntityTemplate()
      {}
      
      public EntityTemplate(String queryShowTemplatesIn,String subQueryIsTemplateIn, String subQueryIsNotTemplateIn,String fieldPrimkeyIdIn,String fieldOfTemplateMenuIn,String fieldIsTemplateIn)
      {   
      queryShowTemplates = queryShowTemplatesIn;
      subQueryIsTemplate = subQueryIsTemplateIn;
      subQueryIsNotTemplate =subQueryIsNotTemplateIn;
      fieldOfTemplateMenu = fieldOfTemplateMenuIn;
      fieldPrimkeyId = fieldPrimkeyIdIn ;
      fieldIsTemplate = fieldIsTemplateIn ;          
      } 
      
      
        public String getQueryShowTemplates()     {     return queryShowTemplates;        }
        public String getSubQueryIsTemplate()     {     return subQueryIsTemplate;        }
        public String getSubQueryIsNotTemplate()     {     return subQueryIsNotTemplate;}
        public String getFieldOfTemplateMenu()     {     return fieldOfTemplateMenu;        }
        public String getFieldPrimkeyId()     {     return fieldPrimkeyId;        }
        public String getFieldIsTemplate()     {     return fieldIsTemplate;}        
    
}
