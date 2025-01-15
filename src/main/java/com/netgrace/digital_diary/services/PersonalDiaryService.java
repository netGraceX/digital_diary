package com.netgrace.digital_diary.services;

import com.netgrace.digital_diary.domain.*;
import com.netgrace.digital_diary.repositories.GoalRepository;
import com.netgrace.digital_diary.repositories.HabitTrackerRepository;
import com.netgrace.digital_diary.repositories.PersonalDiaryRepository;
import com.netgrace.digital_diary.repositories.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalDiaryService {

    @Autowired
    private PersonalDiaryRepository personalDiaryRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private HabitTrackerRepository habitTrackerRepository;

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private PersonalDiaryMapper personalDiaryMapper;

    @Autowired
    private GoalMapper goalMapper;

    @Autowired
    private HabitTrackerMapper habitTrackerMapper;

    @Autowired
    private ToDoListMapper toDoListMapper;

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
        //todo id check
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

    public HabitTrackerDTO createHabitTracker(Long diaryId, HabitTrackerDTO habitTrackerDTO) {
        PersonalDiaryEntity diary = personalDiaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalStateException("Diary with id" + diaryId + "not found"));
        HabitTrackerEntity tracker = habitTrackerMapper.habitTrackerDTOEntityToHabitTracker(habitTrackerDTO);
        tracker.setDiary(diary);
        return habitTrackerMapper.habitTrackerToHabitTrackerDTO(habitTrackerRepository.save(tracker));
    }

    public List<HabitTrackerDTO> getAllHabitTrackers(Long diaryId) {
        PersonalDiaryEntity diary = personalDiaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalStateException("Diary with id" + diaryId + "not found"));
        List<HabitTrackerDTO> trackerList = diary.getHabitTracker()
                .stream()
                .map(habitTrackerMapper::habitTrackerToHabitTrackerDTO)
                .collect(Collectors.toList());
        return trackerList;
    }

    public ToDoListDTO createToDoList(Long diaryId, ToDoListDTO toDoListDTO) {
        PersonalDiaryEntity diary = personalDiaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalStateException("Diary with id" + diaryId + "not found"));
        ToDoListEntity toDoList = toDoListMapper.toDoListDTOtoToDoListEntity(toDoListDTO);
        toDoList.setDiary(diary);
        return toDoListMapper.toDoListEntityToToDoListDTO(toDoListRepository.save(toDoList));
    }

    public List<ToDoListDTO> getAllToDoLists(Long diaryId) {
        PersonalDiaryEntity diary = personalDiaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalStateException("Diary with id" + diaryId + "not found"));
        List<ToDoListDTO> todoList = diary.getToDoList()
                .stream()
                .map(toDoListMapper::toDoListEntityToToDoListDTO)
                .collect(Collectors.toList());
        return todoList;
    }

}

