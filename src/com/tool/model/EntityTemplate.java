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
      *   set only in the entity that uses the template (like esoexo records, not in the esoexotemplates)
      */
    
    private String queryShowTemplates;
    private String subQueryIsTemplate;
    private String subQueryIsNotTemplate;
    private String fieldOfTemplateMenu;
    private String fieldPrimkeyId ;
    private String fieldIsTemplate ;
    
    
      public EntityTemplate()
      {}
      
      /*
      *   set only in the entity that uses the template (like esoexo records, not in the esoexotemplates)
      */
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
