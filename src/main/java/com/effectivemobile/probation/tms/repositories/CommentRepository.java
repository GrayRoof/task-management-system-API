package com.effectivemobile.probation.tms.repositories;

import com.effectivemobile.probation.tms.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Collection<Comment> findAllByTask_Id(Long taskId);
}