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
            param.setSequence(parameter.getSequence());
            param.setName(parameter.getName());
            param.setLabel(parameter.getLabel());
            param.setDataType(parameter.getDataType());
            param.setParameterType(parameter.getParameterType());
            if(parameter.getDefaultValue() != null && parameter.getDefaultValue().trim().equals(""))param.setValue(convert(parameter.getDefaultValue(), parameter.getDataType()));
            parameterList.add(param);
        }
        
        return parameterList;
    }
    
    private Object convert(String value, String dataType){
        Object newValue;
        
        switch(dataType){
            case "String" : newValue = value; break;
            case "Integer" : newValue = Integer.parseInt(value); break;
            case "Double" : newValue = Double.parseDouble(value); break;
            default : newValue = value;
        }
        
        return newValue;
    }
    
    public List<PrintParameter> getParameterList(){
        return createList();
    }
}
