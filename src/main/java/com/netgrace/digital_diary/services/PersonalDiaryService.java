package com.netgrace.digital_diary.services;

import com.netgrace.digital_diary.domain.*;
import com.netgrace.digital_diary.exceptions.UnauthorizedException;
import com.netgrace.digital_diary.repositories.GoalRepository;
import com.netgrace.digital_diary.repositories.HabitTrackerRepository;
import com.netgrace.digital_diary.repositories.PersonalDiaryRepository;
import com.netgrace.digital_diary.repositories.ToDoListRepository;
import com.netgrace.digital_diary.security.User;
import com.netgrace.digital_diary.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalDiaryService {

    @Autowired
    private PersonalDiaryRepository personalDiaryRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private HabitTrackerRepository habitTrackerRepository;

    @Autowired
    private UserRepository userRepository;

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

    @Autowired
    private AuthorizationService authorizationService;

    private PersonalDiaryEntity checkUserAuthorization (Long userId, Long diaryId) {
        User user = authorizationService.verifyUserOwnership(userId);
        PersonalDiaryEntity diary = personalDiaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalStateException("Personal Diary with id " + diaryId + " not found"));
        if (!diary.getAppUser().getId().equals(userId)) {
            throw new UnauthorizedException("You are not authorized to access this diary");
        }
        return diary;
    }
    public PersonalDiaryDTO createPersonalDiary(Long userId, PersonalDiaryDTO personalDiaryDTO) {
        User user = authorizationService.verifyUserOwnership(userId);
        PersonalDiaryEntity diary = personalDiaryMapper.personalDiaryDTOtoPersonalDiary(personalDiaryDTO);
        diary.setAppUser(user);
        return personalDiaryMapper.personalDiaryToPersonalDiaryDTO(personalDiaryRepository.save(diary));
    }

    public List<PersonalDiaryDTO> getAllPersonalDiaries(Long userId) {
        User user = authorizationService.verifyUserOwnership(userId);
        List<PersonalDiaryEntity> personalDiaries = personalDiaryRepository.findByAppUser(user);
        return personalDiaries.stream()
                .map(personalDiaryMapper::personalDiaryToPersonalDiaryDTO)
                .collect(Collectors.toList());
    }

    public PersonalDiaryDTO getPersonalDiaryById(Long userId, Long diaryId) {
        PersonalDiaryEntity diary = checkUserAuthorization (userId, diaryId);
        return personalDiaryMapper.personalDiaryToPersonalDiaryDTO(diary);
    }

    public PersonalDiaryDTO patchPersonalDiary(Long userId, Long diaryId, PersonalDiaryDTO patchDetails) {
        PersonalDiaryEntity existingDiary = checkUserAuthorization (userId, diaryId);
        personalDiaryMapper.updatePersonalDiaryFromDto(patchDetails, existingDiary);
        return personalDiaryMapper.personalDiaryToPersonalDiaryDTO(personalDiaryRepository.save(existingDiary));
    }

    public PersonalDiaryDTO updatePersonalDiary(Long userId, Long diaryId, PersonalDiaryDTO updatedPersonalDiaryDTO) {
        PersonalDiaryEntity existingDiary = checkUserAuthorization (userId, diaryId);
        PersonalDiaryEntity updatedDiary = personalDiaryMapper.personalDiaryDTOtoPersonalDiary(updatedPersonalDiaryDTO);
        updatedDiary.setId(existingDiary.getId());
        updatedDiary.setAppUser(existingDiary.getAppUser());
        updatedDiary.setCreationDate(existingDiary.getCreationDate());
        PersonalDiaryEntity savedDiary = personalDiaryRepository.save(updatedDiary);
        return personalDiaryMapper.personalDiaryToPersonalDiaryDTO(savedDiary);
    }

    public void deletePersonalDiary(Long userId, Long diaryId) {
        PersonalDiaryEntity existingDiary = checkUserAuthorization (userId, diaryId);
        personalDiaryRepository.deleteById(diaryId);
    }

    public GoalDTO createGoal(Long userId, Long diaryId, GoalDTO goalDTO) {
        PersonalDiaryEntity diary = checkUserAuthorization (userId, diaryId);
        GoalEntity goal = goalMapper.goalDTOtoGoalEntity(goalDTO);
        goal.setDiary(diary);
        return goalMapper.goalEntityToGoalDTO(goalRepository.save(goal));
    }

    public List<GoalDTO> getAllGoals(Long userId, Long diaryId) {
        PersonalDiaryEntity diary = checkUserAuthorization (userId, diaryId);
        List<GoalDTO> goals = diary.getGoals().stream().map(goalMapper::goalEntityToGoalDTO).collect(Collectors.toList());
        return goals;
    }

    public HabitTrackerDTO createHabitTracker(Long userId, Long diaryId, HabitTrackerDTO habitTrackerDTO) {
        PersonalDiaryEntity diary = checkUserAuthorization (userId, diaryId);
        HabitTrackerEntity tracker = habitTrackerMapper.habitTrackerDTOEntityToHabitTracker(habitTrackerDTO);
        tracker.setDiary(diary);
        return habitTrackerMapper.habitTrackerToHabitTrackerDTO(habitTrackerRepository.save(tracker));
    }

    public List<HabitTrackerDTO> getAllHabitTrackers(Long userId, Long diaryId) {
        PersonalDiaryEntity diary = checkUserAuthorization (userId, diaryId);
        List<HabitTrackerDTO> trackerList = diary.getHabitTracker()
                .stream()
                .map(habitTrackerMapper::habitTrackerToHabitTrackerDTO)
                .collect(Collectors.toList());
        return trackerList;
    }

    public ToDoListDTO createToDoList(Long userId, Long diaryId, ToDoListDTO toDoListDTO) {
        PersonalDiaryEntity diary = checkUserAuthorization (userId, diaryId);
        ToDoListEntity toDoList = toDoListMapper.toDoListDTOtoToDoListEntity(toDoListDTO);
        toDoList.setDiary(diary);
        return toDoListMapper.toDoListEntityToToDoListDTO(toDoListRepository.save(toDoList));
    }

    public List<ToDoListDTO> getAllToDoLists(Long userId, Long diaryId) {
        PersonalDiaryEntity diary = checkUserAuthorization (userId, diaryId);
        List<ToDoListDTO> todoList = diary.getToDoList()
                .stream()
                .map(toDoListMapper::toDoListEntityToToDoListDTO)
                .collect(Collectors.toList());
        return todoList;
    }

}

