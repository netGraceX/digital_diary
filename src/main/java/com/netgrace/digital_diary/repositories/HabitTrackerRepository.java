package com.netgrace.digital_diary.repositories;

import com.netgrace.digital_diary.domain.HabitTrackerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitTrackerRepository extends JpaRepository<HabitTrackerEntity, Long> {
}
