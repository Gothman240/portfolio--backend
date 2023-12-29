package com.fede.backend.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_languages",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "programmingLanguage_id"),
            uniqueConstraints = {@UniqueConstraint( columnNames = {"project_id","programmingLanguage_id"})}
    )
    private List<ProgrammingLanguage> programmingLanguageList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_framework",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "framework_id"),
            uniqueConstraints = {@UniqueConstraint( columnNames = {"project_id","framework_id"})}
    )
    private List<Framework> frameworkList = new ArrayList<>();

    public Project() {
    }

    public Project( String projectName, String description, String githubUrl, String deployUrl,           Profile profile ) {
        this.projectName = projectName;
        this.description = description;
        this.githubUrl = githubUrl;
        this.deployUrl = deployUrl;
        this.profileProject = profile;
    }

    public Project( String projectName, String description, String githubUrl, String deployUrl, Profile profileProject, List<ProgrammingLanguage> programmingLanguageList ) {
        this.projectName = projectName;
        this.description = description;
        this.githubUrl = githubUrl;
        this.deployUrl = deployUrl;
        this.profileProject = profileProject;
        this.programmingLanguageList = programmingLanguageList;
    }

    private void addLanguagePrograming (ProgrammingLanguage language){
        programmingLanguageList.add( language );
        language.getProjectList().add( this );
    }
}
