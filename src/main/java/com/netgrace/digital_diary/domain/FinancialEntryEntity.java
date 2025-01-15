package com.netgrace.digital_diary.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "financial_entry")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FinancialEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_entry_seq")
    @SequenceGenerator(name = "financial_entry_seq", sequenceName = "financial_entry_seq", allocationSize = 1)
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
