/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.document.filter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.Toolkit;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Wilson
 */
public class DocumentSizeFilter extends DocumentFilter{
    public static final String PROP_MAXCHARACTERS = "maxCharacters";
    private int maxCharacters;
    private boolean DEBUG = false;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    
    public DocumentSizeFilter(){
        this(30);
    }
    
    public DocumentSizeFilter(int maxCharacters){
        this.maxCharacters = maxCharacters;
    }
    
    @Override
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
        throws BadLocationException{
        if(DEBUG)System.out.println("in DocumentsSizeFilter's insertString method");
        
        if((fb.getDocument().getLength() + str.length()) <= this.getMaxCharacters())
            super.insertString(fb, offs, str, a);
        else
            Toolkit.getDefaultToolkit().beep();
    }
    
    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
        throws BadLocationException{
        if(DEBUG)System.out.println("in DocumentsSizeFilter's replace method");
        
        if((fb.getDocument().getLength() + str.length() - length) <= this.getMaxCharacters())
            super.replace(fb, offs, length, str, a);
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
