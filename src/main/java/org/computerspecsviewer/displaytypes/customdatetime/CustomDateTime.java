package org.computerspecsviewer.displaytypes.customdatetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CustomDateTime {
    private LocalDateTime storedDateTime;

    public CustomDateTime(long secondsSinceEpoch) {
        Instant bootTimeInstant = Instant.ofEpochSecond(secondsSinceEpoch);
        ZoneId timeZone = ZoneId.systemDefault();

        storedDateTime = LocalDateTime.ofInstant(bootTimeInstant, timeZone);
    }

    @Override
    public String toString() {
        return storedDateTime.toString();
    }
}
