package com.fede.backend;

import com.fede.backend.models.entities.Experience;
import com.fede.backend.models.entities.Person;
import com.fede.backend.models.entities.Profile;
import com.fede.backend.models.entities.Project;
import com.fede.backend.models.enums.ExperienceType;
import com.fede.backend.repositories.ExperienceRepository;
import com.fede.backend.repositories.ProfileRepository;
import com.fede.backend.repositories.PersonRepository;
import com.fede.backend.repositories.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner( PersonRepository personRepository, ProfileRepository profileRepository, ProjectRepository projectRepository, ExperienceRepository experienceRepository ){
		return args -> {
			Person user = new Person("putuca","putuca");
			Profile profile = new Profile("putuca", "fea", "soy mala", "putuca.jpg", user);
			user.setProfile( profile );
			personRepository.save( user );
			Project project = new Project("nombre","descripcion", "giturl", "deployurl", profile);
			profile.addProject( project );
			projectRepository.save( project );
			Experience experience = new Experience("gorda", "soy muy gorda", new Date(), new Date(), ExperienceType.WORK_EXPERIENCE, profile);
			experienceRepository.save( experience );
			profileRepository.save( profile	);
		} ;
	}
}
