package org.computerspecsviewer.infoquery.networkstatistics;

import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.software.os.InternetProtocolStats;

public class UdpStatisticsInfo extends BaseInfoQuery {
    public CustomNumber datagramsReceived;
    public CustomNumber datagramsSent;
    public CustomNumber datagramsReceivedWithNoPort;
    public CustomNumber datagramsReceivedErrors;

    public UdpStatisticsInfo(InternetProtocolStats.UdpStats udpStats) {
        final String DATAGRAMS = "datagrams";

        datagramsReceived = new CustomNumber(udpStats.getDatagramsReceived(), DATAGRAMS);
        datagramsSent = new CustomNumber(udpStats.getDatagramsSent(), DATAGRAMS);
        datagramsReceivedWithNoPort = new CustomNumber(udpStats.getDatagramsNoPort(), DATAGRAMS);
        datagramsReceivedErrors = new CustomNumber(udpStats.getDatagramsReceivedErrors(), DATAGRAMS);
    }
}
