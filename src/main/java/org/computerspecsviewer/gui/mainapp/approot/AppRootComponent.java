package org.computerspecsviewer.gui.mainapp.approot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import org.computerspecsviewer.gui.views.menutree.MenuTreeComponent;

public class AppRootComponent {
    private static final Insets smallLeftPadding = new Insets(5, 0, 0, 10);

    private SplitPane rootLayout;
    private MenuTreeComponent menuTree;
    private BorderPane displayPane;

    public AppRootComponent() {}

    public SplitPane get() {
        if(rootLayout == null) {
            create();
        }

        return rootLayout;
    }

    private void create() {
        customizeDisplayPane();

        menuTree = new MenuTreeComponent(displayPane);
        rootLayout = new SplitPane(menuTree.getComponent(), displayPane);
        rootLayout.setDividerPosition(0, 0.25);
    }

    private void customizeDisplayPane() {
        displayPane = new BorderPane();
        displayPane.setPadding(smallLeftPadding);

        DisplayPaneFooter footer = new DisplayPaneFooter();
        BorderPane.setAlignment(footer.get(), Pos.CENTER);
        BorderPane.setMargin(footer.get(), new Insets(0, 0, 10, 0));

        displayPane.setBottom(footer.get());
    }
}
