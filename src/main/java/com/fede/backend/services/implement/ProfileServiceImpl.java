package com.fede.backend.services.implement;

import com.fede.backend.models.dto.ProfileDto;
import com.fede.backend.models.dto.mapper.DtoMapperProfile;
import com.fede.backend.models.entities.Person;
import com.fede.backend.models.entities.Profile;
import com.fede.backend.repositories.PersonRepository;
import com.fede.backend.repositories.ProfileRepository;
import com.fede.backend.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional
    public Profile getProfileByUsername( String username ) {
        return profileRepository.findByPersonUsername( username );
    }

    @Override
    public boolean hasProfileOwner( String username, Long id ) {
        Optional<Profile> optionalProfile = profileRepository.findById( id );

        return optionalProfile.map( profile -> profile.getPerson().getUsername().equals( username )).orElse( false );
    }

    @Override
    @Transactional
    public boolean hasProfile(String username) {
        Optional<Person> person = personRepository.findByUsername( username );

        return person.isPresent() && person.orElseThrow().getProfile() != null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileDto> findAll() {
        List<Profile> profileList = (List<Profile>) profileRepository.findAll();

        return profileList.stream()
                .map( profile -> DtoMapperProfile.builder().setProfile( profile ).build() )
                .collect( Collectors.toList());
    }

    @Override
    @Transactional
    public void save( Profile profile, String username ) {
        Person person = personRepository.findByUsername( username ).orElseThrow();

        person.setProfile( profile );
        profile.setPerson( person );

        profileRepository.save( profile );
        personRepository.save( person );

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfileDto> findById( Long id ) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<ProfileDto> update( ProfileDto profileDto, Long id ) {


        Optional<Profile> optionalProfile = profileRepository.findById( id );

        Profile profileDb = null;

        if ( optionalProfile.isPresent() ) {
            profileDb = optionalProfile.orElseThrow();
            profileDb.setName( profileDto.getName() );
            profileDb.setLastname( profileDto.getLastname() );
            profileDb.setAboutMe( profileDto.getAboutMe() );
            profileDb.setProfilePicture( profileDto.getProfilePicture() );

            profileRepository.save( profileDb );
        }

        return Optional.ofNullable( DtoMapperProfile.builder().setProfile( profileDb ).build() );
    }

    @Override
    @Transactional
    public void remove( Long id ) {

    }
}
