package org.computerspecsviewer.infoquery.controller;

import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.computersystem.ComputerSystemInfoQuery;
import org.computerspecsviewer.infoquery.cpu.CpuInfoQuery;
import org.computerspecsviewer.infoquery.disk.DiskInfoQuery;
import org.computerspecsviewer.infoquery.display.DisplayInfoQuery;
import org.computerspecsviewer.infoquery.filesystem.FileSystemInfoQuery;
import org.computerspecsviewer.infoquery.gpu.GpuInfoQuery;
import org.computerspecsviewer.infoquery.mem.MemInfoQuery;
import org.computerspecsviewer.infoquery.networkinterface.NetworkInfoQuery;
import org.computerspecsviewer.infoquery.networkstatistics.NetworkProtocolStatisticsQuery;
import org.computerspecsviewer.infoquery.os.OsInfoQuery;
import org.computerspecsviewer.infoquery.powersource.PowerSourceInfoQuery;
import org.computerspecsviewer.infoquery.soundcard.SoundCardInfoQuery;
import org.computerspecsviewer.infoquery.usbdevice.UsbDeviceInfoQuery;

import java.util.HashMap;
import java.util.Map;

public class InfoQueryController {
    private static final Map<String, BaseInfoQuery> infoQueryMap;
    public static final String[] allInfoTypes = {"computerSystem", "cpu", "disks", "memory", "graphicsCards",
            "displayDevices", "networkInterfaces", "powerSources", "soundCards", "usbDevices", "operatingSystem",
            "fileSystem", "networkStatistics"};

    static {
        infoQueryMap = new HashMap<>();
        infoQueryMap.put("computerSystem", new ComputerSystemInfoQuery());
        infoQueryMap.put("cpu", new CpuInfoQuery());
        infoQueryMap.put("disks", new DiskInfoQuery());
        infoQueryMap.put("memory", new MemInfoQuery());
        infoQueryMap.put("graphicsCards", new GpuInfoQuery());
        infoQueryMap.put("displayDevices", new DisplayInfoQuery());
        infoQueryMap.put("networkInterfaces", new NetworkInfoQuery());
        infoQueryMap.put("powerSources", new PowerSourceInfoQuery());
        infoQueryMap.put("soundCards", new SoundCardInfoQuery());
        infoQueryMap.put("usbDevices", new UsbDeviceInfoQuery());
        infoQueryMap.put("operatingSystem", new OsInfoQuery());
        infoQueryMap.put("fileSystem", new FileSystemInfoQuery());
        infoQueryMap.put("networkStatistics", new NetworkProtocolStatisticsQuery());
    }

    public InfoQueryController() {}

    public boolean foundInfoType(String infoType) {
        return infoQueryMap.containsKey(infoType);
    }

    public BaseInfoQuery getInfoQuery(String infoType) {
        return infoQueryMap.get(infoType);
    }
}
