package com.netgrace.digital_diary.domain;

import java.time.LocalDate;

public interface Diary {

    Long getDiaryId();
    void setDiaryId(Long diaryId);
    String getTitle();
    void setTitle(String title);
    String getDescription();
    void setDescription(String description);
    LocalDate getCreatedDate();
    void setCreatedDate(LocalDate createdDate);
}
