/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jw.service.util.map.GeoCoordinate;
import org.jw.service.util.map.GoogleCoordinate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



/**
 *
 * @author Wilson
 */
public class UtilityGoogleDirectionXML {

    
    public Document loadXMLFromString(String xml) throws ParserConfigurationException, SAXException, IOException{
        
        StringReader stringReader = new StringReader(xml);
        InputSource inputSource = new InputSource(stringReader);
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document xmlDocument = documentBuilder.parse(inputSource);
        
        return xmlDocument;
    }
    
    public static UtilityGoogleDirectionXML getInstance(){
        return GoogleDirectionXMLUtilHolder.INSTANCE;
    }
    
    private static class GoogleDirectionXMLUtilHolder {
        private static final UtilityGoogleDirectionXML INSTANCE = new UtilityGoogleDirectionXML();
    }
    
    
    
    public String createPathString(Document xmlDocument){
        String stringPath = new String();
        NodeList nodeList1 = null;
        ArrayList<GeoCoordinate> coordinateList = null;
        
        nodeList1 = xmlDocument.getElementsByTagName("overview_polyline");       
        
        for(int i = 0; i < nodeList1.getLength(); i++){
            Node node1 = nodeList1.item(i);
            if(node1.getNodeName().equalsIgnoreCase("overview_polyline")){
                NodeList nodeList2 = node1.getChildNodes();
                
                for(int j = 0; j < nodeList2.getLength(); j++){
                    Node node2 = nodeList2.item(j);
                    if(node2.getNodeName().equalsIgnoreCase("points")){
                        NodeList nodeList3 = node2.getChildNodes();
                        for(int k = 0; k < nodeList3.getLength(); k++){
                            Node node3 = nodeList3.item(k);
                            if(node3.getNodeName().equalsIgnoreCase("#text"))
                            coordinateList = decodePolyline(node3.getNodeValue());
                        }
                    }
                }
                
            }
        }
        
        for(GeoCoordinate p : coordinateList){
            stringPath += "|" + p.toString();
        }
       
        return stringPath;
    }
    
    public String getMarkers(Document xmlDocument){            
        GoogleCoordinate startLocation = new GoogleCoordinate();
        GoogleCoordinate endLocation = new GoogleCoordinate();
        NodeList nodeList = xmlDocument.getElementsByTagName("leg");
        
        for(int i = 0; i < nodeList.getLength(); i++){
            Node node1 = nodeList.item(i);
            NodeList childNodes = node1.getChildNodes();
            for(int j = 0; j < childNodes.getLength(); j++){
                Node node2 = childNodes.item(j);
                switch(node2.getNodeName()){
                    case "start_location": startLocation = getCoordinates(node2.getChildNodes());break;
                    case "end_location": endLocation = getCoordinates(node2.getChildNodes());break;    
                }
            }
        }
        
        return "|" + startLocation.toString() + "|" + endLocation.toString();
    }
    
    public GoogleCoordinate getCenterCoordinate(Document xmlDocument){
        NodeList nodeList = xmlDocument.getElementsByTagName("bounds");
        GoogleCoordinate southWest = new GoogleCoordinate();
        GoogleCoordinate northEast = new GoogleCoordinate();
        
        for(int i = 0; i < nodeList.getLength(); i++){
            Node node1 = nodeList.item(i);
            if(node1.getNodeName().equalsIgnoreCase("bounds")){
                NodeList childNodes1 = node1.getChildNodes();
                for(int j = 0; j < childNodes1.getLength(); j++){
                    Node node2 = childNodes1.item(j);
                    switch(node2.getNodeName()){
                        case "southwest" : southWest = getCoordinates(node2.getChildNodes());break;
                        case "northeast" : northEast = getCoordinates(node2.getChildNodes());break;    
                    }
                }
            }
        }
        
        Double latitude = new Double(southWest.getLatitude()+((northEast.getLatitude()-southWest.getLatitude()) / 2));
        Double longitude = new Double(southWest.getLongitude() + ((northEast.getLongitude()-southWest.getLongitude()) / 2));
        
        return new GoogleCoordinate(latitude, longitude);
    }
    
    private GoogleCoordinate getCoordinates(NodeList childNodes){
        GoogleCoordinate coordinates = new GoogleCoordinate();
        
        for(int i = 0; i < childNodes.getLength(); i++){
            Node node = childNodes.item(i);
            switch(node.getNodeName()){
                case "lat" : coordinates.setLatitude(getValues(node.getChildNodes()));break;
                case "lng" : coordinates.setLongitude(getValues(node.getChildNodes()));break;
            }                        
        }
        
        return coordinates;
    }
    
    private Double getValues(NodeList childNodes){
        Double value = new Double(0);
        
        for(int i = 0; i < childNodes.getLength(); i++){
            Node node = childNodes.item(i);            
            value = new Double(String.valueOf(node.getNodeValue()));
        }
        
        return value;
    }
           
    
    
    public ArrayList<GeoCoordinate> decodePolyline(String encodedPath){
        ArrayList<GeoCoordinate> decodedPath = new ArrayList<GeoCoordinate>();
        
        int index = 0;
        int length = encodedPath.length();
        int latitude = 0;
        int longitude = 0;
        
        while(index < length){
            int b;
            int shift = 0;
            int result = 0;
            
            do{
                b = encodedPath.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while(b >= 0x20);
            
            int dlatitude = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            latitude += dlatitude;
            shift = 0; 
            result = 0;
            
            do{
                b = encodedPath.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            }while(b >= 0x20);
            
            int dlongitude = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            longitude += dlongitude;
            
            Double pLatitude = (double) latitude / 1E5;
            Double pLongitude = (double) longitude / 1E5;
            GeoCoordinate p = new GeoCoordinate(pLatitude, pLongitude);
            decodedPath.add(p);
        }
        
        return decodedPath;
    }
}
