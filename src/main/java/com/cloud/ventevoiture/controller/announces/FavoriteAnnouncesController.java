package com.cloud.ventevoiture.controller.announces;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ventevoiture.model.entity.announces.FavoriteAnnounces;
import com.cloud.ventevoiture.model.repository.FavoriteAnnouncesRepository;

@RestController
@RequestMapping("/api/v1/favoriteAnnounces")
public class FavoriteAnnouncesController {
    private final FavoriteAnnouncesRepository favoriteAnnouncesRepository;

    public FavoriteAnnouncesController(FavoriteAnnouncesRepository favoriteAnnouncesRepository) {
        this.favoriteAnnouncesRepository = favoriteAnnouncesRepository;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<FavoriteAnnounces> fav = favoriteAnnouncesRepository.findAll();
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("listFavAnnounces", fav);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody FavoriteAnnounces favAnnounces){
        try {
            HashMap<String, Object> map = new HashMap<>();
            favoriteAnnouncesRepository.save(favAnnounces);
            map.put("message", "Save Favorite Announces successfully");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }

        
    @GetMapping("/{id_person}/person")
    public ResponseEntity<Object> findByPerson(@PathVariable int id_person) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            List<FavoriteAnnounces> fav = (List<FavoriteAnnounces>) favoriteAnnouncesRepository.findByIdPerson(id_person);
            if (fav.isEmpty()) {
                return new ResponseEntity<>("No favorite announcements found for the specified person.", HttpStatus.NOT_FOUND);
            }
            map.put("message", "success");
            map.put("listFavAnnounces", fav);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
