package com.effectivemobile.probation.tms.model.comment;

import com.effectivemobile.probation.tms.model.task.NestedTaskDto;
import com.effectivemobile.probation.tms.model.user.NestedUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String text;
    private NestedTaskDto task;
    private NestedUserDto author;
}
