package org.computerspecsviewer.infoquery.disk;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
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
    public CustomNumber readCount;
    public Space readBytes;
    public CustomNumber writeCount;
    public Space writeBytes;
    public Space size;
    public CustomList<DiskPartition> customPartitions;

    public DiskInfo(HWDiskStore disk) {
        name = disk.getName();
        modelName = disk.getModel();
        serialNumber = disk.getSerial();
        readCount = new CustomNumber(disk.getReads(), "reads");
        readBytes = new Space(disk.getReadBytes());
        writeCount = new CustomNumber(disk.getWrites(), "writes");
        writeBytes = new Space(disk.getWriteBytes());
        size = new Space(disk.getSize());

        List<HWPartition> queriedPartitions = disk.getPartitions();
        List<DiskPartition> partitions = new ArrayList<>();

        for(HWPartition partition: queriedPartitions) {
            partitions.add(new DiskPartition(partition));
        }

        customPartitions = new CustomList<>(partitions, "Disk Partition", true);
    }
}
