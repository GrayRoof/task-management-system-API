package com.effectivemobile.probation.tms.model.task;

import com.effectivemobile.probation.tms.model.comment.CommentDto;
import com.effectivemobile.probation.tms.model.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private UserDto performer;
    private UserDto author;
    private TaskState state;
    private TaskPriority priority;
    Collection<CommentDto> comments;
}
