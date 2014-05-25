/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.jw.service.entity.LocationMap;

/**
 *
 * @author Wilson
 */
public class UtilityDownload {
    public final static String MAP_URL = "https://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=%d&size=%dx%d&scale=%d&maptype=%s&format=%s&sensor=false&markers=color:%s|%f,%f";
    public final static String GOOGLE_STATIC_MAP_HOSTNAME = "maps.googleapis.com";
    private InetAddress apiInetAddress = null;
    
    public static UtilityDownload create() {
        return new UtilityDownload();
    }
    
    private UtilityDownload(){
        try {
            apiInetAddress = InetAddress.getByName(GOOGLE_STATIC_MAP_HOSTNAME);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UtilityDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String createUrl(LocationMap locationMap){
        return String.format(MAP_URL,
                            locationMap.getLatitude(),
                            locationMap.getLongitude(),
                            locationMap.getZoom(),
                            locationMap.getWidth(),
                            locationMap.getHeight(),
                            locationMap.getScale(),
                            locationMap.getMapType(),
                            locationMap.getImageFormat(),
                            locationMap.getMarkerColor(),
                            locationMap.getLatitude(),
                            locationMap.getLongitude());
    }
    
    public ImageIcon downloadMapAsImageIcon(String url){        
        BufferedImage bufferedImage = null;
        
        try {
            URLConnection connection = new URL(url).openConnection();
            try (InputStream inputStream = connection.getInputStream()) {
                bufferedImage = ImageIO.read(inputStream);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(UtilityDownload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilityDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ImageIcon(bufferedImage);
    }
    
    /**
     *
     * @param url
     * @return
     */
    public byte[] downloadMapAsBytes(String url){        
         byte[] imageByte = null;
        
        try {
            URLConnection connection = new URL(url).openConnection();
            try (InputStream inputStream = connection.getInputStream()) {
                BufferedImage bufferedImage = ImageIO.read(inputStream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                baos.flush();
                imageByte = baos.toByteArray();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(UtilityDownload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilityDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return imageByte;
    }
    
    public boolean isAPIReachable() throws IOException, InterruptedException{
        boolean result = true;
        return result;
    }
}
