package org.computerspecsviewer.gui.views.menutree;

import javafx.scene.control.TreeItem;
import org.computerspecsviewer.gui.views.menutree.itemhandlers.InfoQueryItemHandler;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import oshi.util.tuples.Pair;

import java.util.*;

public class MenuTreeStructure {
    private Map<String, List<String>> sectionGraph;
    private Map<String, TreeItem<BaseItemValue>> leafDisplays;
    private Pair<Map<String, List<String>>, Map<String, TreeItem<BaseItemValue>>> structure;

    private final InfoQueryItemHandler infoQueryHandler;

    public MenuTreeStructure() {
        infoQueryHandler = new InfoQueryItemHandler();
    }

    public Pair<Map<String, List<String>>, Map<String, TreeItem<BaseItemValue>>> get() {
        if(sectionGraph == null || leafDisplays == null) {
            create();
        }

        return structure;
    }

    private void create() {
        setSectionGraph();
        setLeafDisplays();

        structure = new Pair<>(sectionGraph, leafDisplays);
    }

    private void setSectionGraph() {
        sectionGraph = new LinkedHashMap<>();
        sectionGraph.put("systemInformation", Arrays.asList("computerSystem", "cpu", "disks", "memory",
                "graphicsCards", "displayDevices", "networkInterfaces", "powerSources", "soundCards",
                "usbDevices", "operatingSystem", "fileSystem", "networkStatistics"));
        sectionGraph.put("settings", Collections.emptyList());
    }

    private void setLeafDisplays() {
        leafDisplays = new HashMap<>();
        leafDisplays.putAll(infoQueryHandler.getItemsMap());
    }
}
