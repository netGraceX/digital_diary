package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TaskDTO {
    @Size(max = 255, message = "Description note cannot exceed 255 characters")
    private String description;
    private TaskStatus status;
}
