/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Wilson
 */
public class UtilityImageIcon {

    public static UtilityImageIcon create() {
        return new UtilityImageIcon();
    }
    
    private UtilityImageIcon(){}
    
    
    public byte[] imageIconToByteArray(javax.swing.ImageIcon imageIcon){        
        ByteArrayOutputStream  bos = new ByteArrayOutputStream();
        BufferedImage bi = new BufferedImage(imageIcon.getIconWidth(),imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new java.awt.RenderingHints(java.awt.RenderingHints.KEY_RENDERING,
                java.awt.RenderingHints.VALUE_RENDER_QUALITY));
        boolean b = g2d.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight(), null);
        try { 
            ImageIO.write(bi, "jpeg", bos);
        } catch (IOException ex) {
            Logger.getLogger(UtilityImageIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bos.toByteArray();
    }
}
