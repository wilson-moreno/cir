/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.factory;

import java.util.ArrayList;
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
            param.setName(parameter.getName());
            param.setLabel(parameter.getLabel());
            param.setDataType(parameter.getDataType());
            if(parameter.getDefaultValue() != null)param.setValue(convert(parameter.getDefaultValue(), parameter.getDataType()));
            parameterList.add(param);
        }
        
        return parameterList;
    }
    
    private Object convert(String value, String dataType){
        Object newValue;
        
        switch(dataType){
            case "String" : newValue = value;
            case "Integer" : newValue = Integer.parseInt(value);
            case "Doulbe" : newValue = Double.parseDouble(value);
            default : newValue = value;
        }
        
        return newValue;
    }
    
    public List<PrintParameter> getParameterList(){
        return createList();
    }
}
