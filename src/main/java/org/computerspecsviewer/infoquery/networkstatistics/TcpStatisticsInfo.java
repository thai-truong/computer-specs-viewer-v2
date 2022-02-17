package org.computerspecsviewer.infoquery.networkstatistics;

import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.software.os.InternetProtocolStats;

public class TcpStatisticsInfo extends BaseInfoQuery {
    public long establishedConnections;
    public long activeConnections;
    public long failedConnections;
    public long passiveConnections;
    public long resetConnections;
    public long segmentsReceived;
    public long segmentsSent;
    public long segmentsRetransmitted;
    public long errorsReceived;
    public long segmentsTransmittedWithReset;

    public TcpStatisticsInfo(InternetProtocolStats.TcpStats tcpStats) {
        establishedConnections = tcpStats.getConnectionsEstablished();
        activeConnections = tcpStats.getConnectionsActive();
        failedConnections = tcpStats.getConnectionFailures();
        passiveConnections = tcpStats.getConnectionsPassive();
        resetConnections = tcpStats.getConnectionsReset();
        segmentsReceived = tcpStats.getSegmentsReceived();
        segmentsSent = tcpStats.getSegmentsSent();
        segmentsRetransmitted = tcpStats.getSegmentsRetransmitted();
        errorsReceived = tcpStats.getInErrors();
        segmentsTransmittedWithReset = tcpStats.getOutResets();
    }
}
