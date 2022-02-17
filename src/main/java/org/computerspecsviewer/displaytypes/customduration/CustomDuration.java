package org.computerspecsviewer.displaytypes.customduration;

import java.time.Duration;

public class CustomDuration {
    public Duration duration;

    public CustomDuration(long seconds) {
        duration = Duration.ofSeconds(seconds);
    }

    @Override
    public String toString() {
        long dayAmount = duration.toDays();

        Duration hourDuration = duration.minusDays(dayAmount);
        long hourAmount = hourDuration.toHours();

        Duration minuteDuration = hourDuration.minusHours(hourAmount);
        long minuteAmount = minuteDuration.toMinutes();

        Duration secondDuration = minuteDuration.minusMinutes(minuteAmount);
        long secondAmount = secondDuration.toMillis() / 1000;

        return dayAmount + " days, " + hourAmount + " hours, " + minuteAmount + " minutes, " + secondAmount + " seconds";
    }
}
