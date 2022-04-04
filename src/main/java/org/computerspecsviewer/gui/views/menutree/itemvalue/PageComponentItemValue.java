package org.computerspecsviewer.gui.views.menutree.itemvalue;

import javafx.scene.layout.Pane;
import org.computerspecsviewer.gui.views.base.PageComponent;

public class PageComponentItemValue extends BaseItemValue {
    private String itemLabel;
    private PageComponent pageComponent;

    public PageComponentItemValue(String itemLabel, PageComponent pageComponent) {
        this.itemLabel = itemLabel;
        this.pageComponent = pageComponent;
    }
    
    @Override
    public String toString() {
        return itemLabel;
    }

    @Override
    public Pane getComponent() {
        if(component == null) {
            component = pageComponent.get();
        }

        return component;
    }
}
