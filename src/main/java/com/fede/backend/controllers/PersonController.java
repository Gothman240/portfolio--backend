package com.fede.backend.controllers;

import com.fede.backend.models.dto.PersonDto;
import com.fede.backend.models.entities.Person;
import com.fede.backend.services.PersonService;
import com.fede.backend.services.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class PersonController {
    @Autowired
    private PersonService personService;
    private final ValidationService validationService;
    @Autowired
    public PersonController( ValidationService validationService ) {
        this.validationService = validationService;
    }

    @GetMapping
    public List<PersonDto> getAll(){
        return personService.findAll();
    }
    @PostMapping
    public ResponseEntity<?> create( @Valid @RequestBody Person person, BindingResult result ){
        if ( result.hasErrors() ){
            return validationService.handleValidationErrors( result );
        }
        return ResponseEntity.status( HttpStatus.CREATED ).body( personService.save( person ) );
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        Optional<PersonDto> dto = personService.findById( id );
        if ( dto.isPresent() ) {
            ResponseEntity.ok(dto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<PersonDto> dto = personService.findById( id );
        if ( dto.isPresent() ){
            personService.remove( id );
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
