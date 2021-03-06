/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.xml.parsers.ParserConfigurationException;
import org.jw.service.entity.Contact;
import org.jw.service.entity.DirectionMap;
import org.jw.service.entity.LocationMap;
import org.jw.service.entity.MeetingPlace;
import org.jw.service.entity.Territory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Wilson
 */
public class UtilityDownload {
    public static final String DIRECTION_API_URL = "https://maps.googleapis.com/maps/api/directions/%s?origin=%f,%f&destination=%f,%f&mode=%s&sensor=false";
    public static final String MAP_URL = "https://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=%d&size=%dx%d&scale=%d&maptype=%s&format=%s&sensor=false&markers=color:%s|%f,%f";
    public static final String GOOGLE_STATIC_MAP_HOSTNAME = "maps.googleapis.com";    
    public static final String CONNECTIVITY_TEST_HOSTNAME = "https://developers.google.com/maps/";
    public static final String MAP_DIRECTION_IMAGE_URL = "https://maps.googleapis.com/maps/api/staticmap?zoom=%d&size=%sx%s&scale=%d&path=color:%s%s&markers=color:red|size:mid%s&sensor=false";
    public static final String PROXIMITY_MAP_IMAGE_URL = "http://maps.googleapis.com/maps/api/staticmap?size=522x350&maptype=roadmap%s&sensor=false";
    
    public static UtilityDownload create() {
        return new UtilityDownload();
    }
    
    private UtilityDownload(){}
    
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
    
    public boolean isAPIReachable(){
        boolean result = true;
        
        try {
            URLConnection urlConn = new URL(CONNECTIVITY_TEST_HOSTNAME).openConnection();
            
        } catch (IOException ex) {
            result = false;
            Logger.getLogger(UtilityDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return result;
    }
    
    public String getDirection(DirectionMap directionMap) throws MalformedURLException, IOException{                
        String directionUrl = String.format(DIRECTION_API_URL,
                                            "xml",
                                            directionMap.getMeetingPlaceId().getLatitude().floatValue(),
                                            directionMap.getMeetingPlaceId().getLongitude().floatValue(),
                                            directionMap.getLocationMapId().getLatitude().floatValue(),
                                            directionMap.getLocationMapId().getLongitude().floatValue(),
                                            directionMap.getTravelMode());
        
        
        URLConnection conn = new URL(directionUrl).openConnection();        
        
        StringBuilder stringBuilder = new StringBuilder();
        
        try (InputStream is = conn.getInputStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")), 8);
            
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");                
            }
        }
        
        return stringBuilder.toString();
    }
    
    public ImageIcon getDirectionMapImage(DirectionMap directionMap) throws MalformedURLException, IOException, SAXException, ParserConfigurationException{
        Document xmlDocument;
        UtilityGoogleDirectionXML xmlUtility;
        String pathString;
        String markers;
        
        xmlUtility = UtilityGoogleDirectionXML.getInstance();
        xmlDocument = xmlUtility.loadXMLFromString(directionMap.getDirection());
        pathString = xmlUtility.createPathString(xmlDocument);
        markers = xmlUtility.getMarkers(xmlDocument);
        
        String finalURL = String.format(MAP_DIRECTION_IMAGE_URL,
                                        directionMap.getZoom(),
                                        directionMap.getWidth(),
                                        directionMap.getHeight(),
                                        directionMap.getScale(),
                                        directionMap.getPathColor(),    
                                        pathString,
                                        markers);        
        URLConnection connection = new URL(finalURL).openConnection();
        BufferedImage bufferedImage = null;
        try (InputStream inputStream = connection.getInputStream()) {
            bufferedImage = ImageIO.read(inputStream);
        }
        
        return new ImageIcon(bufferedImage);
    }
    
    
    public byte[] getDirectionMapAsBytes(DirectionMap directionMap) throws MalformedURLException, IOException, SAXException, ParserConfigurationException{
        Document xmlDocument;
        UtilityGoogleDirectionXML xmlUtility;
        String pathString;
        String markers;
        byte[] imageByte = null;
        
        xmlUtility = UtilityGoogleDirectionXML.getInstance();
        xmlDocument = xmlUtility.loadXMLFromString(directionMap.getDirection());
        pathString = xmlUtility.createPathString(xmlDocument);
        markers = xmlUtility.getMarkers(xmlDocument);
        
        String finalURL = String.format(MAP_DIRECTION_IMAGE_URL,
                                        directionMap.getZoom(),
                                        directionMap.getWidth(),
                                        directionMap.getHeight(),
                                        directionMap.getScale(),
                                        directionMap.getPathColor(),    
                                        pathString,
                                        markers);        
        URLConnection connection = new URL(finalURL).openConnection();
        BufferedImage bufferedImage = null;
        try (InputStream inputStream = connection.getInputStream()) {
            bufferedImage = ImageIO.read(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            imageByte = baos.toByteArray();
        }
        
        return imageByte;
    }
    
    public byte[] downloadProximityMapAsBytes(Territory territory) throws MalformedURLException, IOException{
        byte[] imageBytes = null;
        String markers = "";
        String marker = "";
        
        for(Contact contact : territory.getContactCollection()){            
            if(contact.getLocationMapId() != null){
                LocationMap locationMap;
                locationMap = contact.getLocationMapId();
                marker = createMarker(locationMap);
                if(marker != null) markers = markers.concat(marker);
            }
        }
        
        marker = createMarker(territory.getMeetingPlaceId());
        if(marker != null) markers = markers.concat(marker);
        
        String finalURL = String.format(PROXIMITY_MAP_IMAGE_URL, markers);
        //System.out.println(finalURL);
        URLConnection connection = new URL(finalURL).openConnection();
        BufferedImage bufferedImage = null;
        try (InputStream inputStream = connection.getInputStream()) {
            bufferedImage = ImageIO.read(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            imageBytes = baos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(UtilityDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return imageBytes;
    }
    
    private String createMarker(LocationMap locationMap){
        if(locationMap.getLatitude() == null || locationMap.getLongitude() == null)return null;
        return String.format("&markers=color:%s|label:%s|%f,%f", 
                             locationMap.getMarkerColor(), 
                             locationMap.getMarkerLabel(), 
                             locationMap.getLatitude(),
                             locationMap.getLongitude());
    }
    
    private String createMarker(MeetingPlace meetingPlace){
        if(meetingPlace == null)return null;
        return String.format("&markers=color:%s|label:%s|%f,%f",                             
                             meetingPlace.getTerritoryId().getMarkerColor(),
                             "",
                             meetingPlace.getLatitude(),
                             meetingPlace.getLongitude());
    }
}
    