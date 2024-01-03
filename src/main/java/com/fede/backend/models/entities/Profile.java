package com.fede.backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @Size(max = 255)
    private String aboutMe;
    private String profilePicture;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @OneToMany(mappedBy = "profileProject", fetch = FetchType.EAGER)
    private List<Project> projectList = new ArrayList<>();
    @OneToMany(mappedBy = "profileExperience", fetch = FetchType.EAGER)
    private List<Experience> experienceList = new ArrayList<>();

    public Profile() {
    }

    public Profile( String name, String lastname, String aboutMe, String profilePicture, Person user ) {
        this.name = name;
        this.lastname = lastname;
        this.aboutMe = aboutMe;
        this.profilePicture = profilePicture;
        this.person = user;
    }

   public void addProject(Project project){
        project.setProfileProject( this );
        this.projectList.add( project );
   }

    public void addExperience(Experience experience){
        experience.setProfileExperience( this );
        this.experienceList.add( experience );
    }
}
