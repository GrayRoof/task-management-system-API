package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.model.comment.CommentDto;
import com.effectivemobile.probation.tms.model.task.Task;
import com.effectivemobile.probation.tms.model.task.TaskDto;
import com.effectivemobile.probation.tms.model.task.TaskMapper;
import com.effectivemobile.probation.tms.model.task.NewTaskDto;
import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    @Override
    public TaskDto get(long taskId, long userId) {
        return null;
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
        return TaskMapper.toTaskDto(task);
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
