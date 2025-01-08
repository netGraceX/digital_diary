package com.netgrace.digital_diary.domain;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface PersonalDiaryMapper {
    PersonalDiaryDTO personalDiaryToPersonalDiaryDTO(PersonalDiaryEntity diary);
    PersonalDiaryEntity personalDiaryDTOtoPersonalDiary(PersonalDiaryDTO diaryDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePersonalDiaryFromDto(PersonalDiaryDTO personalDiaryDTO, @MappingTarget PersonalDiaryEntity personalDiaryEntity);
}
