package org.computerspecsviewer.infoquery.computersystem;

import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.Baseboard;

public class BaseboardInfo extends BaseInfoQuery {
    public String serialNumber;
    public String modelName;
    public String version;
    public String manufacturer;

    public BaseboardInfo(Baseboard board) {
        this.serialNumber = board.getSerialNumber();
        this.modelName = board.getModel();
        this.version = board.getVersion();
        this.manufacturer = board.getManufacturer();
    }
}
