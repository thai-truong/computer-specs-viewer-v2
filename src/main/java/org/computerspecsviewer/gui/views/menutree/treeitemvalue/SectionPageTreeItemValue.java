package org.computerspecsviewer.gui.views.menutree.treeitemvalue;

import javafx.scene.Node;
import org.computerspecsviewer.gui.views.sections.SectionPageComponent;

public class SectionPageTreeItemValue implements BaseTreeItemValue {
    private String itemLabel;
    private String title;
    private Node displayComponent;
    private SectionPageComponent sectionPage;

    public SectionPageTreeItemValue(String itemLabel, String title, Node displayComponent) {
        this.itemLabel = itemLabel;
        this.title = title;
        this.displayComponent = displayComponent;
    }

    public String toString() {
        return itemLabel;
    }

    public Node getComponentToDisplay() {
        if(sectionPage == null) {
            sectionPage = new SectionPageComponent(title, displayComponent);
        }

        return sectionPage.get();
    }
}
