package org.computerspecsviewer.gui.views.menutree.itemvalue;

import javafx.scene.Node;
import javafx.scene.text.Text;
import org.computerspecsviewer.gui.views.sections.SectionPageComponent;

public class SectionPageItemValue implements BaseItemValue {
    private String itemLabel;
    private Node displayComponent;
    private SectionPageComponent sectionPage;

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

    public Node getComponentToDisplay() {
        if(sectionPage == null) {
            sectionPage = new SectionPageComponent(itemLabel, displayComponent);
        }

        return sectionPage.get();
    }
}
