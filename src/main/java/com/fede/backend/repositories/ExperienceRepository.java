package com.fede.backend.repositories;

import com.fede.backend.models.entities.Experience;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Long> {
}
