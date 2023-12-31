package com.fede.backend.services.implement;

import com.fede.backend.models.dto.PersonDto;
import com.fede.backend.models.dto.mapper.DtoMapperPerson;
import com.fede.backend.models.entities.Person;
import com.fede.backend.models.entities.Rol;
import com.fede.backend.repositories.PersonRepository;
import com.fede.backend.repositories.RolRepository;
import com.fede.backend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findAll() {
        List<Person> personList= (List<Person>) personRepository.findAll();

        return personList.stream()
                .map( person -> DtoMapperPerson.builder().setPerson( person ).build())
                .collect( Collectors.toList());
    }

    @Override
    @Transactional
    public PersonDto save( Person person ) {
        person.setPassword( passwordEncoder.encode( person.getPassword() ) );

        Optional<Rol> optionalRol = rolRepository.findByName("ROL_USER");

        List<Rol> rolList = new ArrayList<>();

        if ( optionalRol.isPresent() ){
            rolList.add( optionalRol.orElseThrow() );
        }

       person.setRoles( rolList );

        return DtoMapperPerson.builder().setPerson( personRepository.save( person ) ).build();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonDto> findById( Long id ) {
        return personRepository.findById( id ).map( person -> DtoMapperPerson.builder().setPerson( person ).build() );
    }

    @Override
    @Transactional
    public void remove( Long id ) {
        personRepository.deleteById( id );
    }
}
