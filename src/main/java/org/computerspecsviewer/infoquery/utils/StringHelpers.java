package org.computerspecsviewer.infoquery.utils;

import java.util.List;

public class StringHelpers {
    public static String reduceStrList(List<String> sl) {
        StringBuilder reduction = new StringBuilder();

        for(String s: sl) {
            reduction.append(s + "\n");
        }

        return reduction.toString();
    }

    public static String transformFieldName(String name) {
        StringBuilder transformed = new StringBuilder();
        int nameLen = name.length();

        if(isAllUppercase(name)) {
            return name;
        }

        if(nameLen > 0) {
            transformed.append(Character.toUpperCase(name.charAt(0)));
        }

        String toAdd = "";
        int currIdx = 1;
        int incrAmt;

        while(currIdx < nameLen) {
            char c = name.charAt(currIdx);

            if(shouldSpaceOut(c)) {
                toAdd = charToString(c, true);
                incrAmt = 1;
            } else if(Character.isDigit(c)) {
                toAdd = processNumSubstring(name, currIdx);
                incrAmt = toAdd.length();
            } else {
                toAdd = charToString(c, false);
                incrAmt = 1;
            }

            transformed.append(toAdd);
            currIdx += incrAmt;
        }

        return transformed.toString();
    }

    private static String charToString(char c, boolean prependSpace) {
        String space = "";

        if(prependSpace) {
            space = " ";
        }

        return String.format("%s%c", space, c);
    }

    private static String processNumSubstring(String target, int currIdx) {
        StringBuilder num = new StringBuilder();

        for(int i = currIdx; i < target.length(); i++) {
            char c = target.charAt(i);

            if(Character.isDigit(c)) {
                num.append(c);
            } else {
                break;
            }
        }

        return num.toString();
    }

    private static boolean shouldSpaceOut(char c) {
        return Character.isUpperCase(c);
    }

    private static boolean isAllUppercase(String target) {
        for(int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);

            if(!Character.isUpperCase(c)) {
                return false;
            }
        }

        return true;
    }
}
