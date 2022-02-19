package org.computerspecsviewer.infoquery.mem;

import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.VirtualMemory;

public class VirtualMemoryInfo extends BaseInfoQuery {
    public Space virtualMemoryUsedSize;
    public Space virtualMemoryTotalSize;
    public Space swapFileTotalSize;
    public Space swapFileUsedSize;
    public CustomNumber swapInPageCount;
    public CustomNumber swapOutPageCount;

    public VirtualMemoryInfo(VirtualMemory vMem) {
        virtualMemoryUsedSize = new Space(vMem.getVirtualInUse());
        virtualMemoryTotalSize = new Space(vMem.getVirtualMax());
        swapFileUsedSize = new Space(vMem.getSwapUsed());
        swapFileTotalSize = new Space(vMem.getSwapTotal());
        swapInPageCount = new CustomNumber(vMem.getSwapPagesIn(), "swap-ins");
        swapOutPageCount = new CustomNumber(vMem.getSwapPagesOut(), "swap-outs");
    }
}
