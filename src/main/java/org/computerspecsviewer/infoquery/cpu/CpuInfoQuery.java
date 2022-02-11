package org.computerspecsviewer.infoquery.cpu;

import org.computerspecsviewer.infoquery.utils.ReflectionHelpers;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.CentralProcessor;

import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CpuInfoQuery {
    public String name;
    public String vendorName;
    public String familyName;
    public String modelName;
    public String physicalId;
    public boolean is64bit;
    public long maxFrequency;
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
        maxFrequency = cpu.getMaxFreq();

        logicalCoreCount = cpu.getLogicalProcessorCount();
        physicalCoreCount = cpu.getPhysicalProcessorCount();
        interruptCount = cpu.getInterrupts();
        contextSwitchCount = cpu.getContextSwitches();
    }

    public List<String> getFields() {
        return ReflectionHelpers.getFieldsAsStringList(this);
    }

    @Override
    public String toString() {
        List<String> fields = getFields();

        return "";
    }

    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        CentralProcessor.ProcessorIdentifier cpuIdInfo = cpu.getProcessorIdentifier();

        System.out.println(cpuIdInfo.getName());
        System.out.println(cpuIdInfo.getVendor());
        System.out.println(cpuIdInfo.getProcessorID());
        System.out.println(cpu.getLogicalProcessorCount());
        System.out.println(cpu.getPhysicalProcessorCount());
        System.out.println(cpu.getLogicalProcessors());
        System.out.println(cpu.getPhysicalProcessors());
    }
}
