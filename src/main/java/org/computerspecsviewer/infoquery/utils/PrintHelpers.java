package org.computerspecsviewer.infoquery.utils;

import java.util.List;

public class PrintHelpers {
    public static String getTabsFromCount(int tabCount) {
        StringBuilder tabs = new StringBuilder();

        for(int i = 0; i < tabCount; i++) {
            tabs.append("\t");
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
}
