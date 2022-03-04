package org.computerspecsviewer.gui.views.menutree;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import org.computerspecsviewer.gui.views.menutree.treeitemhandlers.InfoQueryItemHandler;
import org.computerspecsviewer.gui.views.menutree.treeitemvalue.BaseTreeItemValue;
import org.computerspecsviewer.gui.views.menutree.treeitemvalue.RootTreeItemValue;

import java.util.List;

public class MenuTreeComponent {
    private TreeView<BaseTreeItemValue> menuTree;
    private BorderPane appDisplaySection;

    public MenuTreeComponent(BorderPane appDisplaySection) {
        this.appDisplaySection = appDisplaySection;
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
        InfoQueryItemHandler infoQueryHandler = new InfoQueryItemHandler();
        List<TreeItem<BaseTreeItemValue>> infoQueryNodes = infoQueryHandler.getItems();

        emptyRoot.getChildren().addAll(infoQueryNodes);

        menuTree = new TreeView<>(emptyRoot);
        menuTree.setShowRoot(false);

        addItemSelectedListener();
    }

    private void addItemSelectedListener() {
        menuTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            TreeItem<BaseTreeItemValue> selectedItem = newValue;
            appDisplaySection.setCenter(selectedItem.getValue().getComponentToDisplay());
        });
    }
}
