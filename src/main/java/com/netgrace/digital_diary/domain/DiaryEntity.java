package com.netgrace.digital_diary.domain;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public class DiaryEntity implements Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;
    private String title;
    private String description;
    private LocalDate createdDate;

    @Override
    public Long getDiaryId() {
        return diaryId;
    }

    @Override
    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

}
