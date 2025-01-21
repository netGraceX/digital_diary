package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "goals")
@Data
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Goal name is required")
    @Size(min = 3, max = 100, message = "Goal name must be between 3 and 100 characters")
    private String goalName;
    private boolean achieved;
    @ElementCollection
    @CollectionTable(name = "goal_steps", joinColumns = @JoinColumn(name = "goal_id"))
    private List<String> steps;
    @ManyToOne
    private PersonalDiaryEntity diary;
}
