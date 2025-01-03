package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "habit_tracker")
@Data
public class HabitTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackerId;

    @ManyToOne
    private PersonalDiaryEntity diary;
}
