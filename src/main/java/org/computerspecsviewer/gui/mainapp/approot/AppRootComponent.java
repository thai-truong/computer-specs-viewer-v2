package org.computerspecsviewer.gui.mainapp.approot;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.computerspecsviewer.gui.views.menutree.MenuTreeComponent;

public class AppRootComponent {
    private static final Integer mainLayoutSpacing = 2;

    private Pane rootLayout;
    private MenuTreeComponent menuTree;
    private BorderPane displaySection;

    public AppRootComponent() {
        rootLayout = new HBox(mainLayoutSpacing);
        displaySection = new BorderPane();
        menuTree = new MenuTreeComponent(displaySection);

        rootLayout.getChildren().addAll(menuTree.getComponent(), new Separator(Orientation.VERTICAL), displaySection);
    }

    public Pane get() {
        return rootLayout;
    }
}
