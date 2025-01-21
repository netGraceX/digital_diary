package com.netgrace.digital_diary.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
public class FinancialEntryDTO {
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    private EntryType type;
    private CategoryOrSource category;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;
    private LocalDate date;
    @Size(max = 255, message = "Description note cannot exceed 255 characters")
    private String descriptionNote;
}
