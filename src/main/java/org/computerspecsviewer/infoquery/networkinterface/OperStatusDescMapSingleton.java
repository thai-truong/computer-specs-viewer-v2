package org.computerspecsviewer.infoquery.networkinterface;

import oshi.hardware.NetworkIF;

import java.util.Map;

public class OperStatusDescMapSingleton {
    public Map<NetworkIF.IfOperStatus, String> operStatusToDesc;
    private static OperStatusDescMapSingleton mapSingleton;

    private OperStatusDescMapSingleton() {
        operStatusToDesc = Map.of(
                NetworkIF.IfOperStatus.DORMANT, "The interface is not up, but is in a pending state, waiting for some external event",
                NetworkIF.IfOperStatus.DOWN, "Down and not operational",
                NetworkIF.IfOperStatus.LOWER_LAYER_DOWN, "Down due to state of lower-layer interface(s)",
                NetworkIF.IfOperStatus.NOT_PRESENT, "Some component is missing",
                NetworkIF.IfOperStatus.TESTING, "In some test mode",
                NetworkIF.IfOperStatus.UNKNOWN, "The interface status is unknown",
                NetworkIF.IfOperStatus.UP, "Up and operational"
        );
    }

    public static OperStatusDescMapSingleton getInstance() {
        if(mapSingleton == null) {
            mapSingleton = new OperStatusDescMapSingleton();
        }

        return mapSingleton;
    }

    public Map<NetworkIF.IfOperStatus, String> getMap() {
        return operStatusToDesc;
    }
}
