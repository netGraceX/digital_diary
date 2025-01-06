package com.netgrace.digital_diary.domain;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
@Data
public class ToDoListDTO {
    private LocalDate dayDate;
    private List<TaskEntity> tasks;
}
