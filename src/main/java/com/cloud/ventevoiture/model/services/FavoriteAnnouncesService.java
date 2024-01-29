package com.cloud.ventevoiture.model.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cloud.ventevoiture.model.entity.announces.Announce;
import com.cloud.ventevoiture.model.entity.announces.FavoriteAnnounces;
import com.cloud.ventevoiture.model.entity.user.Person;
import com.cloud.ventevoiture.model.repository.AnnouncesRepository;
import com.cloud.ventevoiture.model.repository.FavoriteAnnouncesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteAnnouncesService {
    private final FavoriteAnnouncesRepository favRepository; 
    private final AnnouncesRepository announcesRepository;

    public void saveFavoriteAnnounce(Person person, Integer idAnnounce) {
        Optional<Announce> optionalAnnounce = announcesRepository.findById(idAnnounce);
        if (optionalAnnounce.isPresent()) {
            FavoriteAnnounces favoriteAnnounces = new FavoriteAnnounces();
            favoriteAnnounces.setPerson(person);
            favoriteAnnounces.setAnnounces(optionalAnnounce.get()); 
            favRepository.save(favoriteAnnounces);
        } else {
            throw new IllegalArgumentException("L'annonce avec l'ID spécifié n'existe pas.");
        }
    }

    // public Announce findAnnounceById(Integer id){
    //     Optional<Announce> optionalAnnounce = announcesRepository.findById(id);
    //     return optionalAnnounce.orElse(null); 
    // }

}
