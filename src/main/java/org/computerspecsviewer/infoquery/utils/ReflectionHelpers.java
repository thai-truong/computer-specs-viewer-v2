package org.computerspecsviewer.infoquery.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReflectionHelpers {
    public static List<String> getFieldsAsStringList(Object target) {
        List<String> fieldNames;

        try {
            fieldNames = ReflectionHelpers.getInfoFieldNames(target);
        } catch (IllegalAccessException e) {
            return Collections.emptyList();
        }

        return fieldNames;
    }

    public static List<String> getInfoFieldNames(Object target) throws IllegalAccessException {
        Field[] targetFields = target.getClass().getDeclaredFields();
        List<String> strFields = new ArrayList<>();

        for(Field field: targetFields) {
            StringBuilder fieldInfo = new StringBuilder(field.getName());
            fieldInfo.append(": ");
            fieldInfo.append(field.get(target));

            strFields.add(fieldInfo.toString());
        }

        return strFields;
    }
}
