package com.cloud.ventevoiture.controller.announces;


import com.cloud.ventevoiture.controller.request.AnnouncesRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/announces")
public class AnnouncesController {

    @PostMapping
    public ResponseEntity<Object> newAnnounces(Authentication auth, @RequestBody AnnouncesRequest announcesRequest){
        HashMap<String ,Object> map = new HashMap<>();
        map.put("message","announces created");
        System.out.println(auth.getPrincipal());
        System.out.println(announcesRequest);
        return ResponseEntity.ok(map);
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
