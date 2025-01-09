package com.netgrace.digital_diary.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "recurring_expenses")
public class RecurringExpenseEntity extends FinancialEntryEntity {
    @Enumerated(EnumType.STRING)
    private Renewal frequency;
    private LocalDate renewalDate;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

}
