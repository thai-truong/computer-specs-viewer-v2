package org.computerspecsviewer.gui.models.settings;

public enum ConfigLineIndex {
    THEME(0);

    private Integer lineIndex;

    ConfigLineIndex(Integer lineIndex) { this.lineIndex = lineIndex; }

    public Integer getLineIndex() { return lineIndex; }
}
