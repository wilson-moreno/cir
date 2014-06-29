/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.awt.Frame;
import java.awt.Window;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.jw.service.entity.AppsReport;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.print.PrintParameter;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author Wilson
 */
public class DefaultDirectionMapPrintWorker extends SwingWorker<JasperPrint, String>{      
    private final List<PrintParameter> paramList;
    private final Connection connection;
    private final InputStream inputStream;
    private final Window parent;
    private final Map<Integer, AppsReport> templateMap;
    
    public DefaultDirectionMapPrintWorker(Window parent, Map<Integer, AppsReport> templateMap, List<PrintParameter> paramList, Connection connection, InputStream inputStream, DefaultTaskListener taskListener){
        this.parent = parent;
        this.templateMap = templateMap;
        this.paramList = paramList;
        this.connection = connection;
        this.inputStream = inputStream;
        this.addPropertyChangeListener(taskListener);
    }
    
    @Override
    protected JasperPrint doInBackground() throws Exception {        
        return createReport();
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
            Logger.getLogger(DefaultDirectionMapPrintWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private JasperPrint createReport() throws SQLException, JRException{
        ResultSet recordNumbers = fetchRecordNumbers(paramList);
        JasperPrint directionMapPrint = null;
        JasperPrint collatedPrint = createEmptyJasperPrint();
        
        while(recordNumbers.next()){
            String recordNumber = recordNumbers.getString("RECORD_NUMBER");
            directionMapPrint = createDirectionMap(templateMap.get(1), recordNumber);            
            for(JRPrintPage page : directionMapPrint.getPages()){
                collatedPrint.addPage(page);
            }
        }
        
        return collatedPrint;
    }
    
    private JasperPrint createDirectionMap(AppsReport report, String recordNumber) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(report.getQuery());
        statement.setString(1, recordNumber);
        statement.setString(2, recordNumber);
        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(statement.executeQuery());        
        
        return null;
    }
    
    private ResultSet fetchRecordNumbers(List<PrintParameter> paramList) throws SQLException{
        String recordNumberStart = "";
        String recordNumberEnd = "";
        
        for(PrintParameter param : paramList){
            switch(param.getName()){
                case "START_RECORD_NUMBER" : recordNumberStart = (String)param.getValue(); break;
                case "END_RECORD_NUMBER" : recordNumberEnd = (String)param.getValue(); break;
            }
        }
        
        return getRecordNumberResultSet(recordNumberStart, recordNumberEnd);
    }
    
    private Map<String, Object> createParameterMap(AppsReport report, List<PrintParameter> paramList){
        Map<String, Object> parameterMap = new HashMap<>();
        
        parameterMap.put("REPORT_TITLE", report.getTitle());
        for(PrintParameter param : paramList)
            parameterMap.put(param.getName(), param.getValue());
        
        return parameterMap;
    }
 
    
    private JasperPrint createEmptyJasperPrint() throws JRException{
        InputStream inputStream = new ByteArrayInputStream(templateMap.get(1).getFileJasper());        
        JasperPrint empty = JasperFillManager.fillReport(inputStream, null, new JREmptyDataSource());                    
        empty.removePage(0);
        return empty;
    }
    
    private InputStream createInputStream(AppsReport report){
        return new ByteArrayInputStream(report.getFileJasper());
    }
    
    private ResultSet getRecordNumberResultSet(String recordNumberStart, String recordNumberEnd) throws SQLException{
        String queryString = "SELECT c.record_number FROM CIR.Contact c WHERE record_number BETWEEN ? AND ? ORDER BY record_number";
        PreparedStatement statement = connection.prepareStatement(queryString);
        statement.setString(1, recordNumberStart);
        statement.setString(2, recordNumberEnd);        
        return statement.executeQuery();
    }
    
    
    
    
}
