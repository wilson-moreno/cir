/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.document.filter;

import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;


/**
 *
 * @author Wilson
 */
public class DocumentSizeFilterToolkit {
    private static DocumentSizeFilterToolkit toolkit;
    
    public static DocumentSizeFilterToolkit getToolkit(){
        if(toolkit == null){
            toolkit = new DocumentSizeFilterToolkit();
            return toolkit;
        } else {
            return toolkit;
        }
    }
    
    public void setSizeFilter(JTextComponent component, int size){
        DocumentSizeFilter sizeFilter;
        AbstractDocument abstractDocument;
        Document document;
        
        sizeFilter = new DocumentSizeFilter(size);        
        document = component.getDocument();
        abstractDocument = (AbstractDocument) document;
        abstractDocument.setDocumentFilter(sizeFilter);
    }
}
