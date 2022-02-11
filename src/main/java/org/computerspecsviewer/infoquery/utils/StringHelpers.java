package org.computerspecsviewer.infoquery.utils;

public class StringHelpers {
    public static String transformFieldName(String name) {
        StringBuilder transformed = new StringBuilder();
        int nameLen = name.length();

        if(nameLen > 0) {
            transformed.append(Character.toUpperCase(name.charAt(0)));
        }

        for(int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
        }
    }
}
