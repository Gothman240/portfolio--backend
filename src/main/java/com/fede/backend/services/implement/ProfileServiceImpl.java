package com.fede.backend.services.implement;

import com.fede.backend.models.dto.ProfileDto;
import com.fede.backend.services.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Override
    public List<ProfileDto> findAll() {
        return null;
    }

    @Override
    public ProfileDto save() {
        return null;
    }

    @Override
    public Optional<ProfileDto> findById( Long id ) {
        return Optional.empty();
    }

    @Override
    public void remove( Long id ) {

    }
}
