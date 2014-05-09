/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;

/**
 *
 * @author Wilson
 */
public interface DependencyCommand<T>{
    public boolean run(ActionEvent ae);
    public void run(T workerResult, ActionEvent ae);
    public T get();
}
