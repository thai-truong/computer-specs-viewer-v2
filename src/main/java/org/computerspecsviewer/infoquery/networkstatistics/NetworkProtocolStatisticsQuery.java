package org.computerspecsviewer.infoquery.networkstatistics;

import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.software.os.InternetProtocolStats;
import oshi.software.os.OperatingSystem;

public class NetworkProtocolStatisticsQuery extends BaseInfoQuery {
    public TcpStatisticsInfo tcpIpv4Statistics;
    public TcpStatisticsInfo tcpIpv6Statistics;
    public UdpStatisticsInfo udpIpv4Statistics;
    public UdpStatisticsInfo udpIpv6Statistics;

    public NetworkProtocolStatisticsQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        OperatingSystem os = sysInfo.getOperatingSystem();
        InternetProtocolStats stats = os.getInternetProtocolStats();

        tcpIpv4Statistics = new TcpStatisticsInfo(stats.getTCPv4Stats());
        tcpIpv6Statistics = new TcpStatisticsInfo(stats.getTCPv6Stats());

        udpIpv4Statistics = new UdpStatisticsInfo(stats.getUDPv4Stats());
        udpIpv6Statistics = new UdpStatisticsInfo(stats.getUDPv6Stats());
    }

    public static void main(String[] args) {
        NetworkProtocolStatisticsQuery test = new NetworkProtocolStatisticsQuery();
        System.out.println(test);
    }
}
