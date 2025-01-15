package com.netgrace.digital_diary.controllers;

import com.netgrace.digital_diary.domain.FinancialDiaryDTO;
import com.netgrace.digital_diary.domain.FinancialDiaryEntity;
import com.netgrace.digital_diary.services.FinancialDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diaries")
public class FinancialDiaryController {

    private final FinancialDiaryService financialDiaryService;

    @Autowired
    public FinancialDiaryController(FinancialDiaryService financialDiaryService) {
        this.financialDiaryService = financialDiaryService;
    }

    @PostMapping("/financial_diary")
    public ResponseEntity<FinancialDiaryDTO> createFinancialDiary(@RequestBody FinancialDiaryDTO financialDiaryDTO) {
        FinancialDiaryDTO createdDiary = financialDiaryService.createFinancialDiary(financialDiaryDTO);
        return ResponseEntity.ok(createdDiary);
    }

    @GetMapping("/financial_diary")
    public ResponseEntity<List<FinancialDiaryDTO>> createFinancialDiary() {
        return ResponseEntity.ok(financialDiaryService.getAllFinancialDiaries());
    }

    @GetMapping("/financial_diary/{id}")
    public ResponseEntity<FinancialDiaryDTO> getFinancialDiaryById(@PathVariable Long id) {
        return ResponseEntity.ok(financialDiaryService.getFinancialDiaryById(id));
    }

    @PutMapping("/financial_diary/{id}")
    public ResponseEntity<FinancialDiaryDTO> updateFinancialDiary(@PathVariable Long id, @RequestBody FinancialDiaryDTO financialDiaryDTO) {
        FinancialDiaryDTO updatedDiary = financialDiaryService.updateFinancialDiary(id, financialDiaryDTO);
        return ResponseEntity.ok(updatedDiary);
    }

    @PatchMapping("/financial_diary/{id}")
    public ResponseEntity<FinancialDiaryDTO> patchFinancialDiary(@PathVariable Long id, @RequestBody FinancialDiaryDTO patchDetails) {
        FinancialDiaryDTO updatedDiary = financialDiaryService.patchFinancialDiary(id, patchDetails);
        return ResponseEntity.ok(updatedDiary);
    }

    @DeleteMapping("/financial_diary/{id}")
    public ResponseEntity<Void> deleteFinancialDiary(@PathVariable Long id) {
        financialDiaryService.deleteFinancialDiary(id);
        return ResponseEntity.noContent().build();
    }

}
