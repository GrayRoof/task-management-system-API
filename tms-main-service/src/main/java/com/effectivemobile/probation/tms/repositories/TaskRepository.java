package com.effectivemobile.probation.tms.repositories;

import com.effectivemobile.probation.tms.exceptions.NotFoundException;
import com.effectivemobile.probation.tms.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskExtraFilterRepository {
    default Task get(long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Задача с идентификатором #" +
                id + " не зарегистрирована!"));
    }
}
