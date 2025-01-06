package com.netgrace.digital_diary.controllers;

import com.netgrace.digital_diary.domain.PersonalDiaryDTO;
import com.netgrace.digital_diary.domain.PersonalDiaryEntity;
import com.netgrace.digital_diary.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/personal_diary")
    public ResponseEntity<PersonalDiaryDTO> createPersonalDiary(@RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO createdPersonalDiaryEntity = diaryService.createPersonalDiary(
                personalDiaryDTO
        );
        return ResponseEntity.ok(createdPersonalDiaryEntity);
    }

    @GetMapping("/personal_diary")
    public ResponseEntity<List<PersonalDiaryEntity>> getAllPersonalDiaries() {
        List<PersonalDiaryEntity> personalDiaries = diaryService.getAllPersonalDiaries();
        return ResponseEntity.ok(personalDiaries);
    }

    @GetMapping("/personal_diary/{id}")
    public ResponseEntity<PersonalDiaryEntity> getPersonalDiaryById(@PathVariable Long id) {
        return diaryService.getPersonalDiaryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/personal_diary/{id}")
    public ResponseEntity<PersonalDiaryDTO> updatePersonalDiary(@PathVariable Long id, @RequestBody PersonalDiaryDTO personalDiaryDTO) {
        PersonalDiaryDTO updatedPersonalDiaryEntity = diaryService.updatePersonalDiary(id, personalDiaryDTO);
        return ResponseEntity.ok(updatedPersonalDiaryEntity);
    }

    @DeleteMapping("/personal_diary/{id}")
    public ResponseEntity<Void> deletePersonalDiary(@PathVariable Long id) {
        diaryService.deletePersonalDiary(id);
        return ResponseEntity.noContent().build();
    }
}
