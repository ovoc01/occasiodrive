package com.cloud.ventevoiture.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.ventevoiture.model.entity.announces.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer>{
   
}
