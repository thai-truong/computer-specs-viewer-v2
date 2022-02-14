package org.computerspecsviewer.infoquery.utils;

import oshi.util.tuples.Pair;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionHelpers {
    public static List<String> getFieldsAsList(Object target) {
        List<Pair<String, String>> nameValueFields;
        List<String> resList = new ArrayList<>();

        try {
            nameValueFields = ReflectionHelpers.getInfoFieldPairs(target);
        } catch (IllegalAccessException e) {
            return Collections.emptyList();
        }

        for(Pair<String, String> fieldPair: nameValueFields) {
            String name = fieldPair.getA();
            String value = fieldPair.getB();
            String field = String.format("%s: %s", name, value);

            resList.add(field);
        }

        return resList;
    }

    public static Map<String, String> getFieldsAsMap(Object target) {
        List<Pair<String, String>> nameValueFields;
        Map<String, String> resMap = new HashMap<>();

        try {
            nameValueFields = ReflectionHelpers.getInfoFieldPairs(target);
        } catch (IllegalAccessException e) {
            return Collections.emptyMap();
        }

        for(Pair<String, String> fieldPair: nameValueFields) {
            String name = fieldPair.getA();
            String value = fieldPair.getB();

            resMap.put(name, value);
        }

        return resMap;
    }

    private static List<Pair<String, String>> getInfoFieldPairs(Object target) throws IllegalAccessException {
        Field[] targetFields = target.getClass().getDeclaredFields();
        List<Pair<String, String>> nameValueFields = new ArrayList<>();

        for(Field field: targetFields) {
            String fieldName = StringHelpers.transformFieldName(field.getName());
            String fieldValue = field.get(target).toString();

            nameValueFields.add(new Pair<>(fieldName, fieldValue));
        }

        return nameValueFields;
    }
}
