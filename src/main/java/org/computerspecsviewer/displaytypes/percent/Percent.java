package org.computerspecsviewer.displaytypes.percent;

public class Percent {
    public double decimalValue;

    public Percent(double decimalValue) {
        this.decimalValue = decimalValue;
    }

    @Override
    public String toString() {
        return String.format("%.2f%%", decimalValue * 100);
    }
}
