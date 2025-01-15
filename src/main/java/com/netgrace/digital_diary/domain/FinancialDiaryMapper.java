package com.netgrace.digital_diary.domain;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface FinancialDiaryMapper {

    FinancialDiaryDTO financialDiaryToFinancialDiaryDTO(FinancialDiaryEntity diary);
    FinancialDiaryEntity financialDiaryDTOtoFinancialDiary(FinancialDiaryDTO diaryDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFinancialDiaryFromDTO(FinancialDiaryDTO diaryDTO, @MappingTarget FinancialDiaryEntity diary);

}

