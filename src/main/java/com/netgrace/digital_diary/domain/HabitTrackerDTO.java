package com.netgrace.digital_diary.domain;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
@Data
public class HabitTrackerDTO {
    private String habitName;
    private LocalDate monthDate;
    private List<Integer> daysCompleted;
}
