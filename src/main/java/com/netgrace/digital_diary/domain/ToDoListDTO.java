package com.netgrace.digital_diary.domain;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
@Data
public class ToDoListDTO {
    private Long toDoId;
    private LocalDate day;
    private List<Task> tasks;
}
