package com.fede.backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String projectName;
    @NotBlank
    private String description;
    @NotNull
    private String tech;
    @NotBlank
    @URL
    private String githubUrl;
    @NotBlank
    @URL
    private String deployUrl;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profileProject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_framework",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "framework_id"),
            uniqueConstraints = {@UniqueConstraint( columnNames = {"project_id","framework_id"})}
    )
    private Set<Framework> frameworkList = new HashSet<>();

    public Project() {
    }

    public Project( String projectName, String description, String githubUrl, String deployUrl, Profile profile ) {
        this.projectName = projectName;
        this.description = description;
        this.githubUrl = githubUrl;
        this.deployUrl = deployUrl;
        this.profileProject = profile;
    }

}
