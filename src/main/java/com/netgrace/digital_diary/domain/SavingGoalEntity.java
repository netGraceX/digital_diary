package com.netgrace.digital_diary.domain;

import javax.persistence.*;

@Entity
@Table(name = "saving_goals")
public class SavingGoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private CategoryOrSource category;
    private Double budgetRequired;
    private Double actualSavings;
    @ManyToOne
    private FinancialDiaryEntity diary;
}
