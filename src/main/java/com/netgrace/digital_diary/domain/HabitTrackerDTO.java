package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
@Data
public class HabitTrackerDTO {
    @NotNull(message = "Habit name is required")
    @Size(min = 3, max = 100, message = "Habit name must be between 3 and 100 characters")
    private String habitName;
    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date must be in the past or today")
    private LocalDate monthDate;
    private List<Integer> daysCompleted;
}
