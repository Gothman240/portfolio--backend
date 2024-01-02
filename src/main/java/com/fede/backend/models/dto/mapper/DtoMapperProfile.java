package com.fede.backend.models.dto.mapper;

import com.fede.backend.models.dto.ProfileDto;
import com.fede.backend.models.entities.Profile;

public class DtoMapperProfile {
    private Profile profile;

    private DtoMapperProfile() {
    }

    public static DtoMapperProfile builder(){
        return new DtoMapperProfile();
    }
    public DtoMapperProfile setProfile(Profile profile){
        this.profile = profile;
        return this;
    }

    public ProfileDto build(){
        if ( profile == null ) {
            throw new RuntimeException("No se proporcionó la Entidad Profile");
        }
        return new ProfileDto(this.profile.getId(), this.profile.getName(),
                this.profile.getLastname(), this.profile.getAboutMe(), this.profile.getProfilePicture() );
    }
}
