package com.identifix.testing.repositories;

import com.identifix.testing.models.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface FamilyRepository extends CrudRepository<Family, Long> {
}
