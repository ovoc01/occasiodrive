package com.cloud.ventevoiture.model.entity.car;

import com.cloud.ventevoiture.model.entity.car.version.Version;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "model_category_motorisation")
@Data
public class Motorisation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model_category_motorisation")
    private int idMotorisation;

    @Column(name = "engine_power")
    private Double enginePower;

    @Column(name = "description")
    private String description;
    
    
    @Transient
    List<Version> versions;

    @Transient
    List<Transmission> transmissions;

    @Transient
    List<FuelType> fuelTypes;


   @JsonProperty("fullDescription")
    public String getFullDescription() {
        return String.format("%s (%sCV)",description,enginePower);
    }
    
    
    @JsonProperty("versions")
    public List<Version> getVersions(){
        return this.versions;
    }


    @JsonProperty("transmissions")
    public List<Transmission> getTransmissions(){
        return this.transmissions;
    }

    @JsonProperty("fuelTypes")
    public List<FuelType> getFuelTypes(){
        return this.fuelTypes;
    }
}