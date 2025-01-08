package com.netgrace.digital_diary.domain;

import lombok.Data;
import java.util.List;

@Data
public class PersonalDiaryDTO {
    private String title;
    private String description;
    private List<GoalDTO> goals;
    private List<HabitTrackerDTO> habitTracker;
    private List<ToDoListDTO> toDoList;
}