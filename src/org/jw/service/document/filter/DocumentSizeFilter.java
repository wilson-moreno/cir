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

/**
 *
 * @author Wilson
 */
public class DocumentSizeFilter extends DocumentFilter{
    private int maxCharacters;
    private boolean DEBUG = false;
    
    public DocumentSizeFilter(int maxCharacters){
        this.maxCharacters = maxCharacters;
    }
    
    @Override
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
        throws BadLocationException{
        if(DEBUG)System.out.println("in DocumentsSizeFilter's insertString method");
        
        if((fb.getDocument().getLength() + str.length()) <= this.maxCharacters)
            super.insertString(fb, offs, str, a);
        else
            Toolkit.getDefaultToolkit().beep();
    }
    
    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
        throws BadLocationException{
        if(DEBUG)System.out.println("in DocumentsSizeFilter's replace method");
        
        if((fb.getDocument().getLength() + str.length() - length) <= this.maxCharacters)
            super.replace(fb, offs, length, str, a);
        else
            Toolkit.getDefaultToolkit().beep();
    }
}
