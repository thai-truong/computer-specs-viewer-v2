package org.computerspecsviewer.infoquery.filesystem;

import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
import org.computerspecsviewer.displaytypes.custompair.CustomPair;
import org.computerspecsviewer.displaytypes.percent.Percent;
import org.computerspecsviewer.displaytypes.space.Space;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.software.os.OSFileStore;

public class FileStoreInfo extends BaseInfoQuery {
    public String name;
    public String label;
    public String uuid;
    public String type;
    public String description;
    public String mountpoint;
    public Space totalSpace;
    public CustomPair<Space, String> freeSpace;
    public CustomPair<Space, String> usedSpace;
    public CustomNumber totalInodes;
    public CustomPair<CustomNumber, String> freeInodes;
    public CustomPair<CustomNumber, String> usedInodes;

    public FileStoreInfo(OSFileStore osFileStore) {
        final String INODES = "inodes";

        long fsTotalSpace = osFileStore.getTotalSpace();
        long fsFreeSpace = osFileStore.getFreeSpace();
        long fsUsedSpace = fsTotalSpace - fsFreeSpace;
        long fsTotalInodes = osFileStore.getTotalInodes();
        long fsFreeInodes = osFileStore.getFreeInodes();
        long fsUsedInodes = fsTotalInodes - fsFreeInodes;

        name = osFileStore.getName();
        label = osFileStore.getLabel();
        uuid = osFileStore.getUUID();
        type = osFileStore.getType();
        description = osFileStore.getDescription();
        mountpoint = osFileStore.getMount();
        totalSpace = new Space(fsTotalSpace);
        freeSpace = new CustomPair<>(new Space(fsFreeSpace), getExtraPercentInfo(fsFreeSpace, fsTotalSpace));
        usedSpace = new CustomPair<>(new Space(fsUsedSpace), getExtraPercentInfo(fsUsedSpace, fsTotalSpace));
        totalInodes = new CustomNumber(fsTotalInodes, INODES);
        freeInodes = new CustomPair<>(new CustomNumber(fsFreeInodes, INODES), getExtraPercentInfo(fsFreeInodes, fsTotalInodes));
        usedInodes = new CustomPair<>(new CustomNumber(fsUsedInodes, INODES), getExtraPercentInfo(fsUsedInodes, fsTotalInodes));
    }

    private String getExtraPercentInfo(long numerator, long denominator) {
        double decimalValue;

        if(denominator == 0) {
            decimalValue = 1;
        } else {
            decimalValue = (double)numerator / denominator;
        }

        Percent percent = new Percent(decimalValue);
        return "(" + percent + ")";
    }
}
