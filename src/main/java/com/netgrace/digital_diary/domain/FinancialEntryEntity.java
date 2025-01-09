package com.netgrace.digital_diary.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "financial_entry")
public class FinancialEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private EntryType type;
    @Enumerated(EnumType.STRING)
    private CategoryOrSource category;
    private Double amount;
    private LocalDate date;
    private String descriptionNote;
    @ManyToOne
    private FinancialDiaryEntity diary;

}
