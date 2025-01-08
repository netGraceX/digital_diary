package com.netgrace.digital_diary.controllers;

import com.netgrace.digital_diary.domain.GoalDTO;
import com.netgrace.digital_diary.domain.PersonalDiaryDTO;
import com.netgrace.digital_diary.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService journalService) {
        this.diaryService = journalService;
    }


    //DIARY SECTION
    @PostMapping("/personal_diary")
    public ResponseEntity<PersonalDiaryDTO> createPersonalDiary(@RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO createdPersonalDiary = diaryService.createPersonalDiary(
                personalDiaryDTO
        );
        return ResponseEntity.ok(createdPersonalDiary);
    }

    @GetMapping("/personal_diary")
    public ResponseEntity<List<PersonalDiaryDTO>> getAllPersonalDiaries() {
        return ResponseEntity.ok(diaryService.getAllPersonalDiaries());
    }

    @GetMapping("/personal_diary/{id}")
    public PersonalDiaryDTO getPersonalDiaryById(@PathVariable Long id) {
        return diaryService.getPersonalDiaryById(id);
    }

    @PutMapping("/personal_diary/{id}")
    public ResponseEntity<PersonalDiaryDTO> updatePersonalDiary(@PathVariable Long id, @RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO updatedPersonalDiaryEntity = diaryService.updatePersonalDiary(id, personalDiaryDTO);
        return ResponseEntity.ok(updatedPersonalDiaryEntity);
    }

    @PatchMapping("/personal_diary/{id}")
    public ResponseEntity<PersonalDiaryDTO> patchPersonalDiary(@PathVariable Long id, @RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO updatedPersonalDiary = diaryService.patchPersonalDiary(id, personalDiaryDTO);
        return  ResponseEntity.ok(updatedPersonalDiary);
    }

    @DeleteMapping("/personal_diary/{id}")
    public ResponseEntity<Void> deletePersonalDiary(@PathVariable Long id) {
        diaryService.deletePersonalDiary(id);
        return ResponseEntity.noContent().build();
    }

    //GOAL SECTION

    @PostMapping("/personal_diary/goals/{diaryId}")
    public ResponseEntity<GoalDTO> createGoal(@PathVariable Long diaryId, @RequestBody GoalDTO goalDTO) {
        GoalDTO createdGoal = diaryService.createGoal(
                diaryId,
                goalDTO
        );
        return ResponseEntity.ok(createdGoal);
    }

    @GetMapping("/personal_diary/goals/{diaryId}")
    public ResponseEntity<List<GoalDTO>> getAllGoals(@PathVariable Long diaryId) {
        return ResponseEntity.ok(diaryService.getAllGoals(diaryId));
    }

    @GetMapping("/personal_diary/goals/goal/{goalId}")
    public GoalDTO getGoalById(@PathVariable Long goalId) {
        return diaryService.getGoalById(goalId);
    }


    @PutMapping("/personal_diary/goals/{id}")
    public ResponseEntity<GoalDTO> updateGoal(@PathVariable Long id, @RequestBody GoalDTO goalDTO) {
        GoalDTO updatedGoal = diaryService.updateGoal(id, goalDTO);
        return ResponseEntity.ok(updatedGoal);
    }

    @PatchMapping("/personal_diary/goals/{id}")
    public ResponseEntity<GoalDTO> patchGoal(@PathVariable Long id, @RequestBody GoalDTO goalDTO) {
        GoalDTO updatedGoal = diaryService.patchGoal(id, goalDTO);
        return  ResponseEntity.ok(goalDTO);
    }

    @DeleteMapping("/personal_diary/goals/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        diaryService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }

}
