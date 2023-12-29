package com.fede.backend.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String description;
    private String githubUrl;
    private String deployUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profileProject;

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
