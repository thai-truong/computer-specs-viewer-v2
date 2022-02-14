package org.computerspecsviewer.displaytypes.customlist;

import org.computerspecsviewer.infoquery.utils.PrintHelpers;

import java.util.List;

public class CustomList<T> {
    public List<T> targetList;
    public String elemTitle;
    public int index;
    public boolean printBrackets;
    public int tabCount;

    public CustomList(List<T> targetList, String elemTitle, boolean printBrackets) {
        this(targetList, elemTitle, printBrackets, 0);
    }

    public CustomList(List<T> targetList, String elemTitle, boolean printBrackets, int tabCount) {
        this.targetList = targetList;
        this.elemTitle = elemTitle;
        this.index = 1;
        this.printBrackets = printBrackets;
        this.tabCount = tabCount;
    }

    @Override
    public String toString() {
        StringBuilder prettyPrintedList = new StringBuilder();
        String start = "";
        String end = "";
        int titleTabCount = tabCount + 1;
        int elemTabbing = tabCount + 2;

        if(printBrackets) {
            start = PrintHelpers.injectTabs("[\n", tabCount);
            end = PrintHelpers.injectTabs("]", tabCount);
        }

        prettyPrintedList.append(start);

        for(int i = 0; i < targetList.size(); i++) {
            T elem = targetList.get(i);

            String titleStart = String.format("%s %d: {\n", elemTitle, index);
            String titleEnd = "}";

            if(i == targetList.size() - 1) {
                titleEnd += "\n";
            } else {
                titleEnd += ",\n";
            }

            String tabbedStart = PrintHelpers.injectTabs(titleStart, titleTabCount);
            String tabbedEnd = PrintHelpers.injectTabs(titleEnd, titleTabCount);
            String tabbedElem = PrintHelpers.injectTabs(elem.toString(), elemTabbing);

            prettyPrintedList.append(tabbedStart);
            prettyPrintedList.append(tabbedElem);
            prettyPrintedList.append(tabbedEnd);

            index++;
        }

        prettyPrintedList.append(end);

        return prettyPrintedList.toString();
    }
}
