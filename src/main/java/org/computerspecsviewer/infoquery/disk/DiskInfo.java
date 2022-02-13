package org.computerspecsviewer.infoquery.disk;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;

import java.util.ArrayList;
import java.util.List;

public class DiskInfo extends BaseInfoQuery {
    public String name;
    public String modelName;
    public String serialNumber;
    public long readCount;
    public Space readBytes;
    public long writeCount;
    public Space writeBytes;
    public Space size;
    public CustomList<DiskPartition> customPartitions;

    public DiskInfo(HWDiskStore disk) {
        name = disk.getName();
        modelName = disk.getModel();
        serialNumber = disk.getSerial();
        readCount = disk.getReads();
        readBytes = new Space(disk.getReadBytes());
        writeCount = disk.getWrites();
        writeBytes = new Space(disk.getWriteBytes());
        size = new Space(disk.getSize());

        List<HWPartition> queriedPartitions = disk.getPartitions();
        List<DiskPartition> partitions = new ArrayList<>();

        for(HWPartition partition: queriedPartitions) {
            partitions.add(new DiskPartition(partition));
        }

        customPartitions = new CustomList<>(partitions, "Disk Partition", true, 0);
    }
}
