package com.effectivemobile.probation.tms.repositories;

import com.effectivemobile.probation.tms.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
