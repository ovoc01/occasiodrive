package com.cloud.ventevoiture.controller.category;


import com.cloud.ventevoiture.model.category.Category;
import com.cloud.ventevoiture.model.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/save")
    public Category save(@RequestBody Category category){
        return categoryRepository.save(category);
    }



    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    @GetMapping("/listAll")
    public List<Category> listAll(){
        return categoryRepository.findAll();
    }



    @GetMapping("/findOne")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    public Category findOne(@RequestParam int category_id){
        return categoryRepository.findOne(category_id);
    }



    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> delete(@RequestParam int id_category){
        try {
            categoryRepository.deleteById_category(id_category);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
