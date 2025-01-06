package com.netgrace.digital_diary.domain;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper
public interface PersonalDiaryMapper {
    PersonalDiaryDTO personalDiaryToPersonalDiaryDTO(PersonalDiaryEntity diary);
    PersonalDiaryEntity PersonalDiaryDTOtoPersonalDiary(PersonalDiaryDTO diaryDTO);
    void updatePersonalDiaryFromDto(PersonalDiaryDTO personalDiaryDTO, @MappingTarget PersonalDiaryEntity personalDiaryEntity);
}
