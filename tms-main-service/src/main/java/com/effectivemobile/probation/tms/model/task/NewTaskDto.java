package com.effectivemobile.probation.tms.model.task;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NewTaskDto {
    @NotEmpty(message = "Поле name не может быть пустым.")
    private String name;
    private String description;
    private Long performer;
    @NotEmpty(message = "Необходимо указать приоритет Задачи.")
    private String priority;
}
