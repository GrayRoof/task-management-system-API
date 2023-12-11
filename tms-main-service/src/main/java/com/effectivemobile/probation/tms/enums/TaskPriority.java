package com.effectivemobile.probation.tms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskPriority {
    UNKNOWN(0, "НЕ ОПРЕДЕЛЕНО"),
    HIGH(1, "ВЫСОКИЙ"),
    NORMAL(2, "СРЕДНИЙ"),
    LOW(3, "НИЗКИЙ");

    private final long id;
    private final String value;
}
