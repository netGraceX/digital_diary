package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class FinancialDiaryDTO {
    @NotNull(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;
    @Size(max = 255, message = "Description note cannot exceed 255 characters")
    private String description;
    private List<FinancialEntryDTO> entries;
    private List<RecurringExpenseDTO> recurringExpenses;
    private List<SavingGoalDTO> goals;
}
