package com.cloud.ventevoiture.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloud.ventevoiture.model.entity.car.FuelType;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Integer>{

      @Query(value = "Select * from v_motorisation_fuel_type where id_motorisation = :id_motorisation" ,nativeQuery = true)
      public List<FuelType> findFuelTypeByMotorisationId(@Param("id_motorisation") int id_motorisation);
}
