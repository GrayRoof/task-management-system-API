package com.effectivemobile.probation.tms.model.task;

import com.effectivemobile.probation.tms.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tasks")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Task {
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "Performer")
    private User performer;
    @ManyToOne
    @JoinColumn(name = "Author")
    private User author;
    @Column
    private int state;
    @Column
    private int priority;
}
