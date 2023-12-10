package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.comment.CommentDto;
import com.effectivemobile.probation.tms.model.comment.NewCommentDto;
import com.effectivemobile.probation.tms.model.task.Task;
import com.effectivemobile.probation.tms.model.task.TaskDto;
import com.effectivemobile.probation.tms.model.task.NewTaskDto;

import java.util.Collection;

public interface TaskService {

    Task getEntity(long taskId);
    TaskDto get(long taskId, long userId);

    Collection<TaskDto> getAllByUserId(long userId, int from, int size);

    TaskDto add(NewTaskDto taskDto, long authorId);

    CommentDto addComment(NewCommentDto newCommentDto, long userId, long itemId);

    TaskDto patch(TaskDto taskDto, long taskId, long userId);

    Collection<TaskDto> search(String text, long userId, int from, int size);
}
