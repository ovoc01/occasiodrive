package com.cloud.ventevoiture;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cloud.ventevoiture.model.services.file.FileUploadService;

@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        
        SpringApplication.run(SecurityApplication.class, args);
    }

}
