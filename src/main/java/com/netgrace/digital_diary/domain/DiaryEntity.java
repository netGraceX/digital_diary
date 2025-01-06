package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate creationDate;

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDate.now();
    }
}