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
            param.setLabel(parameter.getLabel());
            parameterList.add(param);
        }
        
        return parameterList;
    }
    
    public List<PrintParameter> getParameterList(){
        return createList();
    }
}
