package org.computerspecsviewer.infoquery.networkstatistics;

import org.computerspecsviewer.displaytypes.customsingleton.CustomSingleton;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.software.os.InternetProtocolStats;
import oshi.software.os.OperatingSystem;

public class NetworkProtocolStatisticsQuery extends BaseInfoQuery {
    public CustomSingleton tcpIpv4Statistics;
    public CustomSingleton tcpIpv6Statistics;
    public CustomSingleton udpIpv4Statistics;
    public CustomSingleton udpIpv6Statistics;

    public NetworkProtocolStatisticsQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        OperatingSystem os = sysInfo.getOperatingSystem();
        InternetProtocolStats stats = os.getInternetProtocolStats();

        tcpIpv4Statistics = new CustomSingleton(new TcpStatisticsInfo(stats.getTCPv4Stats()));
        tcpIpv6Statistics = new CustomSingleton(new TcpStatisticsInfo(stats.getTCPv6Stats()));

        udpIpv4Statistics = new CustomSingleton(new UdpStatisticsInfo(stats.getUDPv4Stats()));
        udpIpv6Statistics = new CustomSingleton(new UdpStatisticsInfo(stats.getUDPv6Stats()));
    }

    public static void main(String[] args) {
        NetworkProtocolStatisticsQuery test = new NetworkProtocolStatisticsQuery();
        System.out.println(test);
    }
}
