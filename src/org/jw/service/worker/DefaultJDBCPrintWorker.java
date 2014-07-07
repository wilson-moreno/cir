/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.awt.Frame;
import java.awt.Window;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import org.jw.service.entity.AppsReport;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.print.ListOption;
import org.jw.service.print.PrintParameter;

/**
 *
 * @author Wilson
 */
public class DefaultJDBCPrintWorker extends SwingWorker<JasperPrint, String>{
    private final AppsReport report;
    private final List<PrintParameter> paramList;
    private final Connection connection;
    private final InputStream inputStream;
    private final Window parent;
    
    public DefaultJDBCPrintWorker(Window parent, AppsReport report, List<PrintParameter> paramList, Connection connection, InputStream inputStream, DefaultTaskListener taskListener){
        this.parent = parent;
        this.report = report;
        this.paramList = paramList;
        this.connection = connection;
        this.inputStream = inputStream;
        this.addPropertyChangeListener(taskListener);
    }
    
    @Override
    protected JasperPrint doInBackground() throws Exception {        
        Map<String, Object> paramMap = createParameterMap(report, paramList);        
        JasperPrint jasperPrint;
        jasperPrint = JasperFillManager.fillReport(inputStream, paramMap, connection);        
        return jasperPrint;
    }
    
    private PreparedStatement createPreparedStatement(String query, List<PrintParameter> paramList) throws SQLException{
        Collections.sort(paramList);
        PreparedStatement statement = connection.prepareStatement(query);
        for(PrintParameter param : paramList){            
            if(param.getParameterType().trim().equalsIgnoreCase("Query"))
                setParameter(statement, param);        
        }        
        return statement;
    }
    
    private void setParameter(PreparedStatement statement, PrintParameter param) throws SQLException{
        switch(param.getDataType()){
            case "String" : statement.setString(param.getSequence(), (String) param.getValue());
        }
    }
    
    @Override
    protected void done(){
        try {
            JasperPrint print = get();            
            JRViewer viewer = new JRViewer(print);
            JDialog dialog = new JDialog((Frame)parent, "Print Preview", true);
            dialog.add(viewer);            
            dialog.setSize(parent.getSize());
            dialog.setLocationRelativeTo(parent);
            dialog.setVisible(true);            
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(DefaultJDBCPrintWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Map<String, Object> createParameterMap(AppsReport report, List<PrintParameter> paramList){
        Map<String, Object> parameterMap = new HashMap<>();
        
        parameterMap.put("REPORT_TITLE", report.getTitle());
        for(PrintParameter param : paramList){
            if(param.getParameterType().trim().equalsIgnoreCase("Report")){
                if(param.getValue() instanceof ListOption){
                    ListOption listOption = (ListOption) param.getValue();
                    parameterMap.put(param.getName(), listOption.getValue());                    
                    System.out.println(param.getName() + " : " + listOption.getValue().getClass());
                }else{
                    parameterMap.put(param.getName(), convert(param.getDataType(), param.getValue()));                    
                    Object value = convert(param.getDataType(), param.getValue());
                    System.out.println(param.getName() + " : " + value.getClass());
                }    
            }    
        }
            
        return parameterMap;
    }
    
    
    public Object convert(String dataType, Object value){
        Object convertedValue = null;
        
        switch(dataType){
            case "Integer" : convertedValue = parseInteger(value); break;
            case "Double" : convertedValue = parseDouble(value); break;
            case "String" : convertedValue = value.toString(); break;
            default : convertedValue = value;
        }
        
        return convertedValue;
    }
    
    
    private Double parseDouble(Object value){
        double doubleValue;
        
        try{
            doubleValue = Double.parseDouble(value.toString());
        }catch(NumberFormatException ex){
            doubleValue = 0;
        }    
        
        return doubleValue;
    }   
    
    private Integer parseInteger(Object value){
        int intValue;
        
        try{
            intValue = Integer.parseInt(value.toString());
        }catch(NumberFormatException ex){
            intValue = 0;
        }    
        
        return intValue;
    }   
    
}
