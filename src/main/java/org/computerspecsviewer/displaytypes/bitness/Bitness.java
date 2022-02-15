package org.computerspecsviewer.displaytypes.bitness;

public class Bitness {
    public int bitAmount;

    public Bitness(int bitAmount) {
        this.bitAmount = bitAmount;
    }

    @Override
    public String toString() {
        return String.format("%d-bit", bitAmount);
    }
}
