package com.netgrace.digital_diary.controllers;

import com.netgrace.digital_diary.domain.GoalDTO;
import com.netgrace.digital_diary.domain.HabitTrackerDTO;
import com.netgrace.digital_diary.domain.HabitTrackerEntity;
import com.netgrace.digital_diary.domain.PersonalDiaryDTO;
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


    //DIARY SECTION
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

    //GOAL SECTION

    @PostMapping("/personal_diary/goals/{diaryId}")
    public ResponseEntity<GoalDTO> createGoal(@PathVariable Long diaryId, @RequestBody GoalDTO goalDTO) {
        GoalDTO createdGoal = personalDiaryService.createGoal(
                diaryId,
                goalDTO
        );
        return ResponseEntity.ok(createdGoal);
    }

    @GetMapping("/personal_diary/goals/{diaryId}")
    public ResponseEntity<List<GoalDTO>> getAllGoals(@PathVariable Long diaryId) {
        return ResponseEntity.ok(personalDiaryService.getAllGoals(diaryId));
    }

    @GetMapping("/personal_diary/goals/goal/{goalId}")
    public GoalDTO getGoalById(@PathVariable Long goalId) {
        return personalDiaryService.getGoalById(goalId);
    }


    @PutMapping("/personal_diary/goals/{id}")
    public ResponseEntity<GoalDTO> updateGoal(@PathVariable Long id, @RequestBody GoalDTO goalDTO) {
        GoalDTO updatedGoal = personalDiaryService.updateGoal(id, goalDTO);
        return ResponseEntity.ok(updatedGoal);
    }

    @PatchMapping("/personal_diary/goals/{id}")
    public ResponseEntity<GoalDTO> patchGoal(@PathVariable Long id, @RequestBody GoalDTO goalDTO) {
        GoalDTO updatedGoal = personalDiaryService.patchGoal(id, goalDTO);
        return  ResponseEntity.ok(goalDTO);
    }

    @DeleteMapping("/personal_diary/goals/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        personalDiaryService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }

    //HABIT TRACKER

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


}
