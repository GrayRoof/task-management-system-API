package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.enums.SortMethod;
import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import com.effectivemobile.probation.tms.model.comment.*;
import com.effectivemobile.probation.tms.model.task.*;
import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.paginations.OffsetPageable;
import com.effectivemobile.probation.tms.repositories.CommentRepository;
import com.effectivemobile.probation.tms.repositories.TaskRepository;
import com.effectivemobile.probation.tms.repositories.TaskSearchFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
        return taskRepository.get(taskId);
    }

    @Override
    public TaskDto get(long taskId, long userId) {
        Task task = getEntity(taskId);
        return TaskMapper.toTaskDto(task); //, comments);
    }

    @Override
    public Collection<TaskDto> getAll(long userId,
                                      String text,
                                      int authorId,
                                      int performerId,
                                      List<TaskState> taskStates,
                                      List<TaskPriority> taskPriorities,
                                      SortMethod sortMethod,
                                      int from,
                                      int size) {

        TaskSearchFilter filter = TaskSearchFilter.builder()
                .userId(userId)
                .text(text)
                .authorId(authorId)
                .performerId(performerId)
                .taskStates(taskStates)
                .taskPriorities(taskPriorities)
                .sortMethod(sortMethod == null ? SortMethod.UNSUPPORTED_METHOD : sortMethod)
                .from(from)
                .size(size)
                .build();
        return taskRepository.findAll(filter)
                .stream()
                .map(TaskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto add(NewTaskDto taskDto, long authorId) {
        User performer = userService.getEntity(taskDto.getPerformer());
        User author = userService.getEntity(authorId);
        Task task = taskRepository.save(TaskMapper.toTask(taskDto, performer, author));
        return TaskMapper.toTaskDto(task, new HashSet<>());
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
    public Collection<TaskDto> getAllByAuthorId(long authorId, SortMethod sortMethod, Sort.Direction direction, int from, int size) {
        return taskRepository.findAllByAuthorId(authorId,
                        OffsetPageable.of(from, size, Sort.by(getOrder(direction, sortMethod))))
                .stream()
                .map(TaskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TaskDto> getAllByPerformerId(long performerId, SortMethod sortMethod, Sort.Direction direction, int from, int size) {
        return taskRepository.findAllByPerformerId(performerId,
                        OffsetPageable.of(from, size, Sort.by(getOrder(direction, sortMethod))))
                .stream()
                .map(TaskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    private Sort.Order getOrder(Sort.Direction direction, SortMethod sortMethod) {
        String property;
        switch (sortMethod) {
            case NAME -> property = "name";
            case TASK_STATE -> property = "state";
            case TASK_PRIORITY -> property = "priority";
            default -> property = "id";
        }
        return new Sort.Order(direction, property);
    }
}
