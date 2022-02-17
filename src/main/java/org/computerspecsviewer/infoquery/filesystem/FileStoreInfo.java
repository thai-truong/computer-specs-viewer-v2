package org.computerspecsviewer.infoquery.filesystem;

import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.software.os.OSFileStore;

public class FileStoreInfo extends BaseInfoQuery {
    public String uuid;
    public String name;
    public String label;
    public String description;
    public String mountpoint;
    public String type;
    public Space totalSpace;
    public Space freeSpace;
    public Space usedSpace;
    public CustomNumber totalInodes;
    public CustomNumber freeInodes;
    public CustomNumber usedInodes;

    public FileStoreInfo(OSFileStore fileStore) {
        final String INODES = "Inodes";

        long totalSpaceInfo = fileStore.getTotalSpace();
        long freeSpaceInfo = fileStore.getFreeSpace();
        long totalInodesInfo = fileStore.getTotalInodes();
        long freeInodesInfo = fileStore.getFreeInodes();

        uuid = fileStore.getUUID();
        name = fileStore.getName();
        label = fileStore.getLabel();
        description = fileStore.getDescription();
        mountpoint = fileStore.getMount();
        type = fileStore.getType();
        totalSpace = new Space(totalSpaceInfo);
        freeSpace = new Space(freeSpaceInfo);
        usedSpace = new Space(totalSpaceInfo - freeSpaceInfo);
        totalInodes = new CustomNumber(totalInodesInfo, INODES);
        freeInodes = new CustomNumber(freeInodesInfo, INODES);
        usedInodes = new CustomNumber(totalInodesInfo - freeInodesInfo, INODES);
    }
}
