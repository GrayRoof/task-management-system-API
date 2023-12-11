package com.effectivemobile.probation.tms.controllers;

import com.effectivemobile.probation.tms.enums.SortMethod;
import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import com.effectivemobile.probation.tms.exceptions.NotFoundException;
import com.effectivemobile.probation.tms.model.comment.CommentDto;
import com.effectivemobile.probation.tms.model.comment.NewCommentDto;
import com.effectivemobile.probation.tms.model.task.TaskDto;
import com.effectivemobile.probation.tms.model.task.NewTaskDto;
import com.effectivemobile.probation.tms.services.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping()
    public Collection<TaskDto> getAll(@RequestHeader("X-Current-User-Id") long userId,
                                      @RequestParam(required = false) String text,
                                      @RequestParam(required = false) int authorId,
                                      @RequestParam(required = false) int performerId,
                                      @RequestParam(required = false) List<TaskState> taskStates,
                                      @RequestParam(required = false) List<TaskPriority> taskPriorities,
                                      @RequestParam(required = false, defaultValue = "ID") SortMethod sortMethod,
                                      @PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                      @Positive @RequestParam(defaultValue = "20") int size) {
        log.info("SERVER TASK получен запрос GET ALL от пользователя {}", userId);
        return taskService.getAll(userId, text, authorId, performerId, taskStates, taskPriorities, sortMethod, from, size);
    }

    @GetMapping("/{taskId}")
    public TaskDto getById(@RequestHeader("X-Current-User-Id") long userId,
                           @PathVariable long taskId) throws NotFoundException {
        log.info("SERVER TASK получен запрос GET {} от пользователя {}", taskId, userId);
        return taskService.get(taskId, userId);
    }

    @GetMapping("/i-am-author")
    public Collection<TaskDto> getAllMeAuthor(@RequestHeader("X-Current-User-Id") long authorId,
                                      @RequestParam(required = false, defaultValue = "ID") SortMethod sortMethod,
                                      @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
                                      @Positive @RequestParam(required = false, defaultValue = "20") int size) {
        log.info("SERVER TASK получен запрос GET ALL where user is author");
        return taskService.getAllByAuthorId(authorId, sortMethod, from, size);
    }

    @GetMapping("/i-am-performer")
    public Collection<TaskDto> getAllMePerformer(@RequestHeader("X-Current-User-Id") long performerId,
                                              @RequestParam(required = false, defaultValue = "ID") SortMethod sortMethod,
                                              @PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                              @Positive @RequestParam(defaultValue = "20") int size) {
        log.info("SERVER TASK получен запрос GET ALL where user is performer");
        return taskService.getAllByPerformerId(performerId, sortMethod, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto addTask(@RequestHeader("X-Current-User-Id") long authorId,
                           @Valid @RequestBody NewTaskDto taskDto) throws NotFoundException {
        log.info("SERVER TASK получен запрос POST authorId =" + authorId + "тело запроса: " + taskDto);
        return taskService.add(taskDto, authorId);
    }

    @PostMapping("/{taskId}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@RequestHeader("X-Current-User-Id") long userId,
                                 @PathVariable long taskId,
                                 @RequestBody NewCommentDto newCommentDto
    ) {
        log.info("SERVER TASK COMMENT получен запрос POST userId = " + userId
                + " taskId = " + taskId + " тело запроса: " + newCommentDto);
        return taskService.addComment(newCommentDto, userId, taskId);
    }

    @PatchMapping("/{taskId}")
    public TaskDto patchTask(@RequestHeader("X-Current-User-Id") long userId,
                             @PathVariable long taskId,
                             @Valid @RequestBody TaskDto taskDto) throws NotFoundException {
        log.info("SERVER TASK получен запрос PATCH userId = " + userId
                + " TaskId = " + taskId + " тело запроса " + taskDto);
        return taskService.patch(taskDto, taskId, userId);
    }

    @GetMapping("/search")
    public Collection<TaskDto> search(@RequestHeader("X-Current-User-Id") long userId,
                                      @RequestParam String text,
                                      @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
                                      @Positive @RequestParam(required = false, defaultValue = "20") int size) {
        log.info("SERVER TASK получен запрос GET userId = " + userId + " search = " + text);
        return taskService.search(text, userId, from, size);
    }

}
