package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "to_do_list")
@Data
public class ToDoListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private PersonalDiaryEntity diary;
    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date must be in the past or today")
    private LocalDate dayDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "todo_id")
    private List<TaskEntity> tasks;
}
