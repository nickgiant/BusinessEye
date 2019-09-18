package com.tool.model;

 import com.tool.gui.*;

 import java.util.Hashtable;
 import java.util.Enumeration;

public class DbEntity
{

  private Hashtable entities;
  private ModelEntity me;
  public String[] entityNames;
  public String[] entityCaptions;

 //ModelEntity me;// = new ModelEntity();
 
    public void DbEntity()
    {
      entities = new Hashtable();   // initially empty
      
    }

  public void addModelEntity(ModelEntity entity)throws ModelEntityException
  {
    String name = entity.getName(); // name extracted for key
    System.out.println("1: "+name);
    if (entities.containsKey(name)) // check name not already in use
    {
    	System.out.println("2: "+name);
      throw new ModelEntityException ("ModelEntityException:this entity name is taken");
    }
    System.out.println("3: "+name);
    entities.put(name, entity); // add entity to list with name as key
    System.out.println("4: "+name);
  } 

  public void removeModelEntity (String nameIn)throws ModelEntityException
  {
    if (!entities.containsKey(nameIn)) // check name in list
    {
       throw new ModelEntityException ("ModelEntityException:this entity name does not exist");
    }
    entities.remove(nameIn); // removes name and associted entity from list
  }

  public ModelEntity getModelEntity(String nameIn)throws ModelEntityException
  {
      if (!entities.containsKey(nameIn)) // check name in list
      {
        throw new ModelEntityException ("ModelEntityException:this entity name does not exist");
      }
      return (ModelEntity)entities.get(nameIn); // get associated entity
  }

  public String[] getModelEntityNames() 
  {
  	return entityNames;
  }

  public String[] getModelEntityCaptions() 
  {
  	return entityCaptions;
  }

  public int getModelEntityCount() 
  {
  	return entityNames.length;
  }

  public String getModelEntityName(int i) 
  {
  	return entityNames[i];
  }


  public Enumeration getEntities()
  {
    return entities.elements();  // returns column list as an enumeration object
  }

    private void createNodes()
    {
        System.out.println("createnodes");
       
         entityNames = new  String[1];
        /*         	me = new ModelEntity("cooperative","Συνεταιρισμός"," "," "," ");
             entityNames[0] = "cooperative";*/
          	me = new ModelEntity("cooperative","Συνεταιρισμός"," "," "," ");

      
          try
          {
             this.addModelEntity(me);
             //entityNames[0] = "cooperative";
          }
          catch (ModelEntityException ex)
          {
            System.out.println("error:ModelEntity "+ex.getMessage());
          }
   
       /* 
       new ModelEntity("cooperative","Συνεταιρισμός"," "," "," ");
       // category.add(book);

      new ModelEntity("farmer","Αγρότης",null,null,null);
      //  category.add(book);

       new ModelEntity("invoice","Τιμολόγιο",null,null,null);
      // category.add(book);*/

      /*  book = new ModelEntity("product","Προϊόν",null,null,null);
        //category.add(book);

        book = new ModelEntity("productType","Τύπος προϊόντος",null,null,null);
        //category.add(book);

        book = new ModelEntity("town","Πόλη / Χωριό",null,null,null);
       // category.add(book);
        
        book = new ModelEntity("user","Χρήστης",null,null,null);
        //category.add(book);*/
 
    }

    public static void main(String[] args)
    {
       DbEntity dbe = new DbEntity();
      
       dbe.createNodes();
       System.out.println(dbe.getModelEntityCount());
       System.out.println("name: "+dbe.getModelEntityName(0));
       //getModelEntity(dbe.getModelEntityName(0));
    }

private class ModelEntity
{
        public String name;
        public String caption;
        public String typeForMenu;
        public String typeForView;
        public String query;
        
       /* public String[] entityNames;
        public String[] entityCaptions;*/

        public ModelEntity(String nameIn, String captionIn, String typeForMenuIn, String typeForViewIn, String queryIn)
        {
            name = nameIn;
            caption = captionIn;
            typeForMenu = typeForMenuIn;
            typeForView = typeForViewIn;
            query = queryIn;
           
           System.out.println(name+" "+caption);
        }
        
        public String toString()
        {
            return name;
        }

    public String getName()  {  return name;  }
    public String getCaption()  {  return caption;  }
    public String getTypeForMenu()  {  return typeForMenu;  }
    public String getTypeForView()  {  return typeForView;  }
    public String getQuery()  {  return query;  }

 }
}
