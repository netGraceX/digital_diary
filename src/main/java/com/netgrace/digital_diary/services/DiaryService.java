package com.netgrace.digital_diary.services;

import com.netgrace.digital_diary.domain.*;
import com.netgrace.digital_diary.repositories.GoalRepository;
import com.netgrace.digital_diary.repositories.PersonalDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    @Autowired
    private PersonalDiaryRepository personalDiaryRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private PersonalDiaryMapper personalDiaryMapper;

    @Autowired
    private GoalMapper goalMapper;

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

    //GOAL SECTION

    public GoalDTO createGoal(Long diaryId, GoalDTO goalDTO) {
        PersonalDiaryEntity diary = personalDiaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalStateException("Personal Diary with id " + diaryId + " not found"));
        GoalEntity goal = goalMapper.goalDTOtoGoalEntity(goalDTO);
        goal.setDiary(diary);
        return goalMapper.goalEntityToGoalDTO(goalRepository.save(goal));
    }

    public List<GoalDTO> getAllGoals(Long diaryId) {
        PersonalDiaryEntity diary = personalDiaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalStateException("Personal Diary with id " + diaryId + " not found"));
        List<GoalDTO> goals = diary.getGoals().stream().map(goalMapper::goalEntityToGoalDTO).collect(Collectors.toList());
        return goals;
    }

    public GoalDTO getGoalById(Long goalId) {
        return goalRepository.findById(goalId).map(goalMapper::goalEntityToGoalDTO).get();
    }

    public void deleteGoal(Long id) {
        if(goalRepository.existsById(id)) {
            goalRepository.deleteById(id);
        }else {
        throw new IllegalStateException("Goal with id " + id + " not found");
        }
    }

    public GoalDTO updateGoal(Long id, GoalDTO goalDTO) {
        GoalEntity goal = goalRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Goal with id " + id + " not found"));
        GoalEntity updatedGoal = goalMapper.goalDTOtoGoalEntity(goalDTO);
        updatedGoal.setId(goal.getId());
        updatedGoal.setDiary(goal.getDiary());
        GoalEntity savedGoal = goalRepository.save(updatedGoal);
        return goalMapper.goalEntityToGoalDTO(savedGoal);
    }

    public GoalDTO patchGoal(Long id, GoalDTO goalDTO) {
        GoalEntity goal = goalRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("Goal with id" + id + "not found"));
        goalMapper.updateGoalFromDTO(goalDTO, goal);
        return goalMapper.goalEntityToGoalDTO(goalRepository.save(goal));
    }
}

