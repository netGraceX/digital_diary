package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "personal_diary")
@Data
public class PersonalDiaryEntity extends DiaryEntity{

    @OneToMany(mappedBy = "diary")
    private List<Goal> goals;
    @OneToMany(mappedBy = "diary")
    private List<HabitTracker> habitTracker;
    @OneToMany(mappedBy = "diary")
    private List<ToDoList> toDoList;
    private String notes;

}
