package org.computerspecsviewer.gui.data;

import org.computerspecsviewer.infoquery.utils.StringHelpers;
import oshi.util.tuples.Triplet;

import java.util.HashMap;
import java.util.Map;

public class InfoQueryTextDisplay {
    private Map<String, Triplet<String, String, String>> textDisplayInfoMap;

    public InfoQueryTextDisplay() {
        textDisplayInfoMap = new HashMap<>();

        String compSysDesc = "Contains information about the Computer System";
        String cpuDesc = "Contains information about the Central Processing Unit";
        String diskDesc = "Contains information about the Disk Drives";
        String memDesc = "";
        String graphicsDesc = "";
        String displayDesc = "";
        String networkDesc = "";
        String powerSourceDesc = "";
        String soundDesc = "";
        String usbDesc = "";
        String osDesc = "";
        String fileSystemDesc = "";
        String networkStatsDesc = "";

        textDisplayInfoMap.put("computerSystem", createTextDisplayInfo("computerSystem", compSysDesc, false));
        textDisplayInfoMap.put("cpu", createTextDisplayInfo("cpu", cpuDesc, true));
        textDisplayInfoMap.put("disks", createTextDisplayInfo("disks", diskDesc, false));
        textDisplayInfoMap.put("memory", createTextDisplayInfo("disks", memDesc, false));
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
