package com.effectivemobile.probation.tms.controllers;

import com.effectivemobile.probation.tms.exceptions.NotFoundException;
import com.effectivemobile.probation.tms.model.comment.CommentDto;
import com.effectivemobile.probation.tms.model.comment.NewCommentDto;
import com.effectivemobile.probation.tms.model.task.TaskDto;
import com.effectivemobile.probation.tms.model.task.NewTaskDto;
import com.effectivemobile.probation.tms.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{taskId}")
    public TaskDto getById(@RequestHeader("X-Current-User-Id") long userId,
                           @PathVariable long taskId) throws NotFoundException {
        log.info("SERVER TASK получен запрос GET " + taskId);
        return taskService.get(taskId, userId);
    }

    @GetMapping
    public Collection<TaskDto> getAll(@RequestHeader("X-Current-User-Id") long userId,
                                               @RequestParam(required = false, defaultValue = "0") int from,
                                               @RequestParam(required = false, defaultValue = "20") int size) {
        log.info("SERVER TASK получен запрос GET ALL");
        return taskService.getAllByUserId(userId, from, size);
    }

    @PostMapping
    public TaskDto addTask(@RequestHeader("X-Current-User-Id") long authorId,
                           @Valid @RequestBody NewTaskDto taskDto) throws NotFoundException {
        log.info("SERVER TASK получен запрос POST authorId =" + authorId + "тело запроса: " + taskDto);
        return taskService.add(taskDto, authorId);
    }

    @PostMapping("/{taskId}/comment")
    public CommentDto addComment(
            @RequestHeader("X-Current-User-Id") long userId,
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
                                               @RequestParam(required = false, defaultValue = "0") int from,
                                               @RequestParam(required = false, defaultValue = "20") int size) {
        log.info("SERVER TASK получен запрос GET userId = " + userId + " search = " + text);
        return taskService.search(text, userId, from, size);
    }

}
