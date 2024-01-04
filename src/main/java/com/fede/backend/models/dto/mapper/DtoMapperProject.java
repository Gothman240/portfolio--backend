package com.fede.backend.models.dto.mapper;

import com.fede.backend.models.dto.ProjectDto;
import com.fede.backend.models.entities.Project;

public class DtoMapperProject {
    private Project project;

    private DtoMapperProject() {
    }

    public static DtoMapperProject builder(){
        return new DtoMapperProject();
    }

    public DtoMapperProject setProject(Project project){
        this.project = project;
        return this;
    }

    public ProjectDto build(){
        if ( project == null ) {
            throw new RuntimeException("No se proporcionó la Entidad Project");
        }
        return new ProjectDto(this.project.getId(), this.project.getProjectName(), this.project.getDescription(), this.project.getTech(), this.project.getGithubUrl(), this.project.getDeployUrl());
    }
}
