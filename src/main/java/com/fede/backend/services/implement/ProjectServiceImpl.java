package com.fede.backend.services.implement;

import com.fede.backend.models.dto.ProjectDto;
import com.fede.backend.models.dto.mapper.DtoMapperProject;
import com.fede.backend.models.entities.Person;
import com.fede.backend.models.entities.Profile;
import com.fede.backend.models.entities.Project;
import com.fede.backend.models.request.ProjectRequest;
import com.fede.backend.repositories.PersonRepository;
import com.fede.backend.repositories.ProfileRepository;
import com.fede.backend.repositories.ProjectRepository;
import com.fede.backend.services.ProjectService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    @Transactional
    public List<ProjectDto> getAllProjectByUsername( String username ) {
        Person person = personRepository.findByUsername( username ).orElseThrow(() -> new EntityExistsException("Usuario no encontrado") );

        return person.getProfile().getProjectList().stream()
                .map( project -> DtoMapperProject.builder().setProject( project ).build() )
                .collect( Collectors.toList());
    }

    @Override
    @Transactional
    public boolean hasProjectOwner( String username, Long id ) {
        Optional<Project> optionalProject = projectRepository.findById( id );

        return optionalProject.map( project -> project.getProfileProject().getPerson().getUsername().equals( username ) ).orElse( false );
    }

    @Override
    @Transactional
    public void save( Project project, String username ) {
        Person optionalPerson = personRepository.findByUsername( username ).orElseThrow();
        Profile profile = optionalPerson.getProfile();

        if (!profile.getProjectList().contains(project)) {
            profile.getProjectList().add(project);
            project.setProfileProject( profile );
        }

        projectRepository.save( project );
    }

    @Override
    @Transactional
    public void update( ProjectRequest project, Long id ) {
        Optional<Project> optionalProject = projectRepository.findById( id );
        Project project1 = null;
        if ( optionalProject.isPresent() ){
            project1 = optionalProject.orElseThrow();
            project1.setProjectName( project.getProjectName() );
            project1.setDescription( project.getDescription() );
            project1.setTech( project.getTech() );
            project1.setGithubUrl( project.getGithubUrl() );
            project1.setDeployUrl( project.getDeployUrl() );

            projectRepository.save( project1 );
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectDto> findById( Long id ){
        return projectRepository.findById( id ).map( project -> DtoMapperProject.builder().setProject( project ).build() );
    }

    @Override
    @Transactional
    public void remove( Long id ){
        projectRepository.deleteById( id );
    }


}
