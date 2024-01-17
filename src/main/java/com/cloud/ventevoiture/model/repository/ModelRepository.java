package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.entity.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    @Query(value = "Select * from model where id_model = :id_model" ,nativeQuery = true)
    public Model findOne(@Param("id_model") int id_model);

    @Query(value = "delete from model where id_model = :id_model", nativeQuery = true)
    public void deleteById_model(@Param("id_model") int id_model);

}
