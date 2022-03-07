package org.computerspecsviewer.gui.mainapp.menutree.sections;

import javafx.scene.control.TreeItem;
import javafx.scene.text.Text;
import org.computerspecsviewer.gui.data.SectionTextDisplay;
import org.computerspecsviewer.gui.views.menutree.itemhandlers.InfoQueryItemHandler;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.SectionPageItemValue;
import org.computerspecsviewer.infoquery.utils.StringHelpers;

import java.util.List;

public class SystemInformationSection implements MenuTreeSection {
    private final String sectionType = "systemInformation";
    private TreeItem<BaseItemValue> sectionItem;

    public SystemInformationSection() {}

    public TreeItem<BaseItemValue> getSection() {
        if(sectionItem == null) {
            createSection();
        }

        return sectionItem;
    }

    private void createSection() {
        SectionTextDisplay sectionTextDisplay = new SectionTextDisplay();
        InfoQueryItemHandler infoQueryHandler = new InfoQueryItemHandler();
        List<TreeItem<BaseItemValue>> infoQueryItems = infoQueryHandler.getItems();

        String label = StringHelpers.transformFieldName(sectionType);
        String description = sectionTextDisplay.get(sectionType);

        sectionItem = new TreeItem<>(new SectionPageItemValue(label, label, new Text(description)));
        sectionItem.getChildren().addAll(infoQueryItems);
    }
}
