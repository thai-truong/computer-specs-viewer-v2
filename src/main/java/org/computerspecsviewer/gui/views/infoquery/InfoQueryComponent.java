package org.computerspecsviewer.gui.views.infoquery;

import org.computerspecsviewer.infoquery.base.BaseInfoQuery;

import java.util.Map;

public class InfoQueryComponent {
    Map<String, Object> rawFieldsMap;

    public InfoQueryComponent(BaseInfoQuery infoQuery) {
        rawFieldsMap = infoQuery.getRawFieldsAsMap();
    }
}
