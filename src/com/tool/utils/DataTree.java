// created 18-04-2009
// http://sujitpal.blogspot.com/2006/05/java-data-structure-generic-tree.html

package com.tool.utils;

import java.util.*;
/**
 * Represents a DataTree of Objects of generic type T. The DataTree is represented as
 * a single rootElement which points to a List<DataTreeNode<T>> of children. There is
 * no restriction on the number of children that a particular node may have.
 * This DataTree provides a method to serialize the DataTree into a List by doing a
 * pre-order traversal. It has several methods to allow easy updation of DataTreeNodes
 * in the DataTree.
 */
public class DataTree<T>
{
 
    private DataTreeNode<T> rootElement;
     
    /**
     * Default ctor.
     */
    public DataTree() {
        super();
    }
 
    /**
     * Return the root DataTreeNode of the tree.
     * @return the root element.
     */
    public DataTreeNode<T> getRootElement() {
        return this.rootElement;
    }
 
    /**
     * Set the root Element for the tree.
     * @param rootElement the root element to set.
     */
    public void setRootElement(DataTreeNode<T> rootElement) {
        this.rootElement = rootElement;
    }
     
    /**
     * Returns the DataTree<T> as a List of DataTreeNode<T> objects. The elements of the
     * List are generated from a pre-order traversal of the tree.
     * @return a List<DataTreeNode<T>>.
     */
    public List<DataTreeNode<T>> toList() {
        List<DataTreeNode<T>> list = new ArrayList<DataTreeNode<T>>();
        walk(rootElement, list);
        return list;
    }
     
    /**
     * Returns a String representation of the DataTree. The elements are generated
     * from a pre-order traversal of the DataTree.
     * @return the String representation of the DataTree.
     */
    public String toString() {
        return toList().toString();
    }
     
    /**
     * Walks the DataTree in pre-order style. This is a recursive method, and is
     * called from the toList() method with the root element as the first
     * argument. It appends to the second argument, which is passed by reference
     * as it recurses down the tree.
     * @param element the starting element.
     * @param list the output of the walk.
     */
    private void walk(DataTreeNode<T> element, List<DataTreeNode<T>> list) {
        list.add(element);
        for (DataTreeNode<T> data : element.getChildren()) {
            walk(data, list);
        }
    }
}
