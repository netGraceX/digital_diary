package com.netgrace.digital_diary.domain;

import lombok.Data;

@Data
public class SavingGoalDTO {
    private String name;
    private String description;
    private CategoryOrSource category;
    private Double budgetRequired;
    private Double actualSavings;
}
