package com.cloud.ventevoiture.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncesRepository extends JpaRepository<Announces,Integer>{
    
}
