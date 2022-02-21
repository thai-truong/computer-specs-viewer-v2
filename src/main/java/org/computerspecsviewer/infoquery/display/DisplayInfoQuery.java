package org.computerspecsviewer.infoquery.display;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.hardware.Display;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.ArrayList;
import java.util.List;

public class DisplayInfoQuery extends BaseInfoQuery {
    public CustomList<DisplayInfo> displayDevices;

    public DisplayInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        HardwareAbstractionLayer hal = sysInfo.getHardware();

        List<Display> displayList = hal.getDisplays();
        List<DisplayInfo> displayInfoList = new ArrayList<>();

        for(Display display: displayList) {
            displayInfoList.add(new DisplayInfo(display));
        }

        displayDevices = new CustomList<>(displayInfoList, "Display Device", false);
    }

    @Override
    public String toString() {
        return displayDevices.toString();
    }

    public static void main(String[] args) {
        DisplayInfoQuery test = new DisplayInfoQuery();
        System.out.println(test);
    }
}
