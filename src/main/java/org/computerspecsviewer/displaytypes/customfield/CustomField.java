package org.computerspecsviewer.displaytypes.customfield;

public class CustomField<T> {
    private T fieldValue;
    private String fieldInfo;

    public CustomField(T fieldValue, String fieldInfo) {
        this.fieldValue = fieldValue;
        this.fieldInfo = fieldInfo;
    }

    public T getFieldValue() {
        return fieldValue;
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
