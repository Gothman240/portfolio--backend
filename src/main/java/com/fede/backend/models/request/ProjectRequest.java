package com.fede.backend.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class ProjectRequest {
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

    public ProjectRequest( String projectName, String description, String tech, String githubUrl, String deployUrl ) {
        this.projectName = projectName;
        this.description = description;
        this.tech = tech;
        this.githubUrl = githubUrl;
        this.deployUrl = deployUrl;
    }
}
