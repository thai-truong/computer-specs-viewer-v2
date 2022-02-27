package org.computerspecsviewer.infoquery.display;

import org.computerspecsviewer.displaytypes.customboolean.CustomBoolean;
import org.computerspecsviewer.displaytypes.customfield.CustomField;
import org.computerspecsviewer.displaytypes.customsingleton.CustomSingleton;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.Display;
import oshi.util.EdidUtil;

public class DisplayInfo extends BaseInfoQuery {
    public String productId;
    public String serialNumber;
    public String manufacturerId;
    public Integer yearManufactured;
    public CustomField<Integer> monitorHeight;
    public CustomField<Integer> monitorWidth;
    public CustomBoolean isDigital;
    public CustomSingleton displayDescriptors;

    public DisplayInfo(Display displayDevice) {
        final String CM = "cm";
        byte[] edidInfo = displayDevice.getEdid();

        productId = EdidUtil.getProductID(edidInfo);
        serialNumber = EdidUtil.getSerialNo(edidInfo);
        manufacturerId = EdidUtil.getManufacturerID(edidInfo);
        yearManufactured = EdidUtil.getYear(edidInfo);
        monitorHeight = new CustomField<>(EdidUtil.getVcm(edidInfo), CM);
        monitorWidth = new CustomField<>(EdidUtil.getHcm(edidInfo), CM);
        isDigital = new CustomBoolean(EdidUtil.isDigital(edidInfo), "Yes", "No (Analog)");
        displayDescriptors = new CustomSingleton(new DisplayDescriptors(edidInfo));
    }
}
