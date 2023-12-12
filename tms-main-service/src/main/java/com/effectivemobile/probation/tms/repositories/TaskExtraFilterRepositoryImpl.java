package com.effectivemobile.probation.tms.repositories;

import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import com.effectivemobile.probation.tms.model.task.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class TaskExtraFilterRepositoryImpl implements TaskExtraFilterRepository{

    private  final EntityManager entityManager;
    @Override
    public Collection<Task> findAll(TaskSearchFilter filter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = criteriaBuilder.createQuery(Task.class);
        Root<Task> task = query.from(Task.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getText() != null) {
            predicates
                    .add(criteriaBuilder.or(
                            criteriaBuilder
                                    .like(criteriaBuilder
                                            .upper(task.get("name")), "%" + filter.getText().toUpperCase() + "%"),
                            criteriaBuilder
                                    .like(criteriaBuilder
                                            .upper(task.get("description")), "%" + filter.getText().toUpperCase() + "%")
                    ));
        }
        if (filter.getAuthorId() != 0) {
            predicates
                    .add(criteriaBuilder.equal(task.get("author"), filter.getAuthorId()));
        }
        if (filter.getAuthorId() != 0) {
            predicates
                    .add(criteriaBuilder.equal(task.get("performer"), filter.getPerformerId()));
        }
        List<TaskState> states = filter.getTaskStates();
        if (states != null && !states.isEmpty()) {
            predicates.add(criteriaBuilder.in(task.get("state")).value(states));
        }
        List<TaskPriority> priorities = filter.getTaskPriorities();
        if (priorities != null && !priorities.isEmpty()) {
            predicates.add(criteriaBuilder.in(task.get("priority")).value(priorities));
        }

        Order order = switch (filter.getSortMethod()) {
            case NAME -> criteriaBuilder.desc(task.get("name"));
            case TASK_STATE -> criteriaBuilder.desc(task.get("state"));
            case TASK_PRIORITY -> criteriaBuilder.desc(task.get("priority"));
            default -> criteriaBuilder.asc(task.get("id"));
        };

        return entityManager.createQuery(query.select(task).where(criteriaBuilder.and(predicates.toArray(Predicate[]::new)))
                        .orderBy(order))
                .setFirstResult(filter.getFrom())
                .setMaxResults(filter.getSize())
                .getResultList();
    }
}
