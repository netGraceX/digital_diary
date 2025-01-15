package com.netgrace.digital_diary.domain;

import lombok.Data;
import java.time.LocalDate;
@Data
public class RecurringExpenseDTO {
    private String name;
    private EntryType type;
    private CategoryOrSource category;
    private Double amount;
    private LocalDate date;
    private String descriptionNote;
    private Renewal frequency;
    private LocalDate renewalDate;
    private SubscriptionStatus status;
}
