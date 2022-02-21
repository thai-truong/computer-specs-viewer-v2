package org.computerspecsviewer.infoquery.soundcard;

import org.computerspecsviewer.displaytypes.customlist.CustomList;
import org.computerspecsviewer.infoquery.base.BaseInfoQuery;
import org.computerspecsviewer.infoquery.singletons.SystemInfoSingleton;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.SoundCard;

import java.util.ArrayList;
import java.util.List;

public class SoundCardInfoQuery extends BaseInfoQuery {
    public CustomList<SoundCardInfo> soundCards;

    public SoundCardInfoQuery() {
        SystemInfo sysInfo = SystemInfoSingleton.getInstance();
        HardwareAbstractionLayer hal = sysInfo.getHardware();

        List<SoundCard> soundCardList = hal.getSoundCards();
        List<SoundCardInfo> soundCardInfoList = new ArrayList<>();

        for(SoundCard soundCard: soundCardList) {
            soundCardInfoList.add(new SoundCardInfo(soundCard));
        }

        soundCards = new CustomList<>(soundCardInfoList, "Sound Card", false);
    }

    @Override
    public String toString() {
        return soundCards.toString();
    }

    public static void main(String[] args) {
        SoundCardInfoQuery test = new SoundCardInfoQuery();
        System.out.println(test);
    }
}
