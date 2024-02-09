package com.cloud.ventevoiture.controller.announces;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ventevoiture.model.entity.announces.Announce;
import com.cloud.ventevoiture.model.entity.announces.AnnouncesPicture;
import com.cloud.ventevoiture.model.entity.user.User;
import com.cloud.ventevoiture.model.repository.AnnouncesPictureRepository;
import com.cloud.ventevoiture.model.services.file.FileUploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pictures")
@RequiredArgsConstructor
public class AnnouncesPictureController {
    private final AnnouncesPictureRepository announcesPictureRepository;
    private final FileUploadService fileUploadService;
    @GetMapping("/{id_annonce}")
    public ResponseEntity<Object> findByIdAnnoune(@PathVariable int id_annonce){
        HashMap<String, Object> map = new HashMap<>();
        try {
            List<AnnouncesPicture> an = announcesPictureRepository.findByIdAnnonce(id_annonce);
            map.put("message", "success");
            map.put("listPicture", an);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findByteImage(){
        HashMap<String, Object> map = new HashMap<>();
        String path = "D:\\UNIVERSITY\\L3\\MR ROJO\\occasiodrive\\src\\main\\java\\com\\cloud\\ventevoiture\\controller\\announces\\imageTest\\image-1.jpg";
        try {
            byte [] b = fileUploadService.convertImageToByteArray(path);
            map.put("message", "success");
            map.put("bytes", b);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }

    // @PostMapping
    // public ResponseEntity<Object> save(){
        // HashMap<String, Object> map = new HashMap<>();
        // String path = "D:\\UNIVERSITY\\L3\\MR ROJO\\occasiodrive\\src\\main\\java\\com\\cloud\\ventevoiture\\controller\\announces\\imageTest\\image-2.jpg";
        // try {
        //     byte [] b = fileUploadService.convertImageToByteArray(path);
        //     AnnouncesPicture p = new AnnouncesPicture();
        //     Announce a = new Announce();
        //     a.setId(1);
        //     p.setId_picture(2);
        //     p.setAnnounce(a);
        //     p.setImagebyte(b);
        //     announcesPictureRepository.save(p);
        //     map.put("message", "insertion reussie");
        //     map.put("bytes", b);
        //     return new ResponseEntity<>(map, HttpStatus.OK);
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     map.put("error", e.getMessage());
        //     return ResponseEntity.badRequest().body(map);
        // }
    // }
}


