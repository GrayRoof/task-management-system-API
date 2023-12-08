package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.comment.CommentDto;
import com.effectivemobile.probation.tms.model.task.TaskDto;

import java.util.Collection;

public class TaskServiceImpl implements TaskService {
    @Override
    public TaskDto get(long taskId, long userId) {
        return null;
    }

    @Override
    public Collection<TaskDto> getAllByUserId(long userId, int from, int size) {
        return null;
    }

    @Override
    public TaskDto add(TaskDto taskDto, long userId) {
        return null;
    }

    @Override
    public CommentDto addComment(long userId, long taskId, CommentDto commentDto) {
        return null;
    }

    @Override
    public TaskDto patch(TaskDto taskDto, long taskId, long userId) {
        return null;
    }

    @Override
    public Collection<TaskDto> search(String text, long userId, int from, int size) {
        return null;
    }
}
