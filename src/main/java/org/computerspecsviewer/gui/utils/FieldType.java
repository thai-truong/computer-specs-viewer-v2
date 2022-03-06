package org.computerspecsviewer.gui.utils;

public enum FieldType {
    NORMAL(0),
    INFOQUERY(1),
    LIST(2);

    private Integer fieldType;

    FieldType(Integer fieldType) { this.fieldType = fieldType; }

    public Integer getFieldType() { return fieldType; }
}
