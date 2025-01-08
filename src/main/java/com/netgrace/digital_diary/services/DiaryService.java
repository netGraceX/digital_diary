package com.netgrace.digital_diary.services;

import com.netgrace.digital_diary.domain.PersonalDiaryDTO;
import com.netgrace.digital_diary.domain.PersonalDiaryEntity;
import com.netgrace.digital_diary.domain.PersonalDiaryMapper;
import com.netgrace.digital_diary.repositories.PersonalDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    @Autowired
    private PersonalDiaryRepository personalDiaryRepository;

    @Autowired
    private PersonalDiaryMapper personalDiaryMapper;

    @Transactional
    public PersonalDiaryDTO createPersonalDiary(PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryEntity personalDiaryEntity = personalDiaryMapper.personalDiaryDTOtoPersonalDiary(personalDiaryDTO);
        return personalDiaryMapper.personalDiaryToPersonalDiaryDTO(personalDiaryRepository.save(personalDiaryEntity));
    }

    public List<PersonalDiaryDTO> getAllPersonalDiaries() {
        List<PersonalDiaryEntity> personalDiaries = personalDiaryRepository.findAll();
        return personalDiaries.stream()
                .map(personalDiaryMapper::personalDiaryToPersonalDiaryDTO)
                .collect(Collectors.toList());
    }

    public PersonalDiaryDTO getPersonalDiaryById(Long id) {
        PersonalDiaryDTO personalDiary = personalDiaryRepository.findById(id).map(personalDiaryMapper::personalDiaryToPersonalDiaryDTO).get();
        return personalDiary;
    }

    public PersonalDiaryDTO patchPersonalDiary(Long id, PersonalDiaryDTO patchDetails) {
        PersonalDiaryEntity existingPersonalDiary = personalDiaryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Personal Diary with id " + id + " not found"));
        personalDiaryMapper.updatePersonalDiaryFromDto(patchDetails, existingPersonalDiary);
        return personalDiaryMapper.personalDiaryToPersonalDiaryDTO(personalDiaryRepository.save(existingPersonalDiary));
    }

    public PersonalDiaryDTO updatePersonalDiary(Long id, PersonalDiaryDTO updatedPersonalDiaryDTO) {
        PersonalDiaryEntity existingDiary = personalDiaryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Personal Diary with id " + id + " not found"));

        PersonalDiaryEntity updatedDiary = personalDiaryMapper.personalDiaryDTOtoPersonalDiary(updatedPersonalDiaryDTO);
        updatedDiary.setId(existingDiary.getId());

        PersonalDiaryEntity savedDiary = personalDiaryRepository.save(updatedDiary);

        return personalDiaryMapper.personalDiaryToPersonalDiaryDTO(savedDiary);
    }

    public void deletePersonalDiary(Long id) {
        if (personalDiaryRepository.existsById(id)) {
            personalDiaryRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Personal Diary with id " + id + " not found");
        }
    }


}
