package org.computerspecsviewer.displaytypes.customduration;

public enum DurationLimit {
    UP_TO_SECONDS(1),
    UP_TO_MINUTES(2),
    UP_TO_HOURS(3),
    UP_TO_DAYS(4);

    private int durationLimit;

    DurationLimit(int durationLimit) {
        this.durationLimit = durationLimit;
    }

    public int getDurationLimit() {
        return durationLimit;
    }
}
