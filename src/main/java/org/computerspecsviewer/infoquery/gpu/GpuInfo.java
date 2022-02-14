package org.computerspecsviewer.infoquery.gpu;

import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.GraphicsCard;

public class GpuInfo extends BaseInfoQuery {
    public String deviceId;
    public String name;
    public String vendor;
    public String versionInfo;
    public Space videoRamAvailable;

    public GpuInfo(GraphicsCard gc) {
        this.deviceId = gc.getDeviceId();
        this.name = gc.getName();
        this.vendor = gc.getVendor();
        this.versionInfo = GpuInfo.processVersionInfo(gc.getVersionInfo());
        this.videoRamAvailable = new Space(gc.getVRam());
    }

    private static String processVersionInfo(String vInfo) {
        if(vInfo.contains("=")) {
            return vInfo.substring(vInfo.indexOf('=') + 1);
        }

        return vInfo;
    }
}
