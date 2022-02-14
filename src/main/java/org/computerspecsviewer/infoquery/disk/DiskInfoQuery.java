package org.computerspecsviewer.infoquery.disk;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.ArrayList;
import java.util.List;

public class DiskInfoQuery extends BaseInfoQuery {
    public CustomList<DiskInfo> customDisksInfo;

    public DiskInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        HardwareAbstractionLayer hal = sysInfo.getHardware();

        List<HWDiskStore> disks = hal.getDiskStores();
        List<DiskInfo> disksInfo = new ArrayList<>();

        for(HWDiskStore disk: disks) {
            disksInfo.add(new DiskInfo(disk));
        }

        customDisksInfo = new CustomList<>(disksInfo, "Disk", false);
    }

    public String toString() {
        return customDisksInfo.toString();
    }

    public static void main(String[] args) {
        DiskInfoQuery test = new DiskInfoQuery();
        System.out.println(test);
    }
}
