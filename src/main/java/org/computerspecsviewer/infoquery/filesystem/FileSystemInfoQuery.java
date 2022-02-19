package org.computerspecsviewer.infoquery.filesystem;

import javafx.util.Pair;
import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.util.ArrayList;
import java.util.List;

public class FileSystemInfoQuery extends BaseInfoQuery {
    public CustomNumber maximumFileDescriptorCount;
    public CustomNumber openFileDescriptorCount;
    public CustomList<FileStoreInfo> fileStores;

    public FileSystemInfoQuery() {
        final String FILE_DESCRIPTOR = "file descriptors";

        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        OperatingSystem os = sysInfo.getOperatingSystem();
        FileSystem fileSystem = os.getFileSystem();

        maximumFileDescriptorCount = new CustomNumber(fileSystem.getMaxFileDescriptors(), FILE_DESCRIPTOR);
        openFileDescriptorCount = new CustomNumber(fileSystem.getOpenFileDescriptors(), FILE_DESCRIPTOR);

        List<OSFileStore> osFileStores = fileSystem.getFileStores();
        List<FileStoreInfo> fileStoreInfoList = new ArrayList<>();

        for(OSFileStore osFileStore: osFileStores) {
            fileStoreInfoList.add(new FileStoreInfo(osFileStore));
        }

        fileStores = new CustomList<>(fileStoreInfoList, "File Store", true);
    }

    public static void main(String[] args) {
        FileSystemInfoQuery test = new FileSystemInfoQuery();
        System.out.println(test);
    }
}
