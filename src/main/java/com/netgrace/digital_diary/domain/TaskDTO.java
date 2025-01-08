package com.netgrace.digital_diary.domain;

import lombok.Data;

@Data
public class TaskDTO {
    private String description;
    private TaskStatus status;
}
