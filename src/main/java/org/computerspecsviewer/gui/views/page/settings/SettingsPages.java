package org.computerspecsviewer.gui.views.page.settings;

import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import org.computerspecsviewer.gui.views.base.PageComponent;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.PageComponentItemValue;
import org.computerspecsviewer.gui.views.page.settings.theme.ThemeSettingsPageComponent;
import org.computerspecsviewer.infoquery.utils.StringHelpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsPages {
    private static SettingsPages singleton;
    private Map<String, TreeItem<BaseItemValue>> settingsPages;
    private List<SettingsPageComponent> settingsPageComponents;

    private SettingsPages() {}

    public static synchronized SettingsPages getInstance() {
        if(singleton == null) {
            singleton = new SettingsPages();
        }

        return singleton;
    }

    public Map<String, TreeItem<BaseItemValue>> get() {
        if(settingsPages == null) {
            create();
        }

        return settingsPages;
    }

    private void create() {
        settingsPages = new HashMap<>();
        settingsPageComponents = new ArrayList<>();

        String themeLabel = "themeSettings";
        String themeTitle = StringHelpers.transformFieldName(themeLabel);
        PageComponent themePage = new ThemeSettingsPageComponent(themeTitle);
        TreeItem<BaseItemValue> themePageItem = new TreeItem<>(new PageComponentItemValue(themeTitle, themePage));
        themePage.get();

        settingsPages.put(themeLabel, themePageItem);
        settingsPageComponents.add((SettingsPageComponent) themePage);
    }

    public void initializeAllSettings(Scene scene) {
        for(SettingsPageComponent page: settingsPageComponents) {
            page.initWithPrevSaved(scene);
        }
    }
}
