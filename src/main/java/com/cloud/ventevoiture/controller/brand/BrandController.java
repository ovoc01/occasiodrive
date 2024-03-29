package com.cloud.ventevoiture.controller.brand;

import com.cloud.ventevoiture.controller.request.BrandRequest;
import com.cloud.ventevoiture.model.entity.brand.Brand;
import com.cloud.ventevoiture.model.repository.BrandRepository;
import com.cloud.ventevoiture.model.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandRepository brandRepository;
    private final BrandService brandService;


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody BrandRequest brandRequest){
        try{
            Brand brand = new Brand();
            brand.setBrand(brandRequest.getBrand());
            brandRepository.save(brand);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

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

    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    @GetMapping("/2")
    public List<Brand> listAll2(){
        return  brandRepository.findAll();
    }

    @GetMapping("/all")
    public List<Brand> listAll3(){
        return  brandRepository.findAll();
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
//    @GetMapping("/2")
//    public ResponseEntity<Map<String, Object>> listAll(@RequestParam(defaultValue = "0") int page,
//                                                       @RequestParam(defaultValue = "5") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Brand> brandPage = brandRepository.findAll(pageable);
//        List<Brand> brands = brandPage.getContent();
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("brands", brands);
//        response.put("totalPages", brandPage.getTotalPages());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

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
