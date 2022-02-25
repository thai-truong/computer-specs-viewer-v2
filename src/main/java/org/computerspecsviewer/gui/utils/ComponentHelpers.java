package org.computerspecsviewer.gui.utils;

public class ComponentHelpers {
    public static FieldType getFieldType(Object target) {
        String targetClassName = target.getClass().getSimpleName();
        FieldType targetFieldType;

        switch(targetClassName) {
            case "CustomList":
                targetFieldType = FieldType.CUSTOMLIST;
                break;
            case "CustomSingleton":
                targetFieldType = FieldType.INFOQUERY;
                break;
            default:
                targetFieldType = FieldType.NORMAL;
                break;
        }

        return targetFieldType;
    }
}
