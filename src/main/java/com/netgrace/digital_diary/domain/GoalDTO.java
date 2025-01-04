package com.netgrace.digital_diary.domain;

import lombok.Data;
import java.util.List;

@Data
public class GoalDTO {

    private Long goalId;
    private String goalName;
    private boolean achieved;
    private List<String> steps;
}
