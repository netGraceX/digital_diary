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

@Service
public class DiaryService {

    @Autowired
    private PersonalDiaryRepository personalDiaryRepository;

    @Autowired
    private PersonalDiaryMapper personalDiaryMapper;

    @Transactional
    public PersonalDiaryDTO createPersonalDiary(PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryEntity personalDiaryEntity = personalDiaryMapper.PersonalDiaryDTOtoPersonalDiary(personalDiaryDTO);
        //personalDiaryEntity.getGoals().forEach(
        //        goalEntity -> goalEntity.setDiary(personalDiaryEntity)
        //);
        return personalDiaryMapper.personalDiaryToPersonalDiaryDTO(personalDiaryRepository.save(personalDiaryEntity));
    }

    public List<PersonalDiaryEntity> getAllPersonalDiaries() {
        return personalDiaryRepository.findAll();
    }

    public Optional<PersonalDiaryEntity> getPersonalDiaryById(Long id) {
        return personalDiaryRepository.findById(id);
    }

    public PersonalDiaryDTO updatePersonalDiary(Long id, PersonalDiaryDTO updatedPersonalDiaryDTO) {
        PersonalDiaryEntity existingPersonalDiary = personalDiaryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Personal Diary with id " + id + " not found"));
        personalDiaryMapper.updatePersonalDiaryFromDto(updatedPersonalDiaryDTO, existingPersonalDiary);
        return personalDiaryMapper.personalDiaryToPersonalDiaryDTO(personalDiaryRepository.save(existingPersonalDiary));
    }

    public void deletePersonalDiary(Long id) {
        if (personalDiaryRepository.existsById(id)) {
            personalDiaryRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Personal Diary with id " + id + " not found");
        }
    }


}
