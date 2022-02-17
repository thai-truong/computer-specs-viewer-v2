package org.computerspecsviewer.infoquery.networkstatistics;

import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.software.os.InternetProtocolStats;

public class UdpStatisticsInfo extends BaseInfoQuery {
    public long datagramsReceived;
    public long datagramsSent;
    public long datagramsReceivedWithNoPort;
    public long datagramsReceivedErrors;

    public UdpStatisticsInfo(InternetProtocolStats.UdpStats udpStats) {
        datagramsReceived = udpStats.getDatagramsReceived();
        datagramsSent = udpStats.getDatagramsSent();
        datagramsReceivedWithNoPort = udpStats.getDatagramsNoPort();
        datagramsReceivedErrors = udpStats.getDatagramsReceivedErrors();
    }
}
