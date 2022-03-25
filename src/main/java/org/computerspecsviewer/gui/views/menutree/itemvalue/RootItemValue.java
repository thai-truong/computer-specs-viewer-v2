package org.computerspecsviewer.gui.views.menutree.itemvalue;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RootItemValue extends BaseItemValue {
    public RootItemValue() {}

    public String toString() {
        return "";
    }

    public Pane getComponent() {
        return new VBox();
    }
}
