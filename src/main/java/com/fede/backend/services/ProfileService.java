package com.fede.backend.services;

import com.fede.backend.models.dto.ProfileDto;
import com.fede.backend.models.entities.Profile;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Profile getProfileByUsername(String username);
    boolean hasProfileOwner(String username, Long id);
    boolean hasProfile(String username);
    List<ProfileDto> findAll();
    void save( Profile profile, String username );
    Optional<ProfileDto> findById(Long id);
    Optional<ProfileDto> update( ProfileDto profileDto, Long id );
    void remove(Long id);
}
