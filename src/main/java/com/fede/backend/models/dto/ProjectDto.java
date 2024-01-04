package com.fede.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String projectName;
    private String description;
    private String tech;
    private String githubUrl;
    private String deployUrl;

    public ProjectDto() {
    }

    public ProjectDto( Long id, String projectName, String description, String tech, String githubUrl, String deployUrl ) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.tech = tech;
        this.githubUrl = githubUrl;
        this.deployUrl = deployUrl;
    }
}
