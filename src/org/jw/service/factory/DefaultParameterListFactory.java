/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.factory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jw.service.entity.AppsReportParameter;
import org.jw.service.print.PrintParameter;

/**
 *
 * @author Wilson
 */
public class DefaultParameterListFactory {

    public static List<PrintParameter> create(List<AppsReportParameter> list) {
        DefaultParameterListFactory factory = new DefaultParameterListFactory(list);
        return factory.createList();
    }
    private final List<AppsReportParameter> list;
    
    private DefaultParameterListFactory(List<AppsReportParameter> list){
        this.list = list;
    }
    
    private List<PrintParameter> createList(){
        List<PrintParameter> parameterList = new ArrayList<>();        
        
        for(AppsReportParameter parameter : list){
            if(!parameter.getEnable())continue;
            PrintParameter param = new PrintParameter();
            param.setSequence(parameter.getSequence());
            param.setName(parameter.getName());
            param.setLabel(parameter.getLabel());
            param.setDataType(parameter.getDataType());
            param.setParameterType(parameter.getParameterType());
            param.setControlType(parameter.getControlType());
            param.setQueryText(parameter.getQueryTextId());
            param.setDisplayColumn(parameter.getDisplayColumn());
            param.setValueColumn(parameter.getValueColumn());
            param.setRequired(parameter.getRequired());            
            //if(parameter.getDefaultValue() != null && !parameter.getDefaultValue().trim().equals(""))param.setValue(convert(parameter.getDefaultValue(), parameter.getDataType()));
            parameterList.add(param);
        }
        
        Collections.sort(parameterList);
        
        return parameterList;
    }
    
    private Object convert(String value, String dataType){
        Object newValue;
        
        switch(dataType){
            case "Boolean" : newValue = parseBoolean(value); break;
            case "Character" : newValue = parseCharacter(value); break;
            case "Date" : newValue = parseDate(value); break;
            case "Double" : newValue = parseDouble(value); break;
            case "Float" : newValue = parseFloat(value); break;
            case "Integer" : newValue = parseInteger(value); break;            
            case "Long" : newValue = parseLong(value); break;
            case "Short" : newValue = parseShort(value); break;
            case "String" : newValue = value; break;
            
            default : newValue = value;
        }
        
        return newValue;
    }
    
    private Short parseShort(String shortValue){
        try{
            return Short.parseShort(shortValue);
        }catch(NumberFormatException ex){
            return new Short((short)0);
        }
    }
    
    private Long parseLong(String longValue){
        try{
            return Long.parseLong(longValue);
        }catch(NumberFormatException ex){
            return new Long(0);
        }
    }
    
    private Float parseFloat(String floatValue){
        try{
            return Float.parseFloat(floatValue);
        }catch(NumberFormatException ex){
            return new Float(0.0f);
        }
    }
    
    private java.util.Date parseDate(String dateValue){
        try{
            DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
            return dateFormat.parse(dateValue);
        }catch(ParseException ex){
            return new java.util.Date();
        }    
    }
    
    private Character parseCharacter(String characterValue){
        return characterValue.charAt(0);
    }
    
    private Boolean parseBoolean(String booleanValue){        
        return Boolean.parseBoolean(booleanValue);        
    }
    
    private Double parseDouble(String doubleValue){        
        try{
            return Double.parseDouble(doubleValue);
        }catch(NumberFormatException ex){
            return new Double(0.0);
        }        
    }
    
    private Integer parseInteger(String integer){        
        try{
            return Integer.parseInt(integer);
        }catch(NumberFormatException ex){
            return new Integer(0);
        }        
    }
    
    public List<PrintParameter> getParameterList(){
        return createList();
    }
}
