package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findPersonByIdPerson(Integer id);
}
