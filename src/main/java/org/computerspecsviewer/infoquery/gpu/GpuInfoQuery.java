package org.computerspecsviewer.infoquery.gpu;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.ArrayList;
import java.util.List;


public class GpuInfoQuery extends BaseInfoQuery {
    public CustomList<GpuInfo> customGpusInfo;

    public GpuInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        HardwareAbstractionLayer hal = sysInfo.getHardware();
        List<GraphicsCard> gcList = hal.getGraphicsCards();

        List<GpuInfo> gcInfoList = new ArrayList<>();

        for(GraphicsCard gc: gcList) {
            gcInfoList.add(new GpuInfo(gc));
        }

        customGpusInfo = new CustomList<>(gcInfoList, "Graphics Card", false);
    }

    @Override
    public String toString() {
        return customGpusInfo.toString();
    }

    public static void main(String[] args) {
        GpuInfoQuery test = new GpuInfoQuery();
        System.out.println(test);
    }
}
