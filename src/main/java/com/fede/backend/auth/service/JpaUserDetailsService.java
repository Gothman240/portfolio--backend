package com.fede.backend.auth.service;

import com.fede.backend.models.entities.Person;
import com.fede.backend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        Optional<Person> personOptional = personRepository.findByUsername( username );

        if ( personOptional.isEmpty() ) {
            throw new UsernameNotFoundException( String.format( "Username %s no existe en el sistema!", username ) );
        }

        Person person = personOptional.orElseThrow();

        List<GrantedAuthority> authorities = person.getRoles()
                .stream()
                .map( rol -> new SimpleGrantedAuthority( rol.getName() ) )
                .collect( Collectors.toList());

        return new User( person.getUsername(), person.getPassword(), true, true, true, true, authorities);
    }
}
