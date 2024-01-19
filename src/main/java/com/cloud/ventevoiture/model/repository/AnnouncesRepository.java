package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.entity.announces.Announce;
import com.cloud.ventevoiture.model.entity.announces.FavoriteAnnounces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncesRepository extends JpaRepository<Announce,Integer>{
    @Query(value = "Select * from announces where id_person = :id_person" ,nativeQuery = true)
    public List<Announce> findByIdPerson(@Param("id_person") int id_person);

}
