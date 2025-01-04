package com.netgrace.digital_diary.repositories;

import com.netgrace.digital_diary.domain.PersonalDiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDiaryRepository extends JpaRepository<PersonalDiaryEntity, Long> {

}
