package com.fede.backend.services;

import com.fede.backend.models.dto.ProjectDto;
import com.fede.backend.models.entities.Project;
import com.fede.backend.models.request.ProjectRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<ProjectDto> getAllProjectByUsername( String username);
    boolean hasProjectOwner(String username, Long id);
    void save(Project project, String username);
    void update( ProjectRequest project, Long id);
    Optional<ProjectDto> findById( Long id );
    void remove( Long id );
}
