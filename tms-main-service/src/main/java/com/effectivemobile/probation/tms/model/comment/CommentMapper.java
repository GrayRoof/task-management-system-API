package com.effectivemobile.probation.tms.model.comment;

import com.effectivemobile.probation.tms.model.task.Task;
import com.effectivemobile.probation.tms.model.task.TaskMapper;
import com.effectivemobile.probation.tms.model.user.User;
import com.effectivemobile.probation.tms.model.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    public static Comment toComment(NewCommentDto newCommentDto, Task task, User author) {
        Comment comment = new Comment();
        comment.setText(newCommentDto.getText());
        comment.setAuthor(author);
        comment.setTask(task);
        return comment;

    }

    public static NestedCommentDto toNestedCommentDto(Comment comment) {
        return new NestedCommentDto(
                comment.getText(),
                UserMapper.toNestedDto(comment.getAuthor())
        );
    }

    public static CommentDto toCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                TaskMapper.toNestedTaskDto(comment.getTask()),
                UserMapper.toNestedDto(comment.getAuthor())
        );
    }
}

