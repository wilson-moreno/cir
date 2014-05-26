/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Wilson
 */
public class UtilityImageIcon {
    private final String IMAGE_DIRECTORY = "/org/jw/service/gui/resources/icon/";

    public static UtilityImageIcon create() {
        return new UtilityImageIcon();
    }
    
    private UtilityImageIcon(){}
    
    
    public byte[] imageIconToByteArray(javax.swing.ImageIcon imageIcon){        
        ByteArrayOutputStream  bos = new ByteArrayOutputStream();
        BufferedImage bi = new BufferedImage(imageIcon.getIconWidth(),imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new java.awt.RenderingHints(java.awt.RenderingHints.KEY_RENDERING,
                java.awt.RenderingHints.VALUE_RENDER_QUALITY));
        boolean b = g2d.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight(), null);
        try { 
            ImageIO.write(bi, "png", bos);
        } catch (IOException ex) {
            Logger.getLogger(UtilityImageIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bos.toByteArray();
    }
    
    public ImageIcon scaleImage(ImageIcon source, double factor){
        int w = (int)(source.getIconWidth() * factor);
        int h = (int)(source.getIconHeight() * factor);
        int type = BufferedImage.TYPE_INT_ARGB;
        BufferedImage destination = new BufferedImage(w, h, type);
        Graphics2D g2 = destination.createGraphics();
        g2.drawImage(source.getImage(), 0, 0, w, h, null);
        g2.dispose();
        
        return new ImageIcon(destination);
    }
    
    public ImageIcon fitImageToLabel(JLabel label, ImageIcon source){
        int width = label.getWidth();
        int height = label.getHeight();
        int type = BufferedImage.TYPE_INT_ARGB;        
        BufferedImage target = new BufferedImage(width, height, type);
        Graphics2D g2 = target.createGraphics();
        g2.drawImage(source.getImage(), 0, 0, width, height, null);
        g2.dispose();        
        return new ImageIcon(target);
    }
    
    public ImageIcon getIcon(String iconName){
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(IMAGE_DIRECTORY + iconName));
            return new ImageIcon(img);
        } catch (IOException ex) {
            Logger.getLogger(UtilityImageIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
