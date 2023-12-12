package com.effectivemobile.probation.tms.model.task;

import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import lombok.Data;

@Data
public class UpdateTaskDto {
    private String name;
    private String description;
    private Long performer;
    private TaskState state;
    private TaskPriority priority;
}
