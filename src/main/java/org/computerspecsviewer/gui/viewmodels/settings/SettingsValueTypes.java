package org.computerspecsviewer.gui.viewmodels.settings;

public enum SettingsValueTypes {
    THEME("theme");

    private String settingsType;

    SettingsValueTypes(String settingsType) { this.settingsType = settingsType; }

    public String getStr() { return settingsType; }
}
