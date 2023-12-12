package com.effectivemobile.probation.tms.repositories;

import com.effectivemobile.probation.tms.model.task.Task;

import java.util.Collection;

public interface TaskExtraFilterRepository {
    Collection<Task> findAll(TaskSearchFilter filter);
}
