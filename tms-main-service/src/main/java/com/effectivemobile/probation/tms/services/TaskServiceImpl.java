package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.comment.*;
import com.effectivemobile.probation.tms.model.task.*;
import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.repositories.CommentRepository;
import com.effectivemobile.probation.tms.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;


    @Override
    public Task getEntity(long taskId) {
        return null;
    }

    @Override
    public TaskDto get(long taskId, long userId) {
        Task task = taskRepository.get(taskId);
        Collection<NestedCommentDto> comments = commentRepository.findAllByTask_Id(taskId)
                .stream()
                .map(CommentMapper::toNestedCommentDto)
                .collect(Collectors.toList());
        return TaskMapper.toTaskDto(task, comments);
    }

    @Override
    public Collection<TaskDto> getAllByUserId(long userId, int from, int size) {
        return null;
    }

    @Override
    public TaskDto add(NewTaskDto taskDto, long authorId) {
        User performer = userService.getEntity(taskDto.getPerformer());
        User author = userService.getEntity(authorId);
        Task task = taskRepository.save(TaskMapper.toTask(taskDto, performer, author));
        return TaskMapper.toTaskDto(task, new ArrayList<>());
    }

    @Override
    public CommentDto addComment(NewCommentDto newCommentDto, long userId, long taskId) {
        User user = userService.getEntity(userId);
        Task task = taskRepository.get(taskId);
        Comment newComment = commentRepository.save(CommentMapper.toComment(newCommentDto, task, user));
        return CommentMapper.toCommentDto(newComment);
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
