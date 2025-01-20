package com.netgrace.digital_diary.repositories;

import com.netgrace.digital_diary.domain.PersonalDiaryEntity;
import com.netgrace.digital_diary.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalDiaryRepository extends JpaRepository<PersonalDiaryEntity, Long> {

    List<PersonalDiaryEntity> findByAppUser(User appUser);
}
