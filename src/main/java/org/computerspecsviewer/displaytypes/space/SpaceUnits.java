package org.computerspecsviewer.displaytypes.space;

public enum SpaceUnits {
    B("B"),
    KB("KB"),
    MB("MB"),
    GB("GB");

    private String spaceUnit;

    SpaceUnits(String spaceUnit) {
        this.spaceUnit = spaceUnit;
    }

    public String getSpaceUnit() {
        return spaceUnit;
    }
}
