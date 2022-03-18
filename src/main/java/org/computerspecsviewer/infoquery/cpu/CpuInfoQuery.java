package org.computerspecsviewer.infoquery.cpu;

import org.computerspecsviewer.displaytypes.customboolean.CustomBoolean;
import org.computerspecsviewer.displaytypes.customfield.CustomField;
import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
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
    public CustomBoolean is64bit;
    public CustomField<Double> maxFrequency;
    public Integer logicalCoreCount;
    public Integer physicalCoreCount;
    public CustomNumber interruptCount;
    public CustomNumber contextSwitchCount;

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
        is64bit = new CustomBoolean(cpuIdInfo.isCpu64bit());
        maxFrequency = new CustomField<>(cpu.getMaxFreq() / Math.pow(10, 6), "MHz");

        logicalCoreCount = cpu.getLogicalProcessorCount();
        physicalCoreCount = cpu.getPhysicalProcessorCount();
        interruptCount = new CustomNumber(cpu.getInterrupts(), "interrupts");
        contextSwitchCount = new CustomNumber(cpu.getContextSwitches(), "context switches");
    }

    public static void main(String[] args) {
        CpuInfoQuery test = new CpuInfoQuery();
        System.out.println(test);
    }
}
