package com.effectivemobile.probation.tms.model.task;

import com.effectivemobile.probation.tms.model.comment.Comment;
import com.effectivemobile.probation.tms.model.comment.NestedCommentDto;
import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.model.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    public static TaskDto toTaskDto(Task task, Collection<NestedCommentDto> commentsDto) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setPerformer(UserMapper.toDto(task.getPerformer()));
        taskDto.setAuthor(UserMapper.toDto(task.getAuthor()));
        taskDto.setState(task.getState());
        taskDto.setPriority(task.getPriority());
        taskDto.setComments(commentsDto);
        return taskDto;
    }

    public static NestedTaskDto toNestedTaskDto(Task task) {
        NestedTaskDto nestedTaskDto = new NestedTaskDto();
        nestedTaskDto.setId(task.getId());
        nestedTaskDto.setName(task.getName());
        nestedTaskDto.setDescription(task.getDescription());
        nestedTaskDto.setPerformer(UserMapper.toNestedDto(task.getPerformer()));
        nestedTaskDto.setAuthor(UserMapper.toNestedDto(task.getAuthor()));
        nestedTaskDto.setState(task.getState());
        nestedTaskDto.setPriority(task.getPriority());
        return nestedTaskDto;
    }

    public static Task toTask(TaskDto taskDto, Set<Comment> comments) {
        return new Task(
                taskDto.getId(),
                taskDto.getName() == null ? "" : taskDto.getName(),
                taskDto.getDescription(),
                UserMapper.toUser(taskDto.getPerformer()),
                UserMapper.toUser(taskDto.getAuthor()),
                taskDto.getState(),
                taskDto.getPriority(),
                comments
        );
    }

    public static Task toTask(NewTaskDto newTask, User performer, User author) {
        Task task = new Task();
        task.setName(newTask.getName());
        task.setDescription(newTask.getDescription());
        task.setPerformer(performer);
        task.setAuthor(author);
        task.setState(TaskState.STARTED);
        task.setPriority(TaskPriority.valueOf(newTask.getPriority()));
        return task;
    }
}
