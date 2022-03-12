package org.computerspecsviewer.gui.views.menutree.itemhandlers;

import javafx.scene.control.TreeItem;
import org.computerspecsviewer.gui.viewmodels.InfoQueryViewModel;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.InfoQueryPageItemValue;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.util.tuples.Quartet;

import java.util.*;

public class InfoQueryItemHandler {
    private Map<String, Quartet<String, String, String, BaseInfoQuery>> infoQueryDisplayMap;
    private List<TreeItem<BaseItemValue>> converted = new ArrayList<>();
    private Map<String, TreeItem<BaseItemValue>> convertedMap = new HashMap<>();

    public InfoQueryItemHandler() {
        InfoQueryViewModel infoQueryViewModel = InfoQueryViewModel.getInstance();
        infoQueryDisplayMap = infoQueryViewModel.getInfoQueryMap();
    }

    public List<TreeItem<BaseItemValue>> getItems() {
        if(converted.size() == 0) {
            for(Quartet<String, String, String, BaseInfoQuery> elem: infoQueryDisplayMap.values()) {
                String treeLabel = elem.getA();
                String title = elem.getB();
                String desc = elem.getC();
                BaseInfoQuery infoQuery = elem.getD();

                converted.add(new TreeItem(new InfoQueryPageItemValue(treeLabel, title, desc, infoQuery)));
            }
        }

        return converted;
    }

    public Map<String, TreeItem<BaseItemValue>> getItemsMap() {
        if(convertedMap.size() == 0) {
            for(Map.Entry<String, Quartet<String, String, String, BaseInfoQuery>> entry: infoQueryDisplayMap.entrySet()) {
                String infoQueryType = entry.getKey();
                Quartet<String, String, String, BaseInfoQuery> infoQueryDisplay = entry.getValue();

                String treeLabel = infoQueryDisplay.getA();
                String title = infoQueryDisplay.getB();
                String desc = infoQueryDisplay.getC();
                BaseInfoQuery infoQuery = infoQueryDisplay.getD();

                convertedMap.put(infoQueryType,
                        new TreeItem(new InfoQueryPageItemValue(treeLabel, title, desc, infoQuery)));
            }
        }

        return convertedMap;
    }
}
