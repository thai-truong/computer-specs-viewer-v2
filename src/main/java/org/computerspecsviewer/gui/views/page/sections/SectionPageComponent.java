package org.computerspecsviewer.gui.views.page.sections;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import org.computerspecsviewer.gui.views.base.PageComponent;

public class SectionPageComponent extends PageComponent {
    private static final Integer vBoxSpacing = 2;

    private String title;
    private Node displayComponent;

    public SectionPageComponent(String title, Node displayComponent) {
        this.title = title;
        this.displayComponent = displayComponent;
    }
    
    protected void create() {
        Label pageTitle = new Label(title);
        page.getChildren().addAll(pageTitle, new Separator(), addScroller(displayComponent));
    }
}
