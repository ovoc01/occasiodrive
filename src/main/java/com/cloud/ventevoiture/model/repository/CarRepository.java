package com.cloud.ventevoiture.model.repository;


import com.cloud.ventevoiture.model.car.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO car " +
            "(id_model, id_transmission, id_fuel_type, engine_power, registration, manufacturing, mile_age) " +
            "VALUES " +
            "(:idModel, :idTransmission, :idFuelType, :enginePower, :registration, :manufacturing, :mileAge)",
            nativeQuery = true)
    Car insertCar(
            @Param("idModel") Integer idModel,
            @Param("idTransmission") Integer idTransmission,
            @Param("idFuelType") Integer idFuelType,
            @Param("enginePower") Double enginePower,
            @Param("registration") String registration,
            @Param("manufacturing") Integer manufacturing,
            @Param("mileAge") Double mileAge
    );
}
