package com.zheye.question.domain.util;

import java.time.OffsetDateTime;

import static java.time.temporal.ChronoUnit.MILLIS;

public interface OffsetDateTimes {
    static OffsetDateTime currentTime() {
        return OffsetDateTime.now().truncatedTo(MILLIS);
    }
}
