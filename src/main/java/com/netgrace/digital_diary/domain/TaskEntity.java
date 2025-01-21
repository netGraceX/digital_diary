package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tasks")
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255, message = "Description note cannot exceed 255 characters")
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne
    private ToDoListEntity todo;
}

