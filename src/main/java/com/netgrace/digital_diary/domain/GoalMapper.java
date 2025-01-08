package com.netgrace.digital_diary.domain;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface GoalMapper {
    GoalDTO goalEntityToGoalDTO(GoalEntity goal);
    GoalEntity goalDTOtoGoalEntity(GoalDTO goalDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGoalFromDTO(GoalDTO goalDTO, @MappingTarget GoalEntity goalEntity);
}
