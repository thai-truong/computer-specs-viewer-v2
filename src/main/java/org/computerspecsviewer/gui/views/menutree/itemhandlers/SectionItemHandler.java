package org.computerspecsviewer.gui.views.menutree.itemhandlers;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.SectionPageItemValue;
import org.computerspecsviewer.infoquery.utils.StringHelpers;

import java.util.List;

public class SectionItemHandler {
    public TreeItem<BaseItemValue> createSectionItem(String itemLabel, Node displayComponent,
                                                     List<TreeItem<BaseItemValue>> childrenItems) {
        String title = StringHelpers.transformFieldName(itemLabel);
        TreeItem<BaseItemValue> sectionItem = new TreeItem<>(new SectionPageItemValue(itemLabel,
                title, displayComponent));

        sectionItem.getChildren().addAll(childrenItems);
        return sectionItem;
    }
}
