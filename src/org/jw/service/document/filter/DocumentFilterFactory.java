/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.document.filter;

/**
 *
 * @author Wilson
 */
public class DocumentFilterFactory {

    public PlainDocumentSizeFilter getSizeFilter2(){
        return new PlainDocumentSizeFilter(2);
    }
    
    public PlainDocumentSizeFilter getSizeFilter3(){
        return new PlainDocumentSizeFilter(3);
    }
    
    public PlainDocumentSizeFilter getSizeFilter5(){
        return new PlainDocumentSizeFilter(5);
    }
    
    public PlainDocumentSizeFilter getSizeFilter10(){
        return new PlainDocumentSizeFilter(10);
    }
    
    public PlainDocumentSizeFilter getSizeFilter15(){
        return new PlainDocumentSizeFilter(15);
    }
    
    public PlainDocumentSizeFilter getSizeFilter20(){
        return new PlainDocumentSizeFilter(20);
    }
    
    public PlainDocumentSizeFilter getSizeFilter30(){
        return new PlainDocumentSizeFilter(30);
    }
    
    public PlainDocumentSizeFilter getSizeFilter50(){
        return new PlainDocumentSizeFilter(50);
    }
    
    public PlainDocumentSizeFilter getSizeFilter75(){
        return new PlainDocumentSizeFilter(75);
    }
    
    public PlainDocumentSizeFilter getSizeFilter150(){
        return new PlainDocumentSizeFilter(150);
    }
    
    public PlainDocumentSizeFilter getSizeFilter200(){
        return new PlainDocumentSizeFilter(200);
    }
    
    public PlainDocumentSizeFilter getSizeFilter100(){
        return new PlainDocumentSizeFilter(100);
    }
}
