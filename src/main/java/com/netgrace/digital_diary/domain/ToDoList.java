package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "to_do_list")
@Data
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toDoId;

    @ManyToOne
    private PersonalDiaryEntity diary;
}
