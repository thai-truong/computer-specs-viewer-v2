package org.computerspecsviewer.gui.views.menutree.treeitemhandlers;

import javafx.scene.control.TreeItem;
import org.computerspecsviewer.gui.viewmodels.InfoQueryViewModel;
import org.computerspecsviewer.gui.views.menutree.treeitemvalue.BaseTreeItemValue;
import org.computerspecsviewer.gui.views.menutree.treeitemvalue.InfoQueryPageTreeItemValue;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.util.tuples.Quartet;

import java.util.ArrayList;
import java.util.List;

public class InfoQueryTreeHandler {
    public static List<TreeItem<BaseTreeItemValue>> getTreeItems() {
        InfoQueryViewModel infoQueryViewModel = InfoQueryViewModel.getInstance();
        List<TreeItem<BaseTreeItemValue>> converted = new ArrayList<>();

        for(Quartet<String, String, String, BaseInfoQuery> elem: infoQueryViewModel.getInfoQueryMap().values()) {
            String treeLabel = elem.getA();
            String title = elem.getB();
            String desc = elem.getC();
            BaseInfoQuery infoQuery = elem.getD();

            converted.add(new TreeItem(new InfoQueryPageTreeItemValue(treeLabel, title, desc, infoQuery)));
        }

        return converted;
    }
}
