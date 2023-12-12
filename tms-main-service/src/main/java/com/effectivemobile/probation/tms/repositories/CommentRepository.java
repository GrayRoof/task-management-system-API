package com.effectivemobile.probation.tms.repositories;

import com.effectivemobile.probation.tms.exceptions.NotFoundException;
import com.effectivemobile.probation.tms.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    default Comment get(long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Комментарий с идентификатором #" +
                id + " не найден!"));
    }
    Collection<Comment> findAllByTask_Id(Long taskId);
}