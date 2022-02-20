package org.computerspecsviewer.infoquery.soundcard;

import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import oshi.hardware.SoundCard;
import oshi.util.tuples.Pair;

public class SoundCardInfo extends BaseInfoQuery {
    public String codec;
    public String manufacturer;
    public String driverVersion;

    public SoundCardInfo(SoundCard soundCard) {
        String cardName = soundCard.getName();
        String cardVersion = soundCard.getDriverVersion();
        Pair<String, String> nameManufacturer = getNamesFromOshiName(cardName);

        manufacturer = nameManufacturer.getB();
        driverVersion = getNumericVersion(cardVersion);
        codec = soundCard.getCodec();
    }

    // Since Oshi's sound card full name is a combination of the sound card's manufacturer name and actual device name,
    // separated by a whitespace (<manufacturer-name> + " " + <device-name>) so this attempts to parse the full name
    // to extract both the device name and manufacturer name
    private static Pair<String, String> getNamesFromOshiName(String name) {
        int firstWhitespaceIdx = name.indexOf(" ");
        String manufacturerFirst = name.substring(0, firstWhitespaceIdx);

        int manufacturerSecondIdx = name.indexOf(manufacturerFirst, firstWhitespaceIdx);

        if(manufacturerSecondIdx == -1) {
            return new Pair<>(name, "");
        }

        String fullManufacturer = name.substring(0, manufacturerSecondIdx - 1); // Minus 1 to account for whitespace
        String cardName = name.substring(manufacturerSecondIdx);

        return new Pair<>(cardName, fullManufacturer);
    }

    private static String getNumericVersion(String version) {
        if(version.contains(".sys")) {
            int whitespaceIdx = version.indexOf(" ");
            return version.substring(whitespaceIdx + 1);
        }

        return version;
    }
}
