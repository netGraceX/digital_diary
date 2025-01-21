package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
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
    @NotNull(message = "Habit name is required")
    @Size(min = 3, max = 100, message = "Habit name must be between 3 and 100 characters")
    private String habitName;
    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date must be in the past or today")
    private LocalDate monthDate;
    @ElementCollection
    @CollectionTable(name = "habit_days_completed", joinColumns = @JoinColumn(name = "trackerId"))
    private List<Integer> daysCompleted;
}
