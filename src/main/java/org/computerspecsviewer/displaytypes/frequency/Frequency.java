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

    public long valueInHz;
    public double convVal;
    public FreqUnits convUnit;

    public Frequency(long valueAtHz) {
        this(valueAtHz, FreqUnits.MHz);
    }

    public Frequency(long valueInHz, FreqUnits unit) {
        this.valueInHz = valueInHz;
        this.convVal = valueInHz / Math.pow(10, freqUnitToExp.get(unit));
        this.convUnit = unit;
    }

    @Override
    public String toString() {
        double val = this.convVal;
        FreqUnits unit = this.convUnit;

        return String.format("%.2f %s", val, unit.getFreqUnit());
    }
}
