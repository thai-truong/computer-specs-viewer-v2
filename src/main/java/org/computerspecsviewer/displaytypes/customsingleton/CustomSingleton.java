package org.computerspecsviewer.displaytypes.customsingleton;

import org.computerspecsviewer.infoquery.utils.PrintHelpers;

public class CustomSingleton<T> {
    public T target;
    public int tabCount;

    public CustomSingleton(T target) {
        this(target, 1);
    }

    public CustomSingleton(T target, int tabCount) {
        this.target = target;
        this.tabCount = tabCount;
    }

    @Override
    public String toString() {
        StringBuilder prettyPrintedObj = new StringBuilder();

        String start = "{\n";
        String end = "}";
        String tabbedObj = PrintHelpers.injectTabs(target.toString(), tabCount);

        prettyPrintedObj.append(start);
        prettyPrintedObj.append(tabbedObj);
        prettyPrintedObj.append(end);

        return prettyPrintedObj.toString();
    }
}
