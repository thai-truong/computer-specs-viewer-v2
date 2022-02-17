package org.computerspecsviewer.infoquery.networkstatistics;

import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.software.os.InternetProtocolStats;

public class TcpStatisticsInfo extends BaseInfoQuery {
    public CustomNumber establishedConnections;
    public CustomNumber activeConnections;
    public CustomNumber failedConnections;
    public CustomNumber passiveConnections;
    public CustomNumber resetConnections;
    public CustomNumber segmentsReceived;
    public CustomNumber segmentsSent;
    public CustomNumber segmentsRetransmitted;
    public CustomNumber errorsReceived;
    public CustomNumber segmentsTransmittedWithReset;

    public TcpStatisticsInfo(InternetProtocolStats.TcpStats tcpStats) {
        final String CONNECTIONS = "connections";
        final String SEGMENTS = "segments";

        establishedConnections = new CustomNumber(tcpStats.getConnectionsEstablished(), CONNECTIONS);
        activeConnections = new CustomNumber(tcpStats.getConnectionsActive(), CONNECTIONS);
        failedConnections = new CustomNumber(tcpStats.getConnectionFailures(), CONNECTIONS);
        passiveConnections = new CustomNumber(tcpStats.getConnectionsPassive(), CONNECTIONS);
        resetConnections = new CustomNumber(tcpStats.getConnectionsReset(), CONNECTIONS);
        segmentsReceived = new CustomNumber(tcpStats.getSegmentsReceived(), SEGMENTS);
        segmentsSent = new CustomNumber(tcpStats.getSegmentsSent(), SEGMENTS);
        segmentsRetransmitted = new CustomNumber(tcpStats.getSegmentsRetransmitted(), SEGMENTS);
        errorsReceived = new CustomNumber(tcpStats.getInErrors(), SEGMENTS);
        segmentsTransmittedWithReset = new CustomNumber(tcpStats.getOutResets(), SEGMENTS);
    }
}
