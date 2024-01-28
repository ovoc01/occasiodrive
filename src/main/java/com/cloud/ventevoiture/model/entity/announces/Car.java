package com.cloud.ventevoiture.model.entity.announces;


import com.cloud.ventevoiture.model.entity.brand.Brand;
import com.cloud.ventevoiture.model.entity.car.FuelType;
import com.cloud.ventevoiture.model.entity.car.Motorisation;
import com.cloud.ventevoiture.model.entity.car.Transmission;
import com.cloud.ventevoiture.model.entity.car.version.Version;
import com.cloud.ventevoiture.model.entity.category.Category;
import com.cloud.ventevoiture.model.entity.model.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car", nullable = false)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_brand")
    private Brand brand;

    @OneToOne
    @JoinColumn(name = "id_model")
    private Model model;
    @OneToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @OneToOne
    @JoinColumn(name = "id_motorisation")
    private Motorisation motorisation;
    @OneToOne
    @JoinColumn(name = "id_transmission")
    private Transmission transmission;
    @OneToOne
    @JoinColumn(name = "id_version")
    private Version version;

    @OneToOne
    @JoinColumn(name = "id_fuel_type")
    private FuelType fuelType;


    @Column(name = "registration", length = 50)
    private String registration;


    @Column(name = "mile_age")
    private Double mileAge;


    @JsonProperty("brand")
    public Brand getBrand(){
        Brand b = Brand.builder().id_brand(this.brand.getId_brand()).brand(this.brand.getBrand()).build();
        return b;
    }

}