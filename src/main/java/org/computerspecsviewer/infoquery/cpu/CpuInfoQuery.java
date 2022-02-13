package org.computerspecsviewer.infoquery.cpu;

import org.computerspecsviewer.displaytypes.frequency.Frequency;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.disk.DiskInfoQuery;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.CentralProcessor;

import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;

public class CpuInfoQuery extends BaseInfoQuery {
    public String name;
    public String vendorName;
    public String familyName;
    public String modelName;
    public String physicalId;
    public boolean is64bit;
    public Frequency maxFrequency;
    public int logicalCoreCount;
    public int physicalCoreCount;
    public long interruptCount;
    public long contextSwitchCount;

    public CpuInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        HardwareAbstractionLayer hal = sysInfo.getHardware();

        CentralProcessor cpu = hal.getProcessor();
        CentralProcessor.ProcessorIdentifier cpuIdInfo = cpu.getProcessorIdentifier();

        name = cpuIdInfo.getName();
        vendorName = cpuIdInfo.getVendor();
        familyName = cpuIdInfo.getFamily();
        modelName = cpuIdInfo.getModel();

        physicalId = cpuIdInfo.getProcessorID();
        is64bit = cpuIdInfo.isCpu64bit();
        maxFrequency = new Frequency(cpu.getMaxFreq());

        logicalCoreCount = cpu.getLogicalProcessorCount();
        physicalCoreCount = cpu.getPhysicalProcessorCount();
        interruptCount = cpu.getInterrupts();
        contextSwitchCount = cpu.getContextSwitches();
    }

    public static void main(String[] args) {
        CpuInfoQuery test = new CpuInfoQuery();
        System.out.println(test);
    }
}
