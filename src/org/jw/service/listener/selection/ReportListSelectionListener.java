/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.selection;

import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jw.service.entity.AppsReport;
import org.jw.service.entity.AppsReportParameter;

/**
 *
 * @author Wilson
 */
public class ReportListSelectionListener implements ListSelectionListener{

    public static ReportListSelectionListener create(List<AppsReport> reportList, List<AppsReportParameter> parameterList) {
        return new ReportListSelectionListener(reportList, parameterList);
    }
    
    private final List<AppsReportParameter> parameterList;
    private final List<AppsReport> reportList;
    
    private ReportListSelectionListener(List<AppsReport> reportList,
                                        List<AppsReportParameter> parameterList){
        this.reportList = reportList;
        this.parameterList = parameterList;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(!lse.getValueIsAdjusting()){
            int index = lse.getFirstIndex();
            AppsReport report = reportList.get(index);
            parameterList.clear();
            parameterList.addAll(report.getAppsReportParameterCollection());
        }        
    }
    
}
