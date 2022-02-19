package org.computerspecsviewer.displaytypes.customnumber;

public enum CustomNumberUnits {
    NONE(""),
    K("k"),
    M("m"),
    B("b"),
    T("t");

    private String customNumberUnit;

    CustomNumberUnits(String customNumberUnit) { this.customNumberUnit = customNumberUnit; }

    public String getCustomNumberUnit() {
        return customNumberUnit;
    }
}
