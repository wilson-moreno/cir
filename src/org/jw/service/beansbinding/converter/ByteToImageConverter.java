/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.beansbinding.converter;

import javax.swing.ImageIcon;
import org.jdesktop.beansbinding.Converter;
import org.jw.service.util.UtilityImageIcon;

/**
 *
 * @author Wilson
 */
public class ByteToImageConverter extends Converter<byte[], ImageIcon>{

    public static ByteToImageConverter create() {
        return new ByteToImageConverter();
    }

    private ByteToImageConverter(){}
    
    @Override
    public ImageIcon convertForward(byte[] value) {
        return new javax.swing.ImageIcon(value);
    }

    @Override
    public byte[] convertReverse(ImageIcon value) {
        return UtilityImageIcon.create().imageIconToByteArray(value);
    }
    
}




