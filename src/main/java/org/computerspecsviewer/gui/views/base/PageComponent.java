package org.computerspecsviewer.gui.views.base;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class PageComponent {
    private static final Integer vBoxSpacing = 2;
    protected Pane page;

    public Pane get() {
        if(page == null) {
            page = new VBox(vBoxSpacing);
            create();
        }

        return page;
    }

    public Node addScroller(Node content) {
        ScrollPane scroller = new ScrollPane(content);

        scroller.setStyle("-fx-background-color:transparent;");
        scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroller.setFitToHeight(true);

        return scroller;
    }

    protected abstract void create();
}
