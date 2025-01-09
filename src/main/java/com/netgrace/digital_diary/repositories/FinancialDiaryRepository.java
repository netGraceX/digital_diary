package com.netgrace.digital_diary.repositories;

import com.netgrace.digital_diary.domain.FinancialDiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialDiaryRepository extends JpaRepository<FinancialDiaryEntity, Long> {
}
