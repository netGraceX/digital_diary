package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;
@Data
public class ToDoListDTO {
    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date must be in the past or today")
    private LocalDate dayDate;
    private List<TaskDTO> tasks;
}
