package org.computerspecsviewer.gui.views.menutree.itemvalue;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

public abstract class BaseItemValue {
    protected Pane component;

    public TreeItem<BaseItemValue> parent;
    public TreeItem<BaseItemValue> prev;
    public TreeItem<BaseItemValue> next;

    public abstract String toString();
    public abstract Pane getComponent();

    public void appendToComponent(Node content) {
        if(component == null) {
            component = getComponent();
        }

        component.getChildren().add(content);
    }

    public TreeItem<BaseItemValue> getParent() { return parent; }

    public TreeItem<BaseItemValue> getPrev() {
        return prev;
    }

    public TreeItem<BaseItemValue> getNext() {
        return next;
    }

    public void setParent(TreeItem<BaseItemValue> parent) { this.parent = parent; }

    public void setPrev(TreeItem<BaseItemValue> prev) {
        this.prev = prev;
    }

    public void setNext(TreeItem<BaseItemValue> next) {
        this.next = next;
    }
}
