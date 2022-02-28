package org.computerspecsviewer.gui.views.menutree.treeitemvalue;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class TextPageTreeItemValue implements BaseTreeItemValue {
    public String label;

    public TextPageTreeItemValue(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }

    public Node getComponentToDisplay() {
        return new Label("");
    }
}
