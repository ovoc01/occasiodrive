package com.cloud.ventevoiture.controller.model;

import com.cloud.ventevoiture.controller.request.ModelRequest;
import com.cloud.ventevoiture.model.entity.model.Model;
import com.cloud.ventevoiture.model.repository.ModelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/models")
public class ModelController {
    private final ModelRepository modelRepository;

    public ModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> save (@RequestBody ModelRequest model){
        try{
            Model m = new Model();
            m.setModel(model.getModel());
            modelRepository.save(m);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    @GetMapping
    public List<Model> listAll(){
        return modelRepository.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    @GetMapping("{id_model}")
    public Model findOne(@PathVariable int id_model){
        return modelRepository.findOne(id_model);
    }

    @GetMapping("/all")
    public List<Model> listAll2(){
        return modelRepository.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int id_model){
        try {
            modelRepository.deleteById_model(id_model);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
