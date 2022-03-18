package org.computerspecsviewer.displaytypes.space;

import java.util.HashMap;
import java.util.Map;

public class Space {
    private static Map<SpaceUnits, Integer> spaceUnitToExp;

    static {
        spaceUnitToExp = new HashMap<>();
        spaceUnitToExp.put(SpaceUnits.B, 0);
        spaceUnitToExp.put(SpaceUnits.KB, 3);
        spaceUnitToExp.put(SpaceUnits.MB, 6);
        spaceUnitToExp.put(SpaceUnits.GB, 9);
    }

    private long valueInByte;
    private double convVal;
    private SpaceUnits convUnit;

    public Space(long valueInByte) {
        this(valueInByte, SpaceUnits.GB);
    }

    public Space(long valueInByte, SpaceUnits unit) {
        this.valueInByte = valueInByte;
        this.convVal = valueInByte / Math.pow(10, spaceUnitToExp.get(unit));
        this.convUnit = unit;
    }

    @Override
    public String toString() {
        double val = this.convVal;
        SpaceUnits unit = this.convUnit;

        return String.format("%.2f %s", val, unit.getSpaceUnit());
    }
}
