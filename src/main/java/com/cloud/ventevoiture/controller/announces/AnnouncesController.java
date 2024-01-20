package com.cloud.ventevoiture.controller.announces;

import com.cloud.ventevoiture.controller.request.AnnouncesRequest;
import com.cloud.ventevoiture.model.entity.announces.Announce;
import com.cloud.ventevoiture.model.repository.AnnouncesRepository;

import com.cloud.ventevoiture.model.services.AnnouncesServices;
import com.cloud.ventevoiture.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/announces")
@RequiredArgsConstructor
public class AnnouncesController {

    private final AnnouncesServices announcesServices;
    
    private final AnnouncesRepository announcesRepository;




    // @PostMapping()
    // public SomeEnityData postMethodName(@RequestBody SomeEnityData entity) {
    //     //TODO: process POST request
        
    //     return entity;
    // }

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try {
            List<Announce> annonces = announcesRepository.findAll();
            System.out.println(annonces);
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("listAnnounces", annonces);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id_person}/person")
    public ResponseEntity<Object> findByPerson(@PathVariable int id_person) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            List<Announce> an = (List<Announce>) announcesRepository.findByIdPerson(id_person);
            if (an.isEmpty()) {
                return new ResponseEntity<>("No announcements found for the specified person.", HttpStatus.NOT_FOUND);
            }
            map.put("message", "success");
            map.put("listAnnounces", an);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PostMapping
    public ResponseEntity<Object> newAnnounces(Authentication auth,@RequestBody AnnouncesRequest announcesRequest){
        HashMap<String ,Object> map = new HashMap<>();
        User user = (User) auth.getPrincipal();
        announcesServices.persist(announcesRequest,user);
       
        map.put("message","announces created");
        return ResponseEntity.ok(map);
    }


    @PostMapping(value = "/upload",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> upload(@RequestParam MultipartFile file){

        return ResponseEntity.ok("okey");
    }



    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id")Integer id){
        return null;
    }

    @GetMapping("/{idAnnounces}/validate")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Object> validate(@PathVariable("idAnnounces") Integer idAnnounces, Authentication auth) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = (User) auth.getPrincipal();
            announcesServices.valider(idAnnounces,user);
            map.put("message","Annonces validez");
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }


}
