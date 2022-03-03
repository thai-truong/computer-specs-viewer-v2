package org.computerspecsviewer.gui.views.approot;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.computerspecsviewer.gui.views.menutree.MenuTreeComponent;

public class AppRootComponent {
    private static final Integer mainLayoutSpacing = 2;

    private Pane rootLayout;
    private MenuTreeComponent menuTree;
    private Pane displaySection;

    public AppRootComponent() {
        rootLayout = new HBox(mainLayoutSpacing);
        menuTree = new MenuTreeComponent();
        displaySection = new VBox();

        rootLayout.getChildren().addAll(menuTree.get(), new Separator(Orientation.VERTICAL), displaySection);
    }
}
