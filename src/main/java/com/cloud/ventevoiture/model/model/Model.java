package com.cloud.ventevoiture.model.model;

import com.cloud.ventevoiture.model.brand.Brand;
import com.cloud.ventevoiture.model.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_model;

    @OneToOne
    @JoinColumn(name="id_category")
    Category category;
    @OneToOne
    @JoinColumn(name = "id_brand")
    Brand brand;
    String model;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
