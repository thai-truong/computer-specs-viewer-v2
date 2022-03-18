package org.computerspecsviewer.gui.mainapp.approot;

import javafx.geometry.Insets;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import org.computerspecsviewer.gui.views.menutree.MenuTreeComponent;

public class AppRootComponent {
    private static final Insets smallLeftPadding = new Insets(5, 0, 0, 10);

    private SplitPane rootLayout;
    private MenuTreeComponent menuTree;
    private BorderPane displayPane;

    public AppRootComponent() {
        displayPane = new BorderPane();
        displayPane.setPadding(smallLeftPadding);

        menuTree = new MenuTreeComponent(displayPane);
        rootLayout = new SplitPane(menuTree.getComponent(), displayPane);
        rootLayout.setDividerPosition(0, 0.25);
    }

    public SplitPane get() {
        return rootLayout;
    }
}
