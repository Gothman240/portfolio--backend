package com.fede.backend.repositories;

import com.fede.backend.models.entities.Framework;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameworkRepository extends CrudRepository<Framework, Long> {
}
