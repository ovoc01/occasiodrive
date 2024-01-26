package com.cloud.ventevoiture.controller.brand;

import com.cloud.ventevoiture.model.entity.brand.Brand;
import com.cloud.ventevoiture.model.repository.BrandRepository;
import com.cloud.ventevoiture.model.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandRepository brandRepository;
    private final BrandService brandService;


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
        
        return  brandService.initAllNeededProps();
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
