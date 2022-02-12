package org.computerspecsviewer.displaytypes.frequency;

import java.util.HashMap;
import java.util.Map;

public class Frequency {
    private static Map<FreqUnits, Integer> freqUnitToExp;

    static {
        freqUnitToExp = new HashMap<>();
        freqUnitToExp.put(FreqUnits.Hz, 0);
        freqUnitToExp.put(FreqUnits.KHz, 3);
        freqUnitToExp.put(FreqUnits.MHz, 6);
        freqUnitToExp.put(FreqUnits.GHz, 9);
    }

    public long valueAtHz;
    public double convVal;
    public String convUnit;

    public Frequency(long valueAtHz) {
        this(valueAtHz, FreqUnits.MHz);
    }

    public Frequency(long valueAtHz, FreqUnits unit) {
        this.valueAtHz = valueAtHz;
        this.convVal = valueAtHz / Math.pow(10, freqUnitToExp.get(unit));
        this.convUnit = unit.getFreqUnit();
    }

    @Override
    public String toString() {
        double val = this.convVal;
        String unit = this.convUnit;

        return String.format("%.2f %s", val, unit);
    }
}
