package com.netgrace.digital_diary.repositories;

import com.netgrace.digital_diary.domain.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<GoalEntity, Long> {
}
