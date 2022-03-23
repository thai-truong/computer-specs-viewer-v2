package org.computerspecsviewer.gui.views.page.infoquery.fieldcomponents;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class NormalFieldComponent {
    private static final Integer hBoxSpacing = 4;

    private String label;
    private String value;
    private Pane fieldView;

    public NormalFieldComponent(Object value) {
        this("", value);
    }

    public NormalFieldComponent(String label, Object value) {
        this.label = label;
        this.value = value.toString();
    }

    public Pane get() {
        if(fieldView == null) {
            createFieldView();
        }

        return fieldView;
    }

    private void createFieldView() {
        List<Node> nodesToAdd = new ArrayList<>();

        if(!label.isEmpty()) {
            nodesToAdd.add(new Label(label + ":"));
        }

        nodesToAdd.add(new Text(value));

        fieldView = new HBox(hBoxSpacing);
        fieldView.getChildren().addAll(nodesToAdd);
    }
}
