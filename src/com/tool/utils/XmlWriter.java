// created 03-01-2010

package com.tool.utils;

//http://www.generationjava.com/docs/XmlWriter/XmlWriter.java.html

//com.generationjava.io.xml Directory
//Source
//XmlWriter.java


import java.io.IOException;
import java.io.Writer;

import java.util.Stack;

//import com.generationjava.io.WritingException;

/**
 * Makes writing XML much much easier. 
 *
 * @author <a href="mailto:bayard@generationjava.com">Henri Yandell</a>
 * @version 0.1
 */
public class XmlWriter {

    private Writer writer;      // underlying writer
    private Stack stack;        // of xml entity names
    private StringBuffer attrs; // current attribute string
    private boolean empty;      // is the current node empty
    private boolean closed;     // is the current node closed...

    /**
     * Create an XmlWriter on top of an existing java.io.Writer.
     */
    public XmlWriter(Writer writer) {
        this.writer = writer;
        this.closed = true;
        this.stack = new Stack();
    }

    /**
     * Begin to output an entity. 
     *
     * @param String name of entity.
     */
    public XmlWriter writeEntity(String name) {//throws WritingException {
        try {
            closeOpeningTag();
            this.closed = false;
            this.writer.write("<");
            this.writer.write(name);
            stack.add(name);
            this.empty = true;
            
        } catch (IOException ioe)
        {
        	System.out.println("error XmlWriter.writeEntity "+ioe.getMessage());
            //throw new XmlWritingException(ioe);
        }
        return this;
    }
  
    // close off the opening tag
    // used in DialogMain only because when writted leaves head unclosed
    public void closeOpenTag() 
    {
        try {    	
        if (!this.closed) {
            //writeAttributes();
            this.closed = true;
            this.writer.write(">");
        }
        } catch (IOException ioe)
        {
        	System.out.println("error XmlWriter.closeOpenTag "+ioe.getMessage());
            //throw new XmlWritingException(ioe);
        }        
    }   
  
    // close off the opening tag
    private void closeOpeningTag() throws IOException {
        if (!this.closed) {
            writeAttributes();
            this.closed = true;
            this.writer.write(">");
        }
    }

    // write out all current attributes
    private void writeAttributes() throws IOException {
        if (this.attrs != null) {
            this.writer.write(this.attrs.toString());
            this.attrs.setLength(0);
            this.empty = false;
        }
    }

    /**
     * Write an attribute out for the current entity. 
     * Any xml characters in the value are escaped.
     * Currently it does not actually throw the exception, but 
     * the api is set that way for future changes.
     *
     * @param String name of attribute.
     * @param String value of attribute.
     */
    public XmlWriter writeAttribute(String attr, String value) {//throws WritingException {

        // maintain api
        if (false) System.out.println("error XmlWriter.writeAttribute ");//throw new XmlWritingException();

        if (this.attrs == null) {
            this.attrs = new StringBuffer();
        }
        this.attrs.append(" ");
        this.attrs.append(attr);
        this.attrs.append("=\"");
        this.attrs.append(escapeXml(value));
        this.attrs.append("\"");
        return this;
    }

    /**
     * End the current entity. This will throw an exception 
     * if it is called when there is not a currently open 
     * entity.
     */
    public XmlWriter endEntity() {//throws WritingException {
        try {
            if(this.stack.empty())
            {
            	System.out.println("error XmlWriter.endEntity Called endEntity too many times.");
                //throw new XmlWritingException("Called endEntity too many times. ");
            }
            String name = (String)this.stack.pop();
            if (name != null) {
                if (this.empty) {
                    writeAttributes();
                    this.writer.write("/>");
                    //this.writer.write("\n");
                    
                } else {
                    this.writer.write("</");
                    this.writer.write(name);
                    this.writer.write(">");
                    //this.writer.write("\n");
                }
                this.empty = false;
            }
            
        } catch (IOException ioe)
        {
        	System.out.println("error XmlWriter.endEntity "+ioe.getMessage());
            //throw new XmlWritingException(ioe);
        }
        return this;
    }


    public XmlWriter changeLine() {//throws WritingException {
        try {
            /*if(this.stack.empty())
            {
            	System.out.println("error XmlWriter.changeLine Called changeLine too many times.");
                //throw new XmlWritingException("Called endEntity too many times. ");
            }
            String name = (String)this.stack.pop();
            if (name != null)
            {*/

                 this.writer.write("\n");
       
                this.empty = false;
            //}
            
        } catch (IOException ioe)
        {
        	System.out.println("error XmlWriter.changeLine "+ioe.getMessage());
            //throw new XmlWritingException(ioe);
        }
        return this;
    }

    /**
     * Close this writer. It does not close the underlying 
     * writer, but does throw an exception if there are 
     * as yet unclosed tags.
     */
    public void close(){//throws WritingException {
        if(!this.stack.empty()) 
        {
        	System.out.println("error XmlWriter.close "+"Tags are not all closed. Possibly, "+this.stack.pop()+" is unclosed. ");
           // throw new XmlWritingException("Tags are not all closed. Possibly, "+this.stack.pop()+" is unclosed. ");
        }
    }

