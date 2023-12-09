package com.effectivemobile.probation.tms.model.task;

import lombok.Data;

@Data
public class NewTaskDto {
    private String name;
    private String description;
    private Long performer;
    private String priority;
}
