package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.comment.CommentDto;
import com.effectivemobile.probation.tms.model.task.TaskDto;
import com.effectivemobile.probation.tms.model.task.NewTaskDto;

import java.util.Collection;

public interface TaskService {
    TaskDto get(long itemId, long userId);

    Collection<TaskDto> getAllByUserId(long userId, int from, int size);

    TaskDto add(NewTaskDto taskDto, long authorId);

    CommentDto addComment(long userId, long itemId, CommentDto commentDto);

    TaskDto patch(TaskDto taskDto, long itemId, long userId);

    Collection<TaskDto> search(String text, long userId, int from, int size);
}
