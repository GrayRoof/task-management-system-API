package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.enums.SortMethod;
import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import com.effectivemobile.probation.tms.model.comment.CommentDto;
import com.effectivemobile.probation.tms.model.comment.NewCommentDto;
import com.effectivemobile.probation.tms.model.task.Task;
import com.effectivemobile.probation.tms.model.task.TaskDto;
import com.effectivemobile.probation.tms.model.task.NewTaskDto;

import java.util.Collection;
import java.util.List;

public interface TaskService {

    Task getEntity(long taskId);
    TaskDto get(long taskId, long userId);

    Collection<TaskDto> getAll(long userId, String text, int authorId, int performerId, List<TaskState> taskStates,
                               List<TaskPriority> taskPriorities, SortMethod sortMethod, int from, int size);

    TaskDto add(NewTaskDto taskDto, long authorId);

    CommentDto addComment(NewCommentDto newCommentDto, long userId, long itemId);

    TaskDto patch(TaskDto taskDto, long taskId, long userId);

    Collection<TaskDto> search(String text, long userId, int from, int size);

    Collection<TaskDto> getAllByAuthorId(long authorId, SortMethod sortMethod, int from, int size);

    Collection<TaskDto> getAllByPerformerId(long performerId, SortMethod sortMethod, int from, int size);
}
