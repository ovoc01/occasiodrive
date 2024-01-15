package com.cloud.ventevoiture.controller.brand;

import com.cloud.ventevoiture.model.brand.Brand;
import com.cloud.ventevoiture.model.repository.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {
    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public Brand save(@RequestBody Brand brand){
        return brandRepository.save(brand);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    @GetMapping("/findOne")
    public Brand findOne(int id_brand){
        return brandRepository.findOne(id_brand);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    @GetMapping
    public List<Brand> listAll(){
        return brandRepository.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int id_brand){
        try {
            brandRepository.deleteById_brand(id_brand);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
