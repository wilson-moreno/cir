/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.document.filter;

import java.awt.Toolkit;
import java.beans.PropertyChangeSupport;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Wilson
 */
public class PlainDocumentSizeFilter extends PlainDocument{
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_MAXCHARACTERS = "PROP_MAXCHARACTERS";
    private int maxCharacters;
    private boolean DEBUG = false;
    
    
    public PlainDocumentSizeFilter(){
        this(30);
    }
    
    public PlainDocumentSizeFilter(int maxCharacters){
        this.maxCharacters = maxCharacters;
    }
    
    @Override
    public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
        if(DEBUG)System.out.println("in DocumentsSizeFilter's insertString method");
        if (text == null)return;

        if ((getLength() + text.length()) <= getMaxCharacters()) {
            super.insertString(offset, text, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if(DEBUG)System.out.println("in DocumentsSizeFilter's replace method");
        if (text == null)return;
        
        if((getLength() + text.length() - length) <= this.getMaxCharacters())
            super.replace(offset, length, text, attrs);
        else
            Toolkit.getDefaultToolkit().beep();
    }
    
    

    /**
     * @return the maxCharacters
     */
    public int getMaxCharacters() {
        return maxCharacters;
    }

    /**
     * @param maxCharacters the maxCharacters to set
     */
    public void setMaxCharacters(int maxCharacters) {
        int oldMaxCharacters = this.maxCharacters;
        this.maxCharacters = maxCharacters;
        propertyChangeSupport.firePropertyChange(PROP_MAXCHARACTERS, oldMaxCharacters, maxCharacters);
    }
    
}
