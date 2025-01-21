package com.netgrace.digital_diary.services;

import com.netgrace.digital_diary.domain.FinancialDiaryDTO;
import com.netgrace.digital_diary.domain.FinancialDiaryEntity;
import com.netgrace.digital_diary.domain.FinancialDiaryMapper;
import com.netgrace.digital_diary.domain.PersonalDiaryEntity;
import com.netgrace.digital_diary.exceptions.UnauthorizedException;
import com.netgrace.digital_diary.repositories.FinancialDiaryRepository;
import com.netgrace.digital_diary.security.User;
import com.netgrace.digital_diary.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinancialDiaryService {

    @Autowired
    private FinancialDiaryRepository financialDiaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final AuthorizationService authorizationService;

    @Autowired
    private FinancialDiaryMapper financialDiaryMapper;

    private FinancialDiaryEntity checkUserAuthorization (Long userId, Long diaryId) {
        User user = authorizationService.verifyUserOwnership(userId);
        FinancialDiaryEntity diary = financialDiaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalStateException("Financial Diary with id " + diaryId + " not found"));
        if (!diary.getAppUser().getId().equals(userId)) {
            throw new UnauthorizedException("You are not authorized to access this diary");
        }
        return diary;
    }

    public FinancialDiaryDTO createFinancialDiary(Long userId, FinancialDiaryDTO financialDiaryDTO) {
        User user = authorizationService.verifyUserOwnership(userId);
        FinancialDiaryEntity diaryEntity = financialDiaryMapper.financialDiaryDTOtoFinancialDiary(financialDiaryDTO);
        diaryEntity.setAppUser(user);
        return financialDiaryMapper.financialDiaryToFinancialDiaryDTO(financialDiaryRepository.save(diaryEntity));
    }

    public List<FinancialDiaryDTO> getAllFinancialDiaries(Long userId) {
        User user = authorizationService.verifyUserOwnership(userId);
        List<FinancialDiaryEntity> diaries = financialDiaryRepository.findByAppUser(user);
        return diaries.stream()
                .map(financialDiaryMapper::financialDiaryToFinancialDiaryDTO)
                .collect(Collectors.toList());

    }

    public FinancialDiaryDTO getFinancialDiaryById(Long userId, Long diaryId) {
        FinancialDiaryEntity diary = checkUserAuthorization(userId, diaryId);
        return financialDiaryMapper.financialDiaryToFinancialDiaryDTO(diary);
    }

    public void deleteFinancialDiary(Long userId, Long diaryId) {
        FinancialDiaryEntity diary = checkUserAuthorization(userId, diaryId);
        financialDiaryRepository.deleteById(diaryId);
    }

    public FinancialDiaryDTO updateFinancialDiary(Long userId, Long diaryId, FinancialDiaryDTO financialDiaryDTO) {
        FinancialDiaryEntity existingDiary = checkUserAuthorization(userId, diaryId);
        FinancialDiaryEntity updatedDiary = financialDiaryMapper.financialDiaryDTOtoFinancialDiary(financialDiaryDTO);
        updatedDiary.setId(existingDiary.getId());
        updatedDiary.setAppUser(existingDiary.getAppUser());
        updatedDiary.setCreationDate(existingDiary.getCreationDate());
        FinancialDiaryEntity savedDiary = financialDiaryRepository.save(updatedDiary);
        return financialDiaryMapper.financialDiaryToFinancialDiaryDTO(savedDiary);
    }

    public FinancialDiaryDTO patchFinancialDiary(Long userId, Long diaryId, FinancialDiaryDTO patchDetails) {
        FinancialDiaryEntity existingDiary = checkUserAuthorization(userId, diaryId);
        financialDiaryMapper.updateFinancialDiaryFromDTO(patchDetails, existingDiary);
        return financialDiaryMapper.financialDiaryToFinancialDiaryDTO(financialDiaryRepository.save(existingDiary));
    }
}
