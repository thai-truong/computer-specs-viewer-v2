package org.computerspecsviewer.infoquery.base;

import org.computerspecsviewer.infoquery.utils.ReflectionHelpers;
import org.computerspecsviewer.infoquery.utils.StringHelpers;

import java.util.List;
import java.util.Map;

public abstract class BaseInfoQuery {
    public List<String> getFields() { return ReflectionHelpers.getFieldsAsList(this); }

    public Map<String, String> getFieldsAsMap() { return ReflectionHelpers.getFieldsAsMap(this); }

    @Override
    public String toString() {
        List<String> fields = getFields();
        return StringHelpers.reduceStrList(fields);
    }
}
