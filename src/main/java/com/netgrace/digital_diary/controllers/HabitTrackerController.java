package com.netgrace.digital_diary.controllers;

import com.netgrace.digital_diary.domain.HabitTrackerDTO;
import com.netgrace.digital_diary.services.HabitTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/diaries")
public class HabitTrackerController {

    private final HabitTrackerService habitTrackerService;

    @Autowired
    public HabitTrackerController(HabitTrackerService habitTrackerService) {
        this.habitTrackerService = habitTrackerService;
    }

    @GetMapping("habits/{id}")
    public HabitTrackerDTO getHabitTrackerById(@PathVariable Long id) {
        return habitTrackerService.getHabitTrackerById(id);
    }

    @PutMapping("/habits/{id}")
    public ResponseEntity<HabitTrackerDTO> updateHabitTracker(@PathVariable Long id, @RequestBody HabitTrackerDTO trackerDTO) {
        HabitTrackerDTO updatedTracker = habitTrackerService.updateHabitTracker(id, trackerDTO);
        return ResponseEntity.ok(updatedTracker);
    }

    @PatchMapping("/habits/{id}")
    public ResponseEntity<HabitTrackerDTO> patchHabit(@PathVariable Long id, @RequestBody HabitTrackerDTO trackerDTO) {
        HabitTrackerDTO updatedTracker = habitTrackerService.patchHabitTracker(id, trackerDTO);
        return  ResponseEntity.ok(updatedTracker);
    }

    @DeleteMapping("/habits/{id}")
    public ResponseEntity<Void> deleteHabitTracker(@PathVariable Long id) {
        habitTrackerService.deleteHabitTracker(id);
        return ResponseEntity.noContent().build();
    }

}
