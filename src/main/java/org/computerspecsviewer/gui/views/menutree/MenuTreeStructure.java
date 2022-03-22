package org.computerspecsviewer.gui.views.menutree;

import javafx.scene.Node;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.computerspecsviewer.gui.views.menutree.itemhandlers.InfoQueryItemHandler;
import org.computerspecsviewer.gui.views.menutree.itemvalue.BaseItemValue;
import org.computerspecsviewer.gui.views.sections.customdisplay.SystemInformationSectionDisplay;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;

import java.util.*;

public class MenuTreeStructure {
    private Map<String, List<String>> sectionGraph;
    private Map<String, TreeItem<BaseItemValue>> leafItems;
    private Map<String, Node> sectionDisplays;

    private Triplet<Map<String, List<String>>, Map<String, TreeItem<BaseItemValue>>, Map<String, Node>> structure;
    private MultipleSelectionModel<TreeItem<BaseItemValue>> treeSelectionModel;

    private final InfoQueryItemHandler infoQueryHandler;

    public MenuTreeStructure(MultipleSelectionModel<TreeItem<BaseItemValue>> treeSelectionModel) {
        this.treeSelectionModel = treeSelectionModel;
        infoQueryHandler = new InfoQueryItemHandler();

        sectionGraph = new LinkedHashMap<>();
        sectionGraph.put("systemInformation", Arrays.asList("computerSystem", "cpu", "disks", "memory",
                "graphicsCards", "displayDevices", "networkInterfaces", "powerSources", "soundCards",
                "usbDevices", "operatingSystem", "fileSystem", "networkStatistics"));
        sectionGraph.put("settings", Collections.emptyList());
    }

    public Triplet<Map<String, List<String>>, Map<String, TreeItem<BaseItemValue>>, Map<String, Node>> get() {
        if(sectionGraph == null || leafItems == null || sectionDisplays == null) {
            create();
        }

        return structure;
    }

    private void create() {
        setLeafItems();
        setSectionDisplays();

        structure = new Triplet<>(sectionGraph, leafItems, sectionDisplays);
    }

    private void setLeafItems() {
        leafItems = new HashMap<>();
        leafItems.putAll(infoQueryHandler.getItemsMap());
    }

    private void setSectionDisplays() {
        sectionDisplays = new HashMap<>();

        String sysInfo = "systemInformation";
        SystemInformationSectionDisplay sysInfoDisplay = new SystemInformationSectionDisplay(
                sectionGraph.get(sysInfo),
                infoQueryHandler,
                treeSelectionModel
        );

        sectionDisplays.put(sysInfo, sysInfoDisplay.get());
    }
}
