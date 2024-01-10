package com.cloud.ventevoiture.model.model;

import com.cloud.ventevoiture.model.brand.Brand;
import jakarta.persistence.*;

@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_model;

    @ManyToOne
    @JoinColumn(name = "id_brand")
    Brand brand;
    String model;

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
