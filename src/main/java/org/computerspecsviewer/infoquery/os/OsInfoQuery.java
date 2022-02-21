package org.computerspecsviewer.infoquery.os;

import org.computerspecsviewer.displaytypes.customdatetime.CustomDateTime;
import org.computerspecsviewer.displaytypes.customduration.CustomDuration;
import org.computerspecsviewer.displaytypes.customnumber.CustomNumber;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import org.computerspecsviewer.infoquery.utils.PrintHelpers;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

public class OsInfoQuery extends BaseInfoQuery {
    public String fullName;
    public String bitness;
    public String family;
    public String version;
    public String manufacturer;
    public String buildNumber;
    public String codeName;
    public CustomNumber processCount;
    public CustomNumber threadCount;
    public CustomDateTime bootTime;
    public CustomDuration upTimeSinceBoot;

    public OsInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        OperatingSystem os = sysInfo.getOperatingSystem();
        OperatingSystem.OSVersionInfo osVerInfo = os.getVersionInfo();

        String osFamily = os.getFamily();
        String osManufacturer = os.getManufacturer();
        String osBitness = PrintHelpers.getBitnessString(os.getBitness());

        fullName = getPlatformName(osManufacturer, osFamily) + getVersionInfoStr(osVerInfo, osBitness);
        bitness = osBitness;
        family = osFamily;
        version = osVerInfo.getVersion();
        manufacturer = osManufacturer;
        buildNumber = osVerInfo.getBuildNumber();
        codeName = osVerInfo.getCodeName();
        processCount = new CustomNumber(os.getProcessCount(), "processes");
        threadCount = new CustomNumber(os.getThreadCount(), "threads");
        bootTime = new CustomDateTime(os.getSystemBootTime());
        upTimeSinceBoot = new CustomDuration(os.getSystemUptime());
    }

    public static String getPlatformName(String manufacturer, String family) {
        return manufacturer + " " + family;
    }

    public static String getVersionInfoStr(OperatingSystem.OSVersionInfo verInfo, String bitness) {
        String version = verInfo.getVersion();
        String codeName = verInfo.getCodeName();
        String buildNum = verInfo.getBuildNumber();

        if(!version.isEmpty()) {
            version = " " + version + " ";
        }

        if(!codeName.isEmpty()) {
            codeName = "(" + codeName + ")" + " ";
        }

        if(!buildNum.isEmpty()) {
            buildNum = "Build " + buildNum + " ";
        }

        return version + codeName + buildNum + bitness;
    }

    public static void main(String[] args) {
        OsInfoQuery test = new OsInfoQuery();
        System.out.println(test);
    }
}
