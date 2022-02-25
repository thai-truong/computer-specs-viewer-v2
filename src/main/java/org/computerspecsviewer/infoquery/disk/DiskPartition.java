package org.computerspecsviewer.infoquery.disk;

import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.HWPartition;

public class DiskPartition extends BaseInfoQuery {
    public String id;
    public String name;
    public String mountPoint;
    public String type;
    public Space size;
    public Integer majorId;
    public Integer minorId;

    public DiskPartition(HWPartition partition) {
        id = partition.getIdentification();
        name = partition.getName();
        mountPoint = partition.getMountPoint();
        type = partition.getType();
        size = new Space(partition.getSize());
        majorId = partition.getMajor();
        minorId = partition.getMinor();
    }
}
