package org.computerspecsviewer.gui.views.menutree.itemhandlers;

import javafx.scene.control.TreeItem;
import org.computerspecsviewer.gui.viewmodels.InfoQueryViewModel;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.menutree.itemvalue.InfoQueryPageItemValue;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.util.tuples.Quartet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InfoQueryItemHandler {
    private Collection<Quartet<String, String, String, BaseInfoQuery>> infoQueryDisplays;
    private List<TreeItem<BaseItemValue>> converted = new ArrayList<>();

    public InfoQueryItemHandler() {
        InfoQueryViewModel infoQueryViewModel = InfoQueryViewModel.getInstance();
        infoQueryDisplays = infoQueryViewModel.getInfoQueryMap().values();
    }

    public List<TreeItem<BaseItemValue>> getItems() {
        if(converted.size() == 0) {
            for(Quartet<String, String, String, BaseInfoQuery> elem: infoQueryDisplays) {
                String treeLabel = elem.getA();
                String title = elem.getB();
                String desc = elem.getC();
                BaseInfoQuery infoQuery = elem.getD();

                converted.add(new TreeItem(new InfoQueryPageItemValue(treeLabel, title, desc, infoQuery)));
            }
        }

        return converted;
    }
}
