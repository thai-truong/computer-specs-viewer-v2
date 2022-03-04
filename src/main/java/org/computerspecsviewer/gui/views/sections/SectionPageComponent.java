package org.computerspecsviewer.gui.views.sections;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SectionPageComponent {
    private static final Integer vBoxSpacing = 2;
    
    private String title;
    private Node displayComponent;
    private Pane sectionPage;
    
    public SectionPageComponent(String title, Node displayComponent) {
        this.title = title;
        this.displayComponent = displayComponent;
    }
    
    public Pane get() {
        if(sectionPage == null) {
            createPage();
        }
        
        return sectionPage;
    }
    
    private void createPage() {
        sectionPage = new VBox(vBoxSpacing);
        Label pageTitle = new Label(title);
        
        sectionPage.getChildren().addAll(pageTitle, new Separator(), displayComponent);
    }
}
