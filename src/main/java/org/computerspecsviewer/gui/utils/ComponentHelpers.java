package org.computerspecsviewer.gui.utils;

public class ComponentHelpers {
    public static FieldType getFieldType(Object target) {
        String className = target.getClass().getSimpleName();
        String superclassName = target.getClass().getSuperclass().getSimpleName();
        FieldType targetFieldType;

        if(className.equals("CustomList")) {
            targetFieldType = FieldType.LIST;
        } else if(superclassName.equals("BaseInfoQuery")) {
            targetFieldType = FieldType.INFOQUERY;
        } else {
            targetFieldType = FieldType.NORMAL;
        }

        return targetFieldType;
    }
}
