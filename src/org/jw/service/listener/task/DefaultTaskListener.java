/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.task;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Wilson
 */
public class DefaultTaskListener extends AbstractTaskListener{

    public static DefaultTaskListener create(JProgressBar taskProgressBar, JLabel messageNotificationLabel, String startMessage, String doneMessage, boolean indeterminate) {
        return new DefaultTaskListener(taskProgressBar, messageNotificationLabel, startMessage, doneMessage, indeterminate);
    }
    private final boolean indeterminate;
    private final String startMessage;
    private final String doneMessage;
    
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
    
}
