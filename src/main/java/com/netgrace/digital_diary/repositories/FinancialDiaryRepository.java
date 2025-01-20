package com.netgrace.digital_diary.repositories;

import com.netgrace.digital_diary.domain.FinancialDiaryEntity;
import com.netgrace.digital_diary.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialDiaryRepository extends JpaRepository<FinancialDiaryEntity, Long> {

    List<FinancialDiaryEntity> findByAppUser(User appUser);
}
