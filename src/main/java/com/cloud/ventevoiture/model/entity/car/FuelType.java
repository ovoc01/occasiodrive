package com.cloud.ventevoiture.model.entity.car;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fuel_type")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_fuel_type;
    String label;

    public int getId_fuel_type() {
        return id_fuel_type;
    }
    public void setId_fuel_type(int id_fuel_type) {
        this.id_fuel_type = id_fuel_type;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    
    
}
