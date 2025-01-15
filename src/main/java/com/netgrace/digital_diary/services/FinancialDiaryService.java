package com.netgrace.digital_diary.services;

import com.netgrace.digital_diary.domain.FinancialDiaryDTO;
import com.netgrace.digital_diary.domain.FinancialDiaryEntity;
import com.netgrace.digital_diary.domain.FinancialDiaryMapper;
import com.netgrace.digital_diary.repositories.FinancialDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialDiaryService {

    @Autowired
    private FinancialDiaryRepository financialDiaryRepository;

    @Autowired
    private FinancialDiaryMapper financialDiaryMapper;
    public FinancialDiaryDTO createFinancialDiary(FinancialDiaryDTO financialDiaryDTO) {
        FinancialDiaryEntity diaryEntity = financialDiaryMapper.financialDiaryDTOtoFinancialDiary(financialDiaryDTO);
        return financialDiaryMapper.financialDiaryToFinancialDiaryDTO(financialDiaryRepository.save(diaryEntity));

    }

    public List<FinancialDiaryDTO> getAllFinancialDiaries() {
        List<FinancialDiaryEntity> diaries = financialDiaryRepository.findAll();
        return diaries.stream()
                .map(financialDiaryMapper::financialDiaryToFinancialDiaryDTO)
                .collect(Collectors.toList());
    }

    public FinancialDiaryDTO getFinancialDiaryById(Long id) {
        //todo id check
       return financialDiaryRepository.findById(id).map(financialDiaryMapper::financialDiaryToFinancialDiaryDTO).get();

    }

    public void deleteFinancialDiary(Long id) {
        if(financialDiaryRepository.existsById(id)) {
            financialDiaryRepository.deleteById(id);
        }  else {
            throw new IllegalStateException("Financial Diary with id " + id + " not found");
        }
    }

    public FinancialDiaryDTO updateFinancialDiary(Long id, FinancialDiaryDTO financialDiaryDTO) {
        FinancialDiaryEntity existingDiary = financialDiaryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Financial Diary with id " + id + " not found"));
        FinancialDiaryEntity updatedDiary = financialDiaryMapper.financialDiaryDTOtoFinancialDiary(financialDiaryDTO);
        updatedDiary.setId(existingDiary.getId());
        FinancialDiaryEntity savedDiary = financialDiaryRepository.save(updatedDiary);
        return financialDiaryMapper.financialDiaryToFinancialDiaryDTO(savedDiary);
    }

    public FinancialDiaryDTO patchFinancialDiary(Long id, FinancialDiaryDTO patchDetails) {
        FinancialDiaryEntity existingDiary = financialDiaryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Financial Diary with id " + id + " not found"));
        financialDiaryMapper.updateFinancialDiaryFromDTO(patchDetails, existingDiary);
        return financialDiaryMapper.financialDiaryToFinancialDiaryDTO(financialDiaryRepository.save(existingDiary));
    }
}
