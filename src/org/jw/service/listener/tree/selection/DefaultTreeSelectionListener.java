/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.tree.selection;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jw.service.entity.Contact;
import org.jw.service.util.UtilityTable;
import org.jw.service.util.UtilityTree;

/**
 *
 * @author Wilson
 */
public class DefaultTreeSelectionListener implements TreeSelectionListener{
    private final UtilityTree utilTree;
    private final UtilityTable utilTable;
    
    
    public DefaultTreeSelectionListener(UtilityTree utilTree, UtilityTable utilTable){
        this.utilTree = utilTree;
        this.utilTable = utilTable;
    }
    
    @Override
    public void valueChanged(TreeSelectionEvent tse) {        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) utilTree.getLastSelectedPathComponent();
        if(node != null){
            if(Contact.class.isInstance(node.getUserObject())){
                utilTable.selectItemRow(node.getUserObject());
            }
        }    
    }
    
}
