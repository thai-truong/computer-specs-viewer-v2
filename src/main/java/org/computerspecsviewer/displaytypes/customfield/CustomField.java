package org.computerspecsviewer.displaytypes.customfield;

public class CustomField<T> {
    public T fieldValue;
    public String fieldInfo;

    public CustomField(T fieldValue, String fieldInfo) {
        this.fieldValue = fieldValue;
        this.fieldInfo = fieldInfo;
    }

    @Override
    public String toString() {
        String info = fieldInfo;

        if(!info.isEmpty()) {
            info = " " + info;
        }

        return fieldValue + info;
    }
}
