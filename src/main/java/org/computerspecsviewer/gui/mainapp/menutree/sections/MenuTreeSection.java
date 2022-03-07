package org.computerspecsviewer.gui.mainapp.menutree.sections;

import javafx.scene.control.TreeItem;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;

public interface MenuTreeSection {
    TreeItem<BaseItemValue> getSection();
}
