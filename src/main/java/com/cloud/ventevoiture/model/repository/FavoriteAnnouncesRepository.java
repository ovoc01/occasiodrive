package com.cloud.ventevoiture.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloud.ventevoiture.model.entity.announces.FavoriteAnnounces;

@Repository
public interface FavoriteAnnouncesRepository extends JpaRepository<FavoriteAnnounces,Integer>{
    @Query(value = "Select * from favorite_announces where id_person = :id_person" ,nativeQuery = true)
    public List<FavoriteAnnounces> findByIdPerson(@Param("id_person") int id_person);

}
