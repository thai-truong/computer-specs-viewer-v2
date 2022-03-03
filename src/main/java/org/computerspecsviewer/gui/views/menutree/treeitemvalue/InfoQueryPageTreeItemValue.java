package org.computerspecsviewer.gui.views.menutree.treeitemvalue;

import javafx.scene.Node;
import org.computerspecsviewer.gui.views.infoquery.InfoQueryPageComponent;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;

public class InfoQueryPageTreeItemValue implements BaseTreeItemValue {
    public String treeLabel;
    public String title;
    public String description;
    public BaseInfoQuery infoQuery;
    public InfoQueryPageComponent infoQueryPage;

    public InfoQueryPageTreeItemValue(String treeLabel, String title, String description, BaseInfoQuery infoQuery) {
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
