package com.cloud.ventevoiture.model.entity.model;

import com.cloud.ventevoiture.model.entity.brand.Brand;
import com.cloud.ventevoiture.model.entity.category.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_model;

    @ManyToMany
    @JoinTable(
            name = "model_category",
            joinColumns = {@JoinColumn(name = "id_model")},
            inverseJoinColumns = {@JoinColumn(name = "id_category")}
    )
    private Set<Category> categories;
    @OneToOne
    @JoinColumn(name = "id_brand")
    @JsonIgnoreProperties("models")
    Brand brand;
    String model;

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public int getId_model() {
        return id_model;
    }

    public void setId_model(int id_model) {
        this.id_model = id_model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
