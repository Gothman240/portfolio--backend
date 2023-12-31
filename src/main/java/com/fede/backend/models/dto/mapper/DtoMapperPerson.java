package com.fede.backend.models.dto.mapper;

import com.fede.backend.models.dto.PersonDto;
import com.fede.backend.models.entities.Person;

public class DtoMapperPerson {
    private Person person;

    private DtoMapperPerson(){
    }

    public static DtoMapperPerson builder(){
        return new DtoMapperPerson();
    }
    public DtoMapperPerson setPerson(Person person){
        this.person = person;
        return this;
    }
    public PersonDto build(){
        if ( person == null ) {
            throw new RuntimeException("No se proporcionó la entidad Person");
        }
        return new PersonDto(this.person.getId(), this.person.getUsername());
    }

}
