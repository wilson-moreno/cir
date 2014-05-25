/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import javax.swing.SwingWorker;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDatabase;
import org.jw.service.util.UtilityReportPrint;

/**
 *
 * @author Wilson
 */
public class DefaultContactPrintWorker extends SwingWorker<String, String>{
    private final UtilityDatabase utilDB;
    private final UtilityReportPrint utilPrint;
    
    public DefaultContactPrintWorker(UtilityDatabase utilDB, UtilityReportPrint utilPrint, DefaultTaskListener listener){
        this.utilDB = utilDB;
        this.utilPrint = utilPrint;
        this.addPropertyChangeListener(listener);
    }
    
    @Override
    protected String doInBackground() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
