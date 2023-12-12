package com.effectivemobile.probation.tms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskState {
    UNKNOWN(0, "НЕ ОПРЕДЕЛЕНО"),
    STARTED(1, "НЕ НАЧАТО"),
    IN_WORK(2, "В РАБОТЕ"),
    COMPLETED(3, "ЗАВЕРШЕНО"),
    CANCELED(4, "ОТМЕНЕНО"),
    POSTPONED(5, "ОТЛОЖЕНО");

    private final long id;
    private final String value;
}
