package org.computerspecsviewer.infoquery.filesystem;

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
    public CustomNumber maxFileDescriptorCount;
    public CustomNumber openFileDescriptorCount;
    public CustomList<FileStoreInfo> fileStores;

    public FileSystemInfoQuery() {
        final String FILE_DESCRIPTOR = "file descriptors";

        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        OperatingSystem os = sysInfo.getOperatingSystem();
        FileSystem fileSystem = os.getFileSystem();

        maxFileDescriptorCount = new CustomNumber(fileSystem.getMaxFileDescriptors(), FILE_DESCRIPTOR);
        openFileDescriptorCount = new CustomNumber(fileSystem.getOpenFileDescriptors(), FILE_DESCRIPTOR);

        List<OSFileStore> fsList = fileSystem.getFileStores();
        List<FileStoreInfo> fsiList = new ArrayList<>();

        for(OSFileStore fs: fsList) {
            fsiList.add(new FileStoreInfo(fs));
        }

        fileStores = new CustomList<>(fsiList, "File Store", true);
    }

    public static void main(String[] args) {
        FileSystemInfoQuery test = new FileSystemInfoQuery();
        System.out.println(test);
    }
}
