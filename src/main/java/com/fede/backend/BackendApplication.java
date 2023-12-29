package com.fede.backend;

import com.fede.backend.models.entities.*;
import com.fede.backend.models.enums.ExperienceType;
import com.fede.backend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class BackendApplication {

    public static void main( String[] args ) {
        SpringApplication.run( BackendApplication.class, args );
    }

    @Bean
    public CommandLineRunner runner( PersonRepository personRepository, ProfileRepository profileRepository, ProjectRepository projectRepository, ExperienceRepository experienceRepository, ProgrammingLanguageRepository programmingLanguageRepository, FrameworkRepository frameworkRepository ) {
        return args -> {
            Person user = new Person( "putuca", "putuca" );
            Profile profile = new Profile( "putuca", "fea", "soy mala", "putuca.jpg", user );
            user.setProfile( profile );
            personRepository.save( user );
            Project project = new Project( "nombre", "descripcion", "giturl", "deployurl", profile );
            ProgrammingLanguage language = new ProgrammingLanguage("Java");
            ProgrammingLanguage language2 = new ProgrammingLanguage("Vue");
            project.getProgrammingLanguageList().add( language );
            project.getProgrammingLanguageList().add( language2 );
            profile.getProjectList().add( project );
            programmingLanguageRepository.save( language );
            programmingLanguageRepository.save( language2 );
            Framework framework = new Framework("Srping");
            project.getFrameworkList().add( framework );
            frameworkRepository.save( framework );
            framework.getProjectList().add( project );
            projectRepository.save( project );

            Experience experience = new Experience( "gorda", "soy muy gorda", LocalDate.now(), LocalDate.now(), ExperienceType.WORK_EXPERIENCE, profile );
            experienceRepository.save( experience );
            profileRepository.save( profile );
        };
    }
}
