package org.computerspecsviewer.displaytypes.frequency;

public enum FreqUnits {
    Hz("Hz"),
    KHz("KHz"),
    MHz("MHz"),
    GHz("GHz");

    private String freqUnit;

    FreqUnits(String freqUnit) {
        this.freqUnit = freqUnit;
    }

    public String getFreqUnit() {
        return freqUnit;
    }
}
