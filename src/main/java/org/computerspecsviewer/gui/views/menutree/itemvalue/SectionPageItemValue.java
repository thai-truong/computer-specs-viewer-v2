package org.computerspecsviewer.gui.views.menutree.itemvalue;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.computerspecsviewer.gui.views.page.sections.SectionPageComponent;

public class SectionPageItemValue extends BaseItemValue {
    private String itemLabel;
    private Node displayComponent;

    public SectionPageItemValue(String itemLabel, String description) {
        this.itemLabel = itemLabel;
        this.displayComponent = new Text(description);
    }

    public SectionPageItemValue(String itemLabel, Node displayComponent) {
        this.itemLabel = itemLabel;
        this.displayComponent = displayComponent;
    }

    public String toString() {
        return itemLabel;
    }

    public Pane getComponent() {
        if(component == null) {
            SectionPageComponent sectionPage = new SectionPageComponent(itemLabel, displayComponent);
            component = sectionPage.get();
        }

        return component;
    }
}
