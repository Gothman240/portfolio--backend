package com.fede.backend.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "programmingLanguageList")
    private List<Project> projectList = new ArrayList<>();

    public ProgrammingLanguage() {
    }

    public ProgrammingLanguage( String name ) {
        this.name = name;
    }

    public void addProject( Project project){
        projectList.add( project );
        project.getProgrammingLanguageList().add( this );
    }
}
