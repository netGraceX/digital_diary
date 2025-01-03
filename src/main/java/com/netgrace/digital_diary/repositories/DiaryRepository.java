package com.netgrace.digital_diary.repositories;

import com.netgrace.digital_diary.domain.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

}
