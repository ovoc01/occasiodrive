package com.cloud.ventevoiture.controller.auth;

import com.cloud.ventevoiture.controller.request.RegistrationRequest;
import com.cloud.ventevoiture.model.services.AuthenticationService;
import com.cloud.ventevoiture.controller.request.AuthenticationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest register) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            authenticationService.register(register);
            map.put("message", "User registered successfully");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }




    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest login) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(login);
        try {
            String token = authenticationService.login(login);
            map.put("token", token);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }

}
