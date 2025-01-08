package com.netgrace.digital_diary.domain;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface HabitTrackerMapper {

    HabitTrackerEntity habitTrackerDTOEntityToHabitTracker(HabitTrackerDTO tracker);
    HabitTrackerDTO habitTrackerToHabitTrackerDTO(HabitTrackerEntity tracker);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHabitTrackerFromDTO(HabitTrackerDTO trackerDTO, @MappingTarget HabitTrackerEntity trackerEntity);
}

