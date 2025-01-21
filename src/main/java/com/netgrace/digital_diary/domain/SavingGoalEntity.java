package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "saving_goals")
@Data
public class SavingGoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @Size(max = 255, message = "Description note cannot exceed 255 characters")
    private String description;
    @Enumerated(EnumType.STRING)
    private CategoryOrSource category;
    private Double budgetRequired;
    private Double actualSavings;
    @ManyToOne
    private FinancialDiaryEntity diary;
}
