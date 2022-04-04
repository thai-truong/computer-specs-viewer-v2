package org.computerspecsviewer.gui.views.menutree.itemhandlers;

import javafx.scene.control.TreeItem;
import org.computerspecsviewer.gui.viewmodels.InfoQueryViewModel;
import org.computerspecsviewer.gui.views.base.PageComponent;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.PageComponentItemValue;
import org.computerspecsviewer.gui.views.page.infoquery.InfoQueryPageComponent;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.util.tuples.Quartet;

import java.util.*;

public class InfoQueryItemHandler {
    private Map<String, Quartet<String, String, String, BaseInfoQuery>> infoQueryDisplayMap;
    private Map<String, TreeItem<BaseItemValue>> convertedMap = new HashMap<>();

    public InfoQueryItemHandler() {
        InfoQueryViewModel infoQueryViewModel = InfoQueryViewModel.getInstance();
        infoQueryDisplayMap = infoQueryViewModel.getInfoQueryMap();
    }

    public Map<String, TreeItem<BaseItemValue>> getItemsMap() {
        if(convertedMap.size() == 0) {
            for(Map.Entry<String, Quartet<String, String, String, BaseInfoQuery>> entry: infoQueryDisplayMap.entrySet()) {
                String infoQueryType = entry.getKey();
                Quartet<String, String, String, BaseInfoQuery> infoQueryDisplay = entry.getValue();

                String itemLabel = infoQueryDisplay.getA();
                String title = infoQueryDisplay.getB();
                String desc = infoQueryDisplay.getC();
                BaseInfoQuery infoQuery = infoQueryDisplay.getD();

                PageComponent infoQueryPage = new InfoQueryPageComponent(title, desc, infoQuery);
                convertedMap.put(infoQueryType,
                        new TreeItem(new PageComponentItemValue(itemLabel, infoQueryPage)));
            }
        }

        return convertedMap;
    }
}
