package org.computerspecsviewer.infoquery.usbdevice;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.UsbDevice;

import java.util.ArrayList;
import java.util.List;

public class UsbDeviceInfo extends BaseInfoQuery {
    public String name;
    public String uniqueDeviceId;
    public String serialNumber;
    public String productId;
    public String vendor;

    public UsbDeviceInfo(UsbDevice usb) {
        name = usb.getName();
        uniqueDeviceId = usb.getUniqueDeviceId();
        serialNumber = usb.getSerialNumber();
        productId = usb.getProductId();
        vendor = usb.getVendor();
    }
}
