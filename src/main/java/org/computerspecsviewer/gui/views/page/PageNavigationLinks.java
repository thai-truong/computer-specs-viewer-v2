package org.computerspecsviewer.gui.views.page;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.computerspecsviewer.gui.assets.AppIcons;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;

import java.util.ArrayList;
import java.util.List;

public class PageNavigationLinks {
    private final TreeItem<BaseItemValue> target;
    private final MultipleSelectionModel<TreeItem<BaseItemValue>> treeSelectionModel;

    private Pane linkContainer;

    public PageNavigationLinks(TreeItem<BaseItemValue> target, MultipleSelectionModel<TreeItem<BaseItemValue>> treeSelectionModel) {
        this.target = target;
        this.treeSelectionModel = treeSelectionModel;
    }

    public Pane get() {
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
            linkContainer = new VBox();
            return;
        }

        VBox linkBoxWithSeparator = new VBox();
        HBox linkBox = new HBox();
        List<Node> links = new ArrayList<>();
        AppIcons icons = AppIcons.getInstance();

        Hyperlink prevLink = getLink(links, prev, "Prev");
        links.add(new Separator(Orientation.VERTICAL));
        Hyperlink parentLink = getLink(links, parent, "Back To Section Page");
        links.add(new Separator(Orientation.VERTICAL));
        Hyperlink nextLink = getLink(links, next, "Next");

        if(prevLink != null) {
            prevLink.setGraphic(icons.getPrevIcon());
            HBox.setHgrow(prevLink, Priority.ALWAYS);
        }

        if(parentLink != null) {
            parentLink.setGraphic(icons.getHomeIcon());
            HBox.setHgrow(parentLink, Priority.ALWAYS);
        }

        if(nextLink != null) {
            nextLink.setGraphic(icons.getNextIcon());
            nextLink.setContentDisplay(ContentDisplay.RIGHT);

            HBox.setHgrow(nextLink, Priority.ALWAYS);
        }

        linkBox.getChildren().addAll(links);
        linkBox.setAlignment(Pos.CENTER);

        linkBoxWithSeparator.getChildren().addAll(linkBox, new Separator());
        linkContainer = linkBoxWithSeparator;
    }

    private Hyperlink getLink(List<Node> links, TreeItem<BaseItemValue> toSelect, String text) {
        if(toSelect == null) {
            return null;
        }

        Hyperlink link = new Hyperlink(text);
        link.setOnAction((event -> treeSelectionModel.select(toSelect)));

        links.add(link);
        return link;
    }
}
