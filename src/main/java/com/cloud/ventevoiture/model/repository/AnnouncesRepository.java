package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.entity.announces.Announce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncesRepository extends JpaRepository<Announce,Integer>{
    
}
