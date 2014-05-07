/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jw.service.listener.task;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Wilson
 */
public abstract class AbstractTaskListener implements PropertyChangeListener{
    private final JProgressBar taskProgressBar;
    private final JLabel progressMessageLabel;
    
    public AbstractTaskListener(JProgressBar taskProgressBar,
                                JLabel progressMessageLabel){
        this.taskProgressBar = taskProgressBar;
        this.progressMessageLabel = progressMessageLabel;
    }
    
    
    @Override 
    public final void propertyChange(PropertyChangeEvent evt) {
        this.setPropertyChangeAction(evt);
    }
    
    protected void setMessage(String message){
        this.progressMessageLabel.setText(message);
    }
    
    protected void setProgressBarIndeterminate(boolean b){
        this.taskProgressBar.setIndeterminate(b);
    }
    
    protected void setProgressBarValue(int value){
        this.taskProgressBar.setValue(value);
    }
    
    protected void setProgressBarStringPainted(boolean b){
        this.taskProgressBar.setStringPainted(b);
    }
    
    protected abstract void startTask();
    protected abstract void doneTask();
    protected final void progressUpdate(int value){
        this.taskProgressBar.setValue(value);
    }
    
    protected void setPropertyChangeAction(PropertyChangeEvent evt){
        if("state".equals(evt.getPropertyName().trim())){
            if("STARTED".equals(evt.getNewValue().toString())){
                startTask();
            }else if("DONE".equals(evt.getNewValue().toString())){
                doneTask();
            }
        }else if("progress".equals(evt.getPropertyName().trim())){
            progressUpdate((Integer)evt.getNewValue());
        }
    }
}
