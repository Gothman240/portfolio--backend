package com.fede.backend;

import com.fede.backend.models.entities.*;
import com.fede.backend.models.enums.ExperienceType;
import com.fede.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class BackendApplication {
    @Autowired
    PasswordEncoder passwordEncoder;
    public static void main( String[] args ) {
        SpringApplication.run( BackendApplication.class, args );
    }

    @Bean
    public CommandLineRunner runner(RolRepository rolRepository, PersonRepository personRepository, ProfileRepository profileRepository, ProjectRepository projectRepository, ExperienceRepository experienceRepository, ProgrammingLanguageRepository programmingLanguageRepository, FrameworkRepository frameworkRepository ) {
        return args -> {
            Rol rolUser = new Rol("ROLE_USER");
            Rol rolAdmin = new Rol("ROLE_ADMIN");

            rolRepository.save( rolUser );
            rolRepository.save( rolAdmin );



            Person user = new Person( "putuca", passwordEncoder.encode("putuca") );
            user.getRoles().add( rolAdmin );

            personRepository.save( user );

//            Profile profile = new Profile( "putuca", "fea", "soy mala", "putuca.jpg", user );
//            user.setProfile( profile );
//            personRepository.save( user );
//            Project project = new Project( "nombre", "descripcion", "giturl", "deployurl", profile );
//            ProgrammingLanguage language = new ProgrammingLanguage("Java");
//            ProgrammingLanguage language2 = new ProgrammingLanguage("Vue");
//            project.getProgrammingLanguageList().add( language );
//            project.getProgrammingLanguageList().add( language2 );
//            profile.getProjectList().add( project );
//            programmingLanguageRepository.save( language );
//            programmingLanguageRepository.save( language2 );
//            Framework framework = new Framework("Srping");
//            project.getFrameworkList().add( framework );
//            frameworkRepository.save( framework );
//            framework.getProjectList().add( project );
//            projectRepository.save( project );
//
//            Experience experience = new Experience( "gorda", "soy muy gorda", LocalDate.now(), LocalDate.now(), ExperienceType.WORK_EXPERIENCE, profile );
//            experienceRepository.save( experience );
//            profileRepository.save( profile );
        };
    }
}
