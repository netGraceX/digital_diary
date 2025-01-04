package com.netgrace.digital_diary.domain;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper
public interface PersonalDiaryMapper {

    PersonalDiaryDTO personalDiaryToPersonalDiaryDTO(PersonalDiaryEntity bujo);
    PersonalDiaryEntity PersonalDiaryDTOtoPersonalDiary(PersonalDiaryDTO bujoDTO);

    void updatePersonalDiaryFromDto(PersonalDiaryDTO personalDiaryDTO, @MappingTarget PersonalDiaryEntity personalDiaryEntity);
}
