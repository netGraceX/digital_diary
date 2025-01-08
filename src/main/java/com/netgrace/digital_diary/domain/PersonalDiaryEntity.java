package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "personal_diary")
@Data
public class PersonalDiaryEntity extends DiaryEntity {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private List<GoalEntity> goals;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private List<HabitTrackerEntity> habitTracker;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private List<ToDoListEntity> toDoList;
}
