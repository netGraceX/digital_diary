package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goals")
@Data
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;

    @ManyToOne
    private PersonalDiaryEntity diary;

    private String goalName;
    private boolean achieved;
    @ElementCollection
    @CollectionTable(name = "goal_steps", joinColumns = @JoinColumn(name = "goalId"))
    private List<String> steps;



}
