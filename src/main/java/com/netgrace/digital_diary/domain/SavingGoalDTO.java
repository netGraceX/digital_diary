package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SavingGoalDTO {
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @Size(max = 255, message = "Description note cannot exceed 255 characters")
    private String description;
    private CategoryOrSource category;
    private Double budgetRequired;
    private Double actualSavings;
}
