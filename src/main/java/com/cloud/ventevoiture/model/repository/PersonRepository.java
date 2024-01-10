package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
