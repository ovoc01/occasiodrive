package com.cloud.ventevoiture.model.repository;

import org.springframework.stereotype.Repository;

import com.cloud.ventevoiture.model.entity.car.Transmission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TransmissionRepository  extends JpaRepository<Transmission,Integer>{
   
   @Query(value = "Select * from v_motorisation_transmission where id_motorisation = :id_motorisation" ,nativeQuery = true)
   List<Transmission> findTransmissionByMotorisationId(@Param("id_motorisation") int id_motorisation);
}
