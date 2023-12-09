package com.effectivemobile.probation.tms.model.task;

import com.effectivemobile.probation.tms.model.comment.NestedCommentDto;
import com.effectivemobile.probation.tms.model.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private UserDto performer;
    private UserDto author;
    private TaskState state;
    private TaskPriority priority;
    Collection<NestedCommentDto> comments;
}
