package com.netgrace.digital_diary.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class HabitTrackerDTO {

    private Long trackerId;
    private String habitName;
    private LocalDate month;
    private List<Integer> daysCompleted;
}
