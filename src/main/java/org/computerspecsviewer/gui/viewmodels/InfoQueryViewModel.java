package org.computerspecsviewer.gui.viewmodels;

import org.computerspecsviewer.gui.data.InfoQueryTextDisplay;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.controller.InfoQueryController;
import oshi.util.tuples.Quartet;
import oshi.util.tuples.Triplet;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InfoQueryViewModel {
    private static InfoQueryViewModel singleton;
    private Map<String, Quartet<String, String, String, BaseInfoQuery>> infoQueryMap;

    private InfoQueryViewModel() {
        infoQueryMap = new LinkedHashMap<>();

        InfoQueryTextDisplay infoQueryTextDisplay = new InfoQueryTextDisplay();
        InfoQueryController infoQueryController = new InfoQueryController();
        List<String> infoQueryTypes = infoQueryController.getInfoQueryTypes();

        for(String type: infoQueryTypes) {
            if(!infoQueryTextDisplay.found(type)) {
                continue;
            }

            Triplet<String, String, String> textDisplay = infoQueryTextDisplay.get(type);
            BaseInfoQuery infoQuery = infoQueryController.get(type);

            infoQueryMap.put(type, new Quartet<>(textDisplay.getA(), textDisplay.getB(),
                    textDisplay.getC(), infoQuery));
        }
    }

    public static synchronized InfoQueryViewModel getInstance() {
        if(singleton == null) {
            singleton = new InfoQueryViewModel();
        }

        return singleton;
    }

    public Map<String, Quartet<String, String, String, BaseInfoQuery>> getInfoQueryMap() {
        return infoQueryMap;
    }
}
