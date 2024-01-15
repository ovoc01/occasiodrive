package com.cloud.ventevoiture.controller.car;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    @GetMapping("/init")
    public ResponseEntity<Object> getInitProperties(){
        return ResponseEntity.ok().body("Hello World");
    }
}
