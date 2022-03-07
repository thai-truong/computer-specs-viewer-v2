package org.computerspecsviewer.gui.views.menutree;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import org.computerspecsviewer.gui.views.menutree.itemhandlers.InfoQueryItemHandler;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.RootItemValue;

import java.util.List;

public class MenuTreeComponent {
    private TreeView<BaseItemValue> menuTree;
    private BorderPane appDisplaySection;
    private TreeItem<BaseItemValue> treeRoot = new TreeItem<>(new RootItemValue());

    public MenuTreeComponent(BorderPane appDisplaySection) {
        this.appDisplaySection = appDisplaySection;
    }

    public TreeView<BaseItemValue> getComponent() {
        if(menuTree == null) {
            createComponent();
        }

        return menuTree;
    }

    public void setRootChildren(List<TreeItem<BaseItemValue>> newChildren) {
        treeRoot.getChildren().setAll(newChildren);
    }

    private void createComponent() {
        menuTree = new TreeView<>(treeRoot);
        menuTree.setShowRoot(false);

        addItemSelectedListener();
    }

    private void addItemSelectedListener() {
        menuTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            TreeItem<BaseItemValue> selectedItem = newValue;
            Node componentToDisplay = selectedItem.getValue().getComponentToDisplay();

            if(componentToDisplay != null) {
                appDisplaySection.setCenter(componentToDisplay);
            }
        });
    }
}
