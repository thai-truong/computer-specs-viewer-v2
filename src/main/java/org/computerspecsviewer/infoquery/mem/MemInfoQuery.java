package org.computerspecsviewer.infoquery.mem;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PhysicalMemory;
import oshi.hardware.VirtualMemory;

import java.util.ArrayList;
import java.util.List;

public class MemInfoQuery extends BaseInfoQuery {
    public Space availablePhysicalMemory;
    public Space totalPhysicalMemory;
    public Space pageSize;
    public VirtualMemoryInfo virtualMemoryInfo;
    public CustomList<PhysicalMemoryInfo> physicalMemoryDevices;

    public MemInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        HardwareAbstractionLayer hal = sysInfo.getHardware();
        GlobalMemory mem = hal.getMemory();

        availablePhysicalMemory = new Space(mem.getAvailable());
        totalPhysicalMemory = new Space(mem.getTotal());
        pageSize = new Space(mem.getPageSize());

        VirtualMemory vMem = mem.getVirtualMemory();
        virtualMemoryInfo = new VirtualMemoryInfo(vMem);

        List<PhysicalMemory> physMemList = mem.getPhysicalMemory();
        List<PhysicalMemoryInfo> physMemInfoList = new ArrayList<>();

        for(PhysicalMemory pMem: physMemList) {
            physMemInfoList.add(new PhysicalMemoryInfo(pMem));
        }

        physicalMemoryDevices = new CustomList<>(physMemInfoList, "Physical Memory Device", true);
    }

    public static void main(String[] args) {
        MemInfoQuery test = new MemInfoQuery();
        System.out.println(test);
    }
}
