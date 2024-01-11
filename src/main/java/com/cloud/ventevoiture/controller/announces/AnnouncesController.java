package com.cloud.ventevoiture.controller.announces;


import com.cloud.ventevoiture.controller.request.AnnouncesRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/announces")
public class AnnouncesController {


    public ResponseEntity<Object> newAnnounces(Authentication auth,@RequestBody AnnouncesRequest announcesRequest){
        HashMap<String ,Object> map = new HashMap<>();
        map.put("message","announces created");
        return ResponseEntity.ok(map);
    }



    @PostMapping(value = "/upload",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> upload(@RequestParam MultipartFile file){
        return ResponseEntity.ok("okey");
    }

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return null;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id")Integer id){
        return null;
    }


}
