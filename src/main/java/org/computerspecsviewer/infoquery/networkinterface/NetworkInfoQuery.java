package org.computerspecsviewer.infoquery.networkinterface;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

import java.util.ArrayList;
import java.util.List;

public class NetworkInfoQuery extends BaseInfoQuery {
    public CustomList<NetworkInterfaceInfo> networkInterfacesInfo;

    public NetworkInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        HardwareAbstractionLayer hal = sysInfo.getHardware();

        List<NetworkIF> nwIfs = hal.getNetworkIFs();
        List<NetworkInterfaceInfo> nwIfInfoList = new ArrayList<>();

        for(NetworkIF nwIf: nwIfs) {
            nwIfInfoList.add(new NetworkInterfaceInfo(nwIf));
        }

        networkInterfacesInfo = new CustomList<>(nwIfInfoList, "Network Interface", true);
    }

    @Override
    public String toString() {
        return networkInterfacesInfo.toString();
    }

    public static void main(String[] args) {
        NetworkInfoQuery test = new NetworkInfoQuery();
        System.out.println(test);
    }
}
