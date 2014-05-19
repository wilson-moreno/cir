/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.list;

import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.observablecollections.ObservableListListener;
import org.jw.service.entity.TreeNodeEntity;

/**
 *
 * @author Wilson
 */
public class DefaultTreeObservableListListener  implements ObservableListListener{
    private final JTree tree;    
    private final DefaultMutableTreeNode root;
    private final DefaultTreeModel treeModel;
    
    public DefaultTreeObservableListListener(JTree tree){
        this.tree = tree;        
        this.root = new DefaultMutableTreeNode("Contacts");
        this.treeModel = new DefaultTreeModel(root);
        this.tree.setModel(treeModel);
    }
    
    @Override
    public void listElementsAdded(ObservableList list, int index, int length) {
        List<TreeNodeEntity> subList = list.subList(index, index + length);
        for(TreeNodeEntity item : subList)
            root.add(new DefaultMutableTreeNode(item.getDisplayString()));
        treeModel.reload(root);
    }

    @Override
    public void listElementsRemoved(ObservableList list, int index, List oldElements) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listElementReplaced(ObservableList list, int index, Object oldElement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listElementPropertyChanged(ObservableList list, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
