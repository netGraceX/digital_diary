package com.netgrace.digital_diary.repositories;

import com.netgrace.digital_diary.domain.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
