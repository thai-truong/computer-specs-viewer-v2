package org.computerspecsviewer.infoquery.mem;

import org.computerspecsviewer.displaytypes.frequency.Frequency;
import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.PhysicalMemory;

public class PhysicalMemoryInfo extends BaseInfoQuery {
    public String memoryBankLabel;
    public String type;
    public String manufacturer;
    public Space memoryBankCapacity;
    public Frequency clockSpeed;

    public PhysicalMemoryInfo(PhysicalMemory pMem) {
        this.memoryBankLabel = pMem.getBankLabel();
        this.type = pMem.getMemoryType();
        this.manufacturer = pMem.getManufacturer();
        this.memoryBankCapacity = new Space(pMem.getCapacity());
        this.clockSpeed = new Frequency(pMem.getClockSpeed());
    }
}
