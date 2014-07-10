/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.key.binder;

import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import org.jw.service.gui.component.CndrsCommandPanel;

/**
 *
 * @author Wilson
 */
public class CndrsKeyBinders {
    private final CndrsCommandPanel cndrs;
    public final static String CLOSE_MAP_KEY = "CLOSE";
    public final static String NEW_MAP_KEY = "NEW";
    public final static String DELETE_MAP_KEY = "DELETE";
    public final static String REFRESH_MAP_KEY = "REFRESH";
    public final static String SAVE_MAP_KEY = "SAVE";
    public final static String ENTER_MAP_KEY = "ENTER";
    public final static KeyStroke controlAltX = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK);
    public final static KeyStroke controlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
    public final static KeyStroke controlAltD = KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK);
    public final static KeyStroke controlR = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
    public final static KeyStroke controlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
    public final static KeyStroke controlEnter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_DOWN_MASK);
    
    public CndrsKeyBinders(CndrsCommandPanel cndrsCommandPanel){
        this.cndrs = cndrsCommandPanel;
    }
    
    public void bindCndrsKeys(){
        try{
            setKeyBinder(cndrs.getCloseCommand(), controlAltX, CLOSE_MAP_KEY);
            setKeyBinder(cndrs.getNewCommand(), controlN, NEW_MAP_KEY);
            setKeyBinder(cndrs.getDeleteCommand(), controlAltD, DELETE_MAP_KEY);
            setKeyBinder(cndrs.getRefreshCommand(), controlR, REFRESH_MAP_KEY);
            setKeyBinder(cndrs.getSaveCommand(), controlS, SAVE_MAP_KEY);            
        }catch(NullPointerException ex){}
    }
    
    public static void setKeyBinder(javax.swing.JButton command, KeyStroke keyStroke, String mapKey){
        if(command == null)return;
        command.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, mapKey);
        command.getActionMap().put(mapKey, command.getAction());
    }
    
    public static void setKeyBinder(javax.swing.JComponent component, KeyStroke keyStroke, String mapKey, Action action){
        if(component == null)return;
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, mapKey);
        component.getActionMap().put(mapKey, action);
    }
}
