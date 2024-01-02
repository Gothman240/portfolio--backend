package com.fede.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private Long id;
    private String name;
    private String lastname;
    private String aboutMe;
    private String profilePicture;

    public ProfileDto() {
    }

    public ProfileDto( Long id, String name, String lastname, String aboutMe, String profilePicture ) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.aboutMe = aboutMe;
        this.profilePicture = profilePicture;
    }
}
