package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "goals")
@Data
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;

    @ManyToOne
    private PersonalDiaryEntity diary;



}
