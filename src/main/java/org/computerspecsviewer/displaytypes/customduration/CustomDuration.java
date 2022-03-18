package org.computerspecsviewer.displaytypes.customduration;

import java.time.Duration;

public class CustomDuration {
    private static final String[] durationInfoStrings = new String[]{"days", "hours", "minutes", "seconds"};

    private Duration duration;
    private DurationLimit durationLimit;
    private boolean isNegative;

    public CustomDuration(long seconds) {
        this(seconds, DurationLimit.UP_TO_DAYS);
    }

    public CustomDuration(long seconds, DurationLimit durationLimit) {
        if(seconds >= 0) {
            this.duration = Duration.ofSeconds(seconds);
            isNegative = false;
        } else {
            isNegative = true;
        }

        this.durationLimit = durationLimit;
    }

    @Override
    public String toString() {
        if(isNegative) {
            return "Cannot be calculated";
        }

        Duration currentDuration = duration;
        long dayAmount = -1;
        long hourAmount = -1;
        long minuteAmount = -1;

        if(durationLimit.compareTo(DurationLimit.UP_TO_DAYS) == 0) {
            dayAmount = currentDuration.toDays();
            currentDuration = currentDuration.minusDays(dayAmount);
        }

        if(durationLimit.compareTo(DurationLimit.UP_TO_HOURS) >= 0) {
            hourAmount = currentDuration.toHours();
            currentDuration = currentDuration.minusHours(hourAmount);
        }

        if(durationLimit.compareTo(DurationLimit.UP_TO_MINUTES) >= 0) {
            minuteAmount = currentDuration.toMinutes();
            currentDuration = currentDuration.minusMinutes(minuteAmount);
        }

        long secondAmount = currentDuration.toMillis() / 1000;

        return createDurationString(new long[]{dayAmount, hourAmount, minuteAmount, secondAmount});
    }

    private String createDurationString(long[] durationInfo) {
        StringBuilder durationBuffer = new StringBuilder();
        int infoCount = 4;

        for(int i = 0; i < infoCount; i++) {
            long currInfo = durationInfo[i];
            String currInfoStr = durationInfoStrings[i];

            if(currInfo >= 0) {
                durationBuffer.append(currInfo);
                durationBuffer.append(" ");
                durationBuffer.append(currInfoStr);

                if(i < (infoCount - 1)) {
                    durationBuffer.append(", ");
                }
            }
        }

        return durationBuffer.toString();
    }
}
