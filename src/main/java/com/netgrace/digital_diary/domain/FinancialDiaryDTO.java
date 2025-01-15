package com.netgrace.digital_diary.domain;

import lombok.Data;
import java.util.List;

@Data
public class FinancialDiaryDTO {
    private String title;
    private String description;
    private List<FinancialEntryDTO> entries;
    private List<RecurringExpenseDTO> recurringExpenses;
    private List<SavingGoalDTO> goals;
}
