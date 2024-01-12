package com.cloud.ventevoiture.model.car;

import org.hibernate.annotations.ManyToAny;

import com.cloud.ventevoiture.model.category.Category;
import com.cloud.ventevoiture.model.model.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_car; 
    String registration;

    @ManyToOne
    @JoinColumn(name = "id_model")
    Model model;

    @ManyToOne
    @JoinColumn(name = "id_category")
    Category category;

    @ManyToOne
    @JoinColumn(name = "id_fuel_type")
    FuelType fuel_type;

    @ManyToOne
    @JoinColumn(name = "id_transmission")
    Transmission transmission;
    
    double engine_power;
    int manufacturing_year;
    double mile_age;

    
    public int getId_car() {
        return id_car;
    }
    public void setId_car(int id_car) {
        this.id_car = id_car;
    }
    public String getRegistration() {
        return registration;
    }
    public void setRegistration(String registration) {
        this.registration = registration;
    }
    public Model getModel() {
        return model;
    }
    public void setModel(Model model) {
        this.model = model;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public FuelType getFuel_type() {
        return fuel_type;
    }
    public void setFuel_type(FuelType fuel_type) {
        this.fuel_type = fuel_type;
    }
    public Transmission getTransmission() {
        return transmission;
    }
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }
    public double getEngine_power() {
        return engine_power;
    }
    public void setEngine_power(double engine_power) {
        this.engine_power = engine_power;
    }
    public int getManufacturing_year() {
        return manufacturing_year;
    }
    public void setManufacturing_year(int manufacturing_year) {
        this.manufacturing_year = manufacturing_year;
    }
    public double getMile_age() {
        return mile_age;
    }
    public void setMile_age(double mile_age) {
        this.mile_age = mile_age;
    }


    
}
