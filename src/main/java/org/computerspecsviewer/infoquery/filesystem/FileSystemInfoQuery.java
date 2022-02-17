package org.computerspecsviewer.infoquery.filesystem;

import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OperatingSystem;

public class FileSystemInfoQuery {
    public FileSystemInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        OperatingSystem os = sysInfo.getOperatingSystem();
        FileSystem fsys = os.getFileSystem();
    }
}
