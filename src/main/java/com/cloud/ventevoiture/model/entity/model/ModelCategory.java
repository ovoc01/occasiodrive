package com.cloud.ventevoiture.model.entity.model;


import com.cloud.ventevoiture.model.entity.car.Motorisation;
import com.cloud.ventevoiture.model.entity.category.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;



@Entity
@Data
public class ModelCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModelCategory;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_model_category")
    private List<Motorisation> motorisations;

    @OneToOne
    @JoinColumn(name = "id_model")
    @JsonBackReference
    private Model model;

    /* @ManyToOne
    @JoinColumn(name = "id_category")
    @JsonBackReference
    private Category category; */
}
