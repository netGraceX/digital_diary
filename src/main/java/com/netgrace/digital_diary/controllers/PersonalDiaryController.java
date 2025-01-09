package com.netgrace.digital_diary.controllers;

import com.netgrace.digital_diary.domain.GoalDTO;
import com.netgrace.digital_diary.domain.HabitTrackerDTO;
import com.netgrace.digital_diary.domain.PersonalDiaryDTO;
import com.netgrace.digital_diary.domain.ToDoListDTO;
import com.netgrace.digital_diary.services.PersonalDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/diaries")
public class PersonalDiaryController {

    private final PersonalDiaryService personalDiaryService;

    @Autowired
    public PersonalDiaryController(PersonalDiaryService journalService) {
        this.personalDiaryService = journalService;
    }

    @PostMapping("/personal_diary")
    public ResponseEntity<PersonalDiaryDTO> createPersonalDiary(@RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO createdPersonalDiary = personalDiaryService.createPersonalDiary(
                personalDiaryDTO
        );
        return ResponseEntity.ok(createdPersonalDiary);
    }

    @GetMapping("/personal_diary")
    public ResponseEntity<List<PersonalDiaryDTO>> getAllPersonalDiaries() {
        return ResponseEntity.ok(personalDiaryService.getAllPersonalDiaries());
    }

    @GetMapping("/personal_diary/{id}")
    public PersonalDiaryDTO getPersonalDiaryById(@PathVariable Long id) {
        return personalDiaryService.getPersonalDiaryById(id);
    }

    @PutMapping("/personal_diary/{id}")
    public ResponseEntity<PersonalDiaryDTO> updatePersonalDiary(@PathVariable Long id, @RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO updatedPersonalDiaryEntity = personalDiaryService.updatePersonalDiary(id, personalDiaryDTO);
        return ResponseEntity.ok(updatedPersonalDiaryEntity);
    }

    @PatchMapping("/personal_diary/{id}")
    public ResponseEntity<PersonalDiaryDTO> patchPersonalDiary(@PathVariable Long id, @RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO updatedPersonalDiary = personalDiaryService.patchPersonalDiary(id, personalDiaryDTO);
        return  ResponseEntity.ok(updatedPersonalDiary);
    }

    @DeleteMapping("/personal_diary/{id}")
    public ResponseEntity<Void> deletePersonalDiary(@PathVariable Long id) {
        personalDiaryService.deletePersonalDiary(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/personal_diary/{diaryId}/goals")
    public ResponseEntity<GoalDTO> createGoal(@PathVariable Long diaryId, @RequestBody GoalDTO goalDTO) {
        GoalDTO createdGoal = personalDiaryService.createGoal(
                diaryId,
                goalDTO
        );
        return ResponseEntity.ok(createdGoal);
    }

    @GetMapping("/personal_diary/{diaryId}/goals")
    public ResponseEntity<List<GoalDTO>> getAllGoals(@PathVariable Long diaryId) {
        return ResponseEntity.ok(personalDiaryService.getAllGoals(diaryId));
    }

    @PostMapping("/personal_diary/{diaryId}/habits")
    public ResponseEntity<HabitTrackerDTO> createHabitTracker(@PathVariable Long diaryId, @RequestBody HabitTrackerDTO habitTrackerDTO) {
        HabitTrackerDTO createdHabitTracker = personalDiaryService.createHabitTracker(
                diaryId,
                habitTrackerDTO
        );
        return ResponseEntity.ok(createdHabitTracker);
    }

    @GetMapping("/personal_diary/{diaryId}/habits")
    public ResponseEntity<List<HabitTrackerDTO>> getAllHabitTrackers(@PathVariable Long diaryId) {
        return ResponseEntity.ok(personalDiaryService.getAllHabitTrackers(diaryId));
    }

    @PostMapping("/personal_diary/{diaryId}/todos")
    public ResponseEntity<ToDoListDTO> createToDoList(@PathVariable Long diaryId, @RequestBody ToDoListDTO toDoListDTO) {
        ToDoListDTO createdToDoList = personalDiaryService.createToDoList(
                diaryId,
                toDoListDTO
        );
        return ResponseEntity.ok(createdToDoList);
    }

    @GetMapping("/personal_diary/{diaryId}/todos")
    public ResponseEntity<List<ToDoListDTO>> getAllToDos(@PathVariable Long diaryId) {
        return ResponseEntity.ok(personalDiaryService.getAllToDoLists(diaryId));
    }


}
