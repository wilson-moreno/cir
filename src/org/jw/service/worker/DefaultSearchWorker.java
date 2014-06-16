/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.util.List;
import javax.swing.SwingWorker;
import org.jw.service.entity.Contact;
import org.jw.service.list.Matcher;
import org.jw.service.listener.task.DefaultTaskListener;

/**
 *
 * @author Wilson
 */
public class DefaultSearchWorker extends SwingWorker<String, Contact>{
    private final List<Contact> searchList;
    private final List<Contact> foundList;
    private final Matcher matcher;
    private final Contact key;
    
    
    public DefaultSearchWorker(DefaultTaskListener listener, List<Contact> searchList, List<Contact> foundList, Contact key, Matcher matcher){
        this.searchList = searchList;
        this.foundList = foundList;
        this.matcher = matcher;
        this.key = key;
        listener.setIndeterminate(false);
        this.addPropertyChangeListener(listener);
    }

    @Override
    protected String doInBackground() throws Exception {    
        foundList.clear();
        int i = 1;
        for(Contact contact : searchList){
            if(isCancelled())break;
            setProgress(100 * (i / searchList.size()));
            if(matcher.isMatch(contact, key)){
                publish(contact);
            }
            i++;
        }
        return "";
    }

    @Override
    protected void process(List<Contact> resultList){
        for(Contact contact : resultList){
            foundList.add(contact);
        }
    }
    
}
