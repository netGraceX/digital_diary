package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "financial_diary")
@Data
public class FinancialDiaryEntity extends DiaryEntity {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private List<FinancialEntryEntity> entries;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private List<RecurringExpenseEntity> recurringExpenses;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private List<SavingGoalEntity> goals;
}
