package com.cloud.ventevoiture.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloud.ventevoiture.model.entity.announces.AnnouncesPicture;
    
@Repository
public interface AnnouncesPictureRepository extends JpaRepository<AnnouncesPicture,Integer> {
    
    @Query(value = "Select * from announces_picture where id_announce = :id_announce" ,nativeQuery = true)
    public List<AnnouncesPicture> findByIdAnnonce(@Param("id_announce") int id_announce);
}
