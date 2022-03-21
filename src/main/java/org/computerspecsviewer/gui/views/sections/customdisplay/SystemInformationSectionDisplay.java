package org.computerspecsviewer.gui.views.sections.customdisplay;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.computerspecsviewer.gui.data.SectionTextDisplay;
import org.computerspecsviewer.gui.views.menutree.itemhandlers.InfoQueryItemHandler;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;

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
        VBox contentBox = new VBox();

        contentBox.getChildren().addAll(
                createSectionTextDisplay(),
                createQuickAccessButtons()
        );

        displayComponent = contentBox;
    }

    private Node createSectionTextDisplay() {
        return new Text(sectionText);
    }

    private Node createQuickAccessButtons() {
        VBox buttonContainer = new VBox();
        List<Node> quickAccessButtons = new ArrayList<>();

        for(String infoType: systemInfoTypes) {
            TreeItem<BaseItemValue> infoItem = systemInfoItems.get(infoType);

            Button button = new Button(infoItem.toString());
            button.setOnAction((event) -> treeSelectionModel.select(infoItem));
            quickAccessButtons.add(button);
        }

        buttonContainer.getChildren().addAll(quickAccessButtons);

        return buttonContainer;
    }
}
