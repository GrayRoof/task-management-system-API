package com.effectivemobile.probation.tms.model.task;

import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.model.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    public static TaskDto toTaskDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getName(),
                task.getDescription(),
                UserMapper.toUserDto(task.getPerformer()),
                UserMapper.toUserDto(task.getAuthor()),
                task.getState(),
                task.getPriority(),
                new ArrayList<>());
    }

    public static Task toTask(TaskDto taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getName() == null ? "" : taskDto.getName(),
                taskDto.getDescription(),
                UserMapper.toUser(taskDto.getPerformer()),
                UserMapper.toUser(taskDto.getAuthor()),
                taskDto.getState(),
                taskDto.getPriority()
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
