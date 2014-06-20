/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util.map;

/**
 *
 * @author Wilson
 */
public class GeoCoordinate extends GoogleCoordinate{
    
    public GeoCoordinate(){
        super(new Double(0), new Double(0));
    }
    
    public GeoCoordinate(Double latitude, Double longitude){
        super(latitude, longitude);
    }
    
}
