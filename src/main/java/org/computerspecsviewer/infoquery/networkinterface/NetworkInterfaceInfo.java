package org.computerspecsviewer.infoquery.networkinterface;

import org.computerspecsviewer.displaytypes.customfield.CustomField;
import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.displaytypes.space.SpaceUnits;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.utils.PrintHelpers;
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
    public CustomField<Long> speed;
    public Space bytesReceived;
    public Space bytesSent;
    public CustomNumber packetsReceived;
    public CustomNumber packetsSent;
    public CustomNumber receivedDropCount;
    public CustomNumber sentDropCount;
    public CustomNumber inputErrorCount;
    public CustomNumber outputErrorCount;

    public NetworkInterfaceInfo(NetworkIF nwIf) {
        final String PACKETS = "packets";
        final String DROPS = "drops";
        final String ERRORS = "errors";

        index = nwIf.getIndex();
        name = nwIf.getName();
        alias = nwIf.getIfAlias();
        description = nwIf.getDisplayName();
        interfaceType = nwIf.getIfType();
        macAddress = nwIf.getMacaddr();
        mtu = new Space(nwIf.getMTU(), SpaceUnits.MB);
        speed = new CustomField<>(nwIf.getSpeed(), "bit/sec");
        bytesReceived = new Space(nwIf.getBytesRecv(), SpaceUnits.MB);
        bytesSent = new Space(nwIf.getBytesSent(), SpaceUnits.MB);
        packetsReceived = new CustomNumber(nwIf.getPacketsRecv(), PACKETS);
        packetsSent = new CustomNumber(nwIf.getPacketsSent(), PACKETS);
        receivedDropCount = new CustomNumber(nwIf.getInDrops(), DROPS);
        sentDropCount = new CustomNumber(nwIf.getCollisions(), DROPS);
        inputErrorCount = new CustomNumber(nwIf.getInErrors(), ERRORS);
        outputErrorCount = new CustomNumber(nwIf.getOutErrors(), ERRORS);

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
