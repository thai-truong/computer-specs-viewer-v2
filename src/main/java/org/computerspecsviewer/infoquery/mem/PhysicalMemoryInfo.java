package org.computerspecsviewer.infoquery.mem;

import org.computerspecsviewer.displaytypes.customfield.CustomField;
import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.PhysicalMemory;

public class PhysicalMemoryInfo extends BaseInfoQuery {
    public String memoryBankLabel;
    public String type;
    public String manufacturer;
    public Space memoryBankCapacity;
    public CustomField<Double> clockSpeed;

    public PhysicalMemoryInfo(PhysicalMemory pMem) {
        memoryBankLabel = pMem.getBankLabel();
        type = pMem.getMemoryType();
        manufacturer = pMem.getManufacturer();
        memoryBankCapacity = new Space(pMem.getCapacity());
        clockSpeed = new CustomField<>(pMem.getClockSpeed() / Math.pow(10, 6), "MHz");
    }
}
