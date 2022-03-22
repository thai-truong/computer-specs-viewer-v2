package org.computerspecsviewer.gui.data;

import org.computerspecsviewer.infoquery.utils.StringHelpers;
import oshi.util.tuples.Triplet;

import java.util.HashMap;
import java.util.Map;

public class InfoQueryTextDisplay {
    private static InfoQueryTextDisplay singleton;
    private Map<String, Triplet<String, String, String>> textDisplayInfoMap;

    private InfoQueryTextDisplay() {
        textDisplayInfoMap = new HashMap<>();

        String compSysDesc = "Contains information about the Computer System";
        String cpuDesc = "Contains information about the Central Processing Unit";
        String diskDesc = "Contains information about the Disk Drives";
        String memDesc = "Contains information about the Memory (both physical and virtual)";
        String graphicsDesc = "Contains information about the Graphics Cards";
        String displayDesc = "Contains information about the Display Device(s)";
        String networkDesc = "Contains information about the Network Interface(s)";
        String powerSourceDesc = "Contains information about the Power Source(s)";
        String soundDesc = "Contains information about the Sound Card(s)";
        String usbDesc = "Contains information about the USB device(s)";
        String osDesc = "Contains information about the Operating System";
        String fileSystemDesc = "Contains information about the File System";
        String networkStatsDesc = "Contains information about the Network Statistics";

        textDisplayInfoMap.put("computerSystem", createTextDisplayInfo("computerSystem", compSysDesc, false));
        textDisplayInfoMap.put("cpu", createTextDisplayInfo("cpu", cpuDesc, true));
        textDisplayInfoMap.put("disks", createTextDisplayInfo("disks", diskDesc, false));
        textDisplayInfoMap.put("memory", createTextDisplayInfo("memory", memDesc, false));
        textDisplayInfoMap.put("graphicsCards", createTextDisplayInfo("graphicsCards", graphicsDesc, false));
        textDisplayInfoMap.put("displayDevices", createTextDisplayInfo("display", displayDesc, false));
        textDisplayInfoMap.put("networkInterfaces", createTextDisplayInfo("networkInterfaces", networkDesc, false));
        textDisplayInfoMap.put("powerSources", createTextDisplayInfo("powerSources", powerSourceDesc, false));
        textDisplayInfoMap.put("soundCards", createTextDisplayInfo("soundCards", soundDesc, false));
        textDisplayInfoMap.put("usbDevices", createTextDisplayInfo("usbDevices", usbDesc, false));
        textDisplayInfoMap.put("operatingSystem", createTextDisplayInfo("os", osDesc, true));
        textDisplayInfoMap.put("fileSystem", createTextDisplayInfo("fileSystem", fileSystemDesc, false));
        textDisplayInfoMap.put("networkStatistics", createTextDisplayInfo("networkStatistics", networkStatsDesc, false));
    }

    public static synchronized InfoQueryTextDisplay getInstance() {
        if(singleton == null) {
            singleton = new InfoQueryTextDisplay();
        }

        return singleton;
    }

    public boolean found(String infoType) {
        return textDisplayInfoMap.containsKey(infoType);
    }

    public Triplet<String, String, String> get(String infoType) {
        return textDisplayInfoMap.get(infoType);
    }

    private String createInfoTitle(String base) {
        return base + " Info:";
    }

    private Triplet<String, String, String> createTextDisplayInfo(String infoKey, String desc, boolean capLabel) {
        String label = StringHelpers.transformFieldName(infoKey);

        if(capLabel) {
            label = label.toUpperCase();
        }

        return new Triplet<>(label, createInfoTitle(label), desc);
    }
}
