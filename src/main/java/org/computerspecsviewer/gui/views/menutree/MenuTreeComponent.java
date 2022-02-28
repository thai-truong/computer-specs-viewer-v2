package org.computerspecsviewer.gui.views.menutree;

import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import org.computerspecsviewer.gui.views.menutree.treeitemvalue.BaseTreeItemValue;
import org.computerspecsviewer.gui.views.menutree.treeitemvalue.RootTreeItemValue;

public class MenuTreeComponent {
    public TreeView<BaseTreeItemValue> menuTree;
    public EventHandler<? super MouseEvent> onMouseClicked;

    public MenuTreeComponent(EventHandler<? super MouseEvent> displaySelectedPage) {
        onMouseClicked = displaySelectedPage;
    }

    public TreeView<BaseTreeItemValue> get() {
        if(menuTree == null) {
            createMenuTree();
        }

        return menuTree;
    }

    private void createMenuTree() {
        TreeItem<BaseTreeItemValue> emptyRoot = new TreeItem<>(new RootTreeItemValue());

        // Add other tree nodes to root node here

        menuTree = new TreeView<>(emptyRoot);
        menuTree.setShowRoot(false);
        menuTree.setOnMouseClicked(onMouseClicked);
    }
}
