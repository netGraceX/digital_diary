package com.netgrace.digital_diary.controllers;

import com.netgrace.digital_diary.domain.GoalDTO;
import com.netgrace.digital_diary.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/diaries")
public class GoalController {

    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/goals/{goalId}")
    public GoalDTO getGoalById(@PathVariable Long goalId) {
        return goalService.getGoalById(goalId);
    }


    @PutMapping("/goals/{id}")
    public ResponseEntity<GoalDTO> updateGoal(@PathVariable Long id, @RequestBody GoalDTO goalDTO) {
        GoalDTO updatedGoal = goalService.updateGoal(id, goalDTO);
        return ResponseEntity.ok(updatedGoal);
    }

    @PatchMapping("/goals/{id}")
    public ResponseEntity<GoalDTO> patchGoal(@PathVariable Long id, @RequestBody GoalDTO goalDTO) {
        GoalDTO updatedGoal = goalService.patchGoal(id, goalDTO);
        return  ResponseEntity.ok(updatedGoal);
    }

    @DeleteMapping("/goals/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }


}
