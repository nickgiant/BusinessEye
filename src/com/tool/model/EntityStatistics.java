//14-07-2007
package com.tool.model;

    public class EntityStatistics
    {
        public String name;
        public String entity;
        public String caption;
        public String querySelect;
        public String queryFrom;
        public String queryWhere;
        public String queryBroupBy;
        public String queryOrderBy;
        public boolean isFilterCompany;
        public String fieldCompanyIdName;
        public boolean isFilterYear;
        public String fieldYearName;
        public String primKey;
        public String primKeyDb;
        private EntityPanel[] drillEntityPanel;
        private String[] fieldsOnStatisticsTitle;
        private String[] fieldsOnStatisticsTitleCaption;
        
        
        public EntityStatistics(String nameIn,String entityIn,String captionIn, String querySelectIn, String queryFromIn, String queryWhereIn, 
          String queryBroupByIn, String queryOrderByIn,boolean isFilterCompanyIn, String fieldCompanyIdNameIn,
          boolean isFilterYearIn, String fieldYearNameIn, String primKeyIn, String primKeyDbIn, EntityPanel[] drillEntityPanelIn,
          String [] fieldsOnStatisticsTitleIn,String [] fieldsOnStatisticsTitleCaptionIn)
        {
          name = nameIn;
          entity=entityIn;
          caption=captionIn;
          querySelect=querySelectIn;
          queryFrom=queryFromIn;
          queryWhere=queryWhereIn;
          queryBroupBy=queryBroupByIn;
          queryOrderBy=queryOrderByIn;
          isFilterCompany=isFilterCompanyIn;
         fieldCompanyIdName=fieldCompanyIdNameIn;
         isFilterYear=isFilterYearIn;
         fieldYearName=fieldYearNameIn;
         primKey=primKeyIn;
         primKeyDb=primKeyDbIn;
         drillEntityPanel = drillEntityPanelIn;
         fieldsOnStatisticsTitle=fieldsOnStatisticsTitleIn;
         fieldsOnStatisticsTitleCaption=fieldsOnStatisticsTitleCaptionIn;
        }
        
        public String toString()
        {
            return caption;
        }

        public String getName()  {   return name;  }
        public String getEntity()  {   return entity;  }
        public String getCaption()  {   return caption;  }
        public String getQuerySelect()  {   return querySelect;  }
        public String getQueryFrom()  {   return queryFrom;  }
        public String getQueryWhere()  {   return queryWhere;  }
        public String getQueryBroupBy()  {   return queryBroupBy;  }
        public String getQueryOrderBy()  {   return queryOrderBy;  }
        public boolean getIsFilterCompany()  {   return isFilterCompany;  }
        public String getFieldCompanyName()  {   return fieldCompanyIdName;  }
        public boolean getIsFilterYear()  {   return isFilterYear;  }
        public String getFieldYearName()  {   return fieldYearName;  }
        public String getPrimKey()  {   return primKey;  }
        public String getPrimKeyDb()  {   return primKeyDb;  }
        public EntityPanel[] getDrillEntityPanel() {return drillEntityPanel;}
        public String[] getFieldsOnStatisticsTitle() {return fieldsOnStatisticsTitle;}
        public String[] getFieldsOnStatisticsTitleCaption() {return fieldsOnStatisticsTitleCaption;}

    }