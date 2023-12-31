package com.effectivemobile.probation.tms.model.task;

import com.effectivemobile.probation.tms.enums.TaskPriority;
import com.effectivemobile.probation.tms.enums.TaskState;
import com.effectivemobile.probation.tms.model.comment.Comment;
import com.effectivemobile.probation.tms.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "performer")
    private User performer;
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;
    @Enumerated
    @Column(columnDefinition = "bigint")
    private TaskState state;
    @Enumerated
    @Column(columnDefinition = "bigint")
    private TaskPriority priority;
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Comment> comments = new HashSet<>();
}
