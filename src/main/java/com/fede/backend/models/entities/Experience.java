package com.fede.backend.models.entities;

import com.fede.backend.models.enums.ExperienceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ExperienceType type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profileExperience;

    public Experience() {
    }

    public Experience( String name, String description, LocalDate startDate, LocalDate endDate, ExperienceType type, Profile profileExperience ) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.profileExperience = profileExperience;
    }

}
