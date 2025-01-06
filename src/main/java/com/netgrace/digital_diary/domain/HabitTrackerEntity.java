package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "habit_tracker")
@Data
public class HabitTrackerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private PersonalDiaryEntity diary;
    private String habitName;
    private LocalDate monthDate;
    @ElementCollection
    @CollectionTable(name = "habit_days_completed", joinColumns = @JoinColumn(name = "trackerId"))
    private List<Integer> daysCompleted;
}
