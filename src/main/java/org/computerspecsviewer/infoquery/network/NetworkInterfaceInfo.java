package org.computerspecsviewer.infoquery.network;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.displaytypes.space.SpaceUnits;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.utils.PrintHelpers;
import org.computerspecsviewer.infoquery.utils.StringHelpers;
import oshi.hardware.NetworkIF;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NetworkInterfaceInfo extends BaseInfoQuery {
    public int index;
    public String name;
    public String alias;
    public String description;
    public String operationalStatus;
    public int interfaceType;
    public CustomList<String> ipV4Addresses;
    public CustomList<String> ipV6Addresses;
    public String macAddress;
    public Space mtu;
    public long speed;
    public Space bytesReceived;
    public Space bytesSent;
    public long packetsReceived;
    public long packetsSent;
    public long receivedDropCount;
    public long sentDropCount;
    public long inputErrorCount;
    public long outputErrorCount;

    public NetworkInterfaceInfo(NetworkIF nwIf) {
        index = nwIf.getIndex();
        name = nwIf.getName();
        alias = nwIf.getIfAlias();
        description = nwIf.getDisplayName();
        interfaceType = nwIf.getIfType();
        macAddress = nwIf.getMacaddr();
        mtu = new Space(nwIf.getMTU(), SpaceUnits.MB);
        speed = nwIf.getSpeed();
        bytesReceived = new Space(nwIf.getBytesRecv(), SpaceUnits.MB);
        bytesSent = new Space(nwIf.getBytesSent(), SpaceUnits.MB);
        packetsReceived = nwIf.getPacketsRecv();
        packetsSent = nwIf.getPacketsSent();
        receivedDropCount = nwIf.getInDrops();
        sentDropCount = nwIf.getCollisions();
        inputErrorCount = nwIf.getInErrors();
        outputErrorCount = nwIf.getOutErrors();

        List<String> ipV4AddrList = Arrays.asList(nwIf.getIPv4addr());
        ipV4AddrList = PrintHelpers.injectNewlines(ipV4AddrList);

        List<String> ipV6AddrList = Arrays.asList(nwIf.getIPv6addr());
        ipV6AddrList = PrintHelpers.injectNewlines(ipV6AddrList);

        ipV4Addresses = new CustomList<>(ipV4AddrList, "IPv4 Address", true);
        ipV6Addresses = new CustomList<>(ipV6AddrList, "IPv6 Address", true);

        OperStatusDescMapSingleton singleton = OperStatusDescMapSingleton.getInstance();
        Map<NetworkIF.IfOperStatus, String> operStatusToDesc = singleton.getMap();

        NetworkIF.IfOperStatus opStatus = nwIf.getIfOperStatus();
        operationalStatus = String.format("%s (%s)", operStatusToDesc.get(opStatus), opStatus.name());
    }
}
