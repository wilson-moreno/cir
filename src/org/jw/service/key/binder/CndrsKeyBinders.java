/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.key.binder;

import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import org.jw.service.gui.component.CndrsCommandPanel;

/**
 *
 * @author Wilson
 */
public class CndrsKeyBinders {
    private final CndrsCommandPanel cndrs;
    private final static String CLOSE_MAP_KEY = "CLOSE";
    private final static String NEW_MAP_KEY = "NEW";
    private final static String DELETE_MAP_KEY = "DELETE";
    private final static String REFRESH_MAP_KEY = "REFRESH";
    private final static String SAVE_MAP_KEY = "SAVE";
    
    public CndrsKeyBinders(CndrsCommandPanel cndrsCommandPanel){
        this.cndrs = cndrsCommandPanel;
    }
    
    public void bindCndrsKeys(){
        try{
            KeyStroke controlX = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK);
            KeyStroke controlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
            KeyStroke controlD = KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK);
            KeyStroke controlR = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
            KeyStroke controlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
            
            setKeyBinder(cndrs.getCloseCommand(), controlX, CLOSE_MAP_KEY);
            setKeyBinder(cndrs.getNewCommand(), controlN, NEW_MAP_KEY);
            setKeyBinder(cndrs.getDeleteCommand(), controlD, DELETE_MAP_KEY);
            setKeyBinder(cndrs.getRefreshCommand(), controlR, REFRESH_MAP_KEY);
            setKeyBinder(cndrs.getSaveCommand(), controlS, SAVE_MAP_KEY);            
        }catch(NullPointerException ex){}
    }
    
    private void setKeyBinder(javax.swing.JButton command, KeyStroke keyStroke, String mapKey){
        if(command == null)return;
        command.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, mapKey);
        command.getActionMap().put(mapKey, command.getAction());
    }
}