    /**
     * Output body text. Any xml characters are escaped. 
     */
    public XmlWriter writeText(String text){ //throws WritingException {
        try {
            closeOpeningTag();
            this.empty = false;
            this.writer.write(escapeXml(text));
            
        } catch (IOException ioe)
        {
        	System.out.println("error XmlWriter.writeText "+ioe.getMessage());
            //throw new XmlWritingException(ioe);
        }
        return this;
    }

    // Static functions lifted from generationjava helper classes
    // to make the jar smaller.
    
    // from XmlW
    static public String escapeXml(String str) {
        str = replaceString(str,"&","&amp;");
        str = replaceString(str,"<","&lt;");
        str = replaceString(str,">","&gt;");
        str = replaceString(str,"\"","&quot;");
        str = replaceString(str,"'","&apos;");
        return str;
    }  

    // from StringW
    static public String replaceString(String text, String repl, String with) {
        return replaceString(text, repl, with, -1);
    }  
    /**
     * Replace a string with another string inside a larger string, for
     * the first n values of the search string.
     *
     * @param text String to do search and replace in
     * @param repl String to search for
     * @param with String to replace with
     * @param n    int    values to replace
     *
     * @return String with n values replacEd
     */
    static public String replaceString(String text, String repl, String with, int max) {
        if(text == null) {
            return null;
        }
 
        StringBuffer buffer = new StringBuffer(text.length());
        int start = 0;
        int end = 0;
        while( (end = text.indexOf(repl, start)) != -1 ) {
            buffer.append(text.substring(start, end)).append(with);
            start = end + repl.length();
 
            if(--max == 0) {
                break;
            }
        }
        buffer.append(text.substring(start));
 
        return buffer.toString();
    }              

    // Two example methods. They should output the same XML:
    // <person name="fred" age="12"><phone>425343</phone><bob/></person>
    static public void main(String[] args){ //throws WritingException {
        test1();
        test2();
    }
    static public void test1(){//throws WritingException { throws WritingException {
        Writer writer = new java.io.StringWriter();
        XmlWriter xmlwriter = new XmlWriter(writer);
        xmlwriter.writeEntity("person").writeAttribute("name", "fred").writeAttribute("age", "12").writeEntity("phone").writeText("4254343").endEntity().writeEntity("bob").endEntity().endEntity();
        xmlwriter.close();
        System.err.println(writer.toString());
    }
    static public void test2(){ //throws WritingException { throws WritingException {
        Writer writer = new java.io.StringWriter();
        XmlWriter xmlwriter = new XmlWriter(writer);
        
        xmlwriter.writeEntity("mass");
        xmlwriter.writeEntity("people");
        xmlwriter.writeEntity("person");
        //xmlwriter.writeEntity("name");
        xmlwriter.writeEntity("name");
        xmlwriter.writeText("fred");
        xmlwriter.endEntity();
        //xmlwriter.writeEntity("person");
        xmlwriter.writeEntity("age");
        xmlwriter.writeText("10");
        //xmlwriter.endEntity();
        //xmlwriter.endEntity();        
        xmlwriter.endEntity();
        xmlwriter.endEntity();
        xmlwriter.writeEntity("person");
        //xmlwriter.writeEntity("name");
        xmlwriter.writeEntity("name");
        xmlwriter.writeText("fak");
        xmlwriter.endEntity();
        xmlwriter.writeEntity("age");
        xmlwriter.writeText("82");
        xmlwriter.endEntity();
        //xmlwriter.endEntity();
        xmlwriter.endEntity();// person
        //xmlwriter.endEntity();
       // xmlwriter.writeEntity("phone2");
       // xmlwriter.writeText("2222");
       // xmlwriter.endEntity();        
        //xmlwriter.writeEntity("bob");
        //xmlwriter.endEntity();
        //xmlwriter.endEntity();
        xmlwriter.endEntity();// people
        
        xmlwriter.writeEntity("people");
        xmlwriter.writeEntity("person");
        //xmlwriter.writeEntity("name");
        xmlwriter.writeEntity("name");
        xmlwriter.writeText("toki");
        xmlwriter.endEntity();
        //xmlwriter.writeEntity("person");
        xmlwriter.writeEntity("age");
        xmlwriter.writeText("18");
        //xmlwriter.endEntity();
        //xmlwriter.endEntity();        
        xmlwriter.endEntity();
        xmlwriter.endEntity();
        xmlwriter.writeEntity("person");
        //xmlwriter.writeEntity("name");
        xmlwriter.writeEntity("name");
        xmlwriter.writeText("alli");
        xmlwriter.endEntity();
        xmlwriter.writeEntity("age");
        xmlwriter.writeText("53");
        xmlwriter.endEntity();
        //xmlwriter.endEntity();
        xmlwriter.endEntity();// person
        //xmlwriter.endEntity();
       // xmlwriter.writeEntity("phone2");
       // xmlwriter.writeText("2222");
       // xmlwriter.endEntity();        
        //xmlwriter.writeEntity("bob");
        //xmlwriter.endEntity();
        //xmlwriter.endEntity();
        xmlwriter.endEntity();// people        
        
        xmlwriter.endEntity();// mass
        xmlwriter.close();
        System.err.println(writer.toString());
    }

}

