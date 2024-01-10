package com.cloud.ventevoiture.controller.demo;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@PreAuthorize("hasRole('ADMIN')")
public class DemoController {
    @GetMapping
    public String demo(Authentication authentication){
        if(authentication != null&& authentication.isAuthenticated()){
            return "Hello "+authentication.getPrincipal();
        }
        return "Hello World";
    }
}
