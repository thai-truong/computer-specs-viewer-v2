package org.computerspecsviewer.gui.views.menutree.itemvalue;

import javafx.scene.layout.Pane;
import org.computerspecsviewer.gui.views.page.infoquery.InfoQueryPageComponent;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;

public class InfoQueryPageItemValue extends BaseItemValue {
    private String treeLabel;
    private String title;
    private String description;
    private BaseInfoQuery infoQuery;

    public InfoQueryPageItemValue(String treeLabel, String title, String description, BaseInfoQuery infoQuery) {
        this.treeLabel = treeLabel;
        this.title = title;
        this.description = description;
        this.infoQuery = infoQuery;
    }

    public String toString() {
        return treeLabel;
    }

    public Pane getComponent() {
        if(component == null) {
            InfoQueryPageComponent infoQueryPage = new InfoQueryPageComponent(title, description, infoQuery);
            component = infoQueryPage.get();
        }

        return component;
    }
}
