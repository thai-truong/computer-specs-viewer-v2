package org.computerspecsviewer.gui.viewmodels.settings;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.computerspecsviewer.gui.models.settings.SettingsModel;

import java.util.Map;

public class SettingsViewModel {
    private static SettingsViewModel singleton;

    private SettingsModel settingsModel;
    private StringProperty theme;

    private SettingsViewModel() {
        settingsModel = SettingsModel.getInstance();

        String themeTypeStr = SettingsValueTypes.THEME.getStr();
        theme = new SimpleStringProperty(settingsModel.getSettingsValue(themeTypeStr));
    }

    public static synchronized SettingsViewModel getInstance() {
        if(singleton == null) {
            singleton = new SettingsViewModel();
        }

        return singleton;
    }

    public String getTheme() {
        return theme.get();
    }

    public StringProperty getThemeProperty() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme.set(theme);
        saveSettings(SettingsValueTypes.THEME, theme);
    }

    public void saveSettings(Map<String, String> changes) {
        settingsModel.saveSettingsChanges(changes);
    }

    public void saveSettings(SettingsValueTypes settingsType, String newValue) {
        settingsModel.saveSettingsChange(settingsType.getStr(), newValue);
    }
}
