package com.netgrace.digital_diary.controllers;

import com.netgrace.digital_diary.domain.FinancialDiaryDTO;
import com.netgrace.digital_diary.services.FinancialDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/diaries")
public class FinancialDiaryController {

    private final FinancialDiaryService financialDiaryService;

    @Autowired
    public FinancialDiaryController(FinancialDiaryService financialDiaryService) {
        this.financialDiaryService = financialDiaryService;
    }

    @PostMapping("/financial_diary/{userId}")
    public ResponseEntity<FinancialDiaryDTO> createFinancialDiary(@PathVariable Long userId,
                                                                  @Valid @RequestBody FinancialDiaryDTO financialDiaryDTO) {
        FinancialDiaryDTO createdDiary = financialDiaryService.createFinancialDiary(userId, financialDiaryDTO);
        return ResponseEntity.ok(createdDiary);
    }

    @GetMapping("/financial_diary/{userId}")
    public ResponseEntity<List<FinancialDiaryDTO>> getAllFinancialDiaries(@PathVariable Long userId) {
        return ResponseEntity.ok(financialDiaryService.getAllFinancialDiaries(userId));
    }

    @GetMapping("/financial_diary/{userId}/{id}")
    public ResponseEntity<FinancialDiaryDTO> getFinancialDiaryById(@PathVariable Long userId, @PathVariable Long id) {
        return ResponseEntity.ok(financialDiaryService.getFinancialDiaryById(userId, id));
    }

    @PutMapping("/financial_diary/{userId}/{id}")
    public ResponseEntity<FinancialDiaryDTO> updateFinancialDiary(@PathVariable Long userId,
                                                                  @PathVariable Long id,
                                                                  @Valid @RequestBody FinancialDiaryDTO financialDiaryDTO) {
        FinancialDiaryDTO updatedDiary = financialDiaryService.updateFinancialDiary(userId, id, financialDiaryDTO);
        return ResponseEntity.ok(updatedDiary);
    }

    @PatchMapping("/financial_diary/{userId}/{id}")
    public ResponseEntity<FinancialDiaryDTO> patchFinancialDiary(@PathVariable Long userId,
                                                                 @PathVariable Long id,
                                                                 @Valid @RequestBody FinancialDiaryDTO patchDetails) {
        FinancialDiaryDTO updatedDiary = financialDiaryService.patchFinancialDiary(userId, id, patchDetails);
        return ResponseEntity.ok(updatedDiary);
    }

    @DeleteMapping("/financial_diary/{userId}/{id}")
    public ResponseEntity<Void> deleteFinancialDiary(@PathVariable Long userId, @PathVariable Long id) {
        financialDiaryService.deleteFinancialDiary(userId, id);
        return ResponseEntity.noContent().build();
    }

}
