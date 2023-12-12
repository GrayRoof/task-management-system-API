package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.enums.SortMethod;
import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import com.effectivemobile.probation.tms.exceptions.NotAvailableException;
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
import org.springframework.transaction.annotation.Transactional;

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
    public TaskDto get(long taskId) {
        Task task = getEntity(taskId);
        return TaskMapper.toTaskDto(task);
    }

    @Override
    public Collection<TaskDto> getAll(String text,
                                      int authorId,
                                      int performerId,
                                      List<TaskState> taskStates,
                                      List<TaskPriority> taskPriorities,
                                      SortMethod sortMethod,
                                      int from,
                                      int size) {

        TaskSearchFilter filter = TaskSearchFilter.builder()
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

    @Transactional
    @Override
    public TaskDto add(NewTaskDto taskDto, long authorId) {
        User performer = userService.getEntity(taskDto.getPerformer());
        User author = userService.getEntity(authorId);
        Task task = taskRepository.save(TaskMapper.toTask(taskDto, performer, author));
        Task toOutput = taskRepository.get(task.getId());
        return TaskMapper.toTaskDto(toOutput, new HashSet<>());
    }

    @Transactional
    @Override
    public CommentDto addComment(long taskId, long userId, NewCommentDto newCommentDto) {
        User user = userService.getEntity(userId);
        Task task = taskRepository.get(taskId);
        Comment newComment = commentRepository.save(CommentMapper.toComment(newCommentDto, task, user));
        Comment toOutput = commentRepository.get(newComment.getId());
        return CommentMapper.toCommentDto(toOutput);
    }

    @Transactional
    @Override
    public TaskDto patch(long userId, long taskId, UpdateTaskDto taskDto) {
        User requester = userService.getEntity(userId);
        Task task = taskRepository.get(taskId);
        boolean isAuthor = requester.equals(task.getAuthor());
        boolean isPerformer = requester.equals(task.getPerformer());

        if(isAuthor) {
            if(taskDto.getName() != null) {
                task.setName(taskDto.getName());
            }
            if(taskDto.getDescription() != null) {
                task.setDescription(task.getDescription());
            }
            if (taskDto.getPriority() != null) {
                task.setPriority(taskDto.getPriority());
            }
            if (taskDto.getPerformer() != null) {
                User newPerformer = userService.getEntity(taskDto.getPerformer());
                task.setPerformer(newPerformer);
            }
        } else if(isPerformer) {
                log.info("TEST isPerformer");
                checkUpdateForPerformer(taskDto);
                if(taskDto.getState() != null) {
                    task.setState(taskDto.getState());
                }
        } else {
                throw new NotAvailableException("Вы не являетесь Автором или Исполнителем Задачи!");
            }
        Task newTask = taskRepository.save(task);
        Task toOutput = taskRepository.get(newTask.getId());
        TaskDto fullDto = TaskMapper.toTaskDto(toOutput);
        log.info("TASK: Задача с id = {} изменена согласно данным {}", taskId, taskDto);
        return fullDto;
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

    @Transactional
    @Override
    public void delete(long taskId, long userId) {
        Task task = taskRepository.get(taskId);
        User currentUser = userService.getEntity(userId);
        if (!currentUser.equals(task.getAuthor())) {
            throw new RuntimeException("Только Автор задачи может удалить свою задачу!");
        }
        taskRepository.deleteById(taskId);

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

    private void checkUpdateForPerformer(UpdateTaskDto taskDto) {
        if (taskDto.getName() != null) {
            throw new NotAvailableException("Исполнитель не может изменить Название Задачи.");
        }
        if (taskDto.getDescription() != null) {
            throw new NotAvailableException("Исполнитель не может изменить Описание Задачи.");
        }
        if (taskDto.getPerformer() != null) {
            throw new NotAvailableException("Исполнитель не может назначить Исполнителя Задачи.");
        }
        if (taskDto.getPriority() != null) {
            throw new NotAvailableException("Исполнитель не может изменить Приоритет Задачи.");
        }
    }
}
