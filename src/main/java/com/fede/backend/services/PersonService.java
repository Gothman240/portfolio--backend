package com.fede.backend.services;

import com.fede.backend.models.dto.PersonDto;
import com.fede.backend.models.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonDto> findAll();
    PersonDto save(Person person);
    Optional<PersonDto> findById(Long id);
    void remove (Long id);
}
