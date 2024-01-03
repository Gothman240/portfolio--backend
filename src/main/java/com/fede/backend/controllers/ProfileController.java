package com.fede.backend.controllers;

import com.fede.backend.models.dto.ProfileDto;
import com.fede.backend.models.dto.mapper.DtoMapperProfile;
import com.fede.backend.models.entities.Profile;
import com.fede.backend.repositories.PersonRepository;
import com.fede.backend.services.ProfileService;
import com.fede.backend.services.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ValidationService validationService;

    @Autowired
    private ProfileService profileService;
    @Autowired
    private PersonRepository personRepository;

    public ProfileController( ValidationService validationService ) {
        this.validationService = validationService;
    }

    @PostMapping
    public ResponseEntity<?> create( @Valid @RequestBody Profile profile, BindingResult result, Authentication authentication ){
        if ( result.hasErrors() ){
            return  validationService.handleValidationErrors( result );
        }

        String username =( (String) authentication.getPrincipal());

        if ( profileService.hasProfile(username) ){
            return ResponseEntity.status( HttpStatus.FORBIDDEN ).body( String.format( "El usuario %s ya cuenta con un perfil", username) );
        }

        profileService.save( profile, username );

        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getProfile(@PathVariable String username){
        try {
            Profile profile = profileService.getProfileByUsername(username);

            if (profile != null) {
                return ResponseEntity.ok( DtoMapperProfile.builder().setProfile( profile ).build() );
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error obteniendo el perfil: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update( @Valid @RequestBody ProfileDto profile, BindingResult result, @PathVariable Long id, Authentication authentication){
        if ( result.hasErrors() ) {
            return validationService.handleValidationErrors( result );
        }

        if ( !profileService.hasProfileOwner( authentication.getName(), id) ){
           return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "No tienes los permisos necesarios" );
        }

        Optional<ProfileDto> optionalProfile = profileService.update( profile, id );

        if ( optionalProfile.isPresent() ){
            return ResponseEntity.status( HttpStatus.CREATED ).body( optionalProfile.orElseThrow() );
        }

        return ResponseEntity.notFound().build();

    }
}
