package com.cloud.ventevoiture.controller.announces;

import com.cloud.ventevoiture.controller.request.AdvancedSearchRequest;
import com.cloud.ventevoiture.controller.request.AnnouncesRequest;
import com.cloud.ventevoiture.model.entity.announces.Announce;
import com.cloud.ventevoiture.model.repository.AnnouncesRepository;

import com.cloud.ventevoiture.model.services.AnnouncesServices;
import com.cloud.ventevoiture.model.services.file.FileUploadService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import com.cloud.ventevoiture.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/announces")
@RequiredArgsConstructor
public class AnnouncesController {

    private final AnnouncesServices announcesServices;
    private final AnnouncesRepository announcesRepository;
    private final EntityManager entityManager;
    private final FileUploadService fileUploadService;



    @GetMapping
    public ResponseEntity<Object> findAll() {
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
    public ResponseEntity<Object> findByPerson(@PathVariable int id_person,Authentication authentication) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            User user = (User) authentication.getPrincipal();
            List<Announce> an = (List<Announce>) announcesRepository.findByIdPerson(user.getPerson().getIdPerson());
            if (an.isEmpty()) {
                map.put("message", "Pas d'annonces trouvé pour cette utilisateur");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
            map.put("message", "success");
            map.put("listAnnounces", an);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the request.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/person")
    public ResponseEntity<Object> findByAuth(Authentication authentication) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            User user = (User) authentication.getPrincipal();
            List<Announce> an = (List<Announce>) announcesServices.findByUser(user);
            if (an.isEmpty()) {

                return new ResponseEntity<>("No announcements found for the specified person.", HttpStatus.NOT_FOUND);
            }
            map.put("message", "success");
            map.put("listAnnounces", an);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the request.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<Object> newAnnounces(Authentication auth, @RequestBody AnnouncesRequest announcesRequest) {
        HashMap<String, Object> map = new HashMap<>();
        User user = (User) auth.getPrincipal();
        announcesServices.persist(announcesRequest, user);

        map.put("message", "announces created");
        return ResponseEntity.ok(map);
    }

    @PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> upload(@RequestParam MultipartFile file) {

        return ResponseEntity.ok("okey");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Integer id) {
        return null;
    }

    @GetMapping("/{idAnnounces}/validate")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Object> validate(@PathVariable("idAnnounces") Integer idAnnounces, Authentication auth) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = (User) auth.getPrincipal();
            announcesServices.valider(idAnnounces, user);
            map.put("message", "Annonces validez");
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }

    @GetMapping("/advancedSearch")
    public ResponseEntity<Object> advancedSearch(@RequestBody AdvancedSearchRequest advancedSearchRequest) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Announce> query = builder.createQuery(Announce.class);
            Root<Announce> root = query.from(Announce.class);
            List<Predicate> predicates = new ArrayList<>();            

            announcesServices.searchByKeyword(advancedSearchRequest.getKeyword(), predicates, builder, root);
            announcesServices.searchByDateAnnounce(advancedSearchRequest.getDateAnnounceMin(),
                    advancedSearchRequest.getDateAnnounceMax(), predicates, builder, root);
            announcesServices.searchByModel(advancedSearchRequest.getModel(), predicates, builder, root);
            announcesServices.searchByCategory(advancedSearchRequest.getCategory(), predicates, builder, root);
            announcesServices.searchByBrand(advancedSearchRequest.getBrand(), predicates, builder, root);
            announcesServices.searchByTransmission(advancedSearchRequest.getTransmission(), predicates, builder, root);
            announcesServices.searchByFuelType(advancedSearchRequest.getFuelType(), predicates, builder, root);
            announcesServices.searchByEnginePower(advancedSearchRequest.getEnginePowerMin(),
                    advancedSearchRequest.getEnginePowerMax(), predicates, builder, root);
            announcesServices.searchByManufacturingYear(advancedSearchRequest.getManufacturingYearMin(),
                    advancedSearchRequest.getManufacturingYearMax(), predicates, builder, root);
            announcesServices.searchByMileAge(advancedSearchRequest.getMileAgeMin(), advancedSearchRequest.getMileAgeMax(),
                    predicates, builder, root);
            announcesServices.searchBySellingPrice(advancedSearchRequest.getSellingPriceMin(),
                    advancedSearchRequest.getSellingPriceMax(), predicates, builder, root);

            query.where(predicates.toArray(new Predicate[0]));

            List<Announce> searchResults = entityManager.createQuery(query).getResultList();

            if (searchResults == null || searchResults.isEmpty()) {
                throw new Exception("Aucune annonce trouvée.");
            }

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("message", "success");
            responseMap.put("listAnnounce", searchResults);

            return new ResponseEntity<>(responseMap, HttpStatus.OK);

        } catch (Exception e) {
            // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> upload() {
        try {
            String base64Image = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAACYSURBVDhPYxgFJYwMDAwMjIwMjIwM";
            byte[] compressedImage = fileUploadService.compressBase64Image(base64Image);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("compressedImg", compressedImage);
            responseMap.put("decompressedImg", fileUploadService.decompressBase64Image(compressedImage));
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recent")
    public ResponseEntity<Object> sortByRecentAnnounces(){
        try {
            Sort sort = Sort.by(Sort.Direction.DESC,"dateAnnounces");
            List<Announce> annonces = announcesRepository.findAll(sort);
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("listAnnounces", annonces);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/PriceDESC")
    public ResponseEntity<Object> sortByPriceDesc(){
        try {
            Sort sort = Sort.by(Sort.Direction.DESC,"sellingPrice");
            List<Announce> annonces = announcesRepository.findAll(sort);
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("listAnnounces", annonces);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/PriceASC")
    public ResponseEntity<Object> sortByPriceAsc(){
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"sellingPrice");
            List<Announce> annonces = announcesRepository.findAll(sort);
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("listAnnounces", annonces);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/brandDESC")
    public ResponseEntity<Object> sortByBrandDesc(){
        try {
            Sort sort = Sort.by(Sort.Direction.DESC,"car.model.brand.brand");
            List<Announce> annonces = announcesRepository.findAll(sort);
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("listAnnounces", annonces);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/brandASC")
    public ResponseEntity<Object> sortByBrandAsc(){
        try {
            Sort sort = Sort.by(Sort.Direction.DESC,"car.model.brand.brand");
            List<Announce> annonces = announcesRepository.findAll(sort);
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("listAnnounces", annonces);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mileAgeASC")
    public ResponseEntity<Object> sortByMileAgeAsc(){
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"car.mileAge");
            List<Announce> annonces = announcesRepository.findAll(sort);
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("listAnnounces", annonces);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mileAgeDESC")
    public ResponseEntity<Object> sortByMileAgeDesc(){
        try {
            Sort sort = Sort.by(Sort.Direction.DESC,"car.mileAge");
            List<Announce> annonces = announcesRepository.findAll(sort);
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("listAnnounces", annonces);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id_announce}")
    public ResponseEntity<Object> deleteAnnounces(Authentication auth,@PathVariable("id_announce") Integer idAnnounce){
        HashMap<String,Object> map = new HashMap<>();
        try{
            User user = (User) auth.getPrincipal();
            System.out.println("Id announce :"+idAnnounce);
            announcesServices.deleteAnnouncesById(user,idAnnounce);
            map.put("message", "Annonce supprimé ");
            return ResponseEntity.ok(map);
        }catch(Exception e){
            e.printStackTrace();
            map.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }

    @PostMapping("/selling/{id_announce}")
    public ResponseEntity<Object> sellingAnnounces(Authentication auth, @PathVariable("id_announce")Integer idAnnounce){
        HashMap<String,Object> map = new HashMap<>();
        try{
            User user = (User) auth.getPrincipal();
            announcesServices.sellingAnnounce(user,idAnnounce);
            map.put("message", "Status de l'annonce modifié : voiture vendu ");
            return ResponseEntity.ok(map);
        }catch(Exception e){
        
            map.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }

}
