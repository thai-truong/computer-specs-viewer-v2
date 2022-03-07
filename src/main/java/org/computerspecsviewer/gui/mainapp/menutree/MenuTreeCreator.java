package org.computerspecsviewer.gui.mainapp.menutree;

import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import org.computerspecsviewer.gui.mainapp.menutree.sections.MenuTreeSection;
import org.computerspecsviewer.gui.mainapp.menutree.sections.SystemInformationSection;
import org.computerspecsviewer.gui.views.menutree.MenuTreeComponent;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;

import java.util.ArrayList;
import java.util.List;

public class MenuTreeCreator {
    private MenuTreeComponent menuTree;
    private BorderPane appDisplaySection;

    public MenuTreeCreator(BorderPane appDisplaySection) {
        this.appDisplaySection = appDisplaySection;
    }

    public MenuTreeComponent getMenuTree() {
        if(menuTree == null) {
            createMenuTree();
        }

        return menuTree;
    }

    private void createMenuTree() {
        menuTree = new MenuTreeComponent(appDisplaySection);

        List<TreeItem<BaseItemValue>> topLevelSections = createTopLevelSections();
        menuTree.setRootChildren(topLevelSections);
    }

    private List<TreeItem<BaseItemValue>> createTopLevelSections() {
        List<TreeItem<BaseItemValue>> topLevelSections = new ArrayList<>();

        MenuTreeSection systemInformation = new SystemInformationSection();
        topLevelSections.add(systemInformation.getSection());

        return topLevelSections;
    }
}
