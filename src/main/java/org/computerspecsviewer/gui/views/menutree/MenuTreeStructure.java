package org.computerspecsviewer.gui.views.menutree;

import javafx.scene.control.TreeItem;
import org.computerspecsviewer.gui.views.menutree.itemhandlers.InfoQueryItemHandler;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.page.settings.SettingsPages;
import oshi.util.tuples.Triplet;

import java.util.*;

public class MenuTreeStructure {
    private Map<String, List<String>> sectionGraph;
    private Map<String, TreeItem<BaseItemValue>> leafItems;
    private Map<String, Boolean> toDisplayLinks;

    private Triplet<Map<String, List<String>>, Map<String, TreeItem<BaseItemValue>>, Map<String, Boolean>> structure;

    private final InfoQueryItemHandler infoQueryHandler;

    public MenuTreeStructure() {
        infoQueryHandler = new InfoQueryItemHandler();
    }

    public Triplet<Map<String, List<String>>, Map<String, TreeItem<BaseItemValue>>, Map<String, Boolean>> get() {
        if(sectionGraph == null || leafItems == null || toDisplayLinks == null) {
            create();
        }

        return structure;
    }

    private void create() {
        setSectionGraph();
        setLeafItems();
        setToDisplayLinks();

        structure = new Triplet<>(sectionGraph, leafItems, toDisplayLinks);
    }

    private void setSectionGraph() {
        sectionGraph = new LinkedHashMap<>();
        sectionGraph.put("systemInformation", Arrays.asList("computerSystem", "cpu", "disks", "memory",
                "graphicsCards", "displayDevices", "networkInterfaces", "powerSources", "soundCards",
                "usbDevices", "operatingSystem", "fileSystem", "networkStatistics"));
        sectionGraph.put("settings", Arrays.asList("themeSettings"));
    }

    private void setLeafItems() {
        leafItems = new HashMap<>();
        leafItems.putAll(infoQueryHandler.getItemsMap());

        SettingsPages settingsPages = SettingsPages.getInstance();
        leafItems.putAll(settingsPages.get());
    }

    private void setToDisplayLinks() {
        toDisplayLinks = new HashMap<>();
        toDisplayLinks.put("systemInformation", true);
        toDisplayLinks.put("settings", true);
    }
}
