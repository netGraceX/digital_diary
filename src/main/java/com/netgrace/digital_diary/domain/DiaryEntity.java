package com.netgrace.digital_diary.domain;

import com.netgrace.digital_diary.security.User;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;
    @Size(max = 255, message = "Description note cannot exceed 255 characters")
    private String description;
    private LocalDate creationDate;
    @ManyToOne
    private User appUser;

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDate.now();
    }

}