package com.cloud.ventevoiture.model.car;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transmission")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_transmission;
    String name;

    public int getId_transmission() {
        return id_transmission;
    }
    public void setId_transmission(int id_transmission) {
        this.id_transmission = id_transmission;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
