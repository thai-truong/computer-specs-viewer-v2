package org.computerspecsviewer.gui.views.menutree.treeitemvalue;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class RootTreeItemValue implements BaseTreeItemValue {
    public RootTreeItemValue() {}

    public String toString() {
        return "";
    }

    public Node getComponentToDisplay() {
        return new Label("");
    }
}
