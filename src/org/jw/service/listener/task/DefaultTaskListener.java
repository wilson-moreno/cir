/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.task;

import java.beans.PropertyChangeSupport;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Wilson
 */
public class DefaultTaskListener extends AbstractTaskListener{
    public static final String PROP_STARTMESSAGE = "startMessage";
    public static final String PROP_DONEMESSAGE = "doneMessage";

    public static DefaultTaskListener create(JProgressBar taskProgressBar, JLabel messageNotificationLabel, String startMessage, String doneMessage, boolean indeterminate) {
        return new DefaultTaskListener(taskProgressBar, messageNotificationLabel, startMessage, doneMessage, indeterminate);
    }
    private boolean indeterminate;
    private String startMessage;
    private String doneMessage;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    
    private DefaultTaskListener(JProgressBar taskProgressBar, 
                               JLabel messageNotificationLabel,
                               String startMessage,
                               String doneMessage,
                               boolean indeterminate) {
        super(taskProgressBar, messageNotificationLabel);
        this.startMessage = startMessage;
        this.doneMessage = doneMessage;
        this.indeterminate = indeterminate;
    }

    @Override
    protected void startTask() {
        this.setMessage(startMessage);
        this.setProgressBarValue(0);
        this.setProgressBarStringPainted(!indeterminate);
        this.setProgressBarIndeterminate(indeterminate);
    }

    @Override
    protected void doneTask() {
        this.setMessage(doneMessage);
        if(indeterminate)this.setProgressBarIndeterminate(false);
    }

    /**
     * @return the startMessage
     */
    public String getStartMessage() {
        return startMessage;
    }

    /**
     * @param startMessage the startMessage to set
     */
    public void setStartMessage(String startMessage) {
        java.lang.String oldStartMessage = this.startMessage;
        this.startMessage = startMessage;
        propertyChangeSupport.firePropertyChange(PROP_STARTMESSAGE, oldStartMessage, startMessage);
    }

    /**
     * @return the doneMessage
     */
    public String getDoneMessage() {
        return doneMessage;
    }

    /**
     * @param doneMessage the doneMessage to set
     */
    public void setDoneMessage(String doneMessage) {
        java.lang.String oldDoneMessage = this.doneMessage;
        this.doneMessage = doneMessage;
        propertyChangeSupport.firePropertyChange(PROP_DONEMESSAGE, oldDoneMessage, doneMessage);
    }

    /**
     * @return the indeterminate
     */
    public boolean isIndeterminate() {
        return indeterminate;
    }

    /**
     * @param indeterminate the indeterminate to set
     */
    public void setIndeterminate(boolean indeterminate) {
        this.indeterminate = indeterminate;        
    }
}
