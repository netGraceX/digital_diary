package com.netgrace.digital_diary.controllers;

import com.netgrace.digital_diary.domain.GoalDTO;
import com.netgrace.digital_diary.domain.HabitTrackerDTO;
import com.netgrace.digital_diary.domain.PersonalDiaryDTO;
import com.netgrace.digital_diary.domain.ToDoListDTO;
import com.netgrace.digital_diary.services.PersonalDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/diaries")
public class PersonalDiaryController {

    private final PersonalDiaryService personalDiaryService;

    @Autowired
    public PersonalDiaryController(PersonalDiaryService journalService) {
        this.personalDiaryService = journalService;
    }

    @PostMapping("/personal_diary/{userId}")
    public ResponseEntity<PersonalDiaryDTO> createPersonalDiary(@PathVariable Long userId,
                                                                @Valid @RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO createdPersonalDiary = personalDiaryService.createPersonalDiary(userId, personalDiaryDTO);
        return ResponseEntity.ok(createdPersonalDiary);
    }

    @GetMapping("/personal_diary/{userId}")
    public ResponseEntity<List<PersonalDiaryDTO>> getAllPersonalDiaries(@PathVariable Long userId) {
        return ResponseEntity.ok(personalDiaryService.getAllPersonalDiaries(userId));
    }

    @GetMapping("/personal_diary/{userId}/{id}")
    public PersonalDiaryDTO getPersonalDiaryById(@PathVariable Long userId, @PathVariable Long id) {
        return personalDiaryService.getPersonalDiaryById(userId, id);
    }

    @PutMapping("/personal_diary/{userId}/{id}")
    public ResponseEntity<PersonalDiaryDTO> updatePersonalDiary(@PathVariable Long userId,
                                                                @PathVariable Long id,
                                                                @Valid @RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO updatedPersonalDiaryEntity = personalDiaryService.updatePersonalDiary(userId, id, personalDiaryDTO);
        return ResponseEntity.ok(updatedPersonalDiaryEntity);
    }

    @PatchMapping("/personal_diary/{userId}/{id}")
    public ResponseEntity<PersonalDiaryDTO> patchPersonalDiary(@PathVariable Long userId,
                                                               @PathVariable Long id,
                                                               @Valid @RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO updatedPersonalDiary = personalDiaryService.patchPersonalDiary(userId, id, personalDiaryDTO);
        return  ResponseEntity.ok(updatedPersonalDiary);
    }

    @DeleteMapping("/personal_diary/{userId}/{id}")
    public ResponseEntity<Void> deletePersonalDiary(@PathVariable Long userId, @PathVariable Long id) {
        personalDiaryService.deletePersonalDiary(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/personal_diary/{userId}/{diaryId}/goals")
    public ResponseEntity<GoalDTO> createGoal(@PathVariable Long userId,
                                              @PathVariable Long diaryId,
                                              @Valid @RequestBody GoalDTO goalDTO) {
        GoalDTO createdGoal = personalDiaryService.createGoal(
                userId,
                diaryId,
                goalDTO
        );
        return ResponseEntity.ok(createdGoal);
    }

    @GetMapping("/personal_diary/{userId}/{diaryId}/goals")
    public ResponseEntity<List<GoalDTO>> getAllGoals(@PathVariable Long userId, @PathVariable Long diaryId) {
        return ResponseEntity.ok(personalDiaryService.getAllGoals(userId, diaryId));
    }

    @PostMapping("/personal_diary/{userId}/{diaryId}/habits")
    public ResponseEntity<HabitTrackerDTO> createHabitTracker(@PathVariable Long userId,
                                                              @PathVariable Long diaryId,
                                                              @Valid @RequestBody HabitTrackerDTO habitTrackerDTO) {
        HabitTrackerDTO createdHabitTracker = personalDiaryService.createHabitTracker(
                userId,
                diaryId,
                habitTrackerDTO
        );
        return ResponseEntity.ok(createdHabitTracker);
    }

    @GetMapping("/personal_diary/{userId}/{diaryId}/habits")
    public ResponseEntity<List<HabitTrackerDTO>> getAllHabitTrackers(@PathVariable Long userId, @PathVariable Long diaryId) {
        return ResponseEntity.ok(personalDiaryService.getAllHabitTrackers(userId, diaryId));
    }

    @PostMapping("/personal_diary/{userId}/{diaryId}/todos")
    public ResponseEntity<ToDoListDTO> createToDoList(@PathVariable Long userId,
                                                      @PathVariable Long diaryId,
                                                      @Valid @RequestBody ToDoListDTO toDoListDTO) {
        ToDoListDTO createdToDoList = personalDiaryService.createToDoList(
                userId,
                diaryId,
                toDoListDTO
        );
        return ResponseEntity.ok(createdToDoList);
    }

    @GetMapping("/personal_diary/{userId}/{diaryId}/todos")
    public ResponseEntity<List<ToDoListDTO>> getAllToDos(@PathVariable Long userId, @PathVariable Long diaryId) {
        return ResponseEntity.ok(personalDiaryService.getAllToDoLists(userId, diaryId));
    }


}
