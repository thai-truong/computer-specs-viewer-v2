package org.computerspecsviewer.infoquery.mem;

import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.VirtualMemory;

public class VirtualMemoryInfo extends BaseInfoQuery {
    public Space virtualMemoryUsedSize;
    public Space virtualMemoryTotalSize;
    public Space swapFileTotalSize;
    public Space swapFileUsedSize;
    public long swapInPageCount;
    public long swapOutPageCount;

    public VirtualMemoryInfo(VirtualMemory vMem) {
        this.virtualMemoryUsedSize = new Space(vMem.getVirtualInUse());
        this.virtualMemoryTotalSize = new Space(vMem.getVirtualMax());
        this.swapFileUsedSize = new Space(vMem.getSwapUsed());
        this.swapFileTotalSize = new Space(vMem.getSwapTotal());
        this.swapInPageCount = vMem.getSwapPagesIn();
        this.swapOutPageCount = vMem.getSwapPagesOut();
    }
}
