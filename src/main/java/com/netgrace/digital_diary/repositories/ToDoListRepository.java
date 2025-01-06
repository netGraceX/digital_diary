package com.netgrace.digital_diary.repositories;

import com.netgrace.digital_diary.domain.ToDoListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoListEntity, Long> {
}
