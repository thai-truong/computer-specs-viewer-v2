package org.computerspecsviewer.gui.viewmodels.settings;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.computerspecsviewer.gui.models.settings.SettingsModel;

import java.util.Map;

public class SettingsViewModel {
    private SettingsModel settingsModel;
    private StringProperty theme;

    public SettingsViewModel() {
        settingsModel = SettingsModel.getInstance();

        String themeTypeStr = SettingsValueTypes.THEME.getStr();
        theme = new SimpleStringProperty(settingsModel.getSettingsValue(themeTypeStr));
    }

    public String getTheme() {
        return theme.get();
    }

    public StringProperty getThemeProperty() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme.set(theme);
    }

    public void saveSettings(Map<String, String> changes) {
        settingsModel.saveSettingsChanges(changes);
    }
}
