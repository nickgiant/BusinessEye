// created 18-04-2009
// http://sujitpal.blogspot.com/2006/05/java-data-structure-generic-tree.html
package com.tool.utils;

import java.util.*;

/**
 * Represents a node of the Tree<T> class. The DataTreeNode<T> is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the Tree<T>.
 */
public class DataTreeNode<T>
{
 
    public T data;
    public List<DataTreeNode<T>> children;
 
    /**
     * Default ctor.
     */
    public DataTreeNode() {
        super();
    }
 
    /**
     * Convenience ctor to create a DataTreeNode<T> with an instance of T.
     * @param data an instance of T.
     */
    public DataTreeNode(T data) {
        this();
        setData(data);
    }
    
    public boolean hasNodeChildren()
    {
    	boolean retHasChildren = false;
    	if (this.getNumberOfChildren() >0)
    	{
            retHasChildren=true;
        }
        
        return retHasChildren;
    }
     
    /**
     * Return the children of DataTreeNode<T>. The Tree<T> is represented by a single
     * root DataTreeNode<T> whose children are represented by a List<DataTreeNode<T>>. Each of
     * these DataTreeNode<T> elements in the List can have children. The getChildren()
     * method will return the children of a DataTreeNode<T>.
     * @return the children of DataTreeNode<T>
     */
    public List<DataTreeNode<T>> getChildren() {
        if (this.children == null) {
            return new ArrayList<DataTreeNode<T>>();
        }
        return this.children;
    }
 
    /**
     * Sets the children of a DataTreeNode<T> object. See docs for getChildren() for
     * more information.
     * @param children the List<DataTreeNode<T>> to set.
     */
    public void setChildren(List<DataTreeNode<T>> children) {
        this.children = children;
    }
 
    /**
     * Returns the number of immediate children of this DataTreeNode<T>.
     * @return the number of immediate children.
     */
    public int getNumberOfChildren() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }
     
    /**
     * Adds a child to the list of children for this DataTreeNode<T>. The addition of
     * the first child will create a new List<DataTreeNode<T>>.
     * @param child a DataTreeNode<T> object to set.
     */
    public void addChild(DataTreeNode<T> child) {
        if (children == null) {
            children = new ArrayList<DataTreeNode<T>>();
        }
        children.add(child);
    }
     
    /**
     * Inserts a DataTreeNode<T> at the specified position in the child list. Will     * throw an ArrayIndexOutOfBoundsException if the index does not exist.
     * @param index the position to insert at.
     * @param child the DataTreeNode<T> object to insert.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void insertChildAt(int index, DataTreeNode<T> child) throws IndexOutOfBoundsException {
        if (index == getNumberOfChildren()) {
            // this is really an append
            addChild(child);
            return;
        } else {
            children.get(index); //just to throw the exception, and stop here
            children.add(index, child);
        }
    }
     
    /**
     * Remove the DataTreeNode<T> element at index index of the List<DataTreeNode<T>>.
     * @param index the index of the element to delete.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }
 
    public T getData() {
        return this.data;
    }
 
    public void setData(T data) {
        this.data = data;
    }
    
    public DataTreeNode<T> getChildFromIndex(int index) throws IndexOutOfBoundsException
    {
    	DataTreeNode<T> retChild = null;
    	//int intChildCount =0;
    	//for(int c=0; c<getNumberOfChildren(); c++)
    	//{
    		//System.out.println("DataTreeNode.getChild '"+caption+"' "+children.get(c).toString());
    		
    	//	if(caption.equalsIgnoreCase(children.get(c).toString()))
    	//	{
    			retChild=children.get(index);
    	/*		intChildCount++;
                //System.out.println("DataTreeNode.getChild '"+caption+"' has "+intChildCount);    			
    			if(intChildCount>1)
    			{
    				System.out.println("error DataTreeNode.getChild '"+caption+"' has "+intChildCount+" children nodes(should be only one).");
    			}
    	//	}*/
    	//}
        
        return retChild;
    }    
    
    public DataTreeNode<T> getChildFromCaption(String caption) throws IndexOutOfBoundsException
    {
    	DataTreeNode<T> retChild = null;
    	int intChildCount =0;
    	for(int c=0; c<getNumberOfChildren(); c++)
    	{
    		//System.out.println("DataTreeNode.getChild '"+caption+"' "+children.get(c).toString());
    		
    		if(caption.equalsIgnoreCase(children.get(c).toString()))
    		{
    			retChild=children.get(c);
    			intChildCount++;
                //System.out.println("DataTreeNode.getChild '"+caption+"' has "+intChildCount);    			
    			if(intChildCount>1)
    			{
    				System.out.println("error DataTreeNode.getChild '"+caption+"' has "+intChildCount+" children nodes(should be only one).");
    			}
    		}
    	}
        
        return retChild;
    }
 

 
     
    public String toString() {
        /*StringBuilder sb = new StringBuilder();
        sb.append("{").append(getData().toString()).append(",[");
        int i = 0;
        for (DataTreeNode<T> e : getChildren()) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(e.getData().toString());
            i++;
        }
        sb.append("]").append("}");
        return sb.toString();*/
        
        StringBuilder sb = new StringBuilder();
        sb.append(getData().toString());
        return sb.toString();
    }
    
}
