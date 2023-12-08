package com.effectivemobile.probation.tms.model.comment;

import com.effectivemobile.probation.tms.model.task.Task;
import com.effectivemobile.probation.tms.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "comments")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private int id;
    @Column
    private String text;
    @ManyToOne
    @JoinColumn(name = "Task")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "Author")
    private User author;
}
