package org.computerspecsviewer.gui.views.base;

import javafx.scene.layout.Pane;

public abstract class PageComponent {
    protected Pane page;

    public Pane get() {
        if(page == null) {
            create();
        }

        return page;
    }

    protected abstract void create();
}
