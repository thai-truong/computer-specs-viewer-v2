package org.computerspecsviewer.gui.views.menutree;

import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import org.computerspecsviewer.gui.views.menutree.treeitemhandlers.InfoQueryTreeHandler;
import org.computerspecsviewer.gui.views.menutree.treeitemvalue.BaseTreeItemValue;
import org.computerspecsviewer.gui.views.menutree.treeitemvalue.RootTreeItemValue;

import java.util.List;

public class MenuTreeComponent {
    private TreeView<BaseTreeItemValue> menuTree;
    private EventHandler<? super MouseEvent> onMouseClicked;

    public MenuTreeComponent() {
        this(null);
    }

    public MenuTreeComponent(EventHandler<? super MouseEvent> displaySelectedPage) {
        onMouseClicked = displaySelectedPage;
    }

    public TreeView<BaseTreeItemValue> get() {
        if(menuTree == null) {
            createMenuTree();
        }

        return menuTree;
    }

    public void setOnMouseClicked(EventHandler<? super MouseEvent> displaySelectedPage) {
        menuTree.setOnMouseClicked(displaySelectedPage);
    }

    private void createMenuTree() {
        TreeItem<BaseTreeItemValue> emptyRoot = new TreeItem<>(new RootTreeItemValue());

        // Add other tree nodes to root node here
        List<TreeItem<BaseTreeItemValue>> infoQueryNodes = InfoQueryTreeHandler.getTreeItems();

        emptyRoot.getChildren().addAll(infoQueryNodes);

        menuTree = new TreeView<>(emptyRoot);
        menuTree.setShowRoot(false);
        menuTree.setOnMouseClicked(onMouseClicked);
    }
}
