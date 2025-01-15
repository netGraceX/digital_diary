package com.netgrace.digital_diary.domain;

import lombok.Data;
import java.time.LocalDate;
@Data
public class FinancialEntryDTO {
    private String name;
    private EntryType type;
    private CategoryOrSource category;
    private Double amount;
    private LocalDate date;
    private String descriptionNote;
}
