package com.cloud.ventevoiture.controller.statistique;

import com.cloud.ventevoiture.model.entity.statistique.AnnonceParMois;
import com.cloud.ventevoiture.model.repository.AnnonceParMoisRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/annonce-par-mois")
public class AnnonceParMoisController {

    public final AnnonceParMoisRepository annonceParMoisRepository;

    public AnnonceParMoisController(AnnonceParMoisRepository annonceParMoisRepository) {
        this.annonceParMoisRepository = annonceParMoisRepository;
    }

    @GetMapping
    public List<AnnonceParMois> findAll(){
        return annonceParMoisRepository.findAll();
    }
}
