package org.computerspecsviewer.gui.views.menutree.itemvalue;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class RootItemValue implements BaseItemValue {
    public RootItemValue() {}

    public String toString() {
        return "";
    }

    public Node getComponentToDisplay() {
        return new Label("");
    }
}
