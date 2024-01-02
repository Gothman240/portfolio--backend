package com.fede.backend.services;

import com.fede.backend.models.dto.ProfileDto;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    List<ProfileDto> findAll();
    ProfileDto save();
    Optional<ProfileDto> findById(Long id);
    void remove(Long id);
}
