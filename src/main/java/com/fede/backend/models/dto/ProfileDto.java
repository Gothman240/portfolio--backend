package com.fede.backend.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @Size(max = 255)
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
