package org.computerspecsviewer.displaytypes.customsingleton;

import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.utils.PrintHelpers;

public class CustomSingleton {
    private BaseInfoQuery target;
    private int tabCount;

    public CustomSingleton(BaseInfoQuery target) {
        this(target, 1);
    }

    public CustomSingleton(BaseInfoQuery target, int tabCount) {
        this.target = target;
        this.tabCount = tabCount;
    }

    public BaseInfoQuery getTarget() {
        return target;
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
