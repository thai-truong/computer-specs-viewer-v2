package org.computerspecsviewer.infoquery.base;

import org.computerspecsviewer.infoquery.utils.PrintHelpers;
import org.computerspecsviewer.infoquery.utils.ReflectionHelpers;
import org.computerspecsviewer.infoquery.utils.StringHelpers;

import java.util.List;
import java.util.Map;

public abstract class BaseInfoQuery {
    public List<String> getFields() { return ReflectionHelpers.getFieldsAsList(this); }

    public Map<String, String> getFieldsAsMap() { return ReflectionHelpers.getFieldsAsMap(this); }

    public Map<String, Object> getRawFieldsAsMap() { return ReflectionHelpers.getRawFieldsAsMap(this); }

    @Override
    public String toString() {
        List<String> fields = getFields();
        StringBuilder infoQueryStr = new StringBuilder();
        String fieldsStr = StringHelpers.reduceStrList(fields);

        String start = "{\n";
        String end = "}";
        String tabbedFields = PrintHelpers.injectTabs(fieldsStr, 1);

        infoQueryStr.append(start);
        infoQueryStr.append(tabbedFields);
        infoQueryStr.append(end);

        return infoQueryStr.toString();
    }
}
