package com.cloud.ventevoiture.model.entity.category;


import com.cloud.ventevoiture.model.entity.car.Motorisation;
import com.cloud.ventevoiture.model.entity.model.Model;
import com.cloud.ventevoiture.model.entity.model.ModelCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_category;
    String category;
    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private Set<Model> models;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    @JsonBackReference
    @JsonIgnoreProperties
    private Set<ModelCategory> modelCategories;


    @Transient
    List<Motorisation> motorisations;



    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
