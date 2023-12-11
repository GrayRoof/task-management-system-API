package com.effectivemobile.probation.tms.services;

import com.effectivemobile.probation.tms.enums.SortMethod;
import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import com.effectivemobile.probation.tms.model.comment.CommentDto;
import com.effectivemobile.probation.tms.model.comment.NewCommentDto;
import com.effectivemobile.probation.tms.model.task.Task;
import com.effectivemobile.probation.tms.model.task.TaskDto;
import com.effectivemobile.probation.tms.model.task.NewTaskDto;
import com.effectivemobile.probation.tms.model.task.UpdateTaskDto;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;

public interface TaskService {

    /**
     * Возвращает объект 'Задача' по идентификатору
     * @param taskId идентификатор Задачи
     * @return Task
     */
    Task getEntity(long taskId);

    /**
     * Возвращает DTO объекта 'Задача' по идентификатору
     * @param taskId идентификатор Задачи
     * @return TaskDto
     */
    TaskDto get(long taskId);

    /**
     * Возвращает коллекцию DTO объектов 'Задача' с фильтрацией и сортировкой
     * @param text поисковое слово. Поиск осуществляется по Названию и Описанию Задачи
     * @param authorId идентификатор Автора Задач
     * @param performerId идентификатор Исполнителя Задач
     * @param taskStates список статусов Задач
     * @param taskPriorities список приоритетов Задач
     * @param sortMethod значение перечисления полей для сортировки
     * @param from стартовая позиция пагинации
     * @param size количество выводимых объектов на одной странице
     * @return Collection <'TaskDto>
     */
    Collection<TaskDto> getAll(String text, int authorId, int performerId, List<TaskState> taskStates,
                               List<TaskPriority> taskPriorities, SortMethod sortMethod, int from, int size);

    /**
     * Добавляет новую Задачу
     * @param taskDto данные Задачи
     * @param authorId идентификатор Автора Задачи
     * @return TaskDto - DTO созданной Задачи
     */
    TaskDto add(NewTaskDto taskDto, long authorId);

    /**
     * Добавляет Комментарий к Задаче
     * @param newCommentDto данные Комментария
     * @param userId идентификатор Пользователя
     * @param taskId идентификатор Задачи
     * @return CommentDto - DTO созданного комментария
     */
    CommentDto addComment(NewCommentDto newCommentDto, long userId, long taskId);

    /**
     * Обновляет данные Задачи
     * @param userId идентификатор Пользователя
     * @param taskId идентификатор Задачи
     * @param taskDto данные для обновления
     * @return TaskDto - DTO измененной Задачи
     */
    TaskDto patch(long userId, long taskId, UpdateTaskDto taskDto);

    /**
     * Возвращает все Задачи указанного Автора
     * @param authorId идентификатор Автора Задач
     * @param sortMethod значение перечисления полей для сортировки
     * @param direction направление сортировки
     * @param from стартовая позиция пагинации
     * @param size количество выводимых объектов на одной странице
     * @return Collection <'TaskDto>
     */
    Collection<TaskDto> getAllByAuthorId(long authorId,
                                         SortMethod sortMethod,
                                         Sort.Direction direction,
                                         int from,
                                         int size);

    /**
     * Возвращает все Задачи указанного Исполнителя
     * @param performerId идентификатор Исполнителя Задач
     * @param sortMethod значение перечисления полей для сортировки
     * @param direction направление сортировки
     * @param from стартовая позиция пагинации
     * @param size количество выводимых объектов на одной странице
     * @return Collection <'TaskDto>
     */
    Collection<TaskDto> getAllByPerformerId(long performerId,
                                            SortMethod sortMethod,
                                            Sort.Direction direction,
                                            int from, int size);

    /**
     * Удаляет Задачу (разрешено только Авторам)
     * @param taskId идентификатор Задачи
     * @param userId идентификатор Пользователя
     */
    void delete(long taskId, long userId);
}
