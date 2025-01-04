package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "habit_tracker")
@Data
public class HabitTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackerId;

    @ManyToOne
    private PersonalDiaryEntity diary;

    private String habitName;
    private LocalDate month;
    @ElementCollection
    @CollectionTable(name = "habit_days_completed", joinColumns = @JoinColumn(name = "trackerId"))
    private List<Integer> daysCompleted;
}
