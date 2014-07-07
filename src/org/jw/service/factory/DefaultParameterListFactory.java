/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.factory;

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
            //if(parameter.getDefaultValue() != null && !parameter.getDefaultValue().trim().equals(""))param.setValue(convert(parameter.getDefaultValue(), parameter.getDataType()));
            parameterList.add(param);
        }
        
        Collections.sort(parameterList);
        
        return parameterList;
    }
    
    private Object convert(String value, String dataType){
        Object newValue;
        
        switch(dataType){
            case "String" : newValue = value; break;
            case "Integer" : newValue = parseInteger(value); break;
            case "Double" : newValue = parseDouble(value); break;
            default : newValue = value;
        }
        
        return newValue;
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
