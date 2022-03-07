package org.computerspecsviewer.gui.mainapp.approot;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.computerspecsviewer.gui.mainapp.menutree.MenuTreeCreator;
import org.computerspecsviewer.gui.views.menutree.MenuTreeComponent;

public class AppRootComponent {
    private static final Integer mainLayoutSpacing = 2;

    private Pane rootLayout;
    private MenuTreeCreator menuTree;
    private BorderPane displaySection;

    public AppRootComponent() {
        rootLayout = new HBox(mainLayoutSpacing);
        displaySection = new BorderPane();
        menuTree = new MenuTreeCreator(displaySection);

        rootLayout.getChildren().addAll(menuTree.getMenuTree().getComponent(), new Separator(Orientation.VERTICAL),
                displaySection);
    }

    public Pane get() {
        return rootLayout;
    }
}
