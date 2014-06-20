/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util.map;

import java.beans.PropertyChangeSupport;

/**
 *
 * @author Wilson
 */
public class GoogleCoordinate {
    public static final String PROP_LATITUDE = "PROP_LATITUDE";
    public static final String PROP_LONGITUDE = "PROP_LONGITUDE";
    private Double latitude;
    private Double longitude;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    public GoogleCoordinate(){
        this.latitude = new Double(0);
        this.longitude = new Double(0);
    }
    
    public GoogleCoordinate(double latitude, double longitude){
        this();
        this.latitude = new Double(latitude);
        this.longitude = new Double(longitude);
    }
    
    public GoogleCoordinate(Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        java.lang.Double oldLatitude = this.latitude;
        this.latitude = latitude;
        propertyChangeSupport.firePropertyChange(PROP_LATITUDE, oldLatitude, latitude);
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        java.lang.Double oldLongitude = this.longitude;
        this.longitude = longitude;
        propertyChangeSupport.firePropertyChange(PROP_LONGITUDE, oldLongitude, longitude);
    }
    
 
    @Override
    public String toString(){
        return latitude.toString() + "," + longitude.toString();
    }
}
