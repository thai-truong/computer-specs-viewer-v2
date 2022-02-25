package org.computerspecsviewer.displaytypes.customboolean;

public class CustomBoolean {
    private boolean value;
    private String trueStr;
    private String falseStr;

    public CustomBoolean(boolean value) {
        this(value, "Yes", "No");
    }

    public CustomBoolean(boolean value, String trueStr, String falseStr) {
        this.value = value;
        this.trueStr = trueStr;
        this.falseStr = falseStr;
    }

    @Override
    public String toString() {
        if(value) {
            return trueStr;
        }

        return falseStr;
    }
}
