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
        deviceId = gc.getDeviceId();
        name = gc.getName();
        vendor = gc.getVendor();
        versionInfo = GpuInfo.processVersionInfo(gc.getVersionInfo());
        videoRamAvailable = new Space(gc.getVRam());
    }

    // The GPU version info collected by Oshi (at least for Windows) is of the format "DriverVersion=<version>".
    // Only <version> is desirable and hence the code below gets rid of the "DriverVersion=" if it does exist, or
    // any version string containing "=".
    private static String processVersionInfo(String vInfo) {
        if(vInfo.contains("=")) {
            return vInfo.substring(vInfo.indexOf('=') + 1);
        }

        return vInfo;
    }
}
