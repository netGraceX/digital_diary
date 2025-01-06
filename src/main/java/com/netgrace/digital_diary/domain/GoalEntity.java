package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goals")
@Data
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String goalName;
    private boolean achieved;
    @ElementCollection
    @CollectionTable(name = "goal_steps", joinColumns = @JoinColumn(name = "goal_id"))
    private List<String> steps;

    @ManyToOne
    private PersonalDiaryEntity diary;
}
