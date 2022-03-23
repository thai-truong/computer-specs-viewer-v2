package org.computerspecsviewer.gui.views.page.sections.customdisplay;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.computerspecsviewer.gui.data.SectionTextDisplay;
import org.computerspecsviewer.gui.views.menutree.itemhandlers.InfoQueryItemHandler;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.infoquery.utils.StringHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemInformationSectionDisplay {
    private final List<String> systemInfoTypes;
    private final Map<String, TreeItem<BaseItemValue>> systemInfoItems;
    private final String sectionText;

    private final MultipleSelectionModel<TreeItem<BaseItemValue>> treeSelectionModel;
    private Node displayComponent;

    public SystemInformationSectionDisplay(
            List<String> systemInfoTypes,
            InfoQueryItemHandler infoQueryHandler,
            MultipleSelectionModel<TreeItem<BaseItemValue>> treeSelectionModel
    ) {
        this.systemInfoTypes = systemInfoTypes;
        this.treeSelectionModel = treeSelectionModel;

        systemInfoItems = infoQueryHandler.getItemsMap();
        sectionText = SectionTextDisplay.getInstance().get("systemInformation");
    }

    public Node get() {
        if(displayComponent == null) {
            create();
        }

        return displayComponent;
    }

    private void create() {
        VBox contentBox = new VBox(10);

        contentBox.getChildren().addAll(
                createSectionTextDisplay(),
                createQuickAccessLinks()
        );

        displayComponent = contentBox;
    }

    private Node createSectionTextDisplay() {
        return new Text(sectionText);
    }

    private Node createQuickAccessLinks() {
        VBox linkContainer = new VBox(3.0);

        List<Node> quickAccessLinks = new ArrayList<>();
        quickAccessLinks.add(new Text("Here are the quick access links:"));

        for(String infoType: systemInfoTypes) {
            TreeItem<BaseItemValue> infoItem = systemInfoItems.get(infoType);

            Hyperlink link = new Hyperlink(StringHelpers.transformFieldName(infoType));
            link.setOnAction((event) -> treeSelectionModel.select(infoItem));

            quickAccessLinks.add(link);
        }

        linkContainer.getChildren().addAll(quickAccessLinks);

        return linkContainer;
    }
}
