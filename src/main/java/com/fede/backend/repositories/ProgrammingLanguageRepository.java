package com.fede.backend.repositories;

import com.fede.backend.models.entities.ProgrammingLanguage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammingLanguageRepository extends CrudRepository<ProgrammingLanguage, Long> {

}
