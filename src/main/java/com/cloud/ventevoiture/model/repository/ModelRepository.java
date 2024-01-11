package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModelRepository extends JpaRepository<Model, String> {
    @Query(value = "Select * from model where id_model = :id_model" ,nativeQuery = true)
    public Model findOne(@Param("id_model") int id_model);

    @Query(value = "delete from model where id_model = :id_model", nativeQuery = true)
    public void deleteById_model(@Param("id_model") int id_model);

}
