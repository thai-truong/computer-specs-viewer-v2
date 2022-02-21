package org.computerspecsviewer.infoquery.utils;

import java.util.ArrayList;
import java.util.List;

public class PrintHelpers {
    private static final String TAB = "\t";

    public static String getBitnessString(int bitness) {
        return String.format("%d-bit", bitness);
    }

    public static String getPercentString(double numerator, double denominator) {
        if(denominator == 0) {
            return "No percentage (denominator is 0)";
        }

        return String.format("%.2f%%", (numerator / denominator) * 100);
    }

    public static String getTabsFromCount(int tabCount) {
        StringBuilder tabs = new StringBuilder();

        for(int i = 0; i < tabCount; i++) {
            tabs.append(TAB);
        }

        return tabs.toString();
    }

    public static String injectTabs(String target, int tabCount) {
        String tabbing = getTabsFromCount(tabCount);
        StringBuilder tabbedStr = new StringBuilder(tabbing);

        for(int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            tabbedStr.append(c);

            // Do not add tabs after last newline
            if(c == '\n' && i < target.length() - 1) {
                tabbedStr.append(tabbing);
            }
        }

        return tabbedStr.toString();
    }

    public static List<String> injectNewlines(List<String> target) {
        List<String> newLinedTarget = new ArrayList<>();

        for(String targetStr: target) {
            newLinedTarget.add(targetStr + "\n");
        }

        return newLinedTarget;
    }
}
