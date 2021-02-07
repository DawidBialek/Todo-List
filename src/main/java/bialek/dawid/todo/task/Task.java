package bialek.dawid.todo.task;

import bialek.dawid.todo.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;
    @Column(name = "priority")
    private int priority;
    @Column(name = "deadline")
    private Date deadline;
    @Column(name = "is_done")
    private Boolean isDone;


    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
}
