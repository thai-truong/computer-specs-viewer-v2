package org.computerspecsviewer.infoquery.powersource;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PowerSource;

import java.util.ArrayList;
import java.util.List;

public class PowerSourceInfoQuery extends BaseInfoQuery {
    public CustomList<PowerSourceInfo> powerSources;

    public PowerSourceInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        HardwareAbstractionLayer hal = sysInfo.getHardware();

        List<PowerSource> powerSourceList = hal.getPowerSources();
        List<PowerSourceInfo> powerSourceInfoList = new ArrayList<>();

        for(PowerSource powerSource: powerSourceList) {
            powerSourceInfoList.add(new PowerSourceInfo(powerSource));
        }

        powerSources = new CustomList<>(powerSourceInfoList, "Power Source", true);
    }

    @Override
    public String toString() {
        return powerSources.toString();
    }

    public static void main(String[] args) {
        PowerSourceInfoQuery test = new PowerSourceInfoQuery();
        System.out.println(test);
    }
}
