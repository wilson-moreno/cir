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
import org.jw.service.listener.task.SearchTaskListener;

/**
 *
 * @author Wilson
 */
public class DefaultSearchWorker extends SwingWorker<String, Contact>{
    private final List<Contact> searchList;
    private final List<Contact> foundList;
    private final Matcher matcher;
    private final Contact key;
    private final SearchTaskListener searchListener;
    
    
    public DefaultSearchWorker(DefaultTaskListener listener, List<Contact> searchList, List<Contact> foundList, Contact key, Matcher matcher, SearchTaskListener searchListener){
        this.searchList = searchList;
        this.foundList = foundList;
        this.matcher = matcher;
        this.key = key;
        this.searchListener = searchListener;
        listener.setIndeterminate(false);
        this.addPropertyChangeListener(listener);
        this.addPropertyChangeListener(searchListener);
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
