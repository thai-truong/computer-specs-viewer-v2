package org.computerspecsviewer.gui.views.menutree.treeitemvalue;

import javafx.scene.Node;
import org.computerspecsviewer.gui.views.infoquery.InfoQueryPageComponent;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;

public class InfoQueryPageTreeItemValue implements BaseTreeItemValue {
    public String label;
    public String description;
    public BaseInfoQuery infoQuery;
    public InfoQueryPageComponent infoQueryPage;

    public InfoQueryPageTreeItemValue(String label, String description, BaseInfoQuery infoQuery) {
        this.label = label;
        this.description = description;
        this.infoQuery = infoQuery;
    }

    public String toString() {
        return label;
    }

    public Node getComponentToDisplay() {
        if(infoQueryPage == null) {
            infoQueryPage = new InfoQueryPageComponent(label, description, infoQuery);
        }

        return infoQueryPage.get();
    }
}
