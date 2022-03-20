package org.computerspecsviewer.gui.views.menutree.itemvalue;

import javafx.scene.Node;
import org.computerspecsviewer.gui.views.infoquery.InfoQueryPageComponent;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;

public class InfoQueryPageItemValue implements BaseItemValue {
    private String treeLabel;
    private String title;
    private String description;
    private BaseInfoQuery infoQuery;
    private InfoQueryPageComponent infoQueryPage;

    public InfoQueryPageItemValue(String treeLabel, String title, String description, BaseInfoQuery infoQuery) {
        this.treeLabel = treeLabel;
        this.title = title;
        this.description = description;
        this.infoQuery = infoQuery;
    }

    public String toString() {
        return treeLabel;
    }

    public Node getComponentToDisplay() {
        if(infoQueryPage == null) {
            infoQueryPage = new InfoQueryPageComponent(title, description, infoQuery);
        }

        return infoQueryPage.get();
    }
}
