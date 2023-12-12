package com.effectivemobile.probation.tms.model.task;

import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import com.effectivemobile.probation.tms.model.user.NestedUserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NestedTaskDto {
    private Long id;
    private String name;
    private String description;
    private NestedUserDto performer;
    private NestedUserDto author;
    private TaskState state;
    private TaskPriority priority;
}
