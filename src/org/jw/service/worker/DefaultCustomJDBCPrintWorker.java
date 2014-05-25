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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.SwingWorker;
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
public class DefaultCustomJDBCPrintWorker extends SwingWorker<JasperPrint, String>{
    private final AppsReport report;
    private final List<PrintParameter> paramList;
    private final Connection connection;
    private final InputStream inputStream;
    private final Window parent;
    
    public DefaultCustomJDBCPrintWorker(Window parent, AppsReport report, List<PrintParameter> paramList, Connection connection, InputStream inputStream, DefaultTaskListener taskListener){
        this.parent = parent;
        this.report = report;
        this.paramList = paramList;
        this.connection = connection;
        this.inputStream = inputStream;
        this.addPropertyChangeListener(taskListener);
    }
    
    @Override
    protected JasperPrint doInBackground() throws Exception {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Map<String, Object> paramMap = createParameterMap(report, paramList);        
        ResultSet resultSet = statement.executeQuery(report.getQuery());
        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
        return JasperFillManager.fillReport(inputStream, paramMap, resultSetDataSource);        
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
            Logger.getLogger(DefaultCustomJDBCPrintWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Map<String, Object> createParameterMap(AppsReport report, List<PrintParameter> paramList){
        Map<String, Object> parameterMap = new HashMap<>();
        
        parameterMap.put("REPORT_TITLE", report.getTitle());
        for(PrintParameter param : paramList)
            parameterMap.put(param.getName(), param.getValue());
        
        return parameterMap;
    }
    
}
