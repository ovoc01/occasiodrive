package com.cloud.ventevoiture.controller.category;


import com.cloud.ventevoiture.controller.request.CategoryRequest;
import com.cloud.ventevoiture.model.entity.category.Category;
import com.cloud.ventevoiture.model.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody CategoryRequest category){
        try{
            Category c = new Category();
            c.setCategory(category.getCategory());
            categoryRepository.save(c);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    @GetMapping
    public List<Category> listAll(){
        return categoryRepository.findAll();
    }

    @GetMapping("all")
    public List<Category> listAll2(){
        return categoryRepository.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    @GetMapping("/{id_category}")
    public Category findOne(@PathVariable int id_category){
        return categoryRepository.findOne(id_category);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int id_category){
        try {
            categoryRepository.deleteById_category(id_category);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
