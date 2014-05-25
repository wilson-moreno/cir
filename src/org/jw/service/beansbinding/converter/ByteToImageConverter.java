/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.beansbinding.converter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.jdesktop.beansbinding.Converter;
import org.jw.service.util.UtilityImageIcon;

/**
 *
 * @author Wilson
 */
public class ByteToImageConverter extends Converter<byte[], ImageIcon>{
    private static JLabel label;
    
    public static ByteToImageConverter create() {
        return new ByteToImageConverter();
    }
    
    public static ByteToImageConverter create(JLabel label) {
        return new ByteToImageConverter(label);
    }

    private ByteToImageConverter(){ label = null; }
    private ByteToImageConverter(JLabel label){
        this.label = label;
    }
    
    @Override
    public ImageIcon convertForward(byte[] value) {
        if(label != null){
            return UtilityImageIcon.create().fitImageToLabel(label, new javax.swing.ImageIcon(value));
        }else{
            return new javax.swing.ImageIcon(value);
        }
    }

    @Override
    public byte[] convertReverse(ImageIcon value) {
        return UtilityImageIcon.create().imageIconToByteArray(value);
    }
    
}




