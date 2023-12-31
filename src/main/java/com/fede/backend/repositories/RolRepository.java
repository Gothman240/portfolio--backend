package com.fede.backend.repositories;

import com.fede.backend.models.entities.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {
    Optional<Rol> findByName( String rolUser );
}
