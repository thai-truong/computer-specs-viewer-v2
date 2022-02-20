package org.computerspecsviewer.infoquery.usbdevice;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.UsbDevice;

import java.util.ArrayList;
import java.util.List;

public class UsbDeviceInfoQuery extends BaseInfoQuery {
    public CustomList<UsbDeviceInfo> usbDevices;

    public UsbDeviceInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        HardwareAbstractionLayer hal = sysInfo.getHardware();
        List<UsbDevice> usbDeviceList = hal.getUsbDevices(false);
        List<UsbDeviceInfo> usbDeviceInfos = new ArrayList<>();

        for(UsbDevice usb: usbDeviceList) {
            usbDeviceInfos.add(new UsbDeviceInfo(usb));
        }

        usbDevices = new CustomList<>(usbDeviceInfos, "USB Device", false);
    }

    @Override
    public String toString() {
        return usbDevices.toString();
    }

    public static void main(String[] args) {
        UsbDeviceInfoQuery test = new UsbDeviceInfoQuery();
        System.out.println(test);
    }
}
