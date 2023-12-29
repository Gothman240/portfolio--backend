package com.fede.backend.models.entities;

import com.fede.backend.models.enums.ExperienceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private Date startDate;
    private Date endDate;
    private ExperienceType type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profileExperience;

    public Experience() {
    }

    public Experience( String name, String description, Date startDate, Date endDate, ExperienceType type, Profile profileExperience ) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.profileExperience = profileExperience;
    }

}
