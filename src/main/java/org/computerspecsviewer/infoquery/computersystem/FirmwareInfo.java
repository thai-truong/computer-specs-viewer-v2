package org.computerspecsviewer.infoquery.computersystem;

import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.Firmware;

public class FirmwareInfo extends BaseInfoQuery {
    public String name;
    public String version;
    public String manufacturer;
    public String releaseDate;
    public String description;

    public FirmwareInfo(Firmware fw) {
        this.name = fw.getName();
        this.version = fw.getVersion();
        this.manufacturer = fw.getManufacturer();
        this.releaseDate = fw.getReleaseDate();
        this.description = fw.getDescription();
    }
}
