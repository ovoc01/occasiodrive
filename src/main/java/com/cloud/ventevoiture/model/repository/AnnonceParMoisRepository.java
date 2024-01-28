package com.cloud.ventevoiture.model.repository;

import com.cloud.ventevoiture.model.entity.statistique.AnnonceParMois;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceParMoisRepository extends JpaRepository<AnnonceParMois,Integer> {
   
}
