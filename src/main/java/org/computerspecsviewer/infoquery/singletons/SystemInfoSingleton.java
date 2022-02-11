package org.computerspecsviewer.infoquery.singletons;

import oshi.SystemInfo;

public class SystemInfoSingleton {
    private static SystemInfo uniqueSystemInfo;

    public static synchronized SystemInfo getInstance() {
        if(uniqueSystemInfo == null) {
            uniqueSystemInfo = new SystemInfo();
        }

        return uniqueSystemInfo;
    }
}
