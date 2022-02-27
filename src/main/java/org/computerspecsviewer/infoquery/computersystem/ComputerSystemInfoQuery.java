package org.computerspecsviewer.infoquery.computersystem;

import org.computerspecsviewer.displaytypes.customsingleton.CustomSingleton;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;

public class ComputerSystemInfoQuery extends BaseInfoQuery {
    public String modelName;
    public String manufacturer;
    public CustomSingleton baseboard;
    public CustomSingleton firmware;
    public String hardwareUuid;
    public String serialNumber;

    public ComputerSystemInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        HardwareAbstractionLayer hal = sysInfo.getHardware();
        ComputerSystem compSys = hal.getComputerSystem();

        modelName = compSys.getModel();
        manufacturer = compSys.getManufacturer();
        hardwareUuid = compSys.getHardwareUUID();
        serialNumber = compSys.getSerialNumber();

        BaseboardInfo baseboardInfo = new BaseboardInfo(compSys.getBaseboard());
        baseboard = new CustomSingleton(baseboardInfo);

        FirmwareInfo firmwareInfo = new FirmwareInfo(compSys.getFirmware());
        firmware = new CustomSingleton(firmwareInfo);
    }

    public static void main(String[] args) {
        ComputerSystemInfoQuery test = new ComputerSystemInfoQuery();
        System.out.println(test);
    }
}
