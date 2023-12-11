package com.effectivemobile.probation.tms.repositories;

import com.effectivemobile.probation.tms.enums.SortMethod;
import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TaskSearchFilter {
    private String text;
    private int authorId;
    private int performerId;
    private List<TaskState> taskStates;
    private List<TaskPriority> taskPriorities;
    private SortMethod sortMethod;
    private int from;
    private int size;
}
