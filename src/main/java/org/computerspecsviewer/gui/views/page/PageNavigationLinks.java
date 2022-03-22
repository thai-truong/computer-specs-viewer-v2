package org.computerspecsviewer.gui.views.page;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;

import java.util.ArrayList;
import java.util.List;

public class PageNavigationLinks {
    private final TreeItem<BaseItemValue> target;
    private final MultipleSelectionModel<TreeItem<BaseItemValue>> treeSelectionModel;

    private Node linkContainer;

    public PageNavigationLinks(TreeItem<BaseItemValue> target, MultipleSelectionModel<TreeItem<BaseItemValue>> treeSelectionModel) {
        this.target = target;
        this.treeSelectionModel = treeSelectionModel;
    }

    public Node get() {
        if(linkContainer == null) {
            create();
        }

        return linkContainer;
    }

    private void create() {
        TreeItem<BaseItemValue> parent = target.getValue().getParent();
        TreeItem<BaseItemValue> prev = target.getValue().getPrev();
        TreeItem<BaseItemValue> next = target.getValue().getNext();

        if(parent == null && prev == null && next == null) {
            return;
        }

        HBox linkBox = new HBox();
        List<Hyperlink> links = new ArrayList<>();

        addLink(links, "Prev", prev);
        addLink(links, "Back To Section Page", parent);
        addLink(links, "Next", next);

        linkBox.getChildren().addAll(links);
        linkContainer = linkBox;
    }

    private void addLink(List<Hyperlink> links, String text, TreeItem<BaseItemValue> toSelect) {
        if(toSelect == null) {
            return;
        }

        Hyperlink link = new Hyperlink(text);
        link.setOnAction((event -> treeSelectionModel.select(toSelect)));
        links.add(link);
    }
}
